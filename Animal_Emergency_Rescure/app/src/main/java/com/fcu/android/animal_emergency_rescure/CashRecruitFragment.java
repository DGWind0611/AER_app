package com.fcu.android.animal_emergency_rescure;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class CashRecruitFragment extends Fragment {

    private GridView gvCash;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cash_recruit, container, false);

        gvCash = view.findViewById(R.id.gv_cash);

        // 建立捐款資料
        Cash cash1 = new Cash(R.drawable.cash01, "https://www.hotac.org.tw/content-donate_main");
        Cash cash2 = new Cash(R.drawable.cash02, "https://dogcat.org.tw/donate/");
        Cash cash3 = new Cash(R.drawable.cash03, "https://hsapf.org.tw/donate/");
        Cash cash4 = new Cash(R.drawable.cash04, "https://tsaca.org.tw/donation");
        List<Cash> cash = new ArrayList<>();
        // 加入捐款資料
        cash.add(cash1);
        cash.add(cash2);
        cash.add(cash3);
        cash.add(cash4);
        // 建立適配器
        CashRecruitAdapter cashRecruitAdapter = new CashRecruitAdapter(getContext(), cash);
        gvCash.setAdapter(cashRecruitAdapter);

        return view;
    }
}