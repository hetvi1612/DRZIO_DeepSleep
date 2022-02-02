package drzio.insomnia.treatment.bedtime.yoga.sleep.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.rewarded.RewardedAd;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.text.DecimalFormat;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Excerciselist;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Level1;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Restday;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Daymodals;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Weekmodel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Adapter_Fragment1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final TinyDB tinydb;
    private int WEEK_TAG = 8;
    private List<Object> items;
    private List<Weekmodel> weekdata;
    private final int WEEK = 0, DAYS = 1;
    Context context;
    private int daysdone;
    private int tempprogress;
    boolean isclicked = false;
    private RewardedAd rewardedAd;
    private boolean isLoaded = false;
    private boolean isFailtoload = false;
    private TypedArray imagearray;

    public Adapter_Fragment1(Context context, List<Object> items, List<Weekmodel> weekdata) {
        this.context = context;
        this.items = items;
        this.weekdata = weekdata;
        tinydb = new TinyDB(context);
        Resources res = context.getResources();
        imagearray = res.obtainTypedArray(R.array.allexe_thumbimg);

    }

    @Override
    public int getItemCount() {
        return this.items.size() + weekdata.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position % WEEK_TAG == 0) ? WEEK
                : DAYS;
//        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(context, languages);
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        switch (viewType) {
            case WEEK:
                View v1 = inflater.inflate(R.layout.item_weekdata, viewGroup, false);
                viewHolder = new ViewHolder1(v1);
                break;
            case DAYS:
                View v2 = inflater.inflate(R.layout.item_daydata, viewGroup, false);
                viewHolder = new ViewHolder2(v2);
                break;
            default:
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case WEEK:
                ViewHolder1 vh1 = (ViewHolder1) viewHolder;
                configureViewHolder1(vh1, position / WEEK_TAG);
                break;
            case DAYS:
                ViewHolder2 vh2 = (ViewHolder2) viewHolder;
                configureViewHolder2(vh2, position);
                break;
            default:
                break;
        }
    }


    private void configureViewHolder1(ViewHolder1 vh1, int position) {
        Weekmodel weekmodel = ((Weekmodel) weekdata.get(position));
        vh1.getLabel1().setText(weekmodel.getWname());
        vh1.getLabel2().setText(weekmodel.getCompletedays());
    }

    @SuppressLint({"ResourceType", "SetTextI18n"})
    private void configureViewHolder2(final ViewHolder2 vh2, final int position) {
        final int index = position - (position / WEEK_TAG) - 1;
        final Daymodals daymodals = (Daymodals) items.get(index);
        vh2.getDayname().setText(daymodals.getDayname());
        if (daymodals.isIsrest()) {
            vh2.mCompleicon.setVisibility(View.GONE);
            vh2.getDayname().setText(context.getResources().getString(R.string.rest_day));
            vh2.mRestthumb.setVisibility(View.VISIBLE);
        } else {
            vh2.getDayname().setText(daymodals.getDayname());
            if (daymodals.getIscompleted()) {
                vh2.mCompleicon.setVisibility(View.VISIBLE);
                vh2.mThumbimg.setVisibility(View.VISIBLE);
            } else {
                vh2.mCompleicon.setVisibility(View.VISIBLE);
                vh2.mThumbimg.setVisibility(View.GONE);
            }
            vh2.mRestthumb.setVisibility(View.GONE);
        }

        isSelected(position, daymodals);
        vh2.progress.setProgress((int) daymodals.getDayprogress());
        vh2.textpercentage.setText((int) daymodals.getDayprogress() + "%");

        if (daymodals.getIsselected().equals("true")) {
            vh2.mSelected.setBackgroundResource(R.drawable.gradbtn);
            if (daymodals.getIscompleted()) {
                vh2.getDayname().setTextColor(Color.WHITE);
                vh2.mThumbimg.setVisibility(View.VISIBLE);
            } else {
                if (daymodals.getDayprogress() > 100){
                    vh2.getDayname().setTextColor(Color.WHITE);
                    vh2.mThumbimg.setVisibility(View.VISIBLE);
                }else {
                    vh2.getDayname().setTextColor(Color.WHITE);
                    vh2.progress.setProgressBarColor(context.getResources().getColor(R.color.tbtncolor));
                    vh2.progress.setBackgroundProgressBarColor(context.getResources().getColor(R.color.devidercolor));
                    vh2.mThumbimg.setVisibility(View.GONE);
                }
            }
            vh2.textpercentage.setTextColor(Color.WHITE);
        } else {
            vh2.textpercentage.setTextColor(context.getResources().getColor(R.color.headercolor));
            if (daymodals.getIscompleted()) {
                vh2.getDayname().setTextColor(context.getResources().getColor(R.color.headercolor));
                vh2.progress.setProgressBarColor(context.getResources().getColor(R.color.tbtncolor));
                vh2.progress.setBackgroundProgressBarColor(context.getResources().getColor(R.color.devidercolor));
                vh2.mThumbimg.setVisibility(View.VISIBLE);
            } else {
                if (daymodals.getDayprogress() > 100){
                    vh2.getDayname().setTextColor(context.getResources().getColor(R.color.headercolor));
                    vh2.progress.setProgressBarColor(context.getResources().getColor(R.color.tbtncolor));
                    vh2.progress.setBackgroundProgressBarColor(context.getResources().getColor(R.color.devidercolor));
                    vh2.mThumbimg.setVisibility(View.VISIBLE);
                }else {
                    vh2.getDayname().setTextColor(context.getResources().getColor(R.color.headercolor));
                    vh2.progress.setProgressBarColor(context.getResources().getColor(R.color.tbtncolor));
                    vh2.progress.setBackgroundProgressBarColor(context.getResources().getColor(R.color.devidercolor));
                    vh2.mThumbimg.setVisibility(View.GONE);
                }

            }
            vh2.mSelected.setBackgroundResource(0);
        }


        vh2.mBtnday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tinydb.putInt(Constants.ALEVEL_KEY, 1);
                if (!isclicked) {
                    isclicked = true;
                    if (daymodals.isIsrest()) {
                        isclicked = false;
                        Intent intent = new Intent(context, Activity_Restday.class);
                        intent.putExtra("planename", daymodals.getPlanname());
                        context.startActivity(intent);
                    } else {
                        if (daymodals.getIscompleted()) {
                            tempprogress = 100;
                        } else {
                            tempprogress = (int) daymodals.getDayprogress();
                        }
                        Intent intent = new Intent(context, Activity_Excerciselist.class);
                        intent.putExtra("dname", daymodals.getDayname());
                        intent.putExtra("pos", index);
                        intent.putExtra("dayprogrss", tempprogress);
                        intent.putExtra("level", 1);
                        context.startActivity(intent);
                        ((Activity_Level1) context).finish();
                    }
                }
            }
        });
    }


    public String decimalpoint(float f) {
        String time;
        DecimalFormat df = new DecimalFormat("#.00");
        time = String.valueOf(df.format(f));
        return time;
    }

    public void isSelected(int position, Daymodals daymodals) {
        String dname = tinydb.getString(Constants.DAYCLICK_KEY);
        if (dname.equals(daymodals.getDayname())) {
            daymodals.setIsselected("true");
        } else {
            daymodals.setIsselected("false");
        }
    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {

        private TextView name, txtcount2;

        public ViewHolder1(View v) {
            super(v);
            name = (TextView) v.findViewById(R.id.weekname);
            txtcount2 = (TextView) v.findViewById(R.id.textcount);
        }

        public TextView getLabel1() {
            return name;
        }

        public void setLabel1(TextView label1) {
            this.name = label1;
        }

        public TextView getLabel2() {
            return txtcount2;
        }

        public void setLabel2(TextView label2) {
            this.txtcount2 = label2;
        }
    }


    public class ViewHolder2 extends RecyclerView.ViewHolder {
        CardView mBtnday;
        private TextView dayname, textpercentage;
        private CircularProgressBar progress;
        private RelativeLayout mSelected;
        private RelativeLayout mCompleicon;
        private ImageView mThumbimg, mRestthumb;

        public ViewHolder2(View v) {
            super(v);
            mBtnday = (CardView) v.findViewById(R.id.btnday);
            dayname = (TextView) v.findViewById(R.id.dayname);
            progress = (CircularProgressBar) v.findViewById(R.id.donut_progress);
            mSelected = (RelativeLayout) v.findViewById(R.id.colorlayout);
            textpercentage = (TextView) v.findViewById(R.id.nxtcounting);
            mCompleicon = (RelativeLayout) v.findViewById(R.id.completedicon);
            mThumbimg = (ImageView) v.findViewById(R.id.thumimg);
            mRestthumb = (ImageView) v.findViewById(R.id.restimg);
        }

        public TextView getDayname() {
            return dayname;
        }

        public void setDayname(TextView ivExample) {
            this.dayname = ivExample;
        }
    }


}
