package drzio.insomnia.treatment.bedtime.yoga.sleep.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class StoreBannerData {
    @SerializedName("status")
    public String status;
    @SerializedName("messsge")
    String message;
    @SerializedName("data")
    public ArrayList<Datalist> dataist;

    public static class Datalist {
        @SerializedName("id")
        String id;
        @SerializedName("image_name")
        String image_name;
        @SerializedName("cat_name")
        String cat_name;
        @SerializedName("link")
        String link;
        @SerializedName("is_active")
        String is_active;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImage_name() {
            return image_name;
        }

        public void setImage_name(String image_name) {
            this.image_name = image_name;
        }

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getIs_active() {
            return is_active;
        }

        public void setIs_active(String is_active) {
            this.is_active = is_active;
        }

    }
}
