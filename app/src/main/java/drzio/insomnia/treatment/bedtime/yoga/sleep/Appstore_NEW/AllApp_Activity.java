package drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.ApiInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AllApp_Activity extends AppCompatActivity {
    RecyclerView applist, subapplist, tradingapplist;
    ImageView mAppbanner;
    public String mPackagename = " ";
    public int mAppinstallicon;
    TinyDB tinyDB;
    public int appnumber;
    private RelativeLayout mLaynodata, dataisnotfound;
    LinearLayout leaderbord;
    private Dialog dialog;
    ShimmerFrameLayout shimmerFrameLayout;
    LinearLayout relatedapp;
    AllAppstoreSubAdapter allexerciseAdapter1;
    final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            tinyDB = new TinyDB(context);
            String packageName = Objects.requireNonNull(intent.getData()).getEncodedSchemeSpecificPart();
            try {
                if (mPackagename != null && mPackagename.equals(packageName)) {
                   /* int coins = tinyDB.getInt(Constants.APPCOINS_KEY);
                    Log.e("Coin_Added2", String.valueOf(coins));
                    coins = coins + mAppinstallicon;
                    Constants.UpdateCoins(context, String.valueOf(coins));*/
                    //   mTvcoins.setText(String.valueOf(coins));
                    //  Log.e("Coin_Added", String.valueOf(coins));
                    Log.e("appnumber_appnumber", String.valueOf(appnumber));
                    String androidId = 22+Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID)+22;

                    DownLoadAppApi(androidId, appnumber);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("NewApp", packageName);
        }
    };

    AllAppstoreAdapter.Appclicklistner appclicklistner = new AllAppstoreAdapter.Appclicklistner() {
        @Override
        public void onClick(String apppackage, String coins, String app_number) {
            Rateus(apppackage);
            mPackagename = apppackage.replace("https://play.google.com/store/apps/details?id=", "");
            mAppinstallicon = Integer.parseInt(coins);
            appnumber = Integer.parseInt(app_number);
            //  (app_number);

        }
    };
    AllAppstoreSubAdapter.Appclicklistner appclicklistner1 = new AllAppstoreSubAdapter.Appclicklistner() {
        @Override
        public void onClick(String apppackage, String coins, String app_number) {
            Rateus(apppackage);
            mPackagename = apppackage.replace("https://play.google.com/store/apps/details?id=", "");
            mAppinstallicon = Integer.parseInt(coins);
            appnumber = Integer.parseInt(app_number);
            //  (app_number);

        }
    };
    AllAppstorenonAdapter.Appclicklistner appclicklistner3 = new AllAppstorenonAdapter.Appclicklistner() {
        @Override
        public void onClick(String apppackage, String coins, String app_number) {
            Rateus(apppackage);
            mPackagename = apppackage.replace("https://play.google.com/store/apps/details?id=", "");
            mAppinstallicon = Integer.parseInt(coins);
            appnumber = Integer.parseInt(app_number);
            //  (app_number);

        }
    };
    private String BASEURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_app_);
        tinyDB = new TinyDB(this);
        BASEURL=tinyDB.getString(Constants.NewBaseUrl);
        applist = (RecyclerView) findViewById(R.id.applist);
        subapplist = (RecyclerView) findViewById(R.id.subapplist);
        tradingapplist = (RecyclerView) findViewById(R.id.tradingapplist);
        tradingapplist.setLayoutManager(new LinearLayoutManager(AllApp_Activity.this, LinearLayoutManager.VERTICAL, false));
        shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.slideloader);
        relatedapp=findViewById(R.id.relatedapp);
        subapplist.setLayoutManager(new LinearLayoutManager(AllApp_Activity.this, LinearLayoutManager.VERTICAL, false));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        applist.setLayoutManager(new LinearLayoutManager(AllApp_Activity.this, LinearLayoutManager.HORIZONTAL, false));
        getAllAPPList();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_INSTALL);
        intentFilter.addDataScheme("package");
