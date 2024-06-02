package com.fcu.android.animal_emergency_rescure;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class InformationFragment extends Fragment {
    private GridView gvInfoCards;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_information, container, false);
        gvInfoCards = view.findViewById(R.id.gv_info_cards);

        Informations info_1 = new Informations(1,"發現穿山甲怎麼辦","https://www.tbri.gov.tw/Uploads/userfile/C2_5/2020-02-19_0204301341.pdf");
        Informations info_2 = new Informations(2,"如何分辨幼鳥和雛鳥","https://www.tbri.gov.tw/Uploads/userfile/C2_3/2019-09-19_1454392395.pdf");
        Informations info_3 = new Informations(3,"撿到鳥寶寶！怎麼辨","https://www.tbri.gov.tw/Uploads/userfile/C2_2/2019-09-19_1453537373.pdf");
        Informations info_4 = new Informations(4,"撿到夜鷹寶寶怎麼辦","https://www.tbri.gov.tw/Uploads/userfile/C2_4/2019-09-19_1455054195.pdf");
        Informations info_5 = new Informations(5,"黏鼠板救傷！","https://www.tbri.gov.tw/Uploads/userfile/C2_8/2020-02-19_1815467646.pdf");
        Informations info_6 = new Informations(6,"野生動物救傷手冊","https://www.tbri.gov.tw/Uploads/userfile/C2_6/2019-09-19_1456578398.pdf");

        List<Informations> infos = new ArrayList<>();
        infos.add(info_1);
        infos.add(info_2);
        infos.add(info_3);
        infos.add(info_4);
        infos.add(info_5);
        infos.add(info_6);

        InformationCardAdapter adapter = new InformationCardAdapter(getContext(), infos);
        gvInfoCards.setAdapter(adapter);

        return view;
    }
}