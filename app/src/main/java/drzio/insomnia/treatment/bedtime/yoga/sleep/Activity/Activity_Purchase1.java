package drzio.insomnia.treatment.bedtime.yoga.sleep.Activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.DiscountPrice;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.Purchasenotification;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.ApiInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.Pricedata;
import drzio.insomnia.treatment.bedtime.yoga.sleep.util.IabBroadcastReceiver;
import drzio.insomnia.treatment.bedtime.yoga.sleep.util.IabHelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.util.IabResult;
import drzio.insomnia.treatment.bedtime.yoga.sleep.util.Inventory;
import drzio.insomnia.treatment.bedtime.yoga.sleep.util.Purchase;
import drzio.insomnia.treatment.bedtime.yoga.sleep.util.SkuDetails;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIClient;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import vas.com.currencyconverter.CurrencyConverter;

import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.Purchasedetails;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.SKU_DELAROY_MONTHLY;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.SKU_DELAROY_SIXMONTH;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.SKU_DELAROY_THREEMONTH;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.SKU_DELAROY_YEARLY;

@SuppressLint("LongLogTag")
public class Activity_Purchase1 extends AppCompatActivity implements IabBroadcastReceiver.IabBroadcastListener {
    private static final String TAG =
            "HeightIncrease.inappbill";
    IabHelper mHelper;
    static final String ITEM_SKU = "backpain_lifetime";

    String CurrencyCode;
    private TinyDB tinydb;
    private IabBroadcastReceiver mBroadcastReceiver;
    String mDelaroySku = "";
    private boolean mAutoRenewEnabled;
    private boolean mSubscribedToDelaroy;
    LinearLayout mPbtn1;
    LinearLayout mPbtn2;
    LinearLayout mPbtn3;
    ImageView mCheck1;
    ImageView mCheck2;
    ImageView mCheck3;
    int radioclick = 1;
    private TextView mTvoneprice, mTvtwoprice, mTvthreeprice;

    String coutry = "India";
    String mCurrencycode;
    private Location location;
    private Geocoder geocoder;
    private List<Address> addresses;
    private TextView mTvdiscount2;
    private String tempprice;
    private boolean isBuyenabled;
    private String token;
    private String BASEURL;
    SkuDetails skuDetails;
    private String selectedSuperStar;
    private TextView offdiscount1, offdiscount2;
    String countryCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        setContentView(R.layout.activity_purchase);
        tinydb = new TinyDB(Activity_Purchase1.this);
        token = tinydb.getString(Constants.Authtoken);
        BASEURL = tinydb.getString(Constants.NewBaseUrl);
        offdiscount1 = (TextView) findViewById(R.id.offdiscount1);
        offdiscount2 = (TextView) findViewById(R.id.offdiscount2);
        countryCode = tinydb.getString(Constants.COUNTRY_CODEID);
        Log.e("counrey", countryCode);


