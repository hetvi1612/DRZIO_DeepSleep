package drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm;

import android.annotation.SuppressLint;
import android.app.AlertDialog.Builder;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

@SuppressLint({"SetTextI18n", "WrongConstant"})
public class ReminderAdapter extends Adapter<ReminderAdapter.ProductViewHolder> {
    private SimpleDateFormat f5007a;
    private AlarmHelper alarmHelper;
    private Gson gson;
    private Context mCtx;
    private long mLastClickTime = 0;
    private SharedPreferences mSharedPreferences;
    private Editor prefsEditor;
    public List<Reminder_custom> productList;
    public Reminder_custom reminderproduct;
    public TinyDB tinyDB;

    class ProductViewHolder extends ViewHolder {
        TextView mAlarmTime;
        TextView mDay1;
        TextView mDay2;
        TextView mDay3;
        TextView mDay4;
        TextView mDay5;
        TextView mDay6;
        TextView mDay7;
        ImageView mDeletebtn;
        Switch mSwitch;

        public ProductViewHolder(View view) {
            super(view);
            this.mAlarmTime = (TextView) view.findViewById(R.id.time);
            this.mDay1 = (TextView) view.findViewById(R.id.day1);
            this.mDay2 = (TextView) view.findViewById(R.id.day2);
            this.mDay3 = (TextView) view.findViewById(R.id.day3);
            this.mDay4 = (TextView) view.findViewById(R.id.day4);
            this.mDay5 = (TextView) view.findViewById(R.id.day5);
            this.mDay6 = (TextView) view.findViewById(R.id.day6);
            this.mDay7 = (TextView) view.findViewById(R.id.day7);
            this.mDeletebtn = (ImageView) view.findViewById(R.id.timedelete);
            this.mSwitch = (Switch) view.findViewById(R.id.timeswitch);
        }
    }

    public ReminderAdapter(Context context, List<Reminder_custom> list, Gson gson2, SharedPreferences sharedPreferences, Editor editor, AlarmHelper alarmHelper2) {
        this.mCtx = context;
        this.productList = list;
        this.mSharedPreferences = sharedPreferences;
        this.prefsEditor = editor;
        this.gson = gson2;
        this.alarmHelper = alarmHelper2;
        this.tinyDB = new TinyDB(context);
    }

    public void showTimePickerDialog(final Reminder_custom reminder_custom, final int i) {
        Calendar instance = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(this.mCtx, new OnTimeSetListener() {
            public void onTimeSet(TimePicker timePicker, int i, int i2) {
                if (timePicker.isShown()) {
                    Calendar instance = Calendar.getInstance();
                    instance.set(11, i);
                    instance.set(12, i2);
                    ReminderAdapter.this.showDialog(instance, reminder_custom, i);
                }
            }
        }, instance.get(11), instance.get(12), false);
        timePickerDialog.show();
    }


    public void mo19665a(AlarmHelper alarmHelper2, Calendar calendar) {
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

    public SimpleDateFormat getHourFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh");
        this.f5007a = simpleDateFormat;
        return simpleDateFormat;
    }

    public int getItemCount() {
        return this.productList.size();
    }

