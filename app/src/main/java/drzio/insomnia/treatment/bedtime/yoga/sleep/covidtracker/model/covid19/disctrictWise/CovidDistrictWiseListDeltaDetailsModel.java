package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.covid19.disctrictWise;

import com.google.gson.annotations.SerializedName;

public class CovidDistrictWiseListDeltaDetailsModel {
    @SerializedName("confirmed")
    long confirmed;
    @SerializedName("deceased")
    long deceased;
    @SerializedName("recovered")
    long recovered;

    public CovidDistrictWiseListDeltaDetailsModel(long confirmed2, long deceased2, long recovered2) {
        this.confirmed = confirmed2;
        this.deceased = deceased2;
        this.recovered = recovered2;
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
}
