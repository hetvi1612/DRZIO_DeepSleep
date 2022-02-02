package drzio.insomnia.treatment.bedtime.yoga.sleep.customs.firstdayweekdirect_select;

import java.util.Arrays;
import java.util.List;

public class FirstDayofWeekPOJO {

    private String title;
    private int value;

    public FirstDayofWeekPOJO(String title, int value) {
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

    public static List<FirstDayofWeekPOJO> getExampleDataset() {
        return Arrays.asList(
                new FirstDayofWeekPOJO("Sunday",1),
                new FirstDayofWeekPOJO("Monday",2),
                new FirstDayofWeekPOJO("Saturday",3)
        );
    }
}
