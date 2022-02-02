package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.covid19;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CovidTimeListModel {
    @SerializedName("cases_time_series")
    List<CovidTimeDetailsModel> covidTimeDetailsModelList;

    public CovidTimeListModel(List<CovidTimeDetailsModel> covidTimeDetailsModelList) {
        this.covidTimeDetailsModelList = covidTimeDetailsModelList;
    }

    public List<CovidTimeDetailsModel> getCovidTimeDetailsModelList() {
        return covidTimeDetailsModelList;
    }

    public void setCovidTimeDetailsModelList(List<CovidTimeDetailsModel> covidTimeDetailsModelList) {
        this.covidTimeDetailsModelList = covidTimeDetailsModelList;
    }
}
