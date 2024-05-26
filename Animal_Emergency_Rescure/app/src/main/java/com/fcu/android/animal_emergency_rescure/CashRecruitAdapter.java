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
    public Object getItem(int i) {
        return lsCash.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.cash_recruit_card, viewGroup, false);
        }

        ImageView imCashRecruit = view.findViewById(R.id.iv_cash_recruit);
        Button btnMore = view.findViewById(R.id.btn_more);

        Cash cash = lsCash.get(i);
        imCashRecruit.setImageResource(cash.getCashRecruit());

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(cash.getUrl()));
                context.startActivity(intent);
            }
        };
        btnMore.setOnClickListener(listener);

        return view;
    }
}
