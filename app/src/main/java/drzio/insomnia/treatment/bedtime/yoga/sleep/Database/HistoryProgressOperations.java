package drzio.insomnia.treatment.bedtime.yoga.sleep.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.models.ProgressModal;

public class HistoryProgressOperations {
    private HistoryDB dbHelper;
    private SQLiteDatabase sqlite;

    public HistoryProgressOperations(Context context) {
        this.dbHelper = new HistoryDB(context);
    }

    public int CheckHistoryDBEmpty() {
        try {
            this.sqlite = this.dbHelper.getReadableDatabase();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT count(*) FROM ");
            sb.append(HistoryDB.COL_EXEDAY);
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
        int delete = this.sqlite.delete(HistoryDB.COL_EXEDAY, null, null);
        this.sqlite.close();
        return delete;
    }




    public List<ProgressModal> getAllDaysProgress() {
        ArrayList arrayList = new ArrayList();
        this.sqlite = this.dbHelper.getReadableDatabase();
        try {
            if (this.sqlite != null) {
                SQLiteDatabase sQLiteDatabase = this.sqlite;
                StringBuilder sb = new StringBuilder();
                sb.append("select * from ");
                sb.append(HistoryDB.COL_EXEDAY);
                Cursor rawQuery = sQLiteDatabase.rawQuery(sb.toString(), null);
                if (rawQuery.moveToFirst()) {
                    while (!rawQuery.isAfterLast()) {
                        ProgressModal workoutData = new ProgressModal();
                        workoutData.setExe_date(rawQuery.getString(rawQuery.getColumnIndex(HistoryDB.COL_DATE)));
                        workoutData.setExe_dtime(rawQuery.getString(rawQuery.getColumnIndex(HistoryDB.COL_DTIME)));
                        workoutData.setExe_stime(rawQuery.getInt(rawQuery.getColumnIndex(HistoryDB.COL_STIME)));
                        workoutData.setExe_kcal(rawQuery.getFloat(rawQuery.getColumnIndex(HistoryDB.COL_KCAL)));
                        workoutData.setExe_dname(rawQuery.getString(rawQuery.getColumnIndex(HistoryDB.COL_DNAME)));
                        workoutData.setExe_ddone(rawQuery.getInt(rawQuery.getColumnIndex(HistoryDB.COL_DDONE)));
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


    public long insertExcALLDayData(String date,String dtime,int stime,float kcal,String dname,int ddone) {
        long j = 0;
        try {
            this.sqlite = this.dbHelper.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                String str = HistoryDB.COL_DATE;
                contentValues.put(str, date);
                contentValues.put(HistoryDB.COL_DTIME, dtime);
                contentValues.put(HistoryDB.COL_STIME, stime);
                contentValues.put(HistoryDB.COL_KCAL, kcal);
                contentValues.put(HistoryDB.COL_DNAME, dname);
                contentValues.put(HistoryDB.COL_DDONE, ddone);
                if (this.sqlite != null) {
                    try {
                        j = this.sqlite.insert(HistoryDB.COL_EXEDAY, null, contentValues);
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


}
