package drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public  class Banner {

    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("docs")
    @Expose
    public static List<Doc> docs = null;
    @SerializedName("BMR")
    @Expose
    private Double bmr;

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

    public Double getBmr() {
        return bmr;
    }

    public void setBmr(Double bmr) {
        this.bmr = bmr;
    }

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
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("app_number")
        @Expose
        private List<Long> appNumber = null;
        @SerializedName("visibility")
        @Expose
        private Boolean visibility;
        @SerializedName("click_count")
        @Expose
        private Long clickCount;
        @SerializedName("age")
        @Expose
        private String age;
        @SerializedName("user_type")
        @Expose
        private String userType;
        @SerializedName("weight")
        @Expose
        private String weight;
        @SerializedName("heightP")
        @Expose
        private String heightP;
        @SerializedName("height")
        @Expose
        private String height;
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
        @SerializedName("language")
        @Expose
        private String language;

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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public List<Long> getAppNumber() {
            return appNumber;
        }

        public void setAppNumber(List<Long> appNumber) {
            this.appNumber = appNumber;
        }

        public Boolean getVisibility() {
            return visibility;
        }

        public void setVisibility(Boolean visibility) {
            this.visibility = visibility;
        }

        public Long getClickCount() {
            return clickCount;
        }

        public void setClickCount(Long clickCount) {
            this.clickCount = clickCount;
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

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

    }
/*
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("app_number")
    @Expose
    private List<Integer> appNumber = null;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
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
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("notification_title")
    @Expose
    private String notificationTitle;
    @SerializedName("notification_desc")
    @Expose
    private String notificationDesc;
    @SerializedName("mode")
    @Expose
    private String mode;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Banner withImage(String image) {
        this.image = image;
        return this;
    }

    public List<Integer> getAppNumber() {
        return appNumber;
    }

    public void setAppNumber(List<Integer> appNumber) {
        this.appNumber = appNumber;
    }

    public Banner withAppNumber(List<Integer> appNumber) {
        this.appNumber = appNumber;
        return this;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Banner withCreatedDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Banner withId(String id) {
        this.id = id;
        return this;
    }

    public String getBannerType() {
        return bannerType;
    }

    public void setBannerType(String bannerType) {
        this.bannerType = bannerType;
    }

    public Banner withBannerType(String bannerType) {
        this.bannerType = bannerType;
        return this;
    }

    public String getDefaultType() {
        return defaultType;
    }

    public void setDefaultType(String defaultType) {
        this.defaultType = defaultType;
    }

    public Banner withDefaultType(String defaultType) {
        this.defaultType = defaultType;
        return this;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Banner withLink(String link) {
        this.link = link;
        return this;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Banner withAge(String age) {
        this.age = age;
        return this;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Banner withUserType(String userType) {
        this.userType = userType;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Banner withGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public Banner withNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
        return this;
    }

    public String getNotificationDesc() {
        return notificationDesc;
    }

    public void setNotificationDesc(String notificationDesc) {
        this.notificationDesc = notificationDesc;
    }

    public Banner withNotificationDesc(String notificationDesc) {
        this.notificationDesc = notificationDesc;
        return this;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Banner withMode(String mode) {
        this.mode = mode;
        return this;
    }*/

}
