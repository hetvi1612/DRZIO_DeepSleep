package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model;

import com.google.gson.annotations.SerializedName;


public class HistoryData {
    @SerializedName("data")
    public Datalist datalist;

    public class Datalist{
        @SerializedName("date")
        String date;
        @SerializedName("confirmed")
        String confirmed;
        @SerializedName("deaths")
        String deaths;
        @SerializedName("recovered")
        String recovered;


        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getConfirmed() {
            return confirmed;
        }

        public void setConfirmed(String confirmed) {
            this.confirmed = confirmed;
        }

        public String getDeaths() {
            return deaths;
        }

        public void setDeaths(String deaths) {
            this.deaths = deaths;
        }

        public String getRecovered() {
            return recovered;
        }

        public void setRecovered(String recovered) {
            this.recovered = recovered;
        }
    }

}
