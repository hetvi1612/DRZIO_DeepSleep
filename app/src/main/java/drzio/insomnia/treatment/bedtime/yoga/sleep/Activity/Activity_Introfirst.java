package drzio.insomnia.treatment.bedtime.yoga.sleep.Activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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
import androidx.core.app.ActivityCompat;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;
import com.shawnlin.numberpicker.NumberPicker;

import java.io.IOException;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Activity_Introfirst extends AppCompatActivity
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private TextView mTxtage, mTxtgender, mTxtheight, mTxtweight, mTxtmeal;
    static TinyDB mTinydb;
    private String mGender = "Male";
    private int mealtype = 1;
    String usertype = "veg";
    private TextView mSwich2txt;
    private TextView pageSelected;
    private int mAgevalue = 25;
    private int mHcmvalue = 160;
    private int mHftvalue = 5;
    private int mHinchvalue = 0;
    private int mWkgvalue = 60;
    private int mWlbvalue = 130;
    private boolean isbtncm = true;
    private boolean isbtninch;
    private boolean isBtnkg = true;
    private boolean isBtnLb;
    private LocationManager locationManager;

    GoogleApiClient mGoogleApiClient;
    Location mLastLocation;
    LocationRequest mLocationRequest;
    private List<Address> addresses;
    String BASEURL;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_introfirst);
        if (Build.VERSION.SDK_INT >= 23) {
            requestPermissions(new String[]{/*"android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", */"android.permission.ACCESS_FINE_LOCATION", Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
        }
        mTxtage = (TextView) findViewById(R.id.txtage);
        mTxtgender = (TextView) findViewById(R.id.txtgender);
        mTxtheight = (TextView) findViewById(R.id.txtheight);
        mTxtweight = (TextView) findViewById(R.id.txtweight);
        mTxtmeal = (TextView) findViewById(R.id.txtmeal);
        mTinydb = new TinyDB(Activity_Introfirst.this);

        String getlan = Locale.getDefault().getDisplayLanguage();
        mTinydb = new TinyDB(this);
        Log.e("getDisplayLanguage", getlan);
        if (mTinydb.getBoolean(Constants.AppLanguage)) {
            String languages = mTinydb.getString(Constants.Language);
            Constants.languagechange(this, languages);
        } else {
            if (getlan.equals("hi")) {
                mTinydb.putString(Constants.Language, "hi");
                Constants.languagechange(this, "hi");
            } else {
                mTinydb.putString(Constants.Language, "en");
                Constants.languagechange(this, "en");
            }
        }


        subscribeToPushService();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (mGoogleApiClient != null) {
            mGoogleApiClient.connect();
        } else
            Toast.makeText(this, "Not Connected!", Toast.LENGTH_SHORT).show();

        CardView mBtnnext = (CardView) findViewById(R.id.btnnxt);

        isbtncm = true;
        isbtninch = false;

        isBtnkg = true;
        isBtnLb = false;
        RelativeLayout mBtage = (RelativeLayout) findViewById(R.id.btnage);
        RelativeLayout mBtgender = (RelativeLayout) findViewById(R.id.btngender);
        RelativeLayout mBtheight = (RelativeLayout) findViewById(R.id.btnheight);
        RelativeLayout mBtweight = (RelativeLayout) findViewById(R.id.btnweight);
        RelativeLayout mBtmeal = (RelativeLayout) findViewById(R.id.btnmeal);
        mTxtage.setText(String.valueOf(mAgevalue));
        mTxtgender.setText(mGender);
        isbtncm = true;
        isbtninch = false;
        String temp = mHftvalue + "." + mHinchvalue;
        mTxtheight.setText(temp + " ft inch");
        mTxtweight.setText(mWkgvalue + " kg");
        int mMeal = mealtype;
        if (mMeal == 1) {
            mTxtmeal.setText("Veg");
        } else if (mMeal == 2) {
            mTxtmeal.setText("Non-Veg");
        } else if (mMeal == 3) {
            mTxtmeal.setText("Vegan");
        }
        BASEURL = mTinydb.getString(Constants.NewBaseUrl);
          if (BASEURL.isEmpty())  {
            callfirebasedata();
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
        mBtnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTinydb.putString(Constants.GENDER_KEY, mGender);
                mTinydb.putInt(Constants.MEALTYPE_KEY, mealtype);
                mTinydb.putString(Constants.USERTYPEKEY, usertype);
                mTinydb.putInt(Constants.AGE_KEY, mAgevalue);
                if (isbtncm && mHcmvalue != 0) {
                    mTinydb.putBoolean(Constants.ISCM_KEY, true);
                    mTinydb.putFloat(Constants.HEIGHT_KEY, mHcmvalue);
                } else if (isbtninch && mHftvalue != 0) {
                    String temp = mHftvalue + "." + mHinchvalue;
                    mTinydb.putBoolean(Constants.ISCM_KEY, false);
                    mTinydb.putFloat(Constants.HEIGHT_KEY, Float.parseFloat(temp));
                }
                if (isBtnkg && mWkgvalue != 0) {
                    mTinydb.putBoolean(Constants.ISKG_KEY, true);
                    mTinydb.putInt(Constants.WEIGHT_KEY, mWkgvalue);
                } else if (isBtnLb && mWlbvalue != 0) {
                    mTinydb.putBoolean(Constants.ISKG_KEY, false);
                    mTinydb.putInt(Constants.WEIGHT_KEY, mWlbvalue);
                }
                Intent intent = new Intent(Activity_Introfirst.this, Activity_LoginScreen.class);
                intent.putExtra("isFrom", "intro");
                startActivity(intent);
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                finish();


            }
        });

       /* try {
            if (ActivityCompat.checkSelfPermission(Activity_Introfirst.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Activity_Introfirst.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Activity_Introfirst.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 101);
            } else {
                addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 10);
                Address address = addresses.get(0);
                //coutry = address.getCountryName();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    public void callfirebasedata() {


        try {
            FirebaseDatabase database = FirebaseDatabase.getInstance();

            // Log.e("database", String.valueOf(database));
            DatabaseReference mRefer = database.getReference();
            mRefer.child("ApiNames").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        String name = postSnapshot.getKey().toString();
                        Log.e("apiname", name);
                        if (name.equals("AppstoreApi")) {
                            mTinydb.putString(Constants.AppstoreUrl, (String) postSnapshot.getValue());
                            Log.e("Api1", (String) postSnapshot.getValue());
                        }
                        if (name.equals("DeepSleep")) {
                            mTinydb.putString(Constants.CelluliteUrl, (String) postSnapshot.getValue());
                            Log.e("Api2", (String) postSnapshot.getValue());
                        }
                        if (name.equals("YoutubeApi")) {
                            mTinydb.putString(Constants.YoutubeApi, (String) postSnapshot.getValue());
                            Log.e("Api3", (String) postSnapshot.getValue());
                        }
                        if (name.equals("Appstore_Appid")) {
                            mTinydb.putString(Constants.Appstore_Appid, (String) postSnapshot.getValue());
                            Log.e("Api4", (String) postSnapshot.getValue());
                        }
                        if (name.equals("Banner_id")) {
                            mTinydb.putString(Constants.Storebanner_id, (String) postSnapshot.getValue());
                            Log.e("Api5", (String) postSnapshot.getValue());
                        }
                        if (name.equals("NewsleepApi")) {
                           mTinydb.putString(Constants.NewBaseUrl, (String) postSnapshot.getValue());

//                          mTinydb.putString(Constants.NewBaseUrl, "http://65.0.77.129:7079/");
                            Log.e("Api6", (String) postSnapshot.getValue());
                            Log.e("Apitest", mTinydb.getString(Constants.NewBaseUrl));
                        }
                        if (name.equals("sleepimgurl")) {
                            mTinydb.putString(Constants.Backimageurl, (String) postSnapshot.getValue());
                            Log.e("Api7", (String) postSnapshot.getValue());
                        }
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {

        }

    }


    private void subscribeToPushService() {
        FirebaseMessaging.getInstance().subscribeToTopic("news");
        Log.d("AndroidBash", "Subscribed");
        String token = FirebaseInstanceId.getInstance().getToken();
        if (token != null) {
            Log.d("AndroidBash", token);
        }
    }


    public void DialogAgechange() {
        final Dialog dialog = new Dialog(Activity_Introfirst.this);
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
        final Dialog dialog = new Dialog(Activity_Introfirst.this);
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
        final Dialog dialog = new Dialog(Activity_Introfirst.this);
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
                } else if (isbtninch && mHftvalue != 0) {
                    mTinydb.putBoolean(Constants.ISCM_KEY, false);
                    String temp = mHftvalue + "." + mHinchvalue;
                    mTinydb.putFloat(Constants.HEIGHT_KEY, Float.parseFloat(temp));

                    mTxtheight.setText(temp + " ft inch");
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
            mSwich2txt.setTextColor(getResources().getColor(R.color.headercolor));
            mSwich2txt = null;
        }
    }

    private void mSwitchcminchselected(TextView selected) {
        mSwich2txt = selected;
        mSwich2txt.setBackground(getResources().getDrawable(R.drawable.switchselectedbg));
        mSwich2txt.setTextColor(Color.WHITE);
    }

    public void DialogWeightchange() {
        final Dialog dialog = new Dialog(Activity_Introfirst.this);
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
                } else if (isBtnLb && mWlbvalue != 0) {
                    mTinydb.putBoolean(Constants.ISKG_KEY, false);
                    mTinydb.putInt(Constants.WEIGHT_KEY, mWlbvalue);
                    mTxtweight.setText(mWlbvalue + " lb");
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
            pageSelected.setTextColor(getResources().getColor(R.color.headercolor));
            pageSelected = null;
        }
    }

    private void boxNewSelectedPageTab(TextView selected) {
        pageSelected = selected;
        pageSelected.setBackground(getResources().getDrawable(R.drawable.switchselectedbg));
        pageSelected.setTextColor(Color.WHITE);
    }

    public void DialogMealchange() {
        final Dialog dialog = new Dialog(Activity_Introfirst.this);
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
                mTxtveg.setTextColor(getResources().getColor(R.color.tbtncolor));
                mImgnonveg.setImageResource(R.drawable.nonvegunselect);
                mTxtnonveg.setTextColor(getResources().getColor(R.color.smalltxtcolor));
                mImgvegan.setImageResource(R.drawable.veganunselect);
                mTxtvegan.setTextColor(getResources().getColor(R.color.smalltxtcolor));
                mealtype = 1;
                usertype = "veg";

            }
        });
        mBtnnonveg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImgveg.setImageResource(R.drawable.vegunselect);
                mTxtveg.setTextColor(getResources().getColor(R.color.smalltxtcolor));
                mImgnonveg.setImageResource(R.drawable.nonvegselect);
                mTxtnonveg.setTextColor(getResources().getColor(R.color.tbtncolor));
                mImgvegan.setImageResource(R.drawable.veganunselect);
                mTxtvegan.setTextColor(getResources().getColor(R.color.smalltxtcolor));
                mealtype = 2;
                usertype = "nonveg";
            }
        });
        mBtnvegan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImgveg.setImageResource(R.drawable.vegunselect);
                mTxtveg.setTextColor(getResources().getColor(R.color.smalltxtcolor));
                mImgnonveg.setImageResource(R.drawable.nonvegunselect);
                mTxtnonveg.setTextColor(getResources().getColor(R.color.smalltxtcolor));
                mImgvegan.setImageResource(R.drawable.veganselect);
                mTxtvegan.setTextColor(getResources().getColor(R.color.tbtncolor));
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
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
       // settingRequest();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        settingRequest();
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "Connection Suspended!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Connection Failed!", Toast.LENGTH_SHORT).show();
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(this, 90000);
            } catch (IntentSender.SendIntentException e) {
                e.printStackTrace();
            }
        } else {
            Log.i("Current Location", "Location services connection failed with code " + connectionResult.getErrorCode());
        }
    }

    /*Method to get the enable location settings dialog*/
    public void settingRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(10000);    // 10 seconds, in milliseconds
        mLocationRequest.setFastestInterval(1000);   // 1 second, in milliseconds
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        PendingResult<LocationSettingsResult> result =
                LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient,
                        builder.build());

        result.setResultCallback(new ResultCallback<LocationSettingsResult>() {

            @Override
            public void onResult(@NonNull LocationSettingsResult result) {
                final Status status = result.getStatus();
        //        final LocationSettingsStates state = result.getLocationSettingsStates();
                switch (status.getStatusCode()) {
                    case LocationSettingsStatusCodes.SUCCESS:
                        // All location settings are satisfied. The client can
                        // initialize location requests here.
                        getLocation();
                        break;
                    case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                        // Location settings are not satisfied, but this can be fixed
                        // by showing the user a dialog.
                        try {
                            // Show the dialog by calling startResolutionForResult(),
                            // and check the result in onActivityResult().
                            status.startResolutionForResult(Activity_Introfirst.this, 1000);
                        } catch (IntentSender.SendIntentException e) {
                            // Ignore the error.
                        }
                        break;
                    case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                        // Location settings are not satisfied. However, we have no way
                        // to fix the settings so we won't show the dialog.
                        break;
                }
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {


        final LocationSettingsStates states = LocationSettingsStates.fromIntent(data);
        }catch (Exception e){

        }
        switch (requestCode) {
            case 1000:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        // All required changes were successfully made
                        try {


                            getLocation();
                        }catch (Exception e){

                        }

                        break;
                    case Activity.RESULT_CANCELED:
                        // The user was asked to change settings, but chose not to
                        Toast.makeText(this, "Location Service not Enabled", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                break;
        }
    }

    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        } else {
            /*Getting the location after aquiring location service*/
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);

            if (mLastLocation != null) {
                   /* _progressBar.setVisibility(View.INVISIBLE);
                    _latitude.setText("Latitude: " + String.valueOf(mLastLocation.getLatitude()));
                    _longitude.setText("Longitude: " + String.valueOf(mLastLocation.getLongitude()));*/
                getCountryName(this, mLastLocation.getLatitude(), mLastLocation.getLongitude());
            } else {
                /*if there is no last known location. Which means the device has no data for the loction currently.
                 * So we will get the current location.
                 * For this we'll implement Location Listener and override onLocationChanged*/
                Log.i("Current Location", "No data for location found");
                if (mGoogleApiClient != null)
                    if (!mGoogleApiClient.isConnected())
                        mGoogleApiClient.connect();
                if (mGoogleApiClient.isConnected()) {
                    LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, Activity_Introfirst.this);
                }
            }
        }
    }

    /*When Location changes, this method get called. */
    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
