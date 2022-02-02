package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.covid19.zones;

import com.google.gson.annotations.SerializedName;

public class CovidZonesDetailsModel {
    @SerializedName("district")
    String district;
    @SerializedName("districtcode")
    String districtcode;
    @SerializedName("lastupdated")
    String lastupdated;
    @SerializedName("statecode")
    String statecode;
    @SerializedName("zone")
    String zone;

    public CovidZonesDetailsModel(String district2, String districtcode2, String lastupdated2, String statecode2, String zone2) {
        this.district = district2;
        this.districtcode = districtcode2;
        this.lastupdated = lastupdated2;
        this.statecode = statecode2;
        this.zone = zone2;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String district2) {
        this.district = district2;
    }

    public String getDistrictcode() {
        return this.districtcode;
    }

    public void setDistrictcode(String districtcode2) {
        this.districtcode = districtcode2;
    }

    public String getLastupdated() {
        return this.lastupdated;
    }

    public void setLastupdated(String lastupdated2) {
        this.lastupdated = lastupdated2;
    }

    public String getStatecode() {
        return this.statecode;
    }

    public void setStatecode(String statecode2) {
        this.statecode = statecode2;
    }

    public String getZone() {
        return this.zone;
    }

    public void setZone(String zone2) {
        this.zone = zone2;
    }
}
