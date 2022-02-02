package drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.classes;

public class M_ListViewItem {
    private String alphabet;
    private String date;
    private int imageRow;
    private String repeat;
    private boolean switch_state;
    private String time;
    private String time_am_pm;
    private String time_diff;
    private String title;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getRepeat() {
        return this.repeat;
    }

    public void setRepeat(String str) {
        this.repeat = str;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String str) {
        this.date = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public String getAlphabet() {
        return this.alphabet;
    }

    public void setAlphabet(String str) {
        this.alphabet = str;
    }

    public int getImageRow() {
        return this.imageRow;
    }

    public void setImageRow(int i) {
        this.imageRow = i;
    }

    public String getTime_diff() {
        return this.time_diff;
    }

    public void setTime_diff(String str) {
        this.time_diff = str;
    }

    public String getTime_am_pm() {
        return this.time_am_pm;
    }

    public void setTime_am_pm(String str) {
        this.time_am_pm = str;
    }

    public boolean getSwitch_state() {
        return this.switch_state;
    }

    public void setSwitch_state(boolean z) {
        this.switch_state = z;
    }
}
