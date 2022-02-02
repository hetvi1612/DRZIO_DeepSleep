package drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pricedata {
    @SerializedName("event_name")
    @Expose
    private String eventName;
    @SerializedName("currency_code")
    @Expose
    private String currencyCode;
    @SerializedName("country_name")
    @Expose
    public List<String> countryName = null;
    @SerializedName("app_number")
    @Expose
    public List<String> appNumber = null;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("month_price")
    @Expose
    private Integer monthPrice;
    @SerializedName("one_y_price")
    @Expose
    private Integer oneYPrice;
    @SerializedName("life_t_price")
    @Expose
    private Integer lifeTPrice;
    @SerializedName("month_key")
    @Expose
    private String monthKey;
    @SerializedName("one_y_key")
    @Expose
    private String oneYKey;
    @SerializedName("life_t_key")
    @Expose
    private String lifeTKey;
    @SerializedName("start_date")
    @Expose
    private String startDate;
    @SerializedName("end_date")
    @Expose
    private String endDate;
    @SerializedName("curr_symbol")
    @Expose
    private String currSymbol;


    public String getPercent_offer() {
        return offer_1;
    }

    public void setPercent_offer(String offer_1) {
        this.offer_1 = offer_1;
    }

    public String getOffer_2() {
        return offer_2;
    }

    public void setOffer_2(String offer_2) {
        this.offer_2 = offer_2;
    }

    @SerializedName("offer_2")
    @Expose
    private String offer_2;
    @SerializedName("offer_1")
    @Expose
    private String offer_1;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @SerializedName("image")
    @Expose
    private String image;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public List<String> getCountryName() {
        return countryName;
    }

    public void setCountryName(List<String> countryName) {
        this.countryName = countryName;
    }

    public List<String> getAppNumber() {
        return appNumber;
    }

    public void setAppNumber(List<String> appNumber) {
        this.appNumber = appNumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getMonthPrice() {
        return monthPrice;
    }

    public void setMonthPrice(Integer monthPrice) {
        this.monthPrice = monthPrice;
    }

    public Integer getOneYPrice() {
        return oneYPrice;
    }

    public void setOneYPrice(Integer oneYPrice) {
        this.oneYPrice = oneYPrice;
    }

    public Integer getLifeTPrice() {
        return lifeTPrice;
    }

    public void setLifeTPrice(Integer lifeTPrice) {
        this.lifeTPrice = lifeTPrice;
    }

    public String getMonthKey() {
        return monthKey;
    }

    public void setMonthKey(String monthKey) {
        this.monthKey = monthKey;
    }

    public String getOneYKey() {
        return oneYKey;
    }

    public void setOneYKey(String oneYKey) {
        this.oneYKey = oneYKey;
    }

    public String getLifeTKey() {
        return lifeTKey;
    }

    public void setLifeTKey(String lifeTKey) {
        this.lifeTKey = lifeTKey;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCurrSymbol() {
        return currSymbol;
    }

    public void setCurrSymbol(String currSymbol) {
        this.currSymbol = currSymbol;
    }

}

