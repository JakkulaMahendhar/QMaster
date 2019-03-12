package com.example.image;

import android.app.Application;
import android.media.MediaScannerConnection;
import android.os.Build;
import android.os.Environment;
import android.os.StrictMode;

import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;

public class QMaster extends Application {

    BaseDB baseDB;

    @Override
    public void onCreate() {
        super.onCreate();
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
//        MultiDex.install(this);
        SQLiteDatabase.loadLibs(this);
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

            @Override
            public void uncaughtException(Thread thread, Throwable throwable) {
                //AndroidUtils.showMsg(AndroidUtils.getExceptionRootMessage(throwable), MicroXtend.this.getApplicationContext());
                System.exit(0);
            }
        });
//        setAppDirectorypath();
//        AndroidUtils.createLogFile(this);
    }

    public BaseDB getBaseDb() {
        return baseDB;
    }

    public void setBaseDb(BaseDB baseDB) {
        this.baseDB = baseDB;
    }

    public void setAppDirectorypath() {
        try {
            String device = Build.DEVICE.toUpperCase();
            if (device.equals("GENERIC") || device.equals("SDK")) {
                Constants.ROOTDIRECTORYPATH = getFilesDir().getAbsolutePath();
            } else {
                Constants.ROOTDIRECTORYPATH = Environment.getExternalStorageDirectory().getAbsolutePath();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
