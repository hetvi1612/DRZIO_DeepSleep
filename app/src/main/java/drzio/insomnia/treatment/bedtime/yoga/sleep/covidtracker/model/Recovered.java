package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model;

import java.util.HashMap;

public class Recovered {
    private HashMap<String, HashMap<String, String>> recovered;

    public HashMap<String, HashMap<String, String>> getRecovered() {
        return recovered;
    }

    public void setRecovered(HashMap<String, HashMap<String, String>> recovered) {
        this.recovered = recovered;
    }
}
