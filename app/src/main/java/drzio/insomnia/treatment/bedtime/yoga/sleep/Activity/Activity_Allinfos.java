package drzio.insomnia.treatment.bedtime.yoga.sleep.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;

import java.util.ArrayList;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Activity_Allinfos extends AppCompatActivity {
    private ImageView mDietimg;
    private TextView mDietname;
    private TextView mDietdesc;
    String[] mNames = new String[]{"Beginner Level","Intermediate Level","Advanced Level"};
    int[] mImages = new int[]{R.drawable.img_alv1,R.drawable.img_alv1,R.drawable.img_alv1};
    String[] mDesc = new String[]{};
    int mPosition;
    ArrayList<String> mDescs1= new ArrayList<>();
    private int mImgnum;
    private TinyDB tinydb;
    private AdView adView;
    String mDescs = "After lot of research &amp; development and consulting many Psychiatrist, Neurologist as well as\n" +
            "Dietitian, Dr. Zio has brought you research &amp; evidence based scientific Deep Sleep and Insomnia\n" +
            "treatment formula to improve Sleep Quality as well as to control sleeplessness in routine life without\n" +
            "medicines.\n" +
            "Vedic ancient Yoga, Meditation, selective sleep-inducing diet, Pranayama and recommended stress\n" +
            "relief exercise can help you to manage stress and tension &amp; improve quality of sleep for long-term.\n" +
            "If you are very sensitive and emotional (you face sleeplessness issue frequently) – Try to take control\n" +
            "or preventive measure right from today with personal sleep management therapist in the app.\n" +
            "This app provides perfect Seep Sleep Therapy covering\n" +
            "1. Vedic Yoga for Deep sleep &amp; sleeplessness\n" +
            "2. Stress relief Exercise to control thoughts\n" +
            "3. Sleep time meditation\n" +
            "4. Bedtime, better sleep breathing techniques by Pranayama\n" +
            "5. Sleep inducing Diet\n" +
            "6. Daily Sleep Time Tracker\n" +
            "7. Daily Sleep Quality Tracker\n" +
            "8. Performance measurement chart for better sleep\n" +
            "This app can be used by patients with following problems\n" +
            "1. Yoga &amp; Meditation for Snoring Treatment\n" +
            "2. Yoga &amp; Meditation for Sleep Apnea Treatment\n" +
            "\n" +
            "3. Yoga &amp; Meditation for Narcolepsy Treatment\n" +
            "4. Yoga &amp; Meditation for Restless Legs Syndrome Treatment\n" +
            "5. Yoga &amp; Meditation for Nightmares Treatment\n" +
            "6. Yoga &amp; Meditation for Night Terrors and Sleepwalking Treatment\n" +
            "Etc.\n" +
            "Wishing you Happy Deep Sleep Journey with us… Enjoy…\n" +
            "Find out even more at http://www.drzio.com.";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        tinydb = new TinyDB(Activity_Allinfos.this);

        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(this, languages);


        setContentView(R.layout.activity_allinfos);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mPosition = bundle.getInt("pos");
            mImgnum = bundle.getInt("imgnum");
        }
        mDietimg = (ImageView) findViewById(R.id.dietimg);
        mDietname = (TextView) findViewById(R.id.txtname);
        mDietdesc = (TextView) findViewById(R.id.txtdesc);
        CardView mBtnstart = (CardView) findViewById(R.id.btnstart);

        LinearLayout mAdcontainer = (LinearLayout) findViewById(R.id.banner_container);
        mAdcontainer.setVisibility(View.GONE);
        showFBBanner(Activity_Allinfos.this, mAdcontainer);

        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(FitnessApplication.getInstance()).load(mImages[mImgnum])
                .apply(requestOptions).into(mDietimg);
        mDietname.setText(mNames[mPosition]);
        mDescs1.add(getResources().getString(R.string.info1));
        mDescs1.add(getResources().getString(R.string.info2));
        mDescs1.add(getResources().getString(R.string.info3));
        mDietdesc.setText(mDescs1.get(mPosition));
        ImageView mClose = (ImageView) findViewById(R.id.btnclose);
        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mBtnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mImgnum == 0) {
                    Intent intent = new Intent(Activity_Allinfos.this, Activity_Level1.class);
                    startActivity(intent);
                }else  if (mImgnum == 1) {
                    Intent intent = new Intent(Activity_Allinfos.this, Activity_Level2.class);
                    startActivity(intent);
                }else  if (mImgnum == 2) {
                    Intent intent = new Intent(Activity_Allinfos.this, Activity_Level3.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void showFBBanner(final Context context, final LinearLayout bannercontainer) {
        adView = new AdView(context, Constants.facebook_banner, AdSize.BANNER_HEIGHT_50);
        adView.setAdListener(new com.facebook.ads.AdListener() {
            @Override
            public void onError(Ad ad, AdError adError) {

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


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
