package drzio.insomnia.treatment.bedtime.yoga.sleep.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.VideoOptions;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeContentAd;
import com.google.android.gms.ads.formats.NativeContentAdView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.shawnlin.numberpicker.NumberPicker;

import org.json.JSONException;
import org.json.JSONObject;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.Userupdate_Api;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIClient;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_MyProfile extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    private int mAgevalue;
    private TextView mTxtage, mTxtgender, mTxtheight, mTxtweight, mTxtmeal;
    TinyDB mTinydb;
    private String mGender;
    private int mealtype;
    String usertype = "veg";
    private boolean isbtncm;
    private boolean isbtninch;
    private int mHcmvalue;
    private int mHftvalue;
    private int mHinchvalue;
    private boolean isBtnkg;
    private boolean isBtnLb;
    private int mWkgvalue;
    private int mWlbvalue;
    private TextView mSwich2txt;
    private TextView pageSelected;

    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInAccount account;

    private String Gusername;
    private String Gmail;
    private String Guserid;
    private String Gprofilepicurl;
    private String GFirstname;
    private String GLastname;
    private BackpainAPIInterface apiInterface;
    private String langauge;
    private String token;

    String mHeight;
    String mHeightP;
    String mWeight;
    String mWeightP;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_profiledetails);
        mTxtage = (TextView) findViewById(R.id.txtage);
        mTxtgender = (TextView) findViewById(R.id.txtgender);
        mTxtheight = (TextView) findViewById(R.id.txtheight);
        mTxtweight = (TextView) findViewById(R.id.txtweight);
        mTxtmeal = (TextView) findViewById(R.id.txtmeal);
        mTinydb = new TinyDB(Activity_MyProfile.this);
        String languages = mTinydb.getString(Constants.Language);
        Constants.languagechange(this, languages);
        ImageView mBtnback = (ImageView) findViewById(R.id.btnback);

        token = mTinydb.getString(Constants.Authtoken);
        mBtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        LinearLayout mGbannerlay = findViewById(R.id.adframe);
        mGbannerlay.setVisibility(View.GONE);
        Smallnative(this, mGbannerlay);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        mGender = mTinydb.getString(Constants.GENDER_KEY);
        Log.e("mGender", mGender + "gen");

        //  mealtype = mTinydb.getInt(Constants.MEALTYPE_KEY);
        usertype = mTinydb.getString(Constants.USERTYPEKEY);
        mAgevalue = mTinydb.getInt(Constants.AGE_KEY);
        if (mTinydb.getBoolean(Constants.ISCM_KEY)) {
            isbtncm = true;
            isbtninch = false;
            mHcmvalue = (int) mTinydb.getFloat(Constants.HEIGHT_KEY);
        } else {
            isbtncm = false;
            isbtninch = true;
            float mData = mTinydb.getFloat(Constants.HEIGHT_KEY);
            String doubleAsString = String.valueOf(mData);
            int indexOfDecimal = doubleAsString.indexOf(".");
            mHftvalue = Integer.parseInt(doubleAsString.substring(0, indexOfDecimal));
            String mTrim = doubleAsString.substring(indexOfDecimal).replace(".", "");
            mHinchvalue = Integer.parseInt(mTrim);
        }
        if (mTinydb.getBoolean(Constants.ISKG_KEY)) {
            isBtnkg = true;
            isBtnLb = false;
            mWkgvalue = mTinydb.getInt(Constants.WEIGHT_KEY);
        } else {
            isBtnLb = true;
            isBtnkg = false;
            mWlbvalue = mTinydb.getInt(Constants.WEIGHT_KEY);
        }
        RelativeLayout mBtage = (RelativeLayout) findViewById(R.id.btnage);
        RelativeLayout mBtgender = (RelativeLayout) findViewById(R.id.btngender);
        RelativeLayout mBtheight = (RelativeLayout) findViewById(R.id.btnheight);
        RelativeLayout mBtweight = (RelativeLayout) findViewById(R.id.btnweight);
        RelativeLayout mBtmeal = (RelativeLayout) findViewById(R.id.btnmeal);
        mTxtage.setText(String.valueOf(mTinydb.getInt(Constants.AGE_KEY)));
        mTxtgender.setText(mTinydb.getString(Constants.GENDER_KEY));
        if (mTinydb.getBoolean(Constants.ISCM_KEY)) {
            isbtncm = true;
            isbtninch = false;
            mTxtheight.setText(mTinydb.getFloat(Constants.HEIGHT_KEY) + " cm");
        } else {
            isbtncm = false;
            isbtninch = true;
            mTxtheight.setText(mTinydb.getFloat(Constants.HEIGHT_KEY) + " ft");
        }
        if (mTinydb.getBoolean(Constants.ISKG_KEY)) {
            mTxtweight.setText(mTinydb.getInt(Constants.WEIGHT_KEY) + " kg");
        } else {
            mTxtweight.setText(mTinydb.getInt(Constants.WEIGHT_KEY) + " lb");
        }
        // int mMeal = mTinydb.getInt(Constants.MEALTYPE_KEY);
        String user_typre = mTinydb.getString(Constants.USERTYPEKEY);
        if (user_typre.equals("veg")) {
            mTxtmeal.setText("Veg");
        } else if (user_typre.equals("nonveg")) {
            mTxtmeal.setText("Non-Veg");
        } else if (user_typre.equals("vegan")) {
            mTxtmeal.setText("Vegan");
        }


        mBtage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogAgechange();
            }
        });
        mBtgender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogGenderchange();
            }
        });
        mBtheight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogHeightchange();
            }
        });
        mBtweight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogWeightchange();
            }
        });
        mBtmeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogMealchange();
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


    public void DialogAgechange() {
        final Dialog dialog = new Dialog(Activity_MyProfile.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_agechang);
        TextView btnclose = (TextView) dialog.findViewById(R.id.btnclose);
        NumberPicker mAgepicker = (NumberPicker) dialog.findViewById(R.id.agepicker);
        mAgepicker.setValue(mAgevalue);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mAgevalue != 0) {
                    mTinydb.putInt(Constants.AGE_KEY, mAgevalue);
                    mTxtage.setText(String.valueOf(mAgevalue));
                }
                updatedProfile("age", String.valueOf(mAgevalue));

                dialog.dismiss();
            }
        });
        mAgepicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mAgevalue = newVal;
            }
        });
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    public void DialogGenderchange() {
        final Dialog dialog = new Dialog(Activity_MyProfile.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_genderchang);
        LinearLayout mBtnfemale = (LinearLayout) dialog.findViewById(R.id.btn_female);
        LinearLayout mBtnmale = (LinearLayout) dialog.findViewById(R.id.btn_male);
        ImageView mImgfemale = (ImageView) dialog.findViewById(R.id.img_femele);
        ImageView mImgmale = (ImageView) dialog.findViewById(R.id.img_male);
        TextView mTxtfemale = (TextView) dialog.findViewById(R.id.txt_female);
        TextView mTxtmale = (TextView) dialog.findViewById(R.id.txt_male);
        TextView btnclose = (TextView) dialog.findViewById(R.id.btnclose);

        if (mGender.equals(getResources().getString(R.string.female))) {
            mImgmale.setImageResource(R.drawable.maleunselectedbtn);
            mTxtmale.setTextColor(getResources().getColor(R.color.smalltxtcolor));
            mImgfemale.setImageResource(R.drawable.femaleselectedbtn);
            mTxtfemale.setTextColor(getResources().getColor(R.color.tbtncolor));
        } else {
            mImgmale.setImageResource(R.drawable.maleselectedbtn);
            mTxtmale.setTextColor(getResources().getColor(R.color.tbtncolor));
            mImgfemale.setImageResource(R.drawable.femaleunselectedbtn);
            mTxtfemale.setTextColor(getResources().getColor(R.color.smalltxtcolor));
        }
        mBtnfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImgmale.setImageResource(R.drawable.maleunselectedbtn);
                mTxtmale.setTextColor(getResources().getColor(R.color.smalltxtcolor));
                mImgfemale.setImageResource(R.drawable.femaleselectedbtn);
                mTxtfemale.setTextColor(getResources().getColor(R.color.tbtncolor));
                mGender = String.valueOf(mTxtfemale.getText());
            }
        });
        mBtnmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImgmale.setImageResource(R.drawable.maleselectedbtn);
                mTxtmale.setTextColor(getResources().getColor(R.color.tbtncolor));
                mImgfemale.setImageResource(R.drawable.femaleunselectedbtn);
                mTxtfemale.setTextColor(getResources().getColor(R.color.smalltxtcolor));
                mGender = String.valueOf(mTxtmale.getText());
            }
        });

        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTinydb.putString(Constants.GENDER_KEY, mGender);
                mTxtgender.setText(mGender);
                updatedProfile("gender", mGender);
                dialog.dismiss();
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);

    }

    public void DialogHeightchange() {
        final Dialog dialog = new Dialog(Activity_MyProfile.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_heightchang);
        TextView btnclose = (TextView) dialog.findViewById(R.id.btnclose);
        NumberPicker mCmpicker = (NumberPicker) dialog.findViewById(R.id.cmpicker);
        NumberPicker mFtpicker = (NumberPicker) dialog.findViewById(R.id.ftpicker);
        NumberPicker mInchpicker = (NumberPicker) dialog.findViewById(R.id.inchpicker);
        LinearLayout mSwitchcminch = (LinearLayout) dialog.findViewById(R.id.switchcminch);
        final TextView mBtcm = (TextView) mSwitchcminch.findViewById(R.id.btcm);
        final TextView mBtinch = (TextView) mSwitchcminch.findViewById(R.id.btinch);
        LinearLayout mCmlayout = (LinearLayout) dialog.findViewById(R.id.cmlayout);
        LinearLayout mInchlayout = (LinearLayout) dialog.findViewById(R.id.inchlayout);

        mCmpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mHcmvalue = newVal;
            }
        });


        mFtpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mHftvalue = newVal;
            }
        });


        mInchpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mHinchvalue = newVal;
            }
        });

        if (isbtncm) {
            mSwitchcminchprevious();
            mSwitchcminchselected(mBtcm);
            mCmlayout.setVisibility(View.VISIBLE);
            mInchlayout.setVisibility(View.GONE);
            mCmpicker.setValue(mHcmvalue);
        } else {
            mSwitchcminchprevious();
            mSwitchcminchselected(mBtinch);
            mCmlayout.setVisibility(View.GONE);
            mInchlayout.setVisibility(View.VISIBLE);
            mFtpicker.setValue(mHftvalue);
            mInchpicker.setValue(mHinchvalue);
        }

        mBtcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isbtninch = false;
                isbtncm = true;
                mSwitchcminchprevious();
                mSwitchcminchselected(mBtcm);
                mCmlayout.setVisibility(View.VISIBLE);
                mInchlayout.setVisibility(View.GONE);
            }
        });

        mBtinch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isbtncm = false;
                isbtninch = true;
                mSwitchcminchprevious();
                mSwitchcminchselected(mBtinch);
                mCmlayout.setVisibility(View.GONE);
                mInchlayout.setVisibility(View.VISIBLE);
            }
        });


        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isbtncm && mHcmvalue != 0) {
                    mTinydb.putBoolean(Constants.ISCM_KEY, true);
                    mTinydb.putFloat(Constants.HEIGHT_KEY, mHcmvalue);
                    mTxtheight.setText(mHcmvalue + " cm");
                    updatedProfile("height", String.valueOf(mHcmvalue));
                } else if (isbtninch && mHftvalue != 0) {
                    mTinydb.putBoolean(Constants.ISCM_KEY, false);
                    String temp = mHftvalue + "." + mHinchvalue;
                    mTinydb.putFloat(Constants.HEIGHT_KEY, Float.parseFloat(temp));
                    mTxtheight.setText(temp + " ft");
                    updatedProfile("height", temp);
                }


                dialog.dismiss();
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    private void mSwitchcminchprevious() {
        if (mSwich2txt != null) {
            mSwich2txt.setBackground(null);
            mSwich2txt.setTextColor(Color.BLACK);
            mSwich2txt = null;
        }
    }

    private void mSwitchcminchselected(TextView selected) {
        mSwich2txt = selected;
        mSwich2txt.setBackground(getResources().getDrawable(R.drawable.switchselectedbg));
        mSwich2txt.setTextColor(Color.WHITE);
    }

    public void DialogWeightchange() {
        final Dialog dialog = new Dialog(Activity_MyProfile.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_weightchang);
        TextView btnclose = (TextView) dialog.findViewById(R.id.btnclose);
        NumberPicker mKgpicker = (NumberPicker) dialog.findViewById(R.id.kgpicker);
        NumberPicker mLbspicker = (NumberPicker) dialog.findViewById(R.id.lbsbpicker);
        LinearLayout mLaykglb = (LinearLayout) dialog.findViewById(R.id.laykglb);
        final TextView mBtkg = (TextView) mLaykglb.findViewById(R.id.btkgs);
        final TextView mBtlbs = (TextView) mLaykglb.findViewById(R.id.btlbs);
        LinearLayout mKgslayout = (LinearLayout) dialog.findViewById(R.id.laykgs);
        LinearLayout mLbsslayout = (LinearLayout) dialog.findViewById(R.id.laylbs);
        mKgpicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mWkgvalue = newVal;
            }
        });

        mLbspicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                mWlbvalue = newVal;
            }
        });
        if (isBtnkg) {
            unboxPreviousSelectedPageTab();
            boxNewSelectedPageTab(mBtkg);
            mKgslayout.setVisibility(View.VISIBLE);
            mLbsslayout.setVisibility(View.GONE);
            mKgpicker.setValue(mWkgvalue);
        } else {
            unboxPreviousSelectedPageTab();
            boxNewSelectedPageTab(mBtlbs);
            mKgslayout.setVisibility(View.GONE);
            mLbsslayout.setVisibility(View.VISIBLE);
            mLbspicker.setValue(mWlbvalue);
        }
        mBtkg.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View v) {
                isBtnLb = false;
                isBtnkg = true;
                unboxPreviousSelectedPageTab();
                boxNewSelectedPageTab(mBtkg);
                mKgslayout.setVisibility(View.VISIBLE);
                mLbsslayout.setVisibility(View.GONE);
            }
        });

        mBtlbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isBtnkg = false;
                isBtnLb = true;
                unboxPreviousSelectedPageTab();
                boxNewSelectedPageTab(mBtlbs);
                mKgslayout.setVisibility(View.GONE);
                mLbsslayout.setVisibility(View.VISIBLE);
            }
        });

        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBtnkg && mWkgvalue != 0) {
                    mTinydb.putBoolean(Constants.ISKG_KEY, true);
                    mTinydb.putInt(Constants.WEIGHT_KEY, mWkgvalue);
                    mTxtweight.setText(mWkgvalue + " kg");
                    updatedProfile("weight", String.valueOf(mWkgvalue));
                } else if (isBtnLb && mWlbvalue != 0) {
                    mTinydb.putBoolean(Constants.ISKG_KEY, false);
                    mTinydb.putInt(Constants.WEIGHT_KEY, mWlbvalue);
                    mTxtweight.setText(mWlbvalue + " lb");
                    updatedProfile("weight", String.valueOf(mWlbvalue));
                }
                dialog.dismiss();
            }
        });

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }

    private void unboxPreviousSelectedPageTab() {
        if (pageSelected != null) {
            pageSelected.setBackground(null);
            pageSelected.setTextColor(Color.BLACK);
            pageSelected = null;
        }
    }

    private void boxNewSelectedPageTab(TextView selected) {
        pageSelected = selected;
        pageSelected.setBackground(getResources().getDrawable(R.drawable.switchselectedbg));
        pageSelected.setTextColor(Color.WHITE);
    }

    public void DialogMealchange() {
        final Dialog dialog = new Dialog(Activity_MyProfile.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_mealtypechang);
        LinearLayout mBtnveg = (LinearLayout) dialog.findViewById(R.id.btn_veg);
        LinearLayout mBtnnonveg = (LinearLayout) dialog.findViewById(R.id.btn_nonveg);
        LinearLayout mBtnvegan = (LinearLayout) dialog.findViewById(R.id.btn_vegan);
        ImageView mImgveg = (ImageView) dialog.findViewById(R.id.img_veg);
        ImageView mImgnonveg = (ImageView) dialog.findViewById(R.id.img_nonveg);
        ImageView mImgvegan = (ImageView) dialog.findViewById(R.id.img_vegan);
        TextView mTxtveg = (TextView) dialog.findViewById(R.id.txt_veg);
        TextView mTxtnonveg = (TextView) dialog.findViewById(R.id.txt_nonveg);
        TextView mTxtvegan = (TextView) dialog.findViewById(R.id.txt_vegan);
        TextView btnclose = (TextView) dialog.findViewById(R.id.btnclose);
        if (usertype.equals("vegan")) {
            mImgveg.setImageResource(R.drawable.vegunselect);
            mTxtveg.setTextColor(Color.BLACK);
            mImgnonveg.setImageResource(R.drawable.nonvegunselect);
            mTxtnonveg.setTextColor(Color.BLACK);
            mImgvegan.setImageResource(R.drawable.veganselect);
            mTxtvegan.setTextColor(getResources().getColor(R.color.scolor2));


        } else if (usertype.equals("nonveg")) {
            mImgveg.setImageResource(R.drawable.vegunselect);
            mTxtveg.setTextColor(Color.BLACK);
            mImgnonveg.setImageResource(R.drawable.nonvegselect);

            mTxtnonveg.setTextColor(getResources().getColor(R.color.scolor2));
            mImgvegan.setImageResource(R.drawable.veganunselect);
            mTxtvegan.setTextColor(Color.BLACK);
        }else{
            mImgveg.setImageResource(R.drawable.vegselect);
            mTxtveg.setTextColor(getResources().getColor(R.color.scolor2));
            mImgnonveg.setImageResource(R.drawable.nonvegunselect);
            mTxtnonveg.setTextColor(Color.BLACK);
            mImgvegan.setImageResource(R.drawable.veganunselect);
            mTxtvegan.setTextColor(Color.BLACK);

        }
        mBtnveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImgveg.setImageResource(R.drawable.vegselect);
                mTxtveg.setTextColor(getResources().getColor(R.color.scolor2));
                mImgnonveg.setImageResource(R.drawable.nonvegunselect);
                mTxtnonveg.setTextColor(Color.BLACK);
                mImgvegan.setImageResource(R.drawable.veganunselect);
                mTxtvegan.setTextColor(Color.BLACK);
                mealtype = 1;
                usertype = "veg";

            }
        });
        mBtnnonveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImgveg.setImageResource(R.drawable.vegunselect);
                mTxtveg.setTextColor(Color.BLACK);
                mImgnonveg.setImageResource(R.drawable.nonvegselect);

                mTxtnonveg.setTextColor(getResources().getColor(R.color.scolor2));
                mImgvegan.setImageResource(R.drawable.veganunselect);
                mTxtvegan.setTextColor(Color.BLACK);
                mealtype = 2;
                usertype = "nonveg";
            }
        });
        mBtnvegan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImgveg.setImageResource(R.drawable.vegunselect);
                mTxtveg.setTextColor(Color.BLACK);
                mImgnonveg.setImageResource(R.drawable.nonvegunselect);
                mTxtnonveg.setTextColor(Color.BLACK);
                mImgvegan.setImageResource(R.drawable.veganselect);
                mTxtvegan.setTextColor(getResources().getColor(R.color.scolor2));
                mealtype = 3;
                usertype = "vegan";
            }
        });

        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTinydb.putInt(Constants.MEALTYPE_KEY, mealtype);
                mTinydb.putString(Constants.USERTYPEKEY, usertype);
                if (mealtype == 1) {
                    mTxtmeal.setText("Veg");
                } else if (mealtype == 2) {
                    mTxtmeal.setText("Non-Veg");
                } else if (mealtype == 3) {
                    mTxtmeal.setText("Vegan");
                }
                updatedProfile("meal", String.valueOf(usertype));
                dialog.dismiss();
            }
        });
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }


    @Override
    protected void onStart() {
        super.onStart();
        if (mTinydb.getBoolean(Constants.ISLOGIN_KEY)) {
            OptionalPendingResult<GoogleSignInResult> optPenRes = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
            if (optPenRes.isDone()) {
                Log.e("Login_Result", "Yayy!");
                GoogleSignInResult result = optPenRes.get();
                handleSignInResult(result);
            } else {
                optPenRes.setResultCallback(new ResultCallback<GoogleSignInResult>() {
                    @Override
                    public void onResult(GoogleSignInResult googleSignInResult) {
                        handleSignInResult(googleSignInResult);
                    }
                });
            }
        }

    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.e("Login_Result", "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            account = result.getSignInAccount();
            Gusername = account.getDisplayName();
            Gmail = account.getEmail();
            Guserid = account.getId();
            Gprofilepicurl = String.valueOf(account.getPhotoUrl());
            GFirstname = account.getGivenName();
            GLastname = account.getFamilyName();
        }

    }

    public void updatedProfile(String type, String value) {
        String user_id = mTinydb.getString(Constants.USERID_KEY);
        String first_name = GFirstname;
        String last_name = GLastname;
        String email = Gmail;
        String mobile_number = "0";
        String image = Gprofilepicurl;
        String gender;
        if (type.equals("gender")) {
            gender = value;
        } else {
            gender = mTinydb.getString(Constants.GENDER_KEY);
        }
        String age;
        if (type.equals("age")) {
            age = value;
        } else {
            age = String.valueOf(mAgevalue);
        }

        if (type.equals("height")) {
            if (mTinydb.getBoolean(Constants.ISCM_KEY)) {
                mHeight = value /*+ " cm"*/;
                mHeightP = "cm";
            } else {
                mHeight = value/* + " ft"*/;
                mHeightP = "ft";
            }

        } else {
            if (mTinydb.getBoolean(Constants.ISCM_KEY)) {
                mHeight = String.valueOf(mTinydb.getFloat(Constants.HEIGHT_KEY)) /*+ " cm"*/;
                mHeightP = "cm";
            } else {
                mHeight = String.valueOf(mTinydb.getFloat(Constants.HEIGHT_KEY)) /*+ " ft"*/;
                mHeightP = "ft";

            }
        }
        String height = mHeight;


        if (type.equals("weight")) {
            if (mTinydb.getBoolean(Constants.ISKG_KEY)) {
                mWeight = value/* + " kg"*/;
                mWeightP = "kg";
            } else {
                mWeight = value /*+ " lb"*/;
                mWeightP = "lb";
            }
        } else {
            if (mTinydb.getBoolean(Constants.ISKG_KEY)) {
                mWeight = String.valueOf(mTinydb.getInt(Constants.WEIGHT_KEY)) /*+ " kg"*/;
                mWeightP = "kg";
            } else {
                mWeight = String.valueOf(mTinydb.getInt(Constants.WEIGHT_KEY))/* + " lb"*/;
                mWeightP = "lb";
            }

        }




           /* if (type.equals("height")) {
                if (mTinydb.getBoolean(Constants.ISCM_KEY)) {
                    mHeight = value + " cm";
                } else {
                    mHeight = value + " ft";
                }
            } else {
                if (mTinydb.getBoolean(Constants.ISCM_KEY)) {
                    mHeight = mTinydb.getFloat(Constants.HEIGHT_KEY) + " cm";
                } else {
                    mHeight = mTinydb.getFloat(Constants.HEIGHT_KEY) + " ft";
                }
            }
            String height = mHeight;
            String mWeight;
            if (type.equals("weight")) {
                if (mTinydb.getBoolean(Constants.ISKG_KEY)) {
                    mWeight = value + " kg";
                } else {
                    mWeight = value + " lb";
                }
            } else {
                if (mTinydb.getBoolean(Constants.ISKG_KEY)) {
                    mWeight = mTinydb.getInt(Constants.WEIGHT_KEY) + " kg";
                } else {
                    mWeight = mTinydb.getInt(Constants.WEIGHT_KEY) + " lb";
                }
            }*/

        String weight = mWeight;
        String paid_status = "0";
        String google = Guserid;
        String facebook = "0";
        String user_type;
        if (type.equals("meal")) {
            user_type = value;
        } else {
            user_type = String.valueOf(mTinydb.getString(Constants.USERTYPEKEY));
        }
        String countryname = mTinydb.getString(Constants.COUNTRYID_KEY);
        String statename = mTinydb.getString(Constants.STATEID_KEY);
        String cityname = mTinydb.getString(Constants.CITYID_KEY);
        String fcm_token = mTinydb.getString(Constants.FCMTOKEN_KEY);
        if (mTinydb.getBoolean(Constants.ISLOGIN_KEY)) {
                /*callUpdateApi(user_id, first_name, last_name, email,
                        mobile_number, image, gender, age, height, weight,
                        paid_status, google, facebook, user_type, countryname,
                        statename, cityname, fcm_token);*/


            callUpdateApi(first_name, last_name, email, gender,
                    age, height, weight, countryname,
                    statename, cityname, fcm_token, google, langauge, mHeightP, mWeightP, user_type);


        }
    }

    private void callUpdateApi(String first_name, String last_name, String email, String gender, String age, String height, String weight, String countryname,
                               String statename, String cityname, String fcm_token, String google, String langauge, String mHeightP, String mWeightP, String user_type) {

        try {
            Log.e("heightP", mHeightP);
            Log.e("weightP", mWeightP);
            JSONObject paramObject = new JSONObject();
             String androidId = 22+Settings.Secure.getString(getContentResolver(),
                        Settings.Secure.ANDROID_ID)+22;
            paramObject.put("f_name", first_name);
            paramObject.put("l_name", last_name);
            paramObject.put("gender", gender);
            paramObject.put("age", age);
            paramObject.put("height", height);
            paramObject.put("weight", weight);
            paramObject.put("device_id", androidId);
            paramObject.put("heightP", mHeightP);
            paramObject.put("weightP", mWeightP);
            paramObject.put("user_type", user_type);

            this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient1().create(BackpainAPIInterface.class);

            Call<Userupdate_Api> userCall = apiInterface.getUserupdate(paramObject.toString(), "Bearer " + token);
            userCall.enqueue(new Callback<Userupdate_Api>() {
                @Override
                public void onResponse(Call<Userupdate_Api> call, Response<Userupdate_Api> response) {
                    try {
                        Userupdate_Api userupdate_api = response.body();

                        Log.e("status", String.valueOf(userupdate_api.getStatus()));


                    } catch (Exception e) {
                        Log.e("status1q23", String.valueOf(e));
                    }
                }

                @Override
                public void onFailure(Call<Userupdate_Api> call, Throwable t) {
                    Log.e("status", String.valueOf(t));
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

/*
        @SuppressLint("WrongConstant")
        public void callUpdateApi (String userid, String first_name, String last_name, String email,
                String mobile_number, String image, String gender, String age,
                String height, String weight, String paid_status, String google,
                String facebook, String user_type, String countryname,
                String statename, String cityname, String fcm_token){
            try {
                this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient().create(BackpainAPIInterface.class);
                RequestBody requestBody = new MultipartBody.Builder()
                        .setType(MultipartBody.FORM)
                        .addFormDataPart("usr_id", userid)
                        .addFormDataPart("first_name", first_name)
                        .addFormDataPart("last_name", last_name)
                        .addFormDataPart("email", email)
                        .addFormDataPart("mobile_number", mobile_number)
                        .addFormDataPart("gender", gender)
                        .addFormDataPart("age", age)
                        .addFormDataPart("height", height)
                        .addFormDataPart("weight", weight)
                        .addFormDataPart("user_type_id", user_type)
                        .addFormDataPart("paid_status", paid_status)
                        .addFormDataPart("country_id", countryname)
                        .addFormDataPart("state_id", statename)
                        .addFormDataPart("city_id", cityname)
                        .addFormDataPart("fcm_token", fcm_token)
                        .addFormDataPart("facebook", facebook)
                        .addFormDataPart("google", google)
                        .addFormDataPart("user_image", image)
                        .build();
                this.apiInterface.updateProfile(requestBody).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NotNull Call<String> call, @NotNull Response<String> response) {
                        try {


                            String blogData = (String) response.body();
                        } catch (Exception e) {

                        }
                    }

                    @Override
                    public void onFailure(@NotNull Call<String> call, @NotNull Throwable t) {
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }
}
