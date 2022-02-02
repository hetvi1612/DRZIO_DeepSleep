package drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.MyplanDbhelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Allexercises;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class MyPlansAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private TinyDB tinyDB;
    Context context;
    ArrayList<Allexercises> mDatalist;
    private MyplanDbhelper mDabases;
    TextView mPtotalexe;

    public MyPlansAdapter(Context context, ArrayList<Allexercises> mDatalist, TextView mPtotalexe) {
        this.context = context;
        this.mDatalist = mDatalist;
        this.mDabases = new MyplanDbhelper(context);
        this.mPtotalexe = mPtotalexe;
        tinyDB = new TinyDB(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuItemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_myplanlist, parent, false);
        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(context, languages);
        return new Myplanholder(menuItemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holders, int position) {
        final Myplanholder holder = (Myplanholder) holders;
        Allexercises mdata = mDatalist.get(position);
//        Constants.mUpdatesenddata.add(mdata);
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(FitnessApplication.getInstance()).load(mdata.getExethumb())
                .apply(requestOptions).into(holder.mExethumbimg);
        holder.mTvpexename.setText(mdata.getExname());
        holder.mTvpexetime.setText(hmsTimeFormatter(mdata.getExtime()));
        int temp = mDatalist.size() - 1;
        if (temp == 0) {
            holder.mBtnremove.setVisibility(View.GONE);
        } else {
            holder.mBtnremove.setVisibility(View.VISIBLE);
        }
        mPtotalexe.setText(mDatalist.size() + " exercises");
        holder.mBtnremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Deletedialog(mdata, position);

            }
        });

    }


    public void Deletedialog(Allexercises mdata, int position) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage(context.getResources().getString(R.string.are_you_delete));
        alertDialogBuilder.setPositiveButton(context.getResources().getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        dialog.dismiss();
                        mDatalist.remove(position);
                        mDabases.deleteExercise(mdata.getExname());
                        Constants.mUpdatesenddata.clear();
                        notifyDataSetChanged();
                    }
                });
        alertDialogBuilder.setNegativeButton(context.getResources().getString(R.string.no), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogs) {
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(context.getResources().getColor(R.color.tbtncolor));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(context.getResources().getColor(R.color.tbtncolor));

            }
        });
        alertDialog.show();
    }


    private String hmsTimeFormatter(long milliSeconds) {
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(milliSeconds), TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));
    }

    @Override
    public int getItemCount() {
        return mDatalist.size();
    }


    class Myplanholder extends RecyclerView.ViewHolder {
        TextView mTvpexename, mTvpexetime;
        ImageView mExethumbimg;
        ImageView mBtnremove;

        public Myplanholder(View itemView) {
            super(itemView);
            mTvpexename = (TextView) itemView.findViewById(R.id.tvpexename);
            mTvpexetime = (TextView) itemView.findViewById(R.id.tvpexetime);
            mExethumbimg = (ImageView) itemView.findViewById(R.id.exceimage);
            mBtnremove = (ImageView) itemView.findViewById(R.id.btn_remove);

        }
    }
}
