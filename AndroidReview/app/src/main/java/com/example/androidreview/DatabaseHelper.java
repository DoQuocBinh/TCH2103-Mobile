package com.example.androidreview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Exams";

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EXAM_DATE = "exam_date";
    public static final String DESCRIPTION = "description";

    private SQLiteDatabase database;

    private static final String DATABASE_CREATE = String.format(
      "CREATE TABLE %s (" +
      "   %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
      "   %s TEXT, " +
      "   %s TEXT, " +
      "   %s TEXT)",
      DATABASE_NAME, ID, NAME, EXAM_DATE, DESCRIPTION);

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);

        Log.v(this.getClass().getName(), DATABASE_NAME + " database upgrade to version " +
                newVersion + " - old data lost");
        onCreate(db);
    }

    public long insertExam(String name, String exam_date, String description) {
        ContentValues rowValues = new ContentValues();

        rowValues.put(NAME, name);
        rowValues.put(EXAM_DATE, exam_date);
        rowValues.put(DESCRIPTION, description);

        return database.insertOrThrow(DATABASE_NAME, null, rowValues);
    }

    public ArrayList<Exam> getExams() {
        Cursor cursor = database.query(DATABASE_NAME, new String[] {ID, NAME, EXAM_DATE, DESCRIPTION},
                null, null, null, null, "name");

        ArrayList<Exam> results = new ArrayList<>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String exam_date = cursor.getString(2);
            String description = cursor.getString(3);

            Exam exam = new Exam();
            exam.setExamDate(exam_date);
            exam.setName(name);
            exam.setId(id);
            exam.setDescription(description);

            results.add(exam);

            cursor.moveToNext();
        }
        return results;
    }
}
