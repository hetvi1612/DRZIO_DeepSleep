package drzio.insomnia.treatment.bedtime.yoga.sleep.Diet;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddFood {
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("food_name")
    @Expose
    private String foodName;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("calories")
    @Expose
    private Integer calories;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public AddFood withCreatedDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AddFood withId(String id) {
        this.id = id;
        return this;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public AddFood withFoodName(String foodName) {
        this.foodName = foodName;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public AddFood withUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public AddFood withCalories(Integer calories) {
        this.calories = calories;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AddFood withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public AddFood withImage(String image) {
        this.image = image;
        return this;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public AddFood withV(Integer v) {
        this.v = v;
        return this;
    }

}
