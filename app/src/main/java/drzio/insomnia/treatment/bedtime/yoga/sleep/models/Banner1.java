package drzio.insomnia.treatment.bedtime.yoga.sleep.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Banner1 {
    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("docs")
    @Expose
    public List<Doc> docs = null;
 /*   @SerializedName("BMR")
    @Expose
    public Integer bmr;
*/
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Doc> getDocs() {
        return docs;
    }

    public void setDocs(List<Doc> docs) {
        this.docs = docs;
    }

 /*   public Integer getBmr() {
        return bmr;
    }

    public void setBmr(Integer bmr) {
        this.bmr = bmr;
    }
*/

    public class Doc {

        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("banner_type")
        @Expose
        private String bannerType;
        @SerializedName("default_type")
        @Expose
        private String defaultType;
        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("age")
        @Expose
        private String age;
        @SerializedName("user_type")
        @Expose
        private String userType;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("language")
        @Expose
        private String language;
        @SerializedName("weight")
        @Expose
        private String weight;
        @SerializedName("heightP")
        @Expose
        private String heightP;
        @SerializedName("height")
        @Expose
        private String height;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("app_number")
        @Expose
        private List<Integer> appNumber = null;
        @SerializedName("visibility")
        @Expose
        private Boolean visibility;
        @SerializedName("click_count")
        @Expose
        private Integer clickCount;
        @SerializedName("schedule_time")
        @Expose
        private String scheduleTime;
        @SerializedName("notification_title")
        @Expose
        private String notificationTitle;
        @SerializedName("notification_desc")
        @Expose
        private String notificationDesc;
        @SerializedName("mode")
        @Expose
        private String mode;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("updatedAt")
        @Expose
        private String updatedAt;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBannerType() {
            return bannerType;
        }

        public void setBannerType(String bannerType) {
            this.bannerType = bannerType;
        }

        public String getDefaultType() {
            return defaultType;
        }

        public void setDefaultType(String defaultType) {
            this.defaultType = defaultType;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getHeightP() {
            return heightP;
        }

        public void setHeightP(String heightP) {
            this.heightP = heightP;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public List<Integer> getAppNumber() {
            return appNumber;
        }

        public void setAppNumber(List<Integer> appNumber) {
            this.appNumber = appNumber;
        }

        public Boolean getVisibility() {
            return visibility;
        }

        public void setVisibility(Boolean visibility) {
            this.visibility = visibility;
        }

        public Integer getClickCount() {
            return clickCount;
        }

        public void setClickCount(Integer clickCount) {
            this.clickCount = clickCount;
        }

        public String getScheduleTime() {
            return scheduleTime;
        }

        public void setScheduleTime(String scheduleTime) {
            this.scheduleTime = scheduleTime;
        }

        public String getNotificationTitle() {
            return notificationTitle;
        }

        public void setNotificationTitle(String notificationTitle) {
            this.notificationTitle = notificationTitle;
        }

        public String getNotificationDesc() {
            return notificationDesc;
        }

        public void setNotificationDesc(String notificationDesc) {
            this.notificationDesc = notificationDesc;
        }

        public String getMode() {
            return mode;
        }

        public void setMode(String mode) {
            this.mode = mode;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

    }
}

