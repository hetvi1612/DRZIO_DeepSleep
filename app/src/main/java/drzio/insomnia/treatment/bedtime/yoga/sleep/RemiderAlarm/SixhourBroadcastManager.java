package drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;
import androidx.core.internal.view.SupportMenu;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class SixhourBroadcastManager  extends BroadcastReceiver {
    TinyDB tinyDB;
    private String[] titlelist;
    private String[] desclist;

    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            tinyDB = new TinyDB(context);
            String languages = tinyDB.getString(Constants.Language);
            Constants.languagechange(context, languages);

            Intent it = new Intent(context, MainActivity.class);
            Date c2 = Calendar.getInstance().getTime();
            SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
            String formattedDate2 = df2.format(c2);
//            if (formattedDate2.equals(tinyDB.getString(Constants.TWODAYREMIDERDATE_KEY))) {
            startNotificationReminder(context, 1);
//            }
//            if (formattedDate2.equals(tinyDB.getString(Constants.FIVEDAYREMIDERDATE_KEY))) {
//                startNotificationReminder(context, 5);
//            }
            scheduleAlarm(context);
        } catch (Exception e) {
            Log.i("date", "error == " + e.getMessage());
        }
    }

  /*  public void scheduleAlarm(Context context) {
        try {
            long time = new GregorianCalendar().getTimeInMillis() + 5 * 60 * 1000;
            Intent intentAlarm = new Intent(context, BroadcastManager.class);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            assert alarmManager != null;
            alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(context, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    public void scheduleAlarm(Context context) {
        long time = new GregorianCalendar().getTimeInMillis() + 6 * 60 * 60 * 1000;
        //     long time = new GregorianCalendar().getTimeInMillis() + 60000;
        Intent intentAlarm = new Intent(context, SixhourBroadcastManager.class);
        intentAlarm.setAction("ALARM");
        intentAlarm.addCategory("android.intent.category.DEFAULT");
        intentAlarm.putExtra("ID", 1);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        assert alarmManager != null;
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(context, 1, intentAlarm, 0));
        } else if (Build.VERSION.SDK_INT >= 19) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(context, 1, intentAlarm, 0));
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(context, 1, intentAlarm, 0));
        }
    }



    @SuppressLint("WrongConstant")
    private void startNotificationReminder(Context context2, int noofdays) {
        String mMessage;
        String username;
        String mMessagedec;
        username=tinyDB.getString(Constants.USERFIRSTNAME_KEY);
        if (username.trim().equals(""))
        {
            username="Buddy";
        }
        if (noofdays == 1) {

            mMessage = "Hey"+" "+username +"👋"/*+"."+" " +"Check your coin balance. Click Here."*/;
        } else {
            mMessage = "Hey"+" "+username +"👋"/*+"."+" " +"Check your coin balance. Click Here."*/;
        }
        //mMessagedec="Open App Now, Earn Coins, Get Free Plan";
        RemoteViews collapsedView = new RemoteViews(context2.getPackageName(),
                R.layout.notification_collapsed);
        titlelist = context2.getResources().getStringArray(R.array.Notificationtitles);
        desclist = context2.getResources().getStringArray(R.array.describe);
        StringBuilder sb = new StringBuilder();
        try {
            int temp = titlelist.length - 1;
            int random = getRandomNumberInRange(0, temp);
            sb.append(titlelist[random]);
        }catch (Exception e){
            e.printStackTrace();
            sb.append(titlelist[1]);
        }

        StringBuilder sb2 = new StringBuilder();
        try {
            int temp = desclist.length - 1;
            int random2 = getRandomNumberInRange(0, temp);
            sb2.append(desclist[random2]);
        }catch (Exception e){
            e.printStackTrace();
            sb2.append(desclist[1]);
        }

        collapsedView.setTextViewText(R.id.tvtitle, mMessage);
        collapsedView.setTextViewText(R.id.notificationdrsc, sb2.toString());
        //  collapsedView.setTextViewText(R.id.notificationdrsc, sb2.toString());
        if (Build.VERSION.SDK_INT < 26) {
            NotificationManager notificationManager = (NotificationManager) context2.getSystemService("notification");
            Intent intent = new Intent(context2, MainActivity.class);
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.addFlags(268435456);
            Notification build = new Notification.Builder(context2).setContentIntent(PendingIntent.getActivity(context2, 0, intent, 0)).setAutoCancel(true).setWhen(System.currentTimeMillis()).setSmallIcon(R.drawable.ic_notifications_black_24dp).setContentTitle(mMessage).setDefaults(1).build();
            if (notificationManager != null) {
                notificationManager.notify(0, build);
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
        if (notificationManager2 != null) {
            notificationManager2.createNotificationChannel(notificationChannel);
            NotificationCompat.Builder builder = new NotificationCompat.Builder(context2, str);
            builder.setAutoCancel(true).setContentIntent(PendingIntent.getActivity(context2, 0, intent2, 0)).setDefaults(-1).setAutoCancel(true).setWhen(System.currentTimeMillis()).setSmallIcon(R.drawable.ic_notifications_black_24dp).setCustomContentView(collapsedView).setContentTitle(mMessage).setDefaults(1);
            notificationManager2.notify(0, builder.build());
            return;
        }
        throw new AssertionError();

    }

    private int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}