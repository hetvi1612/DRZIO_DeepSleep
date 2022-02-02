

package drzio.insomnia.treatment.bedtime.yoga.sleep.BottomFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.speech.tts.TextToSpeech;
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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

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
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.data.DataPoint;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.fitness.result.DailyTotalResult;
import com.mannan.translateapi.Language;
import com.yugansh.tyagi.smileyrating.SmileyRatingView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_MyProfile;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Purchase;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_webview;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.AppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.ChooseGenderActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.MaleAppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.BDatabaseOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.DatabaseOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.HistoryProgressOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.MyplanDbhelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.database.DietplanDbhelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Graphdata.GraphdataOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Graphdata.WeightGraphdataOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.ApiInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.RateModel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.Register_Api;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.ReminderMainActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.LoginData;
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

import static com.facebook.FacebookSdk.getApplicationContext;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.isSettingBanner;
import static retrofit2.converter.scalars.ScalarsConverterFactory.create;

public class Fragment_setting extends Fragment implements/* OnDataPointListener,*/ /*NavigationView.OnNavigationItemSelectedListener,*/
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private LinearLayout mBtnsoundop, mBtnreminder, mBtntest,
            mBtnvoicelan, mBtndowntts, mBtndevicetts,
            mBtnlanguages, mBtnreset, mBtnrate,
            mBtnshare, mBtnprivacy;
    public TinyDB tinydb;
    private FitnessApplication fitnessApplication;
    private String[] listItems;
    private String[] applanguages;
    private TextView mTxtvoicelan;
    private TextView mTxtapplan;
    DatabaseOperations databaseOperations;
    BDatabaseOperations databaseOperations2;
    HistoryProgressOperations historyProgressOperations;
    MyplanDbhelper myplanDbhelper;
    DietplanDbhelper dietplanDbhelper;
    String mVoicelan = "Default";
    String mApplan = "Default";
    private LinearLayout mBtnRemoveads;
    private GraphdataOperations graphdataOperations;
    private WeightGraphdataOperations weightGraphdataOperations;
    private String[] feedbacklist;
    private int temprate;
    private Handler mRatehandler;
    private Runnable mRaterunnable;
    private Dialog dialrateexepe;
    private AlertDialog feedbackdial;
    private Dialog dialrateonplay;
    private boolean mSuccess;
    ArrayList<String> mSelectedFeedbackslist = new ArrayList<>();
    private LinearLayout mBtnprofile;
    private BackpainAPIInterface apiInterface;
    Switch mGfitswitch;
    private GoogleApiClient mClient;
    private static final int REQUEST_OAUTH = 1;
    private static final String AUTH_PENDING = "auth_state_pending";
    private boolean authInProgress = false;
    private ImageView mIvdiscover2;
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
    private TextView mTvpurchase;


    //    private NavigationView mNavigationView;
    private final Handler mDrawerHandler = new Handler();
    public static DrawerLayout layout;
    private boolean isClicked = false;

    RadioButton rhindi, rfrench, rchinish, rpotugues, rrussian, aarbic, renglish;
    String selectedSuperStar;

    private ImageView mBtnlogout;
    private TextView mTxtword;
    private LinearLayout mloginlay;
    private LinearLayout mSucceslayout;
    private TextView mProfilename;
    private AvatarImageView mProfilepic;
    private LoginButton loginButton;
    private ImageView signInButton;
    private boolean isFromFb = false;
    private GoogleApiClient mGoogleApiClient;
    private boolean isFromGoogle = false;
    private static final int SIGN_IN_CODE = 9001;
    private GoogleSignInAccount account;
    private String firstName, lastName, email, birthday, gender;
    private URL profilePicture;
    private static final int REQUEST_ACCESS_FINE_LOCATION = 111;

    private CallbackManager callbackManager;
    private String Gusername;
    private String Gmail;
    private String Guserid;
    private String Gprofilepicurl;
    private String GFirstname;
    private String GLastname;
    private String BASEURL;
    private ImageView mBtnmenu;
