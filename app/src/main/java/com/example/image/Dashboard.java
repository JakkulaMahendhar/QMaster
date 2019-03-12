package com.example.image;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class Dashboard extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    boolean doubleBackToExitPressedOnce = false;
    RelativeLayout relativeLayout;

    private static final int MY_PERMISSION_REQUEST_CODE = 100;


    String permissions[] = {Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA};

    BaseDB db;
    DalSignUp dalSignUp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.editable);
            checkPermissions();
            ((QMaster) getApplication()).setAppDirectorypath();
            ((QMaster) getApplication()).setBaseDb(new BaseDB(Dashboard.this));
            db = ((QMaster) getApplication()).getBaseDb();
            relativeLayout = (RelativeLayout) findViewById(R.id.container);
            loadFragment(new HomeFragment());

            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            BottomNavigationViewHelper.disableShiftMode(navigation);
            navigation.setOnNavigationItemSelectedListener(this);

        } catch (Exception e) {

            e.printStackTrace();
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

    private boolean loadFragment(android.support.v4.app.Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Snackbar.make(relativeLayout, "Press back again to exit", Snackbar.LENGTH_LONG).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 3000);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;
        try {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    break;

                case R.id.navigation_dashboard:
                    fragment = new DashboardFragment();
                    break;

                case R.id.navigation_contest:
                    fragment = new ContestFragment();
                    break;

                case R.id.navigation_profile:
                    fragment = new ProfileFragment();
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return loadFragment(fragment);

    }


}
