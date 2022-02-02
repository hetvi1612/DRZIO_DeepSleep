package drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore_NEW;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class ChooseGenderActivity extends AppCompatActivity {
    LinearLayout mBtnfemale,mBtnmale;
    private ImageView femalesele, malesele;
    private TextView mTxtfemale, mTxtmale;

    String mGender;
    private CardView mBtnnext;
    private TinyDB tinydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_gender);
        tinydb=new TinyDB(this);
        mBtnfemale = (LinearLayout) findViewById(R.id.btn_female);
        mBtnmale = (LinearLayout) findViewById(R.id.btn_male);
        femalesele = (ImageView) findViewById(R.id.femalesele);
        malesele = (ImageView) findViewById(R.id.malesele);
        mTxtfemale = (TextView) findViewById(R.id.txt_female);
        mTxtmale = (TextView) findViewById(R.id.txt_male);
        mBtnnext = (CardView) findViewById(R.id.btnnxt);

        mBtnfemale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                femalesele.setVisibility(View.VISIBLE);
                malesele.setVisibility(View.GONE);
             //   mImgmale.setImageResource(R.drawable.maleunselectedbtn);
                mTxtmale.setTextColor(Color.BLACK);
              //  mImgfemale.setImageResource(R.drawable.femaleselectedbtn);
                mTxtfemale.setTextColor(getResources().getColor(R.color.tbtncolor));
                mGender = String.valueOf(mTxtfemale.getText());
            }
        });
        mBtnmale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                malesele.setVisibility(View.VISIBLE);
                femalesele.setVisibility(View.GONE);
           //     mImgmale.setImageResource(R.drawable.maleselectedbtn);
                mTxtmale.setTextColor(getResources().getColor(R.color.tbtncolor));
          //      mImgfemale.setImageResource(R.drawable.femaleunselectedbtn);
                mTxtfemale.setTextColor(Color.BLACK);
                mGender = String.valueOf(mTxtmale.getText());

            }
        });




        mBtnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mGender != null ) {
                    tinydb.putString(Constants.GENDER_KEY, mGender);
                  if (mGender.equals(getResources().getString(R.string.female))) {
                      Intent intent = new Intent(ChooseGenderActivity.this, AppstoreActivity.class);
                      startActivity(intent);
                      overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                      finish();
                  }else {
                      Intent intent = new Intent(ChooseGenderActivity.this, MaleAppstoreActivity.class);
                      startActivity(intent);
                      overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                      finish();
                  }
                  tinydb.putBoolean(Constants.Genderchoose,true);
                }else {
                    Toast.makeText(ChooseGenderActivity.this,getResources().getString(R.string.select_gender),Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}