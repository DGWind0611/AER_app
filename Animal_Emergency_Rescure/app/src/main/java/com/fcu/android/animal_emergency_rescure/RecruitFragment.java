package com.fcu.android.animal_emergency_rescure;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RecruitFragment extends Fragment {

    private FragmentManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recruit, container, false);
        // 取得 FragmentManager 物件
        manager = getActivity().getSupportFragmentManager();
        // 設定按鈕的監聽事件
        view.findViewById(R.id.btn_cash).setOnClickListener(this::onClick);
        view.findViewById(R.id.btn_member).setOnClickListener(this::onClick);
        return view;
    }

    public void onClick(View v) {
        // 開始一個 FragmentTransaction 的交易
        FragmentTransaction transaction = manager.beginTransaction();
        if (v.getId() == R.id.btn_cash) {
            // 跳轉到現金招募 Fragment
            transaction.replace(R.id.container, new CashRecruitFragment()).commit();
        } else if (v.getId() == R.id.btn_member) {
            // 跳轉到會員招募 Fragment
            transaction.replace(R.id.container, new MemberRecruitFragment()).commit();
        }
        // 將 Fragment 加入到返回堆疊
        transaction.addToBackStack(null);
    }
}