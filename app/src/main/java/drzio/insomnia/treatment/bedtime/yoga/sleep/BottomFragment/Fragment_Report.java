/*package drzio.insomnia.treatment.bedtime.yoga.sleep.BottomFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Fragment_Report extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_drawerreport,container,false);
    }
}*/
package drzio.insomnia.treatment.bedtime.yoga.sleep.BottomFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
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
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.iid.FirebaseInstanceId;
import com.shawnlin.numberpicker.NumberPicker;

import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Calenderview;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_LoginScreen;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_weekgoal;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter.WeekGoalAdapter;
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
import drzio.insomnia.treatment.bedtime.yoga.sleep.Fragments.WeightGraphfragments;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Graphdata.GraphdataOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Graphdata.WeightGraphdataOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.Register_Api;
import drzio.insomnia.treatment.bedtime.yoga.sleep.customs.CustomSeekBar;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.LoginData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.ProgressItem;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Weekgoalmodal;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIClient;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_Report extends Fragment implements /*NavigationView.OnNavigationItemSelectedListener,*/ GoogleApiClient.OnConnectionFailedListener {
    public TinyDB mTinydb;
    private TextView mTotalkcal;
    private TextView mTotaltime;
    private TextView mTotalexe;
    private LinearLayout mBtcalbmi;
    private RelativeLayout mBmiscalelayout;
    private CustomSeekBar bmiseekBar;
    private TextView wght2;
    private TextView wght4;
    private TextView mTxtbmi;
  //  private LinearLayout mTemplayer1;
    private LinearLayout mTemplayer2;
    private int mHcmvalue = 100;
    private int mHftvalue = 5;
    private int mHinchvalue = 0;
    private int mWkgvalue = 45;
    private int mWlbvalue = 50;

    private boolean isBtnkg = true;
    private boolean isBtnLb;
    private boolean isbtncm = true;
    private boolean isbtninch;
    private TextView mSwich2txt;
    private TextView pageSelected;
    public float Heightincms = 0.0f;
    private ArrayList<ProgressItem> progressItemList;
    private ProgressItem mProgressItem;
    private float bmitotalSpan = 50.0f;
    private float bmivioletSpan = 15.0f;
    private float bmiyellowSpan = 5.0f;
    private float bmiblueSpan = 3.0f;
    private float bmigreenSpan = 7.0f;
    private float bmiorangeSpan = 10.0f;
    private float bmiredSpan = 10.0f;
    private RelativeLayout mGoalayout;
    private RelativeLayout mSetgoallayout;
    private TextView mGoaldaytxt;
    private WeekGoalAdapter goalAdapter;
    List<Weekgoalmodal> weekgoallist = new ArrayList<>();
    private LocalDate today;
    private String[] daynames = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private TextView mTvweight;

    private TextView mBtncalculate;
    private LinearLayout mAdcard1;
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
    //    public static DrawerLayout layout;
    private boolean isClicked = false;
    private Dialog dialrateexepe;
    private int temprate;
    private String[] feedbacklist;
    //    Activity_Reports activity;
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
    private BackpainAPIInterface apiInterface;
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
    private ImageView mBtnmenu;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getActivity().getWindow().setStatusBarColor(getActivity().getColor(R.color.white));
            }
        }


        View view = inflater.inflate(R.layout.activity_reports, container, false);

        mTinydb = new TinyDB(getContext());
        mTotalkcal = (TextView) view.findViewById(R.id.txttotalcal);
        mTotaltime = (TextView) view.findViewById(R.id.txtexcertime);
        mTotalexe = (TextView) view.findViewById(R.id.txtexcecount);
        mBtcalbmi = (LinearLayout) view.findViewById(R.id.btcalbmi);
        mBmiscalelayout = (RelativeLayout) view.findViewById(R.id.bmiscalelay);
        this.bmiseekBar = (CustomSeekBar) view.findViewById(R.id.bmiseekBar);
        this.wght2 = (TextView) view.findViewById(R.id.wght2);
        this.wght4 = (TextView) view.findViewById(R.id.wght4);
        mTxtbmi = (TextView) view.findViewById(R.id.txtbmi);
      //  mTemplayer1 = (LinearLayout) view.findViewById(R.id.templayer1);
        mTemplayer2 = (LinearLayout) view.findViewById(R.id.templayer2);



        LinearLayout mBtnweight = (LinearLayout) view.findViewById(R.id.btnaddweight);

        LinearLayout mBtnHistory = view.findViewById(R.id.btnhistory);

      /*  RelativeLayout banner1 = (RelativeLayout) view.findViewById(R.id.banner1);
        FrameLayout mGbannerlay23 = view.findViewById(R.id.adframe1234);
        mGbannerlay23.setVisibility(View.GONE);*/
        //   showBanner1(getContext(), mGbannerlay23, banner1);
        mBtnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_Calenderview.class);
                startActivity(intent);
            }
        });
        mTvweight = (TextView) view.findViewById(R.id.txtweight);
        mBtncalculate = (TextView) view.findViewById(R.id.btncalculate);
        LinearLayout mGbannerlay = view.findViewById(R.id.adframe);
