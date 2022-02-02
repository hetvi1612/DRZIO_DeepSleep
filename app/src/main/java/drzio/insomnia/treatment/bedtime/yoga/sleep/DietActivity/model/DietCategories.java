package drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model;

import java.util.ArrayList;

public class DietCategories {
    public String category_id;
    public String catname;



    public static ArrayList<DietCategories> gifcatlistdata = new ArrayList<DietCategories>();

    public static ArrayList<DietCategories> getGifCatlistdata() {
        return gifcatlistdata;
    }

    public static void setGifCatlistdata(ArrayList<DietCategories> gifcatlistdata) {
        DietCategories.gifcatlistdata = gifcatlistdata;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getCatname() {
        return catname;
    }

    public void setCatname(String catname) {
        this.catname = catname;
    }




}
