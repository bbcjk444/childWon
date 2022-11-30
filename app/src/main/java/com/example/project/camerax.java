package com.example.project;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.util.Size;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.google.common.util.concurrent.ListenableFuture;

import java.io.File;

public class camerax extends AppCompatActivity {

    private static final String TAG = "camerax";

    // Variables
    private Preview preview;
    private ImageCapture imageCapture;
    private Camera camera;
    private boolean isBackLens;
    private boolean isFlash;
    Button btn_close, btn_start;

    boolean capture = true;
    int mDegree = 0;
    private PreviewView viewFinder;
    private ImageView imgSwitchCamera;

    private int REQUEST_CODE_PERMISSIONS = 10;
    private final String[] REQUIRED_PERMISSIONS = new String[]{
            "android.permission.CAMERA",
            "android.permission.WRITE_EXTERNAL_STORAGE"
    };


    // handler
    MyHandler handler = new MyHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camerax);

        initVariable();
        initUI();

        checkPermissions();

        btn_close = findViewById(R.id.btn_close);

        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capture = false;
                facematchPage facematchPage = new facematchPage();
                NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                facematchPage.Notification_finish(getApplicationContext(),notificationManager);
                Intent intent = new Intent(getApplicationContext(),c_menuPage.class);
                startActivity(intent);
                finish();
            }
        });
        response response = new response();
        String log = response.getLogcatLog();
        Log.d("Success_Log",log);
    }

    private void initVariable() {
        isBackLens = true;
        isFlash = false;
    }

    private void initUI() {
        viewFinder = findViewById(R.id.viewFinder);
        btn_start = findViewById(R.id.btn_start);
        imgSwitchCamera = findViewById(R.id.img_switch_camera);
        Handler mHandler = new Handler();

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                facematchPage facematchPage = new facematchPage();
                NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
                facematchPage.Notification_start(getApplicationContext(),notificationManager);
                capture=true;
                MyThread t = new MyThread();
                t.start();
                /*MaThread t2 = new MaThread();
                t2.start();*/
            }
        });
        imgSwitchCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isBackLens = !isBackLens;
                startCamera();
            }
        });
    }

    private boolean allPermissionsGranted() {
        for (String permission : REQUIRED_PERMISSIONS) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    void checkPermissions() {
        if (allPermissionsGranted()) {
            startCamera();
        } else {
            ActivityCompat.requestPermissions(this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS);
        }
    }

    void startCamera() {
        ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);
        cameraProviderFuture.addListener(new Runnable() {
            @Override
            public void run() {
                try {
                    // Used to bind the lifecycle of cameras to the lifecycle owner
                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                    // Preview
                    preview = new Preview.Builder()
                            .build();
                    // Select back camera
                    CameraSelector cameraSelector = new CameraSelector.Builder()
                            .requireLensFacing(isBackLens ? CameraSelector.LENS_FACING_BACK : CameraSelector.LENS_FACING_FRONT)
                            .build();

                    // Image Capture
                    int flashMode = ImageCapture.FLASH_MODE_OFF;
                    if (isBackLens && isFlash) {
                        flashMode = ImageCapture.FLASH_MODE_ON;
                    }
                    imageCapture = new ImageCapture.Builder().setTargetResolution(new Size(500,500))
                            .setFlashMode(flashMode)
                            .build();

                    // Unbind use cases before rebinding
                    cameraProvider.unbindAll();
                    camera = cameraProvider.bindToLifecycle(camerax.this, cameraSelector, preview, imageCapture);
                    preview.setSurfaceProvider(viewFinder.createSurfaceProvider());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, ContextCompat.getMainExecutor(this));
    }

    Bitmap capture() {
        final File file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "PHOTO.jpg");
        // Create output options object which contains file + metadata
        final File file2 = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "PHOTO2.jpg");
        ImageCapture.OutputFileOptions outputOptions = new ImageCapture.OutputFileOptions.Builder(file).build();

        // Setup image capture listener which is triggered after photo has
        // been taken
        Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        imageCapture.takePicture(
                outputOptions,
                ContextCompat.getMainExecutor(this),
                new ImageCapture.OnImageSavedCallback() {
                    @Override
                    public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {

                        Uri savedUri = Uri.fromFile(file);
                        Log.e(TAG, savedUri.toString());
                        FileUploadUtils.goSend(file);
                    }
                    @Override
                    public void onError(@NonNull ImageCaptureException exception) {
                        Log.e(TAG, exception.getLocalizedMessage());
                    }
                }

        );

        return bitmap;
    }

    public Bitmap rotateImage(Bitmap bitmap, float degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        return Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),
                bitmap.getHeight(),matrix,true);
    }

    // Camera Thread
    class MyThread extends Thread {
        @Override
        public void run() {
            while(capture){
                Bitmap bitmap = capture();
                Bundle bundle = new Bundle();
                bundle.putParcelable("bitmapImage",bitmap);
                Message m = handler.obtainMessage();
                m.setData(bundle);
                handler.sendMessage(m);
                Handler mHandler = new Handler(Looper.getMainLooper());
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        location_detect location_detect = new location_detect();
                        LocationManager manager = (LocationManager)getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
                        String msg = location_detect.Detect_location(manager);
                        Log.d("location",msg);
                    }
                },0);

                try {
                    Thread.sleep(3000);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                if (capture==false){
                    break;
                }

            }

        }
    }

    // Camera Handler
    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Bundle bundle = msg.getData();
            Bitmap bitmap = bundle.getParcelable("bitmapImage");
        }

    }

}
