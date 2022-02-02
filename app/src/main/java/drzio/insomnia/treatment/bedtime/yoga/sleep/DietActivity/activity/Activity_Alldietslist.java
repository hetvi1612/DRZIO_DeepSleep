package drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import dev.uchitel.eventex.UIEvent;
import dev.uchitel.eventex.UIEventListener;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Apiclients;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.ChooseGenderActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.adapter.Adapter_Alldiets;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.database.DietplanDbhelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.DietCateData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.DietitemsData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.Selecteditems;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIClient;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.isDieatBanner2;

public class Activity_Alldietslist extends AppCompatActivity implements UIEventListener {
    private String isFrom;
    public DietplanDbhelper dietplanDbhelper;
    private RelativeLayout mNovidLay;
    private Button mRefreshbtn;
    private TextView mBtndone;
    private BackpainAPIInterface apiInterface;
    private int index;
    private RecyclerView mCategoryRecycle;
    private RecyclerView mDietrecycler;
    private Adapter_Alldiets adapteralldiet;
    private TinyDB tinyDB;
    private InterstitialAd mInterstitialAdMob;
    private SwipeRefreshLayout mSwipe;
    private RelativeLayout mLoadlay;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_alldietlist);
        ImageView mBtnback = (ImageView) findViewById(R.id.img_back);
        mBtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        tinyDB = new TinyDB(this);
        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(this, languages);

        initAdmobFullAd(this);
        loadAdmobAd();
        mNovidLay = (RelativeLayout) findViewById(R.id.no_video);
        mLoadlay = (RelativeLayout) findViewById(R.id.loadlayout);
        LottieAnimationView mLottie = (LottieAnimationView) findViewById(R.id.lotti2);
        mLottie.setVisibility(View.VISIBLE);
        mLottie.setAnimation("loadanimdial.json");
        mLottie.playAnimation();
        mLottie.loop(true);
        mLoadlay.setVisibility(View.GONE);
        mRefreshbtn = (Button) findViewById(R.id.btrefresh);
        dietplanDbhelper = new DietplanDbhelper(Activity_Alldietslist.this);
        mSwipe = (SwipeRefreshLayout) findViewById(R.id.swipelayout);
        mBtndone = (TextView) findViewById(R.id.btndone);
        mCategoryRecycle = findViewById(R.id.cate_recycle);
        mDietrecycler = findViewById(R.id.diet_recyclerview);
        LinearLayout mStorebtn = (LinearLayout) findViewById(R.id.laystore);
        ImageView mBtnstore = (ImageView) findViewById(R.id.ivbtnstore);
        mBtnstore.setBackgroundResource(R.drawable.adsstoreanim);
        AnimationDrawable anim2 = (AnimationDrawable) mBtnstore.getBackground();
        anim2.start();
        mStorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Alldietslist.this,
                        ChooseGenderActivity.class);
                startActivity(intent);
            }
        });
        mSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipe.setRefreshing(false);
            }
        });
        ShimmerFrameLayout mLoadlay1 = findViewById(R.id.shimmer_container);
        LinearLayout mGbannerlay = findViewById(R.id.adframe2);
        FrameLayout mAdframe4 = (FrameLayout) findViewById(R.id.adframe4);
        if (!tinyDB.getBoolean(Constants.PREMIUN_KEY)) {
        if (isDieatBanner2) {
            mGbannerlay.setVisibility(View.GONE);
            showBanner(Activity_Alldietslist.this, mAdframe4,mLoadlay1);
        } else {
            mAdframe4.setVisibility(View.GONE);
            Smallnative(this, mGbannerlay);
        }
        }else {
            mLoadlay.setVisibility(View.GONE);
        }
        dietplanDbhelper = new DietplanDbhelper(Activity_Alldietslist.this);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isFrom = bundle.getString("isFrom");
        }
        mLoadlay.setVisibility(View.VISIBLE);
        callCategoryApi();

    }


    public void showBanner(Context context, final FrameLayout adMobView, ShimmerFrameLayout mLoadlay1) {
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
                    mLoadlay1.setVisibility(View.GONE);
                    adMobView.setVisibility(View.VISIBLE);
                    isDieatBanner2 = false;
                }

                @Override
                public void onAdFailedToLoad(int i) {
                    mLoadlay1.setVisibility(View.GONE);
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
                        final CategorieAdaper categoriesAdapter = new CategorieAdaper(Activity_Alldietslist.this, datamodalsArrayList);
                        mCategoryRecycle.setLayoutManager(new LinearLayoutManager(Activity_Alldietslist.this, LinearLayoutManager.HORIZONTAL, false));
                        mCategoryRecycle.setAdapter(categoriesAdapter);
                        callDietApi(datamodalsArrayList.get(0).getCategory_id(), false);
                    } catch (Exception e) {
                        e.printStackTrace();
                        mLoadlay.setVisibility(View.GONE);
                        if (mNovidLay != null) {
                            mNovidLay.setVisibility(View.VISIBLE);
                        }
                    }

                }

                @Override
                public void onFailure(@NotNull Call<DietCateData> call, @NotNull Throwable t) {
                    mLoadlay.setVisibility(View.GONE);
                    if (mNovidLay != null) {
                        mNovidLay.setVisibility(View.VISIBLE);
                    }
                   // Log.e("reerror3", t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mLoadlay.setVisibility(View.GONE);
            if (mNovidLay != null) {
                mNovidLay.setVisibility(View.VISIBLE);
            }
        }

    }


    @SuppressLint("WrongConstant")
    public void callDietApi(String catid, boolean z) {
        try {
            if (z) {
                mSwipe.setRefreshing(true);
            }
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
                        for (int i = 0; i < datamodalsArrayList.size(); i++) {
                            final DietitemsData.Datalist effectItem = datamodalsArrayList.get(i);
                            if (effectItem.getCategory_id().equals(catid)) {
                                itemdata.add(effectItem);
                            }
                        }
                        if (mSwipe != null) {
                            mSwipe.setRefreshing(false);
                        }
                        SetData(itemdata);

                    } catch (Exception e) {
                    }
                }

                @Override
                public void onFailure(@NotNull Call<DietitemsData> call, @NotNull Throwable t) {
                    mLoadlay.setVisibility(View.GONE);
                    if (mNovidLay != null) {
                        mNovidLay.setVisibility(View.VISIBLE);
                    }
                    Log.e("reerror4", call.toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mLoadlay.setVisibility(View.GONE);
            if (mNovidLay != null) {
                mNovidLay.setVisibility(View.VISIBLE);
            }
        }

    }

    public void SetData(ArrayList<DietitemsData.Datalist> itemdata) {
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(Activity_Alldietslist.this, LinearLayoutManager.VERTICAL, false);
        mDietrecycler.setLayoutManager(mLayoutManager);
        try {
            adapteralldiet = new Adapter_Alldiets(Activity_Alldietslist.this, itemdata, mDietrecycler);
            mDietrecycler.setAdapter(adapteralldiet);
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
                isDieatBanner2 = true;

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

    public String isFrom() {
        return isFrom;
    }

    @Override
    public boolean onMessage(@NonNull UIEvent uiEvent) {
        if (uiEvent.code == 12345) {
            if (Apiclients.mTemplist.size() != 0) {
                mBtndone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
                            mInterstitialAdMob.show();
                        } else {
                            Donebtnclick();
                        }
                    }
                });
            }
            return true;
        }
        return false;
    }

    public void Donebtnclick() {
        if (Apiclients.mTemplist.size() != 0) {
            for (int i = 0; i < Apiclients.mTemplist.size(); i++) {
                Selecteditems mData = Apiclients.mTemplist.get(i);
                Date c = Calendar.getInstance().getTime();
                 Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
                String formattedDate = df.format(c);
                tinyDB.putString(Constants.DIETPLANDATE_KEY, formattedDate);
                dietplanDbhelper.insertExcALLDayData(formattedDate, mData.getId(), mData.getName(),
                        mData.getDescription(), mData.getImage(), mData.getIs_active(), mData.getUser_type()
                        , mData.getCategory_id());
            }
            Apiclients.mTemplist.clear();
            Intent intent = new Intent(Activity_Alldietslist.this, Activity_Userdietlist.class);
            intent.putExtra("isFrom2", true);
            startActivity(intent);
            finish();
        }
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
                Donebtnclick();

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


    class CategorieAdaper extends RecyclerView.Adapter<CategorieAdaper.CategoryViewholder> {
        private List<DietCateData.Datalist> categoryitems;
        private Context context;
        private int selected = 0;

        public CategorieAdaper(Context context, List<DietCateData.Datalist> categoryitems) {
            this.categoryitems = categoryitems;
            this.context = context;
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @NonNull
        @Override
        public CategoryViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_dietcategories,
                    viewGroup, false);
            CategoryViewholder viewHolder = new CategoryViewholder(view);
            viewHolder.mCardclick = (RelativeLayout) view.findViewById(R.id.cardlay);
            viewHolder.cattitles = (TextView) view
                    .findViewById(R.id.cattitle);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull final CategoryViewholder categoryViewholder, final int position) {
            final DietCateData.Datalist catitems = categoryitems.get(position);

            categoryViewholder.cattitles.setText(catitems.getCatname());
            categoryViewholder.mCardclick.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    index = position;
                    notifyDataSetChanged();
                    callDietApi(catitems.getCategory_id(), true);
                }
            });
            if (index == position) {
                categoryViewholder.cattitles.setTextColor(Color.WHITE);
                categoryViewholder.cattitles.setBackgroundResource(R.drawable.gradbtn);
            } else {
                categoryViewholder.cattitles.setTextColor(context.getResources().getColor(R.color.headercolor));
                categoryViewholder.cattitles.setBackgroundResource(R.drawable.cat_unselected);

            }
        }

        @Override
        public int getItemCount() {
            return categoryitems == null ? 0 : categoryitems.size();
        }

        class CategoryViewholder extends RecyclerView.ViewHolder {
            RelativeLayout mCardclick;
            TextView cattitles;

            public CategoryViewholder(View itemView) {
                super(itemView);
            }
        }
    }

    @Override
    public void onBackPressed() {
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

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
