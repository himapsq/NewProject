package com.example.hima.newproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hima on 14-03-2016.
 */
public class EmployeeDatabase {
    private final MyDbHelper dbHelper;
    public static final String DATABASE_FILE_NAME = "pepper";
    public static final String FIELD_USERNAME = "tname";
    public static final String FIELD_USER_SALARY = "tsalary";
    public static final String FIELD_USER_SUBJECT = "tsubject";
    public static final String FIELD_ID = "tid";
    public static final String TABLE_TRAINER = "trainertable";


    public EmployeeDatabase(Context context) {
        dbHelper = new MyDbHelper(context, DATABASE_FILE_NAME, null, 1);
    }

    private SQLiteDatabase sqLiteDatabase;

    public void open() {
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }

    public void insertDetails(String tname, int tsalary, String tsubject) {
        ContentValues cv = new ContentValues();
        cv.put(FIELD_USERNAME, tname);
        cv.put(FIELD_USER_SALARY, tsalary);
        cv.put(FIELD_USER_SUBJECT, tsubject);
        sqLiteDatabase.insert(TABLE_TRAINER, null, cv);
    }

    public Cursor getDetails() {
        Cursor cursor;
        cursor = sqLiteDatabase.query(TABLE_TRAINER, null, null, null, null, null, null);
        return cursor;
    }

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
