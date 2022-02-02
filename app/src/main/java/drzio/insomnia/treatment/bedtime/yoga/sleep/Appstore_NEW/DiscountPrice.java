package drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DiscountPrice {
    @SerializedName("month_price")
    @Expose
    private String monthPrice;
    @SerializedName("six_m_price")
    @Expose
    private String sixMPrice;
    @SerializedName("life_t_price")
    @Expose
    private String lifeTPrice;

    public String getCurr_symbol() {
        return curr_symbol;
    }

    public void setCurr_symbol(String curr_symbol) {
        this.curr_symbol = curr_symbol;
    }

    @SerializedName("curr_symbol")
    @Expose
    private String curr_symbol;

    public String getMonthPrice() {
        return monthPrice;
    }

    public void setMonthPrice(String monthPrice) {
        this.monthPrice = monthPrice;
    }

    public DiscountPrice withMonthPrice(String monthPrice) {
        this.monthPrice = monthPrice;
        return this;
    }

    public String getSixMPrice() {
        return sixMPrice;
    }

    public void setSixMPrice(String sixMPrice) {
        this.sixMPrice = sixMPrice;
    }

    public DiscountPrice withSixMPrice(String sixMPrice) {
        this.sixMPrice = sixMPrice;
        return this;
    }

    public String getLifeTPrice() {
        return lifeTPrice;
    }

    public void setLifeTPrice(String lifeTPrice) {
        this.lifeTPrice = lifeTPrice;
    }

    public DiscountPrice withLifeTPrice(String lifeTPrice) {
        this.lifeTPrice = lifeTPrice;
        return this;
    }
}
