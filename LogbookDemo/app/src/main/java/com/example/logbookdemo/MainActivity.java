package com.example.logbookdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> imageList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img = findViewById(R.id.imageView);
        EditText inputURL = findViewById(R.id.inputURL);
        inputURL.setText("https://upload.wikimedia.org/wikipedia/commons/1/1c/Ho_Chi_Minh_1946.jpg");

        Button btn = findViewById(R.id.button);
        btn.setOnClickListener(view -> {
            String url = inputURL.getText().toString();
            Picasso.get()
                    .load(url)
                    .into(img);

        });
    }
}