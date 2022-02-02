package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.mathdro;

import com.google.gson.annotations.SerializedName;

public class WorldDataValueModel {
    @SerializedName("value")
    int value;

    public WorldDataValueModel(int value2) {
        this.value = value2;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value2) {
        this.value = value2;
    }
}
