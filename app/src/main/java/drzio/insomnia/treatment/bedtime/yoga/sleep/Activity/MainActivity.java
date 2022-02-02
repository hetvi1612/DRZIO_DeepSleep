package drzio.insomnia.treatment.bedtime.yoga.sleep.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.github.abdularis.civ.AvatarImageView;
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
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.mannan.translateapi.Language;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderView;
import com.yugansh.tyagi.smileyrating.SmileyRatingView;

import net.danlew.android.joda.JodaTimeAndroid;

import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Currency;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter.SliderAdapter;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter.WeekGoalAdapter;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.CategoryData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.TestDatalist;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.TestDatamodel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.TestSubcatdata;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.Testcatdata;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.AddLastTime;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.AllApp_Activity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.AppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.ChooseGenderActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.ExituserData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.MaleAppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity.Activity_Bloglists;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BottomFragment.Fragment_Discover;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BottomFragment.Fragment_Report;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BottomFragment.Fragment_blogs;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BottomFragment.Fragment_main;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BottomFragment.Fragment_setting;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.BDatabaseOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.DatabaseOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.HistoryProgressOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.MyplanDbhelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Diet.CustomUpdateActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Diet.DefalutAndCustomplanActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.database.DietplanDbhelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Fragment_Dialog;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Graphdata.GraphdataOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Graphdata.WeightGraphdataOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.ApiInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.Bmrdata;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.LanguageApi;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.Pricedata;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.RateModel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.Register_Api;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.AlarmHelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.BroadcastManager;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.ForonedayBroadcastManager;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.GoalBroadcastManager;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.ReminderMainActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.SixhourBroadcastManager;
import drzio.insomnia.treatment.bedtime.yoga.sleep.SliderNewAdapter;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.Activity_Covidhome;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Banner1;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.BannerModal;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.LoginData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Weekgoalmodal;
import drzio.insomnia.treatment.bedtime.yoga.sleep.util.IabHelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIClient;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BuildConfig;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseSequence;
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;
import uk.co.deanwild.materialshowcaseview.ShowcaseConfig;

import static com.github.mikephil.charting.charts.Chart.LOG_TAG;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.CHANNEL_NAME;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.catnamearray;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.homebannerdatalist1;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.isFromnotification;
import static retrofit2.converter.scalars.ScalarsConverterFactory.create;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, LocationListener {
    private static TinyDB tinydb;
    List<Weekgoalmodal> weekgoallist = new ArrayList<>();
    private TextView mGoaldaytxt;
    private LocalDate today;
    private WeekGoalAdapter goalAdapter;
    private RelativeLayout mGoalayout;
    private RelativeLayout mSetgoallayout;
    private String[] daynames = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    public ArrayList<BannerModal> mBannerdata = new ArrayList<>();
    private boolean isclicked = false;
    private SliderView sliderView;
    FitnessApplication fitnessApplication;
    private String[] feedbacklist;
    private int temprate;
    private Handler mRatehandler;
    private Runnable mRaterunnable;
    private Dialog dialrateexepe;
    private AlertDialog feedbackdial;
    private Dialog dialrateonplay;
    private boolean mSuccess;
    ArrayList<String> mSelectedFeedbackslist = new ArrayList<>();
    private Dialog mBottomSheetDialog;
    private InterstitialAd mInterstitialAdMob;
    private int Gad;
    private RelativeLayout mACard, mACard1, mACard2;
    private TextView mAtxtDaylefts /*mAtxtDaylefts1, mAtxtDaylefts2*/;
    private TextView mATextapercentage, mATextapercentage1, mATextapercentage2;
    private CircularProgressBar mAprogressbar, mAprogressbar1, mAprogressbar2;
    MainActivity activity;
    private BillingProcessor bp;
    private boolean isClicked = false;

    private ImageView mBtnmenu;
    //  private NavigationView mNavigationView;
    private final Handler mDrawerHandler = new Handler();
    public static DrawerLayout layout;
    private ImageView mBtnlogout;
    private TextView mTxtword;
    private LinearLayout mloginlay;
    private LinearLayout mSucceslayout;
    private TextView mProfilename;
    private AvatarImageView mProfilepic;
    private LoginButton loginButton;
    private ImageView signInButton;
    private boolean isFromFb = false;
    private boolean isFromGoogle = false;
    private String TAG = "LoginActivity";
    private String userId;
    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInAccount account;
    private String firstName, lastName, email, birthday, gender;
    private URL profilePicture;
    private static final int SIGN_IN_CODE = 9001;
    private CallbackManager callbackManager;
    private String Gusername;
    private String Gmail;
    private String Guserid;
    private String Gprofilepicurl;
    private String GFirstname;
    private String GLastname;
    public ArrayList<LoginData> mLogindata = new ArrayList<>();
    private boolean success2;
    private RelativeLayout mAdscard1;
    private SliderAdapter sliderAdapter;
    private BackpainAPIInterface apiInterface;
    private LinearLayout Slidelayout;
    private DatabaseOperations databaseOperations;
    private BDatabaseOperations databaseOperations2;
    private HistoryProgressOperations historyProgressOperations;
    private MyplanDbhelper myplanDbhelper;
    private DietplanDbhelper dietplanDbhelper;
    private GraphdataOperations graphdataOperations;
    private WeightGraphdataOperations weightGraphdataOperations;
    private Handler mAdhandler;
    private Runnable mAdrunnable;
    private ImageView mBtAds;
    private TextView mBlogtxtcount;
    private Dialog dialdiscount;
    private ShimmerFrameLayout mSlideloader;
    private static final int REQUEST_ACCESS_FINE_LOCATION = 111;

    RadioButton rhindi, rfrench, rchinish, rpotugues, rrussian, aarbic, renglish, spanish,
            rthai, ritalian, rturkish, rkorean, rdutch, rswedish, rdanish, rbengali, rtamil, rtelugu, rpunjabi, rpolish,persian;
    String selectedSuperStar;
    TextView mTxtallexe, mTxtallkcal, mTxtalltime;
    Location mLastLocation;
    LocationRequest mLocationRequest;

    private ImageView mIvdiscover2;
    private ImageView mIvProgress2;
    private ImageView mIvhome2;
    private ImageView mIvEarn2;
    private ImageView mIvPurchase2;

    private String token;
    private final int REFRESH_RATE_IN_SECONDS = 3;
    private final Handler refreshHandler = new Handler();
    private final Runnable refreshRunnable = new RefreshRunnable();

    public static ImageView mIvdiscover3;
    public static ImageView mIvProgress3;
    public static ImageView mIvhome3;
    public static ImageView mIvEarn3;
    public static ImageView mIvPurchase3;
    public static TextView mTvdiscover;
    public static TextView mTvprogress;
    public static TextView mTvearn;
    public static TextView mTvpurchase;
    ImageView mIvselected = null;
    ImageView mIvunselect1 = null;
    ImageView mIvunselect2 = null;
    ImageView mIvunselect3 = null;
    ImageView mIvunselect4 = null;
    @SuppressLint("WrongConstant")
    String BASEURL = "";
    Dialog dialog;

    Fragment selectedfragment;
    int value1 = 3;
    LinearLayout mLaydiscover;
    LinearLayout mLayprogress;
    LinearLayout mLayhome;
    LinearLayout mLayearn;
    LinearLayout mLaypurchase;
    LinearLayout mLayrate;
    public static MaterialShowcaseSequence sequence;
    RelativeLayout relativebottom1;
    RelativeLayout relativebottom2;
    RelativeLayout relativebottom3;
    RelativeLayout relativebottom4;
    RelativeLayout relativebottom5;
    Boolean skip_data;
    private String currency_code;
    private String toCurrency = "";
    ShimmerFrameLayout mLoadlay;
    RelativeLayout banner1;
    FrameLayout mGbannerlay2;
    RelativeLayout ad_rela;
    private String langauge;
    private IabHelper mHelper;
    static String ITEM_SKU = "";
    private boolean clicked_exit = false;
    private String latestVersion;
    private String currentVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        tinydb = new TinyDB(MainActivity.this);
        ITEM_SKU = tinydb.getString(Constants.ITEM_SKU1);

        getPriceList();
        tinydb.getBoolean(Constants.PREMIUN_KEY);
        String languages = tinydb.getString(Constants.Language);
        toCurrency = tinydb.getString(Constants.COUNTRY_CODEID);
        Constants.languagechange(this, languages);

        //  Log.e("languages_data", languages + "dfkdfk");
        setContentView(R.layout.act_drawermain);
        token = tinydb.getString(Constants.Authtoken);
        BASEURL = tinydb.getString(Constants.NewBaseUrl);
        //Log.e("BASEURL", BASEURL + "dfkdfk");
        langauge = tinydb.getString(Constants.Language);
   /*     if (languages.equals("ar")) {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        } else {
            getWindow().getDecorView().setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }*/
        skip_data = tinydb.getBoolean(Constants.SKIP_NOINTERNET);

        String mobile_number = "0";
        String gender = tinydb.getString(Constants.GENDER_KEY);
        String age = String.valueOf(tinydb.getInt(Constants.AGE_KEY));
        String mHeight;
        String mHeightP;
        if (tinydb.getBoolean(Constants.ISCM_KEY)) {
            mHeight = String.valueOf(tinydb.getFloat(Constants.HEIGHT_KEY)) /*+ " cm"*/;
            mHeightP = "cm";
        } else {
            mHeight = String.valueOf(tinydb.getFloat(Constants.HEIGHT_KEY)) /*+ " ft"*/;
            mHeightP = "ft";

        }
        String height = mHeight;
        String mWeight;
        String mWeightP;
        if (tinydb.getBoolean(Constants.ISKG_KEY)) {
            mWeight = String.valueOf(tinydb.getInt(Constants.WEIGHT_KEY)) /*+ " kg"*/;
            mWeightP = "kg";
        } else {
            mWeight = String.valueOf(tinydb.getInt(Constants.WEIGHT_KEY))/* + " lb"*/;
            mWeightP = "lb";
        }


        String androidId = 22+Settings.Secure.getString(getContentResolver(),
                        Settings.Secure.ANDROID_ID)+22;
        Log.e("androidId", androidId);
        String weight = mWeight;
        //  String user_type_id = String.valueOf(tinydb.getInt(Constants.MEALTYPE_KEY));
        String paid_status = "0";
        String country_id = tinydb.getString(Constants.COUNTRYID_KEY);
        //       Log.e("coutry",country_id);
       /* String langauge = Resources.getSystem().getConfiguration().locale.getLanguage();
        Log.e("getDisplayLanguage", langauge);

        if (langauge.equals("hi")) {
            // mtinydb.putString(Constants.Language, "hi");
            // Constants.languagechange(getActivity(), "hi");
            langauge = "hindi";
        } else {
            // mtinydb.putString(Constants.Language, "en");
            // Constants.languagechange(getActivity(), "en");
            langauge = "english";
        }*/
        // String langauge = null;
        String state_id = tinydb.getString(Constants.STATEID_KEY);
        //     Log.e("state_id1",state_id);
        String city_id = tinydb.getString(Constants.CITYID_KEY);
        //     Log.e("city_id1",city_id);
        String fcm_token = tinydb.getString(Constants.FCMTOKEN_KEY);
        String facebook = "";
        String google = Guserid;
        String user_image = Gprofilepicurl;
        if (skip_data) {


            ConnectivityManager mgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo netInfo = mgr.getActiveNetworkInfo();
            if (netInfo != null) {
                if (netInfo.isConnected()) {


                    callRegisterApi1(firstName, lastName, email, gender,
                            age, height, weight, country_id,
                            state_id, city_id, fcm_token, google, langauge, mHeightP, mWeightP);
                } else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();

                }
            } else {
                Toast.makeText(MainActivity.this, getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
              /*  tinydb.putBoolean(Constants.SKIP_NOINTERNET, true);
                Intent intent = new Intent(Activity_LoginScreen.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                finish();*/

            }


        }


        try {
            String report = getIntent().getStringExtra("report");
            if (!report.isEmpty()) {
                if (report.equals("report")) {
                    layclick(2);
                }
            }
        }catch (Exception e ){

        }

        File file = this.getBaseContext().getExternalFilesDir("Music");
        if (!file.exists())
            file.mkdir();

        relativebottom1 = (RelativeLayout) findViewById(R.id.relativebottom1);
        relativebottom2 = (RelativeLayout) findViewById(R.id.relativebottom2);
        relativebottom3 = (RelativeLayout) findViewById(R.id.relativebottom3);
        relativebottom4 = (RelativeLayout) findViewById(R.id.relativebottom4);
        relativebottom5 = (RelativeLayout) findViewById(R.id.relativebottom5);
        fitnessApplication = FitnessApplication.getInstance();
        activity = this;
        TextView offdiscount1 = (TextView) findViewById(R.id.offdiscount1);
        ad_rela = (RelativeLayout) findViewById(R.id.ad_rela);
        ad_rela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent10 = new Intent(MainActivity.this, Activity_Purchase.class);
                startActivity(intent10);

            }
        });
        mLoadlay = findViewById(R.id.shimmer_container);
        banner1 = (RelativeLayout) findViewById(R.id.banner1);
        mGbannerlay2 = findViewById(R.id.adframe1234);
        mGbannerlay2.setVisibility(View.GONE);
        showBanner(this, mGbannerlay2, banner1, ad_rela, mLoadlay);
        mBtnmenu = (ImageView) findViewById(R.id.btnmenu);
        //    mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        layout = findViewById(R.id.drawer);
        //   mNavigationView.setNavigationItemSelectedListener(this);
        databaseOperations = new DatabaseOperations(this);
        databaseOperations2 = new BDatabaseOperations(this);
        graphdataOperations = new GraphdataOperations(this);
        weightGraphdataOperations = new WeightGraphdataOperations(this);
        historyProgressOperations = new HistoryProgressOperations(this);
        myplanDbhelper = new MyplanDbhelper(this);
        dietplanDbhelper = new DietplanDbhelper(this);

       // Animuser();
        mIvdiscover2 = (ImageView) findViewById(R.id.ivdiscover2);
        mIvProgress2 = (ImageView) findViewById(R.id.ivprogress2);
        mIvhome2 = (ImageView) findViewById(R.id.ivhome2);
        mIvEarn2 = (ImageView) findViewById(R.id.ivearn2);
        mIvPurchase2 = (ImageView) findViewById(R.id.ivpurchase2);
        mIvdiscover3 = (ImageView) findViewById(R.id.ivdiscover3);
        mIvProgress3 = (ImageView) findViewById(R.id.ivprogress3);
        mIvhome3 = (ImageView) findViewById(R.id.ivhome3);
        mIvEarn3 = (ImageView) findViewById(R.id.ivearn3);
        mIvPurchase3 = (ImageView) findViewById(R.id.ivpurchase3);
        mLaydiscover = (LinearLayout) findViewById(R.id.laydiscover);
        mLayprogress = (LinearLayout) findViewById(R.id.layprogress);
        mLayhome = (LinearLayout) findViewById(R.id.layhome);
        mLayearn = (LinearLayout) findViewById(R.id.layearn);
        mLaypurchase = (LinearLayout) findViewById(R.id.laypurchase);
        mTvdiscover = (TextView) findViewById(R.id.tvdiscover);
        mTvprogress = (TextView) findViewById(R.id.tvprogress);
        mTvearn = (TextView) findViewById(R.id.tvearn);
        mTvpurchase = (TextView) findViewById(R.id.tvpurchase);
        RelativeLayout mTempbmibtn = findViewById(R.id.wwekplans);
        ImageView mBtnpremium = (ImageView) findViewById(R.id.bottomlay);


        String token = FirebaseInstanceId.getInstance().getToken();
        String authe = tinydb.getString(Constants.Authtoken);


        if (authe.isEmpty()) {
            callRegisterApi1(firstName, lastName, email, gender,
                    age, height, weight, country_id,
                    state_id, city_id, fcm_token, google, langauge, mHeightP, mWeightP);
        }
        if (authe != null) {
            SendToken1(authe);

            getPriceList();

        }

        if (value1 == 3) {
            selectedfragment = new Fragment_main();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commitNow();
            mTvdiscover.setTextColor(getResources().getColor(R.color.navunselected));
            mTvprogress.setTextColor(getResources().getColor(R.color.navunselected));
            mTvearn.setTextColor(getResources().getColor(R.color.navunselected));
            mTvpurchase.setTextColor(getResources().getColor(R.color.navunselected));
            mIvhome3.setColorFilter(getResources().getColor(R.color.tbtncolor));
            mIvdiscover3.setColorFilter(getResources().getColor(R.color.grey));
            mIvProgress3.setColorFilter(getResources().getColor(R.color.grey));
            mIvEarn3.setColorFilter(getResources().getColor(R.color.grey));
            mIvPurchase3.setColorFilter(getResources().getColor(R.color.grey));
            mIvunselect1 = mIvdiscover2;
            mIvunselect2 = mIvProgress2;
            mIvselected = mIvhome2;
            mIvunselect3 = mIvEarn2;
            mIvunselect4 = mIvPurchase2;
        }

        if (getIntent().getExtras() != null) {
            try {
                Bundle bundle = getIntent().getExtras();
                if (bundle != null) {
                    isFromnotification = false;
                    /*String mType = bundle.getString("mNotificationtype");
                    Log.e("mNotificationtype", mType);*/

                    String mDefaulteType = bundle.getString("mDefaulteType");
                    String mDatalink = bundle.getString("mLink");
                    Log.e("dmNotificationtype", mDatalink);
                    Log.e("mNotificationtype", mDefaulteType);
                    assert mDefaulteType != null;
                    switch (mDefaulteType) {
                        case "Default":
                            Defaultetype(mDefaulteType, mDatalink);
                            break;
                        case "Mobile":
                            Gotoplay(mDatalink);
                            break;

                        case "Customizeplan":
                            Intent intentt = new Intent(MainActivity.this, Activity_MyTraining.class);
                            intentt.putExtra("noti", "true");
                            startActivity(intentt);
                            break;

                        case "Discover":
                            layclick(1);
                            break;
                        case "NotLogin":
                            layclick(3);
                            layout.openDrawer(Gravity.START);
                            break;
                        case "DietPlan":
                        /*    boolean diet = tinydb.getBoolean(Constants.ReadyDietPlan);
                            if (diet) {
                                Intent intent1 = new Intent(MainActivity.this, DefalutAndCustomplanActivity.class);
                                intent1.putExtra("isFrom2", false);
                                startActivity(intent1);
                            } else {
                                Intent intent = new Intent(MainActivity.this, CustomUpdateActivity.class);
                                intent.putExtra("isFrom", false);
                                startActivity(intent);
                            }*/
                            String tempdate = tinydb.getString(Constants.DIETPLANDATE_KEY);
                            Date c = Calendar.getInstance().getTime();
                             Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
                            String formattedDate = df.format(c);
                            if (!tempdate.isEmpty() && tempdate.equals(formattedDate)) {
                                Intent intent1 = new Intent(MainActivity.this, DefalutAndCustomplanActivity.class);
                                intent1.putExtra("noti", "true");
                                startActivity(intent1);
                            } else {
                                Intent intent = new Intent(MainActivity.this, CustomUpdateActivity.class);
                                intent.putExtra("noti", "true");
                                startActivity(intent);
                            }
                            break;
                        case "Webview":
                            Intent intent = new Intent(MainActivity.this, Activity_webview.class);
                            intent.putExtra("link", mDatalink);
                            startActivity(intent);
                            Log.e("link", mDatalink);
                            break;
                        case "Blog":

                            Intent intent1 = new Intent(MainActivity.this, Activity_webview.class);
                            intent1.putExtra("link", mDatalink);
                            startActivity(intent1);
                            Log.e("link", mDatalink);
                           /* Intent intent1 = new Intent(MainActivity.this, MainActivity.class);
                            //   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            Bundle data1 = new Bundle();
                            data1.putInt("discover",4);
                            intent1.putExtras(data1);
                            //    intent.putExtra("isFrom3", true);
                            startActivity(intent1);*/
                          /*  Intent intent2 = new Intent(MainActivity.this, Activity_Blogdetails.class);
                            intent2.putExtra("bid", mDatalink);
                            startActivity(intent2);*/
                            break;
                        case "Ads":
                            Intent intent3 = new Intent(MainActivity.this, Activity_Purchase.class);
                            startActivity(intent3);
                            break;
                        case "Premium":
                            Intent intent4 = new Intent(MainActivity.this, Activity_Purchase.class);
                            startActivity(intent4);
                            break;

                     /*   case "reason1":
                            tinydb.putString(Constants.ReasonKey, mDefaulteType);
                            DialogDiscount80();

                            Intent intent6 = new Intent(MainActivity.this, Activity_Purchase.class);
                            startActivity(intent6);
                            break;
                        case "reason2":
                            tinydb.putString(Constants.ReasonKey, mDefaulteType);
                            DialogDiscount();

                            Intent intent7 = new Intent(MainActivity.this, Activity_Purchase.class);
                            startActivity(intent7);
                            break;*/
                        case "Appstore":
                            Intent intent9 = new Intent(MainActivity.this, AllApp_Activity.class);
                            startActivity(intent9);
                            break;

                        case "purchase":
                            Intent intent10 = new Intent(MainActivity.this, Activity_Purchase.class);
                            startActivity(intent10);
                            break;
                        case "Covid19":
                            if (!isClicked) {
                                isClicked = true;
                                Intent intent5 = new Intent(MainActivity.this, Activity_Covidhome.class);
                                intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent5);
                                finish();
                            }
                            break;
                        default:
                            Intent intent11 = new Intent(MainActivity.this, MainActivity.class);
                            startActivity(intent11);
                            break;
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        TextView mVersionname = (TextView) findViewById(R.id.version);
        if (BASEURL.equals("http://65.0.77.129:7079/")) {
            mVersionname.setText("Dev " + BuildConfig.VERSION_NAME);
        } else {
            mVersionname.setText("Prod " + BuildConfig.VERSION_NAME);
        }
        //subscribeToPushService();


        mBtnpremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_Purchase.class);
                startActivity(intent);
            }
        });
        if (tinydb.getBoolean(Constants.PREMIUN_KEY)) {
            mBtnpremium.setVisibility(View.GONE);
        }
        InitLoginlayouts();
        mBtnmenu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                layout.openDrawer(Gravity.START);
            }
        });

      /*  try {
            AppUpdaterUtils appUpdaterUtils = new AppUpdaterUtils(this)
                    .withListener(new AppUpdaterUtils.UpdateListener() {
                        @Override
                        public void onSuccess(Update update, Boolean isUpdateAvailable) {
                            if (isUpdateAvailable) {
                                new AppUpdater(MainActivity.this)
                                        .setTitleOnUpdateAvailable("New Features available")
                                        .setUpdateFrom(UpdateFrom.GOOGLE_PLAY)
                                        .setDisplay(Display.DIALOG)
                                        .setButtonDoNotShowAgain(null)
                                        .start();
                            }
                        }

                        @Override
                        public void onFailed(AppUpdaterError error) {
                            Log.d("AppUpdater Error", "Something went wrong");
                        }
                    });
            appUpdaterUtils.start();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //     mSlideloader = (ShimmerFrameLayout) findViewById(R.id.slideloader);

    /*    Slidelayout = (LinearLayout) findViewById(R.id.slidelayout);
        sliderView = findViewById(R.id.imageSlider);
        Slidelayout.setVisibility(View.VISIBLE);*/
        BottomExit();
      /*  mAdscard1 = (RelativeLayout) findViewById(R.id.cardadd);
        LinearLayout mGbannerlay = findViewById(R.id.adframe);
        showhomenative(this, mGbannerlay);*/
        JodaTimeAndroid.init(this);
        today = LocalDate.now();
        int dayofweek = tinydb.getInt("fdow");
        if (today != null) {
            if (dayofweek == 1) {
                today = today.withDayOfWeek(DateTimeConstants.WEDNESDAY);
                daynames = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
            } else if (dayofweek == 2) {
                today = today.withDayOfWeek(DateTimeConstants.THURSDAY);
                daynames = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
            } else if (dayofweek == 3) {
                today = today.withDayOfWeek(DateTimeConstants.TUESDAY);
                daynames = new String[]{"Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri"};
            }
        }
        for (int i = -3; i <= 3; i++) {
            Weekgoalmodal weekgoalmodal = new Weekgoalmodal();
            String temp = String.valueOf(today != null ? today.plusDays(i) : null);
            String day = (String) DateFormat.format("dd", stringtoday(temp));
            Log.e("dates", day);
            weekgoalmodal.setName(removeLeadingZeroes(day));
            weekgoallist.add(weekgoalmodal);
        }


        //getBanner();
    /*    VersionChecker versionChecker = new VersionChecker();
        try {
            latestVersion = versionChecker.execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }*/
        getBmrdata();
       /* final int[] mSongs = new int[]{R.raw.calm_yoga_music, R.raw.dynamic_yoga_music, R.raw.energetic_yoga_music,
                R.raw.peacful_yoga_music, R.raw.piano_ambient_yoga_music, R.raw.relaxingyogamusic};
        final String[] mSongsname = new String[]{"Calm Yoga Music", "Dynamic Yoga Music", "Energetic Yoga Music", "Peacful Yoga Music",
                "Piano Ambient Yoga Music", "Relaxing Yoga Music"};

        for (int i = 0; i < mSongs.length; i++) {
            try {
                String path = Environment.getExternalStorageDirectory() + "/DRzio";

                Log.e("tag", "path=" + path);
                File dir = new File(path);
                if (!dir.exists()  *//*&& !path.startsWith("sounds") && !path.startsWith("webkit")*//*)
                    if (!dir.mkdirs())
                        Log.i("tag", "could not create dir " + path);

                if (dir.mkdirs() || dir.isDirectory()) {
                    String str_song_name = mSongsname[i] + ".mp3";
                    CopyRAWtoSDCard(mSongs[i], path + File.separator + str_song_name);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
        // Bottombar();

        Log.e("token_main", tinydb.getString(Constants.Authtoken));


        PackageManager manager = getPackageManager();

        Date c2 = Calendar.getInstance().getTime();
        SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate2 = df2.format(c2);
        getCalculatedDate(formattedDate2, "dd-MM-yyyy", 3);
        tinydb.putString(Constants.TWODAYREMIDERDATE_KEY, getCalculatedDate(formattedDate2, "dd-MM-yyyy", 2));
        tinydb.putString(Constants.FIVEDAYREMIDERDATE_KEY, getCalculatedDate(formattedDate2, "dd-MM-yyyy", 5));

        mGoalayout = (RelativeLayout) findViewById(R.id.goallayout);
        mSetgoallayout = (RelativeLayout) findViewById(R.id.setgoallayout);
        //  TextView mTxtsetgoals = (TextView) findViewById(R.id.btnsetgoals);
        mTxtallexe = (TextView) findViewById(R.id.totalexes);
        mTxtallkcal = (TextView) findViewById(R.id.totalkcal);
        mTxtalltime = (TextView) findViewById(R.id.totaltime);
        LinearLayout mDatalay = (LinearLayout) findViewById(R.id.layone);
      /*  mDatalay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_Calenderview.class);
                startActivity(intent);
            }
        });
        LinearLayout mBtnhistory = findViewById(R.id.btnhistory);
        mBtnhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_Reports.class);
                startActivity(intent);
                finish();
            }
        });*/
       /* TextView mBtnmore = (TextView) findViewById(R.id.btnmore);
        mBtnmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_Bloglists.class);
                startActivity(intent);
                finish();
            }
        });*/
        mBtAds = (ImageView) findViewById(R.id.btnadtext);
        //  ImageView mBtnstore = (ImageView) findViewById(R.id.ivbtnstore);
        mBlogtxtcount = (TextView) findViewById(R.id.txtcounter);
        try {

            //   mBtnstore.setBackgroundResource(R.drawable.adsstoreanim);
        } catch (Exception e) {

        }
      /*  AnimationDrawable anim2 = (AnimationDrawable) mBtnstore.getBackground();
        anim2.start();
        ImageView mIvcovid = (ImageView) findViewById(R.id.ivcovid);
        mIvcovid.setBackgroundResource(R.drawable.covidanim);
        AnimationDrawable anim3 = (AnimationDrawable) mIvcovid.getBackground();
        anim3.start();*/
