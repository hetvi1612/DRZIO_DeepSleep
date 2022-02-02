package drzio.insomnia.treatment.bedtime.yoga.sleep.Graphdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class WeightGraphdataOperations {
    private WeightGraphdataDB dbHelper;
    private SQLiteDatabase sqlite;

    public WeightGraphdataOperations(Context context) {
        this.dbHelper = new WeightGraphdataDB(context);
    }

    public int CheckHistoryDBEmpty() {
        try {
            this.sqlite = this.dbHelper.getReadableDatabase();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT count(*) FROM ");
            sb.append(WeightGraphdataDB.TABLE_GRAPHDATA);
            Cursor rawQuery = this.sqlite.rawQuery(sb.toString(), null);
            rawQuery.moveToFirst();
            return rawQuery.getInt(0) > 0 ? 1 : 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int deleteTable() {
        this.sqlite = this.dbHelper.getWritableDatabase();
        int delete = this.sqlite.delete(WeightGraphdataDB.TABLE_GRAPHDATA, null, null);
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
                sb.append(WeightGraphdataDB.TABLE_GRAPHDATA);
                Cursor rawQuery = sQLiteDatabase.rawQuery(sb.toString(), null);
                if (rawQuery.moveToFirst()) {
                    while (!rawQuery.isAfterLast()) {
                        GraphData workoutData = new GraphData();
                        workoutData.setDate(rawQuery.getString(rawQuery.getColumnIndex(WeightGraphdataDB.COL_GDATE)));
                        workoutData.setHeight(rawQuery.getString(rawQuery.getColumnIndex(WeightGraphdataDB.COL_GHEIGHT)));
                        workoutData.setCentimeter(rawQuery.getString(rawQuery.getColumnIndex(WeightGraphdataDB.COL_GCMVALUE)));
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
            String str = WeightGraphdataDB.COL_GDATE;
            contentValues.put(str, date);
            contentValues.put(WeightGraphdataDB.COL_GHEIGHT, gheight);
            contentValues.put(WeightGraphdataDB.COL_GCMVALUE, cmvalue);
            if (this.sqlite != null) {
                try {
                    j = this.sqlite.insert(WeightGraphdataDB.TABLE_GRAPHDATA, null, contentValues);
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
        contentValues.put(WeightGraphdataDB.COL_GHEIGHT, gheight);
        contentValues.put(WeightGraphdataDB.COL_GCMVALUE, cmvalue);
        int success = this.sqlite.update(WeightGraphdataDB.TABLE_GRAPHDATA, contentValues, WeightGraphdataDB.COL_GDATE + " = ? ", new String[]{date});
        if (success == 0) {
            isupdated = false;
        } else {
            isupdated = true;
        }
        return isupdated;
    }


}
