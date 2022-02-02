package drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bmrdata {

    @SerializedName("BMR")
    @Expose
    private Integer bmr;

    public Integer getBmr() {
        return bmr;
    }

    public void setBmr(Integer bmr) {
        this.bmr = bmr;


}
}
