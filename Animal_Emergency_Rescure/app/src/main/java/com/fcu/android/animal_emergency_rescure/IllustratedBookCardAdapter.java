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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class IllustratedBookCardAdapter extends BaseAdapter {
    private Context context;
    private List<Species> allSpecies; // 全部物種列表
    private List<Species> species;
    private ImageButton favoriteButton;
    private List<Species> favoriteSpecies;
    private Set<Integer> favoriteSpeciesIds; // 儲存最愛的ID

    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    public static boolean showFavorite = false; // 控制顯示最愛

    // 加載用戶的最愛項目
    public void loadUserFavorites() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String email = currentUser.getEmail();
            if (email != null) {
                String encodedEmail = encodeEmail(email);
                databaseReference.child(encodedEmail).child("favorites").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        favoriteSpeciesIds.clear(); // 清空之前的收藏項
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            favoriteSpeciesIds.add(Integer.valueOf(snapshot.getKey()));
                        }
                        filterFavoriteSpecies(); // 過濾收藏的物種
                        notifyDataSetChanged(); // 更新視圖
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // 處理錯誤
                    }
                });
            }
        }
    }

    // 過濾出最愛物種
    private void filterFavoriteSpecies() {
        species.clear();
        if (showFavorite) {
            for (Species s : allSpecies) {
                if (favoriteSpeciesIds.contains(s.getSpeciesId())) {
                    species.add(s);
                }
            }
        } else {
            species.addAll(allSpecies);
        }
    }


    public IllustratedBookCardAdapter(Context context, List<Species> species) {
        this.context = context;
        this.allSpecies = species; // 赋值给 allSpecies
        this.species = new ArrayList<>(species); // 初始化為全部物種列表
        this.favoriteSpecies = new ArrayList<>(); // 初始化為一個空列表
        this.favoriteSpeciesIds = new HashSet<>();
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
        loadUserFavorites(); // 加載用戶的最愛項目
    }

    @Override
    public int getCount() {
        System.out.println("Size === " + species.size());
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
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.illustrated_book_card, viewGroup, false);
        }

        ImageView speciesPic = view.findViewById(R.id.iv_species_pic);
        TextView speciesName = view.findViewById(R.id.tv_species_name);
        ImageButton favoriteButton = view.findViewById(R.id.imgbtn_favorite);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();

        if (user != null && user.isAnonymous()) {
            favoriteButton.setVisibility(View.GONE);
            speciesPic.setPadding(0, 16, 0, 0);
        }

        Species s = species.get(index);
        speciesPic.setImageResource(s.getSpeciesPicId());
        speciesName.setText(s.getSpeciesName());

        // 跳到圖鑑頁面時根據 favoriteSpeciesIds 設置愛心圖案的狀態
        if (favoriteSpeciesIds.contains(s.getSpeciesId())) {
            favoriteButton.setImageResource(R.drawable.heart_filled);
        } else {
            favoriteButton.setImageResource(R.drawable.heart_empty);
        }

        // 圖鑑卡片點擊事件 intent傳遞資料
        view.setOnClickListener(v -> { // lambda
            Intent intent = new Intent(context, illustratedBookDetailPage.class);
            intent.putExtra("speciesId", s.getSpeciesId());
            intent.putExtra("speciesImageId", s.getSpeciesPicId());
            intent.putExtra("speciesName", s.getSpeciesName());
            intent.putExtra("speciesNative", s.getSpeciesNative().getNativeString());
            intent.putExtra("speciesConservation", s.getSpeciesConservation().getConsString());
            intent.putExtra("speciesDespription", s.getSpeciesDescription());
            context.startActivity(intent);
        });

        // 設置點擊收藏按鈕的事件
        // 更新favoriteButton的點擊事件處理程序
        favoriteButton.setOnClickListener(v -> { // lambda
            FirebaseUser currentUser = mAuth.getCurrentUser();
            if (currentUser != null) {
                String email = currentUser.getEmail();
                if (email != null) {
                    String encodedEmail = encodeEmail(email);

                    // 如果set裡已經有最愛則移除
                    if (favoriteSpeciesIds.contains(s.getSpeciesId())) {
                        favoriteSpeciesIds.remove(s.getSpeciesId());
                        favoriteButton.setImageResource(R.drawable.heart_empty);
                        // 從資料庫中移除
                        databaseReference.child(encodedEmail).child("favorites").child(String.valueOf(s.getSpeciesId())).removeValue();
                    } else { // 否則加入最愛
                        favoriteSpeciesIds.add(s.getSpeciesId());
                        favoriteButton.setImageResource(R.drawable.heart_filled);
                        // 添加到資料庫
                        databaseReference.child(encodedEmail).child("favorites").child(String.valueOf(s.getSpeciesId())).setValue(true);
                    }
                    filterFavoriteSpecies(); // 過濾收藏的物種
                    notifyDataSetChanged(); // 更新視圖
                }
            }
        });

        return view;
    }

    private String encodeEmail(String email) {
        return email.replace(".", ",");
    }

}
