package drzio.insomnia.treatment.bedtime.yoga.sleep.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Allexercises;

import static drzio.insomnia.treatment.bedtime.yoga.sleep.Database.MyplansDB.COL_PEXENAME;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.Database.MyplansDB.COL_PNAME;

public class MyplanDbhelper {
    private MyplansDB dbHelper;
    private SQLiteDatabase sqlite;

    public MyplanDbhelper(Context context) {
        this.dbHelper = new MyplansDB(context);
    }


    public int CheckPlanDBEmpty() {
        this.sqlite = this.dbHelper.getReadableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT count(*) FROM ");
        sb.append(MyplansDB.TABLE_PLANS);
        Cursor rawQuery = this.sqlite.rawQuery(sb.toString(), null);
        rawQuery.moveToFirst();
        return rawQuery.getInt(0) > 0 ? 1 : 0;
    }

    public int deleteTable() {
        this.sqlite = this.dbHelper.getWritableDatabase();
        int delete = this.sqlite.delete(MyplansDB.TABLE_PLANS, null, null);
        this.sqlite.close();
        return delete;
    }


    public int deletePlan(String planname) {
        this.sqlite = this.dbHelper.getWritableDatabase();
        int delete = 0;

        delete = this.sqlite.delete(MyplansDB.TABLE_PLANS, COL_PNAME + "= ?", new String[]{planname});
        this.sqlite.close();
        return delete;
    }

    public int deleteExercise(String exename) {
        this.sqlite = this.dbHelper.getWritableDatabase();
        int delete = 0;

        delete = this.sqlite.delete(MyplansDB.TABLE_PLANS, COL_PEXENAME + "= ?", new String[]{exename});
        this.sqlite.close();
        return delete;
    }


    public List<Allexercises> getAllDaysProgress() {
        ArrayList arrayList = new ArrayList();
        this.sqlite = this.dbHelper.getReadableDatabase();
        try {
            if (this.sqlite != null) {
                SQLiteDatabase sQLiteDatabase = this.sqlite;
                StringBuilder sb = new StringBuilder();
                sb.append("select * from ");
                sb.append(MyplansDB.TABLE_PLANS);
                Cursor rawQuery = sQLiteDatabase.rawQuery(sb.toString(), null);
                if (rawQuery.moveToFirst()) {
                    while (!rawQuery.isAfterLast()) {
                        Allexercises allexercises = new Allexercises();
                        allexercises.setPlanname(rawQuery.getString(rawQuery.getColumnIndex(COL_PNAME)));
                        allexercises.setExname(rawQuery.getString(rawQuery.getColumnIndex(MyplansDB.COL_PEXENAME)));
                        allexercises.setExecalorie(rawQuery.getString(rawQuery.getColumnIndex(MyplansDB.COL_PEXEKCAL)));
                        allexercises.setExethumb(rawQuery.getString(rawQuery.getColumnIndex(MyplansDB.COL_PEXETHUMB)));
                        allexercises.setEximglink(rawQuery.getString(rawQuery.getColumnIndex(MyplansDB.COL_PEXEVIDEO)));
                        allexercises.setVideolink(rawQuery.getString(rawQuery.getColumnIndex(MyplansDB.COL_PEXEYOUVID)));
                        allexercises.setExdescr(rawQuery.getString(rawQuery.getColumnIndex(MyplansDB.COL_PEXEDESC)));
                        allexercises.setExtime(rawQuery.getInt(rawQuery.getColumnIndex(MyplansDB.COL_PEXETIME)));
                        rawQuery.moveToNext();
                        arrayList.add(allexercises);
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





    public long insertExcALLDayData(String pname, String pexename,String pexekcal,String pexthumb,String pexevid,String pexeyvid,String pexedesc, int pexetime) {
        long j = 0;
        try {
            this.sqlite = this.dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            String str = COL_PNAME;
            contentValues.put(str, pname);
            contentValues.put(MyplansDB.COL_PEXENAME, pexename);
            contentValues.put(MyplansDB.COL_PEXEKCAL, pexekcal);
            contentValues.put(MyplansDB.COL_PEXETHUMB, pexthumb);
            contentValues.put(MyplansDB.COL_PEXEVIDEO, pexevid);
            contentValues.put(MyplansDB.COL_PEXEYOUVID, pexeyvid);
            contentValues.put(MyplansDB.COL_PEXEDESC, pexedesc);
            contentValues.put(MyplansDB.COL_PEXETIME, pexetime);
            if (this.sqlite != null) {
                try {
                    j = this.sqlite.insert(MyplansDB.TABLE_PLANS, null, contentValues);
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
