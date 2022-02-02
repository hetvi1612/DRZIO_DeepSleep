package drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import java.util.Calendar;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.SQliteOpenHelper;

@SuppressLint("WrongConstant")
public class CancelNotification extends Service {
    public static final String ALARM_DISMISS;
    public static final String ALARM_SNOOZE;
    public static final String URI_BASE;
    AlarmManager alarmManager;
    Calendar calendar;
    int currentCount;
    long f95id;
    PendingIntent pendingIntent;
    String preferenceID;
    String preference_repeat;
    SQliteOpenHelper sQliteOpenHelper;
    SharedPreferences sharedPreferences;
    int snoozeCounts;
    int snoozeInterval;

    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    static {
        StringBuilder sb = new StringBuilder();
        sb.append(CancelNotification.class.getName());
        sb.append(".");
        URI_BASE = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append(URI_BASE);
        sb2.append("ALARM_DISMISS");
        ALARM_DISMISS = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(URI_BASE);
        sb3.append("ALARM_SNOOZE");
        ALARM_SNOOZE = sb3.toString();
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        this.f95id = intent.getLongExtra("notificationID", 0);
        this.preferenceID = Long.toString(this.f95id);
        this.sQliteOpenHelper = new SQliteOpenHelper(this);
        SQliteOpenHelper sQliteOpenHelper2 = new SQliteOpenHelper(this);
        Cursor GetSingleRow = sQliteOpenHelper2.GetSingleRow(sQliteOpenHelper2.getReadableDatabase(), this.f95id);
        try {
            if (GetSingleRow.moveToFirst()) {
                this.preference_repeat = GetSingleRow.getString(2);
            }
            GetSingleRow.close();
            this.sharedPreferences = getApplicationContext().getSharedPreferences("RingtoneURI", 0);
            SharedPreferences sharedPreferences2 = this.sharedPreferences;
            StringBuilder sb = new StringBuilder();
            sb.append(this.preferenceID);
            sb.append("currentCount");
            this.currentCount = Integer.parseInt(sharedPreferences2.getString(sb.toString(), ""));
            this.sharedPreferences = getApplicationContext().getSharedPreferences("SETTINGS", 0);
            String string = this.sharedPreferences.getString("preference_snoozeinterval", "");
            if (string.equals("")) {
                string = "5";
            }
            this.snoozeInterval = Integer.parseInt(string);
            String string2 = this.sharedPreferences.getString("preference_snoozecounts", "");
            if (string2.equals("")) {
                string2 = "5";
            }
            this.snoozeCounts = Integer.parseInt(string2);
            String action = intent.getAction();
            if (ALARM_DISMISS.equals(action)) {
                if (this.preference_repeat.equals("Once")) {
                    sQliteOpenHelper2.DeleteReminder((int) this.f95id);
                    if (ReminderMainActivity.getInstance() != null) {
                        ReminderMainActivity.getInstance().fetchDatabaseToArraylist();
                    }
                }
                dismiss();
                stopSelf();
            } else if (ALARM_SNOOZE.equals(action)) {
                dismiss();
                this.currentCount++;
                snooze();
                stopSelf();
            }
            return START_REDELIVER_INTENT;
        } catch (Throwable th) {
            GetSingleRow.close();
            throw th;
        }
    }

    private void dismiss() {
        ((NotificationManager) getSystemService("notification")).cancel((int) this.f95id);
    }

    public void snooze() {
        this.sharedPreferences = getApplicationContext().getSharedPreferences("RingtoneURI", 0);
        Editor edit = this.sharedPreferences.edit();
        String num = Integer.toString(this.currentCount);
        StringBuilder sb = new StringBuilder();
        sb.append(this.preferenceID);
        sb.append("currentCount");
        edit.putString(sb.toString(), num);
        edit.apply();
        if (this.currentCount <= this.snoozeCounts) {
            Intent intent = new Intent(this, SnoozeReceiver.class);
            intent.setAction("this.is.SnoozeReceiver");
            intent.addCategory("android.intent.category.DEFAULT");
            intent.putExtra("ID", this.f95id);
            this.alarmManager = (AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM);
            this.pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), (int) this.f95id, intent, 134217728);
            this.calendar = Calendar.getInstance();
            if (VERSION.SDK_INT >= 23) {
                this.alarmManager.setExactAndAllowWhileIdle(0, this.calendar.getTimeInMillis() + ((long) (this.snoozeInterval * 60000)), this.pendingIntent);
            } else if (VERSION.SDK_INT >= 19) {
                this.alarmManager.setExact(0, this.calendar.getTimeInMillis() + ((long) (this.snoozeInterval * 60000)), this.pendingIntent);
            } else {
                this.alarmManager.set(0, this.calendar.getTimeInMillis() + ((long) (this.snoozeInterval * 60000)), this.pendingIntent);
            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Reminder Snooze for ");
            sb2.append(this.snoozeInterval);
            sb2.append(" min");
            Toast.makeText(this, sb2.toString(), 1).show();
            return;
        }
        if (this.preference_repeat.equals("Once")) {
            this.sQliteOpenHelper.DeleteReminder((int) this.f95id);
            if (ReminderMainActivity.getInstance() != null) {
                ReminderMainActivity.getInstance().fetchDatabaseToArraylist();
            }
        }
        Toast.makeText(this, "Snooze counts are over", 1).show();
    }
}
