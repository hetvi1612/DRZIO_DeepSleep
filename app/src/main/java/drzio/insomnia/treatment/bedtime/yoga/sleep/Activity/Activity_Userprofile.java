package drzio.insomnia.treatment.bedtime.yoga.sleep.Activity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.ads.AdView;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.github.abdularis.civ.AvatarImageView;
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
import com.google.android.gms.common.api.Status;
import com.shawnlin.numberpicker.NumberPicker;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Apiclients;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity.Activity_Bloglists;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Diet.CustomUpdateActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Diet.DefalutAndCustomplanActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Fragments.CmGraphfragments;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Fragments.WeightGraphfragments;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.ReminderMainActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.customs.CustomSeekBar;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.LoginData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.ProgressItem;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;


public class Activity_Userprofile extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    private CallbackManager callbackManager;
    private TextView mTxtword;
    private LinearLayout mloginlay;
    private LinearLayout mSucceslayout;
    private TextView mProfilename;
    private AvatarImageView mProfilepic;
    private LoginButton loginButton;
    private ImageView mBtnlogout;
    private boolean isFromFb = false;
    private boolean isFromGoogle = false;
    private String TAG = "LoginActivity";
    private String userId;
    private GoogleApiClient mGoogleApiClient;
    private GoogleSignInAccount account;
    private String firstName, lastName, email, birthday, gender;
    private URL profilePicture;
    private static final int SIGN_IN_CODE = 9001;
    private TextView mTotalkcal;
    private TextView mTotaltime;
    public TinyDB mTinydb;
    private TextView mTotalexe;
    private boolean mSuccess;
    private boolean success2;
    public ArrayList<LoginData> mLogindata = new ArrayList<>();
    private int mAgevalue;
    private String mGender = "female";
    private int mHcmvalue;
    private int mHftvalue;
    private int mHinchvalue;
    private boolean isbtncm = true;
    private boolean isbtninch;
    private TextView mSwich2txt;
    private int mWkgvalue;
    private int mWlbvalue;
    private boolean isBtnkg = true;
    private boolean isBtnLb;
    private TextView pageSelected;
    int mealtype = 2;
    String usertype="veg";
    private TextView mTxtage;
    private TextView mTxtgender;
    private TextView mTxtheight;
    private TextView mTxtweight;
    private TextView mTxtmeal;
    private String Gusername;
    private String Gmail;
    private String Guserid;
    private String Gprofilepicurl;
    private String GFirstname;
    private String GLastname;
    private TextView mTxtreminder;
    //    private LinearLayout mAdcontainer;
    private AdView adView;
    private LinearLayout mBtcalbmi;
    private RelativeLayout mBmiscalelayout;
    private CustomSeekBar bmiseekBar;
    private TextView wght2;
    private TextView wght4;
    private TextView mTxtbmi;
    public float Heightincms = 0.0f;
    private ArrayList<ProgressItem> progressItemList;
    private ProgressItem mProgressItem;
    private float bmitotalSpan = 50.0f;
    private float bmivioletSpan = 15.0f;
    private float bmiyellowSpan = 5.0f;
    private double bmiblueSpan = 4.0d;
    private double bmigreenSpan = 7.0d;
    private float bmiorangeSpan = 10.0f;
    private float bmiredSpan;
    private String fbemail;
    boolean isclicked = false;
    //    private RelativeLayout rectangleAdContainer;
    private @Nullable
    AdView rectangleAdView;
    private LinearLayout mTemplayer1;
    private LinearLayout mTemplayer2;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        Bottombar();
        mTinydb = new TinyDB(Activity_Userprofile.this);

        String languages = mTinydb.getString(Constants.Language);
        Constants.languagechange(this, languages);

        callbackManager = CallbackManager.Factory.create();

        mBtcalbmi = (LinearLayout) findViewById(R.id.btcalbmi);
        mBmiscalelayout = (RelativeLayout) findViewById(R.id.bmiscalelay);
        this.bmiseekBar = (CustomSeekBar) findViewById(R.id.bmiseekBar);
        this.wght2 = (TextView) findViewById(R.id.wght2);
        this.wght4 = (TextView) findViewById(R.id.wght4);
        mTxtbmi = (TextView) findViewById(R.id.txtbmi);
        mTemplayer1 = (LinearLayout) findViewById(R.id.templayer1);
        mTemplayer2 = (LinearLayout) findViewById(R.id.templayer2);
        mBtnlogout = (ImageView) findViewById(R.id.btn_logout);
        mTxtword = (TextView) findViewById(R.id.txtword);
        mloginlay = (LinearLayout) findViewById(R.id.loginlayout);
        mSucceslayout = (LinearLayout) findViewById(R.id.successlayout);
        mProfilename = (TextView) findViewById(R.id.profilename);
        mProfilepic = (AvatarImageView) findViewById(R.id.profile_image);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        mTotalkcal = (TextView) findViewById(R.id.txttotalcal);
        mTotaltime = (TextView) findViewById(R.id.txtexcertime);
        mTotalexe = (TextView) findViewById(R.id.txtexcecount);
        mTxtage = (TextView) findViewById(R.id.txtage);
        mTxtgender = (TextView) findViewById(R.id.txtgender);
        mTxtheight = (TextView) findViewById(R.id.txtheight);
        mTxtweight = (TextView) findViewById(R.id.txtweight);
        mTxtmeal = (TextView) findViewById(R.id.txtmeal);
        mTxtreminder = (TextView) findViewById(R.id.txttime);
