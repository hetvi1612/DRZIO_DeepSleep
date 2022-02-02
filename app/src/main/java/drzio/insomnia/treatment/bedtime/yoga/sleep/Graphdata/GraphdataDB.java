package drzio.insomnia.treatment.bedtime.yoga.sleep.Graphdata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class GraphdataDB extends SQLiteOpenHelper {
    private static int DATABASE_VERSION = 1;
    private static String DATABSE_NAME = "GraphDb";
    static String TABLE_GRAPHDATA = "graph_dates";
    static String COL_GDATE = "date";
    static String COL_GHEIGHT = "height";
    static String COL_GCMVALUE = "centimeter";


    private String TableData;

    public GraphdataDB(Context context) {
        super(context, DATABSE_NAME, null, DATABASE_VERSION);
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE ");
        sb.append(TABLE_GRAPHDATA);
        sb.append(" (");
        sb.append(COL_GDATE);
        sb.append(" TEXT, ");
        sb.append(COL_GHEIGHT);
        sb.append(" TEXT, ");
        sb.append(COL_GCMVALUE);
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
