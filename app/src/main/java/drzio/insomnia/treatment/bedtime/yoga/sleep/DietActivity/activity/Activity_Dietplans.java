package drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Apiclients;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.AppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.ChooseGenderActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.MaleAppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.adapter.Adapter_NewDietslist;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.database.DietplanDbhelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.DietCateData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.DietCategories;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.Dietitems;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.DietitemsData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.Header;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.Selecteditems;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.User_Dietlist;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIClient;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.isDieatBanner;


public class Activity_Dietplans extends AppCompatActivity {
    ArrayList<Dietitems> GIFimgdata = new ArrayList<Dietitems>();
    ArrayList<DietCategories> GIFcatdata = new ArrayList<DietCategories>();
    private boolean isActive;
    boolean isclicked = false;
    TinyDB tinydb;
    FitnessApplication fitnessApplication;

    private RecyclerView mAllrecycler;
    private String tempdate2 = "zxcdsdss";
    ArrayList<User_Dietlist> userDietlists2 = new ArrayList<>();
    public List<Header> mTempDates = new ArrayList<>();
    ArrayList<User_Dietlist> mTempuserDiet = new ArrayList<>();
    private DietplanDbhelper dietplanDbhelper;
    ArrayList<DietitemsData.Datalist> templistitems = new ArrayList<>();
    ArrayList<DietCateData.Datalist> tempcatitems = new ArrayList<>();
    Activity_Dietplans activity;
    private CardView mBtncplan;
    private RelativeLayout mLaynodata;
    private ProgressDialog pd;
    private Button mBtnrefresh;
    private BackpainAPIInterface apiInterface;
    private RelativeLayout mLoadlay;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_statuscontainer);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isActive = bundle.getBoolean("isFrom");
        }
        tinydb = new TinyDB(Activity_Dietplans.this);
        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(this, languages);
        activity = this;
        dietplanDbhelper = new DietplanDbhelper(Activity_Dietplans.this);
        fitnessApplication = FitnessApplication.getInstance();
        mBtncplan = (CardView) findViewById(R.id.btncurrentplan);
        mLaynodata = (RelativeLayout) findViewById(R.id.no_video);
        mBtnrefresh = (Button) findViewById(R.id.btrefresh);
        mLoadlay = (RelativeLayout) findViewById(R.id.loadlayout);
        LottieAnimationView mLottie = (LottieAnimationView) findViewById(R.id.lotti2);
        mLottie.setVisibility(View.VISIBLE);
        mLottie.setAnimation("loadanimdial.json");
        mLottie.playAnimation();
        mLottie.loop(true);
        mLoadlay.setVisibility(View.GONE);
        CardView mBtncustom = (CardView) findViewById(R.id.btncustumplan);

        LinearLayout mStorebtn = (LinearLayout) findViewById(R.id.laystore);
        ImageView mBtnstore = (ImageView) findViewById(R.id.ivbtnstore);
        mBtnstore.setBackgroundResource(R.drawable.adsstoreanim);
        AnimationDrawable anim2 = (AnimationDrawable) mBtnstore.getBackground();
        anim2.start();
        ImageView mBtnback = (ImageView) findViewById(R.id.btnback);
        mBtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mStorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cgender=tinydb.getBoolean(Constants.Genderchoose);
                if (cgender){
                    if (tinydb.getString(Constants.GENDER_KEY).equals(getResources().getString(R.string.female))) {
                        Intent intent = new Intent(Activity_Dietplans.this, AppstoreActivity.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(Activity_Dietplans.this, MaleAppstoreActivity.class);
                        startActivity(intent);
                    }
                }else {
                    Intent intent = new Intent(Activity_Dietplans.this, ChooseGenderActivity.class);
                    startActivity(intent);
                }
            }
        });
        ShimmerFrameLayout mLoadlay1 = findViewById(R.id.shimmer_container);
        LinearLayout mGbannerlay = findViewById(R.id.adframe);
        FrameLayout mAdframe4 = (FrameLayout) findViewById(R.id.adframe4);

        if (!tinydb.getBoolean(Constants.PREMIUN_KEY)) {
        if (isDieatBanner){
            mGbannerlay.setVisibility(View.GONE);
            showBanner(Activity_Dietplans.this,mAdframe4,mLoadlay1);
        }else {
            mAdframe4.setVisibility(View.GONE);
            Smallnative(this, mGbannerlay);
        }
    }else {
        mLoadlay.setVisibility(View.GONE);
    }
        mBtncustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Dietplans.this, Activity_Alldietslist.class);
                startActivity(intent);
            }
        });
        Constants.mPoslist.clear();
        mBtnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadlay.setVisibility(View.VISIBLE);
                callCategoryApi();
            }
        });
        mAllrecycler = (RecyclerView) findViewById(R.id.alldietrecycler);
        mAllrecycler.setLayoutManager(new LinearLayoutManager(Activity_Dietplans.this, LinearLayoutManager.VERTICAL, false));
    }


    public void showBanner(Context context, final FrameLayout adMobView,ShimmerFrameLayout mloadlaypout) {
        try {
            final AdView mAdView = new AdView(context);
            mAdView.setAdSize(AdSize.BANNER);
            mAdView.setAdUnitId(Constants.admob_banner);
            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    if (adMobView != null) {
                        adMobView.removeAllViews();
                    }
                    adMobView.addView(mAdView);
                    mloadlaypout.setVisibility(View.GONE);
                    adMobView.setVisibility(View.VISIBLE);
                    isDieatBanner = false;
                }

                @Override
                public void onAdFailedToLoad(int i) {
                    mloadlaypout.setVisibility(View.GONE);
                    super.onAdFailedToLoad(i);
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


    public void Updatedata() {
        Apiclients.mTemplist.clear();
        if (tinydb.getBoolean(Constants.ADDEDFIXDIET)) {
            String mData = tinydb.getString(Constants.FIXDIET1_KEY);
            List list = (ArrayList<User_Dietlist>) new Gson().fromJson(mData, new TypeToken<ArrayList<User_Dietlist>>() {
            }.getType());
            ArrayList<User_Dietlist> datalist = new ArrayList<>(list);
            for (int i = 0; i < datalist.size(); i++) {
                User_Dietlist data = datalist.get(i);
                Selecteditems selecteditems = new Selecteditems();
                selecteditems.setId(data.getId());
                selecteditems.setName(data.getName());
                selecteditems.setDescription(data.getDescription());
                selecteditems.setImage(data.getImage());
                selecteditems.setIs_active(data.getIs_active());
                selecteditems.setUser_type(data.getUser_type());
                selecteditems.setCategory_id(data.getCategory_id());
                Apiclients.mTemplist.add(selecteditems);
            }

            Adapter_NewDietslist adapterNewDietslist = new Adapter_NewDietslist(Activity_Dietplans.this, datalist, activity);
            mAllrecycler.setAdapter(adapterNewDietslist);
        } else {
            mLoadlay.setVisibility(View.VISIBLE);
            callCategoryApi();
        }

    }


    @SuppressLint("WrongConstant")
    public void callCategoryApi() {
        try {
            this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient().create(BackpainAPIInterface.class);
            this.apiInterface.getCategory().enqueue(new Callback<DietCateData>() {
                @Override
                public void onResponse(@NotNull Call<DietCateData> call, @NotNull Response<DietCateData> response) {
                    try {
                        DietCateData dietCateData = (DietCateData) response.body();
                        ArrayList<DietCateData.Datalist> datamodalsArrayList = dietCateData.dataist;
                        callDietApi();
                        tempcatitems = datamodalsArrayList;
                    }catch (Exception e){

                    }
                }

                @Override
                public void onFailure(@NotNull Call<DietCateData> call, @NotNull Throwable t) {
                    mLoadlay.setVisibility(View.GONE);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mLoadlay.setVisibility(View.GONE);
        }
    }


    @SuppressLint("WrongConstant")
    public void callDietApi() {
        try {
            this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient().create(BackpainAPIInterface.class);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("diet_id", "")
                    .addFormDataPart("category_id", "")
                    .addFormDataPart("user_type", "1")
                    .build();
            this.apiInterface.getDietplan(requestBody).enqueue(new Callback<DietitemsData>() {
                @Override
                public void onResponse(@NotNull Call<DietitemsData> call, @NotNull Response<DietitemsData> response) {
                    try {
                        mLoadlay.setVisibility(View.GONE);
                        DietitemsData dietCateData = (DietitemsData) response.body();
                        ArrayList<DietitemsData.Datalist> itemdata = new ArrayList<>();
                        ArrayList<DietitemsData.Datalist> datamodalsArrayList = dietCateData.dataist;
                        templistitems = datamodalsArrayList;
                        new Dataset().execute();
                    }catch (Exception e){

                    }
                }

                @Override
                public void onFailure(@NotNull Call<DietitemsData> call, @NotNull Throwable t) {
                    mLoadlay.setVisibility(View.GONE);

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mLoadlay.setVisibility(View.GONE);
        }
    }

    class Dataset extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            ArrayList<String> mIds = new ArrayList<>();
            for (int i = 0; i < tempcatitems.size(); i++) {
                mIds.add(tempcatitems.get(i).getCategory_id());
            }
            for (int i = 0; i < mIds.size(); i++) {
                tempdate2 = mIds.get(i);
                userDietlists2.clear();
                for (int i2 = 0; i2 < templistitems.size(); i2++) {
                    if (tempdate2.equals(templistitems.get(i2).getCategory_id())) {
                        User_Dietlist data = new User_Dietlist();
                        data.setDate(minerals(templistitems.get(i2).getCategory_id()));
                        data.setId(templistitems.get(i2).getId());
                        data.setName(templistitems.get(i2).getName());
                        data.setImage(templistitems.get(i2).getImage());
                        data.setCategory_id(templistitems.get(i2).getCategory_id());
                        data.setDescription(templistitems.get(i2).getDescription());
                        userDietlists2.add(data);
                    }
                }
                try {
                    int temp = userDietlists2.size() - 1;
                    mTempuserDiet.add(userDietlists2.get(getRandomNumberInRange(0, temp)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            for (int i = 0; i < mTempuserDiet.size(); i++) {
                User_Dietlist data = mTempuserDiet.get(i);
                Selecteditems selecteditems = new Selecteditems();
                selecteditems.setId(data.getId());
                selecteditems.setName(data.getName());
                selecteditems.setDescription(data.getDescription());
                selecteditems.setImage(data.getImage());
                selecteditems.setIs_active(data.getIs_active());
                selecteditems.setUser_type(data.getUser_type());
                selecteditems.setCategory_id(data.getCategory_id());
                Apiclients.mTemplist.add(selecteditems);
            }
            tinydb.remove(Constants.FIXDIET1_KEY);
            tinydb.remove(Constants.FIXDIET2_KEY);
            String json = new Gson().toJson((Object) mTempuserDiet);
            tinydb.putString(Constants.FIXDIET1_KEY, json);
            tinydb.putBoolean(Constants.ADDEDFIXDIET, true);
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            mLaynodata.setVisibility(View.GONE);
            if (pd != null && pd.isShowing()) {
                pd.dismiss();
            }
            Adapter_NewDietslist adapterNewDietslist = new Adapter_NewDietslist(Activity_Dietplans.this, mTempuserDiet, activity);
            mAllrecycler.setAdapter(adapterNewDietslist);
        }
    }

    public String minerals(String catids) {
        String catname = null;
        for (int i = 0; i < DietCategories.getGifCatlistdata().size(); i++) {
            if (DietCategories.getGifCatlistdata().get(i).getCategory_id().equals(catids)) {
                catname = DietCategories.getGifCatlistdata().get(i).getCatname();
            }
        }
        return catname;
    }


    public static void Smallnative(final Context context, final LinearLayout frameLayout) {
        AdLoader.Builder builder = new AdLoader.Builder(context, Constants.admob_nativead);
        builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
            public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                frameLayout.setVisibility(View.VISIBLE);
                NativeContentAdView nativeContentAdView = (NativeContentAdView) ((Activity) context).getLayoutInflater().inflate(R.layout.ad_native_banner, null);
                populateSmallContentAdView(nativeContentAd, nativeContentAdView);
                frameLayout.removeAllViews();
                frameLayout.addView(nativeContentAdView);
                isDieatBanner = true;
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(int i) {
                Log.e("error", "Failed to load native ad:: " + i);
                FitnessApplication.AdfailToast("MainActivity Small Native", String.valueOf(i));
            }
        }).build().loadAd(new AdRequest.Builder().build());
    }

    @SuppressLint("WrongConstant")
    public static void populateSmallContentAdView(NativeContentAd nativeContentAd, NativeContentAdView nativeContentAdView) {
        nativeContentAdView.setHeadlineView(nativeContentAdView.findViewById(R.id.ad_title_textview));
        nativeContentAdView.setBodyView(nativeContentAdView.findViewById(R.id.ad_describe_textview));
        nativeContentAdView.setCallToActionView(nativeContentAdView.findViewById(R.id.ad_action_button));
        nativeContentAdView.setLogoView(nativeContentAdView.findViewById(R.id.ad_icon_imageview));
        ((TextView) nativeContentAdView.getHeadlineView()).setText(nativeContentAd.getHeadline());
        ((TextView) nativeContentAdView.getBodyView()).setText(nativeContentAd.getBody());
        ((Button) nativeContentAdView.getCallToActionView()).setText(nativeContentAd.getCallToAction());
        com.google.android.gms.ads.formats.NativeAd.Image logo = nativeContentAd.getLogo();
        if (logo == null) {
            nativeContentAdView.getLogoView().setVisibility(4);
        } else {
            ((ImageView) nativeContentAdView.getLogoView()).setImageDrawable(logo.getDrawable());
            nativeContentAdView.getLogoView().setVisibility(0);
        }
        nativeContentAdView.setNativeAd(nativeContentAd);
    }

    private int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }


    @Override
    protected void onStart() {
        isclicked = false;
        super.onStart();
    }

    @Override
    protected void onResume() {
        try {
            Updatedata();
            mBtncplan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (Apiclients.mTemplist.size() != 0) {
                        for (int i = 0; i < Apiclients.mTemplist.size(); i++) {
                            Selecteditems mData = Apiclients.mTemplist.get(i);
                            Date c = Calendar.getInstance().getTime();
                             Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
                            String formattedDate = df.format(c);
                            tinydb.putString(Constants.DIETPLANDATE_KEY, formattedDate);
                            dietplanDbhelper.insertExcALLDayData(formattedDate, mData.getId(), mData.getName(),
                                    mData.getDescription(), mData.getImage(), mData.getIs_active(), mData.getUser_type()
                                    , mData.getCategory_id());
                        }
                        Apiclients.mTemplist.clear();
                        Intent intent = new Intent(Activity_Dietplans.this, Activity_Userdietlist.class);
                        intent.putExtra("isFrom2", true);
                        startActivity(intent);
                        finish();
                    }
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}