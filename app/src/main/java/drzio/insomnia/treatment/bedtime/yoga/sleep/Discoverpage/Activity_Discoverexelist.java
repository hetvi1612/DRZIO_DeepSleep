package drzio.insomnia.treatment.bedtime.yoga.sleep.Discoverpage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Calenderview;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Purchase;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.DatabaseOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Daymodals;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Activity_Discoverexelist extends AppCompatActivity {
    int excercisenames = 0;
    int exethumbimgs = 0;
    int excercisetime = 0;
    int calorie = 0;
    int calorie51 = 0;
    int calorie76 = 0;
    int calorie90 = 0;
    int viddata = 0;
    int youtubevid = 0;
    int exedesc = 0;
    List<Daymodals> excerciseDataList = new ArrayList<>();
    //    int mPosition;
    private String mTime;
    private String dayname;
    public float Totaltime;
    public float Totalkcal;
    private TinyDB tinydb;
    private int REST_TAG = 5;
    private int b;
    List<Object> Templist = new ArrayList<>();
    private int mProgress;
    private DatabaseOperations databaseOperations;
    private boolean isclicked = false;
    private int mLevel;
    private RewardedAd rewardedAd;
    private boolean isLoaded;
    private boolean isFailtoload;
    private boolean isLastdone;
    private RelativeLayout mBtnstart;
    private RelativeLayout mCardrestart;
    private Dialog dialog;
    private InterstitialAd mInterstitialAdMob;
    private int Gad;
    private Dialog dialogload;
    private Handler handler1;
    private Runnable runnable1;
    private Handler handler2;
    private Runnable runnable2;
    private View shine;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_excerciselist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        tinydb = new TinyDB(Activity_Discoverexelist.this);
        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(this, languages);
        ImageView mImgheader = (ImageView) findViewById(R.id.header);

        shine=findViewById(R.id.shine);
        shineAnimation();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            dayname = bundle.getString("dname");
            mProgress = bundle.getInt("dayprogrss");
            mLevel = bundle.getInt("level");
            try {
                isLastdone = bundle.getBoolean("lastcomplete");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        initAdmobFullAd(this);
        loadAdmobAd();
        LinearLayout mAdframe = (LinearLayout) findViewById(R.id.adframe);
        Smallnative(this, mAdframe);
        setSupportActionBar(toolbar);

        if (mLevel == 4) {
            mImgheader.setImageResource(R.drawable.img_premiumnbanner);
            excercisenames = R.array.YogaforInsomniaexe_exename;
            exethumbimgs = R.array.YogaforInsomniaexe_thumbimg;
            excercisetime = R.array.YogaforInsomniaexetotaltime;
            calorie = R.array.YogaforInsomniaexecalorie;
            calorie51 = R.array.YogaforInsomniaexe51_75calorie;
            calorie76 = R.array.YogaforInsomniaexe76_90calorie;
            calorie90 = R.array.YogaforInsomniaexe_90calorie;
            viddata = R.array.YogaforInsomniaexe_vids;
            youtubevid = R.array.YogaforInsomniaexe_youtubevid;
            exedesc = R.array.YogaforInsomniaexedesc;

        } else if (mLevel == 5) {
            mImgheader.setImageResource(R.drawable.img_premiumnbanner);
            excercisenames = R.array.DeepSleepYogaexe_exename;
            exethumbimgs = R.array.DeepSleepYogaexe_thumbimg;
            excercisetime = R.array.DeepSleepYogaexetotaltime;
            calorie = R.array.DeepSleepYogaexetotaltime;
            calorie51 = R.array.DeepSleepYogaexe51_75calorie;
            calorie76 = R.array.DeepSleepYogaexe76_90calorie;
            calorie90 = R.array.DeepSleepYogaexe_90calorie;
            viddata = R.array.DeepSleepYogaexe_vids;
            youtubevid = R.array.DeepSleepYogaexe_youtubevid;
            exedesc = R.array.DeepSleepYogaexedesc;


        } else if (mLevel == 6) {
            mImgheader.setImageResource(R.drawable.img_premiumnbanner);
            excercisenames = R.array.Yognindra_Effectivesleepexename;
            exethumbimgs = R.array.Yognindra_Effectivesleep_thumbimg;
            excercisetime = R.array.Yognindra_Effectivesleeptotaltime;
            calorie = R.array.Yognindra_Effectivesleepcalorie;
            calorie51 = R.array.Yognindra_Effectivesleep51_75calorie;
            calorie76 = R.array.Yognindra_Effectivesleep76_90calorie;
            calorie90 = R.array.Yognindra_Effectivesleep_90calorie;
            viddata = R.array.Yognindra_Effectivesleep_vids;
            youtubevid = R.array.Yognindra_Effectivesleep_youtubevid;
            exedesc = R.array.Yognindra_Effectivesleepexedesc;

        } else if (mLevel == 7) {
            mImgheader.setImageResource(R.drawable.img_premiumnbanner);
            excercisenames = R.array.Bedtimeyogaforsoundsleepexename;
            exethumbimgs = R.array.Bedtimeyogaforsoundsleepexe_thumbimg;
            excercisetime = R.array.Bedtimeyogaforsoundsleepexetotaltime;
            calorie = R.array.Bedtimeyogaforsoundsleepexecalorie;
            calorie51 = R.array.Bedtimeyogaforsoundsleepexe51_75calorie;
            calorie76 = R.array.Bedtimeyogaforsoundsleepexe51_75calorie;
            calorie90 = R.array.Bedtimeyogaforsoundsleepexe_90calorie;
            viddata = R.array.Bedtimeyogaforsoundsleepexe_vids;
            youtubevid = R.array.Bedtimeyogaforsoundsleepexe_youtubevid;
            exedesc = R.array.Bedtimeyogaforsoundsleepexedesc;

        } else if (mLevel == 8) {
            mImgheader.setImageResource(R.drawable.img_premiumnbanner);
            excercisenames = R.array.SleepApneaexename;
            exethumbimgs = R.array.SleepApneaexe_thumbimg;
            excercisetime = R.array.SleepApneaeexetotaltime;
            calorie = R.array.SleepApneaexecalorie;
            calorie51 = R.array.SleepApneaexe51_75calorie;
            calorie76 = R.array.SleepApneaexe76_90calorie;
            calorie90 = R.array.SleepApneaexe_90calorie;
            viddata = R.array.SleepApneaexe_vids;
            youtubevid = R.array.SleepApneaexe_youtubevid;
            exedesc = R.array.SleepApneaexedesc;

        } else if (mLevel == 9) {
            mImgheader.setImageResource(R.drawable.img_premiumnbanner);
            excercisenames = R.array.BreathingExerciseexename;
            exethumbimgs = R.array.BreathingExerciseexe_thumbimg;
            excercisetime = R.array.BreathingExerciseexetotaltime;
            calorie = R.array.BreathingExerciseexecalorie;
            calorie51 = R.array.BreathingExerciseexe51_75calorie;
            calorie76 = R.array.BreathingExerciseexe76_90calorie;
            calorie90 = R.array.BreathingExerciseexe_90calorie;
            viddata = R.array.BreathingExerciseexe_vids;
            youtubevid = R.array.BreathingExerciseexe_youtubevid;
            exedesc = R.array.BreathingExerciseexedesc;
        } else {
            mImgheader.setImageResource(R.drawable.img_premiumnbanner);
            excercisenames = R.array.Yognindra_Effectivesleepexename;
            exethumbimgs = R.array.Yognindra_Effectivesleep_thumbimg;
            excercisetime = R.array.Yognindra_Effectivesleeptotaltime;
            calorie = R.array.Yognindra_Effectivesleep_vids;
            calorie51 = R.array.Yognindra_Effectivesleep51_75calorie;
            calorie76 = R.array.Yognindra_Effectivesleep76_90calorie;
            calorie90 = R.array.Yognindra_Effectivesleep_90calorie;
            viddata = R.array.Yognindra_Effectivesleep_vids;
            youtubevid = R.array.Yognindra_Effectivesleep_youtubevid;
            exedesc = R.array.Yognindra_Effectivesleepexedesc;
        }
      /*  if (mLevel == 5) {
            mImgheader.setImageResource(R.drawable.img_premiumnbanner);
            excercisenames = R.array.stomachexename;
            exethumbimgs = R.array.stomachexe_thumbimg;
            excercisetime = R.array.thighexetotaltime;
            calorie = R.array.stomachexecalorie;
            calorie51 = R.array.stomachexe51_75calorie;
            calorie76 = R.array.stomachexe76_90calorie;
            calorie90 = R.array.stomachexe_90calorie;
            viddata = R.array.stomachexe_vids;
            youtubevid = R.array.stomachexe_youtubevid;
            exedesc = R.array.stomachexedesc;

        } else if (mLevel == 6) {
            mImgheader.setImageResource(R.drawable.img_premiumnbanner);
            excercisenames = R.array.weistexename;
            exethumbimgs = R.array.weistexe_thumbimg;
            excercisetime = R.array.thighexetotaltime;
            calorie = R.array.weistexecalorie;
            calorie51 = R.array.weistexe51_75calorie;
            calorie76 = R.array.weistexe76_90calorie;
            calorie90 = R.array.weistexe_90calorie;
            viddata = R.array.weistexe_vids;
            youtubevid = R.array.weistexe_youtubevid;
            exedesc = R.array.weistexeexedesc;

        } else if (mLevel == 7) {
            mImgheader.setImageResource(R.drawable.img_premiumnbanner);
            excercisenames = R.array.buttexename;
            exethumbimgs = R.array.buttexe_thumbimg;
            excercisetime = R.array.thighexetotaltime;
            calorie = R.array.buttexecalorie;
            calorie51 = R.array.buttexe51_75calorie;
            calorie76 = R.array.buttexe76_90calorie;
            calorie90 = R.array.buttexe_90calorie;
            viddata = R.array.buttexe_vids;
            youtubevid = R.array.buttexe_youtubevid;
            exedesc = R.array.buttexedesc;
        } else if (mLevel == 8) {
            mImgheader.setImageResource(R.drawable.img_premiumnbanner);
            excercisenames = R.array.armexename;
            exethumbimgs = R.array.armexe_thumbimg;
            excercisetime = R.array.thighexetotaltime;
            calorie = R.array.armexecalorie;
            calorie51 = R.array.armexe51_75calorie;
            calorie76 = R.array.armexe76_90calorie;
            calorie90 = R.array.armexe_90calorie;
            viddata = R.array.armexe_vids;
            youtubevid = R.array.armexe_youtubevid;
            exedesc = R.array.armexedesc;
        } else if (mLevel == 9) {
            mImgheader.setImageResource(R.drawable.img_premiumnbanner);
            excercisenames = R.array.breastexename;
            exethumbimgs = R.array.breastexe_thumbimg;
            excercisetime = R.array.thighexetotaltime;
            calorie = R.array.breastexecalorie;
            calorie51 = R.array.breastexe51_75calorie;
            calorie76 = R.array.breastexe76_90calorie;
            calorie90 = R.array.breastexe_90calorie;
            viddata = R.array.breastexe_vids;
            youtubevid = R.array.breastexe_youtubevid;
            exedesc = R.array.breastexedesc;
        } else {
            mImgheader.setImageResource(R.drawable.img_premiumnbanner);
            excercisenames = R.array.thighexename;
            exethumbimgs = R.array.thighexe_thumbimg;
            excercisetime = R.array.thighexetotaltime;
            calorie = R.array.thighexecalorie;
            calorie51 = R.array.thighexe51_75calorie;
            calorie76 = R.array.thighexe76_90calorie;
            calorie90 = R.array.thighexe_90calorie;
            viddata = R.array.thighexe_vids;
            youtubevid = R.array.thighexe_youtubevid;
            exedesc = R.array.thighexeexedesc;
        }*/
        collapsingToolbar.setTitle(dayname);
        TextView mTvdname = (TextView) findViewById(R.id.txtpname);
        mTvdname.setText(dayname);
        collapsingToolbar.setCollapsedTitleTextColor(Color.TRANSPARENT);
        collapsingToolbar.setExpandedTitleColor(Color.TRANSPARENT);
        TextView mTxtbtstart = (TextView) findViewById(R.id.txtbtstart);
        TextView mTxttotaltime = (TextView) findViewById(R.id.txtexcertime);
        TextView mTxtcount = (TextView) findViewById(R.id.txtexcecount);
        TextView mTotalkcal = (TextView) findViewById(R.id.txtexcerkcal);
        mBtnstart = (RelativeLayout) findViewById(R.id.btnstart);
        TextView mTxtrestart = (TextView) findViewById(R.id.txtrestart);
        LinearLayout mDatalay = (LinearLayout) findViewById(R.id.btnhistory);
        mDatalay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Discoverexelist.this, Activity_Calenderview.class);
                startActivity(intent);
            }
        });


        mCardrestart = (RelativeLayout) findViewById(R.id.btnrestart);
        mBtnstart.setClickable(false);

        if (!tinydb.getBoolean(Constants.PREMIUN_KEY)) {
            boolean temp = false;
            if (mLevel == 5) {
                temp = Constants.isDRewardgiven4;
            } else if (mLevel == 6) {
                temp = Constants.isDRewardgiven6;
            } else if (mLevel == 7) {
                temp = Constants.isDRewardgiven7;
            } else if (mLevel == 9) {
                temp = Constants.isDRewardgiven9;
            } else {
                temp = true;
            }
            if (!temp) {
                DialogAdsloading();
                loadRewardedAd();
                DialogPremium();
                if (mLevel == 4) {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
                if (mLevel == 8) {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            }
        }

        databaseOperations = new DatabaseOperations(Activity_Discoverexelist.this);
        ImageView mBtback = (ImageView) findViewById(R.id.btnback);
        mBtback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        if (excerciseDataList != null && excerciseDataList.size() != 0) {
            excerciseDataList.clear();
        }
        String[] temparray = getResources().getStringArray(excercisenames);
        String[] temparray2 = getResources().getStringArray(excercisetime);
        String[] temparray3 = (getResources().getStringArray(calorie));
        if (tinydb.getBoolean(Constants.ISKG_KEY)) {
            int mWeight = tinydb.getInt(Constants.WEIGHT_KEY);
            if (mWeight < 51) {
                temparray3 = (getResources().getStringArray(calorie));
            } else if (mWeight > 51 && mWeight < 76) {
                temparray3 = (getResources().getStringArray(calorie51));
            } else if (mWeight > 75 && mWeight < 90) {
                temparray3 = (getResources().getStringArray(calorie76));
            } else if (mWeight > 90) {
                temparray3 = (getResources().getStringArray(calorie90));
            }
        } else {
            int mWeight = tinydb.getInt(Constants.WEIGHT_KEY);
            if (mWeight < 112) {
                temparray3 = (getResources().getStringArray(calorie));
            } else if (mWeight > 112 && mWeight < 167) {
                temparray3 = (getResources().getStringArray(calorie51));
            } else if (mWeight > 167 && mWeight < 198) {
                temparray3 = (getResources().getStringArray(calorie76));
            } else if (mWeight > 198) {
                temparray3 = (getResources().getStringArray(calorie90));
            }
        }
        String[] temparray4 = (getResources().getStringArray(youtubevid));
        String[] temparray5 = (getResources().getStringArray(exedesc));
        mTxtcount.setText(String.valueOf(temparray.length));
        Resources res = getResources();
        Resources res2 = getResources();
        final TypedArray videoarray = res.obtainTypedArray(viddata);
        final TypedArray thumbsarray = res2.obtainTypedArray(exethumbimgs);


        for (int k = 0; k < temparray2.length; k++) {
            float number = Float.parseFloat(temparray2[k]);
            float value = number / 60;
            Totaltime = Totaltime + value;
        }
        try {
            for (int k2 = 0; k2 < temparray2.length; k2++) {
                float number = Float.parseFloat(temparray2[k2]);
                float kcalvalue = Float.parseFloat(temparray3[k2]);
                float tkcal = kcalvalue / 60;
                float tempmulti = number * tkcal;
                Totalkcal = Totalkcal + tempmulti;
            }
        } catch (Exception e) {

        }

        mTotalkcal.setText(decimalpoint(Totalkcal));

        mTxttotaltime.setText(decimalpoint(Totaltime));
        for (int i = 0; i < temparray.length; i++) {
            Daymodals excerciseData = new Daymodals();
            excerciseData.setExecercisename(temparray[i]);
            if (temparray2[i].length() == 1) {
                mTime = "0" + temparray2[i] + ":00";
            } else {
                mTime = "00:" + temparray2[i];
            }
            try {
                excerciseData.setExecerciseduration(mTime);
                float number = Float.parseFloat(temparray3[i]);
                float values = number / 60;
                excerciseData.setPlanname(getString(R.string.aboveage));
                excerciseData.setCalorie(String.valueOf(values));
                excerciseData.setExecerciseimaga(Videourl(videoarray, i));
                excerciseData.setExethumbs(Thumburl(thumbsarray, i));
                excerciseData.setExedescription(temparray5[i]);
                excerciseData.setYouvideo(temparray4[i]);
                excerciseDataList.add(excerciseData);
                Templist.add(excerciseData);
            }catch (Exception e){

            }
        }


        List<Object> Templist2 = Templist;
        RecyclerView mExcerciseRecycler = (RecyclerView) findViewById(R.id.excerciselist);
        mExcerciseRecycler.setLayoutManager(new LinearLayoutManager(Activity_Discoverexelist.this, LinearLayoutManager.VERTICAL, false));
        AdapterDiscoverexelist excerciselistAdapter = new AdapterDiscoverexelist(Activity_Discoverexelist.this, excerciseDataList, Activity_Discoverexelist.this, mLevel);
        mExcerciseRecycler.setAdapter(excerciselistAdapter);
        if (mProgress == 0) {
            mTxtbtstart.setText("START");
            mCardrestart.setVisibility(View.GONE);
        } else {
            if (mProgress >= 100) {
                mBtnstart.setVisibility(View.GONE);
                mCardrestart.setVisibility(View.VISIBLE);
            } else {
                mTxtbtstart.setText("Continue From " + (int) mProgress + "%");
                mTxtbtstart.setTextSize(15);
                mCardrestart.setVisibility(View.VISIBLE);
            }

        }
        mCardrestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isclicked) {
                    isclicked = true;
                    Gad = 1;
                    if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
                        mInterstitialAdMob.show();
                    } else {
                        Restartbtnclick();
                    }

                }
            }
        });
        mBtnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isclicked) {
                    isclicked = true;
                    Gad = 0;
                    if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
                        mInterstitialAdMob.show();
                    } else {
                        StartbtnClick();
                    }
                }
            }
        });


        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if ((collapsingToolbar.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(collapsingToolbar))) {
                    //  mTvtooltitle.setTextColor(Color.WHITE);
                    mBtback.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
                    //   mIvtips.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                    //  mIvshop.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

                    // mBtnstore.setBackgroundResource(R.drawable.storeicons);
                    mTvdname.setTextColor(Color.BLACK);
                    mTvdname.setVisibility(View.VISIBLE);
                  /*  AnimationDrawable anim = (AnimationDrawable) mBtnstore.getBackground();
                    anim.start();*/
                } else {
                    //    mTvtooltitle.setTextColor(Color.WHITE);
                    //  mTvdname.setVisibility(View.GONE);
                    mBtback.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                    mTvdname.setTextColor(Color.WHITE);
                    // mBtnstore.setBackgroundResource(R.drawable.storeicons);
                  /*  AnimationDrawable anim = (AnimationDrawable) mBtnstore.getBackground();
                    anim.start();*/


                    //   mIvtips.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                    //  mIvshop.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                }
            }
        });


    }



    private void shineAnimation() {
        Animation anim = AnimationUtils.loadAnimation(Activity_Discoverexelist.this, R.anim.left_right);
        shine.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                shine.startAnimation(anim);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }



    public void DialogAdsloading() {
        dialogload = new Dialog(Activity_Discoverexelist.this);
        dialogload.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogload.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogload.setCanceledOnTouchOutside(false);
        dialogload.setContentView(R.layout.dialog_adloading);
        LottieAnimationView mLottie = (LottieAnimationView) dialogload.findViewById(R.id.lotti);
        mLottie.setAnimation("adloadanim.json");
        mLottie.playAnimation();
        mLottie.loop(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialogload.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialogload.getWindow().setAttributes(lp);
        dialogload.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (handler1 != null) {
                    handler1.removeCallbacks(runnable1);
                }
                if (handler2 != null) {
                    handler2.removeCallbacks(runnable2);
                }
            }
        });
    }


    public float round(float d, int decimalPlace) {
        return BigDecimal.valueOf(d).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public void initAdmobFullAd(Context context) {
        if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
            return;
        }
        mInterstitialAdMob = new InterstitialAd(context);
        mInterstitialAdMob.setAdUnitId(Constants.admob_Interstitial);
        mInterstitialAdMob.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                if (Gad == 0) {
                    StartbtnClick();
                } else if (Gad == 1) {
                    Restartbtnclick();
                }
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

    public void initAdmobFullAd1(Context context) {
        if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
            return;
        }
        mInterstitialAdMob = new InterstitialAd(context);
        mInterstitialAdMob.setAdUnitId(Constants.admob_Interstitial);
        mInterstitialAdMob.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                if (mLevel == 5) {
                    Constants.isDRewardgiven4 = true;
                }
                if (mLevel == 6) {
                    Constants.isDRewardgiven6 = true;
                }
                if (mLevel == 7) {
                    Constants.isDRewardgiven7 = true;
                }
               /* if (mLevel == 8) {
                    Constants.isDRewardgiven8 = true;
                }*/
                if (mLevel == 9) {
                    Constants.isDRewardgiven9 = true;
                }
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
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
                Toast.makeText(Activity_Discoverexelist.this, "Pleasee Wait There is No Ad To Show", Toast.LENGTH_SHORT).show();

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


    private void loadRewardedAd() {
        if (rewardedAd == null || !rewardedAd.isLoaded()) {
            rewardedAd = new RewardedAd(Activity_Discoverexelist.this, Constants.admob_rewardad);
            rewardedAd.loadAd(
                    new AdRequest.Builder().build(),
                    new RewardedAdLoadCallback() {
                        @Override
                        public void onRewardedAdLoaded() {
                            isLoaded = true;
                        }

                        @Override
                        public void onRewardedAdFailedToLoad(LoadAdError loadAdError) {
                            super.onRewardedAdFailedToLoad(loadAdError);
                            isFailtoload = true;
                            loadAdmobAd();
                        }
                    });
        }
    }

    private void showRewardedVideo() {
        if (rewardedAd.isLoaded()) {
            RewardedAdCallback adCallback =
                    new RewardedAdCallback() {
                        @Override
                        public void onRewardedAdOpened() {
                        }

                        @Override
                        public void onRewardedAdClosed() {
                            loadRewardedAd();
                        }

                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            if (mLevel == 5) {
                                Constants.isDRewardgiven4 = true;
                            }
                            if (mLevel == 6) {
                                Constants.isDRewardgiven6 = true;
                            }
                            if (mLevel == 7) {
                                Constants.isDRewardgiven7 = true;
                            }
                            if (mLevel == 9) {
                                Constants.isDRewardgiven9 = true;
                            }
                            if (dialog != null && dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        }

                        @Override
                        public void onRewardedAdFailedToShow(int errorCode) {


                            initAdmobFullAd1(Activity_Discoverexelist.this);
                        }
                    };
            rewardedAd.show(Activity_Discoverexelist.this, adCallback);
        }
    }


    public void StartbtnClick() {
        Bundle bundle1 = new Bundle();
        bundle1.putSerializable("listexecercise", (Serializable) excerciseDataList);
        Intent intent = new Intent(Activity_Discoverexelist.this, Activity_DiscoverExercisestart.class);
        intent.putExtra("day", dayname);
        intent.putExtra("level", mLevel);
        intent.putExtras(bundle1);
        startActivity(intent);
        finish();
    }

    public void Restartbtnclick() {
        databaseOperations.insertExcCounter(dayname, 0, mLevel);
        databaseOperations.insertDayComplete(dayname, 0, mLevel);
        databaseOperations.insertExcDayData(dayname, 0, mLevel);
        if (mLevel == 1) {
            tinydb.putString(Constants.DAYCLICK_KEY, dayname);
        } else if (mLevel == 2) {
            tinydb.putString(Constants.ALV2DAYCLICK_KEY, dayname);
        } else if (mLevel == 3) {
            tinydb.putString(Constants.ALV3DAYCLICK_KEY, dayname);
        }
        Bundle bundle1 = new Bundle();
        bundle1.putSerializable("listexecercise", (Serializable) excerciseDataList);
        Intent intent = new Intent(Activity_Discoverexelist.this, Activity_Exercisestart.class);
        intent.putExtra("day", dayname);
        intent.putExtra("level", mLevel);
        intent.putExtras(bundle1);
        startActivity(intent);
        finish();
    }


    public int[] LevelArrays(TypedArray videoarray) {
        int[] resIds = new int[videoarray.length()];
        for (int i2 = 0; i2 < videoarray.length(); i2++) {
            resIds[i2] = videoarray.getResourceId(i2, -1);
        }
        return resIds;
    }

    public String Videourl(TypedArray videoarray, int pos) {
        int[] resIds = new int[videoarray.length()];
        for (int i2 = 0; i2 < videoarray.length(); i2++) {
            resIds[i2] = videoarray.getResourceId(i2, -1);
        }

        Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
                + resIds[pos]);
        return String.valueOf(video);
    }

    public String Thumburl(TypedArray thumbarray, int pos) {
        int[] resIds = new int[thumbarray.length()];
        for (int i2 = 0; i2 < thumbarray.length(); i2++) {
            resIds[i2] = thumbarray.getResourceId(i2, -1);
        }

        Uri thumb = Uri.parse("android.resource://" + getPackageName() + "/"
                + resIds[pos]);
        return String.valueOf(thumb);
    }


    private String hmsTimeFormatter(long milliSeconds) {
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(milliSeconds), TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));
    }


    public String decimalpoint(float f) {
        String time;
        DecimalFormat df = new DecimalFormat("#.00");
        time = String.valueOf(df.format(f));
        return time;
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();

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

    public void DialogPremium() {
        dialog = new Dialog(Activity_Discoverexelist.this, R.style.FullScreenDialogStyle);
        dialog.setContentView(R.layout.dialog_ads);
        dialog.setCanceledOnTouchOutside(false);
        LinearLayout mBtnwatchad = (LinearLayout) dialog.findViewById(R.id.btnwatchad);
        LinearLayout mBtnpremium = (LinearLayout) dialog.findViewById(R.id.btnpurchase);
        mBtnpremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Discoverexelist.this, Activity_Purchase.class);
                startActivity(intent);
            }
        });
        mBtnwatchad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ConnectivityManager mgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = mgr.getActiveNetworkInfo();
                if (netInfo != null) {
                    if (netInfo.isConnected()) {


                        if (rewardedAd != null && rewardedAd.isLoaded()) {
                            showRewardedVideo();
                        } else {
                            if (isFailtoload) {
                                initAdmobFullAd1(Activity_Discoverexelist.this);

                                //  Toast.makeText(Activity_Discoverexelist.this, "Please Try After Some Time", Toast.LENGTH_SHORT).show();
                            } else {
                                if (dialogload != null) {
                                    dialogload.show();
                                    handler1 = new Handler();
                                    runnable1 = new Runnable() {
                                        @Override
                                        public void run() {
                                            if (rewardedAd != null && rewardedAd.isLoaded()) {
                                                dialogload.dismiss();
                                                showRewardedVideo();
                                            } else {
                                                handler2 = new Handler();
                                                runnable2 = new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        dialogload.dismiss();
                                                        if (rewardedAd != null && rewardedAd.isLoaded()) {
                                                            showRewardedVideo();
                                                        } else {

                                                            initAdmobFullAd1(Activity_Discoverexelist.this);
                                                            //  Toast.makeText(Activity_Discoverexelist.this, "Please Try After Some Timee", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }
                                                };
                                                handler2.postDelayed(runnable2, 2000);
                                            }
                                        }
                                    };
                                    handler1.postDelayed(runnable1, 3000);
                                }
                            }
                        }
                    } else {
                        Toast.makeText(Activity_Discoverexelist.this, getResources().getString(R.string.please_check_your_internet_connection), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(Activity_Discoverexelist.this, getResources().getString(R.string.please_check_your_internet_connection), Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                onBackPressed();
            }
        });
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }


    @Override
    protected void onStart() {
        isclicked = false;
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
