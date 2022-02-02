package drzio.insomnia.treatment.bedtime.yoga.sleep.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Adapter.MytrainingAdapter;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.MyplanDbhelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Allexercises;
import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Myplansmodel;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Activity_MyTraining extends AppCompatActivity {
    private ArrayList<Myplansmodel> myplansmodels = new ArrayList<>();
    private String tempname = "zxcdsdss";
    private ArrayList<String> namelist = new ArrayList<>();
    private ArrayList<String> tempnamelist = new ArrayList<>();
    private ArrayList<String> exesizelist = new ArrayList<>();
    private String tempname2 = "zxcdsdss";
    boolean isclicked = false;
    private TinyDB tinydb;
    Intent i;
    String data="";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        tinydb = new TinyDB(Activity_MyTraining.this);
        data = getIntent().getStringExtra("noti");
        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(this, languages);

        setContentView(R.layout.activity_mytraining);
        RelativeLayout mDatalayout = (RelativeLayout) findViewById(R.id.datalayout);
        LinearLayout mNodata = (LinearLayout) findViewById(R.id.nodatalayout);
        RecyclerView mDaterecycler = (RecyclerView) findViewById(R.id.planrecycler);
        ImageView mAddbtn = (ImageView) findViewById(R.id.addmorebtn);
        ImageView mBack = (ImageView) findViewById(R.id.img_back);
        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        MyplanDbhelper mDabase = new MyplanDbhelper(Activity_MyTraining.this);
        mDaterecycler.setLayoutManager(new LinearLayoutManager(Activity_MyTraining.this, LinearLayoutManager.VERTICAL, false));
        ImageView mBtnexer = (ImageView) findViewById(R.id.btnaddexer);
        mBtnexer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isclicked) {
                    isclicked = true;
                    Intent intent = new Intent(Activity_MyTraining.this, Activity_Addexercise.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });

        mAddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isclicked) {
                    isclicked = true;
                    Intent intent = new Intent(Activity_MyTraining.this, Activity_Addexercise.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        });

        if (mDabase.getAllDaysProgress() != null && mDabase.getAllDaysProgress().size() != 0) {
            mNodata.setVisibility(View.GONE);
            mDatalayout.setVisibility(View.VISIBLE);
            List<Allexercises> data = mDabase.getAllDaysProgress();
            for (int i = 0; i < data.size(); i++) {
                if (!tempname.equals(data.get(i).getPlanname())) {
                    tempname = data.get(i).getPlanname();
                    namelist.add(tempname);
                    HashSet<String> hashSet = new HashSet<String>();
                    hashSet.addAll(namelist);
                    namelist.clear();
                    namelist.addAll(hashSet);

                }
            }

            for (int i = 0; i < namelist.size(); i++) {
                tempname2 = namelist.get(i);
                tempnamelist.clear();
                for (int i2 = 0; i2 < data.size(); i2++) {
                    if (tempname2.equals(data.get(i2).getPlanname())) {
                        tempnamelist.add(data.get(i2).getExname());
                    }
                }
                exesizelist.add(String.valueOf(tempnamelist.size()));
                Myplansmodel mData = new Myplansmodel();
                mData.setPlanname(namelist.get(i));
                mData.setTotalexe(exesizelist.get(i));
                myplansmodels.add(mData);
            }

            MytrainingAdapter adapter = new MytrainingAdapter(Activity_MyTraining.this, myplansmodels);
            mDaterecycler.setAdapter(adapter);
        }

    }


    @Override
    public void onBackPressed() {
      /*  if (!isclicked) {
            isclicked = true;
            Intent intent = new Intent(Activity_MyTraining.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }*/
        try {
            if (data != null) {
                if (data.equals("true")) {
                    Intent intent = new Intent(Activity_MyTraining.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else {
                    Intent intent = new Intent(Activity_MyTraining.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            } else {
                Intent intent = new Intent(Activity_MyTraining.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        }catch (Exception e){

        }
    }

    @Override
    protected void onStart() {
        isclicked = false;
        super.onStart();
    }
}
