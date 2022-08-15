package fpt.grw.persistencedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ContactDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_details);

        DatabaseHelper dbHelper = new DatabaseHelper(getApplicationContext());
        String details = dbHelper.getDetails();

        TextView tv = findViewById(R.id.textView);
        tv.setText(details);
    }
}