package drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.ArrayList;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.Appdata;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Store_Adapter_DataAllapps extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<Appdata.Datalist> mAppdatamodel = new ArrayList<>();
    private TinyDB tinyDB;

    public Store_Adapter_DataAllapps(Context context, ArrayList<Appdata.Datalist> mAppdatamodel) {
        this.context = context;
        this.mAppdatamodel = mAppdatamodel;
        tinyDB = new TinyDB(context);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuItemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.appstore_item_allapps, parent, false);
        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(context, languages);
        return new AllappsHolder(menuItemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holders, int i) {
        final AllappsHolder holder = (AllappsHolder) holders;
        final Appdata.Datalist appdata = mAppdatamodel.get(i);

        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(FitnessApplication.getInstance()).load(appdata.getImage_name())
                .apply(requestOptions).into( holder.mIvappicon);
        holder.mTvappname.setText(appdata.getApp_name());
        holder.mTvappdesc.setHtml(appdata.getApp_desc());
        holder.mTvapprating.setText(appdata.getRating() + " \u2605");
        int temp = i+1;
        holder.mTvcount.setText(String.valueOf(temp));
        holder.mMainlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rateus(appdata.getAap_link());
            }
        });
        holder.mTxtlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Rateus(appdata.getAap_link());
            }
        });
    }

    @Override
    public int getItemCount() {
        return mAppdatamodel.size();
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

    class AllappsHolder extends RecyclerView.ViewHolder {
       ImageView mIvappicon;
       TextView mTvappname,mTvappsize,mTvapprating,mTvcount;
        HtmlTextView mTvappdesc;
        LinearLayout mMainlayout;
        LinearLayout mTxtlayout;


        public AllappsHolder(View itemView) {
            super(itemView);
            mMainlayout = itemView.findViewById(R.id.applayout);
            mTxtlayout = itemView.findViewById(R.id.txtlayout);
            mTvappname = (TextView) itemView.findViewById(R.id.tvappname);
            mTvappdesc = (HtmlTextView) itemView.findViewById(R.id.tvdesc);
            mTvappsize = (TextView) itemView.findViewById(R.id.tvsize);
            mTvapprating = (TextView) itemView.findViewById(R.id.tvrating);
            mIvappicon = (ImageView) itemView.findViewById(R.id.ivappicon);
            mTvcount = (TextView) itemView.findViewById(R.id.tvno);

        }
    }
}
