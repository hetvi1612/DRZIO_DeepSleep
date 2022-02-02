package drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.StoreBannerData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Adapter_Cbanner extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private int mCount;
    private TinyDB tinyDB;
    public ArrayList<StoreBannerData.Datalist> mBannerdata ;

    public Adapter_Cbanner(Context context, ArrayList<StoreBannerData.Datalist> mBannerdata) {
        this.context = context;
        this.mBannerdata = mBannerdata;
        tinyDB = new TinyDB(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuItemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.image_slider_layout_itemtwo, parent, false);

        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(context, languages);
        return new ItemViewHolder(menuItemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holders, int position) {
        final ItemViewHolder holder = (ItemViewHolder) holders;
        StoreBannerData.Datalist mData = mBannerdata.get(position);
        Glide.with(FitnessApplication.getInstance())
                .load(mData.getImage_name())
                .into(holder.imageViewBackground);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gotoplay(mData.getLink());
            }
        });
    }




    public void Gotoplay(String mPlaylink) {
        Uri uri1 = Uri.parse(mPlaylink);
        Intent myAppLinkToMarket = new Intent(Intent.ACTION_VIEW, uri1);
        try {
            context.startActivity(myAppLinkToMarket);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "You don't have Google Play installed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public int getItemCount() {
        return mBannerdata.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        RoundedImageView imageViewBackground;
        FrameLayout mGbannerad;
        LinearLayout mNativebanner;

        ItemViewHolder(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            mGbannerad = itemView.findViewById(R.id.adframe);
            mNativebanner = (LinearLayout) itemView.findViewById(R.id.templateContainer);
            this.itemView = itemView;

        }
    }
}
