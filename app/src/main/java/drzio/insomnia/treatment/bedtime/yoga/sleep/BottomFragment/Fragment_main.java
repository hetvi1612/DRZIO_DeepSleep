
package drzio.insomnia.treatment.bedtime.yoga.sleep.BottomFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
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
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
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
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Allinfos;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Calenderview;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Level1;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Level2;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Level3;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_MyTraining;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Purchase;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_webview;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_weekgoal;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.GetcountBulb;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter.SliderAdapter;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter.WeekGoalAdapter;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.CategoryData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.TestDatalist;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.TestDatamodel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.TestSubcatdata;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.Testcatdata;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.AppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.ChooseGenderActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.MaleAppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity.Blogmodel;
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
import drzio.insomnia.treatment.bedtime.yoga.sleep.Graphdata.GraphdataOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Graphdata.WeightGraphdataOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.ApiInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.LanguageApi;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.RateModel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.Register_Api;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.AlarmHelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.BroadcastManager;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.ForonedayBroadcastManager;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.GoalBroadcastManager;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.SixhourBroadcastManager;
import drzio.insomnia.treatment.bedtime.yoga.sleep.SliderNewAdapter;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.Activity_Covidhome;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Banner1;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.BannerModal;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.LoginData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Weekgoalmodal;
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
import uk.co.deanwild.materialshowcaseview.MaterialShowcaseView;

import static com.facebook.FacebookSdk.getApplicationContext;
import static com.github.mikephil.charting.charts.Chart.LOG_TAG;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity.mIvEarn3;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity.mIvProgress3;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity.mIvPurchase3;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity.mIvdiscover3;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity.mIvhome3;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity.mTvdiscover;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity.mTvearn;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity.mTvprogress;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity.mTvpurchase;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity.sequence;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.PREMIUN_KEY;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.catnamearray;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.homebannerdatalist1;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.isAdded;
import static retrofit2.converter.scalars.ScalarsConverterFactory.create;

public class Fragment_main extends Fragment implements /*NavigationView.OnNavigationItemSelectedListener,*/
        GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, LocationListener {
    private static LinearLayout laystore;
    private static Context context;
    private TinyDB tinydb;
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
    private TextView mAtxtDaylefts ,mAtxtDaylefts1, mAtxtDaylefts2;
    private TextView mATextapercentage, mATextapercentage1, mATextapercentage2;
    private CircularProgressBar mAprogressbar, mAprogressbar1, mAprogressbar2;
//    drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity activity;

    private boolean isClicked = false;
    RelativeLayout mCarddiscount;
    private static ImageView mBtnmenu;
    private NavigationView mNavigationView;
    private final Handler mDrawerHandler = new Handler();
    //    public static DrawerLayout layout;
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

    RadioButton rhindi, rfrench, rchinish, rpotugues, rrussian, aarbic, renglish;
    String selectedSuperStar;
    TextView mTxtallexe, mTxtallkcal, mTxtalltime;
    Location mLastLocation;
    LocationRequest mLocationRequest;
    private BillingProcessor bp;
    /*private ImageView mIvdiscover2;
    private ImageView mIvProgress2;
    private ImageView mIvhome2;
    private ImageView mIvEarn2;
    private ImageView mIvPurchase2;
    private ImageView mIvdiscover3;
    private ImageView mIvProgress3;
    private ImageView mIvhome3;
    private ImageView mIvEarn3;
    private ImageView mIvPurchase3;
    private TextView mTvdiscover;
    private TextView mTvprogress;
    private TextView mTvearn;
    private TextView mTvpurchase;*/
    private String token;
    Fragment selectedfragment = null;
    private String BASEURL;


    /*@Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PurchaseCheck();
    }*/

    @SuppressLint("WrongConstant")


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        tinydb = new TinyDB(getContext());
        String lau = tinydb.getString(Constants.Language);

        context = getContext();
        Constants.languagechange(getContext(), lau);
        //     Log.e("laun", lau);
        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(getContext(), languages);
        //     Log.e("languages_data", languages + "dfkdfk");
        PurchaseCheck();
        tinydb.getBoolean(PREMIUN_KEY);
        token = tinydb.getString(Constants.Authtoken);

        BASEURL = tinydb.getString(Constants.NewBaseUrl);
        fitnessApplication = FitnessApplication.getInstance();
//        activity = this;
        RelativeLayout banner1 = (RelativeLayout) view.findViewById(R.id.banner1);
        FrameLayout mGbannerlay2 = view.findViewById(R.id.adframe1234);
        mGbannerlay2.setVisibility(View.GONE);
        // showBanner(getContext(), mGbannerlay2, banner1);
        mBtnmenu = (ImageView) view.findViewById(R.id.btnmenu);

        mNavigationView = (NavigationView) view.findViewById(R.id.navigation_view);
//        layout = view.findViewById(R.id.drawer);
//        mNavigationView.setNavigationItemSelectedListener(this);
        databaseOperations = new DatabaseOperations(getContext());
        databaseOperations2 = new BDatabaseOperations(getContext());
        graphdataOperations = new GraphdataOperations(getContext());
        weightGraphdataOperations = new WeightGraphdataOperations(getContext());
        historyProgressOperations = new HistoryProgressOperations(getContext());
        myplanDbhelper = new MyplanDbhelper(getContext());
        dietplanDbhelper = new DietplanDbhelper(getContext());
        RelativeLayout mTempbmibtn = view.findViewById(R.id.wwekplans);
        laystore = view.findViewById(R.id.laystore);

        boolean sequence = tinydb.getBoolean("donedone");
        if (sequence) {
           // Animuser();
        }
//        ImageView mBtnpremium = (ImageView) view.findViewById(R.id.bottomlay);
//        RecyclerView mRvnavigation = (RecyclerView) view.findViewById(R.id.rvnav);
//        mRvnavigation.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
//        Adapter_Navigation navigation = new Adapter_Navigation(activity, layout, getContext());
//        mRvnavigation.setAdapter(navigation);
        RelativeLayout mDiscoverbtn = (RelativeLayout) view.findViewById(R.id.dpcardview);
        ImageView mIvdiscover = (ImageView) view.findViewById(R.id.addmorebtn);
        RelativeLayout mCustombtn = (RelativeLayout) view.findViewById(R.id.cpcardview);
        mBtnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.layout.openDrawer(Gravity.START);
            }
        });
        mDiscoverbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getContext(), MainActivity.class);
                Bundle data1 = new Bundle();
                data1.putInt("discover",1);
                intent.putExtras(data1);
                startActivity(intent);*/

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainlayout, new Fragment_Discover()).commitNow();

                mTvdiscover.setTextColor(getResources().getColor(R.color.tbtncolor));
                mTvprogress.setTextColor(getResources().getColor(R.color.navunselected));
                mTvearn.setTextColor(getResources().getColor(R.color.navunselected));
                mTvpurchase.setTextColor(getResources().getColor(R.color.navunselected));
                mIvhome3.setColorFilter(getResources().getColor(R.color.grey));
                mIvdiscover3.setColorFilter(getResources().getColor(R.color.tbtncolor));
                mIvProgress3.setColorFilter(getResources().getColor(R.color.grey));
                mIvEarn3.setColorFilter(getResources().getColor(R.color.grey));
                mIvPurchase3.setColorFilter(getResources().getColor(R.color.grey));
            }
        });
        mIvdiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainlayout, new Fragment_Discover()).commitNow();
                /*Intent intent = new Intent(getContext(), Activity_Discover.class);
                startActivity(intent);*/
            }
        });
        mCustombtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_MyTraining.class);
                startActivity(intent);
