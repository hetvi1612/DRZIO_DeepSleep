package drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Exercisedetail;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Purchase;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Allexercises;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class AllexerciseAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final Context context;
    private List<Allexercises> exerciselist;
    boolean isUpdate ;
    String pname;
    TinyDB tinyDB;
    public AllexerciseAdapter(Context context, List<Allexercises> exerciselist, boolean isUpdate, String pname) {
        this.context = context;
        this.exerciselist = exerciselist;
        this.isUpdate = isUpdate;
        this.pname = pname;
        tinyDB = new TinyDB(context);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuItemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_allexerciselist, parent, false);
        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(context, languages);
        return new Allexerciseholder(menuItemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holders, int position) {
        final Allexerciseholder holder = (Allexerciseholder) holders;
        final Allexercises allexercises = exerciselist.get(position);
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(FitnessApplication.getInstance()).load(allexercises.getExethumb())
                .apply(requestOptions).into(holder.mExethums);
        if (!allexercises.isLocked()){
            holder.mLockimg.setVisibility(View.GONE);
        }else {
            holder.mLockimg.setVisibility(View.VISIBLE);
        }
        holder.mExcername.setText(allexercises.getExname());
        holder.mNextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!allexercises.isLocked()){
                    Intent intent = new Intent(context, Activity_Exercisedetail.class);
                    intent.putExtra("mname", allexercises.getExname());
                    intent.putExtra("mThumblink",allexercises.getExethumb());
                    intent.putExtra("mdesc",allexercises.getExdescr());
                    intent.putExtra("mCalorie",allexercises.getExecalorie());
                    intent.putExtra("mvideolink",allexercises.getEximglink());
                    intent.putExtra("myoutube",allexercises.getVideolink());
                    intent.putExtra("update",isUpdate);
                    intent.putExtra("pname",pname);
                //    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intent);
                    /*((Activity_Addexercise)context).finish();*/
                }else {
                    Dialogpurchase();
                }
            }
        });

    }


    public void Dialogpurchase() {
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_purchasenew);
        TextView mBtbuy = (TextView) dialog.findViewById(R.id.buybtn);

        mBtbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(context, Activity_Purchase.class);
                context.startActivity(intent);
            }
        });
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }


    @Override
    public int getItemCount() {
        return exerciselist.size();
    }


    class Allexerciseholder extends RecyclerView.ViewHolder {
        TextView mExcername;
        ImageView mBtnadd;
        RelativeLayout mNextbtn;
        ImageView mExethums;
        ImageView mLockimg;

        public Allexerciseholder(View itemView) {
            super(itemView);
            mBtnadd = (ImageView) itemView.findViewById(R.id.addbtn);
            mExcername = (TextView) itemView.findViewById(R.id.excername);
            mNextbtn = (RelativeLayout) itemView.findViewById(R.id.nextbtn);
            mExethums = (ImageView) itemView.findViewById(R.id.exceimage);
            mLockimg = (ImageView) itemView.findViewById(R.id.imglock);

        }
    }
}
