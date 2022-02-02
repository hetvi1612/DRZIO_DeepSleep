package drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class Appdata {
    @SerializedName("status")
    String status;
    @SerializedName("messsge")
    String message;
    @SerializedName("data")
    public ArrayList<Datalist> dataist;

    public static class Datalist {
        @SerializedName("id")
        private String id;
        @SerializedName("app_name")
        private String app_name;
        @SerializedName("app_title")
        private String app_title;
        @SerializedName("app_desc")
        private String app_desc;
        @SerializedName("rating")
        private String rating;
        @SerializedName("image_name")
        private String image_name;
        @SerializedName("aap_link")
        private String aap_link;
        @SerializedName("cat_name")
        private String cat_name;
        @SerializedName("is_active")
        private String is_active;
        @SerializedName("sub_cat_name")
        private String subcatname;
        @SerializedName("image1")
        private String image1;
        @SerializedName("image2")
        private String image2;
        public static ArrayList<Datalist> appdataModelArrayList = new ArrayList<>();

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getApp_name() {
            return app_name;
        }

        public void setApp_name(String app_name) {
            this.app_name = app_name;
        }

        public String getApp_title() {
            return app_title;
        }

        public void setApp_title(String app_title) {
            this.app_title = app_title;
        }

        public String getApp_desc() {
            return app_desc;
        }

        public void setApp_desc(String app_desc) {
            this.app_desc = app_desc;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        public String getImage_name() {
            return image_name;
        }

        public void setImage_name(String image_name) {
            this.image_name = image_name;
        }

        public String getAap_link() {
            return aap_link;
        }

        public void setAap_link(String aap_link) {
            this.aap_link = aap_link;
        }

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }

        public String getIs_active() {
            return is_active;
        }

        public void setIs_active(String is_active) {
            this.is_active = is_active;
        }

        public String getSubcatname() {
            return subcatname;
        }

        public void setSubcatname(String subcatname) {
            this.subcatname = subcatname;
        }

        public String getImage1() {
            return image1;
        }

        public void setImage1(String image1) {
            this.image1 = image1;
        }

        public String getImage2() {
            return image2;
        }

        public void setImage2(String image2) {
            this.image2 = image2;
        }


        public static ArrayList<Datalist> getAppdataModelArrayList() {
            return appdataModelArrayList;
        }

        public static void setAppdataModelArrayList(ArrayList<Datalist> appdataModelArrayList) {
            Datalist.appdataModelArrayList = appdataModelArrayList;
        }
    }
}
