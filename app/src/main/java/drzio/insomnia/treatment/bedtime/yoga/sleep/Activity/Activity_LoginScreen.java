package drzio.insomnia.treatment.bedtime.yoga.sleep.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.github.abdularis.civ.AvatarImageView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.ApiInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.Pricedata;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.Register_Api;
import drzio.insomnia.treatment.bedtime.yoga.sleep.util.DynamicBillingCheck;
import drzio.insomnia.treatment.bedtime.yoga.sleep.util.IabBroadcastReceiver;
import drzio.insomnia.treatment.bedtime.yoga.sleep.util.IabHelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

import static com.github.mikephil.charting.charts.Chart.LOG_TAG;

public class Activity_LoginScreen extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private CallbackManager callbackManager;
    private ProfileTracker profileTracker;
    private LoginButton loginButton;
    private URL profilePicture;
    private String userId;
    private String TAG = "LoginActivity";
    private LinearLayout mTxtforbtn;
    private static final int SIGN_IN_CODE = 9001;
    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInAccount account;
    private LinearLayout mloginlay;
    private LinearLayout mSucceslayout;
    private TextView mProfilename;
    private AvatarImageView mProfilepic;
    private boolean isFromFb = false;
    private boolean isFromGoogle = false;
    private TextView mBtnlogout;
    private TextView mTxtword;
    private TinyDB tinyDB;
    private String Gusername;
    private String Gmail;
    private String Guserid;
    private String Gprofilepicurl;
    private String GFirstname;
    private String GLastname;
    FitnessApplication fitapp;
    private String mIsFrom;
    private Dialog dialog;
    private InterstitialAd mInterstitialAdMob;
    private int Gad;
    private BackpainAPIInterface apiInterface;
    private boolean isClicked = false;
    Location mLastLocation;
    LocationRequest mLocationRequest;
    private static final int REQUEST_ACCESS_FINE_LOCATION = 111;
    private String BASEURL = "";
    private ApiInterface apiInterfaces;
    String nickname, first_name = "UYUYU", last_name = "YFYHTF", email = "ABC@gmail.com", height = "150", weight = "40", gender = "female", mobile_number = "", age = "25", user_type_id = "", paid_status = "",
            country_id = "India", state_id = "", city_id = "", fcm_token = "yhtgfyhtf", facebook = "", google = "", user_image = "";
    private String langauge = "";
    private String firstName = "";
    private String lastName = "";
    private String birthday = "";
    private IabHelper mHelper;
    private IabBroadcastReceiver mBroadcastReceiver;
    static String ITEM_SKU = "";
    String SKU_DELAROY_MONTHLY;
    String SKU_DELAROY_SIXMONTH;
    private BillingProcessor bp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_loginscreen);
        callbackManager = CallbackManager.Factory.create();
        mTxtforbtn = (LinearLayout) findViewById(R.id.txt_skip);
        mBtnlogout = (TextView) findViewById(R.id.btn_logout);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mIsFrom = bundle.getString("isFrom");
        }
        initAdmobFullAd(this);
        loadAdmobAd();
        fitapp = FitnessApplication.getInstance();
        tinyDB = new TinyDB(Activity_LoginScreen.this);
        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(this, languages);

        tinyDB.putBoolean(Constants.ISGUIDEON_KEY, true);
        tinyDB.putBoolean(Constants.ISTIPSON_KEY, true);

        mTxtword = (TextView) findViewById(R.id.txtword);
        mloginlay = (LinearLayout) findViewById(R.id.loginlayout);
        mSucceslayout = (LinearLayout) findViewById(R.id.successlayout);
        mProfilename = (TextView) findViewById(R.id.profilename);
        mProfilepic = (AvatarImageView) findViewById(R.id.profile_image);
        TextView mBtncondition = (TextView) findViewById(R.id.btncondition);
        TextView mBtnprivacy = (TextView) findViewById(R.id.btnprivacy);
        mBtnprivacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPrivacy(1);
            }
        });
        mBtncondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPrivacy(2);
            }
        });
        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setHeight(100);
        loginButton.setTextColor(Color.WHITE);
        loginButton.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
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
        loginButton.setCompoundDrawablePadding(0);
        BASEURL = tinyDB.getString(Constants.NewBaseUrl);
        Log.e("BASEURLNewapi", BASEURL);
        ImageView signInButton = (ImageView) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                isFromFb = false;
                isFromGoogle = true;
                ConnectivityManager mgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = mgr.getActiveNetworkInfo();
                if (netInfo != null) {
                    if (netInfo.isConnected()) {
                        signIn();

                    } else {

                        Toast.makeText(Activity_LoginScreen.this, "No internet", Toast.LENGTH_SHORT).show();

                    }
                } else {

                    Toast.makeText(Activity_LoginScreen.this, "No internet", Toast.LENGTH_SHORT).show();


                }


            }
        });

    /*    if (ActivityCompat.checkSelfPermission(Activity_LoginScreen.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Activity_LoginScreen.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Activity_LoginScreen.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }*/
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addApi(LocationServices.API)
                .build();



        FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                isFromGoogle = false;
                isFromFb = true;
                mSucceslayout.setVisibility(View.VISIBLE);
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.e(TAG, object.toString());
                        Log.e(TAG, response.toString());

                        try {
                            userId = object.getString("id");
                            profilePicture = new URL("https://graph.facebook.com/" + userId + "/picture?width=500&height=500");
                            if (object.has("first_name"))
                                firstName = object.getString("first_name");
                            if (object.has("last_name"))
                                lastName = object.getString("last_name");
                            if (object.has("email"))
                                email = object.getString("email");
                            if (object.has("birthday"))
                                birthday = object.getString("birthday");
                            if (object.has("gender"))
                                gender = object.getString(Constants.GENDER_KEY);

                            updateUI(true);
                            String tempname = firstName + " " + lastName;
                            updatedata(tempname, profilePicture.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email, birthday, gender , location");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException e) {
                e.printStackTrace();
            }
        };

        loginButton.setReadPermissions("email", "user_birthday", "user_posts", "user_location");
        loginButton.registerCallback(callbackManager, callback);
        mTxtforbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tinyDB.putBoolean("isFirsttime", true);
                if (mIsFrom.equals("reports")) {
                   /* Intent intent = new Intent(Activity_LoginScreen.this, Activity_Reports.class);
                    startActivity(intent);
                    finish();*/

                    Intent intent = new Intent(Activity_LoginScreen.this, MainActivity.class);
                    //   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    Bundle data1 = new Bundle();
                    data1.putInt("discover", 2);
                    intent.putExtras(data1);
                    //    intent.putExtra("isFrom3", true);
                    startActivity(intent);
                    finish();
                } else if (mIsFrom.equals("purchase")) {
                    Intent intent = new Intent(Activity_LoginScreen.this, Activity_Purchase.class);
                    intent.putExtra("proceed", true);
                    startActivity(intent);
                    finish();
                } else {
                    if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
                        Gad = 2;
                        mInterstitialAdMob.show();
                    } else {

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
                            //  String user_type_id = String.valueOf(tinyDB.getInt(Constants.MEALTYPE_KEY));
                            String paid_status = "0";
                            String country_id = tinyDB.getString(Constants.COUNTRYID_KEY);
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
                            String state_id = tinyDB.getString(Constants.STATEID_KEY);
                            //     Log.e("state_id1",state_id);
                            String city_id = tinyDB.getString(Constants.CITYID_KEY);
                            //     Log.e("city_id1",city_id);
                            String fcm_token = tinyDB.getString(Constants.FCMTOKEN_KEY);
                            String facebook = "";
                            String google = Guserid;
                            String user_image = Gprofilepicurl;


                            ConnectivityManager mgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                            NetworkInfo netInfo = mgr.getActiveNetworkInfo();
                            if (netInfo != null) {
                                if (netInfo.isConnected()) {
                                    // tinyDB.putBoolean(Constants.SKIP_NOINTERNET, false);
                                    callRegisterApi(firstName, lastName, email, gender,
                                            age, height, weight, country_id,
                                            state_id, city_id, fcm_token, google, langauge, mHeightP, mWeightP);
                                } else {

                                    tinyDB.putBoolean(Constants.SKIP_NOINTERNET, true);
                                    Intent intent = new Intent(Activity_LoginScreen.this, MainActivity.class);

                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                            Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                                    finish();
                                }
                            } else {
                                tinyDB.putBoolean(Constants.SKIP_NOINTERNET, true);
                                Intent intent = new Intent(Activity_LoginScreen.this, MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                        Intent.FLAG_ACTIVITY_CLEAR_TASK);

                                startActivity(intent);
                                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                                finish();



                            //NextActivity();
                        }
                    }
                }
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
                    Toast.makeText(Activity_LoginScreen.this, "The app was not allowed to get your location, Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }
            }

        }

    }

    @SuppressLint("WrongConstant")
    public void callRegisterApi1(String first_name, String last_name,
                                 String email,
                                 String gender, String age, String height,
                                 String weight, String country_id, String state_id, String city_id,
                                 String fcm_token, String google, String language, String heightp, String weightp) {



        try {
            String getusertype = tinyDB.getString(Constants.USERTYPEKEY);
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

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            BASEURL = tinyDB.getString(Constants.NewBaseUrl);

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
            // this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);

            Call<Register_Api> userCall = apiInterface.getUser(paramObject.toString());
            userCall.enqueue(new Callback<Register_Api>() {
                @Override
                public void onResponse(Call<Register_Api> call, Response<Register_Api> response) {
                    try {


                        Register_Api dietCateData = (Register_Api) response.body();

                        Register_Api.Docs docs = dietCateData.getDocs();
                        Log.e("device_tokens", String.valueOf(dietCateData.token));
                        Log.e("getCountry", String.valueOf(docs.getCountry()));
                        String tpken=dietCateData.token;
                        getPriceList(tpken);
                        tinyDB.putString(Constants.Authtoken, dietCateData.token);
                        tinyDB.putString(Constants.USEREMAIL_KEY, Gmail);
                        String userid = docs.getId();
                        tinyDB.putString(Constants.USERID_KEYs, userid);
                        tinyDB.getBoolean(Constants.PREMIUN_KEY);
                    /*    Intent intent = new Intent(Activity_LoginScreen.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                                Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();*/


                    } catch (Exception e) {

                    }
                }

                @Override
                public void onFailure(Call<Register_Api> call, Throwable t) {

                }
            });
        } catch (Exception e) {

        }

    }


    @SuppressLint("WrongConstant")
    public void callRegisterApi(String first_name, String last_name,
                                String email,
                                String gender, String age, String height,
                                String weight, String country_id, String state_id, String city_id,
                                String fcm_token, String google, String language, String heightp, String weightp) {

        if (BASEURL.isEmpty()) {
            callfirebasedata();
        } else {
            try {
                String getusertype = tinyDB.getString(Constants.USERTYPEKEY);
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
                paramObject.put("language", language);
                paramObject.put("device_id", androidId);
                paramObject.put("heightP", heightp);
                paramObject.put("weightP", weightp);
                paramObject.put("app_number", String.valueOf(22));
                paramObject.put("user_type", getusertype);
                //   this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);

                String token = tinyDB.getString(Constants.Authtoken);
                //  String token = tinyDB.getString(Constants.Authtoken);
                Gson gson = new GsonBuilder()
                        .setLenient()
                        .create();
                BASEURL = tinyDB.getString(Constants.NewBaseUrl);
                HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
                httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                Retrofit retrofit = new Retrofit.Builder().baseUrl(BASEURL)
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build())
                        .build();

                ApiInterface apiInterface = retrofit.create(ApiInterface.class);

                Call<Register_Api> userCall = apiInterface.getUser(paramObject.toString());
                userCall.enqueue(new Callback<Register_Api>() {
                    @Override
                    public void onResponse(Call<Register_Api> call, Response<Register_Api> response) {
                        try {


                            Register_Api dietCateData = (Register_Api) response.body();

                            Register_Api.Docs docs = dietCateData.getDocs();

                            String token_skip = dietCateData.getToken();
                            Log.e("tokenskip", token_skip);
                            tinyDB.putString(Constants.Authtoken, dietCateData.token);

                            String userid = docs.getId();
                            tinyDB.putString(Constants.USERID_KEYs, userid);

                            Log.e("getfName", String.valueOf(docs.getDeviceId() + "fgfhfgh"));
                            String tpken = tinyDB.getString(Constants.Authtoken);
                            tinyDB.putString(Constants.USERID_KEY, docs.getId());
                            tinyDB.putString(Constants.USEREMAIL_KEY, docs.getEmail());
                            tinyDB.putString(Constants.USERFIRSTNAME_KEY, docs.getfName());
                            tinyDB.putString(Constants.USERLASTNAME_KEY, docs.getlName());
                            if (tpken != null) {
                                getPriceList(tpken);


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

    private void getPriceList(String tpken) {
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
            Call<Pricedata> userCall = apiInterface.getprice_new("Bearer " + tpken);
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
                            tinyDB.putString(Constants.DISCOUNT_TXT, "null");
                        } else {
                            tinyDB.putString(Constants.DISCOUNT_TXT, percentage);
                        }
                        if (percentage2.isEmpty()) {
                            tinyDB.putString(Constants.DISCOUNT_TXT2, "null");
                        } else {
                            tinyDB.putString(Constants.DISCOUNT_TXT2, percentage2);
                        }

                        tinyDB.putString(Constants.SKU_DELAROY_MONTHLY, month_key);
                        tinyDB.putString(Constants.SKU_DELAROY_SIXMONTH, one_y_key);
                        tinyDB.putString(Constants.ITEM_SKU1, life_t_key);
                        //OneTimePurchaseCheck();
                        PurchaseCheck();
                        NextActivity();
                    } catch (Exception e) {
                        NextActivity();

                    }
                    //Pricedata. datalists = loginData.result;
                    //  Log.e("id", String.valueOf(Pricedata.id));

                }

                @Override
                public void onFailure(Call<Pricedata> call, Throwable t) {
                    NextActivity();
                    // Log.e("response1", String.valueOf("response"));
                }
            });
        } catch (Exception e) {

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
                if (!tinyDB.getBoolean(Constants.PREMIUN_KEY)) {
                    ShowAds();
                }
            }

            @Override
            public void onBillingInitialized() {
                bp.loadOwnedPurchasesFromGoogle();
                String   Lifetime1 = tinyDB.getString(Constants.ITEM_SKU1);
                String Oneyear1 = tinyDB.getString(Constants.SKU_DELAROY_SIXMONTH);
                String    Onemonth1 = tinyDB.getString(Constants.SKU_DELAROY_MONTHLY);
                if (bp.isPurchased(Lifetime1)) {
                    tinyDB.putBoolean(Constants.PREMIUN_KEY, true);
                    NoAds();

                    if (bp.loadOwnedPurchasesFromGoogle()) {
                        Log.d(LOG_TAG, "Owned Subscription: " + "Oneyear Subscriptions updated.");
                    }
                    Log.d(LOG_TAG, "Owned Subscription: " + "onetime");
                } else if (bp.isSubscribed(Onemonth1)) {
                    tinyDB.putBoolean(Constants.PREMIUN_KEY, true);
                    NoAds();

                    Log.d(LOG_TAG, "Owned Subscription: " + "1 Month");
                    if (bp.loadOwnedPurchasesFromGoogle()) {
                        Log.d(LOG_TAG, "update2: " + bp.loadOwnedPurchasesFromGoogle());
                    }
                } else if (bp.isSubscribed(Oneyear1)) {
                    tinyDB.putBoolean(Constants.PREMIUN_KEY, true);
                    NoAds();

                    if (bp.loadOwnedPurchasesFromGoogle()) {
                        Log.d(LOG_TAG, "Owned Subscription: " + "Oneyear Subscriptions updated.");
                    }
                } else {
                    //   String   Lifetime1 = tinyDB.getString(Constants.ITEM_SKU1);
                    tinyDB.putBoolean(Constants.PREMIUN_KEY, false);
                    ShowAds();
                    bp.consumePurchase(Lifetime1);
                    Log.d(LOG_TAG, "Owned Subscription: " + "No Subscripytions");
                }
            }

            @Override
            public void onPurchaseHistoryRestored() {
               /* if (bp.loadOwnedPurchasesFromGoogle()) {
                    tinyDB.putBoolean(Constants.PREMIUN_KEY,true);
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


  /*  public void OneTimePurchaseCheck() {
        String base64EncodedPublicKey = getString(R.string.base64key);
        mHelper = new IabHelper(this, base64EncodedPublicKey);
        mHelper.enableDebugLogging(true);

        IabHelper.QueryInventoryFinishedListener mGotInventoryListener = (result, inv) -> {
            if (result.isFailure()) {
                Log.e("onetimecheck", "failed to check");
                ShowAds();
                return;
            }
            ITEM_SKU = tinyDB.getString(Constants.ITEM_SKU1);
            boolean mIsPremium = inv.hasPurchase(ITEM_SKU);
            if (mIsPremium) {
                tinyDB.putBoolean(Constants.ONETIMEPURCHASE, true);
                tinyDB.putBoolean(Constants.PREMIUN_KEY, true);
                Log.e("onetimecheck_login", String.valueOf(mIsPremium));
                NoAds();
              *//*  mHelper.consumeAsync(inv.getPurchase(ITEM_SKU),
                        new IabHelper.OnConsumeFinishedListener() {
                            @Override
                            public void onConsumeFinished(Purchase purchase, IabResult result) {
                                Toast.makeText(Appstart_Activity.this,"Purchase Consumed",Toast.LENGTH_SHORT).show();
                            }
                        });*//*
            } else {
                tinyDB.putBoolean(Constants.ONETIMEPURCHASE, false);
                tinyDB.putBoolean(Constants.PREMIUN_KEY, false);
                new DynamicBillingCheck().onCreate(Activity_LoginScreen.this);
                Log.e("onetimecheck", String.valueOf(mIsPremium));
            }
        };
        mHelper.startSetup(result -> {
            if (!result.isSuccess()) {
                ShowAds();
                Log.d(TAG, "In-app Billing setup failed: " +
                        result);
            } else {
                Log.d(TAG, "In-app Billing is set up OK");
            }

            mBroadcastReceiver = new IabBroadcastReceiver(() -> {
                Log.d(TAG, "Received broadcast notification. Querying inventory.");
                try {
                    mHelper.queryInventoryAsync(mGotInventoryListener);
                } catch (Exception e) {
                    Log.d(TAG, "Error querying inventory. Another async operation in progress.");
                }
            });
            IntentFilter broadcastFilter = new IntentFilter(IabBroadcastReceiver.ACTION);
            registerReceiver(mBroadcastReceiver, broadcastFilter);

            try {
                mHelper.queryInventoryAsync(mGotInventoryListener);
            } catch (Exception e) {
                Log.d(TAG, "Error querying inventory. Another async operation in progress.");
            }
        });
    }*/

    public void OneTimePurchaseCheck() {
        String base64EncodedPublicKey = getString(R.string.base64key);
        mHelper = new IabHelper(this, base64EncodedPublicKey);
        mHelper.enableDebugLogging(true);

        IabHelper.QueryInventoryFinishedListener mGotInventoryListener = (result, inv) -> {
            if (result.isFailure()) {
                Log.e("onetimecheck11_login", "failed to check");
                ShowAds();
                return;
            }
            SKU_DELAROY_MONTHLY = tinyDB.getString(Constants.SKU_DELAROY_MONTHLY);
            SKU_DELAROY_SIXMONTH = tinyDB.getString(Constants.SKU_DELAROY_SIXMONTH);
            ITEM_SKU = tinyDB.getString(Constants.ITEM_SKU1);

            boolean mIsPremium = inv.hasPurchase(ITEM_SKU);
            if (mIsPremium) {
                tinyDB.putBoolean(Constants.ONETIMEPURCHASE, true);
                tinyDB.putBoolean(Constants.PREMIUN_KEY, true);
                Log.e("onetimecheck1_login", String.valueOf(mIsPremium));
                NoAds();
              /*  mHelper.consumeAsync(inv.getPurchase(ITEM_SKU),
                        new IabHelper.OnConsumeFinishedListener() {
                            @Override
                            public void onConsumeFinished(Purchase purchase, IabResult result) {
                                Toast.makeText(Appstart_Activity.this,"Purchase Consumed",Toast.LENGTH_SHORT).show();
                            }
                        });*/
            } else {
                tinyDB.putBoolean(Constants.ONETIMEPURCHASE, false);
                tinyDB.putBoolean(Constants.PREMIUN_KEY, false);
                new DynamicBillingCheck().onCreate(Activity_LoginScreen.this);
                Log.e("onetimecheck12", String.valueOf(mIsPremium));
            }
        };
        mHelper.startSetup(result -> {
            if (!result.isSuccess()) {
                ShowAds();
                Log.d(TAG, "In-app Billing setup failed: " +
                        result);
            } else {
                Log.d(TAG, "In-app Billing is set up OK");
            }

            mBroadcastReceiver = new IabBroadcastReceiver(() -> {
                Log.d(TAG, "Received broadcast notification. Querying inventory.");
                try {
                    mHelper.queryInventoryAsync(mGotInventoryListener);
                } catch (Exception e) {
                    Log.d(TAG, "Error querying inventory. Another async operation in progress.");
                }
            });
            IntentFilter broadcastFilter = new IntentFilter(IabBroadcastReceiver.ACTION);
            registerReceiver(mBroadcastReceiver, broadcastFilter);

            try {
                mHelper.queryInventoryAsync(mGotInventoryListener);
            } catch (Exception e) {
                Log.d(TAG, "Error querying inventory. Another async operation in progress.");
            }
        });
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


    public void callfirebasedata() {


        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();

            Log.e("database", String.valueOf(database));
            DatabaseReference mRefer = database.getReference();
            mRefer.child("ApiNames").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        String name = postSnapshot.getKey().toString();
                        Log.e("apiname", name);
                        if (name.equals("AppstoreApi")) {
                            tinyDB.putString(Constants.AppstoreUrl, (String) postSnapshot.getValue());
                            Log.e("Api1", (String) postSnapshot.getValue());
                        }
                        if (name.equals("DeepSleep")) {
                            tinyDB.putString(Constants.CelluliteUrl, (String) postSnapshot.getValue());
                            Log.e("Api2", (String) postSnapshot.getValue());
                        }
                        if (name.equals("YoutubeApi")) {
                            tinyDB.putString(Constants.YoutubeApi, (String) postSnapshot.getValue());
                            Log.e("Api3", (String) postSnapshot.getValue());
                        }
                        if (name.equals("Appstore_Appid")) {
                            tinyDB.putString(Constants.Appstore_Appid, (String) postSnapshot.getValue());
                            Log.e("Api4", (String) postSnapshot.getValue());
                        }
                        if (name.equals("Banner_id")) {
                            tinyDB.putString(Constants.Storebanner_id, (String) postSnapshot.getValue());
                            Log.e("Api5", (String) postSnapshot.getValue());
                        }
                        if (name.equals("NewsleepApi")) {
                            tinyDB.putString(Constants.NewBaseUrl, (String) postSnapshot.getValue());

//                               tinyDB.putString(Constants.NewBaseUrl, "http://65.0.77.129:7079/");
                            Log.e("Api6", (String) postSnapshot.getValue());
                            Log.e("Apitest", tinyDB.getString(Constants.NewBaseUrl));
                        }
                        if (name.equals("sleepimgurl")) {
                            tinyDB.putString(Constants.Backimageurl, (String) postSnapshot.getValue());
                            Log.e("Api7", (String) postSnapshot.getValue());
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Log.e("databaseError", String.valueOf(databaseError));
                }
            });
        } catch (Exception e) {
            Log.e("e", String.valueOf(e));
        }

    }

    public void initAdmobFullAd(Context context) {
        if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
            return;
        }
        mInterstitialAdMob = new InterstitialAd(context);
        mInterstitialAdMob.setAdUnitId(Constants.admob_Interstitial);
        mInterstitialAdMob.setAdListener(new com.google.android.gms.ads.AdListener() {
            @Override
            public void onAdClosed() {
                if (Gad == 0) {
                 /*   Intent intent = new Intent(Activity_LoginScreen.this, Activity_Reports.class);
                    startActivity(intent);
                    finish();*/


                    Intent intent = new Intent(Activity_LoginScreen.this, MainActivity.class);
                    //   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    Bundle data1 = new Bundle();
                    data1.putInt("discover", 2);
                    intent.putExtras(data1);
                    //    intent.putExtra("isFrom3", true);
                    startActivity(intent);
                    finish();
                } /*else if (Gad == 1) {
                    Intent intent = new Intent(Activity_LoginScreen.this, Activity_Purchase.class);
                    intent.putExtra("proceed", true);
                    startActivity(intent);
                    finish();
                } */ else if (Gad == 2) {
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
                    //  String user_type_id = String.valueOf(tinyDB.getInt(Constants.MEALTYPE_KEY));
                    String paid_status = "0";
                    String country_id = tinyDB.getString(Constants.COUNTRYID_KEY);
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
                    String state_id = tinyDB.getString(Constants.STATEID_KEY);
                    //     Log.e("state_id1",state_id);
                    String city_id = tinyDB.getString(Constants.CITYID_KEY);
                    //     Log.e("city_id1",city_id);
                    String fcm_token = tinyDB.getString(Constants.FCMTOKEN_KEY);
                    String facebook = "";
                    String google = Guserid;
                    String user_image = Gprofilepicurl;

                    callRegisterApi(firstName, lastName, email, gender,
                            age, height, weight, country_id,
                            state_id, city_id, fcm_token, google, langauge, mHeightP, mWeightP);
                  /*  Intent intent = new Intent(Activity_LoginScreen.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();*/
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

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);

        if (requestCode == SIGN_IN_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(intent);
            handleSignInResult(result);
        } else {
            callbackManager.onActivityResult(requestCode, responseCode, intent);
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


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, SIGN_IN_CODE);
    }

    private void signOut() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(@NonNull Status status) {
                        updateUI(false);
                    }
                });
    }


    private void updateUI(boolean signedIn) {
        if (signedIn) {
            try {
                tinyDB.putBoolean(Constants.ISLOGIN_KEY, true);
                mloginlay.setVisibility(View.GONE);
                mSucceslayout.setVisibility(View.GONE);
                tinyDB.putBoolean("isFirsttime", true);
            } catch (Exception e) {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }

                Intent intent = new Intent(Activity_LoginScreen.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
                e.printStackTrace();
            }

        } else {
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


    public void DialogPageLoading() {
        try {
            dialog = new Dialog(Activity_LoginScreen.this);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setContentView(R.layout.dialog_adloading);
            LinearLayout mMainlay = dialog.findViewById(R.id.mainlay);
            mMainlay.setBackground(null);
            TextView temptext = dialog.findViewById(R.id.txttitle);
            LottieAnimationView mLottie1 = (LottieAnimationView) dialog.findViewById(R.id.lotti);
            mLottie1.setVisibility(View.GONE);
            LottieAnimationView mLottie = (LottieAnimationView) dialog.findViewById(R.id.lotti2);
            mLottie.setVisibility(View.VISIBLE);
            temptext.setText(getResources().getString(R.string.prepe_app));
            mLottie.setAnimation("loadanimdial.json");
            mLottie.playAnimation();
            mLottie.loop(true);
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setAttributes(lp);


            dialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        try {
                            LoginData loginData = (LoginData) response.body();
                            LoginData.Datalist dataist = loginData.dataist;
                            String status = loginData.status;
                            Log.e("logintsttus", status);

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
                                //    Log.e("coutry",country_id);

                                String state_id = tinyDB.getString(Constants.STATEID_KEY);
                                //     Log.e("state_id1",state_id);
                                String city_id = tinyDB.getString(Constants.CITYID_KEY);
                                //     Log.e("city_id1",city_id);
                                String fcm_token = tinyDB.getString(Constants.FCMTOKEN_KEY);
                                String facebook = "";
                                String google = Guserid;
                                String user_image = Gprofilepicurl;

                                callRegisterApi(first_name, last_name, email, mobile_number, gender,
                                        age, height, weight, user_type_id, paid_status, country_id,
                                        state_id, city_id, fcm_token, facebook, google, user_image);

                            } else {
                                tinyDB.putString(Constants.USERID_KEY, dataist.getId());
                                tinyDB.putString(Constants.USEREMAIL_KEY, dataist.getEmail());
                                tinyDB.putString(Constants.USERFIRSTNAME_KEY, dataist.getFirst_name());
                                tinyDB.putString(Constants.USERLASTNAME_KEY, dataist.getLast_name());
                                //tinyDB.putString(Constants.COUNTRYID_KEY, dataist.getCountryname());
                                String userid = dataist.getId();
                                String age = dataist.getAge();
                                String gender = dataist.getGender();
                                String height = dataist.getHeight();
                                String weight = dataist.getWeight();
                                String usertype = dataist.getUser_type();
                                String countryname = dataist.getCountryname();
                                //    Log.e("countryname",countryname);
                                updatedata(userid, age, gender, height, weight, usertype, countryname);
                                if (dialog != null && dialog.isShowing()) {
                                    dialog.dismiss();
                                }
                                if (mIsFrom.equals("reports")) {
//                            if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
//                                Gad = 0;
//                                mInterstitialAdMob.show();
//                            } else {
                                    Intent intent = new Intent(Activity_LoginScreen.this, Activity_Reports.class);
                                    startActivity(intent);
                                    finish();
//                            }
                                } else if (mIsFrom.equals("purchase")) {
//                            if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
//                                Gad = 1;
//                                mInterstitialAdMob.show();
//                            } else {
                                    Intent intent = new Intent(Activity_LoginScreen.this, Activity_Purchase.class);
                                    intent.putExtra("proceed", true);
                                    startActivity(intent);
                                    finish();
//                            }
                                } else {
//                            if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
//                                Gad = 2;
//                                mInterstitialAdMob.show();
//                            } else {
                                    Intent intent = new Intent(Activity_LoginScreen.this, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
//                            }
                                }
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            NextActivity();
                        }
                    }catch (Exception e){

                    }
                }

                @Override
                public void onFailure(@NotNull Call<LoginData> call, @NotNull Throwable t) {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    NextActivity();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }*/

    public void updatedata(String userid, String mAge, String gen, String height, String weight, String mealtype, String countryid) {
        try {
            tinyDB.putString(Constants.USERID_KEY, userid);
            tinyDB.putInt(Constants.AGE_KEY, Integer.parseInt(mAge));
            tinyDB.putString(Constants.COUNTRYID_KEY, countryid);
            tinyDB.putString(Constants.GENDER_KEY, gen);
            if (height.contains("cm")) {
                tinyDB.putBoolean(Constants.ISCM_KEY, true);
                height = height.replace(" cm", "");
                tinyDB.putFloat(Constants.HEIGHT_KEY, Float.parseFloat(height));
            } else {
                try {
                    String doubleAsString = height;
                    int indexOfDecimal = doubleAsString.indexOf(".");
                    int mFtval = Integer.parseInt(doubleAsString.substring(0, indexOfDecimal));
                    String mTrim = doubleAsString.substring(indexOfDecimal).replace(".", "");
                    mTrim = mTrim.replace(" ft", "");
                    int mInchval = Integer.parseInt(mTrim);
                    tinyDB.putBoolean(Constants.ISCM_KEY, false);
                    String temp = mFtval + "." + mInchval;
                    tinyDB.putFloat(Constants.HEIGHT_KEY, Float.parseFloat(temp));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (weight.contains("kg")) {
                tinyDB.putBoolean(Constants.ISKG_KEY, true);
                weight = weight.replace(" kg", "");
                tinyDB.putInt(Constants.WEIGHT_KEY, Integer.parseInt(weight));
            } else {
                tinyDB.putBoolean(Constants.ISKG_KEY, false);
                weight = weight.replace(" lb", "");
                tinyDB.putInt(Constants.WEIGHT_KEY, Integer.parseInt(weight));
            }
            if (mealtype.equals(getResources().getString(R.string.veg))) {
                tinyDB.putInt(Constants.MEALTYPE_KEY, 1);
                tinyDB.putString(Constants.USERTYPEKEY, "veg");
            } else if (mealtype.equals(getResources().getString(R.string.non_veg))) {
                tinyDB.putInt(Constants.MEALTYPE_KEY, 2);
                tinyDB.putString(Constants.USERTYPEKEY, "nonveg");
            } else if (mealtype.equals("Vegan")) {
                tinyDB.putInt(Constants.MEALTYPE_KEY, 3);
                tinyDB.putString(Constants.USERTYPEKEY, "vegan");
            }

        } catch (Exception e) {
            Log.e("loginerror", e.getMessage().toString());
            e.printStackTrace();
        }
    }




    public void NextActivity() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
        if (mIsFrom.equals("reports")) {
//            if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
//                Gad = 0;
//                mInterstitialAdMob.show();
//            } else {
                /*Intent intent = new Intent(Activity_LoginScreen.this, Activity_Reports.class);
                startActivity(intent);
                finish();*/

            Intent intent = new Intent(Activity_LoginScreen.this, MainActivity.class);
            //   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            Bundle data1 = new Bundle();
            data1.putInt("discover", 2);
            intent.putExtras(data1);
            //    intent.putExtra("isFrom3", true);
            startActivity(intent);
            finish();
//            }
        } else if (mIsFrom.equals("purchase")) {

            Intent intent = new Intent(Activity_LoginScreen.this, Activity_Purchase.class);

            intent.putExtra("proceed", true);
            startActivity(intent);
            finish();

        } else {

            tinyDB.putBoolean(Constants.SKIP_NOINTERNET, false);
            Intent intent = new Intent(Activity_LoginScreen.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                    Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
            finish();

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
    protected void onStart() {
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
        try {
            if (mGoogleApiClient.isConnected()){
                settingRequest();
            }

        }catch (Exception e){

        }
    }


    private void handleSignInResult(GoogleSignInResult result) {

        Log.e(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.getStatus().getStatusCode() == 7) {
            updateUI(true);
        }
        if (result.isSuccess()) {
            DialogPageLoading();
            account = result.getSignInAccount();
            Gusername = account.getDisplayName();
            Gmail = account.getEmail();
            Guserid = account.getId();
            Gprofilepicurl = String.valueOf(account.getPhotoUrl());
            GFirstname = account.getGivenName();
            GLastname = account.getFamilyName();
            boolean mIslogedin = tinyDB.getBoolean(Constants.ISLOGIN_KEY);


            first_name = GFirstname;
            last_name = GLastname;
            email = Gmail;
            mobile_number = "0";
            gender = tinyDB.getString(Constants.GENDER_KEY);
            age = String.valueOf(tinyDB.getInt(Constants.AGE_KEY));
            String mHeight;
            String mHeightP;
            if (tinyDB.getBoolean(Constants.ISCM_KEY)) {
                mHeight = String.valueOf(tinyDB.getFloat(Constants.HEIGHT_KEY)) /*+ " cm"*/;
                mHeightP = "cm";
            } else {
                mHeight = String.valueOf(tinyDB.getFloat(Constants.HEIGHT_KEY)) /*+ " ft"*/;
                mHeightP = "ft";

            }
            height = mHeight;
            String mWeight;
            String mWeightP;
            if (tinyDB.getBoolean(Constants.ISKG_KEY)) {
                mWeight = String.valueOf(tinyDB.getInt(Constants.WEIGHT_KEY)) /*+ " kg"*/;
                mWeightP = "kg";
            } else {
                mWeight = String.valueOf(tinyDB.getInt(Constants.WEIGHT_KEY))/* + " lb"*/;
                mWeightP = "lb";
            }
            weight = mWeight;
//            String user_type_id = String.valueOf(tinyDB.getInt(Constants.MEALTYPE_KEY));
            paid_status = "0";
            country_id = tinyDB.getString(Constants.COUNTRYID_KEY);
                   Log.e("coutry",country_id);
            langauge = Resources.getSystem().getConfiguration().locale.getLanguage();
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
            state_id = tinyDB.getString(Constants.STATEID_KEY);
                 Log.e("state_id1",state_id);
            city_id = tinyDB.getString(Constants.CITYID_KEY);
                 Log.e("city_id1",city_id);
            fcm_token = tinyDB.getString(Constants.FCMTOKEN_KEY);
            String facebook = "";
            google = Guserid;
            String user_image = Gprofilepicurl;


            Log.e("mHeightP", height);
            Log.e("weight", weight);
            if (!tinyDB.getBoolean(Constants.ISLOGIN_KEY)) {
                callRegisterApi1(first_name, last_name, email, gender,
                        age, height, weight, country_id,
                        state_id, city_id, fcm_token, google, langauge, mHeightP, mWeightP);
            }

            //   NextActivity();

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

    private void logout() {
        LoginManager.getInstance().logOut();
        updateUI(false);
    }


    public void DialogPrivacy(int i) {
        final Dialog dialog = new Dialog(Activity_LoginScreen.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_privacypolicy);
        TextView mTitle = (TextView) dialog.findViewById(R.id.txttitle);
        TextView mDesc = (TextView) dialog.findViewById(R.id.txtdescription);
        if (i == 1) {
            mTitle.setText("Privacy Policy");
            mDesc.setText(getResources().getString(R.string.policytxt));
        } else {
            mTitle.setText("Terms of service");
            mDesc.setText(getResources().getString(R.string.termstxt));
        }
        TextView mBtnok = (TextView) dialog.findViewById(R.id.btnok);

        mBtnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (dialog != null && dialog.isShowing()) {
            dialog.cancel();
        }

        if (mBroadcastReceiver != null) {
            unregisterReceiver(mBroadcastReceiver);
        }

        if (mHelper != null) mHelper.dispose();
        mHelper = null;

        finish();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
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
                            status.startResolutionForResult(Activity_LoginScreen.this, 1000);
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
                getCountryName(this, mLastLocation.getLatitude(), mLastLocation.getLongitude());
            } else {
                /*if there is no last known location. Which means the device has no data for the loction currently.
                 * So we will get the current location.
                 * For this we'll implement Location Listener and override onLocationChanged*/
                Log.i("Current Location", "No data for location found");

                if (!mGoogleApiClient.isConnected())
                    mGoogleApiClient.connect();

                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, Activity_LoginScreen.this);

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
                Log.e("getCountryName", addresses.get(0).getCountryName());
                Log.e("getstateName", addresses.get(0).getAdminArea());
                Log.e("getcityName", addresses.get(0).getLocality());

                String country_id = addresses.get(0).getCountryName();
                String state_id = addresses.get(0).getAdminArea();
                String city_id = addresses.get(0).getLocality();

                tinyDB.putString(Constants.COUNTRYID_KEY, country_id);
                tinyDB.putString(Constants.STATEID_KEY, state_id);
                tinyDB.putString(Constants.CITYID_KEY, city_id);


                return addresses.get(0).getCountryName();
            }
            return null;
        } catch (IOException ignored) {
            //do something
        }
        return null;
    }


    @Override
    public void onBackPressed() {
        if (mIsFrom.equals("reports")) {
            Toast.makeText(Activity_LoginScreen.this, "Please sign in first!!", Toast.LENGTH_SHORT).show();
        } else if (mIsFrom.equals("purchase")) {
            Toast.makeText(Activity_LoginScreen.this, "Please sign in first!!", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(Activity_LoginScreen.this, Activity_Introfirst.class);
            startActivity(intent);
            overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
            finish();
        }
    }
}
