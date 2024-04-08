package com.example.appenglish;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.appenglish.fragment.BasicFragment;
import com.example.appenglish.fragment.HomeFragment;
import com.example.appenglish.fragment.IeltsFragment;
import com.example.appenglish.fragment.ProfileFragment;
import com.example.appenglish.fragment.SettingFragment;
import com.example.appenglish.fragment.TestFragment;
import com.example.appenglish.fragment.ToeicFragment;
import com.google.android.material.navigation.NavigationView;

import Database.CreateDatabase;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final int FRAGMENT_HOME = 0;
    private static final int FRAGMENT_BASIC = 1;
    private static final int FRAGMENT_TOEIC = 2;
    private static final int FRAGMENT_IELTS = 3;
    private static final int FRAGMENT_TEST = 4;
    private static final int FRAGMENT_PROFILE = 5;
    private static final int FRAGMENT_EDIT = 6;
    private static final int FRAGMENT_SETTING = 7;
    private int mCurrentFargment = FRAGMENT_HOME;
    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_app);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawerlayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.nav_openDrawer,R.string.nav_closeDrawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.navigationview);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment(new HomeFragment());
        navigationView.getMenu().findItem(R.id.nav_home).setChecked(true);
    }

    @Override

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_home){
            if(mCurrentFargment!= FRAGMENT_HOME){
                replaceFragment(new HomeFragment());
                mCurrentFargment = FRAGMENT_HOME;
            }
        }else if(id == R.id.nav_coban){
            if(mCurrentFargment!= FRAGMENT_BASIC){
                replaceFragment(new BasicFragment());
                mCurrentFargment = FRAGMENT_BASIC;
            }
        }else if(id == R.id.nav_toeic){
            if(mCurrentFargment!= FRAGMENT_TOEIC){
                replaceFragment(new ToeicFragment());
                mCurrentFargment = FRAGMENT_TOEIC;
            }
        }else if(id == R.id.nav_ielts){
            if(mCurrentFargment!= FRAGMENT_IELTS){
                replaceFragment(new IeltsFragment());
                mCurrentFargment = FRAGMENT_IELTS;
            }
        }else if(id == R.id.nav_test){
            if(mCurrentFargment!= FRAGMENT_TEST){
                replaceFragment(new TestFragment());
                mCurrentFargment = FRAGMENT_TEST;
            }
        }else if(id == R.id.nav_profile){
            if(mCurrentFargment!= FRAGMENT_PROFILE){
                replaceFragment(new ProfileFragment());
                mCurrentFargment = FRAGMENT_PROFILE;
            }
        }else if(id == R.id.nav_edit){
            Intent i = new Intent(Home.this, editAcount.class);
            startActivity(i);

        }else if(id == R.id.nav_setting){
            if(mCurrentFargment!= FRAGMENT_SETTING){
                replaceFragment(new SettingFragment());
                mCurrentFargment = FRAGMENT_SETTING;
            }
        }else if(id == R.id.nav_logout){
            AlertDialog.Builder builder = new AlertDialog.Builder(Home.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
            builder.setTitle("You definitely want to sign out of your account?");
            builder.setMessage("Choose yes or no!");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(Home.this, Login.class);
                    startActivity(i);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame,fragment);
        transaction.commit();
    }
    CreateDatabase db = new CreateDatabase(this);
    

}
