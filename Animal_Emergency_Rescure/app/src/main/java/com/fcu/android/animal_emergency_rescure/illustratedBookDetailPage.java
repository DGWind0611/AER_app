package com.fcu.android.animal_emergency_rescure;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class illustratedBookDetailPage extends AppCompatActivity {

    private TextView tvSpeciesDetailName;
    private ImageView ivSpeciesFullSizePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.illustrated_book_detail_page);

        tvSpeciesDetailName = findViewById(R.id.tv_species_detail_name);
        ivSpeciesFullSizePic = findViewById(R.id.iv_species_full_size_pic);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 獲取intent資料
        Intent intent = getIntent();
        String speciesName = intent.getStringExtra("speciesName");
        int speciesImageId = intent.getIntExtra("speciesImageId", -1);

        tvSpeciesDetailName.setText(speciesName);
        ivSpeciesFullSizePic.setImageResource(speciesImageId);

    }
}