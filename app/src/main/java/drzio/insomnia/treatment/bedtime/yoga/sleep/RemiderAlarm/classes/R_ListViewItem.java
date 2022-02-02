package drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.classes;

public class R_ListViewItem {
    private String text;
    private int type;

    public R_ListViewItem(String str, int i) {
        this.text = str;
        this.type = i;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }
}