//                activity.finish();
            }
        });
        mTempbmibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activity_Calenderview.class);
                startActivity(intent);
            }
        });


        ImageView editgoal = view.findViewById(R.id.editgoal);
        editgoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activity_weekgoal.class);
                startActivity(intent);
            }
        });

        /*mBtnpremium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_Purchase.class);
                startActivity(intent);
            }
        });
        if (tinydb.getBoolean(Constants.PREMIUN_KEY)) {
            mBtnpremium.setVisibility(View.GONE);
        }*/
//        InitLoginlayouts();
        /*mBtnmenu.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                layout.openDrawer(Gravity.START);
            }
        });*/

      /*  try {
            AppUpdaterUtils appUpdaterUtils = new AppUpdaterUtils(getContext())
                    .withListener(new AppUpdaterUtils.UpdateListener() {
                        @Override
                        public void onSuccess(Update update, Boolean isUpdateAvailable) {
                            if (isUpdateAvailable) {
                                new AppUpdater(getContext())
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
        mSlideloader = (ShimmerFrameLayout) view.findViewById(R.id.slideloader);

        Slidelayout = (LinearLayout) view.findViewById(R.id.slidelayout);
        sliderView = view.findViewById(R.id.imageSlider);
        Slidelayout.setVisibility(View.VISIBLE);

        ConnectivityManager mgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();


        if (netInfo != null) {
            if (netInfo.isConnected()) {

                //  Toast.makeText(getContext(), "Available", Toast.LENGTH_SHORT).show();
                Log.e("Available", "Available");
               /* if (homebannerdatalist.size() != 0) {
                    try {
                        if (Slidelayout != null) {
                            Slidelayout.setVisibility(View.VISIBLE);
                        }
                        mSlideloader.setVisibility(View.GONE);
                        sliderView.setVisibility(View.VISIBLE);
                        final SliderAdapter adapter = new SliderAdapter(context, homebannerdatalist);
                        sliderView.setSliderAdapter(adapter);
                     *//*   sliderView.setIndicatorAnimation(IndicatorAnimations.DROP);
                        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                        sliderView.setIndicatorSelectedColor(Color.WHITE);
                        sliderView.setIndicatorUnselectedColor(Color.GRAY);
                        sliderView.startAutoCycle();*//*



                        sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
                        //below method is used to setadapter to sliderview.
                        sliderView.setSliderAdapter(adapter);
                        //below method is use to set scroll time in seconds.
                        sliderView.setScrollTimeInSec(5);
                        //to set it scrollable automatically we use below method.
                        sliderView.setAutoCycle(true);
                        //to start autocycle below method is used.
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
                    mSlideloader.setVisibility(View.GONE);

                    //callBannerApi();
                    getBanner();
                }*/
                mSlideloader.setVisibility(View.VISIBLE);
                sliderView.setVisibility(View.VISIBLE);
                //    Slidelayout.setVisibility(View.VISIBLE);

                // Internet Available
            } else {
                //No internet
                mSlideloader.setVisibility(View.GONE);
                sliderView.setVisibility(View.GONE);
                //      Slidelayout.setVisibility(View.GONE);

                // Toast.makeText(getContext(), "No internet", Toast.LENGTH_SHORT).show();
                Log.e("No internet", "No internet");
            }
        } else {
            mSlideloader.setVisibility(View.GONE);
            sliderView.setVisibility(View.GONE);
            //   Slidelayout.setVisibility(View.GONE);
           /* Toast.makeText(getContext(), "No internet1", Toast.LENGTH_SHORT).show();
            Log.e("No internet1", "No internet1");
            SliderAdapteroffline sliderAdapteroffline = new SliderAdapteroffline(getActivity());
            sliderView.setSliderAdapter(sliderAdapteroffline);*/
            //No internet
        }
        BottomExit();
      /*  mAdscard1 = (RelativeLayout) view.findViewById(R.id.cardadd);
        LinearLayout mGbannerlay = view.findViewById(R.id.adframe);
        showhomenative(this, mGbannerlay);*/
        JodaTimeAndroid.init(getContext());
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
        Date c2 = Calendar.getInstance().getTime();
        SimpleDateFormat df2 = new SimpleDateFormat("dd-MM-yyyy");
        String formattedDate2 = df2.format(c2);
        getCalculatedDate(formattedDate2, "dd-MM-yyyy", 3);
        tinydb.putString(Constants.TWODAYREMIDERDATE_KEY, getCalculatedDate(formattedDate2, "dd-MM-yyyy", 2));
        tinydb.putString(Constants.FIVEDAYREMIDERDATE_KEY, getCalculatedDate(formattedDate2, "dd-MM-yyyy", 5));

        mGoalayout = (RelativeLayout) view.findViewById(R.id.goallayout);
        mSetgoallayout = (RelativeLayout) view.findViewById(R.id.setgoallayout);
        TextView mTxtsetgoals = (TextView) view.findViewById(R.id.btnsetgoals);
        mTxtallexe = (TextView) view.findViewById(R.id.totalexes);
        mTxtallkcal = (TextView) view.findViewById(R.id.totalkcal);
        mTxtalltime = (TextView) view.findViewById(R.id.totaltime);
        LinearLayout mDatalay = (LinearLayout) view.findViewById(R.id.layone);
        mDatalay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_Calenderview.class);
                startActivity(intent);
            }
        });
        LinearLayout mBtnhistory = view.findViewById(R.id.btnhistory);
        mBtnhistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(getContext(), Activity_Reports.class);
                startActivity(intent);*/
//                finish();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainlayout, new Fragment_Report()).commitNow();

                mTvdiscover.setTextColor(getResources().getColor(R.color.navunselected));
                mTvprogress.setTextColor(getResources().getColor(R.color.navunselected));
                mTvearn.setTextColor(getResources().getColor(R.color.navunselected));
                mTvpurchase.setTextColor(getResources().getColor(R.color.tbtncolor));
                mIvhome3.setColorFilter(getResources().getColor(R.color.grey));
                mIvdiscover3.setColorFilter(getResources().getColor(R.color.grey));
                mIvProgress3.setColorFilter(getResources().getColor(R.color.grey));
                mIvEarn3.setColorFilter(getResources().getColor(R.color.grey));
                mIvPurchase3.setColorFilter(getResources().getColor(R.color.tbtncolor));

            }
        });
        TextView mBtnmore = (TextView) view.findViewById(R.id.btnmore);
        mBtnmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  Intent intent = new Intent(getContext(), Activity_Bloglists.class);
                startActivity(intent);*/
