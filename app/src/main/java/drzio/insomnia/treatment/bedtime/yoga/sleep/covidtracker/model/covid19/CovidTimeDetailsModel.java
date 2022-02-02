package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.covid19;

import com.google.gson.annotations.SerializedName;

public class CovidTimeDetailsModel {
    @SerializedName("dailyconfirmed")
    String dailyconfirmed;
    @SerializedName("dailydeceased")
    String dailydeceased;
    @SerializedName("dailyrecovered")
    String dailyrecovered;
    @SerializedName("date")
    String date;
    @SerializedName("totalconfirmed")
    String totalconfirmed;
    @SerializedName("totaldeceased")
    String totaldeceased;
    @SerializedName("totalrecovered")
    String totalrecovered;

    public String getDailyconfirmed() {
        return dailyconfirmed;
    }

    public void setDailyconfirmed(String dailyconfirmed) {
        this.dailyconfirmed = dailyconfirmed;
    }

    public String getDailydeceased() {
        return dailydeceased;
    }

    public void setDailydeceased(String dailydeceased) {
        this.dailydeceased = dailydeceased;
    }

    public String getDailyrecovered() {
        return dailyrecovered;
    }

    public void setDailyrecovered(String dailyrecovered) {
        this.dailyrecovered = dailyrecovered;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTotalconfirmed() {
        return totalconfirmed;
    }

    public void setTotalconfirmed(String totalconfirmed) {
        this.totalconfirmed = totalconfirmed;
    }

    public String getTotaldeceased() {
        return totaldeceased;
    }

    public void setTotaldeceased(String totaldeceased) {
        this.totaldeceased = totaldeceased;
    }

    public String getTotalrecovered() {
        return totalrecovered;
    }

    public void setTotalrecovered(String totalrecovered) {
        this.totalrecovered = totalrecovered;
    }
}
