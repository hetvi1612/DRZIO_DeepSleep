package drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import drzio.insomnia.treatment.bedtime.yoga.sleep.Constants;
import drzio.insomnia.treatment.bedtime.yoga.sleep.Database.TinyDB;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.app_utilities.Utility_ResetAlarm;

//import com.project.iqramuqaddas.reminderalarm.app_utilities.Utility_ResetAlarm;

public class TimeChangedReceiver extends BroadcastReceiver {
    Utility_ResetAlarm utility_resetAlarm;
    TinyDB tinyDB;

    public void onReceive(Context context, Intent intent) {

        tinyDB = new TinyDB(context);

        String languages = tinyDB.getString(Constants.Language);
        Constants.languagechange(context, languages);
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            this.utility_resetAlarm = new Utility_ResetAlarm();
            this.utility_resetAlarm.RestartAlarm(context, "yes", 0);
        }
    }
}
