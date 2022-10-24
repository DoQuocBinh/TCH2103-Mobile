package com.example.acourseworkdemo;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper

{
    private SQLiteDatabase database;

    private static final String DATABASE_NAME = "StudentDB";
    private static final String TABLE_STUDENT= "Student";

    private static final String STUDENT_ID = "StudentID";
    private static final String STUDENT_NAME = "StudentName";
    private static final String STUDENT_AGE = "StudentAGE";

    private static final String STDENT_TABLE_CREATE = String.format(
            "CREATE TABLE %s (" +
                    "   %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "   %s TEXT, " +
                    "   %s INTEGER)",
            TABLE_STUDENT, STUDENT_ID, STUDENT_NAME,STUDENT_AGE );


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(STDENT_TABLE_CREATE);
    }

    public long insertStudent(String name, int age) {
        ContentValues rowValues = new ContentValues();

        rowValues.put(STUDENT_NAME, name);
        rowValues.put(STUDENT_AGE, age);

        return database.insertOrThrow(TABLE_STUDENT, null, rowValues);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);

        Log.v(this.getClass().getName(), DATABASE_NAME + " database upgrade to version " +
                i1 + " - old data lost");
        onCreate(sqLiteDatabase);

    }
}
