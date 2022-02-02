package drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.adapter;

import android.content.Context;
import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Diet.CustomUpdateActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Diet.DefalutAndCustomplanActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Diet.DietDatabase_tempselect;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.ListItem;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.activity.Activity_Dietdetails;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.database.DietplanDbhelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.DietCategories;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.Header;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.User_Dietlist;
import drzio.insomnia.treatment.bedtime.yoga.sleep.FitnessApplication;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;


public class Adapter_Userdietnew extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ListItem> items;
    Context context;
    DietplanDbhelper dbhelper;
    boolean isbottom;
    TinyDB tinyDB;
    private int itemscount;
    ArrayList<User_Dietlist> userDietlists2 = new ArrayList<>();
    DietDatabase_tempselect dietDatabase_tempselect;

    public Adapter_Userdietnew(Context context, List<ListItem> items, boolean isbottom, ArrayList<User_Dietlist> userDietlists2) {
        this.context = context;
        this.items = items;
        dbhelper = new DietplanDbhelper(context);
        this.isbottom = isbottom;
        tinyDB = new TinyDB(context);
        this.userDietlists2.addAll(dbhelper.getAllDaysProgress());
        dietDatabase_tempselect = new DietDatabase_tempselect(context);

    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public int getItemViewType(int position) {
        return items.get(position).getItemType();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(context, languages);

        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        if (viewType == ListItem.TYPE_HEADER) {
            View v1 = inflater.inflate(R.layout.item_dietdate, viewGroup, false);
            viewHolder = new ViewHolder1(v1);
        } else if (viewType == ListItem.TYPE_ITEM) {
            View v2 = inflater.inflate(R.layout.item_userdiet, viewGroup, false);
            viewHolder = new ViewHolder2(v2);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof ViewHolder1) {
            Header header = (Header) items.get(position);
            ViewHolder1 VHheader = (ViewHolder1) holder;
            Date c = Calendar.getInstance().getTime();
             Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
            String formattedDate = df.format(c);
            String dates;
            if (header.getName().equals(formattedDate)) {
                dates = "Your Today's Plan";
            } else {
                dates = header.getName();
            }
            VHheader.name.setText(dates);
        } else if (holder instanceof ViewHolder2) {
            final User_Dietlist dietlist = (User_Dietlist) items.get(position);
            ViewHolder2 vh2 = (ViewHolder2) holder;


            Date c = Calendar.getInstance().getTime();
             Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
            String formattedDate = df.format(c);
            String dates;
            if (dietlist.getDate().equals(formattedDate)) {
                vh2.mBtnremove.setVisibility(View.VISIBLE);
                vh2.mBtnupdate.setVisibility(View.VISIBLE);
            } else {
                vh2.mBtnremove.setVisibility(View.GONE);
                vh2.mBtnupdate.setVisibility(View.GONE);
            }
            RequestOptions requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(FitnessApplication.getInstance()).load(dietlist.getImage())
                    .apply(requestOptions).into(vh2.mDietimg);
            vh2.mDietname.setText(dietlist.getName());
            vh2.mDiettype.setText(minerals(dietlist.getCategory_id()));
            vh2.mBtninfo.setVisibility(View.GONE);
            vh2.mBtninfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, Activity_Dietdetails.class);
                    intent.putExtra("dietid", dietlist.getId());
                    context.startActivity(intent);
                }
            });
            vh2.mBtnremove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (int k = 0; k < userDietlists2.size(); k++) {
                        final User_Dietlist dietlist2 = (User_Dietlist) userDietlists2.get(k);
                        if (dietlist2.getDate().equals(formattedDate)) {
                            itemscount++;
                        }
                    }
                    if (itemscount == 1) {
                        Toast.makeText(context, "Keep Atleast One Item In DietPlan", Toast.LENGTH_SHORT).show();
                        itemscount = 0;
                        notifyDataSetChanged();
                    } else {
                        dietDatabase_tempselect.deleteContact(dietlist.getId());
                        dbhelper.deletediet(dietlist.getId(), dietlist.getDate());
                        items.remove(position);
                        itemscount = 0;
                        userDietlists2.clear();
                        userDietlists2.addAll(dbhelper.getAllDaysProgress());
                        notifyDataSetChanged();
                    }
                }
            });

            vh2.mBtnupdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, CustomUpdateActivity.class);
                    intent.putExtra("isFrom", true);
                    context.startActivity(intent);
                    ((DefalutAndCustomplanActivity) context).finish();
                }
            });
        }
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

        public TextView getLabel1() {
            return name;
        }

        public void setLabel1(TextView label1) {
            this.name = label1;
        }

    }


    public class ViewHolder2 extends RecyclerView.ViewHolder {
        ImageView mDietimg;
        TextView mDietname;
        TextView mDiettype;
        LinearLayout mBtninfo;
        ImageView mBtnremove;
        TextView mBtnupdate;

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
            mBtnupdate = (TextView) v
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
