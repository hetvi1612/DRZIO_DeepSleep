package drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.AppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.ChooseGenderActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.MaleAppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.adapter.Adapter_Selected;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.DietCategories;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.Dietitems;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.User_Dietlist;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Activity_ChangeFixplan extends AppCompatActivity {
    ArrayList<Dietitems> dietitems = new ArrayList<>();
    private String catname;
    ArrayList<User_Dietlist> templist = new ArrayList<>();
    private String catids;
    ArrayList<User_Dietlist> finaldata = new ArrayList<>();
    TinyDB tinyDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_changeplan);
        Bundle bundle = getIntent().getExtras();
        if (bundle!= null){
            catname = bundle.getString("catname");
            catids = bundle.getString("catid");
        }

        tinyDB = new TinyDB(this);
        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(this, languages);

        String mData = tinyDB.getString(Constants.FIXDIET1_KEY);

        List list = (ArrayList<User_Dietlist>) new Gson().fromJson(mData, new TypeToken<ArrayList<User_Dietlist>>() {
        }.getType());
        finaldata = new ArrayList<>(list);

        LinearLayout mStorebtn = (LinearLayout) findViewById(R.id.laystore);
        ImageView mBtnstore = (ImageView) findViewById(R.id.ivbtnstore);
        mBtnstore.setBackgroundResource(R.drawable.adsstoreanim);
        AnimationDrawable anim2 = (AnimationDrawable) mBtnstore.getBackground();
        anim2.start();
        mStorebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cgender=tinyDB.getBoolean(Constants.Genderchoose);
                if (cgender){
                    if (tinyDB.getString(Constants.GENDER_KEY).equals(getResources().getString(R.string.female))) {
                        Intent intent = new Intent(Activity_ChangeFixplan.this, AppstoreActivity.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(Activity_ChangeFixplan.this, MaleAppstoreActivity.class);
                        startActivity(intent);
                    }
                }else {
                    Intent intent = new Intent(Activity_ChangeFixplan.this, ChooseGenderActivity.class);
                    startActivity(intent);
                }
            }
        });
        LinearLayout mGbannerlay = findViewById(R.id.adframe);
        mGbannerlay.setVisibility(View.GONE);
        Smallnative(this, mGbannerlay);
        TextView mTvcatname = (TextView) findViewById(R.id.tvcatname);
        RecyclerView mDietrecycler = (RecyclerView) findViewById(R.id.alldietrecycler);
        mDietrecycler.setLayoutManager(new LinearLayoutManager(Activity_ChangeFixplan.this,LinearLayoutManager.VERTICAL,false));
        dietitems = Dietitems.getGiflistdata();
        mTvcatname.setText(catname);
        for (int i2 = 0; i2 < dietitems.size(); i2++) {
            if (dietitems.get(i2).getCategory_id().equals(catids)) {
                User_Dietlist data = new User_Dietlist();
                data.setId(dietitems.get(i2).getId());
                data.setName(dietitems.get(i2).getName());
                data.setImage(dietitems.get(i2).getImage());
                data.setCategory_id(dietitems.get(i2).getCategory_id());
                data.setDescription(dietitems.get(i2).getDescription());
                templist.add(data);
            }
        }
        TextView mBtndone = (TextView) findViewById(R.id.btndone);
        Adapter_Selected adapter_selected = new Adapter_Selected(Activity_ChangeFixplan.this,templist);
        mDietrecycler.setAdapter(adapter_selected);
        mBtndone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Constants.mPoslist.size() != 0){
                    for (int i = 0; i< Constants.mPoslist.size(); i++){
                        User_Dietlist data = templist.get(Constants.mPoslist.get(i));
                        finaldata.add(data);
                    }
                }
                String json = new Gson().toJson((Object) finaldata);
                tinyDB.putString(Constants.FIXDIET1_KEY, json);
                Constants.mPoslist.clear();
                onBackPressed();
            }
        });
    }


    public static void Smallnative(final Context context, final LinearLayout frameLayout) {
        AdLoader.Builder builder = new AdLoader.Builder(context, Constants.admob_nativead);
        builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
            public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                frameLayout.setVisibility(View.VISIBLE);
                NativeContentAdView nativeContentAdView = (NativeContentAdView) ((Activity) context).getLayoutInflater().inflate(R.layout.ad_native_banner, null);
                populateSmallContentAdView(nativeContentAd, nativeContentAdView);
                frameLayout.removeAllViews();
                frameLayout.addView(nativeContentAdView);
//                Animation mzoom = AnimationUtils.loadAnimation(context, R.anim.adzoom_in);
//                frameLayout.startAnimation(mzoom);
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(int i) {
                Log.e("error", "Failed to load native ad:: " + i);
                FitnessApplication.AdfailToast("MainActivity Small Native", String.valueOf(i));
            }
        }).build().loadAd(new AdRequest.Builder().build());
    }

    @SuppressLint("WrongConstant")
    public static void populateSmallContentAdView(NativeContentAd nativeContentAd, NativeContentAdView nativeContentAdView) {
        nativeContentAdView.setHeadlineView(nativeContentAdView.findViewById(R.id.ad_title_textview));
        nativeContentAdView.setBodyView(nativeContentAdView.findViewById(R.id.ad_describe_textview));
        nativeContentAdView.setCallToActionView(nativeContentAdView.findViewById(R.id.ad_action_button));
        nativeContentAdView.setLogoView(nativeContentAdView.findViewById(R.id.ad_icon_imageview));
        ((TextView) nativeContentAdView.getHeadlineView()).setText(nativeContentAd.getHeadline());
        ((TextView) nativeContentAdView.getBodyView()).setText(nativeContentAd.getBody());
        ((Button) nativeContentAdView.getCallToActionView()).setText(nativeContentAd.getCallToAction());
        com.google.android.gms.ads.formats.NativeAd.Image logo = nativeContentAd.getLogo();
        if (logo == null) {
            nativeContentAdView.getLogoView().setVisibility(4);
        } else {
            ((ImageView) nativeContentAdView.getLogoView()).setImageDrawable(logo.getDrawable());
            nativeContentAdView.getLogoView().setVisibility(0);
        }
        nativeContentAdView.setNativeAd(nativeContentAd);
    }


    public String minerals(String catids) {
        String catname = null;
        for (int i = 0; i < DietCategories.getGifCatlistdata().size(); i++) {
            if (DietCategories.getGifCatlistdata().get(i).getCategory_id().equals(catids)) {
                catname = DietCategories.getGifCatlistdata().get(i).getCatname();
            }
        }
        return catname;
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
