package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.covid19.stateWise;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.covid19.CovidTimeDetailsModel;

public class CovidStateWiseListModel {
    @SerializedName("statewise")
    List<CovidStateWiseListDetailsModel> covidStateWiseListDetailsModels;
    @SerializedName("cases_time_series")
    List<CovidTimeDetailsModel> covidTimeDetailsModelList;

    public CovidStateWiseListModel(List<CovidStateWiseListDetailsModel> covidStateWiseListDetailsModels2) {
        this.covidStateWiseListDetailsModels = covidStateWiseListDetailsModels2;
    }

    public List<CovidStateWiseListDetailsModel> getCovidStateWiseListDetailsModels() {
        return this.covidStateWiseListDetailsModels;
    }

    public void setCovidStateWiseListDetailsModels(List<CovidStateWiseListDetailsModel> covidStateWiseListDetailsModels2) {
        this.covidStateWiseListDetailsModels = covidStateWiseListDetailsModels2;
    }

    public List<CovidTimeDetailsModel> getCovidTimeDetailsModelList() {
        return covidTimeDetailsModelList;
    }

    public void setCovidTimeDetailsModelList(List<CovidTimeDetailsModel> covidTimeDetailsModelList) {
        this.covidTimeDetailsModelList = covidTimeDetailsModelList;
    }
}
