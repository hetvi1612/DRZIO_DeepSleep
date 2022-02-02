package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.covid19.disctrictWise;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CovidDistrictWiseListModel {
    @SerializedName("districtData")
    List<CovidDistrictWiseListDetailsModel> covidDistrictWiseListDetailsModels;
    @SerializedName("state")
    String state;

    public CovidDistrictWiseListModel(String state2, List<CovidDistrictWiseListDetailsModel> covidDistrictWiseListDetailsModels2) {
        this.state = state2;
        this.covidDistrictWiseListDetailsModels = covidDistrictWiseListDetailsModels2;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state2) {
        this.state = state2;
    }

    public List<CovidDistrictWiseListDetailsModel> getCovidDistrictWiseListDetailsModels() {
        return this.covidDistrictWiseListDetailsModels;
    }

    public void setCovidDistrictWiseListDetailsModels(List<CovidDistrictWiseListDetailsModel> covidDistrictWiseListDetailsModels2) {
        this.covidDistrictWiseListDetailsModels = covidDistrictWiseListDetailsModels2;
    }
}
