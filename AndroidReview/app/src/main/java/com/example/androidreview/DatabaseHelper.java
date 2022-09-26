package com.example.androidreview;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import entities.Exam;
import entities.ExamDetails;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Exams";
    private static final String TABLE_EXAM = "Exams";
    private static final String TABLE_DETAIL = "Details";

    private static final String DETAIL_ID = "detail_id";
    private static final String DETAIL_QUESTION = "detail_question";
    private static final String DETAIL_PICTURE_URL = "detail_pictureURL";

    public static final String EXAM_ID = "exam_id";
    public static final String NAME = "name";
    public static final String EXAM_DATE = "exam_date";
    public static final String DESCRIPTION = "description";

    private SQLiteDatabase database;

    private static final String TABLE_DETAIL_CREATE = String.format(
            "CREATE TABLE %s (" +
                    "   %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "   %s INTEGER, " +
                    "   %s TEXT, " +
                    "   %s TEXT)",
            TABLE_DETAIL, DETAIL_ID, EXAM_ID, DETAIL_QUESTION, DETAIL_PICTURE_URL);

    private static final String TABLE_EXAM_CREATE = String.format(
      "CREATE TABLE %s (" +
      "   %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
      "   %s TEXT, " +
      "   %s TEXT, " +
      "   %s TEXT)",
            TABLE_EXAM, EXAM_ID, NAME, EXAM_DATE, DESCRIPTION);

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 2);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_EXAM_CREATE);
        db.execSQL(TABLE_DETAIL_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXAM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETAIL);

        Log.v(this.getClass().getName(), TABLE_EXAM + " database upgrade to version " +
                newVersion + " - old data lost");
        onCreate(db);
    }

    public long insertDetail(int exam_id, String question, String pictureURL) {
        ContentValues rowValues = new ContentValues();

        rowValues.put(EXAM_ID, exam_id);
        rowValues.put(DETAIL_QUESTION , question);
        rowValues.put(DETAIL_PICTURE_URL, pictureURL);

        return database.insertOrThrow(TABLE_DETAIL, null, rowValues);
    }
    public long insertExam(String name, String exam_date, String description) {
        ContentValues rowValues = new ContentValues();

        rowValues.put(NAME, name);
        rowValues.put(EXAM_DATE, exam_date);
        rowValues.put(DESCRIPTION, description);

        return database.insertOrThrow(TABLE_EXAM, null, rowValues);
    }

    public ArrayList<ExamDetails> getExamDetails(int examId){

        String MY_QUERY = "SELECT b.detail_id, b.exam_id, a.name,b.detail_pictureURL FROM "+ TABLE_EXAM+ " a INNER JOIN "+
                TABLE_DETAIL + " b ON a.exam_id=b.exam_id WHERE a.exam_id=?";
        Cursor cursor = database.rawQuery(MY_QUERY,new String[]{String.valueOf(examId)});

        ArrayList<ExamDetails> results = new ArrayList<>();

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            int detail_id = cursor.getInt(0);
            int exam_id = cursor.getInt(1);
            String exam_name = cursor.getString(2);
            String pictureURL = cursor.getString(3);

            ExamDetails detail = new ExamDetails();
            detail.setDetail_Id(detail_id);
            detail.setExam_Id(exam_id);
            detail.setExam_name(exam_name);
            detail.setPicture_url(pictureURL);

            results.add(detail);
            cursor.moveToNext();
        }
        return results;
    }

    public ArrayList<Exam> getExams() {
        Cursor cursor = database.query(TABLE_EXAM, new String[] {EXAM_ID, NAME, EXAM_DATE, DESCRIPTION},
                null, null, null, null, EXAM_ID);

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
