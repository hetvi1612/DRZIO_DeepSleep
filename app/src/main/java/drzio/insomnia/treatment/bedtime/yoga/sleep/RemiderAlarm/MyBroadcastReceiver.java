package drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import java.util.Random;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Appstart_Activity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.SQliteOpenHelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.app_utilities.Utility_ResetAlarm;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;


@SuppressLint("WrongConstant")
public class MyBroadcastReceiver extends BroadcastReceiver {
    NotificationManager NotificationManager;
    NotificationCompat.Builder builder;
    int defaults = 0;
    long f91id;
    PowerManager f92pm;
    String preferenceID;
    String preferenceTime;
    String preference_AlarmType;
    String preference_Alertvolume;
    String preference_Ringtonemode;
    String preference_repeat;
    SharedPreferences preferences;
    String reportAs;
    String title;
    Utility_ResetAlarm utility_resetAlarm;
    WakeLock f93wl;
    private String[] titlelist;
    private String[] desclist;


    @SuppressLint("InvalidWakeLockTag")
    public void onReceive(Context context, Intent intent) {
        Context context2 = context;
        this.f92pm = (PowerManager) context2.getSystemService("power");
        if (this.f92pm != null) {
            this.f93wl = this.f92pm.newWakeLock(26, "wakeup_alarm");
        }
        if (this.f93wl != null && !this.f93wl.isHeld()) {
            this.f93wl.acquire();
        }
        titlelist = context2.getResources().getStringArray(R.array.Notificationtitles);
        desclist = context2.getResources().getStringArray(R.array.describe);
        this.f91id = intent.getLongExtra("ID", 0);
        SQliteOpenHelper sQliteOpenHelper = new SQliteOpenHelper(context2);
        Cursor GetSingleRow = sQliteOpenHelper.GetSingleRow(sQliteOpenHelper.getReadableDatabase(), this.f91id);
        try {
            if (GetSingleRow.moveToFirst()) {
                this.title = GetSingleRow.getString(1);
                this.preference_repeat = GetSingleRow.getString(2);
                this.preferenceTime = GetSingleRow.getString(4);
                this.preference_AlarmType = GetSingleRow.getString(8);
                this.reportAs = GetSingleRow.getString(9);
            }
            GetSingleRow.close();
            this.preferenceID = Long.toString(this.f91id);
            this.utility_resetAlarm = new Utility_ResetAlarm();
            this.preferences = context.getApplicationContext().getSharedPreferences("RingtoneURI", 0);
            Editor edit = this.preferences.edit();
            StringBuilder sb = new StringBuilder();
            sb.append(this.preferenceID);
            sb.append("currentCount");
            edit.putString(sb.toString(), "0");
            StringBuilder sb2 = new StringBuilder();
            sb2.append(this.preferenceID);
            sb2.append("SnoozeClicked");
            edit.putString(sb2.toString(), "New");
            edit.apply();
          /*  if (this.reportAs.equals("Alarm")) {
                Intent intent2 = new Intent(context2, AlarmActivity.class);
                intent2.setFlags(268435456);
                intent2.putExtra("notificationID", this.f91id);
                context2.startActivity(intent2);
            } else {*/
            this.preferences = context.getApplicationContext().getSharedPreferences("SETTINGS", 0);
            this.preference_Ringtonemode = this.preferences.getString("preference_RingtoneMode", "");
            this.preference_Alertvolume = this.preferences.getString("preference_alertVolume", "");
            String str = "Reminder Alarm";
            String string = context.getResources().getString(R.string.app_name);
            NotificationManager notificationManager = this.NotificationManager;
            PendingIntent activity = PendingIntent.getActivity(context2, 0, new Intent(context2, Appstart_Activity.class), 134217728);
            Intent intent3 = new Intent(context2, CancelNotification.class);
            intent3.putExtra("notificationID", this.f91id);
            intent3.setAction(CancelNotification.ALARM_DISMISS);
            Intent intent4 = new Intent(context2, CancelNotification.class);
            intent4.putExtra("notificationID", this.f91id);
            intent4.setAction(CancelNotification.ALARM_SNOOZE);
            PendingIntent service = PendingIntent.getService(context2, 0, intent3, 268435456);
            PendingIntent service2 = PendingIntent.getService(context2, 0, intent4, 134217728);
            this.NotificationManager = (NotificationManager) context2.getSystemService("notification");
            if (VERSION.SDK_INT >= 26) {
                this.NotificationManager.createNotificationChannel(new NotificationChannel(str, string, 4));
                this.builder = new NotificationCompat.Builder(context2).setPriority(1).setChannelId(str);
            } else {
                this.builder = new NotificationCompat.Builder(context2).setPriority(1);
            }

            RemoteViews collapsedView = new RemoteViews(context2.getPackageName(),
                    R.layout.notification_collapsed);
            RemoteViews expandedView = new RemoteViews(context2.getPackageName(),
                    R.layout.notification_expanded);
            this.builder.addAction(R.drawable.ic_done_black_24dp, "Done", service);
            this.builder.addAction(R.drawable.ic_access_alarm_black_24dp, "Snooze", service2);
            this.builder.setSmallIcon(R.drawable.ic_notifications_black_24dp);
            this.builder.setLargeIcon(BitmapFactory.decodeResource(context2.getResources(), R.drawable.app_icon));
//            this.builder.setOngoing(true);
            this.builder.setShowWhen(false);
            this.builder.setContentTitle("Hey! It's Deep Sleep Workout Time");
            expandedView.setTextViewText(R.id.tvtime,preferenceTime);
            expandedView.setOnClickPendingIntent(R.id.btnsnooze,service2);
//            expandedView.setOnClickPendingIntent(R.id.btnstart,activity);

            StringBuilder sb3 = new StringBuilder();
            try {
                int temp = titlelist.length - 1;
                int random = getRandomNumberInRange(0, temp);
                sb3.append(titlelist[random]);
            }catch (Exception e){
                sb3.append("YOGA 360 App");
                e.printStackTrace();
            }


            StringBuilder sb4 = new StringBuilder();
            try {
                int temp = desclist.length - 1;
                int random2 = getRandomNumberInRange(0, temp);
                sb4.append(desclist[random2]);
            }catch (Exception e){
                sb4.append("Let's do Yoga360 exercise today.");
                e.printStackTrace();
            }

            if (this.title.isEmpty()){
                expandedView.setTextViewText(R.id.tvtitle, sb3.toString());
                expandedView.setTextViewText(R.id.notificationdrsc,sb4.toString());
                collapsedView.setTextViewText(R.id.tvtitle, sb3.toString());
                collapsedView.setTextViewText(R.id.notificationdrsc,sb4.toString());
            }else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(title);
                stringBuilder.append(" ");
                stringBuilder.append(context2.getResources().getString(R.string.icons));
                expandedView.setTextViewText(R.id.notificationdrsc,stringBuilder.toString());
                collapsedView.setTextViewText(R.id.notificationdrsc,stringBuilder.toString());
            }
            builder.setCustomContentView(collapsedView);
            builder.setCustomBigContentView(expandedView);
            this.builder.setContentIntent(activity);
            if (this.title.isEmpty()){
                this.builder.setContentText("Let's do Yoga360 exercise today.");
            }else {
                this.builder.setContentText(this.title);
            }
            this.builder.setAutoCancel(true);
            if (this.preference_Ringtonemode.equals("")) {
                RingtoneModeON(context);
            } else if (this.preference_Ringtonemode.equals("true")) {
                RingtoneModeON(context);
            } else {
                Uri defaultUri = RingtoneManager.getDefaultUri(2);
                AudioManager audioManager = (AudioManager) context2.getSystemService("audio");
                if (this.preference_Alertvolume.equals("")) {
                    this.preference_Alertvolume = "15";
                }
                audioManager.setStreamVolume(4, Integer.parseInt(this.preference_Alertvolume), 0);
                int streamVolume = audioManager.getStreamVolume(4);
                if (this.preference_AlarmType.equals("Sound")) {
                    this.builder.setSound(defaultUri, streamVolume);
                } else if (this.preference_AlarmType.equals("Vibration")) {
                    this.defaults |= 2;
                    this.builder.setDefaults(this.defaults);
                } else if (this.preference_AlarmType.equals("Sound and Vibration")) {
                    this.builder.setSound(defaultUri, streamVolume);
                    this.defaults |= 2;
                    this.builder.setDefaults(this.defaults);
                }
            }
            this.NotificationManager.notify((int) this.f91id, this.builder.build());
            if (!this.preference_repeat.equals("Once")) {
                this.utility_resetAlarm.RestartAlarm(context2, "no", this.f91id);
            }
//            }
            this.f93wl.release();
        } catch (Throwable th) {
            Throwable th2 = th;
            GetSingleRow.close();
            try {
                throw th2;
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }
    }

    private void RingtoneModeON(Context context) {
        switch (((AudioManager) context.getSystemService("audio")).getRingerMode()) {
            case 0:
                this.defaults |= 2;
                break;
            case 1:
                this.defaults |= 2;
                break;
            case 2:
                if (!this.preference_AlarmType.equals("Sound")) {
                    if (!this.preference_AlarmType.equals("Vibration")) {
                        if (this.preference_AlarmType.equals("Sound and Vibration")) {
                            this.defaults |= 1;
                            this.defaults |= 2;
                            break;
                        }
                    } else {
                        this.defaults |= 2;
                        break;
                    }
                } else {
                    this.defaults |= 1;
                    break;
                }
                break;
        }
        this.builder.setDefaults(this.defaults);
    }

    private int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}

