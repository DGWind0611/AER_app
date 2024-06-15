package com.fcu.android.animal_emergency_rescure;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SecureFragment extends Fragment {

    private CardView btnBird;
    private FragmentManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_secure, container, false);

        btnBird = view.findViewById(R.id.btn_bird);
        manager = getParentFragmentManager();

        btnBird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction transaction = manager.beginTransaction();
                if (v.getId() == R.id.btn_bird) {
                    transaction.replace(R.id.container, new SpeciesInfoFragment()).commit();
                }
                transaction.addToBackStack(null);
            }
        });

        return view;
    }

}