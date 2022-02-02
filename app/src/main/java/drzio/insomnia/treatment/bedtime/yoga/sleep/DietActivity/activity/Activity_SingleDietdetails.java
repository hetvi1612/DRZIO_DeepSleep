package drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.ads.Ad;
import com.facebook.ads.AdChoicesView;
import com.facebook.ads.AdError;
import com.facebook.ads.AdIconView;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdListener;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.AppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.ChooseGenderActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.MaleAppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.DietitemsData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Activity_SingleDietdetails extends AppCompatActivity {
    private ImageView mDietimg;
    private TextView mDietname;
    private TextView mDietdesc;
    private RelativeLayout mNativeframe;
    private List<DietitemsData.Datalist> mDietlist = new ArrayList<>();
    private int mPosition = 1;
    private String dietid;
    private String cateid;
    private RelativeLayout mBtnprev;
    private RelativeLayout mBtnnext;
    private Dialog dialog;
    private TinyDB tinydb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singledietdetail);
        tinydb = new TinyDB(this);

        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(this, languages);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            dietid = bundle.getString("dietid");
            cateid = bundle.getString("cateid");

        }
//        mDietlist = Dietitems.getGiflistdata();
        for (int i = 0; i < mDietlist.size(); i++) {
            DietitemsData.Datalist dietitems = mDietlist.get(i);
            if (dietitems.getId().equals(dietid) && dietitems.getCategory_id().equals(cateid)) {
                mPosition = i;
            }
        }

        TextView mBtnshare = (TextView) findViewById(R.id.btnshare);
        ImageView mBtnstore = (ImageView) findViewById(R.id.ivbtnstore);
        mBtnstore.setBackgroundResource(R.drawable.adsstoreanim);
        AnimationDrawable anim2 = (AnimationDrawable) mBtnstore.getBackground();
        anim2.start();
        mBtnstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cgender=tinydb.getBoolean(Constants.Genderchoose);
                if (cgender){
                    if (tinydb.getString(Constants.GENDER_KEY).equals(getResources().getString(R.string.female))) {
                        Intent intent = new Intent(Activity_SingleDietdetails.this, AppstoreActivity.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(Activity_SingleDietdetails.this, MaleAppstoreActivity.class);
                        startActivity(intent);
                    }
                }else {
                    Intent intent = new Intent(Activity_SingleDietdetails.this, ChooseGenderActivity.class);
                    startActivity(intent);
                }
            }
        });
        mDietimg = (ImageView) findViewById(R.id.dietimg);
        mDietname = (TextView) findViewById(R.id.txtname);
        mDietdesc = (TextView) findViewById(R.id.txtdesc);
        mNativeframe = (RelativeLayout) findViewById(R.id.fbnative);
        mBtnprev = (RelativeLayout) findViewById(R.id.prevexcer);
        mBtnnext = (RelativeLayout) findViewById(R.id.nextexcer);
        RelativeLayout mAdcard = (RelativeLayout) findViewById(R.id.adcard);
        mAdcard.setVisibility(View.GONE);
        mNativeframe.setVisibility(View.GONE);
        showFBNativeAd(Activity_SingleDietdetails.this, mNativeframe,mAdcard);

        if (mDietlist.size() != 0){
            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(FitnessApplication.getInstance()).load(mDietlist.get(mPosition).getImage())
                    .apply(requestOptions).into(mDietimg);
            mDietname.setText(mDietlist.get(mPosition).getName());
            mDietdesc.setText(mDietlist.get(mPosition).getDescription());
            ImageView mClose = (ImageView) findViewById(R.id.btnclose);
            mClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });

            mBtnshare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogPageLoading();
                    new RetrieveFeedTask(mDietlist.get(mPosition).getImage()).execute();
                }
            });
            if (mPosition == 0) {
                mBtnprev.setVisibility(View.GONE);
            }
            if (mPosition == mDietlist.size() - 1) {
                mBtnnext.setVisibility(View.GONE);
            }
            mBtnprev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPosition--;
                    if (mPosition == 0) {
                        mBtnprev.setVisibility(View.GONE);
                    } else {
                        if (mPosition == 1) {
                            mBtnprev.setVisibility(View.GONE);
                        }else {
                            mBtnnext.setVisibility(View.VISIBLE);
                            RequestOptions requestOptions = new RequestOptions()
                                    .diskCacheStrategy(DiskCacheStrategy.ALL);
                            Glide.with(FitnessApplication.getInstance()).load(mDietlist.get(mPosition).getImage())
                                    .apply(requestOptions).into(mDietimg);
                            mDietname.setText(mDietlist.get(mPosition).getName());
                            mDietdesc.setText(mDietlist.get(mPosition).getDescription());
                        }
                    }
                }
            });
            mBtnnext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mPosition++;
                    if (mPosition == mDietlist.size() - 1) {
                        mBtnnext.setVisibility(View.GONE);
                    } else {
                        if (mPosition == mDietlist.size() - 2) {
                            mBtnnext.setVisibility(View.GONE);
                        }else {
                            mBtnprev.setVisibility(View.VISIBLE);
                            RequestOptions requestOptions = new RequestOptions()
                                    .diskCacheStrategy(DiskCacheStrategy.ALL);
                            Glide.with(FitnessApplication.getInstance()).load(mDietlist.get(mPosition).getImage())
                                    .apply(requestOptions).into(mDietimg);
                            mDietname.setText(mDietlist.get(mPosition).getName());
                            mDietdesc.setText(mDietlist.get(mPosition).getDescription());
                        }
                    }

                }
            });

        }else {
            onBackPressed();
        }
    }


    class RetrieveFeedTask extends AsyncTask<String, String, Bitmap> {
        String src;

        public RetrieveFeedTask(String src) {
            this.src = src;
        }
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(src);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.connect();
                InputStream input = connection.getInputStream();
                Bitmap myBitmaps = BitmapFactory.decodeStream(input);
                return myBitmaps;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            Intent shareIntent;
            if (dialog != null){
                dialog.dismiss();
            }
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)+"/Share.png";
            OutputStream out = null;
            File file=new File(path);
            try {
                out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Uri bmpUri = FileProvider.getUriForFile(Activity_SingleDietdetails.this, Activity_SingleDietdetails.this.getApplicationContext().getPackageName() + ".provider", file);
            shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
            StringBuilder sb = new StringBuilder();
            sb.append(mDietlist.get(mPosition).getName() + "\n");
            sb.append(Html.fromHtml(mDietlist.get(mPosition).getDescription()).toString());
            sb.append("\nHey check this application ");
            sb.append("https://play.google.com/store/apps/details?id=");
            sb.append(getPackageName());
            shareIntent.putExtra(Intent.EXTRA_TEXT,sb.toString());
            shareIntent.setType("image/png");
            startActivity(Intent.createChooser(shareIntent,"Share with"));
        }
    }

    public void DialogPageLoading() {
        try {
            dialog = new Dialog(Activity_SingleDietdetails.this);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setContentView(R.layout.dialog_adloading);
            LinearLayout mMainlay = dialog.findViewById(R.id.mainlay);
            mMainlay.setBackground(null);
            TextView temptext = dialog.findViewById(R.id.txttitle);
            LottieAnimationView mLottie1 = (LottieAnimationView) dialog.findViewById(R.id.lotti);
            mLottie1.setVisibility(View.GONE);
            LottieAnimationView mLottie = (LottieAnimationView) dialog.findViewById(R.id.lotti2);
            mLottie.setVisibility(View.VISIBLE);
            temptext.setText("Preparing Sharing Page...");
            mLottie.setAnimation("loadanimdial.json");
            mLottie.playAnimation();
            mLottie.loop(true);
            WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
            lp.copyFrom(dialog.getWindow().getAttributes());
            lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setAttributes(lp);
            dialog.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void showFBNativeAd(final Context context, final RelativeLayout nativeAdContainer, RelativeLayout mAdcard) {
        final NativeAd nativeAd = new NativeAd(context, Constants.facebook_native);
        nativeAd.setAdListener(new NativeAdListener() {

            @Override
            public void onError(Ad ad, AdError adError) {
            }

            @Override
            public void onAdLoaded(Ad ad) {
                mAdcard.setVisibility(View.VISIBLE);
                nativeAdContainer.setVisibility(View.VISIBLE);
//                Animation mzoom = AnimationUtils.loadAnimation(context, R.anim.adzoom_in);
//                mAdcard.startAnimation(mzoom);
                LayoutInflater inflater = LayoutInflater.from(context);
                LinearLayout adView = (LinearLayout) inflater.inflate(R.layout.fb_ad_unit, nativeAdContainer, false);

                if (nativeAdContainer != null) {
                    nativeAdContainer.removeAllViews();
                }
                nativeAdContainer.addView(adView);

                LinearLayout adChoicesContainer = adView.findViewById(R.id.ad_choices_container);
                AdChoicesView adChoicesView = new AdChoicesView(context, nativeAd, true);
                adChoicesContainer.addView(adChoicesView, 0);

                AdIconView nativeAdIcon = adView.findViewById(R.id.native_ad_icon);
                TextView nativeAdTitle = adView.findViewById(R.id.native_ad_title);
                MediaView nativeAdMedia = adView.findViewById(R.id.native_ad_media);
                TextView nativeAdSocialContext = adView.findViewById(R.id.native_ad_social_context);
                TextView nativeAdBody = adView.findViewById(R.id.native_ad_body);
                TextView sponsoredLabel = adView.findViewById(R.id.native_ad_sponsored_label);
                Button nativeAdCallToAction = adView.findViewById(R.id.native_ad_call_to_action);

                // Set the Text.
                nativeAdTitle.setText(nativeAd.getAdvertiserName());
                nativeAdBody.setText(nativeAd.getAdBodyText());
                nativeAdSocialContext.setText(nativeAd.getAdSocialContext());
                nativeAdCallToAction.setVisibility(nativeAd.hasCallToAction() ? View.VISIBLE : View.INVISIBLE);
                nativeAdCallToAction.setText(nativeAd.getAdCallToAction());
                sponsoredLabel.setText(nativeAd.getSponsoredTranslation());

                // Create a list of clickable views
                List<View> clickableViews = new ArrayList<>();
                clickableViews.add(nativeAdTitle);
                clickableViews.add(nativeAdCallToAction);

                // Register the Title and CTA button to listen for clicks.
                nativeAd.registerViewForInteraction(
                        adView,
                        nativeAdMedia,
                        nativeAdIcon,
                        clickableViews);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }

            @Override
            public void onMediaDownloaded(Ad ad) {

            }
        });
        nativeAd.loadAd();

    }


    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
