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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_illustrated_book, container, false);
        gvBirds = view.findViewById(R.id.gv_bird);

        Species bird_1 = new Species(R.drawable.poke1,"鳥0001");
        Species bird_2 = new Species(R.drawable.poke4,"鳥0002");
        Species bird_3 = new Species(R.drawable.poke7,"鳥0003");
        Species bird_4 = new Species(R.drawable.poke25,"鳥0004");
        Species bird_5 = new Species(R.drawable.poke25,"鳥0005");
        Species bird_6 = new Species(R.drawable.poke25,"鳥0006");
        Species bird_7 = new Species(R.drawable.poke25,"鳥0007");

        List<Species> birds = new ArrayList<>();
        birds.add(bird_1);
        birds.add(bird_2);
        birds.add(bird_3);
        birds.add(bird_4);
        birds.add(bird_5);
        birds.add(bird_6);
        birds.add(bird_7);

        IllustratedBookCardAdapter adapter = new IllustratedBookCardAdapter(getContext(), birds);
        gvBirds.setAdapter(adapter);

        // 如果adapter不為空，且有item
        if (adapter != null && adapter.getCount() > 0) {
            int totalHeight = 0;
            int itemsPerRow = 3; // 每行幾個

            int rowCount = (adapter.getCount() + itemsPerRow - 1) / itemsPerRow; // 計算有幾行

            // 計算總高度(item高度*行數)
            for (int i = 0; i < rowCount; i++) {
                View listItem = adapter.getView(i, null, gvBirds);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight() + 60;
            }

            // 設置Gridview高度
            ViewGroup.LayoutParams params = gvBirds.getLayoutParams();
            params.height = totalHeight ;
            gvBirds.setLayoutParams(params);
            gvBirds.requestLayout(); // 重新布局
        }

        return view;
    }
}