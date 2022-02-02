package drzio.insomnia.treatment.bedtime.yoga.sleep.Activity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AppUpdateApi {
    @SerializedName("status")
    @Expose
    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public AppUpdateApi withStatus(Boolean status) {
        this.status = status;
        return this;
    }
}
