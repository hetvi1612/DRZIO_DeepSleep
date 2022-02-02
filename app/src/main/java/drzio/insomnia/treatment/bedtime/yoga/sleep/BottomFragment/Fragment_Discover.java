/*
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Fragment_Discover extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_drawerdiscover,container,false);
    }
}*/

package drzio.insomnia.treatment.bedtime.yoga.sleep.BottomFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
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

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
import com.mannan.translateapi.Language;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderView;
import com.yugansh.tyagi.smileyrating.SmileyRatingView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.AppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.ChooseGenderActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.MaleAppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity.Activity_Blogdetails;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity.BlogData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.BDatabaseOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.DatabaseOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.HistoryProgressOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.MyplanDbhelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.database.DietplanDbhelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Discoverpage.Activity_Discoverexelist;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Graphdata.GraphdataOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Graphdata.WeightGraphdataOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.ApiInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.RateModel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.Register_Api;
import drzio.insomnia.treatment.bedtime.yoga.sleep.SliderNewAdapter;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Banner1;
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

import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.blogdatalist;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.homebannerdatalist1;
import static retrofit2.converter.scalars.ScalarsConverterFactory.create;

public class Fragment_Discover extends Fragment /*implements GoogleApiClient.OnConnectionFailedListener,*//* OnDataPointListener,*//* NavigationView.OnNavigationItemSelectedListener*/ {
    private BackpainAPIInterface apiInterface;
    private RelativeLayout mBlogdetail;
    TinyDB tinyDB;
    private LinearLayout Slidelayout;
    private ShimmerFrameLayout mSlideloader;
    private SliderView sliderView;

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
//    Activity_Discover activity;
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
//    private GoogleApiClient mGoogleApiClient;
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
    private ImageView mBtnmenu;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_discover,container,false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        ImageView mBtnstore = (ImageView) view.findViewById(R.id.ivbtnstore);
        mBtnstore.setBackgroundResource(R.drawable.adsstoreanim);
        AnimationDrawable anim2 = (AnimationDrawable) mBtnstore.getBackground();
        anim2.start();
        mBtnstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cgender=tinyDB.getBoolean(Constants.Genderchoose);
                if (cgender){
                    if (tinyDB.getString(Constants.GENDER_KEY).equals(getResources().getString(R.string.female))) {
                        Intent intent = new Intent(getContext(), AppstoreActivity.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(getContext(), MaleAppstoreActivity.class);
                        startActivity(intent);
                    }
                }else {
                    Intent intent = new Intent(getContext(), ChooseGenderActivity.class);
                    startActivity(intent);
                }
            }
        });
        tinyDB = new TinyDB(getContext());
        token = tinyDB.getString(Constants.Authtoken);
        BASEURL=tinyDB.getString(Constants.NewBaseUrl);

        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(getContext(), languages);


        mBtnmenu = (ImageView) view.findViewById(R.id.btnmenu);
        mBtnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.layout.openDrawer(Gravity.START);
            }
        });
      /*  mNavigationView = (NavigationView)  view.findViewById(R.id.navigation_view);
        layout =  view.findViewById(R.id.drawer);
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

        RelativeLayout mRlayimmunity = (RelativeLayout) view.findViewById(R.id.rlayimmunityexe);
        RelativeLayout mRlayspiritual2 = (RelativeLayout) view.findViewById(R.id.rlayimmunityexe2);
        RelativeLayout mRlayspiritual3 = (RelativeLayout) view.findViewById(R.id.rlayimmunityexe3);
        RelativeLayout mRlayspiritual4 = (RelativeLayout) view.findViewById(R.id.rlayimmunityexe4);
        RelativeLayout mRlayspiritual5 = (RelativeLayout) view.findViewById(R.id.rlayimmunityexe5);
        TextView mTvhybrid = (TextView) view.findViewById(R.id.tvbtnhybridexe);
        ImageView mLock1 = (ImageView) view.findViewById(R.id.imglock1);
        ImageView mLock2 = (ImageView) view.findViewById(R.id.imglock2);
        ImageView mLock3 = (ImageView) view.findViewById(R.id.imglock3);
        ImageView mLock4 = (ImageView) view.findViewById(R.id.imglock4);
//        ImageView mLock5 = (ImageView) view.findViewById(R.id.imglock5);


        if (tinyDB.getBoolean(Constants.PREMIUN_KEY)) {
            mLock1.setVisibility(View.GONE);
            mLock2.setVisibility(View.GONE);
            mLock3.setVisibility(View.GONE);
            mLock4.setVisibility(View.GONE);
//            mLock5.setVisibility(View.GONE);
        }


        Slidelayout = (LinearLayout)  view.findViewById(R.id.slidelayout);
        mSlideloader = (ShimmerFrameLayout)  view.findViewById(R.id.slideloader);
        sliderView =  view.findViewById(R.id.imageSlider);

        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.startAutoCycle();
        //  Slidelayout.setVisibility(View.VISIBLE);
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

        getBanner();
       /* if (homebannerdatalist1.size() != 0) {
            try {
                if (Slidelayout != null) {
                    Slidelayout.setVisibility(View.VISIBLE);
                }
                mSlideloader.setVisibility(View.GONE);
                sliderView.setVisibility(View.VISIBLE);
                final SliderNewAdapter adapter = new SliderNewAdapter(getContext(), homebannerdatalist1);
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

        RelativeLayout banner1 = (RelativeLayout)  view.findViewById(R.id.banner1);
        FrameLayout mGbannerlay2 =  view.findViewById(R.id.adframe1234);
        mGbannerlay2.setVisibility(View.GONE);
      //  showBanner(getContext(), mGbannerlay2, banner1);

       // LinearLayout mAdframebottom = (LinearLayout)  view.findViewById(R.id.adframebottom);
     //   showGOOGLEAdvance2(getContext(), mAdframebottom);
        mRlayimmunity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_Discoverexelist.class);
                intent.putExtra("dname", getResources().getString(R.string.yoga_for_insomnia));
                intent.putExtra("dayprogrss", 0);
                intent.putExtra("level", 4);
                startActivity(intent);
            }
        });

        mRlayspiritual2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_Discoverexelist.class);
                intent.putExtra("dname", getResources().getString(R.string.deep_sleep_yoga));
                intent.putExtra("dayprogrss", 0);
                intent.putExtra("level", 5);
                startActivity(intent);
            }
        });


        mRlayspiritual3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_Discoverexelist.class);
                intent.putExtra("dname", getString(R.string.yog_nidra_effective_sleep));
                intent.putExtra("dayprogrss", 0);
                intent.putExtra("level", 6);
                startActivity(intent);
            }
        });


        mRlayspiritual4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_Discoverexelist.class);
                intent.putExtra("dname", getString(R.string.bed_time_for_sound_sleep));
                intent.putExtra("dayprogrss", 0);
                intent.putExtra("level", 7);
                startActivity(intent);
            }
        });
        mTvhybrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_Discoverexelist.class);
                intent.putExtra("dname", getResources().getString(R.string.sleep_apnea));
                intent.putExtra("dayprogrss", 0);
                intent.putExtra("level", 8);
                startActivity(intent);
            }
        });
        mRlayspiritual5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_Discoverexelist.class);
                intent.putExtra("dname", getString(R.string.bipolar_depresion));
                intent.putExtra("dayprogrss", 0);
                intent.putExtra("level", 9);
                startActivity(intent);
            }
        });

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
                            /*Integer bmrvalue=banners.get(i).getBmr();
                            Log.e("bmevalue", String.valueOf(bmrvalue));
                            tinyDB.putInt(Constants.BMR,bmrvalue);*/
                                ArrayList<Banner1.Doc> docs1 = (ArrayList<Banner1.Doc>) banners.get(i).getDocs();
                                for (int j = 0; j < docs1.size(); j++) {

                                    if (tinyDB.getBoolean(Constants.PREMIUN_KEY)) {
                                        if (!docs1.get(j).getDefaultType().equals("Ads")) {
                                            homebannerdatalist1.add(docs1.get(j));
                                            newList.add(docs1.get(j));
                                            //   Log.e("newList", String.valueOf(homebannerdatalist1.size()));
                                        }
                                    } else {
                                        homebannerdatalist1.add(docs1.get(j));
                                        newList.add(docs1.get(j));
                                        //    Log.e("newList", String.valueOf(homebannerdatalist1.size()));

                                    }
                                    if (Slidelayout != null) {
                                        Slidelayout.setVisibility(View.VISIBLE);
                                    }
                                    if (Slidelayout != null) {
                                        Slidelayout.setVisibility(View.VISIBLE);
                                    }
                                    mSlideloader.setVisibility(View.GONE);
                                    sliderView.setVisibility(View.VISIBLE);
                                    //    Log.e("newList1", String.valueOf(newList));
                                    final SliderNewAdapter adapter = new SliderNewAdapter(getContext(), newList);
                                    //sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
                                    //below method is used to setadapter to sliderview.
                                    sliderView.setSliderAdapter(adapter);
                                    sliderView.setVisibility(View.VISIBLE);
                                    //   final SliderAdapter adapter = new SliderAdapter(context, newList);
                                    sliderView.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
                                    //below method is used to setadapter to sliderview.
                                    sliderView.setSliderAdapter(adapter);
                                    //below method is use to set scroll time in seconds.
                                    //  sliderView.setScrollTimeInSec(3);
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
                        //     Log.e("response1", String.valueOf(t));
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }catch (Exception e){

        }
    }




