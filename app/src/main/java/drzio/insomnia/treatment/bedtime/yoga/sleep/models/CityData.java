package drzio.insomnia.treatment.bedtime.yoga.sleep.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CityData {
    @SerializedName("status")
    String status;
    @SerializedName("messsge")
    String message;
    @SerializedName("data")
    public ArrayList<Datalist> datalists;

    public static class Datalist {
        @SerializedName("id")
        private String id;
        @SerializedName("name")
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

}
