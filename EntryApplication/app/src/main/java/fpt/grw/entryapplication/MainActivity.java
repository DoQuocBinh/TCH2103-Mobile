package fpt.grw.entryapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //find the button
        Button ok = findViewById(R.id.okButton);
        //register the event
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sayHello();
            }
        });
    }

    private void sayHello() {
        //get the text that user entered
        TextInputLayout txtName = findViewById(R.id.txtUserName);
        String name = txtName.getEditText().getText().toString();
        //Display the text by using Toast
        Toast.makeText(getApplicationContext(),"Hello " + name,Toast.LENGTH_LONG).show();
    }
}