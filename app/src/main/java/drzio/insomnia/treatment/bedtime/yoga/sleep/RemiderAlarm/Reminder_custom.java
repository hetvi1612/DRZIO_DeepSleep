package drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm;

public class Reminder_custom {
    private boolean fri;
    private boolean mon;
    private boolean onoff;
    private boolean sat;
    private boolean sun;
    private boolean thr;
    private String time;
    private boolean tue;
    private boolean wen;

    boolean getFri() {
        return this.fri;
    }

    public boolean getMon() {
        return this.mon;
    }

    boolean getOnoff() {
        return this.onoff;
    }

    boolean getSat() {
        return this.sat;
    }

    boolean getSun() {
        return this.sun;
    }

    boolean getThr() {
        return this.thr;
    }

    boolean getTue() {
        return this.tue;
    }

    boolean getWen() {
        return this.wen;
    }

    public String gettime() {
        return this.time;
    }

    void setFri(boolean z) {
        this.fri = z;
    }

    public void setMon(boolean z) {
        this.mon = z;
    }

    void setOnoff(boolean z) {
        this.onoff = z;
    }

    void setSat(boolean z) {
        this.sat = z;
    }

    void setSun(boolean z) {
        this.sun = z;
    }

    void setThr(boolean z) {
        this.thr = z;
    }

    void setTue(boolean z) {
        this.tue = z;
    }

    void setWen(boolean z) {
        this.wen = z;
    }

    void settime(String str) {
        this.time = str;
    }
}
