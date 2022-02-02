package drzio.insomnia.treatment.bedtime.yoga.sleep.models;

import java.util.ArrayList;

public class BannerModal {
    String banner_image;
    String link_option;
    String banner_link;
    String default_type;
    String ads_type;
    public static ArrayList<BannerModal> mBannerlist = new ArrayList<>();


    public String getBanner_image() {
        return banner_image;
    }

    public void setBanner_image(String banner_image) {
        this.banner_image = banner_image;
    }

    public String getLink_option() {
        return link_option;
    }

    public void setLink_option(String link_option) {
        this.link_option = link_option;
    }

    public String getBanner_link() {
        return banner_link;
    }

    public void setBanner_link(String banner_link) {
        this.banner_link = banner_link;
    }

    public String getDefault_type() {
        return default_type;
    }

    public void setDefault_type(String default_type) {
        this.default_type = default_type;
    }

    public String getAds_type() {
        return ads_type;
    }

    public void setAds_type(String ads_type) {
        this.ads_type = ads_type;
    }

    public static ArrayList<BannerModal> getmBannerlist() {
        return mBannerlist;
    }

    public static void setmBannerlist(ArrayList<BannerModal> mBannerlist) {
        BannerModal.mBannerlist = mBannerlist;
    }
}
