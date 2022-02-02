package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.covid19.disctrictWise;

import com.google.gson.annotations.SerializedName;

public class CovidDistrictWiseListDetailsModel {
    @SerializedName("active")
    long active;
    @SerializedName("confirmed")
    long confirmed;
    @SerializedName("deceased")
    long deceased;
    @SerializedName("delta")
    CovidDistrictWiseListDeltaDetailsModel delta;
    @SerializedName("district")
    String district;
    @SerializedName("recovered")
    long recovered;

    public CovidDistrictWiseListDetailsModel(String district2, long confirmed2, long deceased2, long recovered2, long active2, CovidDistrictWiseListDeltaDetailsModel delta2) {
        this.district = district2;
        this.confirmed = confirmed2;
        this.deceased = deceased2;
        this.recovered = recovered2;
        this.active = active2;
        this.delta = delta2;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district2) {
        this.district = district2;
    }

    public long getConfirmed() {
        return this.confirmed;
    }

    public void setConfirmed(long confirmed2) {
        this.confirmed = confirmed2;
    }

    public long getDeceased() {
        return this.deceased;
    }

    public void setDeceased(long deceased2) {
        this.deceased = deceased2;
    }

    public long getRecovered() {
        return this.recovered;
    }

    public void setRecovered(long recovered2) {
        this.recovered = recovered2;
    }

    public long getActive() {
        return this.active;
    }

    public void setActive(long active2) {
        this.active = active2;
    }

    public CovidDistrictWiseListDeltaDetailsModel getDelta() {
        return this.delta;
    }

    public void setDelta(CovidDistrictWiseListDeltaDetailsModel delta2) {
        this.delta = delta2;
    }
}
