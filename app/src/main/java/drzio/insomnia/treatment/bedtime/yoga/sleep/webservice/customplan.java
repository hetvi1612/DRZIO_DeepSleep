package drzio.insomnia.treatment.bedtime.yoga.sleep.webservice;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class customplan {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("nutritions")
    @Expose
    private Nutritions nutritions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public customplan withId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public customplan withName(String name) {
        this.name = name;
        return this;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public customplan withImage(String image) {
        this.image = image;
        return this;
    }

    public Nutritions getNutritions() {
        return nutritions;
    }

    public void setNutritions(Nutritions nutritions) {
        this.nutritions = nutritions;
    }

    public customplan withNutritions(Nutritions nutritions) {
        this.nutritions = nutritions;
        return this;
    }
    public class Nutritions {

        @SerializedName("calories")
        @Expose
        private String calories;
        @SerializedName("protien")
        @Expose
        private String protien;
        @SerializedName("fiber")
        @Expose
        private String fiber;
        @SerializedName("fat")
        @Expose
        private String fat;
        @SerializedName("carbs")
        @Expose
        private String carbs;
        @SerializedName("quantity")
        @Expose
        private String quantity;
        @SerializedName("nQuantity")
        @Expose
        private String nQuantity;
        @SerializedName("updateId")
        @Expose
        private String updateId;

        public String getCalories() {
            return calories;
        }

        public void setCalories(String calories) {
            this.calories = calories;
        }

        public Nutritions withCalories(String calories) {
            this.calories = calories;
            return this;
        }

        public String getProtien() {
            return protien;
        }

        public void setProtien(String protien) {
            this.protien = protien;
        }

        public Nutritions withProtien(String protien) {
            this.protien = protien;
            return this;
        }

        public String getFiber() {
            return fiber;
        }

        public void setFiber(String fiber) {
            this.fiber = fiber;
        }

        public Nutritions withFiber(String fiber) {
            this.fiber = fiber;
            return this;
        }

        public String getFat() {
            return fat;
        }

        public void setFat(String fat) {
            this.fat = fat;
        }

        public Nutritions withFat(String fat) {
            this.fat = fat;
            return this;
        }

        public String getCarbs() {
            return carbs;
        }

        public void setCarbs(String carbs) {
            this.carbs = carbs;
        }

        public Nutritions withCarbs(String carbs) {
            this.carbs = carbs;
            return this;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public Nutritions withQuantity(String quantity) {
            this.quantity = quantity;
            return this;
        }

        public String getnQuantity() {
            return nQuantity;
        }

        public void setnQuantity(String nQuantity) {
            this.nQuantity = nQuantity;
        }

        public Nutritions withnQuantity(String nQuantity) {
            this.nQuantity = nQuantity;
            return this;
        }

        public String getUpdateId() {
            return updateId;
        }

        public void setUpdateId(String updateId) {
            this.updateId = updateId;
        }

        public Nutritions withUpdateId(String updateId) {
            this.updateId = updateId;
            return this;
        }

    }

}
