package drzio.insomnia.treatment.bedtime.yoga.sleep.Activity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAd;
import com.facebook.ads.InterstitialAdListener;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;


public class Activity_webview extends AppCompatActivity {
    private WebView webview;
    private static final String TAG = "Main";
    //    private ProgressDialog progressBar;
    private String mLink;
    private TextView mTitle;
    private TinyDB tinydb;
    private RelativeLayout netlayout;
    private Dialog dialog;
    private InterstitialAd interstitialAd;
    private String mStringTitle = "Daily Tips";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        tinydb = new TinyDB(Activity_webview.this);

        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(this, languages);

        try {
            requestWindowFeature(Window.FEATURE_NO_TITLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_webview);
        Bundle bundle2 = getIntent().getExtras();
        /*if (bundle2!= null){
            mStringTitle = bundle2.getString("title");
            if (mStringTitle == null){
                mStringTitle = "Daily Tips";
            }
        }*/
        netlayout = (RelativeLayout) findViewById(R.id.neterror);
        this.webview = (WebView) findViewById(R.id.webview);
        netlayout.setVisibility(View.GONE);
        ImageView mBack = (ImageView) findViewById(R.id.ivBack);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        mTitle = (TextView) findViewById(R.id.txttitle);
       // mTitle.setText(mStringTitle);
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        webview.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
//        showFBInterstitial();

//        progressBar = ProgressDialog.show(Activity_webview.this, "Please Wait...", "Loading...");

        DialogPageLoading();
        Bundle bundle = getIntent().getExtras();
        webview.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.i(TAG, "Processing webview url click...");
                view.loadUrl(url);
                return true;
            }

            public void onPageFinished(WebView view, String url) {
                Log.i(TAG, "Finished loading URL: " + url);
//                if (progressBar.isShowing()) {
//                    progressBar.dismiss();
//                }
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                }
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                netlayout.setVisibility(View.VISIBLE);
            }
        });
        if (bundle != null) {
            mLink = bundle.getString("link");
        }
        if (mLink != null) {
            webview.loadUrl(mLink);
        }

    }

    public void DialogPageLoading() {
        try {
            dialog = new Dialog(Activity_webview.this);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.dialog_adloading);
            LinearLayout mMainlay = dialog.findViewById(R.id.mainlay);
            mMainlay.setBackground(null);
            TextView temptext = dialog.findViewById(R.id.txttitle);
            LottieAnimationView mLottie1 = (LottieAnimationView) dialog.findViewById(R.id.lotti);
            mLottie1.setVisibility(View.GONE);
            LottieAnimationView mLottie = (LottieAnimationView) dialog.findViewById(R.id.lotti2);
            mLottie.setVisibility(View.VISIBLE);
            temptext.setText("Loading...");
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

    public void showFBInterstitial() {

        interstitialAd = new InterstitialAd(Activity_webview.this, Constants.facebook_interstitial);

        interstitialAd.setAdListener(new InterstitialAdListener() {
            @Override
            public void onInterstitialDisplayed(Ad ad) {

            }

            @Override
            public void onInterstitialDismissed(Ad ad) {
                onBackPressed();
            }

            @Override
            public void onError(Ad ad, AdError adError) {
                FitnessApplication.AdfailToast("Activity_Blogdetails FB Interstatils", String.valueOf(adError.getErrorMessage()));

            }

            @Override
            public void onAdLoaded(Ad ad) {
            }

            @Override
            public void onAdClicked(Ad ad) {
            }

            @Override
            public void onLoggingImpression(Ad ad) {
            }
        });
        interstitialAd.loadAd();
    }


    @Override
    public void onBackPressed() {
//        if (interstitialAd != null && interstitialAd.isAdLoaded()) {
//            interstitialAd.show();
//        } else {
        super.onBackPressed();
        finish();
//        }
    }
}
