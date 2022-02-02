package drzio.insomnia.treatment.bedtime.yoga.sleep.Activity;

import static com.android.billingclient.api.BillingClient.SkuType.INAPP;
import static com.android.billingclient.api.BillingClient.SkuType.SUBS;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.Purchasedetails;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.EditText;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.billingclient.api.AcknowledgePurchaseParams;
import com.android.billingclient.api.AcknowledgePurchaseResponseListener;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.Purchasenotification;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.ApiInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.Pricedata;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.PurchaseDone;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.Register_Api;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.UpdateLocation;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import drzio.insomnia.treatment.bedtime.yoga.sleep.util.Security;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIClient;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


@SuppressLint("LongLogTag")
public class Activity_Purchase extends AppCompatActivity implements BillingProcessor.IBillingHandler, PurchasesUpdatedListener, GoogleApiClient.OnConnectionFailedListener, GoogleApiClient.ConnectionCallbacks, LocationListener {
    String LICENSE_KEY;
    private TinyDB tinydb;
    LinearLayout mPbtn1;
    LinearLayout mPbtn2;
    LinearLayout mPbtn3;
    LinearLayout mPbtn4;

    private TextView mTvdiscount2;
    ImageView mCheck1;
    ImageView mCheck2;
    ImageView mCheck3;
    int radioclick = 2;
    private TextView mTvoneprice, mTvtwoprice, mTvthreeprice, seventrialss, purchasetext, namepurchase;
    //static final String ITEM_SKU = "Acidity_lifetime";
    String coutry = "India";
    String mCurrencycode;
    private Location location;
    private Geocoder geocoder;
    private List<Address> addresses;
    private String tempprice;
    private boolean isBuyenabled;
    private BillingProcessor bp;
    private static final String LOG_TAG = "iabv3";
    // private CelluliteAPIInterface apiInterface;
    private String CurrencyCode = "";
    ImageView ivmenu;
    private String selectedSuperStar;
    TextView offdiscount1, offdiscount2;
    ApiInterface apiInterface;
    private RelativeLayout mBuybtn;
    View shine;
    private String token;
    private String BASEURL;
    String toCurrency;
    String Onemonth, Oneyear, Lifetime;
    private int REFRESH_RATE_IN_SECONDS = 3;
    private final Handler refreshHandler = new Handler();
    private final Runnable refreshRunnable = new RefreshRunnable();
    String month_value, year_vlaue, life_value;
    ImageView purchase_image;

    private BillingClient billingClient;
    private static ArrayList<String> subcribeItemIDs = new ArrayList<String>();


    private String TAG = "LoginActivity";
    private static final int REQUEST_ACCESS_FINE_LOCATION = 111;
    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInAccount account;
    private String Gusername;
    private String Gmail;
    private String Guserid;
    private String Gprofilepicurl;
    private String GFirstname;
    private String GLastname;
    private boolean isFromFb = false;
    private boolean isFromGoogle = false;
    private static final int SIGN_IN_CODE = 9001;
    private LocationRequest mLocationRequest;
    Location mLastLocation;
    String nickname1, first_name1 = "UYUYU", last_name1 = "YFYHTF", email1 = "ABC@gmail.com", height1 = "150", weight1 = "40", gender1 = "female",
            mobile_number1 = "", age1 = "25", user_type_id1 = "", paid_status1 = "",
            country_id1 = "india", state_id1 = "tfr", city_id1 = "rrer",
            fcm_token1 = "yhtgfyhtf", facebook1 = "frfrtrtt", google1 = "223333333",
            user_image1 = "", heightmes1, weightmes1, goal_string1;
    String screen, heightmes, weightmes;
    private String languages;
    private ImageView img_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window =/* ((Activity_Home)getActivity()).*/getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(
                    getResources().getColor(R.color.scolor2));
        }
        tinydb = new TinyDB(Activity_Purchase.this);

        languages = tinydb.getString(Constants.Language);
        Constants.languagechange(this, languages);

        setContentView(R.layout.activity_purchase);
        shine = (View) findViewById(R.id.shine);
        shineAnimation();


        try {
            LocationManager locationManager = (LocationManager) Activity_Purchase.this.getSystemService(LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(Activity_Purchase.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Activity_Purchase.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Activity_Purchase.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
            }
            assert locationManager != null;
            location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            geocoder = new Geocoder(Activity_Purchase.this);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isBuyenabled = bundle.getBoolean("proceed");
        }

        if (!BillingProcessor.isIabServiceAvailable(Activity_Purchase.this)) {
            showToast("In-app billing service is unavailable, please upgrade Android Market/Play to version >= 3.9.16");
        }

      /*  ImageView mBack = (ImageView) findViewById(R.id.ivmenu);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
*/
        purchase_image = findViewById(R.id.purchase_image);
        namepurchase = findViewById(R.id.purchase_text);
        img_back = findViewById(R.id.img_back);
        //purchasetext = (TextView) findViewById(R.id.purchasetext);
        //  seventrialss = (TextView) findViewById(R.id.seventrialss);
        offdiscount1 = (TextView) findViewById(R.id.offdiscount1);
        offdiscount2 = (TextView) findViewById(R.id.offdiscount2);
        coutry = tinydb.getString(Constants.COUNTRYID_KEY);
        BASEURL = tinydb.getString(Constants.NewBaseUrl);
        coutry = tinydb.getString(Constants.COUNTRYID_KEY);
        toCurrency = tinydb.getString(Constants.COUNTRY_CODEID);


        token = tinydb.getString(Constants.Authtoken);

        Log.e("toCurrency", toCurrency);

        String mtype = tinydb.getString(Constants.ReasonKey);
      /*  Onemonth = tinydb.getString(SUB_1MONTH);
        Oneyear = tinydb.getString(SUB_TRAIL1YEAR);
        Lifetime = tinydb.getString(PUR_ONETIME);
        Log.e("month_key1", Onemonth);
        Log.e("one_y_key1", Oneyear);
        Log.e("life_t_key1", Lifetime);

*/

        Onemonth = tinydb.getString(Constants.SKU_DELAROY_MONTHLY);
        Oneyear = tinydb.getString(Constants.SKU_DELAROY_SIXMONTH);
        Lifetime = tinydb.getString(Constants.ITEM_SKU1);
        Log.e("month_key1", Onemonth);
        Log.e("one_y_key1", Oneyear);
        Log.e("life_t_key1", Lifetime);
        month_value = tinydb.getString(Constants.Month_Value);
        year_vlaue = tinydb.getString(Constants.Year_Value);
        life_value = tinydb.getString(Constants.Life_Value);
        getPriceList();
        subcribeItemIDs.add(Onemonth);
        subcribeItemIDs.add(Oneyear);
        subcribeItemIDs.add(Lifetime);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
       /* try {

            mTvoneprice.setText(month_value);
            seventrialss.setText("Free 7 days trial,then " + year_vlaue + " year." + "cancel anytime during the trial");
            purchasetext.setText("Free 7 days trial,then " + year_vlaue + " year." + "cancel anytime during the trial");
            mTvthreeprice.setText(life_value);

        } catch (Exception e) {

        }*/







        /*if (mtype.isEmpty()){
            getPriceList();
        }*//*else {
            getDiscountPriceList(mtype);
        }*/
      /*  if (mtype.equals("1")) {
            offdiscount.setText("80% off");
            offdiscount1.setText("80% off");
            offdiscount2.setText("80% off");
        } else if (mtype.equals("2")) {
            offdiscount.setText("60% off");
            offdiscount1.setText("60% off");
            offdiscount2.setText("60% off");
        } else {
            offdiscount.setText("60% off");
            offdiscount1.setText("80% off");
            offdiscount2.setText("80% off");
        }*/


        String percent_offer = tinydb.getString(Constants.DISCOUNT_TXT);
        String percent_offer2 = tinydb.getString(Constants.DISCOUNT_TXT2);
        if (percent_offer.equals("null")) {
            offdiscount1.setVisibility(View.GONE);

            offdiscount1.setBackgroundColor(Color.TRANSPARENT);


        } else {
            offdiscount1.setText(percent_offer + " % off");

        }
        if (percent_offer2.isEmpty()) {
            offdiscount2.setVisibility(View.GONE);
            offdiscount2.setBackgroundColor(Color.TRANSPARENT);
        } else {
            offdiscount2.setText(percent_offer2 + " % off");
        }

        try {

            for (int i = 0; i < subcribeItemIDs.size(); i++) {
                if (billingClient.isReady()) {
                    initiatePurchase(subcribeItemIDs.get(i));
                }
                //else reconnect service
                else {
                    billingClient = BillingClient.newBuilder(this).enablePendingPurchases().setListener(this).build();
                    int finalI = i;
                    billingClient.startConnection(new BillingClientStateListener() {
                        @Override
                        public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                initiatePurchase(subcribeItemIDs.get(finalI));
                            } else {
                                Toast.makeText(getApplicationContext(), "Error " + billingResult.getDebugMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onBillingServiceDisconnected() {
                        }
                    });
                }
            }
        } catch (Exception e) {

        }
        try {
            if (subcribeItemIDs.size() != 0) {
                billingClient = BillingClient.newBuilder(this)
                        .enablePendingPurchases().setListener(this).build();
                billingClient.startConnection(new BillingClientStateListener() {
                    @Override
                    public void onBillingSetupFinished(@NonNull BillingResult billingResult) {

                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {


                            SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
                            params.setSkusList(subcribeItemIDs).setType(SUBS);
                            billingClient.querySkuDetailsAsync(params.build(),
                                    new SkuDetailsResponseListener() {
                                        @Override
                                        public void onSkuDetailsResponse(@NonNull BillingResult billingResult, @Nullable List<com.android.billingclient.api.SkuDetails> list) {
                                            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && list != null) {
                                                for (com.android.billingclient.api.SkuDetails skuDetails : list) {
                                                    String sku = skuDetails.getSku();
                                                    String price = skuDetails.getPrice();
                                                    Log.e("item price", price);
                                                    try {

                                                        if (Onemonth.equals(sku)) {
                                                            mTvoneprice.setText(price + "\n Per Month");
                                                            tinydb.putString(Constants.Month_Value, price);
                                                        } else if (Oneyear.equals(sku)) {
                                                            mTvtwoprice.setText(price + "/6 Months");
                                                            String prive_proper = price.substring(1);

                                                            Log.e("prive_proper", String.valueOf(price.indexOf(0)) + "dfd");


                                                            DecimalFormat precision = new DecimalFormat("0.00");
                                                            double price_val = Double.parseDouble((prive_proper));
                                                            double price_month = (price_val / 6);

                                                            //   Log.e("String.valueOf(price_month)",String.valueOf(price_month)+"dfd");
                                                            mTvdiscount2.setText(String.valueOf(precision.format(price_month)) + "\nPer Month");


                                                            // mTvdiscount2.setText(String.valueOf(price_month)+ "\nPer Month");

                                                            tinydb.putString(Constants.Year_Value, price);
                                                        }


                                                    } catch (Exception e) {

                                                    }
                                                }
                                            }
                                        }


                                    });


                            SkuDetailsParams.Builder params1 = SkuDetailsParams.newBuilder();
                            params1.setSkusList(subcribeItemIDs).setType(INAPP);
                            billingClient.querySkuDetailsAsync(params1.build(),
                                    new SkuDetailsResponseListener() {
                                        @Override
                                        public void onSkuDetailsResponse(@NonNull BillingResult billingResult, @Nullable List<com.android.billingclient.api.SkuDetails> list) {
                                            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && list != null) {
                                                for (com.android.billingclient.api.SkuDetails skuDetails : list) {
                                                    String sku = skuDetails.getSku();
                                                    String price = skuDetails.getPrice();
                                                    Log.e("item price", price);
                                                    try {


                                                        if (Lifetime.equals(sku)) {
                                                            mTvthreeprice.setText(price);
                                                            tinydb.putString(Constants.Life_Value, price);
                                                        }


                                                    } catch (Exception e) {

                                                    }
                                                }
                                            }
                                        }
                                    });
                        }
                    }

                    @Override
                    public void onBillingServiceDisconnected() {
                    }
                });
            }
        } catch (Exception e) {

        }
        mPbtn1 = (LinearLayout) findViewById(R.id.pbtn1);
        mPbtn2 = (LinearLayout) findViewById(R.id.pbtn2);
        mPbtn3 = (LinearLayout) findViewById(R.id.pbtn3);
        mCheck1 = (ImageView) findViewById(R.id.imgcheck1);
        mCheck2 = (ImageView) findViewById(R.id.imgcheck2);
        mCheck3 = (ImageView) findViewById(R.id.imgcheck3);
