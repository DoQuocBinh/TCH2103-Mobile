package com.example.androidreview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import entities.Exam;
import entities.ExamDetails;

public class ExamDetailsActivity extends AppCompatActivity {
    ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_details);

        Intent intent = getIntent();
        int examId = intent.getIntExtra("examId",0);
        String examName = intent.getStringExtra("examName");

        EditText inputExamId = findViewById(R.id.InputExamNameDetail);
        inputExamId.setText("Exam Id: " + String.valueOf(examId) + " -- " + examName);

        Button btnPrev = findViewById(R.id.btnPreview);
        btnPrev.setOnClickListener(view -> {
            EditText inputURL = findViewById(R.id.inputQuestionPicURL);
            ImageView img = findViewById(R.id.questionImageView);
            DownloadImageTask task = new DownloadImageTask(mProgressDialog,this,img);

            task.execute(inputURL.getText().toString());
        });

        Button btnInsert =findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(view -> {
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            EditText inputURL = findViewById(R.id.inputQuestionPicURL);
            EditText inputQuestion = findViewById(R.id.inputQuestionDetail);
            long detailId = dbHelper.insertDetail(examId,inputQuestion.getText().toString(),
                    inputURL.getText().toString());
            Toast.makeText(this, "new id:" + String.valueOf(detailId), Toast.LENGTH_SHORT).show();
        });

        Button btnViewAll = findViewById(R.id.btnViewAll);
        btnViewAll.setOnClickListener(view -> {
            DatabaseHelper dbHelper = new DatabaseHelper(this);

            List<ExamDetails> details = dbHelper.getExamDetails(examId);
            ArrayAdapter<ExamDetails> arrayAdapter
                    = new ArrayAdapter<ExamDetails>(this, android.R.layout.simple_list_item_1 , details);

            ListView listView = findViewById(R.id.listview_details);
            listView.setAdapter(arrayAdapter);
        });
    }
}