package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model;

import java.util.HashMap;

public class Cases {
    public HashMap<String, HashMap<String, String>> cases = new HashMap<>();

    public HashMap<String, HashMap<String, String>> getCases() {
        return cases;
    }

    public void setCases(HashMap<String, HashMap<String, String>> cases) {
        this.cases = cases;
    }
}