       /* String pricre=String.valueOf(skuDetails.getPrice());
        Log.e("Pricrsss",pricre);*/
        try {
            LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
            }
            assert locationManager != null;
            location = locationManager
                    .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            geocoder = new Geocoder(this);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            isBuyenabled = bundle.getBoolean("proceed");
        }

        ImageView mBack = (ImageView) findViewById(R.id.img_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mPbtn1 = (LinearLayout) findViewById(R.id.pbtn1);
        mPbtn2 = (LinearLayout) findViewById(R.id.pbtn2);
        mPbtn3 = (LinearLayout) findViewById(R.id.pbtn3);
        mCheck1 = (ImageView) findViewById(R.id.imgcheck1);
        mCheck2 = (ImageView) findViewById(R.id.imgcheck2);
        mCheck3 = (ImageView) findViewById(R.id.imgcheck3);
        mTvoneprice = (TextView) findViewById(R.id.tvoneprice);
        mTvtwoprice = (TextView) findViewById(R.id.tvtwoprice);
        mTvdiscount2 = (TextView) findViewById(R.id.tvdiscountprice);
        mTvthreeprice = (TextView) findViewById(R.id.tvthreeprice);
        TextView mTmptv1 = (TextView) findViewById(R.id.tmptxt1);
        TextView mTmptv2 = (TextView) findViewById(R.id.tmptxt2);
        TextView mTmptv3 = (TextView) findViewById(R.id.tmptxt3);


        getPriceList();

        TextView mTvmore = (TextView) findViewById(R.id.btnmore);
        mTvmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Purchase1.this, Activity_webview.class);
                intent.putExtra("title", "Purchase Policy");
                intent.putExtra("link", Purchasedetails);
                startActivity(intent);
            }
        });

        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
            } else {
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10);
                Address address = addresses.get(0);
                coutry = address.getCountryName();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        String mtype = tinydb.getString(Constants.ReasonKey);
        if (mtype.isEmpty()) {
            getPriceList();
        } else {
            getDiscountPriceList(mtype);
        }
        if (mtype.equals("1")) {

            offdiscount1.setText("80% off");
            offdiscount2.setText("80% off");
        } else if (mtype.equals("2")) {

            offdiscount1.setText("60% off");
            offdiscount2.setText("60% off");
        } else {

            offdiscount1.setText("80% off");
            offdiscount2.setText("80% off");
        }
        //   new CurrencyConverterTask().execute();
        String base64EncodedPublicKey = getString(R.string.base64key);

        mHelper = new IabHelper(this, base64EncodedPublicKey);

        mHelper.enableDebugLogging(true);

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            @SuppressLint("LongLogTag")
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    Log.d(TAG, "In-app Billing setup failed: " +
                            result);
                } else {
                    Log.d(TAG, "In-app Billing is set up OK");
                }

                mBroadcastReceiver = new IabBroadcastReceiver(Activity_Purchase1.this);
                IntentFilter broadcastFilter = new IntentFilter(IabBroadcastReceiver.ACTION);
                registerReceiver(mBroadcastReceiver, broadcastFilter);

                Log.d(TAG, "Setup successful. Querying inventory.");
                try {
                    mHelper.queryInventoryAsync(mGotInventoryListener);
                } catch (Exception e) {
                    complain("Error querying inventory. Another async operation in progress.");
                }
            }
        });


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
                    String payload = "";
                    List<String> oldSkus = null;
                    if (!TextUtils.isEmpty(mDelaroySku)
                            && !mDelaroySku.equals(SKU_DELAROY_MONTHLY)) {
                        oldSkus = new ArrayList<String>();

                        oldSkus.add(mDelaroySku);
                    }
                    mHelper.launchPurchaseFlow(Activity_Purchase1.this, SKU_DELAROY_MONTHLY, IabHelper.ITEM_TYPE_SUBS,
                            oldSkus, 10001, mPurchaseFinishedListener, payload);

                } else {
                    Intent intent = new Intent(Activity_Purchase1.this, Activity_LoginScreen.class);
                    intent.putExtra("isFrom", "primium");
                    startActivity(intent);
                    finish();
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
                if (tinydb.getBoolean(Constants.ISLOGIN_KEY)) {
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
                    String payload = "";
                    List<String> oldSkus = null;
                    if (!TextUtils.isEmpty(mDelaroySku)
                            && !mDelaroySku.equals(SKU_DELAROY_SIXMONTH)) {
                        oldSkus = new ArrayList<String>();
                        oldSkus.add(mDelaroySku);
                    }
                    mHelper.launchPurchaseFlow(Activity_Purchase1.this, SKU_DELAROY_SIXMONTH, IabHelper.ITEM_TYPE_SUBS,
                            oldSkus, 10001, mPurchaseFinishedListener, payload);

                } else {
                    Intent intent = new Intent(Activity_Purchase1.this, Activity_LoginScreen.class);
                    intent.putExtra("isFrom", "primium");
                    startActivity(intent);
                    finish();
                }

            }
        });
        mPbtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tinydb.getBoolean(Constants.ISLOGIN_KEY)) {
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
                    mHelper.launchPurchaseFlow(Activity_Purchase1.this, ITEM_SKU, 10001,
                            mPurchaseFinishedListener, "mypurchasetoken");
                } else {
                    Intent intent = new Intent(Activity_Purchase1.this, Activity_LoginScreen.class);
                    intent.putExtra("isFrom", "primium");
                    startActivity(intent);
                    finish();
                }


            }
        });

    }

    private void getDiscountPriceList(String discount) {
        try {
        /*    CurrencyCode = String.valueOf(Currency.getInstance(Locale.getDefault()));
            Log.e("CurrencyCode", CurrencyCode);
*/
            CurrencyCode = String.valueOf(Currency.getInstance(Locale.getDefault()));
            Log.e("CurrencyCode", CurrencyCode);


            String token = tinydb.getString(Constants.Authtoken);
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(loggingInterceptor);

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            OkHttpClient client = httpClient.build();

            String BaseUrl23 = tinydb.getString(Constants.NewBaseUrl);
            Retrofit retrofit = new Retrofit.Builder().baseUrl(BaseUrl23)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(client)
                    .build();
            //     apiInterface = (ApiInterface) CelluliteAPIClient.getCelluliteClient1().create(ApiInterface.class);
            ApiInterface apiInterface = (ApiInterface) BackpainAPIClient.getCelluliteClient1().create(ApiInterface.class);
            //  ApiInterface   apiInterface = retrofit.create(ApiInterface.class);
            try {
                JSONObject paramObject = new JSONObject();
                paramObject.put("reason", discount);
                paramObject.put("country", CurrencyCode);
                paramObject.put("app_number", 22);

                Call<DiscountPrice> userCall = apiInterface.getdiscountprice(paramObject.toString(), "Bearer " + token);
                userCall.enqueue(new Callback<DiscountPrice>() {
                    @Override
                    public void onResponse(Call<DiscountPrice> call, Response<DiscountPrice> response) {
                        try {


                            //  Log.e("response", String.valueOf(response.body()));
                            DiscountPrice loginData = response.body();
//                        String[] heroes = new String[loginData.size()];


                            String price = loginData.getMonthPrice();
                            String symbol = loginData.getCurr_symbol();
                            String lifetime = loginData.getLifeTPrice();


                            mTvoneprice.setText(symbol + String.valueOf(loginData.getMonthPrice()));
                            mTvthreeprice.setText(symbol + String.valueOf(loginData.getLifeTPrice()));
                            mTvtwoprice.setText(symbol + String.valueOf(loginData.getSixMPrice()));

                            mTvdiscount2.setText(symbol + String.valueOf(loginData.getSixMPrice()));

                      /*  mTvoneprice.setText(symbol + price);
                        mTvthreeprice.setText(symbol + lifetime);
                        String sevenstring = symbol + loginData.getSixMPrice();*/
                       /* seventrialss.setText("Free 7 days trial,then "+ sevenstring+" year." +"cancel anytime during the trial");
                        purchasetext.setText("Free 7 days trial,then "+ sevenstring+" year." +"cancel anytime during the trial");*/
                      /*  tinydb.putString(Constants.sevendayTrial, sevenstring);
                        tinydb.putString(Constants.Lifetime, lifetime);
                        tinydb.putString(Constants.OneMonthtime, price);
                        tinydb.putString(Constants.OneYear, loginData.getSixMPrice());*/


                      /*  if (loginData == null) {
                            dataisnotfound.setVisibility(View.VISIBLE);
                            leaderbord.setVisibility(View.GONE);
                        } else {
                            dataisnotfound.setVisibility(View.GONE);
                            leaderbord.setVisibility(View.VISIBLE);

                        }*/
                            //   String id =loginData.get(0).getId();

                            //   Log.e("w5", String.valueOf(response.body()));


                        } catch (Exception e) {

                        }

                        //DiscountPrice. datalists = loginData.result;
                        //  Log.e("id", String.valueOf(DiscountPrice.id));

                    }

                    @Override
                    public void onFailure(Call<DiscountPrice> call, Throwable t) {
                        //Log.e("response1s", String.valueOf(t));
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {

        }
    }

    @SuppressLint("StaticFieldLeak")
    class CurrencyConverterTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                for (int i = 0; i < CurrencyConverter.getCurrencyList().size(); i++) {
                    final Currency currency = CurrencyConverter.getCurrencyList().get(i);
                    if (coutry.equals(CurrencyConverter.getCurrencyLocale(currency).get(0).getDisplayCountry())) {
                        mCurrencycode = currency.getCurrencyCode();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
          /*  CurrencyConverter.calculate(360, "INR", mCurrencycode, new CurrencyConverter.Callback() {
                @Override
                public void onValueCalculated(Double value, Exception e) {
                    if (e != null) {
                        DollerPrice(mTvoneprice,360);
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        String price = mCurrencycode +  CurrencyConverter.formatCurrencyValue(mCurrencycode, value) + "\nPer Month";
                        mTvoneprice.setText(price);
                    }
                }
            });
            CurrencyConverter.calculate(660, "INR", mCurrencycode, new CurrencyConverter.Callback() {
                @Override
                public void onValueCalculated(Double value, Exception e) {
                    if (e != null) {
                        DollerPrice(mTvtwoprice,660);
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        tempprice = CurrencyConverter.formatCurrencyValue(mCurrencycode, value);
                        String price = mCurrencycode + " " + CurrencyConverter.formatCurrencyValue(mCurrencycode, value) + "/6 Months";
                        mTvtwoprice.setText(price);
                    }
                }
            });

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    CurrencyConverter.calculate(110, "INR", mCurrencycode, new CurrencyConverter.Callback() {
                        @Override
                        public void onValueCalculated(Double value, Exception e) {
                            if (e != null) {
                                DollerPrice(mTvdiscount2,110);
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
//                                String price = mCurrencycode + " " + CurrencyConverter.formatCurrencyValue(mCurrencycode, value) + " for one Month \n" + mCurrencycode + " " + tempprice + "/6 Months";
                                String price = mCurrencycode + " " + CurrencyConverter.formatCurrencyValue(mCurrencycode, value) + "\nPer Month";
                                mTvdiscount2.setText(price);
                            }
                        }
                    });
                }
            }, 800);

            CurrencyConverter.calculate(860, "INR", mCurrencycode, new CurrencyConverter.Callback() {
                @Override
                public void onValueCalculated(Double value, Exception e) {
                    if (e != null) {
                        DollerPrice(mTvthreeprice,860);
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    } else {
                        String price = mCurrencycode + " \n " + CurrencyConverter.formatCurrencyValue(mCurrencycode, value);
                        mTvthreeprice.setText(price);
                    }
                }
            });*/
        }
    }


    public void DollerPrice(TextView textView, int price) {
        CurrencyConverter.calculate(price, "INR", "USD", new CurrencyConverter.Callback() {
            @Override
            public void onValueCalculated(Double value, Exception e) {
                if (e != null) {
                    textView.setText(CurrencyConverter.formatCurrencyValue("INR", price));
                 //   Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                } else {
                    textView.setText(CurrencyConverter.formatCurrencyValue("USD", value));
                }
            }
        });
    }


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
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    //not granted
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onResume() {
        super.onResume();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
      /*  try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10);
            Address address = addresses.get(0);
            coutry = address.getCountryName();
            coutry=address.getCountryCode();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }


    public void buyClick(View view) {
        try {
            if (tinydb.getBoolean(Constants.ISLOGIN_KEY) || isBuyenabled) {
                if (radioclick == 1) {
                    String payload = "";
                    List<String> oldSkus = null;
                    if (!TextUtils.isEmpty(mDelaroySku)
                            && !mDelaroySku.equals(SKU_DELAROY_MONTHLY)) {
                        oldSkus = new ArrayList<String>();
                        oldSkus.add(mDelaroySku);
                    }
                    mHelper.launchPurchaseFlow(Activity_Purchase1.this, SKU_DELAROY_MONTHLY, IabHelper.ITEM_TYPE_SUBS,
                            oldSkus, 10001, mPurchaseFinishedListener, payload);

                } else if (radioclick == 2) {
                    String payload = "";
                    List<String> oldSkus = null;
                    if (!TextUtils.isEmpty(mDelaroySku)
                            && !mDelaroySku.equals(SKU_DELAROY_SIXMONTH)) {
                        oldSkus = new ArrayList<String>();
                        oldSkus.add(mDelaroySku);
                    }
                    mHelper.launchPurchaseFlow(Activity_Purchase1.this, SKU_DELAROY_SIXMONTH, IabHelper.ITEM_TYPE_SUBS,
                            oldSkus, 10001, mPurchaseFinishedListener, payload);
                } else if (radioclick == 3) {
                    mHelper.launchPurchaseFlow(this, ITEM_SKU, 10001,
                            mPurchaseFinishedListener, "mypurchasetoken");
                }

            } else {
                Intent intent = new Intent(Activity_Purchase1.this, Activity_LoginScreen.class);
                intent.putExtra("isFrom", "purchase");
                startActivity(intent);
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
            onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (!mHelper.handleActivityResult(requestCode,
                resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener
            = new IabHelper.OnIabPurchaseFinishedListener() {
        public void onIabPurchaseFinished(IabResult result,
                                          Purchase purchase) {
            if (result.isFailure()) {
                return;
            } else if (purchase.getSku().equals(ITEM_SKU)) {
                NoAds();
                tinydb.putBoolean(Constants.ONETIMEPURCHASE, true);
                tinydb.putBoolean(Constants.PREMIUN_KEY, true);
                Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
//                consumeItem();
            } else if (purchase.getSku().equals(SKU_DELAROY_MONTHLY)
                    || purchase.getSku().equals(SKU_DELAROY_SIXMONTH)) {
                Log.d(TAG, "Delaroy subscription purchased.");
                mSubscribedToDelaroy = true;
                mAutoRenewEnabled = purchase.isAutoRenewing();
                mDelaroySku = purchase.getSku();
                tinydb.putBoolean(Constants.PREMIUN_KEY, true);
                Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                finish();
            }
        }
    };


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

    public void consumeItem() {
        mHelper.queryInventoryAsync(mReceivedInventoryListener);
    }

    IabHelper.QueryInventoryFinishedListener mReceivedInventoryListener
            = new IabHelper.QueryInventoryFinishedListener() {
        public void onQueryInventoryFinished(IabResult result,
                                             Inventory inventory) {

            if (result.isFailure()) {
            } else {
                mHelper.consumeAsync(inventory.getPurchase(ITEM_SKU),
                        mConsumeFinishedListener);
            }
        }
    };

    IabHelper.OnConsumeFinishedListener mConsumeFinishedListener =
            new IabHelper.OnConsumeFinishedListener() {
                public void onConsumeFinished(Purchase purchase,
                                              IabResult result) {

                    if (result.isSuccess()) {
                        tinydb.putBoolean(Constants.ONETIMEPURCHASE, true);
                        tinydb.putBoolean(Constants.PREMIUN_KEY, true);
                        Intent i = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        finish();
                    }
                }
            };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mBroadcastReceiver != null) {
            unregisterReceiver(mBroadcastReceiver);
        }

        if (mHelper != null) mHelper.dispose();
        mHelper = null;
       /* if ( feedbackdial!=null && feedbackdial.isShowing() ){
            feedbackdial.dismiss();
        }*/

    }


    boolean verifyDeveloperPayload(Purchase p) {
        String payload = p.getDeveloperPayload();
        return true;
    }

    IabHelper.QueryInventoryFinishedListener mGotInventoryListener = new IabHelper.QueryInventoryFinishedListener() {

        public void onQueryInventoryFinished(IabResult result, Inventory inventory) {
            Log.d(TAG, "Query inventory finished.");

            if (mHelper == null) return;

            if (result.isFailure()) {
                complain("Failed to query inventory: " + result);
                return;
            }

            Log.d(TAG, "Query inventory was successful.");


            Purchase delaroyMonthly = inventory.getPurchase(SKU_DELAROY_MONTHLY);
            Purchase delaroyThreeMonth = inventory.getPurchase(SKU_DELAROY_THREEMONTH);
            Purchase delaroySixMonth = inventory.getPurchase(SKU_DELAROY_SIXMONTH);
            Purchase delaroyYearly = inventory.getPurchase(SKU_DELAROY_YEARLY);
            if (delaroyMonthly != null && delaroyMonthly.isAutoRenewing()) {
                mDelaroySku = SKU_DELAROY_MONTHLY;
                mAutoRenewEnabled = true;
                Log.d("Purchase State", "one month " + delaroyMonthly.getPurchaseState() + "  " + delaroyMonthly.getPurchaseTime());
            } else if (delaroyThreeMonth != null && delaroyThreeMonth.isAutoRenewing()) {
                mDelaroySku = SKU_DELAROY_THREEMONTH;
                mAutoRenewEnabled = true;
                Log.d("Purchase State", "Three month " + delaroyThreeMonth.getPurchaseState() + "  " + delaroyThreeMonth.getPurchaseTime());
            } else if (delaroySixMonth != null && delaroySixMonth.isAutoRenewing()) {
                mDelaroySku = SKU_DELAROY_SIXMONTH;
                mAutoRenewEnabled = true;
                Log.d("Purchase State", "Six month " + delaroySixMonth.getPurchaseState() + "  " + delaroySixMonth.getPurchaseTime());
            } else if (delaroyYearly != null && delaroyYearly.isAutoRenewing()) {
                mDelaroySku = SKU_DELAROY_YEARLY;
                mAutoRenewEnabled = true;
                Log.d("Purchase State", "Yearly " + delaroyYearly.getPurchaseState() + "  " + delaroyYearly.getPurchaseTime());
            } else {
                mDelaroySku = "";
                mAutoRenewEnabled = false;
            }

            mSubscribedToDelaroy = (delaroyMonthly != null && verifyDeveloperPayload(delaroyMonthly))
                    || (delaroySixMonth != null && verifyDeveloperPayload(delaroySixMonth));
            Log.d(TAG, "User " + (mSubscribedToDelaroy ? "HAS" : "DOES NOT HAVE")
                    + " infinite gas subscription.");

            Log.d(TAG, "Initial inventory query finished; enabling main UI.");
        }
    };

    private void getPriceList() {
        try {
            countryCode = tinydb.getString(Constants.COUNTRY_CODEID);
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

            try {
                JSONObject paramObject = new JSONObject();
                paramObject.put("app_number", 22);
                paramObject.put("country_name", countryCode);

                Log.e("pricetoken", token);
                Call<Pricedata> userCall = apiInterface.getprice(paramObject.toString(), "Bearer " + token);
                userCall.enqueue(new Callback<Pricedata>() {
                    @Override
                    public void onResponse(Call<Pricedata> call, Response<Pricedata> response) {
                        try {


                            Log.e("priceresponse", String.valueOf(response.body()));
                            Pricedata loginData = response.body();
                            String symbol = loginData.getCurrSymbol();
                            mTvoneprice.setText(symbol + String.valueOf(loginData.getMonthPrice()));
                            mTvthreeprice.setText(symbol + String.valueOf(loginData.getLifeTPrice()));
                            mTvtwoprice.setText(symbol + String.valueOf(loginData.getOneYPrice()));

                            mTvdiscount2.setText(symbol + String.valueOf(loginData.getOneYPrice()));


                        } catch (Exception e) {

                        }
                        //Pricedata. datalists = loginData.result;
                        //  Log.e("id", String.valueOf(Pricedata.id));

                    }

                    @Override
                    public void onFailure(Call<Pricedata> call, Throwable t) {
                        // Log.e("response1", String.valueOf("response"));
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } catch (Exception e) {

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!tinydb.getBoolean(Constants.PurchaseDialog)) {
            DialogPurchaseFeedback();
        } else {
            finish();
        }
    }


  /*  private Dialog dialdiscount;
    public void DialogPurchaseFeedback() {

        dialdiscount = new Dialog(Activity_Purchase1.this);
        Objects.requireNonNull(dialdiscount.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialdiscount.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialdiscount.setContentView(R.layout.dialog_purchasefeedback);
     *//*   AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
        LayoutInflater inflater = LayoutInflater.from(getApplicationContext());*//*
     *//*  View viewRoot = inflater.inflate(R.layout.dialog_purchasefeedback, null);
        builder.setView(viewRoot);
        feedbackdial = builder.create();
        Objects.requireNonNull(feedbackdial.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
*//*
        // TextView mTxtother = (TextView) viewRoot.findViewById(R.id.txtothers);
        EditText mCustomfeed = (EditText) dialdiscount.findViewById(R.id.customfeedback);
        RadioButton feed1 = (RadioButton) dialdiscount.findViewById(R.id.feedback_one);
        RadioButton feed2 = (RadioButton) dialdiscount.findViewById(R.id.feedback_two);
        RadioButton feed3 = (RadioButton) dialdiscount.findViewById(R.id.feedback_three);
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
        TextView btnok = (TextView) dialdiscount.findViewById(R.id.btnok);
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                callPurchaseFeedbackApi(selectedSuperStar);
                dialdiscount.dismiss();
               // finish();
                tinydb.putBoolean(Constants.PurchaseDialog, true);
            }
        });

        TextView btncancle = (TextView) dialdiscount.findViewById(R.id.btnclose);
        btncancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tinydb.putBoolean(Constants.PurchaseDialog, true);
                dialdiscount.dismiss();
                finish();
            }
        });

       *//* builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                Constants.isRatedialShown = true;
            }
        });*//*

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialdiscount.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialdiscount.show();
        dialdiscount.getWindow().setAttributes(lp);
        dialdiscount.setCanceledOnTouchOutside(false);
    }*/


    public void DialogPurchaseFeedback() {


        try {

            Dialog timersdialog = new Dialog(this);
            Objects.requireNonNull(timersdialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            timersdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            timersdialog.setContentView(R.layout.dialog_purchasefeedback);


            EditText mCustomfeed = (EditText) timersdialog.findViewById(R.id.customfeedback);
            RadioButton feed1 = (RadioButton) timersdialog.findViewById(R.id.feedback_one);
            RadioButton feed2 = (RadioButton) timersdialog.findViewById(R.id.feedback_two);
            RadioButton feed3 = (RadioButton) timersdialog.findViewById(R.id.feedback_three);
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
            TextView btnok = (TextView) timersdialog.findViewById(R.id.btnok);
            btnok.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    callPurchaseFeedbackApi(selectedSuperStar);
                    timersdialog.dismiss();

                    tinydb.putBoolean(Constants.PurchaseDialog, true);
                }
            });

            TextView btncancle = (TextView) timersdialog.findViewById(R.id.btnclose);
            btncancle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tinydb.putBoolean(Constants.PurchaseDialog, true);
                    timersdialog.dismiss();
                }
            });
            //timersdialog.getWindow().setWindowAnimations(R.style.DialogTheme);
            timersdialog.show();
        } catch (Exception e) {


        }
      /*  AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Purchase1.this);
        LayoutInflater inflater = LayoutInflater.from(Activity_Purchase1.this);
        View viewRoot = inflater.inflate(R.layout.dialog_purchasefeedback, null);
        builder.setView(viewRoot);
         feedbackdial = builder.create();
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

                tinydb.putBoolean(Constants.PurchaseDialog,true);
            }
        });

        TextView btncancle = (TextView) viewRoot.findViewById(R.id.btnclose);
        btncancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tinydb.putBoolean(Constants.PurchaseDialog,true);
                feedbackdial.dismiss();
            }
        });



        feedbackdial.show();
        feedbackdial.setCanceledOnTouchOutside(false);*/
    }


    private void callPurchaseFeedbackApi(String selectedSuperStar) {

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


    @Override
    public void receivedBroadcast() {
        Log.d(TAG, "Received broadcast notification. Querying inventory.");
        try {
            mHelper.queryInventoryAsync(mGotInventoryListener);
        } catch (Exception e) {
            complain("Error querying inventory. Another async operation in progress.");
        }
    }

    void complain(String message) {
        Log.e(TAG, "**** Delaroy Error: " + message);
        alert("Error: " + message);
    }

    void alert(String message) {
        AlertDialog.Builder bld = new AlertDialog.Builder(this);
        bld.setMessage(message);
        bld.setNeutralButton("OK", null);
        Log.d(TAG, "Showing alert dialog: " + message);
        bld.create().show();
    }
}
