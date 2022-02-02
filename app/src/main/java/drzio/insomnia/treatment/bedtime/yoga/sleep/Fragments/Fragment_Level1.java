package drzio.insomnia.treatment.bedtime.yoga.sleep.Fragments;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.DatabaseOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Daymodals;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Weekmodel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;


public class Fragment_Level1 extends Fragment {
    private List<Object> items = new ArrayList<>();
    private int WEEK_TAG = 7;
    private List<Weekmodel> weekdata = new ArrayList<>();
    private RecyclerView mDayrecycleview;
    private int a = 0;
    private int b = 0;
    private Adapter_Fragment1 adapter;
    private TinyDB tinydb;
    private DatabaseOperations databaseOperations;
    private int daysdone = 0;
    private float Alldaysprogress = 0;
    private int daycomplete;
    private String[] timearray;
    private Toolbar toolbar;
    private ArrayList<Integer> mTempday = new ArrayList<>();
    private ArrayList<String> mTempweek = new ArrayList<>();
    private boolean isclicked;
    private int planno;
    private View v;
    private String[] temparray3;


    private int[] excercisetime = new int[]{};
    private int[] calorie = new int[]{};
    private int[] calorie51 = new int[]{};
    private int[] calorie76 = new int[]{};
    private int[] calorie90 = new int[]{};

    private float Totaltime;
    private float Totalkcal;
    private ArrayList<String> callist = new ArrayList<>();
    private ArrayList<String> timelist = new ArrayList<>();
    private TypedArray imagearray;
    private ProgressBar mProgressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.v = inflater.inflate(R.layout.fragment_levelone, container, false);
        tinydb=new TinyDB(getContext());
        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(getContext(), languages);
        mProgressBar = (ProgressBar) v.findViewById(R.id.progress_bar);
        return this.v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar = (Toolbar) view.findViewById(R.id.anim_toolbar);

        final TypedArray exetimearray = getResources().obtainTypedArray(R.array.lv1cyclesarray);
        excercisetime = LevelArrays(exetimearray);
        final TypedArray caloriearray = getResources().obtainTypedArray(R.array.lv1caloriecycles);
        calorie = LevelArrays(caloriearray);
        final TypedArray calorie51array = getResources().obtainTypedArray(R.array.lv1_51_75kalcycles);
        calorie51 = LevelArrays(calorie51array);
        final TypedArray calorie76array = getResources().obtainTypedArray(R.array.lv1_76_90kalcycles);
        calorie76 = LevelArrays(calorie76array);
        final TypedArray calorie90array = getResources().obtainTypedArray(R.array.lv1_90kalcycles);
        calorie90 = LevelArrays(calorie90array);

        mDayrecycleview = (RecyclerView) view.findViewById(R.id.daylist);



        mDayrecycleview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        tinydb = new TinyDB(getContext());
        databaseOperations = new DatabaseOperations(getContext());
        timearray = getResources().getStringArray(R.array.totaltime);
        Resources res = getResources();
        imagearray = res.obtainTypedArray(R.array.allexe_thumbimg);
        if (items.size() != 0){
            items.clear();
            mTempday.clear();
            a = 0;
            daycomplete = 0;
            daysdone = 0;
            Alldaysprogress = 0;
        }
        if (weekdata.size()!= 0){
            weekdata.clear();
            mTempweek.clear();
            b = 0;
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    String planname = getString(R.string.aboveage);
                    for (int i = 0; i < 28; i++) {
                        Daymodals daymodals = new Daymodals();
                        a++;
                        int position = a;
                        String name = "Day"+ position;
                        daymodals.setPlanname(planname);
                        daymodals.setDayname(name);
                        daymodals.setDaytxtcal("0");
                        daymodals.setDattxttime("8.00");
                        daymodals.setIsdaydata("true");
//                daymodals.setExethumbs(Thumburl(getContext(),imagearray, i));
                        daymodals.setIscompleted(isdayended(name));
                        daymodals.setDayprogress(dayprogress(name, i));
                        Alldaysprogress = Alldaysprogress + dayprogress(name, i);
                        if (isdayended(name)) {
                            daycomplete++;
                            daysdone++;
                        }
                        if (i % WEEK_TAG == 6) {
                            daymodals.setIsrest(false);
                            mTempday.add(daysdone);
                            daysdone = 0;
                        } else {
                            daymodals.setIsrest(false);
                        }

                        if (i % WEEK_TAG == 0) {
                            b++;
                            mTempweek.add("WEEK" + b);
                        }

                        items.add(daymodals);
                    }

                }catch (Exception e){
                    e.printStackTrace();
                    Log.e("errors",e.getMessage().toString());
                }
            }
        },200);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    for (int j = 0; j < mTempweek.size(); j++) {
                        try {
                            Weekmodel data = new Weekmodel();
                            data.setCompletedays(String.valueOf(mTempday.get(j)));
                            data.setWname(mTempweek.get(j));
                            weekdata.add(data);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Log.e("errors",e.getMessage().toString());
                        }
                    }
                    bindDataToAdapter();
                    String daysleft = String.valueOf(items.size() - daycomplete);
                    float Maxprogress = 24 * 100.00f;
                    float value = Alldaysprogress * 100.00f / Maxprogress;
                    tinydb.putInt(Constants.ALLDAYSPROGRESS_KEY, (int) value);
                    tinydb.putString(Constants.DAYSLEFT_KEY, daysleft);
                    mProgressBar.setVisibility(View.GONE);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        },300);


    }

    public int[] LevelArrays(TypedArray videoarray) {
        int[] resIds = new int[videoarray.length()];
        for (int i2 = 0; i2 < videoarray.length(); i2++) {
            resIds[i2] = videoarray.getResourceId(i2, -1);
        }
        return resIds;
    }


    public String decimalpoint(float f) {
        String time;
        DecimalFormat df = new DecimalFormat("#.00");
        time = String.valueOf(df.format(f));
        return time;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private String Thumburl(Context context,TypedArray videoarray, int pos) {
        int[] resIds = new int[videoarray.length()];
        for (int i2 = 0; i2 < videoarray.length(); i2++) {
            resIds[i2] = videoarray.getResourceId(i2, -1);
        }
        Uri video = Uri.parse("android.resource://" + context.getPackageName() + "/"
                + resIds[pos]);
        return String.valueOf(video);
    }


    private boolean isdayended(String name) {
        try {
            if (databaseOperations.getIsdaycomplete(name,1) == 1) {
                return true;
            } else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e("errors",e.getMessage().toString());
        }
        return false;
    }


    private float dayprogress(String name, int position) {
        try {
            if (isdayended(name)) {
                return (float) 100.00f;
            } else {

                float totaltime = Float.parseFloat("6.00");
                totaltime = totaltime * 60;
                long mLong = databaseOperations.getExcDayProgress(name,1) / 1000;
                float number = Float.parseFloat(String.valueOf(mLong));
                Log.e("tempprogress","  "+ number);
                return (float) number * 100.00f / totaltime;
            }
        }catch (Exception e){
            e.printStackTrace();
            Log.e("errors",e.getMessage().toString());
        }
        return 0;
    }

    private void bindDataToAdapter() {
        adapter = new Adapter_Fragment1(getContext(), items, weekdata);
        mDayrecycleview.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        isclicked = false;
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (tinydb.getString(Constants.DAYCLICK_KEY).equals("")) {
            if (adapter!=null){
                adapter.notifyDataSetChanged();
            }
        }
        if (tinydb.getBoolean(Constants.PREMIUN_KEY)) {
            if (adapter!=null){
                adapter.notifyDataSetChanged();
            }
        }
    }
}
