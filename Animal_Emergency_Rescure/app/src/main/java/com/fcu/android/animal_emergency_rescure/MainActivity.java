package com.fcu.android.animal_emergency_rescure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;

    IllustratedBookFragment illustratedBookFragment = new IllustratedBookFragment();
    InformationFragment informationFragment = new InformationFragment();
    SecureFragment secureFragment = new SecureFragment();
    RecruitFragment recruitFragment = new RecruitFragment();

    AccountFragment accountFragment = new AccountFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, illustratedBookFragment).commit();

        BadgeDrawable badgeDrawable = bottomNavigationView.getOrCreateBadge(R.id.information);
        badgeDrawable.setVisible(true);
        badgeDrawable.setNumber(8);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (item.getItemId() == R.id.illustrated_book) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, illustratedBookFragment).commit();
                    return true;
                } else if (item.getItemId() == R.id.information) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, informationFragment).commit();
                    return true;
                } else if (item.getItemId() == R.id.secure) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, secureFragment).commit();
                    return true;
                } else if (item.getItemId() == R.id.recruit) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, recruitFragment).commit();
                    return true;
                } else if (item.getItemId() == R.id.account) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, accountFragment).commit();
                    return true;
                }
                return false;
            }
        });
    }
}