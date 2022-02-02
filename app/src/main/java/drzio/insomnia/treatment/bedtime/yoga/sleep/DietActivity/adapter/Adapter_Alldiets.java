package drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.io.Serializable;
import java.util.List;

import dev.uchitel.eventex.UIEvent;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Apiclients;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.activity.Activity_Dietdetails;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.DietitemsData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.Selecteditems;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;


public class Adapter_Alldiets extends RecyclerView.Adapter<Adapter_Alldiets.ViralvideoHolder> {

    private List<DietitemsData.Datalist> effectItems;

    private Context context;
    private int selected = 0;
    private boolean mSuccess;
    private int mPos;
    private boolean iscallviewapi = false;
    RecyclerView recyclerView;
    private TinyDB tinyDB;

    public Adapter_Alldiets(Context context, List<DietitemsData.Datalist> effectItems, RecyclerView recyclerView) {
        this.effectItems = effectItems;
        this.context = context;
        this.recyclerView = recyclerView;
        tinyDB = new TinyDB(context);

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public ViralvideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(context, languages);

        View view = LayoutInflater.from(context).inflate(R.layout.item_dietimages,
                parent, false);
        ViralvideoHolder viewHolder = new ViralvideoHolder(view);
        viewHolder.mBtnselect = (CheckBox) view
                .findViewById(R.id.btncheck);
        viewHolder.thumbImage = (ImageView) view
                .findViewById(R.id.dietimg);
        viewHolder.mItemlay = (RelativeLayout) view
                .findViewById(R.id.dietlayout);
        viewHolder.mTxtName = (TextView) view
                .findViewById(R.id.dietname);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final ViralvideoHolder holder, final int position) {
        final DietitemsData.Datalist allImages = effectItems.get(position);
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(FitnessApplication.getInstance()).load(allImages.getImage())
                .apply(requestOptions).into(holder.thumbImage);
        if (allImages.isSelected()) {
            holder.mBtnselect.setChecked(true);
        } else {
            holder.mBtnselect.setChecked(false);
        }
        holder.mTxtName.setText(allImages.getName());
        holder.mItemlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("listdiet", (Serializable) effectItems);
                Intent intent = new Intent(context, Activity_Dietdetails.class);
                intent.putExtra("isbottom", true);
                intent.putExtra("dietid", allImages.getId());
                intent.putExtras(bundle1);
                context.startActivity(intent);
            }
        });
        holder.mBtnselect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    allImages.setSelected(true);
                    new UIEvent(12345).post(buttonView);
                    Selecteditems selecteditems = new Selecteditems();
                    selecteditems.setId(allImages.getId());
                    selecteditems.setName(allImages.getName());
                    selecteditems.setDescription(allImages.getDescription());
                    selecteditems.setImage(allImages.getImage());
                    selecteditems.setIs_active(allImages.getIs_active());
                    selecteditems.setUser_type(allImages.getUser_type());
                    selecteditems.setCategory_id(allImages.getCategory_id());
                    Apiclients.mTemplist.add(selecteditems);
                    if (!recyclerView.isComputingLayout()) {
                        notifyItemChanged(position);
                    }
//                    notifyDataSetChanged();
                }else {
                    if (!recyclerView.isComputingLayout()) {
                        notifyItemChanged(position);
                    }
                    allImages.setSelected(false);
                }
            }
        });
        holder.mBtnselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    private boolean isConnectingToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager
                .getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
            return true;
        else
            return false;
    }


    @Override
    public int getItemCount() {
        return effectItems == null ? 0 : effectItems.size();
    }


    class ViralvideoHolder extends RecyclerView.ViewHolder {
        ImageView thumbImage;
        CheckBox mBtnselect;
        TextView mTxtName;
        RelativeLayout mItemlay;


        public ViralvideoHolder(View itemView) {
            super(itemView);
        }
    }

}
