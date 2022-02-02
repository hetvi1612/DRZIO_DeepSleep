package drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ExituserData {





    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("user")
    @Expose
    private User user;

    public boolean isClickStatus() {
        return clickStatus;
    }

    public void setClickStatus(boolean clickStatus) {
        this.clickStatus = clickStatus;
    }

    @SerializedName("clickStatus")
    @Expose
    private boolean clickStatus;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public ExituserData withStatus(Boolean status) {
        this.status = status;
        return this;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public ExituserData withToken(String token) {
        this.token = token;
        return this;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ExituserData withUser(User user) {
        this.user = user;
        return this;
    }

    public class User {

        @SerializedName("referral")
        @Expose
        private Referral referral;
        @SerializedName("coin")
        @Expose
        private Integer coin;
        @SerializedName("workout_time")
        @Expose
        private Integer workoutTime;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("badge")
        @Expose
        private Integer badge;

        @SerializedName("exercise")
        @Expose
        private Integer exercise;
        @SerializedName("burnt_calory")
        @Expose
        private Integer burntCalory;
        public Referral getReferral() {
            return referral;
        }

        public void setReferral(Referral referral) {
            this.referral = referral;
        }

        public User withReferral(Referral referral) {
            this.referral = referral;
            return this;
        }

        public Integer getCoin() {
            return coin;
        }

        public void setCoin(Integer coin) {
            this.coin = coin;
        }

        public User withCoin(Integer coin) {
            this.coin = coin;
            return this;
        }

        public Integer getWorkoutTime() {
            return workoutTime;
        }

        public void setWorkoutTime(Integer workoutTime) {
            this.workoutTime = workoutTime;
        }

        public User withWorkoutTime(Integer workoutTime) {
            this.workoutTime = workoutTime;
            return this;
        }
        public Integer getExercise() {
            return exercise;
        }

        public void setExercise(Integer exercise) {
            this.exercise = exercise;
        }
        public Integer getBurntCalory() {
            return burntCalory;
        }

        public void setBurntCalory(Integer burntCalory) {
            this.burntCalory = burntCalory;
        }
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public User withId(String id) {
            this.id = id;
            return this;
        }

        public Integer getBadge() {
            return badge;
        }

        public void setBadge(Integer badge) {
            this.badge = badge;
        }

        public User withBadge(Integer badge) {
            this.badge = badge;
            return this;
        }
        public class Referral {

            public Integer getReferredCount() {
                return referredCount;
            }

            public void setReferredCount(Integer referredCount) {
                this.referredCount = referredCount;
            }

            @SerializedName("referredCount")
            @Expose
            private Integer referredCount;

            @SerializedName("code")
            @Expose
            private String code;

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public Referral withCode(String code) {
                this.code = code;
                return this;
            }

        }
    }
}

