package drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.ProgressModal;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

@SuppressLint({"SimpleDateFormat","DefaultLocale"})
public class HistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<ProgressModal> mData;
    private TinyDB tinyDB;


    public HistoryAdapter(Context context, ArrayList<ProgressModal> mData) {
        this.context = context;
        this.mData = mData;
        tinyDB = new TinyDB(context);
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuItemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_history, parent, false);
        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(context, languages);
        return new Historydataholder(menuItemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holders, int position) {
        final Historydataholder holder = (Historydataholder) holders;
        ProgressModal progressModal = mData.get(position);
        holder.mHtvdate.setText(dateformateer(progressModal.getExe_date()));
        String systime = ", " + progressModal.getExe_dtime();
        holder.mHtvtime.setText(systime);
        holder.mHtvdname.setText(progressModal.getExe_dname());
        holder.mHtvstime.setText(hmsTimeFormatter(progressModal.getExe_stime()));
        holder.mHtvkcal.setText(String.valueOf(round(progressModal.getExe_kcal(),1)));

    }


    private static float round(float d, int decimalPlace) {
        return BigDecimal.valueOf(d).setScale(decimalPlace,BigDecimal.ROUND_HALF_UP).floatValue();
    }

    private String hmsTimeFormatter(long milliSeconds) {
        return String.format("%02d:%02d", TimeUnit.MILLISECONDS.toMinutes(milliSeconds), TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    private String dateformateer(String dateaa){
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            Date mDate = null;
            try {
                mDate = dateFormat.parse(dateaa);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            DateFormat dateFormat2 = new SimpleDateFormat("MMM d");
//            assert mDate != null;
            return dateFormat2.format(mDate);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dateaa;
    }


    class Historydataholder extends RecyclerView.ViewHolder {
        TextView mHtvdate,mHtvtime,mHtvdname,mHtvstime,mHtvkcal;

        Historydataholder(View itemView) {
            super(itemView);
            mHtvdate = (TextView) itemView.findViewById(R.id.htvdate);
            mHtvtime = (TextView) itemView.findViewById(R.id.htvtime);
            mHtvdname = (TextView) itemView.findViewById(R.id.htvdname);
            mHtvstime = (TextView) itemView.findViewById(R.id.htvstime);
            mHtvkcal = (TextView) itemView.findViewById(R.id.htvkcal);

        }
    }
}
