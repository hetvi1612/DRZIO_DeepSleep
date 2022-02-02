package drzio.insomnia.treatment.bedtime.yoga.sleep.Activity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.mannan.translateapi.Language;
import com.mannan.translateapi.TranslateAPI;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Allexercises;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Activity_Exercisedetail extends AppCompatActivity {
    private ImageView mBtnclose,mBtnminussec,mBtnplussec;
    private TextView mBtnreset,mTxttime,mTxtexename,mBtnexedesc,mTxtaddsave;
    private CardView mBtnAddsave;
    private long mTime = 30000;
    private String mExername;
    public static  ArrayList<Allexercises> mTemplist = new ArrayList<>();

    private String mExevideo;
    private String mExeyouvideo;
    private String mExedesc;
    private VideoView mVidview;
    private String mExethumblink;
    private String mExecalorie;
    private AdView adView;
    private boolean IsUpdate = false;
    private String pName = "";
    private TinyDB tinyDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_exercisedetail);
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            mExername = bundle.getString("mname");
            mExethumblink = bundle.getString("mThumblink");
            mExevideo = bundle.getString("mvideolink");
            mExecalorie = bundle.getString("mCalorie");
            mExeyouvideo = bundle.getString("myoutube");
            mExedesc = bundle.getString("mdesc");
            IsUpdate = bundle.getBoolean("update");
            pName = bundle.getString("pname");
        }
        LinearLayout mAdcontainer = (LinearLayout) findViewById(R.id.banner_container);
        mAdcontainer.setVisibility(View.GONE);
        showFBBanner(Activity_Exercisedetail.this, mAdcontainer);
        mBtnclose = (ImageView) findViewById(R.id.closebtn);
        mBtnreset = (TextView) findViewById(R.id.btresettime);
        mBtnminussec = (ImageView) findViewById(R.id.removesec);
        mTxttime = (TextView) findViewById(R.id.time);
        mBtnplussec = (ImageView) findViewById(R.id.addsec);
        mTxtexename = (TextView) findViewById(R.id.exername);
        mBtnexedesc = (TextView) findViewById(R.id.exerdesc);
        mBtnAddsave = (CardView) findViewById(R.id.btnadd);
        mTxtaddsave = (TextView) findViewById(R.id.txtaddsave);
        mVidview = (VideoView) findViewById(R.id.vidviewvid);
        mBtnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        Uri video = Uri.parse(mExevideo);
        mVidview.setVideoURI(video);
        mVidview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
                mVidview.start();
            }
        });
        mBtnexedesc.setText(mExedesc);
        tinyDB=new TinyDB(this);
        // mBtnexedesc.setText(mExedesc);
        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(this, languages);

         Log.e("lanFguages",languages);
        /* TranslateAPI translateAPI = new TranslateAPI(
                Language.AUTO_DETECT, languages,
                // Language.HINDI,
                mExedesc);
        translateAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
            @Override
            public void onSuccess(String translatedText) {
                Log.d("TAG", "onSuccess: " + translatedText);
                //  textView.setText(translatedText);
                mBtnexedesc.setText(translatedText);
            *//*    t1=new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR) {
                            //  t1.setLanguage(Locale.UK);
                            Locale locale = new Locale("ar");
                            t1.setLanguage(locale);
                            t1.speak(translatedText, TextToSpeech.QUEUE_FLUSH, null);
                        }
                    }
                });
*//*
            }
            @Override
            public void onFailure(String ErrorText) {
                Log.d("TAG", "onFailure: " + ErrorText);
            }
        });*/
        mTxttime.setText(hmsTimeFormatter(mTime));
        mTxtexename.setText(mExername);
        mBtnplussec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long temp = 5*1000;
                mTime = mTime + temp;
                mTxttime.setText(hmsTimeFormatter(mTime));
            }
        });
        mBtnminussec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long temp = 5*1000;
                if (mTime > 5000) {
                    mTime = mTime - temp;
                }
                mTxttime.setText(hmsTimeFormatter(mTime));
            }
        });
        mBtnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTime = 30000;
                mTxttime.setText(hmsTimeFormatter(mTime));
            }
        });
        mBtnAddsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Allexercises allexercises = new Allexercises();
                allexercises.setExname(mExername);
                allexercises.setExethumb(mExethumblink);
                allexercises.setEximglink(mExevideo);
                allexercises.setVideolink(mExeyouvideo);
                allexercises.setExecalorie(mExecalorie);
                allexercises.setExdescr(mExedesc);
                allexercises.setExtime((int) mTime);
                mTemplist.add(allexercises);
                Intent intent = new Intent(Activity_Exercisedetail.this, Activity_Myplanlist.class);
                intent.putExtra("pname",pName);
                intent.putExtra("update",IsUpdate);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        });
    }


    public void showFBBanner(final Context context, final LinearLayout bannercontainer) {
        adView = new AdView(context, Constants.facebook_banner, AdSize.BANNER_HEIGHT_50);
        adView.setAdListener(new com.facebook.ads.AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {
                FitnessApplication.AdfailToast("Activity_Exercisedetails FB Banner",String.valueOf(adError.getErrorMessage()));

            }

            @Override
            public void onAdLoaded(Ad ad) {
                bannercontainer.setVisibility(View.VISIBLE);
                bannercontainer.addView(adView);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });
        adView.loadAd();
    }


    private String hmsTimeFormatter(long milliSeconds) {
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(milliSeconds), TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));
    }

   /* @Override
    public void onBackPressed() {
        Intent intent = new Intent(Activity_Exercisedetail.this, Activity_Addexercise.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }*/
}
