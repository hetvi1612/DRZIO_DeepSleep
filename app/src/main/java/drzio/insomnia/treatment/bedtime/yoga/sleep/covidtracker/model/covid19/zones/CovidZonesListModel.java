package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.covid19.zones;

import com.google.gson.annotations.SerializedName;

public class CovidZonesListModel {
    @SerializedName("zones")
    CovidZonesDetailsModel zones;

    public CovidZonesListModel(CovidZonesDetailsModel zones2) {
        this.zones = zones2;
    }

    public CovidZonesDetailsModel getZones() {
        return this.zones;
    }

    public void setZones(CovidZonesDetailsModel zones2) {
        this.zones = zones2;
    }
}
