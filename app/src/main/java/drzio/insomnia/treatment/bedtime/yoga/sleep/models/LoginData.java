package drzio.insomnia.treatment.bedtime.yoga.sleep.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginData {
    @SerializedName("status")
    public String status;
    @SerializedName("messsge")
    String message;
    @SerializedName("data")
    public Datalist dataist;


    public static class Datalist implements Serializable {
        @SerializedName("id")
        private String id;
        @SerializedName("first_name")
        private String first_name;
        @SerializedName("last_name")
        private String last_name;
        @SerializedName("email")
        private String email;
        @SerializedName("mobile_number")
        private String mobile_number;
        @SerializedName("image")
        private String image;
        @SerializedName("gender")
        private String gender;
        @SerializedName("age")
        private String age;
        @SerializedName("height")
        private String height;
        @SerializedName("weight")
        private String weight;
        @SerializedName("paid_status")
        private String paid_status;
        @SerializedName("google")
        private String google;
        @SerializedName("facebook")
        private String facebook;
        @SerializedName("user_type")
        private String user_type;
        @SerializedName("countryname")
        private String countryname;
        @SerializedName("statename")
        private String statename;
        @SerializedName("cityname")
        private String cityname;
        @SerializedName("insert_datetime")
        private String insert_datetime;
        @SerializedName("login_time")
        private String login_time;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFirst_name() {
            return first_name;
        }

        public void setFirst_name(String first_name) {
            this.first_name = first_name;
        }

        public String getLast_name() {
            return last_name;
        }

        public void setLast_name(String last_name) {
            this.last_name = last_name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getMobile_number() {
            return mobile_number;
        }

        public void setMobile_number(String mobile_number) {
            this.mobile_number = mobile_number;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getPaid_status() {
            return paid_status;
        }

        public void setPaid_status(String paid_status) {
            this.paid_status = paid_status;
        }

        public String getGoogle() {
            return google;
        }

        public void setGoogle(String google) {
            this.google = google;
        }

        public String getFacebook() {
            return facebook;
        }

        public void setFacebook(String facebook) {
            this.facebook = facebook;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getCountryname() {
            return countryname;
        }

        public void setCountryname(String countryname) {
            this.countryname = countryname;
        }

        public String getStatename() {
            return statename;
        }

        public void setStatename(String statename) {
            this.statename = statename;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public String getInsert_datetime() {
            return insert_datetime;
        }

        public void setInsert_datetime(String insert_datetime) {
            this.insert_datetime = insert_datetime;
        }

        public String getLogin_time() {
            return login_time;
        }

        public void setLogin_time(String login_time) {
            this.login_time = login_time;
        }

    }
}
