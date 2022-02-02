package drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class DietitemsData implements Serializable {
    @SerializedName("status")
    String status;
    @SerializedName("messsge")
    String message;
    @SerializedName("data")
    public ArrayList<Datalist> dataist;


    public static class Datalist implements Serializable{
        @SerializedName("id")
        public String id;
        @SerializedName("name")
        public String name;
        @SerializedName("description")
        public String description;
        @SerializedName("image")
        public String image;
        @SerializedName("is_active")
        public String is_active;
        @SerializedName("user_type")
        public String user_type;
        @SerializedName("category_id")
        public String category_id;
        public boolean isSelected;


        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getIs_active() {
            return is_active;
        }

        public void setIs_active(String is_active) {
            this.is_active = is_active;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }
    }

}
