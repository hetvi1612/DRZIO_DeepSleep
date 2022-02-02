package drzio.insomnia.treatment.bedtime.yoga.sleep.models;

import java.util.ArrayList;

public class StateModal {
    private String id;
    private String name;
    private String country_id;
    public static ArrayList<StateModal> mStatelist = new ArrayList<>();

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

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public static ArrayList<StateModal> getmStatelist() {
        return mStatelist;
    }

    public static void setmStatelist(ArrayList<StateModal> mStatelist) {
        StateModal.mStatelist = mStatelist;
    }
}
