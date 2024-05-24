package com.fcu.android.animal_emergency_rescure;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

public class CashRecruitAdapter extends BaseAdapter {

    private Context context;
    private List<Cash> lsCash;

    public CashRecruitAdapter(Context context, List<Cash> lsCash) {
        this.context = context;
        this.lsCash = lsCash;
    }

    @Override
    public int getCount() {
        return lsCash.size();
    }

    @Override
    public Object getItem(int position) {
        return lsCash.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cash_recruit_layout, parent, false);
        }

        ImageView imCashRecruit = convertView.findViewById(R.id.iv_cash_recruit);
        Button btnMore = convertView.findViewById(R.id.btn_more);

        Cash cash = lsCash.get(position);

        imCashRecruit.setImageResource(cash.getCashRecruit());

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(cash.getUrl()));
                context.startActivity(intent);
            }
        };
        btnMore.setOnClickListener(listener);

        return convertView;
    }
}
