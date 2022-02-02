package drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Objects;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DisplayConfiguration;
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

//import drzio.insomnia.treatment.bedtime.yoga.sleep.MaleAppstoreActivity;

public class MaleAppstoreActivity extends AppCompatActivity {

    ImageView /*butt, head, arm, throat, heart, leg, calf,*/ ivshop;
    public String mPackagename = " ";
    public int mAppinstallicon;
    TinyDB tinyDB;
    public int appnumber;
    ImageView ivback;
    private RelativeLayout mLaynodata, dataisnotfound;
    RelativeLayout leaderbord;
    ImageView img_model,img_abs, img_head, img_arm, img_throat, img_heart, img_thigh, img_calf;
    ImageView img_abs_selected,img_head_selected,img_arm_selected,img_throat_selected,img_heart_selected,img_thigh_selected,img_calf_selected;
    RelativeLayout rl_abs,rl_head,rl_heart,rl_arms,rl_throat,rl_model;
    RelativeLayout rl_thigh,rl_calf;

    static String BASEURL;
    int arm_selected = 0, throat_selected = 0, heart_selected = 0, head_selected = 0, leg_selected = 0, belly_selected = 0, breasr_selected = 0, butt_selected = 0, claf_selected = 0;
    final BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            tinyDB = new TinyDB(context);
            String packageName = Objects.requireNonNull(intent.getData()).getEncodedSchemeSpecificPart();
            try {
                if (mPackagename != null && mPackagename.equals(packageName)) {
                   /* if (!tinyDB.getBoolean(Constants.PREMIUN_KEY)) {
                        int coins = tinyDB.getInt(Constants.APPCOINS_KEY);
                        Log.e("Coin_Added2", String.valueOf(coins));
                        coins = coins + mAppinstallicon;
                        Constants.UpdateCoins(context, String.valueOf(coins));


                        //mTvcoins.setText(String.valueOf(coins));
                        Log.e("Coin_Added", String.valueOf(coins));
                        Log.e("appnumber_appnumber", String.valueOf(appnumber));
                    }*/


                    String androidId = 22+Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID)+22;

                    DownLoadAppApi(androidId, appnumber);


                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("NewApp", packageName);
        }
    };


    AppstoreAdapter1.Appclicklistner appclicklistner = new AppstoreAdapter1.Appclicklistner() {
        @Override
        public void onClick(String apppackage, String coins, String app_number) {
            Rateus(apppackage);
            mPackagename = apppackage.replace("https://play.google.com/store/apps/details?id=", "");
            mAppinstallicon = Integer.parseInt(coins);
            appnumber = Integer.parseInt(app_number);
            //  (app_number);

        }
    };
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_male_appstore);

        tinyDB = new TinyDB(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        leaderbord = (RelativeLayout) findViewById(R.id.leaderbord);
        mLaynodata = (RelativeLayout) findViewById(R.id.noiternets);
        dataisnotfound = (RelativeLayout) findViewById(R.id.dataisnotfound);
        //listview = (ListView) findViewById(R.id.listview);
        /*throat = findViewById(R.id.throat);
        leg = (ImageView) findViewById(R.id.leg);
        arm = (ImageView) findViewById(R.id.arms);
        calf = (ImageView) findViewById(R.id.calf);
        head = (ImageView) findViewById(R.id.head);
        butt = (ImageView) findViewById(R.id.butt);
        heart = (ImageView) findViewById(R.id.heart);*/
        // leg = (ImageView) findViewById(R.id.leg);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_PACKAGE_ADDED);
        intentFilter.addAction(Intent.ACTION_PACKAGE_INSTALL);
        intentFilter.addDataScheme("package");
        //  Objects.requireNonNull(MaleAppstoreActivity.this).registerReceiver(broadcastReceiver, intentFilter);
        ivback = findViewById(R.id.ivback);
        BASEURL=tinyDB.getString(Constants.NewBaseUrl);
        ConnectivityManager mgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = mgr.getActiveNetworkInfo();

        img_model = findViewById(R.id.img_model);
        img_abs = findViewById(R.id.img_abs);
        img_head = findViewById(R.id.img_head);
        img_arm = findViewById(R.id.img_arm);
        img_throat = findViewById(R.id.img_throat);
        img_heart = findViewById(R.id.img_heart);
        img_thigh = findViewById(R.id.img_thigh);
        img_calf = findViewById(R.id.img_calf);
        img_abs_selected = findViewById(R.id.img_abs_selected);
        img_head_selected = findViewById(R.id.img_head_selected);
        img_arm_selected = findViewById(R.id.img_arm_selected);
        img_throat_selected = findViewById(R.id.img_throat_selected);
        img_heart_selected = findViewById(R.id.img_heart_selected);
        img_thigh_selected = findViewById(R.id.img_thigh_selected);
        img_calf_selected = findViewById(R.id.img_calf_selected);
        rl_model=findViewById(R.id.rl_model);
        rl_abs = findViewById(R.id.rl_abs);
        rl_head = findViewById(R.id.rl_head);
        rl_arms = findViewById(R.id.rl_arm);
        rl_throat = findViewById(R.id.rl_throat);
        rl_heart = findViewById(R.id.rl_heart);
        rl_thigh = findViewById(R.id.rl_thigh);
        rl_calf = findViewById(R.id.rl_calf);
        size();
        pad();

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
        ivshop = (ImageView) findViewById(R.id.ivshop);
        ivshop.setBackgroundResource(R.drawable.adsstoreanim);
        AnimationDrawable anim = (AnimationDrawable) ivshop.getBackground();
        anim.start();
        ivshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MaleAppstoreActivity.this, AllApp_Activity.class);
                startActivity(i);
            }
        });
        leaderbord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MaleAppstoreActivity.this, AllApp_Activity.class);
                startActivity(i);
            }
        });
        //appStore("hand");
        ShimmerFrameLayout mLoadlay = findViewById(R.id.shimmer_container);
        RelativeLayout banner1 = (RelativeLayout) findViewById(R.id.banner1);
        FrameLayout mGbannerlay2 = findViewById(R.id.adframe1234);
        mGbannerlay2.setVisibility(View.GONE);
        if (!tinyDB.getBoolean(Constants.PREMIUN_KEY)) {
       showBanner(this, mGbannerlay2, banner1,mLoadlay);
        }else {
            mLoadlay.setVisibility(View.GONE);
        }
        ivback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
      /*  builder = new GuideView.Builder(this)
                .setTitle("Guide Title Text")
                .setContentText("Click Here......")
                .setGravity(Gravity.center)
                .setDismissType(DismissType.anywhere)
                .setTargetView(view1)
                .setGuideListener(new GuideListener() {
                    @Override
                    public void onDismiss(View view) {
                        switch (view.getId()) {
                            case R.id.image1:
                                builder.setTargetView(view2).build();
                                break;
                            case R.id.image2:
                                builder.setTargetView(view3).build();
                                break;
                            case R.id.image3:
                                builder.setTargetView(view4).build();
                                break;
                            case R.id.image4:
                                builder.setTargetView(view5).build();
                                break;
                            case R.id.image5:
                                builder.setTargetView(view6).build();
                                break;
                            case R.id.image6:
                                builder.setTargetView(view7).build();
                                break;
                            case R.id.image7:
                                builder.setTargetView(view8).build();
                                break;
                            case R.id.image8:
                                builder.setTargetView(view9).build();
                                break;
                            case R.id.image9:
                                return;
                        }
                        mGuideView = builder.build();
                        mGuideView.show();
                    }
                });

        mGuideView = builder.build();
        mGuideView.show();*/


        //leg
        img_thigh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (leg_selected == 0) {
                    DialogPageLoading();
                    leg_selected = 1;
                    appStore("THIGH");

                    img_thigh.setVisibility(View.INVISIBLE);
                    img_thigh_selected.setVisibility(View.VISIBLE);
                    img_arm_selected.setVisibility(View.INVISIBLE);
                    img_throat_selected.setVisibility(View.INVISIBLE);
                    img_calf_selected.setVisibility(View.INVISIBLE);
                    img_abs_selected.setVisibility(View.INVISIBLE);
                    img_heart_selected.setVisibility(View.INVISIBLE);
                    img_head_selected.setVisibility(View.INVISIBLE);


                    img_arm.setVisibility(View.VISIBLE);
                    img_throat.setVisibility(View.VISIBLE);
                    img_calf.setVisibility(View.VISIBLE);
                    img_abs.setVisibility(View.VISIBLE);
                    img_heart.setVisibility(View.VISIBLE);
                    img_head.setVisibility(View.VISIBLE);

                    /*leg.setImageResource(R.drawable.thigh_selectedmale);
                    heart.setImageResource(R.drawable.appstore_heartmale);
                    head.setImageResource(R.drawable.appstore_headmale);
                    butt.setImageResource(R.drawable.appstore_absmale);
                    calf.setImageResource(R.drawable.appstore_calfmale);
                    arm.setImageResource(R.drawable.appstore_armsmale);
                    throat.setImageResource(R.drawable.appstore_throatmale);*/
                } else {
                    img_thigh.setVisibility(View.VISIBLE);
//                    leg.setImageResource(R.drawable.appstore_thighmale);
                    leg_selected = 0;
                }
                //    ShowAppDetails("hand"/* namearraylist, ratearraylist, imagearraylist, bannerimagearraylist, linkarraylist, coinarraylist*/);

            }
        });

        img_heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (breasr_selected == 0) {
                    DialogPageLoading();
                    breasr_selected = 1;
                    appStore("HEART");

                    img_heart.setVisibility(View.INVISIBLE);
                    img_heart_selected.setVisibility(View.VISIBLE);
                    img_arm_selected.setVisibility(View.INVISIBLE);
                    img_throat_selected.setVisibility(View.INVISIBLE);
                    img_calf_selected.setVisibility(View.INVISIBLE);
                    img_abs_selected.setVisibility(View.INVISIBLE);
                    img_thigh_selected.setVisibility(View.INVISIBLE);
                    img_head_selected.setVisibility(View.INVISIBLE);

                    img_arm.setVisibility(View.VISIBLE);
                    img_throat.setVisibility(View.VISIBLE);
                    img_calf.setVisibility(View.VISIBLE);
                    img_abs.setVisibility(View.VISIBLE);
                    img_thigh.setVisibility(View.VISIBLE);
                    img_head.setVisibility(View.VISIBLE);

                    /*heart.setImageResource(R.drawable.heart_selectedmale);
                    leg.setImageResource(R.drawable.appstore_thighmale);
                    head.setImageResource(R.drawable.appstore_headmale);
                    butt.setImageResource(R.drawable.appstore_absmale);
                    calf.setImageResource(R.drawable.appstore_calfmale);
                    arm.setImageResource(R.drawable.appstore_armsmale);
                    throat.setImageResource(R.drawable.appstore_throatmale);*/
                } else {
                    img_heart.setVisibility(View.VISIBLE);
//                    heart.setImageResource(R.drawable.appstore_heartmale);
                    breasr_selected = 0;
                }


                //    ShowAppDetails("hand"/* namearraylist, ratearraylist, imagearraylist, bannerimagearraylist, linkarraylist, coinarraylist*/);

            }
        });
        img_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (belly_selected == 0) {
                    DialogPageLoading();
                    belly_selected = 1;
                    appStore("HEAD");

                    img_head.setVisibility(View.INVISIBLE);
                    img_head_selected.setVisibility(View.VISIBLE);
                    img_arm_selected.setVisibility(View.INVISIBLE);
                    img_throat_selected.setVisibility(View.INVISIBLE);
                    img_calf_selected.setVisibility(View.INVISIBLE);
                    img_abs_selected.setVisibility(View.INVISIBLE);
                    img_thigh_selected.setVisibility(View.INVISIBLE);
                    img_heart_selected.setVisibility(View.INVISIBLE);

                    img_arm.setVisibility(View.VISIBLE);
                    img_throat.setVisibility(View.VISIBLE);
                    img_calf.setVisibility(View.VISIBLE);
                    img_abs.setVisibility(View.VISIBLE);
                    img_thigh.setVisibility(View.VISIBLE);
                    img_heart.setVisibility(View.VISIBLE);

                    /*head.setImageResource(R.drawable.head_selectedmale);
                    leg.setImageResource(R.drawable.appstore_thighmale);
                    heart.setImageResource(R.drawable.appstore_heartmale);
                    butt.setImageResource(R.drawable.appstore_absmale);
                    calf.setImageResource(R.drawable.appstore_calfmale);
                    arm.setImageResource(R.drawable.appstore_armsmale);
                    throat.setImageResource(R.drawable.appstore_throatmale);*/
                } else {
                    img_head.setVisibility(View.VISIBLE);

//                    head.setImageResource(R.drawable.appstore_headmale);
                    belly_selected = 0;
                }

                //    ShowAppDetails("hand"/* namearraylist, ratearraylist, imagearraylist, bannerimagearraylist, linkarraylist, coinarraylist*/);

            }
        });

        //abs-butt
        img_abs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (butt_selected == 0) {
                    DialogPageLoading();
                    butt_selected = 1;
                    appStore("BELLY");

                    img_abs.setVisibility(View.INVISIBLE);
                    img_abs_selected.setVisibility(View.VISIBLE);
                    img_arm_selected.setVisibility(View.INVISIBLE);
                    img_throat_selected.setVisibility(View.INVISIBLE);
                    img_calf_selected.setVisibility(View.INVISIBLE);
                    img_head_selected.setVisibility(View.INVISIBLE);
                    img_thigh_selected.setVisibility(View.INVISIBLE);
                    img_heart_selected.setVisibility(View.INVISIBLE);


                    img_arm.setVisibility(View.VISIBLE);
                    img_throat.setVisibility(View.VISIBLE);
                    img_calf.setVisibility(View.VISIBLE);
                    img_head.setVisibility(View.VISIBLE);
                    img_thigh.setVisibility(View.VISIBLE);
                    img_heart.setVisibility(View.VISIBLE);

                    /*butt.setImageResource(R.drawable.abs_selectedmale);
                    leg.setImageResource(R.drawable.appstore_thighmale);
                    heart.setImageResource(R.drawable.appstore_heartmale);
                    head.setImageResource(R.drawable.appstore_headmale);
                    calf.setImageResource(R.drawable.appstore_calfmale);
                    arm.setImageResource(R.drawable.appstore_armsmale);
                    throat.setImageResource(R.drawable.appstore_throatmale);*/
                } else {
                    img_abs.setVisibility(View.VISIBLE);
//                    butt.setImageResource(R.drawable.appstore_absmale);
                    butt_selected = 0;
                }


                //    ShowAppDetails("hand"/* namearraylist, ratearraylist, imagearraylist, bannerimagearraylist, linkarraylist, coinarraylist*/);

            }
        });
        img_calf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (claf_selected == 0) {
                    DialogPageLoading();
                    claf_selected = 1;
                    appStore("CALF");

                    img_calf.setVisibility(View.INVISIBLE);
                    img_calf_selected.setVisibility(View.VISIBLE);
                    img_arm_selected.setVisibility(View.INVISIBLE);
                    img_throat_selected.setVisibility(View.INVISIBLE);
                    img_head_selected.setVisibility(View.INVISIBLE);
                    img_thigh_selected.setVisibility(View.INVISIBLE);
                    img_heart_selected.setVisibility(View.INVISIBLE);
                    img_abs_selected.setVisibility(View.INVISIBLE);

                    img_arm.setVisibility(View.VISIBLE);
                    img_throat.setVisibility(View.VISIBLE);
                    img_head.setVisibility(View.VISIBLE);
                    img_thigh.setVisibility(View.VISIBLE);
                    img_heart.setVisibility(View.VISIBLE);
                    img_abs.setVisibility(View.VISIBLE);

                   /* calf.setImageResource(R.drawable.calf_selectedmale);
                    leg.setImageResource(R.drawable.appstore_thighmale);
                    heart.setImageResource(R.drawable.appstore_heartmale);
                    head.setImageResource(R.drawable.appstore_headmale);
                    butt.setImageResource(R.drawable.appstore_absmale);
                    arm.setImageResource(R.drawable.appstore_armsmale);
                    throat.setImageResource(R.drawable.appstore_throatmale);*/
                } else {
                    img_calf.setVisibility(View.VISIBLE);
//                    calf.setImageResource(R.drawable.appstore_calfmale);
                    claf_selected = 0;
                }


                //    ShowAppDetails("hand"/* namearraylist, ratearraylist, imagearraylist, bannerimagearraylist, linkarraylist, coinarraylist*/);

            }
        });
        img_arm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (arm_selected == 0) {
                    arm_selected = 1;
                    DialogPageLoading();
                    appStore("ARMS");

                    img_arm.setVisibility(View.INVISIBLE);
                    img_arm_selected.setVisibility(View.VISIBLE);
                    img_throat_selected.setVisibility(View.INVISIBLE);
                    img_head_selected.setVisibility(View.INVISIBLE);
                    img_thigh_selected.setVisibility(View.INVISIBLE);
                    img_heart_selected.setVisibility(View.INVISIBLE);
                    img_abs_selected.setVisibility(View.INVISIBLE);
                    img_calf_selected.setVisibility(View.INVISIBLE);

                    img_throat.setVisibility(View.VISIBLE);
                    img_head.setVisibility(View.VISIBLE);
                    img_thigh.setVisibility(View.VISIBLE);
                    img_heart.setVisibility(View.VISIBLE);
                    img_abs.setVisibility(View.VISIBLE);
                    img_calf.setVisibility(View.VISIBLE);

                    /*arm.setImageResource(R.drawable.arm_selectedmale);
                    leg.setImageResource(R.drawable.appstore_thighmale);
                    heart.setImageResource(R.drawable.appstore_heartmale);
                    head.setImageResource(R.drawable.appstore_headmale);
                    butt.setImageResource(R.drawable.appstore_absmale);
                    calf.setImageResource(R.drawable.appstore_calfmale);
                    throat.setImageResource(R.drawable.appstore_throatmale);*/
                } else {
                    img_arm.setVisibility(View.VISIBLE);
//                    arm.setImageResource(R.drawable.appstore_armsmale);
                    arm_selected = 0;
                }


            }
        });
        img_throat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (throat_selected == 0) {
                    DialogPageLoading();
                    throat_selected = 1;
                    appStore("THROAT");

                    img_throat.setVisibility(View.INVISIBLE);
                    img_throat_selected.setVisibility(View.VISIBLE);
                    img_head_selected.setVisibility(View.INVISIBLE);
                    img_thigh_selected.setVisibility(View.INVISIBLE);
                    img_heart_selected.setVisibility(View.INVISIBLE);
                    img_abs_selected.setVisibility(View.INVISIBLE);
                    img_calf_selected.setVisibility(View.INVISIBLE);
                    img_arm_selected.setVisibility(View.INVISIBLE);

                    img_head.setVisibility(View.VISIBLE);
                    img_thigh.setVisibility(View.VISIBLE);
                    img_heart.setVisibility(View.VISIBLE);
                    img_abs.setVisibility(View.VISIBLE);
                    img_calf.setVisibility(View.VISIBLE);
                    img_arm.setVisibility(View.VISIBLE);

                    /*throat.setImageResource(R.drawable.throat_selectedmale);
                    leg.setImageResource(R.drawable.appstore_thighmale);
                    heart.setImageResource(R.drawable.appstore_heartmale);
                    head.setImageResource(R.drawable.appstore_headmale);
                    butt.setImageResource(R.drawable.appstore_absmale);
                    calf.setImageResource(R.drawable.appstore_calfmale);
                    arm.setImageResource(R.drawable.appstore_armsmale);*/
                } else {
                    img_throat.setVisibility(View.VISIBLE);
//                    throat.setImageResource(R.drawable.appstore_throatmale);
                    throat_selected = 0;
                }
            }
        });
    }
    public void size(){

        img_model.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,500);
        img_model.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,500);
        img_abs.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_abs.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,100);
        img_abs_selected.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_abs_selected.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,100);

        img_head.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_head.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,105);
        img_head_selected.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_head_selected.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,100);

        img_arm.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_arm.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,100);
        img_arm_selected.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_arm_selected.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,100);

        img_throat.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_throat.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,100);
        img_throat_selected.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_throat_selected.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,100);

        img_heart.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_heart.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,100);
        img_heart_selected.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_heart_selected.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,100);

        img_thigh.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_thigh.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,100);
        img_thigh_selected.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_thigh_selected.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,100);

        img_calf.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_calf.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,100);
        img_calf_selected.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_calf_selected.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,100);
    }

    public void pad(){
        rl_model.setPadding(
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,20)
                , DisplayConfiguration.height
                        (MaleAppstoreActivity.this,20)
                , DisplayConfiguration.width
                        (MaleAppstoreActivity.this,20)
                , DisplayConfiguration.height
                        (MaleAppstoreActivity.this,20)
        );
        rl_head.setPadding(
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,0)
                , DisplayConfiguration.height
                        (MaleAppstoreActivity.this,0)
                , DisplayConfiguration.width
                        (MaleAppstoreActivity.this,35)
                , DisplayConfiguration.height
                        (MaleAppstoreActivity.this,0)
        );
        rl_heart.setPadding(
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,0)
                , DisplayConfiguration.height
                        (MaleAppstoreActivity.this,75)
                , DisplayConfiguration.width
                        (MaleAppstoreActivity.this,48)
                , DisplayConfiguration.height
                        (MaleAppstoreActivity.this,0)
        );
        rl_abs.setPadding(
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,0)
                , DisplayConfiguration.height
                        (MaleAppstoreActivity.this,120)
                , DisplayConfiguration.width
                        (MaleAppstoreActivity.this,80)
                , DisplayConfiguration.height
                        (MaleAppstoreActivity.this,0)
        );
        rl_calf.setPadding(
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,0)
                , DisplayConfiguration.height
                        (MaleAppstoreActivity.this,280)
                , DisplayConfiguration.width
                        (MaleAppstoreActivity.this,75)
                , DisplayConfiguration.height
                        (MaleAppstoreActivity.this,0)
        );
        rl_throat.setPadding(
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,123)
                , DisplayConfiguration.height
                        (MaleAppstoreActivity.this,35)
                , DisplayConfiguration.width
                        (MaleAppstoreActivity.this,0)
                , DisplayConfiguration.height
                        (MaleAppstoreActivity.this,0)
        );
        rl_arms.setPadding(
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,25)
                , DisplayConfiguration.height
                        (MaleAppstoreActivity.this,70)
                , DisplayConfiguration.width
                        (MaleAppstoreActivity.this,0)
                , DisplayConfiguration.height
                        (MaleAppstoreActivity.this,0)
        );
        rl_thigh.setPadding(
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,55)
                , DisplayConfiguration.height
                        (MaleAppstoreActivity.this,210)
                , DisplayConfiguration.width
                        (MaleAppstoreActivity.this,0)
                , DisplayConfiguration.height
                        (MaleAppstoreActivity.this,0)
        );
    }
