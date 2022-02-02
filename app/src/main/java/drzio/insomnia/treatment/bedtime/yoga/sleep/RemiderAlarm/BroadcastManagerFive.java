package drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.internal.view.SupportMenu;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class BroadcastManagerFive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
          /*  String yourDate = "04/01/2016";
            String yourHour = "16:45:23";
            Date d = new Date();
            DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
            DateFormat hour = new SimpleDateFormat("HH:mm:ss");
            if (date.equals(yourDate) && hour.equals(yourHour)) {*/
                Intent it = new Intent(context, MainActivity.class);
                startNotificationReminder(context,2);
//        }
        } catch (Exception e) {
            Log.i("date", "error == " + e.getMessage());
        }
    }


    @SuppressLint("WrongConstant")
    private void startNotificationReminder(Context context2, int noofdays) {
        String mMessage;
        if (noofdays == 2) {
            mMessage = "You Have Missed Workout Since Two Days. Click Here";
        } else {
            mMessage = "You Have Missed Workout Since Five Days. Click Here";
        }
        if (Build.VERSION.SDK_INT < 26) {
            NotificationManager notificationManager = (NotificationManager) context2.getSystemService("notification");
            Intent intent = new Intent(context2, MainActivity.class);
            intent.setAction("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.LAUNCHER");
            intent.addFlags(268435456);
            Notification build = new Notification.Builder(context2).setContentIntent(PendingIntent.getActivity(context2, 0, intent, 0)).setAutoCancel(true).setWhen(System.currentTimeMillis()).setSmallIcon(R.drawable.ic_notifications_black_24dp).setLargeIcon(BitmapFactory.decodeResource(context2.getResources(), R.drawable.app_icon)).setContentTitle("Hey! it's Deep SleepWorkout time").setContentText("Let's do Cellulite busting Exercise today.").setDefaults(1).build();
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
            builder.setAutoCancel(true).setContentIntent(PendingIntent.getActivity(context2, 0, intent2, 0)).setDefaults(-1).setAutoCancel(true).setWhen(System.currentTimeMillis()).setSmallIcon(R.drawable.ic_notifications_black_24dp).setLargeIcon(BitmapFactory.decodeResource(context2.getResources(), R.drawable.app_icon)).setContentTitle("Hey! it's Deep SleepWorkout time").setContentText(mMessage).setDefaults(1);
            notificationManager2.notify(0, builder.build());
            return;
        }
        throw new AssertionError();

    }

}