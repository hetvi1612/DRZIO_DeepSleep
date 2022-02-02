package drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LanguageApi {
    @SerializedName("status")
    @Expose
    private Boolean status;

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @SerializedName("language")
    @Expose
    private String language;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LanguageApi withStatus(Boolean status) {
        this.status = status;
        return this;
    }

/*    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public LanguageApi withData(String data) {
        this.data = data;
        return this;
    }*/
}