//        rectangleAdContainer = (RelativeLayout) findViewById(R.id.rectangleAdContainer);
//        rectangleAdContainer.setVisibility(View.GONE);

        LinearLayout mGbannerlay = findViewById(R.id.adframe);
        mGbannerlay.setVisibility(View.GONE);
        Smallnative(this, mGbannerlay);

        FrameLayout mChart = (FrameLayout) findViewById(R.id.framechart);
        CmGraphfragments graphfragments = new CmGraphfragments();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.framechart, graphfragments).commit();

        FrameLayout mChart2 = (FrameLayout) findViewById(R.id.framechart2);
        WeightGraphfragments weightGraphfragments = new WeightGraphfragments();
        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
        ft2.add(R.id.framechart2, weightGraphfragments).commit();

//        loadAdView();
        ImageView mBtsetting = (ImageView) findViewById(R.id.btnsetting);
        mBtsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isclicked) {
                    isclicked = true;
                    Intent intent = new Intent(Activity_Userprofile.this, Activity_Settings.class);
                    startActivity(intent);
                }
            }
        });
        mGender = mTinydb.getString(Constants.GENDER_KEY);
        mealtype = mTinydb.getInt(Constants.MEALTYPE_KEY);
        usertype=mTinydb.getString(Constants.USERTYPEKEY);
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
        LinearLayout mBtreminder = (LinearLayout) findViewById(R.id.btreminder);
        RelativeLayout mBtage = (RelativeLayout) findViewById(R.id.btnage);
        RelativeLayout mBtgender = (RelativeLayout) findViewById(R.id.btngender);
        RelativeLayout mBtheight = (RelativeLayout) findViewById(R.id.btnheight);
        RelativeLayout mBtweight = (RelativeLayout) findViewById(R.id.btnweight);
        RelativeLayout mBtmeal = (RelativeLayout) findViewById(R.id.btnmeal);
        mBtreminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isclicked) {
                    isclicked = true;
                    Intent intent = new Intent(Activity_Userprofile.this, ReminderMainActivity.class);
                    startActivity(intent);
                }
            }
        });
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
        int mMeal = mTinydb.getInt(Constants.MEALTYPE_KEY);
        if (mMeal == 1) {
            mTxtmeal.setText("Veg");
        } else if (mMeal == 2) {
            mTxtmeal.setText("Non-Veg");
        } else if (mMeal == 3) {
            mTxtmeal.setText("Vegan");
        }

        mBtcalbmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogCalculatebmi();
            }
        });

        if (mTinydb.getFloat(Constants.BMI_KEY) != 0) {
            mBmiscalelayout.setVisibility(View.VISIBLE);
            mTxtbmi.setText(String.valueOf(mTinydb.getFloat(Constants.BMI_KEY)) + " KG/M²");
            initDataToSeekbar();
        } else {
            mBmiscalelayout.setVisibility(View.GONE);
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
        loginButton.setHeight(100);
        loginButton.setTextColor(Color.WHITE);
        loginButton.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        mBtnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isFromFb) {
                    logout();
                } else {
                    signOut();
                }
            }
        });

        loginButton.setCompoundDrawablePadding(0);
        ImageView signInButton = (ImageView) findViewById(R.id.sign_in_button);
//        SignInButton signInButton = (SignInButton) findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFromFb = false;
                isFromGoogle = true;
                signIn();
            }
        });

        mTemplayer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFromFb = false;
                isFromGoogle = true;
                signIn();
            }
        });
        mTemplayer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isFromFb = false;
                isFromGoogle = true;
                signIn();
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


