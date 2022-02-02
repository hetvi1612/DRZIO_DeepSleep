package drzio.insomnia.treatment.bedtime.yoga.sleep;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.facebook.FacebookSdk;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.Locale;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.customs.FixCursorWindow;

public class FitnessApplication extends Application {
   public TextToSpeech textToSpeech;
   private static FitnessApplication fitnessApplication;
   private FirebaseAnalytics mFirebaseAnalytics;
   public static TinyDB tinyDB;
//    private AppOpenManager appOpenManager;

    public static FitnessApplication getInstance() {
       return fitnessApplication;
   }

   public Boolean isSpeaking() {
       return this.textToSpeech.isSpeaking();
   }

   @Override
   public void onCreate() {
       if (BuildConfig.DEBUG){
           StrictMode.setThreadPolicy(
                   new StrictMode.ThreadPolicy.Builder()
                           .detectDiskReads()
                           .detectDiskWrites()
                           .detectNetwork()
                           .penaltyLog()
                           .build()
           );
           StrictMode.setVmPolicy(
                   new StrictMode.VmPolicy.Builder()
                           .detectAll()
                           .penaltyLog()
                           .build()
           );
       }
       super.onCreate();
       fitnessApplication = this;
       mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
       AudienceNetworkInitializeHelper.initialize(this);
       MobileAds.initialize(this, new OnInitializationCompleteListener() {
           @Override
           public void onInitializationComplete(InitializationStatus initializationStatus) {
               Log.d("admobinitialized", "true");
           }
       });
       FixCursorWindow.fix();
//        Fabric.with(this, new Crashlytics.Builder().core(new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build()).build());
//        AdSettings.setTestMode(true);
       tinyDB = new TinyDB(getApplicationContext());

       String languages = tinyDB.getString(Constants.Language);
       Constants.languagechange(this, languages);
       FacebookSdk.sdkInitialize(this);
       new Thread(new Runnable() {
           @Override
           public void run() {
               Initialize();
           }
       }).start();


       //appOpenManager = new AppOpenManager(this);

   }


   @Override
   public void onTerminate() {
       super.onTerminate();
   }

   public final void Initialize() {
       try {
           if (this.textToSpeech == null) {
               this.textToSpeech = new TextToSpeech(getInstance(), new TextToSpeech.OnInitListener() {
                   @Override
                   public void onInit(int status) {
                       language(status);
                   }
               });
           }
       }catch (Exception e){
           e.printStackTrace();
       }

   }

   public final void language(int i) {
       if (i == 0) {
           try {
               this.textToSpeech.setLanguage(Locale.US);
           }catch (Exception e){
               e.printStackTrace();
           }
//            this.textToSpeech.setLanguage(Locale.TRADITIONAL_CHINESE);
//            this.textToSpeech.setLanguage(Locale.CANADA_FRENCH);
//            this.textToSpeech.setLanguage(Locale.FRANCE);
//            this.textToSpeech.setLanguage(new Locale ("hi","IN"));
       }
   }


 /*  public void Firebasedata() {
       mref = FirebaseDatabase.getInstance().getReference();
       mref.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               GenericTypeIndicator<Map<String, String>> genericTypeIndicator = new GenericTypeIndicator<Map<String, String>>() {
               };
               Map<String, String> map = dataSnapshot.getValue(genericTypeIndicator);
               String mBannerapi = map.get("bannerapi");
               String mBlogapi = map.get("blogapi");
               String mBloglikeapi = map.get("bloglikeapi");
               String mCityapi = map.get("cityapi");
               String mDietcategory = map.get("dietcategory");
               String mDietlist = map.get("dietlist");
               String mInappbanner = map.get("inappbannerapi");
               String mLoginapi = map.get("loginapi");
               String mRegisterapi = map.get("registerapi");
               String mProfileupdateapi = map.get("updateprofileapi");

               tinyDB.putString(Constants.BANNERAPI_KEY, mBannerapi);
               tinyDB.putString(Constants.BLOGAPI_KEY, mBlogapi);
               tinyDB.putString(Constants.LIKEAPI_KEY, mBloglikeapi);
               tinyDB.putString(Constants.CITYAPI_KEY, mCityapi);
               tinyDB.putString(Constants.DIETCATAPI_KEY, mDietcategory);
               tinyDB.putString(Constants.DIETLISTAPI_KEY, mDietlist);
               tinyDB.putString(Constants.INBANNERAPI_KEY, mInappbanner);
               tinyDB.putString(Constants.LOGINAPI_KEY, mLoginapi);
               tinyDB.putString(Constants.REGISTERAPI_KEY, mRegisterapi);
               tinyDB.putString(Constants.UPDATEPROFILEAPI_KEY, mProfileupdateapi);

           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {

           }
       });
   }*/


   public void speak(String str) {
       try {
           if (this.textToSpeech != null) {
               this.textToSpeech.setSpeechRate(0.9f);
               this.textToSpeech.speak(str, 1, null);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

   public static void isAdLoader(ShimmerFrameLayout shimmerFrameLayout){
       if (tinyDB.getBoolean(Constants.PREMIUN_KEY)){
           shimmerFrameLayout.setVisibility(View.GONE);
       }else {
           shimmerFrameLayout.setVisibility(View.VISIBLE);

       }
   }

   public static void isAdLoader2(ShimmerFrameLayout shimmerFrameLayout, LinearLayout cardView){
       if (tinyDB.getBoolean(Constants.PREMIUN_KEY)){
           cardView.setVisibility(View.GONE);
           shimmerFrameLayout.setVisibility(View.GONE);
       }else {
           cardView.setVisibility(View.VISIBLE);
           shimmerFrameLayout.setVisibility(View.VISIBLE);

       }
   }

   public static void showBanner(Context context, final FrameLayout adMobView, int type) {
       try {

           final AdView mAdView = new AdView(context);
           if (type == 2) {
               mAdView.setAdSize(AdSize.FULL_BANNER);
           } else if (type == 3) {
               mAdView.setAdSize(AdSize.MEDIUM_RECTANGLE);
           } else if (type == 4) {
               mAdView.setAdSize(AdSize.BANNER);
           } else {
               mAdView.setAdSize(AdSize.SMART_BANNER);
           }
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
               }

               @Override
               public void onAdFailedToLoad(int i) {
                   super.onAdFailedToLoad(i);
//                    FitnessApplication.AdfailToast("Banner Ads",String.valueOf(i));
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

   public static void AdfailToast(String Adtype,String Massage){
//        Toast.makeText(getInstance(),Adtype +"Adload fail  Reason : " + Massage,Toast.LENGTH_LONG).show();
   }

   public void stop() {
       try {
           if (this.textToSpeech != null) {
               this.textToSpeech.stop();
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
}
