package drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.Dietitems;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.User_Dietlist;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;


public class Adapter_Selected extends RecyclerView.Adapter<Adapter_Selected.ViralvideoHolder> {

    private List<User_Dietlist> effectItems;
    private List<Dietitems> templist;

    private Context context;
    private int selected = 0;
    private boolean mSuccess;
    private int mPos;
    private boolean iscallviewapi = false;
    TinyDB tinyDB;

    public Adapter_Selected(Context context, List<User_Dietlist> effectItems) {
        this.effectItems = effectItems;
        this.context = context;
        tinyDB = new TinyDB(context);


    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public ViralvideoHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(context, languages);

        View view = LayoutInflater.from(context).inflate(R.layout.item_selected,
                parent, false);
        ViralvideoHolder viewHolder = new ViralvideoHolder(view);
        viewHolder.thumbImage = (ImageView) view
                .findViewById(R.id.dietimg);
        viewHolder.mTxtName = (TextView) view
                .findViewById(R.id.dietname);
        viewHolder.mBtncheck = (CheckBox) view
                .findViewById(R.id.btncheck);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final ViralvideoHolder holder, final int position) {
        final User_Dietlist allImages = effectItems.get(position);
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(FitnessApplication.getInstance()).load(allImages.getImage())
                .apply(requestOptions).into(holder.thumbImage);
        holder.mTxtName.setText(allImages.getName());
//        if (mPos == position){
//            holder.mBtncheck.setChecked(false);
//        }
        holder.mBtncheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    holder.mBtncheck.setChecked(true);
                    mPos = position;
//                    datalist.add(allImages);

                    Constants.mPoslist.add(position);
                    notifyDataSetChanged();
                }else {
                    try {
                        for (int i = 0; i< Constants.mPoslist.size(); i++){
                            if (Constants.mPoslist.get(i) == position){
                                Constants.mPoslist.remove(i);
                            }
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    holder.mBtncheck.setChecked(false);
                }
            }
        });
    }




    @Override
    public int getItemCount() {
        return effectItems == null ? 0 : effectItems.size();
    }


    class ViralvideoHolder extends RecyclerView.ViewHolder {
        ImageView thumbImage;
        TextView mTxtName;
        CheckBox mBtncheck;


        ViralvideoHolder(View itemView) {
            super(itemView);
        }
    }

}
