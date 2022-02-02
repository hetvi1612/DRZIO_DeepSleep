package drzio.insomnia.treatment.bedtime.yoga.sleep.models;

import java.util.ArrayList;

public class CityModal {
    private String id;
    private String name;
    public static ArrayList<CityModal> mCitylis = new ArrayList<>();

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

    public static ArrayList<CityModal> getmCitylis() {
        return mCitylis;
    }

    public static void setmCitylis(ArrayList<CityModal> mCitylis) {
        CityModal.mCitylis = mCitylis;
    }
}
