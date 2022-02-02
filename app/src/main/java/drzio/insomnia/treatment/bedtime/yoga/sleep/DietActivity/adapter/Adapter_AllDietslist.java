package drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.activity.Activity_SingleDietdetails;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.database.DietplanDbhelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.DietCategories;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.User_Dietlist;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;


public class Adapter_AllDietslist extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<User_Dietlist> items;
    Context context;
    DietplanDbhelper dbhelper;
    RecyclerView recyclerView;
    private TinyDB tinyDB;

    public Adapter_AllDietslist(Context context, List<User_Dietlist> items, RecyclerView recyclerView) {
        this.context = context;
        this.items = items;
        dbhelper = new DietplanDbhelper(context);
        this.recyclerView = recyclerView;
        tinyDB = new TinyDB(context);

    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public int getItemViewType(int position) {
//        return items.get(position).getItemType();
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(context, languages);
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
//        if (viewType == ListItem.TYPE_HEADER) {
//            View v1 = inflater.inflate(R.layout.item_dietdate, viewGroup, false);
//            viewHolder = new ViewHolder1(v1);
//        } else if (viewType == ListItem.TYPE_ITEM) {
        View v2 = inflater.inflate(R.layout.item_userdiet, viewGroup, false);
        viewHolder = new ViewHolder2(v2);
//        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

       /* if (holder instanceof ViewHolder1) {
            Header header = (Header) items.get(position);
            ViewHolder1 VHheader = (ViewHolder1) holder;
            String dates = header.getName();
            VHheader.name.setText(dates);
//        } else*/ /*if (holder instanceof ViewHolder2) {*/
        final User_Dietlist dietlist = (User_Dietlist) items.get(position);
        ViewHolder2 vh2 = (ViewHolder2) holder;
        vh2.mBtnremove.setVisibility(View.GONE);
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(FitnessApplication.getInstance()).load(dietlist.getImage())
                .apply(requestOptions).into(vh2.mDietimg);
        vh2.mDietname.setText(dietlist.getName());
        vh2.mDiettype.setText(minerals(dietlist.getCategory_id()));
        vh2.mDiettype.setMaxLines(1);
        vh2.mBtninfo.setVisibility(View.VISIBLE);
        vh2.mCheckbox.setVisibility(View.VISIBLE);
        if (dietlist.isCheckd) {
            vh2.mCheckbox.setChecked(true);
        } else {
            vh2.mCheckbox.setChecked(false);
        }
        vh2.mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    dietlist.setCheckd(isChecked);
                } else {
                    dietlist.setCheckd(isChecked);
                }
                if (!recyclerView.isComputingLayout()) {
                    notifyItemChanged(position);
                }

            }
        });
        vh2.mClicklayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Activity_SingleDietdetails.class);
                intent.putExtra("dietid", dietlist.getId());
                intent.putExtra("cateid", dietlist.getCategory_id());
                context.startActivity(intent);
            }
        });
//        }
    }


    public String minerals(String catids) {
        String catname = null;
        for (int i = 0; i < DietCategories.getGifCatlistdata().size(); i++) {
            if (DietCategories.getGifCatlistdata().get(i).getCategory_id().equals(catids)) {
                catname = DietCategories.getGifCatlistdata().get(i).getCatname();
            }
        }
        return catname;
    }


    public class ViewHolder1 extends RecyclerView.ViewHolder {

        private TextView name, txtcount2;

        public ViewHolder1(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.datetxt);
        }


    }


    public class ViewHolder2 extends RecyclerView.ViewHolder {
        ImageView mDietimg;
        TextView mDietname;
        TextView mDiettype;
        LinearLayout mBtninfo;
        ImageView mBtnremove;
        RelativeLayout mClicklayout;
        CheckBox mCheckbox;

        ViewHolder2(View v) {
            super(v);
            mDietimg = (ImageView) v
                    .findViewById(R.id.dietimg);
            mDietname = (TextView) v
                    .findViewById(R.id.dietname);
            mDiettype = (TextView) v
                    .findViewById(R.id.dietype);
            mBtninfo = (LinearLayout) v
                    .findViewById(R.id.btninfo);
            mBtnremove = (ImageView) v
                    .findViewById(R.id.btn_remove);
            mClicklayout = (RelativeLayout) v
                    .findViewById(R.id.dietlayout);
            mCheckbox = (CheckBox) v
                    .findViewById(R.id.btncheck);

        }

        public TextView getDayname() {
            return mDietname;
        }

        public void setDayname(TextView ivExample) {
            this.mDietname = ivExample;
        }
    }
}
