package com.fcu.android.animal_emergency_rescure;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class illustratedBookDetailPage extends AppCompatActivity {

    private TextView tvSpeciesDetailName;
    private ImageView ivSpeciesFullSizePic;
    private TextView tvSpeciesDetailDescription;
    private ImageButton ibSpeciesDetailBack;
    private Button btnDetailPageAddToFavorite;

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;
    private FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.illustrated_book_detail_page);

        tvSpeciesDetailName = findViewById(R.id.tv_species_detail_name);
        ivSpeciesFullSizePic = findViewById(R.id.iv_species_full_size_pic);
        tvSpeciesDetailDescription = findViewById(R.id.tv_species_detail_description);
        ibSpeciesDetailBack = findViewById(R.id.ib_species_detail_page_back);
        btnDetailPageAddToFavorite = findViewById(R.id.btn_speces_detail_add_to_fav);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        currentUser = mAuth.getCurrentUser();

        if (currentUser != null && currentUser.isAnonymous()) {
            // 匿名用戶，不允許加入最愛
            btnDetailPageAddToFavorite.setVisibility(View.GONE);
        }

        // 獲取intent資料
        Intent intent = getIntent();
        int speciesId = intent.getIntExtra("speciesId",-1);
        String speciesName = intent.getStringExtra("speciesName");
        int speciesImageId = intent.getIntExtra("speciesImageId", -1);
        String speciesDescription = intent.getStringExtra("speciesDespription");

        // 設置物種詳細資訊
        tvSpeciesDetailName.setText(speciesName);
        ivSpeciesFullSizePic.setImageResource(speciesImageId);
        tvSpeciesDetailDescription.setText(speciesDescription);

        // 返回上一頁
        ibSpeciesDetailBack.setOnClickListener(view -> finish());

        // 檢查物種是否在最愛中
        checkIfSpeciesIsFavorite(speciesId);
    }

    // 檢查物種是否在最愛中
    private void checkIfSpeciesIsFavorite(int speciesId) {
        if (currentUser != null) {
            String email = currentUser.getEmail();
            if (email != null) {
                String encodedEmail = encodeEmail(email);
                databaseReference.child(encodedEmail).child("favorites").child(String.valueOf(speciesId))
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    // 物種在最愛中，設置按鈕文本為"從最愛移除"
                                    btnDetailPageAddToFavorite.setText("從最愛移除");
                                    btnDetailPageAddToFavorite.setBackgroundColor(getResources().getColor(R.color.fav_light_gray));
                                    btnDetailPageAddToFavorite.setOnClickListener(v -> removeFromFavorites(speciesId));
                                } else {
                                    // 物種不在最愛中，設置按鈕文本為"加入最愛"
                                    btnDetailPageAddToFavorite.setText("加入最愛");
                                    btnDetailPageAddToFavorite.setBackgroundColor(getResources().getColor(R.color.fav_pink));
                                    btnDetailPageAddToFavorite.setOnClickListener(v -> addToFavorites(speciesId));
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                // 處理錯誤
                            }
                        });
            }
        }
    }

    // 加入最愛
    private void addToFavorites(int speciesId) {
        String email = currentUser.getEmail();
        if (email != null) {
            String encodedEmail = encodeEmail(email);
            databaseReference.child(encodedEmail).child("favorites").child(String.valueOf(speciesId)).setValue(true);
            btnDetailPageAddToFavorite.setText("從最愛移除");
            btnDetailPageAddToFavorite.setBackgroundColor(getResources().getColor(R.color.fav_light_gray));
            btnDetailPageAddToFavorite.setOnClickListener(v -> removeFromFavorites(speciesId));
        }
    }

    // 從最愛移除
    private void removeFromFavorites(int speciesId) {
        String email = currentUser.getEmail();
        if (email != null) {
            String encodedEmail = encodeEmail(email);
            databaseReference.child(encodedEmail).child("favorites").child(String.valueOf(speciesId)).removeValue();
            btnDetailPageAddToFavorite.setText("加入最愛");
            btnDetailPageAddToFavorite.setBackgroundColor(getResources().getColor(R.color.fav_pink));
            btnDetailPageAddToFavorite.setOnClickListener(v -> addToFavorites(speciesId));
        }
    }

    private String encodeEmail(String email) {
        return email.replace(".", ",");
    }
}
