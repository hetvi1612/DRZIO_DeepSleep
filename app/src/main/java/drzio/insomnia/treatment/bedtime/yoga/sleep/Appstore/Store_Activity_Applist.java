package drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Appstore.modal.Appdata;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class Store_Activity_Applist extends AppCompatActivity {
    ArrayList<Appdata.Datalist> mTopappdatamodel = new ArrayList<>();
    TinyDB tinydb;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
        tinydb = new TinyDB(Store_Activity_Applist.this);

        String languages = tinydb.getString(Constants.Language);
        Constants.languagechange(this, languages);
        setContentView(R.layout.appstore_applist);
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            String mJsonobject = bundle.getString("Dataobject");
            List list = (List) new Gson().fromJson(mJsonobject, new TypeToken<List<Appdata.Datalist>>() {
            }.getType());
            mTopappdatamodel = (ArrayList<Appdata.Datalist>) list;
        }
        ImageView mIvback = (ImageView) findViewById(R.id.ivback);
        mIvback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        RecyclerView mRvapplist = (RecyclerView) findViewById(R.id.rv_applist);
        mRvapplist.setLayoutManager(new GridLayoutManager(Store_Activity_Applist.this, 3));
        Store_Adapter_Gridapplist adaptergrid = new Store_Adapter_Gridapplist(Store_Activity_Applist.this,mTopappdatamodel);
        mRvapplist.setAdapter(adaptergrid);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
