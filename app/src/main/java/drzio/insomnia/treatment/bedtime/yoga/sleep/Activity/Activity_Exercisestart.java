package drzio.insomnia.treatment.bedtime.yoga.sleep.Activity;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.Manifest;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.ads.Ad;
import com.facebook.ads.AdChoicesView;
import com.facebook.ads.AdError;
import com.facebook.ads.AdIconView;
import com.facebook.ads.AdView;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdListener;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mannan.translateapi.Language;
import com.mannan.translateapi.TranslateAPI;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Completion;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Level1;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Level2;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Level3;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter.Adapter_Musiclist;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter.Adapter_Musiclist1;
import drzio.insomnia.treatment.bedtime.yoga.sleep.AdapterListner1;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.DatabaseOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.HistoryProgressOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.webservice.ApiInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.customs.CircleSeekBar;
import drzio.insomnia.treatment.bedtime.yoga.sleep.customs.CountDownAnimation;
import drzio.insomnia.treatment.bedtime.yoga.sleep.customs.CountDownAnimation2;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Daymodals;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.MusicApi;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class Activity_Exercisestart extends YouTubeBaseActivity implements CountDownAnimation.CountDownListener,
        CountDownAnimation2.CountDownListener,/* OnDataPointListener,*/
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private String dayname;
    List<Daymodals> excerciseDataList = new ArrayList<>();
    private DatabaseOperations databaseOperations;
    long mExecrsicedoneprogress = 0;
    long mGiveupprogress = 0;
    long mfinalprogress = 0;
    FitnessApplication fitnessApplication;
    private TinyDB tinydb;
    private RelativeLayout mSkiplayout, mMainlayout, mPauselayout;
    //    private LinearLayout mControlayout;
    int mPosition;
    private int excCouner;
    private TextView mTxtname, mTxtskip, mTxtcounter, ready_to_go;
    private CircleSeekBar mSkipprogressbar;
    public CountDownTimer readyToGoTimer;
    public CountDownTimer mSpecktimer;
    private long mSkiplayouttimes, f4959s1;
    Boolean f4961u = Boolean.valueOf(false);

    private CountDownTimer exercisetimer;
    private CircleSeekBar mBar;
    private TextView mTextprogress, mTvexename, mTvcurrexe, mTvtotalexe;
    private int timeCountInMilliSeconds;
    private int timeCountInMilliSeconds2;
    private int timeCountInMilliSeconds3;
    private int timeCountInMilliSeconds4;
    private ImageView mBtnresume;
    private long mMainprogress;
    private long mPauseprogress;
    private long mRestprogress;
    private long mLearnprogress;
    private long mSkipprogress;
    private ImageView mBtnpause;
    private CountDownTimer nextexercisetimer;
    private CountDownTimer restexercisetimer;
    private CountDownTimer skipexercisetimer;
    private CountDownTimer learnexercisetimer;
    //    private boolean isNext;
    private boolean isMain;
    private boolean isLearn;
    private boolean isRest;
    private int mResttime = 10;
    private int mLearntime = 15;
    private int REST_TAG = 4;
    long totalprogress;
    String calorievalue;
    float totalcalori;
    float daycalorie;
    int mTotalexe = 0;
    int mAllexe = 0;
    int Round = 0;
    int Gad = 0;

    private boolean isFrommainlay = false;
    private boolean isFromskiplay = false;

    private GoogleApiClient mClient;
    long mini1;
    private boolean authInProgress = false;
    private static final int REQUEST_OAUTH = 1;
    private ImageView mBtnResume, mVidanimiv;
    private TextView mPausedworkout, mWorkoutdesc, mVidanimtv;
    private LinearLayout mVidanimbtn;
    private YouTubePlayerView mYoutubeview;
    private YouTubePlayer player;
    private boolean isClicked = false;
    private ImageView mBtnNextexe;
    private int temp2;
    private boolean isPuasedall = false;
    HistoryProgressOperations progressOperations;
    private boolean ismainstarted = false;
    private Handler mMainhandler;
    private Runnable mMainrunnable;
    private VideoView mVidview;
    private VideoView mPuaseexevidview;
    private boolean isGiveup = false;
    private InterstitialAd mInterstitialAdMob;
    private LinearLayout mNativecontainer;
    private VideoView mSkipVidviews;
    private LinearLayout mAdframe;
    private boolean isComplete = false;
    boolean isbtnclicked = false;
    private Dialog dialog;
    private TextView mReadygotxt;
    private Handler mNxthandelr;
    private Runnable mNxtrunnable;
    private AdView adView;
    private int mLevel;
    private int Tagpos = 4;
    private String[] desclist;
    private Dialog dialclose;
    private LinearLayout mRestnative;
    private CircleSeekBar mRestBar;
    TextView txte;
    private TextView mTvrestprogress;
    private RelativeLayout mRestlayout;
    private ImageView mTvrestresume;
    private TextView mTvrestnextexe;
    private ImageView mIvrestexe;
    private TextView mTvroundname;
    private TextView mTvrestexecounts;
    private TextView mTvrestbtnskip;
    private RelativeLayout mPuasebottom;
    private CardView mCardrestad;
    private FrameLayout mVidframe;
    private RelativeLayout mAminlayout;
    private boolean isAdshown;
    private CountDownAnimation2 countDownAnimation2;
    private ImageView mIvsresume;
    private CountDownAnimation countDownAnimation3;
    private TextView mTvSkcount;
    private MediaPlayer mediaPlayer;
    private ImageView mCinfomusic;
    private RotateAnimation rotate;
    private TextToSpeech t1;
    private int songid = 0;
    ArrayList<File> arrayList = new ArrayList<File>();
    public static String DIRECTORY_TO_SAVE_MEDIA_NOW;
    private boolean counttype;
    private ImageView mBtplaypause;
    Adapter_Musiclist.AdapterListner adapterListner;
    Adapter_Musiclist adapter_musiclist;
    File files_music;
    ImageView mIvsongart, mBtprev, mBtnext;
    TextView mTvsongname, mTvstarttime, mTvendtime;
    RoundCornerProgressBar progressBar;
    List<MusicApi> loginData1;
    public Adapter_Musiclist1 adapter_musiclist1;
    AdapterListner1 adapterListner1;
    private String BASEURL;
    RecyclerView onlinesonglist;
    RecyclerView downloadsonglist;
    ArrayList<String> _idarrayList = new ArrayList<>();
    ArrayList<String> namearrayList = new ArrayList<>();
    ArrayList<String> durationarrayList = new ArrayList<>();
    ArrayList<String> musicarrayList = new ArrayList<>();
    ArrayList<String> imagearrayList = new ArrayList<>();
    ArrayList<String> createdAtarrayList = new ArrayList<>();
    ArrayList<String> updatedAtAtarrayList = new ArrayList<>();
    boolean ispause = false;
    RelativeLayout banner1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.devidercolor, this.getTheme()));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.devidercolor));
        }
        setContentView(R.layout.activity_execercisestart);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            dayname = bundle.getString("day");
            excerciseDataList = (List<Daymodals>) bundle.getSerializable("listexecercise");
            mLevel = bundle.getInt("level");

        }

        DIRECTORY_TO_SAVE_MEDIA_NOW = Environment.getExternalStorageDirectory() + "/Music/Drzio";
        File file1 = this.getBaseContext().getExternalFilesDir("Music");
        if (!file1.exists())
            file1.mkdir();


        //  file = new File(file1, namearrayList1.get(position) + extension1);;

        // getListFiles(new File(DIRECTORY_TO_SAVE_MEDIA_NOW));
        getListFiles(file1);
        desclist = getResources().getStringArray(R.array.describe);
        DialogAdsloading();
        initAdmobFullAd(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this);
        loadAdmobAd();
        databaseOperations = new DatabaseOperations(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this);
        progressOperations = new HistoryProgressOperations(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this);
        mGiveupprogress = databaseOperations.getExcDayProgress(dayname, mLevel);
        fitnessApplication = FitnessApplication.getInstance();
        tinydb = new TinyDB(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this);
        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(this, languages);

        songid = tinydb.getInt(Constants.SONGID_KEY);

        BASEURL = tinydb.getString(Constants.NewBaseUrl);
        if (tinydb.getBoolean(Constants.GFITON_KEY)) {
            Googlefit();
        }
        mSkiplayout = (RelativeLayout) findViewById(R.id.startskiplayout);
        mMainlayout = (RelativeLayout) findViewById(R.id.mainlayout);
        mPauselayout = (RelativeLayout) findViewById(R.id.pauselay);
        mRestlayout = (RelativeLayout) findViewById(R.id.layoutrest);
        mNativecontainer = (LinearLayout) findViewById(R.id.fbnative);
        mNativecontainer.setVisibility(View.GONE);
        showGOOGLEAdvance(this, mNativecontainer);

        mRestnative = (LinearLayout) findViewById(R.id.adframerest);
        mCardrestad = (CardView) findViewById(R.id.cardadd);
        showGOOGLEAdvance(this, mRestnative);
        mPosition = databaseOperations.getDayExcCounter(dayname, mLevel);
        this.excCouner = mPosition;
        mMainlayout.setVisibility(View.GONE);
        // int id = tinydb.getInt(Constants.SONGID_KEY);

        try {
            mediaPlayer = MediaPlayer.create(this, Uri.fromFile(arrayList.get(songid)));
            mediaPlayer.setLooping(true);
            mediaPlayer.setVolume(100, 100);
        } catch (Exception e) {

        }

        layoutSkip();
        Conrolslayout(0);
        calorievalue = excerciseDataList.get(mPosition).getCalorie();
        totalcalori = tinydb.getFloat(Constants.TOTALKCAL_KEY);
        totalprogress = tinydb.getLong(Constants.TOTALTIME_KEY, 0);
        mAllexe = tinydb.getInt(Constants.TOTALEXE_KEY);

        /*if (mLevel == 1 || mLevel == 2){*/
            REST_TAG = 5;
            Tagpos = 4;
        /*}else {
            REST_TAG = 4;
            Tagpos = 3;
        }*/
        if (mPosition >= 0 && mPosition <= 3) {
            Round = 0;
        } else if (mPosition >= 4 && mPosition <= 7) {
            Round = 1;
        } else if (mPosition >= 8 && mPosition <= 11) {
            Round = 2;
        }

        ShimmerFrameLayout mLoadlay = findViewById(R.id.shimmer_container);
            banner1 = (RelativeLayout) findViewById(R.id.banner1);
        FrameLayout mGbannerlay2 = findViewById(R.id.adframe1234);
        mGbannerlay2.setVisibility(View.GONE);
        if (!tinydb.getBoolean(Constants.PREMIUN_KEY)) {
            showBanner(this, mGbannerlay2, banner1, mLoadlay);
        } else {
            mLoadlay.setVisibility(View.GONE);
        }
    }

    public void showBanner(Context context, final FrameLayout adMobView, RelativeLayout loadlayout, ShimmerFrameLayout mLoadlay) {
        try {
            final com.google.android.gms.ads.AdView mAdView = new com.google.android.gms.ads.AdView(context);
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
                    loadlayout.setVisibility(View.GONE);
                    loadlayout.setVisibility(View.GONE);
                    mLoadlay.setVisibility(View.GONE);
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

    public void showFBNativeAd(final Context context, final RelativeLayout nativeAdContainer) {

        final NativeAd nativeAd = new NativeAd(context, Constants.facebook_native);
        nativeAd.setAdListener(new NativeAdListener() {

            @Override
            public void onError(Ad ad, AdError adError) {
            }

            @Override
            public void onAdLoaded(Ad ad) {
                nativeAdContainer.setVisibility(View.VISIBLE);

                LayoutInflater inflater = LayoutInflater.from(context);
                LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.fb_ad_unit, nativeAdContainer, false);

                if (nativeAdContainer != null) {
                    nativeAdContainer.removeAllViews();
                }
                nativeAdContainer.addView(adView);

                LinearLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
                AdChoicesView adChoicesView = new AdChoicesView(context, nativeAd, true);
                adChoicesContainer.addView(adChoicesView, 0);

                AdIconView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
                TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
                MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
                TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
                TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
                TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
                Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

                nativeAdTitle.setText(nativeAd.getAdvertiserName());
                nativeAdBody.setText(nativeAd.getAdBodyText());
                nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
                nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
                nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
                sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

                List<View> clickableViews = new ArrayList<>();
                clickableViews.add(nativeAdTitle);
                clickableViews.add(nativeAdCallToAction);


                nativeAd.registerViewForInteraction(
                        adView,
                        nativeAdMedia,
                        nativeAdIcon,
                        clickableViews);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }

            @Override
            public void onMediaDownloaded(Ad ad) {

            }
        });
        nativeAd.loadAd();

    }

    private void Googlefit() {
        mClient = new GoogleApiClient.Builder(this)
                .addApi(Fitness.HISTORY_API)
                .addApi(Fitness.CONFIG_API)
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ))
                .useDefaultAccount()
                .addConnectionCallbacks(
                        new GoogleApiClient.ConnectionCallbacks() {

                            @Override
                            public void onConnected(Bundle bundle) {


                                //Async To fetch steps
                                //    new Activity_Settings.FetchStepsAsync().execute();
                                new ViewWeekStepCountTask().execute();


                            }

                            @Override
                            public void onConnectionSuspended(int i) {
                                // If your connection to the sensor gets lost at some point,
                                // you'll be able to determine the reason and react to it here.
                                if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_NETWORK_LOST) {
                                    Log.i("TAG", "Connection lost.  Cause: Network Lost.");
                                } else if (i == GoogleApiClient.ConnectionCallbacks.CAUSE_SERVICE_DISCONNECTED) {
                                    Log.i("TAG", "Connection lost.  Reason: Service Disconnected");
                                }
                            }
                        }
                ).addOnConnectionFailedListener(
                        new GoogleApiClient.OnConnectionFailedListener() {
                            // Called whenever the API client fails to connect.
                            @Override
                            public void onConnectionFailed(ConnectionResult result) {
                                Log.i("TAG", "Connection failed. Cause: " + result.toString());
                                if (!result.hasResolution()) {
                                    // Show the localized error dialog
                                    GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(),
                                            drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, 0).show();
                                    return;
                                }
                                // The failure has a resolution. Resolve it.
                                // Called typically when the app is not yet authorized, and an
                                // authorization dialog is displayed to the user.
                                if (!authInProgress) {
                                    try {
                                        Log.i("TAG", "Attempting to resolve failed connection");
                                        authInProgress = true;
                                        result.startResolutionForResult(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, REQUEST_OAUTH);
                                    } catch (IntentSender.SendIntentException e) {
                                        Log.e("TAG",
                                                "Exception while starting resolution activity", e);
                                    }
                                }
                            }
                        }
                ).build();
        mClient.connect();
    }

    private class ViewWeekStepCountTask extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... params) {
            displayLastWeeksData();
            return null;
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.e("HistoryAPI", "onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.e("HistoryAPI", "onConnectionFailed");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.e("HistoryAPI", "onConnected");
        displayLastWeeksData();
    }

    public void displayLastWeeksData() {
        Calendar cal = Calendar.getInstance();
        Date now = new Date();
        cal.setTime(now);
        long endTime = cal.getTimeInMillis();
        cal.add(Calendar.HOUR_OF_DAY, -1);
        long startTime = cal.getTimeInMillis();
        Log.e("start", String.valueOf(startTime));
        Log.e("endTime", String.valueOf(endTime));


        DataSource dataSource = new DataSource.Builder()
                .setAppPackageName(this)
                .setDataType(DataType.TYPE_BASAL_METABOLIC_RATE)
                .setStreamName("TAG" + " - step count")
                .setType(DataSource.TYPE_RAW)
                .build();

        float calories = round(tinydb.getFloat(Constants.TOTALKCAL_KEY), 1);


        DataSet dataSet = DataSet.create(dataSource);

        DataPoint dataPoint = dataSet.createDataPoint()
                .setTimeInterval(startTime, endTime, TimeUnit.MILLISECONDS);
        dataPoint.getValue(Field.FIELD_CALORIES).setFloat(calories);
        dataSet.add(dataPoint);
        Log.i("TAG", "Inserting the dataset in the History API.");
        com.google.android.gms.common.api.Status insertStatus =
                Fitness.HistoryApi.insertData(mClient, dataSet)
                        .await(1000, TimeUnit.MINUTES);


        if (!insertStatus.isSuccess()) {
            Log.i("TAG", "There was a problem inserting the dataset.");
        } else {


            Log.i("TAG", "Data insert was successful!");
            //  new Activity_Settings.FetchCalorieAsync().execute();
        }

    }

    public static float round(float d, int decimalPlace) {
        try {
            return BigDecimal.valueOf(d).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP).floatValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
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
                    Giveupclick();
                } else if (Gad == 1) {
                    Execomplete();
                } else if (Gad == 2) {
                    ClosePauseact();
                }
                loadAdmobAd();
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

    public void layoutSkip() {
        isFromskiplay = true;
        mTxtname = (TextView) findViewById(R.id.txtname);
        mTxtskip = (TextView) findViewById(R.id.skipbtn);
        ready_to_go = (TextView) findViewById(R.id.ready_to_go);
        mTxtcounter = (TextView) findViewById(R.id.counting);
        mTvSkcount = (TextView) findViewById(R.id.mscounttext);
        mIvsresume = (ImageView) findViewById(R.id.btnskipresume);
        mSkipprogressbar = (CircleSeekBar) findViewById(R.id.timer);
        ImageView mBtnback = (ImageView) findViewById(R.id.imgback);
        mBtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        String upperCase = excerciseDataList.get(mPosition).getExecercisename().toUpperCase();
        mTxtname.setText(upperCase);
        mTxtskip.setText(getResources().getString(R.string.skip));
        ready_to_go.setText(getResources().getString(R.string.ready_to_go));
        mSkipVidviews = (VideoView) findViewById(R.id.mvideos);
        if (mSkipVidviews.isPlaying()) {
            mSkipVidviews.pause();
            mSkipVidviews.requestFocus();
        }
        mSkipVidviews.animate().alpha(1);
        Uri video = Uri.parse(excerciseDataList.get(mPosition).getExecerciseimaga());
        mSkipVidviews.setVideoURI(video);
        mSkipVidviews.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mSkipVidviews.start();
            }
        });
        setupskipporgress();
        mTxtskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isFromskiplay = false;
                if (mSkipVidviews.isPlaying()) {
                    mSkipVidviews.pause();
                    mSkipVidviews.requestFocus();
                }
                if (readyToGoTimer != null) {
                    readyToGoTimer.cancel();
                }
                if (skipexercisetimer != null) {
                    skipexercisetimer.cancel();
                }
                if (countDownAnimation3 != null) {
                    countDownAnimation3.cancel();
                }
                mSkiplayout.setVisibility(View.GONE);
                mMainlayout.setVisibility(View.VISIBLE);
                isFrommainlay = true;
                Mainlayout();
            }
        });
        mIvsresume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSkiplayout.setVisibility(View.VISIBLE);
                startSkipTimer(mSkipprogress);
                isFromskiplay = false;
            }
        });
    }


    public void Conrolslayout(int i) {
        ImageView mCvidbtn;
        ImageView mCvolbtn;
        ImageView mCinfobtn;
        if (i == 0) {
            mCvidbtn = (ImageView) findViewById(R.id.videobtn);
            mCvolbtn = (ImageView) findViewById(R.id.volumebtn);
            mCinfobtn = (ImageView) findViewById(R.id.infobtn);
            mCinfomusic = (ImageView) findViewById(R.id.musicbtn);
        } else {
            mCvidbtn = (ImageView) findViewById(R.id.videobtn1);
            mCvolbtn = (ImageView) findViewById(R.id.volumebtn1);
            mCinfobtn = (ImageView) findViewById(R.id.infobtn1);
            mCinfomusic = (ImageView) findViewById(R.id.musicbtn1);
        }
        rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(4000);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setInterpolator(new LinearInterpolator());

        if (!tinydb.getBoolean(Constants.ISMUTEON_KEY)) {
            if (!tinydb.getBoolean(Constants.ISMUSICON_KEY)) {
//                tinydb.putBoolean(Constants.ISMUSICON_KEY, false);
//                mCinfomusic.startAnimation(rotate);
                try {
                    mediaPlayer.start();
                } catch (Exception e) {

                }


            }
        }

        mCinfomusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Puasetime();
                if (mMainlayout.getVisibility() == View.VISIBLE) {
                    if (!ismainstarted) {
                        try {
                            mMainhandler.removeCallbacks(mMainrunnable);
                            String temp = excerciseDataList.get(mPosition).getExecerciseduration();
                            temp = temp.replace("00:", "");
                            float tempsecond = Float.parseFloat(temp);
                            mMainprogress = (int) (tempsecond * 1000.0f);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    isFrommainlay = true;
                } else if (mSkiplayout.getVisibility() == View.VISIBLE) {
                    isFromskiplay = true;
                    if (mIvsresume != null && mIvsresume.getVisibility() == View.VISIBLE) {
                        mIvsresume.setVisibility(View.GONE);
                    }
                }
                DialogVoicesettings();
            }
        });

        mCvidbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Puasetime();
                if (mMainlayout.getVisibility() == View.VISIBLE) {
                    if (!ismainstarted) {
                        try {
                            mMainhandler.removeCallbacks(mMainrunnable);
                            String temp = excerciseDataList.get(mPosition).getExecerciseduration();
                            temp = temp.replace("00:", "");
                            float tempsecond = Float.parseFloat(temp);
                            mMainprogress = (int) (tempsecond * 1000.0f);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    mMainlayout.setVisibility(View.GONE);
                    isFrommainlay = true;
                } else if (mSkiplayout.getVisibility() == View.VISIBLE) {
                    mSkiplayout.setVisibility(View.GONE);
                    isFromskiplay = true;
                    if (mIvsresume != null && mIvsresume.getVisibility() == View.VISIBLE) {
                        mIvsresume.setVisibility(View.GONE);
                    }
                }

                mPauselayout.setVisibility(View.VISIBLE);
                banner1.setVisibility(View.GONE);
                if (player != null) {
                    player.release();
                }
                Pauselayout(1);
            }
        });

        mCvolbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Puasetime();
                if (mMainlayout.getVisibility() == View.VISIBLE) {
                    if (!ismainstarted) {
                        try {
                            mMainhandler.removeCallbacks(mMainrunnable);
                            String temp = excerciseDataList.get(mPosition).getExecerciseduration();
                            temp = temp.replace("00:", "");
                            float tempsecond = Float.parseFloat(temp);
                            mMainprogress = (int) (tempsecond * 1000.0f);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    isFrommainlay = true;
                } else if (mSkiplayout.getVisibility() == View.VISIBLE) {
                    isFromskiplay = true;
                    if (mIvsresume != null && mIvsresume.getVisibility() == View.VISIBLE) {
                        mIvsresume.setVisibility(View.GONE);
                    }
                }
//                DialogVoicesettings();
                DialogMusicsettings();
            }
        });

        mCinfobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //    fitnessApplication.stop();
                Puasetime();
                if (mMainlayout.getVisibility() == View.VISIBLE) {
                    if (!ismainstarted) {
                        try {
                            mMainhandler.removeCallbacks(mMainrunnable);
                            String temp = excerciseDataList.get(mPosition).getExecerciseduration();
                            temp = temp.replace("00:", "");
                            float tempsecond = Float.parseFloat(temp);
                            mMainprogress = (int) (tempsecond * 1000.0f);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    mMainlayout.setVisibility(View.GONE);
                    isFrommainlay = true;
                } else if (mSkiplayout.getVisibility() == View.VISIBLE) {
                    mSkiplayout.setVisibility(View.GONE);
                    isFromskiplay = true;
                    if (mIvsresume != null && mIvsresume.getVisibility() == View.VISIBLE) {
                        mIvsresume.setVisibility(View.GONE);
                    }
                }

                mPauselayout.setVisibility(View.VISIBLE);
                banner1.setVisibility(View.GONE);
                if (player != null) {
                    player.release();
                }
                Pauselayout(2);
            }
        });
    }


