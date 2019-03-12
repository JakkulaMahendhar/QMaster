package com.example.image;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    TextView signIn;
    Button signUp;
    DalSignUp dalSignUp;
    TextInputEditText sign_up_name, sign_up_email, sign_up_password;
    String userName, userEmail, userPassword;
    BaseDB db = null;
    LinearLayout ll_signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_signup);
        db = ((QMaster) getApplication()).getBaseDb();
        dalSignUp = new DalSignUp();
        updateXML();

    }

    public void updateXML() {

        signUp = (Button) findViewById(R.id.signUp);
        signIn = (TextView) findViewById(R.id.signIn);
        sign_up_name = (TextInputEditText) findViewById(R.id.sign_up_name);
        sign_up_email = (TextInputEditText) findViewById(R.id.sign_up_email);
        sign_up_password = (TextInputEditText) findViewById(R.id.sign_up_password);
        ll_signUp = (LinearLayout) findViewById(R.id.ll_signUp);
        signIn.setOnClickListener(this);
        signUp.setOnClickListener(this);

    }

    public String validations(String uname, String email, String password) {

        String alert = "";
        if (uname.equals("")) {
            alert += "Please Enter Full Name\n";
        }
        if (email.equals("")) {
            alert += "Please Enter Email Id\n";
        } else if (!email.equals("")) {
            alert += isValidMail(email);
        }
        if (password.equals("")) {
            alert += "Please Enter Password";
        } else if (!password.equals("") && !(password.length() == 8)) {
            alert += "password must be 8 characters";
        }


        return alert;
    }


    @Override
    public void onClick(View view) {
        try {
            switch (view.getId()) {

                case R.id.signIn:
                    Intent i = new Intent(SignUpActivity.this, SignInActivity.class);
                    startActivity(i);
                    break;

                case R.id.signUp:

                    userName = sign_up_name.getText().toString().trim();
                    userEmail = sign_up_email.getText().toString().trim();
                    userPassword = sign_up_password.getText().toString().trim();
                    String alert = validations(userName, userEmail, userPassword);
                    String encryPass = Utils.compressPassword(userPassword);
                    if (!alert.equals("")) {
                        Utils.showAlert("Alert", alert, this);
                    } else {
                        boolean isEmailExist = dalSignUp.isEmailChecking(userEmail, db);
                        if (isEmailExist) {
                            Utils.showAlert("Error", "Email ID Already Exist", this);
                        } else {
                            boolean res = dalSignUp.insertSignUpDetails(userName, userEmail, encryPass, db);
                            if (res) {
                                Toast.makeText(getApplicationContext(), "Sign up Successfully", Toast.LENGTH_SHORT).show();
                                sign_up_name.setText("");
                                sign_up_email.setText("");
                                sign_up_password.setText("");
                                Intent Login = new Intent(SignUpActivity.this, SignInActivity.class);
                                Login.putExtra("isSignUpComplete","isSignUpComplete");
                                Login.putExtra("UserEmail", userEmail);
                                Login.putExtra("Password", userPassword);
                                startActivity(Login);
                            }
                            break;
                        }
                    }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String isValidMail(String email) {
        boolean check;
        Pattern p;
        Matcher m;
        String alert = "";
        String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        p = Pattern.compile(EMAIL_STRING);

        m = p.matcher(email);
        check = m.matches();

        if (!check) {
            alert = "Please Enter Valid Email Id\n";
        }
        return alert;
    }

}
