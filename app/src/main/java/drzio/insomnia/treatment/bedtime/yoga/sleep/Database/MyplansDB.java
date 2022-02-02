package drzio.insomnia.treatment.bedtime.yoga.sleep.Database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MyplansDB extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 2;
    private static String DATABSE_NAME = "Myplandb";

    static String TABLE_PLANS = "plan_list";
    static String COL_PNAME = "planname";
    static String COL_PEXENAME = "exe_name";
    static String COL_PEXEKCAL = "exe_thumb";
    static String COL_PEXETHUMB = "exe_thumb";
    static String COL_PEXEVIDEO = "exe_video";
    static String COL_PEXEYOUVID = "exe_youtubevid";
    static String COL_PEXEDESC = "exe_desc";
    static String COL_PEXETIME = "exe_time";


    private String TableData;

    public MyplansDB(Context context) {
        super(context, DATABSE_NAME, null, DATABASE_VERSION);
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        sb.append(TABLE_PLANS);
        sb.append(" (");
        sb.append(COL_PNAME);
        sb.append(" TEXT, ");
        sb.append(COL_PEXENAME);
        sb.append(" TEXT, ");
        sb.append(COL_PEXETHUMB);
        sb.append(" TEXT, ");
        sb.append(COL_PEXEVIDEO);
        sb.append(" TEXT, ");
        sb.append(COL_PEXEYOUVID);
        sb.append(" TEXT, ");
        sb.append(COL_PEXEDESC);
        sb.append(" TEXT, ");
        sb.append(COL_PEXETIME);
        sb.append(" REAL)");
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
        if (i2 > i) {
            StringBuilder sb = new StringBuilder();
            sb.append("ALTER TABLE ");
            sb.append(TABLE_PLANS);
            sb.append(" ADD COLUMN ");
            sb.append(COL_PEXEKCAL);
            sb.append(" TEXT");
            try {
                sQLiteDatabase.execSQL(sb.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
