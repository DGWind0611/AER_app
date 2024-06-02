package com.fcu.android.animal_emergency_rescure;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class InformationCardAdapter extends BaseAdapter {
    private Context context;
    private List<Informations> infos;

    public InformationCardAdapter(Context context, List<Informations> infos) {
        this.context = context;
        this.infos = infos;
    }

    @Override
    public int getCount() { return infos.size(); }

    @Override
    public Object getItem(int i) { return infos.get(i); }

    @Override
    public long getItemId(int i) { return i; }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.information_card, viewGroup, false);
        }
        TextView infoCardTitle = view.findViewById(R.id.tv_info_card_title);

        Informations i = infos.get(position);

        infoCardTitle.setText(i.getInfoCardTitle());

        // 點選小知識卡片時跳轉網頁顯示PDF
        infoCardTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse(i.getInfoCardLink());
                // 創建intent開啟URI
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                context.startActivity(intent); // Start the activity to open the URI
            }
        });

        return view;
    }
}
