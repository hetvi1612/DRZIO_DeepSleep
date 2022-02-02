package drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.github.abdularis.civ.AvatarImageView;

import java.util.ArrayList;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.New_Model.GetComment;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;


public class Adapter_addComment extends RecyclerView.Adapter<Adapter_addComment.ViewHolder> {

    ArrayList<GetComment.BlogComment> blogComments;
    Context context;
    Activity activity;
    // private String imageurl="https://femaleworkout-node.s3.ap-south-1.amazonaws.com/food/" ;
    TinyDB tinyDB;

    public Adapter_addComment(Context context, ArrayList<GetComment.BlogComment> blogComments, Activity activity) {
        this.context = context;
        this.blogComments = blogComments;
        this.activity = activity;
        tinyDB = new TinyDB(context);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_commentdata, parent, false);
        ViewHolder holder = new ViewHolder(listItem);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.viewcomment.setText(blogComments.get(position).getComment());
        holder.username.setText(blogComments.get(position).getU_name());
        String mProfname = blogComments.get(position).getU_name();
        String image = blogComments.get(position).getU_image();
        if (!image.isEmpty()) {
            String foodimage = tinyDB.getString(Constants.Backimageurl);
            Glide.with(holder.itemView)
                    .load(foodimage+"/food" + blogComments.get(position).getU_image())
                    .into(holder.image);
        } else {
            //          holder.image.setImageResource(R.drawable.woman);


            try {
                holder.image.setState(AvatarImageView.SHOW_INITIAL);
                holder.image.setAvatarBackgroundColor(context.getResources().getColor(R.color.tbtncolor));
                String s = mProfname.substring(0, 1);
                holder.image.setText(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /*if (blogComments.get(position).getU_image().isEmpty()){
            Glide.with(holder.itemView)
                    .load(R.drawable.user)
                    .into(holder.image);
        }
*/
    }


    @Override
    public int getItemCount() {
        return blogComments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView viewcomment, username;
        AvatarImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            viewcomment = itemView.findViewById(R.id.viewcomment);
            username = itemView.findViewById(R.id.username);
            image = itemView.findViewById(R.id.image);
        }
    }
}
