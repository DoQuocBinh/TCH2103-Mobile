package com.example.androidreview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
            Log.d("BinhDQ",inputURL.getText().toString());
            task.execute(inputURL.getText().toString());
        });
    }
}