//        ShimmerFrameLayout mNativeload = view.findViewById(R.id.nativeload);
        mGbannerlay.setVisibility(View.GONE);
        showGOOGLEAdvance(getContext(), mGbannerlay);
        mAdcard1 = view.findViewById(R.id.adcard1);
        ShimmerFrameLayout mLoadlay = view.findViewById(R.id.shimmer_container);
        LinearLayout mGbannerlay2 = view.findViewById(R.id.adframe2);
        FrameLayout mAdframbanner = view.findViewById(R.id.adframebanner);
        mBtnmenu = (ImageView) view.findViewById(R.id.btnmenu);
        String languages = mTinydb.getString(Constants.Language);
        Constants.languagechange(getContext(), languages);

        /*FrameLayout mAdslay = (FrameLayout) view.findViewById(R.id.adframe123);
        mAdslay.setVisibility(View.GONE);
        FitnessApplication.showBanner(Activity_Reports.this,mAdslay,2);*/
       /* mNavigationView = (NavigationView) view.findViewById(R.id.navigation_view);
        layout = view.findViewById(R.id.drawer);
        mNavigationView.setNavigationItemSelectedListener(this);*/
//        activity = this;

        feedbacklist = getResources().getStringArray(R.array.feedlist);
        databaseOperations = new DatabaseOperations(getContext());
        databaseOperations2 = new BDatabaseOperations(getContext());
        graphdataOperations = new GraphdataOperations(getContext());
        weightGraphdataOperations = new WeightGraphdataOperations(getContext());
        historyProgressOperations = new HistoryProgressOperations(getContext());
        myplanDbhelper = new MyplanDbhelper(getContext());
        dietplanDbhelper = new DietplanDbhelper(getContext());
        if (!mTinydb.getBoolean(Constants.PREMIUN_KEY)) {
            if (Constants.isReportBanner) {
                mGbannerlay2.setVisibility(View.GONE);
                showBanner(getContext(), mAdframbanner, mLoadlay);
            } else {
                mAdframbanner.setVisibility(View.GONE);
                Smallnative(getContext(), mGbannerlay2, mLoadlay);
            }
        } else {
            mLoadlay.setVisibility(View.GONE);
        }
        ImageView mBtnback = (ImageView) view.findViewById(R.id.btnmenu);
        /*mBtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // onBackPressed();
                layout.openDrawer(Gravity.START);
            }
        });*/
        ImageView mBtnstore = (ImageView) view.findViewById(R.id.ivbtnstore);
        mBtnstore.setBackgroundResource(R.drawable.adsstoreanim);
        AnimationDrawable anim2 = (AnimationDrawable) mBtnstore.getBackground();
        anim2.start();
        mBtnstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cgender = mTinydb.getBoolean(Constants.Genderchoose);
                if (cgender) {
                    if (mTinydb.getString(Constants.GENDER_KEY).equals(getResources().getString(R.string.female))) {
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
        mGoalayout = (RelativeLayout) view.findViewById(R.id.goallayout);
        mSetgoallayout = (RelativeLayout) view.findViewById(R.id.setgoallayout);
        TextView mTxtsetgoals = (TextView) view.findViewById(R.id.btnsetgoals);
        RecyclerView mGoalrecycleview = (RecyclerView) view.findViewById(R.id.goalrecycler);
        mTxtsetgoals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activity_weekgoal.class);
                startActivity(intent);
            }
        });
        LinearLayout mDatalay = (LinearLayout) view.findViewById(R.id.layone);
        mDatalay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_Calenderview.class);
                startActivity(intent);
            }
        });

        mBtnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.layout.openDrawer(Gravity.START);
            }
        });
        mGoaldaytxt = (TextView) view.findViewById(R.id.goalday);
        if (mTinydb.getInt("goalday") == 0) {
            mSetgoallayout.setVisibility(View.VISIBLE);
            String goalday = "/" + mTinydb.getInt("goalday");
            mGoaldaytxt.setText(goalday);
        } else {
            mGoalayout.setVisibility(View.VISIBLE);
            mSetgoallayout.setVisibility(View.GONE);
        }
        LinearLayout mbtnWeekgoal = (LinearLayout) view.findViewById(R.id.setweekgoalbtn);
        mbtnWeekgoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Activity_weekgoal.class);
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

        mGoalrecycleview.setLayoutManager(new GridLayoutManager(getContext(), 7));
        goalAdapter = new WeekGoalAdapter(getContext(), weekgoallist);
        mGoalrecycleview.setAdapter(goalAdapter);
