package drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.SQliteOpenHelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.app_utilities.MyPreferences;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.app_utilities.Utility_ResetAlarm;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.classes.M_ListViewItem;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.classes.R_ListViewItem;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.CustomReminderData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

import static java.lang.Integer.parseInt;


@SuppressLint({"WrongConstant", "SimpleDateFormat"})
public class ReminderMainActivity extends AppCompatActivity {
    private static ReminderMainActivity reminderMainActivityRunningInstance;
    M_ListViewItem MListViewItem;
    RecycleViewAdapter adapter;
    FloatingActionButton addReminderButton;
    ArrayList<Integer> arrayListID = new ArrayList<>();
    ArrayList<M_ListViewItem> arrayListsetGet;
    int counter;
    String date;
    int f70id;
    boolean is_in_action_mode = false;
    RecyclerView.LayoutManager layoutManager;
    boolean longClicked;
    MyPreferences myPreferences;
    String preference_currentdate;
    String preference_customNumber;
    String preference_spinnerText;
    String preference_time;
    String real_date;
    String real_time;
    RecyclerView recyclerView;
    RelativeLayout relativeLayout;
    String repeat;
    SQliteOpenHelper sQliteOpenHelper;
    ArrayList<Integer> selectionList;
    String time;
    String time_diff;
    String title;
    TextView toolbartText;
    Utility_ResetAlarm utility_resetAlarm;
    private ImageView mBtndelete;
    private ImageView mBtnback;
    private boolean isopen;
    private String[] tempTime = new String[]{"06:00 AM", "07:00 AM", "08:00 AM", "09:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "01:00 PM", "02:00 PM", "03:00 PM", "04:00 PM", "05:00 PM", "06:00 PM", "07:00 PM", "08:00 PM", "09:00 PM"};
    private boolean[] tempTimebool = new boolean[]{false, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};
    private ArrayList<CustomReminderData> mSelectedtimeslist = new ArrayList<>();
    String[] splitDate;
    String[] splitTime;
    ArrayList<Integer> weekdays = new ArrayList<>();
    Calendar weekdayss;
    Calendar calendar;
    String getAlarmType;
    String getDate;
    String getLocalDate;
    String getRepeat;
    String getReportAs;
    String getTime;
    String getTitle;
    String temporaryTitle;
    R_ListViewItem[] items;
    String ringtoneTitle;
    Uri ringtoneURI;
    private TinyDB tinyDB;


    public static ReminderMainActivity getInstance() {
        return reminderMainActivityRunningInstance;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        tinyDB = new TinyDB(this);

        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(this, languages);
        reminderMainActivityRunningInstance = this;
        setContentView((int) R.layout.activity_remindermain);
        this.myPreferences = new MyPreferences(this);
        setRequestedOrientation(1);
        Bundle bundle1 = getIntent().getExtras();
        if (bundle1 != null) {
            isopen = bundle1.getBoolean("isshow");
        } else {
            isopen = false;
        }

        if (isopen) {
            Intent intent = new Intent(ReminderMainActivity.this, ReminderActivity.class);
            intent.putExtra("Defaultpart", 1);
            startActivity(intent);
            finish();
//            DialogeReminder();
        }
        InitVariables();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("SETTINGS", 0);
        this.preference_currentdate = sharedPreferences.getString("preference_currentdate", "");
        this.preference_time = sharedPreferences.getString("preference_time", "");
//        TimeConverter("07:00 PM");

        SetListeners();
        fetchDatabaseToArraylist();
        ShimmerFrameLayout mLoadlay = findViewById(R.id.shimmer_container);
        RelativeLayout banner1 = (RelativeLayout) findViewById(R.id.banner1);
        FrameLayout mGbannerlay2 = findViewById(R.id.adframe1234);
        mGbannerlay2.setVisibility(View.GONE);

        if (!tinyDB.getBoolean(Constants.PREMIUN_KEY)) {
        showBanner(this, mGbannerlay2, banner1,mLoadlay);
        }else {
            mLoadlay.setVisibility(View.GONE);
        }

    }

    public void showBanner(Context context, final FrameLayout adMobView, RelativeLayout loadlayout, ShimmerFrameLayout mLoadlay) {
        try {
            final AdView mAdView = new AdView(context);
            mAdView.setAdSize(AdSize.BANNER);
            mAdView.setAdUnitId(Constants.admob_banner);
            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();

                    if (adMobView != null) {
                        adMobView.removeAllViews();
                    }
                    adMobView.addView(mAdView);
                    loadlayout.setVisibility(View.VISIBLE);
                    adMobView.setVisibility(View.VISIBLE);
                    mLoadlay.setVisibility(View.GONE);

                    Constants.isReportBanner = false;
                }

                @Override
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    loadlayout.setVisibility(View.GONE);
                    loadlayout.setVisibility(View.GONE);
                    mLoadlay.setVisibility(View.GONE);
                }
            });
            AdRequest adRequest = new AdRequest.Builder().build();
            mAdView.loadAd(adRequest);
            if (adMobView != null) {
                adMobView.removeAllViews();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void InitVariables() {
        this.addReminderButton = (FloatingActionButton) findViewById(R.id.fab);
        this.arrayListsetGet = new ArrayList<>();
        this.sQliteOpenHelper = new SQliteOpenHelper(this);
        this.relativeLayout = (RelativeLayout) findViewById(R.id.noRelative);
        this.recyclerView = (RecyclerView) findViewById(R.id.rc);
        this.recyclerView.setHasFixedSize(true);
        this.layoutManager = new LinearLayoutManager(this);
        this.recyclerView.setLayoutManager(this.layoutManager);
        toolbartText = (TextView) findViewById(R.id.titletxt);
        mBtndelete = (ImageView) findViewById(R.id.btndelete);
        mBtndelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter == 0) {
                    Toast.makeText(ReminderMainActivity.this, "Select any reminder to delete!!", 1).show();
                } else {
                    Iterator it = selectionList.iterator();
                    while (it.hasNext()) {
                        int intValue = ((Integer) it.next()).intValue();
                        if (sQliteOpenHelper.DeleteReminder(intValue) > 0) {
                            arrayListsetGet.clear();
                            arrayListID.clear();
                            fetchDatabaseToArraylist();
                            adapter.notifyDataSetChanged();
                            Intent intent = new Intent(ReminderMainActivity.this, MyBroadcastReceiver.class);
                            intent.setAction("ii.mme.mmyself");
                            intent.addCategory("android.intent.category.DEFAULT");
                            PendingIntent.getBroadcast(getApplicationContext(), intValue, intent, 268435456);
                            Intent intent2 = new Intent(ReminderMainActivity.this, SnoozeReceiver.class);
                            intent2.setAction("this.is.SnoozeReceiver");
                            intent2.addCategory("android.intent.category.DEFAULT");
                            PendingIntent.getBroadcast(getApplicationContext(), intValue, intent2, 268435456);
                        } else {
                            Toast.makeText(ReminderMainActivity.this, getResources().getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
                        }
                    }
                    ClearActionMode();
                }
            }
        });
        mBtnback = (ImageView) findViewById(R.id.icback);
        mBtnback.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        this.selectionList = new ArrayList<>();
        this.utility_resetAlarm = new Utility_ResetAlarm();
    }


    public void SetListeners() {
        this.addReminderButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(ReminderMainActivity.this, ReminderActivity.class);
                intent.putExtra("Defaultpart", 1);
                ReminderMainActivity.this.startActivity(intent);
                finish();
            }
        });
    }

    public void fetchDatabaseToArraylist() {
        Date date2;
        this.arrayListsetGet.clear();
        this.arrayListID.clear();
        SimpleDateFormat simpleDateFormat = null;
        try {
            Cursor Select = this.sQliteOpenHelper.Select(this.sQliteOpenHelper.getReadableDatabase(), null);
            if (Select != null) {
                String str = null;
                while (Select.moveToNext()) {
                    this.f70id = Select.getInt(0);
                    this.title = Select.getString(1);
                    this.repeat = Select.getString(2);
                    this.date = Select.getString(3);
                    this.real_date = this.date;
                    this.time = Select.getString(4);
                    this.real_time = this.time;
                    if (this.repeat.equals("Custom")) {
                        String str2 = "";
                        this.preference_customNumber = Select.getString(11);
                        this.preference_spinnerText = Select.getString(12);
                        if (this.preference_spinnerText.equals("MINUTELY")) {
                            str2 = " minutes";
                        } else if (this.preference_spinnerText.equals("HOURLY")) {
                            str2 = " hours";
                        } else if (this.preference_spinnerText.equals("DAILY")) {
                            str2 = " days";
                        } else if (this.preference_spinnerText.equals("WEEKLY")) {
                            str2 = " weeks";
                        } else if (this.preference_spinnerText.equals("MONTHLY")) {
                            str2 = " months";
                        } else if (this.preference_spinnerText.equals("YEARLY")) {
                            str2 = " years";
                        }
                        StringBuilder sb = new StringBuilder();
                        sb.append("Every ");
                        sb.append(this.preference_customNumber);
                        sb.append(str2);
                        this.repeat = sb.toString();
                    }
                    SimpleDateFormat simpleDateFormat2 = !this.preference_currentdate.equals("") ? this.preference_currentdate.equals("DD/MM/YYYY") ? new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) : this.preference_currentdate.equals("MM/DD/YYYY") ? new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()) : this.preference_currentdate.equals("YYYY/MM/DD") ? new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()) : simpleDateFormat : new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    try {
                        if (this.date.contains("/")) {
                            date2 = simpleDateFormat2.parse(this.date);
                        } else {
                            date2 = new SimpleDateFormat("yyyy-MM-dd").parse(this.date);
                        }
                        this.date = simpleDateFormat2.format(date2);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("hh:mm a", Locale.getDefault());
                    SimpleDateFormat simpleDateFormat4 = new SimpleDateFormat("HH:mm");
                    try {
                        this.time = simpleDateFormat4.format(simpleDateFormat3.parse(this.time));
                    } catch (ParseException e2) {
                        e2.printStackTrace();
                    }
                    if (this.preference_time.equals("")) {
                        try {
                            this.time = simpleDateFormat3.format(simpleDateFormat4.parse(this.time));
                        } catch (ParseException e3) {
                            e3.printStackTrace();
                        }
                        str = this.time.substring(6, this.time.length());
                        this.time = this.time.substring(0, 5);
                    } else if (this.preference_time.equals("false")) {
                        try {
                            this.time = simpleDateFormat3.format(simpleDateFormat4.parse(this.time));
                        } catch (ParseException e4) {
                            e4.printStackTrace();
                        }
                        str = this.time.substring(6, this.time.length());
                        this.time = this.time.substring(0, 5);
                    } else {
                        try {
                            this.time = simpleDateFormat4.format(simpleDateFormat4.parse(this.time));
                        } catch (ParseException e5) {
                            e5.printStackTrace();
                        }
                    }
                    SimpleDateFormat simpleDateFormat5 = new SimpleDateFormat("yyyy-MM-ddHH:mm");
                    try {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(this.real_date);
                        sb2.append(this.real_time);
                        long time2 = simpleDateFormat5.parse(sb2.toString()).getTime() - new Date().getTime();
                        long j = (time2 / 60000) % 60;
                        long j2 = (time2 / 3600000) % 24;
                        long j3 = (time2 / 86400000) % 7;
                        long j4 = time2 / 604800000;
                        this.time_diff = "";
                        if (j4 > 0) {
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append(j4);
                            sb3.append("w ");
                            this.time_diff = sb3.toString();
                        }
                        if (j3 > 0) {
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append(this.time_diff);
                            sb4.append(j3);
                            sb4.append("d ");
                            this.time_diff = sb4.toString();
                        }
                        if (j2 > 0) {
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append(this.time_diff);
                            sb5.append(j2);
                            sb5.append("h ");
                            this.time_diff = sb5.toString();
                        }
                        if (j > 0) {
                            StringBuilder sb6 = new StringBuilder();
                            sb6.append(this.time_diff);
                            sb6.append(j);
                            sb6.append("m");
                            this.time_diff = sb6.toString();
                        } else if (j3 <= 0 && j2 <= 0 && j == 0) {
                            StringBuilder sb7 = new StringBuilder();
                            sb7.append(this.time_diff);
                            sb7.append("1m");
                            this.time_diff = sb7.toString();
                        }
                    } catch (ParseException e6) {
                        e6.printStackTrace();
                    }
                    this.MListViewItem = new M_ListViewItem();
                    this.MListViewItem.setTitle(this.title);
                    this.MListViewItem.setRepeat(this.repeat);
                    this.MListViewItem.setDate(this.date);
                    this.MListViewItem.setTime(this.time);
                    this.MListViewItem.setTime_diff(this.time_diff);
                    this.MListViewItem.setTime_am_pm(str);
                    SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("Alarm_on_off", 0);
                    StringBuilder sb8 = new StringBuilder();
                    sb8.append(this.f70id);
                    sb8.append("alarm_state");
                    this.MListViewItem.setSwitch_state(sharedPreferences.getBoolean(sb8.toString(), true));
                    this.arrayListsetGet.add(this.MListViewItem);
                    this.arrayListID.add(this.f70id);
                    simpleDateFormat = null;
                }
                Select.close();
                if (this.arrayListsetGet.isEmpty()) {
                    this.relativeLayout.setVisibility(0);
                    return;
                }
                this.adapter = new RecycleViewAdapter(this, this.arrayListsetGet);
                this.recyclerView.setAdapter(this.adapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void DialogeReminder() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ReminderMainActivity.this, R.style.MaterialThemeDialog);
        builder.setTitle("Set Reminder");
        for (int i = 0; i < tempTime.length; i++) {
            CustomReminderData data = new CustomReminderData();
            data.setPosition(String.valueOf(i));
            data.setTime(tempTime[i]);
            data.setChecked(tempTimebool[i]);
            mSelectedtimeslist.add(data);
        }
        builder.setMultiChoiceItems(tempTime, tempTimebool, new DialogInterface.OnMultiChoiceClickListener() {
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
                splitTime = new String[2];
                splitDate = new String[3];

                Date c2 = Calendar.getInstance().getTime();
                SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
                String formattedDate2 = df2.format(c2);
                String[] splitdar = formattedDate2.split("-");
                int lengthss = splitdar.length;
                int i34 = 0;
                int i21 = 0;
                while (i34 < lengthss) {
                    int i3 = i21 + 1;
                    splitDate[i21] = splitdar[i34];
                    i34++;
                    i21 = i3;
                }
                for (int i = 0; i < mSelectedtimeslist.size(); i++) {
                    if (mSelectedtimeslist.get(i).isChecked()) {
                        String[] split = TimeConverter(mSelectedtimeslist.get(i).getTime()).split(":");
                        int length = split.length;
                        int i11 = 0;
                        int i2 = 0;
                        while (i11 < length) {
                            int i3 = i2 + 1;
                            splitTime[i2] = split[i11];
                            i11++;
                            i2 = i3;
                        }
                        weekdays.add(0);
                        weekdays.add(1);
                        weekdays.add(2);
                        weekdays.add(3);
                        weekdays.add(4);
                        weekdays.add(5);
                        weekdays.add(6);
                        weekdaysFormat();
                        SetAlarm(SendDataToDatabase(TimeConverter(mSelectedtimeslist.get(i).getTime())));
                    }
                }
                Toast.makeText(ReminderMainActivity.this, "Please Select Time From List", Toast.LENGTH_SHORT).show();
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


    public String TimeConverter(String time) {
        String time24 = "07:00 PM";
        try {
            System.out.println("time in 12 hour format : " + time);
            SimpleDateFormat inFormat = new SimpleDateFormat("hh:mm aa");
            SimpleDateFormat outFormat = new SimpleDateFormat("HH:mm");
            time24 = outFormat.format(inFormat.parse(time));
            Log.e("timestring", time24);
        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
        }
        return time24;
    }


    public void ShowDelDialog(final int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle((CharSequence)getResources().getString(R.string.confirmation));
        builder.setMessage((CharSequence)getResources().getString(R.string.are_you_delete));
        builder.setPositiveButton((CharSequence) getResources().getString(R.string.yes), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                int intValue = (Integer) ReminderMainActivity.this.arrayListID.get(i);
                if (ReminderMainActivity.this.sQliteOpenHelper.DeleteReminder(intValue) > 0) {
                    Toast.makeText(ReminderMainActivity.this, getResources().getString(R.string.deleted), 1).show();
                    ReminderMainActivity.this.arrayListsetGet.clear();
                    ReminderMainActivity.this.arrayListID.clear();
                    ReminderMainActivity.this.fetchDatabaseToArraylist();
                } else {
                    Toast.makeText(ReminderMainActivity.this, getResources().getString(R.string.something_went_wrong), 1).show();
                }
                Intent intent = new Intent(ReminderMainActivity.this, MyBroadcastReceiver.class);
                intent.setAction("ii.mme.mmyself");
                intent.addCategory("android.intent.category.DEFAULT");
                PendingIntent.getBroadcast(ReminderMainActivity.this.getApplicationContext(), intValue, intent, 268435456);
            }
        });
        builder.setNegativeButton((CharSequence) getResources().getString(R.string.cancel), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create().show();
    }

    public void LongClickListener(View view, int i) {
        this.longClicked = true;
        if (this.selectionList.contains(this.arrayListID.get(i))) {
            this.selectionList.remove(this.arrayListID.get(i));
            this.counter--;
        } else {
            this.selectionList.add(this.arrayListID.get(i));
            this.counter++;
        }
        this.is_in_action_mode = true;
        mBtndelete.setVisibility(View.VISIBLE);
        UpdateCounter(this.counter);
    }

    public void PrepareSelection(View view, int i) {
        if (this.longClicked) {
            if (this.selectionList.contains(this.arrayListID.get(i))) {
                this.selectionList.remove(this.arrayListID.get(i));
                this.counter--;
            } else {
                this.selectionList.add(this.arrayListID.get(i));
                this.counter++;
            }
            UpdateCounter(this.counter);
            return;
        }
        int intValue = (Integer) this.arrayListID.get(i);
        Intent intent = new Intent(this, ReminderActivity.class);
        intent.putExtra("ID", intValue);
        intent.putExtra("ForUpdate", 2);
        startActivity(intent);
        finish();
    }

    public void UpdateCounter(int i) {
        if (i == 0) {
            ClearActionMode();
            return;
        }
        TextView textView = this.toolbartText;
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append(" items selected");
        textView.setText(sb.toString());
    }

    public void ClearActionMode() {
        this.longClicked = false;
        this.is_in_action_mode = false;
        this.toolbartText.setText("Set Remider");
        mBtndelete.setVisibility(View.GONE);
        this.counter = 0;
        this.selectionList.clear();
    }

    public void AlarmSwitch_on_off(boolean z, int i) {
        int intValue = (Integer) this.arrayListID.get(i);
        Editor edit = getApplicationContext().getSharedPreferences("Alarm_on_off", 0).edit();
        StringBuilder sb = new StringBuilder();
        sb.append(intValue);
        sb.append("alarm_state");
        edit.putBoolean(sb.toString(), z);
        edit.apply();
        if (!z) {
            Intent intent = new Intent(this, MyBroadcastReceiver.class);
            intent.setAction("ii.mme.mmyself");
            intent.addCategory("android.intent.category.DEFAULT");
            PendingIntent.getBroadcast(getApplicationContext(), intValue, intent, 268435456);
            Intent intent2 = new Intent(this, SnoozeReceiver.class);
            intent2.setAction("this.is.SnoozeReceiver");
            intent2.addCategory("android.intent.category.DEFAULT");
            PendingIntent.getBroadcast(getApplicationContext(), intValue, intent2, 268435456);
            return;
        }
        this.utility_resetAlarm.RestartAlarm(this, "no", (long) intValue);
    }


    public boolean weekdaysFormat() {
        boolean z = false;
        if (this.splitTime.length != 2 || this.splitTime[0] == null || this.splitTime[1] == null || !IsInt_ByException(this.splitTime[0]) || !IsInt_ByException(this.splitTime[1])) {
            return false;
        }
        this.weekdayss = Calendar.getInstance();
        int parseInt = parseInt(this.splitTime[0]);
        int parseInt2 = parseInt(this.splitTime[1]);
        this.weekdayss.set(parseInt(this.splitDate[0]), parseInt(this.splitDate[1]) - 1, parseInt(this.splitDate[2]), parseInt, parseInt2);
        this.weekdayss.set(11, parseInt);
        this.weekdayss.set(12, parseInt2);
        this.weekdayss.set(13, 0);
        this.weekdayss.set(14, 0);
        int i = this.weekdayss.get(7);
        int[] iArr = {1, 2, 3, 4, 5, 6, 7};
        Iterator it = this.weekdays.iterator();
        while (it.hasNext()) {
            if (i == (Integer) it.next() + 1) {
                if (Calendar.getInstance().getTimeInMillis() >= this.weekdayss.getTimeInMillis()) {
                    TimeHasPassed(i, iArr, i, parseInt, parseInt2);
                }
                z = true;
            }
        }
        if (!z) {
            TimeHasPassed(i, iArr, i, parseInt, parseInt2);
        }
        return true;
    }


    private boolean IsInt_ByException(String str) {
        try {
            parseInt(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }

    public void SplitDateTime() {
        String[] split = this.getDate.split("-");
        int length = split.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = i3 + 1;
            this.splitDate[i3] = split[i2];
            i2++;
            i3 = i4;
        }
        String[] split2 = this.getTime.split(":");
        int length2 = split2.length;
        int i5 = 0;
        while (i < length2) {
            int i6 = i5 + 1;
            this.splitTime[i5] = split2[i];
            i++;
            i5 = i6;
        }
    }


    private void TimeHasPassed(int i, int[] iArr, int i2, int i3, int i4) {
        int i5 = i + 1;
        if (i5 == 8) {
            i5 = 1;
        }
        int length = iArr.length;
        int i6 = i5;
        int i7 = 0;
        boolean z = false;
        while (i7 < length) {
            int i8 = iArr[i7];
            Iterator it = this.weekdays.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (i6 != (Integer) it.next() + 1) {
                        if (z) {
                            break;
                        }
                    } else {
                        Calendar instance = Calendar.getInstance();
                        if (i6 != i2) {
                            this.weekdayss.add(5, ((i6 + 7) - this.weekdayss.get(7)) % 7);
                        } else if ((instance.get(11) * 60) + this.weekdayss.get(12) >= (i3 * 60) + i4) {
                            this.weekdayss.add(5, 7);
                        }
                        int i9 = this.weekdayss.get(5);
                        int i10 = this.weekdayss.get(2);
                        int i11 = this.weekdayss.get(1);
                        String format = String.format("%02d", i10 + 1);
                        String format2 = String.format("%02d", i9);
                        StringBuilder sb = new StringBuilder();
                        sb.append(i11);
                        sb.append("-");
                        sb.append(format);
                        sb.append("-");
                        sb.append(format2);
                        this.getDate = sb.toString();
                        z = true;
                    }
                } else {
                    break;
                }
            }
            if (!z) {
                i6++;
                if (i6 == 8) {
                    i6 = 1;
                }
                i7++;
            } else {
                return;
            }
        }
    }

    public void SetDefaultRingtone() {
        try {
            this.ringtoneTitle = RingtoneManager.getRingtone(this, RingtoneManager.getDefaultUri(1)).getTitle(this);
        } catch (Exception unused) {
            this.ringtoneTitle = "Unknown Ringtone";
        }
    }


    public long SendDataToDatabase(String time) {
        long AddReminder = this.sQliteOpenHelper.AddReminder("", "Weekdays", preference_currentdate, time, "Unknown Ringtone", "", new Gson().toJson((Object) this.weekdays), this.getAlarmType, this.getReportAs, "", "", "");
        if (AddReminder > 0) {
            Toast.makeText(this, "Reminder Set", 1).show();
        } else {
            Toast.makeText(this, "Something went wrong", 1).show();
        }
        return AddReminder;
    }


    public void SetAlarm(long j) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intent intent = new Intent(this, MyBroadcastReceiver.class);
        intent.setAction("ii.mme.mmyself");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra("ID", j);
        Editor edit = getApplicationContext().getSharedPreferences("Alarm_on_off", 0).edit();
        StringBuilder sb = new StringBuilder();
        sb.append(j);
        sb.append("alarm_state");
        edit.putBoolean(sb.toString(), true);
        edit.apply();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), (int) j, intent, 0);
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(0, this.weekdayss.getTimeInMillis(), pendingIntent);
        } else if (Build.VERSION.SDK_INT >= 19) {
            alarmManager.setExact(0, this.weekdayss.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.set(0, this.weekdayss.getTimeInMillis(), pendingIntent);
        }
    }


    public void onBackPressed() {
        if (this.is_in_action_mode) {
            ClearActionMode();
            fetchDatabaseToArraylist();
            return;
        }
        finish();
        super.onBackPressed();
    }
}
