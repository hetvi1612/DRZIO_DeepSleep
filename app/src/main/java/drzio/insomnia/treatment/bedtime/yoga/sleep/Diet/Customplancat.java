package drzio.insomnia.treatment.bedtime.yoga.sleep.Diet;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Customplancat {

    @SerializedName("nutritions")
    @Expose
    private Nutritions nutritions;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("time")
    @Expose
    private List<String> time = null;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("type")
    @Expose
    private List<String> type = null;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("created_date")
    @Expose
    private String createdDate;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
    @SerializedName("__v")
    @Expose
    private Integer v;
    public boolean isSelected;

    public Nutritions getNutritions() {
        return nutritions;
    }

    public void setNutritions(Nutritions nutritions) {
        this.nutritions = nutritions;
    }

    public Customplancat withNutritions(Nutritions nutritions) {
        this.nutritions = nutritions;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Customplancat withName(String name) {
        this.name = name;
        return this;
    }

    public List<String> getTime() {
        return time;
    }

    public void setTime(List<String> time) {
        this.time = time;
    }

    public Customplancat withTime(List<String> time) {
        this.time = time;
        return this;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Customplancat withImage(String image) {
        this.image = image;
        return this;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public Customplancat withType(List<String> type) {
        this.type = type;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Customplancat withCategory(String category) {
        this.category = category;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Customplancat withDescription(String description) {
        this.description = description;
        return this;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public Customplancat withCreatedDate(String createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Customplancat withId(String id) {
        this.id = id;
        return this;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Customplancat withQuantity(Integer quantity) {
        this.quantity = quantity;
        return this;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public Customplancat withV(Integer v) {
        this.v = v;
        return this;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public class Nutritions {

        @SerializedName("mineral")
        @Expose
        private List<String> mineral = null;
        @SerializedName("vitamin")
        @Expose
        private List<String> vitamin = null;
        @SerializedName("calories")
        @Expose
        private Integer calories;
        @SerializedName("protien")
        @Expose
        private Double protien;
        @SerializedName("fiber")
        @Expose
        private Double fiber;
        @SerializedName("fat")
        @Expose
        private Double fat;
        @SerializedName("carbs")
        @Expose
        private Float carbs;
        @SerializedName("sugar")
        @Expose
        private Double sugar;

        @SerializedName("category")
        @Expose
        private String category;

        public List<String> getMineral() {
            return mineral;
        }

        public void setMineral(List<String> mineral) {
            this.mineral = mineral;
        }

        public Nutritions withMineral(List<String> mineral) {
            this.mineral = mineral;
            return this;
        }

        public List<String> getVitamin() {
            return vitamin;
        }

        public void setVitamin(List<String> vitamin) {
            this.vitamin = vitamin;
        }

        public Nutritions withVitamin(List<String> vitamin) {
            this.vitamin = vitamin;
            return this;
        }

        public Integer getCalories() {
            return calories;
        }

        public void setCalories(Integer calories) {
            this.calories = calories;
        }

        public Nutritions withCalories(Integer calories) {
            this.calories = calories;
            return this;
        }

        public Double getProtien() {
            return protien;
        }

        public void setProtien(Double protien) {
            this.protien = protien;
        }

        public Nutritions withProtien(Double protien) {
            this.protien = protien;
            return this;
        }

        public Double getFiber() {
            return fiber;
        }

        public void setFiber(Double fiber) {
            this.fiber = fiber;
        }

        public Nutritions withFiber(Double fiber) {
            this.fiber = fiber;
            return this;
        }

        public Double getFat() {
            return fat;
        }

        public void setFat(Double fat) {
            this.fat = fat;
        }

        public Nutritions withFat(Double fat) {
            this.fat = fat;
            return this;
        }

        public Float getCarbs() {
            return carbs;
        }

        public void setCarbs(Float carbs) {
            this.carbs = carbs;
        }

        public Nutritions withCarbs(Float carbs) {
            this.carbs = carbs;
            return this;
        }

        public Double getSugar() {
            return sugar;
        }

        public void setSugar(Double sugar) {
            this.sugar = sugar;
        }

        public Nutritions withSugar(Double sugar) {
            this.sugar = sugar;
            return this;
        }




        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public Nutritions withCategory(String category) {
            this.category = category;
            return this;
        }

    }
}