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
    private CardView btnMam;
    private CardView btnRept;
    private CardView btnAmphi;
    private FragmentManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_secure, container, false);

        btnBird = view.findViewById(R.id.btn_bird);
        btnMam = view.findViewById(R.id.btn_mammal);
        btnRept = view.findViewById(R.id.btn_reptile);
        btnAmphi = view.findViewById(R.id.btn_amphibian);
        manager = getParentFragmentManager();

        btnBird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_bird) {
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.container, new SpeciesInfoFragment(SpeciesType.BIRDS));
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });
        btnMam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_mammal) {
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.container, new SpeciesInfoFragment(SpeciesType.MAMMALS));
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });
        btnRept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_reptile) {
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.container, new SpeciesInfoFragment(SpeciesType.REPTILES));
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });
        btnAmphi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.btn_amphibian) {
                    FragmentTransaction transaction = manager.beginTransaction();
                    transaction.replace(R.id.container, new SpeciesInfoFragment(SpeciesType.AMPHIBIANS));
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });

        return view;
    }

}