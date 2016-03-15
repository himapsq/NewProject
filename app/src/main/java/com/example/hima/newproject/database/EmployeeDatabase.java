package com.example.hima.newproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hima on 14-03-2016.
 */

/**
 * EmployeeDatabase------------- class name
 * TABLE_TRAINER---------------- table name
 * FIELD_USERNAME--------------- fields1  in database
 * FIELD_USER_SALARY------------ fields2  in database
 * FIELD_USER_SUBJECT----------- fields3  in database
 * FIELD_ID--------------------- fields4  in database
 * DATABASE_FILE_NAME = pepper
 */
public class EmployeeDatabase {
    private final MyDbHelper dbHelper;
    public static final String DATABASE_FILE_NAME = "pepper";
    public static final String FIELD_USERNAME = "tname";
    public static final String FIELD_USER_SALARY = "tsalary";
    public static final String FIELD_USER_SUBJECT = "tsubject";
    public static final String FIELD_ID = "tid";
    public static final String TABLE_TRAINER = "trainertable";

    /**
     * constructor for EmployeeDatabase
     * @param context     Context
     */
    public EmployeeDatabase(Context context) {
        dbHelper = new MyDbHelper(context, DATABASE_FILE_NAME, null, 1);
    }

    private SQLiteDatabase sqLiteDatabase;

    /**
     * user defined method for opening the file
     */
    public void open() {
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    /**
     * userdefined method --- insertDetails   for inserting employee details with 3 parameters
     * @param tname --------- employee name
     * @param tsalary-------- employee salary
     * @param tsubject------- employee subject
     */
    public void insertDetails(String tname, int tsalary, String tsubject) {
        ContentValues cv = new ContentValues();
        cv.put(FIELD_USERNAME, tname);
        cv.put(FIELD_USER_SALARY, tsalary);
        cv.put(FIELD_USER_SUBJECT, tsubject);
        sqLiteDatabase.insert(TABLE_TRAINER, null, cv);
    }

    /**
     * for displaying entered employee details using cursor
     * @return Cursor
     */
    public Cursor getDetails() {
        Cursor cursor;
        cursor = sqLiteDatabase.query(TABLE_TRAINER, null, null, null, null, null, null);
        return cursor;
    }

    /**
     * inner class "MyDbHelper"  for creating  employee database table
     */
    private static class MyDbHelper extends SQLiteOpenHelper {

        public MyDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "create table trainer(" +
                    FIELD_ID + " tname integer primary key, " +
                    FIELD_USERNAME + " text, " +
                    FIELD_USER_SALARY + " integer, " +
                    FIELD_USER_SUBJECT + " text);";

            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
