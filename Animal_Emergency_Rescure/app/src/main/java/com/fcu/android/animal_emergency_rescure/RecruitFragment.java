package com.fcu.android.animal_emergency_rescure;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class RecruitFragment extends Fragment {

    private Button btnCash;
    private Button btnMember;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recruit, container, false);
        // Find the views
        btnCash = view.findViewById(R.id.btn_cash);
        btnMember = view.findViewById(R.id.btn_member);
        return view;
    }
}