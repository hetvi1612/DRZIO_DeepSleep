package drzio.insomnia.treatment.bedtime.yoga.sleep.Diet;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import java.util.ArrayList;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class DietDetailsActivity extends AppCompatActivity {

    String mPosition;
    private ImageView mDietimg;
    private TextView mDietname;
    private HtmlTextView mDietdesc;
    private String dietid;
    private RelativeLayout mNativeframe;
    private RelativeLayout mBtnprev;
    private RelativeLayout mBtnnext;
    private RelativeLayout mBottom;
    private Dialog dialog;
    int pos;
    private ArrayList<String> arraylist, imagearraylist, caloriesarray, descripationarrray;
    private String musicurl;
    private TinyDB tinyDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_details);
        Intent i = getIntent();
tinyDB=new TinyDB(DietDetailsActivity.this);
        arraylist = i.getStringArrayListExtra("name");
        imagearraylist = i.getStringArrayListExtra("image");
        caloriesarray = i.getStringArrayListExtra("calories");
        descripationarrray = i.getStringArrayListExtra("descripation");
        mPosition = i.getStringExtra("position");
        Log.e("postion", String.valueOf(mPosition));
        mDietimg = (ImageView) findViewById(R.id.dietimg);
        mDietname = (TextView) findViewById(R.id.txtname);
        mDietdesc = (HtmlTextView) findViewById(R.id.txtdesc);
        mNativeframe = (RelativeLayout) findViewById(R.id.fbnative);
        mBtnprev = (RelativeLayout) findViewById(R.id.prevexcer);
        mBtnnext = (RelativeLayout) findViewById(R.id.nextexcer);
        pos = Integer.parseInt(mPosition);
        String s = i.getStringExtra("arraylist");
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);
        musicurl = tinyDB.getString(Constants.Backimageurl);
        Glide.with(DietDetailsActivity.this).load(musicurl + "food/" + imagearraylist.get(pos))
                .apply(requestOptions).into(mDietimg);



        mDietname.setText(arraylist.get(pos));
        Spanned sp = Html.fromHtml(descripationarrray.get(pos));
        mDietdesc.setText(sp);
        mBtnprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos == 0) {
                    mBtnprev.setVisibility(View.GONE);
                } else {
                    if (pos == 1){
                        mBtnprev.setVisibility(View.GONE);
                    }
                    mBtnnext.setVisibility(View.VISIBLE);
                    pos--;
                  /*  RequestOptions requestOptions = new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL);
                    Glide.with(FitnessApplication.getInstance()).load(mDietlist.get(mPosition).getImage())
                            .apply(requestOptions).into(mDietimg);
                    mDietname.setText(mDietlist.get(mPosition).getName());
                    mDietdesc.setText(mDietlist.get(mPosition).getDescription());*/
                    RequestOptions requestOptions = new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL);

                    Glide.with(DietDetailsActivity.this).load(musicurl + "food/" + imagearraylist.get(pos))
                            .apply(requestOptions).into(mDietimg);



                    mDietname.setText(arraylist.get(pos));
                    Spanned sp = Html.fromHtml(descripationarrray.get(pos));
                    mDietdesc.setText(sp);
                }
            }
        });
        mBtnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pos == arraylist.size() - 1) {
                    mBtnnext.setVisibility(View.GONE);
                } else {
                    if (pos == arraylist.size() - 2) {
                        mBtnnext.setVisibility(View.GONE);
                    }
                    mBtnprev.setVisibility(View.VISIBLE);
                    pos++;
                  /*  RequestOptions requestOptions = new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL);
                    Glide.with(FitnessApplication.getInstance()).load(mDietlist.get(mPosition).getImage())
                            .apply(requestOptions).into(mDietimg);
                    mDietname.setText(mDietlist.get(mPosition).getName());
                    mDietdesc.setText(mDietlist.get(mPosition).getDescription());*/
                    RequestOptions requestOptions = new RequestOptions()
                            .diskCacheStrategy(DiskCacheStrategy.ALL);

                    Glide.with(DietDetailsActivity.this).load(musicurl + "food/" + imagearraylist.get(pos))
                            .apply(requestOptions).into(mDietimg);



                    mDietname.setText(arraylist.get(pos));
                    Spanned sp = Html.fromHtml(descripationarrray.get(pos));
mDietdesc.setText(sp);
                }

            }
        });
    }
}