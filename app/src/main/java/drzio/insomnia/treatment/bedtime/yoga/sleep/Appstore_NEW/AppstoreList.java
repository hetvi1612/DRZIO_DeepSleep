package drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AppstoreList {




    @SerializedName("related")
    @Expose
    private List<Related> related = null;
    @SerializedName("not-related")
    @Expose
    private List<NotRelated> notRelated = null;

    public List<Related> getRelated() {
        return related;
    }

    public void setRelated(List<Related> related) {
        this.related = related;
    }

    public List<NotRelated> getNotRelated() {
        return notRelated;
    }

    public void setNotRelated(List<NotRelated> notRelated) {
        this.notRelated = notRelated;
    }

    public class NotRelated {

        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("banner_image")
        @Expose
        private String bannerImage;
        @SerializedName("rating")
        @Expose
        private Double rating;
        @SerializedName("rating_count")
        @Expose
        private Integer ratingCount;
        @SerializedName("related_app")
        @Expose
        private List<Object> relatedApp = null;
        @SerializedName("part")
        @Expose
        private List<String> part = null;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("number")
        @Expose
        private Integer number;
        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("click")
        @Expose
        private Integer click;
        @SerializedName("download")
        @Expose
        private Integer download;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getBannerImage() {
            return bannerImage;
        }

        public void setBannerImage(String bannerImage) {
            this.bannerImage = bannerImage;
        }

        public Double getRating() {
            return rating;
        }

        public void setRating(Double rating) {
            this.rating = rating;
        }

        public Integer getRatingCount() {
            return ratingCount;
        }

        public void setRatingCount(Integer ratingCount) {
            this.ratingCount = ratingCount;
        }

        public List<Object> getRelatedApp() {
            return relatedApp;
        }

        public void setRelatedApp(List<Object> relatedApp) {
            this.relatedApp = relatedApp;
        }

        public List<String> getPart() {
            return part;
        }

        public void setPart(List<String> part) {
            this.part = part;
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

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getClick() {
            return click;
        }

        public void setClick(Integer click) {
            this.click = click;
        }

        public Integer getDownload() {
            return download;
        }

        public void setDownload(Integer download) {
            this.download = download;
        }

    }

    public class Related {

        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("banner_image")
        @Expose
        private String bannerImage;
        @SerializedName("rating")
        @Expose
        private Double rating;
        @SerializedName("rating_count")
        @Expose
        private Integer ratingCount;
        @SerializedName("related_app")
        @Expose
        private List<String> relatedApp = null;
        @SerializedName("part")
        @Expose
        private List<String> part = null;
        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("number")
        @Expose
        private Integer number;
        @SerializedName("link")
        @Expose
        private String link;
        @SerializedName("description")
        @Expose
        private String description;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getBannerImage() {
            return bannerImage;
        }

        public void setBannerImage(String bannerImage) {
            this.bannerImage = bannerImage;
        }

        public Double getRating() {
            return rating;
        }

        public void setRating(Double rating) {
            this.rating = rating;
        }

        public Integer getRatingCount() {
            return ratingCount;
        }

        public void setRatingCount(Integer ratingCount) {
            this.ratingCount = ratingCount;
        }

        public List<String> getRelatedApp() {
            return relatedApp;
        }

        public void setRelatedApp(List<String> relatedApp) {
            this.relatedApp = relatedApp;
        }

        public List<String> getPart() {
            return part;
        }

        public void setPart(List<String> part) {
            this.part = part;
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

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

    }

}
