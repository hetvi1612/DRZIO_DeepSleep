package drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Apiclients;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.AppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.ChooseGenderActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.MaleAppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.ListItem;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.adapter.Adapter_Userdietnew;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.database.DietplanDbhelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.Header;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.User_Dietlist;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.isDieatBanner3;


public class Activity_Userdietlist extends AppCompatActivity {
    private RecyclerView mDietrecycler;
    private DietplanDbhelper dietplanDbhelper;
    ArrayList<User_Dietlist> userDietlists = new ArrayList<>();
    ArrayList<User_Dietlist> userDietlists2 = new ArrayList<>();
    private TinyDB tinydb;

    public List<Header> mDates = new ArrayList<>();
    private String tempdate = "zxcdsdss";
    private ArrayList<ListItem> items = new ArrayList<>();
    private ArrayList<ListItem> tempitems = new ArrayList<>();
    private String tempdate2 = "zxcdsdss";
    private boolean isbottom;
    private ImageView mBtnback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tinydb = new TinyDB(this);

        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(this, languages);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_userdietlist);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isbottom = bundle.getBoolean("isFrom2");
        }
        mBtnback = (ImageView) findViewById(R.id.btnback);
        mBtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        try {
            Apiclients.mTemplist.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ShimmerFrameLayout mLoadlay = findViewById(R.id.shimmer_container);
        LinearLayout mGbannerlay = findViewById(R.id.adframe);
        FrameLayout mAdframe4 = (FrameLayout) findViewById(R.id.adframe4);

        if (!tinydb.getBoolean(Constants.PREMIUN_KEY)) {
        if (isDieatBanner3) {
            mGbannerlay.setVisibility(View.GONE);
            showBanner(Activity_Userdietlist.this, mAdframe4, mLoadlay);
        } else {
            mAdframe4.setVisibility(View.GONE);
            Smallnative(this, mGbannerlay);
        }
        }else {
            mLoadlay.setVisibility(View.GONE);
        }
        dietplanDbhelper = new DietplanDbhelper(Activity_Userdietlist.this);
        mDietrecycler = (RecyclerView) findViewById(R.id.dietrecycler);
        mDietrecycler.setLayoutManager(new LinearLayoutManager(Activity_Userdietlist.this, LinearLayoutManager.VERTICAL, true));
        userDietlists.addAll(dietplanDbhelper.getAllDaysProgress());

        ImageView mBtnstore = (ImageView) findViewById(R.id.ivbtnstore);
        mBtnstore.setBackgroundResource(R.drawable.adsstoreanim);
        AnimationDrawable anim2 = (AnimationDrawable) mBtnstore.getBackground();
        anim2.start();
        mBtnstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cgender = tinydb.getBoolean(Constants.Genderchoose);
                if (cgender) {
                    if (tinydb.getString(Constants.GENDER_KEY).equals(getResources().getString(R.string.female))) {
                        Intent intent = new Intent(Activity_Userdietlist.this, AppstoreActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(Activity_Userdietlist.this, MaleAppstoreActivity.class);
                        startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(Activity_Userdietlist.this, ChooseGenderActivity.class);
                    startActivity(intent);
                }
            }
        });

        for (int i = 0; i < userDietlists.size(); i++) {
            if (!tempdate.equals(userDietlists.get(i).getDate())) {
                tempdate = userDietlists.get(i).getDate();
                Header header = new Header(userDietlists.get(i).getDate());
                mDates.add(header);
            }

        }
        for (int i = 0; i < mDates.size(); i++) {
            tempdate2 = mDates.get(i).getName();
            userDietlists2.clear();
            for (int i2 = 0; i2 < userDietlists.size(); i2++) {
                if (tempdate2.equals(userDietlists.get(i2).getDate())) {
                    User_Dietlist data = new User_Dietlist();
                    data.setDate(userDietlists.get(i2).getDate());
                    data.setId(userDietlists.get(i2).getId());
                    data.setName(userDietlists.get(i2).getName());
                    data.setImage(userDietlists.get(i2).getImage());
                    data.setCategory_id(userDietlists.get(i2).getCategory_id());
                    data.setDescription(userDietlists.get(i2).getDescription());
                    userDietlists2.add(data);
                }
            }
            tempitems.addAll(userDietlists2);
            tempitems.add(mDates.get(i));
        }
        items.addAll(tempitems);
        Adapter_Userdietnew adapter_userdiet = new Adapter_Userdietnew(Activity_Userdietlist.this, items, isbottom, userDietlists);
        mDietrecycler.setAdapter(adapter_userdiet);
        mDietrecycler.scrollToPosition(adapter_userdiet.getItemCount() - 1);
    }


    public void showBanner(Context context, final FrameLayout adMobView, ShimmerFrameLayout mloadlayout) {
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
                    adMobView.setVisibility(View.VISIBLE);
                    mloadlayout.setVisibility(View.GONE);
                    isDieatBanner3 = false;
                }

                @Override
                public void onAdFailedToLoad(int i) {
                    mloadlayout.setVisibility(View.GONE);
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


    public static void Smallnative(final Context context, final LinearLayout frameLayout) {
        AdLoader.Builder builder = new AdLoader.Builder(context, Constants.admob_nativead);
        builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
            public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                frameLayout.setVisibility(View.VISIBLE);
                NativeContentAdView nativeContentAdView = (NativeContentAdView) ((Activity) context).getLayoutInflater().inflate(R.layout.ad_native_banner, null);
                populateSmallContentAdView(nativeContentAd, nativeContentAdView);
                frameLayout.removeAllViews();
                frameLayout.addView(nativeContentAdView);
                isDieatBanner3 = true;
//                Animation mzoom = AnimationUtils.loadAnimation(context, R.anim.adzoom_in);
//                frameLayout.startAnimation(mzoom);
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


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Activity_Userdietlist.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
