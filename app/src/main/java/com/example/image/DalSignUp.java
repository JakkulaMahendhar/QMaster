package com.example.image;

import android.content.ContentValues;
import android.database.Cursor;
import android.widget.Toast;

import java.util.ArrayList;

public class DalSignUp {

    ArrayList<UserDo> arrayList;

    private static DalSignUp dalSignUp = new DalSignUp();

    private DalSignUp() {

    }

   public static DalSignUp getInstance(){
        return dalSignUp;
    }

    public boolean insertSignUpDetails(String fullname, String email, String password, BaseDB db) {



        boolean result = false;
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("UserName", fullname);
            contentValues.put("UserEmail", email);
            contentValues.put("Password", password);
            contentValues.put("Rowguid", Utils.getRowGUID());


            long res = db.insertRecord(Constants.CREATE_QMASTERLOGIN, contentValues);
            if (res >= 0)
                result = true;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public boolean isEmailChecking(String UserEmail, BaseDB db) {

        Cursor cursor;

        boolean isEmailExists = false;
        try {
            String query = "SELECT 'Yes' from " + Constants.CREATE_QMASTERLOGIN + " where UserEmail = '" + UserEmail + "'";
            cursor = db.getRawQuery(query);

            if (cursor.moveToFirst()) {
                do {
                    String email = Utils.isnull(cursor.getString(0));
                    if (email.equals("Yes")) {
                        isEmailExists = true;
                    }
                } while (cursor.moveToNext());
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

        return isEmailExists;

    }

    public ArrayList getAllUsers(BaseDB db) {

        arrayList = new ArrayList<UserDo>();
        Cursor c;
        try {
            String query = "Select UserId,UserName,UserEmail,RowGuid from " + Constants.CREATE_QMASTERLOGIN;
            c = db.getRawQuery(query);

            if (c.moveToFirst()) {
                do {
                    UserDo userDo = new UserDo();
                    userDo.setUID(Utils.isnull(c.getString(0)));
                    userDo.setUserName(Utils.isnull(c.getString(1)));
                    userDo.setUserEmail(Utils.isnull(c.getString(2)));
                    userDo.setPassword(Utils.isnull(c.getString(3)));
                    arrayList.add(userDo);

                } while (c.moveToNext());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return arrayList;

    }

}
