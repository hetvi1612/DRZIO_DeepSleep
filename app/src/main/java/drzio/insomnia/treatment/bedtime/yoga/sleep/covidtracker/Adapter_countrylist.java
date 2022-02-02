package drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker;

import android.app.Activity;
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
import drzio.insomnia.treatment.bedtime.yoga.sleep.covidtracker.model.mathdro.CountryData;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;


public class Adapter_countrylist extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Activity activity;
    private List<CountryData> countryDataList;
    private TinyDB tinydb;

    public Adapter_countrylist(Activity activity, List<CountryData> countryDataList) {
        this.activity = activity;
        this.countryDataList = countryDataList;
        tinydb = new TinyDB(activity);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuItemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_countrydata, parent, false);

        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(activity, languages);

        return new Statedataholder(menuItemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holders, int position) {
        final Statedataholder holder = (Statedataholder) holders;
        CountryData data = countryDataList.get(position);
        holder.mMainlay.setVisibility(View.VISIBLE);
        holder.mTvstatenane.setText(data.getCountryname());
        holder.mTvcasenum.setText(data.getTotalcases());
        holder.mTvdeathnum.setText(data.getTotaldeaths());
        holder.mTvrecovernum.setText(data.getTotalrecover());
        holder.mTvtodaycases.setText("+"+ data.getNewcases());
        holder.mTvtodaydeaths.setText("+" + data.getNewdeaths());
    }

    @Override
    public int getItemCount() {
        return countryDataList.size();
    }

    class Statedataholder extends RecyclerView.ViewHolder {
        TextView mTvstatenane, mTvcasenum, mTvdeathnum, mTvrecovernum;
        TextView mTvtodaycases,mTvtodaydeaths;
        LinearLayout mMainlay;

        Statedataholder(View itemView) {
            super(itemView);
            mMainlay = (LinearLayout) itemView.findViewById(R.id.mainlayout);
            mTvstatenane = (TextView) itemView.findViewById(R.id.tvstatename);
            mTvcasenum = (TextView) itemView.findViewById(R.id.tvcasesnum);
            mTvdeathnum = (TextView) itemView.findViewById(R.id.tvdeathnum);
            mTvrecovernum = (TextView) itemView.findViewById(R.id.tvrecovernum);
            mTvtodaycases = (TextView) itemView.findViewById(R.id.tvtodaycases);
            mTvtodaydeaths = (TextView) itemView.findViewById(R.id.tvtodaydeath);

        }
    }

}
