package drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity;

import java.util.ArrayList;

public class Blogmodel {

    public String id;
    public String name;
    public String bloglink;
    public String description;
    public String image;
    public String likes;
    public String is_liked;
    private boolean isAd;
    public static ArrayList<Blogmodel> mBloglist = new ArrayList<>();


    public Blogmodel(String id, String name, String bloglink, String description, String image, String likes, String is_liked, boolean isAd) {
        this.id = id;
        this.name = name;
        this.bloglink = bloglink;
        this.description = description;
        this.image = image;
        this.likes = likes;
        this.is_liked = is_liked;
        this.isAd = isAd;
    }

    public Blogmodel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBloglink() {
        return bloglink;
    }

    public void setBloglink(String bloglink) {
        this.bloglink = bloglink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getIs_liked() {
        return is_liked;
    }

    public void setIs_liked(String is_liked) {
        this.is_liked = is_liked;
    }

    public static ArrayList<Blogmodel> getmBloglist() {
        return mBloglist;
    }

    public static void setmBloglist(ArrayList<Blogmodel> mBloglist) {
        Blogmodel.mBloglist = mBloglist;
    }

    public boolean isAd() {
        return isAd;
    }

    public void setAd(boolean ad) {
        isAd = ad;
    }
}