//    private drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Settings activity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_setting, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        tinydb = new TinyDB(getContext());
        feedbacklist = getResources().getStringArray(R.array.feedlist);
        databaseOperations = new DatabaseOperations(getContext());
        databaseOperations2 = new BDatabaseOperations(getContext());
        graphdataOperations = new GraphdataOperations(getContext());
        weightGraphdataOperations = new WeightGraphdataOperations(getContext());
        historyProgressOperations = new HistoryProgressOperations(getContext());
        myplanDbhelper = new MyplanDbhelper(getContext());
        dietplanDbhelper = new DietplanDbhelper(getContext());
        fitnessApplication = FitnessApplication.getInstance();
        mBtnprofile = (LinearLayout) view.findViewById(R.id.btmyprofile);
        mBtnsoundop = (LinearLayout) view.findViewById(R.id.btsoundop);
        mBtnreminder = (LinearLayout) view.findViewById(R.id.btreminder);
        mBtntest = (LinearLayout) view.findViewById(R.id.bttest);
        mBtnvoicelan = (LinearLayout) view.findViewById(R.id.btnvoicelan);
        mBtndowntts = (LinearLayout) view.findViewById(R.id.btndowntts);
        mBtndevicetts = (LinearLayout) view.findViewById(R.id.btndevicetts);
        mBtnlanguages = (LinearLayout) view.findViewById(R.id.btnlanguage);
        mBtnreset = (LinearLayout) view.findViewById(R.id.btnreset);
        mBtnRemoveads = (LinearLayout) view.findViewById(R.id.btnremoveads);
        mBtnrate = (LinearLayout) view.findViewById(R.id.btnrate);
        mBtnshare = (LinearLayout) view.findViewById(R.id.btnshare);
        mBtnprivacy = (LinearLayout) view.findViewById(R.id.btnprivacy);
        listItems = getResources().getStringArray(R.array.voicelanguage);
        applanguages = getResources().getStringArray(R.array.applanguage);
        mTxtvoicelan = (TextView) view.findViewById(R.id.txtvoicelan);
        mTxtapplan = (TextView) view.findViewById(R.id.txtapplan);
        mTxtvoicelan.setText(mVoicelan);
        mTxtapplan.setText(mApplan);
        BASEURL = tinydb.getString(Constants.NewBaseUrl);
        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(getContext(), languages);

//        mNavigationView = (NavigationView) view.findViewById(R.id.navigation_view);
//        layout = view.findViewById(R.id.drawer);
//        mNavigationView.setNavigationItemSelectedListener(getContext());
        feedbacklist = getResources().getStringArray(R.array.feedlist);
        databaseOperations = new DatabaseOperations(getContext());
        databaseOperations2 = new BDatabaseOperations(getContext());
        graphdataOperations = new GraphdataOperations(getContext());
        weightGraphdataOperations = new WeightGraphdataOperations(getContext());
        historyProgressOperations = new HistoryProgressOperations(getContext());
        myplanDbhelper = new MyplanDbhelper(getContext());
        dietplanDbhelper = new DietplanDbhelper(getContext());
        mGfitswitch = (Switch) view.findViewById(R.id.fitswitch);
        if (tinydb.getBoolean(Constants.GFITON_KEY)) {
            mGfitswitch.setChecked(true);
        } else {
            mGfitswitch.setChecked(false);
        }
        RelativeLayout banner1 = (RelativeLayout) view.findViewById(R.id.banner1);
        FrameLayout mGbannerlay2 = view.findViewById(R.id.adframe1234);
        mGbannerlay2.setVisibility(View.GONE);
        //   showBanner(getContext(), mGbannerlay2, banner1);

       /* if (mClient!=null) {
            if (mClient.isConnected()) {
                mGfitswitch.setChecked(true);
            }
        }else {
            mGfitswitch.setChecked(false);
        }
*/

        mBtnmenu = (ImageView) view.findViewById(R.id.btnback);
        mBtnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.layout.openDrawer(Gravity.START);
            }
        });
        mGfitswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    Googlefit();
                    if (mClient.isConnected()) {

                        Log.e("TAG", "Googlefit connecteddd");
                        tinydb.putBoolean(Constants.GFITON_KEY, true);
                    } else {
                        tinydb.putBoolean(Constants.GFITON_KEY, false);
                    }
                   /* if (hasRuntimePermissions()) {
                        insertAndVerifySessionWrapper();
                    } else {
                        requestRuntimePermissions();
                    }*/
                } else {

                    disableGoogleFit();
                   /* FitnessOptions fitnessOptions = FitnessOptions.builder()
                            .addDataType(DataType.TYPE_BASAL_METABOLIC_RATE,FitnessOptions.ACCESS_WRITE)
                            .build();

                    Fitness.getConfigClient(getApplicationContext(), GoogleSignIn.getAccountForExtension(getApplicationContext(), fitnessOptions))
                            .disableFit()
                            .addOnSuccessListener(unused ->
                                    Log.i("TAG", "Disabled Google Fit"))
                            .addOnFailureListener(e ->
                                    Log.w("TAG", "There was an error disabling Google Fit", e));*/
                 /*   if (mClient != null) {
                        if (!mClient.isConnected()) {
                            Log.e("TAG", "Google Fit wasn't connected");
                            return;
                        }
                        else if (mClient.isConnected()) {
                            mClient.disconnect();
                            Toast.makeText(Activity_Settings.getContext(),"Google Fit is Successfully Disconnected",Toast.LENGTH_LONG).show();

                            tinydb.putBoolean(Constants.GFITON_KEY, false);
                            Log.e("TAG", "Google Fit was connected");
                        }
                    }*/

                    tinydb.putBoolean(Constants.GFITON_KEY, false);
                }
            }
        });
        ImageView mBtnstore = (ImageView) view.findViewById(R.id.ivbtnstore);
        mBtnstore.setBackgroundResource(R.drawable.adsstoreanim);
        AnimationDrawable anim2 = (AnimationDrawable) mBtnstore.getBackground();
        anim2.start();
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
        ShimmerFrameLayout mLoadlay = view.findViewById(R.id.shimmer_container);
       /* LinearLayout mGbannerlay = view.findViewById(R.id.adframe);
        FrameLayout mAdframe4 = (FrameLayout) view.findViewById(R.id.adframe4);
        FitnessApplication.isAdLoader(mLoadlay);
        if (isSettingBanner) {
            mGbannerlay.setVisibility(View.GONE);
            showBanner(Activity_Settings.getContext(), mAdframe4, mLoadlay);
        } else {
            mAdframe4.setVisibility(View.GONE);
            Smallnative(getContext(), mGbannerlay, mLoadlay);
        }*/
        TextView mVersionname = (TextView) view.findViewById(R.id.txtversion);
        mVersionname.setText("Version " + BuildConfig.VERSION_NAME);
        if (!tinydb.getBoolean(Constants.PREMIUN_KEY)) {
            mBtnRemoveads.setVisibility(View.VISIBLE);
        } else {
            mBtnRemoveads.setVisibility(View.GONE);
        }
       /* ImageView mBtnback = (ImageView) view.findViewById(R.id.btnback);
        mBtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.openDrawer(Gravity.START);
            }
        });*/
        clickevents();
