package com.example.cameraxdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.Preview;
import androidx.camera.core.VideoCapture;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity implements ImageAnalysis.Analyzer{
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
        if(!allPermissionsGranted()){
            ActivityCompat.requestPermissions(this,REQUIRED_PERMISSIONS,REQUEST_CODE_PERMISSIONS);
        }
        startCamera();

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
    private boolean allPermissionsGranted(){
        for(String permission: REQUIRED_PERMISSIONS){
            if(ContextCompat.checkSelfPermission(this,permission) != PackageManager.PERMISSION_GRANTED){
                return  false;
            }
        }
        return  true;
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, String permission[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permission, grantResults);
        switch (requestCode){
            case REQUEST_CODE_PERMISSIONS:
                if (grantResults.length > 0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(MainActivity.this , "Permissions granted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this , "Permissions denied", Toast.LENGTH_SHORT).show();
                }
        }
    }
    private void startCamera(){
        final ListenableFuture<ProcessCameraProvider> cameraProviderListenableFuture = ProcessCameraProvider.getInstance(this);
        cameraProviderListenableFuture.addListener(()->{
            try {
                ProcessCameraProvider cameraProvider = cameraProviderListenableFuture.get();
                startCameraX(cameraProvider);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },ContextCompat.getMainExecutor(this));
    }

    @SuppressLint("RestrictedApi")
    private void startCameraX(ProcessCameraProvider cameraProvider) {
        cameraProvider.unbindAll();
        CameraSelector cameraSelector = new CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build();
        Preview preview = new Preview.Builder().build();
        preview.setSurfaceProvider(previewView.getSurfaceProvider());

        //image capture usecase
        imageCapture = new ImageCapture.Builder().setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY).build();
        videoCapture = new VideoCapture.Builder().setVideoFrameRate(30).build();
        ImageAnalysis imageAnalysis = new ImageAnalysis.Builder().setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST).build();

        imageAnalysis.setAnalyzer(executor,this);
        cameraProvider.bindToLifecycle((LifecycleOwner) this,cameraSelector,preview,imageCapture,videoCapture);

    }
    @Override
    public void analyze(@NonNull ImageProxy image){
        image.close();
    }
}