package drzio.insomnia.treatment.bedtime.yoga.sleep.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
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
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.shawnlin.numberpicker.NumberPicker;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter.StoreBanner_Adapter;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.AppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.ChooseGenderActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.MaleAppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity.Blogmodel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.DatabaseOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Diet.CustomUpdateActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Diet.DefalutAndCustomplanActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Discoverpage.Activity_Discoverexelist;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.ApiInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.RateModel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.ReminderMainActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.ShareUtils;
import drzio.insomnia.treatment.bedtime.yoga.sleep.SliderNewAdapter;
import drzio.insomnia.treatment.bedtime.yoga.sleep.customs.CustomSeekBar;
import drzio.insomnia.treatment.bedtime.yoga.sleep.customs.ProgressBarAnimation;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Banner1;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.ProgressItem;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.StoreBannerData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIClient;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BuildConfig;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.homebannerdatalist1;
import static retrofit2.converter.scalars.ScalarsConverterFactory.create;

public class Activity_Completion extends AppCompatActivity {
    private int totalwork;
    private String dayname;
    private String time;
    private float mKcal;
    private CustomSeekBar bmiseekBar;

    private TextView wght2;
    private TextView wght4;
    private int mHcmvalue = 100;
    private int mHftvalue = 5;
    private int mHinchvalue = 0;
    private int mWkgvalue = 45;
    private int mWlbvalue = 50;
    private boolean isbtncm = true;
    private boolean isbtninch;
    private TextView mSwich2txt;
    private boolean isBtnkg = true;
    private boolean isBtnLb;
    private TextView pageSelected;
    private RelativeLayout mBmiscalelayout;
    private TinyDB tinyDB;
    public float Heightincms = 0.0f;
    private TextView mTxtbmi;
    ImageView mBtnstore;
    private LinearLayout mBtncontinue;
    private LinearLayout mBtnslayout;
    private boolean mIsActive;
    private Handler ratehandler;
    private Runnable mRaterunnable;
    public ArrayList<Blogmodel> mBlogsdata = new ArrayList<>();
    private int blogid;
    private ImageView thumbImage;
    private TextView mTxtName;
    private HtmlTextView mTxtdesc;
    private TextView mTxtlikes;
    private CardView mBlogdetail;
    private CheckBox mLikebtn;
    boolean isclicked = false;
    private String mPlanname;
    private int mLevel;
    private CountDownTimer mRemindertimer;
    //    private SliderView sliderView;
    public int mStarcount = 0;
    private AlertDialog feedbackdial;
    ArrayList<String> mSelectedFeedbackslist = new ArrayList<>();
    private String[] feedbacklist;
    private boolean mSuccess;
    private CardView mAdcard1;
    private StoreBanner_Adapter storeBannerAdapter;
    private BackpainAPIInterface apiInterface;
    private SliderView sliderView;
    private RelativeLayout mSharelay;
    private TextView mTvrecomand;
    private View mTempview;
    private LinearLayout Slidelayout;
    private ShimmerFrameLayout mSlideloader;
    private String token;
    private int temprate;
    TextView compalteday;
    String BASEURL;
    TextView mTxtreminder, txt_more;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_completion);
        Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        AppBarLayout appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        tinyDB = new TinyDB(Activity_Completion.this);
        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(this, languages);
        token = tinyDB.getString(Constants.Authtoken);
        BASEURL = tinyDB.getString(Constants.NewBaseUrl);
        txt_more = findViewById(R.id.txt_more);
        TextView mTotalexe = (TextView) findViewById(R.id.txtexcecount);
        TextView mTotalkcal = (TextView) findViewById(R.id.txttotalcal);
        TextView mTotaltime = (TextView) findViewById(R.id.txtexcertime);
        RelativeLayout mBtreminder = (RelativeLayout) findViewById(R.id.btreminder);
        LinearLayout mBtcalbmi = (LinearLayout) findViewById(R.id.btcalbmi);
        mBmiscalelayout = (RelativeLayout) findViewById(R.id.bmiscalelay);
        this.bmiseekBar = (CustomSeekBar) findViewById(R.id.bmiseekBar);
        this.wght2 = (TextView) findViewById(R.id.wght2);
        this.wght4 = (TextView) findViewById(R.id.wght4);
        mTxtreminder = (TextView) findViewById(R.id.txttime);
        mTxtbmi = (TextView) findViewById(R.id.txtbmi);
        ImageView mBtnback = (ImageView) findViewById(R.id.btnback);
        mBtncontinue = (LinearLayout) findViewById(R.id.btnconti);
        mBtnslayout = (LinearLayout) findViewById(R.id.btnslayout);
        feedbacklist = getResources().getStringArray(R.array.feedlist);
        mBtnstore = (ImageView) findViewById(R.id.ivbtnstore);
        ImageView mBtnfbshare = (ImageView) findViewById(R.id.btnfb);
        ImageView mBtnwhatsshare = (ImageView) findViewById(R.id.btnwhats);
        ImageView mBtntweetshare = (ImageView) findViewById(R.id.btntwite);
        ImageView mBtnallshare = (ImageView) findViewById(R.id.btnshareall);


        thumbImage = (ImageView) findViewById(R.id.imgthumb);
        mTxtName = (TextView) findViewById(R.id.name);
        mTxtdesc = (HtmlTextView) findViewById(R.id.descriptions);
        mBlogdetail = (CardView) findViewById(R.id.blogdetail);

        mBlogdetail.setVisibility(View.GONE);
        ShimmerFrameLayout mLoadlay = findViewById(R.id.shimmer_container);
        RelativeLayout banner1 = (RelativeLayout) findViewById(R.id.banner1);
        FrameLayout mGbannerlay2 = findViewById(R.id.adframe1234);
        mGbannerlay2.setVisibility(View.GONE);
        if (!tinyDB.getBoolean(Constants.PREMIUN_KEY)) {
            showBanner(this, mGbannerlay2, banner1, mLoadlay);
        }else {
            mLoadlay.setVisibility(View.GONE);
        }
        mAdcard1 = (CardView) findViewById(R.id.cardadd);
        LinearLayout mNativeframe = (LinearLayout) findViewById(R.id.adframe2);
        showGOOGLEAdvance(Activity_Completion.this, mNativeframe);

       /* LinearLayout mGbannerlay = findViewById(R.id.adframe);
        mGbannerlay.setVisibility(View.GONE);*/
        String time = tinyDB.getString("alarmtime");
        if (time != null && !time.equals("")) {
            mTxtreminder.setText(time);
            mTxtreminder.setVisibility(View.VISIBLE);
        } else {
            mTxtreminder.setVisibility(View.GONE);
        }

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            totalwork = bundle.getInt("totalexe");
            dayname = bundle.getString("dname");
            time = bundle.getString("duration");
            mKcal = bundle.getFloat("daykcal");
            mIsActive = bundle.getBoolean("isactive");
            mPlanname = bundle.getString("planname");
            mLevel = bundle.getInt("level");
        }
       /* compalteday = (TextView) findViewById(R.id.compalteday);
        compalteday.setText(dayname + " " + getResources().getString(R.string.is_completed));
        TextView tvtext=(TextView)findViewById(R.id.tvtext);
        tvtext.setText(dayname+ " " + getResources().getString(R.string.is_completed));*/
        LinearLayout laystore = findViewById(R.id.laystore);
        setSupportActionBar(toolbar);
        collapsingToolbar.setTitle(dayname + " " + getResources().getString(R.string.is_completed));
        collapsingToolbar.setExpandedTitleColor(Color.WHITE);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.themefont);
        collapsingToolbar.setCollapsedTitleTypeface(typeface);
        collapsingToolbar.setExpandedTitleTypeface(typeface);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBarPlus1);
        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.headercolor));
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if ((collapsingToolbar.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(collapsingToolbar))) {
                    //   mBtnback.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
                    // laystore.setVisibility(View.GONE);
                    mBtnback.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_back_black_24dp));
                    mBtnback.setAlpha(1.0F);
                    mBtnstore.setBackgroundResource(R.drawable.adsstoreanim);
                    txt_more.setTextColor(getResources().getColor(R.color.black));
                    AnimationDrawable anim2 = (AnimationDrawable) mBtnstore.getBackground();
                    anim2.start();

                } else {
                    //   mBtnback.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                    //   laystore.setVisibility(View.VISIBLE);
                 /*   mBtnstore.setBackgroundResource(R.drawable.adsstoreanim1);
                    txt_more.setTextColor(getResources().getColor(R.color.white));
                    AnimationDrawable anim2 = (AnimationDrawable) mBtnstore.getBackground();
                    anim2.start();*/

                    mBtnback.setImageDrawable(getResources().getDrawable(R.drawable.backk));
                    mBtnback.setAlpha(0.5F);
                    mBtnstore.setBackgroundResource(R.drawable.adsstoreanim);
                    txt_more.setTextColor(getResources().getColor(R.color.black));
                    AnimationDrawable anim2 = (AnimationDrawable) mBtnstore.getBackground();
                    anim2.start();
                }
            }
        });

        if (mIsActive) {
            mBtnslayout.setVisibility(View.VISIBLE);
            mBtncontinue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mLevel < 4) {
                        if (!isclicked) {
                            isclicked = true;
                            Intent intent = new Intent(Activity_Completion.this, Activity_Excerciselist.class);
                            intent.putExtra("dname", dayname);
                            intent.putExtra("pos", 0);
                            intent.putExtra("dayprogrss", 100);
                            intent.putExtra("level", mLevel);
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        if (!isclicked) {
                            isclicked = true;
                            Intent intent = new Intent(Activity_Completion.this, Activity_Discoverexelist.class);
                            intent.putExtra("dname", dayname);
                            intent.putExtra("dayprogrss", 0);
                            intent.putExtra("level", mLevel);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
            });
        } else {
            mBtncontinue.setVisibility(View.GONE);
        }


        mBtnstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cgender = tinyDB.getBoolean(Constants.Genderchoose);
                if (cgender) {
                    if (tinyDB.getString(Constants.GENDER_KEY).equals(getResources().getString(R.string.female))) {
                        Intent intent = new Intent(Activity_Completion.this, AppstoreActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(Activity_Completion.this, MaleAppstoreActivity.class);
                        startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(Activity_Completion.this, ChooseGenderActivity.class);
                    startActivity(intent);
                }
            }
        });

       /* appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if ((collapsingToolbar.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(collapsingToolbar))) {
                    //  mTvtooltitle.setTextColor(Color.WHITE);
                    mBtnback.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
                    //   mIvtips.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                    //  mIvshop.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);

                    // mBtnstore.setBackgroundResource(R.drawable.storeicons);
                    compalteday.setTextColor(Color.BLACK);
                    compalteday.setVisibility(View.VISIBLE);
                  *//*  AnimationDrawable anim = (AnimationDrawable) mBtnstore.getBackground();
                    anim.start();*//*
                } else {
                    //    mTvtooltitle.setTextColor(Color.WHITE);
                    //  mTvdname.setVisibility(View.GONE);
                    mBtnback.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                    compalteday.setTextColor(Color.WHITE);
                    // mBtnstore.setBackgroundResource(R.drawable.storeicons);
                  *//*  AnimationDrawable anim = (AnimationDrawable) mBtnstore.getBackground();
                    anim.start();*//*


                    //   mIvtips.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                    //  mIvshop.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                }
            }
        });*/

    /*    TextView tvtext=(TextView)findViewById(R.id.tvtext);
        tvtext.setText(dayname + " completed");*/
      /*  appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if ((collapsingToolbar.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(collapsingToolbar))) {
                    //  mTvtooltitle.setTextColor(Color.WHITE);
                    mBtnback.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
                    //   mIvtips.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                    //  mIvshop.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                    collapsingToolbar.setCollapsedTitleTextAppearance(R.style.AppBarCollapsed);
                    mBtnstore.setBackgroundResource(R.drawable.adsstoreanim);
                    //  tvtext.setTextColor(Color.BLACK);
                    //   tvtext.setVisibility(View.VISIBLE);
                    AnimationDrawable anim = (AnimationDrawable) mBtnstore.getBackground();
                    anim.start();
                } else {
                    //    mTvtooltitle.setTextColor(Color.WHITE);
                    // tvtext.setVisibility(View.GONE);
                    mBtnback.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                    collapsingToolbar.setExpandedTitleTextAppearance(R.style.AppBarExpanded);
                    mBtnstore.setBackgroundResource(R.drawable.adsstoreanim);
                    AnimationDrawable anim = (AnimationDrawable) mBtnstore.getBackground();
                    anim.start();


                    //   mIvtips.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                    //  mIvshop.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                }
            }
        });*/

   /*     appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if ((collapsingToolbar.getHeight() + verticalOffset) < (2 * ViewCompat.getMinimumHeight(collapsingToolbar))) {
                    //  mTvtooltitle.setTextColor(Color.WHITE);
                    mBtnback.setColorFilter(Color.BLACK, PorterDuff.Mode.SRC_ATOP);
                    //   mIvtips.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                    //  mIvshop.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                    collapsingToolbar.setCollapsedTitleTextAppearance(R.style.AppBarCollapsed);
                    mBtnstore.setBackgroundResource(R.drawable.adsstoreanim);
                    tvtext.setTextColor(Color.BLACK);
                    tvtext.setVisibility(View.VISIBLE);
                    AnimationDrawable anim = (AnimationDrawable) mBtnstore.getBackground();
                    anim.start();
                } else {
                    //    mTvtooltitle.setTextColor(Color.WHITE);
                    tvtext.setVisibility(View.GONE);
                    mBtnback.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                    collapsingToolbar.setExpandedTitleTextAppearance(R.style.AppBarExpanded);
                    mBtnstore.setBackgroundResource(R.drawable.adsstoreanim);
                    AnimationDrawable anim = (AnimationDrawable) mBtnstore.getBackground();
                    anim.start();


                    //   mIvtips.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                    //  mIvshop.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                }
            }
        });*/
        sliderView = findViewById(R.id.imageSlider);
        mTvrecomand = findViewById(R.id.tvrecommand);
        mTempview = findViewById(R.id.tempview);
        //callStorebannerApi();
        mBtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Slidelayout = (LinearLayout) findViewById(R.id.slidelayout);
        mSlideloader = (ShimmerFrameLayout) findViewById(R.id.slideloader);
        sliderView = findViewById(R.id.imageSlider);
        Slidelayout.setVisibility(View.VISIBLE);
        DatabaseOperations databaseOperations = new DatabaseOperations(Activity_Completion.this);
        databaseOperations.insertExcCounter(dayname, 0, mLevel);
        if (tinyDB.getBoolean(Constants.ISCM_KEY)) {
            isbtncm = true;
            isbtninch = false;
            mHcmvalue = (int) tinyDB.getFloat(Constants.HEIGHT_KEY);
            //    mTvheight.setText(tinyDB.getFloat(Constants.HEIGHT_KEY) + " cm");
        } else {
            isbtncm = false;
            isbtninch = true;
            float mData = tinyDB.getFloat(Constants.HEIGHT_KEY);
            String doubleAsString = String.valueOf(mData);
            int indexOfDecimal = doubleAsString.indexOf(".");
            mHftvalue = Integer.parseInt(doubleAsString.substring(0, indexOfDecimal));
            String mTrim = doubleAsString.substring(indexOfDecimal).replace(".", "");
            mHinchvalue = Integer.parseInt(mTrim);
            //  mTvheight.setText(mData + " ft");
        }
        if (tinyDB.getBoolean(Constants.ISKG_KEY)) {
            isBtnkg = true;
            isBtnLb = false;
            mWkgvalue = tinyDB.getInt(Constants.WEIGHT_KEY);
            //    mTvweight.setText(mWkgvalue + " kg");
        } else {
            isBtnLb = true;
            isBtnkg = false;
            mWlbvalue = tinyDB.getInt(Constants.WEIGHT_KEY);
            //   mTvweight.setText(mWlbvalue + " lb");
        }

        mBtreminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Completion.this, ReminderMainActivity.class);
                startActivity(intent);
            }
        });
        mBtcalbmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCalculatebmi();
            }
        });
        if (tinyDB.getFloat(Constants.BMI_KEY) != 0) {
            mBmiscalelayout.setVisibility(View.VISIBLE);
            mTxtbmi.setText(String.valueOf(tinyDB.getFloat(Constants.BMI_KEY)) + "KG/M²");
            initDataToSeekbar();
        } else {
            mBmiscalelayout.setVisibility(View.GONE);
        }
        long minutes = (Long.parseLong(time) / 1000) / 60;
        int seconds = (int) ((Long.parseLong(time) / 1000) % 60);
        mTotalexe.setText(String.valueOf(totalwork));
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("0");
        stringBuilder.append(minutes);
        stringBuilder.append(":");
        stringBuilder.append(seconds);
        mTotaltime.setText(hmsTimeFormatter(Long.parseLong(time)));
        mTotalkcal.setText(String.valueOf(round(mKcal, 2)));
        ratehandler = new Handler();
        String finalTime = time;
        int seconds2 = (int) ((Long.parseLong(time) / 1000));


            DialogRatexeperience(seconds2, mLevel, hmsTimeFormatter(Long.parseLong(finalTime)));

           /* mRaterunnable = new Runnable() {
            @Override
            public void run() {
                DialogRatexeperience(seconds2, mLevel, hmsTimeFormatter(Long.parseLong(finalTime)));
            }
        };*/
        if (mIsActive) {
            ratehandler.postDelayed(mRaterunnable, 1000);
        }
        if (tinyDB.getBoolean(Constants.ISCM_KEY)) {
            isbtncm = true;
            isbtninch = false;
            mHcmvalue = (int) tinyDB.getFloat(Constants.HEIGHT_KEY);
//    mTvheight.setText(tinyDB.getFloat(Constants.HEIGHT_KEY) + " cm");
        } else {
            isbtncm = false;
            isbtninch = true;
            float mData = tinyDB.getFloat(Constants.HEIGHT_KEY);
            String doubleAsString = String.valueOf(mData);
            int indexOfDecimal = doubleAsString.indexOf(".");
            mHftvalue = Integer.parseInt(doubleAsString.substring(0, indexOfDecimal));
            String mTrim = doubleAsString.substring(indexOfDecimal).replace(".", "");
            mHinchvalue = Integer.parseInt(mTrim);
            //  mTvheight.setText(mData + " ft");
        }
        if (tinyDB.getBoolean(Constants.ISKG_KEY)) {
            isBtnkg = true;
            isBtnLb = false;
            mWkgvalue = tinyDB.getInt(Constants.WEIGHT_KEY);
//    mTvweight.setText(mWkgvalue + " kg");
        } else {
            isBtnLb = true;
            isBtnkg = false;
            mWlbvalue = tinyDB.getInt(Constants.WEIGHT_KEY);
            //   mTvweight.setText(mWlbvalue + " lb");
        }


        StringBuilder mSharestr = new StringBuilder();
        mSharestr.append(dayname + getResources().getString(R.string.completed) + "\n");
        mSharestr.append("Total Exercises : " + totalwork + "\n");
        mSharestr.append("Total Duration : " + hmsTimeFormatter(Long.parseLong(finalTime)) + "\n");
        mSharestr.append("Total Calorie : " + round(mKcal, 1) + "\n");
        //mSharestr.append(getString(R.string.app_name));
        mSharestr.append(" at: https://play.google.com/store/apps/details?id=");
        mSharestr.append(getPackageName());
        mSharelay = (RelativeLayout) findViewById(R.id.sharelayout);
        Sharelatout(String.valueOf(totalwork), String.valueOf(round(mKcal, 2)), hmsTimeFormatter(Long.parseLong(time)));
        mBtnfbshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                saveBitMap(Activity_Completion.this,mSharelay);
                ShareUtils.shareFacebook(Activity_Completion.this, mSharestr.toString(), "https://play.google.com/store/apps/details?id=" + getPackageName());
            }
        });
        mBtnwhatsshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.shareViaWhatsApp(Activity_Completion.this, mSharestr.toString());
            }
        });
        mBtnslayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                saveBitMap(Activity_Completion.this,mSharelay);
                ShareUtils.shareViaAll(Activity_Completion.this, mSharestr.toString());
            }
        });
        mBtntweetshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.shareTwitter(Activity_Completion.this, mSharestr.toString(), "https://play.google.com/store/apps/details?id=" + getPackageName(), "", "");
            }
        });
        mBtnallshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.shareViaAll(Activity_Completion.this, mSharestr.toString());
            }
        });
        getBanner();
      /*  if (homebannerdatalist1.size() != 0) {
            try {
                if (Slidelayout != null) {
                    Slidelayout.setVisibility(View.VISIBLE);
                }
                mSlideloader.setVisibility(View.GONE);
                sliderView.setVisibility(View.VISIBLE);
                final SliderNewAdapter adapter = new SliderNewAdapter(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Completion.this, homebannerdatalist1);
                sliderView.setSliderAdapter(adapter);
                sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                sliderView.setIndicatorSelectedColor(Color.WHITE);
                sliderView.setIndicatorUnselectedColor(Color.GRAY);
                sliderView.startAutoCycle();
                sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
                    @Override
                    public void onIndicatorClicked(int position) {
                        sliderView.setCurrentPagePosition(position);
                        try {
                            adapter.notifyDataSetChanged();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                if (sliderView != null) {
                    sliderView.setVisibility(View.GONE);
                }
                if (Slidelayout != null) {
                    Slidelayout.setVisibility(View.GONE);
                }
            }
        } else {
            homebannerdatalist1.clear();
            getBanner();
        }*/
    }


    public void showBanner(Context context, final FrameLayout adMobView, RelativeLayout loadlayout, ShimmerFrameLayout mLoadlay) {
        try {
            final AdView mAdView = new AdView(context);
            mAdView.setAdSize(AdSize.BANNER);
            mAdView.setAdUnitId(Constants.admob_banner);
            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    mLoadlay.setVisibility(View.GONE);
                    if (adMobView != null) {
                        adMobView.removeAllViews();
                    }
                    adMobView.addView(mAdView);
                    loadlayout.setVisibility(View.VISIBLE);
                    adMobView.setVisibility(View.VISIBLE);
                    Constants.isReportBanner = false;
                }

                @Override
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    mLoadlay.setVisibility(View.GONE);
                    loadlayout.setVisibility(View.GONE);
                    loadlayout.setVisibility(View.GONE);
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

    public void Sharelatout(String texe, String tkcal, String ttime) {
        TextView mTvtexe = (TextView) findViewById(R.id.tvtexe);
        TextView mTvtcal = (TextView) findViewById(R.id.tvtcal);
        TextView mTvttime = (TextView) findViewById(R.id.tvttime);
        mTvtexe.setText(texe);
        mTvtcal.setText(tkcal);
        mTvttime.setText(ttime);
    }

    public void getBanner() {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiInterface apiInterface = retrofit.create(ApiInterface.class);
            try {
                JSONObject paramObject = new JSONObject();
                paramObject.put("anum", 22);
                paramObject.put("mode", "banner");


                Call<List<Banner1>> userCall = apiInterface.getbanner(paramObject.toString(), "Bearer " + token);
                userCall.enqueue(new Callback<List<Banner1>>() {
                    @Override
                    public void onResponse(Call<List<Banner1>> call, Response<List<Banner1>> response) {
                        try {
                            ArrayList<Banner1.Doc> newList = new ArrayList<>();
                            ArrayList<Banner1> banners = (ArrayList<Banner1>) response.body();
                            for (int i = 0; i < banners.size(); i++) {
                                ArrayList<Banner1.Doc> docs1 = (ArrayList<Banner1.Doc>) banners.get(i).getDocs();
                                for (int j = 0; j < docs1.size(); j++) {

                                    if (tinyDB.getBoolean(Constants.PREMIUN_KEY)) {
                                        if (!docs1.get(j).getDefaultType().equals("Ads")) {
                                            homebannerdatalist1.add(docs1.get(j));
                                            newList.add(docs1.get(j));
                                            Log.e("newList", String.valueOf(homebannerdatalist1.size()));
                                        }
                                    } else {
                                        homebannerdatalist1.add(docs1.get(j));
                                        newList.add(docs1.get(j));
                                        Log.e("newList", String.valueOf(homebannerdatalist1.size()));

                                    }
                                    if (Slidelayout != null) {
                                        Slidelayout.setVisibility(View.VISIBLE);
                                    }
                                    if (Slidelayout != null) {
                                        Slidelayout.setVisibility(View.VISIBLE);
                                    }
                                    mSlideloader.setVisibility(View.GONE);
                                    sliderView.setVisibility(View.VISIBLE);
                                    Log.e("newList1", String.valueOf(newList));
                                    final SliderNewAdapter adapter = new SliderNewAdapter(Activity_Completion.this, newList);
                                    sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
                                    //below method is used to setadapter to sliderview.
                                    sliderView.setSliderAdapter(adapter);
                                    sliderView.setVisibility(View.VISIBLE);
                                    //   final SliderAdapter adapter = new SliderAdapter(context, newList);
                                    sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
                                    //below method is used to setadapter to sliderview.
                                    sliderView.setSliderAdapter(adapter);
                                    //below method is use to set scroll time in seconds.
                                    sliderView.setScrollTimeInSec(3);
                                    //to set it scrollable automatically we use below method.
                                    sliderView.setAutoCycle(true);
                                    //to start autocycle below method is used.
                                    sliderView.startAutoCycle();
                       /* sliderView.setSliderAdapter(adapter);
                        sliderView.setIndicatorAnimation(IndicatorAnimations.DROP);
                        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                        sliderView.setIndicatorSelectedColor(Color.WHITE);
                        sliderView.setIndicatorUnselectedColor(Color.GRAY);
                        sliderView.startAutoCycle();*/
                                    sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
                                        @Override
                                        public void onIndicatorClicked(int position) {
                                            sliderView.setCurrentPagePosition(position);
                                            try {
                                                adapter.notifyDataSetChanged();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                        }
                                    });
                                }
                            }

                        } catch (Exception e) {

                        }

                    }

                    @Override
                    public void onFailure(Call<List<Banner1>> call, Throwable t) {
                        Log.e("response1", String.valueOf(t));
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {

        }
    }

    /*@SuppressLint("WrongConstant")
    public void callBannerApi() {
        try {
            this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient().create(BackpainAPIInterface.class);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("age", "")
                    .addFormDataPart("user_type", "")
                    .addFormDataPart("height", "")
                    .addFormDataPart("state_id", "")
                    .addFormDataPart("city_id", "")
                    .addFormDataPart("weight", "")
                    .addFormDataPart("language", "")
                    .addFormDataPart("country_id", tinyDB.getString(Constants.COUNTRYID_KEY))
                    .build();
            this.apiInterface.getBanners(requestBody).enqueue(new Callback<BannerData>() {
                @Override
                public void onResponse(@NotNull Call<BannerData> call, @NotNull Response<BannerData> response) {
                    try {
                        BannerData bannerData = (BannerData) response.body();
                        ArrayList<BannerData.Datalist> dataist = bannerData.dataist;

                        ArrayList<BannerData.Datalist> newList = new ArrayList<>();
                        for (int i = 0; i < dataist.size(); i++) {
                            BannerData.Datalist data = dataist.get(i);
                            if (tinyDB.getBoolean(Constants.PREMIUN_KEY)) {
                                if (!data.getDefault_type().equals("Ads")) {
                                    newList.add(data);
                                }
                            } else {
                                newList.add(data);
                            }
                        }

                        if (Slidelayout != null) {
                            Slidelayout.setVisibility(View.VISIBLE);
                        }
                        mSlideloader.setVisibility(View.GONE);
                        sliderView.setVisibility(View.VISIBLE);
                        final SliderAdapter adapter = new SliderAdapter(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Completion.this, homebannerdatalist1);
                        sliderView.setSliderAdapter(adapter);

                        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                        sliderView.setIndicatorSelectedColor(Color.WHITE);
                        sliderView.setIndicatorUnselectedColor(Color.GRAY);
                        sliderView.startAutoCycle();
                        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
                            @Override
                            public void onIndicatorClicked(int position) {
                                sliderView.setCurrentPagePosition(position);
                                try {
                                    adapter.notifyDataSetChanged();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(@NotNull Call<BannerData> call, @NotNull Throwable t) {
                    if (sliderView != null) {
                        sliderView.setVisibility(View.GONE);
                    }
                    if (Slidelayout != null) {
                        Slidelayout.setVisibility(View.GONE);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            if (sliderView != null) {
                sliderView.setVisibility(View.GONE);
            }
            if (Slidelayout != null) {
                Slidelayout.setVisibility(View.GONE);
            }
        }

    }*/
    @SuppressLint("WrongConstant")
    public void callStorebannerApi() {
        try {
            this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getAppstoreClient().create(BackpainAPIInterface.class);
            String bannerid = tinyDB.getString(Constants.Storebanner_id);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("banner_id", bannerid)
//                    .addFormDataPart("cat_id", "")
                    .build();
            this.apiInterface.getStorebanner(requestBody).enqueue(new Callback<StoreBannerData>() {
                @Override
                public void onResponse(@NotNull Call<StoreBannerData> call, @NotNull Response<StoreBannerData> response) {
                    try {
                        StoreBannerData storeBannerData = (StoreBannerData) response.body();
                        ArrayList<StoreBannerData.Datalist> dataist = storeBannerData.dataist;
                        storeBannerAdapter = new StoreBanner_Adapter(Activity_Completion.this, dataist);
                        sliderView.setSliderAdapter(storeBannerAdapter);
                        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                        sliderView.setIndicatorSelectedColor(Color.WHITE);
                        sliderView.setIndicatorUnselectedColor(Color.GRAY);
                        sliderView.startAutoCycle();
                        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
                            @Override
                            public void onIndicatorClicked(int position) {
                                sliderView.setCurrentPagePosition(position);
                                try {
                                    storeBannerAdapter.notifyDataSetChanged();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    } catch (Exception e) {
                        sliderView.setVisibility(View.GONE);
                        mTvrecomand.setVisibility(View.GONE);
                        mTempview.setVisibility(View.GONE);
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(@NotNull Call<StoreBannerData> call, @NotNull Throwable t) {
                    sliderView.setVisibility(View.GONE);
                    mTvrecomand.setVisibility(View.GONE);
                    mTempview.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            sliderView.setVisibility(View.GONE);
            mTvrecomand.setVisibility(View.GONE);
            mTempview.setVisibility(View.GONE);
        }
    }

    public void DialogRatexeperience(int time, int mLevel, String comtime) {
        final Dialog dialog = new Dialog(Activity_Completion.this);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_execomplete);
        TextView mTextexpe = (TextView) dialog.findViewById(R.id.txtdesx);
        TextView mTvpercentage = (TextView) dialog.findViewById(R.id.tvpercentage);
        final RoundCornerProgressBar mProgress = (RoundCornerProgressBar) dialog.findViewById(R.id.progress);
        mProgress.setProgress(0);
        CardView mBtnreminder = dialog.findViewById(R.id.btnreminder);
        TextView mSkipcount = dialog.findViewById(R.id.skipcounts);
        LottieAnimationView mLotti6 = dialog.findViewById(R.id.lotti6);
        TextView mTvperformance = dialog.findViewById(R.id.tvperformance);
        mLotti6.loop(true);
        LinearLayout mStarslayout = dialog.findViewById(R.id.starlayout);
        TextView txt_rat = dialog.findViewById(R.id.txt_rat);
      /*  LikeButton mStar1 = dialog.findViewById(R.id.ivstar1);
        LikeButton mStar2 = dialog.findViewById(R.id.ivstar2);
        LikeButton mStar3 = dialog.findViewById(R.id.ivstar3);
        LikeButton mStar4 = dialog.findViewById(R.id.ivstar4);
        LikeButton mStar5 = dialog.findViewById(R.id.ivstar5);*/

        RatingBar ratingBar = dialog.findViewById(R.id.rating_bar);

        ratingBar.setRating(5);
        mTextexpe.setText(getResources().getString(R.string.rate_txt));
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                temprate = (int) (rating - 1);
                Log.d("Rating", String.valueOf(temprate));
                //   smileyRating.setSmiley(temprate);
                switch (temprate) {
                    case 0:
                        //  mTextexpe.setText("Hated it");
                        break;
                    case 1:
                        // mTextexpe.setText("Disliked it!");
                        break;
                    case 2:
                        // mTextexpe.setText("It's Ok");
                        break;
                    case 3:
                        // mTextexpe.setText("Liked it!");
                        break;
                    case 4:
                        // mTextexpe.setText("Loved it");
                        break;
                }
            }
        });
        CardView mBtnrate = dialog.findViewById(R.id.btnrateus);

        if (!tinyDB.getBoolean(Constants.RATEDIALOGSHOW_KEY) /*&& time > 299*/) {
            mStarslayout.setVisibility(View.VISIBLE);
            mBtnrate.setVisibility(View.VISIBLE);
            txt_rat.setVisibility(View.VISIBLE);
        } else {
            mStarslayout.setVisibility(View.GONE);
            mBtnrate.setVisibility(View.GONE);
            txt_rat.setVisibility(View.GONE);
        }
     /*   mStar1.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                mStarcount++;
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                mStarcount--;
                Animation clickanim = AnimationUtils.loadAnimation(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Completion.this, R.anim.clickanim);
                mStar1.startAnimation(clickanim);
            }
        });
        mStar2.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                mStarcount++;
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                mStarcount--;
                Animation clickanim = AnimationUtils.loadAnimation(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Completion.this, R.anim.clickanim);
                mStar2.startAnimation(clickanim);
            }
        });
        mStar3.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                mStarcount++;
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                mStarcount--;
                Animation clickanim = AnimationUtils.loadAnimation(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Completion.this, R.anim.clickanim);
                mStar3.startAnimation(clickanim);
            }
        });
        mStar4.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                mStarcount++;
            }

            @Override
            public void unLiked(LikeButton likeButton) {
                mStarcount--;
                Animation clickanim = AnimationUtils.loadAnimation(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Completion.this, R.anim.clickanim);
                mStar4.startAnimation(clickanim);
            }
        });
        mStar5.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                mStarcount++;

            }

            @Override
            public void unLiked(LikeButton likeButton) {
                mStarcount--;
                Animation clickanim = AnimationUtils.loadAnimation(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Completion.this, R.anim.clickanim);
                mStar5.startAnimation(clickanim);
            }
        });*/

        if (mLevel < 3) {
            float Maxprogress = 6 * 60;
            float value = time * 100.00f / Maxprogress;
            mTextexpe.setText("Today Workout : " + comtime + "/06:00 min.");
            mTvpercentage.setText(round(value, 0) + "%");
            ProgressBarAnimation mProgressAnimation = new ProgressBarAnimation(mProgress, 1500);
            mProgressAnimation.setProgress((int) value);
        } else {
            float Maxprogress = (float) (7.5 * 60);
            float value = time * 100.00f / Maxprogress;
            mTextexpe.setText("Today Workout : " + comtime + "/09:00 min.");
            mTvpercentage.setText(round(value, 0) + "%");
            ProgressBarAnimation mProgressAnimation = new ProgressBarAnimation(mProgress, 1500);
            mProgressAnimation.setProgress((int) value);
        }

        Log.e("Exetime", String.valueOf(time));
        if (mLevel < 3) {
            if (time <= 72) {
                mLotti6.setAnimation("emojiterrible.json");
                mLotti6.playAnimation();
                mTvperformance.setText(getResources().getString(R.string.you_can_better));
                mTvperformance.setTextColor(getResources().getColor(R.color.tbtncolor));
                mProgress.setProgressColor(getResources().getColor(R.color.tbtncolor));
            } else if (time > 72 && time <= 144) {
                mLotti6.setAnimation("emojibad.json");
                mLotti6.playAnimation();
                mTvperformance.setText(getResources().getString(R.string.perform_better));
                mTvperformance.setTextColor(getResources().getColor(R.color.tbtncolor));
                mProgress.setProgressColor(getResources().getColor(R.color.tbtncolor));
            } else if (time > 144 && time <= 216) {
                mLotti6.setAnimation("emojiokey.json");
                mLotti6.playAnimation();
                mTvperformance.setText(getResources().getString(R.string.perform_okay));
                mTvperformance.setTextColor(getResources().getColor(R.color.tbtncolor));
                mProgress.setProgressColor(getResources().getColor(R.color.tbtncolor));
            } else if (time > 216 && time <= 288) {

                mLotti6.setAnimation("emojigood.json");
                mLotti6.playAnimation();
                mTvperformance.setText(getResources().getString(R.string.perform_good));
                mTvperformance.setTextColor(getResources().getColor(R.color.tbtncolor));
                mProgress.setProgressColor(getResources().getColor(R.color.tbtncolor));
            } else if (time > 288) {
                mLotti6.setAnimation("emojigreat.json");
                mLotti6.playAnimation();
                mTvperformance.setText(getResources().getString(R.string.perform_great));
                mTvperformance.setTextColor(getResources().getColor(R.color.tbtncolor));
                mProgress.setProgressColor(getResources().getColor(R.color.tbtncolor));
            }
        } else {
            if (time <= 108) {
                mLotti6.setAnimation("emojiterrible.json");
                mLotti6.playAnimation();
                mTvperformance.setText(getResources().getString(R.string.you_can_better));
                mTvperformance.setTextColor(getResources().getColor(R.color.tbtncolor));
                mProgress.setProgressColor(getResources().getColor(R.color.tbtncolor));
            } else if (time > 108 && time <= 216) {
                mLotti6.setAnimation("emojibad.json");
                mLotti6.playAnimation();
                mTvperformance.setText(getResources().getString(R.string.perform_better));
                mTvperformance.setTextColor(getResources().getColor(R.color.tbtncolor));
                mProgress.setProgressColor(getResources().getColor(R.color.tbtncolor));
            } else if (time > 216 && time <= 324) {
                mLotti6.setAnimation("emojiokey.json");
                mLotti6.playAnimation();
                mTvperformance.setText(getResources().getString(R.string.perform_okay));
                mTvperformance.setTextColor(getResources().getColor(R.color.tbtncolor));
                mProgress.setProgressColor(getResources().getColor(R.color.tbtncolor));
            } else if (time > 324 && time <= 432) {
                mLotti6.setAnimation("emojigood.json");
                mLotti6.playAnimation();
                mTvperformance.setText(getResources().getString(R.string.perform_good));
                mTvperformance.setTextColor(getResources().getColor(R.color.tbtncolor));
                mProgress.setProgressColor(getResources().getColor(R.color.tbtncolor));
            } else if (time > 432) {
                mLotti6.setAnimation("emojigreat.json");
                mLotti6.playAnimation();
                mTvperformance.setText(getResources().getString(R.string.perform_great));
                mTvperformance.setTextColor(getResources().getColor(R.color.tbtncolor));
                mProgress.setProgressColor(getResources().getColor(R.color.tbtncolor));
            }
        }

        mRemindertimer = new CountDownTimer(16000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mSkipcount.setText("(" + TimeFormatter(millisUntilFinished) + ")");
            }

            @Override
            public void onFinish() {
                dialog.dismiss();
                Intent intent = new Intent(Activity_Completion.this, ReminderMainActivity.class);
                intent.putExtra("isshow", true);
                startActivity(intent);
            }
        };
        mRemindertimer.start();

        mBtnrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temprate = (int) (ratingBar.getRating() - 1);

                if (mRemindertimer != null) {
                    mRemindertimer.cancel();
                }
                if (temprate == 4) {
                    tinyDB.putBoolean(Constants.RATEDIALOGSHOW_KEY, true);
                    dialog.dismiss();
                    Rateus();

                } else {
                    dialog.dismiss();
                    DialogFeedback();
                }
            }
        });
        mBtnreminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mRemindertimer != null) {
                    mRemindertimer.cancel();
                }
                dialog.dismiss();
                Intent intent = new Intent(Activity_Completion.this, ReminderMainActivity.class);
                intent.putExtra("isshow", true);
                startActivity(intent);
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (mRemindertimer != null) {
                    mRemindertimer.cancel();
                }
            }
        });
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    public void DialogFeedback() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Completion.this, R.style.MaterialThemeDialog);
        LayoutInflater inflater = LayoutInflater.from(Activity_Completion.this);
        View viewRoot = inflater.inflate(R.layout.content_feedbox, null);
        builder.setView(viewRoot);
        TextView mTxtother = (TextView) viewRoot.findViewById(R.id.txtothers);
        EditText mCustomfeed = (EditText) viewRoot.findViewById(R.id.customfeedback);
        mTxtother.setVisibility(View.GONE);
        mCustomfeed.setVisibility(View.VISIBLE);
        mTxtother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTxtother.setVisibility(View.GONE);
                mCustomfeed.setVisibility(View.VISIBLE);
                Animation mzoom = AnimationUtils.loadAnimation(Activity_Completion.this, R.anim.zoom_in);
                mCustomfeed.startAnimation(mzoom);
            }
        });
        boolean[] checkedItems = {false, false, false, false, false};
        builder.setTitle("Your Feedback is useful");
        builder.setMultiChoiceItems(feedbacklist, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i, boolean isChecked) {
                if (isChecked) {
                    mSelectedFeedbackslist.add(feedbacklist[i]);
                }
            }
        });
        builder.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                String mOp1;
                if (mSelectedFeedbackslist.size() != 0) {
                    mOp1 = mSelectedFeedbackslist.get(0);
                } else {
                    mOp1 = "";
                }
                String mOp2;
                if (mSelectedFeedbackslist.size() == 2) {
                    mOp2 = mSelectedFeedbackslist.get(1);
                } else {
                    mOp2 = "";
                }
                String mOp3;
                if (mSelectedFeedbackslist.size() == 3) {
                    mOp2 = mSelectedFeedbackslist.get(1);
                    mOp3 = mSelectedFeedbackslist.get(2);
                } else {
                    mOp3 = "";
                }
                String mOp4;
                if (mSelectedFeedbackslist.size() == 4) {
                    mOp2 = mSelectedFeedbackslist.get(1);
                    mOp3 = mSelectedFeedbackslist.get(2);
                    mOp4 = mSelectedFeedbackslist.get(3);
                } else {
                    mOp4 = "";
                }
                String mOp5;
                if (mSelectedFeedbackslist.size() == 5) {
                    mOp2 = mSelectedFeedbackslist.get(1);
                    mOp3 = mSelectedFeedbackslist.get(2);
                    mOp4 = mSelectedFeedbackslist.get(3);
                    mOp5 = mSelectedFeedbackslist.get(4);
                } else {
                    mOp5 = "";
                }
                String mUid = tinyDB.getString(Constants.USERID_KEY);
                String mComment = mCustomfeed.getText().toString();
                // callFeedbackApi(mOp1, mOp2, mOp3, mOp4, mOp5, mComment, mUid);

                String device_name = Build.BRAND + ", " + Build.DEVICE + ", " + Build.MANUFACTURER + ", " + BuildConfig.VERSION_NAME;
                  String androidId = 22+Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID)+2;
                String email = tinyDB.getString(Constants.USEREMAIL_KEY);
                String release = Build.VERSION.RELEASE;
                callFeedbackApi(androidId, device_name, release, email, mOp1, mOp2, mOp3, mOp4, mOp5, mComment/*, mComment, mUid*/);
            }
        });
        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Constants.isRatedialShown = true;
                dialog.dismiss();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Constants.isRatedialShown = true;
            }
        });
        feedbackdial = builder.create();
        feedbackdial.show();
    }


  /*  @SuppressLint("WrongConstant")
    public void callFeedbackApi(String option1, String option2, String option3,
                                String option4, String others, String comments, String user_id) {
        try {
            this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient().create(BackpainAPIInterface.class);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("option1", option1)
                    .addFormDataPart("option2", option2)
                    .addFormDataPart("option3", option3)
                    .addFormDataPart("option4", option4)
                    .addFormDataPart("other", others)
                    .addFormDataPart("comment", comments)
                    .addFormDataPart("user_id", user_id)
                    .build();
            this.apiInterface.sendRetings(requestBody).enqueue(new Callback<String>() {
                @Override
                public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {
                    try {
                        String status = (String) response.body();
                        tinyDB.putBoolean(Constants.RATEDIALOGSHOW_KEY, true);
                        Toast.makeText(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Completion.this, "Feedback Submitted", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {

                    }
                }

                @Override
                public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    public void callFeedbackApi(String androidId, String device_name, String release, String email, String option1, String option2, String option3,
                                String option4, String option5, String comments/*, String user_id*/) {

        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            ApiInterface apiInterface = retrofit.create(ApiInterface.class);
            JSONObject paramObject = new JSONObject();
            paramObject.put("device_id", androidId);
            paramObject.put("app", "44");
            paramObject.put("user_email", email);
            paramObject.put("device_name", device_name);
            paramObject.put("android_version", release);
            paramObject.put("option1", option1);
            paramObject.put("option2", option2);
            paramObject.put("option3", option3);
            paramObject.put("option4", option4);
            paramObject.put("option5", option5);
            paramObject.put("comment", comments);
            Call<RateModel> userCall = apiInterface.getrating(paramObject.toString());
            userCall.enqueue(new Callback<RateModel>() {
                @Override
                public void onResponse(Call<RateModel> call, Response<RateModel> response) {
                    try {
                        //  Log.e("response", String.valueOf(response.body()));
                        RateModel rateModel = response.body();
                        // Log.e("rates", rateModel.getOption1());
                  /*  DietData loginData = response.body();
                    Log.e("BMR", String.valueOf(loginData.bmr));
                    Log.e("w25", String.valueOf(loginData.w25));
                    Log.e("w5", String.valueOf(loginData.w5));
                    Log.e("w1", String.valueOf(loginData.w1));
                    int bmr = loginData.bmr;
                    int w25 = loginData.w25;
                    int w5 = loginData.w5;
                    int w1 = loginData.w1;
                    tinyDB.putInt(Constants.BMR, bmr);
                    tinyDB.putInt(Constants.W25, w25);
                    tinyDB.putInt(Constants.W5, w5);
                    tinyDB.putInt(Constants.W1, w1);*/

                    } catch (Exception e) {
                    }
                }

                @Override
                public void onFailure(Call<RateModel> call, Throwable t) {
                    // Log.e("response", String.valueOf("response"));
                }
            });
        } catch (Exception e) {

        }
    }

    public void Rateus() {
        Uri uri1 = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri1);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(Activity_Completion.this, "You don't have Google Play installed", Toast.LENGTH_LONG).show();
        }

    }


    public static float round(float d, int decimalPlace) {
        return BigDecimal.valueOf(d).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    @SuppressLint("DefaultLocale")
    private String TimeFormatter(long milliSeconds) {
        String time = String.format("%02d", TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));
        return time;
    }

    private String hmsTimeFormatter(long milliSeconds) {
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(milliSeconds), TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));
    }


    public void DialogCalculatebmi() {
        final Dialog dialog = new Dialog(Activity_Completion.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_bmidata);
        NumberPicker mCmpicker = (NumberPicker) dialog.findViewById(R.id.cmpicker);
        NumberPicker mFtpicker = (NumberPicker) dialog.findViewById(R.id.ftpicker);
        NumberPicker mInchpicker = (NumberPicker) dialog.findViewById(R.id.inchpicker);
        LinearLayout mSwitchcminch = (LinearLayout) dialog.findViewById(R.id.switchcminch);
        final TextView mBtcm = (TextView) mSwitchcminch.findViewById(R.id.btcm);
        final TextView mBtinch = (TextView) mSwitchcminch.findViewById(R.id.btinch);
        LinearLayout mCmlayout = (LinearLayout) dialog.findViewById(R.id.cmlayout);
        LinearLayout mInchlayout = (LinearLayout) dialog.findViewById(R.id.inchlayout);
        NumberPicker mKgpicker = (NumberPicker) dialog.findViewById(R.id.kgpicker);
        NumberPicker mLbspicker = (NumberPicker) dialog.findViewById(R.id.lbsbpicker);
        LinearLayout mLaykglb = (LinearLayout) dialog.findViewById(R.id.laykglb);
        final TextView mBtkg = (TextView) mLaykglb.findViewById(R.id.btkgs);
        final TextView mBtlbs = (TextView) mLaykglb.findViewById(R.id.btlbs);
        LinearLayout mKgslayout = (LinearLayout) dialog.findViewById(R.id.laykgs);
        LinearLayout mLbsslayout = (LinearLayout) dialog.findViewById(R.id.laylbs);
        CardView mBtcalculate = (CardView) dialog.findViewById(R.id.calculate);

        mCmpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mHcmvalue = newVal;
            }
        });


        mFtpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mHftvalue = newVal;
            }
        });


        mInchpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mHinchvalue = newVal;
            }
        });

        if (isbtncm) {
            mSwitchcminchprevious();
            mSwitchcminchselected(mBtcm);
            mCmlayout.setVisibility(View.VISIBLE);
            mInchlayout.setVisibility(View.GONE);
            mCmpicker.setValue(mHcmvalue);
        } else {
            mSwitchcminchprevious();
            mSwitchcminchselected(mBtinch);
            mCmlayout.setVisibility(View.GONE);
            mInchlayout.setVisibility(View.VISIBLE);
            mFtpicker.setValue(mHftvalue);
            mInchpicker.setValue(mHinchvalue);
        }

        mBtcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isbtninch = false;
                isbtncm = true;
                mSwitchcminchprevious();
                mSwitchcminchselected(mBtcm);
                mCmlayout.setVisibility(View.VISIBLE);
                mInchlayout.setVisibility(View.GONE);
            }
        });

        mBtinch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isbtncm = false;
                isbtninch = true;
                mSwitchcminchprevious();
                mSwitchcminchselected(mBtinch);
                mCmlayout.setVisibility(View.GONE);
                mInchlayout.setVisibility(View.VISIBLE);
            }
        });


        mKgpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mWkgvalue = newVal;
            }
        });

        mLbspicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mWlbvalue = newVal;
            }
        });

        if (isBtnkg) {
            unboxPreviousSelectedPageTab();
            boxNewSelectedPageTab(mBtkg);
            mKgslayout.setVisibility(View.VISIBLE);
            mLbsslayout.setVisibility(View.GONE);
            mKgpicker.setValue(mWkgvalue);
        } else {
            unboxPreviousSelectedPageTab();
            boxNewSelectedPageTab(mBtlbs);
            mKgslayout.setVisibility(View.GONE);
            mLbsslayout.setVisibility(View.VISIBLE);
            mLbspicker.setValue(mWlbvalue);
        }

        mBtkg.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                isBtnLb = false;
                isBtnkg = true;
                unboxPreviousSelectedPageTab();
                boxNewSelectedPageTab(mBtkg);
                mKgslayout.setVisibility(View.VISIBLE);
                mLbsslayout.setVisibility(View.GONE);
            }
        });
        mBtlbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isBtnkg = false;
                isBtnLb = true;
                unboxPreviousSelectedPageTab();
                boxNewSelectedPageTab(mBtlbs);
                mKgslayout.setVisibility(View.GONE);
                mLbsslayout.setVisibility(View.VISIBLE);
            }
        });


        mBtcalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isbtncm) {
                    String doubleAsString = String.valueOf(round(Float.parseFloat(cmtofeet(mHcmvalue)), 1));
                    int indexOfDecimal = doubleAsString.indexOf(".");
                    mHftvalue = Integer.parseInt(doubleAsString.substring(0, indexOfDecimal));
                    String mTrim = doubleAsString.substring(indexOfDecimal).replace(".", "");
                    mHinchvalue = Integer.parseInt(mTrim);
                }
                if (!isBtnkg) {
                    if (isBtnLb) {
                        float b = (float) calculateBMI(calculateMetres(mHftvalue, mHinchvalue), calculateweight(mWlbvalue));
                        b = round(b, 2);
                        tinyDB.putFloat(Constants.BMI_KEY, b);
                        if (tinyDB.getFloat(Constants.BMI_KEY) != 0) {
                            mBmiscalelayout.setVisibility(View.VISIBLE);
                            mTxtbmi.setText(String.valueOf(b) + " KG/M²");
                            initDataToSeekbar();
                        } else {
                            mBmiscalelayout.setVisibility(View.GONE);
                        }
                    }
                } else {
                    float b2 = calculateBMI(calculateMetres(mHftvalue, mHinchvalue), calculateweight(mWkgvalue));
                    b2 = round(b2, 2);
                    tinyDB.putFloat(Constants.BMI_KEY, (float) b2);
                    if (tinyDB.getFloat(Constants.BMI_KEY) != 0) {
                        mBmiscalelayout.setVisibility(View.VISIBLE);
                        mTxtbmi.setText(String.valueOf(b2) + " KG/M²");
                        initDataToSeekbar();
                    } else {
                        mBmiscalelayout.setVisibility(View.GONE);
                    }
                }
                if (isbtncm && mHcmvalue != 0) {
                    tinyDB.putBoolean(Constants.ISCM_KEY, true);
                    tinyDB.putFloat(Constants.HEIGHT_KEY, mHcmvalue);
                    //  mTvheight.setText(mHcmvalue + " cm");
                } else if (isbtninch && mHftvalue != 0) {
                    tinyDB.putBoolean(Constants.ISCM_KEY, false);
                    String temp = mHftvalue + "." + mHinchvalue;
                    tinyDB.putFloat(Constants.HEIGHT_KEY, Float.parseFloat(temp));
                    // mTvheight.setText(temp + " ft");
                }
                if (isBtnkg && mWkgvalue != 0) {
                    tinyDB.putBoolean(Constants.ISKG_KEY, true);
                    tinyDB.putInt(Constants.WEIGHT_KEY, mWkgvalue);
                    ///  mTvweight.setText(mWkgvalue + " kg");
                } else if (isBtnLb && mWlbvalue != 0) {
                    tinyDB.putBoolean(Constants.ISKG_KEY, false);
                    tinyDB.putInt(Constants.WEIGHT_KEY, mWlbvalue);
                    // mTvweight.setText(mWlbvalue + " lb");
                }
                dialog.dismiss();
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }


    private int getRandomNumberInRange(int min, int max) {

        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }


    public String cmtofeet(int val) {
        String feetval;
        float mCmval = val;
        float feet = (float) (mCmval / 30.48);
        feetval = String.valueOf(feet);
        return feetval;
    }

    public int calculateBMI(float f, float f2) {
        return (int) (f2 / (f * f));
    }

    private float calculateHeightinCentimeter(float f) {
        return (float) ((int) (f * 100.0f));
    }

    public float calculateMetres(float f, float f2) {
        float f3 = (float) (((double) (f + (f2 / 12.0f))) / 3.28d);
        this.Heightincms = calculateHeightinCentimeter(f3);
        return f3;
    }

    public float calculateweight(float f) {
        return isBtnLb ? (float) (((double) f) * 0.453592d) : f;
    }


    private void mSwitchcminchprevious() {
        if (mSwich2txt != null) {
            mSwich2txt.setBackground(null);
            mSwich2txt.setTextColor(Color.BLACK);
            mSwich2txt = null;
        }
    }

    private void mSwitchcminchselected(TextView selected) {
        mSwich2txt = selected;
        mSwich2txt.setBackground(getResources().getDrawable(R.drawable.switchselectedbg));
        mSwich2txt.setTextColor(Color.WHITE);
    }

    private void unboxPreviousSelectedPageTab() {
        if (pageSelected != null) {
            pageSelected.setBackground(null);
            pageSelected.setTextColor(Color.BLACK);
            pageSelected = null;
        }
    }

    private void boxNewSelectedPageTab(TextView selected) {
        pageSelected = selected;
        pageSelected.setBackground(getResources().getDrawable(R.drawable.switchselectedbg));
        pageSelected.setTextColor(Color.WHITE);
    }

    private void initDataToSeekbar() {
        float floatExtra;
        Resources resources = null;
        int i = 0;
        TextView textView2 = null;
        String str = null;
        this.bmiseekBar.setEnabled(true);
        ArrayList<ProgressItem> progressItemList = new ArrayList<>();
        ProgressItem mProgressItem = new ProgressItem();
        float bmitotalSpan = 50.0f;
        float bmivioletSpan = 15.0f;
        mProgressItem.progressItemPercentage = (bmivioletSpan / bmitotalSpan) * 100.0f;
        mProgressItem.color = R.color.violet;
        progressItemList.add(mProgressItem);
        mProgressItem = new ProgressItem();
        float bmiblueSpan = 3.0f;
        mProgressItem.progressItemPercentage = ((float) (bmiblueSpan / ((double) bmitotalSpan))) * 100.0f;
        mProgressItem.color = R.color.blue;
        progressItemList.add(mProgressItem);
        mProgressItem = new ProgressItem();
        float bmigreenSpan = 7.0f;
        mProgressItem.progressItemPercentage = ((float) (bmigreenSpan / ((double) bmitotalSpan))) * 100.0f;
        mProgressItem.color = R.color.green;
        progressItemList.add(mProgressItem);
        mProgressItem = new ProgressItem();
        float bmiyellowSpan = 5.0f;
        mProgressItem.progressItemPercentage = (bmiyellowSpan / bmitotalSpan) * 100.0f;
        mProgressItem.color = R.color.yellow;
        progressItemList.add(mProgressItem);
        mProgressItem = new ProgressItem();
        float bmiorangeSpan = 10.0f;
        mProgressItem.progressItemPercentage = (bmiorangeSpan / bmitotalSpan) * 100.0f;
        mProgressItem.color = R.color.orange;
        progressItemList.add(mProgressItem);
        mProgressItem = new ProgressItem();
        float bmiredSpan = 10.0f;
        mProgressItem.progressItemPercentage = (bmiredSpan / bmitotalSpan) * 100.0f;
        mProgressItem.color = R.color.red;
        progressItemList.add(mProgressItem);
        this.bmiseekBar.initData(progressItemList);
        float floatExtra2 = tinyDB.getFloat(Constants.BMI_KEY);
        this.bmiseekBar.setProgress((int) floatExtra2);
        this.wght4.setText(String.valueOf(floatExtra2));
        if (floatExtra2 >= 0.0f && floatExtra2 <= 15.0f) {
            textView2 = this.wght2;
            str = "Severely Underweight";
        } else if (floatExtra2 >= 16.0f && floatExtra2 <= 18.9f) {
            textView2 = this.wght2;
            str = "Underweight";
        } else if (floatExtra2 >= 19.0f && floatExtra2 <= 25.0f) {
            textView2 = this.wght2;
            str = "Normal (Healthy Weight)";
        } else if (floatExtra2 >= 25.1f && floatExtra2 <= 30.0f) {
            textView2 = this.wght2;
            str = "Overweight";
        } else if (floatExtra2 >= 30.1f || floatExtra2 <= 40.0f) {

            textView2 = this.wght2;
            str = "Obese";
        } else if (floatExtra2 >= 40.1f && floatExtra2 <= 50.0f) {
            textView2 = this.wght2;
            str = "Severely Obese";
        }
        try {
            textView2.setText(str);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(Activity_Completion.this, "Please Add Valid Data", Toast.LENGTH_SHORT).show();
        }
        this.bmiseekBar.invalidate();
        this.bmiseekBar.setEnabled(false);
    }


    @Override
    public void onBackPressed() {
        boolean diet = tinyDB.getBoolean(Constants.ReadyDietPlan);
        if (diet) {
            Intent intent1 = new Intent(Activity_Completion.this, DefalutAndCustomplanActivity.class);
            intent1.putExtra("isFrom2", false);
            startActivity(intent1);
            finish();
        } else {
            Intent intent = new Intent(Activity_Completion.this, CustomUpdateActivity.class);
            intent.putExtra("isFrom", false);
            startActivity(intent);
            finish();
        }
       /* if (!isclicked) {
            isclicked = true;
            if (mIsActive) {

                Intent intent = new Intent(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Completion.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Bundle data1 = new Bundle();
                data1.putInt("discover",4);
                intent.putExtras(data1);
                intent.putExtra("isFrom3", true);
                startActivity(intent);
                finish();
              *//*  Intent intent = new Intent(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Completion.this, Activity_Bloglists.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("isFrom3", true);
                startActivity(intent);
                finish();*//*
            } else {
                Intent intent = new Intent(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Completion.this, Activity_MyTraining.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        }*/
    }


    public void showGOOGLEAdvance(final Context context, final LinearLayout frameLayout) {
        AdLoader.Builder builder = new AdLoader.Builder(context, Constants.admob_nativead);
        builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
            public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                mAdcard1.setVisibility(View.VISIBLE);
                frameLayout.setVisibility(View.VISIBLE);
                NativeContentAdView nativeContentAdView = (NativeContentAdView) ((Activity) context).getLayoutInflater().inflate(R.layout.ad_content, null);
                populateContentAdView(nativeContentAd, nativeContentAdView);
                frameLayout.removeAllViews();
                frameLayout.addView(nativeContentAdView);
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(int i) {
                mAdcard1.setVisibility(View.GONE);
                Log.e("error", "Failed to load native ad:: " + i);
            }
        }).build().loadAd(new AdRequest.Builder().build());
    }

    @SuppressLint("WrongConstant")
    public static void populateContentAdView(NativeContentAd nativeContentAd, NativeContentAdView nativeContentAdView) {
        nativeContentAdView.setHeadlineView(nativeContentAdView.findViewById(R.id.ad_title_textview));
        nativeContentAdView.setImageView(nativeContentAdView.findViewById(R.id.contentad_image));
        nativeContentAdView.setBodyView(nativeContentAdView.findViewById(R.id.ad_describe_textview));
        nativeContentAdView.setCallToActionView(nativeContentAdView.findViewById(R.id.ad_action_button));
        nativeContentAdView.setLogoView(nativeContentAdView.findViewById(R.id.ad_icon_imageview));

//        nativeContentAdView.setAdvertiserView(nativeContentAdView.findViewById(R.id.contentad_advertiser));
        ((TextView) nativeContentAdView.getHeadlineView()).setText(nativeContentAd.getHeadline());
        ((TextView) nativeContentAdView.getBodyView()).setText(nativeContentAd.getBody());
        ((TextView) nativeContentAdView.getCallToActionView()).setText(nativeContentAd.getCallToAction());
//        ((TextView) nativeContentAdView.getAdvertiserView()).setText(nativeContentAd.getAdvertiser());
        List images = nativeContentAd.getImages();
        if (images.size() > 0) {
            ((ImageView) nativeContentAdView.getImageView()).setImageDrawable(((com.google.android.gms.ads.formats.NativeAd.Image) images.get(0)).getDrawable());
           /* try {
                mBackimg.setImageDrawable(((com.google.android.gms.ads.formats.NativeAd.Image) images.get(0)).getDrawable());
            } catch (Exception e) {
                e.printStackTrace();
            }*/

        }
        com.google.android.gms.ads.formats.NativeAd.Image logo = nativeContentAd.getLogo();
        if (logo == null) {
            nativeContentAdView.getLogoView().setVisibility(4);
        } else {
            ((ImageView) nativeContentAdView.getLogoView()).setImageDrawable(logo.getDrawable());
            nativeContentAdView.getLogoView().setVisibility(0);
        }
        nativeContentAdView.setNativeAd(nativeContentAd);
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
                frameLayout.setVisibility(View.GONE);
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


    private File saveBitMap(Context context, View drawView) {
        File pictureFileDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "Handcare");
        if (!pictureFileDir.exists()) {
            boolean isDirectoryCreated = pictureFileDir.mkdirs();
            if (!isDirectoryCreated)
                Log.i("ATG", "Can't create directory to save the image");
            return null;
        }
        String filename = pictureFileDir.getPath() + File.separator + "Tempshare.jpg";
        File pictureFile = new File(filename);
        Bitmap bitmap = getBitmapFromView(drawView);
        try {
            pictureFile.createNewFile();
            FileOutputStream oStream = new FileOutputStream(pictureFile);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, oStream);
            oStream.flush();
            oStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("TAG", "There was an issue saving the image.");
        }
        scanGallery(context, pictureFile.getAbsolutePath());
        return pictureFile;
    }

    private Bitmap getBitmapFromView(View view) {
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null) {
            bgDrawable.draw(canvas);
        } else {
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }

    private void scanGallery(Context cntx, String path) {
        try {
            MediaScannerConnection.scanFile(cntx, new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                public void onScanCompleted(String path, Uri uri) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        isclicked = false;
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (ratehandler != null) {
            ratehandler.removeCallbacks(mRaterunnable);
        }
    }

    @Override
    protected void onDestroy() {
        if (mRemindertimer != null) {
            mRemindertimer.cancel();
        }
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (storeBannerAdapter != null) {
            storeBannerAdapter.notifyDataSetChanged();
        }
        String time = tinyDB.getString("alarmtime");
        if (time != null && !time.equals("")) {
            mTxtreminder.setText(time);
            mTxtreminder.setVisibility(View.VISIBLE);
        } else {
            mTxtreminder.setVisibility(View.GONE);
        }
        super.onResume();
    }
}
