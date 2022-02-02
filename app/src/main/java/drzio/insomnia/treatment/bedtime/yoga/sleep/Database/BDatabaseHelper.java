package drzio.insomnia.treatment.bedtime.yoga.sleep.Database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class BDatabaseHelper extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static String DATABSE_NAME = "BelowageDB";
    static String COL_EXEDAY = "exc_day";
    static String TABLE_LEVEL2 = "level2_table";
    static String TABLE_LEVEL3 = "level3_table";
    static String COL_DAY = "day";
    static String COL_PROGRESS = "progress";
    static String COL_LASTPOS = "counter";
    static String COL_DAYCOMPLETE = "daycomplete";
    static String COL_LEVEL = "exelevel";
    String f5054g;
    String Table2;
    String Table3;

    public BDatabaseHelper(Context context) {
        super(context, DATABSE_NAME, null, DATABASE_VERSION);
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        sb.append(COL_EXEDAY);
        sb.append(" (");
        sb.append(COL_DAY);
        sb.append(" TEXT, ");
        sb.append(COL_PROGRESS);
        sb.append(" INTEGER, ");
        sb.append(COL_LASTPOS);
        sb.append(" INTEGER, ");
        sb.append(COL_LEVEL);
        sb.append(" TEXT, ");
        sb.append(COL_DAYCOMPLETE);
        sb.append(" INTEGER)");
        this.f5054g = sb.toString();
        StringBuilder sb2 = new StringBuilder();
        sb2.append("CREATE TABLE ");
        sb2.append(TABLE_LEVEL2);
        sb2.append(" (");
        sb2.append(COL_DAY);
        sb2.append(" TEXT, ");
        sb2.append(COL_PROGRESS);
        sb2.append(" INTEGER, ");
        sb2.append(COL_LASTPOS);
        sb2.append(" INTEGER, ");
        sb2.append(COL_LEVEL);
        sb2.append(" TEXT, ");
        sb2.append(COL_DAYCOMPLETE);
        sb2.append(" INTEGER)");
        this.Table2 = sb2.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append("CREATE TABLE ");
        sb3.append(TABLE_LEVEL3);
        sb3.append(" (");
        sb3.append(COL_DAY);
        sb3.append(" TEXT, ");
        sb3.append(COL_PROGRESS);
        sb3.append(" INTEGER, ");
        sb3.append(COL_LASTPOS);
        sb3.append(" INTEGER, ");
        sb3.append(COL_LEVEL);
        sb3.append(" TEXT, ");
        sb3.append(COL_DAYCOMPLETE);
        sb3.append(" INTEGER)");
        this.Table3 = sb3.toString();
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(this.f5054g);
            sQLiteDatabase.execSQL(this.Table2);
            sQLiteDatabase.execSQL(this.Table3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i2 > i) {
            StringBuilder sb = new StringBuilder();
            sb.append("ALTER TABLE ");
            sb.append(COL_EXEDAY);
            sb.append(" ADD COLUMN ");
            sb.append(COL_LASTPOS);
            sb.append(" INTEGER");
            sb.append(COL_DAYCOMPLETE);
            sb.append(" INTEGER");
            try {
                sQLiteDatabase.execSQL(sb.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
