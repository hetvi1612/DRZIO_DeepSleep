/*package drzio.insomnia.treatment.bedtime.yoga.sleep.BottomFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Fragment_blogs extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_drawerbloglist,container,false);
    }
}*/


package drzio.insomnia.treatment.bedtime.yoga.sleep.BottomFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.Settings;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.github.abdularis.civ.AvatarImageView;
import com.github.javiersantos.bottomdialogs.BottomDialog;
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
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.makeramen.roundedimageview.RoundedImageView;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.AppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.ChooseGenderActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.MaleAppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity.Activity_ShowBlogwebview;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity.Adapter_addComment;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity.DBHelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity.TimeAgo2;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity.model.AddLike;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity.model.BlogList;
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
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.AddComment;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.ApiInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.GetComment;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.Register_Api;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.LoginData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIClient;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_blogs extends Fragment /*implements*//* OnDataPointListener,*//**//* NavigationView.OnNavigationItemSelectedListener,*//* GoogleApiClient.OnConnectionFailedListener*/ {

    RecyclerView mBottomrecycler;
    private boolean isActive;
    TinyDB tinyDB;
    private ProgressDialog pd;
    private RelativeLayout mLaynodata;
    boolean isclicked = false;
    Fragment_blogs activity;
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
    //    public static DrawerLayout layout;
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
    private ProgressBar loadingPB;
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
    ArrayList<Integer> blogcommnet = new ArrayList<Integer>();


    int page = 1, limit = 10;
    ConnectivityManager mgr;
    NetworkInfo netInfo;
    private RelativeLayout leaderbord;
    private RelativeLayout dataisnotfound;
    private ImageView mBtnmenu;
    Button retry;
    public static Adapter_Allblogs1 adapterTopblogs1;
    public static Adapter_Allblogs adapterTopblogs;
    private RecyclerView commentrecyclerview;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_allblogs, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getActivity().getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
      /*  Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isActive = bundle.getBoolean("isFrom3");
        }*/

//        activity = this;

        mLaynodata = (RelativeLayout) view.findViewById(R.id.no_video);
        mLoadlay = (RelativeLayout) view.findViewById(R.id.loadlayout);
        nestedSV = view.findViewById(R.id.idNestedSV);
        mBottomrecycler = (RecyclerView) view.findViewById(R.id.bottomrecycler);
        loadingPB = view.findViewById(R.id.idPBLoading);
        retry = (Button) view.findViewById(R.id.retry);

        LottieAnimationView mLottie = (LottieAnimationView) view.findViewById(R.id.lotti2);
        mLottie.setVisibility(View.VISIBLE);
        mLottie.setAnimation("loadanimdial.json");
        mLottie.playAnimation();
        mLottie.loop(true);
        mLoadlay.setVisibility(View.GONE);
        Button mBtnrefresh = (Button) view.findViewById(R.id.btrefresh);
        RelativeLayout mBottomlay = (RelativeLayout) view.findViewById(R.id.bottombar);
        ImageView mBtnback = (ImageView) view.findViewById(R.id.btnback);
        /*mBtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layout.openDrawer(Gravity.START);
            }
        });*/
     /*   LinearLayout mAdframe = (LinearLayout) view.findViewById(R.id.adframe2);
        FrameLayout mAdframe4 = (FrameLayout) view.findViewById(R.id.adframe4);
        if (Constants.isBlogBanner) {
            mAdframe.setVisibility(View.GONE);
            showBanner(this, mAdframe4);
        } else {
            mAdframe4.setVisibility(View.GONE);
            Smallnative(this, mAdframe);
        }*/

        ImageView mBtnstore = (ImageView) view.findViewById(R.id.ivbtnstore);
        mBtnstore.setBackgroundResource(R.drawable.adsstoreanim);
        AnimationDrawable anim2 = (AnimationDrawable) mBtnstore.getBackground();
        anim2.start();
        mBtnstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cgender = tinyDB.getBoolean(Constants.Genderchoose);
                if (cgender) {
                    if (tinyDB.getString(Constants.GENDER_KEY).equals(getResources().getString(R.string.female))) {
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


        tinyDB = new TinyDB(getContext());
//        mNavigationView = (NavigationView) view.findViewById(R.id.navigation_view);
//        layout = view.findViewById(R.id.drawer);
//        mNavigationView.setNavigationItemSelectedListener(this);
//        activity = this;
        feedbacklist = getResources().getStringArray(R.array.feedlist);

        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(getContext(), languages);

        databaseOperations = new DatabaseOperations(getContext());
        databaseOperations2 = new BDatabaseOperations(getContext());
        graphdataOperations = new GraphdataOperations(getContext());
        weightGraphdataOperations = new WeightGraphdataOperations(getContext());
        historyProgressOperations = new HistoryProgressOperations(getContext());
        myplanDbhelper = new MyplanDbhelper(getContext());
        dietplanDbhelper = new DietplanDbhelper(getContext());

        token = tinyDB.getString(Constants.Authtoken);

        BASEURL = tinyDB.getString(Constants.NewBaseUrl);
        mBtnmenu = (ImageView) view.findViewById(R.id.btnback);
        mBtnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.layout.openDrawer(Gravity.START);
            }
        });
        leaderbord = view.findViewById(R.id.blog_rela);
        mLaynodata = (RelativeLayout) view.findViewById(R.id.noiternets);
        dataisnotfound = (RelativeLayout) view.findViewById(R.id.dataisnotfound);
        mgr = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        netInfo = mgr.getActiveNetworkInfo();
        if (netInfo != null) {
            if (netInfo.isConnected()) {
                leaderbord.setVisibility(View.VISIBLE);
                mLaynodata.setVisibility(View.GONE);
                dataisnotfound.setVisibility(View.GONE);

            } else {
                if (mLaynodata.getVisibility() == View.GONE) {
                    leaderbord.setVisibility(View.GONE);
                    mLaynodata.setVisibility(View.VISIBLE);
                    dataisnotfound.setVisibility(View.GONE);
                }
            }
        } else {
            if (mLaynodata.getVisibility() == View.GONE) {
                leaderbord.setVisibility(View.GONE);
                mLaynodata.setVisibility(View.VISIBLE);
                dataisnotfound.setVisibility(View.GONE);
            }
        }
        mBtnrefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadlay.setVisibility(View.VISIBLE);
                //  callBlogApi();
                getBlog(page, limit, token);

            }
        });


        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingPB.setVisibility(View.VISIBLE);
                getBlog(page, limit, token);
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
                    getBlog(page, limit, token);

                }
            }
        });
        //  callBlogApi();
        getBlog(page, limit, token);
       /* if (blogdatalist != null && blogdatalist.size() != 0) {
            Blogdata(blogdatalist);
        } else {
            mLoadlay.setVisibility(View.VISIBLE);
            callBlogApi();
        }*/


        // callBlogApi(view);
