package drzio.insomnia.treatment.bedtime.yoga.sleep.firebase;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;


public class MyFirebaseMessagingService extends FirebaseMessagingService {


    private TinyDB mTinydb;
    String key,value;
    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
        mTinydb = new TinyDB(getApplicationContext());
        try {
            Log.e("token", s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mTinydb.putString(Constants.FCMTOKEN_KEY, s);

    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if (remoteMessage.getNotification() != null) {
            String title = remoteMessage.getNotification().getTitle();
            String body = remoteMessage.getNotification().getBody();
            //then here we can use the title and body to build a notification
            for (Map.Entry<String, String> entry : remoteMessage.getData().entrySet()) {
                 key = entry.getKey();
                 value = entry.getValue();
                       Log.e("TAG", "key, " + key + " value " + value);
                    //   String values = remoteMessage.getData().get("value");
            //   Log.e("vakye",values);
            }
            String imageUri = String.valueOf(remoteMessage.getNotification().getImageUrl());
            String linkoption = remoteMessage.getData().get("link_option");
            String bannerlink = remoteMessage.getData().get("banner_link");
            String mDefaulttpe = remoteMessage.getData().get("default_type");
            Bitmap bitmap = getBitmapfromUrl2(imageUri);
            MyNotificationManager.getInstance(getApplicationContext()).displayNotification(title, body, bitmap, linkoption, mDefaulttpe, bannerlink/*,key,value*/);
        } else {
            if (remoteMessage.getData().size() > 0) {
                Map<String, String> data = remoteMessage.getData();
//                if ()
//                22 && 222;
                String title = data.get("title");
                String body = data.get("body");
                String banner_image = data.get("banner_image");
                String link_option = data.get("link_option");
                String default_type = remoteMessage.getData().get("default_type");
                String banner_link = data.get("banner_link");
                String ads_type = data.get("ads_type");
                String notification_type = data.get("notification_type");
                try {
                    Log.e("noti1 ", title);
                    Log.e("noti2 ", body);
                    Log.e("noti3 ", banner_link);
                    Log.e("noti4 ", banner_image);
                    Log.e("noti5 ", remoteMessage.getData().toString());
                    Log.e("noti6 ", default_type);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.e("notierror ", e.getMessage());
                }
                if (banner_image != null){
                    new sendNotification(title, body, banner_image, link_option, default_type, banner_link).execute();
                }else {
                    Bitmap bitmap = getBitmapfromUrl2(banner_image);
                    MyNotificationManager.getInstance(getApplicationContext()).displayNotification(title, body, bitmap, link_option, default_type, banner_link/*,key,value*/);
                }

            }
        }
    }

    public Bitmap getBitmapfromUrl(String imageUrl,String title, String body, String type, String defaulttype, String link) {
        Bitmap chefBitmap = null;
        try {
            chefBitmap = Glide.with(getApplicationContext())
                    .asBitmap()
                    .load(imageUrl)
                    .submit()
                    .get();
            return chefBitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }


    public Bitmap getBitmapfromUrl2(String imageUrl) {
        Bitmap chefBitmap = null;
        try {
            chefBitmap = Glide.with(getApplicationContext())
                    .asBitmap()
                    .load(imageUrl)
                    .submit()
                    .get();
            return chefBitmap;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;

        }
    }


    private class sendNotification extends AsyncTask<Void, Void, Void> {
        String title, body, image, type, defaulttype, link;
        Bitmap bitmap;


        public sendNotification(String title, String body, String image, String type, String defaulttype, String link) {
            this.title = title;
            this.body = body;
            this.image = image;
            this.type = type;
            this.defaulttype = defaulttype;
            this.link = link;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            bitmap = getBitmapfromUrl(image,title,body,type,defaulttype,link);
            Log.e("bitmap", String.valueOf(bitmap));
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            MyNotificationManager.getInstance(getApplicationContext()).displayNotification(title, body, bitmap, type, defaulttype, link/*,key,value*/);

        }
    }
}
