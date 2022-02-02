package drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_MyTraining;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity_Myplanstart;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.MyplanDbhelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Allexercises;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Myplansmodel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class MytrainingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<Myplansmodel> mDatalist;
    private MyplanDbhelper mDabases;
    private List<Allexercises> templist = new ArrayList<>();
    private ArrayList<Allexercises> senddata = new ArrayList<>();
    private TinyDB tinyDB;

    public MytrainingAdapter(Context context, ArrayList<Myplansmodel> mDatalist) {
        this.context = context;
        this.mDatalist = mDatalist;
        mDabases = new MyplanDbhelper(context);
        tinyDB = new TinyDB(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuItemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_planslist, parent, false);
        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(context, languages);  return new Mytrainingholder(menuItemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holders, final int position) {
        final Mytrainingholder holder = (Mytrainingholder) holders;
        templist = mDabases.getAllDaysProgress();
        final Myplansmodel myplansmodel = mDatalist.get(position);
        holder.mTxtplanename.setText(myplansmodel.getPlanname());
        String tempexe;
        if (Integer.parseInt(myplansmodel.getTotalexe()) == 1) {
            tempexe = " exercise";
        } else {
            tempexe = " exercises";
        }
        String totalexe = myplansmodel.getTotalexe() + tempexe;
        holder.mTxtplanexe.setText(totalexe);
        holder.mPlanscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (senddata != null) {
                    senddata.clear();
                }
                for (int i = 0; i < templist.size(); i++) {
                    Allexercises allexercises = templist.get(i);
                    if (myplansmodel.getPlanname().equals(allexercises.getPlanname())) {
                        Allexercises allexe = new Allexercises();
                        allexe.setExname(allexercises.getExname());
                        allexe.setExtime((int) allexercises.getExtime());
                        allexe.setExethumb(allexe.getExethumb());
                        senddata.add(allexercises);
                    }
                }
                Bundle bundle1 = new Bundle();
                bundle1.putSerializable("myplalist", (Serializable) senddata);
                Intent intent = new Intent(context, Activity_Myplanstart.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra("pname", myplansmodel.getPlanname());
                intent.putExtras(bundle1);
                context.startActivity(intent);
                ((Activity_MyTraining)context).finish();

            }
        });
        holder.mBtnoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Deletedialog(myplansmodel,position);

            }
        });

    }

    public void Deletedialog(Myplansmodel myplansmodel, int position) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage(context.getResources().getString(R.string.are_you_delete));
        alertDialogBuilder.setPositiveButton(context.getResources().getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        dialog.dismiss();
                        mDabases.deletePlan(myplansmodel.getPlanname());
                        mDatalist.remove(position);
                        notifyDataSetChanged();
                        notifyDataSetChanged();
                    }
                });
        alertDialogBuilder.setNegativeButton(context.getResources().getString(R.string.no), new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogs) {
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(context.getResources().getColor(R.color.tbtncolor));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(context.getResources().getColor(R.color.tbtncolor));

            }
        });
        alertDialog.show();
    }


    @Override
    public int getItemCount() {
        return this.mDatalist.size();
    }


    class Mytrainingholder extends RecyclerView.ViewHolder {
        CardView mPlanscard;
        TextView mTxtplanename, mTxtplanexe;
        ImageView mBtnoption;

        public Mytrainingholder(View itemView) {
            super(itemView);
            mPlanscard = (CardView) itemView.findViewById(R.id.plancard);
            mTxtplanename = (TextView) itemView.findViewById(R.id.planname);
            mTxtplanexe = (TextView) itemView.findViewById(R.id.planexe);
            mBtnoption = (ImageView) itemView.findViewById(R.id.btn_option);

        }
    }
}
