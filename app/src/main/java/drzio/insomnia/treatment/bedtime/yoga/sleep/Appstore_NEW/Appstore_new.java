package drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Appstore_new {
  /*  @SerializedName("related")
    @Expose
    private List<Related> related = null;
    @SerializedName("not-related")
    @Expose
    private List<Object> notRelated = null;

    public List<Related> getRelated() {
        return related;
    }

    public void setRelated(List<Related> related) {
        this.related = related;
    }

    public Appstore withRelated(List<Related> related) {
        this.related = related;
        return this;
    }

    public List<Object> getNotRelated() {
        return notRelated;
    }

    public void setNotRelated(List<Object> notRelated) {
        this.notRelated = notRelated;
    }

    public Appstore withNotRelated(List<Object> notRelated) {
        this.notRelated = notRelated;
        return this;
    }

    public class Related {

        @SerializedName("rating")
        @Expose
        private Integer rating;
        @SerializedName("rating_count")
        @Expose
        private Integer ratingCount;
        @SerializedName("related_app")
        @Expose
        private List<Object> relatedApp = null;
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
        @SerializedName("image")
        @Expose
        private String image;

        public String getBanner_image() {
            return banner_image;
        }

        public void setBanner_image(String banner_image) {
            this.banner_image = banner_image;
        }

        @SerializedName("banner_image ")
        @Expose
        private String banner_image ;
        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }

        public Related withRating(Integer rating) {
            this.rating = rating;
            return this;
        }

        public Integer getRatingCount() {
            return ratingCount;
        }

        public void setRatingCount(Integer ratingCount) {
            this.ratingCount = ratingCount;
        }

        public Related withRatingCount(Integer ratingCount) {
            this.ratingCount = ratingCount;
            return this;
        }

        public List<Object> getRelatedApp() {
            return relatedApp;
        }

        public void setRelatedApp(List<Object> relatedApp) {
            this.relatedApp = relatedApp;
        }

        public Related withRelatedApp(List<Object> relatedApp) {
            this.relatedApp = relatedApp;
            return this;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Related withId(String id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Related withName(String name) {
            this.name = name;
            return this;
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }

        public Related withNumber(Integer number) {
            this.number = number;
            return this;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public Related withLink(String link) {
            this.link = link;
            return this;
        }

        public Integer getCoin() {
            return coin;
        }

        public void setCoin(Integer coin) {
            this.coin = coin;
        }

        public Related withCoin(Integer coin) {
            this.coin = coin;
            return this;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Related withDescription(String description) {
            this.description = description;
            return this;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Related withImage(String image) {
            this.image = image;
            return this;
        }

    }*/
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
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("part")
    @Expose
    private List<String> part = null;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Appstore_new withImage(String image) {
        this.image = image;
        return this;
    }

    public String getBannerImage() {
        return bannerImage;
    }

    public void setBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
    }

    public Appstore_new withBannerImage(String bannerImage) {
        this.bannerImage = bannerImage;
        return this;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Appstore_new withRating(Double rating) {
        this.rating = rating;
        return this;
    }

    public Integer getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
    }

    public Appstore_new withRatingCount(Integer ratingCount) {
        this.ratingCount = ratingCount;
        return this;
    }

    public List<String> getRelatedApp() {
        return relatedApp;
    }

    public void setRelatedApp(List<String> relatedApp) {
        this.relatedApp = relatedApp;
    }

    public Appstore_new withRelatedApp(List<String> relatedApp) {
        this.relatedApp = relatedApp;
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Appstore_new withId(String id) {
        this.id = id;
        return this;
    }

    public List<String> getPart() {
        return part;
    }

    public void setPart(List<String> part) {
        this.part = part;
    }

    public Appstore_new withPart(List<String> part) {
        this.part = part;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Appstore_new withName(String name) {
        this.name = name;
        return this;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Appstore_new withNumber(Integer number) {
        this.number = number;
        return this;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Appstore_new withLink(String link) {
        this.link = link;
        return this;
    }

    public Integer getCoin() {
        return coin;
    }

    public void setCoin(Integer coin) {
        this.coin = coin;
    }

    public Appstore_new withCoin(Integer coin) {
        this.coin = coin;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Appstore_new withDescription(String description) {
        this.description = description;
        return this;
    }
}
