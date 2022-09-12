package com.example.androidreview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText name = findViewById(R.id.textName);
        EditText date = findViewById(R.id.textDate);
        EditText desc = findViewById(R.id.textDescription);

        Button saveButton = findViewById(R.id.button);
        saveButton.setOnClickListener(view -> {
            DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
            dbHelper.insertExam(name.getText().toString(),date.getText().toString(),
                        desc.getText().toString());

        });

        Button viewAllButton = findViewById(R.id.button2);
        viewAllButton.setOnClickListener(view -> {
            DatabaseHelper dbHelper = new DatabaseHelper(this);

            List<Exam> exams = dbHelper.getExams();
            ArrayAdapter<Exam> arrayAdapter
                    = new ArrayAdapter<Exam>(this, android.R.layout.simple_list_item_1 , exams);

            ListView listView = findViewById(R.id.listview);
            listView.setAdapter(arrayAdapter);
        });
    }
}