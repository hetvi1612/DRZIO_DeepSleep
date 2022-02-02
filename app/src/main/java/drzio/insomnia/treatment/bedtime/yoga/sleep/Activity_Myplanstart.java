package drzio.insomnia.treatment.bedtime.yoga.sleep;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;

import java.io.Serializable;
import java.util.ArrayList;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Addexercise;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_MyPlanExercisestart;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_MyTraining;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter.MyPlansAdapter;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Allexercises;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Daymodals;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Activity_Myplanstart extends AppCompatActivity {
    ArrayList<Allexercises> senddata = new ArrayList<>();
    private String planname;
    ArrayList<Daymodals> plandata = new ArrayList<>();
    private InterstitialAd mInterstitialAdMob;
    private CardView mCardStart;
    private TinyDB tinyDB;

    ArrayList<Allexercises> tempdata = new ArrayList<>();
    private View shine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        tinyDB = new TinyDB(Activity_Myplanstart.this);
        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(this, languages);

        setContentView(R.layout.activity_myplanstart);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            planname = bundle.getString("pname");
            senddata = (ArrayList<Allexercises>) bundle.getSerializable("myplalist");
        }
        initAdmobFullAd(Activity_Myplanstart.this);
        loadAdmobAd();
        LinearLayout mGbannerlay = findViewById(R.id.adframe);
        mGbannerlay.setVisibility(View.GONE);
        Smallnative(this, mGbannerlay);
        RecyclerView mMyplanExcercycler = (RecyclerView) findViewById(R.id.myplanrecycler);
        mCardStart = (CardView) findViewById(R.id.btnstart);
        TextView mTotaltime = (TextView) findViewById(R.id.tvptime);
        TextView mPtotalexe = (TextView) findViewById(R.id.tvptotalexe);
        TextView mTitle = (TextView) findViewById(R.id.tvtitle);
        ImageView mBack = (ImageView) findViewById(R.id.img_back);
        TextView mBtnedit = (TextView) findViewById(R.id.btnedit);
        shine=findViewById(R.id.shine);
        shineAnimation();
        mBtnedit.setVisibility(View.VISIBLE);
        mBtnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.mUpdatesenddata.clear();
                Intent intent = new Intent(Activity_Myplanstart.this, Activity_Addexercise.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("pname", planname);
                startActivity(intent);
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mTitle.setText(planname);
        mMyplanExcercycler.setLayoutManager(new LinearLayoutManager(Activity_Myplanstart.this, LinearLayoutManager.VERTICAL, false));
        if (senddata != null && senddata.size() != 0) {
            mTotaltime.setText(totaltime());
            String total = senddata.size() + " exercises";
            mPtotalexe.setText(total);
            MyPlansAdapter myPlansAdapter = new MyPlansAdapter(Activity_Myplanstart.this, senddata, mPtotalexe);
            mMyplanExcercycler.setAdapter(myPlansAdapter);
        }
        assert senddata != null;
    }

    private void shineAnimation() {
        Animation anim = AnimationUtils.loadAnimation(Activity_Myplanstart.this, R.anim.left_right);
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


    public void initAdmobFullAd(Context context) {
        if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
            return;
        }
        mInterstitialAdMob = new InterstitialAd(context);
        mInterstitialAdMob.setAdUnitId(Constants.admob_Interstitial);
        mInterstitialAdMob.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                if (Constants.mUpdatesenddata.size() != 0) {
                    tempdata = Constants.mUpdatesenddata;
                } else {
                    tempdata = senddata;
                }
                for (int i = 0; i < tempdata.size(); i++) {
                    Daymodals daymodals = new Daymodals();
                    daymodals.setExecercisename(tempdata.get(i).getExname());
                    daymodals.setExecerciseduration(mTimeinseconds(i, tempdata));
                    daymodals.setExecerciseimaga(tempdata.get(i).getEximglink());
                    daymodals.setExethumbs(tempdata.get(i).getExethumb());
                    daymodals.setExedescription(tempdata.get(i).getExdescr());
                    daymodals.setYouvideo(tempdata.get(i).getVideolink());
                    daymodals.setCalorie("0.12");
                    plandata.add(daymodals);
                }
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("listexecercise", (Serializable) plandata);
                Constants.mUpdatesenddata.clear();
                Intent intent = new Intent(Activity_Myplanstart.this, Activity_MyPlanExercisestart.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("day", planname);
                intent.putExtras(bundle1);
                startActivity(intent);
                finish();
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Activity_Myplanstart.this, Activity_MyTraining.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onResume() {
//        senddata = Constants.mUpdatesenddata;
        UpdateData();
        super.onResume();
    }

    public void UpdateData() {
        mCardStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
                    mInterstitialAdMob.show();
                } else {
                    if (Constants.mUpdatesenddata.size() != 0) {
                        tempdata = Constants.mUpdatesenddata;
                    } else {
                        tempdata = senddata;
                    }
                    for (int i = 0; i < tempdata.size(); i++) {
                        Daymodals daymodals = new Daymodals();
                        daymodals.setExecercisename(tempdata.get(i).getExname());
                        daymodals.setExecerciseduration(mTimeinseconds(i, tempdata));
                        daymodals.setExecerciseimaga(tempdata.get(i).getEximglink());
                        daymodals.setExedescription(tempdata.get(i).getExdescr());
                        daymodals.setYouvideo(tempdata.get(i).getVideolink());
                        daymodals.setCalorie("0.12");
                        plandata.add(daymodals);
                    }
                    Bundle bundle1 = new Bundle();
                    bundle1.putSerializable("listexecercise", (Serializable) plandata);
                    Constants.mUpdatesenddata.clear();
                    Intent intent = new Intent(Activity_Myplanstart.this, Activity_MyPlanExercisestart.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    intent.putExtra("day", planname);
                    intent.putExtras(bundle1);
                    startActivity(intent);
                    finish();

                }
            }
        });
    }

    public String mTimeinseconds(int pos, ArrayList<Allexercises> datalist) {
        String time;
        int times = datalist.get(pos).getExtime();
        times = times / 1000;
        time = String.valueOf(times);
        return time;
    }

    public String totaltime() {
        int mTotal = 0;
        for (int i = 0; i < senddata.size(); i++) {
            mTotal = mTotal + senddata.get(i).getExtime();
        }
        int time = mTotal / 60000;
        String temp;
        if (time == 0 || time == 1) {
            temp = " min";
        } else {
            temp = " mins";
        }
        return time + temp;
    }
}
