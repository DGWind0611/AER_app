package com.fcu.android.animal_emergency_rescure;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class IllustratedBookFragment extends Fragment {
    private GridView gvBirds;
    private GridView gvMams;
    private GridView gvRepts;
    private GridView gvAmphis;
    private ProgressBar progressBar;
    private ScrollView contentScrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_illustrated_book, container, false);

        gvBirds = view.findViewById(R.id.gv_bird);
        gvMams = view.findViewById(R.id.gv_mam);
        gvRepts = view.findViewById(R.id.gv_rept);
        gvAmphis = view.findViewById(R.id.gv_amphi);

        progressBar = view.findViewById(R.id.progress_bar);
        contentScrollView = view.findViewById(R.id.sv_species);

        progressBar.setVisibility(View.VISIBLE);
        contentScrollView.setVisibility(View.GONE);

        loadSpeciesData();

        uploadSpeciesData();

        return view;
    }

    private void uploadSpeciesData() { // 程式碼上傳物種至firebase資料庫
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("species");

        // 創建 Species 物件
        //Species bird_1 = new Species(1, R.drawable.poke1, "鳥0001", SpeciesType.BIRDS, "詳細介紹0001");------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

        // 將 Species 物件上傳到 Firebase
        //myRef.child(String.valueOf(bird_1.getSpeciesId())).setValue(bird_1);
    }

    private void loadSpeciesData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("species");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Species> birds = new ArrayList<>();
                List<Species> mams = new ArrayList<>();
                List<Species> repts = new ArrayList<>();
                List<Species> amphis = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Species species = snapshot.getValue(Species.class);

                    if (species != null) {
                        switch (species.getSpeciesType()) {
                            case BIRDS:
                                birds.add(species);
                                break;
                            case MAMMALS:
                                mams.add(species);
                                break;
                            case REPTILES:
                                repts.add(species);
                                break;
                            case AMPHIBIANS:
                                amphis.add(species);
                                break;
                        }
                    }
                }

                // 設置Adapter並更新UI
                IllustratedBookCardAdapter birdAdapter = new IllustratedBookCardAdapter(getContext(), birds);
                gvBirds.setAdapter(birdAdapter);

                IllustratedBookCardAdapter mamAdapter = new IllustratedBookCardAdapter(getContext(), mams);
                gvMams.setAdapter(mamAdapter);

                IllustratedBookCardAdapter reptAdapter = new IllustratedBookCardAdapter(getContext(), repts);
                gvRepts.setAdapter(reptAdapter);

                IllustratedBookCardAdapter amphiAdapter = new IllustratedBookCardAdapter(getContext(), amphis);
                gvAmphis.setAdapter(amphiAdapter);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        calculateAndSetGVsHeight(gvBirds, birdAdapter, 3, 60);
                        calculateAndSetGVsHeight(gvMams, mamAdapter, 3, 60);
                        calculateAndSetGVsHeight(gvRepts, reptAdapter, 3, 60);
                        calculateAndSetGVsHeight(gvAmphis, amphiAdapter, 3, 60);

                        progressBar.setVisibility(View.GONE); // 計算完高度後隱藏進度條
                        contentScrollView.setVisibility(View.VISIBLE);
                    }
                }, 500); // 延遲500毫秒
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // 處理錯誤
                progressBar.setVisibility(View.GONE);
                contentScrollView.setVisibility(View.VISIBLE);
            }
        });
    }

    public void calculateAndSetGVsHeight(GridView gridView, ListAdapter adapter, int itemsPerRow, int extraHeight) {
        // 如果adapter不為空，且有item
        if (adapter != null && adapter.getCount() > 0) {
            int totalHeight = 0;

            // 計算有幾行(最少一行)
            int rowCount = (adapter.getCount() + itemsPerRow - 1) / itemsPerRow;

            // 計算總高度(item高度*行數)
            for (int i = 0; i < rowCount; i++) {
                View listItem = adapter.getView(i, null, gridView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
            totalHeight += rowCount * extraHeight; // 增加額外高度

            // 設置Gridview高度
            ViewGroup.LayoutParams params = gridView.getLayoutParams();
            params.height = totalHeight;
            gridView.setLayoutParams(params);
            gridView.requestLayout(); // 重新布局
        }
    }

    // 設置showFavorite的值
    public void setShowFavorite(boolean showFavorite) {
        IllustratedBookCardAdapter.showFavorite = showFavorite;
    }

    @Override
    public void onPause() {
        super.onPause();
        // 當user離開頁面時，將showFavorite設為false
        IllustratedBookCardAdapter.showFavorite = false;
    }
}