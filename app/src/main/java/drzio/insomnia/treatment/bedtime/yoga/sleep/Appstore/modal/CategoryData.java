package drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class CategoryData {
    @SerializedName("status")
    String status;
    @SerializedName("messsge")
    String message;
    @SerializedName("data")
    public ArrayList<Datalist> dataist;

    public static class Datalist {
        @SerializedName("id")
        String id;
        @SerializedName("name")
        String name;
        public static ArrayList<Datalist> categoryModals = new ArrayList<>();

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

        public static ArrayList<Datalist> getCategoryModals() {
            return categoryModals;
        }

        public static void setCategoryModals(ArrayList<Datalist> categoryModals) {
            Datalist.categoryModals = categoryModals;
        }
    }
}
