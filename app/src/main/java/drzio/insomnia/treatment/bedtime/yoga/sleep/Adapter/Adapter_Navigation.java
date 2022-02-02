package drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_MyTraining;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Purchase;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Reports;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.Activity_Settings;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Activity.MainActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.AppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.ChooseGenderActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW.MaleAppstoreActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity.Activity_Bloglists;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.BDatabaseOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.DatabaseOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.HistoryProgressOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.MyplanDbhelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Diet.CustomUpdateActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Diet.DefalutAndCustomplanActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.database.DietplanDbhelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Discoverpage.Activity_Discover;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Graphdata.GraphdataOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Graphdata.WeightGraphdataOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.ReminderMainActivity;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Adapter_Navigation extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String[] mNamelist = new String[]{"My Training", "Nutrition Plan", "Tips",
            "Reports", "Discover Plan", "Customize Plan", "Remove Ads", "New Apps", "Reminder", "Language Options",
            "Settings", "Rate Us", "Share App", "Restart Progress"};

    private int[] mImagelist = new int[]{R.drawable.ic_navtraining, R.drawable.ic_navmealp, R.drawable.ic_navtips,
            R.drawable.ic_navreport, R.drawable.ic_navdiscover, R.drawable.ic_navcustomplan, R.drawable.btnremoveadcolor,
            R.drawable.ic_storeicon, R.drawable.ic_navreminder,
            R.drawable.ic_navlanguage, R.drawable.ic_navsetting,
            R.drawable.ic_navrate, R.drawable.ic_navshare, R.drawable.ic_navreset
    };
    Context context;
    DrawerLayout mDrawer;
    private TinyDB tinydb;
    MainActivity activity;
    private DatabaseOperations databaseOperations;
    private BDatabaseOperations databaseOperations2;
    private HistoryProgressOperations historyProgressOperations;
    private MyplanDbhelper myplanDbhelper;
    private DietplanDbhelper dietplanDbhelper;
    private GraphdataOperations graphdataOperations;
    private WeightGraphdataOperations weightGraphdataOperations;


    public Adapter_Navigation(Context context, DrawerLayout mDrawer, MainActivity activity) {
        this.context = context;
        this.mDrawer = mDrawer;
        this.activity = activity;
        tinydb = new TinyDB(context);
        databaseOperations = new DatabaseOperations(context);
        databaseOperations2 = new BDatabaseOperations(context);
        graphdataOperations = new GraphdataOperations(context);
        weightGraphdataOperations = new WeightGraphdataOperations(context);
        historyProgressOperations = new HistoryProgressOperations(context);
        myplanDbhelper = new MyplanDbhelper(context);
        dietplanDbhelper = new DietplanDbhelper(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuItemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_navigation, parent, false);
        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(context, languages);
        return new ItemViewHolder(menuItemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holders, int position) {
        final ItemViewHolder holder = (ItemViewHolder) holders;
        if (tinydb.getBoolean(Constants.PREMIUN_KEY)) {
            if (position != 6) {
                holder.mNavicon.setImageResource(mImagelist[position]);
                holder.mItemname.setText(mNamelist[position]);
            }
        } else {
            holder.mNavicon.setImageResource(mImagelist[position]);
            holder.mItemname.setText(mNamelist[position]);
        }
        if (position == 7) {
            holder.mRvmainlay.setBackgroundResource(R.drawable.nav_selector);
            holder.mNavicon.setColorFilter(context.getResources().getColor(R.color.homeprogresscolor));
            holder.mItemname.setTextColor(context.getResources().getColor(R.color.homeprogresscolor));
        } else {
            holder.mRvmainlay.setBackgroundResource(0);
            if (position != 6 && !tinydb.getBoolean(Constants.PREMIUN_KEY)) {
                holder.mNavicon.setColorFilter(context.getResources().getColor(R.color.navunselected));
            }
            holder.mItemname.setTextColor(context.getResources().getColor(R.color.navunselecttxt));
        }
        holder.mRvmainlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.closeDrawers();
                Constants.drwaerselected = 0;
                click(position);
                notifyDataSetChanged();

            }
        });
    }

    public void click(int i) {
        if (i == 1) {
          /*  boolean diet = tinydb.getBoolean(Constants.ReadyDietPlan);
            if (diet) {
                Intent intent1 = new Intent(context, DefalutAndCustomplanActivity.class);
                intent1.putExtra("isFrom2", false);
                context.startActivity(intent1);
            } else {
                Intent intent = new Intent(context, CustomUpdateActivity.class);
                intent.putExtra("isFrom", false);
                context.startActivity(intent);
            }*/
            String tempdate = tinydb.getString(Constants.DIETPLANDATE_KEY);
            Date c = Calendar.getInstance().getTime();
             Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
            String formattedDate = df.format(c);
            if (!tempdate.isEmpty() && tempdate.equals(formattedDate)) {
                Intent intent1 = new Intent(context, DefalutAndCustomplanActivity.class);
                intent1.putExtra("isFrom2", false);
                context.startActivity(intent1);
            } else {
                Intent intent = new Intent(context, CustomUpdateActivity.class);
                intent.putExtra("isFrom", false);
                context.startActivity(intent);
            }
        } else if (i == 2) {
            Intent intent = new Intent(context, Activity_Bloglists.class);
            context.startActivity(intent);
            activity.finish();
        } else if (i == 3) {
            Intent intent = new Intent(context, Activity_Reports.class);
            context.startActivity(intent);
            activity.finish();
        } else if (i == 4) {
            Intent intent = new Intent(context, Activity_Discover.class);
            context.startActivity(intent);
        } else if (i == 5) {
            Intent intent = new Intent(context, Activity_MyTraining.class);
            context.startActivity(intent);
            activity.finish();
        } else if (i == 6) {
            Intent intent = new Intent(context, Activity_Purchase.class);
            context.startActivity(intent);
        } else if (i == 7) {
            boolean cgender = tinydb.getBoolean(Constants.Genderchoose);
            if (cgender) {
                if (tinydb.getString(Constants.GENDER_KEY).equals(context.getResources().getString(R.string.female))) {
                    Intent intent = new Intent(context, AppstoreActivity.class);
                    context.startActivity(intent);
                } else {
                    Intent intent = new Intent(context, MaleAppstoreActivity.class);
                    context.startActivity(intent);
                }
            } else {
                Intent intent = new Intent(context, ChooseGenderActivity.class);
                context.startActivity(intent);
            }
        } else if (i == 8) {
            Intent intent = new Intent(context, ReminderMainActivity.class);
            context.startActivity(intent);
        } else if (i == 9) {
            Intent ttsIntent = new Intent();
            ttsIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
            ttsIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            activity.startActivity(ttsIntent);
        } else if (i == 10) {
            Intent intent = new Intent(context, Activity_Settings.class);
            context.startActivity(intent);
        } else if (i == 11) {
            activity.DialogRatexeperience();
        } else if (i == 12) {
            shareApp(context);
        } else if (i == 13) {
            Resetdialog();
        }
    }


    public void Resetdialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setMessage(context.getResources().getString(R.string.restart_progress));
        alertDialogBuilder.setPositiveButton(context.getResources().getString(R.string.yes),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int arg1) {
                        dialog.dismiss();
                        DeleteTables();
                        ClearPrefrences();
                        Intent i = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        activity.startActivity(i);
                        activity.finish();
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
                alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(activity.getResources().getColor(R.color.tbtncolor));
                alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(activity.getResources().getColor(R.color.tbtncolor));

            }
        });
        alertDialog.show();
    }


    public void DeleteTables() {
        databaseOperations.deleteTable();
        databaseOperations.deleteTableLV2();
        databaseOperations.deleteTableLV3();

        databaseOperations2.deleteTable();
        databaseOperations2.deleteTableLV2();
        databaseOperations2.deleteTableLV3();

        historyProgressOperations.deleteTable();
        myplanDbhelper.deleteTable();
        dietplanDbhelper.deleteTable();
        graphdataOperations.deleteTable();
        weightGraphdataOperations.deleteTable();
    }

    public void ClearPrefrences() {
        tinydb.remove(Constants.TOTALTIME_KEY);
        tinydb.remove(Constants.TOTALEXE_KEY);
        tinydb.remove(Constants.TOTALKCAL_KEY);
        tinydb.remove(Constants.DIETPLANDATE_KEY);

        tinydb.remove(Constants.ALLDAYSPROGRESS_KEY);
        tinydb.remove(Constants.ALLDAYSPROGRESS_LV2KEY);
        tinydb.remove(Constants.ALLDAYSPROGRESS_LV3KEY);

        tinydb.remove(Constants.DAYSLEFT_KEY);
        tinydb.remove(Constants.DAYSLEFT_LV2KEY);
        tinydb.remove(Constants.DAYSLEFT_LV3KEY);

        tinydb.remove(Constants.DAYCLICK_KEY);
        tinydb.remove(Constants.ALV2DAYCLICK_KEY);
        tinydb.remove(Constants.ALV3DAYCLICK_KEY);

        tinydb.remove(Constants.DAYCLICK_BLV1KEY);
        tinydb.remove(Constants.DAYCLICK_BLV2KEY);
        tinydb.remove(Constants.DAYCLICK_BLV3KEY);

        tinydb.remove(Constants.DAYSLEFT_BLV1KEY);
        tinydb.remove(Constants.DAYSLEFT_BLV2KEY);
        tinydb.remove(Constants.DAYSLEFT_BLV3KEY);

        tinydb.remove(Constants.ALLDAYSPROGRESS_BLV1KEY);
        tinydb.remove(Constants.ALLDAYSPROGRESS_BLV2KEY);
        tinydb.remove(Constants.ALLDAYSPROGRESS_BLV3KEY);

        tinydb.remove(Constants.ALEVEL_KEY);
        tinydb.remove(Constants.BLEVEL_KEY);

        tinydb.remove(Constants.ADDEDFIXDIET);
        tinydb.remove(Constants.FIXDIET1_KEY);

    }


    public void shareApp(Context context) {
        final String appPackageName = context.getPackageName();
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, "Deep Sleep- Yoga at: https://play.google.com/store/apps/details?id=drzio.insomnia.treatment.bedtime.yoga.sleep");
        sendIntent.setType("text/plain");
        context.startActivity(sendIntent);
    }


    @Override
    public int getItemCount() {
        return mNamelist.length;
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout mRvmainlay;
        ImageView mNavicon;
        TextView mItemname;

        ItemViewHolder(View itemView) {
            super(itemView);
            mRvmainlay = (RelativeLayout) itemView.findViewById(R.id.mainlay);
            mNavicon = (ImageView) itemView.findViewById(R.id.itemicon);
            mItemname = (TextView) itemView.findViewById(R.id.tvname);

        }
    }
}
