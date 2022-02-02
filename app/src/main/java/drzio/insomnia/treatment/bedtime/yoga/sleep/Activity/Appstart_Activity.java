package drzio.insomnia.treatment.bedtime.yoga.sleep.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.Appdata;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.CategoryData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.ExituserData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.ApiInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.Pricedata;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.InappBannerModal1;
import drzio.insomnia.treatment.bedtime.yoga.sleep.util.IabBroadcastReceiver;
import drzio.insomnia.treatment.bedtime.yoga.sleep.util.IabHelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIClient;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.github.mikephil.charting.charts.Chart.LOG_TAG;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.isFromnotification;
import static retrofit2.converter.scalars.ScalarsConverterFactory.create;


public class Appstart_Activity extends AppCompatActivity {
    public boolean success;
    public ArrayList<InappBannerModal1.Doc> mInappBannerdata = new ArrayList<>();
    ArrayList<Appdata.Datalist> mAppdatalist = new ArrayList<>();
    ArrayList<CategoryData.Datalist> mCatdatalist = new ArrayList<>();
    private TinyDB tinyDB;
    private InterstitialAd mInterstitialAdMob;
    private boolean success4;
    private Gson gson;
    private String Notificationtype;
    private String link;
    private String Defaulttype;
    private BackpainAPIInterface apiInterface;
    //    static final String ITEM_SKU = "backpain_lifetime";
    static String ITEM_SKU = "";
    private static final String TAG =
            "activitysplesh";
    private IabHelper mHelper;
    private IabBroadcastReceiver mBroadcastReceiver;
    String key, value;
    boolean isnotification = false;
    Bundle bundle;
    private String token;
    String BASEURL = "";
    private ApiInterface apiInterfacess;
    private BillingProcessor bp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_appstart);

        Constants.isRatedialShown = false;
        tinyDB = new TinyDB(Appstart_Activity.this);
        BASEURL = tinyDB.getString(Constants.NewBaseUrl);

        token = tinyDB.getString(Constants.Authtoken);
        ITEM_SKU = tinyDB.getString(Constants.ITEM_SKU1);
        Log.e("ITEM_SKU", ITEM_SKU);
        String getlan = Resources.getSystem().getConfiguration().locale.getLanguage();
        Log.e("getlan", getlan);

        if (tinyDB.getBoolean(Constants.AppLanguage)) {
            String languages = tinyDB.getString(Constants.Language);
            Constants.languagechange(this, languages);
            Log.e("languageslanguages1", languages);
            Log.e("AppLanguage1", String.valueOf(tinyDB.getBoolean(Constants.AppLanguage)));
        } else {
            switch (getlan) {
                case "hi":
                    tinyDB.putString(Constants.Language, "hi");
                    Constants.languagechange(this, "hi");
                    //  tinyDB.putString(Constants.DefalutLanguage, "hindi");
                    break;
                case "ar":
                    tinyDB.putString(Constants.Language, "ar");
                    Constants.languagechange(this, "ar");
                    //  tinyDB.putString(Constants.DefalutLanguage, "hindi");
                    break;
                case "th":
                    tinyDB.putString(Constants.Language, "th");
                    Constants.languagechange(this, "th");
                    //  tinyDB.putString(Constants.DefalutLanguage, "english");
                    break;
                case "ru":
                    tinyDB.putString(Constants.Language, "ru");
                    Constants.languagechange(this, "ru");
                    //  tinyDB.putString(Constants.DefalutLanguage, "english");
                    break;
                case "pt":
                    tinyDB.putString(Constants.Language, "pt");
                    Constants.languagechange(this, "pt");
                    //  tinyDB.putString(Constants.DefalutLanguage, "english");
                    break;
                case "es":
                    tinyDB.putString(Constants.Language, "es");
                    Constants.languagechange(this, "es");
                    //  tinyDB.putString(Constants.DefalutLanguage, "english");
                    break;
                case "id":
                    tinyDB.putString(Constants.Language, "id");
                    Constants.languagechange(this, "id");
                    //  tinyDB.putString(Constants.DefalutLanguage, "english");
                    break;
                case "bn":
                    tinyDB.putString(Constants.Language, "bn");
                    Constants.languagechange(this, "bn");
                    //  tinyDB.putString(Constants.DefalutLanguage, "hindi");
                    break;
                case "da":
                    tinyDB.putString(Constants.Language, "da");
                    Constants.languagechange(this, "da");
                    //  tinyDB.putString(Constants.DefalutLanguage, "hindi");
                    break;
                case "it":
                    tinyDB.putString(Constants.Language, "it");
                    Constants.languagechange(this, "it");
                    //  tinyDB.putString(Constants.DefalutLanguage, "english");
                    break;
                case "tr":
                    tinyDB.putString(Constants.Language, "tr");
                    Constants.languagechange(this, "tr");
                    //  tinyDB.putString(Constants.DefalutLanguage, "english");
                    break;
                case "nl":
                    tinyDB.putString(Constants.Language, "nl");
                    Constants.languagechange(this, "nl");
                    //  tinyDB.putString(Constants.DefalutLanguage, "english");
                    break;
                case "ko":
                    tinyDB.putString(Constants.Language, "ko");
                    Constants.languagechange(this, "ko");
                    //  tinyDB.putString(Constants.DefalutLanguage, "english");
                    break;
                case "su":
                    tinyDB.putString(Constants.Language, "su");
                    Constants.languagechange(this, "su");
                    //  tinyDB.putString(Constants.DefalutLanguage, "english");
                    break;
                case "ta":
                    tinyDB.putString(Constants.Language, "ta");
                    Constants.languagechange(this, "ta");
                    //  tinyDB.putString(Constants.DefalutLanguage, "english");
                    break;
                case "te":
                    tinyDB.putString(Constants.Language, "te");
                    Constants.languagechange(this, "te");
                    //  tinyDB.putString(Constants.DefalutLanguage, "english");
                    break;
                case "pa":
                    tinyDB.putString(Constants.Language, "pa");
                    Constants.languagechange(this, "pa");
                    //  tinyDB.putString(Constants.DefalutLanguage, "english");
                    break;
                case "fa":
                    tinyDB.putString(Constants.Language, "fa");
                    Constants.languagechange(this, "fa");
                    //  tinyDB.putString(Constants.DefalutLanguage, "english");
                    break;
                case "pl":
                    tinyDB.putString(Constants.Language, "pl");
                    Constants.languagechange(this, "pl");
                    //  tinyDB.putString(Constants.DefalutLanguage, "english");
                    break;
                default:
                    tinyDB.putString(Constants.Language, "en");
                    Constants.languagechange(this, "en");
                    //  tinyDB.putString(Constants.DefalutLanguage, "english");
                    break;
            }
        }
     /*   Log.e("getDisplayLanguage", getlan);
        if (tinyDB.getBoolean(Constants.AppLanguage)) {
            String languages = tinyDB.getString(Constants.Language);
            Constants.languagechange(this, languages);
        } else {
            if (getlan.equals("हिन्दी")) {
                tinyDB.putString(Constants.Language, "hi");
                Constants.languagechange(this, "hi");
            } else {
                tinyDB.putString(Constants.Language, "en");
                Constants.languagechange(this, "en");
            }
        }*/

        bundle = getIntent().getExtras();
        if (bundle != null) {
            try {


                isnotification = bundle.getBoolean("Notification");
                Notificationtype = bundle.getString("mNotificationtype");
                link = bundle.getString("mLink");
                Defaulttype = bundle.getString("mDefaulteType");

                key = bundle.getString("key");
                value = bundle.getString("value");
           /*     if (!key.isEmpty()) {
                    if (key.equals("playstorelink")) {
                        Uri uri1 = Uri.parse(value);
                        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri1);
                        try {
                            startActivity(myAppLinkToMarket);
                        } catch (ActivityNotFoundException e) {
                            Toast.makeText(getApplicationContext(), "You don't have Google Play installed", Toast.LENGTH_LONG).show();
                        }
                    } else if (key.equals("openapp") && value.equals("true")) {
                        startActivity(new Intent(Appstart_Activity.this, Activity_Discover.class));
                    }

                }*/
                Log.e("notidata", Defaulttype + link + Notificationtype + isnotification);
            } catch (Exception e) {

            }
        }

        //getPriceList();



      /*  Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            try {


            isnotification = bundle.getBoolean("Notification");
            Notificationtype = bundle.getString("mNotificationtype");
            link = bundle.getString("mLink");
            Defaulttype = bundle.getString("mDefaulteType");
            Log.e("notidata", Defaulttype + link + Notificationtype + isnotification);
            }catch (Exception e){

            }
        }*/
      /*  if (!tinyDB.getBoolean(Constants.PREMIUN_KEY)) {
            Constants.admob_banner = getString(R.string.admob_banner);
            Constants.admob_Interstitial = getString(R.string.admob_Interstitial);
            Constants.admob_nativead = getString(R.string.admob_nativead);
            Constants.admob_rewardad = getString(R.string.admob_rewardedad);
            Constants.facebook_banner = getString(R.string.facebook_banner);
            Constants.facebook_interstitial = getString(R.string.facebook_interstitial);
            Constants.facebook_native = getString(R.string.facebook_native);
            Constants.facebook_rectangle = getString(R.string.facebook_rectangle);
            Constants.facebooknative_banner = getString(R.string.facebooknative_banner);
        } else {
            Constants.admob_banner = "";
            Constants.admob_Interstitial = "";
            Constants.admob_nativead = "";
            Constants.admob_rewardad = "";
            Constants.facebook_banner = "";
            Constants.facebook_interstitial = "";
            Constants.facebook_native = "";
            Constants.facebook_rectangle = "";
            Constants.facebooknative_banner = "";
        }*/
      //  OneTimePurchaseCheck();

        PurchaseCheck();
        mInappBannerdata.clear();
        String androidId = 22+Settings.Secure.getString(getContentResolver(),
                Settings.Secure.ANDROID_ID)+22;
        Log.e("device_id", androidId);
        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();

            // Log.e("database", String.valueOf(database));
            DatabaseReference mRefer = database.getReference();
            mRefer.child("ApiName").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        String name = postSnapshot.getKey().toString();
                        Log.e("apiname", name);
                        if (name.equals("AppstoreApi")) {
                            tinyDB.putString(Constants.AppstoreUrl, (String) postSnapshot.getValue());
                            Log.e("Api1", (String) postSnapshot.getValue());
                        }
                        if (name.equals("DeepSleep")) {
                            tinyDB.putString(Constants.CelluliteUrl, (String) postSnapshot.getValue());
                            Log.e("Api2", (String) postSnapshot.getValue());
                        }
                        if (name.equals("YoutubeApi")) {
                            tinyDB.putString(Constants.YoutubeApi, (String) postSnapshot.getValue());
                            Log.e("Api3", (String) postSnapshot.getValue());
                        }
                        if (name.equals("Appstore_Appid")) {
                            tinyDB.putString(Constants.Appstore_Appid, (String) postSnapshot.getValue());
                            Log.e("Api4", (String) postSnapshot.getValue());
                        }
                        if (name.equals("Banner_id")) {
                            tinyDB.putString(Constants.Storebanner_id, (String) postSnapshot.getValue());
                            Log.e("Api5", (String) postSnapshot.getValue());
                        }
                        if (name.equals("NewsleepApi")) {
                            tinyDB.putString(Constants.NewBaseUrl, (String) postSnapshot.getValue());

//                             tinyDB.putString(Constants.NewBaseUrl, "http://65.0.77.129:7079/");
                            Log.e("Api6", (String) postSnapshot.getValue());
                            Log.e("Apitest", tinyDB.getString(Constants.NewBaseUrl));
                        }
                        if (name.equals("sleepimgurl")) {
                            tinyDB.putString(Constants.Backimageurl, (String) postSnapshot.getValue());
                            Log.e("Api7", (String) postSnapshot.getValue());
                        }
                    }
                    try {
                        getcallInAppBannerApi();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {

        }


        if (!tinyDB.getBoolean(Constants.PREMIUN_KEY)) {
            initAdmobFullAd(Appstart_Activity.this);
            loadAdmobAd();
        }

        TextView mTvlay1 = (TextView) findViewById(R.id.tvlay1);
        mTvlay1.setText(getResources().getString(R.string.height_increase_workout));
        //  TextView mTvlay2 = (TextView) findViewById(R.id.tvlay2);
        View mLine1 = (View) findViewById(R.id.line1);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.splesh_bottom_up);
        mLine1.startAnimation(animation);
        mTvlay1.startAnimation(animation);

        init(isnotification);

    }

    public void PurchaseCheck() {
        String LICENSE_KEY = getString(R.string.base64key);
        bp = new BillingProcessor(this, LICENSE_KEY, null, new BillingProcessor.IBillingHandler() {
            @Override
            public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
            }

            @Override
            public void onBillingError(int errorCode, @Nullable Throwable error) {
                Log.d(LOG_TAG, "onBillingError: " + Integer.toString(errorCode));
                if (!tinyDB.getBoolean(Constants.PREMIUN_KEY)) {
                    ShowAds();
                }
            }

            @Override
            public void onBillingInitialized() {
                bp.loadOwnedPurchasesFromGoogle();
                String   Lifetime1 = tinyDB.getString(Constants.ITEM_SKU1);
                String Oneyear1 = tinyDB.getString(Constants.SKU_DELAROY_SIXMONTH);
                String Onemonth1 = tinyDB.getString(Constants.SKU_DELAROY_MONTHLY);
                if (bp.isPurchased(Lifetime1)) {
                    tinyDB.putBoolean(Constants.PREMIUN_KEY, true);
                    NoAds();

                    if (bp.loadOwnedPurchasesFromGoogle()) {
                        Log.d(LOG_TAG, "Owned Subscription: " + "Oneyear Subscriptions updated.");
                    }
                    Log.d(LOG_TAG, "Owned Subscription: " + "onetime");
                } else if (bp.isSubscribed(Onemonth1)) {
                    tinyDB.putBoolean(Constants.PREMIUN_KEY, true);
                    NoAds();

                    Log.d(LOG_TAG, "Owned Subscription: " + "1 Month");
                    if (bp.loadOwnedPurchasesFromGoogle()) {
                        Log.d(LOG_TAG, "update2: " + bp.loadOwnedPurchasesFromGoogle());
                    }
                } else if (bp.isSubscribed(Oneyear1)) {
                    tinyDB.putBoolean(Constants.PREMIUN_KEY, true);
                    NoAds();

                    if (bp.loadOwnedPurchasesFromGoogle()) {
                        Log.d(LOG_TAG, "Owned Subscription: " + "Oneyear Subscriptions updated.");
                    }
                } else {
                    //   String   Lifetime1 = tinyDB.getString(Constants.ITEM_SKU1);
                    tinyDB.putBoolean(Constants.PREMIUN_KEY, false);
                    ShowAds();
                    bp.consumePurchase(Lifetime1);
                    Log.d(LOG_TAG, "Owned Subscription: " + "No Subscripytions");
                }
            }

            @Override
            public void onPurchaseHistoryRestored() {
               /* if (bp.loadOwnedPurchasesFromGoogle()) {
                    tinyDB.putBoolean(Constants.PREMIUN_KEY,true);
                    NoAds();
                }*/
                Log.d(LOG_TAG, "onPurchaseHistoryRestored");
                for (String sku : bp.listOwnedProducts())
                    Log.d(LOG_TAG, "Owned Managed Product: " + sku);
                for (String sku : bp.listOwnedSubscriptions())
                    Log.d(LOG_TAG, "Owned Subscription: " + sku);
            }
        });

    }

    private void getPriceList() {
        try {
            //  countryCode = tinydb.getString(Constants.COUNTRY_CODEID);
           /* Locale locale = Locale.getDefault();
            Currency currency = Currency.getInstance(locale);*/
          /*  String code = Resources.getSystem().getConfiguration().locale.getCountry();
            Log.e("currencycpode", code);
            // CurrencyCode = String.valueOf(Currency.getInstance(code));
            CurrencyCode = Currency.getInstance(new Locale("", code)).getCurrencyCode();
            Log.e("currency", CurrencyCode);*/


            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(loggingInterceptor);

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            OkHttpClient client = httpClient.build();

            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASEURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
            //    apiInterface = (ApiInterface) CelluliteAPIClient.getCelluliteClient1().create(ApiInterface.class);
            ApiInterface apiInterface = retrofit.create(ApiInterface.class);

            // Log.e("pricetoken", token);
            Call<Pricedata> userCall = apiInterface.getprice_new("Bearer " + token);
            userCall.enqueue(new Callback<Pricedata>() {
                @Override
                public void onResponse(Call<Pricedata> call, Response<Pricedata> response) {
                    try {


                        //       Log.e("priceresponse", String.valueOf(response.body()));
                        Pricedata loginData = response.body();
                        String symbol = loginData.getCurrSymbol();

                        // currency_code = loginData.getCurrencyCode();
                        //       Log.e("currency_code", currency_code);


                        String month_key = loginData.getMonthKey();
                        String one_y_key = loginData.getOneYKey();
                        String life_t_key = loginData.getLifeTKey();
                        String percentage = loginData.getPercent_offer();
                        String percentage2 = loginData.getOffer_2();


                        //  Log.e("percentage", percentage);
                        if (percentage.isEmpty()) {
                            tinyDB.putString(Constants.DISCOUNT_TXT, "null");
                        } else {
                            tinyDB.putString(Constants.DISCOUNT_TXT, percentage);
                        }
                        if (percentage2.isEmpty()) {
                            tinyDB.putString(Constants.DISCOUNT_TXT2, "null");
                        } else {
                            tinyDB.putString(Constants.DISCOUNT_TXT2, percentage2);
                        }

                        tinyDB.putString(Constants.SKU_DELAROY_MONTHLY, month_key);
                        tinyDB.putString(Constants.SKU_DELAROY_SIXMONTH, one_y_key);
                        tinyDB.putString(Constants.ITEM_SKU1, life_t_key);
                        //OneTimePurchaseCheck();

                    } catch (Exception e) {

                    }
                    //Pricedata. datalists = loginData.result;
                    //  Log.e("id", String.valueOf(Pricedata.id));

                }

                @Override
                public void onFailure(Call<Pricedata> call, Throwable t) {
                    // Log.e("response1", String.valueOf("response"));
                }
            });
        } catch (Exception e) {

        }
    }


    private void SendToken1(String authe) {
        String BaseUrl23 = tinyDB.getString(Constants.NewBaseUrl);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseUrl23)
                .addConverterFactory(create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //  ApiInterface   apiInterface = retrofit.create(ApiInterface.class);

        ApiInterface apiInterface = (ApiInterface) BackpainAPIClient.getCelluliteClient1().create(ApiInterface.class);
        //     ApiInterface   apiInterface = (ApiInterface) CelluliteAPIClient.getCelluliteClient1().create(ApiInterface.class);
        try {
           /* JSONObject paramObject = new JSONObject();
            paramObject.put("device_id", androidId);*/
            //  paramObject.put("country_name", "INR");


            Call<ExituserData> userCall = apiInterface.getisExit1("Bearer " + authe);
            userCall.enqueue(new Callback<ExituserData>() {
                @Override
                public void onResponse(Call<ExituserData> call, Response<ExituserData> response) {
                    try {


                        Log.e("responsesendtoken1", String.valueOf(response.body()));
                        ExituserData badge = response.body();
                        String token = badge.getToken();
                        tinyDB.putString(Constants.Authtoken, token);
                        ExituserData.User user = badge.getUser();

                        Integer currentString = user.getWorkoutTime();
                        String time = String.valueOf(currentString);
                        // Log.e("coinscoinscoinscoins", String.valueOf(coins));
                        //Log.e("tokentokentoken", String.valueOf(token));
                        // StringTokenizer   StringTokenizer  = new StringTokenizer(time, ":");

                 /*   StringTokenizer tokens = new StringTokenizer(time, ":");
                    int minutes = Integer.parseInt(tokens.nextToken());// this will contain "Fruit"
                    int  seconds= Integer.parseInt(tokens.nextToken());
                    long milliSeconds= ((minutes * 60) + seconds) * 1000;
                    tinyDB.putLong(Constants.TOTALTIME_KEY, milliSeconds);*/
                        tinyDB.putLong(Constants.TOTALTIME_KEY, Long.parseLong(time));

                        String user_id = user.getId();
                        tinyDB.putString(Constants.USERID, user_id);


                        ExituserData.User.Referral referral = user.getReferral();
                        String reffalcode = referral.getCode();
                        int referredCount = referral.getReferredCount();


                        int ExeCount = user.getExercise();
                        tinyDB.putInt(Constants.TOTALEXE_KEY, ExeCount);
                        float Calory = user.getBurntCalory();
                        tinyDB.putFloat(Constants.TOTALKCAL_KEY, Calory);
                    } catch (Exception e) {

                    }
                }

                @Override
                public void onFailure(Call<ExituserData> call, Throwable t) {
                    // Log.e("response1", String.valueOf("response"));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void SendToken(String androidId) {
        try {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(loggingInterceptor);
            OkHttpClient client = httpClient.build();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            // tinyDB.getString(Constants.NewFemaleFitness);
            try {


                BASEURL = tinyDB.getString(Constants.NewBaseUrl);
                Retrofit retrofit = new Retrofit.Builder().baseUrl(BASEURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(client)
                        .build();
                //    ApiInterface   apiInterface = (ApiInterface) CelluliteAPIClient.getCelluliteClient1().create(ApiInterface.class);
                //     ApiInterface   apiInterface = retrofit.create(ApiInterface.class);
//        this.apiInterfaces = (ApiInterface) Objects.requireNonNull(CelluliteAPIClient.getCelluliteClient()).create(ApiInterface.class);
                apiInterfacess = retrofit.create(ApiInterface.class);

                JSONObject paramObject = new JSONObject();
                paramObject.put("device_id", androidId);
                //  paramObject.put("country_name", "INR");


                Call<ExituserData> userCall = apiInterfacess.getisExit(paramObject.toString());
                userCall.enqueue(new Callback<ExituserData>() {
                    @Override
                    public void onResponse(Call<ExituserData> call, Response<ExituserData> response) {
                        try {


                            Log.e("responsesendtoken", String.valueOf(response.body()));
                            ExituserData badge = response.body();
                            String token = badge.getToken();
                            tinyDB.putString(Constants.Authtoken, token);
                            ExituserData.User user = badge.getUser();
                            Integer coins = user.getCoin();


                            // Log.e("coinscoinscoinscoins", String.valueOf(coins));

                            Integer currentString = user.getWorkoutTime();
                            String time = String.valueOf(currentString);
                            Log.e("tokentokentoken", String.valueOf(token));
                            // StringTokenizer   StringTokenizer  = new StringTokenizer(time, ":");

                   /* StringTokenizer tokens = new StringTokenizer(time, ":");
                    int minutes = Integer.parseInt(tokens.nextToken());// this will contain "Fruit"
                    int  seconds= Integer.parseInt(tokens.nextToken());
                    long milliSeconds= ((minutes * 60) + seconds) * 1000;
                    tinyDB.putLong(Constants.TOTALTIME_KEY, time);
*/
                            tinyDB.putLong(Constants.TOTALTIME_KEY, Long.parseLong(time));
                            String user_id = user.getId();
                            tinyDB.putString(Constants.USERID, user_id);


                            ExituserData.User.Referral referral = user.getReferral();
                            String reffalcode = referral.getCode();


                            int referredCount = referral.getReferredCount();
                            int ExeCount = user.getExercise();
                            tinyDB.putInt(Constants.TOTALEXE_KEY, ExeCount);
                            float Calory = user.getBurntCalory();
                            tinyDB.putFloat(Constants.TOTALKCAL_KEY, Calory);
                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onFailure(Call<ExituserData> call, Throwable t) {
                        Log.e("response1", String.valueOf("response"));
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
        }
    }


 /*   public void OneTimePurchaseCheck() {
        String base64EncodedPublicKey = getString(R.string.base64key);
        mHelper = new IabHelper(this, base64EncodedPublicKey);
        mHelper.enableDebugLogging(true);

        IabHelper.QueryInventoryFinishedListener mGotInventoryListener = (result, inv) -> {
            if (result.isFailure()) {
                Log.e("onetimecheck11", "failed to check");
                ShowAds();
                return;
            }
            boolean mIsPremium = inv.hasPurchase(ITEM_SKU);
            if (mIsPremium) {
                tinyDB.putBoolean(Constants.ONETIMEPURCHASE, true);
                tinyDB.putBoolean(Constants.PREMIUN_KEY, true);
                Log.e("onetimecheck1", String.valueOf(mIsPremium));
                NoAds();
            *//*    mHelper.consumeAsync(inv.getPurchase(ITEM_SKU),
                        new IabHelper.OnConsumeFinishedListener() {
                            @Override
                            public void onConsumeFinished(Purchase purchase, IabResult result) {
                                Toast.makeText(Appstart_Activity.this,"Purchase Consumed",Toast.LENGTH_SHORT).show();
                            }
                        });*//*
            } else {
                tinyDB.putBoolean(Constants.ONETIMEPURCHASE, false);
                tinyDB.putBoolean(Constants.PREMIUN_KEY, false);
                new DynamicBillingCheck().onCreate(Appstart_Activity.this);
                Log.e("onetimecheck12", String.valueOf(mIsPremium));
            }
        };
        mHelper.startSetup(result -> {
            if (!result.isSuccess()) {
                ShowAds();
                Log.d(TAG, "In-app Billing setup failed: " +
                        result);
            } else {
                Log.d(TAG, "In-app Billing is set up OK");
            }

            mBroadcastReceiver = new IabBroadcastReceiver(() -> {
                Log.d(TAG, "Received broadcast notification. Querying inventory.");
                try {
                    mHelper.queryInventoryAsync(mGotInventoryListener);
                } catch (Exception e) {
                    Log.d(TAG, "Error querying inventory. Another async operation in progress.");
                }
            });
            IntentFilter broadcastFilter = new IntentFilter(IabBroadcastReceiver.ACTION);
            registerReceiver(mBroadcastReceiver, broadcastFilter);

            try {
                mHelper.queryInventoryAsync(mGotInventoryListener);
            } catch (Exception e) {
                Log.d(TAG, "Error querying inventory. Another async operation in progress.");
            }
        });
    }*/


    public void ShowAds() {
        Constants.admob_openad = getString(R.string.admob_openapp);
        Constants.admob_banner = getString(R.string.admob_banner);
        Constants.admob_Interstitial = getString(R.string.admob_Interstitial);
        Constants.admob_nativead = getString(R.string.admob_nativead);
        Constants.admob_rewardad = getString(R.string.admob_rewardedad);
        Constants.facebook_banner = getString(R.string.facebook_banner);
        Constants.facebook_interstitial = getString(R.string.facebook_interstitial);
        Constants.facebook_native = getString(R.string.facebook_native);
        Constants.facebook_rectangle = getString(R.string.facebook_rectangle);
        Constants.facebooknative_banner = getString(R.string.facebooknative_banner);
    }

    public void NoAds() {
        Constants.admob_banner = "";
        Constants.admob_Interstitial = "";
        Constants.admob_nativead = "";
        Constants.admob_rewardad = "";
        Constants.facebook_banner = "";
        Constants.facebook_interstitial = "";
        Constants.facebook_native = "";
        Constants.facebook_rectangle = "";
        Constants.facebooknative_banner = "";
    }

    public void initAdmobFullAd(Context context) {
        if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
            return;
        }
        mInterstitialAdMob = new InterstitialAd(context);
        mInterstitialAdMob.setAdUnitId(Constants.admob_Interstitial);
        mInterstitialAdMob.setAdListener(new com.google.android.gms.ads.AdListener() {
            @Override
            public void onAdClosed() {
                //init(isnotification);
                String androidId = 22+Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID)+2;
                Log.e("androidId", androidId);
                String token = FirebaseInstanceId.getInstance().getToken();
                String authe = tinyDB.getString(Constants.Authtoken);
              /*  if (authe.isEmpty()) {
                    String androidId1 = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                    Log.e("androidId", androidId1);
                    SendToken(androidId);
                } else {
                    SendToken1(authe);

                }*/

                if (!tinyDB.getBoolean("isFirsttime")) {
                    Intent intent = new Intent(Appstart_Activity.this, Activity_Introfirst.class);
                    startActivity(intent);
                    finish();
                } else {
                    if (bundle != null) {
                        try {
                            isnotification = bundle.getBoolean("Notification");
                        } catch (Exception e) {

                        }
                    }
                    if (isnotification) {
                        Log.e("isNotification", String.valueOf(isnotification));
                        isFromnotification = false;
                        Intent intent = new Intent(Appstart_Activity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("mNotificationtype", Notificationtype);
                        intent.putExtra("mLink", link);
                        intent.putExtra("mDefaulteType", Defaulttype);
                        intent.putExtra("notificationkey", key);
                        intent.putExtra("notificationvalue", value);
//                        Log.e("keyyy", key);
                        //         Log.e("valyee", value);
                        /*tinyDB.putString("notificationkey",key);
                        tinyDB.putString("notificationvalue",value);*/
                        startActivity(intent);
                        finish();
                    } else {
                        if (success4) {
                            Intent intent = new Intent(Appstart_Activity.this, Activity_Inappbanner.class);
                            if (mInappBannerdata != null && mInappBannerdata.size() != 0) {
                                intent.putExtra("imglink", mInappBannerdata.get(0).getImage());
                                intent.putExtra("datatype", mInappBannerdata.get(0).getDefaultType());
                                intent.putExtra("datalink", mInappBannerdata.get(0).getLink());
                                /*intent.putExtra("imglink", mInappBannerdata.get(0).getImage());
                                Log.e("link1", mInappBannerdata.get(0).getImage());
                                intent.putExtra("datatype", mInappBannerdata.get(0).getBannerType());
                                Log.e("link2", mInappBannerdata.get(0).getLink_option());
                                intent.putExtra("datalink", mInappBannerdata.get(0).getBanner_link());
                                Log.e("link3", mInappBannerdata.get(0).getBanner_link());
                                intent.putExtra("default_type", mInappBannerdata.get(0).getDefault_type());
                                Log.e("link4", mInappBannerdata.get(0).getDefault_type());*/
                            }
                            startActivity(intent);
                            finish();
                        } else {
                            Intent intent = new Intent(Appstart_Activity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();


                        }
                    }
                }

             /*   if (bundle != null) {
                    try {
                        isnotification = bundle.getBoolean("Notification");
                    } catch (Exception e) {

                    }
                }
                if (isnotification) {
                    Log.e("isNotification", String.valueOf(isnotification));
                    isFromnotification = false;
                    Intent intent = new Intent(Appstart_Activity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.putExtra("mNotificationtype", Notificationtype);
                    intent.putExtra("mLink", link);
                    intent.putExtra("mDefaulteType", Defaulttype);
                    intent.putExtra("notificationkey", key);
                    intent.putExtra("notificationvalue", value);

                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(Appstart_Activity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }*/
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();

            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
            }
        });
    }

    public void loadAdmobAd() {
        if (mInterstitialAdMob != null && !mInterstitialAdMob.isLoaded()) {
            mInterstitialAdMob.loadAd(new AdRequest.Builder().build());
        }
    }


    public void callfirebasedata() {


        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            // Log.e("database", String.valueOf(database));
            DatabaseReference mRefer = database.getReference();
            mRefer.child("ApiNames").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        String name = postSnapshot.getKey().toString();
                        Log.e("apiname", name);
                        if (name.equals("AppstoreApi")) {
                            tinyDB.putString(Constants.AppstoreUrl, (String) postSnapshot.getValue());
                            Log.e("Api1", (String) postSnapshot.getValue());
                        }
                        if (name.equals("DeepSleep")) {
                            tinyDB.putString(Constants.CelluliteUrl, (String) postSnapshot.getValue());
                            Log.e("Api2", (String) postSnapshot.getValue());
                        }
                        if (name.equals("YoutubeApi")) {
                            tinyDB.putString(Constants.YoutubeApi, (String) postSnapshot.getValue());
                            Log.e("Api3", (String) postSnapshot.getValue());
                        }
                        if (name.equals("Appstore_Appid")) {
                            tinyDB.putString(Constants.Appstore_Appid, (String) postSnapshot.getValue());
                            Log.e("Api4", (String) postSnapshot.getValue());
                        }
                        if (name.equals("Banner_id")) {
                            tinyDB.putString(Constants.Storebanner_id, (String) postSnapshot.getValue());
                            Log.e("Api5", (String) postSnapshot.getValue());
                        }
                        if (name.equals("NewsleepApi")) {
                           tinyDB.putString(Constants.NewBaseUrl, (String) postSnapshot.getValue());

//                             tinyDB.putString(Constants.NewBaseUrl, "http://65.0.77.129:7079/");
                            Log.e("Api6", (String) postSnapshot.getValue());
                            Log.e("Apitest", tinyDB.getString(Constants.NewBaseUrl));
                        }
                        if (name.equals("sleepimgurl")) {
                            tinyDB.putString(Constants.Backimageurl, (String) postSnapshot.getValue());
                            Log.e("Api7", (String) postSnapshot.getValue());
                        }
                    }
                    try {
                        getcallInAppBannerApi();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {

        }

    }

    private void getcallInAppBannerApi() {
        try {
     /*   if (BASEURL.isEmpty()) {
            callfirebasedata();
        } else {*/
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiInterface apiInterface = (ApiInterface) retrofit.create(ApiInterface.class);
            //  ApiInterface   apiInterface = (ApiInterface) CelluliteAPIClient.getCelluliteClient1().create(ApiInterface.class);
            try {
                JSONObject paramObject = new JSONObject();
                paramObject.put("anum", 22);
                paramObject.put("mode", "in_app_popup");
                // paramObject.put("user_type", "Veg");

                Call<List<InappBannerModal1>> userCall = apiInterface.getinapp(paramObject.toString(), "Bearer " + token);
                userCall.enqueue(new Callback<List<InappBannerModal1>>() {
                    @Override
                    public void onResponse(Call<List<InappBannerModal1>> call, Response<List<InappBannerModal1>> response) {
                        try {


                            // Log.e("response", String.valueOf(response.body()));
                            List<InappBannerModal1> loginData = response.body();
                            // Log.e("response", String.valueOf(response.code()));
                            ;
//                    String[] heroes = new String[loginData.size()];
                            for (int i = 0; i < loginData.size(); i++) {
                                Boolean newList = loginData.get(i).getStatus();

                                if (response.code() == 200) {
                                    success4 = true;
                                } else {
                                    success4 = false;
                                }

                                loginData = loginData;
                            }
                        /*  heroes[i] = loginData.get(i).getDefaultType();
                        Log.e("getDefaultType", String.valueOf(loginData.get(i).getDefaultType()));
                        Log.e("getBannerType", String.valueOf(loginData.get(i).getBannerType()));


                        if (tinyDB.getBoolean(Constants.PREMIUN_KEY)) {
                            if (!loginData.get(i).getDefaultType().equals("Ads")) {
                                homebannerdatalist.add(loginData.get(i));
                                newList.add(loginData.get(i));
                            }
                        } else {
                            homebannerdatalist.add(loginData.get(i));

                            newList.add(loginData.get(i));
                        }
*/

                        } catch (Exception e) {

                        }


                    }

                    @Override
                    public void onFailure(Call<List<InappBannerModal1>> call, Throwable t) {
                        //  Log.e("response1", String.valueOf(t));
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {

        }
    }

    private void init(boolean isNotification) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                String androidId = 22+Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID)+2;
                Log.e("androidId", androidId);
                String token = FirebaseInstanceId.getInstance().getToken();
                String authe = tinyDB.getString(Constants.Authtoken);
              /*  if (authe.isEmpty()) {
                    String androidId1 = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                    Log.e("androidId", androidId1);
                    SendToken(androidId);
                } else {
                    SendToken1(authe);

                }*/
                if (!tinyDB.getBoolean("isFirsttime")) {
                    Intent intent = new Intent(Appstart_Activity.this, Activity_Introfirst.class);
                    startActivity(intent);
                    finish();
                } else {
                    if (bundle != null) {
                        try {
                            isnotification = bundle.getBoolean("Notification");
                        } catch (Exception e) {

                        }
                    }
                    if (isNotification) {
                        Log.e("isNotification", String.valueOf(isNotification));
                        isFromnotification = false;
                        Intent intent = new Intent(Appstart_Activity.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("mNotificationtype", Notificationtype);
                        intent.putExtra("mLink", link);
                        intent.putExtra("mDefaulteType", Defaulttype);
                        intent.putExtra("notificationkey", key);
                        intent.putExtra("notificationvalue", value);
//                        Log.e("keyyy", key);
                        //         Log.e("valyee", value);
                        /*tinyDB.putString("notificationkey",key);
                        tinyDB.putString("notificationvalue",value);*/
                        startActivity(intent);
                        finish();
                    } else {
                        if (success4) {
                            Intent intent = new Intent(Appstart_Activity.this, Activity_Inappbanner.class);
                            if (mInappBannerdata != null && mInappBannerdata.size() != 0) {

                                intent.putExtra("imglink", mInappBannerdata.get(0).getImage());
                                intent.putExtra("datatype", mInappBannerdata.get(0).getDefaultType());
                                intent.putExtra("datalink", mInappBannerdata.get(0).getLink());
                                /*intent.putExtra("imglink", mInappBannerdata.get(0).getImage());
                                Log.e("link1", mInappBannerdata.get(0).getImage());
                                intent.putExtra("datatype", mInappBannerdata.get(0).getBannerType());
                                Log.e("link2", mInappBannerdata.get(0).getLink_option());
                                intent.putExtra("datalink", mInappBannerdata.get(0).getBanner_link());
                                Log.e("link3", mInappBannerdata.get(0).getBanner_link());
                                intent.putExtra("default_type", mInappBannerdata.get(0).getDefault_type());
                                Log.e("link4", mInappBannerdata.get(0).getDefault_type());*/
                            }
                            startActivity(intent);
                            finish();
                        } else {
                            if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
                                mInterstitialAdMob.show();
                            } else {

                                Intent intent = new Intent(Appstart_Activity.this, MainActivity.class);
                                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                startActivity(intent);
                                finish();

                            }
                        }
                    }
                }
            }
        }, 3000);
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onRestart() {
        init(false);
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
      /*  if (mBroadcastReceiver != null) {
            unregisterReceiver(mBroadcastReceiver);
        }

        if (mHelper != null) mHelper.dispose();
        mHelper = null;*/
        super.onDestroy();
        finish();
    }


}