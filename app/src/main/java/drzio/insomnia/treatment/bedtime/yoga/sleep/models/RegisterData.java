package drzio.insomnia.treatment.bedtime.yoga.sleep.models;

import com.google.gson.annotations.SerializedName;

public class RegisterData {
    @SerializedName("status")
    public String status;
    @SerializedName("messsge")
    String message;
    @SerializedName("data")
    public Datalist dataist;


    public static class Datalist {
        @SerializedName("id")
        private String id;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }


    }
}
