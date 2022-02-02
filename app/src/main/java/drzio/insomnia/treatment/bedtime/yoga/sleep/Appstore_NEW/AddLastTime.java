package drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddLastTime {
    @SerializedName("ok")
    @Expose
    private Boolean ok;

    public Boolean getOk() {
        return ok;
    }

    public void setOk(Boolean ok) {
        this.ok = ok;
    }

    public AddLastTime withOk(Boolean ok) {
        this.ok = ok;
        return this;
    }


}
