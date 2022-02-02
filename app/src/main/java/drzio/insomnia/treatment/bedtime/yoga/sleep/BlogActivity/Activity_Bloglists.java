package drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
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
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
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
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.CallbackManager;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
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
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.iid.FirebaseInstanceId;
import com.makeramen.roundedimageview.RoundedImageView;
import com.mannan.translateapi.Language;
import com.yugansh.tyagi.smileyrating.SmileyRatingView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_MyTraining;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Purchase;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Reports;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Settings;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.AppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.ChooseGenderActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.MaleAppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity.model.BlogList;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.BDatabaseOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.DatabaseOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.HistoryProgressOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.MyplanDbhelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Diet.CustomUpdateActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Diet.DefalutAndCustomplanActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.database.DietplanDbhelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Discoverpage.Activity_Discover;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Graphdata.GraphdataOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Graphdata.WeightGraphdataOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.ApiInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.RateModel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.Register_Api;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.ReminderMainActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.Activity_Covidhome;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIClient;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BuildConfig;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static retrofit2.converter.scalars.ScalarsConverterFactory.create;

public class Activity_Bloglists extends AppCompatActivity implements/* OnDataPointListener,*/ NavigationView.OnNavigationItemSelectedListener, GoogleApiClient.OnConnectionFailedListener {

    private boolean isActive;
    TinyDB tinyDB;
    private ProgressDialog pd;
    private RelativeLayout mLaynodata;
    boolean isclicked = false;
    Activity_Bloglists activity;
    private List<Object> recyclerViewItems = new ArrayList<>();
    private int adCount = 0;
    BackpainAPIInterface apiInterface;
    private RelativeLayout mLoadlay;
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

    private NavigationView mNavigationView;
    private final Handler mDrawerHandler = new Handler();
    public static DrawerLayout layout;
    private boolean isClicked = false;
    private Dialog dialrateexepe;
    private int temprate;
    private String[] feedbacklist;
    private AlertDialog feedbackdial;
    private DatabaseOperations databaseOperations;
    private BDatabaseOperations databaseOperations2;
    private HistoryProgressOperations historyProgressOperations;
    private MyplanDbhelper myplanDbhelper;
    private DietplanDbhelper dietplanDbhelper;
    private GraphdataOperations graphdataOperations;
    private WeightGraphdataOperations weightGraphdataOperations;
    RadioButton rhindi, rfrench, rchinish, rpotugues, rrussian, aarbic, renglish;
    String selectedSuperStar;
    ArrayList<String> mSelectedFeedbackslist = new ArrayList<>();

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
    private String token;
    private String BASEURL;
    private NestedScrollView nestedSV;
    ArrayList<String> blodid = new ArrayList<>();
    ArrayList<String> blodname = new ArrayList<>();
    ArrayList<List<String>> blodimage = new ArrayList<List<String>>();
    ArrayList<String> blodlink = new ArrayList<>();
    ArrayList<String> blodblog_Des = new ArrayList<>();
    ArrayList<Boolean> blodlikestatus = new ArrayList<Boolean>();
    ArrayList<Integer> blodbloglike = new ArrayList<Integer>();
    ArrayList<String> blodcreated_at = new ArrayList<>();
    ArrayList<String> blodupdated_at = new ArrayList<>();
    private ProgressBar loadingPB;
    int page = 1, limit = 4;
    private RecyclerView mBottomrecycler;

