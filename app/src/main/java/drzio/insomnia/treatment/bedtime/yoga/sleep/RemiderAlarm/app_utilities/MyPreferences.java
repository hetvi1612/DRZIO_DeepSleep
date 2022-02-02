package drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.app_utilities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class MyPreferences {
    private static final String AUTO_SNOOZE = "auto_snooze";
    private Context context;
    private SharedPreferences prefs;
    private Editor editor;

    @SuppressLint("CommitPrefEdits")
    public MyPreferences(Context context2) {
        this.context = context2;
        this.prefs = PreferenceManager.getDefaultSharedPreferences(context2);
        editor = this.prefs.edit();
    }


    public String getAutoSnooze() {
        return this.prefs.getString(AUTO_SNOOZE, "");
    }

    public void setAutoSnooze(String str) {
        this.editor.putString(AUTO_SNOOZE, str);
        this.editor.apply();
    }
}
