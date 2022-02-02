package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.covid19.disctrictWise.CovidDistrictWiseListDetailsModel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.covid19.disctrictWise.CovidDistrictWiseListModel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.webservice.ApiClient;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.webservice.ApiInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Activiy_DistrictData extends AppCompatActivity {
    public List<CovidDistrictWiseListDetailsModel> covidDistrictWiseListDetailsModels = new ArrayList();
    private String mStatename;
    private RecyclerView mRvdistrict;
    private Adapter_districtdata adapter_districtdata;
    private ProgressBar mLoadbar;
    private String mTcases;
    private String mTdeaths;
    private String mTrecovered;
    private TinyDB tinyDB;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        tinyDB = new TinyDB(Activiy_DistrictData.this);

        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(this, languages);

        setContentView(R.layout.activity_districtdata);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mStatename = bundle.getString("statename");
            mTcases = bundle.getString("tcases");
            mTdeaths = bundle.getString("tdeaths");
            mTrecovered = bundle.getString("trecovered");
        }
        ImageView mBtnback = (ImageView) findViewById(R.id.icback);
        mBtnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        TextView mTstatename = (TextView) findViewById(R.id.tvstatename);
        mTstatename.setText(mStatename);
        TextView mTvcasenum = (TextView) findViewById(R.id.tvcasenum);
        TextView mTvdeathnum = (TextView) findViewById(R.id.tvdeathnum);
        TextView mTvrecovernum = (TextView) findViewById(R.id.tvrecovernum);
        mTvcasenum.setText(mTcases);
        mTvdeathnum.setText(mTdeaths);
        mTvrecovernum.setText(mTrecovered);
        mRvdistrict = (RecyclerView) findViewById(R.id.rvdistricts);
        mLoadbar = (ProgressBar) findViewById(R.id.loadbar);
        mLoadbar.setVisibility(View.VISIBLE);
        getDistrictList(mStatename);
    }


    public void getDistrictList(String mStatename) {
        this.covidDistrictWiseListDetailsModels.clear();
        Call<List<CovidDistrictWiseListModel>> covidStateData = ((ApiInterface) ApiClient.getCOVIDClient(Boolean.valueOf(true)).create(ApiInterface.class)).getCovidDistrictData();
        String valueOf = String.valueOf(covidStateData.request().url());
        covidStateData.enqueue(new Callback<List<CovidDistrictWiseListModel>>() {
            public void onResponse(Call<List<CovidDistrictWiseListModel>> call, Response<List<CovidDistrictWiseListModel>> response) {
                int i = 0;
                while (i < ((List) response.body()).size()) {
                    try {
                        if (((CovidDistrictWiseListModel) ((List) response.body()).get(i)).getState().equals(mStatename)) {
                            covidDistrictWiseListDetailsModels.addAll(((CovidDistrictWiseListModel) ((List) response.body()).get(i)).getCovidDistrictWiseListDetailsModels());
                        }
                        i++;
                    } catch (Exception e) {
                        return;
                    }
                }
                try {
                    Collections.sort(covidDistrictWiseListDetailsModels, new Comparator<CovidDistrictWiseListDetailsModel>() {
                        @Override
                        public int compare(CovidDistrictWiseListDetailsModel o1, CovidDistrictWiseListDetailsModel o2) {
                            return Integer.valueOf(String.valueOf(o2.getConfirmed())).compareTo(Integer.valueOf(String.valueOf(o1.getConfirmed())));
                        }
                    });
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                if (mLoadbar != null) {
                    mLoadbar.setVisibility(View.GONE);
                }
                mRvdistrict.setLayoutManager(new LinearLayoutManager(Activiy_DistrictData.this, LinearLayoutManager.VERTICAL, false));
                adapter_districtdata = new Adapter_districtdata(Activiy_DistrictData.this, covidDistrictWiseListDetailsModels);
                mRvdistrict.setAdapter(adapter_districtdata);
            }

            public void onFailure(Call<List<CovidDistrictWiseListModel>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        Intent intent = new Intent(Activiy_DistrictData.this,MainActivity.class);
//        startActivity(intent);
//        finish();
    }
}
