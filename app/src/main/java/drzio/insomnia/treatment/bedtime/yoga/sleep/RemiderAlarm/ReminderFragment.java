package drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.CustomReminderData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

@SuppressLint("WrongConstant")
public class ReminderFragment extends Fragment {
    private static final String TAG = "ReminderFragment";
    private SimpleDateFormat dateformate;
    private AlarmHelper alarmHelper;
    private Gson gson;
    private ReminderAdapter mAdapter;
    private List<Reminder_custom> mTemplist = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private SharedPreferences mSharedPreferences;
    private TextView noreminders;
    private Editor prefsEditor;
    private boolean isShowdial;
    private TinyDB tinyDB;
    private String[] tempTime = new String[]{"06:00 AM", "07:00 AM", "08:00 AM", "09:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "01:00 PM", "02:00 PM", "03:00 PM", "04:00 PM", "05:00 PM", "06:00 PM", "07:00 PM", "08:00 PM", "09:00 PM"};
    private boolean[] tempTimebool = new boolean[]{false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    private ArrayList<CustomReminderData> mSelectedtimeslist = new ArrayList<>();
    private List<Reminder_custom> mReCu = new ArrayList<>();

    private void showTimePickerDialog() {
        Calendar instance = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), new OnTimeSetListener() {
            public void onTimeSet(TimePicker timePicker, int i, int i2) {
                if (timePicker.isShown()) {
                    Calendar instance = Calendar.getInstance();
                    instance.set(11, i);
                    instance.set(12, i2);
                    String str = ReminderFragment.TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("onTimeSet: ");
                    sb.append(ReminderFragment.this.startTimeFormat().format(instance.getTime()));
                    Log.d(str, sb.toString());
                    ReminderFragment.this.showDialog(instance);
                }
            }
        }, instance.get(11), instance.get(12), false);
        timePickerDialog.show();
    }

    private void mo19679a(AlarmHelper alarmHelper2, Calendar calendar) {
        int parseInt;
        int parseInt2;
        int i;
        if (startTimeFormat().format(calendar.getTime()).endsWith("AM")) {
            parseInt = Integer.parseInt(getHourFormat().format(calendar.getTime()));
            parseInt2 = Integer.parseInt(getMinuteFormat().format(calendar.getTime()));
            i = 0;
        } else {
            parseInt = Integer.parseInt(getHourFormat().format(calendar.getTime()));
            parseInt2 = Integer.parseInt(getMinuteFormat().format(calendar.getTime()));
            i = 1;
        }
        alarmHelper2.schedulePendingIntent(parseInt, parseInt2, i);
    }

