package drzio.insomnia.treatment.bedtime.yoga.sleep.Diet;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

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

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.ListItem;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.adapter.Adapter_Userdietnew;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.database.DietplanDbhelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.Header;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.User_Dietlist;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.isDieatBanner3;

public class DefalutAndCustomplanActivity extends AppCompatActivity {
    ArrayList<User_Dietlist> userDietlists = new ArrayList<>();
    ArrayList<User_Dietlist> userDietlists2 = new ArrayList<>();
ImageView btnmenu;
    public List<Header> mDates = new ArrayList<>();
    private String tempdate = "zxcdsdss";
    private ArrayList<ListItem> items = new ArrayList<>();
    private ArrayList<ListItem> tempitems = new ArrayList<>();
    private boolean isbottom;
    String data="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_defalut_and_customplan);
        try {
            Constants.mTemplist.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ShimmerFrameLayout mLoadlay2 = findViewById(R.id.shimmer_container);
        LinearLayout mGbannerlay = findViewById(R.id.adframe);
        FrameLayout mAdframe4 = (FrameLayout) findViewById(R.id.adframe4);
//        FitnessApplication.isAdLoader(mLoadlay2);
        if (isDieatBanner3) {
            mGbannerlay.setVisibility(View.GONE);
         //   showBanner(DefalutAndCustomplanActivity.this, mAdframe4, mLoadlay2);
        } else {
            mAdframe4.setVisibility(View.GONE);
            //Smallnative(DefalutAndCustomplanActivity.this, mGbannerlay, mLoadlay2);
        }
        btnmenu=findViewById(R.id.btnmenu);
        btnmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        data = getIntent().getStringExtra("noti");
        DietplanDbhelper dietplanDbhelper = new DietplanDbhelper(DefalutAndCustomplanActivity.this);
        RecyclerView mDietrecycler = (RecyclerView) findViewById(R.id.dietrecycler);
        mDietrecycler.setLayoutManager(new LinearLayoutManager(DefalutAndCustomplanActivity.this, LinearLayoutManager.VERTICAL, false));
        userDietlists.addAll(dietplanDbhelper.getAllDaysProgress());
        for (int i = 0; i < userDietlists.size(); i++) {
            if (!tempdate.equals(userDietlists.get(i).getDate())) {
                tempdate = userDietlists.get(i).getDate();
                Header header = new Header(userDietlists.get(i).getDate());
                mDates.add(header);
            }

        }
        for (int i = 0; i < mDates.size(); i++) {
            String tempdate2 = mDates.get(i).getName();
            userDietlists2.clear();
            for (int i2 = 0; i2 < userDietlists.size(); i2++) {
                if (tempdate2.equals(userDietlists.get(i2).getDate())) {
                    User_Dietlist data = new User_Dietlist();
                    data.setDate(userDietlists.get(i2).getDate());
                    data.setId(userDietlists.get(i2).getId());
                    data.setName(userDietlists.get(i2).getName());
                    data.setImage(userDietlists.get(i2).getImage());
                    data.setCategory_id(userDietlists.get(i2).getCategory_id());
                    data.setDescription(userDietlists.get(i2).getDescription());
                    userDietlists2.add(data);
                }
            }
            tempitems.add(mDates.get(i));
            tempitems.addAll(userDietlists2);
        }
        items.addAll(tempitems);
        Adapter_Userdietnew adapter_userdiet = new Adapter_Userdietnew(DefalutAndCustomplanActivity.this, items, isbottom, userDietlists);
        mDietrecycler.setAdapter(adapter_userdiet);
        mDietrecycler.scrollToPosition(adapter_userdiet.getItemCount() - 1);

    }

    @Override
    public void onBackPressed() {

        if(data!=null) {
            if (data.equals("true")) {
                Intent intent = new Intent(DefalutAndCustomplanActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            } else {
                Intent intent = new Intent(DefalutAndCustomplanActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        }else {
            finish();
        }
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

}