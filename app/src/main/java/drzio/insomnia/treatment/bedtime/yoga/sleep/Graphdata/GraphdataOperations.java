package drzio.insomnia.treatment.bedtime.yoga.sleep.Graphdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class GraphdataOperations {
    private GraphdataDB dbHelper;
    private SQLiteDatabase sqlite;

    public GraphdataOperations(Context context) {
        this.dbHelper = new GraphdataDB(context);
    }

    public int CheckHistoryDBEmpty() {
        try {
            this.sqlite = this.dbHelper.getReadableDatabase();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT count(*) FROM ");
            sb.append(GraphdataDB.TABLE_GRAPHDATA);
            Cursor rawQuery = this.sqlite.rawQuery(sb.toString(), null);
            rawQuery.moveToFirst();
            return rawQuery.getInt(0) > 0 ? 1 : 0;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteTable() {
        this.sqlite = this.dbHelper.getWritableDatabase();
        int delete = this.sqlite.delete(GraphdataDB.TABLE_GRAPHDATA, null, null);
        this.sqlite.close();
        return delete;
    }


    public List<GraphData> getgraphdata() {
        ArrayList arrayList = new ArrayList();
        this.sqlite = this.dbHelper.getReadableDatabase();
        try {
            if (this.sqlite != null) {
                SQLiteDatabase sQLiteDatabase = this.sqlite;
                StringBuilder sb = new StringBuilder();
                sb.append("select * from ");
                sb.append(GraphdataDB.TABLE_GRAPHDATA);
                Cursor rawQuery = sQLiteDatabase.rawQuery(sb.toString(), null);
                if (rawQuery.moveToFirst()) {
                    while (!rawQuery.isAfterLast()) {
                        GraphData workoutData = new GraphData();
                        workoutData.setDate(rawQuery.getString(rawQuery.getColumnIndex(GraphdataDB.COL_GDATE)));
                        workoutData.setHeight(rawQuery.getString(rawQuery.getColumnIndex(GraphdataDB.COL_GHEIGHT)));
                        workoutData.setCentimeter(rawQuery.getString(rawQuery.getColumnIndex(GraphdataDB.COL_GCMVALUE)));
                        rawQuery.moveToNext();
                        arrayList.add(workoutData);
                    }
                }
                this.sqlite.close();
                return arrayList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }


    public long insertgraphdata(String date, String gheight, String cmvalue) {
        long j = 0;
        try {
            this.sqlite = this.dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            String str = GraphdataDB.COL_GDATE;
            contentValues.put(str, date);
            contentValues.put(GraphdataDB.COL_GHEIGHT, gheight);
            contentValues.put(GraphdataDB.COL_GCMVALUE, cmvalue);
            if (this.sqlite != null) {
                try {
                    j = this.sqlite.insert(GraphdataDB.TABLE_GRAPHDATA, null, contentValues);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.sqlite.close();
            return j;
        } catch (Exception e2) {
            e2.printStackTrace();
            this.sqlite.close();
            return j;
        }
    }


    public boolean update(String date, String gheight, String cmvalue) {
        boolean isupdated;
        this.sqlite = this.dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GraphdataDB.COL_GHEIGHT, gheight);
        contentValues.put(GraphdataDB.COL_GCMVALUE, cmvalue);
        int success = this.sqlite.update(GraphdataDB.TABLE_GRAPHDATA, contentValues, GraphdataDB.COL_GDATE + " = ? ", new String[]{date});
        if (success == 0) {
            isupdated = false;
        } else {
            isupdated = true;
        }
        return isupdated;
    }


}
