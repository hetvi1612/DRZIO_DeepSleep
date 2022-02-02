package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.mathdro;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CountryInfo {
    @SerializedName("countries")
    public  ArrayList<Datalist> countrylist;


    public class Datalist{
        @SerializedName("name")
        String name;
        @SerializedName("iso2")
        String iso2;
        @SerializedName("iso3")
        String iso3;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIso2() {
            return iso2;
        }

        public void setIso2(String iso2) {
            this.iso2 = iso2;
        }

        public String getIso3() {
            return iso3;
        }

        public void setIso3(String iso3) {
            this.iso3 = iso3;
        }
    }

}
