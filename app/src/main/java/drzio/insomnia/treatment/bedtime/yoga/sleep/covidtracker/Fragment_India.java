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
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_webview;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.covid19.CovidTimeDetailsModel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.covid19.stateWise.CovidStateWiseListDetailsModel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.covid19.stateWise.CovidStateWiseListModel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.webservice.ApiClient;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.webservice.ApiInterface;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static drzio.insomnia.treatment.bedtime.yoga.sleep.Constants.TimeDetailsModelList;

//import com.covid.tracker.model.covid19.CovidTimeDetailsModel;
//import com.covid.tracker.model.covid19.stateWise.CovidStateWiseListDetailsModel;
//import com.covid.tracker.model.covid19.stateWise.CovidStateWiseListModel;
//import com.covid.tracker.webservice.ApiClient;
//import com.covid.tracker.webservice.ApiInterface;

//import static com.covid.tracker.Constants.TimeDetailsModelList;

public class Fragment_India extends Fragment {
    private View v;
    private TextView mTvcasenum;
    private TextView mTvdeathnum;
    private TextView mTvrecovernum;
    public List<CovidStateWiseListDetailsModel> covidStateWiseListDetailsModels = new ArrayList();
    private RecyclerView mRvstates;
    private Adapter_statelist adapterStatelist;
    private TextView mTvUpdated;
    private ProgressBar mLoadbar;
    public List<CovidTimeDetailsModel> covidTimeDetailsModelList = new ArrayList();
    private TextView mTvnewcasenum;
    private TextView mTvnewdeathnum;
    private TextView mTvnewrecovernum;
    private TinyDB tinyDB;


    public static Fragment_India getInstance(String title) {
        Fragment_India fi = new Fragment_India();
        return fi;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.v = inflater.inflate(R.layout.fragment_indiatracker, container, false);
        tinyDB = new TinyDB(getContext());

        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(getContext(), languages);
        return this.v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View rootView = view.findViewById(R.id.rootView);
        mTvcasenum = (TextView) view.findViewById(R.id.tvcasenum);
        mTvdeathnum = (TextView) view.findViewById(R.id.tvdeathnum);
        mTvrecovernum = (TextView) view.findViewById(R.id.tvrecovernum);
        mTvnewcasenum = (TextView) view.findViewById(R.id.tvnewcasenum);
        mTvnewdeathnum = (TextView) view.findViewById(R.id.tvnewdeathnum);
        mTvnewrecovernum = (TextView) view.findViewById(R.id.tvnewrecovernum);
        mTvUpdated = (TextView) view.findViewById(R.id.tvupdated);
        mRvstates = (RecyclerView) view.findViewById(R.id.rvstate);
        LinearLayout mLive = (LinearLayout) view.findViewById(R.id.btlive);
        mLive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Activity_webview.class);
                intent.putExtra("title", "Live Now");
                intent.putExtra("link", "https://drzio.com");
                startActivity(intent);
            }
        });
        mLoadbar = (ProgressBar) view.findViewById(R.id.loadbar);
        mLoadbar.setVisibility(View.VISIBLE);
        getCovidData();

    }


    public void getCovidData() {
        this.covidStateWiseListDetailsModels.clear();
        Call<CovidStateWiseListModel> covidStateData = ((ApiInterface) ApiClient.getCOVIDClient(Boolean.valueOf(true)).create(ApiInterface.class)).getCovidStateData();
        String valueOf = String.valueOf(covidStateData.request().url());
        covidStateData.enqueue(new Callback<CovidStateWiseListModel>() {
            public void onResponse(Call<CovidStateWiseListModel> call, Response<CovidStateWiseListModel> response) {
                try {
                    covidTimeDetailsModelList.addAll(((CovidStateWiseListModel) response.body()).getCovidTimeDetailsModelList());
                    covidStateWiseListDetailsModels.addAll(((CovidStateWiseListModel) response.body()).getCovidStateWiseListDetailsModels());
                    Utils.indiadatalist.addAll(((CovidStateWiseListModel) response.body()).getCovidStateWiseListDetailsModels());
                    int i = 0;
                    while (i < covidStateWiseListDetailsModels.size()) {
                        try {
                            if (((CovidStateWiseListDetailsModel) covidStateWiseListDetailsModels.get(i)).getStatecode().equals("TT")) {

                                mTvcasenum.setText((covidStateWiseListDetailsModels.get(i)).getConfirmed());
                                mTvdeathnum.setText((covidStateWiseListDetailsModels.get(i)).getDeaths());
                                mTvrecovernum.setText((covidStateWiseListDetailsModels.get(i)).getRecovered());
                                mTvnewcasenum.setText((covidStateWiseListDetailsModels.get(i)).getDeltaconfirmed());
                                mTvnewdeathnum.setText((covidStateWiseListDetailsModels.get(i)).getDeltadeaths());
                                mTvnewrecovernum.setText((covidStateWiseListDetailsModels.get(i)).getDeltarecovered());
                                StringBuilder sb2 = new StringBuilder();
                                sb2.append("Updated: ");
                                String temp = ((CovidStateWiseListDetailsModel) covidStateWiseListDetailsModels.get(i)).getLastupdatedtime();
                                temp = temp.replace("/", "-");
                                sb2.append(temp);
                                mTvUpdated.setText(sb2.toString());
                            }

                            i++;
                        } catch (Exception e3) {
                        }
                    }
                    int i2 = 0;
                    int temp = covidTimeDetailsModelList.size() - 12;
                    while (i2 < covidTimeDetailsModelList.size()) {
                        if (i2 > temp) {
                            CovidTimeDetailsModel data = new CovidTimeDetailsModel();
                            data.setDailyconfirmed(covidTimeDetailsModelList.get(i2).getDailyconfirmed());
                            data.setDailydeceased(covidTimeDetailsModelList.get(i2).getDailydeceased());
                            data.setDailyrecovered(covidTimeDetailsModelList.get(i2).getDailyrecovered());
                            data.setDate(covidTimeDetailsModelList.get(i2).getDate());
                            data.setTotalconfirmed(covidTimeDetailsModelList.get(i2).getTotalconfirmed());
                            data.setTotaldeceased(covidTimeDetailsModelList.get(i2).getTotaldeceased());
                            data.setTotalrecovered(covidTimeDetailsModelList.get(i2).getTotalrecovered());
                            TimeDetailsModelList.add(data);
                            Log.e("dates", covidTimeDetailsModelList.get(i2).getDate());
                        }
                        i2++;
                    }
                    if (mLoadbar != null) {
                        mLoadbar.setVisibility(View.GONE);
                    }
                    mRvstates.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
                    adapterStatelist = new Adapter_statelist(getActivity(), covidStateWiseListDetailsModels);
                    mRvstates.setAdapter(adapterStatelist);
                    Fragment_indiaraph fragmentIndiaraph = new Fragment_indiaraph();
                    FragmentTransaction ft2 = getActivity().getSupportFragmentManager().beginTransaction();
                    ft2.add(R.id.framecontainer, fragmentIndiaraph).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onFailure(Call<CovidStateWiseListModel> call, Throwable t) {
            }
        });
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
