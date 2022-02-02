package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.webservice;



import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.CountrycaseData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.HistoryData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.covid19.disctrictWise.CovidDistrictWiseListModel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.covid19.stateWise.CovidStateWiseListModel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.mathdro.CountryInfo;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.mathdro.WorldDataModel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.MusicApi;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    String URL_BASE = "http://52.66.231.200:7074/";

    @GET("v2/state_district_wise.json")
    Call<List<CovidDistrictWiseListModel>> getCovidDistrictData();

    @GET("data.json")
    Call<CovidStateWiseListModel> getCovidStateData();

    @GET("api")
    Call<WorldDataModel> getCovidWorldData();

    @GET("api/countries")
    Call<CountryInfo> getCovidCountrylist();

    @GET("api/countries/{name}")
    Call<WorldDataModel> getCovidCountryData(@Path(value = "name", encoded = true) String name);

    @GET("v2/countries")
    Call<List<CountrycaseData>> getCovidNewCountrylist();

    @GET("total")
    Call<HistoryData> getCovidHistorylist(@Query("date") String date);


    @Headers("Content-Type: application/json")
    @GET("music/displayMusic")
    Call<List<MusicApi>> getMusic();

}
