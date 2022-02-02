package drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter;


import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.HistoryProgressOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.ProgressModal;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Weekgoalmodal;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class WeekGoalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Weekgoalmodal> weekgoallist = new ArrayList<>();
    private String[] daynames = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private LocalDate today;
    private TinyDB tinydb;
    String exe_datehistory;
    public Weekgoalholder holder;

    HistoryProgressOperations operations;
    private ArrayList<ProgressModal> progressModals = new ArrayList<>();
    ArrayList<String> historydate = new ArrayList<>();
    ArrayList<String> weekdate = new ArrayList<>();

    public WeekGoalAdapter(Context context, List<Weekgoalmodal> weekgoallist) {
        this.context = context;
        this.weekgoallist = weekgoallist;
        tinydb = new TinyDB(context);
        today = LocalDate.now();
        if (tinydb.getInt("fdow") != 0) {
            int dayofweek = tinydb.getInt("fdow");
            if (today != null) {
                if (dayofweek == 1) {
                    today = today.withDayOfWeek(DateTimeConstants.WEDNESDAY);
                    daynames = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
                } else if (dayofweek == 2) {
                    today = today.withDayOfWeek(DateTimeConstants.THURSDAY);
                    daynames = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
                } else if (dayofweek == 3) {
                    today = today.withDayOfWeek(DateTimeConstants.TUESDAY);
                    daynames = new String[]{"Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri"};
                }
            }
        }


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuItemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_weekgoal, parent, false);
        return new Weekgoalholder(menuItemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holders, int position) {
        holder = (Weekgoalholder) holders;
        Weekgoalmodal weekmodel = weekgoallist.get(position);
      /*  if (IsCurrentdate(weekmodel.getName())){
            holder.mTxtdate.setTextColor(context.getResources().getColor(R.color.tbtncolor));
        }else {*/
        holder.mTxtdate.setText(weekmodel.getName());
        holder.mTxtname.setText(daynames[position]);
        weekdate.add(weekmodel.getName());
        operations = new HistoryProgressOperations(context);
        if (operations.CheckHistoryDBEmpty() != 0) {
            progressModals = (ArrayList<ProgressModal>) operations.getAllDaysProgress();
            Log.e("progressModalsss", String.valueOf(operations.getAllDaysProgress()));

            for (int i1 = 0; i1 < progressModals.size(); i1++) {
                ProgressModal progressModal = progressModals.get(i1);
                exe_datehistory = progressModal.getExe_date();
                Log.e("exe_date", exe_datehistory);

                // String day = (String) DateFormat.format("dd", stringtoday(exe_datehistory));
                Log.e("exe_datehistory", dateformateer(exe_datehistory));
                Log.e("exe_datehistory1", String.valueOf(weekmodel.getName()));

                historydate.add(dateformateer(exe_datehistory));


            }
            //   boolean boolval = dateformateer(exe_datehistory).equals(weekmodel.getName()); //returns true because lists are equal
            //      Log.e("boolval", String.valueOf(boolval));

        }
        try {
            if (weekdate.get(position).equals(historydate.get(position))) {
                holder.mTxtdate.setBackground(context.getResources().getDrawable(R.drawable.circle_history));
                Log.e("weekmodel.getName()", weekmodel.getName());
                Log.e("historydate", historydate.get(position));
                holder.mTxtdate.setText("");

            }else {
                holder.mTxtdate.setBackground(context.getResources().getDrawable(R.drawable.holo_circle_shape));
                holder.mTxtdate.setTextColor(context.getResources().getColor(R.color.headercolor));
                /*}*/


            }
        }catch (Exception e){
            //  compareArrays(weekdate,historydate);
        }
       /* for (String str1 : weekdate) {
            for (String str2 : historydate) {


                if (str1.equals(str2)) {
                    //  b = true;
                    holder.mTxtdate.setForeground(context.getResources().getDrawable(R.drawable.circle_history));
                    Log.e("exe_boolean", String.valueOf(str1));
                    //   holder.mTxtdate.setTextColor(context.getResources().getColor(R.color.def_pointer_color));
                }
            }
        }*/
    }

    public boolean IsCurrentdate(String data) {
        boolean z;
        Locale locale=new Locale("en");
        // String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        //SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy",locale);
        Date dates = null;


        Date date = Calendar.getInstance().getTime();
        System.out.println("Current time => " + date);

        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy",locale);
        String formattedDate = format.format(date);
        try {
            dates = format.parse(formattedDate);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String day = (String) DateFormat.format("dd", dates);
        String temp = removeLeadingZeroes(day);
        if (data.equals(temp)) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }

    public static void compareArrays(ArrayList<String> array1, ArrayList<String> array2) {
        boolean b = false;

        for (String str1 : array1) {
            for (String str2 : array2) {
                if (str1.equals(str2)) {
                    b = true;
                    //   holder.mTxtdate.setBackground(context.getResources().getColor(R.color.tbtncolor));
                    Log.e("exe_boolean", String.valueOf(str1));

                }
            }
        }

    }

    private String dateformateer1(String dateaa) {
        try {
            java.text.DateFormat dateFormat = new SimpleDateFormat("dd");
            Date mDate = null;
            try {
                mDate = dateFormat.parse(dateaa);

            } catch (ParseException e) {
                e.printStackTrace();
            }

          /*  java.text.DateFormat dateFormat2 = new SimpleDateFormat("MMM d");
            assert mDate != null;*/
            return dateFormat.format(mDate);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dateaa;
    }
    private String dateformateer(String dateaa){
        try {
            java.text.DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
            Date mDate = null;
            try {
                mDate = dateFormat.parse(dateaa);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            java.text.DateFormat dateFormat2 = new SimpleDateFormat("dd");
//            assert mDate != null;
            return dateFormat2.format(mDate);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dateaa;
    }

    public static String removeLeadingZeroes(String value) {
        return new Integer(value).toString();
    }

    @Override
    public int getItemCount() {
        return weekgoallist.size();
    }


    class Weekgoalholder extends RecyclerView.ViewHolder {
        TextView mTxtdate, mTxtname;

        public Weekgoalholder(View itemView) {
            super(itemView);
            mTxtdate = (TextView) itemView.findViewById(R.id.txtdate);
            mTxtname = (TextView) itemView.findViewById(R.id.txtname);

        }
    }
}

/*

public class WeekGoalAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Weekgoalmodal> weekgoallist = new ArrayList<>();
    private String[] daynames = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private LocalDate today;
    private TinyDB tinydb;

    public   Weekgoalholder holder;

    HistoryProgressOperations operations;
    private ArrayList<ProgressModal> progressModals = new ArrayList<>();
    ArrayList<String> historydate=new ArrayList<>();
    ArrayList<String> weekdate=new ArrayList<>();
    public WeekGoalAdapter(Context context, List<Weekgoalmodal> weekgoallist) {
        this.context = context;
        this.weekgoallist = weekgoallist;
        tinydb = new TinyDB(context);
        today = LocalDate.now();
        if (tinydb.getInt("fdow") != 0) {
            int dayofweek = tinydb.getInt("fdow");
            if (today != null) {
                if (dayofweek == 1) {
                    today = today.withDayOfWeek(DateTimeConstants.WEDNESDAY);
                    daynames = new String[]{"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
                } else if (dayofweek == 2) {
                    today = today.withDayOfWeek(DateTimeConstants.THURSDAY);
                    daynames = new String[]{"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};
                } else if (dayofweek == 3) {
                    today = today.withDayOfWeek(DateTimeConstants.TUESDAY);
                    daynames = new String[]{"Sat", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri"};
                }
            }
        }


    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View menuItemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_weekgoal, parent, false);
        return new Weekgoalholder(menuItemLayoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holders, int position) {
        holder = (Weekgoalholder) holders;
        Weekgoalmodal weekmodel = weekgoallist.get(position);
        */
/*if (IsCurrentdate(weekmodel.getName())){
            holder.mTxtdate.setTextColor(context.getResources().getColor(R.color.tbtncolor));
        }else {*//*

        holder.mTxtdate.setTextColor(context.getResources().getColor(R.color.headercolor));
        //  }
        holder.mTxtname.setText(daynames[position]);
        holder.mTxtdate.setText(weekmodel.getName());
        weekdate.add(weekmodel.getName());
        operations = new HistoryProgressOperations(context);
        if (operations.CheckHistoryDBEmpty() != 0) {
            progressModals = (ArrayList<ProgressModal>) operations.getAllDaysProgress();
            Log.e("progressModalsss", String.valueOf( operations.getAllDaysProgress()));
            for (int i1=0;i1<progressModals.size();i1++){
                ProgressModal progressModal = progressModals.get(i1);
                String exe_datehistory=progressModal.getExe_date();
                Log.e("exe_date",exe_datehistory);

                // String day = (String) DateFormat.format("dd", stringtoday(exe_datehistory));
                Log.e("exe_datehistory", dateformateer1(exe_datehistory));


                historydate.add(dateformateer1(exe_datehistory));
            }

        }
        //  compareArrays(weekdate,historydate);
        for(String  str1 : weekdate){
            for(String str2 : historydate){
                if(str2.contains(str1)){
                    //  b = true;
                    holder.mTxtdate.setForeground(context.getResources().getDrawable(R.drawable.circle_history));
                    Log.e("exe_boolean", String.valueOf(str1));
                    Log.e("exe_booleanstr2", String.valueOf(str2));
                    //holder.mTxtdate.setTextColor(context.getResources().getColor(R.color.def_pointer_color));
                }
            }
        }
    }

    public boolean IsCurrentdate(String data) {
        boolean z;
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        Date dates = null;
        try {
            dates = format.parse(date);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String day = (String) DateFormat.format("dd", dates);
        String temp = removeLeadingZeroes(day);
        if (data.equals(temp)) {
            z = true;
        } else {
            z = false;
        }
        return z;
    }
    public static void compareArrays( ArrayList<String> array1, ArrayList<String> array2) {
        boolean b = false;

        for(String  str1 : array1){
            for(String str2 : array2){
                if(str1.equals(str2)){
                    b = true;
                    //   holder.mTxtdate.setBackground(context.getResources().getColor(R.color.tbtncolor));
                    Log.e("exe_boolean", String.valueOf(str1));

                }
            }
        }

    }
    private String dateformateer1(String dateaa){
        try {
            java.text.DateFormat dateFormat = new SimpleDateFormat("dd");
            Date mDate = null;
            try {
                mDate = dateFormat.parse(dateaa);

            } catch (ParseException e) {
                e.printStackTrace();
            }

          */
/*  java.text.DateFormat dateFormat2 = new SimpleDateFormat("MMM d");
            assert mDate != null;*//*

            return dateFormat.format(mDate);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dateaa;
    }
    public static String removeLeadingZeroes(String value) {
        return new Integer(value).toString();
    }
    @Override
    public int getItemCount() {
        return weekgoallist.size();
    }


    class Weekgoalholder extends RecyclerView.ViewHolder {
        TextView mTxtdate,mTxtname;

        public Weekgoalholder(View itemView) {
            super(itemView);
            mTxtdate = (TextView) itemView.findViewById(R.id.txtdate);
            mTxtname = (TextView) itemView.findViewById(R.id.txtname);

        }
    }
}
*/
