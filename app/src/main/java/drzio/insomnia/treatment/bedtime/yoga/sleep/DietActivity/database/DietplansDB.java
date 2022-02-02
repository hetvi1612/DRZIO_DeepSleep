package drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DietplansDB extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static String DATABSE_NAME = "Userdietdb";


    static String TABLE_PLANS = "diet_list";
    static String TABLE_HISTORY = "diet_history";
    static String COL_DDATE = "diet_date";
    static String COL_DIETID = "diet_id";
    static String COL_DIETNAME = "diet_name";
    static String COL_DIETDESC = "diet_desc";
    static String COL_DIETIMG = "diet_img";
    static String COL_DIETISACTIVE = "diet_isactive";
    static String COL_DIETTYPE = "diet_type";
    static String COL_DIETCATID = "diet_catid";


    private String TableData;

    public DietplansDB(Context context) {
        super(context, DATABSE_NAME, null, DATABASE_VERSION);
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        sb.append(TABLE_PLANS);
        sb.append(" (");
        sb.append(COL_DDATE);
        sb.append(" TEXT, ");
        sb.append(COL_DIETID);
        sb.append(" TEXT, ");
        sb.append(COL_DIETNAME);
        sb.append(" TEXT, ");
        sb.append(COL_DIETDESC);
        sb.append(" TEXT, ");
        sb.append(COL_DIETIMG);
        sb.append(" TEXT, ");
        sb.append(COL_DIETISACTIVE);
        sb.append(" TEXT, ");
        sb.append(COL_DIETTYPE);
        sb.append(" TEXT, ");
        sb.append(COL_DIETCATID);
        sb.append(" TEXT)");
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
