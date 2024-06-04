package com.fcu.android.animal_emergency_rescure;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class AccountFragment extends Fragment {

    private Button btnStar, btnChangePassword, btnLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        btnStar = view.findViewById(R.id.btn_star);
        btnChangePassword = view.findViewById(R.id.btn_change_password);
        btnLogout = view.findViewById(R.id.btn_logout);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_star) {
                    // TODO: Implement star
                } else if (v.getId() == R.id.btn_change_password) {
                    // TODO: Implement change password
                } else if (v.getId() == R.id.btn_logout) {
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), SignInActivity.class);
                    getActivity().startActivity(intent);
                }
            }
        };
        btnStar.setOnClickListener(listener);
        btnChangePassword.setOnClickListener(listener);
        btnLogout.setOnClickListener(listener);

        return view;
    }
}