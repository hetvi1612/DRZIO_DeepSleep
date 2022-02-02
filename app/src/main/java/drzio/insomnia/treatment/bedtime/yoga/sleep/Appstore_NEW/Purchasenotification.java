package drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Purchasenotification {
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("reason")
    @Expose
    private String reason;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Purchasenotification withSuccess(Boolean success) {
        this.success = success;
        return this;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Purchasenotification withReason(String reason) {
        this.reason = reason;
        return this;
    }

}

