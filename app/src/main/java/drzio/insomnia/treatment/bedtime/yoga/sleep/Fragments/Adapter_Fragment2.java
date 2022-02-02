package drzio.insomnia.treatment.bedtime.yoga.sleep.Fragments;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.rewarded.RewardItem;
import com.google.android.gms.ads.rewarded.RewardedAd;
import com.google.android.gms.ads.rewarded.RewardedAdCallback;
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback;
import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Excerciselist;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Level2;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Purchase;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Restday;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Daymodals;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Weekmodel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Adapter_Fragment2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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
    private boolean isRewarded = false;
    boolean isLastdone;


    public Adapter_Fragment2(Context context, List<Object> items, List<Weekmodel> weekdata, boolean isLastdone) {
        this.context = context;
        this.items = items;
        this.weekdata = weekdata;
        tinydb = new TinyDB(context);
        this.isLastdone = isLastdone;

    }

    @Override
    public int getItemCount() {
        return this.items.size() + weekdata.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position % WEEK_TAG == 0) ? WEEK
                : DAYS;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
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
//        vh2.mTotalcal.setText(daymodals.getDaytxtcal());
//        vh2.mTotaltime.setText(daymodals.getDattxttime());
        if (daymodals.isIsrest()) {
//            vh2.mProgresslayout.setVisibility(View.GONE);
            vh2.mCompleicon.setVisibility(View.GONE);
            vh2.getDayname().setText("Rest Day");
            vh2.mRestthumb.setVisibility(View.VISIBLE);

        } else {
            vh2.getDayname().setText(daymodals.getDayname());
            if (daymodals.getIscompleted()) {
//                vh2.mProgresslayout.setVisibility(View.VISIBLE);
                vh2.mCompleicon.setVisibility(View.VISIBLE);
                vh2.mThumbimg.setVisibility(View.VISIBLE);
            } else {
//                vh2.mProgresslayout.setVisibility(View.VISIBLE);
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
                vh2.getDayname().setTextColor(Color.WHITE);
                vh2.progress.setProgressBarColor(context.getResources().getColor(R.color.tbtncolor));
                vh2.progress.setBackgroundProgressBarColor(context.getResources().getColor(R.color.devidercolor));
                vh2.mThumbimg.setVisibility(View.GONE);
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
                vh2.getDayname().setTextColor(context.getResources().getColor(R.color.headercolor));
                vh2.progress.setProgressBarColor(context.getResources().getColor(R.color.tbtncolor));
                vh2.progress.setBackgroundProgressBarColor(context.getResources().getColor(R.color.devidercolor));
                vh2.mThumbimg.setVisibility(View.GONE);
            }
            vh2.mSelected.setBackgroundResource(0);
        }


        vh2.mBtnday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tinydb.putInt(Constants.ALEVEL_KEY,2);
//                if (!daymodals.getIsLocked() || isRewarded) {
                    if (!isclicked) {
                        isclicked = true;
                        if (daymodals.isIsrest()) {
                            isclicked = false;
                            Intent intent = new Intent(context, Activity_Restday.class);
                            intent.putExtra("planename",daymodals.getPlanname());
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
                            intent.putExtra("level",2);
                            intent.putExtra("lastcomplete",isLastdone);
                            context.startActivity(intent);
                            ((Activity_Level2) context).finish();
                        }
                    }
//                } else {
//                    loadRewardedAd();
//                    Dialogpurchase(daymodals);
//                }
            }
        });
    }

    public void isSelected(int position, Daymodals daymodals) {
        String dname = tinydb.getString(Constants.ALV2DAYCLICK_KEY);
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
//        private LinearLayout mProgresslayout;
        private RelativeLayout mCompleicon;
        private ImageView mThumbimg,mRestthumb;
//        private TextView mTotalcal, mTotaltime;

        public ViewHolder2(View v) {
            super(v);
            mBtnday = (CardView) v.findViewById(R.id.btnday);
            dayname = (TextView) v.findViewById(R.id.dayname);
            progress = (CircularProgressBar) v.findViewById(R.id.donut_progress);
            mSelected = (RelativeLayout) v.findViewById(R.id.colorlayout);
            textpercentage = (TextView) v.findViewById(R.id.nxtcounting);
//            mProgresslayout = (LinearLayout) v.findViewById(R.id.progresslay);
            mCompleicon = (RelativeLayout) v.findViewById(R.id.completedicon);
            mThumbimg = (ImageView) v.findViewById(R.id.thumimg);
            mRestthumb = (ImageView) v.findViewById(R.id.restimg);
//            mTotalcal = (TextView) v.findViewById(R.id.txttotalcal);
//            mTotaltime = (TextView) v.findViewById(R.id.txttotaltime);
        }

        public TextView getDayname() {
            return dayname;
        }

        public void setDayname(TextView ivExample) {
            this.dayname = ivExample;
        }
    }


    public void Dialogpurchase(Daymodals daymodals) {
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_purchase);
        TextView mBtwatch = (TextView) dialog.findViewById(R.id.btwatchad);
        TextView mBtninfo = (TextView) dialog.findViewById(R.id.btninfo);
        TextView mBtbuy = (TextView) dialog.findViewById(R.id.buybtn);
        mBtninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Please Complete Previous Level To Unlock This Level.",Toast.LENGTH_LONG).show();
            }
        });
        mBtwatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLoaded && !isFailtoload){
                    dialog.dismiss();
                    showRewardedVideo(daymodals);
                }else {
                    if (isFailtoload){
                        dialog.dismiss();
                        Toast.makeText(context,"Please Try After Some Time",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context,"Please wait Ad is loading...",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        mBtbuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(context, Activity_Purchase.class);
                context.startActivity(intent);
            }
        });
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
    }


    private void loadRewardedAd() {
        if (rewardedAd == null || !rewardedAd.isLoaded()) {
            rewardedAd = new RewardedAd(context, Constants.admob_rewardad);
            rewardedAd.loadAd(
                    new AdRequest.Builder().build(),
                    new RewardedAdLoadCallback() {
                        @Override
                        public void onRewardedAdLoaded() {
                            isLoaded = true;
                        }

                        @Override
                        public void onRewardedAdFailedToLoad(int errorCode) {
                            isFailtoload = true;
                        }
                    });
        }
    }

    private void showRewardedVideo(Daymodals daymodals) {
        if (rewardedAd.isLoaded()) {
            RewardedAdCallback adCallback =
                    new RewardedAdCallback() {
                        @Override
                        public void onRewardedAdOpened() {
                        }

                        @Override
                        public void onRewardedAdClosed() {
                            loadRewardedAd();
                        }

                        @Override
                        public void onUserEarnedReward(@NonNull RewardItem rewardItem) {
                            isRewarded = true;
                            notifyDataSetChanged();
                        }

                        @Override
                        public void onRewardedAdFailedToShow(int errorCode) {
                            Toast.makeText(context,"Please Wait There is No Ad To Show",Toast.LENGTH_SHORT).show();
                        }
                    };
            rewardedAd.show((Activity_Level2)context, adCallback);
        }
    }

}
