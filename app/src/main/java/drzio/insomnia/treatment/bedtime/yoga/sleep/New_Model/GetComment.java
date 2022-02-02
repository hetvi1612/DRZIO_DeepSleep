package drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetComment {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("blog_comment")
    @Expose
    public List<BlogComment> blogComment = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<BlogComment> getBlogComment() {
        return blogComment;
    }

    public void setBlogComment(List<BlogComment> blogComment) {
        this.blogComment = blogComment;
    }



    public class BlogComment {


        @SerializedName("_id")
        @Expose
        private String id;
        @SerializedName("comment")
        @Expose
        private String comment;
        @SerializedName("uId")
        @Expose
        private String uId;

        @SerializedName("u_image")
        @Expose
        private String u_image;

        @SerializedName("u_name")
        @Expose
        private String u_name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public String getuId() {
            return uId;
        }

        public void setuId(String uId) {
            this.uId = uId;
        }



        public String getU_image() {
            return u_image;
        }

        public void setU_image(String u_image) {
            this.u_image = u_image;
        }


        public String getU_name() {
            return u_name;
        }

        public void setU_name(String u_name) {
            this.u_name = u_name;
        }
    }
}
