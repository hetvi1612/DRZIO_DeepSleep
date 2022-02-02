package drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BlogList {


    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("blog_name")
    @Expose
    private String blogName;
    @SerializedName("image")
    @Expose
    private List<String> image = null;
    @SerializedName("blog_link")
    @Expose
    private String blogLink;
    @SerializedName("likeStatus")
    @Expose
    private Boolean likeStatus;
    @SerializedName("blog_like")
    @Expose
    private Integer blogLike;

    public Integer getBlog_comment() {
        return blog_comment;
    }

    public void setBlog_comment(Integer blog_comment) {
        this.blog_comment = blog_comment;
    }

    @SerializedName("blog_comment")
    @Expose
    private Integer blog_comment;
   /* @SerializedName("blog_description")
    @Expose
    private String blogDescription;*/

    public String getMeta_description() {
        return meta_description;
    }

    public void setMeta_description(String meta_description) {
        this.meta_description = meta_description;
    }

    @SerializedName("meta_description")
    @Expose
    private String meta_description;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;
    public String is_liked;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBlogName() {
        return blogName;
    }

    public void setBlogName(String blogName) {
        this.blogName = blogName;
    }

    public List<String> getImage() {
        return image;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public String getBlogLink() {
        return blogLink;
    }

    public void setBlogLink(String blogLink) {
        this.blogLink = blogLink;
    }

    public Boolean getLikeStatus() {
        return likeStatus;
    }

    public void setLikeStatus(Boolean likeStatus) {
        this.likeStatus = likeStatus;
    }

    public Integer getBlogLike() {
        return blogLike;
    }

    public void setBlogLike(Integer blogLike) {
        this.blogLike = blogLike;
    }

  /*  public String getBlogDescription() {
        return blogDescription;
    }

    public void setBlogDescription(String blogDescription) {
        this.blogDescription = blogDescription;
    }*/

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
