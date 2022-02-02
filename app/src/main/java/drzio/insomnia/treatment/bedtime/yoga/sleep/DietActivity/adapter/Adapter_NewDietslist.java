package drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.activity.Activity_ChangeFixplan;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.activity.Activity_Dietplans;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.activity.Activity_SingleDietdetails;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.database.DietplanDbhelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.DietCategories;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.User_Dietlist;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;


public class Adapter_NewDietslist extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<User_Dietlist> items;
    Context context;
    DietplanDbhelper dbhelper;
    Activity_Dietplans activity;
    TinyDB tinyDB;
    private int itemscount;

    public Adapter_NewDietslist(Context context, List<User_Dietlist> items, Activity_Dietplans activity) {
        this.context = context;
        this.items = items;
        dbhelper = new DietplanDbhelper(context);
        this.activity = activity;
        tinyDB = new TinyDB(context);

    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(context, languages);
        RecyclerView.ViewHolder viewHolder = null;

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View v2 = inflater.inflate(R.layout.item_fixuserdiet, viewGroup, false);
        viewHolder = new ViewHolder2(v2);
        assert viewHolder != null;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder holder, int position) {
        final User_Dietlist dietlist = (User_Dietlist) items.get(position);
        ViewHolder2 vh2 = (ViewHolder2) holder;
        vh2.mBtnremove.setVisibility(View.GONE);
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(FitnessApplication.getInstance()).load(dietlist.getImage())
                .apply(requestOptions).into(vh2.mDietimg);
        vh2.mDietname.setText(dietlist.getName());
        vh2.mDiettype.setText(dietlist.getDate());
        vh2.mDiettype.setMaxLines(3);
        vh2.mBtninfo.setVisibility(View.GONE);
        vh2.mClicklayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Activity_SingleDietdetails.class);
                intent.putExtra("dietid", dietlist.getId());
                intent.putExtra("cateid", dietlist.getCategory_id());
                context.startActivity(intent);
            }
        });
        vh2.mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Activity_ChangeFixplan.class);
                intent.putExtra("catname",minerals(dietlist.getCategory_id()));
                intent.putExtra("catid",dietlist.getCategory_id());
                context.startActivity(intent);
            }
        });
        vh2.mBtnremove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int temp = items.size() - 1;
                if (temp  == 0){
                    Toast.makeText(context,"Keep Atleast One Item In DietPlan",Toast.LENGTH_SHORT).show();
                }else {
                    items.remove(position);
                }
                notifyDataSetChanged();
                String json = new Gson().toJson((Object) items);
                tinyDB.putString(Constants.FIXDIET1_KEY, json);
            }
        });
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
        TextView mUpdate;

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
            mUpdate = (TextView) v
                    .findViewById(R.id.btn_update);

        }

        public TextView getDayname() {
            return mDietname;
        }

        public void setDayname(TextView ivExample) {
            this.mDietname = ivExample;
        }
    }
}
