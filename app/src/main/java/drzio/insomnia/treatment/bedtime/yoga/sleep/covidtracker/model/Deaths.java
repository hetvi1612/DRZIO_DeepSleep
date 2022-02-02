package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model;

import java.util.HashMap;

public class Deaths {
    private HashMap<String, HashMap<String, String>> deaths;

    public HashMap<String, HashMap<String, String>> getDeaths() {
        return deaths;
    }

    public void setDeaths(HashMap<String, HashMap<String, String>> deaths) {
        this.deaths = deaths;
    }
}