//        NavigationData(view);
//        InitLoginlayouts(view);
//        initbottomnavigation();

        return view;
    }

    private void getBlog(int page, int limit, String token) {
        try {


            this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);

            this.apiInterface.getBlogs1(page, limit, token).enqueue(new Callback<List<BlogList>>() {
                @Override
                public void onResponse(Call<List<BlogList>> call, Response<List<BlogList>> response) {
                    try {
                        loadingPB.setVisibility(View.GONE);
                        dataisnotfound.setVisibility(View.GONE);
                        mLaynodata.setVisibility(View.GONE);
                        leaderbord.setVisibility(View.VISIBLE);
                        Log.e("fdffg", String.valueOf(response.body()));
                        ArrayList<BlogList> blogLists = (ArrayList<BlogList>) response.body();

                        for (int i = 0; i < blogLists.size(); i++) {
                            Log.e("Tag", "data" + blogLists);


                            blodid.add(blogLists.get(i).getId());
                            blodname.add(blogLists.get(i).getBlogName());
                            blodimage.add(blogLists.get(i).getImage());
                            blodlink.add(blogLists.get(i).getBlogLink());
                            blodblog_Des.add(blogLists.get(i).getMeta_description());
                            blodlikestatus.add(blogLists.get(i).getLikeStatus());
                            blodbloglike.add(blogLists.get(i).getBlogLike());
                            blodcreated_at.add(blogLists.get(i).getCreatedAt());
                            blodupdated_at.add(blogLists.get(i).getUpdatedAt());
                            blogcommnet.add(blogLists.get(i).getBlog_comment());


                            Log.e("bloddatalist", String.valueOf(blodid));
                            Log.e("bloddatalist", String.valueOf(blodname));


                        }


                        Log.e("bloddatalist1", String.valueOf(blodname));
                        Log.e("priceresponse", String.valueOf(response.body()));
                        mBottomrecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

                        if (!tinyDB.getBoolean(Constants.PREMIUN_KEY)) {
                            adapterTopblogs1 = new Adapter_Allblogs1(getContext(), blodid, blodname, blodimage, blodlink, blodblog_Des, blodlikestatus,
                                    blodbloglike, blodcreated_at, blodupdated_at, blogcommnet);
                            mBottomrecycler.setAdapter(adapterTopblogs1);
                        } else {
                            adapterTopblogs = new Adapter_Allblogs(getContext(), blodid, blodname, blodimage, blodlink, blodblog_Des, blodlikestatus, blodbloglike, blodcreated_at,
                                    blodupdated_at, blogcommnet);
                            mBottomrecycler.setAdapter(adapterTopblogs);

                        }

                    } catch (Exception e) {
                        Log.e("idee", String.valueOf(e));
                        loadingPB.setVisibility(View.GONE);

                    }

                }

                @Override
                public void onFailure(Call<List<BlogList>> call, Throwable t) {
                    Log.e("response1tt", String.valueOf(t));
                    loadingPB.setVisibility(View.GONE);
                    //dataisnotfound.setVisibility(View.VISIBLE);
                    mLaynodata.setVisibility(View.VISIBLE);
                }
            });

        } catch (Exception e) {
            //  dataisnotfound.setVisibility(View.VISIBLE);
            mLaynodata.setVisibility(View.VISIBLE);
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


/*
    private void callBlogApi(View view) {
        try {
            this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);

            //   String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiIxMDJhMGNmNjc0NjZjYWNjIiwiaWF0IjoxNjI2MzIxMDcwfQ.v8_1g2RFNemXKR24C2bVTqbWrnZ7FZh7drO5M13-XzY";
            this.apiInterface.getBlog1(token).enqueue(new Callback<List<BlogList>>() {
                @Override
                public void onResponse(@NotNull Call<List<BlogList>> call, @NotNull Response<List<BlogList>> response) {
                    try {
                        Log.e("repspoxeblog", String.valueOf(response.body()));
                        loadingPB.setVisibility(View.GONE);
                        mBottomrecycler.setVisibility(View.VISIBLE);
                        ArrayList<BlogList> blogLists = (ArrayList<BlogList>) response.body();

                        for (int i = 0; i < blogLists.size(); i++) {
                            Log.e("Tag", "data" + blogLists);

                            String id = blogLists.get(i).getId();
                            String link = blogLists.get(i).getBlogLink();
                            String name = blogLists.get(i).getBlogName();
                            boolean likestatus = blogLists.get(i).getLikeStatus();
                            String blog_Des = blogLists.get(i).getMeta_description();
                            Log.e("Tag", "id" + id);
                            Log.e("Tag", "link" + link);
                            Log.e("Tag", "name" + name);
                            Log.e("Tag", "likestatus" + likestatus);
                            Log.e("Tag", "blog_Des" + blog_Des);


                        }

                        mBottomrecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
//                        Adapter_Allblogs adapterTopblogs = new Adapter_Allblogs(getContext(), blogLists, getActivity());
                        //Adapter_Allblogs adapterTopblogs = new Adapter_Allblogs(getActivity(), blogLists, getActivity());
                        mBottomrecycler.setAdapter(adapterTopblogs);
                        Log.e("Tag", "data" + response);

                    } catch (Exception e) {
                        Log.e("repspoxeblogcatch", String.valueOf(e));
                        mLoadlay.setVisibility(View.GONE);
                        mLaynodata.setVisibility(View.VISIBLE);
                        e.printStackTrace();

                        loadingPB.setVisibility(View.VISIBLE);
                        mBottomrecycler.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onFailure(@NotNull Call<List<BlogList>> call, @NotNull Throwable t) {
                    loadingPB.setVisibility(View.VISIBLE);
                    mBottomrecycler.setVisibility(View.GONE);
                }
            });
        } catch (Exception e) {
            loadingPB.setVisibility(View.VISIBLE);
            mBottomrecycler.setVisibility(View.GONE);
            e.printStackTrace();
        }

    }
*/


    @Override
    public void onDestroy() {
        super.onDestroy();

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
                    Toast.makeText(getContext(), "The app was not allowed to get your location, Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }
            }

        }

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


    @Override
    public void onResume() {
        try {
            commentrecyclerview.notifyAll();
            mBottomrecycler.invalidate();

            if (!tinyDB.getBoolean(Constants.PREMIUN_KEY)) {
                adapterTopblogs1 = new Adapter_Allblogs1(getContext(), blodid, blodname, blodimage, blodlink, blodblog_Des, blodlikestatus,
                        blodbloglike, blodcreated_at, blodupdated_at, blogcommnet);
                mBottomrecycler.setAdapter(adapterTopblogs1);
            } else {
                adapterTopblogs = new Adapter_Allblogs(getContext(), blodid, blodname, blodimage, blodlink, blodblog_Des,
                        blodlikestatus, blodbloglike, blodcreated_at, blodupdated_at, blogcommnet);
                mBottomrecycler.setAdapter(adapterTopblogs);

            }
            if (adapterTopblogs1 != null) {
                adapterTopblogs1.notifyDataSetChanged();
            }
            if (adapterTopblogs != null) {
                adapterTopblogs.notifyDataSetChanged();
            }

        } catch (Exception e) {

        }
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
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
/*
                            callRegisterApi(first_name, last_name, email, mobile_number, gender,
                                    age, height, weight, user_type_id, paid_status, country_id,
                                    state_id, city_id, fcm_token, facebook, google, user_image);*/

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
            String getusertype = tinyDB.getString(Constants.USERTYPEKEY);
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


    /*public void Blogdata(ArrayList<BlogData.Datalist> datalists,View view) {
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
        LinearLayout mGbannerlay = view.findViewById(R.id.adframe);
        mGbannerlay.setVisibility(View.GONE);
        showGOOGLEAdvance(getContext(), mGbannerlay);
      *//*  RecyclerView mBottomrecycler = (RecyclerView) findViewById(R.id.bottomrecycler);
        mBottomrecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        Adapter_Allblogs adapterTopblogs = new Adapter_Allblogs(getContext(), recyclerViewItems, activity);
        mBottomrecycler.setAdapter(adapterTopblogs);*//*
    }*/

    public void showGOOGLEAdvance(final Context context, final LinearLayout frameLayout) {
        AdLoader.Builder builder = new AdLoader.Builder(context, Constants.admob_nativead);
        builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
            public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                frameLayout.setVisibility(View.VISIBLE);
                NativeContentAdView       nativeContentAdView = (NativeContentAdView) ((Activity) context).getLayoutInflater().inflate(R.layout.ad_gnativelaytwo, null);
                populateContentAdView(nativeContentAd, nativeContentAdView);
                frameLayout.removeAllViews();
                frameLayout.addView(nativeContentAdView);
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(int i) {
                Log.e("error", "Failed to load native ad:: " + i);
                NativeContentAdView        nativeContentAdView = (NativeContentAdView) ((Activity) context).getLayoutInflater().inflate(R.layout.ad_content1, null);
                frameLayout.setVisibility(View.GONE);
                nativeContentAdView.setVisibility(View.GONE);
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


   /* @Override
    public void onBackPressed() {
        if (isActive) {
          *//*  Intent intent = new Intent(getContext(), Activity_Reports.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);*//*
            finish();
        } else {
         *//*   Intent intent = new Intent(getContext(), MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);*//*
            finish();
        }
    }*/

    @Override
    public void onStart() {
        super.onStart();
    }

    public class Adapter_Allblogs1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        ArrayList personNames;
        //  ArrayList<BlogList> blogmodelList;
        Context context;
        DBHelper mDbhelper;

        Activity activity;
        TinyDB tinyDB;
        // List<Catagorylist> catagorylists;
        Boolean clicked = true;
        private int mPosition;
        private int mPositioncomment;
        String imageurl = "https://drzio-android.s3.ap-south-1.amazonaws.com/blog/";
        String userId;
        boolean addlike = false;
        private String imagename;
        int pos;
        ArrayList<String> blog_arrayname = new ArrayList<>();
        RecyclerView.ViewHolder holder;
        String userID;
        private BackpainAPIInterface apiInterface;
        private String token;
        private String BASEURL;

        ArrayList<String> blodid = new ArrayList<>();
        ArrayList<String> blodname = new ArrayList<>();
        ArrayList<List<String>> blodimage = new ArrayList<List<String>>();
        ArrayList<String> blodlink = new ArrayList<>();
        ArrayList<String> blodblog_Des = new ArrayList<>();
        ArrayList<Boolean> blodlikestatus = new ArrayList<Boolean>();
        ArrayList<Integer> blodbloglike = new ArrayList<Integer>();
        ArrayList<String> blodcreated_at = new ArrayList<>();
        ArrayList<String> blodupdated_at = new ArrayList<>();
        ArrayList<Integer> blogcommnet = new ArrayList<>();
        private final int ADS = 1, DATA = 0;
        private String musicurl;
        int page = 1, limit = 10;

  /*  public Adapter_Allblogs1(Context context, ArrayList<BlogList> blogmodelList, Activity_Bloglists activity) {
        this.context = context;
        this.blogmodelList = blogmodelList;
        mDbhelper = new DBHelper(context);
        this.userID = userID;


    }

    public Adapter_Allblogs1(Context context, ArrayList<BlogList> blogmodelList) {
        this.context = context;
        this.blogmodelList = blogmodelList;
        mDbhelper = new DBHelper(context);
        this.userID = userID;
    }*/


        public Adapter_Allblogs1(Context context, ArrayList<String> blodid1, ArrayList<String> blodname1, ArrayList<List<String>> blodimage1,
                                 ArrayList<String> blodlink1, ArrayList<String> blodblog_des1, ArrayList<Boolean> blodlikestatus1,
                                 ArrayList<Integer> blodbloglike1, ArrayList<String> blodcreated_at1, ArrayList<String> blodupdated_at1,
                                 ArrayList<Integer> blogcommnet1) {
            this.context = context;
            this.blodid = blodid1;
            blodname = blodname1;
            blodimage = blodimage1;
            blodlink = blodlink1;
            blodblog_Des = blodblog_des1;
            blodlikestatus = blodlikestatus1;
            blodbloglike = blodbloglike1;
            blodcreated_at = blodcreated_at1;
            blodupdated_at = blodupdated_at1;
            blogcommnet = blogcommnet1;
            mDbhelper = new DBHelper(context);
            tinyDB = new TinyDB(context);
        }

        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           /* View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blogdata, parent, false);
            MyViewHolder vh = new MyViewHolder(v);
            return vh;*/


            RecyclerView.ViewHolder viewHolder = null;
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            switch (viewType) {
                case DATA:
                    View v2 = inflater.inflate(R.layout.item_blogdata, parent, false);
                    viewHolder = new BlogdataHolder(v2);
                    break;
                case ADS:
                    View v1 = inflater.inflate(R.layout.ad_content1, parent, false);
                    viewHolder = new AdsHolder(v1);
                    break;
                default:
            }
            assert viewHolder != null;
            return viewHolder;
        }


 /*   @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blogdata, parent, false);
        Adapter_Allblogs1.BlogdataHolder vh = new Adapter_Allblogs1.BlogdataHolder(v);
        tinyDB = new TinyDB(context);
        return vh;
    }*/


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holders, final int pos) {
            switch (holders.getItemViewType()) {
                case ADS:
                    AdsHolder adHolder = (AdsHolder) holders;
                    try {
                        showGOOGLEAdvance(context, adHolder.mRelativeFBAdmob);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    break;
                case DATA:
                    //  final int position = pos - Math.round(pos / 2);
                    final int position = pos - Math.round(pos / 4);
                    BlogdataHolder holder = (BlogdataHolder) holders;
                    token = tinyDB.getString(Constants.Authtoken);
                    BASEURL = tinyDB.getString(Constants.NewBaseUrl);
                    TimeAgo2 timeAgo2 = new TimeAgo2();


                    ArrayList<String> images = (ArrayList<String>) blodimage.get(position)/*.getImage()*/;
                    String[] imagearray = new String[images.size()];
                    for (int i1 = 0; i1 < images.size(); i1++) {
                        imagearray[i1] = images.get(i1);
                        Log.e("imagesname", String.valueOf(images.get(i1)));
                    }

                    try {
                        imagename = blodimage.get(position)/*.getImage()*/.get(0);
                    } catch (Exception e) {
                        Log.e("exception", e.toString());
                    }


                    holder.btnexestart.setText("Following");


                    holder.blog_catagoryname.setText(context.getResources().getString(R.string.height_increase_workout));


                    Log.e("size", String.valueOf(blog_arrayname.size()));
                    Log.e("blogsize", String.valueOf(blodname.size()));
                    holder.nameofblog.setText(blodname.get(position)/*.getBlogName()*/);


                    ArrayList<String> listlikes = mDbhelper.getLikeid();
                    for (int i = 0; i < listlikes.size(); i++) {
                        String likeid = listlikes.get(i);
                        if (likeid != null && listlikes.contains(blodid.get(position)/*.getId()*/)) {
                            holder.like.setChecked(true);
                            holder.like.setBackground(context.getResources().getDrawable(R.drawable.heartlike));
                        } else {
                            holder.like.setChecked(false);
                            holder.like.setBackground(context.getResources().getDrawable(R.drawable.heart1));

                        }
                    }
                    holder.like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                  /*  if (netInfo != null) {
                        if (netInfo.isConnected()) {
                            leaderbord.setVisibility(View.VISIBLE);
                            mLaynodata.setVisibility(View.GONE);*/
                            if (isChecked) {
                                int likeadd = Integer.valueOf(blodbloglike.get(position)/*.getBlogLike()*/) + 1;
                                String likes = String.valueOf(likeadd);

                                if (blogcommnet.get(position) == 0) {
                                    holder.addcomment.setText(likes + " Likes");
                                } else {
                                    holder.addcomment.setText(likes + " Likes  . "
                                            + String.valueOf(blogcommnet.get(position)) + " Comments");
                                }

                                CallAddlike(blodid.get(position)/*.getId()*/, position);
                                mDbhelper.insertContact(blodid.get(position)/*.getId()*/);
                                holder.like.setBackground(context.getResources().getDrawable(R.drawable.heartlike));
                            } else {
                                CallAddunlike(blodid.get(position)/*.getId()*/, position);
                                holder.addcomment.setText(String.valueOf(blodbloglike.get(position)/*.getBlogLike()*/));
                                mDbhelper.deleteContact(blodid.get(position)/*.getId()*/);
                                holder.like.setBackground(context.getResources().getDrawable(R.drawable.heart1));


                                if (blogcommnet.get(position)/*.blogComment.size()*/ == 0) {
                                    holder.addcomment.setText(String.valueOf(blodbloglike.get(position)/*.getBlogLike()*/) + " Likes");
                                } else {
                                    holder.addcomment.setText(String.valueOf(blodbloglike.get(position)/*.getBlogLike()*/) + " Likes  . "
                                            + String.valueOf(blogcommnet.get(position)/*.blogComment.size()*/) + " Comments");
                                }
                                holder.addcomment.setText(String.valueOf(blodbloglike.get(position)/*.getBlogLike()*/) + " Likes");
                            }


                        }
                    });

                    try {
                        int blog_likess = blodbloglike.get(position)/*.getBlogLike()*/;

                        if (blog_likess != 0) {
                            holder.addcomment.setText(blog_likess + " Likes");
                        }
                        if (blogcommnet.get(position)/*.blogComment.size()*/ > 0) {
                            holder.addcomment.setText(String.valueOf(blogcommnet.get(position)/*.blogComment.size()*/) + " Comments");
                        }
                        if (blog_likess != 0 && blogcommnet.get(position)/*.blogComment.size()*/ > 0) {
                            holder.addcomment.setText(blog_likess + " Likes  . "
                                    + String.valueOf(blogcommnet.get(position)/*.blogComment.size()*/) + " Comments");
                        }


                        if (blog_likess == 0 && blogcommnet.get(position)/*.blogComment.size()*/ == 0) {
                            holder.addcomment.setText("Write a Comment");
                        }
                    } catch (Exception e) {

                    }

                    // holder.addcomment.setText(String.valueOf(blodbloglike.get(position)/*.getBlogLike()*/) + " Likes");

                    holder.addcomment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mPositioncomment = position;
                            DialogComment();

                        }
                    });
                    SliderAdapter adapter = new SliderAdapter(context, blodimage.get(position)/*.getImage()*/);
                    holder.slider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
                    holder.slider.setSliderAdapter(adapter);
                    holder.slider.setScrollTimeInSec(7);
                    holder.slider.setAutoCycle(true);
                    holder.slider.startAutoCycle();

                    if (blodimage.get(position)/*.getImage()*/.size() == 1) {
              /*  holder.slider.setVisibility(View.GONE);
                Log.e("gone","gone");
                Glide.with(getContext())
                        .load(imageurl + blogmodelList.get(position).getImage().get(0))
                        .into(holder.ivicon1);*/
                        holder.slider.setFocusable(false);
                    } else {
                        Log.e("gone1", "gone1");
                        //   holder.ivicon1.setVisibility(View.GONE);
                        holder.slider.setVisibility(View.VISIBLE);
                        holder.slider.setScrollTimeInSec(7);
                        holder.slider.setAutoCycle(true);
                        holder.slider.startAutoCycle();
                    }
                    holder.blogdescription.setHtml(blodblog_Des.get(position)/*.getBlogDescription()*/);








