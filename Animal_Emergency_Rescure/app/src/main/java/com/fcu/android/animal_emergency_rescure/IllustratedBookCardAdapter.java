package com.fcu.android.animal_emergency_rescure;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class IllustratedBookCardAdapter extends BaseAdapter {
    private Context context;
    private List<Species> species;
    private ImageButton favoriteButton;
    private Set<Integer> favoriteSpeciesIds; // 儲存最愛的ID

    public IllustratedBookCardAdapter(Context context, List<Species> species) {
        this.context = context;
        this.species = species;
        this.favoriteSpeciesIds = new HashSet<>();
    }

    @Override
    public int getCount() {
        return species.size();
    }

    @Override
    public Object getItem(int i) {
        return species.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int index, View view, ViewGroup viewGroup) {
        if(view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.illustrated_book_card,viewGroup,false);
        }

        ImageView speciesPic = view.findViewById(R.id.iv_species_pic);
        TextView speciesName = view.findViewById(R.id.tv_species_name);
        ImageButton favoriteButton = view.findViewById(R.id.imgbtn_favorite);

        Species s = species.get(index);
        speciesPic.setImageResource(s.getSpeciesPicId());
        speciesName.setText(s.getSpeciesName());

        // 圖鑑卡片點擊事件 intent傳遞資料
        view.setOnClickListener(v -> { // lambda
            Intent intent = new Intent(context, illustratedBookDetailPage.class);
            intent.putExtra("speciesImageId", s.getSpeciesPicId());
            intent.putExtra("speciesName", s.getSpeciesName());
            context.startActivity(intent);
        });

        // 設置點擊收藏按鈕的事件
        favoriteButton.setOnClickListener(v -> { // lambda
            // 如果set裡已經有最愛則移除
            if (favoriteSpeciesIds.contains(s.getSpeciesId())) {
                favoriteSpeciesIds.remove(s.getSpeciesId());
                favoriteButton.setImageResource(R.drawable.heart_empty);
            } else { // 否則加入最愛
                favoriteSpeciesIds.add(s.getSpeciesId());
                favoriteButton.setImageResource(R.drawable.heart_filled);
            }
        });


        return view;
    }

}
