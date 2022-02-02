package drzio.insomnia.treatment.bedtime.yoga.sleep.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import drzio.insomnia.treatment.bedtime.yoga.sleep.models.Daymodals;
import drzio.insomnia.treatment.bedtime.yoga.sleep.R;

public class DatabaseOperations {
    private DatabaseHelper dbHelper;
    private SQLiteDatabase sqlite;
Context context;
    public DatabaseOperations(Context context) {
        this.dbHelper = new DatabaseHelper(context);
        this.context=context;
    }

    public int CheckDBEmpty() {
        try {
            this.sqlite = this.dbHelper.getReadableDatabase();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT count(*) FROM ");
            sb.append(DatabaseHelper.COL_EXEDAY);
            Cursor rawQuery = this.sqlite.rawQuery(sb.toString(), null);
            try {
                rawQuery.moveToFirst();
            } catch (Exception e) {
                if (rawQuery != null)
                    rawQuery.close();
            }
            return rawQuery.getInt(0) > 0 ? 1 : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    public int CheckDBLV2Empty() {
        try {
            this.sqlite = this.dbHelper.getReadableDatabase();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT count(*) FROM ");
            sb.append(DatabaseHelper.TABLE_LEVEL2);
            Cursor rawQuery = this.sqlite.rawQuery(sb.toString(), null);
            try {
                rawQuery.moveToFirst();
            } catch (Exception e) {
                if (rawQuery != null)
                    rawQuery.close();
            }
            return rawQuery.getInt(0) > 0 ? 1 : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int CheckDBLV3Empty() {
        try {
            this.sqlite = this.dbHelper.getReadableDatabase();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT count(*) FROM ");
            sb.append(DatabaseHelper.TABLE_LEVEL3);
            Cursor rawQuery = this.sqlite.rawQuery(sb.toString(), null);
            try {
                rawQuery.moveToFirst();
            } catch (Exception e) {
                if (rawQuery != null)
                    rawQuery.close();
            }
            return rawQuery.getInt(0) > 0 ? 1 : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int deleteTable() {
        this.sqlite = this.dbHelper.getWritableDatabase();
        int delete = this.sqlite.delete(DatabaseHelper.COL_EXEDAY, null, null);
        this.sqlite.close();
        return delete;
    }

    public int deleteTableLV2() {
        this.sqlite = this.dbHelper.getWritableDatabase();
        int delete = this.sqlite.delete(DatabaseHelper.TABLE_LEVEL2, null, null);
        this.sqlite.close();
        return delete;
    }

    public int deleteTableLV3() {
        this.sqlite = this.dbHelper.getWritableDatabase();
        int delete = this.sqlite.delete(DatabaseHelper.TABLE_LEVEL3, null, null);
        this.sqlite.close();
        return delete;
    }

    public List<Daymodals> getAllDaysProgress() {
        ArrayList arrayList = new ArrayList();
        this.sqlite = this.dbHelper.getReadableDatabase();
        try {
            if (this.sqlite != null) {
                SQLiteDatabase sQLiteDatabase = this.sqlite;
                StringBuilder sb = new StringBuilder();
                sb.append("select * from ");
                sb.append(DatabaseHelper.COL_EXEDAY);
                Cursor rawQuery = sQLiteDatabase.rawQuery(sb.toString(), null);
                if (rawQuery.moveToFirst()) {
                    while (!rawQuery.isAfterLast()) {
                        Daymodals workoutData = new Daymodals();
                        workoutData.setDayname(rawQuery.getString(rawQuery.getColumnIndex(DatabaseHelper.COL_DAY)));
                        workoutData.setDayprogress(rawQuery.getFloat(rawQuery.getColumnIndex(DatabaseHelper.COL_PROGRESS)));
                        int donedata = rawQuery.getInt(rawQuery.getColumnIndex(DatabaseHelper.COL_DAYCOMPLETE));
                        if (donedata == 1) {
                            workoutData.setIscompleted(true);
                        } else {
                            workoutData.setIscompleted(false);
                        }
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


    public int getDayExcCounter(String str, int level) {
        String tablename = null;
        if (level == 1) {
            tablename = DatabaseHelper.COL_EXEDAY;
        } else if (level == 2) {
            tablename = DatabaseHelper.TABLE_LEVEL2;
        } else if (level == 3) {
            tablename = DatabaseHelper.TABLE_LEVEL3;
        }
        this.sqlite = this.dbHelper.getReadableDatabase();
        int i = 0;
        if (this.sqlite != null) {
            Cursor rawQuery = null;
            try {
                SQLiteDatabase sQLiteDatabase = this.sqlite;
                StringBuilder sb = new StringBuilder();
                sb.append("select * from ");
                sb.append(tablename);
                sb.append(" where ");
                sb.append(DatabaseHelper.COL_DAY);
                sb.append(" = '");
                sb.append(str);
                sb.append("'");
                rawQuery = sQLiteDatabase.rawQuery(sb.toString(), null);
                if (rawQuery.moveToFirst()) {
                    i = rawQuery.getInt(rawQuery.getColumnIndex(DatabaseHelper.COL_LASTPOS));
                }
                this.sqlite.close();
            } finally {
                if (rawQuery != null)
                    rawQuery.close();
            }

        }
        return i;
    }


    public long getExcDayProgress(String str, int level) {
        String tablename = null;
        if (level == 1) {
            tablename = DatabaseHelper.COL_EXEDAY;
        } else if (level == 2) {
            tablename = DatabaseHelper.TABLE_LEVEL2;
        } else if (level == 3) {
            tablename = DatabaseHelper.TABLE_LEVEL3;
        }
        this.sqlite = this.dbHelper.getReadableDatabase();
        long f = 0;
        if (this.sqlite != null) {
            Cursor rawQuery = null;
            try {
                SQLiteDatabase sQLiteDatabase = this.sqlite;
                StringBuilder sb = new StringBuilder();
                sb.append("select * from ");
                sb.append(tablename);
                sb.append(" where ");
                sb.append(DatabaseHelper.COL_DAY);
                sb.append(" = '");
                sb.append(str);
                sb.append("'");
                rawQuery = sQLiteDatabase.rawQuery(sb.toString(), null);
                if (rawQuery.moveToFirst()) {
                    f = rawQuery.getLong(rawQuery.getColumnIndex(DatabaseHelper.COL_PROGRESS));
                }
                this.sqlite.close();
            } catch (Exception e){
                e.printStackTrace();
            }finally {
                if (rawQuery != null)
                    rawQuery.close();
            }
        }
        return f;
    }


    public int getIsdaycomplete(String str, int level) {
        try {

            String tablename = null;
            if (level == 1) {
                tablename = DatabaseHelper.COL_EXEDAY;
            } else if (level == 2) {
                tablename = DatabaseHelper.TABLE_LEVEL2;
            } else if (level == 3) {
                tablename = DatabaseHelper.TABLE_LEVEL3;
            }
            this.sqlite = this.dbHelper.getReadableDatabase();
            int f = 0;
            if (this.sqlite != null) {
                Cursor rawQuery = null;
                try {
                    SQLiteDatabase sQLiteDatabase = this.sqlite;
                    StringBuilder sb = new StringBuilder();
                    sb.append("select * from ");
                    sb.append(tablename);
                    sb.append(" where ");
                    sb.append(DatabaseHelper.COL_DAY);
                    sb.append(" = '");
                    sb.append(str);
                    sb.append("'");
                    try {
                        rawQuery = sQLiteDatabase.rawQuery(sb.toString(), null);
                        if (rawQuery.moveToFirst()) {
                            f = rawQuery.getInt(rawQuery.getColumnIndex(DatabaseHelper.COL_DAYCOMPLETE));
                        }
                    } finally {
                        if (rawQuery != null)
                            rawQuery.close();
                    }
                    this.sqlite.close();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    if (rawQuery != null)
                        rawQuery.close();
                }

            }
            return f;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public long insertExcALLDayData(int level) {
        String tablename = null;
        if (level == 1) {
            tablename = DatabaseHelper.COL_EXEDAY;
        } else if (level == 2) {
            tablename = DatabaseHelper.TABLE_LEVEL2;
        } else if (level == 3) {
            tablename = DatabaseHelper.TABLE_LEVEL3;
        }
        long j = 0;
        try {
            this.sqlite = this.dbHelper.getWritableDatabase();
            for (int i = 1; i <= 29; i++) {
                ContentValues contentValues = new ContentValues();
                String str = DatabaseHelper.COL_DAY;
                StringBuilder sb = new StringBuilder();
                sb.append(context.getResources().getString(R.string.day));
                sb.append(i);
                contentValues.put(str, sb.toString());
                contentValues.put(DatabaseHelper.COL_PROGRESS, Double.valueOf(0.0d));
                contentValues.put(DatabaseHelper.COL_LASTPOS, Integer.valueOf(0));
                contentValues.put(DatabaseHelper.COL_DAYCOMPLETE, Integer.valueOf(0));
                if (this.sqlite != null) {
                    try {
                        j = this.sqlite.insert(tablename, null, contentValues);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            this.sqlite.close();
            return j;
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.e("dataerror",e2.getMessage());
            this.sqlite.close();
            return j;
        }
    }


    public int insertExcDayData(String str, long f, int level) {
        String tablename = null;
        if (level == 1) {
            tablename = DatabaseHelper.COL_EXEDAY;
        } else if (level == 2) {
            tablename = DatabaseHelper.TABLE_LEVEL2;
        } else if (level == 3) {
            tablename = DatabaseHelper.TABLE_LEVEL3;
        }
        int i = 0;
        try {
            this.sqlite = this.dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseHelper.COL_PROGRESS, f);
            if (this.sqlite != null) {
                try {
                    SQLiteDatabase sQLiteDatabase = this.sqlite;
                    String str2 = tablename;
                    StringBuilder sb = new StringBuilder();
                    sb.append(DatabaseHelper.COL_DAY);
                    sb.append("='");
                    sb.append(str);
                    sb.append("'");
                    i = sQLiteDatabase.update(str2, contentValues, sb.toString(), null);
                } catch (Exception e) {
                    Log.e("dataerror",e.getMessage());
                    e.printStackTrace();
                }
            }
            this.sqlite.close();
            return i;
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.e("dataerror",e2.getMessage());
            this.sqlite.close();
            return 0;
        }
    }


    public int insertExcCounter(String str, int f, int level) {
        String tablename = null;
        if (level == 1) {
            tablename = DatabaseHelper.COL_EXEDAY;
        } else if (level == 2) {
            tablename = DatabaseHelper.TABLE_LEVEL2;
        } else if (level == 3) {
            tablename = DatabaseHelper.TABLE_LEVEL3;
        }
        int i = 0;
        try {
            this.sqlite = this.dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseHelper.COL_LASTPOS, f);
            if (this.sqlite != null) {
                try {
                    SQLiteDatabase sQLiteDatabase = this.sqlite;
                    String str2 = tablename;
                    StringBuilder sb = new StringBuilder();
                    sb.append(DatabaseHelper.COL_DAY);
                    sb.append("='");
                    sb.append(str);
                    sb.append("'");
                    i = sQLiteDatabase.update(str2, contentValues, sb.toString(), null);
                } catch (Exception e) {
                    Log.e("dataerror",e.getMessage());
                    e.printStackTrace();
                }
            }
            this.sqlite.close();
            return i;
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.e("dataerror",e2.getMessage());
            this.sqlite.close();
            return 0;
        }
    }


    public int insertDayComplete(String str, int daycomplete, int level) {
        String tablename = null;
        if (level == 1) {
            tablename = DatabaseHelper.COL_EXEDAY;
        } else if (level == 2) {
            tablename = DatabaseHelper.TABLE_LEVEL2;
        } else if (level == 3) {
            tablename = DatabaseHelper.TABLE_LEVEL3;
        }
        int i = 0;
        try {
            this.sqlite = this.dbHelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(DatabaseHelper.COL_DAYCOMPLETE, daycomplete);
            if (this.sqlite != null) {
                try {
                    SQLiteDatabase sQLiteDatabase = this.sqlite;
                    String str2 = tablename;
                    StringBuilder sb = new StringBuilder();
                    sb.append(DatabaseHelper.COL_DAY);
                    sb.append("='");
                    sb.append(str);
                    sb.append("'");
                    i = sQLiteDatabase.update(str2, contentValues, sb.toString(), null);
                } catch (Exception e) {
                    Log.e("dataerror",e.getMessage());
                    e.printStackTrace();
                }
            }
            this.sqlite.close();
            return i;
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.e("dataerror",e2.getMessage());
            this.sqlite.close();
            return 0;
        }
    }


}
