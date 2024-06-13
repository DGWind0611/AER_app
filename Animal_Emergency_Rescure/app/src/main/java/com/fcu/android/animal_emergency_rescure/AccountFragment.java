package com.fcu.android.animal_emergency_rescure;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountFragment extends Fragment {

    private Button btnStar, btnChangePassword, btnLogout;
    private FragmentManager manager;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        btnStar = view.findViewById(R.id.btn_star);
        btnChangePassword = view.findViewById(R.id.btn_change_password);
        btnLogout = view.findViewById(R.id.btn_logout);
        mAuth = FirebaseAuth.getInstance();
        manager = getParentFragmentManager();

        // 依照一般使用者或訪客顯示不同的按鈕
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user.isAnonymous()) {
            btnStar.setVisibility(View.GONE);
            btnChangePassword.setVisibility(View.GONE);
            btnLogout.setVisibility(View.VISIBLE);
        } else {
            btnStar.setVisibility(View.VISIBLE);
            btnChangePassword.setVisibility(View.VISIBLE);
            btnLogout.setVisibility(View.VISIBLE);
        }

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = manager.beginTransaction();
                if (v.getId() == R.id.btn_star) {
                    // 將showFavorite設置为true
                    IllustratedBookCardAdapter.showFavorite = true;
                    // 跳轉至圖鑑頁面
                    transaction.replace(R.id.container, new IllustratedBookFragment()).commit();
                } else if (v.getId() == R.id.btn_change_password) {
                    // 跳轉至修改密碼頁面
                    transaction.replace(R.id.container, new ChangePasswordFragment()).commit();
                } else if (v.getId() == R.id.btn_logout) {
                    mAuth.signOut();
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), SignInActivity.class);
                    getActivity().startActivity(intent);
                    // 如果是訪客，登出時刪除帳號
                    if (user.isAnonymous()) {
                        user.delete();
                    }
                }
                transaction.addToBackStack(null);
            }
        };
        btnStar.setOnClickListener(listener);
        btnChangePassword.setOnClickListener(listener);
        btnLogout.setOnClickListener(listener);

        return view;
    }


}