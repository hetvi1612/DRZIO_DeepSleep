package drzio.insomnia.treatment.bedtime.yoga.sleep.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class HistoryDB extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static String DATABSE_NAME = "Progressdb";
  static String COL_EXEDAY = "exc_day";
    static String COL_DATE = "date";
    static String COL_DTIME = "sys_time";
    static String COL_STIME = "spendtime";
    static String COL_KCAL = "datekcal";
    static String COL_DNAME = "dayname";
    static String COL_DDONE = "daydone";

    private String TableData;

    public HistoryDB(Context context) {
        super(context, DATABSE_NAME, null, DATABASE_VERSION);
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        sb.append(COL_EXEDAY);
        sb.append(" (");
        sb.append(COL_DATE);
        sb.append(" TEXT, ");
        sb.append(COL_DTIME);
        sb.append(" TEXT, ");
        sb.append(COL_STIME);
        sb.append(" INTEGER, ");
        sb.append(COL_KCAL);
        sb.append(" REAL, ");
        sb.append(COL_DNAME);
        sb.append(" TEXT, ");
        sb.append(COL_DDONE);
        sb.append(" INTEGER)");
        this.TableData = sb.toString();
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(this.TableData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {

    }
}
