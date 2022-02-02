package drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build.VERSION;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import java.util.Random;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.SQliteOpenHelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;


@SuppressLint("WrongConstant")
public class SnoozeReceiver extends BroadcastReceiver {
    NotificationCompat.Builder builder;
    int defaults = 0;
    long f94id;
    String preference_AlarmType;
    String preference_Alertvolume;
    String preference_Ringtonemode;
    String reportAs;
    SharedPreferences sharedPreference;
    String time;
    String title;
    private String[] titlelist;
    private String[] desclist;
    private TinyDB tinyDB;


    public void onReceive(Context context, Intent intent) {
        this.f94id = intent.getLongExtra("ID", 0);
        SQliteOpenHelper sQliteOpenHelper = new SQliteOpenHelper(context);
        titlelist = context.getResources().getStringArray(R.array.Notificationtitles);
        desclist = context.getResources().getStringArray(R.array.describe);
        Cursor GetSingleRow = sQliteOpenHelper.GetSingleRow(sQliteOpenHelper.getReadableDatabase(), this.f94id);

        tinyDB = new TinyDB(context);

        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(context, languages);

        try {
            if (GetSingleRow.moveToFirst()) {
                this.title = GetSingleRow.getString(1);
                this.time = GetSingleRow.getString(4);
                this.preference_AlarmType = GetSingleRow.getString(8);
                this.reportAs = GetSingleRow.getString(9);
            }
            GetSingleRow.close();
            String l = Long.toString(this.f94id);

            this.sharedPreference = context.getApplicationContext().getSharedPreferences("SETTINGS", 0);
            this.preference_Ringtonemode = this.sharedPreference.getString("preference_RingtoneMode", "");
            this.preference_Alertvolume = this.sharedPreference.getString("preference_alertVolume", "");
            String str = "Reminder Alarm";
            String string = context.getResources().getString(R.string.app_name);
            Intent intent3 = new Intent(context, CancelNotification.class);
            intent3.putExtra("notificationID", this.f94id);
            intent3.setAction(CancelNotification.ALARM_DISMISS);
            Intent intent4 = new Intent(context, CancelNotification.class);
            intent4.putExtra("notificationID", this.f94id);
            intent4.putExtra("Title", this.title);
            intent4.putExtra("Time", this.time);
            intent4.setAction(CancelNotification.ALARM_SNOOZE);
             PendingIntent service = PendingIntent.getService(context, 0, intent3, 268435456);
            PendingIntent service2 = PendingIntent.getService(context, 0, intent4, 134217728);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
            if (VERSION.SDK_INT >= 26) {
                notificationManager.createNotificationChannel(new NotificationChannel(str, string, 4));
                this.builder = new NotificationCompat.Builder(context).setPriority(1).setChannelId(str);
            } else {
                this.builder = new NotificationCompat.Builder(context).setPriority(1);
            }
            PendingIntent activity = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 134217728);
            RemoteViews collapsedView = new RemoteViews(context.getPackageName(),
                    R.layout.notification_collapsed);
            RemoteViews expandedView = new RemoteViews(context.getPackageName(),
                    R.layout.notification_expanded);
            this.builder.addAction(R.drawable.ic_done_black_24dp, "Done", service);
            this.builder.addAction(R.drawable.ic_access_alarm_black_24dp, "Snooze", service2);
            this.builder.setSmallIcon(R.drawable.ic_notifications_black_24dp);
            this.builder.setContentTitle("Reminder Alarm (Snoozed)");
            this.builder.setContentIntent(activity);
            this.builder.setContentText(this.title);
//            this.builder.setAutoCancel(true);
            this.builder.setShowWhen(false);
            expandedView.setTextViewText(R.id.tvtime,time);
            expandedView.setOnClickPendingIntent(R.id.btnsnooze,service2);


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
                expandedView.setTextViewText(R.id.tvtitle, "Reminder Alarm (Snoozed)");
                expandedView.setTextViewText(R.id.notificationdrsc,sb4.toString());
                collapsedView.setTextViewText(R.id.tvtitle, "Reminder Alarm (Snoozed)");
                collapsedView.setTextViewText(R.id.notificationdrsc,sb4.toString());
            }else {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(title);
                stringBuilder.append(" ");
                stringBuilder.append(context.getResources().getString(R.string.icons));
                expandedView.setTextViewText(R.id.notificationdrsc,stringBuilder.toString());
                collapsedView.setTextViewText(R.id.notificationdrsc,stringBuilder.toString());
            }
            builder.setCustomContentView(collapsedView);
            builder.setCustomBigContentView(expandedView);

            if (this.preference_Ringtonemode.equals("")) {
                RingtoneModeON(context);
            } else if (this.preference_Ringtonemode.equals("true")) {
                RingtoneModeON(context);
            } else {
                Uri defaultUri = RingtoneManager.getDefaultUri(2);
                AudioManager audioManager = (AudioManager) context.getSystemService("audio");
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
            notificationManager.notify((int) this.f94id, this.builder.build());
        } catch (Throwable th) {
            GetSingleRow.close();
            throw th;
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
