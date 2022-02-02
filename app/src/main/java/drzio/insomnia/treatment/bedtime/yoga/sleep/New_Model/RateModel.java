package drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RateModel {
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("device_id")
    @Expose
    private String deviceId;
    @SerializedName("app")
    @Expose
    private String app;
    @SerializedName("user_email")
    @Expose
    private String userEmail;
    @SerializedName("option1")
    @Expose
    private String option1;
    @SerializedName("option2")
    @Expose
    private String option2;
    @SerializedName("device_name")
    @Expose
    private String deviceName;
    @SerializedName("android_version")
    @Expose
    private String androidVersion;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public RateModel withDate(String date) {
        this.date = date;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public RateModel withId(String id) {
        this.id = id;
        return this;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public RateModel withDeviceId(String deviceId) {
        this.deviceId = deviceId;
        return this;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public RateModel withApp(String app) {
        this.app = app;
        return this;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public RateModel withUserEmail(String userEmail) {
        this.userEmail = userEmail;
        return this;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public RateModel withOption1(String option1) {
        this.option1 = option1;
        return this;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public RateModel withOption2(String option2) {
        this.option2 = option2;
        return this;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public RateModel withDeviceName(String deviceName) {
        this.deviceName = deviceName;
        return this;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    public RateModel withAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
        return this;
    }

}
