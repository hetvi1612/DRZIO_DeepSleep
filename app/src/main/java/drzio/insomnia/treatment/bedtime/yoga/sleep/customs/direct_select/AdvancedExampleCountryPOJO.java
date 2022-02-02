package drzio.insomnia.treatment.bedtime.yoga.sleep.customs.direct_select;

import java.util.Arrays;
import java.util.List;

public class AdvancedExampleCountryPOJO {

    private String title;
    private int value;

    public AdvancedExampleCountryPOJO(String title,int value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public static List<AdvancedExampleCountryPOJO> getExampleDataset() {
        return Arrays.asList(
                new AdvancedExampleCountryPOJO("1 Day",1),
                new AdvancedExampleCountryPOJO("2 Days",2),
                new AdvancedExampleCountryPOJO("3 Days",3),
                new AdvancedExampleCountryPOJO("4 Days",4),
                new AdvancedExampleCountryPOJO("5 Days",5),
                new AdvancedExampleCountryPOJO("6 Days",6),
                new AdvancedExampleCountryPOJO("7 Days",7)
        );
    }
}
