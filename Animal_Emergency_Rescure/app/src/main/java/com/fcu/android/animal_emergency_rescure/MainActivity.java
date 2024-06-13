package com.fcu.android.animal_emergency_rescure;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    //上方search bar
    private EditText search;
    private Button btnFront;
    private Button btnFavorite;
    private Button btnRecently;
    private ImageButton btnFilter;
    private View indicatorFront;
    private View indicatorFavorite;
    private View indicatorRecently;
    private FirebaseAuth mAuth;
    private FirebaseUser user;


    //底部導覽列
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

        search = findViewById(R.id.et_search);
        btnFront = findViewById(R.id.btn_front);
        btnFavorite = findViewById(R.id.btn_favorite);
        btnRecently = findViewById(R.id.btn_recently);
        indicatorFront = findViewById(R.id.indicator_front);
        indicatorFavorite = findViewById(R.id.indicator_favorite);
        indicatorRecently = findViewById(R.id.indicator_recently);
        btnFilter = findViewById(R.id.btn_filter);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View filterLayout = findViewById(R.id.filter_layout);
                if (filterLayout.getVisibility() == View.GONE) {
                    filterLayout.setVisibility(View.VISIBLE);
                } else {
                    filterLayout.setVisibility(View.GONE);
                }
            }
        });
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_front) {
                    indicatorFront.setVisibility(View.VISIBLE);
                    indicatorFavorite.setVisibility(View.INVISIBLE);
                    indicatorRecently.setVisibility(View.INVISIBLE);
                } else if (v.getId() == R.id.btn_favorite) {
                    indicatorFront.setVisibility(View.INVISIBLE);
                    indicatorFavorite.setVisibility(View.VISIBLE);
                    indicatorRecently.setVisibility(View.INVISIBLE);
                } else if (v.getId() == R.id.btn_recently) {
                    indicatorFront.setVisibility(View.INVISIBLE);
                    indicatorFavorite.setVisibility(View.INVISIBLE);
                    indicatorRecently.setVisibility(View.VISIBLE);
                }
            }
        };

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.secure);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, secureFragment).commit();

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
        btnFront.setOnClickListener(onClickListener);
        btnFavorite.setOnClickListener(onClickListener);
        btnRecently.setOnClickListener(onClickListener);
    }
    // 如果是訪客，離開時將刪除資料
    @Override
    protected void onStop() {
        super.onStop();
        mAuth = FirebaseAuth.getInstance();
        user = mAuth.getCurrentUser();
        if (user != null && user.isAnonymous()) {
            mAuth.signOut();
            user.delete();
        }
    }
}