/*

    public void size(){

        img_abs.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_abs.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,95);
        img_abs_selected.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_abs_selected.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,95);

        img_head.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_head.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,95);
        img_head_selected.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_head_selected.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,95);

        img_arm.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_arm.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,95);
        img_arm_selected.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_arm_selected.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,95);

        img_throat.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_throat.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,95);
        img_throat_selected.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_throat_selected.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,95);

        img_heart.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_heart.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,95);
        img_heart_selected.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_heart_selected.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,95);

        img_thigh.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_thigh.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,95);
        img_thigh_selected.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_thigh_selected.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,95);

        img_calf.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_calf.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,95);
        img_calf_selected.getLayoutParams().width =
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,100);
        img_calf_selected.getLayoutParams().height =
                DisplayConfiguration.height
                        (MaleAppstoreActivity.this,95);
    }

    public void pad(){
        rl_head.setPadding(
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,0)
                ,DisplayConfiguration.height
                        (MaleAppstoreActivity.this,15)
                ,DisplayConfiguration.width
                        (MaleAppstoreActivity.this,50)
                ,DisplayConfiguration.height
                        (MaleAppstoreActivity.this,0)
        );
        rl_heart.setPadding(
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,0)
                ,DisplayConfiguration.height
                        (MaleAppstoreActivity.this,100)
                ,DisplayConfiguration.width
                        (MaleAppstoreActivity.this,60)
                ,DisplayConfiguration.height
                        (MaleAppstoreActivity.this,0)
        );
        rl_abs.setPadding(
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,0)
                ,DisplayConfiguration.height
                        (MaleAppstoreActivity.this,140)
                ,DisplayConfiguration.width
                        (MaleAppstoreActivity.this,80)
                ,DisplayConfiguration.height
                        (MaleAppstoreActivity.this,0)
        );
        rl_calf.setPadding(
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,0)
                ,DisplayConfiguration.height
                        (MaleAppstoreActivity.this,260)
                ,DisplayConfiguration.width
                        (MaleAppstoreActivity.this,85)
                ,DisplayConfiguration.height
                        (MaleAppstoreActivity.this,0)
        );
        rl_throat.setPadding(
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,115)
                ,DisplayConfiguration.height
                        (MaleAppstoreActivity.this,50)
                ,DisplayConfiguration.width
                        (MaleAppstoreActivity.this,0)
                ,DisplayConfiguration.height
                        (MaleAppstoreActivity.this,0)
        );
        rl_arms.setPadding(
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,55)
                ,DisplayConfiguration.height
                        (MaleAppstoreActivity.this,85)
                ,DisplayConfiguration.width
                        (MaleAppstoreActivity.this,0)
                ,DisplayConfiguration.height
                        (MaleAppstoreActivity.this,0)
        );
        rl_thigh.setPadding(
                DisplayConfiguration.width
                        (MaleAppstoreActivity.this,55)
                ,DisplayConfiguration.height
                        (MaleAppstoreActivity.this,210)
                ,DisplayConfiguration.width
                        (MaleAppstoreActivity.this,0)
                ,DisplayConfiguration.height
                        (MaleAppstoreActivity.this,0)
        );
    }
*/

    public void showBanner(Context context, final FrameLayout adMobView, RelativeLayout loadlayout, ShimmerFrameLayout mLoadlay) {
        try {
            final AdView mAdView = new AdView(context);
            mAdView.setAdSize(AdSize.BANNER);
            mAdView.setAdUnitId(Constants.admob_banner);
            mAdView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    mLoadlay.setVisibility(View.GONE);
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
                    mLoadlay.setVisibility(View.GONE);
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

    public void DialogPageLoading() {
        try {
            dialog = new Dialog(MaleAppstoreActivity.this);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            dialog.setContentView(R.layout.dialog_adloading);
            LinearLayout mMainlay = dialog.findViewById(R.id.mainlay);
            mMainlay.setBackground(null);
            TextView temptext = dialog.findViewById(R.id.txttitle);
            LottieAnimationView mLottie1 = (LottieAnimationView) dialog.findViewById(R.id.lotti);
            mLottie1.setVisibility(View.GONE);
            LottieAnimationView mLottie = (LottieAnimationView) dialog.findViewById(R.id.lotti2);
            mLottie.setVisibility(View.VISIBLE);
            temptext.setText(getResources().getString(R.string.prepe_app));
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


    private void appStore(String partname) {
        try {

            /*    String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiJhYmMxMjM0IiwiaWF0IjoxNjIzNzU5NDIxfQ.yKkrufRKbPX8yW5ei8a31VPfIKif8cqWHLwmzbHGDv0";
             */
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

            apiInterface.getAppstore(partname, "Bearer " + token).enqueue(new Callback<List<Appstore_new>>() {
                @Override
                public void onResponse(@NotNull Call<List<Appstore_new>> call, @NotNull Response<List<Appstore_new>> response) {
                    try {
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        List<Appstore_new> relateds = response.body();

                        //   List<Appstore.Related> relateds = loginData.g();
                        assert relateds != null;
                        if (relateds.size() == 0) {
                            if (dialog != null && dialog.isShowing()) {
                                dialog.dismiss();
                            }
                            Toast.makeText(MaleAppstoreActivity.this, getResources().getString(R.string.please_check_after), Toast.LENGTH_LONG).show();
                        }
                        for (int i = 0; i < relateds.size(); i++) {
                            String a1 = relateds.get(i).getImage();
                            //   Log.e("getCoin", String.valueOf(a1));
                           /* String namee=relateds.get(i).getName();
                            namearraylist.add(relateds.get(i).getName());
                            ratearraylist.add(relateds.get(i).getRating());
                            imagearraylist.add(relateds.get(i).getImage());
                            bannerimagearraylist.add(relateds.get(i).getBannerImage());
                            linkarraylist.add(relateds.get(i).getLink());
                            coinarraylist.add(relateds.get(i).getCoin());*/


                            //                String[] heroes = new String[loginData.size()];


                           /* int a1 = loginData.get(i).getCoin();
                            String a2 = String.valueOf(loginData.get(i).getName());
                            String a3 = String.valueOf(loginData.get(i).getImage());
                            Log.e("getCoin", String.valueOf(loginData.get(i).getCoin()));
                            Log.e("getCoinDays", String.valueOf(loginData.get(i).getName()));
                            Log.e("getNickname", String.valueOf(loginData.get(i).getImage()));*/
                        }
                        ShowAppDetails(relateds, partname/* namearraylist, ratearraylist, imagearraylist, bannerimagearraylist, linkarraylist, coinarraylist*/);
                        if (relateds == null) {
                            dataisnotfound.setVisibility(View.VISIBLE);
                            leaderbord.setVisibility(View.GONE);
                            if (dialog != null && dialog.isShowing()) {
                                dialog.dismiss();
                            }
                        } else {
                            dataisnotfound.setVisibility(View.GONE);
                            leaderbord.setVisibility(View.VISIBLE);
                            if (dialog != null && dialog.isShowing()) {
                                dialog.dismiss();
                            }

                        }

/*

                            CustomAdapter allexerciseAdapter = new CustomAdapter(LeaderbordActivity.this, Appstorelist,Appstorelist1,Appstorelist2);
                            recyclerView.setAdapter(allexerciseAdapter);
*/


                    } catch (Exception e) {
                        if (dialog != null && dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        Toast.makeText(MaleAppstoreActivity.this, getResources().getString(R.string.please_check_after), Toast.LENGTH_LONG).show();

                    /*    mLoadlay.setVisibility(View.GONE);
                        mLaynodata.setVisibility(View.VISIBLE);*/
                        e.printStackTrace();
                    }
                }


                @Override
                public void onFailure(@NotNull Call<List<Appstore_new>> call, @NotNull Throwable t) {
                    if (dialog != null && dialog.isShowing()) {
                        dialog.dismiss();
                    }
                    Toast.makeText(MaleAppstoreActivity.this, getResources().getString(R.string.please_check_after), Toast.LENGTH_LONG).show();

                    //   Toast.makeText(AppstoreActivity.this, "try after sometime", Toast.LENGTH_LONG).show();
               /*     mLoadlay.setVisibility(View.GONE);
                    mLaynodata.setVisibility(View.VISIBLE);*/
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
          /*  mLoadlay.setVisibility(View.GONE);
            mLaynodata.setVisibility(View.VISIBLE);*/
            e.printStackTrace();
        }
    }

    /*  AppstoreAdapter1.Appclicklistner appclicklistner = new AppstoreAdapter1.Appclicklistner() {
          @Override
          public void onClick(String apppackage, String coins) {
              Rateus(apppackage);
              mPackagename = apppackage.replace("https://play.google.com/store/apps/details?id=","");
              mAppinstallicon = Integer.parseInt(coins);
          }
      };
  */
    private void ShowAppDetails(List<Appstore_new> partname, String name/*ArrayList<String> namearraylist, ArrayList<Integer> ratearraylist, ArrayList<String> imagearraylist, ArrayList<String> bannerimagearraylist, ArrayList<String> linkarraylist, ArrayList<Integer> coinarraylist*/) {
        Dialog timersdialog = new Dialog(MaleAppstoreActivity.this);
        Objects.requireNonNull(timersdialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        timersdialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        timersdialog.setContentView(R.layout.dialog_appstoredetails);
        timersdialog.setCanceledOnTouchOutside(true);
        RecyclerView listview = (RecyclerView) timersdialog.findViewById(R.id.gridview);
        // appStore("hand");
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        listview.setLayoutManager(gridLayoutManager); // set LayoutManager to RecyclerView
        TextView name2222 = (TextView) timersdialog.findViewById(R.id.all_appname);
        if (partname.equals("BELLY")){
            name2222.setText("ABS" + "WORKOUT");
        }else {
            name2222.setText(name + "WORKOUT");
        }

        Log.e("partname", String.valueOf(partname));
        AppstoreAdapter1 allexerciseAdapter = new AppstoreAdapter1(MaleAppstoreActivity.this, partname, appclicklistner);
        listview.setAdapter(allexerciseAdapter);
        TextView allapp = timersdialog.findViewById(R.id.all_app);
        allapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MaleAppstoreActivity.this, AllApp_Activity.class);
                startActivity(i);
                timersdialog.cancel();
            }
        });

        timersdialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                dialog.cancel();
            }
        });
        timersdialog.getWindow().setWindowAnimations(R.style.DialogTheme);
        //  timersdialog.show();

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(Objects.requireNonNull(timersdialog.getWindow()).getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        timersdialog.show();
        timersdialog.getWindow().setAttributes(lp);
    }


    public void Rateus(String link) {
        Uri uri1 = Uri.parse(link);
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri1);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(MaleAppstoreActivity.this, "You don't have Google Play installed", Toast.LENGTH_LONG).show();
        }
    }

    public static class AppstoreAdapter1 extends RecyclerView.Adapter<AppstoreAdapter1.CategoryViewholder> {
        private Context context;
        private int selected = 0;
        List<Appstore_new> categoryitems;
        Appclicklistner appclicklistner;
        TinyDB tinyDB;
        private String musicurl;

        public interface Appclicklistner {
            void onClick(String apppackage, String coins, String app_number);
        }


        public AppstoreAdapter1(Context context, List<Appstore_new> categoryitems1, Appclicklistner appclicklistner) {
            this.categoryitems = categoryitems1;
            this.context = context;
            this.appclicklistner = appclicklistner;
            tinyDB = new TinyDB(context);
        }

        @Override
        public int getItemViewType(int position) {
            return super.getItemViewType(position);
        }

        @NonNull
        @Override
        public CategoryViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(context).inflate(R.layout.custom_appstore1,
                    viewGroup, false);
            CategoryViewholder viewHolder = new CategoryViewholder(view);
          /*  viewHolder.mCardclick = (RelativeLayout) view.findViewById(R.id.cardlay);
            viewHolder.cattitles = (TextView) view
                    .findViewById(R.id.cattitle);*/


            viewHolder.app_name = (TextView) view.findViewById(R.id.app_names);
            viewHolder.rate = (TextView) view.findViewById(R.id.rates);
            viewHolder.app_coins = (TextView) view.findViewById(R.id.coins);
            viewHolder.mCatagorydetail = view.findViewById(R.id.mCatagorydetail);
            viewHolder.banner_image = (ImageView) view.findViewById(R.id.banner_image);
            viewHolder.app_images = (ImageView) view.findViewById(R.id.app_images);


            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull final CategoryViewholder categoryViewholder, final int position) {
            //     final DietCateData.Datalist catitems = categoryitems.get(position);
            categoryViewholder.app_name.setText(categoryitems.get(position).getName());
            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            musicurl=tinyDB.getString(Constants.Backimageurl);
            Glide.with(context).load(musicurl + "apps/" + categoryitems.get(position).getImage())
                    .apply(requestOptions).into(categoryViewholder.app_images);
            if (!tinyDB.getBoolean(Constants.PREMIUN_KEY)) {
                categoryViewholder.app_coins.setVisibility(View.VISIBLE);
            } else {
                categoryViewholder.app_coins.setVisibility(View.GONE);

            }
            Double rates = categoryitems.get(position).getRating();
            categoryViewholder.rate.setText(String.valueOf(rates + "★"));
            Integer coin = categoryitems.get(position).getCoin();
            categoryViewholder.app_coins.setText(String.valueOf(coin));
            categoryViewholder.mCatagorydetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    try {
                        context.startActivity(new Intent(Intent.ACTION_VIEW,
                                Uri.parse(categoryitems.get(position).getLink())));
                    } catch (ActivityNotFoundException anfe) {

                    }
                    //   appclicklistner.onClick(categoryitems.get(position).getLink(), String.valueOf(categoryitems.get(position).getCoin()), String.valueOf(categoryitems.get(position).getNumber()));
                    Log.e("appnumber", String.valueOf(categoryitems.get(position).getNumber()));
                    Appstore_Click(categoryitems.get(position).getNumber());
                }
            });

        }


        @Override
        public int getItemCount() {
            return categoryitems == null ? 0 : categoryitems.size();
        }

        class CategoryViewholder extends RecyclerView.ViewHolder {
            ImageView banner_image, app_images;
            TextView app_name, rate, app_coins, likecount, addComment, blog_catagoryfollowers, btnexestart;
            RelativeLayout mCatagorydetail;

            RelativeLayout catagoty_lay;


            public CategoryViewholder(View itemView) {
                super(itemView);
            }
        }

        public void Appstore_Click(Integer number) {
            try {

                //  String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiJhYmMxMjMiLCJpYXQiOjE2MjMxMzIyMDR9.Ch5Y8hEnw761tZ-RteBAu37qttx6G2YvFBjYH6gtV4k";
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
                    paramObject.put("number", number);
                    Call<Appstore_Addclick> call = apiInterface.addappstoreclick(paramObject.toString(), "Bearer " + token);
                    call.enqueue(new Callback<Appstore_Addclick>() {
                        @Override
                        public void onResponse(Call<Appstore_Addclick> call, Response<Appstore_Addclick> response) {
                            try {
                                //    Log.e("response", String.valueOf(response.body().toString()));

                                Appstore_Addclick appstore_addclick = response.body();
                                String app_name = appstore_addclick.getName();
                                Log.e("app_name", app_name);

                            } catch (Exception e) {

                            }


                        }

                        @Override
                        public void onFailure(Call<Appstore_Addclick> call, Throwable t) {
                            //     Log.e("erooraddtime", t.toString());
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }catch (Exception e){

            }
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


                            Log.e("response", String.valueOf(response.body().toString()));
                            Appstore_Downloadclick appstore_addclick = response.body();
                            Appstore_Downloadclick.Download app_name = appstore_addclick.getDownload();
                            Log.e("dow_app_name", app_name.getName());
                            Log.e("dow_app_number", String.valueOf(app_name.getNumber()));
                        } catch (Exception e) {
                        }
                    }

                    @Override
                    public void onFailure(Call<Appstore_Downloadclick> call, Throwable t) {
                        Log.e("erooraddtime", t.toString());
                    }
                });
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }catch (Exception e){

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}