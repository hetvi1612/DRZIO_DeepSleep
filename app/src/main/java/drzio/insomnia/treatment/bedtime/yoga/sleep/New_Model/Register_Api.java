package drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Register_Api {

    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("token")
    @Expose
    public String token;
    @SerializedName("docs")
    @Expose
    private Docs docs;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Register_Api withStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Register_Api withMessage(String message) {
        this.message = message;
        return this;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Register_Api withToken(String token) {
        this.token = token;
        return this;
    }

    public Docs getDocs() {
        return docs;
    }

    public void setDocs(Docs docs) {
        this.docs = docs;
    }

    public Register_Api withDocs(Docs docs) {
        this.docs = docs;
        return this;
    }
    public class Docs {

       /* @SerializedName("cart")
        @Expose
        private Cart cart;
        @SerializedName("premium")
        @Expose
        private Premium premium;
        @SerializedName("notification_tokens")
        @Expose
        private NotificationTokens notificationTokens;
        @SerializedName("notification_flags")
        @Expose
        private NotificationFlags notificationFlags;*/
        @SerializedName("language")
        @Expose
        private String language;
        @SerializedName("app_number")
        @Expose
        private Integer appNumber;
        @SerializedName("badge")
        @Expose
        private Integer badge;
        @SerializedName("_id")
        @Expose
        private String id;
       /* @SerializedName("referral")
        @Expose
        private Referral referral;
        @SerializedName("goal")
        @Expose
        private List<String> goal = null;*/
        @SerializedName("created_date")
        @Expose
        private String createdDate;
        @SerializedName("workout_time")
        @Expose
        private Integer workoutTime;
        @SerializedName("google")
        @Expose
        private String google;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("gender")
        @Expose
        private String gender;
        @SerializedName("age")
        @Expose
        private String age;
        @SerializedName("height")
        @Expose
        private String height;
        @SerializedName("weight")
        @Expose
        private String weight;
        @SerializedName("country")
        @Expose
        private String country;
        @SerializedName("state")
        @Expose
        private String state;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("tokens")
        @Expose
        private List<Object> tokens = null;
        @SerializedName("deviceId")
        @Expose
        private String deviceId;
        @SerializedName("f_name")
        @Expose
        private String fName;
        @SerializedName("l_name")
        @Expose
        private String lName;
        @SerializedName("createdAt")
        @Expose
        private String createdAt;
        @SerializedName("updatedAt")
        @Expose
        private String updatedAt;

      /*  public Cart getCart() {
            return cart;
        }

        public void setCart(Cart cart) {
            this.cart = cart;
        }

        public Docs withCart(Cart cart) {
            this.cart = cart;
            return this;
        }

        public Premium getPremium() {
            return premium;
        }

        public void setPremium(Premium premium) {
            this.premium = premium;
        }

        public Docs withPremium(Premium premium) {
            this.premium = premium;
            return this;
        }

        public NotificationTokens getNotificationTokens() {
            return notificationTokens;
        }

        public void setNotificationTokens(NotificationTokens notificationTokens) {
            this.notificationTokens = notificationTokens;
        }

        public Docs withNotificationTokens(NotificationTokens notificationTokens) {
            this.notificationTokens = notificationTokens;
            return this;
        }

        public NotificationFlags getNotificationFlags() {
            return notificationFlags;
        }

        public void setNotificationFlags(NotificationFlags notificationFlags) {
            this.notificationFlags = notificationFlags;
        }

        public Docs withNotificationFlags(NotificationFlags notificationFlags) {
            this.notificationFlags = notificationFlags;
            return this;
        }*/

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public Docs withLanguage(String language) {
            this.language = language;
            return this;
        }

        public Integer getAppNumber() {
            return appNumber;
        }

        public void setAppNumber(Integer appNumber) {
            this.appNumber = appNumber;
        }

        public Docs withAppNumber(Integer appNumber) {
            this.appNumber = appNumber;
            return this;
        }

        public Integer getBadge() {
            return badge;
        }

        public void setBadge(Integer badge) {
            this.badge = badge;
        }

        public Docs withBadge(Integer badge) {
            this.badge = badge;
            return this;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Docs withId(String id) {
            this.id = id;
            return this;
        }

       /* public Referral getReferral() {
            return referral;
        }

        public void setReferral(Referral referral) {
            this.referral = referral;
        }

        public Docs withReferral(Referral referral) {
            this.referral = referral;
            return this;
        }
*/
        /*public List<String> getGoal() {
            return goal;
        }

        public void setGoal(List<String> goal) {
            this.goal = goal;
        }

        public Docs withGoal(List<String> goal) {
            this.goal = goal;
            return this;
        }*/

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public Docs withCreatedDate(String createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        public Integer getWorkoutTime() {
            return workoutTime;
        }

        public void setWorkoutTime(Integer workoutTime) {
            this.workoutTime = workoutTime;
        }

        public Docs withWorkoutTime(Integer workoutTime) {
            this.workoutTime = workoutTime;
            return this;
        }

        public String getGoogle() {
            return google;
        }

        public void setGoogle(String google) {
            this.google = google;
        }

        public Docs withGoogle(String google) {
            this.google = google;
            return this;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Docs withEmail(String email) {
            this.email = email;
            return this;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public Docs withGender(String gender) {
            this.gender = gender;
            return this;
        }

        public String getAge() {
            return age;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public Docs withAge(String age) {
            this.age = age;
            return this;
        }

        public String getHeight() {
            return height;
        }

        public void setHeight(String height) {
            this.height = height;
        }

        public Docs withHeight(String height) {
            this.height = height;
            return this;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public Docs withWeight(String weight) {
            this.weight = weight;
            return this;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public Docs withCountry(String country) {
            this.country = country;
            return this;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public Docs withState(String state) {
            this.state = state;
            return this;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Docs withCity(String city) {
            this.city = city;
            return this;
        }

        public List<Object> getTokens() {
            return tokens;
        }

        public void setTokens(List<Object> tokens) {
            this.tokens = tokens;
        }

        public Docs withTokens(List<Object> tokens) {
            this.tokens = tokens;
            return this;
        }

        public String getDeviceId() {
            return deviceId;
        }

        public void setDeviceId(String deviceId) {
            this.deviceId = deviceId;
        }

        public Docs withDeviceId(String deviceId) {
            this.deviceId = deviceId;
            return this;
        }

        public String getfName() {
            return fName;
        }

        public void setfName(String fName) {
            this.fName = fName;
        }

        public Docs withfName(String fName) {
            this.fName = fName;
            return this;
        }

        public String getlName() {
            return lName;
        }

        public void setlName(String lName) {
            this.lName = lName;
        }

        public Docs withlName(String lName) {
            this.lName = lName;
            return this;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Docs withCreatedAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public Docs withUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

    }

}
