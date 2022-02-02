package drzio.insomnia.treatment.bedtime.yoga.sleep;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.AlarmHelper;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.NotificationPublisher;
import drzio.insomnia.treatment.bedtime.yoga.sleep.RemiderAlarm.Reminder_custom;

@SuppressLint("WrongConstant")
public class BootReceiver extends BroadcastReceiver {
    private static final String PREFERENCE_LAST_REQUEST_CODE = "PREFERENCE_LAST_REQUEST_CODE";
    private static final String TAG = "BootReceiver";
    private AlarmHelper alarmHelper;
    private Context context;
    private SharedPreferences sharedPreferences;
    private SimpleDateFormat startTimeFormat;

    private int getNextRequestCode() {
        int i = this.sharedPreferences.getInt(PREFERENCE_LAST_REQUEST_CODE, 0) + 1;
        if (i == Integer.MAX_VALUE) {
            i = 0;
        }
        this.sharedPreferences.edit().putInt(PREFERENCE_LAST_REQUEST_CODE, i).apply();
        return i;
    }

    private PendingIntent getPendingIntent() {
        Intent intent = new Intent("drzio.insomnia.treatment.bedtime.yoga.sleep.NOTIFY_ACTION");
        intent.setClass(this.context, NotificationPublisher.class);
        intent.setFlags(268435456);
        return PendingIntent.getBroadcast(this.context, getNextRequestCode(), intent, 134217728);
    }

    private void setBootReceiverEnabled(int i) {
        this.context.getPackageManager().setComponentEnabledSetting(new ComponentName(this.context, BootReceiver.class), i, 1);
    }


    public void mo19547a(int i, int i2, int i3) {
        Calendar instance = Calendar.getInstance();
        instance.set(11, i);
        instance.set(12, i2);
        instance.set(13, 0);
        instance.set(14, 0);
        instance.set(9, i3);
        mo19548a(instance.getTimeInMillis(), getPendingIntent());
    }


    public void mo19548a(long j, PendingIntent pendingIntent) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("scheduling_PendingIntent: ");
        sb.append(j);
        sb.append("/");
        sb.append(pendingIntent);
        Log.d(str, sb.toString());
        AlarmManager alarmManager = (AlarmManager) this.context.getSystemService(NotificationCompat.CATEGORY_ALARM);
        if (VERSION.SDK_INT >= 23) {
            alarmManager.setExactAndAllowWhileIdle(0, j, pendingIntent);
        } else if (VERSION.SDK_INT >= 19) {
            alarmManager.setExact(0, j, pendingIntent);
        } else {
            alarmManager.set(0, j, pendingIntent);
        }
        setBootReceiverEnabled(1);
    }


    public void mo19549a(AlarmHelper alarmHelper2, Calendar calendar) {
        int parseInt;
        int parseInt2;
        int i;
        if (start_TimeFormat().format(calendar.getTime()).endsWith("AM")) {
            parseInt = Integer.parseInt(getHourFormat().format(calendar.getTime()));
            parseInt2 = Integer.parseInt(getMinuteFormat().format(calendar.getTime()));
            i = 0;
        } else {
            parseInt = Integer.parseInt(getHourFormat().format(calendar.getTime()));
            parseInt2 = Integer.parseInt(getMinuteFormat().format(calendar.getTime()));
            i = 1;
        }
        mo19547a(parseInt, parseInt2, i);
    }

    public SimpleDateFormat getHourFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh");
        this.startTimeFormat = simpleDateFormat;
        return simpleDateFormat;
    }

    public SimpleDateFormat getMinuteFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm");
        this.startTimeFormat = simpleDateFormat;
        return simpleDateFormat;
    }

    public void onReceive(Context context2, Intent intent) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("onReceive - Intent Action: ");
        sb.append(intent.getAction());
        Log.d(str, sb.toString());
        this.context = context2;
        setAlarm(context2);
    }

    public void setAlarm(Context context2) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context2);
        List list = (List) new Gson().fromJson(this.sharedPreferences.getString("Reminder_customObjectList", null), new TypeToken<List<Reminder_custom>>() {
        }.getType());
        if (list != null && list.size() > 0) {
            this.alarmHelper = new AlarmHelper(context2);
            Calendar instance = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm", Locale.ENGLISH);
            for (int i = 0; i < list.size(); i++) {
                try {
                    instance.setTime(simpleDateFormat.parse(((Reminder_custom) list.get(i)).gettime().substring(0, 5)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                mo19549a(this.alarmHelper, instance);
            }
        }
    }

    public SimpleDateFormat start_TimeFormat() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");
        this.startTimeFormat = simpleDateFormat;
        return simpleDateFormat;
    }
}
