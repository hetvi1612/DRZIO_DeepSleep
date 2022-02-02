package drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.internal.view.SupportMenu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

@SuppressLint("WrongConstant")
public class NotificationPublisher extends BroadcastReceiver {
    private static final String PREFERENCE_LAST_NOTIF_ID = "PREFERENCE_LAST_NOTIF_ID";

    static final boolean f5046a = true;
    private String TAG = "NotificationPublisher";
    private AlarmHelper alarmHelper;
    private Context context;
    private SharedPreferences sharedPreferences;
    private SimpleDateFormat startTimeFormat;
    TinyDB tinyDB;

    private int getNextNotifId() {
        int i = this.sharedPreferences.getInt(PREFERENCE_LAST_NOTIF_ID, 0) + 1;
        if (i == Integer.MAX_VALUE) {
            i = 0;
        }
        this.sharedPreferences.edit().putInt(PREFERENCE_LAST_NOTIF_ID, i).apply();
        return i;
    }

    private void startNotification(Context context2) {

        PendingIntent existAlarm = this.alarmHelper.existAlarm(this.sharedPreferences.getInt(PREFERENCE_LAST_NOTIF_ID, 0));
        if (existAlarm != null) {
            existAlarm.cancel();
        }
        if (VERSION.SDK_INT < 26) {
            NotificationManager notificationManager = (NotificationManager) context2.getSystemService("notification");
            Intent intent = new Intent(context2, MainActivity.class);
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.addFlags(268435456);
            Notification build = new Notification.Builder(context2).setContentIntent(PendingIntent.getActivity(context2, getNextNotifId(), intent, 0)).setAutoCancel(true).setWhen(System.currentTimeMillis()).setSmallIcon(R.drawable.ic_notifications_black_24dp).setLargeIcon(BitmapFactory.decodeResource(context2.getResources(), R.drawable.app_icon)).setContentTitle("Hey! It's Deep SleepBuster Workout Time").setContentText("Let's do Deep SleepBuster exercise today.").setDefaults(1).build();
            if (f5046a || notificationManager != null) {
                notificationManager.notify(getNextNotifId(), build);
                return;
            }
            throw new AssertionError();
        }
        Intent intent2 = new Intent(context2, MainActivity.class);
        intent2.setAction("android.intent.action.MAIN");
        intent2.addCategory("android.intent.category.LAUNCHER");
        intent2.addFlags(268435456);
        NotificationManager notificationManager2 = (NotificationManager) context2.getSystemService("notification");
        String str = "my_channel_id_01";
        NotificationChannel notificationChannel = new NotificationChannel(str, "My Notifications", 4);
        notificationChannel.setDescription("Channel description");
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(SupportMenu.CATEGORY_MASK);
        notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
        notificationChannel.enableVibration(true);
        if (f5046a || notificationManager2 != null) {
            notificationManager2.createNotificationChannel(notificationChannel);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context2, str);
            builder.setAutoCancel(true).setContentIntent(PendingIntent.getActivity(context2, getNextNotifId(), intent2, 0)).setDefaults(-1).setAutoCancel(true).setWhen(System.currentTimeMillis()).setSmallIcon(R.drawable.ic_notifications_black_24dp).setLargeIcon(BitmapFactory.decodeResource(context2.getResources(), R.drawable.app_icon)).setContentTitle("Hey! It's Deep SleepBuster Workout Time").setContentText("Let's do Deep SleepBuster exercise today.").setDefaults(1);
            notificationManager2.notify(getNextNotifId(), builder.build());
            return;
        }
        throw new AssertionError();

    }




    public void mo19714a(AlarmHelper alarmHelper2, Calendar calendar) {
        int parseInt;
        int parseInt2;
        int i;
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("setTimeHrAndMin   ");
        sb.append(startTimeFormat().format(calendar.getTime()));
        Log.d(str, sb.toString());
        if (startTimeFormat().format(calendar.getTime()).equalsIgnoreCase("AM")) {
            parseInt = Integer.parseInt(getHourFormat().format(calendar.getTime()));
            parseInt2 = Integer.parseInt(getMinuteFormat().format(calendar.getTime()));
            i = 0;
        } else if (startTimeFormat().format(calendar.getTime()).equalsIgnoreCase("PM")) {
            parseInt = Integer.parseInt(getHourFormat().format(calendar.getTime()));
            parseInt2 = Integer.parseInt(getMinuteFormat().format(calendar.getTime()));
            i = 1;
        } else {
            return;
        }
        alarmHelper2.schedulePendingIntent(parseInt, parseInt2, i, 86400000);
    }




    public SimpleDateFormat getHourFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh", Locale.ENGLISH);
        this.startTimeFormat = simpleDateFormat;
        return simpleDateFormat;
    }

    public SimpleDateFormat getMinuteFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm", Locale.ENGLISH);
        this.startTimeFormat = simpleDateFormat;
        return simpleDateFormat;
    }

    public void onReceive(Context context2, Intent intent) {
        String str = this.TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onReceive1 ==========");
        sb.append(intent.getAction());
        Log.d(str, sb.toString());
        this.context = context2;
        tinyDB = new TinyDB(context2);
        this.alarmHelper = new AlarmHelper(context2);
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context2);
        List list = (List) new Gson().fromJson(this.sharedPreferences.getString("Reminder_customObjectList", null), new TypeToken<List<Reminder_custom>>() {
        }.getType());
        Calendar instance = Calendar.getInstance();
        instance.get(11);
        instance.get(12);
        int i = instance.get(7);

      /*  Date c2 = Calendar.getInstance().getTime();
        SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate2 = df2.format(c2);

        if (tinyDB.getString(Constants.TWODAYREMIDERDATE_KEY).equals(formattedDate2)) {
            int LastHour = tinyDB.getInt(Constants.LASTOPENHOUR);
            int LastMinute = tinyDB.getInt(Constants.LASTOPENMINUTE);
            Calendar instance3 = Calendar.getInstance();
            instance3.set(11, LastHour);
            instance3.set(12, LastMinute);
            String tempdate = startTimeFormat().format(instance.getTime());
            Log.d("scheduletime", tempdate.toString());
            if (startTimeFormat().format(instance3.getTime()).equals(tempdate)) {
                startNotificationReminder(context2, 2);
            }
        } else {
            int LastHour = tinyDB.getInt(Constants.LASTOPENHOUR);
            int LastMinute = tinyDB.getInt(Constants.LASTOPENMINUTE);
            Calendar instance2 = Calendar.getInstance();
            instance2.set(11, LastHour);
            instance2.set(12, LastMinute);
            alarmHelper.schedulePendingIntent(LastHour, LastMinute, 1, 172800000);

//            ReminderTime(this.alarmHelper,instance2);
            Log.d("tempschedule", " true");
        }

        if (tinyDB.getString(Constants.FIVEDAYREMIDERDATE_KEY).equals(formattedDate2)) {
            int LastHour = tinyDB.getInt(Constants.LASTOPENHOUR);
            int LastMinute = tinyDB.getInt(Constants.LASTOPENMINUTE);
            Calendar instance4 = Calendar.getInstance();
            instance4.set(11, LastHour);
            instance4.set(12, LastMinute);
            String tempdate = startTimeFormat().format(instance.getTime());
            Log.d("scheduletime", tempdate.toString());
            if (startTimeFormat().format(instance4.getTime()).equals(tempdate)) {
                startNotificationReminder(context2, 2);
            }
        } else {
            int LastHour = tinyDB.getInt(Constants.LASTOPENHOUR);
            int LastMinute = tinyDB.getInt(Constants.LASTOPENMINUTE);
            Calendar instance5 = Calendar.getInstance();
            instance5.set(11, LastHour);
            instance5.set(12, LastMinute);
            ReminderTime(this.alarmHelper,instance5);
        }*/
        if (list != null && list.size() > 0) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                if (((Reminder_custom) list.get(i2)).gettime().equals(startTimeFormat().format(instance.getTime())) && ((Reminder_custom) list.get(i2)).getOnoff()) {
                    if ((((Reminder_custom) list.get(i2)).getSun() && i == 1) || ((((Reminder_custom) list.get(i2)).getMon() && i == 2) || ((((Reminder_custom) list.get(i2)).getTue() && i == 3) || ((((Reminder_custom) list.get(i2)).getWen() && i == 4) || ((((Reminder_custom) list.get(i2)).getThr() && i == 5) || ((((Reminder_custom) list.get(i2)).getFri() && i == 6) || (((Reminder_custom) list.get(i2)).getSat() && i == 7))))))) {
                        startNotification(context2);
                    }
                    mo19714a(this.alarmHelper, instance);
                }
            }
            String action = intent.getAction();
            action.getClass();
            if (action.equals("android.intent.action.TIME_SET")) {
                for (int i3 = 0; i3 < list.size(); i3++) {
                    String str2 = this.TAG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("onReceive: +++++++++++++");
                    sb2.append(((Reminder_custom) list.get(i3)).gettime());
                    Log.d(str2, sb2.toString());
                    if (((Reminder_custom) list.get(i3)).gettime().toUpperCase().endsWith("AM") || ((Reminder_custom) list.get(i3)).gettime().toLowerCase().endsWith("am") || ((Reminder_custom) list.get(i3)).gettime().toUpperCase().endsWith("a.m.") || ((Reminder_custom) list.get(i3)).gettime().toLowerCase().endsWith("a.m.")) {
                        this.alarmHelper.schedulePendingIntent(Integer.parseInt(((Reminder_custom) list.get(i3)).gettime().substring(0, 2)), Integer.parseInt(((Reminder_custom) list.get(i3)).gettime().substring(3, 5)), 0);
                    } else if (((Reminder_custom) list.get(i3)).gettime().toUpperCase().endsWith("PM") || ((Reminder_custom) list.get(i3)).gettime().toUpperCase().endsWith("pm") || ((Reminder_custom) list.get(i3)).gettime().toUpperCase().endsWith("p.m.") || ((Reminder_custom) list.get(i3)).gettime().toLowerCase().endsWith("p.m.")) {
                        this.alarmHelper.schedulePendingIntent(Integer.parseInt(((Reminder_custom) list.get(i3)).gettime().substring(0, 2)), Integer.parseInt(((Reminder_custom) list.get(i3)).gettime().substring(3, 5)), 1);
                    }
                }
            }

        }
    }

    public SimpleDateFormat startTimeFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        this.startTimeFormat = simpleDateFormat;
        return simpleDateFormat;
    }
}
