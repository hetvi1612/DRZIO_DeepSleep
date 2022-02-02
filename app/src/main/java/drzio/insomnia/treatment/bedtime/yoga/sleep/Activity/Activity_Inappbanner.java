package drzio.insomnia.treatment.bedtime.yoga.sleep.Activity;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Diet.CustomUpdateActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Diet.DefalutAndCustomplanActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.Activity_Covidhome;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Activity_Inappbanner extends AppCompatActivity {


    private String mImglink;
    private String mDatatype;
    private String mDatalink;
    private String mDefault_type;


    public TinyDB tinydb;
    private CountDownTimer mSkiptimer;
    private TextView mCounter;
    private long mLefttime;
    private boolean isFinished = false;
    private boolean isClicked;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inappbanner);
        tinydb = new TinyDB(Activity_Inappbanner.this);
        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(this, languages);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mImglink = bundle.getString("imglink");
            mDatatype = bundle.getString("datatype");
            mDatalink = bundle.getString("datalink");
            mDefault_type = bundle.getString("default_type");
        }
        ImageView mMainimg = (ImageView) findViewById(R.id.adimage);
        LinearLayout mClosebtn = (LinearLayout) findViewById(R.id.btnclose);
        mCounter = (TextView) findViewById(R.id.txtcount);
        mSkiptimer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mLefttime = millisUntilFinished;
                mCounter.setText("(" + TimeFormatter(millisUntilFinished) + ")");
            }

            @Override
            public void onFinish() {
                onBackPressed();
                isFinished = true;
            }
        };
        mSkiptimer.start();
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(FitnessApplication.getInstance()).load(mImglink)
                .apply(requestOptions).into(mMainimg);
        mClosebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSkiptimer.cancel();
                onBackPressed();
            }
        });
        mMainimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDatatype!=null) {
                    switch (mDatatype) {
                        case "Default":
                            Defaultetype(mDefault_type, mDatalink);
                            break;
                        case "Mobile":
                            Gotoplay(mDatalink);
                            break;
                        case "Customizeplan":
                            Intent intentt = new Intent(Activity_Inappbanner.this, Activity_MyTraining.class);
                            startActivity(intentt);
                        case "Web":

                            String tempdate = tinydb.getString(Constants.DIETPLANDATE_KEY);
                            Date c = Calendar.getInstance().getTime();
                             Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
                            String formattedDate = df.format(c);
                            if (!tempdate.isEmpty() && tempdate.equals(formattedDate)) {
                                Intent intent1 = new Intent(Activity_Inappbanner.this, DefalutAndCustomplanActivity.class);
                                intent1.putExtra("isFrom2", false);
                                startActivity(intent1);
                            } else {
                                Intent intent = new Intent(Activity_Inappbanner.this, CustomUpdateActivity.class);
                                intent.putExtra("isFrom", false);
                                startActivity(intent);
                            }

                           /* String tempdate = tinydb.getString(Constants.DIETPLANDATE_KEY);
                            Date c = Calendar.getInstance().getTime();
                             Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
                            String formattedDate = df.format(c);
                            if (!tempdate.isEmpty() && tempdate.equals(formattedDate)) {
                                Intent intent1 = new Intent(Activity_Inappbanner.this, CustomUpdateActivity.class);
                                intent1.putExtra("isFrom2", false);
                                startActivity(intent1);
                            } else {
                                Intent intent = new Intent(Activity_Inappbanner.this, DefalutAndCustomplanActivity.class);
                                intent.putExtra("isFrom", false);
                                startActivity(intent);
                            }*/
                            break;
                        case "Webview":
                            isClicked = true;
                            mSkiptimer.cancel();
                            Intent intent = new Intent(Activity_Inappbanner.this, Activity_webview.class);
                            intent.putExtra("link", mDatalink);
                            startActivity(intent);
                            break;
                        case "Blog":
                            isClicked = true;
                            mSkiptimer.cancel();
                            Intent intent1 = new Intent(Activity_Inappbanner.this, Activity_webview.class);
                            intent1.putExtra("link", mDatalink);
                            startActivity(intent1);
                            Log.e("link",mDatalink);
                          /*  Intent intent1 = new Intent(Activity_Inappbanner.this, MainActivity.class);
                            //   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            Bundle data1 = new Bundle();
                            data1.putInt("discover",4);
                            intent1.putExtras(data1);
                            //    intent.putExtra("isFrom3", true);
                            startActivity(intent1);*/
                          /*  Intent intent2 = new Intent(Activity_Inappbanner.this, Activity_Blogdetails.class);
                            intent2.putExtra("bid", mDatalink);
                            startActivity(intent2);*/
                            break;
                        case "Ads":
                            Toast.makeText(Activity_Inappbanner.this, mDatalink, Toast.LENGTH_SHORT).show();
                            break;
                    }
                }
            }
        });
    }

    public void Defaultetype(String mType, String mDatalink) {
        if (mType != null) {
            switch (mType) {
                case "Default":
                    Intent intent3 = new Intent(Activity_Inappbanner.this, Activity_Level1.class);
                    startActivity(intent3);
                    finish();
                    break;
                case "Mobile":
                    Gotoplay(mDatalink);
                    break;

                case "Customizeplan":
                    Intent intentt = new Intent(Activity_Inappbanner.this, Activity_MyTraining.class);
                    startActivity(intentt);
                case "Web":

                    boolean diet = tinydb.getBoolean(Constants.ReadyDietPlan);
                    if (diet) {
                        Intent intent1 = new Intent(Activity_Inappbanner.this, DefalutAndCustomplanActivity.class);
                        intent1.putExtra("isFrom2", false);
                        startActivity(intent1);
                    } else {
                        Intent intent = new Intent(Activity_Inappbanner.this, CustomUpdateActivity.class);
                        intent.putExtra("isFrom", false);
                        startActivity(intent);
                    }
                    String tempdate = tinydb.getString(Constants.DIETPLANDATE_KEY);
                    Date c = Calendar.getInstance().getTime();
                     Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
                    String formattedDate = df.format(c);
                    if (!tempdate.isEmpty() && tempdate.equals(formattedDate)) {
                        Intent intent1 = new Intent(Activity_Inappbanner.this, DefalutAndCustomplanActivity.class);
                        intent1.putExtra("isFrom2", false);
                        startActivity(intent1);
                    } else {
                        Intent intent = new Intent(Activity_Inappbanner.this, CustomUpdateActivity.class);
                        intent.putExtra("isFrom", false);
                        startActivity(intent);
                    }
                    break;
                case "Webview":
                    Intent intent = new Intent(Activity_Inappbanner.this, Activity_webview.class);
                    intent.putExtra("link", mDatalink);
                    startActivity(intent);
                    break;
                case "Blog":
                    Intent intent1 = new Intent(Activity_Inappbanner.this, Activity_webview.class);
                    intent1.putExtra("link", mDatalink);
                    startActivity(intent1);
                    Log.e("link",mDatalink);
                   /* Intent intent1 = new Intent(Activity_Inappbanner.this, MainActivity.class);
                    //   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    Bundle data1 = new Bundle();
                    data1.putInt("discover",4);
                    intent1.putExtras(data1);
                    //    intent.putExtra("isFrom3", true);
                    startActivity(intent1);*/
                  /*  Intent intent2 = new Intent(Activity_Inappbanner.this, Activity_Blogdetails.class);
                    intent2.putExtra("bid", mDatalink);
                    startActivity(intent2);*/
                    break;
                case "Ads":
                    Intent intent31 = new Intent(Activity_Inappbanner.this, Activity_Purchase.class);
                    startActivity(intent31);
                    break;
                case "Premium":
                    Intent intent4 = new Intent(Activity_Inappbanner.this, Activity_Purchase.class);
                    startActivity(intent4);
                    break;
                case "Covid19":
                    Intent intent5 = new Intent(Activity_Inappbanner.this, Activity_Covidhome.class);
                    startActivity(intent5);
                    break;
            }
        } else {
            Intent intent3 = new Intent(Activity_Inappbanner.this, Activity_Level1.class);
            startActivity(intent3);
            finish();
        }
    }
    @SuppressLint("DefaultLocale")
    private String TimeFormatter(long milliSeconds) {
        String time = String.format("%02d", TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));
        return time;
    }

    public void Gotoplay(String mPlaylink) {
        Uri uri1 = Uri.parse(mPlaylink);
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri1);
        try {
            startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(Activity_Inappbanner.this, "You don't have Google Play installed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Activity_Inappbanner.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isFinished){
            onBackPressed();
            finish();
        }
        if (isClicked){
            if (mDatatype.equals("Webview") || mDatatype.equals("Blog")){
                mSkiptimer = new CountDownTimer(mLefttime, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        mCounter.setText("(" + TimeFormatter(millisUntilFinished) + ")");
                    }

                    @Override
                    public void onFinish() {
                        onBackPressed();
                        finish();
                    }
                };
                mSkiptimer.start();
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
