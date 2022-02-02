package drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class TestSubcatdata {
    @SerializedName("id")
    public String subcatid;
    @SerializedName("name")
    public String subcatname;
    @SerializedName("data")
    public ArrayList<Appdata.Datalist> subcatdata;

}