//        mCheck1 = (ImageView) findViewById(R.id.imgcheck1);
//        mCheck2 = (ImageView) findViewById(R.id.imgcheck2);
//        mCheck3 = (ImageView) findViewById(R.id.imgcheck3);
        mTvoneprice = (TextView) findViewById(R.id.tvoneprice);
        mTvtwoprice = (TextView) findViewById(R.id.tvtwoprice);
        mTvdiscount2 = (TextView) findViewById(R.id.tvdiscountprice);
        mTvthreeprice = (TextView) findViewById(R.id.tvthreeprice);
        TextView mTmptv1 = (TextView) findViewById(R.id.tmptxt1);
        TextView mTmptv2 = (TextView) findViewById(R.id.tmptxt2);
        TextView mTmptv3 = (TextView) findViewById(R.id.tmptxt3);

        TextView mTvmore = (TextView) findViewById(R.id.btnmore);
        mTvmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Purchase.this, Activity_webview.class);
                intent.putExtra("title", "Purchase Policy");
                intent.putExtra("link", Purchasedetails);
                startActivity(intent);
            }
        });
        try {
            if (ActivityCompat.checkSelfPermission(Activity_Purchase.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Activity_Purchase.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Activity_Purchase.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
            } else {

                Locale locale = new Locale("en");
                geocoder = new Geocoder(Activity_Purchase.this, locale);
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10);
                Address address = addresses.get(0);
                coutry = addresses.get(0).getCountryName();
                String state_id = addresses.get(0).getAdminArea();
                String city_id = addresses.get(0).getLocality();
                Log.e("coutry__", coutry);
                Boolean success = tinydb.getBoolean(Constants.Success_Country);
                if (!success) {
                    UpdateLocationApi(coutry, state_id, city_id);
                }


              /*  try {


                    TranslateAPI translateAPI = new TranslateAPI(
                            Language.AUTO_DETECT,
                            Language.ENGLISH, coutry
                    );

                    translateAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
                        @Override
                        public void onSuccess(String translatedText) {
                            Log.d("TAG", "onSuccess: " + translatedText);

                            tinydb.putString(Constants.COUNTRYID_KEY, translatedText);
                            Log.e("translatedText_contry", translatedText);
                        }

                        @Override
                        public void onFailure(String ErrorText) {
                            Log.d("TAG", "onFailure: " + ErrorText);
                        }
                    });
                    TranslateAPI translateAPI2 = new TranslateAPI(
                            Language.AUTO_DETECT,
                            Language.ENGLISH, state_id
                    );

                    translateAPI2.setTranslateListener(new TranslateAPI.TranslateListener() {
                        @Override
                        public void onSuccess(String translatedText) {
                            Log.d("TAG", "onSuccess: " + translatedText);

                            tinydb.putString(Constants.STATEID_KEY, translatedText);
                            Log.e("translatedText_state", translatedText); }

                        @Override
                        public void onFailure(String ErrorText) {
                            Log.d("TAG", "onFailure: " + ErrorText);
                        }
                    });
                    TranslateAPI translateAPI1 = new TranslateAPI(
                            Language.AUTO_DETECT,
                            Language.ENGLISH, city_id
                    );

                    translateAPI1.setTranslateListener(new TranslateAPI.TranslateListener() {
                        @Override
                        public void onSuccess(String translatedText) {
                            Log.d("TAG", "onSuccess: " + translatedText);

                            tinydb.putString(Constants.CITYID_KEY, translatedText);
                            Log.e("translatedText_city", translatedText);
                        }

                        @Override
                        public void onFailure(String ErrorText) {
                            Log.d("TAG", "onFailure: " + ErrorText);
                        }
                    });
                } catch (Exception e) {

                }*/
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        LICENSE_KEY = getString(R.string.base64key);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .addApi(LocationServices.API)
                .build();
        bp = new BillingProcessor(Activity_Purchase.this, LICENSE_KEY, Activity_Purchase.this);
        bp.initialize();

        mPbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tinydb.getBoolean(Constants.ISLOGIN_KEY)) {

                    mPbtn1.setBackgroundResource(R.drawable.purchase_gradbtn);
                    mCheck1.setImageResource(R.drawable.ic_premstar_selected);
                    mCheck1.setColorFilter(Color.WHITE);
                    mPbtn2.setBackgroundResource(R.drawable.purchase_unselectbtn);
                    mCheck2.setImageResource(R.drawable.ic_premcrown_deselect);
                    mCheck2.setColorFilter(getResources().getColor(R.color.piccolor));
                    mPbtn3.setBackgroundResource(R.drawable.purchase_unselectbtn);
                    mCheck3.setImageResource(R.drawable.ic_premdiamond_deselect);
                    mCheck3.setColorFilter(getResources().getColor(R.color.piccolor));
                    mTvoneprice.setTextColor(Color.WHITE);
                    mTvtwoprice.setTextColor(getResources().getColor(R.color.headercolor));
                    mTvdiscount2.setTextColor(getResources().getColor(R.color.headercolor));
                    mTvthreeprice.setTextColor(getResources().getColor(R.color.headercolor));
                    mTmptv1.setTextColor(Color.WHITE);
                    mTmptv2.setTextColor(getResources().getColor(R.color.headercolor));
                    mTmptv3.setTextColor(getResources().getColor(R.color.headercolor));

                    radioclick = 1;
                    String Onemonth1 = tinydb.getString(Constants.SKU_DELAROY_MONTHLY);
                    bp.subscribe(Activity_Purchase.this, Onemonth1);
                } else {
                   /* Intent intent = new Intent(Activity_Purchase.this, Activity_LoginScreen.class);
                    intent.putExtra("isFrom", "primium");
                    startActivity(intent);
                    finish();*/

                    boolean hasPermissionLocation = (ContextCompat.checkSelfPermission(getApplicationContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
                    if (!hasPermissionLocation) {
                        ActivityCompat.requestPermissions(Activity_Purchase.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                REQUEST_ACCESS_FINE_LOCATION);
                    }


                    signIn();
                }
            }
        });
        mPbtn2.setBackgroundResource(R.drawable.purchase_gradbtn);
        mCheck2.setImageResource(R.drawable.ic_premcrown_selected);
        mCheck2.setColorFilter(Color.WHITE);
        mTvtwoprice.setTextColor(Color.WHITE);
        mTvdiscount2.setTextColor(Color.WHITE);
        mTmptv2.setTextColor(Color.WHITE);
        mPbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tinydb.getBoolean(Constants.ISLOGIN_KEY)/* || isBuyenabled*/) {

                    mPbtn1.setBackgroundResource(R.drawable.purchase_unselectbtn);
                    mCheck1.setImageResource(R.drawable.ic_premstar_deselect);
                    mCheck1.setColorFilter(getResources().getColor(R.color.piccolor));
                    mPbtn2.setBackgroundResource(R.drawable.purchase_gradbtn);
                    mCheck2.setImageResource(R.drawable.ic_premcrown_selected);
                    mCheck2.setColorFilter(Color.WHITE);
                    mPbtn3.setBackgroundResource(R.drawable.purchase_unselectbtn);
                    mCheck3.setImageResource(R.drawable.ic_premdiamond_deselect);
                    mCheck3.setColorFilter(getResources().getColor(R.color.piccolor));
                    radioclick = 2;
                    mTvoneprice.setTextColor(getResources().getColor(R.color.headercolor));
                    mTvtwoprice.setTextColor(Color.WHITE);
                    mTvdiscount2.setTextColor(Color.WHITE);
                    mTvthreeprice.setTextColor(getResources().getColor(R.color.headercolor));

                    mTmptv1.setTextColor(getResources().getColor(R.color.headercolor));
                    mTmptv2.setTextColor(Color.WHITE);
                    mTmptv3.setTextColor(getResources().getColor(R.color.headercolor));
                    String Oneyear1 = tinydb.getString(Constants.SKU_DELAROY_SIXMONTH);
                    bp.subscribe(Activity_Purchase.this, Oneyear1);
                    mTmptv1.setTextColor(getResources().getColor(R.color.btitlecolor));
                    mTvoneprice.setTextColor(getResources().getColor(R.color.btitlecolor));
                } else {
                   /* Intent intent = new Intent(Activity_Purchase.this, Activity_LoginScreen.class);
                    intent.putExtra("isFrom", "primium");
                    startActivity(intent);
                    finish();*/

                    boolean hasPermissionLocation = (ContextCompat.checkSelfPermission(getApplicationContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
                    if (!hasPermissionLocation) {
                        ActivityCompat.requestPermissions(Activity_Purchase.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                REQUEST_ACCESS_FINE_LOCATION);
                    }


                    signIn();
                }
            }
        });

        mPbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tinydb.getBoolean(Constants.ISLOGIN_KEY) /* || isBuyenabled*/) {
                    mPbtn1.setBackgroundResource(R.drawable.purchase_unselectbtn);
                    mCheck1.setImageResource(R.drawable.ic_premstar_deselect);
                    mCheck1.setColorFilter(getResources().getColor(R.color.piccolor));
                    mPbtn2.setBackgroundResource(R.drawable.purchase_unselectbtn);
                    mCheck2.setImageResource(R.drawable.ic_premcrown_deselect);
                    mCheck2.setColorFilter(getResources().getColor(R.color.piccolor));
                    mPbtn3.setBackgroundResource(R.drawable.purchase_gradbtn);
                    mCheck3.setImageResource(R.drawable.ic_premdiamond_selcted);
                    mCheck3.setColorFilter(Color.WHITE);
                    radioclick = 3;
                    mTvoneprice.setTextColor(getResources().getColor(R.color.headercolor));
                    mTvtwoprice.setTextColor(getResources().getColor(R.color.headercolor));
                    mTvdiscount2.setTextColor(getResources().getColor(R.color.headercolor));
                    mTvthreeprice.setTextColor(Color.WHITE);
                    mTmptv1.setTextColor(getResources().getColor(R.color.headercolor));
                    mTmptv2.setTextColor(getResources().getColor(R.color.headercolor));
                    mTmptv3.setTextColor(Color.WHITE);
                    String Lifetime1 = tinydb.getString(Constants.ITEM_SKU1);
                    bp.purchase(Activity_Purchase.this, Lifetime1);
                    Log.e("Lifetime_key", Lifetime1);
                    mTmptv3.setTextColor(Color.WHITE);
                    mTvthreeprice.setTextColor(Color.WHITE);


                } else {
                   /* Intent intent = new Intent(Activity_Purchase.this, Activity_LoginScreen.class);
                    intent.putExtra("isFrom", "primium");
                    startActivity(intent);
                    finish();*/

                    boolean hasPermissionLocation = (ContextCompat.checkSelfPermission(getApplicationContext(),
                            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);
                    if (!hasPermissionLocation) {
                        ActivityCompat.requestPermissions(Activity_Purchase.this,
                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                REQUEST_ACCESS_FINE_LOCATION);
                    }


                    signIn();
                }
            }
        });








       /* mPbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tinydb.getBoolean(Constants.ISLOGIN_KEY) || tinydb.getBoolean(Constants.ISFacebookLOGIN_KEY)*//* || isBuyenabled*//*) {

                    mTmptv3.setTextColor(getResources().getColor(R.color.btitlecolor));
                    mTvthreeprice.setTextColor(getResources().getColor(R.color.btitlecolor));


                    mPbtn1.setBackgroundResource(R.drawable.purchase_gradbtn);
//                mCheck1.setImageResource(R.drawable.ic_premstar_selected);
//                mCheck1.setColorFilter(Color.WHITE);
                    mPbtn2.setBackgroundResource(R.drawable.purchase_unselectbtn);
//                mCheck2.setImageResource(R.drawable.ic_premcrown_deselect);
//                mCheck2.setColorFilter(getResources().getColor(R.color.piccolor));
                    mPbtn3.setBackgroundResource(R.drawable.purchase_unselectbtn);
                    mPbtn4.setBackgroundResource(R.drawable.purchase_unselectbtn);
//                mCheck3.setImageResource(R.drawable.ic_premdiamond_deselect);
//                mCheck3.setColorFilter(getResources().getColor(R.color.piccolor));
                    mTmptv1.setTextColor(Color.WHITE);
                    mTvoneprice.setTextColor(Color.WHITE);
                    mTmptv2.setTextColor(Color.BLACK);
                    mTvtwoprice.setTextColor(Color.BLACK);
                    mTmptv3.setTextColor(Color.BLACK);
                    mTvthreeprice.setTextColor(Color.BLACK);
                    tmptxt7.setTextColor(Color.BLACK);
//                mTvtwoprice.setTextColor(getResources().getColor(R.color.headercolor));
//                mTvdiscount2.setTextColor(getResources().getColor(R.color.headercolor));
//                mTvthreeprice.setTextColor(getResources().getColor(R.color.headercolor));
//                mTmptv1.setTextColor(Color.WHITE);
//                mTmptv2.setTextColor(getResources().getColor(R.color.headercolor));
//                mTmptv3.setTextColor(getResources().getColor(R.color.headercolor));
                    radioclick = 1;
                    //  String onemonth=tinydb.getString(SUB_1MONTH);
                    Log.e("onClick:  oneyear", Onemonth);
                    bp.subscribe(Activity_Purchase.this, Onemonth);
                }
            }
        });
        mPbtn2.setBackgroundResource(R.drawable.purchase_gradbtn);
//        mCheck2.setImageResource(R.drawable.ic_premcrown_selected);
//        mCheck2.setColorFilter(Color.WHITE);
        mTvtwoprice.setTextColor(Color.WHITE);
        mTvdiscount2.setTextColor(Color.WHITE);
        mTmptv2.setTextColor(Color.WHITE);
        tmptxt7.setTextColor(Color.WHITE);
        mPbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tinydb.getBoolean(Constants.ISLOGIN_KEY) || tinydb.getBoolean(Constants.ISFacebookLOGIN_KEY)*//* || isBuyenabled*//*) {
                    mTmptv2.setTextColor(Color.WHITE);
                    mTvtwoprice.setTextColor(Color.WHITE);
                    mTmptv3.setTextColor(getResources().getColor(R.color.btitlecolor));
                    mTvthreeprice.setTextColor(getResources().getColor(R.color.btitlecolor));


                    mPbtn1.setBackgroundResource(R.drawable.purchase_unselectbtn);
//                mCheck1.setImageResource(R.drawable.ic_premstar_deselect);
//                mCheck1.setColorFilter(getResources().getColor(R.color.piccolor));
                    mPbtn2.setBackgroundResource(R.drawable.purchase_gradbtn);
//                mCheck2.setImageResource(R.drawable.ic_premcrown_selected);
//                mCheck2.setColorFilter(Color.WHITE);
                    mPbtn3.setBackgroundResource(R.drawable.purchase_unselectbtn);
                    mPbtn4.setBackgroundResource(R.drawable.purchase_unselectbtn);
//                mCheck3.setImageResource(R.drawable.ic_premdiamond_deselect);
//                mCheck3.setColorFilter(getResources().getColor(R.color.piccolor));
                    radioclick = 2;
//                mTvoneprice.setTextColor(getResources().getColor(R.color.headercolor));
//                mTvtwoprice.setTextColor(Color.WHITE);
//                mTvdiscount2.setTextColor(Color.WHITE);
//                mTvthreeprice.setTextColor(getResources().getColor(R.color.headercolor));
//                mTmptv1.setTextColor(getResources().getColor(R.color.headercolor));
//                mTmptv2.setTextColor(Color.WHITE);
//                mTmptv3.setTextColor(getResources().getColor(R.color.headercolor));
                    //   String oneyear=tinydb.getString(SUB_TRAIL1YEAR);

                    bp.subscribe(Activity_Purchase.this, Oneyear);


                    Log.e("onClick:  oneyear", Oneyear);
                    mTmptv1.setTextColor(getResources().getColor(R.color.btitlecolor));
                    mTvoneprice.setTextColor(getResources().getColor(R.color.btitlecolor));
                }
            }
        });
        mPbtn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tinydb.getBoolean(Constants.ISLOGIN_KEY) || tinydb.getBoolean(Constants.ISFacebookLOGIN_KEY)*//* || isBuyenabled*//*) {

                    mPbtn1.setBackgroundResource(R.drawable.purchase_unselectbtn);
//                mCheck1.setImageResource(R.drawable.ic_premstar_deselect);
//                mCheck1.setColorFilter(getResources().getColor(R.color.piccolor));
                    mPbtn4.setBackgroundResource(R.drawable.purchase_gradbtn);
//                mCheck2.setImageResource(R.drawable.ic_premcrown_selected);
//                mCheck2.setColorFilter(Color.WHITE);
                    mPbtn3.setBackgroundResource(R.drawable.purchase_unselectbtn);
                    mPbtn4.setBackgroundResource(R.drawable.purchase_unselectbtn);
//                mCheck3.setImageResource(R.drawable.ic_premdiamond_deselect);
//                mCheck3.setColorFilter(getResources().getColor(R.color.piccolor));
                    radioclick = 2;
//                mTvoneprice.setTextColor(getResources().getColor(R.color.headercolor));
//                mTvtwoprice.setTextColor(Color.WHITE);
//                mTvdiscount2.setTextColor(Color.WHITE);
//                mTvthreeprice.setTextColor(getResources().getColor(R.color.headercolor));
//                mTmptv1.setTextColor(getResources().getColor(R.color.headercolor));
//                mTmptv2.setTextColor(Color.WHITE);
//                mTmptv3.setTextColor(getResources().getColor(R.color.headercolor));
                    //   String oneyear=tinydb.getString(SUB_TRAIL1YEAR);
                    Log.e("onClick:  oneyear", Oneyear);
                    bp.subscribe(Activity_Purchase.this, Oneyear);
                    //  bp.subscribe(Activity_Purchase.this, SUB_TRAIL1YEAR);
                }
            }
        });
        mPbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tinydb.getBoolean(Constants.ISLOGIN_KEY) || tinydb.getBoolean(Constants.ISFacebookLOGIN_KEY)*//* || isBuyenabled*//*) {

                    mTmptv1.setTextColor(getResources().getColor(R.color.btitlecolor));
                    mTvoneprice.setTextColor(getResources().getColor(R.color.btitlecolor));
                    mPbtn1.setBackgroundResource(R.drawable.purchase_unselectbtn);
//                mCheck1.setImageResource(R.drawable.ic_premstar_deselect);
//                mCheck1.setColorFilter(getResources().getColor(R.color.piccolor));
                    mPbtn2.setBackgroundResource(R.drawable.purchase_unselectbtn);
//                mCheck2.setImageResource(R.drawable.ic_premcrown_deselect);
//                mCheck2.setColorFilter(getResources().getColor(R.color.piccolor));
                    mPbtn3.setBackgroundResource(R.drawable.purchase_gradbtn);
                    mPbtn4.setBackgroundResource(R.drawable.purchase_unselectbtn);
//                mCheck3.setImageResource(R.drawable.ic_premdiamond_selcted);
//                mCheck3.setColorFilter(Color.WHITE);
                    radioclick = 3;
//                mTvoneprice.setTextColor(getResources().getColor(R.color.headercolor));
//                mTvtwoprice.setTextColor(getResources().getColor(R.color.headercolor));
//                mTvdiscount2.setTextColor(getResources().getColor(R.color.headercolor));
//                mTvthreeprice.setTextColor(Color.WHITE);
//                mTmptv1.setTextColor(getResources().getColor(R.color.headercolor));
//                mTmptv2.setTextColor(getResources().getColor(R.color.headercolor));
//                mTmptv3.setTextColor(Color.WHITE);
                    //     String lifetime=tinydb.getString(PUR_ONETIME);

                    bp.purchase(Activity_Purchase.this, Lifetime);
                    //   bp.purchase(Activity_Purchase.this, PUR_ONETIME);
                    mTmptv3.setTextColor(Color.WHITE);
                    mTvthreeprice.setTextColor(Color.WHITE);
                    Log.e("onClick:  oneyear", Lifetime);
                    tmptxt7.setTextColor(Color.BLACK);
                }
            }
        });*/
        mBuybtn = (RelativeLayout) findViewById(R.id.buybtn);

        mBuybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyClick(view);
            }
        });
    }


    private void UpdateLocationApi(String country_id, String state_id, String city_id) {
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
            String BaseUrl23 = tinydb.getString(Constants.NewBaseUrl);

            Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseUrl23)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
            //   ApiInterface   apiInterface = retrofit.create(ApiInterface.class);
            ApiInterface apiInterface = (ApiInterface) BackpainAPIClient.getCelluliteClient1().create(ApiInterface.class);
            try {
                JSONObject paramObject = new JSONObject();
                paramObject.put("country", country_id);
                paramObject.put("state", state_id);
                paramObject.put("city", city_id);
                Call<UpdateLocation> userCall = apiInterface.updatelocation(paramObject.toString(), "Bearer " + token);
                userCall.enqueue(new Callback<UpdateLocation>() {
                    @Override
                    public void onResponse(Call<UpdateLocation> call, Response<UpdateLocation> response) {
                        try {


                            UpdateLocation badge = response.body();
                            boolean ok = badge.getStatus();
                            Log.e("getStatusgetStatus", String.valueOf(ok));
                            Log.e("getCountrygetCountry", String.valueOf(badge.getCountry()));
                            tinydb.putBoolean(Constants.Success_Country, true);

                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onFailure(Call<UpdateLocation> call, Throwable t) {
                        Log.e("response1", String.valueOf(t));
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {

        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, SIGN_IN_CODE);

    }


    private void initiatePurchase(final String PRODUCT_ID) {
        List<String> skuList = new ArrayList<>();
        skuList.add(PRODUCT_ID);
        SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
        params.setSkusList(skuList).setType(SUBS);


        BillingResult billingResult = billingClient.isFeatureSupported(BillingClient.FeatureType.SUBSCRIPTIONS);

        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
            billingClient.querySkuDetailsAsync(params.build(),
                    new SkuDetailsResponseListener() {
                        @Override
                        public void onSkuDetailsResponse(@NonNull BillingResult billingResult,
                                                         List<com.android.billingclient.api.SkuDetails> skuDetailsList) {
                            if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                if (skuDetailsList != null && skuDetailsList.size() > 0) {
                                    for (com.android.billingclient.api.SkuDetails skuDetails : skuDetailsList) {
                                        String sku = skuDetails.getSku();
                                        String price = skuDetails.getPrice();

                                        Toast.makeText(getApplicationContext(), "price" + price + "Found", Toast.LENGTH_SHORT).show();


                                    }
                                    BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                                            .setSkuDetails(skuDetailsList.get(0))
                                            .build();
                                    billingClient.launchBillingFlow(Activity_Purchase.this, flowParams);

                                } else {
                                    //try to add item/product id "s1" "s2" "s3" inside subscription in google play console
                                    Toast.makeText(getApplicationContext(), "Subscribe Item " + PRODUCT_ID + " not Found", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(),
                                        " Error " + billingResult.getDebugMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(),
                    "Sorry Subscription not Supported. Please Update Play Store", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onPurchasesUpdated(BillingResult billingResult, @Nullable List<com.android.billingclient.api.Purchase> purchases) {
        //if item newly purchased
        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && purchases != null) {
            handlePurchases(purchases);
        }
        //if item already purchased then check and reflect changes
        else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.ITEM_ALREADY_OWNED) {
            com.android.billingclient.api.Purchase.PurchasesResult queryAlreadyPurchasesResult = billingClient.queryPurchases(SUBS);
            List<com.android.billingclient.api.Purchase> alreadyPurchases = queryAlreadyPurchasesResult.getPurchasesList();
            if (alreadyPurchases != null) {
                handlePurchases(alreadyPurchases);
            }
        }
        //if purchase cancelled
        else if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.USER_CANCELED) {
            Toast.makeText(getApplicationContext(), "Purchase Canceled", Toast.LENGTH_SHORT).show();
        }
        // Handle any other error msgs
        else {
            Toast.makeText(getApplicationContext(), "Error " + billingResult.getDebugMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    void handlePurchases(List<com.android.billingclient.api.Purchase> purchases) {
        for (com.android.billingclient.api.Purchase purchase : purchases) {

            final int index = subcribeItemIDs.indexOf(purchase.getSku());
            //purchase found
            if (index > -1) {

                //if item is purchased
                if (purchase.getPurchaseState() == com.android.billingclient.api.Purchase.PurchaseState.PURCHASED) {
                    if (!verifyValidSignature(purchase.getOriginalJson(), purchase.getSignature())) {
                        // Invalid purchase
                        // show error to user
                        Toast.makeText(getApplicationContext(), "Error : Invalid Purchase", Toast.LENGTH_SHORT).show();
                        continue;//skip current iteration only because other items in purchase list must be checked if present
                    }
                    // else purchase is valid
                    //if item is purchased/subscribed and not Acknowledged
                    if (!purchase.isAcknowledged()) {
                        AcknowledgePurchaseParams acknowledgePurchaseParams =
                                AcknowledgePurchaseParams.newBuilder()
                                        .setPurchaseToken(purchase.getPurchaseToken())
                                        .build();

                        billingClient.acknowledgePurchase(acknowledgePurchaseParams,
                                new AcknowledgePurchaseResponseListener() {
                                    @Override
                                    public void onAcknowledgePurchaseResponse(BillingResult billingResult) {
                                        if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                                            //if purchase is acknowledged
                                            //then saved value in preference

                                        }
                                    }
                                });

                    }
                    //else item is purchased and also acknowledged
                    else {
                        // Grant entitlement to the user on item purchase

                    }
                }
                //if purchase is pending
                else if (purchase.getPurchaseState() == com.android.billingclient.api.Purchase.PurchaseState.PENDING) {

                }
                //if purchase is refunded or unknown
                else if (purchase.getPurchaseState() == com.android.billingclient.api.Purchase.PurchaseState.UNSPECIFIED_STATE) {
                    //mark purchase false in case of UNSPECIFIED_STATE

                }
            }

        }

    }


    private boolean verifyValidSignature(String signedData, String signature) {


        //String base64Key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApTnlnDh15DKEhM/Usg5yZ2ejS4pwmdwEklDaQ28paT/C1YKp3fkzahq27VE2Yj3RHc9IIDxyNyn1OrZi03zAUPI/ULINXaVpwPLdJ5dQEs6h5tFWbJvaUOfE63s9ihNo4QhPAbWw+bnEkelByAf1MYe1hx3N8AGt8VgZof9/HK6Mk5/K2rQZryjoBoMiZk4A2vm3ZsBHqEU1j7kKVwW0t0Sw9P8GIOqeqlxqfm849HmIpEAhhyVlC6EAK0PDasTVarz1rmc6I3gIrgt8pAAYBPO/QtqfPAfNv9lNUoKvMEug2zFO6fWVymt/tjpZmXcqY8Bp63AMO7Khtv6GsRURTQIDAQAB";
        return Security.verifyPurchase(LICENSE_KEY, signedData, signature);
    }


    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void onProductPurchased(@NotNull String productId, TransactionDetails details) {
        Log.d(LOG_TAG, productId);
        NoAds();
        try {
            Log.d(LOG_TAG, "Description : " + details.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        tinydb.putBoolean(Constants.PREMIUN_KEY, true);
        Intent i = new Intent(Activity_Purchase.this, Appstart_Activity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        ((Activity) Activity_Purchase.this).finish();
    }

    @Override
    public void onPurchaseHistoryRestored() {
        showToast("onPurchaseHistoryRestored");
        for (String sku : bp.listOwnedProducts())
            Log.d(LOG_TAG, "Owned Managed Product: " + sku);
        for (String sku : bp.listOwnedSubscriptions())
            Log.d(LOG_TAG, "Owned Subscription: " + sku);
    }

    @Override
    public void onBillingError(int errorCode, Throwable error) {
        Log.d(LOG_TAG, "onBillingError: " + Integer.toString(errorCode));
        /*if (!tinydb.getBoolean(Constants.PurchaseDialog)) {
            //DialogPurchaseFeedback();
        }*/
    }

    @Override
    public void onBillingInitialized() {
        Log.d(LOG_TAG, "onBillingInitialized: Sucess");
        tinydb.putBoolean(Constants.PREMIUN_KEY, true);
        bp.loadOwnedPurchasesFromGoogle();

        if (bp.isPurchased(Lifetime)) {
            tinydb.putBoolean(Constants.PREMIUN_KEY, true);
            NoAds();
            String lifetime_Price = tinydb.getString(Constants.Life_Value);
            String contry_name = tinydb.getString(Constants.COUNTRYID_KEY);
            String username = tinydb.getString(Constants.USERFIRSTNAME_KEY);
            callPurchaseDoneApi(Lifetime, "lifetime", lifetime_Price, contry_name, username);

            Log.d(LOG_TAG, "Owned Subscription: " + "onetime");
        } else if (bp.isSubscribed(Onemonth)) {
            tinydb.putBoolean(Constants.PREMIUN_KEY, true);
            NoAds();
            String lifetime_Price = tinydb.getString(Constants.Month_Value);
            String contry_name = tinydb.getString(Constants.COUNTRYID_KEY);
            String username = tinydb.getString(Constants.USERFIRSTNAME_KEY);
            callPurchaseDoneApi(Onemonth, "Onemonth", lifetime_Price, contry_name, username);
            Log.d(LOG_TAG, "Owned Subscription: " + "1 Month");


            if (bp.loadOwnedPurchasesFromGoogle()) {
                Log.d(LOG_TAG, "update2: " + bp.loadOwnedPurchasesFromGoogle());
            }
        } else if (bp.isSubscribed(Oneyear)) {
            tinydb.putBoolean(Constants.PREMIUN_KEY, true);
            NoAds();
            String lifetime_Price = tinydb.getString(Constants.Year_Value);
            String contry_name = tinydb.getString(Constants.COUNTRYID_KEY);
            String username = tinydb.getString(Constants.USERFIRSTNAME_KEY);
            callPurchaseDoneApi(Oneyear, "1year", lifetime_Price, contry_name, username);
            Log.d(LOG_TAG, "Owned Subscription: " + "6 Month");

            if (bp.loadOwnedPurchasesFromGoogle()) {
                Log.d(LOG_TAG, "Owned Subscription: " + "6 Month Subscriptions updated.");
            }
        } else {
            tinydb.putBoolean(Constants.PREMIUN_KEY, false);
            bp.consumePurchase(Lifetime);
            Log.d(LOG_TAG, "Owned Subscription: " + "No Subscripytions");
        }

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
            apiInterface = (ApiInterface) BackpainAPIClient.getCelluliteClient1().create(ApiInterface.class);
            //   ApiInterface apiInterface = retrofit.create(ApiInterface.class);

            Log.e("pricetoken", token);
            Call<Pricedata> userCall = apiInterface.getprice_new("Bearer " + token);
            userCall.enqueue(new Callback<Pricedata>() {
                @Override
                public void onResponse(Call<Pricedata> call, Response<Pricedata> response) {
                    try {


                        Log.e("priceresponse", String.valueOf(response.body()));
                        Pricedata loginData = response.body();
                        String symbol = loginData.getCurrSymbol();

                        String currency_code = loginData.getCurrencyCode();
                        Log.e("currency_code", currency_code);


                        String month_key = loginData.getMonthKey();
                        String one_y_key = loginData.getOneYKey();
                        String life_t_key = loginData.getLifeTKey();
                        Log.e("month_key", month_key);
                        Log.e("one_y_key", one_y_key);
                        Log.e("life_t_key", life_t_key);

                        tinydb.putString(Constants.SKU_DELAROY_MONTHLY, month_key);
                        tinydb.putString(Constants.SKU_DELAROY_SIXMONTH, one_y_key);
                        tinydb.putString(Constants.ITEM_SKU1, life_t_key);


                        String image = loginData.getImage();
                        Log.e("image_premium", image);
                        String event_name = loginData.getEventName();
                        namepurchase.setText(event_name);
                        Log.e("event_name", image);
                        RequestOptions requestOptions = new RequestOptions()
                                .diskCacheStrategy(DiskCacheStrategy.ALL);
                        String imageurl = tinydb.getString(Constants.Backimageurl);
                        Glide.with(getApplicationContext()).load(imageurl + "price/" + image)
                                .apply(requestOptions).into(purchase_image);
                       /* RequestOptions requestOptions = new RequestOptions()
                                .diskCacheStrategy(DiskCacheStrategy.ALL);
                        Glide.with(FitnessApplication.getInstance()).load("https://femaleworkout-node.s3.amazonaws.com/price"+loginData.getImage())
                                .apply(requestOptions).into(purchase_image);
*/
                        String month_price = String.valueOf(loginData.getMonthPrice());
                        String one_y_price = String.valueOf(loginData.getOneYPrice());
                        String life_t_price = String.valueOf(loginData.getLifeTPrice());

                        Log.e("month_price", month_price);
                        Log.e("one_y_price", one_y_price);
                        Log.e("life_t_price", life_t_price);

                        double month_value = Double.parseDouble(String.valueOf(month_price));
                        double one_value = Double.parseDouble(String.valueOf(one_y_price));
                        double life_value = Double.parseDouble(String.valueOf(life_t_price));


                      /*  CurrencyConverter.calculate(month_value, currency_code, toCurrency, new CurrencyConverter.Callback() {
                            @Override
                            public void onValueCalculated(Double value, Exception e) {
                                if (e != null) {
                                    Log.e("month_value", CurrencyConverter.formatCurrencyValue(toCurrency, value));

                                } else {
                                    //       mTvoneprice.setText(CurrencyConverter.formatCurrencyValue(toCurrency, value));
                                    // tinydb.putString(Constants.Month_Value, CurrencyConverter.formatCurrencyValue(toCurrency, value));
                                    Log.e("month_value", CurrencyConverter.formatCurrencyValue(toCurrency, value));
                                }
                            }
                        });
                        CurrencyConverter.calculate(one_value, currency_code, toCurrency, new CurrencyConverter.Callback() {
                            @Override
                            public void onValueCalculated(Double value, Exception e) {
                                if (e != null) {
                                    Log.e("one_value", CurrencyConverter.formatCurrencyValue(toCurrency, value));

                                } else {
                                    tinydb.putString(Constants.Year_Value, CurrencyConverter.formatCurrencyValue(toCurrency, value));
                                    String sevenstring = CurrencyConverter.formatCurrencyValue(toCurrency, value);
                                    //   seventrialss.setText("Free 7 days trial,then "+ sevenstring+" year." +"cancel anytime during the trial");
                                    // purchasetext.setText("Free 7 days trial,then "+ sevenstring+" year." +"cancel anytime during the trial");
                                    Log.e("one_value", CurrencyConverter.formatCurrencyValue(toCurrency, value));
                                }
                            }
                        });

                        CurrencyConverter.calculate(life_value, currency_code, toCurrency, new CurrencyConverter.Callback() {
                            @Override
                            public void onValueCalculated(Double value, Exception e) {
                                if (e != null) {
                                    Log.e("life_value", CurrencyConverter.formatCurrencyValue(toCurrency, value));

                                } else {
                                    //   tinydb.putString(Constants.Life_Value, CurrencyConverter.formatCurrencyValue(toCurrency, value));
                                    //   mTvthreeprice.setText(CurrencyConverter.formatCurrencyValue(toCurrency, value));
                                    Log.e("life_value", CurrencyConverter.formatCurrencyValue(toCurrency, value));
                                }
                            }
                        });*/


                   /*     JSONMonthFetch jsonMonthFetch = new JSONMonthFetch();
                        jsonMonthFetch.execute(month_price);
                        JSONYEARFetch jsonyearFetch  = new JSONYEARFetch();
                        jsonyearFetch.execute(one_y_price);
                        JSONLIFEFetch jsonlifeFetch = new JSONLIFEFetch();
                        jsonlifeFetch.execute(life_t_price);*/


                    } catch (Exception e) {

                    }
                    //Pricedata. datalists = loginData.result;
                    //  Log.e("id", String.valueOf(Pricedata.id));

                }

                @Override
                public void onFailure(Call<Pricedata> call, Throwable t) {
                    Log.e("response1", String.valueOf(t.getMessage()));

                    refreshHandler.removeCallbacks(refreshRunnable);
                    refreshHandler.postDelayed(refreshRunnable, REFRESH_RATE_IN_SECONDS * 1000);
                }
            });
        } catch (Exception e) {

        }
    }

    private class RefreshRunnable implements Runnable {
        @Override
        public void run() {
         //   getPriceList();


        }
    }

    private void callPurchaseDoneApi(String planname, String month_d, String price, String contry_name, String username) {
        try {

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
            //    apiInterface = (ApiInterface) CelluliteAPIClient.getCelluliteClient1().create(ApiInterface.class);
            ApiInterface apiInterface = (ApiInterface) BackpainAPIClient.getCelluliteClient1().create(ApiInterface.class);
            //  ApiInterface   apiInterface = retrofit.create(ApiInterface.class);
            try {
                String token = tinydb.getString(Constants.Authtoken);
                String userid = tinydb.getString(Constants.USERID);
                JSONObject paramObject = new JSONObject();
                /*   paramObject.put("f_name", username);*/
                paramObject.put("app_number", 22);
                paramObject.put("user_id", userid);
                paramObject.put("plan_n", planname /*+ "_FemaleFitness"*/);
                paramObject.put("price", price);
                paramObject.put("country", contry_name);
                paramObject.put("month_d", month_d);
                Call<PurchaseDone> userCall = apiInterface.getpurchasedone(paramObject.toString(), "Bearer " + token);

                userCall.enqueue(new Callback<PurchaseDone>() {
                    @Override
                    public void onResponse(Call<PurchaseDone> call, Response<PurchaseDone> response) {
                        try {


                            // Log.e("response", String.valueOf(response.body()));
                            PurchaseDone loginData = response.body();
                            //Log.e("response", String.valueOf(response.code()));
                            //     boolean price = loginData.getStatus();
                            //   Log.e("price", String.valueOf(price));
                        } catch (Exception e) {

                        }


                    }

                    @Override
                    public void onFailure(Call<PurchaseDone> call, Throwable t) {
                        //     Log.e("response1", String.valueOf(t));
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {

        }
    }

   /* private void getPriceList1() {
        try {

            this.apiInterface = (CelluliteAPIInterface) Objects.requireNonNull(CelluliteAPIClient.getLocalClient()).create(CelluliteAPIInterface.class);

            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("country_name", "india")
                    .build();
            this.apiInterface.getpricelist(requestBody).enqueue(new Callback<PriceList>() {
                @Override
                public void onResponse(@NotNull Call<PriceList> call, @NotNull Response<PriceList> response) {
                    PriceList bannerData = (PriceList) response.body();
                    assert bannerData != null;
                    ArrayList<PriceList.Datalist> dataist = bannerData.dataist;
                    Log.e("datalistsdataist", String.valueOf(dataist));
                    Log.e("getPrice", String.valueOf(dataist.get(0).getMonthPrice()));
                    String symbol = dataist.get(0).getSymbole();
                    String lifetime = dataist.get(0).getLifetimePrice();
                    String price = dataist.get(0).getMonthPrice();
                    mTvthreeprice.setText(symbol+" " + lifetime);
                    mTvoneprice.setText(symbol +" "+ price);
                    //    String price=bannerData.

                    String sevenstring=symbol+dataist.get(0).getSixMonthPrice();
                    seventrialss.setText("Free 7 days trial,then "+ sevenstring+"/year." +" \n cancle anytime during the trial");
                    tinydb.putString(Constants.sevendayTrial,sevenstring);
                    tinydb.putString(Constants.Lifetime,lifetime);
                    tinydb.putString(Constants.OneMonthtime,price);
                    //tinydb.putString(Constants.time,lifetime);
                }

                @Override
                public void onFailure(@NotNull Call<PriceList> call, @NotNull Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        String LICENSE_KEY = Activity_Purchase.this.getString(R.string.base64key);
////        bp = new BillingProcessor(Activity_Purchase.this, LICENSE_KEY, this);
////        bp.initialize();
        if (!bp.handleActivityResult(requestCode, resultCode, data))
            super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SIGN_IN_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);

        }
        if (requestCode == 1000) {

            switch (requestCode) {
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
            //   Log.e("getDisplayLanguage", langauge);

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
            //  updatedata(Gusername, Gprofilepicurl);

        } else {
            updateUI(false);
        }

    }


    private void updateUI(boolean signedIn) {
        if (signedIn) {
            tinydb.putBoolean(Constants.ISLOGIN_KEY, true);


        } else {
            tinydb.remove(Constants.USERID_KEY);
            tinydb.remove(Constants.USEREMAIL_KEY);
            tinydb.remove(Constants.USERFIRSTNAME_KEY);
            tinydb.remove(Constants.USERLASTNAME_KEY);
            tinydb.putBoolean(Constants.ISLOGIN_KEY, false);


        }
    }

  /*  public void updatedata(String mProfname, String imgurl) {
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

    }*/
    /**/

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
                            status.startResolutionForResult(Activity_Purchase.this, 1000);
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
                getCountryName(Activity_Purchase.this, mLastLocation.getLatitude(), mLastLocation.getLongitude());
            } else {
                /*if there is no last known location. Which means the device has no data for the loction currently.
                 * So we will get the current location.
                 * For this we'll implement Location Listener and override onLocationChanged*/
                Log.i("Current Location", "No data for location found");

                if (!mGoogleApiClient.isConnected())
                    mGoogleApiClient.connect();

                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, Activity_Purchase.this);

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
                //    Log.e("getCountryName", addresses.get(0).getCountryName());
                //     Log.e("getstateName", addresses.get(0).getAdminArea());
                //     Log.e("getcityName", addresses.get(0).getLocality());

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

    @Override
    protected void onStop() {

        mGoogleApiClient.disconnect();
        super.onStop();
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
            String androidId = 37 + Settings.Secure.getString(getContentResolver(),
                    Settings.Secure.ANDROID_ID) + 37;
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
            paramObject.put("app_number", String.valueOf(37));
            paramObject.put("user_type", getusertype);
            this.apiInterface = (ApiInterface) BackpainAPIClient.getCelluliteClient1().create(ApiInterface.class);

            Call<Register_Api> userCall = apiInterface.getUser(paramObject.toString());
            userCall.enqueue(new Callback<Register_Api>() {
                @Override
                public void onResponse(Call<Register_Api> call, Response<Register_Api> response) {
                    try {


                        Register_Api dietCateData = (Register_Api) response.body();

                        Register_Api.Docs docs = dietCateData.getDocs();
                        //       Log.e("token", String.valueOf(dietCateData.token));
                        tinydb.putString(Constants.Authtoken, dietCateData.token);

                        String userid = docs.getId();
                        tinydb.putString(Constants.USERID_KEYs, userid);


                    } catch (Exception e) {
                    }
                }

                @Override
                public void onFailure(Call<Register_Api> call, Throwable t) {
                    //   Toast.makeText(Activity_Purchase.this, t.toString(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
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
    }

  /*  public void callRegisterApi(String first_name, String last_name,
                                String email, String mobile_number,
                                String gender, String age, String height,
                                String weight, String user_type_id, String paid_status,
                                String country_id, String state_id, String city_id,
                                String fcm_token, String facebook, String google, String user_image, String goal) {
        try {
            Log.e("country_id1", country_id);
            Log.e("state_id1", state_id);
            Log.e("city_id1", city_id);

            String token = tinydb.getString(Constants.Authtoken);
            String BaseUrl23 = tinydb.getString(Constants.NewFemaleFitness);
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BaseUrl23)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiInterface apiInterface = (ApiInterface) CelluliteAPIClient.getCelluliteClient1().create(ApiInterface.class);

            //  apiInterface1 = (ApiInterface) CelluliteAPIClient.getCelluliteClient1().create(ApiInterface.class);
            //   ApiInterface   apiInterface = retrofit.create(ApiInterface.class);
            try {
                JSONObject paramObject = new JSONObject();
                String androidId = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
                paramObject.put("device_id", androidId);
                paramObject.put("first_name", first_name);
                paramObject.put("last_name", last_name);
                paramObject.put("email", email);
                paramObject.put("mobile_number", mobile_number);
                paramObject.put("gender", gender);
                paramObject.put("age", age);
                paramObject.put("height", height);
                paramObject.put("heightP", heightmes);
                paramObject.put("weight", weight);
                paramObject.put("weightP", weightmes);
                paramObject.put("user_type_id", user_type_id);
                paramObject.put("paid_status", paid_status);
                paramObject.put("country", country_id);
                paramObject.put("state", state_id);
                paramObject.put("city", city_id);
                paramObject.put("fcm_token", fcm_token);
                paramObject.put("facebook", facebook);
                paramObject.put("google", google);
                paramObject.put("user_image", user_image);
                paramObject.put("goal", goal);
                paramObject.put("langauge", languages);
                paramObject.put("app_number", 1);
          *//*  paramObject.put("device_id", androidId);
            paramObject.put("nickname", nickname);
            paramObject.put("mobile_number", mobile_number);
            paramObject.put("gender", gender);
            paramObject.put("age", age);
            paramObject.put("height", height);
            paramObject.put("weight", weight);
            paramObject.put("user_type_id", user_type_id);
            paramObject.put("paid_status", paid_status);
            paramObject.put("country", country_id);
            paramObject.put("state", state_id);
            paramObject.put("city", city_id);
            paramObject.put("fcm_token", fcm_token);
            paramObject.put("facebook", facebook);
            paramObject.put("google", google);
            paramObject.put("user_image", user_image);*//*
                Call<Usertoken> userCall = apiInterface.getUser(paramObject.toString(), "Bearer " + token);
                userCall.enqueue(new Callback<Usertoken>() {
                    @Override
                    public void onResponse(Call<Usertoken> call, Response<Usertoken> response) {
                        try {


                            Usertoken dietCateData = (Usertoken) response.body();
                            //     Log.e("sendtokenstatus", String.valueOf(dietCateData));
                            //  Log.e("error",dietCateData.error);
                            //     Log.e("token", dietCateData.token);
                            Usertoken.Docs docs = dietCateData.getDocs();
                            String getCountry = docs.getfName();
                            //   Log.e("getCountry", String.valueOf(getCountry));
                            tinydb.putString(Constants.Authtoken, dietCateData.token);
                            int coins = docs.getCoin();
                            tinydb.putInt(Constants.APPCOINS_KEY, coins);
                            String userid = docs.getId();
                            //   Log.e("userid", String.valueOf(userid));
                            tinydb.putString(Constants.USERID, userid);

//                    Usertoken.Docs goal = (Usertoken.Docs) docs.getGoal();
                            List<String> strings = docs.getGoal();
                            for (int i = 0; i < strings.size(); i++) {
                                String s = strings.get(i);
                                //     Log.e("goals", String.valueOf(s));
                                Log.e("stringsstrings", String.valueOf(strings));
                            }
                            String fname = docs.getGoogle();
                            Log.e("getCountry", String.valueOf(fname));
                            Log.e("stringsstrings", String.valueOf(userid));
                            callBlogApi(dietCateData.token);
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<Usertoken> call, Throwable t) {

                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {

        }
    }
    @SuppressLint("WrongConstant")
    public void callBlogApi(String tokens) {
        try {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(loggingInterceptor);
            OkHttpClient client = httpClient.build();
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            String BaseUrl23 = tinydb.getString(Constants.NewFemaleFitness);

            Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseUrl23)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
            ApiInterface apiInterface = retrofit.create(ApiInterface.class);
            //     ApiInterface apiInterface = (ApiInterface) CelluliteAPIClient.getCelluliteClient1().create(ApiInterface.class);
            //String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiJhYmMxMjM0IiwiaWF0IjoxNjIzNzU5NDIxfQ.yKkrufRKbPX8yW5ei8a31VPfIKif8cqWHLwmzbHGDv0";
            Call<Bloglist> call = apiInterface.getBlogs("Bearer " + tokens);
            call.enqueue(new Callback<Bloglist>() {
                @Override
                public void onResponse(Call<Bloglist> call, Response<Bloglist> response) {
                    try {
                        Log.e("dfggf111111111", response.code() + "");
                        Bloglist blogData = (Bloglist) response.body();
                        ArrayList<Bloglist.Blog> datalists = (ArrayList<Bloglist.Blog>) blogData.blogs;
                        int array = datalists.size();
                        if (datalists.isEmpty()) {
                            Log.e("isEmpty", "isEmpty");
                            tinydb.putBoolean(Constants.isBlogAddedFirst, false);
                        } else {
                            Log.e("isEmptynot", "isEmptynot");
                            tinydb.putBoolean(Constants.isBlogAddedFirst, true);
                        }
                    } catch (Exception e) {
                    }
                }

                @Override
                public void onFailure(Call<Bloglist> call, Throwable t) {
                    //          Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                    Log.e("blog", t.toString());

                }
            });

        } catch (Exception e) {
        }
    }*/
/*    public void DollerPrice(TextView textView, int price) {
        CurrencyConverter.calculate(price, "INR", "USD", new CurrencyConverter.Callback() {
            @Override
            public void onValueCalculated(Double value, Exception e) {
                if (e != null) {
                    textView.setText(CurrencyConverter.formatCurrencyValue("INR", price));
                } else {
                    textView.setText(CurrencyConverter.formatCurrencyValue("USD", value));
                }
            }
        });
    }*/


    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10);
                        Address address = addresses.get(0);
                        coutry = address.getCountryName();

                        Log.e("coutry_", coutry);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    //not granted
                }
                break;

            case REQUEST_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    isFromFb = false;
                    isFromGoogle = true;
                    signIn();

                    try {
                        addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10);
                        Address address = addresses.get(0);
                        coutry = address.getCountryName();
                        String state_id = addresses.get(0).getAdminArea();
                        String city_id = addresses.get(0).getLocality();
                        Log.e("coutry", coutry);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Toast.makeText(Activity_Purchase.this, "Permission granted.", Toast.LENGTH_SHORT).show();

                  /*  finish();
                    startActivity(getIntent());*/

                } else {
                    Toast.makeText(Activity_Purchase.this, "The app was not allowed to get your location, Please consider granting it this permission", Toast.LENGTH_LONG).show();
                }
            }
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(Activity_Purchase.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Activity_Purchase.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10);
            Address address = addresses.get(0);
            coutry = address.getCountryName();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void buyClick(View view) {
        try {
            if (tinydb.getBoolean(Constants.ISLOGIN_KEY) /* || isBuyenabled*/) {
                if (radioclick == 1) {
                    boolean isSubsUpdateSupported = bp.isSubscriptionUpdateSupported();
                    if (isSubsUpdateSupported) {
                        //String onemonth = tinydb.getString(Onemonth);
                        String Onemonth1 = tinydb.getString(Constants.SKU_DELAROY_MONTHLY);
                        bp.subscribe(Activity_Purchase.this, Onemonth1);
                        // bp.subscribe(Activity_Purchase.this, Onemonth);
                    }
                } else if (radioclick == 2) {
                    boolean isSubsUpdateSupported = bp.isSubscriptionUpdateSupported();
                    if (isSubsUpdateSupported) {
                        //   String oneyear = tinydb.getString(Oneyear);

                        String Oneyear1 = tinydb.getString(Constants.SKU_DELAROY_SIXMONTH);
                        bp.subscribe(Activity_Purchase.this, Oneyear1);
                        // bp.subscribe(Activity_Purchase.this, Oneyear);
                    }

                } else if (radioclick == 3) {
                    boolean isOneTimePurchaseSupported = bp.isOneTimePurchaseSupported();
                    if (isOneTimePurchaseSupported) {
                        //   String lifetime = tinydb.getString(Lifetime);

                        String Lifetime1 = tinydb.getString(Constants.ITEM_SKU1);
                        bp.purchase(Activity_Purchase.this, Lifetime1);
                        //     bp.purchase(Activity_Purchase.this, Lifetime);
                        // bp.purchase(Activity_Purchase.this, PUR_ONETIME);
                    }

                }
            } else {
                signIn();
            /*    Intent intent = new Intent(Activity_Purchase.this, Activity_LoginScreen.class);
                intent.putExtra("isFrom", "purchase");
                startActivity(intent);
                ((Activity) Activity_Purchase.this).finish();*/
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        //     super.onBackPressed();
  /*      Intent intent = new Intent(Activity_Purchase.this, Activity_Home.class);
        startActivity(intent);*/

        if (!tinydb.getBoolean(Constants.PurchaseDialog)) {
            DialogPurchaseFeedback();
        } else {
            finish();
        }
    }

    @Override
    public void onDestroy() {
        if (bp != null)
            bp.release();
        super.onDestroy();
    }

    public void DialogPurchaseFeedback() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View viewRoot = inflater.inflate(R.layout.dialog_purchasefeedback, null);
        builder.setView(viewRoot);
        AlertDialog feedbackdial = builder.create();
        Objects.requireNonNull(feedbackdial.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        // TextView mTxtother = (TextView) viewRoot.findViewById(R.id.txtothers);
        EditText mCustomfeed = (EditText) viewRoot.findViewById(R.id.customfeedback);
        RadioButton feed1 = (RadioButton) viewRoot.findViewById(R.id.feedback_one);
        RadioButton feed2 = (RadioButton) viewRoot.findViewById(R.id.feedback_two);
        RadioButton feed3 = (RadioButton) viewRoot.findViewById(R.id.feedback_three);
        feed1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mCustomfeed.setVisibility(View.GONE);
                    selectedSuperStar = "1";
                }
            }
        });
        feed2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mCustomfeed.setVisibility(View.GONE);
                    selectedSuperStar = "2";
                }
            }
        });
        feed3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mCustomfeed.setVisibility(View.VISIBLE);
                    selectedSuperStar = "3";
                }
            }
        });
        if (feed1.isChecked()) {
            selectedSuperStar = "1";

        } else if (feed2.isChecked()) {
            selectedSuperStar = "2";
        } else if (feed3.isChecked()) {
            selectedSuperStar = "3";
            mCustomfeed.setVisibility(View.VISIBLE);
        }
        TextView btnok = (TextView) viewRoot.findViewById(R.id.btnok);
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callPurchaseFeedbackApi(selectedSuperStar);
                feedbackdial.dismiss();

                tinydb.putBoolean(Constants.PurchaseDialog, true);
            }
        });

        TextView btncancle = (TextView) viewRoot.findViewById(R.id.btnclose);
        btncancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tinydb.putBoolean(Constants.PurchaseDialog, true);
                feedbackdial.dismiss();
            }
        });

       /* builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Constants.isRatedialShown = true;
            }
        });*/

        feedbackdial.show();
        feedbackdial.setCanceledOnTouchOutside(false);
    }

    private void callPurchaseFeedbackApi(String selectedSuperStar) {
        String token = tinydb.getString(Constants.Authtoken);
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
        //  apiInterface = (ApiInterface) CelluliteAPIClient.getCelluliteClient1().create(ApiInterface.class);
        //   ApiInterface   apiInterface = retrofit.create(ApiInterface.class);
        ApiInterface apiInterface = (ApiInterface) BackpainAPIClient.getCelluliteClient1().create(ApiInterface.class);
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("reason", selectedSuperStar);


            Call<Purchasenotification> userCall = apiInterface.addPurchasenotification(paramObject.toString(), "Bearer " + token);
            userCall.enqueue(new Callback<Purchasenotification>() {
                @Override
                public void onResponse(Call<Purchasenotification> call, Response<Purchasenotification> response) {
                    try {


                        Purchasenotification badge = response.body();
                        boolean ok = badge.getSuccess();
                        Log.e("Languageresponse", String.valueOf(ok));
                        Log.e("LanguageresponsegetData", String.valueOf(badge.getReason()));

                    } catch (Exception e) {

                    }
                }

                @Override
                public void onFailure(Call<Purchasenotification> call, Throwable t) {
                    Log.e("response1", String.valueOf(t));
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void showToast(String message) {
        Toast.makeText(Activity_Purchase.this, message, Toast.LENGTH_LONG).show();
    }

    private void shineAnimation() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.left_right);
        shine.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                shine.startAnimation(anim);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

}
