package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.covid19.disctrictWise.CovidDistrictWiseListDetailsModel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Adapter_districtdata extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity activity;
    public List<CovidDistrictWiseListDetailsModel> covidDistrictWiseListDetailsModels = new ArrayList();
    private TinyDB tinyDB;

    public Adapter_districtdata(Activity activity, List<CovidDistrictWiseListDetailsModel> covidDistrictWiseListDetailsModels) {
        this.activity = activity;
        this.covidDistrictWiseListDetailsModels = covidDistrictWiseListDetailsModels;
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
        CovidDistrictWiseListDetailsModel data = covidDistrictWiseListDetailsModels.get(position);
        holder.mMainlay.setVisibility(View.VISIBLE);
        holder.mTvstatenane.setText(data.getDistrict());
        holder.mTvcasenum.setText(String.valueOf(data.getConfirmed()));
        holder.mTvdeathnum.setText(String.valueOf(data.getDeceased()));
        holder.mTvrecovernum.setText(String.valueOf(data.getRecovered()));
    }

    @Override
    public int getItemCount() {
        return covidDistrictWiseListDetailsModels.size();
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
