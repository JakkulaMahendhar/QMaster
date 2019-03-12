package com.example.image;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;



import java.io.IOException;

public class MainActivity extends Activity {
    private static final int REQUEST_CODE = 99;
    private Button scanButton;
    private Button cameraButton;
    private Button mediaButton;
    private ImageView scannedImageView;
    private static final int MY_PERMISSION_REQUEST_CODE = 100;


    String permissions[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            Utils.createLogFile(this);
            checkPermissions();
            //init();
            Utils.logMsg("onCreate Called");
        }catch (Exception e){
            Utils.logMsg("MainActivity.onCreate()"+e.getMessage());
        }
    }

    public void checkPermissions() {
        try {
            if (ActivityCompat.checkSelfPermission(this, permissions[0]) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, permissions[1]) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, permissions[2]) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, permissions, MY_PERMISSION_REQUEST_CODE);
            }
        } catch (Exception e) {
            Utils.logMsg("MainActivity.checkPermissions()" + e.getMessage());
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == MY_PERMISSION_REQUEST_CODE) {

            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED) {

                Toast.makeText(this, " permissions granted", Toast.LENGTH_LONG).show();

            } else {

                Toast.makeText(this, " permissions denied", Toast.LENGTH_LONG).show();

            }
        }
    }

/*
    private void init() {
        try {
            scanButton = (Button) findViewById(R.id.scanButton);
            scanButton.setOnClickListener(new ScanButtonClickListener());
            cameraButton = (Button) findViewById(R.id.cameraButton);
            cameraButton.setOnClickListener(new ScanButtonClickListener(ScanConstants.OPEN_CAMERA));
            mediaButton = (Button) findViewById(R.id.mediaButton);
            mediaButton.setOnClickListener(new ScanButtonClickListener(ScanConstants.OPEN_MEDIA));
            scannedImageView = (ImageView) findViewById(R.id.scannedImage);
        } catch (Exception e) {
            Utils.logMsg("MainActivity.init()" + e.getMessage());
        }
    }*/

    /*private class ScanButtonClickListener implements View.OnClickListener {

        private int preference;

        public ScanButtonClickListener(int preference) {
            this.preference = preference;
        }

        public ScanButtonClickListener() {
        }

        *//*@Override
        public void onClick(View v) {
            try {
                startScan(preference);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*//*
    }*/

    /*protected void startScan(int preference) {
        try {
            Intent intent = new Intent(this, ScanActivity.class);
            intent.putExtra(ScanConstants.OPEN_INTENT_PREFERENCE, preference);
            startActivityForResult(intent, REQUEST_CODE);
        } catch (Exception e) {
            Utils.logMsg("MainActivity.startScan()" + e.getMessage());

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                Uri uri = data.getExtras().getParcelable(ScanConstants.SCANNED_RESULT);
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    getContentResolver().delete(uri, null, null);
                    scannedImageView.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Utils.logMsg("MainActivity.onActivityCreated()" + e.getMessage());
                }
            }
        } catch (Exception e) {
            Utils.logMsg("MainActivity.onActivityCreated()" + e.getMessage());
        }
    }*/


}
