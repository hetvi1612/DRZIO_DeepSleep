package drzio.insomnia.treatment.bedtime.yoga.sleep.Activity;

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
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter.ExcerciselistAdapter;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.DatabaseOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Daymodals;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Activity_Excerciselist extends AppCompatActivity {
    int[] excercisenames = new int[]{};
    int[] exethumbimgs = new int[]{};
    int[] excercisetime = new int[]{};
    int[] calorie = new int[]{};
    int[] calorie51 = new int[]{};
    int[] calorie76 = new int[]{};
    int[] calorie90 = new int[]{};
    int[] viddata = new int[]{};
    int[] youtubevid = new int[]{};
    int[] exedesc = new int[]{};
    List<Daymodals> excerciseDataList = new ArrayList<>();
    int mPosition;
    private String mTime;
    private String dayname;
    public float Totaltime;
    public float Totalkcal;
    private TinyDB tinydb;
    private int REST_TAG = 6;
    private int b;
    ArrayList<String> roundnames = new ArrayList<>();
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
        tinydb = new TinyDB(Activity_Excerciselist.this);
        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(this, languages);
        ImageView mImgheader = (ImageView) findViewById(R.id.header);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            dayname = bundle.getString("dname");
            mPosition = bundle.getInt("pos");
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
       /* if (mLevel == 2) {
            mImgheader.setImageResource(R.drawable.img_alv22);
            final TypedArray daysarray = getResources().obtainTypedArray(R.array.lv2daysarray);
            excercisenames = LevelArrays(daysarray);
            final TypedArray thumbarray = getResources().obtainTypedArray(R.array.lv2thumbssarray);
            exethumbimgs = LevelArrays(thumbarray);
            final TypedArray exetimearray = getResources().obtainTypedArray(R.array.lv2cyclesarray);
            excercisetime = LevelArrays(exetimearray);
            final TypedArray caloriearray = getResources().obtainTypedArray(R.array.lv2caloriecycles);
            calorie = LevelArrays(caloriearray);
            final TypedArray calorie51array = getResources().obtainTypedArray(R.array.lv2_51_75kalcycles);
            calorie51 = LevelArrays(calorie51array);
            final TypedArray calorie76array = getResources().obtainTypedArray(R.array.lv2_76_90kalcycles);
            calorie76 = LevelArrays(calorie76array);
            final TypedArray calorie90array = getResources().obtainTypedArray(R.array.lv2_90kalcycles);
            calorie90 = LevelArrays(calorie90array);
            final TypedArray vidarrays = getResources().obtainTypedArray(R.array.lv2_exevideos);
            viddata = LevelArrays(vidarrays);
            final TypedArray youtubevidarrays = getResources().obtainTypedArray(R.array.lv2_youtubevids);
            youtubevid = LevelArrays(youtubevidarrays);
            final TypedArray descarray = getResources().obtainTypedArray(R.array.lv2_exedescriptions);
            exedesc = LevelArrays(descarray);
            REST_TAG = 6;
        } else if (mLevel == 3) {
            mImgheader.setImageResource(R.drawable.img_alv33);
            final TypedArray daysarray = getResources().obtainTypedArray(R.array.lv3daysarray);
            excercisenames = LevelArrays(daysarray);
            final TypedArray thumbarray = getResources().obtainTypedArray(R.array.lv3thumbssarray);
            exethumbimgs = LevelArrays(thumbarray);
            final TypedArray exetimearray = getResources().obtainTypedArray(R.array.lv3cyclesarray);
            excercisetime = LevelArrays(exetimearray);
            final TypedArray caloriearray = getResources().obtainTypedArray(R.array.lv3caloriecycles);
            calorie = LevelArrays(caloriearray);
            final TypedArray calorie51array = getResources().obtainTypedArray(R.array.lv3_51_75kalcycles);
            calorie51 = LevelArrays(calorie51array);
            final TypedArray calorie76array = getResources().obtainTypedArray(R.array.lv3_76_90kalcycles);
            calorie76 = LevelArrays(calorie76array);
            final TypedArray calorie90array = getResources().obtainTypedArray(R.array.lv3_90kalcycles);
            calorie90 = LevelArrays(calorie90array);
            final TypedArray vidarrays = getResources().obtainTypedArray(R.array.lv3_exevideos);
            viddata = LevelArrays(vidarrays);
            final TypedArray youtubevidarrays = getResources().obtainTypedArray(R.array.lv3_youtubevids);
            youtubevid = LevelArrays(youtubevidarrays);
            final TypedArray descarray = getResources().obtainTypedArray(R.array.lv3_exedescriptions);
            exedesc = LevelArrays(descarray);
            REST_TAG = 5;
        } else {*/
            mImgheader.setImageResource(R.drawable.img_alv1);
            final TypedArray daysarray = getResources().obtainTypedArray(R.array.lv1daysarray);
            excercisenames = LevelArrays(daysarray);
            final TypedArray thumbarray = getResources().obtainTypedArray(R.array.lv1thumbssarray);
            exethumbimgs = LevelArrays(thumbarray);
            final TypedArray exetimearray = getResources().obtainTypedArray(R.array.lv1cyclesarray);
            excercisetime = LevelArrays(exetimearray);
            final TypedArray caloriearray = getResources().obtainTypedArray(R.array.lv1caloriecycles);
            calorie = LevelArrays(caloriearray);
            final TypedArray calorie51array = getResources().obtainTypedArray(R.array.lv1_51_75kalcycles);
            calorie51 = LevelArrays(calorie51array);
            final TypedArray calorie76array = getResources().obtainTypedArray(R.array.lv1_76_90kalcycles);
            calorie76 = LevelArrays(calorie76array);
            final TypedArray calorie90array = getResources().obtainTypedArray(R.array.lv1_90kalcycles);
            calorie90 = LevelArrays(calorie90array);
            final TypedArray vidarrays = getResources().obtainTypedArray(R.array.lv1_exevideos);
            viddata = LevelArrays(vidarrays);
            final TypedArray youtubevidarrays = getResources().obtainTypedArray(R.array.lv1_youtubevids);
            youtubevid = LevelArrays(youtubevidarrays);
            final TypedArray descarray = getResources().obtainTypedArray(R.array.lv1_exedescriptions);
            exedesc = LevelArrays(descarray);
            REST_TAG = 6;
     /*   }*/
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
                Intent intent = new Intent(Activity_Excerciselist.this, Activity_Calenderview.class);
                startActivity(intent);
            }
        });
        mCardrestart = (RelativeLayout) findViewById(R.id.btnrestart);
        mBtnstart.setClickable(false);
        if (!tinydb.getBoolean(Constants.PREMIUN_KEY)) {
            boolean temp = false;
            if (mLevel == 2) {
                temp = Constants.isARewardgiven2;
            } else if (mLevel == 3) {
                temp = Constants.isARewardgiven3;
            } else {
                temp = true;
            }
            if (!temp) {
                DialogAdsloading();
                loadRewardedAd();
                DialogPremium();
                if (mLevel == 1) {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
                if (mLevel == 2 && isLastdone) {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
                if (mLevel == 3 && isLastdone) {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }
            }
        }
        databaseOperations = new DatabaseOperations(Activity_Excerciselist.this);
        shine=findViewById(R.id.shine);
        shineAnimation();
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
        String[] temparray = getResources().getStringArray(excercisenames[mPosition]);
        String[] temparray2 = getResources().getStringArray(excercisetime[mPosition]);
        String[] temparray3 = (getResources().getStringArray(calorie[mPosition]));
        if (tinydb.getBoolean(Constants.ISKG_KEY)) {
            int mWeight = tinydb.getInt(Constants.WEIGHT_KEY);
            if (mWeight < 51) {
                temparray3 = (getResources().getStringArray(calorie[mPosition]));
            } else if (mWeight > 51 && mWeight < 76) {
                temparray3 = (getResources().getStringArray(calorie51[mPosition]));
            } else if (mWeight > 75 && mWeight < 90) {
                temparray3 = (getResources().getStringArray(calorie76[mPosition]));
            } else if (mWeight > 90) {
                temparray3 = (getResources().getStringArray(calorie90[mPosition]));
            }
        } else {
            int mWeight = tinydb.getInt(Constants.WEIGHT_KEY);
            if (mWeight < 112) {
                temparray3 = (getResources().getStringArray(calorie[mPosition]));
            } else if (mWeight > 112 && mWeight < 167) {
                temparray3 = (getResources().getStringArray(calorie51[mPosition]));
            } else if (mWeight > 167 && mWeight < 198) {
                temparray3 = (getResources().getStringArray(calorie76[mPosition]));
            } else if (mWeight > 198) {
                temparray3 = (getResources().getStringArray(calorie90[mPosition]));
            }
        }
        String[] temparray4 = (getResources().getStringArray(youtubevid[mPosition]));
        String[] temparray5 = (getResources().getStringArray(exedesc[mPosition]));
        mTxtcount.setText(String.valueOf(temparray.length));
        Resources res = getResources();
        Resources res2 = getResources();



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
        final TypedArray videoarray = res.obtainTypedArray(viddata[mPosition]);
        final TypedArray thumbsarray = res2.obtainTypedArray(exethumbimgs[mPosition]);


        for (int k = 0; k < temparray2.length; k++) {
            try{


            float number = Float.parseFloat(temparray2[k]);
            float value = number / 60;
            Totaltime = Totaltime + value;
            }catch (NumberFormatException n){

            }
        }

        for (int k2 = 0; k2 < temparray3.length; k2++) {
            try {


            float number = Float.parseFloat(temparray3[k2]);
            float values = number / 60;
            float temp = Float.parseFloat(temparray2[k2]);
            float temp2 = values * temp;
            Totalkcal = Totalkcal + temp2;
            }catch (Exception e){

            }
        }

        mTotalkcal.setText(decimalpoint(Totalkcal));

        mTxttotaltime.setText(decimalpoint(Totaltime));
        for (int i = 0; i < temparray.length; i++) {
            try {
                Daymodals excerciseData = new Daymodals();
                excerciseData.setExecercisename(temparray[i]);
                if (temparray2[i].length() == 1) {
                    mTime = "0" + temparray2[i] + ":00";
                } else {
                    mTime = "00:" + temparray2[i];
                }
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (int k = 0; k < temparray.length; k++) {
            if (k % REST_TAG == 0) {
                b++;
                roundnames.add("Round " + b);
                Templist.add(roundnames);
            }
        }

        List<Object> Templist2 = Templist;
        RecyclerView mExcerciseRecycler = (RecyclerView) findViewById(R.id.excerciselist);
        mExcerciseRecycler.setLayoutManager(new LinearLayoutManager(Activity_Excerciselist.this, LinearLayoutManager.VERTICAL, false));
        ExcerciselistAdapter excerciselistAdapter = new ExcerciselistAdapter(Activity_Excerciselist.this, excerciseDataList, Activity_Excerciselist.this, roundnames, mLevel);
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
    }



    private void shineAnimation() {
        Animation anim = AnimationUtils.loadAnimation(Activity_Excerciselist.this, R.anim.left_right);
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

    public void loadAdmobAd() {
        if (mInterstitialAdMob != null && !mInterstitialAdMob.isLoaded()) {
            mInterstitialAdMob.loadAd(new AdRequest.Builder().build());
        }
    }

 /*   public void InitializePremium() {
        mPremiunlay.setVisibility(View.VISIBLE);
        LinearLayout mBtnwatchad = (LinearLayout) findViewById(R.id.btnwatchad);
        LinearLayout mBtnpremium = (LinearLayout) findViewById(R.id.btnpurchase);
        mBtnpremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Excerciselist.this, Activity_Purchase.class);
                startActivity(intent);
            }
        });
        mBtnwatchad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rewardedAd != null && rewardedAd.isLoaded()) {
                    showRewardedVideo();
                } else {
                    if (isFailtoload) {
                        Toast.makeText(Activity_Excerciselist.this, "Please Try After Some Time", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Activity_Excerciselist.this, "Please wait Ad is loading...", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }*/


    private void loadRewardedAd() {
        if (rewardedAd == null || !rewardedAd.isLoaded()) {
            rewardedAd = new RewardedAd(Activity_Excerciselist.this, Constants.admob_rewardad);
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
                            if (mLevel == 2) {
                                Constants.isARewardgiven2 = true;
                            }
                            if (mLevel == 3) {
                                Constants.isARewardgiven3 = true;
                            }
                            if (dialog != null && dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        }

                        @Override
                        public void onRewardedAdFailedToShow(int errorCode) {
                            initAdmobFullAd1(Activity_Excerciselist.this);
                            //Toast.makeText(Activity_Excerciselist.this, "Please Wait There is No Ad To Show", Toast.LENGTH_SHORT).show();
                        }
                    };
            rewardedAd.show(Activity_Excerciselist.this, adCallback);
        }
    }


    public void StartbtnClick() {
        if (mLevel == 1) {
            tinydb.putString(Constants.DAYCLICK_KEY, dayname);
        } else if (mLevel == 2) {
            tinydb.putString(Constants.ALV2DAYCLICK_KEY, dayname);
        } else if (mLevel == 3) {
            tinydb.putString(Constants.ALV3DAYCLICK_KEY, dayname);
        }
        Bundle bundle1 = new Bundle();
        bundle1.putSerializable("listexecercise", (Serializable) excerciseDataList);
        Intent intent = new Intent(Activity_Excerciselist.this, Activity_Exercisestart.class);
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
        Intent intent = new Intent(Activity_Excerciselist.this, Activity_Exercisestart.class);
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
        if (mLevel == 2) {
            Intent intent = new Intent(Activity_Excerciselist.this, Activity_Level2.class);
            startActivity(intent);
            finish();
        } else if (mLevel == 3) {
            Intent intent = new Intent(Activity_Excerciselist.this, Activity_Level3.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(Activity_Excerciselist.this, Activity_Level1.class);
            startActivity(intent);
            finish();
        }

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


    public void DialogAdsloading() {
        dialogload = new Dialog(Activity_Excerciselist.this);
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


    public void DialogPremium() {
        dialog = new Dialog(Activity_Excerciselist.this, R.style.FullScreenDialogStyle);
        dialog.setContentView(R.layout.dialog_ads);
        dialog.setCanceledOnTouchOutside(false);
        LinearLayout mBtnwatchad = (LinearLayout) dialog.findViewById(R.id.btnwatchad);
        LinearLayout mBtnpremium = (LinearLayout) dialog.findViewById(R.id.btnpurchase);
        mBtnpremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Excerciselist.this, Activity_Purchase.class);
                startActivity(intent);
            }
        });
        mBtnwatchad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rewardedAd != null && rewardedAd.isLoaded()) {
                    showRewardedVideo();
                } else {
                    if (isFailtoload) {
                        initAdmobFullAd1(Activity_Excerciselist.this);

                       // Toast.makeText(Activity_Excerciselist.this, "Please Try After Some Time", Toast.LENGTH_SHORT).show();
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

                                                    initAdmobFullAd1(Activity_Excerciselist.this);

                                                    //Toast.makeText(Activity_Excerciselist.this, "Please Try After Some Time", Toast.LENGTH_SHORT).show();
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


    public void initAdmobFullAd1(Context context) {
        if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
            return;
        }
        mInterstitialAdMob = new InterstitialAd(context);
        mInterstitialAdMob.setAdUnitId(Constants.admob_Interstitial);
        mInterstitialAdMob.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                if (mLevel == 2) {
                    Constants.isARewardgiven2 = true;
                }
                if (mLevel == 3) {
                    Constants.isARewardgiven3 = true;
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
                Toast.makeText(Activity_Excerciselist.this, "Pleasee Wait There is No Ad To Show", Toast.LENGTH_SHORT).show();

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
        if (dialogload != null && dialogload.isShowing()) {
            dialogload.dismiss();
        }
        if (handler1 != null) {
            handler1.removeCallbacks(runnable1);
        }
        if (handler2 != null) {
            handler2.removeCallbacks(runnable2);
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
