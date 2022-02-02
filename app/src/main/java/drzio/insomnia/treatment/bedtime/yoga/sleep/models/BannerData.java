package drzio.insomnia.treatment.bedtime.yoga.sleep.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BannerData {
    @SerializedName("status")
    String status;
    @SerializedName("messsge")
    String message;
    @SerializedName("data")
    public ArrayList<Datalist> dataist;


    public static class Datalist {
        @SerializedName("banner_image")
        String banner_image;
        @SerializedName("link_option")
        String link_option;
        @SerializedName("banner_link")
        String banner_link;
        @SerializedName("default_type")
        String default_type;
        @SerializedName("ads_type")
        String ads_type;

        public String getBanner_image() {
            return banner_image;
        }

        public void setBanner_image(String banner_image) {
            this.banner_image = banner_image;
        }

        public String getLink_option() {
            return link_option;
        }

        public void setLink_option(String link_option) {
            this.link_option = link_option;
        }

        public String getBanner_link() {
            return banner_link;
        }

        public void setBanner_link(String banner_link) {
            this.banner_link = banner_link;
        }

        public String getDefault_type() {
            return default_type;
        }

        public void setDefault_type(String default_type) {
            this.default_type = default_type;
        }

        public String getAds_type() {
            return ads_type;
        }

        public void setAds_type(String ads_type) {
            this.ads_type = ads_type;
        }
    }
}
