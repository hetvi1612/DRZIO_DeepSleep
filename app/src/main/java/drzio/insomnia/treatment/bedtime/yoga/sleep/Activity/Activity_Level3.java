package drzio.insomnia.treatment.bedtime.yoga.sleep.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.DatabaseOperations;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Fragments.Fragment_Level3;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Activity_Level3 extends AppCompatActivity {
    private DatabaseOperations databaseOperations;
//    private ImageView mTemplock;
    private TinyDB tinydb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_level3);
        databaseOperations = new DatabaseOperations(this);
        tinydb = new TinyDB(this);
        ImageView imageView = (ImageView) findViewById(R.id.backgroundimg);
        imageView.setBackgroundResource(R.drawable.img_alv3);
        TextView mtxtDaylefts = (TextView) findViewById(R.id.mdaysLeft);
        TextView mTextapercentage = (TextView) findViewById(R.id.mpercentScore);
        CircularProgressBar mAprogressbar = (CircularProgressBar) findViewById(R.id.mprogress);
        TextView mTitletxt = (TextView) findViewById(R.id.mtitletxt);
            mTextapercentage.setVisibility(View.VISIBLE);
            String percentage = tinydb.getInt(Constants.ALLDAYSPROGRESS_LV3KEY) + "%";
            String dayleft = tinydb.getString(Constants.DAYSLEFT_LV3KEY) +
                    " days left";
            mtxtDaylefts.setText(dayleft);
            mTextapercentage.setText(percentage);
            mAprogressbar.setProgress(tinydb.getInt(Constants.ALLDAYSPROGRESS_LV3KEY));
            mAprogressbar.setProgressBarColor(getResources().getColor(R.color.homeprogresscolor));
//        }

        Fragment_Level3 fragmentLevel3 = new Fragment_Level3();
        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
        ft2.add(R.id.framecontainer, fragmentLevel3).commit();

        ImageView mBtnremoveads = (ImageView) findViewById(R.id.btnremoveads);
        if (tinydb.getBoolean(Constants.PREMIUN_KEY)){
            mBtnremoveads.setVisibility(View.GONE);
        }
        mBtnremoveads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Level3.this, Activity_Purchase.class);
                startActivity(intent);
            }
        });

        ImageView mBtback = (ImageView) findViewById(R.id.btnback);
        mBtback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    public boolean isPreviouscompleted(int level) {
        int count = 0;
        for (int i = 0; i < 28; i++) {
            String name = "Day" + i;
            if (databaseOperations.getIsdaycomplete(name, level) == 1) {
                count++;
            }
        }
        if (count == 24) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    public void onBackPressed() {
       /* Intent intent = new Intent(Activity_Level3.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
