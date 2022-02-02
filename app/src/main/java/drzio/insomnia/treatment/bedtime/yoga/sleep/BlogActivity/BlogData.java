package drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class BlogData {

    @SerializedName("status")
    String status;
    @SerializedName("messsge")
    String message;
    @SerializedName("data")
    public ArrayList<Datalist> datalists;



    public static class Datalist {
        @SerializedName("blog_id")
        public String id;
        @SerializedName("name")
        public String name;
        @SerializedName("link")
        public String bloglink;
        @SerializedName("description")
        public String description;
        @SerializedName("image")
        public String image;
        @SerializedName("likes")
        public String likes;
        public String is_liked;
        private boolean isAd;

        public Datalist(String id, String name, String bloglink, String description, String image, String likes, String is_liked, boolean isAd) {
            this.id = id;
            this.name = name;
            this.bloglink = bloglink;
            this.description = description;
            this.image = image;
            this.likes = likes;
            this.is_liked = is_liked;
            this.isAd = isAd;
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


        public boolean isAd() {
            return isAd;
        }

        public void setAd(boolean ad) {
            isAd = ad;
        }
    }

}