    public SimpleDateFormat getMinuteFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm");
        this.f5007a = simpleDateFormat;
        return simpleDateFormat;
    }

    public void onBindViewHolder(final ProductViewHolder productViewHolder, int i) {
        this.reminderproduct = (Reminder_custom) this.productList.get(i);
        productViewHolder.mAlarmTime.setText(this.reminderproduct.gettime());
        tinyDB.putString("alarmtime", this.reminderproduct.gettime());

        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(mCtx, languages);

        productViewHolder.mAlarmTime.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ReminderAdapter.this.reminderproduct = (Reminder_custom) ReminderAdapter.this.productList.get(productViewHolder.getAdapterPosition());
                ReminderAdapter.this.showTimePickerDialog(ReminderAdapter.this.reminderproduct, productViewHolder.getAdapterPosition());
            }
        });
        if (i == 0) {
            productViewHolder.mDeletebtn.setVisibility(View.GONE);
        } else {
            productViewHolder.mDeletebtn.setVisibility(View.VISIBLE);
        }
        productViewHolder.mDay1.setVisibility(0);
        productViewHolder.mDay2.setVisibility(0);
        productViewHolder.mDay3.setVisibility(0);
        productViewHolder.mDay4.setVisibility(0);
        productViewHolder.mDay5.setVisibility(0);
        productViewHolder.mDay6.setVisibility(0);
        productViewHolder.mDay7.setVisibility(0);
        if (this.reminderproduct.getMon()) {
            productViewHolder.mDay1.setText("Mon");
        } else {
            productViewHolder.mDay1.setVisibility(8);
        }
        if (this.reminderproduct.getTue()) {
            productViewHolder.mDay2.setText("Tue");
        } else {
            productViewHolder.mDay2.setVisibility(8);
        }
        if (this.reminderproduct.getWen()) {
            productViewHolder.mDay3.setText("Wed");
        } else {
            productViewHolder.mDay3.setVisibility(8);
        }
        if (this.reminderproduct.getThr()) {
            productViewHolder.mDay4.setText("Thu");
        } else {
            productViewHolder.mDay4.setVisibility(8);
        }
        if (this.reminderproduct.getFri()) {
            productViewHolder.mDay5.setText("Fri");
        } else {
            productViewHolder.mDay5.setVisibility(8);
        }
        if (this.reminderproduct.getSat()) {
            productViewHolder.mDay6.setText("Sat");
        } else {
            productViewHolder.mDay6.setVisibility(8);
        }
        if (this.reminderproduct.getSun()) {
            productViewHolder.mDay7.setText("Sun");
        } else {
            productViewHolder.mDay7.setVisibility(8);
        }
        productViewHolder.mSwitch.setChecked(this.reminderproduct.getOnoff());
        productViewHolder.mSwitch.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                ReminderAdapter.this.reminderproduct = (Reminder_custom) ReminderAdapter.this.productList.get(productViewHolder.getAdapterPosition());
                ReminderAdapter.this.reminderproduct.setOnoff(z);
                String json = ReminderAdapter.this.gson.toJson((Object) ReminderAdapter.this.productList);
                ReminderAdapter.this.prefsEditor = ReminderAdapter.this.mSharedPreferences.edit();
                ReminderAdapter.this.prefsEditor.putString("Reminder_customObjectList", json);
                ReminderAdapter.this.prefsEditor.apply();
            }
        });
        productViewHolder.mDeletebtn.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (SystemClock.elapsedRealtime() - ReminderAdapter.this.mLastClickTime >= 1000) {
                    ReminderAdapter.this.mLastClickTime = SystemClock.elapsedRealtime();
                    ReminderAdapter.this.removeAt(productViewHolder.getAdapterPosition());
                }
            }
        });
    }

    public ProductViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ProductViewHolder(LayoutInflater.from(this.mCtx).inflate(R.layout.layout_remindercustom_row, null));
    }

    public void removeAt(int i) {
        this.productList.remove(i);
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, this.productList.size());
        String json = this.gson.toJson((Object) this.productList);
        this.prefsEditor = this.mSharedPreferences.edit();
        this.prefsEditor.putString("Reminder_customObjectList", json);
        this.prefsEditor.apply();
    }

    @SuppressLint("ResourceType")
    public void showDialog(Calendar calendar, Reminder_custom reminder_custom, int i) {
        Builder builder = new Builder(this.mCtx);
        builder.setTitle("Days");
        final ArrayList arrayList = new ArrayList();
        builder.setMultiChoiceItems(this.mCtx.getResources().getStringArray(R.array.day_list), null, new OnMultiChoiceClickListener() {
            public void onClick(DialogInterface dialogInterface, int i, boolean z) {
                if (z) {
                    arrayList.add(Integer.valueOf(i));
                    return;
                }
                if (arrayList.contains(Integer.valueOf(i))) {
                    arrayList.remove(Integer.valueOf(i));
                }
            }
        });
        @SuppressLint("ResourceType") String string = this.mCtx.getString(17039370);
        final Reminder_custom reminder_custom2 = reminder_custom;
        final Calendar calendar2 = calendar;
        final int i2 = i;
        DialogInterface.OnClickListener r2 = new DialogInterface.OnClickListener() {
            @SuppressLint("CommitPrefEdits")
            public void onClick(DialogInterface dialogInterface, int i) {
                if (arrayList.size() > 0) {
                    dialogInterface.dismiss();
                    reminder_custom2.settime(ReminderAdapter.this.startTimeFormat().format(calendar2.getTime()));
                    reminder_custom2.setMon(false);
                    reminder_custom2.setTue(false);
                    reminder_custom2.setWen(false);
                    reminder_custom2.setThr(false);
                    reminder_custom2.setFri(false);
                    reminder_custom2.setSat(false);
                    reminder_custom2.setSun(false);
                    for (int i2 = 0; i2 < arrayList.size(); i2++) {
                        if (((Integer) arrayList.get(i2)).equals(0)) {
                            reminder_custom2.setMon(true);
                        } else if (((Integer) arrayList.get(i2)).equals(1)) {
                            reminder_custom2.setTue(true);
                        } else if (((Integer) arrayList.get(i2)).equals(2)) {
                            reminder_custom2.setWen(true);
                        } else if (((Integer) arrayList.get(i2)).equals(3)) {
                            reminder_custom2.setThr(true);
                        } else if (((Integer) arrayList.get(i2)).equals(4)) {
                            reminder_custom2.setFri(true);
                        } else if (((Integer) arrayList.get(i2)).equals(5)) {
                            reminder_custom2.setSat(true);
                        } else if (((Integer) arrayList.get(i2)).equals(6)) {
                            reminder_custom2.setSun(true);
                        }
                    }
                    reminder_custom2.setOnoff(true);
                    ReminderAdapter.this.mo19665a(ReminderAdapter.this.alarmHelper, calendar2);
                    String json = ReminderAdapter.this.gson.toJson((Object) ReminderAdapter.this.productList);
                    ReminderAdapter.this.prefsEditor = ReminderAdapter.this.mSharedPreferences.edit();
                    ReminderAdapter.this.prefsEditor.putString("Reminder_customObjectList", json);
                    ReminderAdapter.this.prefsEditor.apply();
                    ReminderAdapter.this.notifyItemChanged(i2);
                    ReminderAdapter.this.notifyItemRangeChanged(i2, ReminderAdapter.this.productList.size());
                    notifyDataSetChanged();
                    return;
                }
                Toast.makeText(ReminderAdapter.this.mCtx, "Please select at-least one day", 0).show();
            }
        };
        builder.setPositiveButton(string, r2);
        builder.setNegativeButton(this.mCtx.getString(17039360), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.create().show();
    }

    private SimpleDateFormat startTimeFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        this.f5007a = simpleDateFormat;
        return simpleDateFormat;
    }
}
