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

        uploadSpeciesData();

        loadSpeciesData();

        return view;
    }

    private void uploadSpeciesData() { // 程式碼上傳物種至firebase資料庫
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("species");

        //Species bird_1 = new Species(1, R.drawable.poke1, "鳥0001", SpeciesType.BIRDS, "詳細介紹0001");
        Species bird_1 = new Species(1, R.drawable.s01, "斯氏繡眼", SpeciesType.BIRDS,"綠繡眼",SpeciesNative.NATIVE,SpeciesCons.NORMAL, "留鳥。棲息於樹林地帶以昆蟲、植物果實為主食。為平地、都市常見種類，出現於闊葉林、果園、公園、樹林。主要食物為果實、昆蟲。海拔分布於0至2000公尺。");
        Species bird_2 = new Species(2, R.drawable.s02, "麻雀", SpeciesType.BIRDS,"樹麻雀",SpeciesNative.NATIVE,SpeciesCons.NORMAL, "留鳥。嘴三角錐型，常在地面上靈活跳躍，多半出現於人類聚落或開墾地附近，為最常見的種類之一。出現於公園、樹林、稻田、果園、草生地。主要食物為種子、果實。海拔分布於0至600公尺。");
        Species bird_3 = new Species(3, R.drawable.s03, "臺灣藍鵲", SpeciesType.BIRDS,"紅嘴山鵲",SpeciesNative.NATIVE,SpeciesCons.PROTECTED, "留鳥。食性雜食性，警覺性高，主要棲息於樹林中，築巢於高枝上，雛鳥為晚熟性。出現於闊葉林。主要食物為種子、果實、昆蟲。海拔分布於100至1200公尺。");
        Species bird_4 = new Species(4, R.drawable.s04, "黑面琵鷺", SpeciesType.BIRDS,"黑臉琵鷺",SpeciesNative.NONNATIVE,SpeciesCons.NORMAL, "冬候鳥。常小群出現於海岸附近的沙洲及淺灘。多於黃昏及夜間覓食，白天休息停棲。覓食時，以扁平的匙狀嘴喙於淺水中左右撈動。主要食物為魚類、昆蟲、兩生類。海拔分布於0至50公尺。");
        Species bird_5 = new Species(5, R.drawable.s05, "喜鵲", SpeciesType.BIRDS,"無",SpeciesNative.INVASIVE,SpeciesCons.NORMAL, "留鳥。食性雜食性，警覺性高，主要棲息於樹林中，築巢於高枝上，雛鳥為晚熟性。出現於闊葉林、草生地。主要食物為種子、果實、昆蟲。海拔分布於0至600公尺。");
        Species bird_6 = new Species(6, R.drawable.s06, "黑長尾雉", SpeciesType.BIRDS,"帝雉",SpeciesNative.NATIVE,SpeciesCons.PROTECTED, "留鳥。飛行能力不佳。食性以植物種子、嫩葉、漿果及土中小蟲為食，機警羞怯，於晨昏覓食。慣常棲息於樹林底層及交界，於地面築巢，雛鳥為早熟性。出現於闊葉林、灌叢、針葉林。海拔分布於1800至3900公尺。");
        Species bird_7 = new Species(7, R.drawable.s07, "五色鳥", SpeciesType.BIRDS,"台灣擬啄木",SpeciesNative.NATIVE,SpeciesCons.NORMAL, "留鳥。出現於闊葉林，也常於人類住家附近公園出現。主要食物為果實、昆蟲。習性似啄木鳥，會以嘴喙在樹幹上挖洞築巢。海拔分布於0至2800公尺。");

        // 將 Species 物件上傳到 Firebase
        //myRef.child(String.valueOf(bird_1.getSpeciesId())).setValue(bird_1);
        myRef.child(String.valueOf(bird_1.getSpeciesId())).setValue(bird_1);
        myRef.child(String.valueOf(bird_2.getSpeciesId())).setValue(bird_2);
        myRef.child(String.valueOf(bird_3.getSpeciesId())).setValue(bird_3);
        myRef.child(String.valueOf(bird_4.getSpeciesId())).setValue(bird_4);
        myRef.child(String.valueOf(bird_5.getSpeciesId())).setValue(bird_5);
        myRef.child(String.valueOf(bird_6.getSpeciesId())).setValue(bird_6);
        myRef.child(String.valueOf(bird_7.getSpeciesId())).setValue(bird_7);
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