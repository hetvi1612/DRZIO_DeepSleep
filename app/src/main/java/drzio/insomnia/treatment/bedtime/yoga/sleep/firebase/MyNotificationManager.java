package drzio.insomnia.treatment.bedtime.yoga.sleep.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Base64;
import android.util.Log;

import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Appstart_Activity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class MyNotificationManager {

    private Context mCtx;
    private static MyNotificationManager mInstance;
    NotificationManager mNotifyMgr;
    NotificationCompat.Builder mBuilder;

    private MyNotificationManager(Context context) {
        mCtx = context;
    }

    String temp = "";

    public static synchronized MyNotificationManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MyNotificationManager(context);
        }
        return mInstance;
    }

    public int createID() {
        Date now = new Date();
        int id = Integer.parseInt(new SimpleDateFormat("ddHHmmss", Locale.US).format(now));
        return id;
    }


    public void displayNotification(String title, String body, Bitmap image, String type, String defaulttype, String link) {
        Intent intent = new Intent(mCtx, Appstart_Activity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("Notification", true);
        intent.putExtra("mNotificationtype", type);
        intent.putExtra("mLink", link);
        intent.putExtra("mDefaulteType", defaulttype);
        int id = createID();
       /* PendingIntent pendingIntent = PendingIntent.getActivity(mCtx, id, intent,
                PendingIntent.FLAG_ONE_SHOT);*/
        PendingIntent pendingIntent = PendingIntent.getActivity(mCtx, id, intent,
                PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            image.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] b = baos.toByteArray();
            temp = Base64.encodeToString(b, Base64.DEFAULT);

            Log.e("temp", temp + "jfgkgjk");
            Log.e("image", String.valueOf(image) + "nulll");
        } catch (Exception e) {

        }
        if (image == null) {


            mBuilder =
                    new NotificationCompat.Builder(mCtx, Constants.CHANNEL_ID)
                            .setSmallIcon(R.drawable.ic_cnotiicon2)
                            .setColor(ContextCompat.getColor(mCtx, R.color.tbtncolor))
                            .setContentTitle(title)
                            .setContentText(body)
                            .setAutoCancel(true)
                            .setStyle(new NotificationCompat.BigTextStyle().bigText(body))
                            .setContentIntent(pendingIntent);
            mNotifyMgr =
                    (NotificationManager) mCtx.getSystemService(NOTIFICATION_SERVICE);


        } else {
         /*   PendingIntent pendingIntent = PendingIntent.getActivity(mCtx, id, intent,
                    PendingIntent.FLAG_ONE_SHOT);*/

            mBuilder =
                    new NotificationCompat.Builder(mCtx, Constants.CHANNEL_ID)
                            .setSmallIcon(R.drawable.ic_cnotiicon2)
                            .setColor(ContextCompat.getColor(mCtx, R.color.tbtncolor))
                            .setLargeIcon(image)
                            .setStyle(new NotificationCompat.BigPictureStyle()
                                    .bigPicture(image))
                            .setContentTitle(title)
                            .setContentText(body)
                            .setAutoCancel(true)
                            .setContentIntent(pendingIntent);
            mNotifyMgr =
                    (NotificationManager) mCtx.getSystemService(NOTIFICATION_SERVICE);


        }

        if (mNotifyMgr != null) {
            mNotifyMgr.notify(id, mBuilder.build());
        }
    }

/*
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(mCtx, Constants.CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_cnotiicon2)
                        .setColor(ContextCompat.getColor(mCtx, R.color.tbtncolor))
                        .setLargeIcon(image)
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(image))
                        .setContentTitle(title)
                        .setContentText(body)
                        .setAutoCancel(true)
                        .setSound(defaultSoundUri)
                        .setContentIntent(pendingIntent);

        NotificationManager mNotifyMgr =
                (NotificationManager) mCtx.getSystemService(NOTIFICATION_SERVICE);


        if (mNotifyMgr != null) {
            mNotifyMgr.notify(id, mBuilder.build());
        }*/
}


       /* public void displayNotification(String title, String body, Bitmap image , String type,String defaulttype,String link,String key,String value) {
            Intent intent = new Intent(mCtx, Appstart_Activity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("Notification",true);
            intent.putExtra("mNotificationtype", type);
            intent.putExtra("mLink", link);
            intent.putExtra("mDefaulteType",defaulttype);
          //  Intent intent1 = new Intent(mCtx, MainActivity.class);
           // intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("openapp", defaulttype);
            intent.putExtra("playstorelink",link);
            intent.putExtra("key",key);
            intent.putExtra("value",value);
            Log.e("defaulttype",defaulttype);
           Log.e("playstorelink",link);
            Log.e("mLink",link);
        int id = createID();
        PendingIntent pendingIntent = PendingIntent.getActivity(mCtx, id, intent,
                PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(mCtx, Constants.CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_cnotiicon2)
                        .setColor(ContextCompat.getColor(mCtx, R.color.tbtncolor))
                        .setLargeIcon(image)
                        .setStyle(new NotificationCompat.BigPictureStyle()
                                .bigPicture(image))
                        .setContentTitle(title)
                        .setContentText(body)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent);

        NotificationManager mNotifyMgr =
                (NotificationManager) mCtx.getSystemService(NOTIFICATION_SERVICE);


        if (mNotifyMgr != null) {
            mNotifyMgr.notify(id, mBuilder.build());
        }
    }
*/


