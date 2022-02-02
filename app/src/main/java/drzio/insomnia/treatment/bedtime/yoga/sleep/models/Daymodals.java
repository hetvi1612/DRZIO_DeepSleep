package drzio.insomnia.treatment.bedtime.yoga.sleep.models;

import java.io.Serializable;

public class Daymodals implements Serializable {
    private String Planname;
    private String dayname;
    private String daytxtcal;
    private String dattxttime;
    private String daythumbs;
    private boolean isrest;
    private float dayprogress;
    private String isdaydata;
    private String isselected;
    private boolean iscompleted;
    private String execercisename;
    private String exethumbs;
    private String execerciseduration;
    private String exedescription;
    private String execerciseimaga;
    private String youvideo;
    private String calorie;
    private boolean isLocked;

    public String getPlanname() {
        return Planname;
    }

    public void setPlanname(String planname) {
        Planname = planname;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public String getIsselected() {
        return isselected;
    }

    public void setIsselected(String isselected) {
        this.isselected = isselected;
    }

    public boolean getIscompleted() {
        return iscompleted;
    }

    public void setIscompleted(boolean iscompleted) {
        this.iscompleted = iscompleted;
    }

    public String getIsdaydata() {
        return isdaydata;
    }

    public void setIsdaydata(String isdaydata) {
        this.isdaydata = isdaydata;
    }

    public String getDayname() {
        return dayname;
    }

    public void setDayname(String dayname) {
        this.dayname = dayname;
    }

    public String getDaytxtcal() {
        return daytxtcal;
    }

    public void setDaytxtcal(String daytxtcal) {
        this.daytxtcal = daytxtcal;
    }

    public String getDattxttime() {
        return dattxttime;
    }

    public void setDattxttime(String dattxttime) {
        this.dattxttime = dattxttime;
    }

    public String getDaythumbs() {
        return daythumbs;
    }

    public void setDaythumbs(String daythumbs) {
        this.daythumbs = daythumbs;
    }

    public boolean isIsrest() {
        return isrest;
    }

    public void setIsrest(boolean isrest) {
        this.isrest = isrest;
    }

    public float getDayprogress() {
        return dayprogress;
    }

    public void setDayprogress(float dayprogress) {
        this.dayprogress = dayprogress;
    }

    public String getExecercisename() {
        return execercisename;
    }

    public void setExecercisename(String execercisename) {
        this.execercisename = execercisename;
    }

    public String getExethumbs() {
        return exethumbs;
    }

    public void setExethumbs(String exethumbs) {
        this.exethumbs = exethumbs;
    }

    public String getExecerciseduration() {
        return execerciseduration;
    }

    public void setExecerciseduration(String execerciseduration) {
        this.execerciseduration = execerciseduration;
    }

    public String getExedescription() {
        return exedescription;
    }

    public void setExedescription(String exedescription) {
        this.exedescription = exedescription;
    }

    public String getExecerciseimaga() {
        return execerciseimaga;
    }

    public void setExecerciseimaga(String execerciseimaga) {
        this.execerciseimaga = execerciseimaga;
    }

    public String getYouvideo() {
        return youvideo;
    }

    public void setYouvideo(String youvideo) {
        this.youvideo = youvideo;
    }

    public boolean isIscompleted() {
        return iscompleted;
    }

    public String getCalorie() {
        return calorie;
    }

    public void setCalorie(String calorie) {
        this.calorie = calorie;
    }

    public boolean getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }
}