//                finish();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.mainlayout, new Fragment_Report()).commitNow();

                mTvdiscover.setTextColor(getResources().getColor(R.color.navunselected));
                mTvprogress.setTextColor(getResources().getColor(R.color.tbtncolor));
                mTvearn.setTextColor(getResources().getColor(R.color.navunselected));
                mTvpurchase.setTextColor(getResources().getColor(R.color.navunselected));
                mIvhome3.setColorFilter(getResources().getColor(R.color.grey));
                mIvdiscover3.setColorFilter(getResources().getColor(R.color.grey));
                mIvProgress3.setColorFilter(getResources().getColor(R.color.tbtncolor));
                mIvEarn3.setColorFilter(getResources().getColor(R.color.grey));
                mIvPurchase3.setColorFilter(getResources().getColor(R.color.grey));
            }
        });
        mBtAds = (ImageView) view.findViewById(R.id.btnadtext);
        ImageView mBtnstore = (ImageView) view.findViewById(R.id.ivbtnstore);
        mBlogtxtcount = (TextView) view.findViewById(R.id.txtcounter);
        try {
            mBtnstore.setBackgroundResource(R.drawable.adsstoreanim);
        } catch (Exception e) {

        }
        AnimationDrawable anim2 = (AnimationDrawable) mBtnstore.getBackground();
        anim2.start();
        ImageView mIvcovid = (ImageView) view.findViewById(R.id.ivcovid);
        mIvcovid.setBackgroundResource(R.drawable.covidanim);
        AnimationDrawable anim3 = (AnimationDrawable) mIvcovid.getBackground();
        anim3.start();
        mBlogtxtcount.setVisibility(View.GONE);
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

       /* if (lau.isEmpty()) {
            tinydb.putString(Constants.Language, "en");
            Log.e("laung", "ghhg" + lau);
        }*/
        mBtAds.setBackgroundResource(R.drawable.ic_off);
        callBlogApi();
        mBtAds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBlogtxtcount.setVisibility(View.GONE);
                mBtAds.setBackgroundResource(R.drawable.ic_off);
                tinydb.putInt(Constants.NEWBLOGSCOUNTS, 0);


                Intent intent = new Intent(getContext(), MainActivity.class);
                // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                Bundle data1 = new Bundle();
                data1.putInt("discover", 4);
                intent.putExtras(data1);
                //   intent.putExtra("isFrom3", true);
                startActivity(intent);
                //      finish();
             /*   Intent intent = new Intent(getContext(), Activity_Bloglists.class);
//                intent.putExtra("isFrom3", true);
                startActivity(intent);*/
//                finish();
                tinydb.putInt(Constants.PRESENTBLOGCOUNTS, Blogmodel.getmBloglist().size());
            }
        });

        mBtnstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cgender = tinydb.getBoolean(Constants.Genderchoose);
                if (cgender) {
                    if (tinydb.getString(Constants.GENDER_KEY).equals(getResources().getString(R.string.female))) {
                        Intent intent = new Intent(getContext(), AppstoreActivity.class);
                        startActivity(intent);
                    } else {
                        Intent intent = new Intent(getContext(), MaleAppstoreActivity.class);
                        startActivity(intent);
                    }
                } else {
                    Intent intent = new Intent(getContext(), ChooseGenderActivity.class);
                    startActivity(intent);
                }
            }
        });
        RecyclerView mGoalrecycleview = (RecyclerView) view.findViewById(R.id.goalrecycler);
        mGoaldaytxt = (TextView) view.findViewById(R.id.goalday);
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

        String totalexe = String.valueOf(tinydb.getInt(Constants.TOTALEXE_KEY));
        mTxtallexe.setText(totalexe);
        mTxtallkcal.setText(String.valueOf(round(tinydb.getFloat(Constants.TOTALKCAL_KEY), 1)));
        long temp = tinydb.getLong(Constants.TOTALTIME_KEY, 0);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(temp);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(temp) % 60;
        mTxtalltime.setText(minutes + ":" + seconds);

        if (!tinydb.getBoolean(Constants.RATEDIALOGSHOW_KEY)) {
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
        }

        LinearLayout mbtnWeekgoal = (LinearLayout) view.findViewById(R.id.setweekgoalbtn);
        mbtnWeekgoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activity_weekgoal.class);
                startActivity(intent);
            }
        });
        scheduleAlarm();
        scheduletwoAlarm();
        schedule6oclockAlarm1();
        schedulegoalAlarm();
        InitializeAPlans(view);
        InitializeBPlans(view);


        getBanner();

        feedbacklist = getResources().getStringArray(R.array.feedlist);
        mTxtsetgoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activity_weekgoal.class);
                startActivity(intent);
            }
        });
        mGoalrecycleview.setLayoutManager(new GridLayoutManager(getContext(), 7));
        goalAdapter = new WeekGoalAdapter(getContext(), weekgoallist);
        mGoalrecycleview.setAdapter(goalAdapter);
