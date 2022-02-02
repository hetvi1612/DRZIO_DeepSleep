package drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import drzio.insomnia.treatment.bedtime.yoga.sleep.R;


public class AlarmMainActivity extends AppCompatActivity {
    private boolean isopen = false;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(1024, 1024);
        setContentView((int) R.layout.alarm_activity_main);
        Bundle bundle1 = getIntent().getExtras();
        if (bundle1!=null){
            isopen = bundle1.getBoolean("isshow");
        }else {
            isopen = false;
        }
        if (bundle == null) {
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.sample_content_fragment, new ReminderFragment());
            beginTransaction.commit();
        }
    }

    public boolean isOpen() {
        return isopen;
    }
}
