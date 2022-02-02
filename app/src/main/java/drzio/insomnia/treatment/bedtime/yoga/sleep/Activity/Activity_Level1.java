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
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Fragments.Fragment_Level1;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Activity_Level1 extends AppCompatActivity {
    private TinyDB tinydb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        setContentView(R.layout.activity_beginnerlevel);
        tinydb = new TinyDB(this);
        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(this, languages);
        ImageView imageView = (ImageView) findViewById(R.id.backgroundimg);
        imageView.setBackgroundResource(R.drawable.img_alv1);
        TextView mtxtDaylefts = (TextView) findViewById(R.id.mdaysLeft);
        TextView mTextapercentage = (TextView) findViewById(R.id.mpercentScore);
        CircularProgressBar mAprogressbar = (CircularProgressBar) findViewById(R.id.mprogress);
        TextView mTitletxt = (TextView) findViewById(R.id.mtitletxt);

        mTextapercentage.setVisibility(View.VISIBLE);
        String percentage = tinydb.getInt(Constants.ALLDAYSPROGRESS_KEY) + "%";
        String dayleft = tinydb.getString(Constants.DAYSLEFT_KEY) + " "+ getResources().getString(R.string.days_left);
        mtxtDaylefts.setText(dayleft);
        mTextapercentage.setText(percentage);
        mAprogressbar.setProgressBarColor(getResources().getColor(R.color.homeprogresscolor));
        mAprogressbar.setProgress(tinydb.getInt(Constants.ALLDAYSPROGRESS_KEY));

        Fragment_Level1 fragmentLevel1 = new Fragment_Level1();
        FragmentTransaction ft2 = getSupportFragmentManager().beginTransaction();
        ft2.add(R.id.framecontainer, fragmentLevel1).commit();

        ImageView mBtnremoveads = (ImageView) findViewById(R.id.btnremoveads);
        if (tinydb.getBoolean(Constants.PREMIUN_KEY)) {
            mBtnremoveads.setVisibility(View.GONE);
        }
        mBtnremoveads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Level1.this, Activity_Purchase.class);
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


    @Override
    public void onBackPressed() {
      /*  Intent intent = new Intent(Activity_Level1.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);*/
        finish();
    }
}
