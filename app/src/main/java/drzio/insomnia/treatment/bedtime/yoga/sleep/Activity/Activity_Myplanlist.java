package drzio.insomnia.treatment.bedtime.yoga.sleep.Activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.ads.InterstitialAd;

import java.util.Objects;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter.MyPlansAdapter;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.MyplanDbhelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Allexercises;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

import static drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisedetail.mTemplist;

public class Activity_Myplanlist extends AppCompatActivity {



    MyplanDbhelper myplanDbhelper;
    private Dialog dialog2;
    private InterstitialAd interstitialAd;
    private String temp;
    public boolean isUpdate =  false;
    public String pname = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      /*  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }*/
        setContentView(R.layout.activity_myplanlist);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            pname  = bundle.getString("pname");
            isUpdate = bundle.getBoolean("update");
        }
        RecyclerView mMyplanExcercycler = (RecyclerView) findViewById(R.id.myplanrecycler);
        ImageView mAddbtn = (ImageView) findViewById(R.id.addmorebtn);
        TextView mTotaltime = (TextView) findViewById(R.id.tvptime);
        TextView mPtotalexe = (TextView) findViewById(R.id.tvptotalexe);
        TextView mToolsave = (TextView) findViewById(R.id.toolsave);
        ImageView mBackbtn = (ImageView) findViewById(R.id.img_back);
        mBackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        FrameLayout mAdslay = (FrameLayout) findViewById(R.id.adframe);
        mAdslay.setVisibility(View.GONE);
        //FitnessApplication.showBanner(Activity_Myplanlist.this,mAdslay,4);

       /* DialogAdsloading();
        showFBInterstitial();*/
        myplanDbhelper = new MyplanDbhelper(Activity_Myplanlist.this);
        mMyplanExcercycler.setLayoutManager(new LinearLayoutManager(Activity_Myplanlist.this, LinearLayoutManager.VERTICAL, false));
        if (mTemplist != null && mTemplist.size() != 0) {
            mTotaltime.setText(totaltime());
            String total = mTemplist.size() + " exercises";
            mPtotalexe.setText(total);
            MyPlansAdapter myPlansAdapter = new MyPlansAdapter(Activity_Myplanlist.this, mTemplist,mPtotalexe);
            mMyplanExcercycler.setAdapter(myPlansAdapter);
        }
        mAddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Myplanlist.this, Activity_Addexercise.class);
                if (isUpdate){
                    intent.putExtra("pname",pname);
                }
                startActivity(intent);
                finish();
            }
        });
        mToolsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isUpdate){
                    Dialogsave();
                }else {
                    for (int i = 0; i < mTemplist.size(); i++) {
                      //  Allexercises data = mTemplist.get(i);


                        Allexercises data = mTemplist.get(i);
                        int count;
                        if (data.isCounttype()){
                            count = 1;
                        }else {
                            count = 0;
                        }
                        myplanDbhelper.insertExcALLDayData(pname, data.getExname(),data.getExecalorie(),data.getExethumb(),data.getEximglink(),data.getVideolink(),data.getExdescr(), data.getExtime());


                      //  myplanDbhelper.insertExcALLDayData(pname, data.getExecercisename(),data.getCalorie(),data.getExecerciseimaga(),data.getExecerciseimaga(),data.getVideolink(),data.getExedescription(), data.getExtime(),count);
                    }
                    mTemplist.clear();
                    Intent intent = new Intent(Activity_Myplanlist.this, Activity_MyTraining.class);
                    intent.putExtra("tabpos",0);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }


  /*  public void showFBInterstitial() {

        interstitialAd = new InterstitialAd(Activity_Myplanlist.this, Constants.facebook_interstitial);

        interstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                for (int i = 0; i < mTemplist.size(); i++) {
                    String planname = temp;
                    Allexercises data = mTemplist.get(i);
                    int count;
                    if (data.isCounttype()){
                        count = 1;
                    }else {
                        count = 0;
                    }
                    myplanDbhelper.insertExcALLDayData(planname, data.getExname(),data.getExecalorie(),data.getExethumb(),data.getEximglink(),data.getVideolink(),data.getExdescr(), data.getExtime(),count);
                }
                mTemplist.clear();
                Intent intent = new Intent(Activity_Myplanlist.this, Activity_MyTraining.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onError(Ad ad, AdError adError) {
            }

            @Override
            public void onAdLoaded(Ad ad) {
            }

            @Override
            public void onAdClicked(Ad ad) {
            }

            @Override
            public void onLoggingImpression(Ad ad) {
            }
        });
        interstitialAd.loadAd();
    }*/




   /* public void DialogAdsloading() {
        dialog2 = new Dialog(Activity_Myplanlist.this);
        Objects.requireNonNull(dialog2.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.setCancelable(false);
        dialog2.setCanceledOnTouchOutside(false);
        dialog2.setContentView(R.layout.dialog_adloading);
        LottieAnimationView mLottie = (LottieAnimationView) dialog2.findViewById(R.id.lotti);
        mLottie.setAnimation("adloadanim.json");
        mLottie.playAnimation();
        mLottie.loop(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog2.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog2.getWindow().setAttributes(lp);
    }*/

    public String totaltime() {
        int mTotal = 0;
        for (int i = 0; i < mTemplist.size(); i++) {
            mTotal = mTotal + mTemplist.get(i).getExtime();
        }
        int time = mTotal / 60000;
        String temp;
        if (time == 0 || time == 1) {
            temp = " min";
        } else {
            temp = " mins";
        }
        return time + temp;
    }


    public void Dialogsave() {
        final Dialog dialog = new Dialog(Activity_Myplanlist.this);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_savemyplan);
        final EditText mTvPlanename = (EditText) dialog.findViewById(R.id.edtvplanname);
        TextView mBtncancel = (TextView) dialog.findViewById(R.id.btncancel);
        TextView mBtnokk = (TextView) dialog.findViewById(R.id.btnokk);
        mBtnokk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = mTvPlanename.getText().toString();
                if (!temp.equals("")) {
                    dialog.dismiss();
                    if (interstitialAd!=null && interstitialAd.isAdLoaded()){
                        dialog2.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog2.dismiss();
                                interstitialAd.show();
                            }
                        },1000);

                    }else {





                        for (int i = 0; i < mTemplist.size(); i++) {
                            String planname = String.valueOf(mTvPlanename.getText());
                            Allexercises data = mTemplist.get(i);
                            int count;
                            if (data.isCounttype()){
                                count = 1;
                            }else {
                                count = 0;
                            }
                            myplanDbhelper.insertExcALLDayData(planname, data.getExname(),data.getExecalorie(),data.getExethumb(),data.getEximglink(),data.getVideolink(),data.getExdescr(), data.getExtime());
                        }
                        mTemplist.clear();
                        Intent intent = new Intent(Activity_Myplanlist.this, Activity_MyTraining.class);
                       /* intent.putExtra("tabpos",0);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);*/
                        startActivity(intent);
                        finish();
                    }
                }else {
                    Toast.makeText(Activity_Myplanlist.this,"Please Enter Your Plan Name",Toast.LENGTH_SHORT).show();
                }
            }
        });
        mBtncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setWindowAnimations(R.style.DialogTheme);

        dialog.show();


    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Save changes?");
        alertDialogBuilder.setPositiveButton("SAVE",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Dialogsave();
                    }
                });
        alertDialogBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                if (mTemplist != null) {
                    mTemplist.clear();
                }
                Intent intent = new Intent(Activity_Myplanlist.this, Activity_MyTraining.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
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
    }

}
  /*  MyplanDbhelper myplanDbhelper;
    private Dialog dialog2;
    private InterstitialAd interstitialAd;
    private String temp;
    public boolean isUpdate =  false;
    public String pname = "";
    private TinyDB tinyDB;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        tinyDB = new TinyDB(Activity_Myplanlist.this);
        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(this, languages);
        setContentView(R.layout.activity_myplanlist);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            pname  = bundle.getString("pname");
            isUpdate = bundle.getBoolean("update");
        }
        RecyclerView mMyplanExcercycler = (RecyclerView) findViewById(R.id.myplanrecycler);
        ImageView mAddbtn = (ImageView) findViewById(R.id.addmorebtn);
        TextView mTotaltime = (TextView) findViewById(R.id.tvptime);
        TextView mPtotalexe = (TextView) findViewById(R.id.tvptotalexe);
        TextView mToolsave = (TextView) findViewById(R.id.toolsave);
        ImageView mBackbtn = (ImageView) findViewById(R.id.img_back);
        mBackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        FrameLayout mAdslay = (FrameLayout) findViewById(R.id.adframe);
        mAdslay.setVisibility(View.GONE);
        FitnessApplication.showBanner(Activity_Myplanlist.this,mAdslay,4);

        DialogAdsloading();
        showFBInterstitial();
        myplanDbhelper = new MyplanDbhelper(Activity_Myplanlist.this);
        mMyplanExcercycler.setLayoutManager(new LinearLayoutManager(Activity_Myplanlist.this, LinearLayoutManager.VERTICAL, false));
        if (mTemplist != null && mTemplist.size() != 0) {
            mTotaltime.setText(totaltime());
            String total = mTemplist.size() + " exercises";
            mPtotalexe.setText(total);
            MyPlansAdapter myPlansAdapter = new MyPlansAdapter(Activity_Myplanlist.this, mTemplist,mPtotalexe);
            mMyplanExcercycler.setAdapter(myPlansAdapter);
        }
        mAddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Myplanlist.this, Activity_Addexercise.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                if (isUpdate){
                    intent.putExtra("pname",pname);
                }
                startActivity(intent);
                finish();
            }
        });
        mToolsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isUpdate){
                    Dialogsave();
                }else {
                    for (int i = 0; i < mTemplist.size(); i++) {
                        Allexercises data = mTemplist.get(i);
                        myplanDbhelper.insertExcALLDayData(pname, data.getExname(),data.getExecalorie(),data.getExethumb(),data.getEximglink(),data.getVideolink(),data.getExdescr(), data.getExtime());
                    }
                    if (mTemplist != null) {
                        mTemplist.clear();
                    }
                    Intent intent = new Intent(Activity_Myplanlist.this, Activity_MyTraining.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }


        public void showFBInterstitial() {

        interstitialAd = new InterstitialAd(Activity_Myplanlist.this, Constants.facebook_interstitial);

        interstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {
            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                for (int i = 0; i < mTemplist.size(); i++) {
                    String planname = temp;
                    Allexercises data = mTemplist.get(i);
                    myplanDbhelper.insertExcALLDayData(planname, data.getExname(),data.getExecalorie(),data.getExethumb(),data.getEximglink(),data.getVideolink(),data.getExdescr(), data.getExtime());
                }
                if (mTemplist != null) {
                    mTemplist.clear();
                }
                Intent intent = new Intent(Activity_Myplanlist.this, Activity_MyTraining.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }

            @Override
            public void onError(Ad ad, AdError adError) {
            }

            @Override
            public void onAdLoaded(Ad ad) {
            }

            @Override
            public void onAdClicked(Ad ad) {
            }

            @Override
            public void onLoggingImpression(Ad ad) {
            }
        });
        interstitialAd.loadAd();
    }



    public void DialogAdsloading() {
        dialog2 = new Dialog(Activity_Myplanlist.this);
        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog2.setCancelable(false);
        dialog2.setCanceledOnTouchOutside(false);
        dialog2.setContentView(R.layout.dialog_adloading);
        LottieAnimationView mLottie = (LottieAnimationView) dialog2.findViewById(R.id.lotti);
        mLottie.setAnimation("adloadanim.json");
        mLottie.playAnimation();
        mLottie.loop(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog2.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog2.getWindow().setAttributes(lp);
    }

    public String totaltime() {
        int mTotal = 0;
        for (int i = 0; i < mTemplist.size(); i++) {
            mTotal = mTotal + mTemplist.get(i).getExtime();
        }
        int time = mTotal / 60000;
        String temp;
        if (time == 0 || time == 1) {
            temp = " min";
        } else {
            temp = " mins";
        }
        return time + temp;
    }


    public void Dialogsave() {
        final Dialog dialog = new Dialog(Activity_Myplanlist.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_savemyplan);
        final EditText mTvPlanename = (EditText) dialog.findViewById(R.id.edtvplanname);
        TextView mBtncancel = (TextView) dialog.findViewById(R.id.btncancel);
        TextView mBtnokk = (TextView) dialog.findViewById(R.id.btnokk);
        mBtnokk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = mTvPlanename.getText().toString();
                if (!temp.equals("")) {
                    dialog.dismiss();
                    if (interstitialAd!=null && interstitialAd.isAdLoaded()){
                        dialog2.show();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dialog2.dismiss();
                                interstitialAd.show();
                            }
                        },1000);

                    }else {
                        for (int i = 0; i < mTemplist.size(); i++) {
                            String planname = String.valueOf(mTvPlanename.getText());
                            Allexercises data = mTemplist.get(i);
                            myplanDbhelper.insertExcALLDayData(planname, data.getExname(),data.getExecalorie(),data.getExethumb(),data.getEximglink(),data.getVideolink(),data.getExdescr(), data.getExtime());
                        }
                        if (mTemplist != null) {
                            mTemplist.clear();
                        }
                        Intent intent = new Intent(Activity_Myplanlist.this, Activity_MyTraining.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }
                }else {
                    Toast.makeText(Activity_Myplanlist.this,"Please Enter Your Plan Name",Toast.LENGTH_SHORT).show();
                }
            }
        });
        mBtncancel.setOnClickListener(new View.OnClickListener() {
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
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(getResources().getString(R.string.save_change));
        alertDialogBuilder.setPositiveButton(getResources().getString(R.string.save),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Dialogsave();
                    }
                });
        alertDialogBuilder.setNegativeButton(getResources().getString(R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (mTemplist != null) {
                    mTemplist.clear();
                }
                Intent intent = new Intent(Activity_Myplanlist.this, Activity_MyTraining.class);
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
    }
}*/