//        getDaysNames();
        DatabaseOperations databaseOperations = new DatabaseOperations(getContext());
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

        BDatabaseOperations databaseOperations2 = new BDatabaseOperations(getContext());
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

      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager mNotificationManager =
                    (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);
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
                    startActivity(new Intent(getContext(), Activity_Purchase.class));
                } else if (notificationkey.equals("blog") && notificationvalue.equals("true")) {
                    startActivity(new Intent(getContext(), Activity_Bloglists.class));
                } else if (notificationkey.equals("diet") && notificationvalue.equals("true")) {
                    startActivity(new Intent(getContext(), Activity_Dietplans.class));
                } else if (notificationkey.equals("covid") && notificationvalue.equals("true")) {
                    startActivity(new Intent(getContext(), Activity_Covidhome.class));
                } else if (notificationkey.equals("home") && notificationvalue.equals("true")) {
                    startActivity(new Intent(getContext(), drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity.class));
                } else if (notificationkey.equals("drziostore") && notificationvalue.equals("true")) {
                    startActivity(new Intent(getContext(), AppstoreActivity.class));
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
        if (getIntent().getExtras() != null) {
            try {
                Bundle bundle = getIntent().getExtras();
                if (bundle != null) {
                    isFromnotification = false;
                    String mType = bundle.getString("mNotificationtype");
                    Log.e("mNotificationtype", mType);
                    String mDatalink = bundle.getString("mLink");
                    String mDefaulteType = bundle.getString("mDefaulteType");
                    assert mType != null;
                    switch (mType) {
                        case "Default":
                            Defaultetype(mDefaulteType, mDatalink);
                            break;
                        case "Mobile":
                            Gotoplay(mDatalink);
                            break;
                        case "Web":
                            String tempdate = tinydb.getString(Constants.DIETPLANDATE_KEY);
                            Date c = Calendar.getInstance().getTime();
                             Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
                            String formattedDate = df.format(c);
                            if (!tempdate.isEmpty() && tempdate.equals(formattedDate)) {
                                Intent intent1 = new Intent(getContext(), Activity_Userdietlist.class);
                                intent1.putExtra("isFrom2", true);
                                startActivity(intent1);
                            } else {
                                Intent intent1 = new Intent(getContext(), Activity_Dietplans.class);
                                intent1.putExtra("isFrom", true);
                                startActivity(intent1);
                            }
                            break;
                        case "Webview":
                            Intent intent = new Intent(getContext(), Activity_webview.class);
                            intent.putExtra("link", mDatalink);
                            startActivity(intent);
                            break;
                        case "Blog":
                            Intent intent2 = new Intent(getContext(), Activity_Blogdetails.class);
                            intent2.putExtra("bid", mDatalink);
                            startActivity(intent2);
                            break;
                        case "Ads":
                            Intent intent3 = new Intent(getContext(), Activity_Purchase.class);
                            startActivity(intent3);
                            break;
                        case "Premium":
                            Intent intent4 = new Intent(getContext(), Activity_Purchase.class);
                            startActivity(intent4);
                            break;
                        case "Covid19":
                            if (!isClicked) {
                                isClicked = true;
                                Intent intent5 = new Intent(getContext(), Activity_Covidhome.class);
                                intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intent5);
//                                finish();
                            }
                            break;

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        subscribeToPushService();
*/
        return view;
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

    public void showBanner(Context context, final FrameLayout adMobView, RelativeLayout loadlayout) {
        try {
            final AdView mAdView = new AdView(context);
            mAdView.setAdSize(AdSize.BANNER);
            mAdView.setAdUnitId(Constants.admob_banner);
            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    loadlayout.setVisibility(View.GONE);
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



    public void DialogDiscount() {
        dialdiscount = new Dialog(getContext());
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
                Intent intent = new Intent(getContext(), Activity_Purchase.class);
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



    public void InitializeAPlans(View view) {
        mACard = (RelativeLayout) view.findViewById(R.id.cardview);
        mAtxtDaylefts = (TextView) view.findViewById(R.id.mdaysLeft);
        mATextapercentage = (TextView) view.findViewById(R.id.mpercentScore);
        mAprogressbar = (CircularProgressBar) view.findViewById(R.id.mprogress);

        /*mACard1 = (RelativeLayout) view.findViewById(R.id.cardview1);
        mAtxtDaylefts1 = (TextView) view.findViewById(R.id.mdaysLeft1);
        mATextapercentage1 = (TextView) view.findViewById(R.id.mpercentScore1);
        mAprogressbar1 = (CircularProgressBar) view.findViewById(R.id.mprogress1);
        ImageView mLock1 = (ImageView) view.findViewById(R.id.imglock1);

        mACard2 = (RelativeLayout) view.findViewById(R.id.cardview2);
        mAtxtDaylefts2 = (TextView) view.findViewById(R.id.mdaysLeft2);
        mATextapercentage2 = (TextView) view.findViewById(R.id.mpercentScore2);
        mAprogressbar2 = (CircularProgressBar) view.findViewById(R.id.mprogress2);
        ImageView mLock2 = (ImageView) view.findViewById(R.id.imglock2);*/

        LinearLayout mBtninfo1 = (LinearLayout) view.findViewById(R.id.btninfo);
        /*LinearLayout mBtninfo2 = (LinearLayout) view.findViewById(R.id.btninfo1);
        LinearLayout mBtninfo3 = (LinearLayout) view.findViewById(R.id.btninfo3);*/
        mCarddiscount = (RelativeLayout) view.findViewById(R.id.carddiscount);

        if (tinydb.getBoolean(Constants.PREMIUN_KEY)) {
          /*  mLock1.setVisibility(View.GONE);
            mLock2.setVisibility(View.GONE);*/
            mCarddiscount.setVisibility(View.GONE);
        } else {
           /* mLock1.setVisibility(View.VISIBLE);
            mLock2.setVisibility(View.VISIBLE);*/
            mCarddiscount.setVisibility(View.VISIBLE);
        }
        ViewFlipper viewFlipper = view.findViewById(R.id.disflipperid);
        viewFlipper.startFlipping();
        mCarddiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_Purchase.class);
                startActivity(intent);
            }
        });

        mBtninfo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_Allinfos.class);
                intent.putExtra("pos", 0);
                intent.putExtra("imgnum", 0);
                startActivity(intent);
            }
        });
       /* mBtninfo2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_Allinfos.class);
                intent.putExtra("pos", 1);
                intent.putExtra("imgnum", 1);
                startActivity(intent);
            }
        });
        mBtninfo3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_Allinfos.class);
                intent.putExtra("pos", 2);
                intent.putExtra("imgnum", 2);
                startActivity(intent);
            }
        });*/
        mACard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  if (!isclicked) {
                    isclicked = true;*/
//                    Gad = 1;
//                    if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
//                        mInterstitialAdMob.show();
//                    }else {
                Intent intent = new Intent(getContext(), Activity_Level1.class);
                startActivity(intent);
//                    finish();
//                    }
                /* }*/

            }
        });

        /*mACard1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             *//*   if (!isclicked) {
                    isclicked = true;*//*
//                    Gad = 2;
//                    if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
//                        mInterstitialAdMob.show();
//                    }else {
                    Intent intent = new Intent(getContext(), Activity_Level2.class);
                    startActivity(intent);
                //    finish();
//                    }
                *//*}*//*
            }
        });

        mACard2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              *//*  if (!isclicked) {
                    isclicked = true;*//*
//                    Gad = 3;
//                    if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
//                        mInterstitialAdMob.show();
//                    }else {
                    Intent intent = new Intent(getContext(), Activity_Level3.class);
                    startActivity(intent);
                 //   finish();
//                    }
               *//* }*//*
            }
        });*/
    }

    public void InitializeBPlans(View view) {

        RelativeLayout mNutrition = (RelativeLayout) view.findViewById(R.id.nbcardview);
        mNutrition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              /*  boolean diet = tinydb.getBoolean(Constants.ReadyDietPlan);
                if (diet) {
                    Intent intent1 = new Intent(getContext(), DefalutAndCustomplanActivity.class);
                    intent1.putExtra("isFrom2", false);
                    startActivity(intent1);
                } else {
                    Intent intent = new Intent(getContext(), CustomUpdateActivity.class);
                    intent.putExtra("isFrom", false);
                    startActivity(intent);
                }


            }*/
                String tempdate = tinydb.getString(Constants.DIETPLANDATE_KEY);
                Date c = Calendar.getInstance().getTime();
                Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
                String formattedDate = df.format(c);
                if (!tempdate.isEmpty() && tempdate.equals(formattedDate)) {
                    Intent intent1 = new Intent(getContext(), DefalutAndCustomplanActivity.class);
                    intent1.putExtra("isFrom2", false);
                    startActivity(intent1);
                } else {
                    Intent intent = new Intent(getContext(), CustomUpdateActivity.class);
                    intent.putExtra("isFrom", false);
                    startActivity(intent);
                }
            }
        });

    }

    /*public void initAdmobFullAd(Context context) {
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
                    finish();
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
    }*/

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


    public void scheduletwoAlarm() {
        long time = new GregorianCalendar().getTimeInMillis() + 48 * 60 * 60 * 1000;
        Log.e("timetime", String.valueOf(time));
//        long time = new GregorianCalendar().getTimeInMillis() + 60000;
        Intent intentAlarm = new Intent(getContext(), BroadcastManager.class);
        intentAlarm.setAction("ALARM");
        intentAlarm.addCategory("android.intent.category.DEFAULT");
        intentAlarm.putExtra("ID", 1);
        @SuppressLint("WrongConstant") AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(NotificationCompat.CATEGORY_ALARM);
        assert alarmManager != null;
//        alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(getContext(), 1, intentAlarm, 0));
        } else if (Build.VERSION.SDK_INT >= 19) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(getContext(), 1, intentAlarm, 0));
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(getContext(), 1, intentAlarm, 0));
        }
    }

    public void scheduleAlarm() {
        long time = new GregorianCalendar().getTimeInMillis() + 24 * 60 * 60 * 1000;
        //     long time = new GregorianCalendar().getTimeInMillis() + 60000;
        Intent intentAlarm = new Intent(getContext(), ForonedayBroadcastManager.class);
        intentAlarm.setAction("ALARM");
        intentAlarm.addCategory("android.intent.category.DEFAULT");
        intentAlarm.putExtra("ID", 1);
        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(NotificationCompat.CATEGORY_ALARM);
        assert alarmManager != null;
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(getContext(), 1, intentAlarm, 0));
        } else if (Build.VERSION.SDK_INT >= 19) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(getContext(), 1, intentAlarm, 0));
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(getContext(), 1, intentAlarm, 0));
        }
    }

    public void schedule6oclockAlarm1() {
        long time = new GregorianCalendar().getTimeInMillis() + 6 * 60 * 60 * 1000;
        Log.e("6oclockalaramtimetime", String.valueOf(time));
//        long time = new GregorianCalendar().getTimeInMillis() + 60000;
        Intent intentAlarm = new Intent(getContext(), SixhourBroadcastManager.class);
        intentAlarm.setAction("ALARM");
        intentAlarm.addCategory("android.intent.category.DEFAULT");
        intentAlarm.putExtra("ID", 1);
        @SuppressLint("WrongConstant") AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(NotificationCompat.CATEGORY_ALARM);
        assert alarmManager != null;
//        alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(getContext(), 1, intentAlarm, 0));
        } else if (Build.VERSION.SDK_INT >= 19) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(getContext(), 1, intentAlarm, 0));
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(getContext(), 1, intentAlarm, 0));
        }
    }

    public void schedulegoalAlarm() {
        long time = new GregorianCalendar().getTimeInMillis() + 24 * 60 * 60 * 1000;
        Log.e("goalalaramtimetime", String.valueOf(time));
//        long time = new GregorianCalendar().getTimeInMillis() + 60000;
        Intent intentAlarm = new Intent(getContext(), GoalBroadcastManager.class);
        intentAlarm.setAction("ALARM");
        intentAlarm.addCategory("android.intent.category.DEFAULT");
        intentAlarm.putExtra("ID", 1);
        @SuppressLint("WrongConstant") AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(NotificationCompat.CATEGORY_ALARM);
        assert alarmManager != null;
//        alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(this, 1, intentAlarm, PendingIntent.FLAG_UPDATE_CURRENT));
        if (Build.VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(getContext(), 1, intentAlarm, 0));
        } else if (Build.VERSION.SDK_INT >= 19) {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(getContext(), 1, intentAlarm, 0));
        } else {
            alarmManager.set(AlarmManager.RTC_WAKEUP, time, PendingIntent.getBroadcast(getContext(), 1, intentAlarm, 0));
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
                    Intent intent3 = new Intent(getContext(), Activity_Level1.class);
                    startActivity(intent3);
//                    finish();
                    break;
                case "Mobile":
                    Gotoplay(mDatalink);
                    break;

                case "Customizeplan":
                    Intent intentt = new Intent(getContext(), Activity_MyTraining.class);
                    startActivity(intentt);
                case "Web":
                    String tempdate = tinydb.getString(Constants.DIETPLANDATE_KEY);
                    Date c = Calendar.getInstance().getTime();
                     Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
                    String formattedDate = df.format(c);
                    if (!tempdate.isEmpty() && tempdate.equals(formattedDate)) {
                        Intent intent1 = new Intent(getContext(), DefalutAndCustomplanActivity.class);
                        intent1.putExtra("isFrom2", false);
                        startActivity(intent1);
                    } else {
                        Intent intent = new Intent(getContext(), CustomUpdateActivity.class);
                        intent.putExtra("isFrom", false);
                        startActivity(intent);
                    }
                    break;
                case "Webview":
                    Intent intent = new Intent(getContext(), Activity_webview.class);
                    intent.putExtra("link", mDatalink);
                    startActivity(intent);
                    break;
                case "Blog":
                    Intent intent1 = new Intent(getContext(), MainActivity.class);
                    //   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    Bundle data1 = new Bundle();
                    data1.putInt("discover", 4);
                    intent1.putExtras(data1);
                    //    intent.putExtra("isFrom3", true);
                    startActivity(intent1);
                   /* Intent intent2 = new Intent(getContext(), Activity_Blogdetails.class);
                    intent2.putExtra("bid", mDatalink);
                    startActivity(intent2);*/
                    break;
                case "Ads":
                    Intent intent31 = new Intent(getContext(), Activity_Purchase.class);
                    startActivity(intent31);
                    break;
                case "Premium":
                    Intent intent4 = new Intent(getContext(), Activity_Purchase.class);
                    startActivity(intent4);
                    break;
                case "Covid19":
                    if (!isClicked) {
                        isClicked = true;
                        Intent intent5 = new Intent(getContext(), Activity_Covidhome.class);
                        intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent5);
//                        finish();
                    }
                    break;
            }
        } else {
            Intent intent3 = new Intent(getContext(), Activity_Level1.class);
            startActivity(intent3);
//            finish();
        }
    }

    /*public void Adsdialog() {
        Fragment_Dialog dialog = new Fragment_Dialog();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        dialog.show(ft, Fragment_Dialog.TAG);
    }*/

   /* @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }*/


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
            String androidId = 22+Settings.Secure.getString(getContext().getContentResolver(),
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
    public void onStart() {
        super.onStart();
        PurchaseCheck();
    }
/* @Override
    public void onStart() {
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
    }*/

    @Override
    public void onPause() {
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
            Toast.makeText(getContext(), "You don't have Google Play installed", Toast.LENGTH_LONG).show();
        }
    }

    public void Rateus() {
        Uri uri1 = Uri.parse("https://play.google.com/store/apps/details?id=" + getContext().getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri1);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getContext(), "You don't have Google Play installed", Toast.LENGTH_LONG).show();
        }
    }

    /*@Override
    public void onBackPressed() {
        if (layout.isDrawerOpen(Gravity.LEFT)) {
            layout.closeDrawers();
        } else {

            if (tinydb.getBoolean(Constants.RATEDIALOGSHOW_KEY)) {
                mBottomSheetDialog.show();
            } else {
                DialogRatexeperience();
            }
            //mBottomSheetDialog.show();
        }
    }*/

    public void DialogRatexeperience() {
        dialrateexepe = new Dialog(getContext());
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
            }
        });
        dialrateexepe.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Constants.isRatedialShown = true;
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
        dialrateonplay = new Dialog(getContext());
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
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.MaterialThemeDialog);
        LayoutInflater inflater = LayoutInflater.from(getContext());
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
                Animation mzoom = AnimationUtils.loadAnimation(getContext(), R.anim.zoom_in);
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
                String androidId = 22+Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID)+22;
                String email = tinydb.getString(Constants.USEREMAIL_KEY);
                String release = Build.VERSION.RELEASE;
                callFeedbackApi(androidId, device_name, release, email, mOp1, mOp2, mOp3, mOp4, mOp5, mComment/*, mComment, mUid*/);
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
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

    @SuppressLint("BASEURL")
    public void BottomExit() {
        final View view = getLayoutInflater().inflate(R.layout.dialog_exit, null);
        RelativeLayout mLayad = (RelativeLayout) view.findViewById(R.id.localad2);
        TextView mBtncancel = (TextView) view.findViewById(R.id.btnexit);
        ImageView mBtnExit = (ImageView) view.findViewById(R.id.imgclose);
        ImageView mIvappic = (ImageView) view.findViewById(R.id.imageView);
        ImageView mIvbanner = (ImageView) view.findViewById(R.id.imageView3);
        TextView mTvappname = (TextView) view.findViewById(R.id.appname);
        HtmlTextView mTvdesc = (HtmlTextView) view.findViewById(R.id.appdesc);
        TextView mBtnInstall = (TextView) view.findViewById(R.id.textView2);
        LinearLayout mAdframebottom = (LinearLayout) view.findViewById(R.id.adframebottom);
        showGOOGLEAdvance2(getContext(), mAdframebottom);

        mBottomSheetDialog = new Dialog(getContext(), R.style.MaterialDialogSheet);
        mBottomSheetDialog.setContentView(view);
        mBottomSheetDialog.setCancelable(true);
        mBottomSheetDialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mBottomSheetDialog.getWindow().setGravity(Gravity.BOTTOM);
//        mBottomSheetDialog.show();

        mBtncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
//                finish();
            }
        });
        mBtnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomSheetDialog.dismiss();
            }
        });

    }

    public void Playstoreopen(String name) {
//        Uri uri1 = Uri.parse("https://play.google.com/store/apps/details?id=" + name);
        Uri uri1 = Uri.parse(name);
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri1);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getContext(), "You don't have Google Play installed", Toast.LENGTH_LONG).show();
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

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);
        if (requestCode == SIGN_IN_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(intent);
            handleSignInResult(result);

        }
        if (requestCode == 1000) {

            switch (responseCode) {
                case Activity.RESULT_OK:
                    // All required changes were successfully made
//                    getLocation();
                    break;
                case Activity.RESULT_CANCELED:
                    // The user was asked to change settings, but chose not to
                    Toast.makeText(getContext(), "Location Service not Enabled", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    }


    /*private void signOut() {
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

    }*/



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

    @SuppressLint("WrongConstant")
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

                        /*    callRegisterApi(first_name, last_name, email, mobile_number, gender,
                                    age, height, weight, user_type_id, paid_status, country_id,
                                    state_id, city_id, fcm_token, facebook, google, user_image);*/

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
    }

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
            BASEURL = tinydb.getString(Constants.NewBaseUrl);
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

    public static void Animuser() {

      /*  MaterialShowcaseSequence sequence = new MaterialShowcaseSequence((Activity) context, SHOWCASE_ID);

        ShowcaseConfig config = new ShowcaseConfig();
        config.setDelay(500); // half second between each showcase view


        sequence.setOnItemShownListener(new MaterialShowcaseSequence.OnSequenceItemShownListener() {
            @Override
            public void onShow(MaterialShowcaseView itemView, int position) {
                Toast.makeText(itemView.getContext(), "Item #" + position, Toast.LENGTH_SHORT).show();
            }
        });

        sequence.setConfig(config);*/

        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder((Activity) context)
                        .setTarget(laystore)
                        .setDismissText(context.getResources().getString(R.string.tap_here))
                        .setContentText(context.getResources().getString(R.string.stor_edu))
                        .withCircleShape()
                        .build()
        );
        /*sequence.addSequenceItem(
                new MaterialShowcaseView.Builder((Activity) context)
                        .setSkipText("SKIP")
                        .setTarget(ivtimer)
                        .setDismissText("GOT IT")
                        .setContentText("This is button two")
                        .withRectangleShape(true)
                        .build()
        );*/
        sequence.addSequenceItem(
                new MaterialShowcaseView.Builder((Activity) context)
                        .setTarget(mBtnmenu)
                        .setDismissText(context.getResources().getString(R.string.tap_here))
                        .setContentText(context.getResources().getString(R.string.menu_edu))
                        .withCircleShape()
                        .build()
        );

        // sequence.start();

    }

    public void ShowAds() {
        Constants.admob_openad = context.getResources().getString(R.string.admob_openapp);
        Constants.admob_banner = context.getResources().getString(R.string.admob_banner);
        Constants.admob_Interstitial = context.getResources().getString(R.string.admob_Interstitial);
        Constants.admob_nativead = context.getResources().getString(R.string.admob_nativead);
        Constants.admob_rewardad = context.getResources().getString(R.string.admob_rewardedad);
        Constants.facebook_banner = context.getResources().getString(R.string.facebook_banner);
        Constants.facebook_interstitial = context.getResources().getString(R.string.facebook_interstitial);
        Constants.facebook_native = context.getResources().getString(R.string.facebook_native);
        Constants.facebook_rectangle = context.getResources().getString(R.string.facebook_rectangle);
        Constants.facebooknative_banner = context.getResources().getString(R.string.facebooknative_banner);
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

    public void PurchaseCheck() {
        String LICENSE_KEY = getString(R.string.base64key);
        bp = new BillingProcessor(getContext(), LICENSE_KEY, null, new BillingProcessor.IBillingHandler() {
            @Override
            public void onProductPurchased(@NonNull String productId, @Nullable TransactionDetails details) {
            }

            @Override
            public void onBillingError(int errorCode, @Nullable Throwable error) {
                Log.e(LOG_TAG, "onBillingError: " + Integer.toString(errorCode));
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
                        Log.e(LOG_TAG, "Owned Subscription: " + "Oneyear Subscriptions updated.");
                    }
                    Log.e(LOG_TAG, "Owned Subscription: " + "onetime");
                } else if (bp.isSubscribed(Onemonth1)) {
                    tinydb.putBoolean(Constants.PREMIUN_KEY, true);
                    NoAds();

                    Log.e(LOG_TAG, "Owned Subscription: " + "1 Month");
                    if (bp.loadOwnedPurchasesFromGoogle()) {
                        Log.e(LOG_TAG, "update2: " + bp.loadOwnedPurchasesFromGoogle());
                    }
                } else if (bp.isSubscribed(Oneyear1)) {
                    tinydb.putBoolean(Constants.PREMIUN_KEY, true);
                    NoAds();

                    if (bp.loadOwnedPurchasesFromGoogle()) {
                        Log.e(LOG_TAG, "Owned Subscription: " + "Oneyear Subscriptions updated.");
                    }
                } else {
                    //   String   Lifetime1 = tinydb.getString(Constants.ITEM_SKU1);
                    tinydb.putBoolean(Constants.PREMIUN_KEY, false);
                    ShowAds();
                    bp.consumePurchase(Lifetime1);
                    Log.e(LOG_TAG, "Owned Subscription: " + "No Subscripytions");
                }
            }

            @Override
            public void onPurchaseHistoryRestored() {
               /* if (bp.loadOwnedPurchasesFromGoogle()) {
                    tinydb.putBoolean(Constants.PREMIUN_KEY,true);
                    NoAds();
                }*/
                Log.e(LOG_TAG, "onPurchaseHistoryRestored");
                for (String sku : bp.listOwnedProducts())
                    Log.e(LOG_TAG, "Owned Managed Product: " + sku);
                for (String sku : bp.listOwnedSubscriptions())
                    Log.e(LOG_TAG, "Owned Subscription: " + sku);
            }
        });

    }


    public void getBanner() {
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
                paramObject.put("anum", 22);
                paramObject.put("mode", "banner");


                Call<List<Banner1>> userCall = apiInterface.getbanner(paramObject.toString(), "Bearer " + token);
                userCall.enqueue(new Callback<List<Banner1>>() {
                    @Override
                    public void onResponse(Call<List<Banner1>> call, Response<List<Banner1>> response) {
                        try {
                            mSlideloader.setVisibility(View.VISIBLE);
                            sliderView.setVisibility(View.VISIBLE);
                            Log.e("response", String.valueOf(response));
                            ArrayList<Banner1.Doc> newList = new ArrayList<>();
                            ArrayList<Banner1> banners = (ArrayList<Banner1>) response.body();

                            for (int i = 0; i < banners.size(); i++) {


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
                                    mSlideloader.setVisibility(View.GONE);
                                    sliderView.setVisibility(View.VISIBLE);
                                    Log.e("newList1", String.valueOf(newList));
                                    final SliderNewAdapter adapter = new SliderNewAdapter(getContext(), newList);
                                    sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
                                    sliderView.setSliderAdapter(adapter);
                                    sliderView.setAutoCycle(true);
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
                                }
                            }

                        } catch (Exception e) {
                            Log.e("responsecatch", String.valueOf(e));
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Banner1>> call, Throwable t) {
                        Log.e("responsevbanner", String.valueOf(t));

                        mSlideloader.setVisibility(View.GONE);
                        sliderView.setVisibility(View.GONE);
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
                mSlideloader.setVisibility(View.GONE);
                sliderView.setVisibility(View.GONE);
            }
        } catch (Exception e) {

            mSlideloader.setVisibility(View.GONE);
            sliderView.setVisibility(View.GONE);
        }
    }

    public void DialogLanguage() {
        try {


            Dialog timersdialog = new Dialog(getContext());
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
            Button submit = (Button) timersdialog.findViewById(R.id.submitButton);
            rfrench.setVisibility(View.GONE);
            rchinish.setVisibility(View.GONE);
            rpotugues.setVisibility(View.GONE);
            rrussian.setVisibility(View.GONE);
            aarbic.setVisibility(View.GONE);
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

            } else if (languages.equals(Constants.Portugues)) {
                rpotugues.setChecked(true);

            } else if (languages.equals(Constants.Russian)) {
                rrussian.setChecked(true);

            } else if (languages.equals(Constants.Aarbic)) {
                aarbic.setChecked(true);

            }
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (renglish.isChecked()) {
                        selectedSuperStar = Language.ENGLISH;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        tinydb.putBoolean(Constants.AppLanguage, true);
                        LanguageChangeAPI("english");
                    } else if (rhindi.isChecked()) {
                        selectedSuperStar = Language.HINDI;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        tinydb.putBoolean(Constants.AppLanguage, true);
                        LanguageChangeAPI("hindi");
                    } else if (rfrench.isChecked()) {
                        selectedSuperStar = Language.FRENCH;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                    } else if (rchinish.isChecked()) {
                        selectedSuperStar = Language.CHINESE;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                    } else if (rpotugues.isChecked()) {
                        selectedSuperStar = Language.PORTUGUESE;

                        tinydb.putString(Constants.Language, selectedSuperStar);
                    } else if (rrussian.isChecked()) {
                        selectedSuperStar = Language.RUSSIAN;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                    } else if (aarbic.isChecked()) {
                        selectedSuperStar = Language.ARABIC;
                        tinydb.putString(Constants.Language, selectedSuperStar);

                    }
                    String laun = tinydb.getString(Constants.Language);
                    Log.e("languages", laun);
                    timersdialog.dismiss();
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
            } else if (mealtype.equals("Non-Veg")) {
                tinydb.putInt(Constants.MEALTYPE_KEY, 2);
            } else if (mealtype.equals("Vegan")) {
                tinydb.putInt(Constants.MEALTYPE_KEY, 3);
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
                mHeight = tinydb.getFloat(Constants.HEIGHT_KEY) + " cm";
                mHeightP = "cm";
            } else {
                mHeight = tinydb.getFloat(Constants.HEIGHT_KEY) + " ft";
                mHeightP = "ft";

            }
            String height = mHeight;
            String mWeight;
            String mWeightP;
            if (tinydb.getBoolean(Constants.ISKG_KEY)) {
                mWeight = tinydb.getInt(Constants.WEIGHT_KEY) + " kg";
                mWeightP = "kg";
            } else {
                mWeight = tinydb.getInt(Constants.WEIGHT_KEY) + " lb";
                mWeightP = "lb";
            }
            String weight = mWeight;
            String user_type_id = String.valueOf(tinydb.getInt(Constants.MEALTYPE_KEY));
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

            callRegisterApi(first_name, last_name, email, gender,
                    age, height, weight, country_id,
                    state_id, city_id, fcm_token, google, langauge, mHeightP, mWeightP);

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
              String androidId = 22+Settings.Secure.getString(getContext().getContentResolver(),
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
                    //Toast.makeText(getContext(), t.toString(), Toast.LENGTH_LONG).show();
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
//        settingRequest();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(getContext(), "Connection Failed!", Toast.LENGTH_SHORT).show();
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(getActivity(), 90000);
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
//                        getLocation();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied, but this can be fixed
                        // by showing the user a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(getActivity(), 1000);
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

   /* public void getLocation() {
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        } else {
            *//*Getting the location after aquiring location service*//*
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);

            if (mLastLocation != null) {
                   *//* _progressBar.setVisibility(View.INVISIBLE);
                    _latitude.setText("Latitude: " + String.valueOf(mLastLocation.getLatitude()));
                    _longitude.setText("Longitude: " + String.valueOf(mLastLocation.getLongitude()));*//*
                getCountryName(getContext(), mLastLocation.getLatitude(), mLastLocation.getLongitude());
            } else {
                *//*if there is no last known location. Which means the device has no data for the loction currently.
     * So we will get the current location.
     * For this we'll implement Location Listener and override onLocationChanged*//*
                Log.i("Current Location", "No data for location found");

                if (!mGoogleApiClient.isConnected())
                    mGoogleApiClient.connect();

                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, requireContext());
            }
        }
    }*/

    /*When Location changes, this method get called. */
    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
//            _progressBar.setVisibility(View.INVISIBLE);
//            _latitude.setText("Latitude: " + String.valueOf(mLastLocation.getLatitude()));
//            _longitude.setText("Longitude: " + String.valueOf(mLastLocation.getLongitude()));
        getCountryName(getContext(), mLastLocation.getLatitude(), mLastLocation.getLongitude());

    }

    public String getCountryName(Context context, double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            Address result;

            if (addresses != null && !addresses.isEmpty()) {
                Log.e("getCountryName", addresses.get(0).getCountryName());
                Log.e("getstateName", addresses.get(0).getAdminArea());
                Log.e("getcityName", addresses.get(0).getLocality());

                String country_id = addresses.get(0).getCountryName();
                String state_id = addresses.get(0).getAdminArea();
                String city_id = addresses.get(0).getLocality();

                tinydb.putString(Constants.COUNTRYID_KEY, country_id);
                tinydb.putString(Constants.STATEID_KEY, state_id);
                tinydb.putString(Constants.CITYID_KEY, city_id);


                return addresses.get(0).getCountryName();
            }
            return null;
        } catch (IOException ignored) {
            //do something
        }
        return null;
    }

    /*@Override
    public void onStop() {
        isclicked = false;
        mGoogleApiClient.disconnect();
        super.onStop();
    }*/

    @Override
    public void onResume() {
        super.onResume();
        updatedayoffweek();
        updategoalday();
        UpdateAplanData();
        try {
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


        } catch (Exception e) {

        }
        if (sliderAdapter != null) {
            sliderAdapter.notifyDataSetChanged();
        }
        String totalexe = String.valueOf(tinydb.getInt(Constants.TOTALEXE_KEY));
        mTxtallexe.setText(totalexe);
        mTxtallkcal.setText(String.valueOf(round(tinydb.getFloat(Constants.TOTALKCAL_KEY), 1)));
        long temp = tinydb.getLong(Constants.TOTALTIME_KEY, 0);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(temp);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(temp) % 60;
        mTxtalltime.setText(minutes + ":" + seconds);
        //settingRequest();
    }

    String dayleft1;

    public void UpdateAplanData() {
        mATextapercentage.setVisibility(View.VISIBLE);
        String percentage = tinydb.getInt(Constants.ALLDAYSPROGRESS_KEY) + "%";
        String dayleft = tinydb.getString(Constants.DAYSLEFT_KEY);
        if (dayleft.isEmpty()) {
            dayleft1 = "28 " + getResources().getString(R.string.days_left);
        } else {
            dayleft1 = tinydb.getString(Constants.DAYSLEFT_KEY) + " " +
                    getResources().getString(R.string.days_left);
        }
        mAtxtDaylefts.setText(dayleft1);
        mATextapercentage.setText(percentage);
        mAprogressbar.setProgressBarColor(getResources().getColor(R.color.homeprogresscolor));
        mAprogressbar.setProgress(tinydb.getInt(Constants.ALLDAYSPROGRESS_KEY));

       /*  mATextapercentage1.setVisibility(View.VISIBLE);
        String percentage1 = tinydb.getInt(Constants.ALLDAYSPROGRESS_LV2KEY) + "%";
        String dleft = tinydb.getString(Constants.DAYSLEFT_LV2KEY);
        String dayleft1;
        if (dleft.isEmpty()) {
            dayleft1 = "28 " + getResources().getString(R.string.days_left);
        } else {
            dayleft1 = tinydb.getString(Constants.DAYSLEFT_LV2KEY) + " " +
                    getResources().getString(R.string.days_left);
        }
       mAtxtDaylefts1.setText(dayleft1);
         mATextapercentage1.setText(percentage1);
        mAprogressbar1.setProgress(tinydb.getInt(Constants.ALLDAYSPROGRESS_LV2KEY));
        mAprogressbar1.setProgressBarColor(getResources().getColor(R.color.homeprogresscolor));


        mATextapercentage2.setVisibility(View.VISIBLE);
        String percentage2 = tinydb.getInt(Constants.ALLDAYSPROGRESS_LV3KEY) + "%";
        String dayleft2 = tinydb.getString(Constants.DAYSLEFT_LV3KEY) + " " +
                getResources().getString(R.string.days_left);
         mAtxtDaylefts2.setText(dayleft2);
        mATextapercentage2.setText(percentage2);
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
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage(getResources().getString(R.string.restart_progress));
        alertDialogBuilder.setPositiveButton(getResources().getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        dialog.dismiss();
                        DeleteTables();
                        ClearPrefrences();
                        Intent i = getContext().getPackageManager().getLaunchIntentForPackage(getContext().getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        getActivity().startActivity(i);
                        getActivity().finish();
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
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getActivity().getResources().getColor(R.color.tbtncolor));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getActivity().getResources().getColor(R.color.tbtncolor));

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
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Downlaod this app " + getResources().getString(R.string.app_name) + " and Join over 1M+ community here at: https://play.google.com/store/apps/details?id=" + getContext().getPackageName());
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }


    @Override
    public void onDestroy() {
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



