package drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.model.User_Dietlist;

import static drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.database.DietplansDB.COL_DDATE;
import static drzio.insomnia.treatment.bedtime.yoga.sleep.DietActivity.database.DietplansDB.COL_DIETID;


public class DietplanDbhelper {
    private DietplansDB dbHelper;
    private SQLiteDatabase sqlite;

    public DietplanDbhelper(Context context) {
        this.dbHelper = new DietplansDB(context);
    }


    public int CheckPlanDBEmpty() {
        this.sqlite = this.dbHelper.getReadableDatabase();
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT count(*) FROM ");
        sb.append(DietplansDB.TABLE_PLANS);
        Cursor rawQuery = this.sqlite.rawQuery(sb.toString(), null);
        rawQuery.moveToFirst();
        return rawQuery.getInt(0) > 0 ? 1 : 0;
    }

    public int deleteTable() {
        this.sqlite = this.dbHelper.getWritableDatabase();
        int delete = this.sqlite.delete(DietplansDB.TABLE_PLANS, null, null);
        this.sqlite.close();
        return delete;
    }


    public int deletediet(String dietid,String date) {
        Date c = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat") Locale locale = new Locale("en");
                SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy",locale);
        String formattedDate = df.format(c);
        this.sqlite = this.dbHelper.getWritableDatabase();
        int delete = 0;
        if (formattedDate.equals(date)){
            delete = this.sqlite.delete(DietplansDB.TABLE_PLANS, COL_DIETID + "= ?", new String[]{dietid});
        }
        this.sqlite.close();
        return delete;
    }


    public List<User_Dietlist> getAllDaysProgress() {
        ArrayList arrayList = new ArrayList();
        this.sqlite = this.dbHelper.getReadableDatabase();
        try {
            if (this.sqlite != null) {
                SQLiteDatabase sQLiteDatabase = this.sqlite;
                StringBuilder sb = new StringBuilder();
                sb.append("select * from ");
                sb.append(DietplansDB.TABLE_PLANS);
                Cursor rawQuery = sQLiteDatabase.rawQuery(sb.toString(), null);
                if (rawQuery.moveToFirst()) {
                    while (!rawQuery.isAfterLast()) {
                        User_Dietlist allexercises = new User_Dietlist();
                        allexercises.setDate(rawQuery.getString(rawQuery.getColumnIndex(COL_DDATE)));
                        allexercises.setId(rawQuery.getString(rawQuery.getColumnIndex(COL_DIETID)));
                        allexercises.setName(rawQuery.getString(rawQuery.getColumnIndex(DietplansDB.COL_DIETNAME)));
                        allexercises.setDescription(rawQuery.getString(rawQuery.getColumnIndex(DietplansDB.COL_DIETDESC)));
                        allexercises.setImage(rawQuery.getString(rawQuery.getColumnIndex(DietplansDB.COL_DIETIMG)));
                        allexercises.setIs_active(rawQuery.getString(rawQuery.getColumnIndex(DietplansDB.COL_DIETISACTIVE)));
                        allexercises.setUser_type(rawQuery.getString(rawQuery.getColumnIndex(DietplansDB.COL_DIETTYPE)));
                        allexercises.setCategory_id(rawQuery.getString(rawQuery.getColumnIndex(DietplansDB.COL_DIETCATID)));
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


    public long insertExcALLDayData(String date, String did, String dname, String ddesc, String dimage,String disactive,String dtype,String dcatid) {
        long j = 0;
        try {
            this.sqlite = this.dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            String str = COL_DDATE;
            contentValues.put(str, date);
            contentValues.put(COL_DIETID, did);
            contentValues.put(DietplansDB.COL_DIETNAME, dname);
            contentValues.put(DietplansDB.COL_DIETDESC, ddesc);
            contentValues.put(DietplansDB.COL_DIETIMG, dimage);
            contentValues.put(DietplansDB.COL_DIETISACTIVE, disactive);
            contentValues.put(DietplansDB.COL_DIETTYPE, dtype);
            contentValues.put(DietplansDB.COL_DIETCATID, dcatid);
            if (this.sqlite != null) {
                try {
                    j = this.sqlite.insert(DietplansDB.TABLE_PLANS, null, contentValues);
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

/*
    public boolean update(String date, String gheight,String cmvalue) {
        this.sqlite = this.dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(GraphdataDB.COL_GHEIGHT, gheight);
        contentValues.put(GraphdataDB.COL_GCMVALUE, cmvalue);
        this.sqlite.update(GraphdataDB.TABLE_PLANS, contentValues, GraphdataDB.COL_GDATE + " = ? ", new String[] { date } );
        return true;
    }
*/
}