//        NavigationData();
//        InitLoginlayouts(view);
//        initbottomnavigation();

        return view;
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


    /*public void initbottomnavigation() {
        mIvdiscover2 = (ImageView) view.findViewById(R.id.ivdiscover2);
        mIvProgress2 = (ImageView) view.findViewById(R.id.ivprogress2);
        mIvhome2 = (ImageView) view.findViewById(R.id.ivhome2);
        mIvEarn2 = (ImageView) view.findViewById(R.id.ivearn2);
        mIvPurchase2 = (ImageView) view.findViewById(R.id.ivpurchase2);
        mIvdiscover3 = (ImageView) view.findViewById(R.id.ivdiscover3);
        mIvProgress3 = (ImageView) view.findViewById(R.id.ivprogress3);
        mIvhome3 = (ImageView) view.findViewById(R.id.ivhome3);
        mIvEarn3 = (ImageView) view.findViewById(R.id.ivearn3);
        mIvPurchase3 = (ImageView) view.findViewById(R.id.ivpurchase3);
        LinearLayout mLaydiscover = (LinearLayout) view.findViewById(R.id.laydiscover);
        LinearLayout mLayprogress = (LinearLayout) view.findViewById(R.id.layprogress);
        LinearLayout mLayhome = (LinearLayout) view.findViewById(R.id.layhome);
        LinearLayout mLayearn = (LinearLayout) view.findViewById(R.id.layearn);
        LinearLayout mLaypurchase = (LinearLayout) view.findViewById(R.id.laypurchase);
        mTvdiscover = (TextView) view.findViewById(R.id.tvdiscover);
        mTvprogress = (TextView) view.findViewById(R.id.tvprogress);
        mTvearn = (TextView) view.findViewById(R.id.tvearn);
        mTvpurchase = (TextView) view.findViewById(R.id.tvpurchase);
        mTvdiscover.setTextColor(getResources().getColor(R.color.navunselected));
        mTvprogress.setTextColor(getResources().getColor(R.color.navunselected));
        mTvearn.setTextColor(getResources().getColor(R.color.tbtncolor));
        mTvpurchase.setTextColor(getResources().getColor(R.color.navunselected));
        mIvhome3.setColorFilter(getResources().getColor(R.color.grey));
        mIvdiscover3.setColorFilter(getResources().getColor(R.color.grey));
        mIvProgress3.setColorFilter(getResources().getColor(R.color.grey));
        mIvEarn3.setColorFilter(getResources().getColor(R.color.tbtncolor));
        mIvPurchase3.setColorFilter(getResources().getColor(R.color.grey));
        mLaydiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activity_Discover.class);


                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
                finish();
            }
        });
        mLayprogress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activity_Bloglists.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
                finish();
            }
        });
        mLayhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainAct.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
                finish();
            }
        });
        mLayearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mLaypurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activity_Reports.class);


                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
                finish();
            }
        });
    }*/


    /*public void NavigationData() {
        LinearLayout mLaymt = (LinearLayout) view.findViewById(R.id.llaymt);
        LinearLayout mLaynp = (LinearLayout) view.findViewById(R.id.llaynp);
        LinearLayout mLaytip = (LinearLayout) view.findViewById(R.id.llaytip);
        LinearLayout mLayreport = (LinearLayout) view.findViewById(R.id.llayreport);
        LinearLayout mLaycovid = (LinearLayout) view.findViewById(R.id.llaycovid);
        LinearLayout mLaydiscover = (LinearLayout) view.findViewById(R.id.llaydiscover);
        LinearLayout mLaycustom = (LinearLayout) view.findViewById(R.id.llaycustom);
        LinearLayout mLayra = (LinearLayout) view.findViewById(R.id.llayra);
        LinearLayout mLaystore = (LinearLayout) view.findViewById(R.id.llayas);
        LinearLayout mLayreminder = (LinearLayout) view.findViewById(R.id.llayremind);
        LinearLayout mLaylang = (LinearLayout) view.findViewById(R.id.llaylo);
        LinearLayout mLaysetting = (LinearLayout) view.findViewById(R.id.llaysetting);
        LinearLayout mLayrate = (LinearLayout) view.findViewById(R.id.llayrate);
        LinearLayout mLayshare = (LinearLayout) view.findViewById(R.id.llayshare);
        LinearLayout mLayrestart = (LinearLayout) view.findViewById(R.id.llayrp);
        //  LinearLayout mBtncovid = (LinearLayout) view.findViewById(R.id.btncovid);
        if (tinydb.getBoolean(Constants.RATEDIALOGSHOW_KEY)) {
            mLayrate.setVisibility(View.GONE);
        }else {
            mLayrate.setVisibility(View.VISIBLE);
        }
       *//* mBtncovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(Activity_Reports.getContext(), 14);
            }
        });*//*
        if (tinydb.getBoolean(Constants.PREMIUN_KEY)) {
            mLayra.setVisibility(View.GONE);
        }
        mLaymt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(getContext(), 0);
            }
        });
        mLaynp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(getContext(), 1);
            }
        });
        mLaytip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(getContext(), 2);
            }
        });
        mLayreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(getContext(), 3);
            }
        });
        mLaycovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(getContext(), 14);
            }
        });
        mLaydiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(getContext(), 4);

            }
        });
        mLaycustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(getContext(), 5);
            }
        });
        mLayra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(getContext(), 6);

            }
        });
        mLaystore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(getContext(), 7);

            }
        });
        mLayreminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(getContext(), 8);

            }
        });
        mLaylang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(getContext(), 9);

            }
        });
        mLaysetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(getContext(), 10);

            }
        });
        mLayrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(getContext(), 11);

            }
        });
        mLayshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(getContext(), 12);

            }
        });
        mLayrestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(getContext(), 13);

            }
        });
    }*/

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (feedbackdial != null && feedbackdial.isShowing()) {
            feedbackdial.dismiss();
        }
        if (dialrateexepe != null && dialrateexepe.isShowing()) {
            dialrateexepe.dismiss();
        }
    }
   /* public void click(Context context, int i) {
        if (i == 1) {
            String tempdate = tinydb.getString(Constants.DIETPLANDATE_KEY);
            Date c = Calendar.getInstance().getTime();
             Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
            String formattedDate = df.format(c);
            if (!tempdate.isEmpty() && tempdate.equals(formattedDate)) {
                Intent intent1 = new Intent(context, Activity_Userdietlist.class);
                intent1.putExtra("isFrom2", false);
                context.startActivity(intent1);
            } else {
                Intent intent = new Intent(context, Activity_Dietplans.class);
                intent.putExtra("isFrom", false);
                context.startActivity(intent);
            }
        } else if (i == 2) {
            if (!isClicked) {
                isClicked = true;
                Intent intent = new Intent(context, Activity_Bloglists.class);
                context.startActivity(intent);
//                finish();
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
                    Intent intent = new Intent(context, AppstoreActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(context, MaleAppstoreActivity.class);
                    startActivity(intent);
                }
            } else {
                Intent intent = new Intent(context, ChooseGenderActivity.class);
                startActivity(intent);
            }
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
            Intent intent = new Intent(context, drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Settings.class);
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
                Intent intent5 = new Intent(getContext(), Activity_Covidhome.class);
                intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent5);
                finish();
            }
        }
    }*/


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
                        //  LanguageChangeAPI("hindi");
                    } else if (rhindi.isChecked()) {
                        selectedSuperStar = Language.HINDI;
                        tinydb.putString(Constants.Language, selectedSuperStar);
                        tinydb.putBoolean(Constants.AppLanguage, true);
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

    /*public void InitLoginlayouts(View view) {
        mBtnlogout = (ImageView) view.findViewById(R.id.btn_logout);
        mTxtword = (TextView) view.findViewById(R.id.txtword);
        mloginlay = (LinearLayout) view.findViewById(R.id.loginlayout);
        mSucceslayout = (LinearLayout) view.findViewById(R.id.successlayout);
        mProfilename = (TextView) view.findViewById(R.id.profilename);
        mProfilepic = (AvatarImageView) view.findViewById(R.id.profile_image);
        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        signInButton = (ImageView) view.findViewById(R.id.sign_in_button);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .enableAutoManage(getActivity(), 1,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addApi(LocationServices.API)
                .build();

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hasPermissionLocation = (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
                if (!hasPermissionLocation) {
                    ActivityCompat.requestPermissions(getActivity(),
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
                if (isFromFb) {
                    logout();
                } else {
                    signOut();
                }
            }
        });


    }*/

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
                    Toast.makeText(getContext(), "The app was not allowed to get your location, Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }
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

    @Override
    public void onResume() {
        super.onResume();
        /*if (tinydb.getBoolean(Constants.ISLOGIN_KEY)) {
            try {
                String token = FirebaseInstanceId.getInstance().getToken();
                //     SendToken(token);
                OptionalPendingResult<GoogleSignInResult> optPenRes = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
                if (optPenRes.isDone()) {
                    Log.e("Login_Result", "Yayy!");
                    GoogleSignInResult result = optPenRes.get();
                    handleSignInResult(result);
                } else {
                    optPenRes.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                        @Override
                        public void onResult(@NotNull GoogleSignInResult googleSignInResult) {
                            handleSignInResult(googleSignInResult);
                        }
                    });
                }
            } catch (Exception e0) {
            }
        }*/
    }

    private void logout() {
        LoginManager.getInstance().logOut();
        updateUI(false);
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


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, SIGN_IN_CODE);

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
                    //   getLocation();
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

    private void handleSignInResult(GoogleSignInResult result) {

        Log.e("TAG", "handleSignInResult:" + result.isSuccess());
        if (result.getStatus().getStatusCode() == 7) {
            updateUI(true);
        }
        if (result.isSuccess()) {
            //     DialogPageLoading();
            account = result.getSignInAccount();
            Gusername = account.getDisplayName();
            Gmail = account.getEmail();
            Guserid = account.getId();
            Gprofilepicurl = String.valueOf(account.getPhotoUrl());
            GFirstname = account.getGivenName();
            GLastname = account.getFamilyName();
            boolean mIslogedin = tinydb.getBoolean(Constants.ISLOGIN_KEY);


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
            String user_type_id = String.valueOf(tinydb.getInt(Constants.MEALTYPE_KEY));
            String paid_status = "0";
            String country_id = tinydb.getString(Constants.COUNTRYID_KEY);
            //       Log.e("coutry",country_id);
            String langauge = Resources.getSystem().getConfiguration().locale.getLanguage();
            Log.e("getDisplayLanguage", langauge);

            if (langauge.equals("hi")) {
                // mTinydb.putString(Constants.Language, "hi");
                // Constants.languagechange(getActivity(), "hi");
                langauge = "hindi";
            } else {
                // mTinydb.putString(Constants.Language, "en");
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

            callRegisterApi(first_name, last_name, email, gender,
                    age, height, weight, country_id,
                    state_id, city_id, fcm_token, google, langauge, mHeightP, mWeightP);

            // NextActivity();

          /*  if (!mIslogedin) {
                if (Gmail != null) {
                    String token = tinydb.getString(Constants.FCMTOKEN_KEY);
                    callLoginApi(Gmail, "", Guserid, token);
                }
            }*/
            updateUI(true);
            updatedata(Gusername, Gprofilepicurl);
        } else {
            updateUI(false);
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

                           /* callRegisterApi(first_name, last_name, email, mobile_number, gender,
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

    @SuppressLint("WrongConstant")
    public void callRegisterApi(String first_name, String last_name,
                                String email,
                                String gender, String age, String height,
                                String weight, String country_id, String state_id, String city_id,
                                String fcm_token, String google, String language, String heightp, String weightp) {
        /*try {
            String androidId = Settings.Secure.getString(getContentResolver(),
                    Settings.Secure.ANDROID_ID);
            this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient().create(BackpainAPIInterface.class);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("first_name", first_name)
                    .addFormDataPart("last_name", last_name)
                    .addFormDataPart("email", email)
                    .addFormDataPart("gender", gender)
                    .addFormDataPart("age", age)
                    .addFormDataPart("height", height)
                    .addFormDataPart("weight", weight)
                    .addFormDataPart("country", country_id)
                    .addFormDataPart("state", state_id)
                    .addFormDataPart("city", city_id)
                    .addFormDataPart("fcm_token", fcm_token)
                    .addFormDataPart("google", google)
                    .addFormDataPart("language", language)
                    .addFormDataPart("device_id", androidId)
                    .addFormDataPart("heightP", heightp)
                    .addFormDataPart("weightP", weightp)
                    .addFormDataPart("app_number", String.valueOf(3))


                    .build();
            this.apiInterface.sendUser(requestBody).enqueue(new Callback<RegisterData>() {
                @Override
                public void onResponse(@NotNull Call<RegisterData> call, @NotNull Response<RegisterData> response) {
                    try {
                        RegisterData dietCateData = (RegisterData) response.body();
                        RegisterData.Datalist dataist = dietCateData.dataist;
                        Log.e("registerstatus", dietCateData.status);

                        if (dietCateData.status.equals("true")) {
                            tinydb.putString(Constants.USERID_KEY, dataist.getId());
                            tinydb.putString(Constants.USEREMAIL_KEY, email);
                            tinydb.putString(Constants.USERFIRSTNAME_KEY, first_name);
                            tinydb.putString(Constants.USERLASTNAME_KEY, last_name);
                            NextActivity();
                        } else {
                            NextActivity();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        NextActivity();
                    }
                }

                @Override
                public void onFailure(@NotNull Call<RegisterData> call, @NotNull Throwable t) {
                    //  Log.e("registerfail",t.getMessage());
                    NextActivity();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }*/


        try {
            String getusertype=tinydb.getString(Constants.USERTYPEKEY);

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


            paramObject.put("user_type",getusertype);
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

                        Log.e("getfName", String.valueOf(docs.getfName()));
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
   /* @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }*/


    private void Googlefit() {
        mClient = new GoogleApiClient.Builder(getContext())
                .addApi(Fitness.HISTORY_API)
                .addApi(Fitness.CONFIG_API)
                .addScope(new Scope(Scopes.FITNESS_ACTIVITY_READ))
                .useDefaultAccount()
                .addConnectionCallbacks(
                        new GoogleApiClient.ConnectionCallbacks() {

                            @Override
                            public void onConnected(Bundle bundle) {
                                tinydb.putBoolean(Constants.GFITON_KEY, true);
                                mGfitswitch.setChecked(true);
                                //Async To fetch steps
                                new FetchStepsAsync().execute();
                                new ViewWeekStepCountTask().execute();
                                Toast.makeText(getApplicationContext(), "Google Fit is Successfully Connected", Toast.LENGTH_LONG).show();
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
                )/*.addOnConnectionFailedListener(
                        new GoogleApiClient.OnConnectionFailedListener() {
                            // Called whenever the API client fails to connect.
                            @Override
                            public void onConnectionFailed(ConnectionResult result) {
                                Log.i("TAG", "Connection failedd. Cause: " + result.toString());
                                if (!result.hasResolution()) {
                                    // Show the localized error dialog
                                    mGfitswitch.setChecked(false);
                                    GooglePlayServicesUtil.getErrorDialog(result.getErrorCode(),
                                            Activity_Settings.this, 0).show();
                                    return;
                                }
                                // The failure has a resolution. Resolve it.
                                // Called typically when the app is not yet authorized, and an
                                // authorization dialog is displayed to the user.
                                if (!authInProgress) {
                                    try {
                                        Log.i("TAG", "Attempting to resolve failed connectionsss");
                                        // mGfitswitch.setChecked(false);
                                        authInProgress = true;
                                        result.startResolutionForResult(Activity_Settings.this, REQUEST_OAUTH);
                                    } catch (IntentSender.SendIntentException e) {
                                        Log.e("TAG",
                                                "Exception while starting resolution activity", e);
                                    }
                                }
                            }
                        }
                )*/

                .enableAutoManage(getActivity(), 1, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(ConnectionResult result) {
                        Log.i("TAG", "Google Play services connection failed. Cause: " +
                                result.toString());
                        Toast.makeText(getContext(),
                                /*result.getErrorCode() +*/ "Exception while connecting to Google Play services, might be because you didn't allow access",
                                Toast.LENGTH_LONG).show();
                        Intent restartActivity = new Intent(getContext(), MainActivity.class);
                        Bundle data1 = new Bundle();
                        data1.putInt("discover", 5);
                        restartActivity.putExtras(data1);
                        startActivity(restartActivity);
//                        finish();
                        tinydb.putBoolean(Constants.GFITON_KEY, false);
                        mGfitswitch.setChecked(true);
                    }
                })


                .build();
        mClient.connect();

    }

    public void disableGoogleFit() {
        if (mClient != null) {
            if (!mClient.isConnected()) {
                Log.e("TAG", "Google Fit wasn't connected");
                return;
            }

            PendingResult<Status> pendingResult = Fitness.ConfigApi.disableFit(mClient);

            pendingResult.setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(Status status) {
                    if (status.isSuccess()) {
                        Log.e("TAG", "Google Fit disabled");
                        tinydb.putBoolean(Constants.GFITON_KEY, false);
                        Toast.makeText(getContext(), "Google Fit is Successfully Disconnected", Toast.LENGTH_LONG).show();
                    } else {
                        Log.e("TAG", "Google Fit wasn't disabled " + status);
                        tinydb.putBoolean(Constants.GFITON_KEY, true);
                        Toast.makeText(getContext(), "Error!!! \nGoogle Fit is not Disconnected", Toast.LENGTH_LONG).show();

                    }
                }
            });
        }
    }

  /*  @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }*/

    private class ViewWeekStepCountTask extends AsyncTask<Void, Void, Void> {
        protected Void doInBackground(Void... params) {
            displayLastWeeksData();
            return null;
        }
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

// Create a data source
        DataSource dataSource = new DataSource.Builder()
                .setAppPackageName(getContext())
                .setDataType(DataType.TYPE_BASAL_METABOLIC_RATE)
                .setStreamName("TAG" + " - step count")
                .setType(DataSource.TYPE_RAW)
                .build();

// Create a data set
        float calories = 0.1f;
        DataSet dataSet = DataSet.create(dataSource);
// For each data point, specify a start time, end time, and the data value -- in this case,
// the number of new steps.
        DataPoint dataPoint = dataSet.createDataPoint()
                .setTimeInterval(startTime, endTime, TimeUnit.MILLISECONDS);
        dataPoint.getValue(Field.FIELD_CALORIES).setFloat(calories);
        dataSet.add(dataPoint);
        Log.i("TAG", "Inserting the dataset in the History API.");
        Status insertStatus =
                Fitness.HistoryApi.insertData(mClient, dataSet)
                        .await(1000, TimeUnit.MINUTES);

// Before querying the data, check to see if the insertion succeeded.
        if (!insertStatus.isSuccess()) {
//            mGfitswitch.setChecked(false);
            //tinydb.putBoolean(GFITON_KEY,false);

            Log.i("TAG", "There was a problem inserting the dataset.");
        } else {

            // mGfitswitch.setChecked(true);
            // tinydb.putBoolean(GFITON_KEY,true);
            Log.i("TAG", "Data insert was successful!");
            new FetchCalorieAsync().execute();
        }

    }


    @Override
    public void onConnectionSuspended(int i) {
        mGfitswitch.setChecked(false);
        tinydb.putBoolean(Constants.GFITON_KEY, false);

        Log.e("HistoryAPI", "onConnectionSuspended");
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        mGfitswitch.setChecked(false);
        tinydb.putBoolean(Constants.GFITON_KEY, false);

        Log.e("HistoryAPI", "onConnectionFailed");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Log.e("HistoryAPI", "onConnected");
        mGfitswitch.setChecked(true);
        tinydb.putBoolean(Constants.GFITON_KEY, true);

        displayLastWeeksData();
    }


    private class FetchCalorieAsync extends AsyncTask<Object, Object, Float> {
        protected Float doInBackground(Object... params) {
            float total = 0;
            PendingResult<DailyTotalResult> result = Fitness.HistoryApi.readDailyTotal(mClient, DataType.TYPE_CALORIES_EXPENDED);
            DailyTotalResult totalResult = result.await(30, TimeUnit.SECONDS);
            if (totalResult.getStatus().isSuccess()) {
                DataSet totalSet = totalResult.getTotal();
                if (totalSet != null) {
                    total = totalSet.isEmpty()
                            ? 0
                            : totalSet.getDataPoints().get(0).getValue(Field.FIELD_CALORIES).asFloat();
                }
            } else {
                Log.w("TAG", "There was a problem getting the step count.");
            }
            return total;
        }


        @Override
        protected void onPostExecute(Float aLong) {
            super.onPostExecute(aLong);

            //Total steps covered for that day
            Log.i("TAG", "Total steps: " + aLong);

        }
    }

    private class FetchStepsAsync extends AsyncTask<Object, Object, Long> {
        protected Long doInBackground(Object... params) {
            long total = 0;
            PendingResult<DailyTotalResult> result = Fitness.HistoryApi.readDailyTotal(mClient, DataType.TYPE_STEP_COUNT_DELTA);
            DailyTotalResult totalResult = result.await(30, TimeUnit.SECONDS);
            if (totalResult.getStatus().isSuccess()) {
                DataSet totalSet = totalResult.getTotal();
                if (totalSet != null) {
                    total = totalSet.isEmpty()
                            ? 0
                            : totalSet.getDataPoints().get(0).getValue(Field.FIELD_STEPS).asInt();
                }
            } else {
                Log.w("TAG", "There was a problem getting the step count.");
            }
            return total;
        }


        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);

            //Total steps covered for that day
            Log.i("TAG", "Total steps: " + aLong);

        }
    }


    public void showBanner(Context context, final FrameLayout adMobView, ShimmerFrameLayout loadlayout) {
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
                    adMobView.setVisibility(View.VISIBLE);
                    isSettingBanner = false;
                }

                @Override
                public void onAdFailedToLoad(int i) {
                    loadlayout.setVisibility(View.GONE);
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

    public void clickevents() {
        mBtnprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_MyProfile.class);
                startActivity(intent);
            }
        });
        mBtnsoundop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogVoicesettings();
            }
        });
        mBtnreminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ReminderMainActivity.class);
                startActivity(intent);
            }
        });
        mBtntest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Testvoicedialog();
            }
        });
        mBtnvoicelan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogVoicelanguage();
            }
        });
        mBtndowntts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ttsIntent = new Intent();
                ttsIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                ttsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(ttsIntent);
            }
        });
        mBtndevicetts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent();
                    intent.setAction("com.android.settings.TTS_SETTINGS");
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Oops! The function is not available in your device.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mBtnlanguages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogApplanguage();
            }
        });
        mBtnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resetdialog();
            }
        });
        mBtnRemoveads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_Purchase.class);
                startActivity(intent);
            }
        });
        mBtnrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogRatexeperience();
            }
        });
        mBtnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareApp(getContext());
            }
        });
        mBtnprivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_webview.class);
                intent.putExtra("link", "http://drzio.com/privacy-policy/");
                startActivity(intent);
            }
        });

    }

    public void Rateus() {
        Uri uri1 = Uri.parse("https://play.google.com/store/apps/details?id=" + getContext().getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri1);
        try {
            startActivity(myAppLinkToMarket);
            mBtnrate.setVisibility(View.GONE);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getContext(), "You don't have Google Play installed", Toast.LENGTH_LONG).show();
        }
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
                        Intent i = getActivity().getBaseContext().getPackageManager().getLaunchIntentForPackage(getActivity().getBaseContext().getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
//                        finish();
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
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.tbtncolor));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.tbtncolor));

            }
        });
        alertDialog.show();
    }


    public void Testvoicedialog() {
        if (!fitnessApplication.isSpeaking()) {
            fitnessApplication.speak(getResources().getString(R.string.did_you_hear_the_test_voice));
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage(getResources().getString(R.string.did_you_hear_the_test_voice));
        alertDialogBuilder.setPositiveButton(getResources().getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        dialog.dismiss();
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
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(getResources().getColor(R.color.themegreen));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(getResources().getColor(R.color.themegreen));

            }
        });
        alertDialog.show();
    }

    public void DialogApplanguage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.MaterialThemeDialog);
        int checkedItem = 0;
        builder.setSingleChoiceItems(applanguages, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mApplan = applanguages[which];
                mTxtapplan.setText(mApplan);
                Toast.makeText(getContext(), "Position: " + which + " Value: " + applanguages[which], Toast.LENGTH_LONG).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                }, 500);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void DialogVoicelanguage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.MaterialThemeDialog);