//        getDaysNames();

        String totalexe = String.valueOf(mTinydb.getInt(Constants.TOTALEXE_KEY));
        mTotalexe.setText(totalexe);
        mTotalkcal.setText(String.valueOf(round(mTinydb.getFloat(Constants.TOTALKCAL_KEY), 1)));
        long temp = mTinydb.getLong(Constants.TOTALTIME_KEY, 0);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(temp);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(temp) % 60;
        mTotaltime.setText(minutes + ":" + seconds);

        if (mTinydb.getBoolean(Constants.ISCM_KEY)) {
            isbtncm = true;
            isbtninch = false;
            mHcmvalue = (int) mTinydb.getFloat(Constants.HEIGHT_KEY);

        } else {
            isbtncm = false;
            isbtninch = true;
            float mData = mTinydb.getFloat(Constants.HEIGHT_KEY);
            String doubleAsString = String.valueOf(mData);
            int indexOfDecimal = doubleAsString.indexOf(".");
            mHftvalue = Integer.parseInt(doubleAsString.substring(0, indexOfDecimal));
            String mTrim = doubleAsString.substring(indexOfDecimal).replace(".", "");
            mHinchvalue = Integer.parseInt(mTrim);

        }
        if (mTinydb.getBoolean(Constants.ISKG_KEY)) {
            isBtnkg = true;
            isBtnLb = false;
            mWkgvalue = mTinydb.getInt(Constants.WEIGHT_KEY);
            mTvweight.setText(mWkgvalue + " kg");
        } else {
            isBtnLb = true;
            isBtnkg = false;
            mWlbvalue = mTinydb.getInt(Constants.WEIGHT_KEY);
            mTvweight.setText(mWlbvalue + " lb");
        }

       /* new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    FrameLayout mChart = (FrameLayout) view.findViewById(R.id.framechart);
                    CmGraphfragments graphfragments = new CmGraphfragments();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.add(R.id.framechart, graphfragments).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 100);*/

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    //FrameLayout mChart2 = (FrameLayout) view.findViewById(R.id.framechart2);
                    WeightGraphfragments weightGraphfragments = new WeightGraphfragments();
                    FragmentTransaction ft2 = getChildFragmentManager().beginTransaction();
                    ft2.add(R.id.framechart2, weightGraphfragments).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 100);


        mBtcalbmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCalculatebmi();
            }
        });
        mBtncalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCalculatebmi();
            }
        });
        if (mTinydb.getFloat(Constants.BMI_KEY) != 0) {
            mBtncalculate.setVisibility(View.GONE);
            mBmiscalelayout.setVisibility(View.VISIBLE);
            mTxtbmi.setText(String.valueOf(mTinydb.getFloat(Constants.BMI_KEY)) + " KG/M²");
            initDataToSeekbar();
        } else {
            mBmiscalelayout.setVisibility(View.GONE);
            mBtncalculate.setVisibility(View.VISIBLE);
        }

        if (mTinydb.getBoolean(Constants.ISLOGIN_KEY)) {
         //   mTemplayer1.setVisibility(View.GONE);
            mTemplayer2.setVisibility(View.GONE);
        } else {
         //   mTemplayer1.setVisibility(View.VISIBLE);
            mTemplayer2.setVisibility(View.VISIBLE);
         /*   mTemplayer1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), Activity_LoginScreen.class);
                    intent.putExtra("isFrom", "reports");
                    startActivity(intent);
//                    finish();
                }
            });*/
            mTemplayer2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), Activity_LoginScreen.class);
                    intent.putExtra("isFrom", "reports");
                    startActivity(intent);
//                    finish();
                }
            });

        }

        mBtnweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogWeightchange();
            }
        });
