package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Activity_Covidhome extends AppCompatActivity {
    private ArrayList<String> stringArray = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private InterstitialAd mInterstitialAdMob;
    private TinyDB tinyDB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        tinyDB = new TinyDB(Activity_Covidhome.this);

        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(this, languages);

        setContentView(R.layout.activity_covidhome);
        SlidingTabLayout mCattab = (SlidingTabLayout) findViewById(R.id.catetab);
        stringArray.add("India");
        stringArray.add("Global");
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        for (String title : stringArray) {
            if (title.equals("India")) {
                mFragments.add(Fragment_India.getInstance(title));
            } else {
                mFragments.add(Fragment_Global.getInstance(title));
            }
        }
     //   initAdmobFullAd(Activity_Covidhome.this);
       //  loadAdmobAd();
        ImageView mBtnback = (ImageView) findViewById(R.id.btnback);
        mBtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mCattab.setViewPager(viewPager);
    }


/*
    public void initAdmobFullAd(Context context) {
        if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
            return;
        }
        mInterstitialAdMob = new InterstitialAd(context);
        mInterstitialAdMob.setAdUnitId(Constants.admob_Interstitial);
        mInterstitialAdMob.setAdListener(new com.google.android.gms.ads.AdListener() {
            @Override
            public void onAdClosed() {
                Intent intent = new Intent(Activity_Covidhome.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();

            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
            }

            @Override
            public void onAdClicked() {
                super.onAdClicked();
            }

            @Override
            public void onAdImpression() {
                super.onAdImpression();
            }
        });
    }
*/

   /* public void loadAdmobAd() {
        if (mInterstitialAdMob != null && !mInterstitialAdMob.isLoaded()) {
            mInterstitialAdMob.loadAd(new AdRequest.Builder().build());
        }
    }*/

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public String getPageTitle(int position) {
            return stringArray.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()){
            mInterstitialAdMob.show();
        }else {
            Intent intent = new Intent(Activity_Covidhome.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();        }

    }
}
