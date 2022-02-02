package drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Appstore_Downloadclick {
    @SerializedName("download")
    @Expose
    private Download download;

    public Download getDownload() {
        return download;
    }

    public void setDownload(Download download) {
        this.download = download;
    }

    public Appstore_Downloadclick withDownload(Download download) {
        this.download = download;
        return this;
    }

    public class Download {

        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("banner_image")
        @Expose
        private String bannerImage;
        @SerializedName("rating")
        @Expose
        private Integer rating;
        @SerializedName("rating_count")
        @Expose
        private Integer ratingCount;
        @SerializedName("related_app")
        @Expose
        private List<Object> relatedApp = null;
        @SerializedName("part")
        @Expose
        private List<Object> part = null;
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
        @SerializedName("coin")
        @Expose
        private Integer coin;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("download")
        @Expose
        private Integer download;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Download withImage(String image) {
            this.image = image;
            return this;
        }

        public String getBannerImage() {
            return bannerImage;
        }

        public void setBannerImage(String bannerImage) {
            this.bannerImage = bannerImage;
        }

        public Download withBannerImage(String bannerImage) {
            this.bannerImage = bannerImage;
            return this;
        }

        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }

        public Download withRating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public Integer getRatingCount() {
            return ratingCount;
        }

        public void setRatingCount(Integer ratingCount) {
            this.ratingCount = ratingCount;
        }

        public Download withRatingCount(Integer ratingCount) {
            this.ratingCount = ratingCount;
            return this;
        }

        public List<Object> getRelatedApp() {
            return relatedApp;
        }

        public void setRelatedApp(List<Object> relatedApp) {
            this.relatedApp = relatedApp;
        }

        public Download withRelatedApp(List<Object> relatedApp) {
            this.relatedApp = relatedApp;
            return this;
        }

        public List<Object> getPart() {
            return part;
        }

        public void setPart(List<Object> part) {
            this.part = part;
        }

        public Download withPart(List<Object> part) {
            this.part = part;
            return this;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Download withId(String id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Download withName(String name) {
            this.name = name;
            return this;
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public Download withNumber(Integer number) {
            this.number = number;
            return this;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public Download withLink(String link) {
            this.link = link;
            return this;
        }

        public Integer getCoin() {
            return coin;
        }

        public void setCoin(Integer coin) {
            this.coin = coin;
        }

        public Download withCoin(Integer coin) {
            this.coin = coin;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Download withDescription(String description) {
            this.description = description;
            return this;
        }

        public Integer getDownload() {
            return download;
        }

        public void setDownload(Integer download) {
            this.download = download;
        }

        public Download withDownload(Integer download) {
            this.download = download;
            return this;
        }
    }
}
