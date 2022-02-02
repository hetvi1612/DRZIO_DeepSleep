package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.covid19.stateWise.CovidStateWiseListDetailsModel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;


public class Adapter_statelist extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity activity;
    private List<CovidStateWiseListDetailsModel> covidStateWiseListDetailsModelList;
    private TinyDB tinyDB;

    public Adapter_statelist(Activity activity, List<CovidStateWiseListDetailsModel> covidStateWiseListDetailsModelList) {
        this.activity = activity;
        this.covidStateWiseListDetailsModelList = covidStateWiseListDetailsModelList;
        tinyDB = new TinyDB(activity);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuItemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_statedata, parent, false);
        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(activity, languages);
        return new Statedataholder(menuItemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holders, int position) {
        final Statedataholder holder = (Statedataholder) holders;
        CovidStateWiseListDetailsModel data = covidStateWiseListDetailsModelList.get(position);
        holder.mMainlay.setVisibility(View.VISIBLE);
        holder.mTvstatenane.setText(data.getState());
        holder.mTvcasenum.setText(data.getConfirmed());
        holder.mTvdeathnum.setText(data.getDeaths());
        holder.mTvrecovernum.setText(data.getRecovered());
        holder.mMainlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, Activiy_DistrictData.class);
                intent.putExtra("statename",data.getState());
                intent.putExtra("tcases",data.getConfirmed());
                intent.putExtra("tdeaths",data.getDeaths());
                intent.putExtra("trecovered",data.getRecovered());
                activity.startActivity(intent);
//                activity.finish();
            }
        });
    }

    @Override
    public int getItemCount() {
        return covidStateWiseListDetailsModelList.size();
    }

    class Statedataholder extends RecyclerView.ViewHolder {
        TextView mTvstatenane, mTvcasenum, mTvdeathnum, mTvrecovernum;
        LinearLayout mMainlay;

        Statedataholder(View itemView) {
            super(itemView);
            mMainlay = (LinearLayout) itemView.findViewById(R.id.mainlayout);
            mTvstatenane = (TextView) itemView.findViewById(R.id.tvstatename);
            mTvcasenum = (TextView) itemView.findViewById(R.id.tvcasesnum);
            mTvdeathnum = (TextView) itemView.findViewById(R.id.tvdeathnum);
            mTvrecovernum = (TextView) itemView.findViewById(R.id.tvrecovernum);

        }
    }

}
