package drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.ads.Ad;
import com.facebook.ads.AdError;
import com.facebook.ads.NativeAdListener;
import com.facebook.ads.NativeAdViewAttributes;
import com.facebook.ads.NativeBannerAd;
import com.facebook.ads.NativeBannerAdView;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.makeramen.roundedimageview.RoundedImageView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Level1;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_MyTraining;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Purchase;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_webview;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Diet.CustomUpdateActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Diet.DefalutAndCustomplanActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.ApiInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.bannerClick;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.Activity_Covidhome;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Banner1;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static retrofit2.converter.scalars.ScalarsConverterFactory.create;

public class SliderAdapter extends
        SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private Context context;
    private int mCount;
    NativeBannerAd mNativeBannerAd;
    //  public ArrayList<BannerData.Datalist> mBannerdata = new ArrayList<>();
    private TinyDB tinydb;
    public ArrayList<Banner1.Doc> mBannerdata = new ArrayList<>();
    String BASEURL;
    private String musicurl;

    public SliderAdapter(Context context, ArrayList<Banner1.Doc> mBannerdata) {
        this.context = context;
        this.mBannerdata = mBannerdata;
        tinydb = new TinyDB(context);
    }

    public void setCount(int count) {
        this.mCount = count;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }


    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
        Banner1.Doc mData = mBannerdata.get(position);
        BASEURL=tinydb.getString(Constants.NewBaseUrl);

        Log.e("mData","Adss");
        musicurl = tinydb.getString(Constants.Backimageurl);
        try {
            if (mData.getDefaultType().equals("Ads")) {
                viewHolder.mLoadlayout.setVisibility(View.VISIBLE);
                viewHolder.mGbannerlay.setVisibility(View.VISIBLE);
                showGOOGLEAdvance(context, viewHolder.mGbannerlay, viewHolder.mLoadlayout);
            } else {
                viewHolder.mLoadlayout.setVisibility(View.GONE);
                viewHolder.mGbannerlay.setVisibility(View.GONE);
            }
        } catch (Exception e) {
        }
        Glide.with(FitnessApplication.getInstance())
                .load(musicurl+"banner/" + mData.getImage())
                .centerCrop()
                .into(viewHolder.imageViewBackground);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string = mData.getId();
                add_click(string);
                switch (mData.getDefaultType()) {
                    case "Default":
                        Defaultetype(mData.getDefaultType(), mData.getLink());
                        break;
                    case "Mobile":
                        Gotoplay(mData.getLink());
                        break;

                    case "Customizeplan":
                        Intent intentt = new Intent(context, Activity_MyTraining.class);
                        context.startActivity(intentt);
                    case "Web":
                        boolean diet = tinydb.getBoolean(Constants.ReadyDietPlan);
                      /*  if (diet) {
                            Intent intent1 = new Intent(context, DefalutAndCustomplanActivity.class);
                            intent1.putExtra("isFrom2", false);
                            context.startActivity(intent1);
                        } else {
                            Intent intent = new Intent(context, CustomUpdateActivity.class);
                            intent.putExtra("isFrom", false);
                            context.startActivity(intent);
                        }*/
                        String tempdate = tinydb.getString(Constants.DIETPLANDATE_KEY);
                        Date c = Calendar.getInstance().getTime();
                         Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
                        String formattedDate = df.format(c);
                        if (!tempdate.isEmpty() && tempdate.equals(formattedDate)) {
                            Intent intent1 = new Intent(context, DefalutAndCustomplanActivity.class);
                            intent1.putExtra("isFrom2", false);
                            context.startActivity(intent1);
                        } else {
                            Intent intent = new Intent(context, CustomUpdateActivity.class);
                            intent.putExtra("isFrom", false);
                            context.startActivity(intent);
                        }
                        break;
                    case "Webview":
                        Intent intent = new Intent(context, Activity_webview.class);
                        intent.putExtra("link", mData.getLink());
                        context.startActivity(intent);
                        break;
                    case "Blog":
                        Intent intent1 = new Intent(context, Activity_webview.class);
                        intent1.putExtra("link", mData.getLink());
                        context.startActivity(intent1);
                       /* Intent intent1 = new Intent(context, MainActivity.class);
                        //   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        Bundle data1 = new Bundle();
                        data1.putInt("discover",4);
                        intent1.putExtras(data1);
                        //    intent.putExtra("isFrom3", true);
                        context.startActivity(intent1);*/
                       /* Intent intent2 = new Intent(context, Activity_Blogdetails.class);
                        intent2.putExtra("bid", mData.getLink());
                        context.startActivity(intent2);*/
                        break;
                    case "Ads":
                    case "Premium":
                        Intent intent4 = new Intent(context, Activity_Purchase.class);
                        context.startActivity(intent4);
                        break;
                    /*case "Covid19":
                        Intent intent5 = new Intent(context, Activity_Covidhome.class);
                        context.startActivity(intent5);
                        break;*/
                }
            }
        });
    }


    public void Defaultetype(String mType, String mDatalink) {
        if (mType != null) {
            switch (mType) {
                case "Default":
                    Intent intent3 = new Intent(context, Activity_Level1.class);
                    context.startActivity(intent3);
                    ((MainActivity) context).finish();
                    break;
                case "Mobile":
                    Gotoplay(mDatalink);
                    break;
                case "Customizeplan":
                    Intent intentt = new Intent(context, Activity_MyTraining.class);
                    context.startActivity(intentt);
                case "Web":
                /*    boolean diet = tinydb.getBoolean(Constants.ReadyDietPlan);
                    if (diet) {
                        Intent intent1 = new Intent(context, DefalutAndCustomplanActivity.class);
                        intent1.putExtra("isFrom2", false);
                        context.startActivity(intent1);
                    } else {
                        Intent intent = new Intent(context, CustomUpdateActivity.class);
                        intent.putExtra("isFrom", false);
                        context.startActivity(intent);
                    }*/
                    String tempdate = tinydb.getString(Constants.DIETPLANDATE_KEY);
                    Date c = Calendar.getInstance().getTime();
                     Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
                    String formattedDate = df.format(c);
                    if (!tempdate.isEmpty() && tempdate.equals(formattedDate)) {
                        Intent intent1 = new Intent(context, DefalutAndCustomplanActivity.class);
                        intent1.putExtra("isFrom2", false);
                        context.startActivity(intent1);
                    } else {
                        Intent intent = new Intent(context, CustomUpdateActivity.class);
                        intent.putExtra("isFrom", false);
                        context.startActivity(intent);
                    }
                    break;
                case "Webview":
                    Intent intent = new Intent(context, Activity_webview.class);
                    intent.putExtra("link", mDatalink);
                    context.startActivity(intent);
                    break;
                case "Blog":

                    Intent intent1 = new Intent(context, Activity_webview.class);
                    intent1.putExtra("link", mDatalink);
                    context.startActivity(intent1);
                  /*  Intent intent1 = new Intent(context, MainActivity.class);
                    //   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    Bundle data1 = new Bundle();
                    data1.putInt("discover",4);
                    intent1.putExtras(data1);
                    //    intent.putExtra("isFrom3", true);
                    context.startActivity(intent1);*/
               /*     Intent intent2 = new Intent(context, Activity_Blogdetails.class);
                    intent2.putExtra("bid", mDatalink);
                    context.startActivity(intent2);*/
                    break;
                case "Ads":
                    Intent intent31 = new Intent(context, Activity_Purchase.class);
                    context.startActivity(intent31);
                    break;
                case "Premium":
                    Intent intent4 = new Intent(context, Activity_Purchase.class);
                    context.startActivity(intent4);
                    break;
                case "Covid19":
                    Intent intent5 = new Intent(context, Activity_Covidhome.class);
                    context.startActivity(intent5);
            }
        } else {
            Intent intent3 = new Intent(context, Activity_Level1.class);
            context.startActivity(intent3);
            ((MainActivity) context).finish();
        }
    }


    public void Gotoplay(String mPlaylink) {
        Uri uri1 = Uri.parse(mPlaylink);
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri1);
        try {
            context.startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "You don't have Google Play installed", Toast.LENGTH_LONG).show();
        }
    }


    public void showGOOGLEAdvance(final Context context, final LinearLayout frameLayout,ShimmerFrameLayout loadlayout) {
        AdLoader.Builder builder = new AdLoader.Builder(context, Constants.admob_nativead);
        builder.forContentAd(new NativeContentAd.OnContentAdLoadedListener() {
            public void onContentAdLoaded(NativeContentAd nativeContentAd) {
                loadlayout.setVisibility(View.GONE);
                frameLayout.setVisibility(View.VISIBLE);
                NativeContentAdView nativeContentAdView = (NativeContentAdView) ((Activity) context).getLayoutInflater().inflate(R.layout.ad_slidenative, null);
                populateContentAdView(nativeContentAd, nativeContentAdView);
                frameLayout.removeAllViews();
                frameLayout.addView(nativeContentAdView);
            }
        });
        builder.withNativeAdOptions(new NativeAdOptions.Builder().setVideoOptions(new VideoOptions.Builder().setStartMuted(false).build()).build());
        builder.withAdListener(new AdListener() {
            public void onAdFailedToLoad(int i) {
                loadlayout.setVisibility(View.GONE);
                Log.e("errorslide", "Failed to load native ad:: " + i);
            }
        }).build().loadAd(new AdRequest.Builder().build());
    }
    private void add_click(String string) {
        try {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASEURL)
                    .addConverterFactory(create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiInterface apiInterface = retrofit.create(ApiInterface.class);
            JSONObject paramObject = new JSONObject();
            paramObject.put("banner_id", string);


            apiInterface.getbannerClick(paramObject.toString()).enqueue(new Callback<bannerClick>() {
                @Override
                public void onResponse(@NotNull Call<bannerClick> call, @NotNull Response<bannerClick> response) {
                    try {

                        bannerClick relateds = response.body();
                        String s = relateds.getMessage();
                        //     Log.e("dfsfdsfs", s);


                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }


                @Override
                public void onFailure(@NotNull Call<bannerClick> call, @NotNull Throwable t) {
               /*     mLoadlay.setVisibility(View.GONE);
                    mLaynodata.setVisibility(View.VISIBLE);*/
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
          /*  mLoadlay.setVisibility(View.GONE);
            mLaynodata.setVisibility(View.VISIBLE);*/
            e.printStackTrace();
        }
    }
    @SuppressLint("WrongConstant")
    public static void populateContentAdView(NativeContentAd nativeContentAd, NativeContentAdView nativeContentAdView) {
        nativeContentAdView.setHeadlineView(nativeContentAdView.findViewById(R.id.ad_title_textview));
        nativeContentAdView.setImageView(nativeContentAdView.findViewById(R.id.contentad_image));
        nativeContentAdView.setBodyView(nativeContentAdView.findViewById(R.id.ad_describe_textview));
        nativeContentAdView.setCallToActionView(nativeContentAdView.findViewById(R.id.ad_action_button));
        nativeContentAdView.setLogoView(nativeContentAdView.findViewById(R.id.ad_icon_imageview));
       // ImageView mBackimg = nativeContentAdView.findViewById(R.id.adblurimg);
//        nativeContentAdView.setAdvertiserView(nativeContentAdView.findViewById(R.id.contentad_advertiser));
        ((TextView) nativeContentAdView.getHeadlineView()).setText(nativeContentAd.getHeadline());
        ((TextView) nativeContentAdView.getBodyView()).setText(nativeContentAd.getBody());
        ((TextView) nativeContentAdView.getCallToActionView()).setText(nativeContentAd.getCallToAction());
//        ((TextView) nativeContentAdView.getAdvertiserView()).setText(nativeContentAd.getAdvertiser());
        List images = nativeContentAd.getImages();
        if (images.size() > 0) {
            ((ImageView) nativeContentAdView.getImageView()).setImageDrawable(((com.google.android.gms.ads.formats.NativeAd.Image) images.get(0)).getDrawable());
          /*  try {
                mBackimg.setImageDrawable(((com.google.android.gms.ads.formats.NativeAd.Image) images.get(0)).getDrawable());
            } catch (Exception e) {
                e.printStackTrace();
            }*/

        }
        com.google.android.gms.ads.formats.NativeAd.Image logo = nativeContentAd.getLogo();
        if (logo == null) {
            nativeContentAdView.getLogoView().setVisibility(4);
        } else {
            ((ImageView) nativeContentAdView.getLogoView()).setImageDrawable(logo.getDrawable());
            nativeContentAdView.getLogoView().setVisibility(0);
        }
        nativeContentAdView.setNativeAd(nativeContentAd);
    }



    public void showBanner(Context context, final FrameLayout adMobView, int type) {
        final AdView mAdView = new AdView(context);
        mAdView.setAdSize(AdSize.SMART_BANNER);
        mAdView.setAdUnitId(Constants.admob_banner);
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (adMobView != null) {
                    adMobView.removeAllViews();
                }
                adMobView.addView(mAdView);
                adMobView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                FitnessApplication.AdfailToast("Banner Ad", String.valueOf(i));

            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        if (adMobView != null) {
            adMobView.removeAllViews();
        }
    }


    protected void createAndLoadNativeAd(final LinearLayout container) {
        mNativeBannerAd = new NativeBannerAd(context, Constants.facebooknative_banner);
        mNativeBannerAd.setAdListener(new NativeAdListener() {
            @Override
            public void onMediaDownloaded(Ad ad) {

            }

            @Override
            public void onError(Ad ad, AdError adError) {

            }

            @Override
            public void onAdLoaded(Ad ad) {
                if (mNativeBannerAd == null || mNativeBannerAd != ad) {
                    return;
                }
                reloadAdContainer(container);
            }

            @Override
            public void onAdClicked(Ad ad) {

            }

            @Override
            public void onLoggingImpression(Ad ad) {

            }
        });
        mNativeBannerAd.loadAd();

    }


    private void reloadAdContainer(LinearLayout mNativeAdContainer) {
        if (context != null && mNativeBannerAd != null && mNativeBannerAd.isAdLoaded()) {
            mNativeAdContainer.removeAllViews();

            NativeAdViewAttributes attributes = new NativeAdViewAttributes()
                    .setBackgroundColor(Color.WHITE)
                    .setTitleTextColor(0xff4e5665)
                    .setDescriptionTextColor(0xff90949c)
                    .setButtonBorderColor(0xff4080ff)
                    .setButtonTextColor(Color.WHITE)
                    .setButtonColor(0xff4080ff);

            View adView =
                    NativeBannerAdView.render(context, mNativeBannerAd, NativeBannerAdView.Type.HEIGHT_120, attributes);
            mNativeAdContainer.addView(adView, 0);
            mNativeAdContainer.setVisibility(View.VISIBLE);
            mNativeAdContainer.setBackgroundColor(Color.TRANSPARENT);

        }
    }

    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mBannerdata.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        RoundedImageView imageViewBackground;
        FrameLayout mGbannerad;
        LinearLayout mNativebanner,mGbannerlay;
        ShimmerFrameLayout mLoadlayout;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            mGbannerad = itemView.findViewById(R.id.adframe);
            mNativebanner = (LinearLayout) itemView.findViewById(R.id.templateContainer);
            mGbannerlay = itemView.findViewById(R.id.adframe2);
            mLoadlayout = itemView.findViewById(R.id.slideloader);
            this.itemView = itemView;
        }
    }


}