//            _progressBar.setVisibility(View.INVISIBLE);
//            _latitude.setText("Latitude: " + String.valueOf(mLastLocation.getLatitude()));
//            _longitude.setText("Longitude: " + String.valueOf(mLastLocation.getLongitude()));
        getCountryName(this, mLastLocation.getLatitude(), mLastLocation.getLongitude());

    }
  /*  public String getCountryName(Context context, double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            Address result;

            if (addresses != null && !addresses.isEmpty()) {
              *//*  Log.e("getCountryName", addresses.get(0).getCountryName());
                Log.e("getstateName", addresses.get(0).getAdminArea());
                Log.e("getcityName", addresses.get(0).getLocality());*//*
                String country_id = addresses.get(0).getCountryName();
                String state_id = addresses.get(0).getAdminArea();
                String city_id = addresses.get(0).getLocality();
                String countrycode = addresses.get(0).getCountryCode();
                getCurrencyCode(countrycode);
//               String CurrencyCode = String.valueOf(Currency.getInstance(countrycode));

              *//*  mTinydb.putString(Constants.COUNTRYID_KEY, country_id);
                mTinydb.putString(Constants.STATEID_KEY, state_id);
                mTinydb.putString(Constants.CITYID_KEY, city_id);*//*
                try {


                    TranslateAPI translateAPI = new TranslateAPI(
                            Language.AUTO_DETECT,
                            Language.ENGLISH, country_id
                    );

                    translateAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
                        @Override
                        public void onSuccess(String translatedText) {
                            Log.d("TAG", "onSuccess: " + translatedText);
                            //  textView.setText(translatedText);
                            //  vh2.mExcerName.setText(translatedText);
                            mTinydb.putString(Constants.COUNTRYID_KEY, translatedText);
                            Log.e("translatedText_contry", translatedText);
            *//*    t1=new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR) {
                            //  t1.setLanguage(Locale.UK);
                            Locale locale = new Locale("ar");

                            t1.setLanguage(locale);
                            t1.speak(translatedText, TextToSpeech.QUEUE_FLUSH, null);

                        }
                    }
                });
*//*
                        }

                        @Override
                        public void onFailure(String ErrorText) {
                            Log.d("TAG", "onFailure: " + ErrorText);
                        }
                    });
                    TranslateAPI translateAPI2 = new TranslateAPI(
                            Language.AUTO_DETECT,
                            Language.ENGLISH, state_id
                    );

                    translateAPI2.setTranslateListener(new TranslateAPI.TranslateListener() {
                        @Override
                        public void onSuccess(String translatedText) {
                            Log.d("TAG", "onSuccess: " + translatedText);
                            //  textView.setText(translatedText);
                            //  vh2.mExcerName.setText(translatedText);
                            mTinydb.putString(Constants.STATEID_KEY, translatedText);
                            Log.e("translatedText_state", translatedText);
            *//*    t1=new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR) {
                            //  t1.setLanguage(Locale.UK);
                            Locale locale = new Locale("ar");

                            t1.setLanguage(locale);
                            t1.speak(translatedText, TextToSpeech.QUEUE_FLUSH, null);

                        }
                    }
                });
*//*
                        }

                        @Override
                        public void onFailure(String ErrorText) {
                            Log.d("TAG", "onFailure: " + ErrorText);
                        }
                    });
                    TranslateAPI translateAPI1 = new TranslateAPI(
                            Language.AUTO_DETECT,
                            Language.ENGLISH, city_id
                    );

                    translateAPI1.setTranslateListener(new TranslateAPI.TranslateListener() {
                        @Override
                        public void onSuccess(String translatedText) {
                            Log.d("TAG", "onSuccess: " + translatedText);
                            //  textView.setText(translatedText);
                            //  vh2.mExcerName.setText(translatedText);
                            mTinydb.putString(Constants.CITYID_KEY, translatedText);
                            Log.e("translatedText_city", translatedText);
            *//*    t1=new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR) {
                            //  t1.setLanguage(Locale.UK);
                            Locale locale = new Locale("ar");

                            t1.setLanguage(locale);
                            t1.speak(translatedText, TextToSpeech.QUEUE_FLUSH, null);

                        }
                    }
                });
*//*
                        }

                        @Override
                        public void onFailure(String ErrorText) {
                            Log.d("TAG", "onFailure: " + ErrorText);
                        }
                    });
                } catch (Exception e) {

                }
                return addresses.get(0).getCountryName();
            }
            return null;
        } catch (IOException ignored) {
            //do something
        }
        return null;
    }*/




    public String getCountryName(Context context, double latitude, double longitude) {

        Locale locale =new Locale("en");
        Geocoder geocoder = new Geocoder(context, locale);
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            Address result;

            if (addresses != null && !addresses.isEmpty()) {
              /*  Log.e("getCountryName", addresses.get(0).getCountryName());
                Log.e("getstateName", addresses.get(0).getAdminArea());
                Log.e("getcityName", addresses.get(0).getLocality());*/
                String country_id = addresses.get(0).getCountryName();
                String state_id = addresses.get(0).getAdminArea();
                String city_id = addresses.get(0).getLocality();
                String countrycode = addresses.get(0).getCountryCode();
                getCurrencyCode(countrycode);
//               String CurrencyCode = String.valueOf(Currency.getInstance(countrycode));

                mTinydb.putString(Constants.COUNTRYID_KEY, country_id);
                mTinydb.putString(Constants.STATEID_KEY, state_id);
                mTinydb.putString(Constants.CITYID_KEY, city_id);
            /*    try {


                    TranslateAPI translateAPI = new TranslateAPI(
                            Language.AUTO_DETECT,
                            Language.ENGLISH, country_id
                    );

                    translateAPI.setTranslateListener(new TranslateAPI.TranslateListener() {
                        @Override
                        public void onSuccess(String translatedText) {
                            Log.d("TAG", "onSuccess: " + translatedText);
                            //  textView.setText(translatedText);
                            //  vh2.mExcerName.setText(translatedText);
                            mTinydb.putString(Constants.COUNTRYID_KEY, translatedText);
                            Log.e("translatedText_contry", translatedText);
            *//*    t1=new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR) {
                            //  t1.setLanguage(Locale.UK);
                            Locale locale = new Locale("ar");

                            t1.setLanguage(locale);
                            t1.speak(translatedText, TextToSpeech.QUEUE_FLUSH, null);

                        }
                    }
                });
*//*
                        }

                        @Override
                        public void onFailure(String ErrorText) {
                            Log.d("TAG", "onFailure: " + ErrorText);
                        }
                    });
                    TranslateAPI translateAPI2 = new TranslateAPI(
                            Language.AUTO_DETECT,
                            Language.ENGLISH, state_id
                    );

                    translateAPI2.setTranslateListener(new TranslateAPI.TranslateListener() {
                        @Override
                        public void onSuccess(String translatedText) {
                            Log.d("TAG", "onSuccess: " + translatedText);
                            //  textView.setText(translatedText);
                            //  vh2.mExcerName.setText(translatedText);
                            mTinydb.putString(Constants.STATEID_KEY, translatedText);
                            Log.e("translatedText_state", translatedText);
            *//*    t1=new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR) {
                            //  t1.setLanguage(Locale.UK);
                            Locale locale = new Locale("ar");

                            t1.setLanguage(locale);
                            t1.speak(translatedText, TextToSpeech.QUEUE_FLUSH, null);

                        }
                    }
                });
*//*
                        }

                        @Override
                        public void onFailure(String ErrorText) {
                            Log.d("TAG", "onFailure: " + ErrorText);
                        }
                    });
                    TranslateAPI translateAPI1 = new TranslateAPI(
                            Language.AUTO_DETECT,
                            Language.ENGLISH, city_id
                    );

                    translateAPI1.setTranslateListener(new TranslateAPI.TranslateListener() {
                        @Override
                        public void onSuccess(String translatedText) {
                            Log.d("TAG", "onSuccess: " + translatedText);
                            //  textView.setText(translatedText);
                            //  vh2.mExcerName.setText(translatedText);
                            mTinydb.putString(Constants.CITYID_KEY, translatedText);
                            Log.e("translatedText_city", translatedText);
            *//*    t1=new TextToSpeech(context, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if(status != TextToSpeech.ERROR) {
                            //  t1.setLanguage(Locale.UK);
                            Locale locale = new Locale("ar");

                            t1.setLanguage(locale);
                            t1.speak(translatedText, TextToSpeech.QUEUE_FLUSH, null);

                        }
                    }
                });
*//*
                        }

                        @Override
                        public void onFailure(String ErrorText) {
                            Log.d("TAG", "onFailure: " + ErrorText);
                        }
                    });
                } catch (Exception e) {

                }*/
                return addresses.get(0).getCountryName();
            }
            return null;
        } catch (IOException ignored) {
            //do something
        }
        return null;
    }


