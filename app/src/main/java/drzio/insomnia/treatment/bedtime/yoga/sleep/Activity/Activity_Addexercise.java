package drzio.insomnia.treatment.bedtime.yoga.sleep.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter.AllexerciseAdapter;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Allexercises;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

import static drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisedetail.mTemplist;

public class Activity_Addexercise extends AppCompatActivity {
    private List<Allexercises> exerciselist = new ArrayList<>();
    private TinyDB tinydb;
    String[] temparray4;
    private boolean isUpdate = false;
    private String mPname;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_addexercise);
        tinydb = new TinyDB(Activity_Addexercise.this);
        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(this, languages);

        RecyclerView mRecyclerall = (RecyclerView) findViewById(R.id.rcallexercise);
        ImageView mBack = (ImageView) findViewById(R.id.ic_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            mPname =  bundle.getString("pname");
            isUpdate = true;
        }
        /*FrameLayout mAdslay = (FrameLayout) findViewById(R.id.adframe);
        mAdslay.setVisibility(View.GONE);
        FitnessApplication.showBanner(Activity_Addexercise.this,mAdslay,2);*/


        ShimmerFrameLayout mLoadlay = findViewById(R.id.shimmer_container);
        RelativeLayout banner1 = (RelativeLayout) findViewById(R.id.banner1);
        FrameLayout mGbannerlay2 = findViewById(R.id.adframe1234);
        mGbannerlay2.setVisibility(View.GONE);
        if (tinydb.getBoolean(Constants.PREMIUN_KEY)){
            mLoadlay.setVisibility(View.GONE);
        }else {
            showBanner(this, mGbannerlay2, banner1, mLoadlay);
        }
        mRecyclerall.setLayoutManager(new LinearLayoutManager(Activity_Addexercise.this, LinearLayoutManager.VERTICAL, false));
        String[] temparray = getResources().getStringArray(R.array.All_exercise);
        String[] temparray2 = getResources().getStringArray(R.array.allexe_youtubevid);
        String[] temparray3 = getResources().getStringArray(R.array.allexeexedesc);

        temparray4 = getResources().getStringArray(R.array.allexecalorie);
        if (tinydb.getBoolean(Constants.ISKG_KEY)) {
            int mWeight = tinydb.getInt(Constants.WEIGHT_KEY);
            if (mWeight < 51) {
                temparray4 = (getResources().getStringArray(R.array.allexecalorie));
            } else if (mWeight > 51 && mWeight < 76) {
                temparray4 = (getResources().getStringArray(R.array.allexe51_75calorie));
            } else if (mWeight > 75 && mWeight < 90) {
                temparray4 = (getResources().getStringArray(R.array.allexe76_90calorie));
            } else if (mWeight > 90) {
                temparray4 = (getResources().getStringArray(R.array.allexe_90calorie));
            }
        } else {
            int mWeight = tinydb.getInt(Constants.WEIGHT_KEY);
            if (mWeight < 112) {
                temparray4 = (getResources().getStringArray(R.array.allexecalorie));
            } else if (mWeight > 112 && mWeight < 167) {
                temparray4 = (getResources().getStringArray(R.array.allexe51_75calorie));
            } else if (mWeight > 167 && mWeight < 198) {
                temparray4 = (getResources().getStringArray(R.array.allexe76_90calorie));
            } else if (mWeight > 198) {
                temparray4 = (getResources().getStringArray(R.array.allexe_90calorie));
            }
        }

        Resources res = getResources();
        final TypedArray videoarray = res.obtainTypedArray(R.array.allexe_vids);
        final TypedArray imagearray = res.obtainTypedArray(R.array.allexe_thumbimg);

        for (int i = 0; i < temparray.length; i++) {
            Allexercises data = new Allexercises();
            data.setExname(temparray[i].toUpperCase());
            data.setExtime(20);
            if (!tinydb.getBoolean(Constants.PREMIUN_KEY)){
                if (i >= 30){
                    data.setLocked(true);
                }else {
                    data.setLocked(false);
                }
            }else {
                data.setLocked(false);
            }
            try {
                data.setEximglink(Videourl(videoarray, i));
                data.setExethumb(Videourl(imagearray, i));
                float number = Float.parseFloat(temparray4[i]);
                float values = number / 60;
                data.setExecalorie(String.valueOf(values));
                data.setVideolink(temparray2[i]);
                data.setExdescr(temparray3[i]);
                exerciselist.add(data);
            }catch (Exception e)
            {

            }
        }
        AllexerciseAdapter allexerciseAdapter = new AllexerciseAdapter(Activity_Addexercise.this, exerciselist,isUpdate,mPname);
        mRecyclerall.setAdapter(allexerciseAdapter);

    }


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

    public String Videourl(TypedArray videoarray,int pos) {
        int[] resIds = new int[videoarray.length()];
        for (int i2 = 0; i2 < videoarray.length(); i2++) {
            resIds[i2] = videoarray.getResourceId(i2, -1);
        }

        Uri video = Uri.parse("android.resource://" + getPackageName() + "/"
                + resIds[pos]);
        return String.valueOf(video);
    }

    @Override
    public void onBackPressed() {
        if (mTemplist.size() != 0){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage(getResources().getString(R.string.save_change));


            alertDialogBuilder.setPositiveButton(getResources().getString(R.string.save),
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent intent = new Intent(Activity_Addexercise.this, Activity_Myplanlist.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                            finish();
                        }
                    });
            alertDialogBuilder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    if (mTemplist != null) {
                        mTemplist.clear();
                    }
                    Intent intent = new Intent(Activity_Addexercise.this, Activity_MyTraining.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();

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
        }else {
            Intent intent = new Intent(Activity_Addexercise.this, Activity_MyTraining.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
    }
}
