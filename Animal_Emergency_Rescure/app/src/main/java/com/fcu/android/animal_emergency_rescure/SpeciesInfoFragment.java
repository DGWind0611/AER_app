package com.fcu.android.animal_emergency_rescure;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class SpeciesInfoFragment extends Fragment {

    FragmentManager manager;
    private Spinner spCity;

    private ListView lvAnimals;
    //private List<Animal> animalList;
    private List<Species> animalList;
    private AnimalAdapter animalAdapter;

    private ImageButton btnCloseFilter;
    private final String[] citys = {"台北市", "新北市", "桃園市", "台中市", "台南市", "高雄市", "基隆市", "新竹市", "嘉義市", "新竹縣", "苗栗縣", "彰化縣", "南投縣", "雲林縣", "嘉義縣", "屏東縣", "宜蘭縣", "花蓮縣", "台東縣", "澎湖縣", "金門縣", "連江縣"};

    private String cityStr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_species_info, container, false);

        btnCloseFilter = view.findViewById(R.id.btn_close_filter);
        lvAnimals = view.findViewById(R.id.lv_animals);
        animalList = new ArrayList<>();

        //animalList.add(new Animal("臺灣山鷓鴣", "Taiwan Partridge", "留鳥、普遍", "臺灣特有種", "三級保育類", "深山竹雞", "紅腳仔", "紅腳竹雞", R.drawable.account));
        //animalList.add(new Animal("斯氏繡眼", "Swinhoe's White-eye", "留鳥", "體綠，眼周白色", "", "綠繡眼", "暗綠繡眼鳥青啼仔", "", R.drawable.account));

        Species bird_1 = new Species(1, R.drawable.s01, "斯氏繡眼", SpeciesType.BIRDS,"綠繡眼",SpeciesNative.NATIVE,SpeciesCons.NORMAL, "留鳥。棲息於樹林地帶以昆蟲、植物果實為主食。為平地、都市常見種類，出現於闊葉林、果園、公園、樹林。主要食物為果實、昆蟲。海拔分布於0至2000公尺。");
        Species bird_2 = new Species(2, R.drawable.s02, "麻雀", SpeciesType.BIRDS,"樹麻雀",SpeciesNative.NATIVE,SpeciesCons.NORMAL, "留鳥。嘴三角錐型，常在地面上靈活跳躍，多半出現於人類聚落或開墾地附近，為最常見的種類之一。出現於公園、樹林、稻田、果園、草生地。主要食物為種子、果實。海拔分布於0至600公尺。");
        Species bird_3 = new Species(3, R.drawable.s03, "臺灣藍鵲", SpeciesType.BIRDS,"紅嘴山鵲",SpeciesNative.NATIVE,SpeciesCons.PROTECTED, "留鳥。食性雜食性，警覺性高，主要棲息於樹林中，築巢於高枝上，雛鳥為晚熟性。出現於闊葉林。主要食物為種子、果實、昆蟲。海拔分布於100至1200公尺。");
        Species bird_4 = new Species(4, R.drawable.s04, "黑面琵鷺", SpeciesType.BIRDS,"黑臉琵鷺",SpeciesNative.NONNATIVE,SpeciesCons.NORMAL, "冬候鳥。常小群出現於海岸附近的沙洲及淺灘。多於黃昏及夜間覓食，白天休息停棲。覓食時，以扁平的匙狀嘴喙於淺水中左右撈動。主要食物為魚類、昆蟲、兩生類。海拔分布於0至50公尺。");
        Species bird_5 = new Species(4, R.drawable.s05, "喜鵲", SpeciesType.BIRDS,"無",SpeciesNative.INVASIVE,SpeciesCons.NORMAL, "留鳥。食性雜食性，警覺性高，主要棲息於樹林中，築巢於高枝上，雛鳥為晚熟性。出現於闊葉林、草生地。主要食物為種子、果實、昆蟲。海拔分布於0至600公尺。");
        Species bird_6 = new Species(6, R.drawable.s06, "黑長尾雉", SpeciesType.BIRDS,"帝雉",SpeciesNative.NATIVE,SpeciesCons.PROTECTED, "留鳥。飛行能力不佳。食性以植物種子、嫩葉、漿果及土中小蟲為食，機警羞怯，於晨昏覓食。慣常棲息於樹林底層及交界，於地面築巢，雛鳥為早熟性。出現於闊葉林、灌叢、針葉林。海拔分布於1800至3900公尺。");
        Species bird_7 = new Species(7, R.drawable.s07, "五色鳥", SpeciesType.BIRDS,"台灣擬啄木",SpeciesNative.NATIVE,SpeciesCons.NORMAL, "留鳥。出現於闊葉林，也常於人類住家附近公園出現。主要食物為果實、昆蟲。習性似啄木鳥，會以嘴喙在樹幹上挖洞築巢。海拔分布於0至2800公尺。");

        animalList.add(bird_1);
        animalList.add(bird_2);
        animalList.add(bird_3);
        animalList.add(bird_4);
        animalList.add(bird_5);
        animalList.add(bird_6);
        animalList.add(bird_7);

        animalAdapter = new AnimalAdapter(getContext(), animalList);
        lvAnimals.setAdapter(animalAdapter);

        lvAnimals.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Animal selectedAnimal = animalList.get(position);
                Species selectedSpecies = animalList.get(position);

                Intent intent = new Intent(getActivity(), SecureAgencyInfo.class);
//              intent.putExtra("infoContent", selectedAnimal.getScientificName());
                intent.putExtra("speciesName", selectedSpecies.getSpeciesName());
                startActivity(intent);
            }
        });
        btnCloseFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConstraintLayout filter = view.findViewById(R.id.filter_layout);
                filter.setVisibility(View.GONE);
            }
        });

        return view;

        //        // Get the outer layout
//        RelativeLayout outerLayout = findViewById(R.layout.activity_main);
//
//        // Inflate the inner layout
//        View innerLayout = getLayoutInflater().inflate(R.layout.filter, null);
//
//        // Add the inner layout to the outer layout
//        outerLayout.addView(innerLayout);


//        //city相關篩選spinner
//        spCity = spCity.findViewById(R.id.sp_city);
//        ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(this, R.layout.dropdown_city, citys);
//
//        adapterCity.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spCity.setAdapter(adapterCity);
//        AdapterView.OnItemSelectedListener spCityListener = new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                cityStr = citys[position];
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                cityStr=" ";
//            }
//        };
//        spCity.setOnItemSelectedListener(spCityListener);
    }
}