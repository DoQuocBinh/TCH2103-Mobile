package com.example.androidreview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import entities.Exam;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText name = findViewById(R.id.textName);
        EditText date = findViewById(R.id.textDate);
        EditText desc = findViewById(R.id.textDescription);

        date.setOnFocusChangeListener((view, b) -> {
            if(b){
                DatePickerFragment newFragment = new DatePickerFragment();
                newFragment.setDateInput(date);
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

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

            //register event: click on Listview Item
            listView.setOnItemClickListener((adapterView, view1, i, l) -> {
                Exam selectedExam = exams.get(i);
                openDetails(selectedExam);
            });
        });
    }

    private void openDetails(Exam selectedExam) {
        Intent intent = new Intent(MainActivity.this,ExamDetailsActivity.class);
        intent.putExtra("examId",selectedExam.getId());
        intent.putExtra("examName",selectedExam.getName());
        startActivity(intent);
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{
        public EditText getDateInput() {
            return dateInput;
        }

        public void setDateInput(EditText dateInput) {
            this.dateInput = dateInput;
        }

        EditText dateInput;

        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(requireContext(), this, year, month, day);
        }

        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            dateInput.setText((String.valueOf(i2 + "/" + i1 + "/" +i)));
        }
    }
}