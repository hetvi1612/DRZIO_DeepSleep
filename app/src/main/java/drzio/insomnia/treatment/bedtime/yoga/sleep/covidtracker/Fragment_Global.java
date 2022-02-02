package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_webview;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.CountrycaseData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.HistoryData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.mathdro.CountryData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.mathdro.WorldDataModel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.webservice.ApiClient;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.webservice.ApiInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.Globhistorydatalist;

//import com.covid.tracker.model.CountrycaseData;
//import com.covid.tracker.model.HistoryData;
//import com.covid.tracker.model.mathdro.CountryData;
//import com.covid.tracker.model.mathdro.WorldDataModel;
//import com.covid.tracker.webservice.ApiClient;
//import com.covid.tracker.webservice.ApiInterface;

//import static com.covid.tracker.Constants.Globhistorydatalist;

public class Fragment_Global extends Fragment {
    private static final String TAG = "TAG " + Fragment_Global.class.getSimpleName();

    private View v;
    private TextView mTvcasenum;
    private TextView mTvdeathnum;
    private TextView mTvrecovernum;
    private RecyclerView mRvstates;
    private Adapter_countrylist adapterStatelist;
    private TextView mTvUpdated;
    private ArrayList<CountryData> countryDataArrayList = new ArrayList<>();
    private ProgressBar mLoadbar;
    ArrayList<String> mDatelist = new ArrayList<>();
    private TinyDB tinyDB;


    public static Fragment_Global getInstance(String title) {
        Fragment_Global fi = new Fragment_Global();
        return fi;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.v = inflater.inflate(R.layout.fragment_worldtracker, container, false);
        tinyDB = new TinyDB(getContext());

        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(getContext(), languages);
        return this.v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTvcasenum = (TextView) view.findViewById(R.id.tvcasenum);
        mTvdeathnum = (TextView) view.findViewById(R.id.tvdeathnum);
        mTvrecovernum = (TextView) view.findViewById(R.id.tvrecovernum);
        mTvUpdated = (TextView) view.findViewById(R.id.tvupdated);
        mRvstates = (RecyclerView) view.findViewById(R.id.rvstate);
        mLoadbar = (ProgressBar) view.findViewById(R.id.loadbar);
        mLoadbar.setVisibility(View.VISIBLE);
        LinearLayout mLive = (LinearLayout) view.findViewById(R.id.btlive);
        mLive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_webview.class);
                intent.putExtra("title","Live Now");
                intent.putExtra("link","https://drzio.com");
                startActivity(intent);
            }
        });
        getCovidWorldData();
        getCovidCoutryData();
//        LoagGraph();

    }


    public static String getCalculatedDate(String date, String dateFormat, int days) {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat s = new SimpleDateFormat(dateFormat);
        if (!date.isEmpty()) {
            try {
                cal.setTime(s.parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        cal.add(Calendar.DAY_OF_YEAR, days);
        return s.format(new Date(cal.getTimeInMillis()));
    }



    public void getCovidWorldData() {
        Call<WorldDataModel> covidWorldData = ((ApiInterface) ApiClient.getMathdroClient(Boolean.valueOf(true)).create(ApiInterface.class)).getCovidWorldData();
        String valueOf = String.valueOf(covidWorldData.request().url());
        covidWorldData.enqueue(new Callback<WorldDataModel>() {
            public void onResponse(Call<WorldDataModel> call, Response<WorldDataModel> response) {
                try {
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Updated: ");
                    sb2.append(((WorldDataModel) response.body()).getLastupdate());
                    mTvUpdated.setText(sb2.toString());
                    mTvcasenum.setText(numberFormat(((WorldDataModel) response.body()).getConfirmed().getValue()));
                    mTvrecovernum.setText(numberFormat(((WorldDataModel) response.body()).getRecovered().getValue()));
                    mTvdeathnum.setText(numberFormat(((WorldDataModel) response.body()).getDeaths().getValue()));
                } catch (Exception e) {
                }
            }

            public void onFailure(Call<WorldDataModel> call, Throwable t) {
            }
        });
    }

    public void getCovidCoutryData() {
        Call<List<CountrycaseData>> countrylist = ((ApiInterface) ApiClient.getWorldClient()
                .create(ApiInterface.class)).getCovidNewCountrylist();
        countrylist.enqueue(new Callback<List<CountrycaseData>>() {
            public void onResponse(Call<List<CountrycaseData>> call, Response<List<CountrycaseData>> response) {
                try {
                    for (int i = 0; i < response.body().size(); i++) {
                        CountryData data = new CountryData();
                        data.setCountryname((response.body().get(i).getCountry()));
                        data.setTotalcases((response.body().get(i).getCases()));
                        data.setTotaldeaths((response.body().get(i).getDeaths()));
                        data.setTotalrecover((response.body().get(i).getRecovered()));
                        data.setNewcases((response.body().get(i).getTodayCases()));
                        data.setNewdeaths((response.body().get(i).getTodayDeaths()));
                        countryDataArrayList.add(data);
                    }
                    if (mLoadbar != null) {
                        mLoadbar.setVisibility(View.GONE);
                    }
                    mRvstates.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    adapterStatelist = new Adapter_countrylist(getActivity(), countryDataArrayList);
                    mRvstates.setAdapter(adapterStatelist);

                } catch (Exception e) {
                }
            }

            public void onFailure(Call<List<CountrycaseData>> call, Throwable t) {
               // Log.e("Dataerror", t.getMessage());
            }
        });
    }


    public void getCovidHistoryData(String Query, final boolean isLast) {
        Call<HistoryData> countrylist = ((ApiInterface) ApiClient.getWorldClient2()
                .create(ApiInterface.class)).getCovidHistorylist(Query);
        countrylist.enqueue(new Callback<HistoryData>() {
            public void onResponse(Call<HistoryData> call, Response<HistoryData> response) {
                HistoryData.Datalist data = ((HistoryData) response.body()).datalist;
                Globhistorydatalist.add(data);
            }

            public void onFailure(Call<HistoryData> call, Throwable t) {
                Log.e("Dataerror", Query);
            }
        });
    }


    public static String numberFormat(int number) {
        return NumberFormat.getNumberInstance(Locale.CHINESE).format((long) number);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {

        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
