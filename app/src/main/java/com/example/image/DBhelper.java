package com.example.image;

import android.content.Context;

import net.sqlcipher.database.SQLiteDatabase;

import net.sqlcipher.database.SQLiteOpenHelper;


public class DBhelper extends SQLiteOpenHelper
{

  public DBhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
  {

    super(context, name,factory, version);
  }

  @Override
  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    try {

    }catch (Exception e){
      e.printStackTrace();
    }

  }

  @Override
  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    try{

    }catch (Exception e){
      e.printStackTrace();
    }

  }
}