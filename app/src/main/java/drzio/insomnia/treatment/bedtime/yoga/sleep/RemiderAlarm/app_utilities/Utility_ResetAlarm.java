package drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.app_utilities;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build.VERSION;

import androidx.core.app.NotificationCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.SQliteOpenHelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.MyBroadcastReceiver;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.ReminderMainActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.SnoozeReceiver;

//import com.project.iqramuqaddas.reminderalarm.ReminderMainActivity;
//import com.project.iqramuqaddas.reminderalarm.database.SQliteOpenHelper;
//import com.project.iqramuqaddas.reminderalarm.receiver.MyBroadcastReceiver;
//import com.project.iqramuqaddas.reminderalarm.receiver.SnoozeReceiver;

@SuppressLint("WrongConstant")
public class Utility_ResetAlarm {
    private Calendar calendar;
    private int f85d;
    private SQLiteDatabase f86db;
    private long f87id;
    private int f88m;
    private boolean matched;
    private int noOfDays;
    private String preferenceTime;
    private String preference_DateFormat;
    private String preference_customNumber;
    private String preference_date;
    private String preference_repeat;
    private String preference_spinnerText;
    private int repeatNumber;
    private SQliteOpenHelper sQliteOpenHelper;
    private String[] splitDate = new String[3];
    private String[] splitTime = new String[2];
    private String state;
    private ArrayList<Integer> weekdays;
    private Calendar weekdayss;
    private int f89y;

    public void RestartAlarm(Context context, String str, long j) {
        this.sQliteOpenHelper = new SQliteOpenHelper(context);
        this.f86db = this.sQliteOpenHelper.getReadableDatabase();
        if (str.equals("yes")) {
            CancelAllAlarms(context);
            Cursor Select = this.sQliteOpenHelper.Select(this.f86db, null);
            if (Select != null) {
                while (Select.moveToNext()) {
                    this.f87id = (long) Select.getInt(0);
                    SharedPreferences sharedPreferences = context.getSharedPreferences("Alarm_on_off", 0);
                    StringBuilder sb = new StringBuilder();
                    sb.append(this.f87id);
                    sb.append("alarm_state");
                    if (sharedPreferences.getBoolean(sb.toString(), true)) {
                        this.preference_date = Select.getString(3);
                        this.preference_repeat = Select.getString(2);
                        this.preferenceTime = Select.getString(4);
                        String string = Select.getString(7);
                        this.preference_customNumber = Select.getString(11);
                        this.preference_spinnerText = Select.getString(12);
                        this.weekdays = (ArrayList) new Gson().fromJson(string, new TypeToken<List<Integer>>() {
                        }.getType());
                        CompareTime(context);
                    }
                }
                Select.close();
            }
        } else if (str.equals("no")) {
            this.f87id = j;
            Cursor GetSingleRow = this.sQliteOpenHelper.GetSingleRow(this.f86db, this.f87id);
            try {
                if (GetSingleRow.moveToFirst()) {
                    this.preference_date = GetSingleRow.getString(3);
                    this.preference_repeat = GetSingleRow.getString(2);
                    this.preferenceTime = GetSingleRow.getString(4);
                    String string2 = GetSingleRow.getString(7);
                    this.preference_customNumber = GetSingleRow.getString(11);
                    this.preference_spinnerText = GetSingleRow.getString(12);
                    this.weekdays = (ArrayList) new Gson().fromJson(string2, new TypeToken<List<Integer>>() {
                    }.getType());
                }
                GetSingleRow.close();
                CompareTime(context);
            } catch (Throwable th) {
                GetSingleRow.close();
                throw th;
            }
        }
    }

