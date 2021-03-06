package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model;

import com.google.gson.annotations.SerializedName;

public class CountrycaseData {
    @SerializedName("country")
    String country;
    @SerializedName("cases")
    String cases;
    @SerializedName("todayCases")
    String todayCases;
    @SerializedName("deaths")
    String deaths;
    @SerializedName("todayDeaths")
    String todayDeaths;
    @SerializedName("recovered")
    String recovered;
    @SerializedName("active")
    String active;
    @SerializedName("critical")
    String critical;


    public CountrycaseData(String country, String cases, String todayCases, String deaths, String todayDeaths, String recovered, String active, String critical) {
        this.country = country;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
        this.active = active;
        this.critical = critical;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(String todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCritical() {
        return critical;
    }

    public void setCritical(String critical) {
        this.critical = critical;
    }
}