    public Activity_Bloglists() {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_drawerbloglist);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isActive = bundle.getBoolean("isFrom3");
        }
        activity = this;
        mLaynodata = (RelativeLayout) findViewById(R.id.no_video);
        mLoadlay = (RelativeLayout) findViewById(R.id.loadlayout);
        nestedSV = findViewById(R.id.idNestedSV);
        mBottomrecycler = (RecyclerView) findViewById(R.id.bottomrecycler);
        LottieAnimationView mLottie = (LottieAnimationView) findViewById(R.id.lotti2);
        mLottie.setVisibility(View.VISIBLE);
        mLottie.setAnimation("loadanimdial.json");
        mLottie.playAnimation();
        mLottie.loop(true);
        mLoadlay.setVisibility(View.GONE);
        Button mBtnrefresh = (Button) findViewById(R.id.btrefresh);
        RelativeLayout mBottomlay = (RelativeLayout) findViewById(R.id.bottombar);
        ImageView mBtnback = (ImageView) findViewById(R.id.btnback);
        mBtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.openDrawer(Gravity.START);
            }
        });

     /*   LinearLayout mAdframe = (LinearLayout) findViewById(R.id.adframe2);
        FrameLayout mAdframe4 = (FrameLayout) findViewById(R.id.adframe4);
        if (Constants.isBlogBanner) {
            mAdframe.setVisibility(View.GONE);
            showBanner(this, mAdframe4);
        } else {
            mAdframe4.setVisibility(View.GONE);
            Smallnative(this, mAdframe);
        }*/

        ImageView mBtnstore = (ImageView) findViewById(R.id.ivbtnstore);
        mBtnstore.setBackgroundResource(R.drawable.adsstoreanim);
        AnimationDrawable anim2 = (AnimationDrawable) mBtnstore.getBackground();
        anim2.start();
        mBtnstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cgender=tinyDB.getBoolean(Constants.Genderchoose);
                if (cgender){
                    if (tinyDB.getString(Constants.GENDER_KEY).equals(getResources().getString(R.string.female))) {
                        Intent intent = new Intent(Activity_Bloglists.this, AppstoreActivity.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(Activity_Bloglists.this, MaleAppstoreActivity.class);
                        startActivity(intent);
                    }
                }else {
                    Intent intent = new Intent(Activity_Bloglists.this, ChooseGenderActivity.class);
                    startActivity(intent);
                }
            }
        });
        RelativeLayout banner1 = (RelativeLayout) findViewById(R.id.banner1);
        FrameLayout mGbannerlay2 = findViewById(R.id.adframe1234);
        mGbannerlay2.setVisibility(View.GONE);
        showBanner(this, mGbannerlay2, banner1);

       /* FrameLayout mAdslay = (FrameLayout) findViewById(R.id.adframe);
        mAdslay.setVisibility(View.GONE);
        FitnessApplication.showBanner(Activity_Bloglists.this,mAdslay,2);*/
        tinyDB = new TinyDB(Activity_Bloglists.this);
        mNavigationView = (NavigationView) findViewById(R.id.navigation_view);
        layout = findViewById(R.id.drawer);
        mNavigationView.setNavigationItemSelectedListener(this);
        activity = this;
        feedbacklist = getResources().getStringArray(R.array.feedlist);
        databaseOperations = new DatabaseOperations(this);
        databaseOperations2 = new BDatabaseOperations(this);
        graphdataOperations = new GraphdataOperations(this);
        weightGraphdataOperations = new WeightGraphdataOperations(this);
        historyProgressOperations = new HistoryProgressOperations(this);
        myplanDbhelper = new MyplanDbhelper(this);
        dietplanDbhelper = new DietplanDbhelper(this);

        BASEURL=tinyDB.getString(Constants.NewBaseUrl);

        token=tinyDB.getString(Constants.Authtoken);
       /* if (blogdatalist != null && blogdatalist.size() != 0) {
            Blogdata(blogdatalist);
        } else {
            mLoadlay.setVisibility(View.VISIBLE);
            callBlogApi();
        }*/

        mBtnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadlay.setVisibility(View.VISIBLE);
              //  callBlogApi();
               // getBlog(page, limit,token);

            }
        });

        nestedSV.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // on scroll change we are checking when users scroll as bottom.
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    // in this method we are incrementing page number,
                    // making progress bar visible and calling get data method.

                    Log.e("scroll", "scrolltrue");
                    page++;
                    loadingPB.setVisibility(View.VISIBLE);
                //    getBlog(page, limit, "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiIyMGNhMGVjMWIzZTJmNTUyIiwiaWF0IjoxNjI4MzQzOTk2fQ.Zkif182KkhW4QY9A_uoSMuSibCJF-CDYMRBU8uhlAUw");

                }
            }
        });
      //  callBlogApi();
       // getBlog(page, limit,token);
        NavigationData();
        InitLoginlayouts();
        initbottomnavigation();
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


    private void callBlogApi() {
        try {
            this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);

         //   String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiIxMDJhMGNmNjc0NjZjYWNjIiwiaWF0IjoxNjI2MzIxMDcwfQ.v8_1g2RFNemXKR24C2bVTqbWrnZ7FZh7drO5M13-XzY";
            this.apiInterface.getBlog1(token).enqueue(new Callback<List<BlogList>>() {
                @Override
                public void onResponse(@NotNull Call<List<BlogList>> call, @NotNull Response<List<BlogList>> response) {
                    try {
                        Log.e("repspoxeblog", String.valueOf(response.body()));

                        ArrayList<BlogList> blogLists = (ArrayList<BlogList>) response.body();

                        for (int i = 0; i < blogLists.size(); i++) {
                            Log.e("Tag", "data" + blogLists);

                          String id=blogLists.get(i).getId();
                          String link=blogLists.get(i).getBlogLink();
                          String name=blogLists.get(i).getBlogName();
                          boolean likestatus=blogLists.get(i).getLikeStatus();
                          String blog_Des=blogLists.get(i).getMeta_description();
                            Log.e("Tag", "id" + id);
                            Log.e("Tag", "link" + link);
                            Log.e("Tag", "name" + name);
                            Log.e("Tag", "likestatus" + likestatus);
                            Log.e("Tag", "blog_Des" + blog_Des);


                        }

                        mBottomrecycler.setLayoutManager(new LinearLayoutManager(Activity_Bloglists.this, LinearLayoutManager.VERTICAL, false));
                        Adapter_Allblogs adapterTopblogs = new Adapter_Allblogs(Activity_Bloglists.this, blogLists, activity);
                        mBottomrecycler.setAdapter(adapterTopblogs);
                        Log.e("Tag", "data" + response);

                    } catch (Exception e) {
                        Log.e("repspoxeblogcatch", String.valueOf(e));
                        mLoadlay.setVisibility(View.GONE);
                        mLaynodata.setVisibility(View.VISIBLE);
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(@NotNull Call<List<BlogList>> call, @NotNull Throwable t) {
                    mLoadlay.setVisibility(View.GONE);
                    mLaynodata.setVisibility(View.VISIBLE);
                }
            });
        } catch (Exception e) {
            mLoadlay.setVisibility(View.GONE);
            mLaynodata.setVisibility(View.VISIBLE);
            e.printStackTrace();
        }

    }


    public void initbottomnavigation() {
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
        mTvprogress.setTextColor(getResources().getColor(R.color.tbtncolor));
        mTvearn.setTextColor(getResources().getColor(R.color.navunselected));
        mTvpurchase.setTextColor(getResources().getColor(R.color.navunselected));
        mIvhome3.setColorFilter(getResources().getColor(R.color.grey));
        mIvdiscover3.setColorFilter(getResources().getColor(R.color.grey));
        mIvProgress3.setColorFilter(getResources().getColor(R.color.tbtncolor));
        mIvEarn3.setColorFilter(getResources().getColor(R.color.grey));
        mIvPurchase3.setColorFilter(getResources().getColor(R.color.grey));
        mLaydiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Bloglists.this, Activity_Discover.class);


                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
                finish();
            }
        });
        mLayprogress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        mLayhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Bloglists.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
                finish();
            }
        });
        mLayearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Bloglists.this, Activity_Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
                finish();
            }
        });
        mLaypurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Activity_Bloglists.this, Activity_Reports.class);


                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
                finish();
            }
        });
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
        LinearLayout mLayrate = (LinearLayout) findViewById(R.id.llayrate);
        LinearLayout mLayshare = (LinearLayout) findViewById(R.id.llayshare);
        LinearLayout mLayrestart = (LinearLayout) findViewById(R.id.llayrp);
        //  LinearLayout mBtncovid = (LinearLayout) findViewById(R.id.btncovid);
        if (tinyDB.getBoolean(Constants.RATEDIALOGSHOW_KEY)) {
            mLayrate.setVisibility(View.GONE);
        } else {
            mLayrate.setVisibility(View.VISIBLE);
        }
       /* mBtncovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(Activity_Reports.this, 14);
            }
        });*/
        if (tinyDB.getBoolean(Constants.PREMIUN_KEY)) {
            mLayra.setVisibility(View.GONE);
        }
        mLaymt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(Activity_Bloglists.this, 0);
            }
        });
        mLaynp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(Activity_Bloglists.this, 1);
            }
        });
        mLaytip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(Activity_Bloglists.this, 2);
            }
        });
        mLayreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(Activity_Bloglists.this, 3);
            }
        });
        mLaycovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(Activity_Bloglists.this, 14);
            }
        });
        mLaydiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(Activity_Bloglists.this, 4);

            }
        });
        mLaycustom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(Activity_Bloglists.this, 5);
            }
        });
        mLayra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(Activity_Bloglists.this, 6);

            }
        });
        mLaystore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(Activity_Bloglists.this, 7);

            }
        });
        mLayreminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(Activity_Bloglists.this, 8);

            }
        });
        mLaylang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(Activity_Bloglists.this, 9);

            }
        });
        mLaysetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(Activity_Bloglists.this, 10);

            }
        });
        mLayrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(Activity_Bloglists.this, 11);

            }
        });
        mLayshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(Activity_Bloglists.this, 12);

            }
        });
        mLayrestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(Activity_Bloglists.this, 13);

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (feedbackdial != null && feedbackdial.isShowing()) {
            feedbackdial.dismiss();
        }
        if (dialrateexepe != null && dialrateexepe.isShowing()) {
            dialrateexepe.dismiss();
        }
    }

    public void click(Context context, int i) {
        if (i == 1) {
            String tempdate = tinyDB.getString(Constants.DIETPLANDATE_KEY);
            Date c = Calendar.getInstance().getTime();
             Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
            String formattedDate = df.format(c);
            if (!tempdate.isEmpty() && tempdate.equals(formattedDate)) {
                Intent intent1 = new Intent(context, DefalutAndCustomplanActivity.class);
                intent1.putExtra("isFrom2", false);
                startActivity(intent1);
            } else {
                Intent intent = new Intent(context, CustomUpdateActivity.class);
                intent.putExtra("isFrom", false);
                startActivity(intent);
            }
         /*   String tempdate = tinyDB.getString(Constants.DIETPLANDATE_KEY);
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
            }*/
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
            boolean cgender = tinyDB.getBoolean(Constants.Genderchoose);
            if (cgender) {
                if (tinyDB.getString(Constants.GENDER_KEY).equals(getResources().getString(R.string.female))) {
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
           /* Intent ttsIntent = new Intent();
            ttsIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
            ttsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(ttsIntent);*/
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
                Intent intent5 = new Intent(Activity_Bloglists.this, Activity_Covidhome.class);
                intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent5);
                finish();
            }
        }
    }

    public void Resetdialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(Activity_Bloglists.this);
        alertDialogBuilder.setMessage("Restart Progress ?");
        alertDialogBuilder.setPositiveButton("YES",
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
        alertDialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

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
        tinyDB.remove(Constants.TOTALTIME_KEY);
        tinyDB.remove(Constants.TOTALEXE_KEY);
        tinyDB.remove(Constants.TOTALKCAL_KEY);
        tinyDB.remove(Constants.DIETPLANDATE_KEY);

        tinyDB.remove(Constants.ALLDAYSPROGRESS_KEY);
        tinyDB.remove(Constants.ALLDAYSPROGRESS_LV2KEY);
        tinyDB.remove(Constants.ALLDAYSPROGRESS_LV3KEY);

        tinyDB.remove(Constants.DAYSLEFT_KEY);
        tinyDB.remove(Constants.DAYSLEFT_LV2KEY);
        tinyDB.remove(Constants.DAYSLEFT_LV3KEY);

        tinyDB.remove(Constants.DAYCLICK_KEY);
        tinyDB.remove(Constants.ALV2DAYCLICK_KEY);
        tinyDB.remove(Constants.ALV3DAYCLICK_KEY);

        tinyDB.remove(Constants.DAYCLICK_BLV1KEY);
        tinyDB.remove(Constants.DAYCLICK_BLV2KEY);
        tinyDB.remove(Constants.DAYCLICK_BLV3KEY);

        tinyDB.remove(Constants.DAYSLEFT_BLV1KEY);
        tinyDB.remove(Constants.DAYSLEFT_BLV2KEY);
        tinyDB.remove(Constants.DAYSLEFT_BLV3KEY);

        tinyDB.remove(Constants.ALLDAYSPROGRESS_BLV1KEY);
        tinyDB.remove(Constants.ALLDAYSPROGRESS_BLV2KEY);
        tinyDB.remove(Constants.ALLDAYSPROGRESS_BLV3KEY);

        tinyDB.remove(Constants.ALEVEL_KEY);
        tinyDB.remove(Constants.BLEVEL_KEY);

        tinyDB.remove(Constants.ADDEDFIXDIET);
        tinyDB.remove(Constants.FIXDIET1_KEY);

    }

    public void shareApp(Context context) {
        final String appPackageName = context.getPackageName();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Downlaod this app " + getResources().getString(R.string.app_name) + " and Join over 1M+ community here at: https://play.google.com/store/apps/details?id=" + getPackageName());
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
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
            Button submit = (Button) timersdialog.findViewById(R.id.submitButton);
            String languages = tinyDB.getString(Constants.Language);
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
                        tinyDB.putString(Constants.Language, selectedSuperStar);
                        tinyDB.putBoolean(Constants.AppLanguage, true);
                        //  LanguageChangeAPI("hindi");
                    } else if (rhindi.isChecked()) {
                        selectedSuperStar = Language.HINDI;
                        tinyDB.putString(Constants.Language, selectedSuperStar);
                        tinyDB.putBoolean(Constants.AppLanguage, true);
                    } else if (rfrench.isChecked()) {
                        selectedSuperStar = Language.FRENCH;
                        tinyDB.putString(Constants.Language, selectedSuperStar);
                    } else if (rchinish.isChecked()) {
                        selectedSuperStar = Language.CHINESE;
                        tinyDB.putString(Constants.Language, selectedSuperStar);
                    } else if (rpotugues.isChecked()) {
                        selectedSuperStar = Language.PORTUGUESE;

                        tinyDB.putString(Constants.Language, selectedSuperStar);
                    } else if (rrussian.isChecked()) {
                        selectedSuperStar = Language.RUSSIAN;
                        tinyDB.putString(Constants.Language, selectedSuperStar);
                    } else if (aarbic.isChecked()) {
                        selectedSuperStar = Language.ARABIC;
                        tinyDB.putString(Constants.Language, selectedSuperStar);

                    }
                    String laun = tinyDB.getString(Constants.Language);
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

    public void DialogRatexeperience() {
        dialrateexepe = new Dialog(Activity_Bloglists.this);
        dialrateexepe.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialrateexepe.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialrateexepe.setContentView(R.layout.dialog_rateexperience);
        TextView mRatebtn = (TextView) dialrateexepe.findViewById(R.id.btnok);
        TextView mClosebtn = (TextView) dialrateexepe.findViewById(R.id.btnclose);
        TextView mTextexpe = (TextView) dialrateexepe.findViewById(R.id.txtdesx);
        SmileyRatingView smileyRating = dialrateexepe.findViewById(R.id.smiley_view);
        RatingBar ratingBar = dialrateexepe.findViewById(R.id.rating_bar);
        ratingBar.setRating(0);
        mTextexpe.setText("Do you like this App ?");
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                temprate = (int) (rating - 1);
                Log.d("Rating", String.valueOf(temprate));
                smileyRating.setSmiley(temprate);
                switch (temprate) {
                    case 0:
                        mTextexpe.setText("Hated it");
                        break;
                    case 1:
                        mTextexpe.setText("Disliked it!");
                        break;
                    case 2:
                        mTextexpe.setText("It's Ok");
                        break;
                    case 3:
                        mTextexpe.setText("Liked it!");
                        break;
                    case 4:
                        mTextexpe.setText("Loved it");
                        break;
                }
            }
        });
        mRatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temprate == 4) {
                    dialrateexepe.dismiss();
                    tinyDB.putBoolean(Constants.RATEDIALOGSHOW_KEY, true);
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

    public void Rateus() {
        Uri uri1 = Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri1);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(Activity_Bloglists.this, "You don't have Google Play installed", Toast.LENGTH_LONG).show();
        }
    }


    public void DialogFeedback() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Bloglists.this, R.style.MaterialThemeDialog);
        LayoutInflater inflater = LayoutInflater.from(Activity_Bloglists.this);
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
                Animation mzoom = AnimationUtils.loadAnimation(Activity_Bloglists.this, R.anim.zoom_in);
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
             //   callFeedbackApi(mOp1, mOp2, mOp3, mOp4, mOp5, mComment, mUid);

                String device_name = Build.BRAND + ", " + Build.DEVICE + ", " + Build.MANUFACTURER + ", " + BuildConfig.VERSION_NAME;
                  String androidId = 22+Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID)+2;
                String email = tinyDB.getString(Constants.USEREMAIL_KEY);
                String release = Build.VERSION.RELEASE;
                callFeedbackApi(androidId, device_name, release, email, mOp1, mOp2, mOp3, mOp4, mOp5,mComment/*, mComment, mUid*/);
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
                    ActivityCompat.requestPermissions(Activity_Bloglists.this,
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
                if (tinyDB.getBoolean(Constants.ISLOGIN_KEY)) {
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
                    Toast.makeText(Activity_Bloglists.this, "The app was not allowed to get your location, Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }
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


    private void logout() {
        LoginManager.getInstance().logOut();
        updateUI(false);
    }

    private void updateUI(boolean signedIn) {
        if (signedIn) {
            tinyDB.putBoolean(Constants.ISLOGIN_KEY, true);
            mloginlay.setVisibility(View.GONE);
            mSucceslayout.setVisibility(View.VISIBLE);

        } else {
            tinyDB.remove(Constants.USERID_KEY);
            tinyDB.remove(Constants.USEREMAIL_KEY);
            tinyDB.remove(Constants.USERFIRSTNAME_KEY);
            tinyDB.remove(Constants.USERLASTNAME_KEY);
            tinyDB.putBoolean(Constants.ISLOGIN_KEY, false);
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
                    //   getLocation();
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


    @Override
    protected void onResume() {
        super.onResume();
        if (tinyDB.getBoolean(Constants.ISLOGIN_KEY)) {
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
            boolean mIslogedin = tinyDB.getBoolean(Constants.ISLOGIN_KEY);


            String first_name = GFirstname;
            String last_name = GLastname;
            String email = Gmail;
            String mobile_number = "0";
            String gender = tinyDB.getString(Constants.GENDER_KEY);
            String age = String.valueOf(tinyDB.getInt(Constants.AGE_KEY));
            String mHeight;
            String mHeightP;
            if (tinyDB.getBoolean(Constants.ISCM_KEY)) {
                mHeight = String.valueOf(tinyDB.getFloat(Constants.HEIGHT_KEY)) /*+ " cm"*/;
                mHeightP = "cm";
            } else {
                mHeight = String.valueOf(tinyDB.getFloat(Constants.HEIGHT_KEY)) /*+ " ft"*/;
                mHeightP = "ft";

            }
            String height = mHeight;
            String mWeight;
            String mWeightP;
            if (tinyDB.getBoolean(Constants.ISKG_KEY)) {
                mWeight = String.valueOf(tinyDB.getInt(Constants.WEIGHT_KEY)) /*+ " kg"*/;
                mWeightP = "kg";
            } else {
                mWeight = String.valueOf(tinyDB.getInt(Constants.WEIGHT_KEY))/* + " lb"*/;
                mWeightP = "lb";
            }
            String weight = mWeight;
            String user_type_id = String.valueOf(tinyDB.getInt(Constants.MEALTYPE_KEY));
            String paid_status = "0";
            String country_id = tinyDB.getString(Constants.COUNTRYID_KEY);
            //       Log.e("coutry",country_id);
            String langauge = Resources.getSystem().getConfiguration().locale.getLanguage();
            Log.e("getDisplayLanguage", langauge);

            if (langauge.equals("hi")) {
                // tinyDB.putString(Constants.Language, "hi");
                // Constants.languagechange(getActivity(), "hi");
                langauge = "hindi";
            } else {
                // tinyDB.putString(Constants.Language, "en");
                // Constants.languagechange(getActivity(), "en");
                langauge = "english";
            }

            // String langauge = null;
            String state_id = tinyDB.getString(Constants.STATEID_KEY);
            //     Log.e("state_id1",state_id);
            String city_id = tinyDB.getString(Constants.CITYID_KEY);
            //     Log.e("city_id1",city_id);
            String fcm_token = tinyDB.getString(Constants.FCMTOKEN_KEY);
            String facebook = "";
            String google = Guserid;
            String user_image = Gprofilepicurl;

            callRegisterApi(first_name, last_name, email, gender,
                    age, height, weight, country_id,
                    state_id, city_id, fcm_token, google, langauge, mHeightP, mWeightP);


          /*  if (!mIslogedin) {
                if (Gmail != null) {
                    String token = tinyDB.getString(Constants.FCMTOKEN_KEY);
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

  /*  @SuppressLint("WrongConstant")
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
                            String gender = tinyDB.getString(Constants.GENDER_KEY);
                            String age = String.valueOf(tinyDB.getInt(Constants.AGE_KEY));
                            String mHeight;
                            if (tinyDB.getBoolean(Constants.ISCM_KEY)) {
                                mHeight = tinyDB.getFloat(Constants.HEIGHT_KEY) + " cm";
                            } else {
                                mHeight = tinyDB.getFloat(Constants.HEIGHT_KEY) + " ft";
                            }
                            String height = mHeight;
                            String mWeight;
                            if (tinyDB.getBoolean(Constants.ISKG_KEY)) {
                                mWeight = tinyDB.getInt(Constants.WEIGHT_KEY) + " kg";
                            } else {
                                mWeight = tinyDB.getInt(Constants.WEIGHT_KEY) + " lb";
                            }
                            String weight = mWeight;
                            String user_type_id = String.valueOf(tinyDB.getInt(Constants.MEALTYPE_KEY));
                            String paid_status = "0";
                            String country_id = tinyDB.getString(Constants.COUNTRYID_KEY);
                            String state_id = tinyDB.getString(Constants.STATEID_KEY);
                            String city_id = tinyDB.getString(Constants.CITYID_KEY);
                            String fcm_token = tinyDB.getString(Constants.FCMTOKEN_KEY);
                            String facebook = "";
                            String google = Guserid;
                            String user_image = Gprofilepicurl;
*//*
                            callRegisterApi(first_name, last_name, email, mobile_number, gender,
                                    age, height, weight, user_type_id, paid_status, country_id,
                                    state_id, city_id, fcm_token, facebook, google, user_image);*//*

                        } else {
                            tinyDB.putString(Constants.USERID_KEY, dataist.getId());
                            tinyDB.putString(Constants.USEREMAIL_KEY, dataist.getEmail());
                            tinyDB.putString(Constants.USERFIRSTNAME_KEY, dataist.getFirst_name());
                            tinyDB.putString(Constants.USERLASTNAME_KEY, dataist.getLast_name());
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
                            tinyDB.putString(Constants.USERID_KEY, dataist.getId());
                            tinyDB.putString(Constants.USEREMAIL_KEY, email);
                            tinyDB.putString(Constants.USERFIRSTNAME_KEY, first_name);
                            tinyDB.putString(Constants.USERLASTNAME_KEY, last_name);
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
            String getusertype=tinyDB.getString(Constants.USERTYPEKEY);
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
                        tinyDB.putString(Constants.Authtoken, dietCateData.token);

                        String userid = docs.getId();
                        tinyDB.putString(Constants.USERID_KEYs, userid);

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
            tinyDB.putString(Constants.USERID_KEY, userid);
            tinyDB.putInt(Constants.AGE_KEY, Integer.parseInt(mAge));
            tinyDB.putString(Constants.GENDER_KEY, gen);
            if (height.contains("cm")) {
                tinyDB.putBoolean(Constants.ISCM_KEY, true);
                height = height.replace(" cm", "");
                tinyDB.putFloat(Constants.HEIGHT_KEY, Float.parseFloat(height));
                String finalHeight = height;
            } else {
                String doubleAsString = height;
                int indexOfDecimal = doubleAsString.indexOf(".");
                int mFtval = Integer.parseInt(doubleAsString.substring(0, indexOfDecimal));
                String mTrim = doubleAsString.substring(indexOfDecimal).replace(".", "");
                mTrim = mTrim.replace(" ft", "");
                int mInchval = Integer.parseInt(mTrim);
                tinyDB.putBoolean(Constants.ISCM_KEY, false);
                String temp = mFtval + "." + mInchval;
                tinyDB.putFloat(Constants.HEIGHT_KEY, Float.parseFloat(temp));
            }
            if (weight.contains("kg")) {
                tinyDB.putBoolean(Constants.ISKG_KEY, true);
                weight = weight.replace(" kg", "");
                tinyDB.putInt(Constants.WEIGHT_KEY, Integer.parseInt(weight));
                String finalWeight = weight;
            } else {
                tinyDB.putBoolean(Constants.ISKG_KEY, false);
                weight = weight.replace(" lb", "");
                tinyDB.putInt(Constants.WEIGHT_KEY, Integer.parseInt(weight));
                String finalWeight1 = weight;
            }
            if (mealtype.equals("Veg")) {
                tinyDB.putInt(Constants.MEALTYPE_KEY, 1);
                tinyDB.putString(Constants.USERTYPEKEY,"veg");
            } else if (mealtype.equals("Non-Veg")) {
                tinyDB.putInt(Constants.MEALTYPE_KEY, 2);
                tinyDB.putString(Constants.USERTYPEKEY,"nonveg");

            } else if (mealtype.equals("Vegan")) {
                tinyDB.putInt(Constants.MEALTYPE_KEY, 3);
                tinyDB.putString(Constants.USERTYPEKEY,"vegan");

            }
        } catch (Exception e) {
            Log.e("ploginerror", "" + e.getMessage().toString());
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }


    public void showBanner(Context context, final FrameLayout adMobView) {
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
                    Constants.isBlogBanner = false;
                }

                @Override
                public void onAdFailedToLoad(int i) {
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


   /* @SuppressLint("WrongConstant")
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
                        mLoadlay.setVisibility(View.GONE);
                        BlogData blogData = (BlogData) response.body();
                        ArrayList<BlogData.Datalist> datalists = blogData.datalists;
                        blogdatalist = datalists;
                        Blogdata(datalists);
                        int blogcounts = tinyDB.getInt(Constants.PRESENTBLOGCOUNTS);
                        if (blogcounts == 0 || blogcounts > datalists.size()) {
                            tinyDB.putInt(Constants.PRESENTBLOGCOUNTS, datalists.size());
                        }
                        if (blogcounts != 0 && tinyDB.getInt(Constants.PRESENTBLOGCOUNTS) != 0) {
                            int oldblogs = tinyDB.getInt(Constants.PRESENTBLOGCOUNTS);
                            if (datalists.size() > oldblogs) {
                                int Newblogs = datalists.size() - oldblogs;
                                tinyDB.putInt(Constants.NEWBLOGSCOUNTS, Newblogs);
                            } else {
                                tinyDB.putInt(Constants.NEWBLOGSCOUNTS, 0);
                            }
                        }
                    }catch (Exception e){
                        mLoadlay.setVisibility(View.GONE);
                        mLaynodata.setVisibility(View.VISIBLE);
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(@NotNull Call<BlogData> call, @NotNull Throwable t) {
                    mLoadlay.setVisibility(View.GONE);
                    mLaynodata.setVisibility(View.VISIBLE);
                }
            });
        } catch (Exception e) {
            mLoadlay.setVisibility(View.GONE);
            mLaynodata.setVisibility(View.VISIBLE);
            e.printStackTrace();
        }
    }*/

    public void Blogdata(ArrayList<BlogData.Datalist> datalists) {
        for (int i = 0; i < datalists.size(); i++) {
            BlogData.Datalist data = datalists.get(i);
            recyclerViewItems.add(data);
            if (adCount == 3) {
                recyclerViewItems.add(new BlogData.Datalist("", "", "",
                        "", "", "", "", true));
                adCount = 0;
            } else {
                adCount++;
            }
        }
        mLaynodata.setVisibility(View.GONE);
        LinearLayout mGbannerlay = findViewById(R.id.adframe);
        mGbannerlay.setVisibility(View.GONE);
        showGOOGLEAdvance(Activity_Bloglists.this, mGbannerlay);
      /*  RecyclerView mBottomrecycler = (RecyclerView) findViewById(R.id.bottomrecycler);
        mBottomrecycler.setLayoutManager(new LinearLayoutManager(Activity_Bloglists.this, LinearLayoutManager.VERTICAL, false));
        Adapter_Allblogs adapterTopblogs = new Adapter_Allblogs(Activity_Bloglists.this, recyclerViewItems, activity);
        mBottomrecycler.setAdapter(adapterTopblogs);*/
    }

    public void showGOOGLEAdvance(final Context context, final LinearLayout frameLayout) {
        AdLoader.Builder builder = new AdLoader.Builder(context, Constants.admob_nativead);
        builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
            public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                frameLayout.setVisibility(View.VISIBLE);
                NativeContentAdView nativeContentAdView = (NativeContentAdView) ((Activity) context).getLayoutInflater().inflate(R.layout.ad_gnativelaytwo, null);
                populateContentAdView(nativeContentAd, nativeContentAdView);
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
    public static void populateContentAdView(NativeContentAd
                                                     nativeContentAd, NativeContentAdView nativeContentAdView) {
        nativeContentAdView.setHeadlineView(nativeContentAdView.findViewById(R.id.ad_title_textview));
        nativeContentAdView.setImageView(nativeContentAdView.findViewById(R.id.contentad_image));
        nativeContentAdView.setBodyView(nativeContentAdView.findViewById(R.id.ad_describe_textview));
        nativeContentAdView.setCallToActionView(nativeContentAdView.findViewById(R.id.ad_action_button));
        ((TextView) nativeContentAdView.getHeadlineView()).setText(nativeContentAd.getHeadline());
        ((TextView) nativeContentAdView.getBodyView()).setText(nativeContentAd.getBody());
        ((TextView) nativeContentAdView.getCallToActionView()).setText(nativeContentAd.getCallToAction());
//        ((TextView) nativeContentAdView.getAdvertiserView()).setText(nativeContentAd.getAdvertiser());
        List images = nativeContentAd.getImages();
        if (images.size() > 0) {
            ((RoundedImageView) nativeContentAdView.getImageView()).setImageDrawable(((com.google.android.gms.ads.formats.NativeAd.Image) images.get(0)).getDrawable());
        }
        nativeContentAdView.setNativeAd(nativeContentAd);
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
                Constants.isBlogBanner = true;

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


    @Override
    public void onBackPressed() {
        if (isActive) {
          /*  Intent intent = new Intent(Activity_Bloglists.this, Activity_Reports.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);*/
            finish();
        } else {
         /*   Intent intent = new Intent(Activity_Bloglists.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);*/
            finish();
        }

    }

    @Override
    protected void onStart() {
        isclicked = false;
        super.onStart();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


}