    private void CompareTime(Context context) {
        this.preference_DateFormat = context.getApplicationContext().getSharedPreferences("SETTINGS", 0).getString("preference_currentdate", "");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
        try {
            this.preferenceTime = new SimpleDateFormat("HH:mm").format(simpleDateFormat.parse(this.preferenceTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SplitDateTime();
        ConvertToDateMonthYearFormat();
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MM-yyyyHH:mm");
        Date date = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(this.preference_date);
            sb.append(this.preferenceTime);
            date = simpleDateFormat2.parse(sb.toString());
        } catch (ParseException e2) {
            e2.printStackTrace();
        }
        this.calendar = Calendar.getInstance();
        this.calendar.setTime(date);
        if (Calendar.getInstance().getTimeInMillis() < this.calendar.getTimeInMillis()) {
            SplitDateTime();
            @SuppressLint("DefaultLocale") String format = String.format("%02d", Integer.parseInt(this.splitDate[1]));
            @SuppressLint("DefaultLocale") String format2 = String.format("%02d", Integer.parseInt(this.splitDate[0]));
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.splitDate[2]);
            sb2.append("-");
            sb2.append(format);
            sb2.append("-");
            sb2.append(format2);
            this.preference_date = sb2.toString();
            SplitDateTime();
            ResetAlarm(context);
        } else if (!this.preference_repeat.equals("Once")) {
            UpdateDateTime(context);
        }
    }

    private void ConvertToDateMonthYearFormat() {
        if (!this.preference_date.contains("/")) {
            this.preference_date = this.splitDate[2] +
                    "-" +
                    this.splitDate[1] +
                    "-" +
                    this.splitDate[0];
        } else if (this.preference_DateFormat.equals("")) {
            this.preference_date = this.splitDate[0] +
                    "-" +
                    this.splitDate[1] +
                    "-" +
                    this.splitDate[2];
        } else if (this.preference_DateFormat.equals("DD/MM/YYYY")) {
            this.preference_date = this.splitDate[0] +
                    "-" +
                    this.splitDate[1] +
                    "-" +
                    this.splitDate[2];
        } else if (this.preference_DateFormat.equals("MM/DD/YYYY")) {
            this.preference_date = this.splitDate[1] +
                    "-" +
                    this.splitDate[0] +
                    "-" +
                    this.splitDate[2];
        } else if (this.preference_DateFormat.equals("YYYY/MM/DD")) {
            this.preference_date = this.splitDate[2] +
                    "-" +
                    this.splitDate[1] +
                    "-" +
                    this.splitDate[0];
        }
    }

    private void ResetAlarm(Context context) {
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        Intent intent = new Intent(context, MyBroadcastReceiver.class);
        intent.setAction("ii.mme.mmyself");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra("ID", this.f87id);
        PendingIntent broadcast = PendingIntent.getBroadcast(context.getApplicationContext(), (int) this.f87id, intent, 134217728);
        Calendar instance = Calendar.getInstance();
        instance.set(Integer.parseInt(this.splitDate[0]), Integer.parseInt(this.splitDate[1]) - 1, Integer.parseInt(this.splitDate[2]), Integer.parseInt(this.splitTime[0]), Integer.parseInt(this.splitTime[1]));
        instance.set(11, Integer.parseInt(this.splitTime[0]));
        instance.set(12, Integer.parseInt(this.splitTime[1]));
        instance.set(13, 0);
        instance.set(14, 0);
        if (VERSION.SDK_INT >= 23) {
            assert alarmManager != null;
            alarmManager.setExactAndAllowWhileIdle(0, instance.getTimeInMillis(), broadcast);
        } else if (VERSION.SDK_INT >= 19) {
            assert alarmManager != null;
            alarmManager.setExact(0, instance.getTimeInMillis(), broadcast);
        } else {
            assert alarmManager != null;
            alarmManager.set(0, instance.getTimeInMillis(), broadcast);
        }
        if (ReminderMainActivity.getInstance() != null) {
            ReminderMainActivity.getInstance().fetchDatabaseToArraylist();
        }
    }

    private void UpdateDateTime(Context context) {
        switch (this.preference_repeat) {
            case "Hourly":
                this.state = "hourly";
                UpdateDate();
                break;
            case "Daily":
                this.state = "daily";
                UpdateDate();
                break;
            case "Weekly":
                this.state = "weekly";
                UpdateDate();
                break;
            case "Monthly":
                this.state = "monthly";
                UpdateDate();
                break;
            case "Yearly":
                this.state = "yearly";
                UpdateDate();
                break;
            case "Weekdays":
                SplitDateTime();
                int parseInt = Integer.parseInt(this.splitTime[0]);
                int parseInt2 = Integer.parseInt(this.splitTime[1]);
                this.weekdayss = Calendar.getInstance();
                this.weekdayss.set(11, Integer.parseInt(this.splitTime[0]));
                this.weekdayss.set(12, Integer.parseInt(this.splitTime[1]));
                int i = this.weekdayss.get(7);
                int[] iArr = {1, 2, 3, 4, 5, 6, 7};
                Iterator it = this.weekdays.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else if (i == (Integer) it.next() + 1) {
                        this.matched = true;
                        if (Calendar.getInstance().getTimeInMillis() >= this.weekdayss.getTimeInMillis()) {
                            TimeHasPassed(i, iArr, i, parseInt, parseInt2);
                        }
                    }
                }
                if (!this.matched) {
                    TimeHasPassed(i, iArr, i, parseInt, parseInt2);
                }
                this.f85d = this.weekdayss.get(5);
                this.f88m = this.weekdayss.get(2);
                this.f89y = this.weekdayss.get(1);
                this.f88m++;
                String format = String.format("%02d", this.f88m);
                String format2 = String.format("%02d", this.f85d);
                StringBuilder sb = new StringBuilder();
                sb.append(this.f89y);
                sb.append("-");
                sb.append(format);
                sb.append("-");
                sb.append(format2);
                this.preference_date = sb.toString();
                SplitDateTime();
                break;
            case "Custom":
                this.preference_customNumber = this.preference_customNumber.trim();
                this.noOfDays = Integer.parseInt(this.preference_customNumber.replaceAll("\\D+", ""));
                if (this.preference_spinnerText.equals("MINUTELY")) {
                    this.state = "c_minutely";
                    UpdateDate();
                } else if (this.preference_spinnerText.equals("HOURLY")) {
                    this.state = "c_hourly";
                    UpdateDate();
                } else if (this.preference_spinnerText.equals("DAILY")) {
                    this.state = "c_daily";
                    UpdateDate();
                } else if (this.preference_spinnerText.equals("WEEKLY")) {
                    this.state = "c_weekly";
                    this.noOfDays *= 7;
                    UpdateDate();
                } else if (this.preference_spinnerText.equals("MONTHLY")) {
                    this.state = "c_monthly";
                    UpdateDate();
                } else if (this.preference_spinnerText.equals("YEARLY")) {
                    this.state = "c_yearly";
                    UpdateDate();
                }
                break;
        }
        this.sQliteOpenHelper.UpdateDateTimeColumn((int) this.f87id, this.preference_date, this.preferenceTime);
        ResetAlarm(context);
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
                        this.preference_date = sb.toString();
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
        String[] split = this.preferenceTime.split(":");
        int length = split.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            int i4 = i3 + 1;
            this.splitTime[i3] = split[i2];
            i2++;
            i3 = i4;
        }
        String[] split2 = this.preference_date.split(this.preference_date.contains("/") ? "/" : "-");
        int length2 = split2.length;
        int i5 = 0;
        while (i < length2) {
            int i6 = i5 + 1;
            this.splitDate[i5] = split2[i];
            i++;
            i5 = i6;
        }
    }

    private void UpdateDate() {
        String[] strArr = new String[2];
        if (this.state.equals("hourly")) {
            Calendar instance = Calendar.getInstance();
            this.calendar.set(1, instance.get(1));
            this.calendar.set(2, instance.get(2));
            this.calendar.set(5, instance.get(5));
            String[] split = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date()).split(":");
            int length = split.length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = i2 + 1;
                strArr[i2] = split[i];
                i++;
                i2 = i3;
            }
            StringBuilder sb = new StringBuilder();
            sb.append(strArr[0]);
            sb.append(":");
            sb.append(this.splitTime[1]);
            this.preferenceTime = sb.toString();
            this.calendar.set(11, Integer.parseInt(strArr[0]));
            if (Calendar.getInstance().getTimeInMillis() >= this.calendar.getTimeInMillis()) {
                this.calendar.add(11, 1);
            }
        } else if (this.state.equals("daily")) {
            Calendar instance2 = Calendar.getInstance();
            this.calendar.set(1, instance2.get(1));
            this.calendar.set(2, instance2.get(2));
            this.calendar.set(5, instance2.get(5));
            if (Calendar.getInstance().getTimeInMillis() >= this.calendar.getTimeInMillis()) {
                this.calendar.add(5, 1);
            }
        } else if (this.state.equals("weekly")) {
            long timeInMillis = this.calendar.getTimeInMillis();
            Calendar instance3 = Calendar.getInstance();
            int timeInMillis2 = (int) ((instance3.getTimeInMillis() - timeInMillis) / 86400000);
            if (timeInMillis2 % 7 == 0) {
                this.calendar.set(1, instance3.get(1));
                this.calendar.set(2, instance3.get(2));
                this.calendar.set(5, instance3.get(5));
                if (instance3.getTimeInMillis() >= this.calendar.getTimeInMillis()) {
                    this.calendar.add(5, 7);
                }
            } else {
                this.calendar.add(5, ((timeInMillis2 + 6) / 7) * 7);
            }
        } else if (this.state.equals("monthly")) {
            Calendar instance4 = Calendar.getInstance();
            GetMonth(this.calendar.get(2), this.calendar.get(1));
            int timeInMillis3 = (int) ((instance4.getTimeInMillis() - this.calendar.getTimeInMillis()) / 86400000);
            if (timeInMillis3 % this.repeatNumber == 0) {
                this.calendar.set(1, instance4.get(1));
                this.calendar.set(2, instance4.get(2));
                this.calendar.set(5, instance4.get(5));
                if (instance4.getTimeInMillis() >= this.calendar.getTimeInMillis()) {
                    this.calendar.add(5, this.repeatNumber);
                }
            } else {
                this.calendar.add(5, ((timeInMillis3 + (this.repeatNumber - 1)) / this.repeatNumber) * this.repeatNumber);
            }
        } else if (this.state.equals("yearly")) {
            Calendar instance5 = Calendar.getInstance();
            GetYear(this.calendar.get(1));
            int timeInMillis4 = (int) ((instance5.getTimeInMillis() - this.calendar.getTimeInMillis()) / 86400000);
            if (timeInMillis4 % this.repeatNumber == 0) {
                this.calendar.set(1, instance5.get(1));
                this.calendar.set(2, instance5.get(2));
                this.calendar.set(5, instance5.get(5));
                if (instance5.getTimeInMillis() >= this.calendar.getTimeInMillis()) {
                    this.calendar.add(5, this.repeatNumber);
                }
            } else {
                this.calendar.add(5, ((timeInMillis4 + (this.repeatNumber - 1)) / this.repeatNumber) * this.repeatNumber);
            }
        } else if (this.state.equals("c_minutely")) {
            Calendar instance6 = Calendar.getInstance();
            long timeInMillis5 = instance6.getTimeInMillis() - this.calendar.getTimeInMillis();
            long j = timeInMillis5 - ((long) (((int) (timeInMillis5 / 86400000)) * 86400000));
            int i4 = ((int) (j - ((long) (((int) (j / 86400000)) * 3600000)))) / 60000;
            if (i4 % this.noOfDays == 0) {
                this.calendar.set(11, instance6.get(11));
                this.calendar.set(12, instance6.get(12));
                if (instance6.getTimeInMillis() >= this.calendar.getTimeInMillis()) {
                    this.calendar.add(12, this.noOfDays);
                }
            } else {
                this.calendar.add(12, ((i4 + (this.noOfDays - 1)) / this.noOfDays) * this.noOfDays);
            }
            int i5 = this.calendar.get(12);
            this.splitTime[1] = String.format("%02d", i5);
        } else if (this.state.equals("c_hourly")) {
            Calendar instance7 = Calendar.getInstance();
            instance7.set(12, 0);
            Calendar instance8 = Calendar.getInstance();
            instance8.set(11, Integer.parseInt(this.splitTime[0]));
            instance8.set(12, 0);
            long timeInMillis6 = instance7.getTimeInMillis() - instance8.getTimeInMillis();
            int i6 = (int) ((timeInMillis6 - ((long) (((int) (timeInMillis6 / 86400000)) * 86400000))) / 3600000);
            if (i6 % this.noOfDays == 0) {
                this.calendar.set(11, instance7.get(11));
                this.calendar.set(1, instance7.get(1));
                this.calendar.set(2, instance7.get(2));
                this.calendar.set(5, instance7.get(5));
                if (Calendar.getInstance().getTimeInMillis() >= this.calendar.getTimeInMillis()) {
                    this.calendar.add(11, this.noOfDays);
                }
            } else {
                this.calendar.add(11, ((i6 + (this.noOfDays - 1)) / this.noOfDays) * this.noOfDays);
            }
        } else if (this.state.equals("c_daily")) {
            long timeInMillis7 = this.calendar.getTimeInMillis();
            Calendar instance9 = Calendar.getInstance();
            int timeInMillis8 = (int) ((instance9.getTimeInMillis() - timeInMillis7) / 86400000);
            if (timeInMillis8 % this.noOfDays == 0) {
                this.calendar.set(1, instance9.get(1));
                this.calendar.set(2, instance9.get(2));
                this.calendar.set(5, instance9.get(5));
                if (instance9.getTimeInMillis() >= this.calendar.getTimeInMillis()) {
                    this.calendar.add(5, this.noOfDays);
                }
            } else {
                this.calendar.add(5, ((timeInMillis8 + (this.noOfDays - 1)) / this.noOfDays) * this.noOfDays);
            }
        } else if (this.state.equals("c_weekly")) {
            long timeInMillis9 = this.calendar.getTimeInMillis();
            Calendar instance10 = Calendar.getInstance();
            int timeInMillis10 = (int) ((instance10.getTimeInMillis() - timeInMillis9) / 86400000);
            if (timeInMillis10 % this.noOfDays == 0) {
                this.calendar.set(1, instance10.get(1));
                this.calendar.set(2, instance10.get(2));
                this.calendar.set(5, instance10.get(5));
                if (instance10.getTimeInMillis() >= this.calendar.getTimeInMillis()) {
                    this.calendar.add(5, this.noOfDays);
                }
            } else {
                this.calendar.add(5, ((timeInMillis10 + (this.noOfDays - 1)) / this.noOfDays) * this.noOfDays);
            }
        } else if (this.state.equals("c_monthly")) {
            long timeInMillis11 = this.calendar.getTimeInMillis();
            Calendar instance11 = Calendar.getInstance();
            int timeInMillis12 = (int) ((instance11.getTimeInMillis() - timeInMillis11) / 86400000);
            GetMonth(this.calendar.get(2), this.calendar.get(1));
            this.noOfDays *= this.repeatNumber;
            if (timeInMillis12 % this.noOfDays == 0) {
                this.calendar.set(1, instance11.get(1));
                this.calendar.set(2, instance11.get(2));
                this.calendar.set(5, instance11.get(5));
                if (instance11.getTimeInMillis() >= this.calendar.getTimeInMillis()) {
                    this.calendar.add(5, this.noOfDays);
                }
            } else {
                this.calendar.add(5, ((timeInMillis12 + (this.noOfDays - 1)) / this.noOfDays) * this.noOfDays);
            }
        } else if (this.state.equals("c_yearly")) {
            long timeInMillis13 = this.calendar.getTimeInMillis();
            Calendar instance12 = Calendar.getInstance();
            int timeInMillis14 = (int) ((instance12.getTimeInMillis() - timeInMillis13) / 86400000);
            GetYear(this.calendar.get(1));
            this.noOfDays *= this.repeatNumber;
            if (timeInMillis14 % this.noOfDays == 0) {
                this.calendar.set(1, instance12.get(1));
                this.calendar.set(2, instance12.get(2));
                this.calendar.set(5, instance12.get(5));
                if (instance12.getTimeInMillis() >= this.calendar.getTimeInMillis()) {
                    this.calendar.add(5, this.noOfDays);
                }
            } else {
                this.calendar.add(5, ((timeInMillis14 + (this.noOfDays - 1)) / this.noOfDays) * this.noOfDays);
            }
        }
        int i7 = this.calendar.get(11);
        this.f85d = this.calendar.get(5);
        this.f88m = this.calendar.get(2);
        this.f89y = this.calendar.get(1);
        this.f88m++;
        this.splitTime[0] = String.format("%02d", i7);
        String format = String.format("%02d", this.f88m);
        String format2 = String.format("%02d", this.f85d);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(this.f89y);
        sb2.append("-");
        sb2.append(format);
        sb2.append("-");
        sb2.append(format2);
        this.preference_date = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(this.splitTime[0]);
        sb3.append(":");
        sb3.append(this.splitTime[1]);
        this.preferenceTime = sb3.toString();
        SplitDateTime();
    }

    private void GetMonth(int i, int i2) {
        if (i == 0 || i == 2 || i == 4 || i == 6 || i == 7 || i == 9 || i == 11) {
            this.repeatNumber = 31;
        }
        if (i == 3 || i == 5 || i == 8 || i == 10) {
            this.repeatNumber = 30;
        }
        if (i != 1) {
            return;
        }
        if (((GregorianCalendar) GregorianCalendar.getInstance()).isLeapYear(i2)) {
            this.repeatNumber = 29;
        } else {
            this.repeatNumber = 28;
        }
    }

    private void GetYear(int i) {
        if (((GregorianCalendar) GregorianCalendar.getInstance()).isLeapYear(i)) {
            this.repeatNumber = 366;
        } else {
            this.repeatNumber = 365;
        }
    }

    private void CancelAllAlarms(Context context) {
        Cursor Select = this.sQliteOpenHelper.Select(this.sQliteOpenHelper.getReadableDatabase(), null);
        if (Select != null) {
            while (Select.moveToNext()) {
                this.f87id = (long) Select.getInt(0);
                Intent intent = new Intent(context, MyBroadcastReceiver.class);
                intent.setAction("ii.mme.mmyself");
                intent.addCategory("android.intent.category.DEFAULT");
                PendingIntent.getBroadcast(context.getApplicationContext(), (int) this.f87id, intent, 268435456);
                Intent intent2 = new Intent(context, SnoozeReceiver.class);
                intent2.setAction("this.is.SnoozeReceiver");
                intent2.addCategory("android.intent.category.DEFAULT");
                PendingIntent.getBroadcast(context.getApplicationContext(), (int) this.f87id, intent2, 268435456);
            }
            Select.close();
        }
    }
}
