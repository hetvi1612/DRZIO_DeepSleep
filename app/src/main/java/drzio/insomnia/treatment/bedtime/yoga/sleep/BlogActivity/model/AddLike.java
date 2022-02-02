package drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddLike {
    @SerializedName("blog_like")
    @Expose
    private Integer blogLike;

    public Integer getBlogLike() {
        return blogLike;
    }

    public void setBlogLike(Integer blogLike) {
        this.blogLike = blogLike;
    }
}