/*
    public void Pauselayout(int val) {
        try {
            mMainlayout.setVisibility(View.GONE);
            mBtnResume = (ImageView) findViewById(R.id.btnpauseclose);
            mPausedworkout = (TextView) findViewById(R.id.pauseworkoutname);
            mWorkoutdesc = (TextView) findViewById(R.id.pauseworkoutdesc);
            ConnectivityManager mgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = mgr.getActiveNetworkInfo();
            if (netInfo != null) {
                if (netInfo.isConnected()) {
                    String languages = tinydb.getString(Constants.Language);
                    TranslateAPI translateAPI = new TranslateAPI(
                            Language.AUTO_DETECT, languages,
                            // Language.HINDI,
                            excerciseDataList.get(mPosition).getExedescription());

                    translateAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
                        @Override
                        public void onSuccess(String translatedText) {
                            Log.d("TAG", "onSuccess: " + translatedText);
                            //  textView.setText(translatedText);
                            mWorkoutdesc.setText(translatedText);
                            t1=new TextToSpeech(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, new TextToSpeech.OnInitListener() {
                                @Override
                                public void onInit(int status) {
                                    if(status != TextToSpeech.ERROR) {
                                        //  t1.setLanguage(Locale.UK);
                                        Locale locale = new Locale(languages);

                                        t1.setLanguage(locale);
                                        t1.speak(translatedText, TextToSpeech.QUEUE_FLUSH, null);

                                    }
                                }
                            });

                        }

                        @Override
                        public void onFailure(String ErrorText) {
                            Log.d("TAG", "onFailure: " + ErrorText);
                        }
                    });


                } else {
                    mWorkoutdesc.setText(excerciseDataList.get(mPosition).getExedescription());
                }
            } else {
                mWorkoutdesc.setText(excerciseDataList.get(mPosition).getExedescription());
            }










            // mWorkoutdesc.setText(excerciseDataList.get(mPosition).getExedescription());
            mVidanimbtn = (LinearLayout) findViewById(R.id.vidanimbtn);
            mVidanimiv = (ImageView) findViewById(R.id.icvidanim);
            mVidanimtv = (TextView) findViewById(R.id.tvvidanim);
            mAminlayout = (RelativeLayout) findViewById(R.id.animlay);
            mAminlayout.setVisibility(View.VISIBLE);
            mVidframe = (FrameLayout) findViewById(R.id.videolay);
            mVidframe.setVisibility(View.VISIBLE);
            mPuaseexevidview = (VideoView) findViewById(R.id.tempvidoe);
            //  PlayAnimation(mPuaseexevidview, excerciseDataList.get(mPosition).getExecerciseimaga());
            if (mPuaseexevidview.isPlaying()) {
                mPuaseexevidview.pause();
                mPuaseexevidview.requestFocus();
            }
            mPuaseexevidview.animate().alpha(1);
          //  String videos = "android.resource://" + getPackageName()+ "/" + "raw" + "/" + excerciseDataList.get(mPosition).getExecerciseimaga();
            Uri video = Uri.parse(excerciseDataList.get(mPosition).getExecerciseimaga());
         //   Uri video = Uri.parse(videos);
            mPuaseexevidview.setVideoURI(video);
            mPuaseexevidview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                    mPuaseexevidview.start();
                }
            });
            mYoutubeview = (YouTubePlayerView) findViewById(R.id.youtube_view);
            mYoutubeview.initialize(Constants.YoutubeApi, new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    if (!b) {
                        player = youTubePlayer;
                        player.setShowFullscreenButton(false);
                        youTubePlayer.cueVideo(excerciseDataList.get(mPosition).getYouvideo());
                    }
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            });
            String temp = excerciseDataList.get(mPosition).getExecerciseduration();
            temp = temp.replace("00:", "");
            String stringBuilder = excerciseDataList.get(mPosition).getExecercisename().toUpperCase() + ' ' + temp + "s";
            mPausedworkout.setText(stringBuilder);
            mBtnResume.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isClicked && !isAdshown) {
                        Gad = 2;
                        if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
                            isAdshown = true;
                            mInterstitialAdMob.show();
                        } else {
                            ClosePauseact();
                        }
                    } else {
                        ClosePauseact();
                    }
                }
            });
            if (val == 1) {
                if (mNativecontainer != null) {
                    mNativecontainer.setVisibility(View.GONE);
                }



                mVidanimbtn.setBackgroundResource(R.drawable.gradbtn);
                mVidanimiv.setImageTintList(ColorStateList.valueOf(Color.WHITE));

                mVidanimiv.setImageResource(R.drawable.ic_image_black_24dp);
                mVidanimtv.setText(getResources().getString(R.string.animation));
                mAminlayout.setVisibility(View.GONE);
                mPuaseexevidview.setVisibility(View.GONE);
                if (mPuaseexevidview != null && mPuaseexevidview.isPlaying()) {
                    mPuaseexevidview.animate().alpha(0);
                    mPuaseexevidview.pause();
                }
                isClicked = true;
            } else {
                if (mSkipVidviews != null && mSkipVidviews.isPlaying()) {
                    mSkipVidviews.setVisibility(View.GONE);
                }
                if (mVidview != null && mVidview.isPlaying()) {
                    mVidview.setVisibility(View.GONE);
                }
                if (mNativecontainer != null) {
                    mNativecontainer.setVisibility(View.VISIBLE);
                }
                mVidanimbtn.setBackgroundResource(R.drawable.gradbtn);
                mVidanimiv.setImageTintList(ColorStateList.valueOf(Color.WHITE));

                mVidanimiv.setImageResource(R.drawable.ic_videocam_black_24dp);
                mVidanimtv.setText(getResources().getString(R.string.video));
                mAminlayout.setVisibility(View.VISIBLE);
                mPuaseexevidview.animate().alpha(1);
                mPuaseexevidview.setVisibility(View.VISIBLE);
                if (mPuaseexevidview != null && !mPuaseexevidview.isPlaying()) {
                    mPuaseexevidview.start();
                }
                isClicked = false;
            }
            mVidanimbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isClicked) {
                        if (mNativecontainer != null) {
                            mNativecontainer.setVisibility(View.GONE);
                        }
                        mVidanimbtn.setBackgroundResource(R.drawable.gradbtn);
                        mVidanimiv.setImageTintList(ColorStateList.valueOf(Color.WHITE));

                        mVidanimiv.setImageResource(R.drawable.ic_image_black_24dp);
                        mVidanimtv.setText(getResources().getString(R.string.animation));
                        mPuaseexevidview.animate().alpha(0);
                        mPuaseexevidview.setVisibility(View.GONE);
                        mAminlayout.setVisibility(View.GONE);
                        if (mPuaseexevidview != null && mPuaseexevidview.isPlaying()) {
                            mPuaseexevidview.pause();
                        }
                        if (player != null) {
                            player.play();
                        }
                        isClicked = true;
                    } else {
                        if (mNativecontainer != null) {
                            mNativecontainer.setVisibility(View.VISIBLE);
                        }
                        mVidanimbtn.setBackgroundResource(R.drawable.gradbtn);
                        mVidanimiv.setImageTintList(ColorStateList.valueOf(Color.WHITE));

                        mVidanimiv.setImageResource(R.drawable.ic_videocam_black_24dp);
                        mVidanimtv.setText(getResources().getString(R.string.video));
                        mPuaseexevidview.animate().alpha(1);
                        mPuaseexevidview.setVisibility(View.VISIBLE);
                        mAminlayout.setVisibility(View.VISIBLE);
                        if (mPuaseexevidview != null && !mPuaseexevidview.isPlaying()) {
                            mPuaseexevidview.start();
                        }
                        if (player != null) {
                            player.pause();
                        }
                        isClicked = false;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/

    public void Pauselayout(int val) {
        try {
            mMainlayout.setVisibility(View.GONE);
            mBtnResume = (ImageView) findViewById(R.id.btnpauseclose);
            mPausedworkout = (TextView) findViewById(R.id.pauseworkoutname);
            mWorkoutdesc = (TextView) findViewById(R.id.pauseworkoutdesc);
            mWorkoutdesc.setText(excerciseDataList.get(mPosition).getExedescription());
            mVidanimbtn = (LinearLayout) findViewById(R.id.vidanimbtn);
            mVidanimiv = (ImageView) findViewById(R.id.icvidanim);
            mVidanimtv = (TextView) findViewById(R.id.tvvidanim);
            mAminlayout = (RelativeLayout) findViewById(R.id.animlay);
            mAminlayout.setVisibility(View.VISIBLE);
            mVidframe = (FrameLayout) findViewById(R.id.videolay);
            mVidframe.setVisibility(View.VISIBLE);
            mPuaseexevidview = (VideoView) findViewById(R.id.tempvidoe);
            mPuaseexevidview.setVisibility(View.VISIBLE);
            if (mPuaseexevidview.isPlaying()) {
                mPuaseexevidview.pause();
                mPuaseexevidview.requestFocus();
            }
  /*          ConnectivityManager mgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = mgr.getActiveNetworkInfo();
            if (netInfo != null) {
                if (netInfo.isConnected()) {
                    String languages = tinydb.getString(Constants.Language);
                    TranslateAPI translateAPI = new TranslateAPI(
                            Language.AUTO_DETECT, languages,
                            // Language.HINDI,
                            excerciseDataList.get(mPosition).getExedescription());

                    translateAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
                        @Override
                        public void onSuccess(String translatedText) {
                            Log.d("TAG", "onSuccess: " + translatedText);
                            //  textView.setText(translatedText);
                            mWorkoutdesc.setText(translatedText);
                           *//* t1=new TextToSpeech(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, new TextToSpeech.OnInitListener() {
                                @Override
                                public void onInit(int status) {
                                    if(status != TextToSpeech.ERROR) {
                                        //  t1.setLanguage(Locale.UK);
                                        Locale locale = new Locale(languages);

                                        t1.setLanguage(locale);
                                        t1.speak(translatedText, TextToSpeech.QUEUE_FLUSH, null);

                                    }
                                }
                            });*//*

                        }

                        @Override
                        public void onFailure(String ErrorText) {
                            Log.d("TAG", "onFailure: " + ErrorText);
                        }
                    });


                } else {
                    mWorkoutdesc.setText(excerciseDataList.get(mPosition).getExedescription());
                }
            } else {
                mWorkoutdesc.setText(excerciseDataList.get(mPosition).getExedescription());
            }*/
            mWorkoutdesc.setText(excerciseDataList.get(mPosition).getExedescription());
            mPuaseexevidview.animate().alpha(1);
            Uri video = Uri.parse(excerciseDataList.get(mPosition).getExecerciseimaga());
            mPuaseexevidview.setVideoURI(video);
            mPuaseexevidview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                    mPuaseexevidview.start();
                }
            });
            mYoutubeview = (YouTubePlayerView) findViewById(R.id.youtube_view);
            mYoutubeview.initialize(Constants.YoutubeApi, new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    if (!b) {
                        player = youTubePlayer;
                        player.setShowFullscreenButton(false);
                        youTubePlayer.cueVideo(excerciseDataList.get(mPosition).getYouvideo());
                        try {
                            if (fitnessApplication.isSpeaking()) {
                                fitnessApplication.stop();
                            }
                            if (mediaPlayer != null) {
                                mediaPlayer.pause();
                            }
                        } catch (Exception r) {

                        }
                    }
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            });
            String temp = excerciseDataList.get(mPosition).getExecerciseduration();
            temp = temp.replace("00:", "");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(excerciseDataList.get(mPosition).getExecercisename().toUpperCase());
            stringBuilder.append(' ');
            stringBuilder.append(temp);
            stringBuilder.append("s");
            mPausedworkout.setText(stringBuilder.toString());
            mBtnResume.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isClicked && !isAdshown) {
                        Gad = 2;
                        if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
                            isAdshown = true;
                            mInterstitialAdMob.show();
                        } else {
                            ClosePauseact();
                        }
                    } else {
                        ClosePauseact();
                    }
                }
            });
            if (val == 1) {
                if (mNativecontainer != null) {
                    mNativecontainer.setVisibility(View.GONE);
                }
                mVidanimbtn.setBackgroundResource(R.drawable.gradbtn);
                mVidanimiv.setImageResource(R.drawable.ic_image_black_24dp);
                mVidanimtv.setText(getResources().getString(R.string.animation));
                mAminlayout.setVisibility(View.GONE);
                mPuaseexevidview.setVisibility(View.GONE);
                if (mPuaseexevidview != null && mPuaseexevidview.isPlaying()) {
                    mPuaseexevidview.animate().alpha(0);
                    mPuaseexevidview.pause();
                }
                isClicked = true;
            } else {
                if (mSkipVidviews != null && mSkipVidviews.isPlaying()) {
                    mSkipVidviews.setVisibility(View.GONE);
                }
                if (mVidview != null && mVidview.isPlaying()) {
                    mVidview.setVisibility(View.GONE);
                }
                if (mNativecontainer != null) {
                    mNativecontainer.setVisibility(View.VISIBLE);
                }
                mVidanimbtn.setBackgroundResource(R.drawable.gradbtn);
                mVidanimiv.setImageResource(R.drawable.ic_videocam_black_24dp);
                mVidanimtv.setText(getResources().getString(R.string.video));
                mAminlayout.setVisibility(View.VISIBLE);
                mPuaseexevidview.animate().alpha(1);
                mPuaseexevidview.setVisibility(View.VISIBLE);
                if (mPuaseexevidview != null && !mPuaseexevidview.isPlaying()) {
                    mPuaseexevidview.start();
                }
                isClicked = false;
            }
            mVidanimbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isClicked) {
                        if (mNativecontainer != null) {
                            mNativecontainer.setVisibility(View.GONE);
                        }
                        mVidanimbtn.setBackgroundResource(R.drawable.gradbtn);
                        mVidanimiv.setImageResource(R.drawable.ic_image_black_24dp);
                        mVidanimtv.setText(getResources().getString(R.string.animation));
                        mPuaseexevidview.animate().alpha(0);
                        mPuaseexevidview.setVisibility(View.GONE);
                        mAminlayout.setVisibility(View.GONE);
                        if (mPuaseexevidview != null && mPuaseexevidview.isPlaying()) {
                            mPuaseexevidview.pause();
                        }

                        try {
                            if (mediaPlayer.isPlaying()) {
                                mediaPlayer.pause();
                            }
                        }catch (Exception e){

                        }
                        isClicked = true;
                    } else {
                        if (mNativecontainer != null) {
                            mNativecontainer.setVisibility(View.VISIBLE);
                        }
                        mVidanimbtn.setBackgroundResource(R.drawable.gradbtn);
                        mVidanimiv.setImageResource(R.drawable.ic_videocam_black_24dp);
                        mVidanimtv.setText(getResources().getString(R.string.video));
                        mPuaseexevidview.animate().alpha(1);
                        mPuaseexevidview.setVisibility(View.VISIBLE);
                        mAminlayout.setVisibility(View.VISIBLE);
                        if (mPuaseexevidview != null && !mPuaseexevidview.isPlaying()) {
                            mPuaseexevidview.start();
                        }
                       /* if (mediaPlayer  != null) {
                            mediaPlayer.start();
                        }*/
                        try {
                            if (mediaPlayer != null) {
                                if (!ispause) {
                                    if (!tinydb.getBoolean(Constants.ISMUTEON_KEY)) {
                                        mediaPlayer.start();
                                    }

                                } else {
                                    mediaPlayer.pause();
                                }
                            }
                        } catch (Exception e) {

                        }

                       /* if (player != null) {
                            player.pause();
                        }*/
                        isClicked = false;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ClosePauseact() {
        if (isMain) {
            mMainlayout.setVisibility(View.VISIBLE);
            if (mVidview != null) {
                mVidview.setVisibility(View.VISIBLE);
            }
        } else if (isRest) {
            mMainlayout.setVisibility(View.GONE);
            if (mVidview != null) {
                mVidview.setVisibility(View.VISIBLE);
            }
            if (mTvrestresume != null) {
                mTvrestresume.setClickable(true);
                isPuasedall = true;
                mTvrestresume.setVisibility(View.VISIBLE);
            }
            mRestlayout.setVisibility(View.VISIBLE);
        } else if (isLearn) {
            mMainlayout.setVisibility(View.VISIBLE);
            if (mVidview != null) {
                mVidview.setVisibility(View.VISIBLE);
            }
        } else {
            mSkiplayout.setVisibility(View.VISIBLE);
            if (mSkipVidviews != null) {
                mSkipVidviews.setVisibility(View.VISIBLE);
            }
        }
        if (player != null) {
            player.release();
        }


        try {
            if (mediaPlayer != null) {
                if (!ispause) {
                    if (!tinydb.getBoolean(Constants.ISMUTEON_KEY)) {
                        mediaPlayer.start();
                    }

                } else {
                    mediaPlayer.pause();
                }
            }
        } catch (Exception e) {

        }
        if (mPuaseexevidview != null && mPuaseexevidview.isPlaying()) {
            mPuaseexevidview.pause();
            mPuaseexevidview.setVisibility(View.GONE);
            mPuaseexevidview.animate().alpha(0);
        }
        mVidframe.setVisibility(View.GONE);
        mAminlayout.setVisibility(View.GONE);

                mPauselayout.setVisibility(View.GONE);
                banner1.setVisibility(View.VISIBLE);
        if (!isPuasedall) {
            Resumetimer();
        }
    }


    private void CopyRAWtoSDCard(int id, String path) throws IOException {
        InputStream in = getResources().openRawResource(id);
        FileOutputStream out = new FileOutputStream(path);
        byte[] buff = new byte[1024];
        int read = 0;
        try {
            while ((read = in.read(buff)) > 0) {
                out.write(buff, 0, read);
            }
        } finally {
            in.close();
            out.close();
        }


    }

    @SuppressLint("UseSwitchCompatOrMaterialCode")
    public void DialogVoicesettings() {
        final Dialog dialog = new Dialog(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_mutevoice);
        final Switch mVoicemute = (Switch) dialog.findViewById(R.id.switchmute);
        final Switch mGuidevoice = (Switch) dialog.findViewById(R.id.switchguidevoice);
        final Switch mTipsvoice = (Switch) dialog.findViewById(R.id.switchtipsvoice);
        TextView mClosedial = (TextView) dialog.findViewById(R.id.txtdialok);
      /*  ArrayAdapter arrayAdapter = new ArrayAdapter<>(this,
                R.layout.mytextview,
                R.id.text1, songnames);
        ListView listView = dialog.findViewById(R.id.songslist);
        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        listView.setAdapter(arrayAdapter);
        int selectedid = tinydb.getInt(Constants.SONGID_KEY);
        listView.setItemChecked(selectedid, true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tinydb.putInt(Constants.SONGID_KEY, position);
                mediaPlayer.release();
                mediaPlayer = MediaPlayer.create(Activity_Exercisestart.this, songids[position]);
                mediaPlayer.setLooping(true);
                mediaPlayer.setVolume(100, 100);
                if (!tinydb.getBoolean(Constants.ISMUTEON_KEY)) {
                    mediaPlayer.start();
                }
            }
        });*/

        mClosedial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
//                if (!isPuasedall) {
//                    Resumetimer();
//                }
                if (isFromskiplay) {
                    Resumetimer();
                }
                if (mBtnresume != null && !isPuasedall) {
                    if (!counttype) {
                        isPuasedall = true;
                        mBtnresume.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        if (tinydb.getBoolean(Constants.ISMUTEON_KEY)) {
            mVoicemute.setChecked(true);
        } else {
            mVoicemute.setChecked(false);

        }
        if (tinydb.getBoolean(Constants.ISGUIDEON_KEY)) {
            mGuidevoice.setChecked(true);
        } else {
            mGuidevoice.setChecked(false);
        }
        if (tinydb.getBoolean(Constants.ISTIPSON_KEY)) {
            mTipsvoice.setChecked(true);
        } else {
            mTipsvoice.setChecked(false);
        }

        mVoicemute.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mVoicemute.setChecked(true);
                    mGuidevoice.setChecked(false);
                    mTipsvoice.setChecked(false);
                    tinydb.putBoolean(Constants.ISMUTEON_KEY, true);
                    tinydb.putBoolean(Constants.ISGUIDEON_KEY, false);
                    tinydb.putBoolean(Constants.ISTIPSON_KEY, false);
                    if (fitnessApplication.isSpeaking()) {
                        fitnessApplication.stop();
                    }
                    try {
                        if (mediaPlayer != null) {
                            mediaPlayer.pause();
                        }
                    } catch (Exception e) {

                    }


                    if (rotate != null) {
                        rotate.cancel();
                    }
                } else {
                    mVoicemute.setChecked(false);
                    mGuidevoice.setChecked(true);
                    mTipsvoice.setChecked(true);
                    tinydb.putBoolean(Constants.ISMUTEON_KEY, false);
                    tinydb.putBoolean(Constants.ISGUIDEON_KEY, true);
                    tinydb.putBoolean(Constants.ISTIPSON_KEY, true);

                    try {
                        if (mediaPlayer != null) {
                            mediaPlayer.start();
                        }
                    } catch (Exception e) {

                    }

//                    if (rotate != null) {
//                        mCinfomusic.startAnimation(rotate);
//                    }
                }
            }
        });
        mGuidevoice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mGuidevoice.setChecked(true);
                    if (mVoicemute.isChecked()) {
                        mVoicemute.setChecked(false);
                        tinydb.putBoolean(Constants.ISMUTEON_KEY, false);
                    }
                    tinydb.putBoolean(Constants.ISGUIDEON_KEY, true);
                } else {
                    mGuidevoice.setChecked(false);
                    tinydb.putBoolean(Constants.ISGUIDEON_KEY, false);
                    if (fitnessApplication.isSpeaking()) {
                        fitnessApplication.stop();
                    }
                }
            }
        });
        mTipsvoice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mTipsvoice.setChecked(true);
                    if (mVoicemute.isChecked()) {
                        mVoicemute.setChecked(false);
                        tinydb.putBoolean(Constants.ISMUTEON_KEY, false);
                    }
                    tinydb.putBoolean(Constants.ISTIPSON_KEY, true);
                } else {
                    mTipsvoice.setChecked(false);
                    tinydb.putBoolean(Constants.ISTIPSON_KEY, false);
                    if (fitnessApplication.isSpeaking()) {
                        fitnessApplication.stop();
                    }
                }
            }
        });
        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (mBtnresume != null && !isPuasedall) {
                    if (!counttype) {
                        isPuasedall = true;
                        mBtnresume.setVisibility(View.VISIBLE);
                    }
                }
                if (isFromskiplay) {
                    Resumetimer();
                }
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (mBtnresume != null && !isPuasedall) {
                    if (!counttype) {
                        isPuasedall = true;
                        mBtnresume.setVisibility(View.VISIBLE);
                    }
                }
                if (isFromskiplay) {
                    Resumetimer();
                }
            }
        });
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        try {
            dialog.getWindow().setWindowAnimations(R.style.DialogTheme);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        dialog.getWindow().setAttributes(lp);

    }


    @SuppressLint({"UseSwitchCompatOrMaterialCode", "WrongConstant"})
    public void DialogMusicsettings() {
        final Dialog dialog = new Dialog(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_musiclist);
        Button submitButton = (Button) dialog.findViewById(R.id.submitButton);

        onlinesonglist = (RecyclerView) dialog.findViewById(R.id.songslist);
        downloadsonglist = (RecyclerView) dialog.findViewById(R.id.songslist1);
        getMusicApi();

        mIvsongart = (ImageView) dialog.findViewById(R.id.ivimg);
        mTvsongname = (TextView) dialog.findViewById(R.id.tvsongname);
        mBtprev = (ImageView) dialog.findViewById(R.id.btprev);
        mBtplaypause = (ImageView) dialog.findViewById(R.id.btpause);
        mBtnext = (ImageView) dialog.findViewById(R.id.btnext);
        mTvstarttime = (TextView) dialog.findViewById(R.id.tvstarttime);
        mTvendtime = (TextView) dialog.findViewById(R.id.tvendtime);
        progressBar = (RoundCornerProgressBar) dialog.findViewById(R.id.songbar);

        adapterListner1 = new AdapterListner1() {
            @Override
            public void onPlaymusic(int position) {

                tinydb.putBoolean(Constants.ISMUTEON_KEY, false);

                File file1 = getBaseContext().getExternalFilesDir("Music");
                if (!file1.exists())
                    file1.mkdir();
                getListFiles(file1);
                tinydb.putInt(Constants.SONGID_KEY, position);
                songid = tinydb.getInt(Constants.SONGID_KEY);
                if (position == 0) {
                    mBtprev.setAlpha(0.5f);
                } else {
                    mBtprev.setAlpha(1f);
                }
                int lenth = arrayList.size() - 1;
                if (position == lenth) {
                    mBtnext.setAlpha(0.5f);
                } else {
                    mBtnext.setAlpha(1f);
                }
                try {
                    if (mediaPlayer != null) {

                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.reset();
                        }

                    }
                    // songid = arrayList.size() - 1;
                    mediaPlayer = MediaPlayer.create(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, Uri.fromFile(arrayList.get(songid)));
                    mediaPlayer.setLooping(true);
                    mediaPlayer.setVolume(100, 100);
                    mediaPlayer.start();

                } catch (Exception e) {

                }
                File file = null;
                ChangeSong(file, songid, mIvsongart, mTvsongname, progressBar, mTvstarttime, mTvendtime);
                Log.e("Songpos", String.valueOf(songid));
            }
        };
        try {
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    try{
                        progressBar.setMax(mediaPlayer.getDuration());
                        mTvendtime.setText(hmsTimeFormatter(mediaPlayer.getDuration()));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            });
        } catch (Exception e) {

        }

        try {

            File file = (File) this.arrayList.get(songid);
            Log.e("filefilefile", String.valueOf(file));
            mTvsongname.setText(file.getName());
            // mIvsongart.setImageResource(songimgs[songid]);
            //  mTvsongname.setText(songnames[songid]);
//        mediaPlayer.start();
            progressBar.setProgress(0);
            MediaProgress(progressBar, mTvstarttime);
            if (!mediaPlayer.isPlaying()) {
                mBtplaypause.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                mBtplaypause.setColorFilter(getResources().getColor(R.color.white));
            } else {
                mBtplaypause.setImageResource(R.drawable.ic_baseline_pause_24);
            }
        } catch (Exception e) {

        }


        if (songid == 0) {
            mBtprev.setAlpha(0.5f);
        } else {
            mBtprev.setAlpha(1f);
        }
        int lenth = arrayList.size() - 1;
        if (songid == lenth) {
            mBtnext.setAlpha(0.5f);
        } else {
            mBtnext.setAlpha(1f);
        }

        mBtnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tinydb.putBoolean(Constants.ISMUTEON_KEY, false);
                int lenth = arrayList.size() - 1;
                if (songid == lenth) {
                    mBtnext.setAlpha(0.5f);
                } else {
                    mBtnext.setAlpha(1f);
                    tinydb.putInt(Constants.SONGID_KEY, songid + 1);
                    songid = tinydb.getInt(Constants.SONGID_KEY);
                    if (songid == lenth) {
                        mBtnext.setAlpha(0.5f);
                    }
                    ChangeSong(files_music, songid, mIvsongart, mTvsongname, progressBar, mTvstarttime, mTvendtime);
                }
                if (songid == 0) {
                    mBtprev.setAlpha(0.5f);
                } else {
                    mBtprev.setAlpha(1f);
                }
            }
        });

        mBtprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tinydb.putBoolean(Constants.ISMUTEON_KEY, false);
                if (songid > 0) {
                    mBtprev.setAlpha(1f);
                    tinydb.putInt(Constants.SONGID_KEY, songid - 1);
                    songid = tinydb.getInt(Constants.SONGID_KEY);
                    ChangeSong(files_music, songid, mIvsongart, mTvsongname, progressBar, mTvstarttime, mTvendtime);
                    if (songid == 0) {
                        mBtprev.setAlpha(0.5f);
                    }
                    try{
                        int lenth = arrayList.size() - 1;
                        if (songid == lenth) {
                            mBtnext.setAlpha(0.5f);
                        } else {
                            mBtnext.setAlpha(1f);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                } else {
                    mBtprev.setAlpha(0.5f);
                }

            }
        });
        mBtplaypause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (arrayList.size() == 0) {

                        Toast.makeText(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, "Please Download Music!..", Toast.LENGTH_SHORT).show();
                    }
                    if (mediaPlayer.isPlaying()) {
                        tinydb.putBoolean(Constants.ISMUTEON_KEY, false);
                        tinydb.putBoolean(Constants.ISMUSICON_KEY, true);
                        mBtplaypause.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                        mBtplaypause.setColorFilter(getResources().getColor(R.color.white));
                        mediaPlayer.pause();
                        ispause = true;

                        tinydb.putBoolean(Constants.isPAuse, true);
                    } else {
                        tinydb.putBoolean(Constants.ISMUTEON_KEY, false);
                        tinydb.putBoolean(Constants.ISMUSICON_KEY, false);
                        mBtplaypause.setImageResource(R.drawable.ic_baseline_pause_24);
                        mBtplaypause.setColorFilter(getResources().getColor(R.color.white));
                        mediaPlayer.start();
                        ispause = false;
                    }
                } catch (Exception e) {

                }
            }
        });
        try {

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    progressBar.setProgress(0);
                    MediaProgress(progressBar, mTvstarttime);
                }
            });
        } catch (Exception e) {

        }
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean hasPermissionLocation = (ContextCompat.checkSelfPermission(Activity_Exercisestart.this,
                        WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED);
                if (!hasPermissionLocation) {
                    ActivityCompat.requestPermissions(Activity_Exercisestart.this,
                            new String[]{WRITE_EXTERNAL_STORAGE},
                            111);
                } else {
                    try {


                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 1);
                    } catch (Exception e) {

                    }
                }
                /* adapter_musiclist = new Adapter_Musiclist(Activity_Exercisestart.this, (getListFiles(new File(DIRECTORY_TO_SAVE_MEDIA_NOW))),adapterListner);
                mRvsonglist.setLayoutManager(new LinearLayoutManager(Activity_Exercisestart.this, LinearLayout.VERTICAL, false));
                mRvsonglist.setAdapter(adapter_musiclist);
*/
              /*  adapter_musiclist1 = new Adapter_Musiclist1(Activity_Exercisestart.this, (getListFiles(new File(DIRECTORY_TO_SAVE_MEDIA_NOW))), adapterListner1);
                mRvsonglist1.setLayoutManager(new LinearLayoutManager(Activity_Exercisestart.this, LinearLayout.VERTICAL, false));
                mRvsonglist1.setAdapter(adapter_musiclist1);*/
            }
        });
        File file1 = this.getBaseContext().getExternalFilesDir("Music");
        if (!file1.exists())
            file1.mkdir();
        adapter_musiclist1 = new Adapter_Musiclist1(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, (getListFiles(file1)), adapterListner1);
        downloadsonglist.setLayoutManager(new LinearLayoutManager(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, LinearLayout.VERTICAL, false));
        downloadsonglist.setAdapter(adapter_musiclist1);
      /*  adapter_musiclist1.notifyDataSetChanged();
        adapter_musiclist.notifyDataSetChanged();*/
        try {
            mediaPlayer.setLooping(true);
        } catch (Exception e) {

        }

        adapter_musiclist = new Adapter_Musiclist(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, adapterListner1, _idarrayList, namearrayList, durationarrayList,
                musicarrayList, imagearrayList, createdAtarrayList, updatedAtAtarrayList, adapterListner, onlinesonglist, (getListFiles(file1)));
        onlinesonglist.setLayoutManager(new LinearLayoutManager(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, LinearLayout.VERTICAL, false));
        onlinesonglist.setAdapter(adapter_musiclist);





       /* Adapter_Musiclist adapter_musiclist = new Adapter_Musiclist(Activity_Exercisestart.this, (getListFiles(new File(DIRECTORY_TO_SAVE_MEDIA_NOW))),adapterListner);
        mRvsonglist.setLayoutManager(new LinearLayoutManager(Activity_Exercisestart.this, LinearLayout.VERTICAL, false));
        mRvsonglist.setAdapter(adapter_musiclist);*/
        //  this.recyclerView.setAdapter(new VideoDownloadedAdapter(getListFiles(new File(DIRECTORY_TO_SAVE_MEDIA_NOW)), this));
        // }

        dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (mBtnresume != null && !isPuasedall) {
                    if (!counttype) {
                        isPuasedall = true;
                        mBtnresume.setVisibility(View.VISIBLE);
                    }
                }
                if (isFromskiplay) {
                    Resumetimer();
                }
                dialog.cancel();
            }

        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (mBtnresume != null && !isPuasedall) {
                    if (!counttype) {
                        isPuasedall = true;
                        mBtnresume.setVisibility(View.VISIBLE);
                    }
                }
                if (isFromskiplay) {
                    Resumetimer();
                }
                dialog.cancel();
            }
        });
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (mBtnresume != null && !isPuasedall) {
                    if (!counttype) {
                        isPuasedall = true;
                        mBtnresume.setVisibility(View.VISIBLE);
                    }
                }
                if (isFromskiplay) {
                    Resumetimer();
                }

            }
        });
        dialog.setCanceledOnTouchOutside(true);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        try {
            dialog.getWindow().setWindowAnimations(R.style.DialogTheme);
            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

        dialog.getWindow().setAttributes(lp);
    }

    private void getMusicApi() {
        try {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(loggingInterceptor);

            OkHttpClient client = httpClient.build();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASEURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();

            ApiInterface apiInterface = retrofit.create(ApiInterface.class);

            apiInterface.getMusic().enqueue(new Callback<List<MusicApi>>() {


                @Override
                public void onResponse(@NotNull Call<List<MusicApi>> call, @NotNull Response<List<MusicApi>> response) {
                    try {
                        // loginData1.clear();
                        _idarrayList = new ArrayList<>();
                        namearrayList = new ArrayList<>();
                        durationarrayList = new ArrayList<>();
                        musicarrayList = new ArrayList<>();
                        imagearrayList = new ArrayList<>();
                        createdAtarrayList = new ArrayList<>();
                        updatedAtAtarrayList = new ArrayList<>();
                        loginData1 = response.body();
                        Log.e("responseresponse", String.valueOf(response.body()));

                        for (int i = 0; i < loginData1.size(); i++) {

                            if (loginData1.get(i).getMusic().contains(".")) {
                                extension1 = loginData1.get(i).getMusic().substring(loginData1.get(i).getMusic().lastIndexOf("."));
                            }


                            File file1 = getBaseContext().getExternalFilesDir("Music");
                            if (!file1.exists())
                                file1.mkdir();
                            // String path = Environment.getExternalStorageDirectory() + "/Music/DrZio/" + loginData1.get(i).getName() + extension1;
                            String path = file1 + loginData1.get(i).getName() + extension1;

                            file = new File(file1, loginData1.get(i).getName() + extension1);
                            if (file.exists()) {
                                Log.e("extst", String.valueOf(file));
                                _idarrayList.remove(loginData1.get(i).getId());
                                namearrayList.remove(loginData1.get(i).getName());
                                durationarrayList.remove(loginData1.get(i).getDuration());
                                musicarrayList.remove(loginData1.get(i).getMusic());
                                imagearrayList.remove(loginData1.get(i).getImage());
                                createdAtarrayList.remove(loginData1.get(i).getCreatedAt());
                                updatedAtAtarrayList.remove(loginData1.get(i).getUpdatedAt());
                            } else /*if (!file.getName().equals(namearrayList.get(i)))*/ {
                                Log.e("extstnot", String.valueOf(file));
                                _idarrayList.add(loginData1.get(i).getId());
                                namearrayList.add(loginData1.get(i).getName());
                                durationarrayList.add(loginData1.get(i).getDuration());
                                musicarrayList.add(loginData1.get(i).getMusic());
                                imagearrayList.add(loginData1.get(i).getImage());
                                createdAtarrayList.add(loginData1.get(i).getCreatedAt());
                                updatedAtAtarrayList.add(loginData1.get(i).getUpdatedAt());
                            }
                         /*   String s = loginData1.get(i).getName();
                            Log.e("musicname", String.valueOf(s));
                            if (loginData1.get(i).getMusic().contains(".")) {
                                extension1 = loginData1.get(i).getMusic().substring(loginData1.get(i).getMusic().lastIndexOf("."));
                            }
                            String path = Environment.getExternalStorageDirectory() + "/DrZio/" + loginData1.get(i).getName() + extension1;

                            file = new File(path);
                        *//*    if (file.getName().equals(loginData1.get(i).getName())) {
                                Log.e("musicresarrayList", String.valueOf(loginData1.get(i)));
                            }*//*
                            if (file.exists()) {
                                Log.e("extst", String.valueOf(file));
                                //   musicresarrayList.remove(i);
                                Log.e("musicresarrayList", String.valueOf(musicresarrayList.get(i).getName()));
                            } else {
                                Log.e("extstnot", String.valueOf(file));
                                Log.e("musicresarrayListnot", String.valueOf(musicresarrayList.get(i).getName()));

                                _idarrayList.add(loginData1.get(i).getId());
                               namearrayList .add(loginData1.get(i).getName());
                                 durationarrayList .add(loginData1.get(i).getDuration());
                              musicarrayList .add(loginData1.get(i).getMusic());
                                 imagearrayList .add(loginData1.get(i).getImage());
                               createdAtarrayList .add(loginData1.get(i).getCreatedAt());
                                updatedAtAtarrayList .add(loginData1.get(i).getUpdatedAt());
                            }*/

                        }
                        File file1 = getBaseContext().getExternalFilesDir("Music");
                        if (!file1.exists())
                            file1.mkdir();
                        adapter_musiclist = new Adapter_Musiclist(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, adapterListner1, _idarrayList, namearrayList, durationarrayList,
                                musicarrayList, imagearrayList, createdAtarrayList, updatedAtAtarrayList, adapterListner, onlinesonglist, (getListFiles(file1)));
                        onlinesonglist.setLayoutManager(new LinearLayoutManager(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, LinearLayout.VERTICAL, false));
                        onlinesonglist.setAdapter(adapter_musiclist);

                        adapter_musiclist1.notifyDataSetChanged();
                        adapter_musiclist.notifyDataSetChanged();

                        /*  adapter_musiclist = new Adapter_Musiclist(Activity_Exercisestart.this, loginData1, adapterListner, onlinesonglist, (getListFiles(new File(DIRECTORY_TO_SAVE_MEDIA_NOW))));
                        onlinesonglist.setLayoutManager(new LinearLayoutManager(Activity_Exercisestart.this, LinearLayout.VERTICAL, false));
                        onlinesonglist.setAdapter(adapter_musiclist);*/


                    } catch (Exception e) {
                        Log.e("responseresponse", String.valueOf(e));
                    }


                }

                @Override
                public void onFailure
                        (@NotNull Call<List<MusicApi>> call, @NotNull Throwable t) {

                    Log.e("fail_error", String.valueOf(t));

                }

            });
        } catch (Exception e) {
            e.printStackTrace();


        }
    }

    ArrayList<String> musiclist = new ArrayList<>();
    private String extension1;


    public void ChangeSong(File file, int songid, ImageView mIvsongart, TextView
            mTvsongname, RoundCornerProgressBar progressBar, TextView mTvstarttime, TextView mTvendtime) {
        //  mIvsongart.setImageResource(songimgs[i]);

        //mTvsongname.setText(songnames[i]);
        try {
            File files = (File) this.arrayList.get(songid);
            Log.e("filefilefile", String.valueOf(files));
            mTvsongname.setText(files.getName());
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, Uri.fromFile(arrayList.get(songid)));
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    progressBar.setProgress(0);
                    progressBar.setMax(mediaPlayer.getDuration());
                    mTvendtime.setText(hmsTimeFormatter(mediaPlayer.getDuration()));
                }
            });

            mediaPlayer.start();
            try {
                if (!mediaPlayer.isPlaying()) {
                    mBtplaypause.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    mBtplaypause.setColorFilter(getResources().getColor(R.color.white));
                } else {
                    mBtplaypause.setImageResource(R.drawable.ic_baseline_pause_24);
                }
            } catch (Exception e) {

            }

        } catch (Exception e) {
        }
        MediaProgress(progressBar, mTvstarttime);

    }

    public void MediaProgress(RoundCornerProgressBar progressBar, TextView mTvstarttime) {
        progressBar.setProgress(0);
        Handler hdlr = new Handler();
        new Thread(new Runnable() {
            public void run() {
                try {
                    int currentPosition = mediaPlayer.getCurrentPosition();
                    int total = mediaPlayer.getDuration();
                    while (currentPosition < total) {
                        hdlr.post(new Runnable() {
                            public void run() {
                                try {
                                    progressBar.setProgress(mediaPlayer.getCurrentPosition());
                                    mTvstarttime.setText(hmsTimeFormatter(mediaPlayer.getCurrentPosition()));
                                } catch (Exception e) {

                                }
                            }
                        });
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private ArrayList<File> getListFiles(File file) {

        File[] listFiles = file.listFiles();
        if (listFiles != null) {
            for (File file2 : listFiles) {
                if ((file2.getName().endsWith(".mp3") || file2.getName().endsWith(".ogg") || file2.getName().endsWith(".aac")) && !arrayList.contains(file2)) {
                    arrayList.add(file2);
                }
            }
        }
        Log.e("arrayList", String.valueOf(arrayList));
        return arrayList;
    }

   /* public void Pauselayout(int val) {
        try {
            mMainlayout.setVisibility(View.GONE);
            mBtnResume = (ImageView) findViewById(R.id.btnpauseclose);
            mPausedworkout = (TextView) findViewById(R.id.pauseworkoutname);
            mWorkoutdesc = (TextView) findViewById(R.id.pauseworkoutdesc);
            mWorkoutdesc.setText(excerciseDataList.get(mPosition).getExedescription());
            mVidanimbtn = (LinearLayout) findViewById(R.id.vidanimbtn);
            mVidanimiv = (ImageView) findViewById(R.id.icvidanim);
            mVidanimtv = (TextView) findViewById(R.id.tvvidanim);
            mAminlayout = (RelativeLayout) findViewById(R.id.animlay);
            mAminlayout.setVisibility(View.VISIBLE);
            mVidframe = (FrameLayout) findViewById(R.id.videolay);
            mVidframe.setVisibility(View.VISIBLE);
            mPuaseexevidview = (VideoView) findViewById(R.id.tempvidoe);
            mPuaseexevidview.setVisibility(View.VISIBLE);
            if (mPuaseexevidview.isPlaying()) {
                mPuaseexevidview.pause();
                mPuaseexevidview.requestFocus();
            }

            String languages = tinydb.getString(Constants.Language);
            TranslateAPI translateAPI = new TranslateAPI(
                    Language.AUTO_DETECT, languages,
                    // Language.HINDI,
                    excerciseDataList.get(mPosition).getExedescription());

            translateAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
                @Override
                public void onSuccess(String translatedText) {
                    Log.d("TAG", "onSuccess: " + translatedText);
                    //  textView.setText(translatedText);
                    mWorkoutdesc.setText(translatedText);
                    t1 = new TextToSpeech(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, new TextToSpeech.OnInitListener() {
                        @Override
                        public void onInit(int status) {
                            if (status != TextToSpeech.ERROR) {
                                //  t1.setLanguage(Locale.UK);
                                Locale locale = new Locale("hi_IN");

                                t1.setLanguage(locale);
                                t1.speak(translatedText, TextToSpeech.QUEUE_FLUSH, null);

                            }
                        }
                    });

                }

                @Override
                public void onFailure(String ErrorText) {
                    Log.d("TAG", "onFailure: " + ErrorText);
                }
            });

            mPuaseexevidview.animate().alpha(1);
            Uri video = Uri.parse(excerciseDataList.get(mPosition).getExecerciseimaga());
            mPuaseexevidview.setVideoURI(video);
            mPuaseexevidview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                    mPuaseexevidview.start();
                }
            });
            mYoutubeview = (YouTubePlayerView) findViewById(R.id.youtube_view);
            mYoutubeview.initialize(Constants.YoutubeApi, new YouTubePlayer.OnInitializedListener() {
                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                    if (!b) {
                        player = youTubePlayer;
                        player.setShowFullscreenButton(false);
                        youTubePlayer.cueVideo(excerciseDataList.get(mPosition).getYouvideo());
                    }
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

                }
            });
            String temp = excerciseDataList.get(mPosition).getExecerciseduration();
            temp = temp.replace("00:", "");
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(excerciseDataList.get(mPosition).getExecercisename().toUpperCase());
            stringBuilder.append(' ');
            stringBuilder.append(temp);
            stringBuilder.append("s");
            mPausedworkout.setText(stringBuilder.toString());
            mBtnResume.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isClicked && !isAdshown) {
                        Gad = 2;
                        if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
                            isAdshown = true;
                            mInterstitialAdMob.show();
                        } else {
                            ClosePauseact();
                        }
                    } else {
                        ClosePauseact();
                    }
                }
            });
            if (val == 1) {
                if (mNativecontainer != null) {
                    mNativecontainer.setVisibility(View.GONE);
                }
                mVidanimbtn.setBackgroundResource(R.drawable.btnpuasebg);
                mVidanimiv.setImageResource(R.drawable.ic_image_black_24dp);
                mVidanimtv.setText(getResources().getString(R.string.animation));
                mAminlayout.setVisibility(View.GONE);
                mPuaseexevidview.setVisibility(View.GONE);
                if (mPuaseexevidview != null && mPuaseexevidview.isPlaying()) {
                    mPuaseexevidview.animate().alpha(0);
                    mPuaseexevidview.pause();
                }
                isClicked = true;
            } else {
                if (mSkipVidviews != null && mSkipVidviews.isPlaying()) {
                    mSkipVidviews.setVisibility(View.GONE);
                }
                if (mVidview != null && mVidview.isPlaying()) {
                    mVidview.setVisibility(View.GONE);
                }
                if (mNativecontainer != null) {
                    mNativecontainer.setVisibility(View.VISIBLE);
                }
                mVidanimbtn.setBackgroundResource(R.drawable.btnpuasebg);
                mVidanimiv.setImageResource(R.drawable.ic_videocam_black_24dp);
                mVidanimtv.setText(getResources().getString(R.string.video));
                mAminlayout.setVisibility(View.VISIBLE);
                mPuaseexevidview.animate().alpha(1);
                mPuaseexevidview.setVisibility(View.VISIBLE);
                if (mPuaseexevidview != null && !mPuaseexevidview.isPlaying()) {
                    mPuaseexevidview.start();
                }
                isClicked = false;
            }
            mVidanimbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!isClicked) {
                        if (mNativecontainer != null) {
                            mNativecontainer.setVisibility(View.GONE);
                        }
                        mVidanimbtn.setBackgroundResource(R.drawable.btnpuasebg);
                        mVidanimiv.setImageResource(R.drawable.ic_image_black_24dp);
                        mVidanimtv.setText(getResources().getString(R.string.animation));
                        mPuaseexevidview.animate().alpha(0);
                        mPuaseexevidview.setVisibility(View.GONE);
                        mAminlayout.setVisibility(View.GONE);
                        if (mPuaseexevidview != null && mPuaseexevidview.isPlaying()) {
                            mPuaseexevidview.pause();
                        }
                        if (player != null) {
                            player.play();
                        }
                        isClicked = true;
                    } else {
                        if (mNativecontainer != null) {
                            mNativecontainer.setVisibility(View.VISIBLE);
                        }
                        mVidanimbtn.setBackgroundResource(R.drawable.btnpuasebg);
                        mVidanimiv.setImageResource(R.drawable.ic_videocam_black_24dp);
                        mVidanimtv.setText(getResources().getString(R.string.video));
                        mPuaseexevidview.animate().alpha(1);
                        mPuaseexevidview.setVisibility(View.VISIBLE);
                        mAminlayout.setVisibility(View.VISIBLE);
                        if (mPuaseexevidview != null && !mPuaseexevidview.isPlaying()) {
                            mPuaseexevidview.start();
                        }
                        if (player != null) {
                            player.pause();
                        }
                        isClicked = false;
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
*/
/*
    public void ClosePauseact() {
        if (isMain) {
            mMainlayout.setVisibility(View.VISIBLE);
            if (mVidview != null) {
                mVidview.setVisibility(View.VISIBLE);
            }
        } else if (isRest) {
            mMainlayout.setVisibility(View.GONE);
            if (mVidview != null) {
                mVidview.setVisibility(View.VISIBLE);
            }
            if (mTvrestresume != null) {
                mTvrestresume.setClickable(true);
                isPuasedall = true;
                mTvrestresume.setVisibility(View.VISIBLE);
            }
            mRestlayout.setVisibility(View.VISIBLE);
        } else if (isLearn) {
            mMainlayout.setVisibility(View.VISIBLE);
            if (mVidview != null) {
                mVidview.setVisibility(View.VISIBLE);
            }
        } else {
            mSkiplayout.setVisibility(View.VISIBLE);
            if (mSkipVidviews != null) {
                mSkipVidviews.setVisibility(View.VISIBLE);
            }
        }
        if (player != null) {
            player.release();
        }
        if (mPuaseexevidview != null && mPuaseexevidview.isPlaying()) {
            mPuaseexevidview.pause();
            mPuaseexevidview.setVisibility(View.GONE);
            mPuaseexevidview.animate().alpha(0);
        }
        mVidframe.setVisibility(View.GONE);
        mAminlayout.setVisibility(View.GONE);

                mPauselayout.setVisibility(View.GONE);
                banner1.setVisibility(View.VISIBLE);
        if (!isPuasedall) {
            Resumetimer();
        }
    }*/


    public void Execercisetimer(long j) {

        CountDownTimer r2 = new CountDownTimer(j, 1000) {
            public void onFinish() {
                readyToGoTimer.cancel();
            }

            public void onTick(long j) {
                mSkiplayouttimes = j;
                long j2 = j - 1000;
                long j3 = j2 / 1000;
                f4959s1 = j;
            }
        };
        this.readyToGoTimer = r2.start();
    }

    public void setupskipporgress() {
        isMain = false;
        isLearn = false;
        isRest = false;

        this.timeCountInMilliSeconds4 = (int) (10 * 1000.0f);
        this.mSkipprogressbar.setMaxProcess(this.timeCountInMilliSeconds4 / 50);
        mSkipprogressbar.setCurProcess(timeCountInMilliSeconds4 / 1000);
        Execercisetimer(11000);
        startSkipTimer(timeCountInMilliSeconds4);
    }

    private void startSkipTimer(long time) {

        int count = (int) (time / 1000);
        mTvSkcount.setVisibility(View.VISIBLE);
        countDownAnimation3 = new CountDownAnimation(mTvSkcount, count, tinydb, fitnessApplication);
        countDownAnimation3.setCountDownListener(this);
        Animation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f,
                0.0f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        countDownAnimation3.setAnimation(animationSet);
        countDownAnimation3.setStartCount(count);
        countDownAnimation3.start();


        String lowercase = excerciseDataList.get(mPosition).getExecercisename().replace("_", " ").toLowerCase();
        if (time == 10000) {
            if (tinydb.getBoolean(Constants.ISGUIDEON_KEY) && !tinydb.getBoolean(Constants.ISMUTEON_KEY)) {
                fitnessApplication.speak(getString(R.string.ready_to_go));
                StringBuilder sb = new StringBuilder();
                sb.append(getString(R.string.startspeech));
                sb.append(lowercase);
                fitnessApplication.speak(sb.toString());
            }
        }
        this.skipexercisetimer = new CountDownTimer(time, 50) {
            public void onTick(long millisUntilFinished) {
                isFromskiplay = true;
                mSkipprogress = millisUntilFinished;
                mTxtcounter.setText(TimeFormatter(millisUntilFinished));
                mSkipprogressbar.setCurProcess((int) (millisUntilFinished / 50));
            }

            public void onFinish() {
                if (countDownAnimation3 != null) {
                    countDownAnimation3.cancel();
                }
                isFromskiplay = false;
                if (mSkipVidviews.isPlaying()) {
                    mSkipVidviews.pause();
                    mSkipVidviews.requestFocus();
                }
                readyToGoTimer.cancel();
                skipexercisetimer.cancel();
                f4961u = Boolean.FALSE;
                mSkipprogressbar.setCurProcess(0);
                mSkiplayout.setVisibility(View.GONE);
                mMainlayout.setVisibility(View.VISIBLE);
                Mainlayout();
            }
        }.start();
    }


    private void Mainlayout() {
        Conrolslayout(1);
        mSkipVidviews.animate().alpha(0);
        mSkipVidviews.setVisibility(View.GONE);
        if (mSkipVidviews != null && mSkipVidviews.isPlaying()) {
            mSkipVidviews.invalidate();
        }
        this.mBar = (CircleSeekBar) findViewById(R.id.bar);
        this.mTextprogress = (TextView) findViewById(R.id.txt_progress);
        mTvexename = (TextView) findViewById(R.id.excName);
        mAdframe = (LinearLayout) findViewById(R.id.fbnative);
        mAdframe.setVisibility(View.GONE);
        mTvcurrexe = (TextView) findViewById(R.id.currentexe);
        mTvtotalexe = (TextView) findViewById(R.id.totalexe);
        mBtnresume = (ImageView) findViewById(R.id.btnresume);
        mBtnpause = (ImageView) findViewById(R.id.btnpuase);
        mBtnNextexe = (ImageView) findViewById(R.id.btn_nxt);
        mBtnpause.setImageResource(R.drawable.ic_pause_black_24dp);
        ImageView mBtnclose = (ImageView) findViewById(R.id.imgback2);
        mBtnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        mReadygotxt = (TextView) findViewById(R.id.mcounttext);
        mVidview = (VideoView) findViewById(R.id.vidviewvid);
        if (mVidview.isPlaying()) {
            mVidview.pause();
            mVidview.requestFocus();
        }
        String upperCase = excerciseDataList.get(mPosition).getExecercisename().toUpperCase();
        mTvexename.setText(upperCase);
        mTvtotalexe.setText(String.valueOf(excerciseDataList.size()));
        int tempint = mPosition + 1;
        mTvcurrexe.setText(String.valueOf(tempint));
        mBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMain) {
                    if (ismainstarted) {
                        isPuasedall = true;
                        mBtnpause.setImageResource(R.drawable.ic_stop_black_24dp);
                        mBar.setAlpha(0.5f);
                        Puasetime();
                        try {
                            if (mediaPlayer.isPlaying()) {
                                mediaPlayer.pause();
                            }
                        } catch (Exception e) {

                        }
                        mVidview.pause();
                        mBtnresume.setVisibility(View.VISIBLE);
                    }
                } else {
                    isPuasedall = true;
                    mBtnpause.setImageResource(R.drawable.ic_stop_black_24dp);
                    mBar.setAlpha(0.5f);
                    Puasetime();
                    mVidview.pause();
                    try {
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.pause();
                        }
                    } catch (Exception e) {

                    }
                    mBtnresume.setVisibility(View.VISIBLE);
                }
            }
        });
        mBtnresume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPuasedall = false;
                mBtnresume.setVisibility(View.GONE);
                mBar.setAlpha(1.0f);
                mBtnpause.setImageResource(R.drawable.ic_pause_black_24dp);
                Resumetimer();
                mVidview.resume();
                try {
                    if (!tinydb.getBoolean(Constants.ISMUTEON_KEY)) {
                        if (!tinydb.getBoolean(Constants.ISMUSICON_KEY)) {
//                tinydb.putBoolean(Constants.ISMUSICON_KEY, false);
//                mCinfomusic.startAnimation(rotate);
                            try {
                                mediaPlayer.start();
                            } catch (Exception e) {

                            }


                        }
                    }
                } catch (Exception e) {

                }
            }
        });
        mBtnpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isMain) {
                    if (ismainstarted) {
                        isPuasedall = true;
                        mBtnpause.setImageResource(R.drawable.ic_stop_black_24dp);
                        mBar.setAlpha(0.5f);
                        Puasetime();
                        mVidview.pause();
                        try {
                            if (mediaPlayer.isPlaying()) {
                                mediaPlayer.pause();
                            }
                        } catch (Exception e) {

                        }
                        mBtnresume.setVisibility(View.VISIBLE);
                    }
                } else {
                    isPuasedall = true;
                    mBtnpause.setImageResource(R.drawable.ic_stop_black_24dp);
                    mBar.setAlpha(0.5f);
                    Puasetime();
                    mVidview.pause();
                    try {
                        if (mediaPlayer.isPlaying()) {
                            mediaPlayer.pause();
                        }
                    } catch (Exception e) {

                    }
                    mBtnresume.setVisibility(View.VISIBLE);
                }
            }
        });

        mBtnNextexe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isPuasedall) {
                    if (isMain) {
                        if (mPosition < excerciseDataList.size()) {
                            if (!ismainstarted) {
                                mMainhandler.removeCallbacks(mMainrunnable);
                            }
                            Puasetime();
                            if (mPosition % REST_TAG == Tagpos) {
                                Round++;
                                if (Round == 3) {
                                    Nxtprocesses2(1);
                                } else {
                                    Nxtprocesses2(2);
                                }
                            } else {
                                Nxtprocesses2(1);
                            }
                        } else {
                            Nxtprocesses2(1);
                        }
                    } else {
                        Puasetime();
                        startmain();
                    }
                } else {
                    Toast.makeText(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, getResources().getString(R.string.press_the_resume), Toast.LENGTH_SHORT).show();
                }
            }
        });
        startmain();
    }

    public void Resumetimer() {
        if (isFromskiplay) {
            mSkiplayout.setVisibility(View.VISIBLE);
            startSkipTimer(mSkipprogress);
            isFromskiplay = false;
        } else if (isLearn) {
            startLearnTimer(mLearnprogress);
        } else if (isMain) {
            if (!counttype) {
                startCountDownTimer(mMainprogress);
            }
        } else if (isRest) {
            startPauseTimer(mPauseprogress);
            NextSpeaktimer(mSkiplayouttimes);
        }
    }

    public void Puasetime() {
        if (mSkiplayout.getVisibility() == View.VISIBLE) {
            readyToGoTimer.cancel();
            skipexercisetimer.cancel();
            if (countDownAnimation3 != null) {
                countDownAnimation3.cancel();
            }
        } else if (isMain) {
            if (exercisetimer != null && !counttype) {
                exercisetimer.cancel();
            }
        } else if (isLearn) {
            if (learnexercisetimer != null) {
                learnexercisetimer.cancel();
            }
            if (countDownAnimation2 != null) {
                countDownAnimation2.cancel();
            }
        } else if (isRest) {
            if (nextexercisetimer != null) {
                readyToGoTimer.cancel();
                nextexercisetimer.cancel();
            }
        }
    }


    public void Learnprocesses() {
        ismainstarted = false;
        setLearnprogress();
    }

    public void setLearnprogress() {
        try {
            if (mPosition == excerciseDataList.size() - 1) {
                if (mBtnNextexe != null) {
                    mBtnNextexe.setVisibility(View.GONE);
                }
            }
            isMain = false;
            isRest = false;
            isLearn = true;
            String upperCase = excerciseDataList.get(mPosition).getExecercisename().toUpperCase();
            mTvexename.setText(upperCase);
            int tempint = mPosition + 1;
            mVidview.animate().alpha(1);
            try {
                mVidview.setVisibility(View.VISIBLE);
                Uri video = Uri.parse(excerciseDataList.get(mPosition).getExecerciseimaga());
                mVidview.setVideoURI(video);
                mVidview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setLooping(true);
                        mVidview.start();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

            mTvcurrexe.setText(String.valueOf(tempint));
            mBar.setReachedColor(getResources().getColor(R.color.scolor2), getResources().getColor(R.color.scolor2));
            this.timeCountInMilliSeconds3 = (int) (mLearntime * 1000.0f);
            this.mBar.setMaxProcess(this.timeCountInMilliSeconds3 / 50);
            mBar.setCurProcess(timeCountInMilliSeconds3 / 1000);
            startLearnTimer(timeCountInMilliSeconds3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startLearnTimer(long time) {
        this.learnexercisetimer = new CountDownTimer(time, 50) {
            public void onTick(long millisUntilFinished) {
                mLearnprogress = millisUntilFinished;
                mTextprogress.setText(TimeFormatter(millisUntilFinished));
                mBar.setCurProcess((int) (millisUntilFinished / 50));

            }

            public void onFinish() {
                mReadygotxt.setVisibility(View.GONE);
                learnexercisetimer.cancel();
                startmain();
            }
        }.start();
        int count = (int) (time / 1000);
        mReadygotxt.setTextSize(60);
        countDownAnimation2 = new CountDownAnimation2(mReadygotxt, count, tinydb, fitnessApplication);
        countDownAnimation2.setCountDownListener(this);
        Animation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f,
                0.0f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        Animation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        countDownAnimation2.setAnimation(animationSet);
        countDownAnimation2.setStartCount(count);
        countDownAnimation2.start();
    }


    private void startmain() {
        isRest = false;
        isMain = true;
        isLearn = false;
        setTimerValues();
        mMainhandler = new Handler();
        mMainrunnable = new Runnable() {
            @Override
            public void run() {
                try {
                    startCountDownTimer(timeCountInMilliSeconds);
                    if (tinydb.getBoolean(Constants.ISTIPSON_KEY) && !tinydb.getBoolean(Constants.ISMUTEON_KEY)) {
                        ConnectivityManager mgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                        NetworkInfo netInfo = mgr.getActiveNetworkInfo();
                        if (netInfo != null) {
                            if (netInfo.isConnected()) {
                                String languages = tinydb.getString(Constants.Language);
                                TranslateAPI translateAPI = new TranslateAPI(
                                        Language.AUTO_DETECT, languages,
                                        //Language.HINDI,
                                        excerciseDataList.get(mPosition).getExedescription());

                                translateAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
                                    @Override
                                    public void onSuccess(String translatedText) {
                                        Log.d("TAG", "onSuccess: " + translatedText);
                                        //  textView.setText(translatedText);
                                        // mCworkdesc.setText(translatedText);
                                        fitnessApplication.speak(translatedText);

                                    }

                                    @Override
                                    public void onFailure(String ErrorText) {
                                        Log.d("TAG", "onFailure: " + ErrorText);
                                    }
                                });


                            } else {
                                fitnessApplication.speak(excerciseDataList.get(mPosition).getExedescription());
                            }
                        } else {
                            fitnessApplication.speak(excerciseDataList.get(mPosition).getExedescription());
                        }
                    /*    String languages = tinydb.getString(Constants.Language);
                        TranslateAPI translateAPI = new TranslateAPI(
                                Language.AUTO_DETECT, languages,
                                //Language.HINDI,
                                excerciseDataList.get(mPosition).getExedescription());

                        translateAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
                            @Override
                            public void onSuccess(String translatedText) {
                                Log.d("TAG", "onSuccess: " + translatedText);
                                //  textView.setText(translatedText);
                                // mCworkdesc.setText(translatedText);
                                fitnessApplication.speak(translatedText);

                            }

                            @Override
                            public void onFailure(String ErrorText) {
                                Log.d("TAG", "onFailure: " + ErrorText);
                            }
                        });*/
                        // fitnessApplication.speak(excerciseDataList.get(mPosition).getExedescription());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        mMainhandler.postDelayed(mMainrunnable, 1200);
        if (mPosition == 0 || excCouner != 0) {
            mVidview.animate().alpha(1);
            Uri video = Uri.parse(excerciseDataList.get(mPosition).getExecerciseimaga());
            mVidview.setVideoURI(video);
            mVidview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.setLooping(true);
                    mVidview.start();
                }
            });
        }
    }

    private void setTimerValues() {
        try {
            String temp = excerciseDataList.get(mPosition).getExecerciseduration();
            temp = temp.replace("00:", "");
            float tempsecond = Float.parseFloat(temp);
            mTextprogress.setText(temp);
            this.timeCountInMilliSeconds = (int) (tempsecond * 1000.0f);
            mBar.setReachedColor(getResources().getColor(R.color.ecolor1), getResources().getColor(R.color.scolor2));
            setProgressBarValues();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void setProgressBarValues() {
        this.mBar.setMaxProcess(this.timeCountInMilliSeconds / 50);
        this.mBar.setCurProcess(this.timeCountInMilliSeconds / 1000);
        ValueAnimator anim = ValueAnimator.ofInt(0, mBar.getMaxProcess());
        anim.setDuration(1000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animProgress = (Integer) animation.getAnimatedValue();
                mBar.setCurProcess(animProgress);
            }
        });
        anim.start();
    }

    private void startCountDownTimer(long time) {
        this.exercisetimer = new CountDownTimer(time, 50) {
            public void onTick(long millisUntilFinished) {
                ismainstarted = true;
                mMainprogress = millisUntilFinished;
                mTextprogress.setText(TimeFormatter(millisUntilFinished));
                mBar.setCurProcess((int) (millisUntilFinished / 50));
            }

            public void onFinish() {
                mVidview.animate().alpha(0);
                ismainstarted = false;
                if (mPosition % REST_TAG == Tagpos) {
                    Round++;
                    if (Round == 3) {
                        Nxtprocesses(1);
                    } else {
//                        setuprestporgress();
                        Nxtprocesses(2);
                    }
                } else {
                    Nxtprocesses(1);
                }
            }
        }.start();
    }

    public void Nxtprocesses(int i) {
        isRest = true;
        isLearn = false;
        mAdframe.setVisibility(View.GONE);
        mMainlayout.setVisibility(View.GONE);
        mRestlayout.setVisibility(View.VISIBLE);
        ismainstarted = false;
        mRestnative.setVisibility(View.VISIBLE);
        mTvroundname = (TextView) findViewById(R.id.tvrounds);
        mTvrestnextexe = (TextView) findViewById(R.id.tvnexename);
        mTvrestexecounts = (TextView) findViewById(R.id.tvexecount);
        mTvrestbtnskip = (TextView) findViewById(R.id.tvbtnskip);
        mIvrestexe = (ImageView) findViewById(R.id.ivrestexe);
        mTvrestresume = (ImageView) findViewById(R.id.ivrestresume);
        mRestBar = (CircleSeekBar) findViewById(R.id.restbar);
        txte = findViewById(R.id.txte);
        mTvrestprogress = (TextView) findViewById(R.id.tvrestprogress);
        mPuasebottom = (RelativeLayout) findViewById(R.id.nxtbottombar);
        mExecrsicedoneprogress = mExecrsicedoneprogress + (timeCountInMilliSeconds);
        mfinalprogress = (timeCountInMilliSeconds);
        mGiveupprogress = mGiveupprogress + (timeCountInMilliSeconds);
        Totalcaleri();
        TotalProgress();
        mTotalexe++;
        mAllexe++;
        tinydb.putInt(Constants.TOTALEXE_KEY, mAllexe);
        mPosition++;
        if (mPosition == excerciseDataList.size()) {
            Gad = 1;
            if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
                mInterstitialAdMob.show();
            } else {
                Execomplete();
            }
        } else {
            setpauseprogress(i);
        }
    }

    public void Execomplete() {
        isComplete = true;
        tinydb.putInt(Constants.TOTALEXE_KEY, mAllexe);
        historydata(1);
        if (excCouner != 0) {
            mfinalprogress = mGiveupprogress + mfinalprogress;
        }
        databaseOperations.insertExcCounter(dayname, 1, mLevel);
        databaseOperations.insertDayComplete(dayname, 1, mLevel);
        databaseOperations.insertExcDayData(dayname, mfinalprogress, mLevel);
        tinydb.putFloat(Constants.TOTALKCAL_KEY, totalcalori);
        String totalexe = String.valueOf(tinydb.getInt(Constants.TOTALEXE_KEY));
        String calaory = String.valueOf(round(tinydb.getFloat(Constants.TOTALKCAL_KEY), 1));


        Constants.UpdatTime(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, String.valueOf("coins"), String.valueOf(mExecrsicedoneprogress), totalexe, calaory);

        Intent intent = new Intent(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, Activity_Completion.class);
        intent.putExtra("totalexe", mTotalexe);
        intent.putExtra("dname", dayname);
        intent.putExtra("duration", String.valueOf(mExecrsicedoneprogress));
        intent.putExtra("daykcal", daycalorie);
        intent.putExtra("isactive", true);
        intent.putExtra("planname", excerciseDataList.get(1).getPlanname());
        intent.putExtra("level", mLevel);
        startActivity(intent);
        finish();
    }

    public void Nxtprocesses2(int i) {
        if (!ismainstarted) {
            String temp = excerciseDataList.get(mPosition).getExecerciseduration();
            temp = temp.replace("00:", "");
            float tempsecond = Float.parseFloat(temp);
            this.timeCountInMilliSeconds = (int) (tempsecond * 1000.0f);
        }
        if (mSpecktimer != null) {
            mSpecktimer.cancel();
        }
        mAdframe.setVisibility(View.GONE);
        mMainlayout.setVisibility(View.GONE);
//        mControlayout.setVisibility(View.GONE);
        mRestlayout.setVisibility(View.VISIBLE);
        mRestnative.setVisibility(View.VISIBLE);
        mTvroundname = (TextView) findViewById(R.id.tvrounds);
        mTvrestnextexe = (TextView) findViewById(R.id.tvnexename);
        mTvrestexecounts = (TextView) findViewById(R.id.tvexecount);
        mTvrestbtnskip = (TextView) findViewById(R.id.tvbtnskip);
        mIvrestexe = (ImageView) findViewById(R.id.ivrestexe);
        mTvrestresume = (ImageView) findViewById(R.id.ivrestresume);
        mRestBar = (CircleSeekBar) findViewById(R.id.restbar);
        txte = findViewById(R.id.txte);
        mPuasebottom = (RelativeLayout) findViewById(R.id.nxtbottombar);
        mTvrestprogress = (TextView) findViewById(R.id.tvrestprogress);
        if (excCouner != 0) {
            temp2 = excCouner - 1;
        } else {
            temp2 = excCouner;
        }
        Totalcaleri2();
        TotalProgress2();
        mAllexe++;
        tinydb.putInt(Constants.TOTALEXE_KEY, mAllexe);
//        if (!isRest) {
        long progress = (long) (timeCountInMilliSeconds);
        long temp;
        if (ismainstarted) {
            temp = progress - mMainprogress;
        } else {
            temp = 0;
        }
        mExecrsicedoneprogress = mExecrsicedoneprogress + temp;
        Log.e("doneprogress " + mPosition, String.valueOf(mExecrsicedoneprogress));
        mfinalprogress = mfinalprogress + (timeCountInMilliSeconds);
        mGiveupprogress = mGiveupprogress + (timeCountInMilliSeconds);
        String totalexe = String.valueOf(tinydb.getInt(Constants.TOTALEXE_KEY));
        String calaory = String.valueOf(round(tinydb.getFloat(Constants.TOTALKCAL_KEY), 1));


        Constants.UpdatTime(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, String.valueOf("coins"), String.valueOf(mExecrsicedoneprogress), totalexe, calaory);

        Log.e("giveupprogrees", "  " + mGiveupprogress + "tempprogress " + timeCountInMilliSeconds);
//        }
        fitnessApplication.stop();
        mTotalexe++;
        mPosition++;
        ismainstarted = false;
        if (mPosition == excerciseDataList.size()) {
            if (!isbtnclicked) {
                isbtnclicked = true;
                Gad = 1;
                if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
                    mInterstitialAdMob.show();
                } else {
                    Execomplete();
                }
            }

        } else {
            fitnessApplication.stop();
            databaseOperations.insertExcDayData(dayname, mGiveupprogress, mLevel);
            setpauseprogress(i);
        }
    }

    public void setpauseprogress(int i) {
        try {
            if (mPosition == excerciseDataList.size() - 1) {
                if (mBtnNextexe != null) {
                    mBtnNextexe.setVisibility(View.GONE);
                }
            }
            isMain = false;
            isLearn = false;
            isRest = true;
            String upperCase = excerciseDataList.get(mPosition).getExecercisename().toUpperCase();
            mTvrestnextexe.setText(upperCase);
            mTvexename.setText(upperCase);
            int tempint = mPosition + 1;
            mVidview.animate().alpha(1);
            try {
                mVidview.setVisibility(View.VISIBLE);
                Uri video = Uri.parse(excerciseDataList.get(mPosition).getExecerciseimaga());
                mVidview.setVideoURI(video);
                mVidview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        mp.setLooping(true);
                        mVidview.start();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            mTvcurrexe.setText(String.valueOf(tempint));
            if (i == 1) {
                int tempround = Round + 1;
                StringBuilder sb = new StringBuilder();
                sb.append("Round ");
                sb.append(tempround);
                sb.append("\n");
                sb.append(tempint);
                sb.append(" of ");
                sb.append(excerciseDataList.size());
                mTvroundname.setText(sb.toString());
            } else {
                mTvroundname.setText(getResources().getString(R.string.round) + " " + Round + " " + getResources().getString(R.string.is_completed));

            }
            StringBuilder sb2 = new StringBuilder();
            sb2.append("NEXT ");
            sb2.append(tempint);
            sb2.append("/");
            sb2.append(excerciseDataList.size());
            mTvrestexecounts.setText(sb2.toString());
            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(FitnessApplication.getInstance()).load(excerciseDataList.get(mPosition).getExethumbs())
                    .apply(requestOptions).into(mIvrestexe);
            mRestBar.setReachedColor(getResources().getColor(R.color.tbtncolor), getResources().getColor(R.color.tbtncolor));
            if (i == 1) {
                this.timeCountInMilliSeconds3 = (int) (mResttime * 1000.0f);
                this.mRestBar.setMaxProcess(this.timeCountInMilliSeconds3 / 50);
                mRestBar.setCurProcess(timeCountInMilliSeconds3 / 1000);
                NextSpeaktimer(11000);
                startPauseTimer(timeCountInMilliSeconds3);
            } else {
                this.timeCountInMilliSeconds3 = (int) (30 * 1000.0f);
                this.mRestBar.setMaxProcess(this.timeCountInMilliSeconds3 / 50);
                mRestBar.setCurProcess(timeCountInMilliSeconds3 / 1000);
                NextSpeaktimer(11000);
                startPauseTimer(timeCountInMilliSeconds3);
            }
            mTvrestbtnskip.setText(getResources().getString(R.string.skip));
            txte.setText(getResources().getString(R.string.take_nrest));
            mTvrestbtnskip.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isPuasedall) {
                        mRestlayout.setVisibility(View.GONE);
                        mMainlayout.setVisibility(View.VISIBLE);
                        nextexercisetimer.cancel();
                        Learnprocesses();
                    } else {
                        Toast.makeText(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, getResources().getString(R.string.press_the_resume), Toast.LENGTH_SHORT).show();
                    }

                }
            });

            mPuasebottom.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Puasetime();
                    mRestlayout.setVisibility(View.GONE);

                mPauselayout.setVisibility(View.VISIBLE);
                banner1.setVisibility(View.GONE);
                    if (player != null) {
                        player.release();
                    }
                    Pauselayout(2);
                }
            });

            mTvrestresume.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isPuasedall = false;
                    mTvrestresume.setVisibility(View.INVISIBLE);
                    Resumetimer();
                    mTvrestresume.setClickable(false);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void NextSpeaktimer(long j) {

        CountDownTimer r2 = new CountDownTimer(j, 1000) {
            public void onFinish() {
                readyToGoTimer.cancel();
            }

            public void onTick(long j) {
                mSkiplayouttimes = j;
                long j2 = j - 1000;
                long j3 = j2 / 1000;
                f4959s1 = j;
                if (j3 == 9) {
                    try {
                        if (tinydb.getBoolean(Constants.ISGUIDEON_KEY) && !tinydb.getBoolean(Constants.ISMUTEON_KEY)) {
                            String lowercase = excerciseDataList.get(mPosition).getExecercisename().replace("_", " ").toLowerCase();
                            fitnessApplication.speak(getString(R.string.ready_to_go));
                            StringBuilder sb = new StringBuilder();
                            sb.append(getString(R.string.nextexespeech));
                            sb.append(lowercase);
                            fitnessApplication.speak(sb.toString());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        this.readyToGoTimer = r2.start();
    }


    private void startPauseTimer(long time) {
        this.nextexercisetimer = new CountDownTimer(time, 50) {
            public void onTick(long millisUntilFinished) {
                mPauseprogress = millisUntilFinished;
                mTvrestprogress.setText(TimeFormatter(millisUntilFinished));
                mRestBar.setCurProcess((int) (millisUntilFinished / 50));
            }

            public void onFinish() {
                mRestlayout.setVisibility(View.GONE);
                mMainlayout.setVisibility(View.VISIBLE);
                nextexercisetimer.cancel();
                Learnprocesses();
            }
        }.start();
    }

    public void setuprestporgress() {
        mRestlayout.setVisibility(View.VISIBLE);
        ismainstarted = false;
        isMain = false;
        isRest = true;
        mRestnative.setVisibility(View.VISIBLE);
        TextView mTvroundname = (TextView) findViewById(R.id.tvrounds);
        TextView mTvnextexe = (TextView) findViewById(R.id.tvnexename);
        TextView mTvexecounts = (TextView) findViewById(R.id.tvexecount);
        TextView mTvbtnskip = (TextView) findViewById(R.id.tvbtnskip);
        ImageView mIvrestexe = (ImageView) findViewById(R.id.ivrestexe);
        mTvrestresume = (ImageView) findViewById(R.id.ivrestresume);
        mRestBar = (CircleSeekBar) findViewById(R.id.restbar);
        txte = findViewById(R.id.txte);
        mTvrestprogress = (TextView) findViewById(R.id.tvrestprogress);
        mVidview.animate().alpha(0);
        mAdframe.setVisibility(View.GONE);
        mTvroundname.setText(getResources().getString(R.string.round) + Round + getResources().getString(R.string.is_completed));
        int tempint = mPosition + 1;
        int temp2 = tempint + 1;
        mTvcurrexe.setText(String.valueOf(temp2));
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(FitnessApplication.getInstance()).load(excerciseDataList.get(tempint).getExethumbs())
                .apply(requestOptions).into(mIvrestexe);
        String upperCase = excerciseDataList.get(tempint).getExecercisename().toUpperCase();
        mTvnextexe.setText(upperCase);
        mTvexecounts.setText("NEXT " + temp2 + "/" + excerciseDataList.size());
        mRestBar.setReachedColor(getResources().getColor(R.color.tbtncolor), getResources().getColor(R.color.tbtncolor));
        this.timeCountInMilliSeconds2 = (int) (30 * 1000.0f);
        this.mRestBar.setMaxProcess(this.timeCountInMilliSeconds2 / 50);
        mRestBar.setCurProcess(timeCountInMilliSeconds2 / 1000);
        RestSpeaktimer(31000);
        startRestTimer(timeCountInMilliSeconds2);

        mTvbtnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (readyToGoTimer != null) {
                    readyToGoTimer.cancel();
                }
                if (restexercisetimer != null) {
                    restexercisetimer.cancel();
                }
                mRestlayout.setVisibility(View.GONE);
//                mControlayout.setVisibility(View.VISIBLE);
                nextexercisetimer.cancel();
                startmain();
            }
        });
        mTvrestresume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTvrestresume.setVisibility(View.INVISIBLE);
                Resumetimer();
                mTvrestresume.setClickable(false);
            }
        });
    }

    public void Playstoreopen(String name) {
        Uri uri1 = Uri.parse("https://play.google.com/store/apps/details?id=" + name);
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri1);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, "You don't have Google Play installed", Toast.LENGTH_LONG).show();
        }
    }

    public void RestSpeaktimer(long j) {
        CountDownTimer r2 = new CountDownTimer(j, 1000) {
            public void onFinish() {
                readyToGoTimer.cancel();
            }

            public void onTick(long j) {
                mSkiplayouttimes = j;
                long j2 = j - 1000;
                long j3 = j2 / 1000;
                f4959s1 = j;
                if (j3 == 29) {
                    if (tinydb.getBoolean(Constants.ISGUIDEON_KEY) && !tinydb.getBoolean(Constants.ISMUTEON_KEY)) {
                        if (fitnessApplication.isSpeaking()) {
                            fitnessApplication.stop();
                        }
                        String rest = "Take A RestRound" + Round + getResources().getString(R.string.is_completed);
                        fitnessApplication.speak(rest);
                    }
                }
            }
        };
        this.readyToGoTimer = r2.start();
    }

    private void startRestTimer(long time) {
        this.restexercisetimer = new CountDownTimer(time, 50) {
            public void onTick(long millisUntilFinished) {
                mRestprogress = millisUntilFinished;
                mTvrestprogress.setText(TimeFormatter(millisUntilFinished));
                mRestBar.setCurProcess((int) (millisUntilFinished / 50));
            }

            public void onFinish() {
                if (readyToGoTimer != null) {
                    readyToGoTimer.cancel();
                }
                if (restexercisetimer != null) {
                    restexercisetimer.cancel();
                }
                mRestlayout.setVisibility(View.GONE);
                nextexercisetimer.cancel();
                startmain();
            }
        }.start();
    }


    public void TotalProgress() {
//        if (!isRest) {
        long temp = (long) (timeCountInMilliSeconds);
        long temp2 = temp - mMainprogress;
        totalprogress = totalprogress + temp;
        tinydb.putLong(Constants.TOTALTIME_KEY, totalprogress);
        Log.e("totalprog " + mPosition, String.valueOf(temp));
//        }
    }

    public void Totalcaleri() {
        try {
            calorievalue = excerciseDataList.get(mPosition).getCalorie();
        } catch (Exception e) {
            e.printStackTrace();
        }
        long temp = (long) (timeCountInMilliSeconds);
        long temp3 = temp / 1000;
        float kcalvalue = Float.parseFloat(calorievalue);
        float tempmulti = kcalvalue * temp3;
        totalcalori = totalcalori + tempmulti;
        daycalorie = daycalorie + tempmulti;
        Log.e("totalcal", String.valueOf(tempmulti));
    }

    public void TotalProgress2() {
//        if (!isRest) {
        long temp = (long) (timeCountInMilliSeconds);
        long ltemp2;
        if (ismainstarted) {
            ltemp2 = temp - mMainprogress;
//                ltemp2 = temp;
        } else {
            ltemp2 = 0;
        }
        Log.e("totalprog " + mPosition, String.valueOf(ltemp2));
        totalprogress = totalprogress + ltemp2;
        tinydb.putLong(Constants.TOTALTIME_KEY, totalprogress);
//        }

    }

    public void Totalcaleri2() {
        calorievalue = excerciseDataList.get(mPosition).getCalorie();
        long temp = (long) (timeCountInMilliSeconds);
        long ltemp2;
        if (ismainstarted) {
            ltemp2 = temp - mMainprogress;
        } else {
            ltemp2 = 0;
        }
        long temp3 = ltemp2 / 1000;
        float kcalvalue = Float.parseFloat(calorievalue);
        float tempmulti = temp3 * kcalvalue;
        Log.e("totalcal " + mPosition, String.valueOf(ltemp2));
        totalcalori = totalcalori + tempmulti;
        daycalorie = daycalorie + tempmulti;
    }

    private String TimeFormatter(long milliSeconds) {
        return String.format("%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)))});
    }

    private String hmsTimeFormatter(long milliSeconds) {
        return String.format("%02d:%02d", new Object[]{Long.valueOf(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)))});
    }


    public static void Smallnative(final Context context, final LinearLayout frameLayout) {
        AdLoader.Builder builder = new AdLoader.Builder(context, Constants.admob_nativead);
        builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
            public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                frameLayout.setVisibility(View.VISIBLE);
                NativeContentAdView nativeContentAdView = (NativeContentAdView) ((Activity) context).getLayoutInflater().inflate(R.layout.ad_native_bannertwo, null);
                populateSmallContentAdView(nativeContentAd, nativeContentAdView);
                frameLayout.removeAllViews();
                frameLayout.addView(nativeContentAdView);
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(int i) {
                Log.e("error", "Failed to load native ad:: " + i);
            }
        }).build().loadAd(new AdRequest.Builder().build());
    }

    @SuppressLint("WrongConstant")
    public static void populateSmallContentAdView(NativeContentAd
                                                          nativeContentAd, NativeContentAdView nativeContentAdView) {
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

    public void Dialogclose() {
        dialclose = new Dialog(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this);
        dialclose.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialclose.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialclose.setCanceledOnTouchOutside(false);
        dialclose.setCancelable(false);
        dialclose.setContentView(R.layout.dialog_stopexcercise);
        TextView mTemptxt = (TextView) dialclose.findViewById(R.id.tmptxt);
        TextView mBtngiveup = (TextView) dialclose.findViewById(R.id.btngiveup);
        TextView mBtncontinue = (TextView) dialclose.findViewById(R.id.btncontinue);
        ImageView mBtnclosedial = (ImageView) dialclose.findViewById(R.id.closedial);
        try {
            mTemptxt.setText(desclist[getRandomNumberInRange(0, desclist.length)]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mBtngiveup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isbtnclicked) {
                    isbtnclicked = true;
                    isGiveup = true;
                    dialclose.dismiss();
                    if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
                        Gad = 0;
                        fitnessApplication.stop();
                        mInterstitialAdMob.show();
                    } else {
                        Giveupclick();
                        fitnessApplication.stop();
                    }
                }
            }
        });
        mBtncontinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialclose.dismiss();
                if (!isPuasedall) {
                    Resumetimer();
                }
            }
        });
        mBtnclosedial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialclose.dismiss();
                if (!isPuasedall) {
                    Resumetimer();
                }

            }
        });
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialclose.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        try {
            dialclose.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialclose.getWindow().setAttributes(lp);

    }

    public void Giveupclick() {
        Puasetime();

        tinydb.putFloat(Constants.TOTALKCAL_KEY, totalcalori);
        tinydb.putInt(Constants.TOTALEXE_KEY, mAllexe);
        historydata(0);
        databaseOperations.insertExcCounter(dayname, mPosition, mLevel);
        databaseOperations.insertExcDayData(dayname, mGiveupprogress, mLevel);

        String totalexe = String.valueOf(tinydb.getInt(Constants.TOTALEXE_KEY));
        String calaory = String.valueOf(round(tinydb.getFloat(Constants.TOTALKCAL_KEY), 1));


        Constants.UpdatTime(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, String.valueOf("coins"), String.valueOf(mExecrsicedoneprogress), totalexe, calaory);

        Intent intent;
        if (mLevel == 1) {
            intent = new Intent(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, Activity_Level1.class);
        } else if (mLevel == 2) {
            intent = new Intent(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, Activity_Level2.class);
        } else if (mLevel == 3) {
            intent = new Intent(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, Activity_Level3.class);
        } else {
            intent = new Intent(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this, drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.class);
        }
        if (t1 != null) {
            if (t1.isSpeaking()) {
                t1.stop();
            }
        }

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    public void historydata(int done) {
        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);

        Locale locale = new Locale("en");
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy", locale);
        String formattedDate = df.format(c);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("hh:mm a");
        String strtime = mdformat.format(calendar.getTime());

        progressOperations.insertExcALLDayData(formattedDate, strtime, (int) mExecrsicedoneprogress, daycalorie, dayname, done);
    }


    public void DialogAdsloading() {
        dialog = new Dialog(drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisestart.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_adloading);
        LottieAnimationView mLottie = (LottieAnimationView) dialog.findViewById(R.id.lotti);
        mLottie.setAnimation("adloadanim.json");
        mLottie.playAnimation();
        mLottie.loop(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(lp);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 1) {
            try {
                Uri uri = data.getData();
                File file = new File(getPath(uri));

                Log.e("uri", String.valueOf(file));
                String s = file.toString();
                String path = Environment.getExternalStorageDirectory() + "/Music/Drzio/";
                File file1 = getBaseContext().getExternalFilesDir("Music");
                if (!file1.exists())
                    file1.mkdir();
                copyFileOrDirectory(s, String.valueOf(file1));

            } catch (Exception e) {

            }

        }
    }


    public static void copyFileOrDirectory(String srcDir, String dstDir) {

        try {
            File src = new File(srcDir);
            File dst = new File(dstDir, src.getName());

            if (src.isDirectory()) {

                String files[] = src.list();
                int filesLength = files.length;
                for (int i = 0; i < filesLength; i++) {
                    String src1 = (new File(src, files[i]).getPath());
                    String dst1 = dst.getPath();
                    copyFileOrDirectory(src1, dst1);

                }
            } else {
                copyFile(src, dst);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.getParentFile().exists())
            destFile.getParentFile().mkdirs();

        if (!destFile.exists()) {
            destFile.createNewFile();
        }

        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        } finally {
            if (source != null) {
                source.close();
            }
            if (destination != null) {
                destination.close();
            }
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor == null) return null;
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String s = cursor.getString(column_index);
        cursor.close();
        return s;
    }

    @Override
    protected void onStart() {
        isbtnclicked = false;
        super.onStart();
    }

    /*@Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        try {
            if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                mediaPlayer.start();
            }
        }catch (Exception e){

        }
        super.onRestart();
    }
*/
    @Override
    protected void onPause() {
        try {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
        } catch (Exception e) {

        }
        super.onPause();
    }

    @Override
    protected void onStop() {
        Puasetime();
        if (fitnessApplication.isSpeaking()) {
            fitnessApplication.stop();
        }
        try {
            if (mPosition != excerciseDataList.size() && !isRest) {
                if (!ismainstarted) {
                    mMainhandler.removeCallbacks(mMainrunnable);
                    String temp = excerciseDataList.get(mPosition).getExecerciseduration();
                    temp = temp.replace("00:", "");
                    float tempsecond = Float.parseFloat(temp);
                    this.mMainprogress = (int) (tempsecond * 1000.0f);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (isRest) {
            if (mTvrestresume != null) {
                mTvrestresume.setClickable(true);
                isPuasedall = true;
                mTvrestresume.setVisibility(View.VISIBLE);
            }
        } else if (isLearn) {
            if (mBtnresume != null) {
                isPuasedall = true;
                mBtnresume.setVisibility(View.VISIBLE);
            }
        } else if (isMain) {
            if (mBtnresume != null) {
                isPuasedall = true;
                mBtnresume.setVisibility(View.VISIBLE);
            }
        }

        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Puasetime();
        if (dialclose != null) {
            dialclose.dismiss();
        }
        if (dialog != null) {
            dialog.dismiss();
        }
        try {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        } catch (Exception e) {

        }

        super.onDestroy();
    }

    @Override
    public void onBackPressed() {

        if (mPauselayout.getVisibility() == View.VISIBLE) {
            if (isClicked && !isAdshown) {
                Gad = 2;
                if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
                    isAdshown = true;
                    mInterstitialAdMob.show();
                } else {
                    ClosePauseact();
                }
            } else {
                ClosePauseact();
            }
        } else {
            try {
                if (mPosition != excerciseDataList.size()) {
                    if (!ismainstarted) {
                        mMainhandler.removeCallbacks(mMainrunnable);
                        String temp = excerciseDataList.get(mPosition).getExecerciseduration();
                        temp = temp.replace("00:", "");
                        float tempsecond = Float.parseFloat(temp);
                        this.mMainprogress = (int) (tempsecond * 1000.0f);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            Dialogclose();
            Puasetime();
        }
    }


    public void showGOOGLEAdvance(final Context context, final LinearLayout frameLayout) {
        AdLoader.Builder builder = new AdLoader.Builder(context, Constants.admob_nativead);
        builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
            public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                if (mCardrestad != null) {
                    mCardrestad.setVisibility(View.VISIBLE);
                }
                NativeContentAdView nativeContentAdView = (NativeContentAdView) ((Activity) context).getLayoutInflater().inflate(R.layout.ad_content, null);
                populateContentAdView(nativeContentAd, nativeContentAdView);
                frameLayout.removeAllViews();
                frameLayout.addView(nativeContentAdView);
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(int i) {
                mRestnative.setVisibility(View.GONE);
                if (mCardrestad != null) {
                    mCardrestad.setVisibility(View.GONE);
                }
                Log.e("error", "Failed to load native ad:: " + i);
            }
        }).build().loadAd(new AdRequest.Builder().build());
    }

    @SuppressLint("WrongConstant")
    public static void populateContentAdView(NativeContentAd
                                                     nativeContentAd, NativeContentAdView nativeContentAdView) {
        nativeContentAdView.setHeadlineView(nativeContentAdView.findViewById(R.id.ad_title_textview));
        nativeContentAdView.setImageView(nativeContentAdView.findViewById(R.id.contentad_image));
        nativeContentAdView.setBodyView(nativeContentAdView.findViewById(R.id.ad_describe_textview));
        nativeContentAdView.setCallToActionView(nativeContentAdView.findViewById(R.id.ad_action_button));
        nativeContentAdView.setLogoView(nativeContentAdView.findViewById(R.id.ad_icon_imageview));
        //ImageView mBackimg = nativeContentAdView.findViewById(R.id.adblurimg);
//        nativeContentAdView.setAdvertiserView(nativeContentAdView.findViewById(R.id.contentad_advertiser));
        ((TextView) nativeContentAdView.getHeadlineView()).setText(nativeContentAd.getHeadline());
        ((TextView) nativeContentAdView.getBodyView()).setText(nativeContentAd.getBody());
        ((TextView) nativeContentAdView.getCallToActionView()).setText(nativeContentAd.getCallToAction());
//        ((TextView) nativeContentAdView.getAdvertiserView()).setText(nativeContentAd.getAdvertiser());
        List images = nativeContentAd.getImages();
        if (images.size() > 0) {
            ((ImageView) nativeContentAdView.getImageView()).setScaleType(ImageView.ScaleType.CENTER_CROP);
            ((ImageView) nativeContentAdView.getImageView()).setImageDrawable(((com.google.android.gms.ads.formats.NativeAd.Image) images.get(0)).getDrawable());
                /*try {
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


    private int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    @Override
    public void onCountDownEnd(CountDownAnimation animation) {

    }

    @Override
    public void onCountDownEnd(CountDownAnimation2 animation) {

    }

    File file;

    @Override
    protected void onResume() {
        songid = tinydb.getInt(Constants.SONGID_KEY);
        try {
            adapter_musiclist1.notifyDataSetChanged();
            adapter_musiclist.notifyDataSetChanged();
            // getListFiles(new File(DIRECTORY_TO_SAVE_MEDIA_NOW));
            File file1 = getBaseContext().getExternalFilesDir("Music");
            if (!file1.exists())
                file1.mkdir();
            getListFiles(file1);
            int i = arrayList.size() - 1;

            ChangeSong(file, i, mIvsongart, mTvsongname, progressBar, mTvstarttime, mTvendtime);

        } catch (Exception e) {
        }
        if (tinydb.getBoolean(Constants.ISMUTEON_KEY)) {
            try {
                mediaPlayer.stop();
            } catch (Exception e) {

            }
        } else {
            try {
                if (mediaPlayer != null) {
                    if (!ispause) {
                        if (tinydb.getBoolean(Constants.ISMUTEON_KEY)) {
                            mediaPlayer.start();
                        }
                    } else {
                        mediaPlayer.pause();
                    }
                }
            } catch (Exception e) {

            }

        }
        super.onResume();
    }
   /* @Override
    protected void onResume() {
        songid = tinydb.getInt(Constants.SONGID_KEY);
        if (tinydb.getBoolean(Constants.ISMUTEON_KEY)) {
            mediaPlayer.stop();
        } else {
            try {
                mediaPlayer.start();
            }catch (Exception e){

            }

        }
        *//*try {
            adapter_musiclist.notifyDataSetChanged();
            getListFiles(new File(DIRECTORY_TO_SAVE_MEDIA_NOW));
          //  int i = arrayList.size()-1;
         //   ChangeSong(files_music,i, mIvsongart, mTvsongname, progressBar, mTvstarttime, mTvendtime);

        }catch (Exception e) {
        }*//*
        try {
            adapter_musiclist.notifyDataSetChanged();
            getListFiles(new File(DIRECTORY_TO_SAVE_MEDIA_NOW));
            mediaPlayer = MediaPlayer.create(this, Uri.fromFile(arrayList.get(songid)));
            mediaPlayer.setLooping(true);
            mediaPlayer.setVolume(100, 100);
        } catch (Exception e) {

        }
        super.onResume();
    }*/

    @Override
    protected void onRestart() {
        try {
            if (!tinydb.getBoolean(Constants.ISMUSICON_KEY)) {
                if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
            }
        } catch (Exception e) {
            Log.e("errror", e.toString());
        }
        super.onRestart();
    }
}
