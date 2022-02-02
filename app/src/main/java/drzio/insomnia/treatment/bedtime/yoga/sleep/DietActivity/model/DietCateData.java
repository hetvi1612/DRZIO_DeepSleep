package drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DietCateData {
    @SerializedName("status")
    String status;
    @SerializedName("messsge")
    String message;
    @SerializedName("data")
    public ArrayList<Datalist> dataist;

    public static class Datalist {
        @SerializedName("id")
        public String category_id;
        @SerializedName("name")
        public String catname;

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getCatname() {
            return catname;
        }

        public void setCatname(String catname) {
            this.catname = catname;
        }
    }

}
