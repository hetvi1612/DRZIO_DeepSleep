package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.covid19.stateDaily;

import com.google.gson.annotations.SerializedName;

public class CovidStateDailyDetailsModel {
    @SerializedName("an")
    String f129an;
    @SerializedName("ap")
    String f130ap;
    @SerializedName("date")
    String date;
    @SerializedName("status")
    String status;

    public CovidStateDailyDetailsModel(String status2, String date2, String an, String ap) {
        this.status = status2;
        this.date = date2;
        this.f129an = an;
        this.f130ap = ap;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status2) {
        this.status = status2;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date2) {
        this.date = date2;
    }

    public String getAn() {
        return this.f129an;
    }

    public void setAn(String an) {
        this.f129an = an;
    }

    public String getAp() {
        return this.f130ap;
    }

    public void setAp(String ap) {
        this.f130ap = ap;
    }
}
