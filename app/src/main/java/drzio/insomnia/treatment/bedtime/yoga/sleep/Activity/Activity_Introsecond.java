package drzio.insomnia.treatment.bedtime.yoga.sleep.Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.CityData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.CityModal;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.CountryModal;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.StateModal;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIClient;
import drzio.insomnia.treatment.bedtime.yoga.sleep.webservice.BackpainAPIInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Activity_Introsecond extends AppCompatActivity {
    private CardView mBtnnext;
    private TinyDB tinydb;
    public ArrayList<StateModal> mStatedata = new ArrayList<>();
    public ArrayList<CityModal> mCitydata = new ArrayList<>();
    ArrayList<String> mCountrylist = new ArrayList<>();
    ArrayList<String> mStatelist = new ArrayList<>();
    ArrayList<String> mStateidlist = new ArrayList<>();
    ArrayList<String> mCitylist = new ArrayList<>();
    ArrayList<String> mCityidlist = new ArrayList<>();
    private String mCountryname;
    private String mCountryid;
    private String mStatename;
    private String mStateid;
    private String cityname;
    private String mCityid;
    private SpinnerDialog Countrydial;
    private TextView mBtncountry;
    private SpinnerDialog statedialog;
    private TextView mBtnstate;
    private TextView mBtncity;
    private SpinnerDialog mCitydialog;
    public ArrayList<CountryModal> mCountrydata = new ArrayList<>();
    private ProgressDialog pd;
    private boolean success4;
    private TextView mTextbtn;
    private BackpainAPIInterface apiInterface;
    private boolean isClicked = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_introsecond);
        tinydb = new TinyDB(Activity_Introsecond.this);
        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(this, languages);

        mBtnnext = (CardView) findViewById(R.id.btnnxt);
        mBtncountry = (TextView) findViewById(R.id.btncountry);
        mBtnstate = (TextView) findViewById(R.id.btnstate);
        mBtncity = (TextView) findViewById(R.id.btncity);
        LinearLayout mSkipbtn = (LinearLayout) findViewById(R.id.txt_skip);
        mTextbtn = findViewById(R.id.textbtn);
        mTextbtn.setText("Next");
        mCountrydata.clear();
        Countrydata();
        for (int i = 0; i < mCountrydata.size(); i++) {
            CountryModal mdata = mCountrydata.get(i);
            mCountrylist.add(mdata.getName());
        }

        Countrydial = new SpinnerDialog(Activity_Introsecond.this, mCountrylist,
                "Search Country");

        Countrydial.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                try {
                    mTextbtn.setText("Next");
                    mStatename = "";
                    cityname = "";
                    mBtnstate.setText(getString(R.string.select_state));
                    mBtncity.setText(getString(R.string.select_city));
                    mStatedata.clear();
                    mStatelist.clear();
                    StateModal.mStatelist.clear();
                    mCountryname = item;
                    mBtncountry.setText(mCountryname);
                    mCountryid = mCountrydata.get(position).getId();
                    Statedata();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        mBtncountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Countrydial.showSpinerDialog();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        mBtnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mCountryname != null && !mCountryname.equals("")) {
                        tinydb.putString(Constants.COUNTRYID_KEY, mCountryid);
                        tinydb.putString(Constants.COUNTRYNAME_KEY, mCountryname);
                        if (mStatename != null && !mStatename.equals("")) {
                            tinydb.putString(Constants.STATENAME_KEY, mStatename);
                            tinydb.putString(Constants.STATEID_KEY, mStateid);
                            if (cityname != null && !cityname.equals("")) {
                                tinydb.putString(Constants.CITYNAME_KEY, cityname);
                                tinydb.putString(Constants.CITYID_KEY, mCityid);
                            }
                            Intent intent = new Intent(Activity_Introsecond.this, Activity_LoginScreen.class);
                            intent.putExtra("isFrom", "intro");
                            startActivity(intent);
                            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                            finish();
                        } else {
                            Toast.makeText(Activity_Introsecond.this, "Please Select State", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Activity_Introsecond.this, "Please Select Country", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        mSkipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isClicked) {
                    isClicked = true;
                    Intent intent = new Intent(Activity_Introsecond.this, Activity_LoginScreen.class);
                    intent.putExtra("isFrom", "intro");
                    startActivity(intent);
                    overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                    finish();
                }

            }
        });
    }


    public void Countrydata() {
        try {
            JSONArray m_jArry = new JSONArray(loadJSONFromAsset("country.json"));

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String id = jo_inside.getString("id");
                String name = jo_inside.getString("name");
                CountryModal countryModal = new CountryModal();
                countryModal.setId(id);
                countryModal.setName(name);
                mCountrydata.add(countryModal);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void Statedata() {
        try {
            JSONArray m_jArry = new JSONArray(loadJSONFromAsset("state.json"));

            for (int i = 0; i < m_jArry.length(); i++) {
                JSONObject jo_inside = m_jArry.getJSONObject(i);
                String id = jo_inside.getString("id");
                String name = jo_inside.getString("name");
                String country_id = jo_inside.getString("country_id");
                StateModal stateModal = new StateModal();
                stateModal.setId(id);
                stateModal.setName(name);
                stateModal.setCountry_id(country_id);
                mStatedata.add(stateModal);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < mStatedata.size(); i++) {
            StateModal mdata = mStatedata.get(i);
            if (mdata.getCountry_id().equals(mCountryid)) {
                mStatelist.add(mdata.getName());
                mStateidlist.add(mdata.getId());
            }
        }

        statedialog = new SpinnerDialog(Activity_Introsecond.this, mStatelist,
                "Search State");

        statedialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                try {
                    cityname = "";
                    mBtncity.setText(getString(R.string.select_city));
                    mCitydata.clear();
                    mCitylist.clear();
                    CityModal.mCitylis.clear();
                    mStatename = item;
                    mStateid = mStateidlist.get(position);
                    mBtnstate.setText(mStatename);
                    callCityApi(mStateid);
                    Log.e("mStateid",mStateid);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        mBtnstate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    statedialog.showSpinerDialog();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @SuppressLint("WrongConstant")
    public void callCityApi(String stateid) {
        try {
            this.apiInterface = (BackpainAPIInterface) BackpainAPIClient.getCelluliteClient().create(BackpainAPIInterface.class);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("state_id", stateid)
                    .build();
            this.apiInterface.getCity(requestBody).enqueue(new Callback<CityData>() {
                @Override
                public void onResponse(@NotNull Call<CityData> call, @NotNull Response<CityData> response) {
                    try {
                        CityData cityData = (CityData) response.body();
                        ArrayList<CityData.Datalist> datalists = cityData.datalists;
                        for (int i = 0; i < datalists.size(); i++) {
                            CityData.Datalist mdata = datalists.get(i);
                            mCitylist.add(mdata.getName());

                            mCityidlist.add(mdata.getId());
                        }

                        mCitydialog = new SpinnerDialog(Activity_Introsecond.this, mCitylist,
                                "Search City");

                        mCitydialog.bindOnSpinerListener(new OnSpinerItemClick() {
                            @Override
                            public void onClick(String item, int position) {
                                try {
                                    cityname = item;
                                    mBtncity.setText(cityname);
                                    mCityid = mCityidlist.get(position);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                        mBtncity.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                try {
                                    mCitydialog.showSpinerDialog();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(@NotNull Call<CityData> call, @NotNull Throwable t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset(String filename) {
        String json = null;
        try {
            InputStream is = getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
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

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Activity_Introsecond.this, Activity_Introfirst.class);
        startActivity(intent);
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_right);
        finish();
    }
}
