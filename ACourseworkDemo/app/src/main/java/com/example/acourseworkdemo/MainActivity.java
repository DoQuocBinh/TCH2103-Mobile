package com.example.acourseworkdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText inputName = findViewById(R.id.inputName);
        EditText inputAge = findViewById(R.id.inputAge);
        Button button = findViewById(R.id.button);

        button.setOnClickListener(view -> {
            String name = inputName.getText().toString();
            if(name.equals("")){
                inputName.setError("Ten khong de trang!");
                return;
            }
            int age = Integer.parseInt(inputAge.getText().toString());
            if(age  <20){
                inputAge.setError("Age khong nho hon 20!");
                return;
            }
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            long id = dbHelper.insertStudent(name,age);
            Toast.makeText(this, "New student id:" + String.valueOf(id), Toast.LENGTH_SHORT).show();
        });

    }
}