//        mBlogtxtcount.setVisibility(View.GONE);
       /* int newblogcount = tinydb.getInt(Constants.NEWBLOGSCOUNTS);
        if (newblogcount != 0) {
            mBtAds.setBackgroundResource(R.drawable.adsanimationtwo);
            AnimationDrawable anim = (AnimationDrawable) mBtAds.getBackground();
            anim.start();
            mBlogtxtcount.setVisibility(View.VISIBLE);
            mBlogtxtcount.setText(String.valueOf(newblogcount));
        } else {
            mBtAds.setBackgroundResource(R.drawable.ic_off);
        }*/
        String lau = tinydb.getString(Constants.Language);
        Log.e("laun", lau);
       /* if (lau.isEmpty()) {
            tinydb.putString(Constants.Language, "en");
            Log.e("laung", "ghhg" + lau);
        }*/
      /*  mBtAds.setBackgroundResource(R.drawable.ic_off);
        callBlogApi();
        mBtAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBlogtxtcount.setVisibility(View.GONE);
                mBtAds.setBackgroundResource(R.drawable.ic_off);
                tinydb.putInt(Constants.NEWBLOGSCOUNTS, 0);
                Intent intent = new Intent(MainActivity.this, Activity_Bloglists.class);
//                intent.putExtra("isFrom3", true);
                startActivity(intent);
                finish();
                tinydb.putInt(Constants.PRESENTBLOGCOUNTS, Blogmodel.getmBloglist().size());
            }
        });*/

     /*   mBtnstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cgender = tinydb.getBoolean(Constants.Genderchoose);
                if (cgender) {
                    if (tinydb.getString(Constants.GENDER_KEY).equals(getResources().getString(R.string.female))) {
                        Intent intent = new Intent(MainActivity.this, AppstoreActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(MainActivity.this, MaleAppstoreActivity.class);
                        startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(MainActivity.this, ChooseGenderActivity.class);
                    startActivity(intent);
                }
            }
        });*/
       /* RecyclerView mGoalrecycleview = (RecyclerView) findViewById(R.id.goalrecycler);
        mGoaldaytxt = (TextView) findViewById(R.id.goalday);
        if (tinydb.getInt("goalday") == 0) {
            mSetgoallayout.setVisibility(View.VISIBLE);
            String goalday = "/" + tinydb.getInt("goalday");
            mGoaldaytxt.setText(goalday);
        } else {
            mGoalayout.setVisibility(View.VISIBLE);
            mSetgoallayout.setVisibility(View.GONE);
        }
        int num = tinydb.getInt(Constants.DISCOUNTDIALO_TIME);
        if (!isAdded) {
            isAdded = true;
            if (num == 2) {
                tinydb.putInt(Constants.DISCOUNTDIALO_TIME, 0);
                if (!tinydb.getBoolean(Constants.PREMIUN_KEY)) {
                    DialogDiscount();
                }
            } else {
                num++;
                tinydb.putInt(Constants.DISCOUNTDIALO_TIME, num);
            }
        }
*/
        String totalexe = String.valueOf(tinydb.getInt(Constants.TOTALEXE_KEY));
//        mTxtallexe.setText(totalexe);
//        mTxtallkcal.setText(String.valueOf(round(tinydb.getFloat(Constants.TOTALKCAL_KEY), 1)));
        long temp = tinydb.getLong(Constants.TOTALTIME_KEY, 0);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(temp);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(temp) % 60;
//        mTxtalltime.setText(minutes + ":" + seconds);
       /* if (!tinydb.getBoolean(Constants.RATEDIALOGSHOW_KEY)) {
            if (!Constants.isRatedialShown) {
                if (minutes > 5 && minutes <= 50) {
                    mRatehandler = new Handler();
                    mRaterunnable = new Runnable() {
                        @Override
                        public void run() {
                            if (dialdiscount == null) {
                                DialogRatexeperience();
                            }
                        }
                    };
                    mRatehandler.postDelayed(mRaterunnable, 2000);
                }
            }
        }*/
   /*     LinearLayout mbtnWeekgoal = (LinearLayout) findViewById(R.id.setweekgoalbtn);
        mbtnWeekgoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_weekgoal.class);
                startActivity(intent);
            }
        });*/


        Bundle extras1 = getIntent().getExtras();
        if (extras1 != null) {
            value1 = extras1.getInt("discover");
            Log.e("TAG", "onCreate: " + value1);
            /*  if(value1 == 1)
             {
             } */
        }

        if (value1 == 1) {
            layclick(1);
        } else if (value1 == 2) {
            layclick(2);
        } else if (value1 == 3) {

            layclick(3);
        } else if (value1 == 4) {
            layclick(4);
        } else if (value1 == 5) {
            layclick(5);
        }
        scheduleAlarm();
        scheduletwoAlarm();
        schedule6oclockAlarm1();
        schedulegoalAlarm();
        //    InitializeAPlans();
        //   InitializeBPlans();
        NavigationData();
        initbottomnavigation();
    /*    if (homebannerdatalist1.size() != 0) {
            try {
                if (Slidelayout != null) {
                    Slidelayout.setVisibility(View.VISIBLE);
                }
                mSlideloader.setVisibility(View.GONE);
                sliderView.setVisibility(View.VISIBLE);
                final SliderNewAdapter adapter = new SliderNewAdapter(MainActivity.this, homebannerdatalist1);
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
        //callBannerApi();
      /*  if (homebannerdatalist.size() != 0) {
            try {
                if (Slidelayout != null) {
                    Slidelayout.setVisibility(View.VISIBLE);
                }
                //     mSlideloader.setVisibility(View.GONE);
                sliderView.setVisibility(View.VISIBLE);
                final SliderAdapter adapter = new SliderAdapter(MainActivity.this, homebannerdatalist);
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
            homebannerdatalist.clear();
            callBannerApi();
        }*/
        /*FrameLayout mAdslay = (FrameLayout) findViewById(R.id.adframe1);
        mAdslay.setVisibility(View.GONE);
        FitnessApplication.showBanner(MainActivity.this, mAdslay, 2);
        FrameLayout mAdframerectangle = (FrameLayout) findViewById(R.id.adframerect);
        FitnessApplication.showBanner(MainActivity.this, mAdframerectangle, 3);*/
        feedbacklist = getResources().getStringArray(R.array.feedlist);
      /*  mTxtsetgoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_weekgoal.class);
                startActivity(intent);
            }
        });*/
        //mGoalrecycleview.setLayoutManager(new GridLayoutManager(MainActivity.this, 7));
        goalAdapter = new WeekGoalAdapter(MainActivity.this, weekgoallist);
        //  mGoalrecycleview.setAdapter(goalAdapter);
