package com.example.image;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.os.Environment;

import net.sqlcipher.database.SQLiteDatabase;

import java.io.File;

public class BaseDB {

    private Context context ;
    private DBhelper dbhelper;
    boolean isDbCreated = false;
    private SQLiteDatabase db;
    File path = null;
    public BaseDB(Context c) {
        try {
            File file = new File(Constants.ROOTDIRECTORYPATH + "/" + Constants.DATABASE_DIRECTORY + "/"+ Constants.DATABASE_NAME);
            if (!file.exists()) {
                createDataBase(c);
                isDbCreated = true;
            } else {
                File directory = new File(Constants.ROOTDIRECTORYPATH,Constants.DATABASE_DIRECTORY);
                File dbfile = new File(directory.getAbsolutePath(), Constants.DATABASE_NAME);
                db = SQLiteDatabase.openDatabase(dbfile.getPath(), Constants.DBPASSWORD, null, 0);
            }
            context = c;

            dbhelper = new DBhelper(context, file.getAbsolutePath(), null, 1);
            if (isDbCreated) {
                createTables();
            } else {
                createTables();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void createDataBase(Context context) {
        try {
            File directory = new File(Constants.ROOTDIRECTORYPATH,Constants.DATABASE_DIRECTORY);
            if (!directory.exists()) {
                directory.mkdir();
                MediaScannerConnection.scanFile(context, new String[]{directory.getPath()}, null, null);
            }
            File dbfile = new File(directory.getAbsolutePath(), Constants.DATABASE_NAME);
            db = SQLiteDatabase.openOrCreateDatabase(dbfile,Constants.DBPASSWORD,null);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public long insertRecord(String tableName, ContentValues newtaskValues) {
        long res = -1;
        try {
            res = db.insert(tableName, null, newtaskValues);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


    private void createTables() {
        try {
            String CREATE_SIGNUP_DETAILS = "CREATE TABLE IF NOT EXISTS " + Constants.CREATE_QMASTERLOGIN + " (UserId integer primary key AutoIncrement, Rowguid Text, UserName Text, UserEmail Text, Password Text )";
            db.execSQL(CREATE_SIGNUP_DETAILS);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Cursor getRawQuery(String s) {
        Cursor c = null;
        try {
            if (!db.isOpen()) {
                File directory = new File(Constants.ROOTDIRECTORYPATH, Constants.DATABASE_DIRECTORY);
                File dbfile = new File(directory.getAbsolutePath(), Constants.DATABASE_NAME);
                db = SQLiteDatabase.openDatabase(dbfile.getPath(), Constants.DBPASSWORD, null, 0);
            }
            c = db.rawQuery(s, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }


}
