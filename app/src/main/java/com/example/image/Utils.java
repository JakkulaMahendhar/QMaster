package com.example.image;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Environment;
import android.util.Base64;

import net.sqlcipher.database.SQLiteDatabase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Utils {

    private static String Detailed_log;
    private static FileWriter fileWriter = null;

    public static void createLogFile(Context context) {
        try {
           File path = Environment.getExternalStorageDirectory();
            File logfile = new File(path.getAbsolutePath(),"QMaster.txt");
            if (!logfile.exists()) {

                logfile.createNewFile();

            }
            Detailed_log  = logfile.getAbsolutePath();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String compressPassword(String password) {

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(password.length());
        try {
            GZIPOutputStream gos = new GZIPOutputStream(byteArrayOutputStream);
            gos.write(password.getBytes());
            gos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] password1 = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(password1, 0);
    }

    public static String decompress(String text) throws IOException {
        byte[] byteTemp = Base64.decode(text.getBytes(), 0);
        final int BUFFER_SIZE = 32;
        ByteArrayInputStream is = new ByteArrayInputStream(byteTemp);
        GZIPInputStream gis = new GZIPInputStream(is, BUFFER_SIZE);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] data = new byte[BUFFER_SIZE];
        int bytesRead;
        while ((bytesRead = gis.read(data)) != -1) {
            baos.write(data, 0, bytesRead);
        }
        gis.close();
        return baos.toString("UTF-8");
    }

    public static void logMsg(String msg) {
        logMessage(msg);
    }

    public static void logMessage(String msg) {
        try {

            fileWriter = new FileWriter(Detailed_log, true);
            fileWriter.write(new SimpleDateFormat("ddMMMyyyy HH:mm:ss.SSS").format(new Date()) + "-"
                    + " version 1.0 " + " => " + msg + "\r\n");
        } catch (Exception e) {
            e.getMessage();
        } finally {
            try {
                fileWriter.flush();
                fileWriter.close();

            } catch (Exception ex) {
                ex.getMessage();
            }
        }
    }

    public static void showAlert(String title, String message, Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.customdialogtheme);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        AlertDialog ad = builder.create();
        ad.setTitle(title);
        ad.setMessage(message);
        ad.show();

    }

    public static String getRowGUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String isnull(String value) {
        String s = "";
        if (value == null) {
            return s;
        }
        return value;
    }


}


