package drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.view.ViewCompat;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.IllegalFormatConversionException;
import java.util.Iterator;
import java.util.Locale;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.SQliteOpenHelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.classes.NumericKeyBoardTransformationMethod;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.classes.R_ListViewItem;
import drzio.insomnia.treatment.bedtime.yoga.sleep.inter_face.Reminder_Interface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

import static java.lang.Integer.parseInt;

@SuppressLint("WrongConstant")
public class ReminderActivity extends AppCompatActivity implements Reminder_Interface {

    String Custom;
    AlarmManager alarmManager;
    Calendar calendar;
    int callDefaultMethods;
    int callUpdateMethods;
    boolean clock;
    R_ListAdapter customAdapter;
    String custom_number;
    View dialogView;
    String getAlarmType;
    String getDate;
    String getLocalDate;
    String getRepeat;
    String getReportAs;
    String getTime;
    String getTitle;
    int index;
    public boolean isRunning;
    R_ListViewItem[] items;
    ListView listView;
    MyBroadcastReceiver myBroadcastReceiver;
    int newID;
    PendingIntent pendingIntent;
    String preference_currentdate;
    String preference_time;
    String ringtoneTitle;
    Uri ringtoneURI;
    SQliteOpenHelper sQliteOpenHelper;
    String spinnerText;
    String[] splitDate;
    String[] splitTime;
    String temporaryTitle;
    long timeDiff;
    TimePicker timePicker;
    int updatedID;
    ArrayList<Integer> weekdays;
    Calendar weekdayss;
    TinyDB tinyDB;
    private View mDaylayout;