//        signInButton.setSize(SignInButton.SIZE_STANDARD);

        FacebookCallback<LoginResult> callback = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                isFromGoogle = false;
                isFromFb = true;
                mSucceslayout.setVisibility(View.VISIBLE);
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.e(TAG, object.toString());
                        Log.e(TAG, response.toString());

                        try {
                            userId = object.getString("id");
                            profilePicture = new URL("https://graph.facebook.com/" + userId + "/picture?width=500&height=500");
                            if (object.has("first_name"))
                                firstName = object.getString("first_name");
                            if (object.has("last_name"))
                                lastName = object.getString("last_name");
                            if (object.has("email"))
                                fbemail = object.getString("email");
                            if (object.has("birthday"))
                                birthday = object.getString("birthday");
                            if (object.has("gender"))
                                gender = object.getString(Constants.GENDER_KEY);

                            updateUI(true);
                            String tempname = firstName + " " + lastName;
                            String token = mTinydb.getString(Constants.FCMTOKEN_KEY);
                            new LoginApi(fbemail, userId, "", token, "facebook").execute();
                            if (!success2) {

                                if (mSuccess) {
                                    updatedata(tempname, profilePicture.toString());
                                }
                            } else {
                                updatedata(tempname, profilePicture.toString());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id, first_name, last_name, email, birthday, gender , location");
                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
            }

            @Override
            public void onError(FacebookException e) {
                e.printStackTrace();
            }
        };

        loginButton.setReadPermissions("email", "user_birthday", "user_posts", "user_location");
        loginButton.registerCallback(callbackManager, callback);
        mTotalkcal.setText(String.valueOf(round(mTinydb.getFloat(Constants.TOTALKCAL_KEY), 1)));
        long temp = mTinydb.getLong(Constants.TOTALTIME_KEY, 0);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(temp);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(temp) % 60;
        mTotaltime.setText(minutes + ":" + seconds);
        String totalexe = String.valueOf(mTinydb.getInt(Constants.TOTALEXE_KEY));
        mTotalexe.setText(totalexe);
    }


    public FragmentRefreshListener getFragmentRefreshListener() {
        return fragmentRefreshListener;
    }

    public void setFragmentRefreshListener(FragmentRefreshListener fragmentRefreshListener) {
        this.fragmentRefreshListener = fragmentRefreshListener;
    }

    private FragmentRefreshListener fragmentRefreshListener;


    public static float round(float d, int decimalPlace) {
        return BigDecimal.valueOf(d).setScale(decimalPlace, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public void Bottombar() {
        ImageView mBtplan = (ImageView) findViewById(R.id.bottomplan);
        ImageView mBtdiet = (ImageView) findViewById(R.id.bottomdiet);
        ImageView mBttips = (ImageView) findViewById(R.id.bottomtips);
        ImageView mBtprofile = (ImageView) findViewById(R.id.bottomprofile);
        mBtplan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bottomclicks(0);
            }
        });
        mBtdiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bottomclicks(1);
            }
        });
        mBttips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bottomclicks(2);
            }
        });
    }

    public void Bottomclicks(int i) {
        if (!isclicked) {
            isclicked = true;
            if (i == 0) {
                Intent intent = new Intent(Activity_Userprofile.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(0, R.anim.fast_fade_out);
            } else if (i == 1) {



                boolean diet = mTinydb.getBoolean(Constants.ReadyDietPlan);
                if (diet) {
                    Intent intent1 = new Intent(Activity_Userprofile.this, DefalutAndCustomplanActivity.class);
                    intent1.putExtra("isFrom2", false);
                    startActivity(intent1);
                } else {
                    Intent intent = new Intent(Activity_Userprofile.this, CustomUpdateActivity.class);
                    intent.putExtra("isFrom", false);
                    startActivity(intent);
                }
              /*  String tempdate = mTinydb.getString(Constants.DIETPLANDATE_KEY);
                Date c = Calendar.getInstance().getTime();
                 Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
                String formattedDate = df.format(c);
                if (!tempdate.isEmpty() && tempdate.equals(formattedDate)) {
                    Intent intent1 = new Intent(Activity_Userprofile.this, CustomUpdateActivity.class);
                    intent1.putExtra("isFrom2", false);
                    startActivity(intent1);
                } else {
                    Intent intent = new Intent(Activity_Userprofile.this, DefalutAndCustomplanActivity.class);
                    intent.putExtra("isFrom", false);
                    startActivity(intent);
                }*/

            } else if (i == 2) {
                Intent intent = new Intent(Activity_Userprofile.this, Activity_Bloglists.class);
                intent.putExtra("isFrom3", true);
                startActivity(intent);
                overridePendingTransition(0, R.anim.fast_fade_out);
            }
            finish();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        super.onActivityResult(requestCode, responseCode, intent);
        if (requestCode == SIGN_IN_CODE) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(intent);
            handleSignInResult(result);

        } else {
            callbackManager.onActivityResult(requestCode, responseCode, intent);
        }
    }


    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, SIGN_IN_CODE);

    }

    private void signOut() {
        try {
            Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                    new ResultCallback<Status>() {
                        @Override
                        public void onResult(@NonNull Status status) {
                            updateUI(false);
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    private void updateUI(boolean signedIn) {
        if (signedIn) {
            mTinydb.putBoolean(Constants.ISLOGIN_KEY, true);
            mloginlay.setVisibility(View.GONE);
            mSucceslayout.setVisibility(View.VISIBLE);
            mTemplayer1.setVisibility(View.GONE);
            mTemplayer2.setVisibility(View.GONE);
        } else {
            mTinydb.remove(Constants.USERID_KEY);
            mTinydb.remove(Constants.USEREMAIL_KEY);
            mTinydb.remove(Constants.USERFIRSTNAME_KEY);
            mTinydb.remove(Constants.USERLASTNAME_KEY);
            mTinydb.putBoolean(Constants.ISLOGIN_KEY, false);
            mSucceslayout.setVisibility(View.GONE);
            mloginlay.setVisibility(View.VISIBLE);
            mProfilepic.setState(AvatarImageView.SHOW_IMAGE);
            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(FitnessApplication.getInstance()).load(R.drawable.img_profile)
                    .apply(requestOptions).into(mProfilepic);
            mTemplayer1.setVisibility(View.VISIBLE);
            mTemplayer2.setVisibility(View.VISIBLE);
        }
    }

    public void updatedata(String mProfname, String imgurl) {
        mProfilename.setText(mProfname);
        if (imgurl != null && !imgurl.equals("null")) {
            mProfilepic.setState(AvatarImageView.SHOW_IMAGE);
            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(FitnessApplication.getInstance()).load(imgurl)
                    .apply(requestOptions).into(mProfilepic);
        } else {
            try {
                mProfilepic.setState(AvatarImageView.SHOW_INITIAL);
                mProfilepic.setAvatarBackgroundColor(getResources().getColor(R.color.colorAccent));
                String s = mProfname.substring(0, 1);
                mProfilepic.setText(s);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    protected void onStart() {
        isclicked = false;
        super.onStart();
        OptionalPendingResult<GoogleSignInResult> optPenRes = Auth.GoogleSignInApi.silentSignIn(mGoogleApiClient);
        if (optPenRes.isDone()) {
            Log.e(TAG, "Yayy!");
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

    private void handleSignInResult(GoogleSignInResult result) {
        Log.e(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            account = result.getSignInAccount();
            Gusername = account.getDisplayName();
            Gmail = account.getEmail();
            Guserid = account.getId();
            Gprofilepicurl = String.valueOf(account.getPhotoUrl());
            GFirstname = account.getGivenName();
            GLastname = account.getFamilyName();
            boolean mIslogedin = mTinydb.getBoolean(Constants.ISLOGIN_KEY);
            if (!mIslogedin) {
                if (Gmail != null) {
                    String token = mTinydb.getString(Constants.FCMTOKEN_KEY);
                    new LoginApi(Gmail, "", Guserid, token, "google").execute();

                }
            }
            updateUI(true);
            updatedata(Gusername, Gprofilepicurl);
        } else {
            updateUI(false);
        }

    }

    private void logout() {
        LoginManager.getInstance().logOut();
        updateUI(false);
    }


    @SuppressLint("StaticFieldLeak")
    class LoginApi extends AsyncTask<String, String, String> {
        String email;
        String fbid;
        String googleid;
        String fcmtoken;
        String Platform;

        public LoginApi(String email, String fbid, String googleid, String fcmtoken, String Platform) {
            this.email = email;
            this.fbid = fbid;
            this.googleid = googleid;
            this.fcmtoken = fcmtoken;
            this.Platform = Platform;
        }

        @Override
        protected String doInBackground(String... params) {
            String adurl = Apiclients.LOGIN_API;
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(adurl);
            try {
                List<NameValuePair> nameValuePairs = new ArrayList<>(4);
                nameValuePairs.add(new BasicNameValuePair("email", this.email));
                nameValuePairs.add(new BasicNameValuePair("facebook_id", this.fbid));
                nameValuePairs.add(new BasicNameValuePair("google_id", this.googleid));
                nameValuePairs.add(new BasicNameValuePair("fcm_token", this.fcmtoken));
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs);
                httppost.setEntity(urlEncodedFormEntity);
                String result = EntityUtils.toString(httpclient.execute(httppost).getEntity());
                Log.e("Response Popup: ", "" + result);
                if (result != null) {
                    JSONObject jsonObject = new JSONObject(result);
                    success2 = jsonObject.getBoolean("status");
                    if (success2) {
                        Log.e("islogin: ", "Login success" + result);
                        JSONObject jsonObject_top = jsonObject.getJSONObject("data");
                        String userid = jsonObject_top.getString("id");
                        String fname = jsonObject_top.getString("first_name");
                        String lname = jsonObject_top.getString("last_name");
                        String mailid = jsonObject_top.getString("email");
                        String mobileno = jsonObject_top.getString("mobile_number");
                        String profilepic = jsonObject_top.getString("image");
                        String gender = jsonObject_top.getString("gender");
                        String age = jsonObject_top.getString("age");
                        String height = jsonObject_top.getString("height");
                        String weight = jsonObject_top.getString("weight");
                        String paystate = jsonObject_top.getString("paid_status");
                        String isgoogle = jsonObject_top.getString("google");
                        String isfacebook = jsonObject_top.getString("facebook");
                        String usertype = jsonObject_top.getString("user_type");
                        String countryname = jsonObject_top.getString("countryname");
                        String statename = jsonObject_top.getString("statename");
                        String cityname = jsonObject_top.getString("cityname");
                        String insert_datetime = jsonObject_top.getString("insert_datetime");
                        String login_time = jsonObject_top.getString("login_time");
                        LoginData data = new LoginData();

                       /* data.setId(userid);
                        data.setFirst_name(fname);
                        data.setLast_name(lname);
                        data.setEmail(mailid);
                        data.setMobile_number(mobileno);
                        data.setImage(profilepic);
                        data.setGender(gender);
                        data.setAge(age);
                        data.setHeight(height);
                        data.setWeight(weight);
                        data.setPaid_status(paystate);
                        data.setGoogle(isgoogle);
                        data.setFacebook(isfacebook);
                        data.setUser_type(usertype);
                        data.setCountryname(countryname);
                        data.setStatename(statename);
                        data.setCityname(cityname);
                        data.setInsert_datetime(insert_datetime);
                        data.setLogin_time(login_time);*/
                        mLogindata.add(data);
                        mTinydb.putString(Constants.USERID_KEY, userid);
                        mTinydb.putString(Constants.USEREMAIL_KEY, mailid);
                        mTinydb.putString(Constants.USERFIRSTNAME_KEY, fname);
                        mTinydb.putString(Constants.USERLASTNAME_KEY, lname);
//                        LoginData.setmLogininfos(mLogindata);
                        updatedata(userid, age, gender, height, weight, usertype);

                    }
                }
            } catch (IOException e) {
            } catch (JSONException e2) {
                Log.e("Error", "" + e2.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (!success2) {
                String first_name;
                String last_name;
                String email;
                if (Platform.equals("facebook")) {
                    first_name = firstName;
                    last_name = lastName;
                    email = fbemail;
                } else {
                    first_name = GFirstname;
                    last_name = GLastname;
                    email = Gmail;
                }

                String mobile_number = "0";
                String gender = mTinydb.getString(Constants.GENDER_KEY);
                String age = String.valueOf(mTinydb.getInt(Constants.AGE_KEY));
                String mHeight;
                if (mTinydb.getBoolean(Constants.ISCM_KEY)) {
                    mHeight = mTinydb.getFloat(Constants.HEIGHT_KEY) + " cm";
                } else {
                    mHeight = mTinydb.getFloat(Constants.HEIGHT_KEY) + " ft";
                }
                String height = mHeight;
                String mWeight;
                if (mTinydb.getBoolean(Constants.ISKG_KEY)) {
                    mWeight = mTinydb.getInt(Constants.WEIGHT_KEY) + " kg";
                } else {
                    mWeight = mTinydb.getInt(Constants.WEIGHT_KEY) + " lb";
                }
                String weight = mWeight;
                String user_type_id = String.valueOf(mTinydb.getInt(Constants.MEALTYPE_KEY));
                String paid_status = "0";
                String country_id = mTinydb.getString(Constants.COUNTRYID_KEY);
                String state_id = mTinydb.getString(Constants.STATEID_KEY);
                String city_id = mTinydb.getString(Constants.CITYID_KEY);
                String fcm_token = mTinydb.getString(Constants.FCMTOKEN_KEY);
                String facebook = userId;
                String google = Guserid;
                String user_image = Gprofilepicurl;

                new UserRegisterAPI(first_name, last_name, email, mobile_number, gender,
                        age, height, weight, user_type_id, paid_status, country_id,
                        state_id, city_id, fcm_token, facebook, google, user_image).execute();

            }
        }
    }

    public void updatedata(String userid, String mAge, String gen, String height, String weight, String mealtype) {
        try {
            mTinydb.putString(Constants.USERID_KEY, userid);
            mTinydb.putInt(Constants.AGE_KEY, Integer.parseInt(mAge));
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTxtage.setText(mAge);
                }
            });
            mTinydb.putString(Constants.GENDER_KEY, gen);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTxtgender.setText(gen);
                }
            });
            if (height.contains("cm")) {
                mTinydb.putBoolean(Constants.ISCM_KEY, true);
                height = height.replace(" cm", "");
                mTinydb.putFloat(Constants.HEIGHT_KEY, Float.parseFloat(height));
                String finalHeight = height;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTxtheight.setText(finalHeight + " cm");
                    }
                });
            } else {
                String doubleAsString = height;
                int indexOfDecimal = doubleAsString.indexOf(".");
                int mFtval = Integer.parseInt(doubleAsString.substring(0, indexOfDecimal));
                String mTrim = doubleAsString.substring(indexOfDecimal).replace(".", "");
                mTrim = mTrim.replace(" ft", "");
                int mInchval = Integer.parseInt(mTrim);
                mTinydb.putBoolean(Constants.ISCM_KEY, false);
                String temp = mFtval + "." + mInchval;
                mTinydb.putFloat(Constants.HEIGHT_KEY, Float.parseFloat(temp));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTxtheight.setText(temp + " ft");
                    }
                });
            }
            if (weight.contains("kg")) {
                mTinydb.putBoolean(Constants.ISKG_KEY, true);
                weight = weight.replace(" kg", "");
                mTinydb.putInt(Constants.WEIGHT_KEY, Integer.parseInt(weight));
                String finalWeight = weight;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTxtweight.setText(finalWeight + " kg");
                    }
                });
            } else {
                mTinydb.putBoolean(Constants.ISKG_KEY, false);
                weight = weight.replace(" lb", "");
                mTinydb.putInt(Constants.WEIGHT_KEY, Integer.parseInt(weight));
                String finalWeight1 = weight;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTxtweight.setText(finalWeight1 + " lb");
                    }
                });
            }
            if (mealtype.equals("Veg")) {
                mTinydb.putInt(Constants.MEALTYPE_KEY, 1);
                mTinydb.putString(Constants.USERTYPEKEY,"veg");
            } else if (mealtype.equals("Non-Veg")) {
                mTinydb.putInt(Constants.MEALTYPE_KEY, 2);
                mTinydb.putString(Constants.USERTYPEKEY,"nonveg");
            } else if (mealtype.equals("Vegan")) {
                mTinydb.putInt(Constants.MEALTYPE_KEY, 3);
                mTinydb.putString(Constants.USERTYPEKEY,"vegan");
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mTxtmeal.setText(mealtype);
                }
            });
        } catch (Exception e) {
            Log.e("ploginerror", "" + e.getMessage().toString());
            e.printStackTrace();
        }
    }


    public class UserRegisterAPI extends AsyncTask<String, String, String> {
        String first_name;
        String last_name;
        String email;
        String mobile_number;
        String gender;
        String age;
        String height;
        String weight;
        String user_type_id;
        String paid_status;
        String country_id;
        String state_id;
        String city_id;
        String fcm_token;
        String facebook;
        String google;
        String user_image;

        public UserRegisterAPI(String first_name, String last_name, String email, String mobile_number, String gender, String age, String height, String weight, String user_type_id, String paid_status, String country_id, String state_id, String city_id, String fcm_token, String facebook, String google, String user_image) {
            this.first_name = first_name;
            this.last_name = last_name;
            this.email = email;
            this.mobile_number = mobile_number;
            this.gender = gender;
            this.age = age;
            this.height = height;
            this.weight = weight;
            this.user_type_id = user_type_id;
            this.paid_status = paid_status;
            this.country_id = country_id;
            this.state_id = state_id;
            this.city_id = city_id;
            this.fcm_token = fcm_token;
            this.facebook = facebook;
            this.google = google;
            this.user_image = user_image;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String adurl = Apiclients.USERREGISTER_API;
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(adurl);
            try {
                List<NameValuePair> nameValuePairs = new ArrayList<>(17);
                nameValuePairs.add(new BasicNameValuePair("first_name", first_name));
                nameValuePairs.add(new BasicNameValuePair("last_name", last_name));
                nameValuePairs.add(new BasicNameValuePair("email", email));
                nameValuePairs.add(new BasicNameValuePair("mobile_number", mobile_number));
                nameValuePairs.add(new BasicNameValuePair("gender", gender));
                nameValuePairs.add(new BasicNameValuePair("age", age));
                nameValuePairs.add(new BasicNameValuePair("height", height));
                nameValuePairs.add(new BasicNameValuePair("weight", weight));
                nameValuePairs.add(new BasicNameValuePair("user_type_id", user_type_id));
                nameValuePairs.add(new BasicNameValuePair("paid_status", paid_status));
                nameValuePairs.add(new BasicNameValuePair("country_id", country_id));
                nameValuePairs.add(new BasicNameValuePair("state_id", state_id));
                nameValuePairs.add(new BasicNameValuePair("city_id", city_id));
                nameValuePairs.add(new BasicNameValuePair("fcm_token", fcm_token));
                nameValuePairs.add(new BasicNameValuePair("facebook", facebook));
                nameValuePairs.add(new BasicNameValuePair("google", google));
                nameValuePairs.add(new BasicNameValuePair("user_image", user_image));
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs);
                httppost.setEntity(urlEncodedFormEntity);
                String result = EntityUtils.toString(httpclient.execute(httppost).getEntity());
                Log.e("Response Popup: ", "" + result);
                if (result != null) {
                    JSONObject jsonObject = new JSONObject(result);
                    mSuccess = jsonObject.getBoolean("status");
                    if (mSuccess) {
                        JSONObject jsonArray_top = jsonObject.getJSONObject("data");
                        String userid = jsonArray_top.getString("id");
                        mTinydb.putString(Constants.USERID_KEY, userid);
                        mTinydb.putString(Constants.USEREMAIL_KEY, email);
                        mTinydb.putString(Constants.USERFIRSTNAME_KEY, first_name);
                        mTinydb.putString(Constants.USERLASTNAME_KEY, last_name);
                        Log.e("userid", "Your user id" + userid);
                    }
                }
            } catch (IOException e) {
            } catch (JSONException e2) {
                Log.e("Error", "" + e2.toString());
            }
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(Activity_Userprofile.this, "Register success", Toast.LENGTH_SHORT).show();
        }
    }


    public class UpdateprofileAPI extends AsyncTask<String, String, String> {
        private String userid, first_name, last_name, email, mobile_number, image,
                gender, age, height, weight, paid_status, google, facebook, user_type,
                countryname, statename, cityname, fcm_token;

        public UpdateprofileAPI(String userid, String first_name, String last_name, String email, String mobile_number, String image, String gender, String age, String height, String weight, String paid_status, String google, String facebook, String user_type, String countryname, String statename, String cityname, String fcm_token) {
            this.userid = userid;
            this.first_name = first_name;
            this.last_name = last_name;
            this.email = email;
            this.mobile_number = mobile_number;
            this.image = image;
            this.gender = gender;
            this.age = age;
            this.height = height;
            this.weight = weight;
            this.paid_status = paid_status;
            this.google = google;
            this.facebook = facebook;
            this.user_type = user_type;
            this.countryname = countryname;
            this.statename = statename;
            this.cityname = cityname;
            this.fcm_token = fcm_token;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String adurl = Apiclients.UPDATE_PROFILE;
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(adurl);
            try {
                List<NameValuePair> nameValuePairs = new ArrayList<>(17);
                nameValuePairs.add(new BasicNameValuePair("usr_id", userid));
                nameValuePairs.add(new BasicNameValuePair("first_name", first_name));
                nameValuePairs.add(new BasicNameValuePair("last_name", last_name));
                nameValuePairs.add(new BasicNameValuePair("email", email));
                nameValuePairs.add(new BasicNameValuePair("mobile_number", mobile_number));
                nameValuePairs.add(new BasicNameValuePair("gender", gender));
                nameValuePairs.add(new BasicNameValuePair("age", age));
                nameValuePairs.add(new BasicNameValuePair("height", height));
                nameValuePairs.add(new BasicNameValuePair("weight", weight));
                nameValuePairs.add(new BasicNameValuePair("user_type_id", user_type));
                nameValuePairs.add(new BasicNameValuePair("paid_status", paid_status));
                nameValuePairs.add(new BasicNameValuePair("country_id", countryname));
                nameValuePairs.add(new BasicNameValuePair("state_id", statename));
                nameValuePairs.add(new BasicNameValuePair("city_id", cityname));
                nameValuePairs.add(new BasicNameValuePair("fcm_token", fcm_token));
                nameValuePairs.add(new BasicNameValuePair("facebook", facebook));
                nameValuePairs.add(new BasicNameValuePair("google", google));
                nameValuePairs.add(new BasicNameValuePair("user_image", image));
                Log.e("imagedata", image);
                UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(nameValuePairs);
                httppost.setEntity(urlEncodedFormEntity);
                String result = EntityUtils.toString(httpclient.execute(httppost).getEntity());
                Log.e("Response Popup: ", "" + result);
                if (result != null) {
                    JSONObject jsonObject = new JSONObject(result);
                    mSuccess = jsonObject.getBoolean("status");
                    if (mSuccess) {
                        JSONObject jsonArray_top = jsonObject.getJSONObject("data");

                    }
                }
            } catch (IOException e) {
            } catch (JSONException e2) {
                Log.e("Error", "" + e2.toString());
            }
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
//            Toast.makeText(Activity_Userprofile.this, "Register success", Toast.LENGTH_SHORT).show();
        }
    }


    public void DialogAgechange() {
        final Dialog dialog = new Dialog(Activity_Userprofile.this);
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
        String mHeight;
        if (type.equals("height")) {
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
        }
        String weight = mWeight;
        String paid_status = "0";
        String google = Guserid;
        String facebook = "0";
        String user_type;
        if (type.equals("meal")) {
            user_type = value;
        } else {
            user_type = String.valueOf(mTinydb.getInt(Constants.MEALTYPE_KEY));
        }
        String countryname = mTinydb.getString(Constants.COUNTRYID_KEY);
        String statename = mTinydb.getString(Constants.STATEID_KEY);
        String cityname = mTinydb.getString(Constants.CITYID_KEY);
        String fcm_token = mTinydb.getString(Constants.FCMTOKEN_KEY);
        if (mTinydb.getBoolean(Constants.ISLOGIN_KEY)) {
            new UpdateprofileAPI(user_id, first_name, last_name, email,
                    mobile_number, image, gender, age, height, weight,
                    paid_status, google, facebook, user_type, countryname,
                    statename, cityname, fcm_token).execute();
        }
    }

    public void DialogGenderchange() {
        final Dialog dialog = new Dialog(Activity_Userprofile.this);
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
            mTxtmale.setTextColor(Color.WHITE);
            mImgfemale.setImageResource(R.drawable.femaleselectedbtn);
            mTxtfemale.setTextColor(getResources().getColor(R.color.themegreen));
        }
        mBtnfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImgmale.setImageResource(R.drawable.maleunselectedbtn);
                mTxtmale.setTextColor(Color.WHITE);
                mImgfemale.setImageResource(R.drawable.femaleselectedbtn);
                mTxtfemale.setTextColor(getResources().getColor(R.color.themegreen));
                mGender = String.valueOf(mTxtfemale.getText());
            }
        });
        mBtnmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImgmale.setImageResource(R.drawable.maleselectedbtn);
                mTxtmale.setTextColor(getResources().getColor(R.color.themegreen));
                mImgfemale.setImageResource(R.drawable.femaleunselectedbtn);
                mTxtfemale.setTextColor(Color.WHITE);
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
        final Dialog dialog = new Dialog(Activity_Userprofile.this);
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
                if (getFragmentRefreshListener() != null) {
                    getFragmentRefreshListener().onRefresh();
                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                try {
                    CmGraphfragments graphfragments = new CmGraphfragments();
                    transaction.replace(R.id.framechart, graphfragments).commit();

                } catch (IllegalStateException ignored) {
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
        final Dialog dialog = new Dialog(Activity_Userprofile.this);
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
                if (getFragmentRefreshListener() != null) {
                    getFragmentRefreshListener().onRefresh();
                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                try {
                    WeightGraphfragments graphfragments = new WeightGraphfragments();
                    transaction.replace(R.id.framechart2, graphfragments).commit();

                } catch (IllegalStateException ignored) {
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
        final Dialog dialog = new Dialog(Activity_Userprofile.this);
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

        mBtnveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImgveg.setImageResource(R.drawable.vegselect);
                mTxtveg.setTextColor(getResources().getColor(R.color.themegreen));
                mImgnonveg.setImageResource(R.drawable.nonvegunselect);
                mTxtnonveg.setTextColor(Color.WHITE);
                mImgvegan.setImageResource(R.drawable.veganunselect);
                mTxtvegan.setTextColor(Color.WHITE);
                mealtype = 1;
                usertype="veg";

            }
        });
        mBtnnonveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImgveg.setImageResource(R.drawable.vegunselect);
                mTxtveg.setTextColor(Color.WHITE);
                mImgnonveg.setImageResource(R.drawable.nonvegselect);

                mTxtnonveg.setTextColor(getResources().getColor(R.color.themegreen));
                mImgvegan.setImageResource(R.drawable.veganunselect);
                mTxtvegan.setTextColor(Color.WHITE);
                mealtype = 2;
                usertype="nonveg";
            }
        });
        mBtnvegan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImgveg.setImageResource(R.drawable.vegunselect);
                mTxtveg.setTextColor(Color.WHITE);
                mImgnonveg.setImageResource(R.drawable.nonvegunselect);
                mTxtnonveg.setTextColor(Color.WHITE);
                mImgvegan.setImageResource(R.drawable.veganselect);
                mTxtvegan.setTextColor(getResources().getColor(R.color.themegreen));
                mealtype = 3;
                usertype="vegan";
            }
        });

        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTinydb.putInt(Constants.MEALTYPE_KEY, mealtype);
                mTinydb.putString(Constants.USERTYPEKEY,usertype);
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


    public void DialogCalculatebmi() {
        final Dialog dialog = new Dialog(Activity_Userprofile.this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_bmidata);
        NumberPicker mCmpicker = (NumberPicker) dialog.findViewById(R.id.cmpicker);
        NumberPicker mFtpicker = (NumberPicker) dialog.findViewById(R.id.ftpicker);
        NumberPicker mInchpicker = (NumberPicker) dialog.findViewById(R.id.inchpicker);
        LinearLayout mSwitchcminch = (LinearLayout) dialog.findViewById(R.id.switchcminch);
        final TextView mBtcm = (TextView) mSwitchcminch.findViewById(R.id.btcm);
        final TextView mBtinch = (TextView) mSwitchcminch.findViewById(R.id.btinch);
        LinearLayout mCmlayout = (LinearLayout) dialog.findViewById(R.id.cmlayout);
        LinearLayout mInchlayout = (LinearLayout) dialog.findViewById(R.id.inchlayout);
        NumberPicker mKgpicker = (NumberPicker) dialog.findViewById(R.id.kgpicker);
        NumberPicker mLbspicker = (NumberPicker) dialog.findViewById(R.id.lbsbpicker);
        LinearLayout mLaykglb = (LinearLayout) dialog.findViewById(R.id.laykglb);
        final TextView mBtkg = (TextView) mLaykglb.findViewById(R.id.btkgs);
        final TextView mBtlbs = (TextView) mLaykglb.findViewById(R.id.btlbs);
        LinearLayout mKgslayout = (LinearLayout) dialog.findViewById(R.id.laykgs);
        LinearLayout mLbsslayout = (LinearLayout) dialog.findViewById(R.id.laylbs);
        CardView mBtcalculate = (CardView) dialog.findViewById(R.id.calculate);

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


        mBtcalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isbtncm) {
                    String doubleAsString = String.valueOf(round(Float.parseFloat(cmtofeet(mHcmvalue)), 1));
                    int indexOfDecimal = doubleAsString.indexOf(".");
                    mHftvalue = Integer.parseInt(doubleAsString.substring(0, indexOfDecimal));
                    String mTrim = doubleAsString.substring(indexOfDecimal).replace(".", "");
                    mHinchvalue = Integer.parseInt(mTrim);
                }
                if (!isBtnkg) {
                    if (isBtnLb) {
                        float b = (float) calculateBMI(calculateMetres(mHftvalue, mHinchvalue), calculateweight(mWlbvalue));
                        mTinydb.putFloat(Constants.BMI_KEY, b);
                        if (mTinydb.getFloat(Constants.BMI_KEY) != 0) {
                            mBmiscalelayout.setVisibility(View.VISIBLE);
                            mTxtbmi.setText(String.valueOf(b) + " KG/M²");
                            initDataToSeekbar();
                        } else {
                            mBmiscalelayout.setVisibility(View.GONE);
                        }
                    }
                } else {
                    int b2 = calculateBMI(calculateMetres(mHftvalue, mHinchvalue), calculateweight(mWkgvalue));
                    mTinydb.putFloat(Constants.BMI_KEY, (float) b2);
                    if (mTinydb.getFloat(Constants.BMI_KEY) != 0) {
                        mBmiscalelayout.setVisibility(View.VISIBLE);
                        mTxtbmi.setText(String.valueOf(b2) + " KG/M²");
                        initDataToSeekbar();
                    } else {
                        mBmiscalelayout.setVisibility(View.GONE);
                    }
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


    public String cmtofeet(int val) {
        String feetval;
        float mCmval = val;
        float feet = (float) (mCmval / 30.48);
        feetval = String.valueOf(feet);
        return feetval;
    }


    public int calculateBMI(float f, float f2) {
        return (int) (f2 / (f * f));
    }

    private float calculateHeightinCentimeter(float f) {
        return (float) ((int) (f * 100.0f));
    }

    public float calculateMetres(float f, float f2) {
        float f3 = (float) (((double) (f + (f2 / 12.0f))) / 3.28d);
        this.Heightincms = calculateHeightinCentimeter(f3);
        return f3;
    }

    public float calculateweight(float f) {
        return isBtnLb ? (float) (((double) f) * 0.453592d) : f;
    }

    private void initDataToSeekbar() {
        float floatExtra;
        Resources resources = null;
        int i = 0;
        TextView textView2 = null;
        String str = null;
        this.bmiseekBar.setEnabled(true);
        this.progressItemList = new ArrayList<>();
        this.mProgressItem = new ProgressItem();
        this.mProgressItem.progressItemPercentage = (this.bmivioletSpan / this.bmitotalSpan) * 100.0f;
        this.mProgressItem.color = R.color.violet;
        this.progressItemList.add(this.mProgressItem);
        this.mProgressItem = new ProgressItem();
        this.mProgressItem.progressItemPercentage = ((float) (this.bmiblueSpan / ((double) this.bmitotalSpan))) * 100.0f;
        this.mProgressItem.color = R.color.blue;
        this.progressItemList.add(this.mProgressItem);
        this.mProgressItem = new ProgressItem();
        this.mProgressItem.progressItemPercentage = ((float) (this.bmigreenSpan / ((double) this.bmitotalSpan))) * 100.0f;
        this.mProgressItem.color = R.color.green;
        this.progressItemList.add(this.mProgressItem);
        this.mProgressItem = new ProgressItem();
        this.mProgressItem.progressItemPercentage = (this.bmiyellowSpan / this.bmitotalSpan) * 100.0f;
        this.mProgressItem.color = R.color.yellow;
        this.progressItemList.add(this.mProgressItem);
        this.mProgressItem = new ProgressItem();
        this.mProgressItem.progressItemPercentage = (this.bmiorangeSpan / this.bmitotalSpan) * 100.0f;
        this.mProgressItem.color = R.color.orange;
        this.progressItemList.add(this.mProgressItem);
        this.mProgressItem = new ProgressItem();
        this.mProgressItem.progressItemPercentage = (this.bmiredSpan / this.bmitotalSpan) * 100.0f;
        this.mProgressItem.color = R.color.red;
        this.progressItemList.add(this.mProgressItem);
        this.bmiseekBar.initData(this.progressItemList);
        float floatExtra2 = mTinydb.getFloat(Constants.BMI_KEY);
        this.bmiseekBar.setProgress((int) floatExtra2);
        this.wght4.setText(String.valueOf(floatExtra2));
        if (floatExtra2 >= 0.0f && floatExtra2 <= 15.0f) {
            textView2 = this.wght2;
            str = "Severely Underweight";
        } else if (floatExtra2 >= 16.0f && floatExtra2 <= 18.0f) {
            textView2 = this.wght2;
            str = "Underweight";
        } else if (floatExtra2 >= 19.0f && floatExtra2 <= 25.0f) {
            textView2 = this.wght2;
            str = "Normal (Healthy Weight)";
        } else if (floatExtra2 >= 26.0f && floatExtra2 <= 30.0f) {
            textView2 = this.wght2;
            str = "Overweight";
        } else if (floatExtra2 < 31.0f || floatExtra2 > 40.0f) {
            if (floatExtra2 >= 41.0f && floatExtra2 <= 50.0f) {
                textView2 = this.wght2;
                str = "Severely Obese";
            }
            this.bmiseekBar.invalidate();
            this.bmiseekBar.setEnabled(false);
        } else {
            textView2 = this.wght2;
            str = "Obese";
        }
        try {
            textView2.setText(str);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(Activity_Userprofile.this, "Please Add Valid Data", Toast.LENGTH_SHORT).show();
        }
        this.bmiseekBar.invalidate();
        this.bmiseekBar.setEnabled(false);
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
        builder.withAdListener(new com.google.android.gms.ads.AdListener() {
            public void onAdFailedToLoad(int i) {
                Log.e("error", "Failed to load native ad:: " + i);
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
        String time = mTinydb.getString("alarmtime");
        if (time != null && !time.equals("")) {
            mTxtreminder.setText(time);
        }
        super.onResume();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Activity_Userprofile.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        overridePendingTransition(0, R.anim.fast_fade_out);
        finish();
    }

    public interface FragmentRefreshListener {
        void onRefresh();
    }
}