//        builder.setTitle("Choose Language");

        int checkedItem = 0;
        builder.setSingleChoiceItems(listItems, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                    }
                }, 500);
                mVoicelan = listItems[which];
                mTxtvoicelan.setText(mVoicelan);
                Toast.makeText(getContext(), "Position: " + which + " Value: " + listItems[which], Toast.LENGTH_LONG).show();
            }
        });
//
//        builder.setPositiveButton("Done", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//            }
//        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void DialogVoicesettings() {
        final Dialog dialog = new Dialog(getContext());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_mutevoice);
        final Switch mVoicemute = (Switch) dialog.findViewById(R.id.switchmute);
        final Switch mGuidevoice = (Switch) dialog.findViewById(R.id.switchguidevoice);
        final Switch mTipsvoice = (Switch) dialog.findViewById(R.id.switchtipsvoice);
        TextView mClosedial = (TextView) dialog.findViewById(R.id.txtdialok);
        mClosedial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
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
                } else {
                    mVoicemute.setChecked(false);
                    mGuidevoice.setChecked(true);
                    mTipsvoice.setChecked(true);
                    tinydb.putBoolean(Constants.ISMUTEON_KEY, false);
                    tinydb.putBoolean(Constants.ISGUIDEON_KEY, true);
                    tinydb.putBoolean(Constants.ISTIPSON_KEY, true);
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


  /*  public void DialogRatexeperience() {
        dialrateexepe = new Dialog(getContext());
        dialrateexepe.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialrateexepe.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialrateexepe.setContentView(R.layout.dialog_rateexperience);
        TextView mRatebtn = (TextView) dialrateexepe.findViewById(R.id.btnok);
        TextView mClosebtn = (TextView) dialrateexepe.findViewById(R.id.btnclose);
        TextView mTextexpe = (TextView) dialrateexepe.findViewById(R.id.txtdesx);
        SmileyRatingView smileyRating = dialrateexepe.findViewById(R.id.smiley_view);
        RatingBar ratingBar = dialrateexepe.findViewById(R.id.rating_bar);
        ratingBar.setRating(0);
        mTextexpe.setText(getResources().getString(R.string.do_you_like_this_app));
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
                        mTextexpe.setText(getResources().getString(R.string.loved_it));
                        break;
                }
            }
        });
        mRatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temprate == 3 || temprate == 4) {
                    dialrateexepe.dismiss();
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
                dialrateexepe.dismiss();
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialrateexepe.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialrateexepe.show();
        dialrateexepe.getWindow().setAttributes(lp);
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
                Rateus();
            }
        });
        mTxtcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialrateonplay.dismiss();
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

                String device_name = Build.BRAND + ", " + Build.DEVICE + ", " + Build.MANUFACTURER + ", " + BuildConfig.VERSION_NAME;
                String androidId = 22+Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID)+22;
                String email = tinydb.getString(Constants.USEREMAIL_KEY);
                String release = Build.VERSION.RELEASE;
                mBtnrate.setVisibility(View.GONE);
                callFeedbackApi(androidId, device_name, release, email, mOp1, mOp2, mOp3, mOp4, mOp5,mComment/*, mComment, mUid*/);
                // callFeedbackApi(mOp1, mOp2, mOp3, mOp4, mOp5, mComment, mUid);
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {

            }
        });
        feedbackdial = builder.create();
        feedbackdial.show();
    }


    /* @SuppressLint("WrongConstant")
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
                         tinydb.putBoolean(Constants.RATEDIALOGSHOW_KEY, true);
                         Toast.makeText(getContext(), getResources().getString(R.string.feedback_submitted), Toast.LENGTH_SHORT).show();
                     }catch (Exception e){

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

    public static void Smallnative(final Context context, final LinearLayout frameLayout, ShimmerFrameLayout loadlayout) {
        AdLoader.Builder builder = new AdLoader.Builder(context, Constants.admob_nativead);
        builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
            public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                loadlayout.setVisibility(View.GONE);
                frameLayout.setVisibility(View.VISIBLE);
                NativeContentAdView nativeContentAdView = (NativeContentAdView) ((Activity) context).getLayoutInflater().inflate(R.layout.ad_native_banner, null);
                populateSmallContentAdView(nativeContentAd, nativeContentAdView);
                frameLayout.removeAllViews();
                frameLayout.addView(nativeContentAdView);
                isSettingBanner = true;
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(int i) {
                loadlayout.setVisibility(View.GONE);
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
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
  /*  @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }*/
}
