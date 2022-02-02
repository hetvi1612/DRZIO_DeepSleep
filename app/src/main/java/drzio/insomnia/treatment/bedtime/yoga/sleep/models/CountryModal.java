package drzio.insomnia.treatment.bedtime.yoga.sleep.models;

import java.util.ArrayList;

public class CountryModal {

    private String id;
    private String name;
    public static ArrayList<CountryModal> mCountrylist = new ArrayList<>();

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

    public static ArrayList<CountryModal> getmCountrylist() {
        return mCountrylist;
    }

    public static void setmCountrylist(ArrayList<CountryModal> mCountrylist) {
        CountryModal.mCountrylist = mCountrylist;
    }
}