    private SimpleDateFormat getHourFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh");
        this.dateformate = simpleDateFormat;
        return simpleDateFormat;
    }

    private SimpleDateFormat getMinuteFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm");
        this.dateformate = simpleDateFormat;
        return simpleDateFormat;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.layout_reminderfragment, viewGroup, false);
        inflate.setTag(TAG);
        try {
            AlarmMainActivity activity = (AlarmMainActivity) getActivity();
            isShowdial = activity.isOpen();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tinyDB = new TinyDB(getContext());

        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(getContext(), languages);
        Toolbar toolbar = (Toolbar) inflate.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.getNavigationIcon().mutate().setColorFilter(Color.WHITE, Mode.SRC_IN);
        setHasOptionsMenu(true);
        this.alarmHelper = new AlarmHelper(getActivity());
        this.mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity().getApplicationContext());
        this.mRecyclerView = (RecyclerView) inflate.findViewById(R.id.reminderlist);
        this.mRecyclerView.setHasFixedSize(true);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.noreminders = (TextView) inflate.findViewById(R.id.noreminder);
        this.gson = new Gson();

        if (isShowdial) {
        DialogeReminder();
        }

        if (!tinyDB.getBoolean("isCustomadded")) {
            Reminder_custom reminderCustom = new Reminder_custom();
            reminderCustom.setFri(true);
            reminderCustom.setMon(true);
            reminderCustom.setSat(true);
            reminderCustom.setThr(true);
            reminderCustom.setSun(true);
            reminderCustom.setTue(true);
            reminderCustom.setWen(true);
            reminderCustom.setOnoff(true);
            reminderCustom.settime("07:00 AM");
            mReCu.add(reminderCustom);
            String json = ReminderFragment.this.gson.toJson((Object) ReminderFragment.this.mReCu);
            ReminderFragment.this.prefsEditor = ReminderFragment.this.mSharedPreferences.edit();
            ReminderFragment.this.prefsEditor.putString("Reminder_customObjectList", json);
            ReminderFragment.this.prefsEditor.apply();
            tinyDB.putBoolean("isCustomadded", true);
        }
        this.mReCu = (List) this.gson.fromJson(this.mSharedPreferences.getString("Reminder_customObjectList", null), new TypeToken<List<Reminder_custom>>() {
        }.getType());
        if (this.mReCu == null || this.mReCu.size() <= 0) {
            this.mRecyclerView.setVisibility(8);
            this.noreminders.setVisibility(0);
        } else {
            this.mRecyclerView.setVisibility(0);
            this.mAdapter = new ReminderAdapter(getActivity(), this.mReCu, this.gson, this.mSharedPreferences, this.prefsEditor, this.alarmHelper);
            this.mRecyclerView.setAdapter(this.mAdapter);
            this.noreminders.setVisibility(8);
        }
        ((ImageView) inflate.findViewById(R.id.addreminder)).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ReminderFragment.this.showTimePickerDialog();
            }
        });
        return inflate;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == 16908332) {
            getActivity().finish();
        }
        return super.onOptionsItemSelected(menuItem);
    }

    private void showDialog(final Calendar calendar) {
        Builder builder = new Builder(getActivity());

        builder.setTitle("Days");
        final ArrayList arrayList = new ArrayList();
        builder.setMultiChoiceItems(getResources().getStringArray(R.array.day_list), null, new OnMultiChoiceClickListener() {
            public void onClick(DialogInterface dialogInterface, int i, boolean z) {
                if (z) {
                    arrayList.add(i);
                    return;
                }
                if (arrayList.contains(i)) {
                    arrayList.remove(Integer.valueOf(i));
                }
            }
        });
        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (arrayList.size() > 0) {
                    dialogInterface.dismiss();
                    Reminder_custom reminder_custom = new Reminder_custom();
                    reminder_custom.settime(ReminderFragment.this.startTimeFormat().format(calendar.getTime()));
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        if (((Integer) arrayList.get(i2)).equals(0)) {
                            reminder_custom.setMon(true);
                        } else if (((Integer) arrayList.get(i2)).equals(1)) {
                            reminder_custom.setTue(true);
                        } else if (((Integer) arrayList.get(i2)).equals(2)) {
                            reminder_custom.setWen(true);
                        } else if (((Integer) arrayList.get(i2)).equals(3)) {
                            reminder_custom.setThr(true);
                        } else if (((Integer) arrayList.get(i2)).equals(4)) {
                            reminder_custom.setFri(true);
                        } else if (((Integer) arrayList.get(i2)).equals(5)) {
                            reminder_custom.setSat(true);
                        } else if (((Integer) arrayList.get(i2)).equals(6)) {
                            reminder_custom.setSun(true);
                        }
                    }
                    ReminderFragment.this.mo19679a(ReminderFragment.this.alarmHelper, calendar);
                    reminder_custom.setOnoff(true);
                    if (ReminderFragment.this.mReCu == null || ReminderFragment.this.mReCu.size() <= 0) {
                        ReminderFragment.this.mReCu = new ArrayList<>();
                    }
                    ReminderFragment.this.mReCu.add(reminder_custom);
                    String json = ReminderFragment.this.gson.toJson((Object) ReminderFragment.this.mReCu);
                    ReminderFragment.this.prefsEditor = ReminderFragment.this.mSharedPreferences.edit();
                    ReminderFragment.this.prefsEditor.putString("Reminder_customObjectList", json);
                    ReminderFragment.this.prefsEditor.apply();
                    ReminderFragment.this.mRecyclerView.setVisibility(0);
                    ReminderFragment reminderFragment = ReminderFragment.this;
                    reminderFragment.mAdapter = new ReminderAdapter(ReminderFragment.this.getActivity(), ReminderFragment.this.mReCu, ReminderFragment.this.gson, ReminderFragment.this.mSharedPreferences, ReminderFragment.this.prefsEditor, ReminderFragment.this.alarmHelper);
                    ReminderFragment.this.mRecyclerView.setAdapter(ReminderFragment.this.mAdapter);
                    ReminderFragment.this.noreminders.setVisibility(8);
                    return;
                }
                Toast.makeText(ReminderFragment.this.getActivity(), "Please select at-least one day", 0).show();
            }
        });
        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    private SimpleDateFormat startTimeFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        this.dateformate = simpleDateFormat;
        return simpleDateFormat;
    }


    public void DialogeReminder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.MaterialThemeDialog);
        builder.setTitle("Set Reminder");
        for (int i = 0; i < tempTime.length; i++) {
            CustomReminderData data = new CustomReminderData();
            data.setPosition(String.valueOf(i));
            data.setTime(tempTime[i]);
            data.setChecked(tempTimebool[i]);
            mSelectedtimeslist.add(data);
        }
        builder.setMultiChoiceItems(tempTime, tempTimebool, new OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i, boolean isChecked) {
                if (isChecked) {
                    CustomReminderData data = new CustomReminderData();
                    data.setPosition(String.valueOf(i));
                    data.setTime(tempTime[i]);
                    data.setChecked(isChecked);
                    mSelectedtimeslist.set(i, data);
                } else {
                    CustomReminderData data = new CustomReminderData();
                    data.setPosition(String.valueOf(i));
                    data.setTime(tempTime[i]);
                    data.setChecked(isChecked);
                    mSelectedtimeslist.set(i, data);
                }
            }
        });
        builder.setPositiveButton("SET", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (ReminderFragment.this.mReCu == null || ReminderFragment.this.mReCu.size() <= 0) {
                    ReminderFragment.this.mReCu = new ArrayList<>();
                }
                dialog.dismiss();
                for (int i = 0; i < mSelectedtimeslist.size(); i++) {
                    CustomReminderData data = mSelectedtimeslist.get(i);
                    if (data.isChecked()) {
                        Reminder_custom reminderCustom = new Reminder_custom();
                        reminderCustom.setFri(true);
                        reminderCustom.setMon(true);
                        reminderCustom.setSat(true);
                        reminderCustom.setThr(true);
                        reminderCustom.setSun(true);
                        reminderCustom.setTue(true);
                        reminderCustom.setWen(true);
                        reminderCustom.setOnoff(true);
                        reminderCustom.settime(data.getTime());
                        mReCu.add(reminderCustom);
                    }

                }
                if (mReCu != null && mReCu.size() != 0) {
                    String json = ReminderFragment.this.gson.toJson((Object) ReminderFragment.this.mReCu);
                    ReminderFragment.this.prefsEditor = ReminderFragment.this.mSharedPreferences.edit();
                    ReminderFragment.this.prefsEditor.putString("Reminder_customObjectList", json);
                    ReminderFragment.this.prefsEditor.apply();
                    mRecyclerView.setVisibility(0);
                    mAdapter = new ReminderAdapter(getActivity(), mReCu, gson, mSharedPreferences, prefsEditor, alarmHelper);
                    mRecyclerView.setAdapter(mAdapter);
                    noreminders.setVisibility(8);
                    mAdapter.notifyDataSetChanged();
                    if (mSelectedtimeslist != null) {
                        mSelectedtimeslist.clear();
                    }
                } else {
                    Toast.makeText(getContext(), "Please Select Time From List", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Constants.isRatedialShown = true;
                if (mSelectedtimeslist != null) {
                    mSelectedtimeslist.clear();
                }
                dialog.dismiss();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (mSelectedtimeslist != null) {
                    mSelectedtimeslist.clear();
                }
                Constants.isRatedialShown = true;
            }
        });
        AlertDialog timerdial = builder.create();
        timerdial.show();
    }


}
