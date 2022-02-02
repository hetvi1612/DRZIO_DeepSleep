package drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.StoreBannerData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class StoreBanner_Adapter extends
        SliderViewAdapter<StoreBanner_Adapter.SliderAdapterVH> {

    private Context context;
    private int mCount;
    public ArrayList<StoreBannerData.Datalist> mBannerdata ;
    private TinyDB tinyDB;

    public StoreBanner_Adapter(Context context, ArrayList<StoreBannerData.Datalist> mBannerdata) {
        this.context = context;
        this.mBannerdata = mBannerdata;
        tinyDB = new TinyDB(context);
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
    public StoreBanner_Adapter.SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item, null);
        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(context, languages);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(StoreBanner_Adapter.SliderAdapterVH viewHolder, int position) {
        StoreBannerData.Datalist mData = mBannerdata.get(position);

        Glide.with(FitnessApplication.getInstance())
                .load(mData.getImage_name())
                .centerCrop()
                .into(viewHolder.imageViewBackground);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Gotoplay(mData.getLink());
            }
        });
    }

    @Override
    public int getCount() {
        if (mBannerdata != null){
            return mBannerdata.size();
        }else {
            return 0;
        }
    }


    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        FrameLayout mGbannerad;
        LinearLayout mNativebanner;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            mGbannerad = itemView.findViewById(R.id.adframe);
            mNativebanner = (LinearLayout) itemView.findViewById(R.id.templateContainer);
            this.itemView = itemView;
        }
    }


}