/*
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
        mTvdiscover.setTextColor(getResources().getColor(R.color.tbtncolor));
        mTvprogress.setTextColor(getResources().getColor(R.color.navunselected));
        mTvearn.setTextColor(getResources().getColor(R.color.navunselected));
        mTvpurchase.setTextColor(getResources().getColor(R.color.navunselected));
        mIvhome3.setColorFilter(getResources().getColor(R.color.grey));
        mIvdiscover3.setColorFilter(getResources().getColor(R.color.tbtncolor));
        mIvProgress3.setColorFilter(getResources().getColor(R.color.grey));
        mIvEarn3.setColorFilter(getResources().getColor(R.color.grey));
        mIvPurchase3.setColorFilter(getResources().getColor(R.color.grey));
        mLaydiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mLayprogress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext()s, Activity_Bloglists.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
                finish();
            }
        });
        mLayhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext()s, MainActivity.class);


                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
                finish();
            }
        });
        mLayearn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext()s, Activity_Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
                finish();
            }
        });
        mLaypurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext()s, Activity_Reports.class);


                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
                finish();
            }
        });
    }
*/

    /*public void NavigationData(View view) {
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
        //  LinearLayout mBtncovid = (LinearLayout) findViewById(R.id.btncovid);
        if (tinyDB.getBoolean(Constants.RATEDIALOGSHOW_KEY)) {
            mLayrate.setVisibility(View.GONE);
        } else {
            mLayrate.setVisibility(View.VISIBLE);
        }
       *//* mBtncovid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click(Activity_Reports.this, 14);
            }
        });*//*
        if (tinyDB.getBoolean(Constants.PREMIUN_KEY)) {
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

    /*public void click(Context context, int i) {
        if (i == 1) {
            String tempdate = tinyDB.getString(Constants.DIETPLANDATE_KEY);
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
                Intent intent5 = new Intent(getContext(), Activity_Covidhome.class);
                intent5.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent5);
                finish();
            }
        }
    }*/

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
                        Intent i = getContext().getPackageManager().getLaunchIntentForPackage(getContext().getPackageName());
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
            String languages = tinyDB.getString(Constants.Language);
        //    Log.e("languages1", languages);
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
                //    Log.e("languages", laun);
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
        mTextexpe.setText(getContext().getResources().getString(R.string.do_you_like_this_app));
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                temprate = (int) (rating - 1);
                Log.d("Rating", String.valueOf(temprate));
                smileyRating.setSmiley(temprate);
                switch (temprate) {
                    case 0:
                        mTextexpe.setText(getContext().getResources().getString(R.string.hated_it));
                        break;
                    case 1:
                        mTextexpe.setText(getContext().getResources().getString(R.string.dislike_it));
                        break;
                    case 2:
                        mTextexpe.setText(getContext().getResources().getString(R.string.its_ok));
                        break;
                    case 3:
                        mTextexpe.setText(getContext().getResources().getString(R.string.liked_it));
                        break;
                    case 4:
                        mTextexpe.setText(getContext().getResources().getString(R.string.loved_it));
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
        Uri uri1 = Uri.parse("https://play.google.com/store/apps/details?id=" + getContext().getPackageName());
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri1);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(getContext(), "You don't have Google Play installed", Toast.LENGTH_LONG).show();
        }
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
                //callFeedbackApi(mOp1, mOp2, mOp3, mOp4, mOp5, mComment, mUid);

                String device_name = Build.BRAND + ", " + Build.DEVICE + ", " + Build.MANUFACTURER + ", " + BuildConfig.VERSION_NAME;
                String androidId = 22+Settings.Secure.getString(getContext().getContentResolver(), Settings.Secure.ANDROID_ID)+22;
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
    }*/


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

        mGoogleApiClient = new GoogleApiClient.Builder(getContext())
                .enableAutoManage(getActivity(), this)
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

   /* @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {

            case REQUEST_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    isFromFb = false;
                    isFromGoogle = true;
                    signIn();
                    //Toast.makeText(MainActivity.this, "Permission granted.", Toast.LENGTH_SHORT).show();

                  *//*  finish();
                    startActivity(getIntent());*//*

                } else {
                    Toast.makeText(getContext(), "The app was not allowed to get your location, Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }
            }

        }

    }*/

  /*  private void signOut() {
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


   /* private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, SIGN_IN_CODE);
    }*/

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
      //      Log.e("getDisplayLanguage", langauge);

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

                        /*    callRegisterApi(first_name, last_name, email, mobile_number, gender,
                                    age, height, weight, user_type_id, paid_status, country_id,
                                    state_id, city_id, fcm_token, facebook, google, user_image);
*/
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
                     //   Log.e("token", String.valueOf(dietCateData.token));
                        tinyDB.putString(Constants.Authtoken, dietCateData.token);

                        String userid = docs.getId();
                        tinyDB.putString(Constants.USERID_KEYs, userid);

                   //     Log.e("getfName", String.valueOf(docs.getfName()));
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
            } else if (mealtype.equals("Non-Veg")) {
                tinyDB.putInt(Constants.MEALTYPE_KEY, 2);
            } else if (mealtype.equals("Vegan")) {
                tinyDB.putInt(Constants.MEALTYPE_KEY, 3);
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
                        RandomBlog(getView());
                    } catch (Exception e) {

                    }
                }

                @Override
                public void onFailure(@NotNull Call<BlogData> call, @NotNull Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void RandomBlog(View view) {
        try {
            ArrayList<BlogData.Datalist> bloglist = blogdatalist;
            ImageView thumbImage = (ImageView) view.findViewById(R.id.imgthumb);
            TextView mTxtName = (TextView) view.findViewById(R.id.name);
            HtmlTextView mTxtdesc = (HtmlTextView) view.findViewById(R.id.descriptions);
            TextView mTemptipstxt = (TextView) view.findViewById(R.id.txttip);
            View mTempview = (View) view.findViewById(R.id.tipview);
            TextView mBtnmore = (TextView) view.findViewById(R.id.btnmore);
            mBlogdetail = (RelativeLayout) view.findViewById(R.id.blogdetail);
            if (bloglist.size() != 0) {
                try {
                    mTemptipstxt.setVisibility(View.VISIBLE);
                    mTempview.setVisibility(View.VISIBLE);
                    mBlogdetail.setVisibility(View.VISIBLE);
                    int temp = blogdatalist.size() - 1;
                    int random = getRandomNumberInRange(0, temp);
                    BlogData.Datalist data = bloglist.get(random);
                    RequestOptions requestOptions = new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL);
                    Glide.with(FitnessApplication.getInstance()).load(data.getImage())
                            .apply(requestOptions).into(thumbImage);
                    mTxtName.setText(data.getName());
                    mTxtdesc.setHtml(data.getDescription());
                    mTxtdesc.setMaxLines(2);
                    mBlogdetail.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getContext(), Activity_Blogdetails.class);
                            intent.putExtra("bid", data.getId());
                            startActivity(intent);

                        }
                    });
                    mBtnmore.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(getContext(), MainActivity.class);
                           // intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            Bundle data1 = new Bundle();
                            data1.putInt("discover",4);
                            intent.putExtras(data1);
                         //   intent.putExtra("isFrom3", true);
                            startActivity(intent);

                           /* Intent intent = new Intent(getContext(), Activity_Bloglists.class);
                            startActivity(intent);*/
//                            finish();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                mTemptipstxt.setVisibility(View.GONE);
                mTempview.setVisibility(View.GONE);
                mBlogdetail.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int getRandomNumberInRange(int min, int max) {
        try {
            if (min >= max) {
                throw new IllegalArgumentException("max must be greater than min");
            }
            Random r = new Random();
            return r.nextInt((max - min) + 1) + min;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    @Override
    public void onResume() {
//        if (blogdatalist != null && blogdatalist.size() != 0) {
////            RandomBlog(getView());
//        } else {
//            //  callBlogApi();
//        }
       /* if (tinyDB.getBoolean(Constants.ISLOGIN_KEY)) {
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
        super.onResume();
    }


    public void showGOOGLEAdvance2(final Context context, final LinearLayout frameLayout) {
        AdLoader.Builder builder = new AdLoader.Builder(context, Constants.admob_nativead);
        builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
            public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                //  mAdlay.setVisibility(View.VISIBLE);
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

    @Override
    public void onPause() {
        super.onPause();
    }


   /* @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        return false;
    }*/
}
