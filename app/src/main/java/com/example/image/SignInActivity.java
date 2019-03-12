package com.example.image;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {


    TextView signUp;
    Cursor cursor;
    BaseDB db;
    TextInputEditText sign_in_email, sign_in_password;
    Button login;
    String Uemail, Upassword;
    DalSignUp dalSignUp;
    ArrayList<UserDo> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_sign_in);
            arrayList = new ArrayList<>();
            dalSignUp = new DalSignUp();
            db = ((QMaster) getApplication()).getBaseDb();
            signUp = (TextView) findViewById(R.id.signUp);
            sign_in_email = (TextInputEditText) findViewById(R.id.sign_in_email);
            sign_in_password = (TextInputEditText) findViewById(R.id.sign_in_password);
            login = (Button) findViewById(R.id.login);
            signUp.setOnClickListener(this);
            login.setOnClickListener(this);
            arrayList = dalSignUp.getAllUsers(db);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.login:
                Uemail = sign_in_email.getText().toString().trim();
                Upassword = sign_in_password.getText().toString().trim();
                checkUser(Uemail, Upassword);
                break;
            case R.id.signUp:
                Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);
                break;

        }
    }

    public void checkUser(String email, String password) {
        try {
            Cursor cursor;
            String query = "Select * from " + Constants.CREATE_QMASTERLOGIN + " where UserEmail = '" + email + "'";
            cursor = db.getRawQuery(query);
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    String userName = Utils.isnull(cursor.getString(cursor.getColumnIndex("UserName")));
                    String userEmail = Utils.isnull(cursor.getString(cursor.getColumnIndex("UserEmail")));
                    String password_decompressed = Utils.decompress(cursor.getString(cursor.getColumnIndex("Password")));
                    if (password.equals(password_decompressed)) {
                        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intentProduct = new Intent(SignInActivity.this, HomeFragment.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("name", userName);
                        bundle.putString("email", userEmail);
                        ProfileFragment Fragment = new ProfileFragment();
                        Fragment.setArguments(bundle);
                        loadFragment(Fragment);
                    } else {
                        Toast.makeText(getApplicationContext(), "Incorrect Credentials Error", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "User Does Not Exist ", Toast.LENGTH_SHORT).show();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean loadFragment(android.support.v4.app.Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.login_fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}