//        Objects.requireNonNull(drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.AllApp_Activity.this).registerReceiver(broadcastReceiver, intentFilter);
        /*RelativeLayout main_layout = (RelativeLayout) findViewById(R.id.cardview);
        ViewFlipper viewFlipper = findViewById(R.id.flipperid);
        viewFlipper.startFlipping();*/
        leaderbord = (LinearLayout) findViewById(R.id.leaderbord);
        mLaynodata = (RelativeLayout) findViewById(R.id.noiternets);
        dataisnotfound = (RelativeLayout) findViewById(R.id.dataisnotfound);
        ConnectivityManager mgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();

        if (netInfo != null) {
            if (netInfo.isConnected()) {
                leaderbord.setVisibility(View.VISIBLE);
                mLaynodata.setVisibility(View.GONE);
                //  Toast.makeText(getContext(),"Available",Toast.LENGTH_SHORT).show();
                // Internet Available
            } else {
                //No internet
                leaderbord.setVisibility(View.GONE);
                //   Toast.makeText(getContext(),"No internet",Toast.LENGTH_SHORT).show();
                mLaynodata.setVisibility(View.VISIBLE);
            }
        } else {
            leaderbord.setVisibility(View.GONE);
            //   Toast.makeText(getContext(),"No internet1",Toast.LENGTH_SHORT).show();
            mLaynodata.setVisibility(View.VISIBLE);
            //No internet
        }
        //DialogPageLoading();
        mAppbanner = findViewById(R.id.banner_image);
      /*  RoundedImageView mAppbanner2 = findViewById(R.id.ivappbanner2);
        RoundedImageView mAppicon = findViewById(R.id.ivappicon);
        TextView mTvappname = findViewById(R.id.tvappname);
        TextView mTvapprating = findViewById(R.id.tvapprating);
        TextView mTvcoin = (TextView) findViewById(R.id.tvcoin);*/
        ImageView imageView = findViewById(R.id.ivback);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    public void DialogPageLoading() {
        try {
            dialog = new Dialog(this);
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_adloading);
            LinearLayout mMainlay = dialog.findViewById(R.id.mainlay);
            mMainlay.setBackground(null);
            TextView temptext = dialog.findViewById(R.id.txttitle);
            LottieAnimationView mLottie1 = (LottieAnimationView) dialog.findViewById(R.id.lotti);
            mLottie1.setVisibility(View.GONE);
            LottieAnimationView mLottie = (LottieAnimationView) dialog.findViewById(R.id.lotti2);
            mLottie.setVisibility(View.VISIBLE);
            temptext.setText("Loading...");
            mLottie.setAnimation("loader.json");
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

    private void getAllAPPList() {
        try {

            //   String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiJhYmMxMjM0IiwiaWF0IjoxNjIzNzU5NDIxfQ.yKkrufRKbPX8yW5ei8a31VPfIKif8cqWHLwmzbHGDv0";
            String token = tinyDB.getString(Constants.Authtoken);
            Log.e("token", token);
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

                    apiInterface.getAppstorelist(22, "Bearer " + token).enqueue(new Callback<AppstoreList>() {
                @Override
                public void onResponse(@NotNull Call<AppstoreList> call, @NotNull Response<AppstoreList> response) {
                    try {
                        Log.e("tpokee", response.toString());
                        AppstoreList relateds = response.body();
                        List<AppstoreList.Related> related = relateds.getRelated();
                        for (int i = 0; i < related.size(); i++) {
                            Log.e("image", related.get(i).getName());
                            String image = related.get(2).getBannerImage();

                        }

                        shimmerFrameLayout.setVisibility(View.GONE);
                        applist.setVisibility(View.VISIBLE);
                        applist.setLayoutManager(new LinearLayoutManager(AllApp_Activity.this, LinearLayoutManager.HORIZONTAL, false));
                        AllAppstoreAdapter allexerciseAdapter = new AllAppstoreAdapter(AllApp_Activity.this, related, appclicklistner);
                        applist.setAdapter(allexerciseAdapter);

                        if (related.size() > 3) {
                            relatedapp.setVisibility(View.VISIBLE);
                            subapplist.setVisibility(View.VISIBLE);
                            allexerciseAdapter1 = new AllAppstoreSubAdapter(AllApp_Activity.this, related, appclicklistner1);
                            subapplist.setAdapter(allexerciseAdapter1);
                        } else {
                            subapplist.setVisibility(View.GONE);
                            relatedapp.setVisibility(View.GONE);
                        }


                        List<AppstoreList.NotRelated> notRelateds = relateds.getNotRelated();
                        AllAppstorenonAdapter allAppstorenonAdapter = new AllAppstorenonAdapter(AllApp_Activity.this, notRelateds, appclicklistner3);
                        tradingapplist.setAdapter(allAppstorenonAdapter);
                        dialog.dismiss();

                        if (relateds == null) {
                            dataisnotfound.setVisibility(View.VISIBLE);
                            leaderbord.setVisibility(View.GONE);
                        } else {
                            dataisnotfound.setVisibility(View.GONE);
                            leaderbord.setVisibility(View.VISIBLE);

                        }

                    } catch (Exception e) {
                    /*    mLoadlay.setVisibility(View.GONE);
                        mLaynodata.setVisibility(View.VISIBLE);*/
                        e.printStackTrace();
                    }
                }


                @Override
                public void onFailure(@NotNull Call<AppstoreList> call, @NotNull Throwable t) {
                  //  Toast.makeText(AllApp_Activity.this, t.toString(), Toast.LENGTH_LONG).show();
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
               /*     mLoadlay.setVisibility(View.GONE);
                    mLaynodata.setVisibility(View.VISIBLE);*/
                }
            });
        } catch (Exception e) {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
            e.printStackTrace();
          /*  mLoadlay.setVisibility(View.GONE);
            mLaynodata.setVisibility(View.VISIBLE);*/
            e.printStackTrace();
        }
    }

    public void Rateus(String link) {
        Uri uri1 = Uri.parse(link);
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri1);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(AllApp_Activity.this, "You don't have Google Play installed", Toast.LENGTH_LONG).show();
        }
    }

    private void DownLoadAppApi(String androidId, int appnumber) {
        try {
            String token = tinyDB.getString(Constants.Authtoken);
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
                paramObject.put("deviceId", androidId);
                paramObject.put("number", appnumber);
                Call<Appstore_Downloadclick> call = apiInterface.adddowappstoreclick(paramObject.toString(), "Bearer " + token);
                call.enqueue(new Callback<Appstore_Downloadclick>() {
                    @Override
                    public void onResponse(Call<Appstore_Downloadclick> call, Response<Appstore_Downloadclick> response) {
                        try {


                            ///   Log.e("response", String.valueOf(response.body().toString()));
                            Appstore_Downloadclick appstore_addclick = response.body();
                            Appstore_Downloadclick.Download app_name = appstore_addclick.getDownload();
                            // Log.e("dow_app_name", app_name.getName());
                            // Log.e("dow_app_number", String.valueOf(app_name.getNumber()));
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<Appstore_Downloadclick> call, Throwable t) {
                        //   Log.e("erooraddtime", t.toString());
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }catch (Exception e){

        }

    }

}