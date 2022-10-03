package com.example.cameraxdemo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.VideoCapture;
import androidx.camera.view.PreviewView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {
    private PreviewView previewView;
    private ImageCapture imageCapture;
    private VideoCapture videoCapture;

    private Executor executor = Executors.newSingleThreadExecutor();
    private final int REQUEST_CODE_PERMISSIONS = 1001;
    private final String[] REQUIRED_PERMISSIONS = new String[] {"android.permission.CAMERA"
                        ,"android.permission.RECORD_AUDIO"};
    ImageView imageView;
    EditText inputPictureUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        inputPictureUri = findViewById(R.id.inputUri);
        previewView = findViewById(R.id.previewView);
        Button btn = findViewById(R.id.button);

        btn.setOnClickListener(view -> {
            final String[]  options = {"Take photo","Choose from library","View a picture from Uri"};
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setItems(options,(dialog,item)->{
                if (options[item]=="Take photo"){
                    Toast.makeText(this,"take photo",Toast.LENGTH_SHORT).show();
                }else if(options[item]=="Choose from library"){
                    Toast.makeText(this,"Choose from library",Toast.LENGTH_SHORT).show();
                }else if(options[item]=="View a picture from Uri"){
                    Toast.makeText(this,"Choose from library",Toast.LENGTH_SHORT).show();
                }
            });
            builder.show();
        });

    }
}