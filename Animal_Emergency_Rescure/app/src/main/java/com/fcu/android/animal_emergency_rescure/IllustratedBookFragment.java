package com.fcu.android.animal_emergency_rescure;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.List;


public class IllustratedBookFragment extends Fragment {
    private GridView gvBirds;
    private GridView gvMams;
    private GridView gvRepts;
    private GridView gvAmphis;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_illustrated_book, container, false);
        gvBirds = view.findViewById(R.id.gv_bird);
        gvMams = view.findViewById(R.id.gv_mam);
        gvRepts = view.findViewById(R.id.gv_rept);
        gvAmphis = view.findViewById(R.id.gv_amphi);

        Species bird_1 = new Species(1,R.drawable.poke1,"鳥0001","詳細介紹0001");
        Species bird_2 = new Species(2,R.drawable.poke4,"鳥0002","詳細介紹0002");
        Species bird_3 = new Species(3,R.drawable.poke7,"鳥0003","詳細介紹0003");
        Species bird_4 = new Species(4,R.drawable.poke25,"鳥0004","詳細介紹0004");
        Species bird_5 = new Species(5,R.drawable.poke25,"鳥0005","詳細介紹0005");
        Species bird_6 = new Species(6,R.drawable.poke25,"鳥0006","詳細介紹0006");
        Species bird_7 = new Species(7,R.drawable.poke25,"鳥0007","詳細介紹0007");

        Species mam_1 = new Species(8,R.drawable.poke25, "哺乳0001","詳細介紹0008");

        Species rept_1 = new Species(9,R.drawable.poke25, "爬蟲0001","詳細介紹0009");
        Species rept_2 = new Species(10,R.drawable.poke25, "爬蟲0002","詳細介紹0010");
        Species rept_3 = new Species(11,R.drawable.poke25, "爬蟲0003","詳細介紹0011");
        Species rept_4 = new Species(12,R.drawable.poke25, "爬蟲0004","詳細介紹0012");

        Species amphi_1 = new Species(13,R.drawable.poke25, "兩棲0001","詳細介紹0013");
        Species amphi_2 = new Species(14,R.drawable.poke25, "兩棲0002","詳細介紹0014");
        Species amphi_3 = new Species(15,R.drawable.poke25, "兩棲0003","詳細介紹0015");
        Species amphi_4 = new Species(16,R.drawable.poke25, "兩棲0004","詳細介紹0016");
        Species amphi_5 = new Species(17,R.drawable.poke25, "兩棲0005","詳細介紹0017");
        Species amphi_6 = new Species(18,R.drawable.poke25, "兩棲0006","詳細介紹0018");
        Species amphi_7 = new Species(19,R.drawable.poke25, "兩棲0007","詳細介紹0019");
        Species amphi_8 = new Species(20,R.drawable.poke25, "兩棲0008","詳細介紹0020");
        Species amphi_9 = new Species(21,R.drawable.poke25, "兩棲0009","詳細介紹0021");
        Species amphi_10 = new Species(22,R.drawable.poke25, "兩棲0010","詳細介紹0022");

        List<Species> birds = new ArrayList<>();
        birds.add(bird_1);
        birds.add(bird_2);
        birds.add(bird_3);
        birds.add(bird_4);
        birds.add(bird_5);
        birds.add(bird_6);
        birds.add(bird_7);

        List mams = new ArrayList<>();
        mams.add(mam_1);

        List repts = new ArrayList<>();
        repts.add(rept_1);
        repts.add(rept_2);
        repts.add(rept_3);
        repts.add(rept_4);

        List amphis = new ArrayList<>();
        amphis.add(amphi_1);
        amphis.add(amphi_2);
        amphis.add(amphi_3);
        amphis.add(amphi_4);
        amphis.add(amphi_5);
        amphis.add(amphi_6);
        amphis.add(amphi_7);
        amphis.add(amphi_8);
        amphis.add(amphi_9);
        amphis.add(amphi_10);

        IllustratedBookCardAdapter birdAdapter = new IllustratedBookCardAdapter(getContext(), birds);
        gvBirds.setAdapter(birdAdapter);
        IllustratedBookCardAdapter mamAdapter = new IllustratedBookCardAdapter(getContext(), mams);
        gvMams.setAdapter(mamAdapter);
        IllustratedBookCardAdapter reptAdapter = new IllustratedBookCardAdapter(getContext(), repts);
        gvRepts.setAdapter(reptAdapter);
        IllustratedBookCardAdapter amphiAdapter = new IllustratedBookCardAdapter(getContext(), amphis);
        gvAmphis.setAdapter(amphiAdapter);

        calculateAndSetGVsHeight(gvBirds, birdAdapter, 3, 60);
        calculateAndSetGVsHeight(gvMams, mamAdapter, 3, 60);
        calculateAndSetGVsHeight(gvRepts, reptAdapter, 3, 60);
        calculateAndSetGVsHeight(gvAmphis, amphiAdapter, 3, 60);
        return view;
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