    @SuppressLint("WrongConstant")
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView((int) R.layout.activity_reminder);
        setRequestedOrientation(1);
        getWindow().setSoftInputMode(3);
        tinyDB = new TinyDB(ReminderActivity.this);

        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(this, languages);
        ShimmerFrameLayout mLoadlay = findViewById(R.id.shimmer_container);
        LinearLayout mAdframe = (LinearLayout) findViewById(R.id.adframe2);
        FrameLayout mAdframe4 = (FrameLayout) findViewById(R.id.adframe4);
        if (!tinyDB.getBoolean(Constants.PREMIUN_KEY)) {
            if (Constants.isReminderBanner) {
                mAdframe.setVisibility(View.GONE);
                mLoadlay.setVisibility(View.VISIBLE);
                showBanner(this, mAdframe4, mLoadlay);
            } else {
                mAdframe4.setVisibility(View.GONE);
                mLoadlay.setVisibility(View.GONE);
                Smallnative(this, mAdframe);
            }
        }else {

        }
        InitVariables();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("SETTINGS", 0);
        this.preference_currentdate = sharedPreferences.getString("preference_currentdate", "");
        this.preference_time = sharedPreferences.getString("preference_time", "");
        Intent intent = getIntent();
        this.callDefaultMethods = intent.getIntExtra("Defaultpart", 0);
        this.updatedID = intent.getIntExtra("ID", 0);
        this.callUpdateMethods = intent.getIntExtra("ForUpdate", 0);
        mDaylayout = findViewById(R.id.linearDay);
        mDaylayout.setVisibility(View.GONE);
        SetTimeDate();
    }


    public void showBanner(Context context, final FrameLayout adMobView,ShimmerFrameLayout loadlayout) {
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
                    adMobView.setVisibility(View.VISIBLE);
                    Constants.isReminderBanner = false;
                    loadlayout.setVisibility(View.GONE);
                }

                @Override
                public void onAdFailedToLoad(int i) {
                    loadlayout.setVisibility(View.GONE);
                    super.onAdFailedToLoad(i);
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


    public void OnReminderItemClickListener(int i) {
        if (i == 0) {
            EnterTitle();
        } else if (i == 1) {
            ShowCalendar();
        } else if (i == 2) {
            ShowRepeatDialog();
        } else if (i == 3) {
            AlarmType();
        } else if (i == 4) {
            ReportAs();
        }
    }


    public void InitVariables() {
        this.sQliteOpenHelper = new SQliteOpenHelper(this);
        this.weekdays = new ArrayList<>();
        this.splitDate = new String[3];
        this.splitTime = new String[2];
        this.myBroadcastReceiver = new MyBroadcastReceiver();
        this.ringtoneURI = Uri.parse("");
        this.listView = (ListView) findViewById(R.id.listView);
        this.items = new R_ListViewItem[5];
        if (customAdapter != null) {
            customAdapter.notifyDataSetChanged();
        }
        this.Custom = "Custom";
        this.timePicker = (TimePicker) findViewById(R.id.timepicker);
//        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(this.toolbar);
    }


    public void SetTimeDate() {
        if (this.callDefaultMethods == 1) {
            SetDefaultDateTime();
        } else if (this.callUpdateMethods ==2) {
            SetExistingData();
        }
    }


    public void SetDefaultDateTime() {
        this.calendar = Calendar.getInstance();
        int i = this.calendar.get(1);
        int i2 = this.calendar.get(2) + 1;
        int i3 = this.calendar.get(5);
        SimpleDateFormat simpleDateFormat = !this.preference_currentdate.equals("") ? this.preference_currentdate.equals("DD/MM/YYYY") ? new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) : this.preference_currentdate.equals("MM/DD/YYYY") ? new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()) : this.preference_currentdate.equals("YYYY/MM/DD") ? new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()) : null : new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String format = String.format("%02d", i2);
        String format2 = String.format("%02d", i3);
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append("-");
        sb.append(format);
        sb.append("-");
        sb.append(format2);
        this.getDate = sb.toString();
        this.getLocalDate = simpleDateFormat.format(Calendar.getInstance().getTime());
        int i4 = this.calendar.get(11);
        int i5 = this.calendar.get(12);
        String format3 = String.format("%02d", i4);
        String format4 = String.format("%02d", i5);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(format3);
        sb2.append(":");
        sb2.append(format4);
        this.getTime = sb2.toString();
        TimePicker();
        this.getRepeat = "Daily";
        this.getAlarmType = "Sound";
        this.getReportAs = "Notification";
        this.temporaryTitle = "Let's do Yoga360 exercise today.";
        this.getTitle = "";
        SetDefaultRingtone();
        this.items[0] = new R_ListViewItem(this.temporaryTitle, 0);
        this.items[1] = new R_ListViewItem(this.getLocalDate, 1);
        this.items[2] = new R_ListViewItem(this.getRepeat, 2);
        this.items[3] = new R_ListViewItem("Sound", 3);
        this.items[4] = new R_ListViewItem("Notification", 4);
        this.customAdapter = new R_ListAdapter(this, R.id.text, this.items, this);
        this.listView.setAdapter(this.customAdapter);
        this.weekdays.add(0);
        this.weekdays.add(6);
        Weekdays();
    }

    public void SetDefaultRingtone() {
        try {
            this.ringtoneTitle = RingtoneManager.getRingtone(this, RingtoneManager.getDefaultUri(1)).getTitle(this);
        } catch (Exception unused) {
            this.ringtoneTitle = "Unknown Ringtone";
        }
    }


    public void SetExistingData() {
        String str = "";
        Cursor GetSingleRow = this.sQliteOpenHelper.GetSingleRow(this.sQliteOpenHelper.getReadableDatabase(), (long) this.updatedID);
        try {
            if (GetSingleRow.moveToFirst()) {
                this.getTitle = GetSingleRow.getString(1);
                this.getRepeat = GetSingleRow.getString(2);
                this.getDate = GetSingleRow.getString(3);
                this.getTime = GetSingleRow.getString(4);
                this.ringtoneTitle = GetSingleRow.getString(5);
                this.ringtoneURI = Uri.parse(GetSingleRow.getString(6));
                str = GetSingleRow.getString(7);
                this.getAlarmType = GetSingleRow.getString(8);
                this.getReportAs = GetSingleRow.getString(9);
                this.Custom = GetSingleRow.getString(10);
                this.custom_number = GetSingleRow.getString(11);
                this.spinnerText = GetSingleRow.getString(12);
            }
            GetSingleRow.close();
            boolean PreviousDateFormat = PreviousDateFormat();
            SimpleDateFormat simpleDateFormat = null;
            if (this.preference_currentdate.equals("")) {
                simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                if (PreviousDateFormat) {
                    String format = String.format("%02d", parseInt(this.splitDate[0]));
                    String format2 = String.format("%02d", parseInt(this.splitDate[1]));
                    StringBuilder sb = new StringBuilder();
                    sb.append(this.splitDate[2]);
                    sb.append("-");
                    sb.append(format);
                    sb.append("-");
                    sb.append(format2);
                    this.getDate = sb.toString();
                }
            } else if (this.preference_currentdate.equals("DD/MM/YYYY")) {
                if (PreviousDateFormat) {
                    String format3 = String.format("%02d", parseInt(this.splitDate[1]));
                    String format4 = String.format("%02d", parseInt(this.splitDate[0]));
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append(this.splitDate[2]);
                    sb2.append("-");
                    sb2.append(format3);
                    sb2.append("-");
                    sb2.append(format4);
                    this.getDate = sb2.toString();
                }
                simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            } else if (this.preference_currentdate.equals("MM/DD/YYYY")) {
                if (PreviousDateFormat) {
                    String format5 = String.format("%02d", parseInt(this.splitDate[0]));
                    String format6 = String.format("%02d", parseInt(this.splitDate[1]));
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append(this.splitDate[2]);
                    sb3.append("-");
                    sb3.append(format5);
                    sb3.append("-");
                    sb3.append(format6);
                    this.getDate = sb3.toString();
                }
                simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
            } else if (this.preference_currentdate.equals("YYYY/MM/DD")) {
                if (PreviousDateFormat) {
                    @SuppressLint("DefaultLocale") String format7 = String.format("%02d", parseInt(this.splitDate[1]));
                    String format8 = String.format("%02d", parseInt(this.splitDate[2]));
                    StringBuilder sb4 = new StringBuilder();
                    sb4.append(this.splitDate[0]);
                    sb4.append("-");
                    sb4.append(format7);
                    sb4.append("-");
                    sb4.append(format8);
                    this.getDate = sb4.toString();
                }
                simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
            }
            try {
                this.getLocalDate = simpleDateFormat.format(new SimpleDateFormat("yyyy-MM-dd").parse(this.getDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("hh:mm a", Locale.getDefault());
            try {
                this.getTime = new SimpleDateFormat("HH:mm").format(simpleDateFormat2.parse(this.getTime));
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
            if (this.getTitle.equals("")) {
                this.temporaryTitle = "Let's do Yoga360 exercise today.";
            } else {
                this.temporaryTitle = this.getTitle;
            }
            this.items[0] = new R_ListViewItem(this.temporaryTitle, 0);
            this.items[1] = new R_ListViewItem(this.getLocalDate, 1);
            this.items[2] = new R_ListViewItem(this.getRepeat, 2);
            this.items[3] = new R_ListViewItem(this.getAlarmType, 3);
            this.items[4] = new R_ListViewItem(this.getReportAs, 4);
            this.customAdapter = new R_ListAdapter(this, R.id.text, this.items, this);
            this.listView.setAdapter(this.customAdapter);
            TimePicker();
            this.weekdays = (ArrayList) new Gson().fromJson(str, new TypeToken<ArrayList<Integer>>() {
            }.getType());
            Weekdays();
        } catch (Throwable th) {
            GetSingleRow.close();
            throw th;
        }
    }

    public boolean PreviousDateFormat() {
        boolean contains = this.getDate.contains("/");
        if (contains) {
            String[] split = this.getDate.split("/");
            int length = split.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = i2 + 1;
                this.splitDate[i2] = split[i];
                i++;
                i2 = i3;
            }
        }
        return contains;
    }

    public void OnClick(View view2) {
        int id = view2.getId();
        if (id == R.id.cancel_reminder) {
            startActivity(new Intent(this, ReminderMainActivity.class));
            finish();
        } else if (id == R.id.save_reminder) {
            SaveReminder();
        }else  if (id==R.id.btnstart){
            SaveReminder();
        }
    }

    public void TimePicker() {
        String[] split = this.getTime.split(":");
        int length = split.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i2 + 1;
            this.splitTime[i2] = split[i];
            i++;
            i2 = i3;
        }
        this.timePicker.setCurrentHour(parseInt(this.splitTime[0]));
        this.timePicker.setCurrentMinute(parseInt(this.splitTime[1]));
        if (this.preference_time.equals("")) {
            this.clock = false;
        } else if (this.preference_time.equals("false")) {
            this.clock = false;
        } else {
            this.clock = true;
        }
        this.timePicker.setIs24HourView(this.clock);
        this.timePicker.setOnTimeChangedListener(new OnTimeChangedListener() {
            public void onTimeChanged(TimePicker timePicker, int i, int i2) {
                ReminderActivity.this.getTime = String.format("%02d:%02d", i, i2);
            }
        });
    }

    private void EnterTitle() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.WrapEverythingDialog);
        this.dialogView = getLayoutInflater().inflate(R.layout.alarm_name_dialog_layout, null);
        builder.setView(this.dialogView);
        final AlertDialog create = builder.create();
        create.show();
        LayoutParams layoutParams = new LayoutParams();
        layoutParams.copyFrom(create.getWindow().getAttributes());
        layoutParams.width = -2;
        layoutParams.height = -2;
        layoutParams.gravity = 17;
        create.getWindow().setAttributes(layoutParams);
        create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        final EditText editText = (EditText) this.dialogView.findViewById(R.id.alarm_name);
        Button button = (Button) this.dialogView.findViewById(R.id.OK_alarm);
        Button button2 = (Button) this.dialogView.findViewById(R.id.Cancel_alarm);
        if (!this.getTitle.isEmpty()) {
            editText.setText(this.getTitle);
        }
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ReminderActivity.this.temporaryTitle = editText.getText().toString();
                if (!ReminderActivity.this.temporaryTitle.equals("")) {
                    ReminderActivity.this.getTitle = ReminderActivity.this.temporaryTitle;
                } else {
                    ReminderActivity.this.temporaryTitle = "Let's do Yoga360 exercise today.";
                    ReminderActivity.this.getTitle = "";
                }
                ReminderActivity.this.items[0] = new R_ListViewItem(ReminderActivity.this.temporaryTitle, 0);
                ReminderActivity.this.customAdapter.notifyDataSetChanged();
                create.cancel();
            }
        });
        button2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                ReminderActivity.this.items[2] = new R_ListViewItem(ReminderActivity.this.getRepeat, 2);
                ReminderActivity.this.customAdapter.notifyDataSetChanged();
                create.cancel();
            }
        });
    }


    public void ShowRepeatDialog() {
        int i = 0;
        this.index = 0;
        String[] strArr = {"Once", "Hourly", "Daily", "Weekly", "Monthly", "Yearly", "Weekdays"};
        if (!this.getRepeat.isEmpty()) {
            while (true) {
                if (i >= strArr.length) {
                    break;
                } else if (this.getRepeat.equals(strArr[i])) {
                    this.index = i;
                    break;
                } else {
                    this.index = 7;
                    i++;
                }
            }
        }
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.dialogcustomview, strArr);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Repeat");
        builder.setSingleChoiceItems(arrayAdapter, this.index, new DialogInterface.OnClickListener() {
            @SuppressLint("ResourceType")
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 7) {
                    ReminderActivity.this.getRepeat = "Custom";
                } else {
                    ReminderActivity.this.getRepeat = (String) arrayAdapter.getItem(i);
                }
                if (ReminderActivity.this.getRepeat.equals("Weekdays")) {
                    ReminderActivity.this.Weekdays();
                } else if (ReminderActivity.this.getRepeat.equals("Custom")) {
                    dialogInterface.cancel();
                    AlertDialog.Builder builder = new AlertDialog.Builder(ReminderActivity.this, R.style.WrapEverythingDialog);
                    LayoutInflater layoutInflater = ReminderActivity.this.getLayoutInflater();
                    ReminderActivity.this.dialogView = layoutInflater.inflate(R.layout.custom_repeat, null);
                    builder.setView(ReminderActivity.this.dialogView);
                    final AlertDialog create = builder.create();
                    create.show();
                    LayoutParams layoutParams = new LayoutParams();
                    layoutParams.copyFrom(create.getWindow().getAttributes());
                    layoutParams.width = -2;
                    layoutParams.height = -2;
                    layoutParams.gravity = 17;
                    create.getWindow().setAttributes(layoutParams);
                    create.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                    final Spinner spinner = (Spinner) ReminderActivity.this.dialogView.findViewById(R.id.spinner);
                    final EditText editText = (EditText) ReminderActivity.this.dialogView.findViewById(R.id.custom_editText);
                    editText.setInputType(18);
                    editText.setTransformationMethod(new NumericKeyBoardTransformationMethod());
                    final TextView textView = (TextView) ReminderActivity.this.dialogView.findViewById(R.id.custom_repeatoption);
                    Button button = (Button) ReminderActivity.this.dialogView.findViewById(R.id.Okbutton_custom);
                    Button button2 = (Button) ReminderActivity.this.dialogView.findViewById(R.id.Cancelbutton_custom);
                    ArrayAdapter arrayAdapter = new ArrayAdapter(ReminderActivity.this, 17367048, new String[]{"MINUTELY", "HOURLY", "DAILY", "WEEKLY", "MONTHLY", "YEARLY"});
                    arrayAdapter.setDropDownViewResource(17367049);
                    spinner.setAdapter(arrayAdapter);
                    if (!(ReminderActivity.this.spinnerText == null || ReminderActivity.this.custom_number == null)) {
                        for (int i2 = 0; i2 < spinner.getAdapter().getCount(); i2++) {
                            if (spinner.getAdapter().getItem(i2).toString().contains(ReminderActivity.this.spinnerText)) {
                                spinner.setSelection(i2);
                            }
                        }
                        editText.setText(ReminderActivity.this.custom_number);
                    }
                    spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                        public void onNothingSelected(AdapterView<?> adapterView) {
                        }

                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                            switch (i) {
                                case 0:
                                    textView.setText("Minute(s)");
                                    break;
                                case 1:
                                    textView.setText("Hour(s)");
                                    break;
                                case 2:
                                    textView.setText("Day(s)");
                                    break;
                                case 3:
                                    textView.setText("Week(s)");
                                    break;
                                case 4:
                                    textView.setText("Month(s)");
                                    break;
                                case 5:
                                    textView.setText("Year(s)");
                                    break;
                            }
                            ReminderActivity.this.spinnerText = spinner.getSelectedItem().toString();
                        }
                    });
                    button.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            ReminderActivity.this.custom_number = editText.getText().toString();
                            if (ReminderActivity.this.custom_number == null || ReminderActivity.this.custom_number.isEmpty()) {
                                ReminderActivity.this.getRepeat = "Once";
                                ReminderActivity.this.Custom = "Custom";
                                ReminderActivity.this.custom_number = null;
                                ReminderActivity.this.spinnerText = null;
                            } else if (!ReminderActivity.this.custom_number.equals("0")) {
                                ReminderActivity.this.weekdays.clear();
                                ReminderActivity.this.Weekdays();
                                ReminderActivity reminderActivity = ReminderActivity.this;
                                StringBuilder sb = new StringBuilder();
                                sb.append(ReminderActivity.this.getRepeat);
                                sb.append(" (Every ");
                                sb.append(ReminderActivity.this.custom_number);
                                sb.append(" ");
                                sb.append(textView.getText().toString());
                                sb.append(")");
                                reminderActivity.Custom = sb.toString();
                            } else {
                                ReminderActivity.this.getRepeat = "Once";
                                ReminderActivity.this.Custom = "Custom";
                                ReminderActivity.this.custom_number = null;
                                ReminderActivity.this.spinnerText = null;
                            }
                            ReminderActivity.this.items[2] = new R_ListViewItem(ReminderActivity.this.getRepeat, 2);
                            ReminderActivity.this.customAdapter.notifyDataSetChanged();
                            create.cancel();
                        }
                    });
                    button2.setOnClickListener(new OnClickListener() {
                        public void onClick(View view) {
                            if (ReminderActivity.this.custom_number == null || ReminderActivity.this.custom_number.isEmpty()) {
                                ReminderActivity.this.getRepeat = "Once";
                                ReminderActivity.this.Custom = "Custom";
                                ReminderActivity.this.custom_number = null;
                                ReminderActivity.this.spinnerText = null;
                            } else if (ReminderActivity.this.callUpdateMethods != 2) {
                                ReminderActivity.this.getRepeat = "Once";
                                ReminderActivity.this.Custom = "Custom";
                                ReminderActivity.this.custom_number = null;
                                ReminderActivity.this.spinnerText = null;
                            }
                            ReminderActivity.this.items[2] = new R_ListViewItem(ReminderActivity.this.getRepeat, 2);
                            ReminderActivity.this.customAdapter.notifyDataSetChanged();
                            create.cancel();
                        }
                    });
                }
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!ReminderActivity.this.getRepeat.equals("Weekdays")) {
                    ReminderActivity.this.weekdays.clear();
                    ReminderActivity.this.Weekdays();
                }
                if (!ReminderActivity.this.getRepeat.equals("Custom")) {
                    ReminderActivity.this.Custom = "Custom";
                    ReminderActivity.this.custom_number = null;
                    ReminderActivity.this.spinnerText = null;
                }
                if (ReminderActivity.this.getRepeat.equals("Weekdays")) {
                    mDaylayout.setVisibility(View.VISIBLE);
                } else {
                    mDaylayout.setVisibility(View.GONE);
                }
                ReminderActivity.this.items[2] = new R_ListViewItem(ReminderActivity.this.getRepeat, 2);
                ReminderActivity.this.customAdapter.notifyDataSetChanged();
                dialogInterface.cancel();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create().show();
    }


    public void Weekdays() {
        final int[] iArr = {R.id.buttonSun, R.id.buttonMon, R.id.buttonTue, R.id.buttonWed, R.id.buttonThu, R.id.buttonFri, R.id.buttonSat};
        if (!this.weekdays.isEmpty()) {
            for (int i = 0; i < this.weekdays.size(); i++) {
                Button button = (Button) findViewById(iArr[(Integer) this.weekdays.get(i)]);
                button.setSelected(true);
                button.setTextColor(-1);
            }
        } else {
            for (int findViewById : iArr) {
                Button button2 = (Button) findViewById(findViewById);
                button2.setSelected(false);
                button2.setTextColor(ViewCompat.MEASURED_STATE_MASK);
            }
        }
        for (int findViewById2 : iArr) {
            final Button button3 = (Button) findViewById(findViewById2);
            button3.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (ReminderActivity.this.getRepeat.equals("Weekdays")) {
                        int i = 0;
                        if (button3.isSelected()) {
                            button3.setTextColor(ViewCompat.MEASURED_STATE_MASK);
                            button3.setSelected(false);
                            while (i < iArr.length) {
                                if (button3 == ReminderActivity.this.findViewById(iArr[i])) {
                                    ReminderActivity.this.weekdays.remove(Integer.valueOf(i));
                                    return;
                                }
                                i++;
                            }
                            return;
                        }
                        while (true) {
                            if (i >= iArr.length) {
                                break;
                            } else if (button3 == ReminderActivity.this.findViewById(iArr[i])) {
                                ReminderActivity.this.weekdays.add(i);
                                break;
                            } else {
                                i++;
                            }
                        }
                        button3.setSelected(true);
                        button3.setTextColor(-1);
                        return;
                    }
                    Toast.makeText(ReminderActivity.this, "Please select Weekdays in Repeat Reminder option", 1).show();
                }
            });
        }
    }

    public void SaveReminder() {
        tinyDB.putString("alarmtime", getTime);
        SplitDateTime();
        if (this.getRepeat.equals("Once") || this.getRepeat.equals("Hourly") || this.getRepeat.equals("Daily") || this.getRepeat.equals("Weekly") || this.getRepeat.equals("Monthly") || this.getRepeat.equals("Yearly") || this.getRepeat.equals("Custom")) {
            if (!checkTimeDifference()) {
                Toast.makeText(this, "Date or Time format in your language is not supported", 1).show();
            } /*else if (this.timeDiff <= 0) {
                Toast.makeText(this, "Please set reminder for upcoming time", 1).show();
            } */else if (this.callDefaultMethods == 1) {
                SetAlarm(SendDataToDatabase());
            } else if (this.callUpdateMethods == 2) {
                Intent intent = new Intent(this, SnoozeReceiver.class);
                intent.setAction("this.is.SnoozeReceiver");
                intent.addCategory("android.intent.category.DEFAULT");
                PendingIntent.getBroadcast(getApplicationContext(), this.updatedID, intent, 268435456);
                UpdateDatabase();
                SetAlarm((long) this.newID);
            }
        } else if (!weekdaysFormat()) {
            Toast.makeText(this, "Date or Time format in your language is not supported", 1).show();
        } else if (this.weekdays.isEmpty()) {
            Toast.makeText(this, "Please select any weekday", 1).show();
        } else if (this.callDefaultMethods == 1) {
            SetAlarm(SendDataToDatabase());
        } else if (this.callUpdateMethods == 2) {
            Intent intent2 = new Intent(this, SnoozeReceiver.class);
            intent2.setAction("this.is.SnoozeReceiver");
            intent2.addCategory("android.intent.category.DEFAULT");
            PendingIntent.getBroadcast(getApplicationContext(), this.updatedID, intent2, 268435456);
            UpdateDatabase();
            SetAlarm((long) this.newID);
        }
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

    private boolean checkTimeDifference() {
        boolean z = false;
        if (this.splitDate.length == 3 && this.splitTime.length == 2 && this.splitDate[0] != null && this.splitDate[1] != null && this.splitDate[2] != null && this.splitTime[0] != null && this.splitTime[1] != null && IsInt_ByException(this.splitDate[0]) && IsInt_ByException(this.splitDate[1]) && IsInt_ByException(this.splitDate[2]) && IsInt_ByException(this.splitTime[0]) && IsInt_ByException(this.splitTime[1])) {
            this.calendar = Calendar.getInstance();
            this.calendar.set(parseInt(this.splitDate[0]), parseInt(this.splitDate[1]) - 1, parseInt(this.splitDate[2]), parseInt(this.splitTime[0]), parseInt(this.splitTime[1]));
            z = true;
        }
        if (z) {
            long timeInMillis = this.calendar.getTimeInMillis();
            this.calendar.setTimeInMillis(timeInMillis);
            Calendar instance = Calendar.getInstance();
            long timeInMillis2 = instance.getTimeInMillis();
            instance.setTimeInMillis(timeInMillis2);
            this.timeDiff = timeInMillis - timeInMillis2;
        }
        return z;
    }

    private boolean IsInt_ByException(String str) {
        try {
            parseInt(str);
            return true;
        } catch (NumberFormatException unused) {
            return false;
        }
    }


    public void UpdateDatabase() {
        if (this.sQliteOpenHelper.UpdateReminder(this.updatedID, this.getTitle, this.getRepeat, this.getDate, this.getTime, this.ringtoneTitle, this.ringtoneURI.toString(), new Gson().toJson((Object) this.weekdays), this.getAlarmType, this.getReportAs, this.Custom, this.custom_number, this.spinnerText) > 0) {
            Toast.makeText(this, "Updated", 1).show();
            this.newID = this.updatedID;
            return;
        }
        Toast.makeText(this, "Something went wrong", 1).show();
    }

    public void ShowCalendar() {
        String[] strArr = new String[3];
        ContextWrapper r2 = new ContextWrapper(this) {
            private Resources wrappedResources;

            public Resources getResources() {
                Resources resources = super.getResources();
                if (this.wrappedResources == null) {
                    this.wrappedResources = new Resources(resources.getAssets(), resources.getDisplayMetrics(), resources.getConfiguration()) {
                        @NonNull
                        public String getString(int i, Object... objArr) throws NotFoundException {
                            try {
                                return super.getString(i, objArr);
                            } catch (IllegalFormatConversionException e) {
                                Log.e("DatePickerDialogFix", "IllegalFormatConversionException Fixed!", e);
                                String string = super.getString(i);
                                StringBuilder sb = new StringBuilder();
                                sb.append("%");
                                sb.append(e.getConversion());
                                return String.format(getConfiguration().locale, string.replaceAll(sb.toString(), "%s"), objArr);
                            }
                        }
                    };
                }
                return this.wrappedResources;
            }
        };
        this.calendar = Calendar.getInstance();
        String[] split = this.getDate.split("-");
        int length = split.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = i2 + 1;
            strArr[i2] = split[i];
            i++;
            i2 = i3;
        }
        this.calendar.set(parseInt(strArr[0]), parseInt(strArr[1]) - 1, parseInt(strArr[2]));
        int i4 = this.calendar.get(5);
        int i5 = this.calendar.get(2);
        DatePickerDialog datePickerDialog = new DatePickerDialog(r2, new OnDateSetListener() {
            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                int i4 = i2 + 1;
                SimpleDateFormat simpleDateFormat = !ReminderActivity.this.preference_currentdate.equals("") ? ReminderActivity.this.preference_currentdate.equals("DD/MM/YYYY") ? new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()) : ReminderActivity.this.preference_currentdate.equals("MM/DD/YYYY") ? new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault()) : ReminderActivity.this.preference_currentdate.equals("YYYY/MM/DD") ? new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()) : null : new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String format = String.format("%02d", i4);
                String format2 = String.format("%02d", i3);
                ReminderActivity reminderActivity = ReminderActivity.this;
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                sb.append("-");
                sb.append(format);
                sb.append("-");
                sb.append(format2);
                reminderActivity.getDate = sb.toString();
                Calendar instance = Calendar.getInstance();
                instance.set(i, i2, i3);
                ReminderActivity.this.getLocalDate = simpleDateFormat.format(instance.getTime());
                ReminderActivity.this.items[1] = new R_ListViewItem(ReminderActivity.this.getLocalDate, 1);
                ReminderActivity.this.customAdapter.notifyDataSetChanged();
            }
        }, this.calendar.get(1), i5, i4);
        datePickerDialog.show();
    }


    public long SendDataToDatabase() {
        long AddReminder = this.sQliteOpenHelper.AddReminder(this.getTitle, this.getRepeat, this.getDate, this.getTime, this.ringtoneTitle, this.ringtoneURI.toString(), new Gson().toJson((Object) this.weekdays), this.getAlarmType, this.getReportAs, this.Custom, this.custom_number, this.spinnerText);
        if (AddReminder > 0) {
            Toast.makeText(this, "Reminder Set", 1).show();
        } else {
            Toast.makeText(this, "Something went wrong", 1).show();
        }
        return AddReminder;
    }

    public void SetAlarm(long j) {
        this.alarmManager = (AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM);
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
        this.pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), (int) j, intent, 0);
        if (this.getRepeat.equals("Once") || this.getRepeat.equals("Hourly") || this.getRepeat.equals("Daily") || this.getRepeat.equals("Weekly") || this.getRepeat.equals("Monthly") || this.getRepeat.equals("Yearly") || this.getRepeat.equals("Custom")) {
            this.calendar.set(parseInt(this.splitDate[0]), parseInt(this.splitDate[1]) - 1, parseInt(this.splitDate[2]), parseInt(this.splitTime[0]), parseInt(this.splitTime[1]));
            this.calendar.set(11, parseInt(this.splitTime[0]));
            this.calendar.set(12, parseInt(this.splitTime[1]));
            this.calendar.set(13, 0);
            this.calendar.set(14, 0);
            if (VERSION.SDK_INT >= 23) {
                this.alarmManager.setExactAndAllowWhileIdle(0, this.calendar.getTimeInMillis(), this.pendingIntent);
            } else if (VERSION.SDK_INT >= 19) {
                this.alarmManager.setExact(0, this.calendar.getTimeInMillis(), this.pendingIntent);
            } else {
                this.alarmManager.set(0, this.calendar.getTimeInMillis(), this.pendingIntent);
            }
        } else if (this.getRepeat.equals("Weekdays")) {
            if (VERSION.SDK_INT >= 23) {
                this.alarmManager.setExactAndAllowWhileIdle(0, this.weekdayss.getTimeInMillis(), this.pendingIntent);
            } else if (VERSION.SDK_INT >= 19) {
                this.alarmManager.setExact(0, this.weekdayss.getTimeInMillis(), this.pendingIntent);
            } else {
                this.alarmManager.set(0, this.weekdayss.getTimeInMillis(), this.pendingIntent);
            }
        }
        startActivity(new Intent(this, ReminderMainActivity.class));
        finish();
    }

    public void getSpeechInput() {
        Intent intent = new Intent("android.speech.action.RECOGNIZE_SPEECH");
        intent.putExtra("android.speech.extra.LANGUAGE_MODEL", "free_form");
        intent.putExtra("android.speech.extra.LANGUAGE", Locale.getDefault());
        intent.putExtra("android.speech.extra.PROMPT", "Say Something");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 10);
        } else {
            Toast.makeText(this, "Your Device Doesn't Support Speech Input!!", 1).show();
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 10 && i2 == -1 && intent != null) {
            ArrayList stringArrayListExtra = intent.getStringArrayListExtra("android.speech.extra.RESULTS");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Confirm Text");
            builder.setMessage((CharSequence) stringArrayListExtra.get(0));
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            });
            builder.setNegativeButton("REPEAT", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    ReminderActivity.this.getSpeechInput();
                }
            });
            builder.create().show();
        }
        super.onActivityResult(i, i2, intent);
    }

    private void AlarmType() {
        this.index = 0;
        String[] strArr = {"Sound", "Vibration", "Sound and Vibration"};
        if (!this.getAlarmType.isEmpty()) {
            for (int i = 0; i < strArr.length; i++) {
                if (this.getAlarmType.equals(strArr[i])) {
                    this.index = i;
                }
            }
        }
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.dialogcustomview, strArr);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Alarm type");
        builder.setSingleChoiceItems(arrayAdapter, this.index, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ReminderActivity.this.getAlarmType = (String) arrayAdapter.getItem(i);
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ReminderActivity.this.items[3] = new R_ListViewItem(ReminderActivity.this.getAlarmType, 3);
                ReminderActivity.this.customAdapter.notifyDataSetChanged();
                dialogInterface.cancel();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create().show();
    }

    private void ReportAs() {
        this.index = 0;
        String[] strArr = {"Alarm", "Notification"};
        if (!this.getReportAs.isEmpty()) {
            for (int i = 0; i < strArr.length; i++) {
                if (this.getReportAs.equals(strArr[i])) {
                    this.index = i;
                }
            }
        }
        final ArrayAdapter arrayAdapter = new ArrayAdapter(this, R.layout.dialogcustomview, strArr);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Report As");
        builder.setSingleChoiceItems(arrayAdapter, this.index, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ReminderActivity.this.getReportAs = (String) arrayAdapter.getItem(i);
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ReminderActivity.this.items[4] = new R_ListViewItem(ReminderActivity.this.getReportAs, 4);
                ReminderActivity.this.customAdapter.notifyDataSetChanged();
                dialogInterface.cancel();
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create().show();
    }

    public static void Smallnative(final Context context, final LinearLayout frameLayout) {
        AdLoader.Builder builder = new AdLoader.Builder(context, Constants.admob_nativead);
        builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
            public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                frameLayout.setVisibility(View.VISIBLE);
                NativeContentAdView nativeContentAdView = (NativeContentAdView) ((Activity) context).getLayoutInflater().inflate(R.layout.ad_native_banner, null);
                populateSmallContentAdView(nativeContentAd, nativeContentAdView);
                frameLayout.removeAllViews();
                frameLayout.addView(nativeContentAdView);
                Constants.isReminderBanner = true;
//                Animation mzoom = AnimationUtils.loadAnimation(context, R.anim.adzoom_in);
//                frameLayout.startAnimation(mzoom);
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(int i) {
                Log.e("error", "Failed to load native ad:: " + i);
                FitnessApplication.AdfailToast("MainActivity Small Native", String.valueOf(i));
            }
        }).build().loadAd(new AdRequest.Builder().build());
    }

    @SuppressLint("WrongConstant")
    public static void populateSmallContentAdView(NativeContentAd nativeContentAd, NativeContentAdView nativeContentAdView) {
        nativeContentAdView.setHeadlineView(nativeContentAdView.findViewById(R.id.ad_title_textview));
        nativeContentAdView.setBodyView(nativeContentAdView.findViewById(R.id.ad_describe_textview));
        nativeContentAdView.setCallToActionView(nativeContentAdView.findViewById(R.id.ad_action_button));
        nativeContentAdView.setLogoView(nativeContentAdView.findViewById(R.id.ad_icon_imageview));
        ((TextView) nativeContentAdView.getHeadlineView()).setText(nativeContentAd.getHeadline());
        ((TextView) nativeContentAdView.getBodyView()).setText(nativeContentAd.getBody());
        ((Button) nativeContentAdView.getCallToActionView()).setText(nativeContentAd.getCallToAction());
        com.google.android.gms.ads.formats.NativeAd.Image logo = nativeContentAd.getLogo();
        if (logo == null) {
            nativeContentAdView.getLogoView().setVisibility(4);
        } else {
            ((ImageView) nativeContentAdView.getLogoView()).setImageDrawable(logo.getDrawable());
            nativeContentAdView.getLogoView().setVisibility(0);
        }
        nativeContentAdView.setNativeAd(nativeContentAd);
    }


    public void onBackPressed() {
        startActivity(new Intent(this, ReminderMainActivity.class));
        finish();
    }

    public void onStart() {
        super.onStart();
        this.isRunning = true;
    }

    public void onStop() {
        super.onStop();
        this.isRunning = false;
    }

    public void onDestroy() {
        this.isRunning = false;
        super.onDestroy();
    }
}
