package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.mathdro;

import com.google.gson.annotations.SerializedName;

public class WorldDataModel {
    @SerializedName("confirmed")
    WorldDataValueModel confirmed;
    @SerializedName("deaths")
    WorldDataValueModel deaths;
    @SerializedName("recovered")
    WorldDataValueModel recovered;
    @SerializedName("lastUpdate")
    String lastupdate;

    public WorldDataModel(WorldDataValueModel confirmed2, WorldDataValueModel recovered2, WorldDataValueModel deaths2, String lastupdate) {
        this.confirmed = confirmed2;
        this.recovered = recovered2;
        this.deaths = deaths2;
        this.lastupdate = lastupdate;
    }

    public WorldDataValueModel getConfirmed() {
        return this.confirmed;
    }

    public void setConfirmed(WorldDataValueModel confirmed2) {
        this.confirmed = confirmed2;
    }

    public WorldDataValueModel getRecovered() {
        return this.recovered;
    }

    public void setRecovered(WorldDataValueModel recovered2) {
        this.recovered = recovered2;
    }

    public WorldDataValueModel getDeaths() {
        return this.deaths;
    }

    public void setDeaths(WorldDataValueModel deaths2) {
        this.deaths = deaths2;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }
}
