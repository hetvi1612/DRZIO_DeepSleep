package drzio.insomnia.treatment.bedtime.yoga.sleep.BlogActivity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Bloglike.db";
    public static final String CONTACTS_TABLE_NAME = "likesandviews";
    public static final String CONTACTS_COLUMN_ID = "id";
    public static final String LIKES_ID = "likeid";
    private HashMap hp;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table likesandviews " +
                        "(id integer primary key, likeid text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS likesandviews");
        onCreate(db);
    }

    public boolean insertContact (String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("likeid", name);
        db.insert("likesandviews", null, contentValues);
        return true;
    }

    public Cursor getData(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from likesandviews where likeid="+id+"", null );
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, CONTACTS_TABLE_NAME);
        return numRows;
    }

    public boolean updateContact (Integer id, String likeid, String viewid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("likeid", likeid);
        db.update("likesandviews", contentValues, "id = ? ", new String[] { Integer.toString(id) } );
        return true;
    }

    public Integer deleteContact(String string) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("likesandviews",
                "likeid = ? ",
                new String[] { string });
    }

    public ArrayList<String> getLikeid() {
        ArrayList<String> mIds = new ArrayList<>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor res =  db.rawQuery( "select * from likesandviews", null );
            res.moveToFirst();

            while(res.isAfterLast() == false){
                mIds.add(res.getString(res.getColumnIndex(LIKES_ID)));
                res.moveToNext();
            }
            return mIds;
        }catch (Exception e){
            e.printStackTrace();
        }
        return mIds;
    }
}