/*
        if (blogmodelList.get(position).getBlogDescription() != null) {
            Log.e("desss",blogmodelList.get(position).getBlogDescription());
            holder.blogdescription.setVisibility(View.VISIBLE);
            holder.blogdescription.setHtml(blogmodelList.get(position).getBlogDescription());
        } else {
            holder.blogdescription.setVisibility(View.GONE);
        }*/

                    try {
                        URL url = new URL(blodlink.get(position)/*.getBlogLink()*/);
                        String host = url.getHost();
                        holder.link.setText(host);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }

                    try {
                        holder.time_text.setText(timeAgo2.covertTimeToText(blodcreated_at.get(position)/*.getCreatedAt()*/));
                    } catch (Exception e) {

                    }


                  /*  holder.slider.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (!blodlink.get(position)*//*.getBlogLink()*//*.isEmpty()) {
                                Log.e("share", "gfgdedd");
                                Intent intent = new Intent(context, Activity_ShowBlogwebview.class);
                                intent.putExtra("link", blodlink.get(position)*//*.getBlogLink()*//*);
                                intent.putExtra("id", blodid.get(position)*//*.getId()*//*);
                                intent.putExtra("position", position);
                                intent.putExtra("userid", userId);
                                intent.putExtra("blogname", blodname.get(position)*//*.getBlogName()*//*);
                                intent.putExtra("bloglink", blodlink.get(position)*//*.getBlogLink()*//*);
                                intent.putExtra("bloglike", blodbloglike.get(position)*//*.getBlogLike()*//*);
                                String[] blogdata = new String[blodname.size()];
                                intent.putExtra("blogmodelList", String.valueOf(blogdata));
                                try {
                                    if (!imagename.isEmpty()) {
                                        intent.putExtra("image", blodimage.get(position)*//*.getImage()*//*.get(0));
                                    }
                                } catch (Exception e) {

                                }

                                context.startActivity(intent);
                            } else {
                                //  Toast.makeText(context, context.getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                            }
                        }
                    });*/

                    holder.link.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Log.e("share", "gfg");
                   /* if (netInfo != null) {
                        if (netInfo.isConnected()) {
                            leaderbord.setVisibility(View.VISIBLE);
                            mLaynodata.setVisibility(View.GONE);*/
                            if (!blodlink.get(position)/*.getBlogLink()*/.isEmpty()) {
                                Log.e("share", "gfgdedd");
                                Intent intent = new Intent(context, Activity_ShowBlogwebview.class);
                                intent.putExtra("link", blodlink.get(position)/*.getBlogLink()*/);
                                intent.putExtra("id", blodid.get(position)/*.getId()*/);
                                intent.putExtra("position", position);
                                intent.putExtra("userid", userId);
                                intent.putExtra("blogname", blodname.get(position)/*.getBlogName()*/);
                                intent.putExtra("bloglink", blodlink.get(position)/*.getBlogLink()*/);
                                intent.putExtra("bloglike", blodbloglike.get(position)/*.getBlogLike()*/);
                                String[] blogdata = new String[blodname.size()];
                                intent.putExtra("blogmodelList", String.valueOf(blogdata));
                                try {
                                    if (!imagename.isEmpty()) {
                                        intent.putExtra("image", blodimage.get(position)/*.getImage()*/.get(0));
                                    }
                                } catch (Exception e) {

                                }

                                context.startActivity(intent);
                            } else {
                                //  Toast.makeText(context, context.getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                            }
                     /*   } else {

                            leaderbord.setVisibility(View.GONE);
                            mLaynodata.setVisibility(View.VISIBLE);
                        }
                    } else {
                        leaderbord.setVisibility(View.GONE);
                        mLaynodata.setVisibility(View.VISIBLE);
                    }*/

                        }
                    });


                    holder.mBlogdetail.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Log.e("share", "gfg");
                   /* if (netInfo != null) {
                        if (netInfo.isConnected()) {
                            leaderbord.setVisibility(View.VISIBLE);
                            mLaynodata.setVisibility(View.GONE);*/
                            if (!blodlink.get(position)/*.getBlogLink()*/.isEmpty()) {
                                Log.e("share", "gfgdedd");
                                Intent intent = new Intent(context, Activity_ShowBlogwebview.class);
                                intent.putExtra("link", blodlink.get(position)/*.getBlogLink()*/);
                                intent.putExtra("id", blodid.get(position)/*.getId()*/);
                                intent.putExtra("position", position);
                                intent.putExtra("userid", userId);
                                intent.putExtra("blogname", blodname.get(position)/*.getBlogName()*/);
                                intent.putExtra("bloglink", blodlink.get(position)/*.getBlogLink()*/);
                                intent.putExtra("bloglike", blodbloglike.get(position)/*.getBlogLike()*/);
                                String[] blogdata = new String[blodname.size()];
                                intent.putExtra("blogmodelList", String.valueOf(blogdata));
                                try {
                                    if (!imagename.isEmpty()) {
                                        intent.putExtra("image", blodimage.get(position)/*.getImage()*/.get(0));
                                    }
                                } catch (Exception e) {

                                }

                                context.startActivity(intent);
                            } else {
                                //  Toast.makeText(context, context.getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                            }

                     /*   } else {

                            leaderbord.setVisibility(View.GONE);
                            mLaynodata.setVisibility(View.VISIBLE);
                        }
                    } else {
                        leaderbord.setVisibility(View.GONE);
                        mLaynodata.setVisibility(View.VISIBLE);
                    }*/

                        }
                    });
                    holder.blogdescription.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (!blodlink.get(position)/*.getBlogLink()*/.isEmpty()) {
                                Log.e("share", "gfgdedd");
                                Intent intent = new Intent(context, Activity_ShowBlogwebview.class);
                                intent.putExtra("link", blodlink.get(position)/*.getBlogLink()*/);
                                intent.putExtra("id", blodid.get(position)/*.getId()*/);
                                intent.putExtra("position", position);
                                intent.putExtra("userid", userId);
                                intent.putExtra("blogname", blodname.get(position)/*.getBlogName()*/);
                                intent.putExtra("bloglink", blodlink.get(position)/*.getBlogLink()*/);
                                intent.putExtra("bloglike", blodbloglike.get(position)/*.getBlogLike()*/);
                                String[] blogdata = new String[blodname.size()];
                                intent.putExtra("blogmodelList", String.valueOf(blogdata));
                                try {
                                    if (!imagename.isEmpty()) {
                                        intent.putExtra("image", blodimage.get(position)/*.getImage()*/.get(0));
                                    }
                                } catch (Exception e) {

                                }

                                context.startActivity(intent);
                            } else {

                            }

                        }
                    });
                    holder.ad_icon_layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if (!blodlink.get(position)/*.getBlogLink()*/.isEmpty()) {
                                Log.e("share", "gfgdedd");
                                Intent intent = new Intent(context, Activity_ShowBlogwebview.class);
                                intent.putExtra("link", blodlink.get(position)/*.getBlogLink()*/);
                                intent.putExtra("id", blodid.get(position)/*.getId()*/);
                                intent.putExtra("position", position);
                                intent.putExtra("userid", userId);
                                intent.putExtra("blogname", blodname.get(position)/*.getBlogName()*/);
                                intent.putExtra("bloglink", blodlink.get(position)/*.getBlogLink()*/);
                                intent.putExtra("bloglike", blodbloglike.get(position)/*.getBlogLike()*/);
                                String[] blogdata = new String[blodname.size()];
                                intent.putExtra("blogmodelList", String.valueOf(blogdata));
                                try {
                                    if (!imagename.isEmpty()) {
                                        intent.putExtra("image", blodimage.get(position)/*.getImage()*/.get(0));
                                    }
                                } catch (Exception e) {

                                }

                                context.startActivity(intent);
                            } else {
                                //  Toast.makeText(context, context.getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                            }
                            Toast.makeText(context, context.getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                        }
                    });
                    musicurl = tinyDB.getString(Constants.Backimageurl);
                    holder.sharebutton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            try {
                                mPosition = position;
                                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                //    shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                                StringBuilder sb = new StringBuilder();
                                sb.append(blodname.get(mPosition)/*.getBlogName()*/ + "\n");
                                sb.append(Html.fromHtml(blodlink.get(mPosition)/*.getBlogLink()*/).toString());
                                sb.append("\nHey check this application ");
                                sb.append("https://play.google.com/store/apps/details?id=");
                                sb.append(context.getPackageName());
                                shareIntent.putExtra(Intent.EXTRA_TEXT, sb.toString());
                                shareIntent.setType("text/*");
                                context.startActivity(Intent.createChooser(shareIntent, "Share with"));
                                /*  if (!blodimage.get(position)*//*.getImage()*//*.get(0).isEmpty()) {*/
                                //    new RetrieveFeedTask(musicurl + "blog/" + blodimage.get(position)/*.getImage()*/.get(0)).execute();

                                /* }*/
                            } catch (Exception e) {
                                Log.e("errorinshare", e.toString());
                            }
                        }
                    });
                    break;

                default:
                    break;


            }


        }


        EditText addComment;
        ImageView sendcomment;
        BottomSheetBehavior bottomSheetBehavior;


        private void DialogComment() {
            callShowCommentApi();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View customView = inflater.inflate(R.layout.custom_view, null);
            commentrecyclerview = (RecyclerView) customView.findViewById(R.id.commentrecyclerview);
            addComment = (EditText) customView.findViewById(R.id.addComment);
            sendcomment = customView.findViewById(R.id.sendcomment);
            sendcomment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String comment = addComment.getText().toString();
                    if (!comment.isEmpty()) {
                        callCommentApi(comment);
                    } else {
                        Toast.makeText(context, "Please write a comment!!..", Toast.LENGTH_LONG).show();
                    }
                }
            });

            new BottomDialog.Builder(context)
                    .setTitle("Comments")
                    .setCustomView(customView)
                    .show();
        }

        private void callCommentApi(String comment) {

            //  String token = Constants.Authtoken;
            try {

                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClient.addInterceptor(loggingInterceptor);

                OkHttpClient client = httpClient.build();
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();
        /*Retrofit retrofit = new Retrofit.Builder().baseUrl(CelluliteAPIInterface.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();*/
                String idofblog = blodid.get(mPositioncomment)/*.getId()*/;
                ApiInterface apiInterface = (ApiInterface) BackpainAPIClient.getCelluliteClient1().create(ApiInterface.class);
                //  ApiInterface   apiInterface = retrofit.create(ApiInterface.class);
                //       CelluliteAPIInterface apiInterface = retrofit.create(CelluliteAPIInterface.class);
                try {
                    JSONObject paramObject = new JSONObject();
                    paramObject.put("uId", userId);
                    paramObject.put("comment", comment);


                    Call<AddComment> userCall = apiInterface.addComment(idofblog, paramObject.toString(), "Bearer " + token);
                    userCall.enqueue(new Callback<AddComment>() {
                        @Override
                        public void onResponse(Call<AddComment> call, Response<AddComment> response) {
                            Log.e("TAG1", String.valueOf(response.code()) /*+ response.body().toString()*/);

                            addComment.setText("");
                            callShowCommentApi();
                            try {
                                if (adapterTopblogs1 != null) {
                                    adapterTopblogs1.notifyDataSetChanged();
                                }
                                if (adapterTopblogs != null) {
                                    adapterTopblogs.notifyDataSetChanged();
                                }
//                                getBlog(page, limit, token);
                            } catch (Exception e) {

                            }

                        }

                        @Override
                        public void onFailure(Call<AddComment> call, Throwable t) {
                            //    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {

            }
        }

        @SuppressLint("WrongConstant")
        public void callShowCommentApi() {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(loggingInterceptor);

            OkHttpClient client = httpClient.build();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();


            String idofblog = blodid.get(mPositioncomment)/*.getId()*/;
            ApiInterface apiInterface = (ApiInterface) BackpainAPIClient.getCelluliteClient1().create(ApiInterface.class);


            Call<GetComment> userCall = apiInterface.getComment(idofblog, "Bearer " + token);


            userCall.enqueue(new Callback<GetComment>() {
                @Override
                public void onResponse(Call<GetComment> call, Response<GetComment> response) {
//                    Log.d("TAG1", response.code() + "");
                    GetComment getComment = (GetComment) response.body();
                    assert getComment != null;

                    ArrayList<GetComment.BlogComment> blogComments = (ArrayList<GetComment.BlogComment>) getComment.blogComment;


                    String[] heroes = new String[blogComments.size()];

                    for (int i = 0; i < blogComments.size(); i++) {
                        heroes[i] = blogComments.get(i).getComment();


                        Log.e("imagesname", String.valueOf(blogComments) + "hgh");


                    }

                    commentrecyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                    Adapter_addComment adapterTopblogs = new Adapter_addComment(context, blogComments, activity);
                    commentrecyclerview.setAdapter(adapterTopblogs);


                }

                @Override
                public void onFailure(Call<GetComment> call, Throwable t) {
                    //   Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }


        class RetrieveFeedTask extends AsyncTask<String, String, Bitmap> {
            String src;

            public RetrieveFeedTask(String src) {
                this.src = src;
            }

            @Override
            protected Bitmap doInBackground(String... strings) {
                try {
                    URL url = new URL(src);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    Bitmap myBitmaps = BitmapFactory.decodeStream(input);
                    return myBitmaps;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                Intent shareIntent;

                String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Share.png";
                OutputStream out = null;
                File file = new File(path);
                try {
                    out = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Uri bmpUri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
                shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                StringBuilder sb = new StringBuilder();
                sb.append(blodname.get(mPosition)/*.getBlogName()*/ + "\n");
                sb.append(Html.fromHtml(blodlink.get(mPosition)/*.getBlogLink()*/).toString());
                sb.append("\nHey check this application ");
                sb.append("https://play.google.com/store/apps/details?id=");
                sb.append(context.getPackageName());
                shareIntent.putExtra(Intent.EXTRA_TEXT, sb.toString());
                shareIntent.setType("text/*");
                context.startActivity(Intent.createChooser(shareIntent, "Share with"));
                // callShareApi();
            }
        }

        @Override
        public int getItemCount() {
            int size = blodname.size();
            if (blodname.size() > 0) {
                return blodname.size() + Math.round(blodname.size() / 6);
            }
            return blodname.size();
        }

        @Override
        public int getItemViewType(int position) {
            if ((position + 1) % 4 == 0) {
                return ADS;
            }
            return DATA;
        }
 /*   @Override
    public int getItemCount() {
        return blodname.size();
    }*/

        public class BlogdataHolder extends RecyclerView.ViewHolder {
            public int position;
            ImageView mIvicon, mIvsmallicon;
            TextView nameofblog, mTxtviews, blog_catagoryname, likecount, addcomment, blog_catagoryfollowers, btnexestart;
            RelativeLayout mBlogdetail;
            TextView time_text;
            CheckBox like;
            ImageView sharebutton;
            LinearLayout mLaylike;
            RelativeLayout ad_icon_layout;
            SliderView slider;
            HtmlTextView blogdescription;
            TextView link;
            RoundedImageView ivicon1;
            RelativeLayout catagoty_lay;

            public BlogdataHolder(View itemView) {
                super(itemView);
                nameofblog = (TextView) itemView.findViewById(R.id.nameofblog);
                blog_catagoryname = (TextView) itemView.findViewById(R.id.blog_catagoryname);
                mTxtviews = (TextView) itemView.findViewById(R.id.views);
                mBlogdetail = (RelativeLayout) itemView.findViewById(R.id.blogdetail);
                ad_icon_layout = itemView.findViewById(R.id.ad_icon_layout);
                like = itemView.findViewById(R.id.likebutton);
                likecount = itemView.findViewById(R.id.likecount);
                sharebutton = itemView.findViewById(R.id.sharebutton);
                mLaylike = (LinearLayout) itemView.findViewById(R.id.laylike);
                slider = itemView.findViewById(R.id.slider);
                //  ivicon1 = itemView.findViewById(R.id.ivicon1);
                addcomment = itemView.findViewById(R.id.addComment);
                blogdescription = (HtmlTextView) itemView.findViewById(R.id.blogdescription);
                blog_catagoryfollowers = itemView.findViewById(R.id.blog_catagoryfollowers);
                time_text = itemView.findViewById(R.id.time_text);
                link = itemView.findViewById(R.id.link);
                btnexestart = itemView.findViewById(R.id.btnexestart);
                catagoty_lay = itemView.findViewById(R.id.catagoty_lay);
            }
        }

        public class AdsHolder extends RecyclerView.ViewHolder {
            LinearLayout mRelativeFBAdmob;
            //   CardView mAdcard;

            AdsHolder(View itemView) {
                super(itemView);
                mRelativeFBAdmob = (LinearLayout) itemView.findViewById(R.id.adframe);
                //     mAdcard = (CardView) itemView.findViewById(R.id.adcard);
            }
        }



        public void showGOOGLEAdvance(final Context context, final LinearLayout frameLayout) {
            try {
                AdLoader.Builder builder = new AdLoader.Builder(context, Constants.admob_nativead);
                builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
                    public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                        frameLayout.setVisibility(View.VISIBLE);
                        NativeContentAdView    nativeContentAdView = (NativeContentAdView) ((Activity) context).getLayoutInflater().inflate(R.layout.ad_content, null);
                        nativeContentAdView.setVisibility(View.VISIBLE);

                        populateContentAdView(nativeContentAd, nativeContentAdView);
                        frameLayout.removeAllViews();
                        frameLayout.addView(nativeContentAdView);
                    }
                });
                builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
                builder.withAdListener(new AdListener() {
                    public void onAdFailedToLoad(int i) {
                        Log.e("error", "Failed to load native ad:: " + i);
                        NativeContentAdView    nativeContentAdView = (NativeContentAdView) ((Activity) context).getLayoutInflater().inflate(R.layout.ad_content, null);
                           nativeContentAdView.setVisibility(View.GONE);
                        frameLayout.setVisibility(View.GONE);
                    }
                }).build().loadAd(new AdRequest.Builder().build());
            } catch (Exception e) {

            }
        }

        @SuppressLint("WrongConstant")
        public void populateContentAdView(NativeContentAd nativeContentAd, NativeContentAdView nativeContentAdView) {
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

        private void CallAddlike(String id, int pos) {
            //  String token = Constants.Authtoken;

            //   this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);

            // String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiIxMDJhMGNmNjc0NjZjYWNjIiwiaWF0IjoxNjI2MzIxMDcwfQ.v8_1g2RFNemXKR24C2bVTqbWrnZ7FZh7drO5M13-XzY";

            try {
                this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);

                JSONObject paramObject = new JSONObject();
                paramObject.put("update", "true");
                Call<AddLike> call = apiInterface.getLike(id, paramObject.toString(), "Bearer " + token);
                call.enqueue(new Callback<AddLike>() {
                    @Override
                    public void onResponse(Call<AddLike> call, Response<AddLike> response) {
                        Log.e("response", String.valueOf(response.code()));
                        AddLike addLike = response.body();
                        Log.e("likes", String.valueOf(addLike.getBlogLike()));
                        String likes = String.valueOf(addLike.getBlogLike());
                        Log.e("like", String.valueOf(likes));


                    }

                    @Override
                    public void onFailure(Call<AddLike> call, Throwable t) {
                        Log.e("eroor", t.toString());
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        private void CallAddunlike(String id, int pos) {

            //  String token = Constants.Authtoken;


            //   this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);

            // String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiIxMDJhMGNmNjc0NjZjYWNjIiwiaWF0IjoxNjI2MzIxMDcwfQ.v8_1g2RFNemXKR24C2bVTqbWrnZ7FZh7drO5M13-XzY";

            try {
                this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);

                JSONObject paramObject = new JSONObject();
                paramObject.put("update", "false");
                Call<AddLike> call = apiInterface.getLike(id, paramObject.toString(), "Bearer " + token);
                call.enqueue(new Callback<AddLike>() {
                    @Override
                    public void onResponse(Call<AddLike> call, Response<AddLike> response) {
                        Log.e("response", String.valueOf(response.body().toString()));

                        AddLike addLike = response.body();
                        Log.e("likes_unlike", String.valueOf(addLike.getBlogLike()));
                        String likes = String.valueOf(addLike.getBlogLike());

                    }

                    @Override
                    public void onFailure(Call<AddLike> call, Throwable t) {
                        Log.e("eroor", t.toString());
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


        private class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {
            private final List<String> mSliderItems;

            public SliderAdapter(Context context, List<String> sliderDataArrayList) {
                this.mSliderItems = sliderDataArrayList;
            }

            @Override
            public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
                View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null);
                return new SliderAdapterViewHolder(inflate);
            }


            @Override
            public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int position) {

                //  final Bloglist.Blog.Image sliderItem = mSliderItems.get(position);

                Glide.with(viewHolder.itemView)
                        .load(musicurl + "blog/" + mSliderItems.get(position))
                        .into(imageViewBackground);

              /*  viewHolder.imageViewBackground.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (!blodlink.get(position)*//*.getBlogLink()*//*.isEmpty()) {
                            Log.e("share", "gfgdedd");
                            Intent intent = new Intent(context, Activity_ShowBlogwebview.class);
                            intent.putExtra("link", blodlink.get(position)*//*.getBlogLink()*//*);
                            intent.putExtra("id", blodid.get(position)*//*.getId()*//*);
                            intent.putExtra("position", position);
                            intent.putExtra("userid", userId);
                            intent.putExtra("blogname", blodname.get(position)*//*.getBlogName()*//*);
                            intent.putExtra("bloglink", blodlink.get(position)*//*.getBlogLink()*//*);
                            intent.putExtra("bloglike", blodbloglike.get(position)*//*.getBlogLike()*//*);
                            String[] blogdata = new String[blodname.size()];
                            intent.putExtra("blogmodelList", String.valueOf(blogdata));
                            try {
                                if (!imagename.isEmpty()) {
                                    intent.putExtra("image", blodimage.get(position)*//*.getImage()*//*.get(0));
                                }
                            } catch (Exception e) {

                            }

                            context.startActivity(intent);
                        } else {
                            //  Toast.makeText(context, context.getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                        }
                    }
                });*/
            }

            @Override
            public int getCount() {
                return mSliderItems.size();
            }

            public ImageView imageViewBackground;

            class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
                //Adapter class for initializing the views of our slider view.
                View itemView;


                public SliderAdapterViewHolder(View itemView) {
                    super(itemView);
                    imageViewBackground = itemView.findViewById(R.id.myimage);
                    this.itemView = itemView;
                }
            }
        }

    }


    @SuppressLint("WrongConstant")
    public class Adapter_Allblogs extends RecyclerView.Adapter<Adapter_Allblogs.BlogdataHolder> {

        ArrayList personNames;
        //  ArrayList<BlogList> blogmodelList;
        Context context;
        DBHelper mDbhelper;

        Activity activity;
        TinyDB tinyDB;
        // List<Catagorylist> catagorylists;
        Boolean clicked = true;
        private int mPosition;
        private int mPositioncomment;
        String imageurl = "https://drzio-android.s3.ap-south-1.amazonaws.com/blog/";
        String userId;
        boolean addlike = false;
        private String imagename;
        int pos;
        ArrayList<String> blog_arrayname = new ArrayList<>();
        RecyclerView.ViewHolder holder;
        String userID;
        private BackpainAPIInterface apiInterface;
        private String token;
        private String BASEURL;

        ArrayList<String> blodid = new ArrayList<>();
        ArrayList<String> blodname = new ArrayList<>();
        ArrayList<List<String>> blodimage = new ArrayList<List<String>>();
        ArrayList<String> blodlink = new ArrayList<>();
        ArrayList<String> blodblog_Des = new ArrayList<>();
        ArrayList<Boolean> blodlikestatus = new ArrayList<Boolean>();
        ArrayList<Integer> blodbloglike = new ArrayList<Integer>();
        ArrayList<String> blodcreated_at = new ArrayList<>();
        ArrayList<String> blodupdated_at = new ArrayList<>();
        ArrayList<Integer> blogcommnet = new ArrayList<>();
        private final int ADS = 1, DATA = 0;
        private String musicurl;
        int page = 1, limit = 10;

  /*  public Adapter_Allblogs(Context context, ArrayList<BlogList> blogmodelList, Activity_Bloglists activity) {
        this.context = context;
        this.blogmodelList = blogmodelList;
        mDbhelper = new DBHelper(context);
        this.userID = userID;


    }

    public Adapter_Allblogs(Context context, ArrayList<BlogList> blogmodelList) {
        this.context = context;
        this.blogmodelList = blogmodelList;
        mDbhelper = new DBHelper(context);
        this.userID = userID;
    }*/


        public Adapter_Allblogs(Context context, ArrayList<String> blodid1, ArrayList<String> blodname1, ArrayList<List<String>> blodimage1,
                                ArrayList<String> blodlink1, ArrayList<String> blodblog_des1, ArrayList<Boolean> blodlikestatus1,
                                ArrayList<Integer> blodbloglike1, ArrayList<String> blodcreated_at1, ArrayList<String> blodupdated_at1,
                                ArrayList<Integer> blogcommnet1) {
            this.context = context;
            this.blodid = blodid1;
            blodname = blodname1;
            blodimage = blodimage1;
            blodlink = blodlink1;
            blodblog_Des = blodblog_des1;
            blodlikestatus = blodlikestatus1;
            blodbloglike = blodbloglike1;
            blodcreated_at = blodcreated_at1;
            blodupdated_at = blodupdated_at1;
            blogcommnet = blogcommnet1;
            mDbhelper = new DBHelper(context);
            tinyDB = new TinyDB(context);
        }

        public BlogdataHolder onCreateViewHolder(ViewGroup parent, int viewType) {
           /* View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blogdata, parent, false);
            MyViewHolder vh = new MyViewHolder(v);
            return vh;*/


            BlogdataHolder viewHolder = null;
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());

            View v2 = inflater.inflate(R.layout.item_blogdata, parent, false);
            viewHolder = new BlogdataHolder(v2);


            assert viewHolder != null;
            return viewHolder;
        }


 /*   @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_blogdata, parent, false);
        Adapter_Allblogs.BlogdataHolder vh = new Adapter_Allblogs.BlogdataHolder(v);
        tinyDB = new TinyDB(context);
        return vh;
    }*/


        @Override
        public void onBindViewHolder(BlogdataHolder holders, final int pos) {

            //  final int position = pos - Math.round(pos / 2);
            final int position = pos - Math.round(pos / 4);
            BlogdataHolder holder = (BlogdataHolder) holders;
            token = tinyDB.getString(Constants.Authtoken);
            BASEURL = tinyDB.getString(Constants.NewBaseUrl);
            TimeAgo2 timeAgo2 = new TimeAgo2();


            ArrayList<String> images = (ArrayList<String>) blodimage.get(position)/*.getImage()*/;
            String[] imagearray = new String[images.size()];
            for (int i1 = 0; i1 < images.size(); i1++) {
                imagearray[i1] = images.get(i1);
                Log.e("imagesname", String.valueOf(images.get(i1)));
            }

            try {
                imagename = blodimage.get(position)/*.getImage()*/.get(0);
            } catch (Exception e) {
                Log.e("exception", e.toString());
            }


            holder.btnexestart.setText("Following");


            holder.blog_catagoryname.setText(context.getResources().getString(R.string.height_increase_workout));


            Log.e("size", String.valueOf(blog_arrayname.size()));
            Log.e("blogsize", String.valueOf(blodname.size()));
            holder.nameofblog.setText(blodname.get(position)/*.getBlogName()*/);


            ArrayList<String> listlikes = mDbhelper.getLikeid();
            for (int i = 0; i < listlikes.size(); i++) {
                String likeid = listlikes.get(i);
                if (likeid != null && listlikes.contains(blodid.get(position)/*.getId()*/)) {
                    holder.like.setChecked(true);
                    holder.like.setBackground(context.getResources().getDrawable(R.drawable.heartlike));
                } else {
                    holder.like.setChecked(false);
                    holder.like.setBackground(context.getResources().getDrawable(R.drawable.heart1));

                }
            }
            holder.like.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                  /*  if (netInfo != null) {
                        if (netInfo.isConnected()) {
                            leaderbord.setVisibility(View.VISIBLE);
                            mLaynodata.setVisibility(View.GONE);*/
                    try {
                        if (isChecked) {
                            int likeadd = Integer.valueOf(blodbloglike.get(position)/*.getBlogLike()*/) + 1;
                            String likes = String.valueOf(likeadd);

                            if (blogcommnet.get(position) == 0) {
                                holder.addcomment.setText(likes + " Likes");
                            } else {
                                holder.addcomment.setText(likes + " Likes  . "
                                        + String.valueOf(blogcommnet.get(position)) + " Comments");
                            }

                            CallAddlike(blodid.get(position)/*.getId()*/, position);
                            mDbhelper.insertContact(blodid.get(position)/*.getId()*/);
                            holder.like.setBackground(context.getResources().getDrawable(R.drawable.heartlike));
                        } else {
                            CallAddunlike(blodid.get(position)/*.getId()*/, position);
                            holder.addcomment.setText(String.valueOf(blodbloglike.get(position)/*.getBlogLike()*/));
                            mDbhelper.deleteContact(blodid.get(position)/*.getId()*/);
                            holder.like.setBackground(context.getResources().getDrawable(R.drawable.heart1));


                            if (blogcommnet.get(position)/*.blogComment.size()*/ == 0) {
                                holder.addcomment.setText(String.valueOf(blodbloglike.get(position)/*.getBlogLike()*/) + " Likes");
                            } else {
                                holder.addcomment.setText(String.valueOf(blodbloglike.get(position)/*.getBlogLike()*/) + " Likes  . "
                                        + String.valueOf(blogcommnet.get(position)/*.blogComment.size()*/) + " Comments");
                            }
                            holder.addcomment.setText(String.valueOf(blodbloglike.get(position)/*.getBlogLike()*/) + " Likes");
                        }

                    } catch (Exception e) {

                    }
                }
            });

            try {

                int blog_likess = blodbloglike.get(position)/*.getBlogLike()*/;

                if (blog_likess != 0) {
                    holder.addcomment.setText(blog_likess + " Likes");
                }
                if (blogcommnet.get(position)/*.blogComment.size()*/ > 0) {
                    holder.addcomment.setText(String.valueOf(blogcommnet.get(position)/*.blogComment.size()*/) + " Comments");
                }
                if (blog_likess != 0 && blogcommnet.get(position)/*.blogComment.size()*/ > 0) {
                    holder.addcomment.setText(blog_likess + " Likes  . "
                            + String.valueOf(blogcommnet.get(position)/*.blogComment.size()*/) + " Comments");
                }


                if (blog_likess == 0 && blogcommnet.get(position)/*.blogComment.size()*/ == 0) {
                    holder.addcomment.setText("Write a Comment");
                }
            } catch (Exception e) {

            }
            // holder.addcomment.setText(String.valueOf(blodbloglike.get(position)/*.getBlogLike()*/) + " Likes");

            holder.addcomment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPositioncomment = position;
                    DialogComment();

                }
            });
            SliderAdapter adapter = new SliderAdapter(context, blodimage.get(position)/*.getImage()*/);
            holder.slider.setAutoCycleDirection(SliderView.LAYOUT_DIRECTION_LTR);
            holder.slider.setSliderAdapter(adapter);
            holder.slider.setScrollTimeInSec(7);
            holder.slider.setAutoCycle(true);
            holder.slider.startAutoCycle();
            try {
                if (blodimage.get(position)/*.getImage()*/.size() == 1) {
              /*  holder.slider.setVisibility(View.GONE);
                Log.e("gone","gone");
                Glide.with(getContext())
                        .load(imageurl + blogmodelList.get(position).getImage().get(0))
                        .into(holder.ivicon1);*/
                    holder.slider.setFocusable(false);
                } else {
                    Log.e("gone1", "gone1");
                    //   holder.ivicon1.setVisibility(View.GONE);
                    holder.slider.setVisibility(View.VISIBLE);
                    holder.slider.setScrollTimeInSec(7);
                    holder.slider.setAutoCycle(true);
                    holder.slider.startAutoCycle();
                }
                holder.blogdescription.setHtml(blodblog_Des.get(position)/*.getBlogDescription()*/);


            } catch (Exception e) {

            }




/*
        if (blogmodelList.get(position).getBlogDescription() != null) {
            Log.e("desss",blogmodelList.get(position).getBlogDescription());
            holder.blogdescription.setVisibility(View.VISIBLE);
            holder.blogdescription.setHtml(blogmodelList.get(position).getBlogDescription());
        } else {
            holder.blogdescription.setVisibility(View.GONE);
        }*/

            try {
                URL url = new URL(blodlink.get(position)/*.getBlogLink()*/);
                String host = url.getHost();
                holder.link.setText(host);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            try {
                holder.time_text.setText(timeAgo2.covertTimeToText(blodcreated_at.get(position)/*.getCreatedAt()*/));
            } catch (Exception e) {

            }


            holder.slider.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!blodlink.get(position)/*.getBlogLink()*/.isEmpty()) {
                        Log.e("share", "gfgdedd");
                        Intent intent = new Intent(context, Activity_ShowBlogwebview.class);
                        intent.putExtra("link", blodlink.get(position)/*.getBlogLink()*/);
                        intent.putExtra("id", blodid.get(position)/*.getId()*/);
                        intent.putExtra("position", position);
                        intent.putExtra("userid", userId);
                        intent.putExtra("blogname", blodname.get(position)/*.getBlogName()*/);
                        intent.putExtra("bloglink", blodlink.get(position)/*.getBlogLink()*/);
                        intent.putExtra("bloglike", blodbloglike.get(position)/*.getBlogLike()*/);
                        String[] blogdata = new String[blodname.size()];
                        intent.putExtra("blogmodelList", String.valueOf(blogdata));
                        try {
                            if (!imagename.isEmpty()) {
                                intent.putExtra("image", blodimage.get(position)/*.getImage()*/.get(0));
                            }
                        } catch (Exception e) {

                        }

                        context.startActivity(intent);
                    } else {
                        //  Toast.makeText(context, context.getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                    }
                }
            });

            holder.link.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.e("share", "gfg");
                   /* if (netInfo != null) {
                        if (netInfo.isConnected()) {
                            leaderbord.setVisibility(View.VISIBLE);
                            mLaynodata.setVisibility(View.GONE);*/
                    if (!blodlink.get(position)/*.getBlogLink()*/.isEmpty()) {
                        Log.e("share", "gfgdedd");
                        Intent intent = new Intent(context, Activity_ShowBlogwebview.class);
                        intent.putExtra("link", blodlink.get(position)/*.getBlogLink()*/);
                        intent.putExtra("id", blodid.get(position)/*.getId()*/);
                        intent.putExtra("position", position);
                        intent.putExtra("userid", userId);
                        intent.putExtra("blogname", blodname.get(position)/*.getBlogName()*/);
                        intent.putExtra("bloglink", blodlink.get(position)/*.getBlogLink()*/);
                        intent.putExtra("bloglike", blodbloglike.get(position)/*.getBlogLike()*/);
                        String[] blogdata = new String[blodname.size()];
                        intent.putExtra("blogmodelList", String.valueOf(blogdata));
                        try {
                            if (!imagename.isEmpty()) {
                                intent.putExtra("image", blodimage.get(position)/*.getImage()*/.get(0));
                            }
                        } catch (Exception e) {

                        }

                        context.startActivity(intent);
                    } else {
                        //  Toast.makeText(context, context.getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                    }
                     /*   } else {

                            leaderbord.setVisibility(View.GONE);
                            mLaynodata.setVisibility(View.VISIBLE);
                        }
                    } else {
                        leaderbord.setVisibility(View.GONE);
                        mLaynodata.setVisibility(View.VISIBLE);
                    }*/

                }
            });


            holder.mBlogdetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.e("share", "gfg");
                   /* if (netInfo != null) {
                        if (netInfo.isConnected()) {
                            leaderbord.setVisibility(View.VISIBLE);
                            mLaynodata.setVisibility(View.GONE);*/
                    if (!blodlink.get(position)/*.getBlogLink()*/.isEmpty()) {
                        Log.e("share", "gfgdedd");
                        Intent intent = new Intent(context, Activity_ShowBlogwebview.class);
                        intent.putExtra("link", blodlink.get(position)/*.getBlogLink()*/);
                        intent.putExtra("id", blodid.get(position)/*.getId()*/);
                        intent.putExtra("position", position);
                        intent.putExtra("userid", userId);
                        intent.putExtra("blogname", blodname.get(position)/*.getBlogName()*/);
                        intent.putExtra("bloglink", blodlink.get(position)/*.getBlogLink()*/);
                        intent.putExtra("bloglike", blodbloglike.get(position)/*.getBlogLike()*/);
                        String[] blogdata = new String[blodname.size()];
                        intent.putExtra("blogmodelList", String.valueOf(blogdata));
                        try {
                            if (!imagename.isEmpty()) {
                                intent.putExtra("image", blodimage.get(position)/*.getImage()*/.get(0));
                            }
                        } catch (Exception e) {

                        }

                        context.startActivity(intent);
                    } else {
                        //  Toast.makeText(context, context.getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                    }

                     /*   } else {

                            leaderbord.setVisibility(View.GONE);
                            mLaynodata.setVisibility(View.VISIBLE);
                        }
                    } else {
                        leaderbord.setVisibility(View.GONE);
                        mLaynodata.setVisibility(View.VISIBLE);
                    }*/

                }
            });
            holder.blogdescription.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                 /*   if (netInfo != null) {
                        if (netInfo.isConnected()) {
                            leaderbord.setVisibility(View.VISIBLE);
                            mLaynodata.setVisibility(View.GONE);*/
                    if (!blodlink.get(position)/*.getBlogLink()*/.isEmpty()) {
                        Log.e("share", "gfgdedd");
                        Intent intent = new Intent(context, Activity_ShowBlogwebview.class);
                        intent.putExtra("link", blodlink.get(position)/*.getBlogLink()*/);
                        intent.putExtra("id", blodid.get(position)/*.getId()*/);
                        intent.putExtra("position", position);
                        intent.putExtra("userid", userId);
                        intent.putExtra("blogname", blodname.get(position)/*.getBlogName()*/);
                        intent.putExtra("bloglink", blodlink.get(position)/*.getBlogLink()*/);
                        intent.putExtra("bloglike", blodbloglike.get(position)/*.getBlogLike()*/);
                        String[] blogdata = new String[blodname.size()];
                        intent.putExtra("blogmodelList", String.valueOf(blogdata));
                        try {
                            if (!imagename.isEmpty()) {
                                intent.putExtra("image", blodimage.get(position)/*.getImage()*/.get(0));
                            }
                        } catch (Exception e) {

                        }

                        context.startActivity(intent);
                    } else {
                        //  Toast.makeText(context, context.getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                    }

                   /*     } else {

                            leaderbord.setVisibility(View.GONE);
                            mLaynodata.setVisibility(View.VISIBLE);
                        }
                    } else {
                        leaderbord.setVisibility(View.GONE);
                        mLaynodata.setVisibility(View.VISIBLE);
                    }
*/
                }
            });
            holder.ad_icon_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                  /*  if (netInfo != null) {
                        if (netInfo.isConnected()) {
                            leaderbord.setVisibility(View.VISIBLE);
                            mLaynodata.setVisibility(View.GONE);*/
                    if (!blodlink.get(position)/*.getBlogLink()*/.isEmpty()) {
                        Log.e("share", "gfgdedd");
                        Intent intent = new Intent(context, Activity_ShowBlogwebview.class);
                        intent.putExtra("link", blodlink.get(position)/*.getBlogLink()*/);
                        intent.putExtra("id", blodid.get(position)/*.getId()*/);
                        intent.putExtra("position", position);
                        intent.putExtra("userid", userId);
                        intent.putExtra("blogname", blodname.get(position)/*.getBlogName()*/);
                        intent.putExtra("bloglink", blodlink.get(position)/*.getBlogLink()*/);
                        intent.putExtra("bloglike", blodbloglike.get(position)/*.getBlogLike()*/);
                        String[] blogdata = new String[blodname.size()];
                        intent.putExtra("blogmodelList", String.valueOf(blogdata));
                        try {
                            if (!imagename.isEmpty()) {
                                intent.putExtra("image", blodimage.get(position)/*.getImage()*/.get(0));
                            }
                        } catch (Exception e) {

                        }

                        context.startActivity(intent);
                    } else {
                        //  Toast.makeText(context, context.getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                    }

                }
            });
            musicurl = tinyDB.getString(Constants.Backimageurl);
            holder.sharebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    try {
                        mPosition = position;
                        Intent shareIntent = new Intent(Intent.ACTION_SEND);
                        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        //    shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                        StringBuilder sb = new StringBuilder();
                        sb.append(blodname.get(mPosition)/*.getBlogName()*/ + "\n");
                        sb.append(Html.fromHtml(blodlink.get(mPosition)/*.getBlogLink()*/).toString());
                        sb.append("\nHey check this application ");
                        sb.append("https://play.google.com/store/apps/details?id=");
                        sb.append(context.getPackageName());
                        shareIntent.putExtra(Intent.EXTRA_TEXT, sb.toString());
                        shareIntent.setType("*/*");
                        context.startActivity(Intent.createChooser(shareIntent, "Share with"));
                        /*  if (!blodimage.get(position)*//*.getImage()*//*.get(0).isEmpty()) {
                            new RetrieveFeedTask(musicurl + "blog/" + blodimage.get(position)*//*.getImage()*//*.get(0)).execute();

                        }*/
                    } catch (Exception e) {
                        Log.e("errorinshare", e.toString());
                    }
                }
            });


        }


        EditText addComment;
        ImageView sendcomment;
        BottomSheetBehavior bottomSheetBehavior;
        public RecyclerView commentrecyclerview;

        private void DialogComment() {
            callShowCommentApi();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View customView = inflater.inflate(R.layout.custom_view, null);
            commentrecyclerview = (RecyclerView) customView.findViewById(R.id.commentrecyclerview);
            addComment = (EditText) customView.findViewById(R.id.addComment);
            sendcomment = customView.findViewById(R.id.sendcomment);
            sendcomment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String comment = addComment.getText().toString();
                    if (!comment.isEmpty()) {
                        callCommentApi(comment);
                    } else {
                        Toast.makeText(context, "Please write a comment!!..", Toast.LENGTH_LONG).show();
                    }
                }
            });

            new BottomDialog.Builder(context)
                    .setTitle("Comments")
                    .setCustomView(customView)
                    .show();
        }

        private void callCommentApi(String comment) {

            //  String token = Constants.Authtoken;

            try {
                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClient.addInterceptor(loggingInterceptor);

                OkHttpClient client = httpClient.build();
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();
        /*Retrofit retrofit = new Retrofit.Builder().baseUrl(CelluliteAPIInterface.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();*/
                String idofblog = blodid.get(mPositioncomment)/*.getId()*/;
                ApiInterface apiInterface = (ApiInterface) BackpainAPIClient.getCelluliteClient1().create(ApiInterface.class);
                //  ApiInterface   apiInterface = retrofit.create(ApiInterface.class);
                //       CelluliteAPIInterface apiInterface = retrofit.create(CelluliteAPIInterface.class);
                try {
                    JSONObject paramObject = new JSONObject();
                    paramObject.put("uId", userId);
                    paramObject.put("comment", comment);


                    Call<AddComment> userCall = apiInterface.addComment(idofblog, paramObject.toString(), "Bearer " + token);
                    userCall.enqueue(new Callback<AddComment>() {
                        @Override
                        public void onResponse(Call<AddComment> call, Response<AddComment> response) {
                            Log.e("TAG1", String.valueOf(response.code()) /*+ response.body().toString()*/);

                            addComment.setText("");
                            callShowCommentApi();
                            try {
                                if (adapterTopblogs1 != null) {
                                    adapterTopblogs1.notifyDataSetChanged();
                                }
                                if (adapterTopblogs != null) {
                                    adapterTopblogs.notifyDataSetChanged();
                                }
                                //getBlog(page, limit,token);
                            } catch (Exception e) {

                            }

                        }

                        @Override
                        public void onFailure(Call<AddComment> call, Throwable t) {
                            //    Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {

            }
        }

        @SuppressLint("WrongConstant")
        public void callShowCommentApi() {

            //     String token = Constants.Authtoken;
            try {

                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClient.addInterceptor(loggingInterceptor);

                OkHttpClient client = httpClient.build();
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();
      /*  Retrofit retrofit = new Retrofit.Builder().baseUrl(CelluliteAPIInterface.BASE_URL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();*/

                String idofblog = blodid.get(mPositioncomment)/*.getId()*/;
                ApiInterface apiInterface = (ApiInterface) BackpainAPIClient.getCelluliteClient1().create(ApiInterface.class);
                //  ApiInterface   apiInterface = retrofit.create(ApiInterface.class);
                //  CelluliteAPIInterface apiInterface = retrofit.create(CelluliteAPIInterface.class);

                Call<GetComment> userCall = apiInterface.getComment(idofblog, "Bearer " + token);


                userCall.enqueue(new Callback<GetComment>() {
                    @Override
                    public void onResponse(Call<GetComment> call, Response<GetComment> response) {
                        Log.d("TAG1", response.code() + "");
                        GetComment getComment = (GetComment) response.body();
                        assert getComment != null;

                        ArrayList<GetComment.BlogComment> blogComments = (ArrayList<GetComment.BlogComment>) getComment.blogComment;


                        String[] heroes = new String[blogComments.size()];

                        for (int i = 0; i < blogComments.size(); i++) {
                            heroes[i] = blogComments.get(i).getComment();


                            Log.e("imagesname", String.valueOf(blogComments) + "hgh");


                        }

                        commentrecyclerview.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                        Adapter_addComment adapterTopblogs = new Adapter_addComment(context, blogComments, activity);
                        commentrecyclerview.setAdapter(adapterTopblogs);


                    }

                    @Override
                    public void onFailure(Call<GetComment> call, Throwable t) {
                        //   Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            } catch (Exception e) {

            }

        }


        class RetrieveFeedTask extends AsyncTask<String, String, Bitmap> {
            String src;

            public RetrieveFeedTask(String src) {
                this.src = src;
            }

            @Override
            protected Bitmap doInBackground(String... strings) {
                try {
                    URL url = new URL(src);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setDoInput(true);
                    connection.connect();
                    InputStream input = connection.getInputStream();
                    Bitmap myBitmaps = BitmapFactory.decodeStream(input);
                    return myBitmaps;
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);
                Intent shareIntent;

                String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Share.png";
                OutputStream out = null;
                File file = new File(path);
                try {
                    out = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                    out.flush();
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //      Uri bmpUri = FileProvider.getUriForFile(context, context.getPackageName() + ".provider", file);
                shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                //    shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                StringBuilder sb = new StringBuilder();
                sb.append(blodname.get(mPosition)/*.getBlogName()*/ + "\n");
                sb.append(Html.fromHtml(blodlink.get(mPosition)/*.getBlogLink()*/).toString());
                sb.append("\nHey check this application ");
                sb.append("https://play.google.com/store/apps/details?id=");
                sb.append(context.getPackageName());
                shareIntent.putExtra(Intent.EXTRA_TEXT, sb.toString());
                shareIntent.setType("*/*");
                context.startActivity(Intent.createChooser(shareIntent, "Share with"));
                // callShareApi();
            }
        }

        @Override
        public int getItemCount() {
            int size = blodname.size();
            if (blodname.size() > 0) {
                return blodname.size() + Math.round(blodname.size() / 6);
            }
            return blodname.size();
        }

        @Override
        public int getItemViewType(int position) {
            if ((position + 1) % 4 == 0) {
                return ADS;
            }
            return DATA;
        }
 /*   @Override
    public int getItemCount() {
        return blodname.size();
    }*/

        public class BlogdataHolder extends RecyclerView.ViewHolder {
            public int position;
            ImageView mIvicon, mIvsmallicon;
            TextView nameofblog, mTxtviews, blog_catagoryname, likecount, addcomment, blog_catagoryfollowers, btnexestart;
            RelativeLayout mBlogdetail;
            TextView time_text;
            CheckBox like;
            ImageView sharebutton;
            LinearLayout mLaylike;
            RelativeLayout ad_icon_layout;
            SliderView slider;
            HtmlTextView blogdescription;
            TextView link;
            RoundedImageView ivicon1;
            RelativeLayout catagoty_lay;

            public BlogdataHolder(View itemView) {
                super(itemView);
                nameofblog = (TextView) itemView.findViewById(R.id.nameofblog);
                blog_catagoryname = (TextView) itemView.findViewById(R.id.blog_catagoryname);
                mTxtviews = (TextView) itemView.findViewById(R.id.views);
                mBlogdetail = (RelativeLayout) itemView.findViewById(R.id.blogdetail);
                ad_icon_layout = itemView.findViewById(R.id.ad_icon_layout);
                like = itemView.findViewById(R.id.likebutton);
                likecount = itemView.findViewById(R.id.likecount);
                sharebutton = itemView.findViewById(R.id.sharebutton);
                mLaylike = (LinearLayout) itemView.findViewById(R.id.laylike);
                slider = itemView.findViewById(R.id.slider);
                //  ivicon1 = itemView.findViewById(R.id.ivicon1);
                addcomment = itemView.findViewById(R.id.addComment);
                blogdescription = (HtmlTextView) itemView.findViewById(R.id.blogdescription);
                blog_catagoryfollowers = itemView.findViewById(R.id.blog_catagoryfollowers);
                time_text = itemView.findViewById(R.id.time_text);
                link = itemView.findViewById(R.id.link);
                btnexestart = itemView.findViewById(R.id.btnexestart);
                catagoty_lay = itemView.findViewById(R.id.catagoty_lay);
            }
        }

        public class AdsHolder extends RecyclerView.ViewHolder {
            LinearLayout mRelativeFBAdmob;
            //   CardView mAdcard;

            AdsHolder(View itemView) {
                super(itemView);
                mRelativeFBAdmob = (LinearLayout) itemView.findViewById(R.id.adframe);
                //     mAdcard = (CardView) itemView.findViewById(R.id.adcard);
            }
        }

        NativeContentAdView nativeContentAdView;

        public void showGOOGLEAdvance(final Context context, final LinearLayout frameLayout) {
            AdLoader.Builder builder = new AdLoader.Builder(context, Constants.admob_nativead);
            builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
                public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                    frameLayout.setVisibility(View.VISIBLE);
                    nativeContentAdView = (NativeContentAdView) ((Activity) context).getLayoutInflater().inflate(R.layout.ad_content1, null);
                    nativeContentAdView.setVisibility(View.VISIBLE);

                    populateContentAdView(nativeContentAd, nativeContentAdView);
                    frameLayout.removeAllViews();
                    frameLayout.addView(nativeContentAdView);
                }
            });
            builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
            builder.withAdListener(new AdListener() {
                public void onAdFailedToLoad(int i) {
                    Log.e("error", "Failed to load native ad:: " + i);
                    nativeContentAdView.setVisibility(View.GONE);
                }
            }).build().loadAd(new AdRequest.Builder().build());
        }

        @SuppressLint("WrongConstant")
        public void populateContentAdView(NativeContentAd nativeContentAd, NativeContentAdView nativeContentAdView) {
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

        private void CallAddlike(String id, int pos) {
            //  String token = Constants.Authtoken;

            //   this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);

            // String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiIxMDJhMGNmNjc0NjZjYWNjIiwiaWF0IjoxNjI2MzIxMDcwfQ.v8_1g2RFNemXKR24C2bVTqbWrnZ7FZh7drO5M13-XzY";

            try {
                this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);

                JSONObject paramObject = new JSONObject();
                paramObject.put("update", "true");
                Call<AddLike> call = apiInterface.getLike(id, paramObject.toString(), "Bearer " + token);
                call.enqueue(new Callback<AddLike>() {
                    @Override
                    public void onResponse(Call<AddLike> call, Response<AddLike> response) {
                        Log.e("response", String.valueOf(response.code()));
                        AddLike addLike = response.body();
                        Log.e("likes", String.valueOf(addLike.getBlogLike()));
                        String likes = String.valueOf(addLike.getBlogLike());
                        Log.e("like", String.valueOf(likes));


                    }

                    @Override
                    public void onFailure(Call<AddLike> call, Throwable t) {
                        Log.e("eroor", t.toString());
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        private void CallAddunlike(String id, int pos) {

            //  String token = Constants.Authtoken;


            //   this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);

            // String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiIxMDJhMGNmNjc0NjZjYWNjIiwiaWF0IjoxNjI2MzIxMDcwfQ.v8_1g2RFNemXKR24C2bVTqbWrnZ7FZh7drO5M13-XzY";

            try {
                this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);

                JSONObject paramObject = new JSONObject();
                paramObject.put("update", "false");
                Call<AddLike> call = apiInterface.getLike(id, paramObject.toString(), "Bearer " + token);
                call.enqueue(new Callback<AddLike>() {
                    @Override
                    public void onResponse(Call<AddLike> call, Response<AddLike> response) {
                        Log.e("response", String.valueOf(response.body().toString()));

                        AddLike addLike = response.body();
                        Log.e("likes_unlike", String.valueOf(addLike.getBlogLike()));
                        String likes = String.valueOf(addLike.getBlogLike());

                    }

                    @Override
                    public void onFailure(Call<AddLike> call, Throwable t) {
                        Log.e("eroor", t.toString());
                    }
                });

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


        private class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterViewHolder> {
            private final List<String> mSliderItems;

            public SliderAdapter(Context context, List<String> sliderDataArrayList) {
                this.mSliderItems = sliderDataArrayList;
            }

            @Override
            public SliderAdapterViewHolder onCreateViewHolder(ViewGroup parent) {
                View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout, null);
                return new SliderAdapterViewHolder(inflate);
            }


            @Override
            public void onBindViewHolder(SliderAdapterViewHolder viewHolder, final int position) {

                //  final Bloglist.Blog.Image sliderItem = mSliderItems.get(position);

                Glide.with(viewHolder.itemView)
                        .load(musicurl + "blog/" + mSliderItems.get(position))
                        .into(viewHolder.imageViewBackground);

                viewHolder.imageViewBackground.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            if (!blodlink.get(position)/*.getBlogLink()*/.isEmpty()) {
                                Log.e("share", "gfgdedd");
                                Intent intent = new Intent(context, Activity_ShowBlogwebview.class);
                                intent.putExtra("link", blodlink.get(position)/*.getBlogLink()*/);
                                intent.putExtra("id", blodid.get(position)/*.getId()*/);
                                intent.putExtra("position", position);
                                intent.putExtra("userid", userId);
                                intent.putExtra("blogname", blodname.get(position)/*.getBlogName()*/);
                                intent.putExtra("bloglink", blodlink.get(position)/*.getBlogLink()*/);
                                intent.putExtra("bloglike", blodbloglike.get(position)/*.getBlogLike()*/);
                                String[] blogdata = new String[blodname.size()];
                                intent.putExtra("blogmodelList", String.valueOf(blogdata));
                                try {
                                    if (!imagename.isEmpty()) {
                                        intent.putExtra("image", blodimage.get(position)/*.getImage()*/.get(0));
                                    }
                                } catch (Exception e) {

                                }

                                context.startActivity(intent);
                            } else {
                                //  Toast.makeText(context, context.getResources().getString(R.string.error), Toast.LENGTH_LONG).show();
                            }
                        } catch (Exception e) {

                        }
                    }
                });
            }

            @Override
            public int getCount() {
                return mSliderItems.size();
            }

            class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {
                //Adapter class for initializing the views of our slider view.
                View itemView;
                ImageView imageViewBackground;

                public SliderAdapterViewHolder(View itemView) {
                    super(itemView);
                    imageViewBackground = itemView.findViewById(R.id.myimage);
                    this.itemView = itemView;
                }
            }
        }


    }


}
