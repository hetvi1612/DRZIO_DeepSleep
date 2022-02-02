package drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity;

import android.annotation.SuppressLint;
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
import android.os.Build;
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
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import org.jetbrains.annotations.NotNull;
import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_webview;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.AppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.ChooseGenderActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.MaleAppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIClient;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.blogdatalist;

public class Activity_Blogdetails extends AppCompatActivity {
    private String mBlogid;
    public ArrayList<BlogData.Datalist> mBloglist = new ArrayList<>();

    private int mPosition = 1;
    private RelativeLayout mBtnprev;
    private RelativeLayout mBtnnext;
    private RelativeLayout mNativeframe;
    public int Tempfirstpos;
    private ImageView mMainimg;
    private TextView mTitletxt;
    private HtmlTextView mTxtdesc;
    public boolean isBtnclicked;
    private ImageView mBtnreadmore;
    private Bitmap myBitmap;
    private Dialog dialog;
    private InterstitialAd mInterstitialAdMob;
    private BackpainAPIInterface apiInterface;
    private RelativeLayout mLoadlay;
    private TinyDB tinyDB;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        tinyDB = new TinyDB(Activity_Blogdetails.this);

        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(this, languages);

        setContentView(R.layout.activity_blogdetails);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mBlogid = bundle.getString("bid");
        }
        mLoadlay = (RelativeLayout) findViewById(R.id.loadlayout);
        LottieAnimationView mLottie = (LottieAnimationView) findViewById(R.id.lotti2);
        mLottie.setVisibility(View.VISIBLE);
        mLottie.setAnimation("loadanimdial.json");
        mLottie.playAnimation();
        mLottie.loop(true);
        mLoadlay.setVisibility(View.GONE);
        TextView mBtnshare = (TextView) findViewById(R.id.btnshare);
        ImageView mBtnstore = (ImageView) findViewById(R.id.ivbtnstore);
        mBtnstore.setBackgroundResource(R.drawable.adsstoreanim);
        AnimationDrawable anim2 = (AnimationDrawable) mBtnstore.getBackground();
        anim2.start();
        initAdmobFullAd(this);
        loadAdmobAd();
        mBtnstore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cgender=tinyDB.getBoolean(Constants.Genderchoose);
                if (cgender){
                    if (tinyDB.getString(Constants.GENDER_KEY).equals(getResources().getString(R.string.female))) {
                        Intent intent = new Intent(Activity_Blogdetails.this, AppstoreActivity.class);
                        startActivity(intent);
                    }else{
                        Intent intent = new Intent(Activity_Blogdetails.this, MaleAppstoreActivity.class);
                        startActivity(intent);
                    }
                }else {
                    Intent intent = new Intent(Activity_Blogdetails.this, ChooseGenderActivity.class);
                    startActivity(intent);
                }
            }
        });
        try {
            mBloglist = blogdatalist;
            mMainimg = (ImageView) findViewById(R.id.mainimg);
            mTitletxt = (TextView) findViewById(R.id.txttilte);
            mNativeframe = (RelativeLayout) findViewById(R.id.fbnative);
            RelativeLayout adcard = (RelativeLayout) findViewById(R.id.adcard);
            showFBNativeAd(Activity_Blogdetails.this, mNativeframe, adcard);
            mBtnprev = (RelativeLayout) findViewById(R.id.prevexcer);
            mBtnnext = (RelativeLayout) findViewById(R.id.nextexcer);
            mTxtdesc = (HtmlTextView) findViewById(R.id.txtdesc);
            mBtnreadmore = (ImageView) findViewById(R.id.btn_readmore);
            if (mBloglist != null && mBloglist.size() != 0) {
                for (int i = 0; i < mBloglist.size(); i++) {
                    BlogData.Datalist mdata = mBloglist.get(i);
                    if (mdata.getId().equals(mBlogid)) {
                        mPosition = i;
                        Tempfirstpos = i;
                    }
                }
                loadblog();
            } else {
                mLoadlay.setVisibility(View.VISIBLE);
                callBlogApi();
            }


            ImageView mBack = (ImageView) findViewById(R.id.icback);
            mBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            mBtnshare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogPageLoading();
                    new RetrieveFeedTask(mBloglist.get(mPosition).getImage()).execute();
                }
            });
            if (mPosition == 0) {
                mBtnprev.setVisibility(View.GONE);
            }
            if (mPosition == mBloglist.size() - 1) {
                mBtnnext.setVisibility(View.GONE);
            }
            mBtnprev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isBtnclicked = true;
                    if (mPosition == 0) {
                        mBtnprev.setVisibility(View.GONE);
                    } else {
                        if (mPosition == 1) {
                            mBtnprev.setVisibility(View.GONE);
                        }
                        mBtnnext.setVisibility(View.VISIBLE);
                        mPosition--;
                        RequestOptions requestOptions = new RequestOptions()
                                .diskCacheStrategy(DiskCacheStrategy.ALL);
                        Glide.with(FitnessApplication.getInstance()).load(mBloglist.get(mPosition).getImage())
                                .apply(requestOptions).into(mMainimg);
                        mTitletxt.setText(mBloglist.get(mPosition).getName());
                        mTxtdesc.setHtml(mBloglist.get(mPosition).getDescription());
                    }
                }
            });
            mBtnnext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    isBtnclicked = true;
                    if (mPosition == mBloglist.size() - 1) {
                        mBtnnext.setVisibility(View.GONE);
                    } else {
                        if (mPosition == mBloglist.size() - 2) {
                            mBtnnext.setVisibility(View.GONE);
                        }
                        mBtnprev.setVisibility(View.VISIBLE);
                        mPosition++;
                        RequestOptions requestOptions = new RequestOptions()
                                .diskCacheStrategy(DiskCacheStrategy.ALL);
                        Glide.with(FitnessApplication.getInstance()).load(mBloglist.get(mPosition).getImage())
                                .apply(requestOptions).into(mMainimg);
                        mTitletxt.setText(mBloglist.get(mPosition).getName());
                        mTxtdesc.setHtml(mBloglist.get(mPosition).getDescription());
                    }

                }
            });
            mBtnreadmore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Activity_Blogdetails.this, Activity_webview.class);
                    intent.putExtra("link", mBloglist.get(mPosition).getBloglink());
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadblog() {
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(FitnessApplication.getInstance()).load(mBloglist.get(mPosition).getImage())
                .apply(requestOptions).into(mMainimg);
        mTitletxt.setText(mBloglist.get(mPosition).getName());
        mTxtdesc.setHtml(mBloglist.get(mPosition).getDescription());
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
            if (dialog != null) {
                dialog.dismiss();
            }
            String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/Share.png";
            OutputStream out = null;
            File file = new File(path);
            try {
                out = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                out.flush();
                out.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Uri bmpUri = FileProvider.getUriForFile(Activity_Blogdetails.this, Activity_Blogdetails.this.getApplicationContext().getPackageName() + ".provider", file);
            shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
            StringBuilder sb = new StringBuilder();
            sb.append(mBloglist.get(mPosition).getName() + "\n");
            sb.append(Html.fromHtml(mBloglist.get(mPosition).getDescription()).toString());
            sb.append("\nHey check this application ");
            sb.append("https://play.google.com/store/apps/details?id=");
            sb.append(getPackageName());
            shareIntent.putExtra(Intent.EXTRA_TEXT, sb.toString());
            shareIntent.setType("image/png");
            startActivity(Intent.createChooser(shareIntent, "Share with"));
        }
    }

    public void DialogPageLoading() {
        try {
            dialog = new Dialog(Activity_Blogdetails.this);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void showFBNativeAd(final Context context, final RelativeLayout nativeAdContainer, RelativeLayout mAdcard) {
        final NativeAd nativeAd = new NativeAd(context, Constants.facebook_native);
        nativeAd.setAdListener(new NativeAdListener() {

            @Override
            public void onError(Ad ad, AdError adError) {
            }

            @Override
            public void onAdLoaded(Ad ad) {
                mAdcard.setVisibility(View.VISIBLE);
                nativeAdContainer.setVisibility(View.VISIBLE);
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


    @SuppressLint("WrongConstant")
    public void callBlogApi() {
        try {
            this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient().create(BackpainAPIInterface.class);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("blog_id", "")
                    .build();
            this.apiInterface.getBlogs(requestBody).enqueue(new Callback<BlogData>() {
                @Override
                public void onResponse(@NotNull Call<BlogData> call, @NotNull Response<BlogData> response) {
                    try {

                        mLoadlay.setVisibility(View.GONE);
                        BlogData blogData = (BlogData) response.body();
                        ArrayList<BlogData.Datalist> datalists = blogData.datalists;
                        blogdatalist = datalists;
                        mBloglist = blogdatalist;
                        loadblog();


                    } catch (Exception e) {

                    }
                }
                @Override
                public void onFailure(@NotNull Call<BlogData> call, @NotNull Throwable t) {
                    onBackPressed();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mLoadlay.setVisibility(View.GONE);
        }
    }


    public void initAdmobFullAd(Context context) {
        if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
            return;
        }
        mInterstitialAdMob = new InterstitialAd(context);
        mInterstitialAdMob.setAdUnitId(Constants.admob_Interstitial);
        mInterstitialAdMob.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                Intent intent = new Intent(Activity_Blogdetails.this, Activity_Bloglists.class);
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

    public void loadAdmobAd() {
        if (mInterstitialAdMob != null && !mInterstitialAdMob.isLoaded()) {
            mInterstitialAdMob.loadAd(new AdRequest.Builder().build());
        }
    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAdMob != null && mInterstitialAdMob.isLoaded()) {
            mInterstitialAdMob.show();
        } else {
            Intent intent = new Intent(Activity_Blogdetails.this, Activity_Bloglists.class);
            startActivity(intent);
            finish();
        }


    }
}
