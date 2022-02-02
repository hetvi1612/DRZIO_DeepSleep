package drzio.insomnia.treatment.bedtime.yoga.sleep.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.ramotion.directselect.DSListView;

import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.customs.direct_select.AdvancedExampleCountryAdapter;
import drzio.insomnia.treatment.bedtime.yoga.sleep.customs.direct_select.AdvancedExampleCountryPOJO;
import drzio.insomnia.treatment.bedtime.yoga.sleep.customs.firstdayweekdirect_select.FirstDayofWeekAdapter;
import drzio.insomnia.treatment.bedtime.yoga.sleep.customs.firstdayweekdirect_select.FirstDayofWeekPOJO;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Activity_weekgoal extends AppCompatActivity {
    private TinyDB tinydb;
    private DSListView<AdvancedExampleCountryPOJO>  pickerView;
    CardView btnnxt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekgoal);
        tinydb = new TinyDB(Activity_weekgoal.this);

        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(this, languages);

        ImageView mBtback = (ImageView) findViewById(R.id.btnback);
        btnnxt=findViewById(R.id.btnnxt);
        mBtback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        ShimmerFrameLayout mLoadlay = findViewById(R.id.shimmer_container);
        RelativeLayout banner1 = (RelativeLayout) findViewById(R.id.banner1);
        FrameLayout mGbannerlay2 = findViewById(R.id.adframe1234);
        mGbannerlay2.setVisibility(View.GONE);

        if (!tinydb.getBoolean(Constants.PREMIUN_KEY)) {
        showBanner(this, mGbannerlay2, banner1,mLoadlay);
    }else {
        mLoadlay.setVisibility(View.GONE);
    }
        List<AdvancedExampleCountryPOJO> exampleDataSet = AdvancedExampleCountryPOJO.getExampleDataset();

        ArrayAdapter<AdvancedExampleCountryPOJO> adapter = new AdvancedExampleCountryAdapter(
                this, R.layout.advanced_example_country_list_item, exampleDataSet);

        pickerView = findViewById(R.id.ds_county_list);
        int temp = tinydb.getInt("selected");
        pickerView.setSelectedIndex(temp);
        pickerView.setAdapter(adapter);


        List<FirstDayofWeekPOJO> firstDayofWeekPOJOS = FirstDayofWeekPOJO.getExampleDataset();

        ArrayAdapter<FirstDayofWeekPOJO> firstDayofWeekAdapter = new FirstDayofWeekAdapter(
                this, R.layout.advanced_example_country_list_item, firstDayofWeekPOJOS);

        DSListView<FirstDayofWeekPOJO> dayofWeekPOJODSListView = findViewById(R.id.ds_day_of_week_list);
        int temp2 = tinydb.getInt("selectedday");
        dayofWeekPOJODSListView.setSelectedIndex(temp2);
        dayofWeekPOJODSListView.setAdapter(firstDayofWeekAdapter);

        btnnxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Activity_weekgoal.this, MainActivity.class);
               //tinydb.putInt("fdow",selectedItem.getValue());
                i.putExtra("report","report");
                startActivity(i);
                finish();
            }
        });

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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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
    protected void onDestroy() {
        super.onDestroy();
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
