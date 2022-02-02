package drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Badge {
    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Badge(Boolean status) {
        this.status = status;
    }

    @SerializedName("status")
    @Expose
    private Boolean status;




}

