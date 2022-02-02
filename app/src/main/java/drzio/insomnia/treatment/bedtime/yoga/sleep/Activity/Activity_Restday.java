package drzio.insomnia.treatment.bedtime.yoga.sleep.Activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Activity_Restday extends AppCompatActivity {
    private AdView adView;
    private boolean isclicked = false;
    private String Planname;
    private TinyDB tinydb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        tinydb = new TinyDB(Activity_Restday.this);

        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(this, languages);
        setContentView(R.layout.activity_restday);
        LinearLayout mAdcontainer = (LinearLayout) findViewById(R.id.banner_container);
        mAdcontainer.setVisibility(View.GONE);
        showFBBanner(Activity_Restday.this, mAdcontainer);
        CardView mBtnfinish = (CardView) findViewById(R.id.btnfinish);
        ImageView mBack = (ImageView) findViewById(R.id.imgback);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            Planname = bundle.getString("planename");
        }
        mBtnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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
        if (!isclicked){
            isclicked = true;
            if (Planname.equals(getString(R.string.aboveage))){
                finish();
                super.onBackPressed();
            }else {
                finish();
                super.onBackPressed();
            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