//        NavigationData(view);
//        InitLoginlayouts(view);
//        initbottomnavigation();

        return view;
    }


    public void showBanner1(Context context, final FrameLayout adMobView, RelativeLayout loadlayout) {
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


    public void Resetdialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
        alertDialogBuilder.setMessage("Restart Progress ?");
        alertDialogBuilder.setPositiveButton("YES",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        dialog.dismiss();
                        DeleteTables();
                        ClearPrefrences();
                        Intent i = getActivity().getPackageManager().getLaunchIntentForPackage(getActivity().getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        getActivity().startActivity(i);
                        getActivity().finish();
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
        mTinydb.remove(Constants.TOTALTIME_KEY);
        mTinydb.remove(Constants.TOTALEXE_KEY);
        mTinydb.remove(Constants.TOTALKCAL_KEY);
        mTinydb.remove(Constants.DIETPLANDATE_KEY);

        mTinydb.remove(Constants.ALLDAYSPROGRESS_KEY);
        mTinydb.remove(Constants.ALLDAYSPROGRESS_LV2KEY);
        mTinydb.remove(Constants.ALLDAYSPROGRESS_LV3KEY);

        mTinydb.remove(Constants.DAYSLEFT_KEY);
        mTinydb.remove(Constants.DAYSLEFT_LV2KEY);
        mTinydb.remove(Constants.DAYSLEFT_LV3KEY);

        mTinydb.remove(Constants.DAYCLICK_KEY);
        mTinydb.remove(Constants.ALV2DAYCLICK_KEY);
        mTinydb.remove(Constants.ALV3DAYCLICK_KEY);

        mTinydb.remove(Constants.DAYCLICK_BLV1KEY);
        mTinydb.remove(Constants.DAYCLICK_BLV2KEY);
        mTinydb.remove(Constants.DAYCLICK_BLV3KEY);

        mTinydb.remove(Constants.DAYSLEFT_BLV1KEY);
        mTinydb.remove(Constants.DAYSLEFT_BLV2KEY);
        mTinydb.remove(Constants.DAYSLEFT_BLV3KEY);

        mTinydb.remove(Constants.ALLDAYSPROGRESS_BLV1KEY);
        mTinydb.remove(Constants.ALLDAYSPROGRESS_BLV2KEY);
        mTinydb.remove(Constants.ALLDAYSPROGRESS_BLV3KEY);

        mTinydb.remove(Constants.ALEVEL_KEY);
        mTinydb.remove(Constants.BLEVEL_KEY);

        mTinydb.remove(Constants.ADDEDFIXDIET);
        mTinydb.remove(Constants.FIXDIET1_KEY);

    }


    @SuppressLint("WrongConstant")
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
                        mTinydb.putBoolean(Constants.RATEDIALOGSHOW_KEY, true);
                        Toast.makeText(getContext(), "Feedback Submitted", Toast.LENGTH_SHORT).show();
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


   /* public void InitLoginlayouts(View view) {
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

        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .enableAutoManage(getActivity(), 1,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
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
            mTinydb.putBoolean(Constants.ISLOGIN_KEY, true);
            mloginlay.setVisibility(View.GONE);
            mSucceslayout.setVisibility(View.VISIBLE);

        } else {
            mTinydb.remove(Constants.USERID_KEY);
            mTinydb.remove(Constants.USEREMAIL_KEY);
            mTinydb.remove(Constants.USERFIRSTNAME_KEY);
            mTinydb.remove(Constants.USERLASTNAME_KEY);
            mTinydb.putBoolean(Constants.ISLOGIN_KEY, false);
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
            boolean mIslogedin = mTinydb.getBoolean(Constants.ISLOGIN_KEY);


            String first_name = GFirstname;
            String last_name = GLastname;
            String email = Gmail;
            String mobile_number = "0";
            String gender = mTinydb.getString(Constants.GENDER_KEY);
            String age = String.valueOf(mTinydb.getInt(Constants.AGE_KEY));
            String mHeight;
            String mHeightP;
            if (mTinydb.getBoolean(Constants.ISCM_KEY)) {
                mHeight = String.valueOf(mTinydb.getFloat(Constants.HEIGHT_KEY)) /*+ " cm"*/;
                mHeightP = "cm";
            } else {
                mHeight = String.valueOf(mTinydb.getFloat(Constants.HEIGHT_KEY)) /*+ " ft"*/;
                mHeightP = "ft";

            }
            String height = mHeight;
            String mWeight;
            String mWeightP;
            if (mTinydb.getBoolean(Constants.ISKG_KEY)) {
                mWeight = String.valueOf(mTinydb.getInt(Constants.WEIGHT_KEY)) /*+ " kg"*/;
                mWeightP = "kg";
            } else {
                mWeight = String.valueOf(mTinydb.getInt(Constants.WEIGHT_KEY))/* + " lb"*/;
                mWeightP = "lb";
            }
            String weight = mWeight;
            String user_type_id = String.valueOf(mTinydb.getInt(Constants.MEALTYPE_KEY));
            String paid_status = "0";
            String country_id = mTinydb.getString(Constants.COUNTRYID_KEY);
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
            String state_id = mTinydb.getString(Constants.STATEID_KEY);
            //     Log.e("state_id1",state_id);
            String city_id = mTinydb.getString(Constants.CITYID_KEY);
            //     Log.e("city_id1",city_id);
            String fcm_token = mTinydb.getString(Constants.FCMTOKEN_KEY);
            String facebook = "";
            String google = Guserid;
            String user_image = Gprofilepicurl;

            callRegisterApi(first_name, last_name, email, gender,
                    age, height, weight, country_id,
                    state_id, city_id, fcm_token, google, langauge, mHeightP, mWeightP);


          /*  if (!mIslogedin) {
                if (Gmail != null) {
                    String token = mTinydb.getString(Constants.FCMTOKEN_KEY);
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
                            String gender = mTinydb.getString(Constants.GENDER_KEY);
                            String age = String.valueOf(mTinydb.getInt(Constants.AGE_KEY));
                            String mHeight;
                            if (mTinydb.getBoolean(Constants.ISCM_KEY)) {
                                mHeight = mTinydb.getFloat(Constants.HEIGHT_KEY) + " cm";
                            } else {
                                mHeight = mTinydb.getFloat(Constants.HEIGHT_KEY) + " ft";
                            }
                            String height = mHeight;
                            String mWeight;
                            if (mTinydb.getBoolean(Constants.ISKG_KEY)) {
                                mWeight = mTinydb.getInt(Constants.WEIGHT_KEY) + " kg";
                            } else {
                                mWeight = mTinydb.getInt(Constants.WEIGHT_KEY) + " lb";
                            }
                            String weight = mWeight;
                            String user_type_id = String.valueOf(mTinydb.getInt(Constants.MEALTYPE_KEY));
                            String paid_status = "0";
                            String country_id = mTinydb.getString(Constants.COUNTRYID_KEY);
                            String state_id = mTinydb.getString(Constants.STATEID_KEY);
                            String city_id = mTinydb.getString(Constants.CITYID_KEY);
                            String fcm_token = mTinydb.getString(Constants.FCMTOKEN_KEY);
                            String facebook = "";
                            String google = Guserid;
                            String user_image = Gprofilepicurl;

                           /* callRegisterApi(first_name, last_name, email, mobile_number, gender,
                                    age, height, weight, user_type_id, paid_status, country_id,
                                    state_id, city_id, fcm_token, facebook, google, user_image);*/
                            /**/
                        } else {
                            mTinydb.putString(Constants.USERID_KEY, dataist.getId());
                            mTinydb.putString(Constants.USEREMAIL_KEY, dataist.getEmail());
                            mTinydb.putString(Constants.USERFIRSTNAME_KEY, dataist.getFirst_name());
                            mTinydb.putString(Constants.USERLASTNAME_KEY, dataist.getLast_name());
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
                            mTinydb.putString(Constants.USERID_KEY, dataist.getId());
                            mTinydb.putString(Constants.USEREMAIL_KEY, email);
                            mTinydb.putString(Constants.USERFIRSTNAME_KEY, first_name);
                            mTinydb.putString(Constants.USERLASTNAME_KEY, last_name);
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
            String getusertype = mTinydb.getString(Constants.USERTYPEKEY);
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
                        mTinydb.putString(Constants.Authtoken, dietCateData.token);

                        String userid = docs.getId();
                        mTinydb.putString(Constants.USERID_KEYs, userid);

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
            mTinydb.putString(Constants.USERID_KEY, userid);
            mTinydb.putInt(Constants.AGE_KEY, Integer.parseInt(mAge));
            mTinydb.putString(Constants.GENDER_KEY, gen);
            if (height.contains("cm")) {
                mTinydb.putBoolean(Constants.ISCM_KEY, true);
                height = height.replace(" cm", "");
                mTinydb.putFloat(Constants.HEIGHT_KEY, Float.parseFloat(height));
                String finalHeight = height;
            } else {
                String doubleAsString = height;
                int indexOfDecimal = doubleAsString.indexOf(".");
                int mFtval = Integer.parseInt(doubleAsString.substring(0, indexOfDecimal));
                String mTrim = doubleAsString.substring(indexOfDecimal).replace(".", "");
                mTrim = mTrim.replace(" ft", "");
                int mInchval = Integer.parseInt(mTrim);
                mTinydb.putBoolean(Constants.ISCM_KEY, false);
                String temp = mFtval + "." + mInchval;
                mTinydb.putFloat(Constants.HEIGHT_KEY, Float.parseFloat(temp));
            }
            if (weight.contains("kg")) {
                mTinydb.putBoolean(Constants.ISKG_KEY, true);
                weight = weight.replace(" kg", "");
                mTinydb.putInt(Constants.WEIGHT_KEY, Integer.parseInt(weight));
                String finalWeight = weight;
            } else {
                mTinydb.putBoolean(Constants.ISKG_KEY, false);
                weight = weight.replace(" lb", "");
                mTinydb.putInt(Constants.WEIGHT_KEY, Integer.parseInt(weight));
                String finalWeight1 = weight;
            }
            if (mealtype.equals("Veg")) {
                mTinydb.putInt(Constants.MEALTYPE_KEY, 1);
            } else if (mealtype.equals("Non-Veg")) {
                mTinydb.putInt(Constants.MEALTYPE_KEY, 2);
            } else if (mealtype.equals("Vegan")) {
                mTinydb.putInt(Constants.MEALTYPE_KEY, 3);
            }
        } catch (Exception e) {
            Log.e("ploginerror", "" + e.getMessage().toString());
            e.printStackTrace();
        }
    }

    /*@Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }*/


   /* public void DialogHeightchange() {
        final Dialog dialog = new Dialog(getContext());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_heightchang);
        TextView btnclose = (TextView) dialog.findViewById(R.id.btnclose);
        NumberPicker mCmpicker = (NumberPicker) dialog.findViewById(R.id.cmpicker);
        NumberPicker mFtpicker = (NumberPicker) dialog.findViewById(R.id.ftpicker);
        NumberPicker mInchpicker = (NumberPicker) dialog.findViewById(R.id.inchpicker);
        LinearLayout mSwitchcminch = (LinearLayout) dialog.findViewById(R.id.switchcminch);
        final TextView mBtcm = (TextView) mSwitchcminch.findViewById(R.id.btcm);
        final TextView mBtinch = (TextView) mSwitchcminch.findViewById(R.id.btinch);
        LinearLayout mCmlayout = (LinearLayout) dialog.findViewById(R.id.cmlayout);
        LinearLayout mInchlayout = (LinearLayout) dialog.findViewById(R.id.inchlayout);

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


        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isbtncm && mHcmvalue != 0) {
                    mTinydb.putBoolean(Constants.ISCM_KEY, true);
                    mTinydb.putFloat(Constants.HEIGHT_KEY, mHcmvalue);

                } else if (isbtninch && mHftvalue != 0) {
                    mTinydb.putBoolean(Constants.ISCM_KEY, false);
                    String temp = mHftvalue + "." + mHinchvalue;
                    mTinydb.putFloat(Constants.HEIGHT_KEY, Float.parseFloat(temp));


                }

             *//*   FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                try {
                    CmGraphfragments graphfragments = new CmGraphfragments();
                    transaction.replace(R.id.framechart, graphfragments).commit();

                } catch (IllegalStateException ignored) {
                }*//*

                dialog.dismiss();
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }*/


    public void DialogWeightchange() {
        final Dialog dialog = new Dialog(getContext());
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_weightchang);
        TextView btnclose = (TextView) dialog.findViewById(R.id.btnclose);
        NumberPicker mKgpicker = (NumberPicker) dialog.findViewById(R.id.kgpicker);
        NumberPicker mLbspicker = (NumberPicker) dialog.findViewById(R.id.lbsbpicker);
        LinearLayout mLaykglb = (LinearLayout) dialog.findViewById(R.id.laykglb);
        final TextView mBtkg = (TextView) mLaykglb.findViewById(R.id.btkgs);
        final TextView mBtlbs = (TextView) mLaykglb.findViewById(R.id.btlbs);
        LinearLayout mKgslayout = (LinearLayout) dialog.findViewById(R.id.laykgs);
        LinearLayout mLbsslayout = (LinearLayout) dialog.findViewById(R.id.laylbs);
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

        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBtnkg && mWkgvalue != 0) {
                    mTinydb.putBoolean(Constants.ISKG_KEY, true);
                    mTinydb.putInt(Constants.WEIGHT_KEY, mWkgvalue);
                    mTvweight.setText(mWkgvalue + " kg");
                } else if (isBtnLb && mWlbvalue != 0) {
                    mTinydb.putBoolean(Constants.ISKG_KEY, false);
                    mTinydb.putInt(Constants.WEIGHT_KEY, mWlbvalue);
                    mTvweight.setText(mWlbvalue + " lb");
                }

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                try {
                    WeightGraphfragments graphfragments = new WeightGraphfragments();
                    transaction.replace(R.id.framechart2, graphfragments).commit();

                } catch (IllegalStateException ignored) {
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


    public FragmentRefreshListener getFragmentRefreshListener() {
        return fragmentRefreshListener;
    }

    public static void setFragmentRefreshListener(FragmentRefreshListener fragmentRefreshListener) {
        fragmentRefreshListener = fragmentRefreshListener;
    }

    private FragmentRefreshListener fragmentRefreshListener;


    public void DialogCalculatebmi() {
        final Dialog dialog = new Dialog(getContext());
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
                        mTinydb.putFloat(Constants.BMI_KEY, b);
                        if (mTinydb.getFloat(Constants.BMI_KEY) != 0) {
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
                    mTinydb.putFloat(Constants.BMI_KEY, (float) b2);
                    if (mTinydb.getFloat(Constants.BMI_KEY) != 0) {
                        mBmiscalelayout.setVisibility(View.VISIBLE);
                        mTxtbmi.setText(String.valueOf(b2) + " KG/M²");
                        initDataToSeekbar();
                    } else {
                        mBmiscalelayout.setVisibility(View.GONE);
                    }
                }
                if (isbtncm && mHcmvalue != 0) {
                    mTinydb.putBoolean(Constants.ISCM_KEY, true);
                    mTinydb.putFloat(Constants.HEIGHT_KEY, mHcmvalue);

                } else if (isbtninch && mHftvalue != 0) {
                    mTinydb.putBoolean(Constants.ISCM_KEY, false);
                    String temp = mHftvalue + "." + mHinchvalue;
                    mTinydb.putFloat(Constants.HEIGHT_KEY, Float.parseFloat(temp));

                }
                if (isBtnkg && mWkgvalue != 0) {
                    mTinydb.putBoolean(Constants.ISKG_KEY, true);
                    mTinydb.putInt(Constants.WEIGHT_KEY, mWkgvalue);
                    mTvweight.setText(mWkgvalue + " kg");
                } else if (isBtnLb && mWlbvalue != 0) {
                    mTinydb.putBoolean(Constants.ISKG_KEY, false);
                    mTinydb.putInt(Constants.WEIGHT_KEY, mWlbvalue);
                    mTvweight.setText(mWlbvalue + " lb");
                }
                mBtncalculate.setVisibility(View.GONE);
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

    public static float round(float d, int decimalPlace) {
        try {
            return BigDecimal.valueOf(d).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP).floatValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
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

    public String cmtofeet(int val) {
        String feetval;
        float mCmval = val;
        float feet = (float) (mCmval / 30.48);
        feetval = String.valueOf(feet);
        return feetval;
    }


    public float calculateBMI(float f, float f2) {
        return (f2 / (f * f));
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

    private void initDataToSeekbar() {
        float floatExtra;
        Resources resources = null;
        int i = 0;
        TextView textView2 = null;
        String str = null;
        this.bmiseekBar.setEnabled(true);
        this.progressItemList = new ArrayList<>();
        this.mProgressItem = new ProgressItem();
        this.mProgressItem.progressItemPercentage = (this.bmivioletSpan / this.bmitotalSpan) * 100.0f;
        this.mProgressItem.color = R.color.violet;
        this.progressItemList.add(this.mProgressItem);
        this.mProgressItem = new ProgressItem();
        this.mProgressItem.progressItemPercentage = ((float) (this.bmiblueSpan / ((double) this.bmitotalSpan))) * 100.0f;
        this.mProgressItem.color = R.color.blue;
        this.progressItemList.add(this.mProgressItem);
        this.mProgressItem = new ProgressItem();
        this.mProgressItem.progressItemPercentage = ((float) (this.bmigreenSpan / ((double) this.bmitotalSpan))) * 100.0f;
        this.mProgressItem.color = R.color.green;
        this.progressItemList.add(this.mProgressItem);
        this.mProgressItem = new ProgressItem();
        this.mProgressItem.progressItemPercentage = (this.bmiyellowSpan / this.bmitotalSpan) * 100.0f;
        this.mProgressItem.color = R.color.yellow;
        this.progressItemList.add(this.mProgressItem);
        this.mProgressItem = new ProgressItem();
        this.mProgressItem.progressItemPercentage = (this.bmiorangeSpan / this.bmitotalSpan) * 100.0f;
        this.mProgressItem.color = R.color.orange;
        this.progressItemList.add(this.mProgressItem);
        this.mProgressItem = new ProgressItem();
        this.mProgressItem.progressItemPercentage = (this.bmiredSpan / this.bmitotalSpan) * 100.0f;
        this.mProgressItem.color = R.color.red;
        this.progressItemList.add(this.mProgressItem);
        this.bmiseekBar.initData(this.progressItemList);
        float floatExtra2 = mTinydb.getFloat(Constants.BMI_KEY);
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
            Toast.makeText(getContext(), "Please Add Valid Data", Toast.LENGTH_SHORT).show();
        }
        this.bmiseekBar.invalidate();
        this.bmiseekBar.setEnabled(false);
    }


    /*@Override
    public void onBackPressed() {

      *//*  Intent intent = new Intent(Activity_Reports.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*//*
        finish();
     *//*   Intent intent = new Intent(Activity_Reports.this, Activity_Reports.class);
        startActivity(intent);
        finish();*//*
    }*/

    public void Smallnative(final Context context, final LinearLayout frameLayout, ShimmerFrameLayout loadlayout) {
        AdLoader.Builder builder = new AdLoader.Builder(context, Constants.admob_nativead);
        builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
            public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                loadlayout.setVisibility(View.GONE);
                mAdcard1.setVisibility(View.VISIBLE);
                frameLayout.setVisibility(View.VISIBLE);
                NativeContentAdView nativeContentAdView = (NativeContentAdView) ((Activity) context).getLayoutInflater().inflate(R.layout.ad_native_banner, null);
                populateSmallContentAdView(nativeContentAd, nativeContentAdView);
                frameLayout.removeAllViews();
                frameLayout.addView(nativeContentAdView);
                Constants.isReportBanner = true;
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(int i) {
                loadlayout.setVisibility(View.GONE);
                mAdcard1.setVisibility(View.GONE);
                Log.e("error", "Failed to load native ad:: " + i);
                FitnessApplication.AdfailToast("Activity_Reports Small Native", String.valueOf(i));
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


    public void updatedayoffweek() {
        today = LocalDate.now();
        if (mTinydb.getInt("fdow") != 0) {
            int dayofweek = mTinydb.getInt("fdow");
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
                Log.e("dates ", day);
                weekgoalmodal.setName(removeLeadingZeroes(day));
                weekgoallist.add(weekgoalmodal);
            }
//            getDaysNames();
            goalAdapter.notifyDataSetChanged();
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
                    mAdcard1.setVisibility(View.VISIBLE);
                    adMobView.setVisibility(View.VISIBLE);
                    Constants.isReportBanner = false;
                }

                @Override
                public void onAdFailedToLoad(int i) {
                    super.onAdFailedToLoad(i);
                    mAdcard1.setVisibility(View.GONE);
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


    public void showGOOGLEAdvance(final Context context, final LinearLayout frameLayout) {
        AdLoader.Builder builder = new AdLoader.Builder(context, Constants.admob_nativead);
        builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
            public void onContentAdLoaded(NativeContentAd nativeContentAd) {
//                loadlayout.setVisibility(View.GONE);
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
//                loadlayout.setVisibility(View.GONE);
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
        ((TextView) nativeContentAdView.getHeadlineView()).setText(nativeContentAd.getHeadline());
        ((TextView) nativeContentAdView.getBodyView()).setText(nativeContentAd.getBody());
        ((TextView) nativeContentAdView.getCallToActionView()).setText(nativeContentAd.getCallToAction());
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


    @Override
    public void onResume() {
        updatedayoffweek();
        if (mTinydb.getBoolean(Constants.ISCM_KEY)) {
            isbtncm = true;
            isbtninch = false;
            mHcmvalue = (int) mTinydb.getFloat(Constants.HEIGHT_KEY);

        } else {
            isbtncm = false;
            isbtninch = true;
            float mData = mTinydb.getFloat(Constants.HEIGHT_KEY);
            String doubleAsString = String.valueOf(mData);
            int indexOfDecimal = doubleAsString.indexOf(".");
            mHftvalue = Integer.parseInt(doubleAsString.substring(0, indexOfDecimal));
            String mTrim = doubleAsString.substring(indexOfDecimal).replace(".", "");
            mHinchvalue = Integer.parseInt(mTrim);

        }
        if (mTinydb.getBoolean(Constants.ISKG_KEY)) {
            isBtnkg = true;
            isBtnLb = false;
            mWkgvalue = mTinydb.getInt(Constants.WEIGHT_KEY);
            mTvweight.setText(mWkgvalue + " kg");
        } else {
            isBtnLb = true;
            isBtnkg = false;
            mWlbvalue = mTinydb.getInt(Constants.WEIGHT_KEY);
            mTvweight.setText(mWlbvalue + " lb");
        }

        if (mTinydb.getBoolean(Constants.ISLOGIN_KEY)) {
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
        super.onResume();
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

    public static String removeLeadingZeroes(String value) {
        return new Integer(value).toString();
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


    public interface FragmentRefreshListener {
        void onRefresh();
    }


}
