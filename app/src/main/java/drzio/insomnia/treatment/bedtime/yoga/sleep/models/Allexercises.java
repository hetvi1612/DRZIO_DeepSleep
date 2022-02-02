package drzio.insomnia.treatment.bedtime.yoga.sleep.models;

import java.io.Serializable;

public class Allexercises implements Serializable {
    String Planname;
    String Exname;
    String Exethumb;
    String Execalorie;
    int  Extime;
    String Eximglink;
    String videolink;
    String Exdescr;
    boolean counttype;

    boolean IsLocked;
    public boolean isCounttype() {
        return counttype;
    }

    public void setCounttype(boolean counttype) {
        this.counttype = counttype;
    }
    public String getPlanname() {
        return Planname;
    }

    public void setPlanname(String planname) {
        Planname = planname;
    }

    public String getExname() {
        return Exname;
    }

    public void setExname(String exname) {
        Exname = exname;
    }

    public String getExethumb() {
        return Exethumb;
    }

    public void setExethumb(String exethumb) {
        Exethumb = exethumb;
    }

    public String getExecalorie() {
        return Execalorie;
    }

    public void setExecalorie(String execalorie) {
        Execalorie = execalorie;
    }

    public int getExtime() {
        return Extime;
    }

    public void setExtime(int extime) {
        Extime = extime;
    }

    public String getEximglink() {
        return Eximglink;
    }

    public void setEximglink(String eximglink) {
        Eximglink = eximglink;
    }

    public String getVideolink() {
        return videolink;
    }

    public void setVideolink(String videolink) {
        this.videolink = videolink;
    }

    public String getExdescr() {
        return Exdescr;
    }

    public void setExdescr(String exdescr) {
        Exdescr = exdescr;
    }

    public boolean isLocked() {
        return IsLocked;
    }

    public void setLocked(boolean locked) {
        IsLocked = locked;
    }
}