//        getDaysNames();
        DatabaseOperations databaseOperations = new DatabaseOperations(MainActivity.this);
        if (databaseOperations.CheckDBEmpty() == 0) {
            databaseOperations.insertExcALLDayData(1);
        }
        try {
            if (databaseOperations.CheckDBLV2Empty() == 0) {
                databaseOperations.insertExcALLDayData(2);
            }
            if (databaseOperations.CheckDBLV3Empty() == 0) {
                databaseOperations.insertExcALLDayData(3);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("dataerror", e.getMessage());
        }

        BDatabaseOperations databaseOperations2 = new BDatabaseOperations(MainActivity.this);
        try {
            if (databaseOperations2.CheckDBEmpty() == 0) {
                databaseOperations2.insertExcALLDayData(1);
            }
            if (databaseOperations2.CheckDBLV2Empty() == 0) {
                databaseOperations2.insertExcALLDayData(2);
            }
            if (databaseOperations2.CheckDBLV3Empty() == 0) {
                databaseOperations2.insertExcALLDayData(3);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("dataerror", e.getMessage());
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager =
                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, CHANNEL_NAME, importance);
            mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            assert mNotificationManager != null;
            mNotificationManager.createNotificationChannel(mChannel);
        }
        if (Constants.storedatalist.size() == 0) {
            //callCategoryApi();
        }

        try {


            String notificationkey = getIntent().getStringExtra("notificationkey");
            String notificationvalue = getIntent().getStringExtra("notificationvalue");
            Log.e("notificationkey", notificationkey);
            Log.e("notificationvalue", notificationvalue);
            if (notificationkey != null) {
                if (notificationkey.equals("playstore")) {
                    Uri uri1 = Uri.parse(notificationvalue);
                    Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri1);
                    try {
                        startActivity(myAppLinkToMarket);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getApplicationContext(), "You don't have Google Play installed", Toast.LENGTH_LONG).show();
                    }
                } else if (notificationkey.equals("purchase") && notificationvalue.equals("true")) {
                    startActivity(new Intent(MainActivity.this, Activity_Purchase.class));
                } else if (notificationkey.equals("blog") && notificationvalue.equals("true")) {
                    startActivity(new Intent(MainActivity.this, Activity_Bloglists.class));
                } else if (notificationkey.equals("diet") && notificationvalue.equals("true")) {
                    //startActivity(new Intent(MainActivity.this, Activity_Dietplans.class));

                    boolean diet = tinydb.getBoolean(Constants.ReadyDietPlan);
                    if (diet) {
                        Intent intent1 = new Intent(MainActivity.this, DefalutAndCustomplanActivity.class);
                        intent1.putExtra("isFrom2", false);
                        startActivity(intent1);
                    } else {
                        Intent intent = new Intent(MainActivity.this, CustomUpdateActivity.class);
                        intent.putExtra("isFrom", false);
                        startActivity(intent);
                    }
                  /*  String tempdate = tinydb.getString(Constants.DIETPLANDATE_KEY);
                    Date c = Calendar.getInstance().getTime();
                     Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
                    String formattedDate = df.format(c);
                    if (!tempdate.isEmpty() && tempdate.equals(formattedDate)) {
                        Intent intent1 = new Intent(MainActivity.this, CustomUpdateActivity.class);
                        intent1.putExtra("isFrom2", false);
                        startActivity(intent1);
                    } else {
                        Intent intent = new Intent(MainActivity.this, DefalutAndCustomplanActivity.class);
                        intent.putExtra("isFrom", false);
                        startActivity(intent);
                    }*/
                } else if (notificationkey.equals("covid") && notificationvalue.equals("true")) {
                    startActivity(new Intent(MainActivity.this, Activity_Covidhome.class));
                } else if (notificationkey.equals("home") && notificationvalue.equals("true")) {
                    startActivity(new Intent(MainActivity.this, MainActivity.class));
                } else if (notificationkey.equals("drziostore") && notificationvalue.equals("true")) {
                    startActivity(new Intent(MainActivity.this, AppstoreActivity.class));
                }
                if (notificationkey.equals("webview")) {
                    Uri uri1 = Uri.parse(notificationvalue);
                    Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri1);
                    try {
                        startActivity(myAppLinkToMarket);
                    } catch (ActivityNotFoundException e) {
                        Toast.makeText(getApplicationContext(), "You don't have Google Play installed", Toast.LENGTH_LONG).show();
                    }
                }

            }
        } catch (Exception e) {

        }



    VersionChecker versionChecker = new VersionChecker();
        try {
            String latestVersion = versionChecker.execute().get();
            String versionName = BuildConfig.VERSION_NAME.replace("-DEBUG", "");

            APIAppUpdate(latestVersion, versionName);
            if (latestVersion != null && !latestVersion.isEmpty()) {
                if (!latestVersion.equals(versionName)) {
                    //    showDialogToSendToPlayStore();
            //        Log.e("update", "Current version " + Float.valueOf(versionName) + ", Playstore version " + Float.valueOf(latestVersion));

                } else {
                   // Log.e("update", "Current version " + Float.valueOf(versionName) + ", Playstore version " + Float.valueOf(latestVersion));

                    //   continueWithTheLogin();
                }
            }
//            Log.e("update", "Current version " + Float.valueOf(versionName) + ", Playstore version " + Float.valueOf(latestVersion));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
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
                if (!tinydb.getBoolean(Constants.PREMIUN_KEY)) {
                    ShowAds();
                }
            }

            @Override
            public void onBillingInitialized() {
                bp.loadOwnedPurchasesFromGoogle();
                String Lifetime1 = tinydb.getString(Constants.ITEM_SKU1);
                String Oneyear1 = tinydb.getString(Constants.SKU_DELAROY_SIXMONTH);
                String Onemonth1 = tinydb.getString(Constants.SKU_DELAROY_MONTHLY);
                if (bp.isPurchased(Lifetime1)) {
                    tinydb.putBoolean(Constants.PREMIUN_KEY, true);
                    NoAds();

                    if (bp.loadOwnedPurchasesFromGoogle()) {
                        Log.d(LOG_TAG, "Owned Subscription: " + "Oneyear Subscriptions updated.");
                    }
                    Log.d(LOG_TAG, "Owned Subscription: " + "onetime");
                } else if (bp.isSubscribed(Onemonth1)) {
                    tinydb.putBoolean(Constants.PREMIUN_KEY, true);
                    NoAds();

                    Log.d(LOG_TAG, "Owned Subscription: " + "1 Month");
                    if (bp.loadOwnedPurchasesFromGoogle()) {
                        Log.d(LOG_TAG, "update2: " + bp.loadOwnedPurchasesFromGoogle());
                    }
                } else if (bp.isSubscribed(Oneyear1)) {
                    tinydb.putBoolean(Constants.PREMIUN_KEY, true);
                    NoAds();

                    if (bp.loadOwnedPurchasesFromGoogle()) {
                        Log.d(LOG_TAG, "Owned Subscription: " + "Oneyear Subscriptions updated.");
                    }
                } else {
                    //   String   Lifetime1 = tinydb.getString(Constants.ITEM_SKU1);
                    tinydb.putBoolean(Constants.PREMIUN_KEY, false);
                    ShowAds();
                    bp.consumePurchase(Lifetime1);
                    Log.d(LOG_TAG, "Owned Subscription: " + "No Subscripytions");
                }
            }

            @Override
            public void onPurchaseHistoryRestored() {
               /* if (bp.loadOwnedPurchasesFromGoogle()) {
                    tinydb.putBoolean(Constants.PREMIUN_KEY,true);
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

    private void callRegisterApi1(String firstName, String lastName, String email, String gender,
                                  String age, String height, String weight, String country_id,
                                  String state_id, String city_id, String fcm_token, String google, String langauge,
                                  String mHeightP, String mWeightP) {


        if (BASEURL.isEmpty()) {
            callfirebasedata();
        } else {
            try {
                String getusertype = tinydb.getString(Constants.USERTYPEKEY);
                JSONObject paramObject = new JSONObject();
                String androidId = 22+Settings.Secure.getString(getContentResolver(),
                        Settings.Secure.ANDROID_ID)+22;

                paramObject.put("gender", gender);
                paramObject.put("age", age);
                paramObject.put("height", height);
                paramObject.put("weight", weight);
                paramObject.put("country", country_id);
                paramObject.put("state", state_id);
                paramObject.put("city", city_id);
                paramObject.put("fcm_token", fcm_token);
                paramObject.put("google", google);
                paramObject.put("language", langauge);
                paramObject.put("device_id", androidId);
                paramObject.put("heightP", mHeightP);
                paramObject.put("weightP", mWeightP);
                paramObject.put("app_number", String.valueOf(22));
                paramObject.put("user_type", getusertype);
                //   this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);

                String token = tinydb.getString(Constants.Authtoken);

                //  String token = tinydb.getString(Constants.Authtoken);
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();
                BASEURL = tinydb.getString(Constants.NewBaseUrl);

                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                Retrofit retrofit = new Retrofit.Builder().baseUrl(BASEURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build())
                        .build();
                //  ApiInterface   apiInterface = (ApiInterface) CelluliteAPIClient.getCelluliteClient1().create(ApiInterface.class);

                //  apiInterface1 = (ApiInterface) CelluliteAPIClient.getCelluliteClient1().create(ApiInterface.class);
                ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        /*    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(loggingInterceptor);

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            OkHttpClient client = httpClient.build();
            // String   BaseUrl23 = tinydb.getString(Constants.NewFemaleFitness);
            BASEURL = tinydb.getString(Constants.NewBaseUrl);
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASEURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();*/
                //  this.apiInterfaces = retrofit.create(ApiInterface.class);

                Call<Register_Api> userCall = apiInterface.getUser(paramObject.toString());
                userCall.enqueue(new Callback<Register_Api>() {
                    @Override
                    public void onResponse(Call<Register_Api> call, Response<Register_Api> response) {
                        try {


                            Register_Api dietCateData = (Register_Api) response.body();

                            Register_Api.Docs docs = dietCateData.getDocs();

                            String token_skip = dietCateData.getToken();
                            Log.e("tokenskip", token_skip);
                            tinydb.putString(Constants.Authtoken, dietCateData.token);

                            String userid = docs.getId();
                            tinydb.putString(Constants.USERID_KEYs, userid);

                            Log.e("getfName", String.valueOf(docs.getDeviceId() + "fgfhfgh"));
                            String tpken = tinydb.getString(Constants.Authtoken);
                            tinydb.putBoolean(Constants.SKIP_NOINTERNET, false);
                            if (tpken != null) {
                                getBanner();

                            }
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<Register_Api> call, Throwable t) {

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
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
                            tinydb.putString(Constants.AppstoreUrl, (String) postSnapshot.getValue());
                            Log.e("Api1", (String) postSnapshot.getValue());
                        }
                        if (name.equals("DeepSleep")) {
                            tinydb.putString(Constants.CelluliteUrl, (String) postSnapshot.getValue());
                            Log.e("Api2", (String) postSnapshot.getValue());
                        }
                        if (name.equals("YoutubeApi")) {
                            tinydb.putString(Constants.YoutubeApi, (String) postSnapshot.getValue());
                            Log.e("Api3", (String) postSnapshot.getValue());
                        }
                        if (name.equals("Appstore_Appid")) {
                            tinydb.putString(Constants.Appstore_Appid, (String) postSnapshot.getValue());
                            Log.e("Api4", (String) postSnapshot.getValue());
                        }
                        if (name.equals("Banner_id")) {
                            tinydb.putString(Constants.Storebanner_id, (String) postSnapshot.getValue());
                            Log.e("Api5", (String) postSnapshot.getValue());
                        }
                        if (name.equals("NewsleepApi")) {
                            tinydb.putString(Constants.NewBaseUrl, (String) postSnapshot.getValue());

//                              tinydb.putString(Constants.NewBaseUrl, "http://65.0.77.129:7079/");
                            Log.e("Api6", (String) postSnapshot.getValue());
                            Log.e("Apitest", tinydb.getString(Constants.NewBaseUrl));
                        }
                        if (name.equals("sleepimgurl")) {
                            tinydb.putString(Constants.Backimageurl, (String) postSnapshot.getValue());
                            Log.e("Api7", (String) postSnapshot.getValue());
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {

        }

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
                            tinydb.putString(Constants.DISCOUNT_TXT, "null");
                        } else {
                            tinydb.putString(Constants.DISCOUNT_TXT, percentage);
                        }
                        if (percentage2.isEmpty()) {
                            tinydb.putString(Constants.DISCOUNT_TXT2, "null");
                        } else {
                            tinydb.putString(Constants.DISCOUNT_TXT2, percentage2);
                        }
                        tinydb.putString(Constants.SKU_DELAROY_MONTHLY, month_key);
                        tinydb.putString(Constants.SKU_DELAROY_SIXMONTH, one_y_key);
                        tinydb.putString(Constants.ITEM_SKU1, life_t_key);

                        PurchaseCheck();


                    } catch (Exception e) {

                    }


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
        try {
            String BaseUrl23 = tinydb.getString(Constants.NewBaseUrl);
            String token = tinydb.getString(Constants.Authtoken);
            Log.e("token_skip", String.valueOf(token));
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(loggingInterceptor);

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            OkHttpClient client = httpClient.build();
            // String   BaseUrl23 = tinydb.getString(Constants.NewFemaleFitness);
            BASEURL = tinydb.getString(Constants.NewBaseUrl);
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASEURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
            ApiInterface apiInterface = retrofit.create(ApiInterface.class);
            //  ApiInterface   apiInterface = retrofit.create(ApiInterface.class);

            // ApiInterface apiInterface = (ApiInterface) BackpainAPIClient.getCelluliteClient1().create(ApiInterface.class);
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
                            tinydb.putString(Constants.Authtoken, token);
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
                    tinydb.putLong(Constants.TOTALTIME_KEY, milliSeconds);*/
                            tinydb.putLong(Constants.TOTALTIME_KEY, Long.parseLong(time));

                            String user_id = user.getId();
                            tinydb.putString(Constants.USERID, user_id);


                            ExituserData.User.Referral referral = user.getReferral();
                            String reffalcode = referral.getCode();
                            int referredCount = referral.getReferredCount();


                            int ExeCount = user.getExercise();
                            tinydb.putInt(Constants.TOTALEXE_KEY, ExeCount);
                            float Calory = user.getBurntCalory();
                            tinydb.putFloat(Constants.TOTALKCAL_KEY, Calory);
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
        } catch (Exception e) {

        }
    }


    private void getBmrdata() {

        try {
            String token = tinydb.getString(Constants.Authtoken);
            String BASEURL = tinydb.getString(Constants.NewBaseUrl);
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


            Call<Bmrdata> userCall = apiInterface.getBmrdata("Bearer " + token);
            userCall.enqueue(new Callback<Bmrdata>() {
                @Override
                public void onResponse(Call<Bmrdata> call, Response<Bmrdata> response) {
                    try {

                        Log.e("response", String.valueOf(response));
                        Bmrdata bmrdata = response.body();
                        Integer getbmrdata = bmrdata.getBmr();
                        Log.e(TAG, "getbmrdata: " + getbmrdata);
                        tinydb.putInt(Constants.BMR, getbmrdata);


                    } catch (Exception e) {
                        Log.e("responsecatch", String.valueOf(e));
                    }


                }

                @Override
                public void onFailure(Call<Bmrdata> call, Throwable t) {
                    Log.e("responsevbanner", String.valueOf(t));
                }
            });
        } catch (Exception e) {


        }

    }

    public void DialogDiscount80() {
        dialdiscount = new Dialog(MainActivity.this);
        Objects.requireNonNull(dialdiscount.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialdiscount.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialdiscount.setContentView(R.layout.dialog_discount1);
        ImageView mIvmain = (ImageView) dialdiscount.findViewById(R.id.ivmain);
        TextView mTxtcancel = (TextView) dialdiscount.findViewById(R.id.tvcancel);
        dialdiscount.setCancelable(false);
        dialdiscount.setCanceledOnTouchOutside(false);
        mIvmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialdiscount.dismiss();
                Intent intent = new Intent(MainActivity.this, Activity_Purchase.class);
                startActivity(intent);
            }
        });
        mTxtcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialdiscount.dismiss();
            }
        });
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialdiscount.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialdiscount.show();
        dialdiscount.getWindow().setAttributes(lp);
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

    private void APIAppUpdate(String latestVersion, String versionChecker) {
        try {
            String token = tinydb.getString(Constants.Authtoken);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(loggingInterceptor);

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            OkHttpClient client = httpClient.build();
            // String   BaseUrl23 = tinydb.getString(Constants.NewFemaleFitness);
            BASEURL = tinydb.getString(Constants.NewBaseUrl);
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASEURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
            //     apiInterface = (ApiInterface) CelluliteAPIClient.getCelluliteClient1().create(ApiInterface.class);
            //   ApiInterface apiInterface = (ApiInterface) CelluliteAPIClient.getCelluliteClient1().create(ApiInterface.class);
            ApiInterface apiInterface = retrofit.create(ApiInterface.class);
            try {
                JSONObject paramObject = new JSONObject();
                paramObject.put("lVersion", latestVersion);
                paramObject.put("uVersion", versionChecker);

                Call<AppUpdateApi> userCall = apiInterface.appupdateapi(paramObject.toString(), "Bearer " + token);
                userCall.enqueue(new Callback<AppUpdateApi>() {
                    @Override
                    public void onResponse(Call<AppUpdateApi> call, Response<AppUpdateApi> response) {
                        try {


                            AppUpdateApi loginData = response.body();

                            Boolean appupdate = loginData.getStatus();

                            Log.e("appupdate", String.valueOf(appupdate));
                            if (appupdate) {
                                showUpdateDialog();
                            }
                        } catch (Exception e) {

                        }

                    }

                    @Override
                    public void onFailure(Call<AppUpdateApi> call, Throwable t) {
                        Log.e("response1s", String.valueOf(t));
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {

        }
    }


    private void showUpdateDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.drzio_updatestring));
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse
                        ("market://details?id=yourAppPackageName")));
                dialog.dismiss();
            }
        });
       /* builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                background.start();
            }
        });*/
        builder.setCancelable(false);
        dialog = builder.show();
    }

    /*  public class VersionChecker extends AsyncTask<String, String, String> {

          @Override
          protected String doInBackground(String... params) {

              String newVersion = null;

              try {
                  Document document = Jsoup.connect("https://play.google.com/store/apps/details?id=" + getPackageName() + "&hl=en")
                          .timeout(30000)
                          .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                          .referrer("http://www.google.com")
                          .get();
                  if (document != null) {
                      Elements element = document.getElementsContainingOwnText("Current Version");
                      for (Element ele : element) {
                          if (ele.siblingElements() != null) {
                              Elements sibElemets = ele.siblingElements();
                              for (Element sibElemet : sibElemets) {
                                  newVersion = sibElemet.text();
                              }
                          }
                      }
                  }

              } catch (IOException e) {
                  e.printStackTrace();
              }
              return newVersion;
          }
      }*/
    class versionChecker extends AsyncTask<String, String, String> {
        String newVersion = null;

        @Override
        protected String doInBackground(String... params) {

            try {
                newVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + getPackageName() + "&hl=en")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get()
                        .select("div[itemprop=softwareVersion]")
                        .first()
                        .ownText();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return newVersion;
        }
    }

    public void showBanner(Context context, final FrameLayout adMobView, RelativeLayout loadlayout, RelativeLayout offdiscount1, ShimmerFrameLayout mLoadlay) {
        try {
            final AdView mAdView = new AdView(context);
            mAdView.setAdSize(AdSize.BANNER);
            mAdView.setAdUnitId(Constants.admob_banner);
            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    loadlayout.setVisibility(View.GONE);
                    mLoadlay.setVisibility(View.GONE);
                    if (adMobView != null) {
                        adMobView.removeAllViews();
                    }
                    adMobView.addView(mAdView);
                    offdiscount1.setVisibility(View.VISIBLE);
                    loadlayout.setVisibility(View.VISIBLE);
                    adMobView.setVisibility(View.VISIBLE);
                    Constants.isReportBanner = false;
                }

                @Override
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    refreshHandler.removeCallbacks(refreshRunnable);
                    refreshHandler.postDelayed(refreshRunnable, REFRESH_RATE_IN_SECONDS * 1000);
                    loadlayout.setVisibility(View.GONE);
                    loadlayout.setVisibility(View.GONE);
                    offdiscount1.setVisibility(View.GONE);
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


    private class RefreshRunnable implements Runnable {
        @Override
        public void run() {
            showBanner(getApplicationContext(), mGbannerlay2, banner1, ad_rela, mLoadlay);


        }
    }
  /*  public void initbottomnavigation() {
        mIvdiscover2 = (ImageView) findViewById(R.id.ivdiscover2);
        mIvProgress2 = (ImageView) findViewById(R.id.ivprogress2);
        mIvhome2 = (ImageView) findViewById(R.id.ivhome2);
        mIvEarn2 = (ImageView) findViewById(R.id.ivearn2);
        mIvPurchase2 = (ImageView) findViewById(R.id.ivpurchase2);
        mIvdiscover3 = (ImageView) findViewById(R.id.ivdiscover3);
        mIvProgress3 = (ImageView) findViewById(R.id.ivprogress3);
        mIvhome3 = (ImageView) findViewById(R.id.ivhome3);
        mIvEarn3 = (ImageView) findViewById(R.id.ivearn3);
        mIvPurchase3 = (ImageView) findViewById(R.id.ivpurchase3);
        LinearLayout mLaydiscover = (LinearLayout) findViewById(R.id.laydiscover);
        LinearLayout mLayprogress = (LinearLayout) findViewById(R.id.layprogress);
        LinearLayout mLayhome = (LinearLayout) findViewById(R.id.layhome);
        LinearLayout mLayearn = (LinearLayout) findViewById(R.id.layearn);
        LinearLayout mLaypurchase = (LinearLayout) findViewById(R.id.laypurchase);
        mTvdiscover = (TextView) findViewById(R.id.tvdiscover);
        mTvprogress = (TextView) findViewById(R.id.tvprogress);
        mTvearn = (TextView) findViewById(R.id.tvearn);
        mTvpurchase = (TextView) findViewById(R.id.tvpurchase);
        mTvdiscover.setTextColor(getResources().getColor(R.color.navunselected));
        mTvprogress.setTextColor(getResources().getColor(R.color.navunselected));
        mTvearn.setTextColor(getResources().getColor(R.color.navunselected));
        mTvpurchase.setTextColor(getResources().getColor(R.color.navunselected));
        mIvhome3.setColorFilter(getResources().getColor(R.color.tbtncolor));
        mIvdiscover3.setColorFilter(getResources().getColor(R.color.grey));
        mIvProgress3.setColorFilter(getResources().getColor(R.color.grey));
        mIvEarn3.setColorFilter(getResources().getColor(R.color.grey));
        mIvPurchase3.setColorFilter(getResources().getColor(R.color.grey));
        mLaydiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_Discover.class);


                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
            }
        });
        mLayprogress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_Bloglists.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
            }
        });
        mLayhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mLayearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
            }
        });
        mLaypurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Activity_Reports.class);


                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
            }
        });
    }*/

    public void initbottomnavigation() {

        mLaydiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                layclick(1);

                /*Intent intent = new Intent(MainActivity2.this, Activity_Discover.class);
                startActivity(intent);*/
            }
        });

        mLaypurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layclick(2);
            }
        });

        mLayhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layclick(3);
            }
        });

        mLayprogress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layclick(4);
             /* Intent intent = new Intent(MainActivity2.this, Activity_Bloglists.class);
                startActivity(intent);*/
            }
        });

        mLayearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layclick(5);
          /*   Intent intent = new Intent(MainActivity2.this, Activity_Settings.class);
                startActivity(intent);*/

            }
        });
    }

    public void layclick(int i) {


        if (i == 1) {
            selectedfragment = new Fragment_Discover();
            Log.e("frag", " discover");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commitNow();

            mTvdiscover.setTextColor(getResources().getColor(R.color.tbtncolor));
            mIvdiscover3.setColorFilter(getResources().getColor(R.color.tbtncolor));
            mTvprogress.setTextColor(getResources().getColor(R.color.navunselected));
            mTvearn.setTextColor(getResources().getColor(R.color.navunselected));
            mTvpurchase.setTextColor(getResources().getColor(R.color.navunselected));
            mIvEarn3.setColorFilter(getResources().getColor(R.color.grey));
            mIvProgress3.setColorFilter(getResources().getColor(R.color.grey));
            mIvhome3.setColorFilter(getResources().getColor(R.color.grey));

            mIvPurchase3.setColorFilter(getResources().getColor(R.color.grey));
            mIvselected = mIvdiscover2;
            mIvunselect1 = mIvProgress2;
            mIvunselect2 = mIvhome2;
            mIvunselect3 = mIvEarn2;
            mIvunselect4 = mIvPurchase2;
        } else if (i == 2) {
            selectedfragment = new Fragment_Report();
            Log.e("frag", " report");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commitNow();
            mTvdiscover.setTextColor(getResources().getColor(R.color.navunselected));
            mIvPurchase3.setColorFilter(getResources().getColor(R.color.tbtncolor));
            mTvprogress.setTextColor(getResources().getColor(R.color.navunselected));
            mTvearn.setTextColor(getResources().getColor(R.color.navunselected));
            mTvpurchase.setTextColor(getResources().getColor(R.color.tbtncolor));
            mIvEarn3.setColorFilter(getResources().getColor(R.color.grey));
            mIvProgress3.setColorFilter(getResources().getColor(R.color.grey));
            mIvhome3.setColorFilter(getResources().getColor(R.color.grey));
            mIvdiscover3.setColorFilter(getResources().getColor(R.color.grey));

            mIvunselect1 = mIvdiscover2;
            mIvunselect3 = mIvProgress2;
            mIvunselect2 = mIvhome2;
            mIvunselect4 = mIvEarn2;
            mIvselected = mIvPurchase2;
        } else if (i == 3) {

            Log.e("frag", " home");

            selectedfragment = new Fragment_main();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commitNow();
            mTvdiscover.setTextColor(getResources().getColor(R.color.navunselected));
            mTvprogress.setTextColor(getResources().getColor(R.color.navunselected));
            mTvearn.setTextColor(getResources().getColor(R.color.navunselected));
            mTvpurchase.setTextColor(getResources().getColor(R.color.navunselected));
            mIvhome3.setColorFilter(getResources().getColor(R.color.tbtncolor));
            mIvdiscover3.setColorFilter(getResources().getColor(R.color.grey));
            mIvProgress3.setColorFilter(getResources().getColor(R.color.grey));
            mIvEarn3.setColorFilter(getResources().getColor(R.color.grey));
            mIvPurchase3.setColorFilter(getResources().getColor(R.color.grey));
            mIvunselect1 = mIvdiscover2;
            mIvunselect2 = mIvProgress2;
            mIvselected = mIvhome2;
            mIvunselect3 = mIvEarn2;
            mIvunselect4 = mIvPurchase2;
        } else if (i == 4) {
            selectedfragment = new Fragment_blogs();
            Log.e("frag", " blog");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commitNow();

            mTvdiscover.setTextColor(getResources().getColor(R.color.navunselected));
            mTvprogress.setTextColor(getResources().getColor(R.color.tbtncolor));
            mTvearn.setTextColor(getResources().getColor(R.color.navunselected));
            mTvpurchase.setTextColor(getResources().getColor(R.color.navunselected));
            mIvProgress3.setColorFilter(getResources().getColor(R.color.tbtncolor));

            mIvhome3.setColorFilter(getResources().getColor(R.color.grey));
            mIvdiscover3.setColorFilter(getResources().getColor(R.color.grey));
            mIvEarn3.setColorFilter(getResources().getColor(R.color.grey));
            mIvPurchase3.setColorFilter(getResources().getColor(R.color.grey));

            mIvunselect1 = mIvdiscover2;
            mIvselected = mIvProgress2;
            mIvunselect3 = mIvhome2;
            mIvunselect4 = mIvEarn2;
            mIvunselect2 = mIvPurchase2;
        } else if (i == 5) {
            selectedfragment = new Fragment_setting();
            Log.e("frag", " setting");
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commitNow();

            mTvdiscover.setTextColor(getResources().getColor(R.color.navunselected));
            mTvprogress.setTextColor(getResources().getColor(R.color.navunselected));
            mTvearn.setTextColor(getResources().getColor(R.color.tbtncolor));
            mTvpurchase.setTextColor(getResources().getColor(R.color.navunselected));
            mIvEarn3.setColorFilter(getResources().getColor(R.color.tbtncolor));
            mIvProgress3.setColorFilter(getResources().getColor(R.color.grey));
            mIvhome3.setColorFilter(getResources().getColor(R.color.grey));
            mIvdiscover3.setColorFilter(getResources().getColor(R.color.grey));
            mIvPurchase3.setColorFilter(getResources().getColor(R.color.grey));
            mIvunselect1 = mIvdiscover2;
            mIvunselect2 = mIvProgress2;
            mIvunselect3 = mIvhome2;
            mIvselected = mIvEarn2;
            mIvunselect4 = mIvPurchase2;
        }

        if (mIvunselect1.getVisibility() == View.VISIBLE) {
            Animation animation;
            animation = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.btmslide_in_bottom);
            mIvunselect1.setAnimation(animation);
            mIvunselect1.setVisibility(View.GONE);
        } else if (mIvunselect2.getVisibility() == View.VISIBLE) {
            Animation animation;
            animation = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.btmslide_in_bottom);
            mIvunselect2.setAnimation(animation);
            mIvunselect2.setVisibility(View.GONE);
        } else if (mIvunselect3.getVisibility() == View.VISIBLE) {
            Animation animation;
            animation = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.btmslide_in_bottom);
            mIvunselect3.setAnimation(animation);
            mIvunselect3.setVisibility(View.GONE);
        } else if (mIvunselect4.getVisibility() == View.VISIBLE) {
            Animation animation;
            animation = AnimationUtils.loadAnimation(getApplicationContext(),
                    R.anim.btmslide_in_bottom);
            mIvunselect4.setAnimation(animation);
            mIvunselect4.setVisibility(View.GONE);
        }
        Animation animation;
        animation = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.btmslide_in_top);
        mIvselected.setAnimation(animation);
        mIvselected.setVisibility(View.VISIBLE);

    }


    public void NavigationData() {
        LinearLayout mLaymt = (LinearLayout) findViewById(R.id.llaymt);
        LinearLayout mLaynp = (LinearLayout) findViewById(R.id.llaynp);
        LinearLayout mLaytip = (LinearLayout) findViewById(R.id.llaytip);
        LinearLayout mLayreport = (LinearLayout) findViewById(R.id.llayreport);
        LinearLayout mLaycovid = (LinearLayout) findViewById(R.id.llaycovid);
        LinearLayout mLaydiscover = (LinearLayout) findViewById(R.id.llaydiscover);
        LinearLayout mLaycustom = (LinearLayout) findViewById(R.id.llaycustom);
        LinearLayout mLayra = (LinearLayout) findViewById(R.id.llayra);
        LinearLayout mLaystore = (LinearLayout) findViewById(R.id.llayas);
        LinearLayout mLayreminder = (LinearLayout) findViewById(R.id.llayremind);
        LinearLayout mLaylang = (LinearLayout) findViewById(R.id.llaylo);
        LinearLayout mLaysetting = (LinearLayout) findViewById(R.id.llaysetting);
        mLayrate = (LinearLayout) findViewById(R.id.llayrate);
        LinearLayout mLayshare = (LinearLayout) findViewById(R.id.llayshare);
        LinearLayout mLayrestart = (LinearLayout) findViewById(R.id.llayrp);
//        LinearLayout mBtncovid = (LinearLayout) findViewById(R.id.btncovid);
        if (tinydb.getBoolean(Constants.RATEDIALOGSHOW_KEY)) {
            mLayrate.setVisibility(View.GONE);
        } else {
            mLayrate.setVisibility(View.VISIBLE);
        }
        /*mBtncovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(MainAct.this, 14);
            }
        });*/
        if (tinydb.getBoolean(Constants.PREMIUN_KEY)) {
            mLayra.setVisibility(View.GONE);
        }
        mLaymt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(MainActivity.this, 0);
            }
        });
        mLaynp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(MainActivity.this, 1);
            }
        });
        mLaytip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(MainActivity.this, 2);
            }
        });
        mLayreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(MainActivity.this, 3);
            }
        });
        mLaycovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(MainActivity.this, 14);
            }
        });
        mLaydiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(MainActivity.this, 4);

            }
        });
        mLaycustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(MainActivity.this, 5);
            }
        });
        mLayra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(MainActivity.this, 6);

            }
        });
        mLaystore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(MainActivity.this, 7);

            }
        });
        mLayreminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(MainActivity.this, 8);

            }
        });
        mLaylang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(MainActivity.this, 9);

            }
        });
        mLaysetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(MainActivity.this, 10);

            }
        });
        mLayrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(MainActivity.this, 11);

            }
        });
        mLayshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(MainActivity.this, 12);

            }
        });
        mLayrestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(MainActivity.this, 13);

            }
        });
    }

    public void click(Context context, int i) {
        if (i == 1) {
         /*   boolean diet = tinydb.getBoolean(Constants.ReadyDietPlan);
            if (diet) {
                Intent intent1 = new Intent(MainActivity.this, DefalutAndCustomplanActivity.class);
                intent1.putExtra("isFrom2", false);
                startActivity(intent1);
            } else {
                Intent intent = new Intent(MainActivity.this, CustomUpdateActivity.class);
                intent.putExtra("isFrom", false);
                startActivity(intent);
            }*/
            String tempdate = tinydb.getString(Constants.DIETPLANDATE_KEY);
            Date c = Calendar.getInstance().getTime();
             Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
            String formattedDate = df.format(c);
            if (!tempdate.isEmpty() && tempdate.equals(formattedDate)) {
                Intent intent1 = new Intent(MainActivity.this, DefalutAndCustomplanActivity.class);
                intent1.putExtra("isFrom2", false);
                startActivity(intent1);
            } else {
                Intent intent = new Intent(MainActivity.this, CustomUpdateActivity.class);
                intent.putExtra("isFrom", false);
                startActivity(intent);
            }
        } else if (i == 2) {
            if (layout.isDrawerOpen(Gravity.LEFT)) {
                layout.closeDrawers();
            }
            selectedfragment = new Fragment_blogs();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commitNow();
            mTvdiscover.setTextColor(getResources().getColor(R.color.navunselected));
            mTvprogress.setTextColor(getResources().getColor(R.color.tbtncolor));
            mTvearn.setTextColor(getResources().getColor(R.color.navunselected));
            mTvpurchase.setTextColor(getResources().getColor(R.color.navunselected));
            mIvhome3.setColorFilter(getResources().getColor(R.color.grey));
            mIvdiscover3.setColorFilter(getResources().getColor(R.color.grey));
            mIvProgress3.setColorFilter(getResources().getColor(R.color.tbtncolor));
            mIvEarn3.setColorFilter(getResources().getColor(R.color.grey));
            mIvPurchase3.setColorFilter(getResources().getColor(R.color.grey));

                /*Intent intent = new Intent(context, Activity_Bloglists.class);
                context.startActivity(intent);
                finish();*/
        } else if (i == 3) {
            if (layout.isDrawerOpen(Gravity.LEFT)) {
                layout.closeDrawers();
            }
            selectedfragment = new Fragment_Report();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commitNow();
            mTvdiscover = (TextView) findViewById(R.id.tvdiscover);
            mTvprogress = (TextView) findViewById(R.id.tvprogress);
            mTvearn = (TextView) findViewById(R.id.tvearn);
            mTvpurchase = (TextView) findViewById(R.id.tvpurchase);
            mTvdiscover.setTextColor(getResources().getColor(R.color.navunselected));
            mTvprogress.setTextColor(getResources().getColor(R.color.navunselected));
            mTvearn.setTextColor(getResources().getColor(R.color.navunselected));
            mTvpurchase.setTextColor(getResources().getColor(R.color.tbtncolor));
            mIvhome3.setColorFilter(getResources().getColor(R.color.grey));
            mIvdiscover3.setColorFilter(getResources().getColor(R.color.grey));
            mIvProgress3.setColorFilter(getResources().getColor(R.color.grey));
            mIvEarn3.setColorFilter(getResources().getColor(R.color.grey));
            mIvPurchase3.setColorFilter(getResources().getColor(R.color.tbtncolor));
           /* Intent intent = new Intent(context, Activity_Reports.class);
            context.startActivity(intent);
            finish();*/
        } else if (i == 4) {
            if (layout.isDrawerOpen(Gravity.LEFT)) {
                layout.closeDrawers();
            }
            selectedfragment = new Fragment_Discover();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commitNow();

            mTvdiscover.setTextColor(getResources().getColor(R.color.tbtncolor));
            mTvprogress.setTextColor(getResources().getColor(R.color.navunselected));
            mTvearn.setTextColor(getResources().getColor(R.color.navunselected));
            mTvpurchase.setTextColor(getResources().getColor(R.color.navunselected));
            mIvhome3.setColorFilter(getResources().getColor(R.color.grey));
            mIvdiscover3.setColorFilter(getResources().getColor(R.color.tbtncolor));
            mIvProgress3.setColorFilter(getResources().getColor(R.color.grey));
            mIvEarn3.setColorFilter(getResources().getColor(R.color.grey));
            mIvPurchase3.setColorFilter(getResources().getColor(R.color.grey));
             /*Intent intent = new Intent(context, Activity_Discover.class);
            context.startActivity(intent);*/
        } else if (i == 5) {
            Intent intent = new Intent(context, Activity_MyTraining.class);
            context.startActivity(intent);
            //  finish();
        } else if (i == 6) {
            Intent intent = new Intent(context, Activity_Purchase.class);
            context.startActivity(intent);
        } else if (i == 7) {

            boolean cgender = tinydb.getBoolean(Constants.Genderchoose);
            if (cgender) {
                if (tinydb.getString(Constants.GENDER_KEY).equals(getResources().getString(R.string.female))) {
                    Intent intent = new Intent(MainActivity.this, AppstoreActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, MaleAppstoreActivity.class);
                    startActivity(intent);
                }
            } else {
                Intent intent = new Intent(MainActivity.this, ChooseGenderActivity.class);
                startActivity(intent);
            }
          /*  Intent intent = new Intent(context, AppstoreActivity.class);
            context.startActivity(intent);*/
        } else if (i == 8) {
            Intent intent = new Intent(context, ReminderMainActivity.class);
            context.startActivity(intent);
        } else if (i == 9) {
           /* Intent ttsIntent = new Intent();
            ttsIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
            ttsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(ttsIntent);*/
            DialogLanguage();
        } else if (i == 10) {
            if (layout.isDrawerOpen(Gravity.LEFT)) {
                layout.closeDrawers();
            }
            selectedfragment = new Fragment_setting();
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedfragment).commitNow();
            mTvdiscover = (TextView) findViewById(R.id.tvdiscover);
            mTvprogress = (TextView) findViewById(R.id.tvprogress);
            mTvearn = (TextView) findViewById(R.id.tvearn);
            mTvpurchase = (TextView) findViewById(R.id.tvpurchase);
            mTvdiscover.setTextColor(getResources().getColor(R.color.navunselected));
            mTvprogress.setTextColor(getResources().getColor(R.color.navunselected));
            mTvearn.setTextColor(getResources().getColor(R.color.tbtncolor));
            mTvpurchase.setTextColor(getResources().getColor(R.color.navunselected));
            mIvhome3.setColorFilter(getResources().getColor(R.color.grey));
            mIvdiscover3.setColorFilter(getResources().getColor(R.color.grey));
            mIvProgress3.setColorFilter(getResources().getColor(R.color.grey));
            mIvEarn3.setColorFilter(getResources().getColor(R.color.tbtncolor));
            mIvPurchase3.setColorFilter(getResources().getColor(R.color.grey));
            /*Intent intent = new Intent(context, Activity_Settings.class);
            context.startActivity(intent);*/
        } else if (i == 11) {
            DialogRatexeperience();
        } else if (i == 12) {
            shareApp(context);
        } else if (i == 13) {
            Resetdialog();
        } else if (i == 14) {
            if (!isClicked) {
                isClicked = true;
                Intent intent5 = new Intent(MainActivity.this, Activity_Covidhome.class);
                intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent5);
                finish();
            }
        }
    }

    public void DialogDiscount() {
        dialdiscount = new Dialog(MainActivity.this);
        Objects.requireNonNull(dialdiscount.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialdiscount.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialdiscount.setContentView(R.layout.dialog_discount);
        ImageView mIvmain = (ImageView) dialdiscount.findViewById(R.id.ivmain);
        TextView mTxtcancel = (TextView) dialdiscount.findViewById(R.id.tvcancel);
        dialdiscount.setCancelable(false);
        dialdiscount.setCanceledOnTouchOutside(false);
        mIvmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialdiscount.dismiss();
                Intent intent = new Intent(MainActivity.this, Activity_Purchase.class);
                startActivity(intent);
            }
        });
        mTxtcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialdiscount.dismiss();
            }
        });
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialdiscount.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialdiscount.show();
        dialdiscount.getWindow().setAttributes(lp);
    }

 /*   public void click(Context context, int i) {
        if (i == 1) {
            String tempdate = tinydb.getString(Constants.DIETPLANDATE_KEY);
            Date c = Calendar.getInstance().getTime();
             Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
            String formattedDate = df.format(c);
            if (!tempdate.isEmpty() && tempdate.equals(formattedDate)) {
                Intent intent1 = new Intent(context, CustomUpdateActivity.class);
                intent1.putExtra("isFrom2", false);
                context.startActivity(intent1);
            } else {
                Intent intent = new Intent(context, DefalutAndCustomplanActivity.class);
                intent.putExtra("isFrom", false);
                context.startActivity(intent);
            }
        } else if (i == 2) {
            if (!isClicked) {
                isClicked = true;
                Intent intent = new Intent(context, Activity_Bloglists.class);
                context.startActivity(intent);
                finish();
            }

        } else if (i == 3) {
            Intent intent = new Intent(context, Activity_Reports.class);
            context.startActivity(intent);
            finish();
        } else if (i == 4) {
            Intent intent = new Intent(context, Activity_Discover.class);
            context.startActivity(intent);
        } else if (i == 5) {
            Intent intent = new Intent(context, Activity_MyTraining.class);
            context.startActivity(intent);
            finish();
        } else if (i == 6) {
            Intent intent = new Intent(context, Activity_Purchase.class);
            context.startActivity(intent);
        } else if (i == 7) {

            boolean cgender = tinydb.getBoolean(Constants.Genderchoose);
            if (cgender) {
                if (tinydb.getString(Constants.GENDER_KEY).equals(getResources().getString(R.string.female))) {
                    Intent intent = new Intent(MainActivity.this, AppstoreActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, MaleAppstoreActivity.class);
                    startActivity(intent);
                }
            } else {
                Intent intent = new Intent(MainActivity.this, ChooseGenderActivity.class);
                startActivity(intent);
            }
          *//*  Intent intent = new Intent(context, AppstoreActivity.class);
            context.startActivity(intent);*//*
        } else if (i == 8) {
            Intent intent = new Intent(context, ReminderMainActivity.class);
            context.startActivity(intent);
        } else if (i == 9) {
           *//* Intent ttsIntent = new Intent();
            ttsIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
            ttsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(ttsIntent);*//*
            DialogLanguage();
        } else if (i == 10) {
            Intent intent = new Intent(context, Activity_Settings.class);
            context.startActivity(intent);
        } else if (i == 11) {
            DialogRatexeperience();
        } else if (i == 12) {
            shareApp(context);
        } else if (i == 13) {
            Resetdialog();
        } else if (i == 14) {
            if (!isClicked) {
                isClicked = true;
                Intent intent5 = new Intent(MainActivity.this, Activity_Covidhome.class);
                intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent5);
                finish();
            }
        }
    }*/

    public void InitLoginlayouts() {
        mBtnlogout = (ImageView) findViewById(R.id.btn_logout);
        mTxtword = (TextView) findViewById(R.id.txtword);
        mloginlay = (LinearLayout) findViewById(R.id.loginlayout);
        mSucceslayout = (LinearLayout) findViewById(R.id.successlayout);
        mProfilename = (TextView) findViewById(R.id.profilename);
        mProfilepic = (AvatarImageView) findViewById(R.id.profile_image);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        signInButton = (ImageView) findViewById(R.id.sign_in_button);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addApi(LocationServices.API)
                .build();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hasPermissionLocation = (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
                if (!hasPermissionLocation) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_ACCESS_FINE_LOCATION);
                }

                isFromFb = false;
                isFromGoogle = true;
                signIn();

            }
        });

        mBtnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tinydb.getBoolean(Constants.ISLOGIN_KEY)) {
                    logoutdialog();
                }
              /*  if (isFromFb) {
                    logout();
                } else {
                    signOut();
                }*/
            }
        });


    }

    public void logoutdialog() {
       /* if (!fitnessApplication.isSpeaking()) {
            fitnessApplication.speak("Did you hear the test voice?");
        }*/
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(getResources().getString(R.string.are_you_sure));
        alertDialogBuilder.setPositiveButton(getResources().getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        signOut();
                        //btmylogoutprofile.setVisibility(View.GONE);
                        //btnlogouts.setVisibility(View.GONE);
                        dialog.dismiss();
                    }
                });
        alertDialogBuilder.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialogBuilder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

            case REQUEST_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    isFromFb = false;
                    isFromGoogle = true;
                    signIn();
                    //Toast.makeText(MainActivity.this, "Permission granted.", Toast.LENGTH_SHORT).show();

                  /*  finish();
                    startActivity(getIntent());*/

                } else {
                    Toast.makeText(MainActivity.this, "The app was not allowed to get your location, Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }
            }

        }

    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, SIGN_IN_CODE);

    }


    public void InitializeAPlans() {
        mACard = (RelativeLayout) findViewById(R.id.cardview);
        mAtxtDaylefts = (TextView) findViewById(R.id.mdaysLeft);
        mATextapercentage = (TextView) findViewById(R.id.mpercentScore);
        mAprogressbar = (CircularProgressBar) findViewById(R.id.mprogress);

       /* mACard1 = (RelativeLayout) findViewById(R.id.cardview1);
        mAtxtDaylefts1 = (TextView) findViewById(R.id.mdaysLeft1);
        mATextapercentage1 = (TextView) findViewById(R.id.mpercentScore1);
        mAprogressbar1 = (CircularProgressBar) findViewById(R.id.mprogress1);
        ImageView mLock1 = (ImageView) findViewById(R.id.imglock1);

        mACard2 = (RelativeLayout) findViewById(R.id.cardview2);
        mAtxtDaylefts2 = (TextView) findViewById(R.id.mdaysLeft2);
        mATextapercentage2 = (TextView) findViewById(R.id.mpercentScore2);
        mAprogressbar2 = (CircularProgressBar) findViewById(R.id.mprogress2);
        ImageView mLock2 = (ImageView) findViewById(R.id.imglock2);*/

        LinearLayout mBtninfo1 = (LinearLayout) findViewById(R.id.btninfo);
       /* LinearLayout mBtninfo2 = (LinearLayout) findViewById(R.id.btninfo1);
        LinearLayout mBtninfo3 = (LinearLayout) findViewById(R.id.btninfo3);*/

    /*    ViewFlipper viewFlipper = findViewById(R.id.disflipperid);
        viewFlipper.startFlipping();
        RelativeLayout mCarddiscount = (RelativeLayout) findViewById(R.id.carddiscount);
        mCarddiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_Purchase.class);
                startActivity(intent);
            }
        });*/
        if (tinydb.getBoolean(Constants.PREMIUN_KEY)) {

            //   mCarddiscount.setVisibility(View.GONE);
        }

      /*  mBtninfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_Allinfos.class);
                intent.putExtra("pos", 0);
                intent.putExtra("imgnum", 0);
                startActivity(intent);
            }
        });*/
      /*  mBtninfo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_Allinfos.class);
                intent.putExtra("pos", 1);
                intent.putExtra("imgnum", 1);
                startActivity(intent);
            }
        });
        mBtninfo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Activity_Allinfos.class);
                intent.putExtra("pos", 2);
                intent.putExtra("imgnum", 2);
                startActivity(intent);
            }
        });*/
       /* mACard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isclicked) {
                    isclicked = true;
//                    Gad = 1;
//                    if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
//                        mInterstitialAdMob.show();
//                    }else {
                    Intent intent = new Intent(activity, Activity_Level1.class);
                    startActivity(intent);
                    finish();
//                    }
                }

            }
        });*/

      /*  mACard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isclicked) {
                    isclicked = true;
//                    Gad = 2;
//                    if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
//                        mInterstitialAdMob.show();
//                    }else {
                    Intent intent = new Intent(activity, Activity_Level2.class);
                    startActivity(intent);
                    finish();
//                    }
                }
            }
        });

        mACard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isclicked) {
                    isclicked = true;
//                    Gad = 3;
//                    if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
//                        mInterstitialAdMob.show();
//                    }else {
                    Intent intent = new Intent(activity, Activity_Level3.class);
                    startActivity(intent);
                    finish();
//                    }
                }
            }
        });*/
    }

    public void InitializeBPlans() {

        RelativeLayout mNutrition = (RelativeLayout) findViewById(R.id.nbcardview);
        mNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


         /*       boolean diet = tinydb.getBoolean(Constants.ReadyDietPlan);
                if (diet) {
                    Intent intent1 = new Intent(MainActivity.this, DefalutAndCustomplanActivity.class);
                    intent1.putExtra("isFrom2", false);
                    startActivity(intent1);
                } else {
                    Intent intent = new Intent(MainActivity.this, CustomUpdateActivity.class);
                    intent.putExtra("isFrom", false);
                    startActivity(intent);
                }*/


                String tempdate = tinydb.getString(Constants.DIETPLANDATE_KEY);
                Date c = Calendar.getInstance().getTime();
                 Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
                String formattedDate = df.format(c);
                if (!tempdate.isEmpty() && tempdate.equals(formattedDate)) {
                    Intent intent1 = new Intent(MainActivity.this, DefalutAndCustomplanActivity.class);
                    intent1.putExtra("isFrom2", false);
                    startActivity(intent1);
                } else {
                    Intent intent = new Intent(MainActivity.this, CustomUpdateActivity.class);
                    intent.putExtra("isFrom", false);
                    startActivity(intent);
                }
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
                if (Gad == 1) {
                    Intent intent = new Intent(activity, Activity_Level1.class);
                    startActivity(intent);
                    //  finish();
                } else if (Gad == 2) {
                    Intent intent = new Intent(activity, Activity_Level2.class);
                    startActivity(intent);
                    finish();
                } else if (Gad == 3) {
                    Intent intent = new Intent(activity, Activity_Level3.class);
                    startActivity(intent);
                    finish();
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
                FitnessApplication.AdfailToast("MainActivity Interstatials", String.valueOf(i));
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


 /*   public void scheduleAlarm() {
        long time = new GregorianCalendar().getTimeInMillis() +  5 * 60 * 1000;
        Intent intentAlarm = new Intent(this, BroadcastManager.class);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        assert alarmManager != null;
        alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
    }*/


    private void Animuser() {
        String SHOWCASE_ID = getResources().getString(R.string.back_pain_relief);

        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(200); // half second between each showcase view

        sequence = new MaterialShowcaseSequence(this, SHOWCASE_ID);

        sequence.setOnItemShownListener(new MaterialShowcaseSequence.OnSequenceItemShownListener() {
            @Override
            public void onShow(MaterialShowcaseView itemView, int position) {
                //   Toast.makeText(itemView.getContext(), "Item #" + position, Toast.LENGTH_SHORT).show();
            }
        });

        sequence.setConfig(config);

        sequence.addSequenceItem(relativebottom5, getResources().getString(R.string.sett_edu), getResources().getString(R.string.tap_here));

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setSkipText(getResources().getString(R.string.skip))
                        .setTarget(relativebottom2)
                        .setDismissText(getResources().getString(R.string.tap_here))
                        .setContentText(getResources().getString(R.string.repo_edu))
                        .withCircleShape()

                        .build()
        );
        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setTarget(relativebottom3)
                        .setDismissText(getResources().getString(R.string.tap_here))
                        .setContentText(getResources().getString(R.string.hom_edu))
                        .withCircleShape()
                        .build()
        );

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setTarget(relativebottom1)
                        .setDismissText(getResources().getString(R.string.tap_here))
                        .setContentText(getResources().getString(R.string.dis_edu))
                        .withCircleShape()
                        .build()
        );
        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder(this)
                        .setTarget(relativebottom4)
                        .setDismissText(getResources().getString(R.string.tap_here))
                        .setContentText(getResources().getString(R.string.blog_edu))
                        .withCircleShape()
                        .build()
        );
        try {
            tinydb.putBoolean("donedone", true);

        } catch (Exception e) {
            Log.e("notgoing", "notgoing");
        }

        sequence.start();

    }


    public void scheduletwoAlarm() {
        long time = new GregorianCalendar().getTimeInMillis() + 48 * 60 * 60 * 1000;
        Log.e("timetime", String.valueOf(time));
//        long time = new GregorianCalendar().getTimeInMillis() + 60000;
        Intent intentAlarm = new Intent(this, BroadcastManager.class);
        intentAlarm.setAction("ALARM");
        intentAlarm.addCategory("android.intent.category.DEFAULT");
        intentAlarm.putExtra("ID", 1);
        @SuppressLint("WrongConstant") AlarmManager alarmManager = (AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM);
        assert alarmManager != null;
//        alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(this, 1, intentAlarm, 0));
        } else if (Build.VERSION.SDK_INT >= 19) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(this, 1, intentAlarm, 0));
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(this, 1, intentAlarm, 0));
        }
    }

    public void scheduleAlarm() {
        long time = new GregorianCalendar().getTimeInMillis() + 24 * 60 * 60 * 1000;
        //     long time = new GregorianCalendar().getTimeInMillis() + 60000;
        Intent intentAlarm = new Intent(this, ForonedayBroadcastManager.class);
        intentAlarm.setAction("ALARM");
        intentAlarm.addCategory("android.intent.category.DEFAULT");
        intentAlarm.putExtra("ID", 1);
        AlarmManager alarmManager = (AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM);
        assert alarmManager != null;
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(this, 1, intentAlarm, 0));
        } else if (Build.VERSION.SDK_INT >= 19) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(this, 1, intentAlarm, 0));
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(this, 1, intentAlarm, 0));
        }
    }

    public void schedule6oclockAlarm1() {
        long time = new GregorianCalendar().getTimeInMillis() + 6 * 60 * 60 * 1000;
        Log.e("6oclockalaramtimetime", String.valueOf(time));
//        long time = new GregorianCalendar().getTimeInMillis() + 60000;
        Intent intentAlarm = new Intent(this, SixhourBroadcastManager.class);
        intentAlarm.setAction("ALARM");
        intentAlarm.addCategory("android.intent.category.DEFAULT");
        intentAlarm.putExtra("ID", 1);
        @SuppressLint("WrongConstant") AlarmManager alarmManager = (AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM);
        assert alarmManager != null;
//        alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(this, 1, intentAlarm, 0));
        } else if (Build.VERSION.SDK_INT >= 19) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(this, 1, intentAlarm, 0));
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(this, 1, intentAlarm, 0));
        }
    }

    public void schedulegoalAlarm() {
        long time = new GregorianCalendar().getTimeInMillis() + 24 * 60 * 60 * 1000;
        Log.e("goalalaramtimetime", String.valueOf(time));
//        long time = new GregorianCalendar().getTimeInMillis() + 60000;
        Intent intentAlarm = new Intent(this, GoalBroadcastManager.class);
        intentAlarm.setAction("ALARM");
        intentAlarm.addCategory("android.intent.category.DEFAULT");
        intentAlarm.putExtra("ID", 1);
        @SuppressLint("WrongConstant") AlarmManager alarmManager = (AlarmManager) getSystemService(NotificationCompat.CATEGORY_ALARM);
        assert alarmManager != null;
//        alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(this, 1, intentAlarm, 0));
        } else if (Build.VERSION.SDK_INT >= 19) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(this, 1, intentAlarm, 0));
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(this, 1, intentAlarm, 0));
        }
    }

    public void Schedulenotification(AlarmHelper alarmHelper2, Calendar calendar) {
        int parseInt;
        int parseInt2;
        int i;
        if (startTimeFormat().format(calendar.getTime()).endsWith("AM")) {
            parseInt = Integer.parseInt(getHourFormat().format(calendar.getTime()));
            parseInt2 = Integer.parseInt(getMinuteFormat().format(calendar.getTime()));
            i = 0;
        } else {
            parseInt = Integer.parseInt(getHourFormat().format(calendar.getTime()));
            parseInt2 = Integer.parseInt(getMinuteFormat().format(calendar.getTime()));
            i = 1;
        }
        alarmHelper2.schedulePendingIntent(parseInt, parseInt2, i);
    }

    public SimpleDateFormat getHourFormat() {
        return new SimpleDateFormat("hh");
    }

    public SimpleDateFormat getMinuteFormat() {
        return new SimpleDateFormat("mm");
    }

    private SimpleDateFormat startTimeFormat() {
        return new SimpleDateFormat("hh:mm a");
    }

    public void Defaultetype(String mType, String mDatalink) {
        if (mType != null) {
            switch (mType) {
                case "Default":
                    Intent intent3 = new Intent(MainActivity.this, Activity_Level1.class);
                    startActivity(intent3);
                    //  finish();
                    break;
                case "Mobile":
                    Gotoplay(mDatalink);
                    break;
                case "Customizeplan":
                    Intent intentt = new Intent(MainActivity.this, Activity_MyTraining.class);
                    startActivity(intentt);
                case "Web":
                    String tempdate = tinydb.getString(Constants.DIETPLANDATE_KEY);
                    Date c = Calendar.getInstance().getTime();
                     Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
                    String formattedDate = df.format(c);
                    if (!tempdate.isEmpty() && tempdate.equals(formattedDate)) {
                        Intent intent1 = new Intent(MainActivity.this, DefalutAndCustomplanActivity.class);
                        intent1.putExtra("isFrom2", false);
                        startActivity(intent1);
                    } else {
                        Intent intent = new Intent(MainActivity.this, CustomUpdateActivity.class);
                        intent.putExtra("isFrom", false);
                        startActivity(intent);
                    }
                    break;
                case "Webview":
                    Intent intent = new Intent(MainActivity.this, Activity_webview.class);
                    intent.putExtra("link", mDatalink);
                    startActivity(intent);
                    break;
                case "Blog":


                    Intent intent1 = new Intent(MainActivity.this, Activity_webview.class);
                    intent1.putExtra("link", mDatalink);
                    startActivity(intent1);
                    Log.e("link", mDatalink);
                 /*   Intent intent1 = new Intent(MainActivity.this, MainActivity.class);
                    //   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    Bundle data1 = new Bundle();
                    data1.putInt("discover",4);
                    intent1.putExtras(data1);
                    //    intent.putExtra("isFrom3", true);
                    startActivity(intent1);*/
                  /*  Intent intent2 = new Intent(MainActivity.this, Activity_Blogdetails.class);
                    intent2.putExtra("bid", mDatalink);
                    startActivity(intent2);*/
                    break;
                case "Ads":
                    Intent intent31 = new Intent(MainActivity.this, Activity_Purchase.class);
                    startActivity(intent31);
                    break;
                case "Premium":
                    Intent intent4 = new Intent(MainActivity.this, Activity_Purchase.class);
                    startActivity(intent4);
                    break;
                case "Appstore":
                    Intent intent9 = new Intent(MainActivity.this, AllApp_Activity.class);
                    startActivity(intent9);
                    break;

                case "purchase":
                    Intent intent10 = new Intent(MainActivity.this, Activity_Purchase.class);
                    startActivity(intent10);
                    break;
                case "Covid19":
                    if (!isClicked) {
                        isClicked = true;
                        Intent intent5 = new Intent(MainActivity.this, Activity_Covidhome.class);
                        intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent5);
                        finish();
                    }
                    break;
                default:
                    Intent intent11 = new Intent(MainActivity.this, Activity_Purchase.class);
                    startActivity(intent11);
                    break;
            }
        } else {
            Intent intent3 = new Intent(MainActivity.this, Activity_Level1.class);
            startActivity(intent3);
            //  finish();
        }
    }


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

    public void Adsdialog() {
        Fragment_Dialog dialog = new Fragment_Dialog();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        dialog.show(ft, Fragment_Dialog.TAG);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }


    public static String getCalculatedDate(String date, String dateFormat, int days) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat(dateFormat);
        if (!date.isEmpty()) {
            try {
                cal.setTime(s.parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        cal.add(Calendar.DAY_OF_YEAR, days);
        Log.e("after 3 days", s.format(new Date(cal.getTimeInMillis())));
        return s.format(new Date(cal.getTimeInMillis()));
    }


    private void subscribeToPushService() {
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        Log.d("AndroidBash", "Subscribed");
        String token = FirebaseInstanceId.getInstance().getToken();
        if (token != null) {
            if (!Constants.isTokanAdd) {
                SendToken(token);
            }
            Log.d("AndroidBash", token);
        }
    }


    @SuppressLint("WrongConstant")
    public void SendToken(String token) {
        try {
            this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient().create(BackpainAPIInterface.class);
            StringBuilder sb = new StringBuilder();
            sb.append(Build.BRAND);
            sb.append(", ");
            sb.append(Build.DEVICE);
            sb.append(", ");
            sb.append(Build.MANUFACTURER);
            sb.append(", ");
            sb.append(BuildConfig.VERSION_NAME);
             String androidId = 22+Settings.Secure.getString(getContentResolver(),
                        Settings.Secure.ANDROID_ID)+22;
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("device_token", token)
                    .addFormDataPart("device_id", androidId)
                    .addFormDataPart("device_info", sb.toString())
                    .build();
            this.apiInterface.sendToken(requestBody).enqueue(new Callback<String>() {
                @Override
                public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {
                    String dietCateData = (String) response.body();
//                tinydb.putBoolean(Constants.TOKENSEND_KEY, true);
                    try {
                        Constants.isTokanAdd = true;
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
    }


    public Date stringtoday(String dtStart) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(dtStart);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }


    public static float round(float d, int decimalPlace) {
        return BigDecimal.valueOf(d).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public static String removeLeadingZeroes(String value) {
        return new Integer(value).toString();
    }


    @Override
    protected void onStart() {
        isclicked = false;
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> optPenRes = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (optPenRes.isDone()) {
            Log.e(TAG, "Yayy!");
            GoogleSignInResult result = optPenRes.get();
            handleSignInResult(result);
        } else {
            optPenRes.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                @Override
                public void onResult(GoogleSignInResult googleSignInResult) {
                    handleSignInResult(googleSignInResult);
                }
            });
        }

    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    public void updatedayoffweek() {
        today = LocalDate.now();
        if (tinydb.getInt("fdow") != 0) {
            int dayofweek = tinydb.getInt("fdow");
            if (today != null) {
                if (dayofweek == 1) {
                    today = today.withDayOfWeek(DateTimeConstants.WEDNESDAY);
                    daynames = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
                } else if (dayofweek == 2) {
                    today = today.withDayOfWeek(DateTimeConstants.THURSDAY);
                    daynames = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
                } else if (dayofweek == 3) {
                    today = today.withDayOfWeek(DateTimeConstants.TUESDAY);
                    daynames = new String[]{"Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri"};
                }
            }
            weekgoallist.clear();
            for (int i = -3; i <= 3; i++) {
                Weekgoalmodal weekgoalmodal = new Weekgoalmodal();
                String temp = String.valueOf(today != null ? today.plusDays(i) : null);
                String day = (String) DateFormat.format("dd", stringtoday(temp));
                Log.e("dates", day);
                weekgoalmodal.setName(removeLeadingZeroes(day));
                weekgoallist.add(weekgoalmodal);
            }
//            getDaysNames();
            try {
                goalAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void updategoalday() {
        if (tinydb.getInt("goalday") != 0) {
            mGoalayout.setVisibility(View.VISIBLE);
            mSetgoallayout.setVisibility(View.GONE);
            String goalday = "/" + tinydb.getInt("goalday");
            mGoaldaytxt.setText(goalday);
        }
    }


    public void Gotoplay(String mPlaylink) {
        Uri uri1 = Uri.parse(mPlaylink);
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri1);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(MainActivity.this, "You don't have Google Play installed", Toast.LENGTH_LONG).show();
        }
    }


    public void Rateus() {
        Uri uri1 = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri1);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(MainActivity.this, "You don't have Google Play installed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        if (layout.isDrawerOpen(Gravity.LEFT)) {
            layout.closeDrawers();
        } else {

            if (tinydb.getBoolean(Constants.RATEDIALOGSHOW_KEY)) {
                mBottomSheetDialog.show();
            } else {
                if (clicked_exit) {
                    mBottomSheetDialog.show();
                } else {
                    DialogRatexeperience();
                }
            }
            //mBottomSheetDialog.show();
        }
    }


    public void DialogRatexeperience() {
        dialrateexepe = new Dialog(MainActivity.this);
        dialrateexepe.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialrateexepe.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialrateexepe.setContentView(R.layout.dialog_rateexperience);
        TextView mRatebtn = (TextView) dialrateexepe.findViewById(R.id.btnok);
        TextView mClosebtn = (TextView) dialrateexepe.findViewById(R.id.btnclose);
        TextView mTextexpe = (TextView) dialrateexepe.findViewById(R.id.txtdesx);
        SmileyRatingView smileyRating = dialrateexepe.findViewById(R.id.smiley_view);
        RatingBar ratingBar = dialrateexepe.findViewById(R.id.rating_bar);
        ratingBar.setRating(5);

        mTextexpe.setText(getResources().getString(R.string.rate_txt));
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                temprate = (int) (rating - 1);
                Log.d("Rating", String.valueOf(temprate));
                smileyRating.setSmiley(temprate);
                switch (temprate) {
                    case 0:
                        mTextexpe.setText(getResources().getString(R.string.hated_it));
                        break;
                    case 1:
                        mTextexpe.setText(getResources().getString(R.string.dislike_it));
                        break;
                    case 2:
                        mTextexpe.setText(getResources().getString(R.string.its_ok));
                        break;
                    case 3:
                        mTextexpe.setText(getResources().getString(R.string.liked_it));
                        break;
                    case 4:
                       mTextexpe.setText(getResources().getString(R.string.rate_txt));
                        break;
                }
            }
        });
        mRatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temprate = (int) (ratingBar.getRating() - 1);
                if (temprate == 4) {
                    dialrateexepe.dismiss();
                    tinydb.putBoolean(Constants.RATEDIALOGSHOW_KEY, true);
                    Rateus();
//                    DialogRateOnPlaystore();
                } else {
                    dialrateexepe.dismiss();
                    DialogFeedback();
                }
            }
        });
        mClosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.isRatedialShown = true;
                dialrateexepe.dismiss();
                clicked_exit = true;
            }
        });
        dialrateexepe.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Constants.isRatedialShown = true;
                clicked_exit = true;
            }
        });
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialrateexepe.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialrateexepe.show();
        dialrateexepe.getWindow().setAttributes(lp);
    }


    public void DialogRateOnPlaystore() {
        dialrateonplay = new Dialog(MainActivity.this);
        dialrateonplay.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialrateonplay.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialrateonplay.setContentView(R.layout.dialog_rateonplay);
        TextView mTxtrate = (TextView) dialrateonplay.findViewById(R.id.btnrateus);
        TextView mTxtcancel = (TextView) dialrateonplay.findViewById(R.id.btnclose);

        mTxtrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialrateonplay.dismiss();
                tinydb.putBoolean(Constants.RATEDIALOGSHOW_KEY, true);
                Rateus();
            }
        });
        mTxtcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constants.isRatedialShown = true;
                dialrateonplay.dismiss();
            }
        });
        dialrateonplay.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Constants.isRatedialShown = true;
            }
        });
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialrateonplay.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialrateonplay.show();
        dialrateonplay.getWindow().setAttributes(lp);
    }


    public void DialogFeedback() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this, R.style.MaterialThemeDialog);
        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
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
                Animation mzoom = AnimationUtils.loadAnimation(MainActivity.this, R.anim.zoom_in);
                mCustomfeed.startAnimation(mzoom);
            }
        });
        boolean[] checkedItems = {false, false, false, false, false};
        builder.setTitle(getResources().getString(R.string.your_feedback_is_useful));
        builder.setMultiChoiceItems(feedbacklist, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i, boolean isChecked) {
                if (isChecked) {
                    mSelectedFeedbackslist.add(feedbacklist[i]);
                }
            }
        });
        builder.setPositiveButton(getResources().getString(R.string.submit), new DialogInterface.OnClickListener() {
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

                String mUid = tinydb.getString(Constants.USERID_KEY);
                String mComment = mCustomfeed.getText().toString();
                // callFeedbackApi(mOp1, mOp2, mOp3, mOp4, mOp5, mComment, mUid);


                String device_name = Build.BRAND + ", " + Build.DEVICE + ", " + Build.MANUFACTURER + ", " + BuildConfig.VERSION_NAME;
                  String androidId = 22+Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID)+2;
                String email = tinydb.getString(Constants.USEREMAIL_KEY);

                Log.e("TAG", "onClick: " + mOp1 + mOp2 + mOp3 + mOp4 + mOp5 + mComment + "fgfgh " + email + "fgfgg");
                String release = Build.VERSION.RELEASE;
                callFeedbackApi(androidId, device_name, release, email, mOp1, mOp2, mOp3, mOp4, mOp5, mComment/*, mComment, mUid*/);
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Constants.isRatedialShown = true;
                clicked_exit = true;
                dialog.dismiss();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                clicked_exit = true;
                Constants.isRatedialShown = true;
            }
        });
        feedbackdial = builder.create();
        feedbackdial.show();
    }

    @SuppressLint("BASEURL")
    public void BottomExit() {
        final View view = MainActivity.this.getLayoutInflater().inflate(R.layout.dialog_exit, null);
        RelativeLayout mLayad = (RelativeLayout) view.findViewById(R.id.localad2);
        TextView mBtncancel = (TextView) view.findViewById(R.id.btnexit);
        ImageView mBtnExit = (ImageView) view.findViewById(R.id.imgclose);
        ImageView mIvappic = (ImageView) view.findViewById(R.id.imageView);
        ImageView mIvbanner = (ImageView) view.findViewById(R.id.imageView3);
        TextView mTvappname = (TextView) view.findViewById(R.id.appname);
        HtmlTextView mTvdesc = (HtmlTextView) view.findViewById(R.id.appdesc);
        TextView mBtnInstall = (TextView) view.findViewById(R.id.textView2);
        LinearLayout mAdframebottom = (LinearLayout) view.findViewById(R.id.adframebottom);
        showGOOGLEAdvance2(MainActivity.this, mAdframebottom);

        mBottomSheetDialog = new Dialog(MainActivity.this, R.style.MaterialDialogSheet);
        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
//        mBottomSheetDialog.show();

        mBtncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
                LastTimeAppApi();
                finish();
            }
        });
        mBtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
            }
        });

    }


    private void LastTimeAppApi() {
        try {
            Date c = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("MM dd yyyy hh:mm:ss a");
            String formattedDate = df.format(c);
            Log.e("formattedDate", formattedDate);
            String token = tinydb.getString(Constants.Authtoken);
            Log.e("tokentoken", token);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(loggingInterceptor);
            OkHttpClient client = httpClient.build();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            String BaseUrl23 = tinydb.getString(Constants.NewBaseUrl);

            Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseUrl23)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
     /*   Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.URL_BASE)
                .addConverterFactory(create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();*/
            //  apiInterface = (ApiInterface) CelluliteAPIClient.getCelluliteClient1().create(ApiInterface.class);
            ApiInterface apiInterface = (ApiInterface) BackpainAPIClient.getCelluliteClient1().create(ApiInterface.class);
            //   ApiInterface   apiInterface = retrofit.create(ApiInterface.class);
            try {
                JSONObject paramObject = new JSONObject();
                paramObject.put("last_time", formattedDate);


                Call<AddLastTime> userCall = apiInterface.addlasttime(paramObject.toString(), "Bearer " + token);
                userCall.enqueue(new Callback<AddLastTime>() {
                    @Override
                    public void onResponse(Call<AddLastTime> call, Response<AddLastTime> response) {
                        try {


                            Log.e("response", String.valueOf(response.body()));
                            AddLastTime badge = response.body();
                            boolean ok = badge.getOk();
                            Log.e("response1", String.valueOf(ok));
                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onFailure(Call<AddLastTime> call, Throwable t) {
                        Log.e("response1", String.valueOf(t));
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {

        }
    }

    public void Playstoreopen(String name) {
//        Uri uri1 = Uri.parse("https://play.google.com/store/apps/details?id=" + name);
        Uri uri1 = Uri.parse(name);
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri1);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(MainActivity.this, "You don't have Google Play installed", Toast.LENGTH_LONG).show();
        }
    }

    private int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
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

    public void showGOOGLEAdvance2(final Context context, final LinearLayout frameLayout) {
        AdLoader.Builder builder = new AdLoader.Builder(context, Constants.admob_nativead);
        builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
            public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                frameLayout.setVisibility(View.VISIBLE);
                NativeContentAdView nativeContentAdView = (NativeContentAdView) ((Activity) context).getLayoutInflater().inflate(R.layout.ad_content, null);
                populateContentAdView2(nativeContentAd, nativeContentAdView);
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
    public static void populateContentAdView2(NativeContentAd nativeContentAd, NativeContentAdView nativeContentAdView) {
        nativeContentAdView.setHeadlineView(nativeContentAdView.findViewById(R.id.ad_title_textview));
        nativeContentAdView.setImageView(nativeContentAdView.findViewById(R.id.contentad_image));
        nativeContentAdView.setBodyView(nativeContentAdView.findViewById(R.id.ad_describe_textview));
        nativeContentAdView.setCallToActionView(nativeContentAdView.findViewById(R.id.ad_action_button));
        nativeContentAdView.setLogoView(nativeContentAdView.findViewById(R.id.ad_icon_imageview));
        ((TextView) nativeContentAdView.getHeadlineView()).setText(nativeContentAd.getHeadline());
        ((TextView) nativeContentAdView.getBodyView()).setText(nativeContentAd.getBody());
        ((TextView) nativeContentAdView.getCallToActionView()).setText(nativeContentAd.getCallToAction());
        ((TextView) nativeContentAdView.getCallToActionView()).setBackgroundResource(R.drawable.gradbtn2);
        List images = nativeContentAd.getImages();
        if (images.size() > 0) {
            ((ImageView) nativeContentAdView.getImageView()).setImageDrawable(((com.google.android.gms.ads.formats.NativeAd.Image) images.get(0)).getDrawable());
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


    public void callFeedbackApi(String androidId, String device_name, String release, String email, String option1, String option2, String option3,
                                String option4, String option5, String comments/*, String comments, String user_id*/) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        try {
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

                        tinydb.putBoolean(Constants.RATEDIALOGSHOW_KEY, true);
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
                    tinydb.putInt(Constants.BMR, bmr);
                    tinydb.putInt(Constants.W25, w25);
                    tinydb.putInt(Constants.W5, w5);
                    tinydb.putInt(Constants.W1, w1);*/

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

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);
        if (requestCode == SIGN_IN_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(intent);
            handleSignInResult(result);

        }
        if (requestCode == 1000) {

            switch (responseCode) {
                case Activity.RESULT_OK:
                    // All required changes were successfully made
                    getLocation();
                    break;
                case Activity.RESULT_CANCELED:
                    // The user was asked to change settings, but chose not to
                    Toast.makeText(this, "Location Service not Enabled", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }


    private void signOut() {
        try {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            updateUI(false);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



   /* @SuppressLint("WrongConstant")
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
                    .addFormDataPart("country_id", tinydb.getString(Constants.COUNTRYID_KEY))
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
                            if (tinydb.getBoolean(Constants.PREMIUN_KEY)) {
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
                        sliderView.setVisibility(View.VISIBLE);
                        final SliderAdapter adapter = new SliderAdapter(MainActivity.this, newList);
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

 /*   @SuppressLint("WrongConstant")
    public void callLoginApi(String email, String fbid, String googleid, String fcmtoken) {
        try {
            this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient().create(BackpainAPIInterface.class);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("email", email)
                    .addFormDataPart("facebook_id", fbid)
                    .addFormDataPart("google_id", googleid)
                    .addFormDataPart("fcm_token", fcmtoken)
                    .build();
            this.apiInterface.getLogin(requestBody).enqueue(new Callback<LoginData>() {
                @Override
                public void onResponse(@NotNull Call<LoginData> call, @NotNull Response<LoginData> response) {
                    try {
                        LoginData loginData = (LoginData) response.body();
                        LoginData.Datalist dataist = loginData.dataist;
                        String status = loginData.status;

                        if (status.equals("false")) {
                            String first_name = GFirstname;
                            String last_name = GLastname;
                            String email = Gmail;
                            String mobile_number = "0";
                            String gender = tinydb.getString(Constants.GENDER_KEY);
                            String age = String.valueOf(tinydb.getInt(Constants.AGE_KEY));
                            String mHeight;
                            if (tinydb.getBoolean(Constants.ISCM_KEY)) {
                                mHeight = tinydb.getFloat(Constants.HEIGHT_KEY) + " cm";
                            } else {
                                mHeight = tinydb.getFloat(Constants.HEIGHT_KEY) + " ft";
                            }
                            String height = mHeight;
                            String mWeight;
                            if (tinydb.getBoolean(Constants.ISKG_KEY)) {
                                mWeight = tinydb.getInt(Constants.WEIGHT_KEY) + " kg";
                            } else {
                                mWeight = tinydb.getInt(Constants.WEIGHT_KEY) + " lb";
                            }
                            String weight = mWeight;
                            String user_type_id = String.valueOf(tinydb.getInt(Constants.MEALTYPE_KEY));
                            String paid_status = "0";
                            String country_id = tinydb.getString(Constants.COUNTRYID_KEY);
                            String state_id = tinydb.getString(Constants.STATEID_KEY);
                            String city_id = tinydb.getString(Constants.CITYID_KEY);
                            String fcm_token = tinydb.getString(Constants.FCMTOKEN_KEY);
                            String facebook = "";
                            String google = Guserid;
                            String user_image = Gprofilepicurl;

                        *//*    callRegisterApi(first_name, last_name, email, mobile_number, gender,
                                    age, height, weight, user_type_id, paid_status, country_id,
                                    state_id, city_id, fcm_token, facebook, google, user_image);*//*

                        } else {
                            tinydb.putString(Constants.USERID_KEY, dataist.getId());
                            tinydb.putString(Constants.USEREMAIL_KEY, dataist.getEmail());
                            tinydb.putString(Constants.USERFIRSTNAME_KEY, dataist.getFirst_name());
                            tinydb.putString(Constants.USERLASTNAME_KEY, dataist.getLast_name());
                            String userid = dataist.getId();
                            String age = dataist.getAge();
                            String gender = dataist.getGender();
                            String height = dataist.getHeight();
                            String weight = dataist.getWeight();
                            String usertype = dataist.getUser_type();
                            updatedata(userid, age, gender, height, weight, usertype);
                        }
                    } catch (Exception e) {

                    }
                }

                @Override
                public void onFailure(@NotNull Call<LoginData> call, @NotNull Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    private void LanguageChangeAPI(String language) {
        try {
            String token = tinydb.getString(Constants.Authtoken);
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
            try {
                JSONObject paramObject = new JSONObject();
                paramObject.put("language", language);


                Call<LanguageApi> userCall = apiInterface.getaddLanguage(paramObject.toString(), "Bearer " + token);
                userCall.enqueue(new Callback<LanguageApi>() {
                    @Override
                    public void onResponse(Call<LanguageApi> call, Response<LanguageApi> response) {
                        try {


                            LanguageApi badge = response.body();
                            boolean ok = badge.getStatus();
                            Log.e("Languageresponse", String.valueOf(ok));
                            Log.e("LanguageresponsegetData", String.valueOf(badge.getLanguage()));

                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onFailure(Call<LanguageApi> call, Throwable t) {
                        Log.e("response1", String.valueOf(t));
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {

        }
    }

    public void getBanner() {
        String token = tinydb.getString(Constants.Authtoken);
        String BASEURL = tinydb.getString(Constants.NewBaseUrl);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(loggingInterceptor);

        OkHttpClient client = httpClient.build();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        try {
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BASEURL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
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

                            Log.e("response", String.valueOf(response));
                            ArrayList<Banner1.Doc> newList = new ArrayList<>();
                            ArrayList<Banner1> banners = (ArrayList<Banner1>) response.body();

                            for (int i = 0; i < banners.size(); i++) {

                             /*   Integer bmrvalue = banners.get(i).getBmr();
                                Log.e("bmevalue", String.valueOf(bmrvalue));
                                tinydb.putInt(Constants.BMR, bmrvalue);*/


                                ArrayList<Banner1.Doc> docs1 = (ArrayList<Banner1.Doc>) banners.get(i).getDocs();
                                for (int j = 0; j < docs1.size(); j++) {

                                    if (tinydb.getBoolean(Constants.PREMIUN_KEY)) {
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
                                    final SliderNewAdapter adapter = new SliderNewAdapter(MainActivity.this, newList);
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
                            Log.e("responsecatch", String.valueOf(e));
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Banner1>> call, Throwable t) {
                        Log.e("responsevbanner", String.valueOf(t));
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {

        }
    }


    public void DialogLanguage() {
        try {


            Dialog timersdialog = new Dialog(this);
            Objects.requireNonNull(timersdialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            timersdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            timersdialog.setContentView(R.layout.dialog_language);
            rhindi = (RadioButton) timersdialog.findViewById(R.id.rhindi);
            renglish = (RadioButton) timersdialog.findViewById(R.id.renglish);
            rfrench = (RadioButton) timersdialog.findViewById(R.id.rfrench);
            rchinish = (RadioButton) timersdialog.findViewById(R.id.rchinish);
            rpotugues = (RadioButton) timersdialog.findViewById(R.id.rpotugues);
            rrussian = (RadioButton) timersdialog.findViewById(R.id.rrussian);
            aarbic = (RadioButton) timersdialog.findViewById(R.id.aarbic);
            spanish = (RadioButton) timersdialog.findViewById(R.id.spanish);

            ritalian = (RadioButton) timersdialog.findViewById(R.id.ritalian);
            rturkish = (RadioButton) timersdialog.findViewById(R.id.rturkish);
            rkorean = (RadioButton) timersdialog.findViewById(R.id.rkorean);
            rdutch = (RadioButton) timersdialog.findViewById(R.id.rdutch);
            rswedish = (RadioButton) timersdialog.findViewById(R.id.rswedish);
            rdanish = (RadioButton) timersdialog.findViewById(R.id.rdanish);
            rbengali = (RadioButton) timersdialog.findViewById(R.id.rbengali);
            rtamil = (RadioButton) timersdialog.findViewById(R.id.rtamil);
            rtelugu = (RadioButton) timersdialog.findViewById(R.id.rtelugu);
            rpunjabi = (RadioButton) timersdialog.findViewById(R.id.rpunjabi);
            rpolish = (RadioButton) timersdialog.findViewById(R.id.rpolish);
            rthai = (RadioButton) timersdialog.findViewById(R.id.rthai);
            persian = (RadioButton) timersdialog.findViewById(R.id.persian);

            Button submit = (Button) timersdialog.findViewById(R.id.submitButton);

            String languages = tinydb.getString(Constants.Language);
            Log.e("languages1", languages);
            if (languages.equals(Constants.English)) {
                renglish.setChecked(true);
            } else if (languages.equals(Constants.Hindi)) {
                rhindi.setChecked(true);
            } else if (languages.equals(Constants.French)) {
                rfrench.setChecked(true);

            } else if (languages.equals(Constants.Chinish)) {
                rchinish.setChecked(true);

            } else if (languages.equals(Constants.THAI)) {
                rthai.setChecked(true);

            } else if (languages.equals(Constants.Portugues)) {
                rpotugues.setChecked(true);

            } else if (languages.equals(Constants.Russian)) {
                rrussian.setChecked(true);

            } else if (languages.equals(Constants.Aarbic)) {
                aarbic.setChecked(true);

            } else if (languages.equals(Constants.Spanish)) {
                spanish.setChecked(true);
            } else if (languages.equals(Constants.ITALIAN)) {
                ritalian.setChecked(true);
            } else if (languages.equals(Constants.TURKISH)) {
                rturkish.setChecked(true);
            } else if (languages.equals(Constants.DUTCH)) {
                rdutch.setChecked(true);
            } else if (languages.equals(Constants.KOREAN)) {
                rkorean.setChecked(true);
            } else if (languages.equals(Constants.SWEDISH)) {
                rswedish.setChecked(true);
            } else if (languages.equals(Constants.DANISH)) {
                rdanish.setChecked(true);
            } else if (languages.equals(Constants.BENGALI)) {
                rbengali.setChecked(true);
            } else if (languages.equals(Constants.TAMIL)) {
                rtamil.setChecked(true);
            } else if (languages.equals(Constants.TELUGU)) {
                rtelugu.setChecked(true);
            } else if (languages.equals(Constants.PUNJABI)) {
                rpunjabi.setChecked(true);
            } else if (languages.equals(Constants.POLISH)) {
                rpolish.setChecked(true);
            }else if (languages.equals(Constants.PERSIAN)){
                persian.setChecked(true);
            }


            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (renglish.isChecked()) {
                        selectedSuperStar = Language.ENGLISH;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        tinydb.putBoolean(Constants.AppLanguage, true);
                        Constants.languagechange(MainActivity.this, selectedSuperStar);
                        LanguageChangeAPI("english");
                    } else if (rhindi.isChecked()) {
                        selectedSuperStar = Language.HINDI;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        tinydb.putBoolean(Constants.AppLanguage, true);
                        Constants.languagechange(MainActivity.this, selectedSuperStar);
                        LanguageChangeAPI("hindi");
                    } else if (rfrench.isChecked()) {
                        selectedSuperStar = Language.FRENCH;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        tinydb.putBoolean(Constants.AppLanguage, true);
                        Constants.languagechange(MainActivity.this, selectedSuperStar);
                        LanguageChangeAPI("french");
                    } else if (rchinish.isChecked()) {
                        selectedSuperStar = Language.CHINESE;
                        tinydb.putBoolean(Constants.AppLanguage, true);
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        Constants.languagechange(MainActivity.this, selectedSuperStar);
                        LanguageChangeAPI("chinese");
                    } else if (rpotugues.isChecked()) {
                        selectedSuperStar = Language.PORTUGUESE;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        tinydb.putBoolean(Constants.AppLanguage, true);
                        Constants.languagechange(MainActivity.this, selectedSuperStar);
                        LanguageChangeAPI("portuguese");
                    } else if (rrussian.isChecked()) {
                        selectedSuperStar = Language.RUSSIAN;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        tinydb.putBoolean(Constants.AppLanguage, true);
                        Constants.languagechange(MainActivity.this, selectedSuperStar);
                        LanguageChangeAPI("russian");
                    } else if (aarbic.isChecked()) {
                        selectedSuperStar = Language.ARABIC;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        tinydb.putBoolean(Constants.AppLanguage, true);
                        Constants.languagechange(MainActivity.this, selectedSuperStar);
                        LanguageChangeAPI("arabic");
                    } else if (spanish.isChecked()) {
                        selectedSuperStar = Language.SPANISH;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        tinydb.putBoolean(Constants.AppLanguage, true);
                        Constants.languagechange(MainActivity.this, selectedSuperStar);
                        LanguageChangeAPI("spanish");
                    } else if (ritalian.isChecked()) {
                        selectedSuperStar = Language.ITALIAN;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        tinydb.putBoolean(Constants.AppLanguage, true);
                        Constants.languagechange(MainActivity.this, selectedSuperStar);
                        LanguageChangeAPI("italian");
                    } else if (rturkish.isChecked()) {
                        selectedSuperStar = Language.TURKISH;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        tinydb.putBoolean(Constants.AppLanguage, true);
                        Constants.languagechange(MainActivity.this, selectedSuperStar);
                        LanguageChangeAPI("turkish");
                    } else if (rdanish.isChecked()) {
                        selectedSuperStar = Language.DANISH;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        tinydb.putBoolean(Constants.AppLanguage, true);
                        Constants.languagechange(MainActivity.this, selectedSuperStar);
                        LanguageChangeAPI("danish");
                    } else if (rbengali.isChecked()) {
                        selectedSuperStar = Language.BENGALI;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        tinydb.putBoolean(Constants.AppLanguage, true);
                        Constants.languagechange(MainActivity.this, selectedSuperStar);
                        LanguageChangeAPI("bengali");
                    } else if (rtamil.isChecked()) {
                        selectedSuperStar = Language.TAMIL;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        tinydb.putBoolean(Constants.AppLanguage, true);
                        Constants.languagechange(MainActivity.this, selectedSuperStar);
                        LanguageChangeAPI("tamil");
                    } else if (rtelugu.isChecked()) {
                        selectedSuperStar = Language.TELUGU;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        tinydb.putBoolean(Constants.AppLanguage, true);
                        Constants.languagechange(MainActivity.this, selectedSuperStar);
                        LanguageChangeAPI("telugu");
                    } else if (rpunjabi.isChecked()) {
                        selectedSuperStar = Language.PUNJABI;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        tinydb.putBoolean(Constants.AppLanguage, true);
                        Constants.languagechange(MainActivity.this, selectedSuperStar);
                        LanguageChangeAPI("punjabi");
                    } else if (rpolish.isChecked()) {
                        selectedSuperStar = Language.POLISH;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        tinydb.putBoolean(Constants.AppLanguage, true);
                        Constants.languagechange(MainActivity.this, selectedSuperStar);
                        LanguageChangeAPI("polish");
                    } else if (rthai.isChecked()) {
                        selectedSuperStar = Language.THAI;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        tinydb.putBoolean(Constants.AppLanguage, true);
                        Constants.languagechange(MainActivity.this, selectedSuperStar);
                        LanguageChangeAPI("thai");
                    } else if (rkorean.isChecked()) {
                        selectedSuperStar = Language.KOREAN;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        tinydb.putBoolean(Constants.AppLanguage, true);
                        Constants.languagechange(MainActivity.this, selectedSuperStar);
                        LanguageChangeAPI("korean");
                    } else if (rdutch.isChecked()) {
                        selectedSuperStar = Language.DUTCH;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        tinydb.putBoolean(Constants.AppLanguage, true);
                        Constants.languagechange(MainActivity.this, selectedSuperStar);
                        LanguageChangeAPI("dutch");
                    } else if (rswedish.isChecked()) {
                        selectedSuperStar = Language.SWEDISH;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        tinydb.putBoolean(Constants.AppLanguage, true);
                        Constants.languagechange(MainActivity.this, selectedSuperStar);
                        LanguageChangeAPI("swedish");
                    }else   if (persian.isChecked()) {
                        selectedSuperStar = Language.PERSIAN;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        tinydb.putBoolean(Constants.AppLanguage, true);
                        Constants.languagechange(MainActivity.this, selectedSuperStar);
                        LanguageChangeAPI("persian");
                    }


                    String laun = tinydb.getString(Constants.Language);
                    Log.e("languages", laun);
                    timersdialog.dismiss();
                    Intent intent = new Intent(MainActivity.this, Appstart_Activity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                            Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                    //Toast.makeText(getApplicationContext(), selectedSuperStar, Toast.LENGTH_LONG).show(); // print the value of selected super star
                }
            });
            timersdialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                }
            });
            //timersdialog.getWindow().setWindowAnimations(R.style.DialogTheme);
            timersdialog.show();
        } catch (Exception e) {

        }
    }


    /*  private void LanguageChangeAPI(String language) {
          String token = tinydb.getString(Constants.Authtoken);
          OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
          HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
          loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
          httpClient.addInterceptor(loggingInterceptor);
          OkHttpClient client = httpClient.build();
          Gson gson = new GsonBuilder()
                  .setLenient()
                  .create();
          Retrofit retrofit = new Retrofit.Builder().baseUrl(ApiInterface.URL_BASE)
                  .addConverterFactory(ScalarsConverterFactory.create())
                  .addConverterFactory(GsonConverterFactory.create(gson))
                  .client(client)
                  .build();

          ApiInterface apiInterface = retrofit.create(ApiInterface.class);
          try {
              JSONObject paramObject = new JSONObject();
              paramObject.put("language", language);


              Call<LanguageApi> userCall = apiInterface.getaddLanguage(paramObject.toString(), "Bearer " + token);
              userCall.enqueue(new Callback<LanguageApi>() {
                  @Override
                  public void onResponse(Call<LanguageApi> call, Response<LanguageApi> response) {
                      try {



                          LanguageApi badge = response.body();
                          boolean ok = badge.getStatus();
                          Log.e("Languageresponse", String.valueOf(ok));
                          Log.e("LanguageresponsegetData", String.valueOf(badge.getData()));

                      } catch (Exception e) {

                      }
                  }

                  @Override
                  public void onFailure(Call<LanguageApi> call, Throwable t) {
                      Log.e("response1", String.valueOf(t));
                  }
              });
          } catch (JSONException e) {
              e.printStackTrace();
          }
      }*/
    @SuppressLint("WrongConstant")
    public void callCategoryApi() {
        try {
            this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getAppstoreClient().create(BackpainAPIInterface.class);
            this.apiInterface.getAppCategory().enqueue(new Callback<CategoryData>() {
                @Override
                public void onResponse(@NotNull Call<CategoryData> call, @NotNull Response<CategoryData> response) {
                    try {
                        CategoryData dietCateData = (CategoryData) response.body();
                        assert dietCateData != null;
                        ArrayList<CategoryData.Datalist> dataist = dietCateData.dataist;
                        CategoryData.Datalist.setCategoryModals(dataist);
                        Testcatdata testcatdata = new Testcatdata();
                        ArrayList<Testcatdata> testcatdataArrayList = new ArrayList<>();
                        for (int i = 0; i < dataist.size(); i++) {
                            CategoryData.Datalist catedata = dataist.get(i);
                            testcatdata.setCatid(catedata.getId());
                            testcatdata.setCatname(catedata.getName());
                            catnamearray.add(catedata.getName());
                            testcatdataArrayList.add(testcatdata);
                            int last = dataist.size() - 1;
                            if (i == last) {
                                callSubcatApi(catedata.getId(), catedata.getName(), true);
                            } else {
                                callSubcatApi(catedata.getId(), catedata.getName(), false);
                            }
                        }
                    } catch (Exception e) {

                    }
                }

                @Override
                public void onFailure(@NotNull Call<CategoryData> call, @NotNull Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("WrongConstant")
    public void callSubcatApi(String catid, String catname, boolean islast) {
        try {
            String appid = tinydb.getString(Constants.Appstore_Appid);
            this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getAppstoreClient().create(BackpainAPIInterface.class);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("cat_id", catid)
                    .addFormDataPart("app_id", appid)
                    .build();
            this.apiInterface.getStoredata(requestBody).enqueue(new Callback<TestDatamodel>() {
                @Override
                public void onResponse(@NotNull Call<TestDatamodel> call, @NotNull Response<TestDatamodel> response) {
                    try {
                        TestDatamodel subcatData = (TestDatamodel) response.body();
                        ArrayList<TestSubcatdata> dataist = subcatData.dataist;
                        for (int i = 0; i < dataist.size(); i++) {
                            try {
                                TestSubcatdata testSubcatdata = dataist.get(i);
                                TestDatalist testDatalist = new TestDatalist();
                                testDatalist.setCatename(catname);
                                testDatalist.setId(testSubcatdata.subcatid);
                                testDatalist.setSubcatname(testSubcatdata.subcatname);
                                Gson gson = new Gson();
                                String json = gson.toJson((Object) testSubcatdata.subcatdata);
                                testDatalist.setDatajson(json);
                                Constants.storedatalist.add(testDatalist);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e) {
                    }
                }

                @Override
                public void onFailure(@NotNull Call<TestDatamodel> call, @NotNull Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatedata(String userid, String mAge, String gen, String height, String weight, String mealtype) {
        try {
            tinydb.putString(Constants.USERID_KEY, userid);
            tinydb.putInt(Constants.AGE_KEY, Integer.parseInt(mAge));
            tinydb.putString(Constants.GENDER_KEY, gen);
            if (height.contains("cm")) {
                tinydb.putBoolean(Constants.ISCM_KEY, true);
                height = height.replace(" cm", "");
                tinydb.putFloat(Constants.HEIGHT_KEY, Float.parseFloat(height));
                String finalHeight = height;
            } else {
                String doubleAsString = height;
                int indexOfDecimal = doubleAsString.indexOf(".");
                int mFtval = Integer.parseInt(doubleAsString.substring(0, indexOfDecimal));
                String mTrim = doubleAsString.substring(indexOfDecimal).replace(".", "");
                mTrim = mTrim.replace(" ft", "");
                int mInchval = Integer.parseInt(mTrim);
                tinydb.putBoolean(Constants.ISCM_KEY, false);
                String temp = mFtval + "." + mInchval;
                tinydb.putFloat(Constants.HEIGHT_KEY, Float.parseFloat(temp));
            }
            if (weight.contains("kg")) {
                tinydb.putBoolean(Constants.ISKG_KEY, true);
                weight = weight.replace(" kg", "");
                tinydb.putInt(Constants.WEIGHT_KEY, Integer.parseInt(weight));
                String finalWeight = weight;
            } else {
                tinydb.putBoolean(Constants.ISKG_KEY, false);
                weight = weight.replace(" lb", "");
                tinydb.putInt(Constants.WEIGHT_KEY, Integer.parseInt(weight));
                String finalWeight1 = weight;
            }
            if (mealtype.equals("Veg")) {
                tinydb.putInt(Constants.MEALTYPE_KEY, 1);
                tinydb.putString(Constants.USERTYPEKEY, "veg");
            } else if (mealtype.equals("Non-Veg")) {
                tinydb.putInt(Constants.MEALTYPE_KEY, 2);
                tinydb.putString(Constants.USERTYPEKEY, "nonveg");
            } else if (mealtype.equals("Vegan")) {
                tinydb.putInt(Constants.MEALTYPE_KEY, 3);
                tinydb.putString(Constants.USERTYPEKEY, "vegan");
            }
        } catch (Exception e) {
            Log.e("ploginerror", "" + e.getMessage().toString());
            e.printStackTrace();
        }
    }


    private void handleSignInResult(GoogleSignInResult result) {
        Log.e(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            account = result.getSignInAccount();
            Gusername = account.getDisplayName();
            Gmail = account.getEmail();
            Guserid = account.getId();
            Gprofilepicurl = String.valueOf(account.getPhotoUrl());
            GFirstname = account.getGivenName();
            GLastname = account.getFamilyName();
            tinydb.putString(Constants.USEREMAIL_KEY, Gmail);
            boolean mIslogedin = tinydb.getBoolean(Constants.ISLOGIN_KEY);
           /* if (!mIslogedin) {
                if (Gmail != null) {
                    String token = tinydb.getString(Constants.FCMTOKEN_KEY);
                    callLoginApi(Gmail, "", Guserid, token);

                }
            }*/
            String first_name = GFirstname;
            String last_name = GLastname;
            String email = Gmail;
            String mobile_number = "0";
            String gender = tinydb.getString(Constants.GENDER_KEY);
            String age = String.valueOf(tinydb.getInt(Constants.AGE_KEY));
            String mHeight;
            String mHeightP;
            if (tinydb.getBoolean(Constants.ISCM_KEY)) {
                mHeight = String.valueOf(tinydb.getFloat(Constants.HEIGHT_KEY)) /*+ " cm"*/;
                mHeightP = "cm";
            } else {
                mHeight = String.valueOf(tinydb.getFloat(Constants.HEIGHT_KEY)) /*+ " ft"*/;
                mHeightP = "ft";

            }
            String height = mHeight;
            String mWeight;
            String mWeightP;
            if (tinydb.getBoolean(Constants.ISKG_KEY)) {
                mWeight = String.valueOf(tinydb.getInt(Constants.WEIGHT_KEY)) /*+ " kg"*/;
                mWeightP = "kg";
            } else {
                mWeight = String.valueOf(tinydb.getInt(Constants.WEIGHT_KEY))/* + " lb"*/;
                mWeightP = "lb";
            }
            String weight = mWeight;
//            String user_type_id = String.valueOf(tinydb.getInt(Constants.MEALTYPE_KEY));
            String paid_status = "0";
            String country_id = tinydb.getString(Constants.COUNTRYID_KEY);
            //       Log.e("coutry",country_id);
            String langauge = Locale.getDefault().getDisplayLanguage();
            Log.e("getDisplayLanguage", langauge);

            if (langauge.equals("हिन्दी")) {
                // mTinydb.putString(Constants.Language, "hi");
                // Constants.languagechange(getActivity(), "hi");
                langauge = "hindi";
            } else {
                // mTinydb.putString(Constants.Language, "en");
                // Constants.languagechange(getActivity(), "en");
                langauge = "english";
            }
            String state_id = tinydb.getString(Constants.STATEID_KEY);
            //     Log.e("state_id1",state_id);
            String city_id = tinydb.getString(Constants.CITYID_KEY);
            //     Log.e("city_id1",city_id);
            String fcm_token = tinydb.getString(Constants.FCMTOKEN_KEY);
            String facebook = "";
            String google = Guserid;
            String user_image = Gprofilepicurl;
            if (!tinydb.getBoolean(Constants.ISLOGIN_KEY)) {
                callRegisterApi(first_name, last_name, email, gender,
                        age, height, weight, country_id,
                        state_id, city_id, fcm_token, google, langauge, mHeightP, mWeightP);
            }
            updateUI(true);
            updatedata(Gusername, Gprofilepicurl);

        } else {
            updateUI(false);
        }

    }


    @SuppressLint("WrongConstant")
    public void callRegisterApi(String first_name, String last_name,
                                String email,
                                String gender, String age, String height,
                                String weight, String country_id, String state_id, String city_id,
                                String fcm_token, String google, String language, String heightp, String weightp) {


        try {
            String getusertype = tinydb.getString(Constants.USERTYPEKEY);
            JSONObject paramObject = new JSONObject();
             String androidId = 22+Settings.Secure.getString(getContentResolver(),
                        Settings.Secure.ANDROID_ID)+22;
            paramObject.put("first_name", first_name);
            paramObject.put("last_name", last_name);
            paramObject.put("email", email);
            paramObject.put("gender", gender);
            paramObject.put("age", age);
            paramObject.put("height", height);
            paramObject.put("weight", weight);
            paramObject.put("country", country_id);
            paramObject.put("state", state_id);
            paramObject.put("city", city_id);
            paramObject.put("fcm_token", fcm_token);
            paramObject.put("google", google);
            paramObject.put("language", language);
            paramObject.put("device_id", androidId);
            paramObject.put("heightP", heightp);
            paramObject.put("weightP", weightp);
            paramObject.put("app_number", String.valueOf(22));
            paramObject.put("user_type", getusertype);
            paramObject.put("skip", "false");
            this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);

            Call<Register_Api> userCall = apiInterface.getUser(paramObject.toString());
            userCall.enqueue(new Callback<Register_Api>() {
                @Override
                public void onResponse(Call<Register_Api> call, Response<Register_Api> response) {
                    try {


                        Register_Api dietCateData = (Register_Api) response.body();

                        Register_Api.Docs docs = dietCateData.getDocs();
                        Log.e("token", String.valueOf(dietCateData.token));
                        tinydb.putString(Constants.Authtoken, dietCateData.token);

                        String userid = docs.getId();
                        tinydb.putString(Constants.USERID_KEYs, userid);


                    } catch (Exception e) {
                    }
                }

                @Override
                public void onFailure(Call<Register_Api> call, Throwable t) {
                    //Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void logout() {
        LoginManager.getInstance().logOut();
        updateUI(false);
    }

/*
    @SuppressLint("WrongConstant")
    public void callBlogApi() {
        try {
            this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient().create(BackpainAPIInterface.class);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("blog_id", "")
                    .build();
            this.apiInterface.getBlogs(requestBody).enqueue(new Callback<BlogData>() {
                @Override
                public void onResponse(@NotNull Call<BlogData> call, @NotNull Response<BlogData> response) {
                    try {
                        BlogData blogData = (BlogData) response.body();
                        ArrayList<BlogData.Datalist> datalists = blogData.datalists;
                        blogdatalist = datalists;
                        int blogcounts = tinydb.getInt(Constants.PRESENTBLOGCOUNTS);
                        if (blogcounts == 0 || blogcounts > datalists.size()) {
                            tinydb.putInt(Constants.PRESENTBLOGCOUNTS, datalists.size());
                        }
                        if (blogcounts != 0 && tinydb.getInt(Constants.PRESENTBLOGCOUNTS) != 0) {
                            int oldblogs = tinydb.getInt(Constants.PRESENTBLOGCOUNTS);
                            if (datalists.size() > oldblogs) {
                                int Newblogs = datalists.size() - oldblogs;
                                tinydb.putInt(Constants.NEWBLOGSCOUNTS, Newblogs);
                                mBtAds.setBackgroundResource(R.drawable.adsanimationtwo);
                                AnimationDrawable anim = (AnimationDrawable) mBtAds.getBackground();
                                anim.start();
                                mBlogtxtcount.setVisibility(View.VISIBLE);
                                mBlogtxtcount.setText(String.valueOf(Newblogs));
                            } else {
                                tinydb.putInt(Constants.NEWBLOGSCOUNTS, 0);
                                mBtAds.setBackgroundResource(R.drawable.ic_off);
                            }
                        }

                    }catch (Exception e){

                    }
                }

                @Override
                public void onFailure(@NotNull Call<BlogData> call, @NotNull Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    @SuppressLint("WrongConstant")
    public void callBlogApi() {
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
            token = tinydb.getString(Constants.Authtoken);
            apiInterface.getCountBlog("Bearer " + token).enqueue(new Callback<GetcountBulb>() {
                @Override
                public void onResponse(@NotNull Call<GetcountBulb> call, @NotNull Response<GetcountBulb> response) {
                    try {
                        Log.e("Ress", response.toString());
                        GetcountBulb blogData = (GetcountBulb) response.body();
                        assert blogData != null;
                        int count = blogData.getLength();
                        Log.e("count", String.valueOf(count));
                        int oldnum = tinydb.getInt(Constants.PRESENTBLOGCOUNTSBLOG);
                        Log.e("countnum", String.valueOf(oldnum));
                        if (oldnum == 0) {
                            tinydb.putInt(Constants.PRESENTBLOGCOUNTSBLOG, count);
                            tinydb.putInt(Constants.NEWBLOGSCOUNTS, 1);
                        }
                        if (oldnum < count) {
                            tinydb.putInt(Constants.NEWBLOGSCOUNTS, 1);
                            tinydb.putInt(Constants.PRESENTBLOGCOUNTSBLOG, count);
                            mBtAds.setBackgroundResource(R.drawable.adsanimationtwo);
                            AnimationDrawable anim = (AnimationDrawable) mBtAds.getBackground();
                            anim.start();
                            mBlogtxtcount.setVisibility(View.VISIBLE);
                            mBlogtxtcount.setText(String.valueOf(1));
                        } else {
                            mBlogtxtcount.setVisibility(View.GONE);
                            tinydb.putInt(Constants.NEWBLOGSCOUNTS, 0);
                            mBtAds.setBackgroundResource(R.drawable.ic_off);
                        }
                       /* ArrayList<BlogData.Datalist> datalists = blogData.datalists;
                        blogdatalist = datalists;
                        int blogcounts = tinydb.getInt(Constants.PRESENTBLOGCOUNTS);
                        if (blogcounts == 0 || blogcounts < datalists.size()) {
//                        blogcounts = blogcounts + 1;
                            tinydb.putInt(Constants.PRESENTBLOGCOUNTS, datalists.size());
                        }
                        if (tinydb.getInt(Constants.PRESENTBLOGCOUNTS) != 0) {
                            int oldblogs = tinydb.getInt(Constants.PRESENTBLOGCOUNTS);
                            int num = tinydb.getInt(TOTALBLOG_KEY);
                            if (oldblogs > num) {
                                if (num == 0) {
                                    tinydb.putInt(TOTALBLOG_KEY, 1);
                                }
//                            int Newblogs = datalists.size() - oldblogs;
                                tinydb.putInt(Constants.NEWBLOGSCOUNTS, 1);
                                mBtAds.setBackgroundResource(R.drawable.adsanimationtwo);
                                AnimationDrawable anim = (AnimationDrawable) mBtAds.getBackground();
                                anim.start();
                                mBlogtxtcount.setVisibility(View.VISIBLE);
                                mBlogtxtcount.setText(String.valueOf(1));
                            } else {
                                tinydb.putInt(Constants.NEWBLOGSCOUNTS, 0);
                                mBtAds.setBackgroundResource(R.drawable.ic_off);
                            }
                        }
                   */
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(@NotNull Call<GetcountBulb> call, @NotNull Throwable t) {
                    Log.e("Ress1", t.toString());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void updateUI(boolean signedIn) {
        if (signedIn) {
            tinydb.putBoolean(Constants.ISLOGIN_KEY, true);
            mloginlay.setVisibility(View.GONE);
            mSucceslayout.setVisibility(View.VISIBLE);

        } else {
            tinydb.remove(Constants.USERID_KEY);
            tinydb.remove(Constants.USEREMAIL_KEY);
            tinydb.remove(Constants.USERFIRSTNAME_KEY);
            tinydb.remove(Constants.USERLASTNAME_KEY);
            tinydb.putBoolean(Constants.ISLOGIN_KEY, false);
            mSucceslayout.setVisibility(View.GONE);
            mloginlay.setVisibility(View.VISIBLE);
            mProfilepic.setState(AvatarImageView.SHOW_IMAGE);
            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(FitnessApplication.getInstance()).load(R.drawable.img_profile)
                    .apply(requestOptions).into(mProfilepic);

        }
    }

    public void updatedata(String mProfname, String imgurl) {
        mProfilename.setText(mProfname);
        if (imgurl != null && !imgurl.equals("null")) {
            mProfilepic.setState(AvatarImageView.SHOW_IMAGE);
            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(FitnessApplication.getInstance()).load(imgurl)
                    .apply(requestOptions).into(mProfilepic);
        } else {
            try {
                mProfilepic.setState(AvatarImageView.SHOW_INITIAL);
                mProfilepic.setAvatarBackgroundColor(getResources().getColor(R.color.colorAccent));
                String s = mProfname.substring(0, 1);
                mProfilepic.setText(s);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.e("connectedddd", "content");
        settingRequest();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Connection Failed!", Toast.LENGTH_SHORT).show();
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, 90000);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.i("Current Location", "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    /*Method to get the enable location settings dialog*/
    public void settingRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);    // 10 seconds, in milliseconds
        mLocationRequest.setFastestInterval(1000);   // 1 second, in milliseconds
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient,
                        builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {

            @Override
            public void onResult(@NonNull LocationSettingsResult result) {
                final Status status = result.getStatus();
                final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can
                        // initialize location requests here.
                        getLocation();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied, but this can be fixed
                        // by showing the user a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(MainActivity.this, 1000);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way
                        // to fix the settings so we won't show the dialog.
                        break;
                }
            }

        });
    }

 /*   @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
        switch (requestCode) {
            case 1000:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        // All required changes were successfully made
                        getLocation();
                        break;
                    case Activity.RESULT_CANCELED:
                        // The user was asked to change settings, but chose not to
                        Toast.makeText(this, "Location Service not Enabled", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                break;
        }
    }*/

    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        } else {
            /*Getting the location after aquiring location service*/
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);

            if (mLastLocation != null) {
                   /* _progressBar.setVisibility(View.INVISIBLE);
                    _latitude.setText("Latitude: " + String.valueOf(mLastLocation.getLatitude()));
                    _longitude.setText("Longitude: " + String.valueOf(mLastLocation.getLongitude()));*/
                try {
                    getCountryName(this, mLastLocation.getLatitude(), mLastLocation.getLongitude());
                }catch (Exception e){

                }

            } else {
                /*if there is no last known location. Which means the device has no data for the loction currently.
                 * So we will get the current location.
                 * For this we'll implement Location Listener and override onLocationChanged*/
                Log.i("Current Location", "No data for location found");

                if (!mGoogleApiClient.isConnected())
                    mGoogleApiClient.connect();

                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, MainActivity.this);

            }
        }
    }

    /*When Location changes, this method get called. */
    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
//            _progressBar.setVisibility(View.INVISIBLE);
//            _latitude.setText("Latitude: " + String.valueOf(mLastLocation.getLatitude()));
//            _longitude.setText("Longitude: " + String.valueOf(mLastLocation.getLongitude()));
        getCountryName(this, mLastLocation.getLatitude(), mLastLocation.getLongitude());

    }

    public String getCountryName(Context context, double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            Address result;

            if (addresses != null && !addresses.isEmpty()) {
                //   Log.e("getCountryName", addresses.get(0).getCountryName());
                //   Log.e("getstateName", addresses.get(0).getAdminArea());
                //   Log.e("getcityName", addresses.get(0).getLocality());

                String country_id = addresses.get(0).getCountryName();
                String state_id = addresses.get(0).getAdminArea();
                String city_id = addresses.get(0).getLocality();

                tinydb.putString(Constants.COUNTRYID_KEY, country_id);
                tinydb.putString(Constants.STATEID_KEY, state_id);
                tinydb.putString(Constants.CITYID_KEY, city_id);
                String countrycode = addresses.get(0).getCountryCode();
                getCurrencyCode(countrycode);

                return addresses.get(0).getCountryName();
            }
            return null;
        } catch (IOException ignored) {
            //do something
        }
        return null;
    }



    public class VersionChecker extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {

            String newVersion = null;

            try {
                Document document = Jsoup.connect("https://play.google.com/store/apps/details?id=" + getPackageName() + "&hl=en")
                        .timeout(30000)
                        .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                        .referrer("http://www.google.com")
                        .get();
                if (document != null) {
                    Elements element = document.getElementsContainingOwnText("Current Version");
                    for (Element ele : element) {
                        if (ele.siblingElements() != null) {
                            Elements sibElemets = ele.siblingElements();
                            for (Element sibElemet : sibElemets) {
                                newVersion = sibElemet.text();
                            }
                        }
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
            return newVersion;
        }
    }

    public static String getCurrencyCode(String countryCode) {
        String s = Currency.getInstance(new Locale("", countryCode)).getCurrencyCode();
        tinydb.putString(Constants.COUNTRY_CODEID, s);

       // Log.e("ssssssssssss", s);
        return Currency.getInstance(new Locale("", countryCode)).getCurrencyCode();
    }

    @Override
    protected void onStop() {
        isclicked = false;
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        PurchaseCheck();

        VersionChecker versionChecker = new VersionChecker();
        try {
            String latestVersion = versionChecker.execute().get();
            String versionName = BuildConfig.VERSION_NAME.replace("-DEBUG", "");

            APIAppUpdate(latestVersion, versionName);
            if (latestVersion != null && !latestVersion.isEmpty()) {
                if (!latestVersion.equals(versionName)) {
                    //    showDialogToSendToPlayStore();
                    //   Log.e("update", "Current version " + Float.valueOf(versionName) + ", Playstore version " + Float.valueOf(latestVersion));

                } else {
                    //     Log.e("update", "Current version " + Float.valueOf(versionName) + ", Playstore version " + Float.valueOf(latestVersion));

                    //   continueWithTheLogin();
                }
            }
//            Log.e("update", "Current version " + Float.valueOf(versionName) + ", Playstore version " + Float.valueOf(latestVersion));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //  updatedayoffweek();
        // updategoalday();
        //       UpdateAplanData();
        try {

            if (tinydb.getBoolean(Constants.RATEDIALOGSHOW_KEY)) {
                mLayrate.setVisibility(View.GONE);
            } else {
                mLayrate.setVisibility(View.VISIBLE);
            }
            int newblog = tinydb.getInt(Constants.NEWBLOGSCOUNTS);
            if (newblog == 1) {
                mBtAds.setBackgroundResource(R.drawable.adsanimationtwo);
                AnimationDrawable anim = (AnimationDrawable) mBtAds.getBackground();
                anim.start();
                mBlogtxtcount.setVisibility(View.VISIBLE);
                mBlogtxtcount.setText(String.valueOf(1));
            } else {
                mBlogtxtcount.setVisibility(View.GONE);
                tinydb.putInt(Constants.NEWBLOGSCOUNTS, 0);
                mBtAds.setBackgroundResource(R.drawable.ic_off);
            }


            skip_data = tinydb.getBoolean(Constants.SKIP_NOINTERNET);
            if (skip_data) {


                ConnectivityManager mgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = mgr.getActiveNetworkInfo();
                if (netInfo != null) {
                    if (netInfo.isConnected()) {

                        String mobile_number = "0";
                        String gender = tinydb.getString(Constants.GENDER_KEY);
                        String age = String.valueOf(tinydb.getInt(Constants.AGE_KEY));
                        String mHeight;
                        String mHeightP;
                        if (tinydb.getBoolean(Constants.ISCM_KEY)) {
                            mHeight = String.valueOf(tinydb.getFloat(Constants.HEIGHT_KEY)) /*+ " cm"*/;
                            mHeightP = "cm";
                        } else {
                            mHeight = String.valueOf(tinydb.getFloat(Constants.HEIGHT_KEY)) /*+ " ft"*/;
                            mHeightP = "ft";

                        }
                        String height = mHeight;
                        String mWeight;
                        String mWeightP;
                        if (tinydb.getBoolean(Constants.ISKG_KEY)) {
                            mWeight = String.valueOf(tinydb.getInt(Constants.WEIGHT_KEY)) /*+ " kg"*/;
                            mWeightP = "kg";
                        } else {
                            mWeight = String.valueOf(tinydb.getInt(Constants.WEIGHT_KEY))/* + " lb"*/;
                            mWeightP = "lb";
                        }
                        String weight = mWeight;
                        //  String user_type_id = String.valueOf(tinydb.getInt(Constants.MEALTYPE_KEY));
                        String paid_status = "0";
                        String country_id = tinydb.getString(Constants.COUNTRYID_KEY);
                        //       Log.e("coutry",country_id);
                        String langauge = Resources.getSystem().getConfiguration().locale.getLanguage();
                        Log.e("getDisplayLanguage", langauge);

                        if (langauge.equals("hi")) {
                            // mtinydb.putString(Constants.Language, "hi");
                            // Constants.languagechange(getActivity(), "hi");
                            langauge = "hindi";
                        } else {
                            // mtinydb.putString(Constants.Language, "en");
                            // Constants.languagechange(getActivity(), "en");
                            langauge = "english";
                        }
                        // String langauge = null;
                        String state_id = tinydb.getString(Constants.STATEID_KEY);
                        //     Log.e("state_id1",state_id);
                        String city_id = tinydb.getString(Constants.CITYID_KEY);
                        //     Log.e("city_id1",city_id);
                        String fcm_token = tinydb.getString(Constants.FCMTOKEN_KEY);
                        String facebook = "";
                        String google = Guserid;
                        String user_image = Gprofilepicurl;

                        callRegisterApi1(firstName, lastName, email, gender,
                                age, height, weight, country_id,
                                state_id, city_id, fcm_token, google, langauge, mHeightP, mWeightP);
                    } else {
                        Toast.makeText(MainActivity.this, getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();

                  /*  tinydb.putBoolean(Constants.SKIP_NOINTERNET, true);
                    Intent intent = new Intent(Activity_LoginScreen.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                    finish();*/
                    }
                } else {
                    Toast.makeText(MainActivity.this, getResources().getString(R.string.no_internet), Toast.LENGTH_LONG).show();
              /*  tinydb.putBoolean(Constants.SKIP_NOINTERNET, true);
                Intent intent = new Intent(Activity_LoginScreen.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                finish();*/

                }


            }


        } catch (Exception e) {

        }
        if (sliderAdapter != null) {
            sliderAdapter.notifyDataSetChanged();
        }
        String totalexe = String.valueOf(tinydb.getInt(Constants.TOTALEXE_KEY));
//        mTxtallexe.setText(totalexe);
        //      mTxtallkcal.setText(String.valueOf(round(tinydb.getFloat(Constants.TOTALKCAL_KEY), 1)));
        long temp = tinydb.getLong(Constants.TOTALTIME_KEY, 0);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(temp);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(temp) % 60;
        //     mTxtalltime.setText(minutes + ":" + seconds);
        //settingRequest();
    }

    public void UpdateAplanData() {
        mATextapercentage.setVisibility(View.VISIBLE);
        String percentage = tinydb.getInt(Constants.ALLDAYSPROGRESS_KEY) + "%";
        String dayleft = tinydb.getString(Constants.DAYSLEFT_KEY) + " " +
                getResources().getString(R.string.days_left);
        mAtxtDaylefts.setText(dayleft);
        mATextapercentage.setText(percentage);
        mAprogressbar.setProgressBarColor(getResources().getColor(R.color.homeprogresscolor));
        mAprogressbar.setProgress(tinydb.getInt(Constants.ALLDAYSPROGRESS_KEY));

//        mATextapercentage1.setVisibility(View.VISIBLE);
        String percentage1 = tinydb.getInt(Constants.ALLDAYSPROGRESS_LV2KEY) + "%";
        String dleft = tinydb.getString(Constants.DAYSLEFT_LV2KEY);
        String dayleft1;
        if (dleft.isEmpty()) {
            dayleft1 = "28 " + getResources().getString(R.string.days_left);
        } else {
            dayleft1 = tinydb.getString(Constants.DAYSLEFT_LV2KEY) + " " +
                    getResources().getString(R.string.days_left);
        }
        //mAtxtDaylefts1.setText(dayleft1);
        // mATextapercentage1.setText(percentage1);
       /* mAprogressbar1.setProgress(tinydb.getInt(Constants.ALLDAYSPROGRESS_LV2KEY));
        mAprogressbar1.setProgressBarColor(getResources().getColor(R.color.homeprogresscolor));


        mATextapercentage2.setVisibility(View.VISIBLE);*/
        String percentage2 = tinydb.getInt(Constants.ALLDAYSPROGRESS_LV3KEY) + "%";
        String dayleft2 = tinydb.getString(Constants.DAYSLEFT_LV3KEY) + " " +
                getResources().getString(R.string.days_left);
        // mAtxtDaylefts2.setText(dayleft2);
      /*  mATextapercentage2.setText(percentage2);
        mAprogressbar2.setProgress(tinydb.getInt(Constants.ALLDAYSPROGRESS_LV3KEY));
        mAprogressbar2.setProgressBarColor(getResources().getColor(R.color.homeprogresscolor));*/

    }


    public void showhomenative(final Context context, final LinearLayout frameLayout) {
        AdLoader.Builder builder = new AdLoader.Builder(context, Constants.admob_nativead);
        builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
            public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                mAdscard1.setVisibility(View.VISIBLE);
                frameLayout.setVisibility(View.VISIBLE);
                NativeContentAdView nativeContentAdView = (NativeContentAdView) ((Activity) context).getLayoutInflater().inflate(R.layout.ad_gnativehome, null);
                populatehomenative(nativeContentAd, nativeContentAdView);
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
    public void populatehomenative(NativeContentAd nativeContentAd, NativeContentAdView nativeContentAdView) {
        nativeContentAdView.setHeadlineView(nativeContentAdView.findViewById(R.id.ad_title_textview));
        nativeContentAdView.setImageView(nativeContentAdView.findViewById(R.id.contentad_image));
        nativeContentAdView.setBodyView(nativeContentAdView.findViewById(R.id.ad_describe_textview));
        nativeContentAdView.setCallToActionView(nativeContentAdView.findViewById(R.id.ad_action_button));
        ImageView mTransimg = ((ImageView) nativeContentAdView.findViewById(R.id.transimg));
        ((TextView) nativeContentAdView.getHeadlineView()).setText(nativeContentAd.getHeadline());
        ((TextView) nativeContentAdView.getBodyView()).setText(nativeContentAd.getBody());
        ((TextView) nativeContentAdView.getCallToActionView()).setText(nativeContentAd.getCallToAction());
        ((TextView) nativeContentAdView.getCallToActionView()).setTextColor(getResources().getColor(R.color.tbtncolor));


        Resources res = getApplicationContext().getResources();
        TransitionDrawable transition;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            transition = (TransitionDrawable) res.getDrawable(R.drawable.adtranstion, null);
        } else {
            transition = (TransitionDrawable) res.getDrawable(R.drawable.adtranstion);
        }
        mTransimg.setImageDrawable(transition);
        transition.setCrossFadeEnabled(true);
        mAdhandler = new Handler();
        mAdrunnable = new Runnable() {
            @Override
            public void run() {
                ((TextView) nativeContentAdView.getCallToActionView()).setTextColor(Color.WHITE);
                transition.startTransition(100);
            }
        };
        mAdhandler.postDelayed(mAdrunnable, 3000);

        List images = nativeContentAd.getImages();
        if (images.size() > 0) {
            ((RoundedImageView) nativeContentAdView.getImageView()).setImageDrawable(((com.google.android.gms.ads.formats.NativeAd.Image) images.get(0)).getDrawable());
        }
        nativeContentAdView.setNativeAd(nativeContentAd);
    }

    public void Resetdialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilder.setMessage(getResources().getString(R.string.restart_progress));
        alertDialogBuilder.setPositiveButton(getResources().getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        dialog.dismiss();
                        DeleteTables();
                        ClearPrefrences();
                        Intent i = getPackageManager().getLaunchIntentForPackage(getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        activity.startActivity(i);
                        activity.finish();
                    }
                });
        alertDialogBuilder.setNegativeButton(getResources().getString(R.string.no), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogs) {
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(activity.getResources().getColor(R.color.tbtncolor));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(activity.getResources().getColor(R.color.tbtncolor));

            }
        });
        alertDialog.show();
    }


    public void DeleteTables() {
        databaseOperations.deleteTable();
        databaseOperations.deleteTableLV2();
        databaseOperations.deleteTableLV3();

        databaseOperations2.deleteTable();
        databaseOperations2.deleteTableLV2();
        databaseOperations2.deleteTableLV3();

        historyProgressOperations.deleteTable();
        myplanDbhelper.deleteTable();
        dietplanDbhelper.deleteTable();
        graphdataOperations.deleteTable();
        weightGraphdataOperations.deleteTable();
    }

    public void ClearPrefrences() {
        tinydb.remove(Constants.TOTALTIME_KEY);
        tinydb.remove(Constants.TOTALEXE_KEY);
        tinydb.remove(Constants.TOTALKCAL_KEY);
        tinydb.remove(Constants.DIETPLANDATE_KEY);

        tinydb.remove(Constants.ALLDAYSPROGRESS_KEY);
        tinydb.remove(Constants.ALLDAYSPROGRESS_LV2KEY);
        tinydb.remove(Constants.ALLDAYSPROGRESS_LV3KEY);

        tinydb.remove(Constants.DAYSLEFT_KEY);
        tinydb.remove(Constants.DAYSLEFT_LV2KEY);
        tinydb.remove(Constants.DAYSLEFT_LV3KEY);

        tinydb.remove(Constants.DAYCLICK_KEY);
        tinydb.remove(Constants.ALV2DAYCLICK_KEY);
        tinydb.remove(Constants.ALV3DAYCLICK_KEY);

        tinydb.remove(Constants.DAYCLICK_BLV1KEY);
        tinydb.remove(Constants.DAYCLICK_BLV2KEY);
        tinydb.remove(Constants.DAYCLICK_BLV3KEY);

        tinydb.remove(Constants.DAYSLEFT_BLV1KEY);
        tinydb.remove(Constants.DAYSLEFT_BLV2KEY);
        tinydb.remove(Constants.DAYSLEFT_BLV3KEY);

        tinydb.remove(Constants.ALLDAYSPROGRESS_BLV1KEY);
        tinydb.remove(Constants.ALLDAYSPROGRESS_BLV2KEY);
        tinydb.remove(Constants.ALLDAYSPROGRESS_BLV3KEY);

        tinydb.remove(Constants.ALEVEL_KEY);
        tinydb.remove(Constants.BLEVEL_KEY);

        tinydb.remove(Constants.ADDEDFIXDIET);
        tinydb.remove(Constants.FIXDIET1_KEY);

    }


    public void shareApp(Context context) {
        final String appPackageName = context.getPackageName();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Downlaod this app " + getResources().getString(R.string.app_name) + " and Join over 1M+ community here at: https://play.google.com/store/apps/details?id=" + getPackageName());
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }


    @Override
    protected void onDestroy() {
        if (mRatehandler != null && mRaterunnable != null) {
            mRatehandler.removeCallbacks(mRaterunnable);
        }
        if (dialrateexepe != null && dialrateexepe.isShowing()) {
            dialrateexepe.dismiss();
        }
        if (feedbackdial != null && feedbackdial.isShowing()) {
            feedbackdial.dismiss();
        }
        if (mAdhandler != null && mAdrunnable != null) {
            mAdhandler.removeCallbacks(mAdrunnable);
        }
        super.onDestroy();
    }
}
