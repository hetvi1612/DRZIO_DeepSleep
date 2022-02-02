package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.covid19.stateWise;

import com.google.gson.annotations.SerializedName;

public class CovidStateWiseListDetailsModel {
    @SerializedName("active")
    String active;
    @SerializedName("confirmed")
    String confirmed;
    @SerializedName("deaths")
    String deaths;
    @SerializedName("deltaconfirmed")
    String deltaconfirmed;
    @SerializedName("deltadeaths")
    String deltadeaths;
    @SerializedName("deltarecovered")
    String deltarecovered;
    @SerializedName("lastupdatedtime")
    String lastupdatedtime;
    @SerializedName("recovered")
    String recovered;
    @SerializedName("state")
    String state;
    @SerializedName("statecode")
    String statecode;
    @SerializedName("statenotes")
    String statenotes;

    public CovidStateWiseListDetailsModel(String active2, String confirmed2, String lastupdatedtime2, String deaths2, String recovered2, String state2, String statecode2, String deltaconfirmed2, String deltarecovered2, String deltadeaths2, String statenotes2) {
        this.active = active2;
        this.confirmed = confirmed2;
        this.lastupdatedtime = lastupdatedtime2;
        this.deaths = deaths2;
        this.recovered = recovered2;
        this.state = state2;
        this.statecode = statecode2;
        this.deltaconfirmed = deltaconfirmed2;
        this.deltarecovered = deltarecovered2;
        this.deltadeaths = deltadeaths2;
        this.statenotes = statenotes2;
    }

    public String getActive() {
        return this.active;
    }

    public void setActive(String active2) {
        this.active = active2;
    }

    public String getConfirmed() {
        return this.confirmed;
    }

    public void setConfirmed(String confirmed2) {
        this.confirmed = confirmed2;
    }

    public String getLastupdatedtime() {
        return this.lastupdatedtime;
    }

    public void setLastupdatedtime(String lastupdatedtime2) {
        this.lastupdatedtime = lastupdatedtime2;
    }

    public String getDeaths() {
        return this.deaths;
    }

    public void setDeaths(String deaths2) {
        this.deaths = deaths2;
    }

    public String getRecovered() {
        return this.recovered;
    }

    public void setRecovered(String recovered2) {
        this.recovered = recovered2;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state2) {
        this.state = state2;
    }

    public String getStatecode() {
        return this.statecode;
    }

    public void setStatecode(String statecode2) {
        this.statecode = statecode2;
    }

    public String getDeltaconfirmed() {
        return this.deltaconfirmed;
    }

    public void setDeltaconfirmed(String deltaconfirmed2) {
        this.deltaconfirmed = deltaconfirmed2;
    }

    public String getDeltarecovered() {
        return this.deltarecovered;
    }

    public void setDeltarecovered(String deltarecovered2) {
        this.deltarecovered = deltarecovered2;
    }

    public String getDeltadeaths() {
        return this.deltadeaths;
    }

    public void setDeltadeaths(String deltadeaths2) {
        this.deltadeaths = deltadeaths2;
    }

    public String getStatenotes() {
        return this.statenotes;
    }

    public void setStatenotes(String statenotes2) {
        this.statenotes = statenotes2;
    }
}
