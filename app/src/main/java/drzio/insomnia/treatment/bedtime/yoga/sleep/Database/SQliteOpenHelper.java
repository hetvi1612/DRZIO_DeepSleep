package drzio.insomnia.treatment.bedtime.yoga.sleep.Database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

@SuppressLint("WrongConstant")
public class SQliteOpenHelper extends SQLiteOpenHelper {
    private static final String ALARMTYPE = "alarm_type";
    private static final String CUSTOM = "custom";
    private static final String CUSTOM_NUMBER = "custom_number";
    private static final String DATABASE_NAME = "FitnessReminderAlarm.db";
    private static final String DATE = "date";
    private static final String f90ID = "id";
    private static final String REPEAT = "repeat";
    private static final String REPORTAS = "report_as";
    private static final String RINGTONE_TITLE = "ringtone_title";
    private static final String RINGTONE_URI = "ringtone_uri";
    private static final String SPINNER_TEXT = "spinner_text";
    private static final String TABLE_NAME = "Reminder";
    private static final String TIME = "time";
    private static final String TITLE = "title";
    private static final String WEEKDAYS = "weekdays";
    private Context mContext;

    public SQliteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.mContext = context;
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE \"main\".\"Reminder\" (\n    \"id\" INTEGER PRIMARY KEY AUTOINCREMENT,\n    \"title\" TEXT,\n    \"repeat\" TEXT,\n    \"date\" TEXT ,\n    \"time\" TEXT ,\n    \"ringtone_title\" TEXT ,\n    \"ringtone_uri\" TEXT ,\n    \"weekdays\" TEXT ,\n    \"alarm_type\" TEXT ,\n    \"report_as\" TEXT ,\n    \"custom\" TEXT ,\n    \"custom_number\" TEXT ,\n    \"spinner_text\" TEXT\n);\n");
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS Reminder");
        onCreate(sQLiteDatabase);
    }

    public long AddReminder(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, str);
        contentValues.put(REPEAT, str2);
        contentValues.put(DATE, str3);
        contentValues.put(TIME, str4);
        contentValues.put(RINGTONE_TITLE, str5);
        contentValues.put(RINGTONE_URI, str6);
        contentValues.put(WEEKDAYS, str7);
        contentValues.put(ALARMTYPE, str8);
        contentValues.put(REPORTAS, str9);
        contentValues.put(CUSTOM, str10);
        contentValues.put(CUSTOM_NUMBER, str11);
        contentValues.put(SPINNER_TEXT, str12);
        return writableDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public Cursor Select(SQLiteDatabase sQLiteDatabase, String str) {
        return sQLiteDatabase.query(TABLE_NAME, null, str, null, null, null, "date ASC, time ASC");
    }

    public int UpdateReminder(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TITLE, str);
        contentValues.put(REPEAT, str2);
        contentValues.put(DATE, str3);
        contentValues.put(TIME, str4);
        contentValues.put(RINGTONE_TITLE, str5);
        contentValues.put(RINGTONE_URI, str6);
        contentValues.put(WEEKDAYS, str7);
        contentValues.put(ALARMTYPE, str8);
        contentValues.put(REPORTAS, str9);
        contentValues.put(CUSTOM, str10);
        contentValues.put(CUSTOM_NUMBER, str11);
        contentValues.put(SPINNER_TEXT, str12);
        StringBuilder sb = new StringBuilder();
        sb.append("id= '");
        sb.append(i);
        sb.append("'");
        return writableDatabase.update(TABLE_NAME, contentValues, sb.toString(), null);
    }

    public Cursor GetSingleRow(SQLiteDatabase sQLiteDatabase, long j) {
        String[] strArr = {String.valueOf(j)};
        return sQLiteDatabase.query(TABLE_NAME, null, "ID=?", strArr, null, null, null);
    }

    public void UpdateDateTimeColumn(int i, String str, String str2) {
        String[] strArr = {String.valueOf(i)};
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, str);
        contentValues.put(TIME, str2);
        getWritableDatabase().update(TABLE_NAME, contentValues, "ID=?", strArr);
    }

    public int DeleteReminder(int i) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("id = '");
        sb.append(i);
        sb.append("'");
        return writableDatabase.delete(TABLE_NAME, sb.toString(), null);
    }

    public Cursor SelectDateColumn(SQLiteDatabase sQLiteDatabase, String str) {
        return sQLiteDatabase.query(TABLE_NAME, new String[]{f90ID, DATE}, str, null, null, null, null);
    }

    public Cursor SelectTimeColumn(SQLiteDatabase sQLiteDatabase, String str) {
        return sQLiteDatabase.query(TABLE_NAME, new String[]{f90ID, TIME}, str, null, null, null, null);
    }

    public int UpdateDate(int i, String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DATE, str);
        StringBuilder sb = new StringBuilder();
        sb.append("id = '");
        sb.append(i);
        sb.append("'");
        return writableDatabase.update(TABLE_NAME, contentValues, sb.toString(), null);
    }

    public int UpdateTime(int i, String str) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TIME, str);
        StringBuilder sb = new StringBuilder();
        sb.append("id = '");
        sb.append(i);
        sb.append("'");
        return writableDatabase.update(TABLE_NAME, contentValues, sb.toString(), null);
    }

    public void backup(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(this.mContext.getDatabasePath(DATABASE_NAME).toString()));
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    fileInputStream.close();
                    Toast.makeText(this.mContext, "Backup Completed", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        } catch (Exception e) {
            Toast.makeText(this.mContext, "Unable to backup. Retry", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public void importDB(String str) {
        String file = this.mContext.getDatabasePath(DATABASE_NAME).toString();
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(str));
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    fileInputStream.close();
                    Toast.makeText(this.mContext, "Import Completed", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        } catch (Exception e) {
            Toast.makeText(this.mContext, "Unable to import. Retry", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}