/*
    public String getCountryName(Context context, double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocation(latitude, longitude, 1);
            Address result;

            if (addresses != null && !addresses.isEmpty()) {
              */
/*  Log.e("getCountryName", addresses.get(0).getCountryName());
                Log.e("getstateName", addresses.get(0).getAdminArea());
                Log.e("getcityName", addresses.get(0).getLocality());*//*


                String country_id = addresses.get(0).getCountryName();
                String state_id = addresses.get(0).getAdminArea();
                String city_id = addresses.get(0).getLocality();
                String ZIP = addresses.get(0).getPostalCode();

                String countrycode = addresses.get(0).getCountryCode();
                getCurrencyCode(countrycode);
                mTinydb.putString(Constants.COUNTRYID_KEY, country_id);
                mTinydb.putString(Constants.STATEID_KEY, state_id);
                mTinydb.putString(Constants.CITYID_KEY, city_id);
                //  mTinydb.putString(Constants.COUNTRY_CODE, String.valueOf(addresses.get(0).getCountryCode()));


                return addresses.get(0).getCountryName();
            }
            return null;
        } catch (IOException ignored) {
            //do something
        }
        return null;
    }
*/

    public static void getCurrencyCode(String countryCode) {
        try {
            String s = Currency.getInstance(new Locale("", countryCode)).getCurrencyCode();
            mTinydb.putString(Constants.COUNTRY_CODEID, s);

            Log.e("ssssssssssss", s);
         //   return Currency.getInstance(new Locale("", countryCode)).getCurrencyCode();
        }catch (Exception e){

        }
    }
}
