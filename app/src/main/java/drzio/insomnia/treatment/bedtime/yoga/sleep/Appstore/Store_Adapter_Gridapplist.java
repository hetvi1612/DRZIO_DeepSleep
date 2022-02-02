package drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.Appdata;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;


public class Store_Adapter_Gridapplist extends RecyclerView.Adapter<Store_Adapter_Gridapplist.ViralvideoHolder> {
    private Context context;
    ArrayList<Appdata.Datalist> mTopappdatamodel = new ArrayList<>();
    private TinyDB tinyDB;

    public Store_Adapter_Gridapplist(Context context, ArrayList<Appdata.Datalist> mTopappdatamodel) {
        this.context = context;
        this.mTopappdatamodel = mTopappdatamodel;
        tinyDB = new TinyDB(context);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ViralvideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.app_store_item_gridappslist,
                parent, false);
        ViralvideoHolder viewHolder = new ViralvideoHolder(view);
        viewHolder.mIvappicon = (ImageView) view
                .findViewById(R.id.ivappicon);
        viewHolder.mTvappname = (TextView) view
                .findViewById(R.id.tvappname);
        viewHolder.mTvapprating = (TextView) view
                .findViewById(R.id.tvapprating);
        viewHolder.mMainlayout = (RelativeLayout) view
                .findViewById(R.id.cardview);
        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(context, languages);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final ViralvideoHolder holder, final int position) {
        final Appdata.Datalist appdataModel = mTopappdatamodel.get(position);

        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(FitnessApplication.getInstance()).load(appdataModel.getImage_name())
                .apply(requestOptions).into(holder.mIvappicon);
        holder.mTvappname.setText(appdataModel.getApp_name());
        String mRating = appdataModel.getRating() + " \u2605";
        holder.mTvapprating.setText(mRating);
        holder.mMainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rateus(appdataModel.getAap_link());
            }
        });
    }


    public void Rateus(String link) {
        Uri uri1 = Uri.parse(link);
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri1);
        try {
            context.startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "You don't have Google Play installed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public int getItemCount() {
        return mTopappdatamodel == null ? 0 : mTopappdatamodel.size();
    }


    class ViralvideoHolder extends RecyclerView.ViewHolder {
     ImageView mIvappicon;
     TextView mTvappname,mTvapprating;
     RelativeLayout mMainlayout;


        public ViralvideoHolder(View itemView) {
            super(itemView);
        }
    }

}
