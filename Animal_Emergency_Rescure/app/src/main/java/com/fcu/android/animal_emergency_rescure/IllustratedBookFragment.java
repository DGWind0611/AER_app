package com.fcu.android.animal_emergency_rescure;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.ScrollView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class IllustratedBookFragment extends Fragment {
    private GridView gvBirds;
    private GridView gvMams;
    private GridView gvRepts;
    private GridView gvAmphis;
    private ProgressBar progressBar;
    private ScrollView contentScrollView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_illustrated_book, container, false);

        gvBirds = view.findViewById(R.id.gv_bird);
        gvMams = view.findViewById(R.id.gv_mam);
        gvRepts = view.findViewById(R.id.gv_rept);
        gvAmphis = view.findViewById(R.id.gv_amphi);

        progressBar = view.findViewById(R.id.progress_bar);
        contentScrollView = view.findViewById(R.id.sv_species);

        progressBar.setVisibility(View.VISIBLE);
        contentScrollView.setVisibility(View.GONE);

        uploadSpeciesData();

        loadSpeciesData();

        return view;
    }

    private void uploadSpeciesData() { // 程式碼上傳物種至firebase資料庫
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("species");

        // 創建 Species 物件
        //Species bird_1 = new Species(1, R.drawable.poke1, "鳥0001", SpeciesType.BIRDS, "詳細介紹0001");
        Species bird_1 = new Species(1, R.drawable.s01, "斯氏繡眼", SpeciesType.BIRDS,"綠繡眼",SpeciesNative.NATIVE,SpeciesCons.NORMAL, "留鳥。棲息於樹林地帶以昆蟲、植物果實為主食。為平地、都市常見種類，出現於闊葉林、果園、公園、樹林。主要食物為果實、昆蟲。海拔分布於0至2000公尺。");
        Species bird_2 = new Species(2, R.drawable.s02, "麻雀", SpeciesType.BIRDS,"樹麻雀",SpeciesNative.NATIVE,SpeciesCons.NORMAL, "留鳥。嘴三角錐型，常在地面上靈活跳躍，多半出現於人類聚落或開墾地附近，為最常見的種類之一。出現於公園、樹林、稻田、果園、草生地。主要食物為種子、果實。海拔分布於0至600公尺。");
        Species bird_3 = new Species(3, R.drawable.s03, "臺灣藍鵲", SpeciesType.BIRDS,"紅嘴山鵲",SpeciesNative.NATIVE,SpeciesCons.PROTECTED, "留鳥。食性雜食性，警覺性高，主要棲息於樹林中，築巢於高枝上，雛鳥為晚熟性。出現於闊葉林。主要食物為種子、果實、昆蟲。海拔分布於100至1200公尺。");
        Species bird_4 = new Species(4, R.drawable.s04, "黑面琵鷺", SpeciesType.BIRDS,"黑臉琵鷺",SpeciesNative.NONNATIVE,SpeciesCons.NORMAL, "冬候鳥。常小群出現於海岸附近的沙洲及淺灘。多於黃昏及夜間覓食，白天休息停棲。覓食時，以扁平的匙狀嘴喙於淺水中左右撈動。主要食物為魚類、昆蟲、兩生類。海拔分布於0至50公尺。");
        Species bird_5 = new Species(5, R.drawable.s05, "喜鵲", SpeciesType.BIRDS,"無",SpeciesNative.INVASIVE,SpeciesCons.NORMAL, "留鳥。食性雜食性，警覺性高，主要棲息於樹林中，築巢於高枝上，雛鳥為晚熟性。出現於闊葉林、草生地。主要食物為種子、果實、昆蟲。海拔分布於0至600公尺。");
        Species bird_6 = new Species(6, R.drawable.s06, "黑長尾雉", SpeciesType.BIRDS,"帝雉",SpeciesNative.NATIVE,SpeciesCons.PROTECTED, "留鳥。飛行能力不佳。食性以植物種子、嫩葉、漿果及土中小蟲為食，機警羞怯，於晨昏覓食。慣常棲息於樹林底層及交界，於地面築巢，雛鳥為早熟性。出現於闊葉林、灌叢、針葉林。海拔分布於1800至3900公尺。");
        Species bird_7 = new Species(7, R.drawable.s07, "五色鳥", SpeciesType.BIRDS,"台灣擬啄木",SpeciesNative.NATIVE,SpeciesCons.NORMAL, "留鳥。出現於闊葉林，也常於人類住家附近公園出現。主要食物為果實、昆蟲。習性似啄木鳥，會以嘴喙在樹幹上挖洞築巢。海拔分布於0至2800公尺。");

        Species mam_1 = new Species(8, R.drawable.s08, "瓶鼻海豚", SpeciesType.MAMMALS,"寬吻海豚",SpeciesNative.NATIVE,SpeciesCons.PROTECTED, "以魚類或頭足類為食，偶而吃甲殼類。沿岸型的瓶鼻海豚一群在10隻以內；外海型一群通常為25隻以內，最多可達500隻。沿岸型的瓶鼻海豚出沒於河流、潟湖與開放海域，外海型則常見於離島周遭與開放性海域。台灣在所有海域皆有發現，主要位於本島東部與台灣海峽。");
        Species mam_2 = new Species(9, R.drawable.s09, "台灣獼猴", SpeciesType.MAMMALS,"無",SpeciesNative.NATIVE,SpeciesCons.NORMAL, "台灣的原生種，分布於由海平面至海拔三千公尺以下的地區。例如：陽明山、台北市天母水管路古道、台東縣東河鄉、屏東縣滿州鄉、雲林縣林內鄉、玉山國家公園塔塔加、高雄市鼓山區的壽山國家自然公園、台南市南化區的烏山風景區、澎湖縣四腳嶼。");
        Species mam_3 = new Species(10, R.drawable.s10, "台灣黑熊", SpeciesType.MAMMALS,"綠繡眼",SpeciesNative.NATIVE,SpeciesCons.PROTECTED, "台灣特有亞種，為台灣最大型陸生動物，數量極少。雜食性，而且以植物為主，芽、葉、根、莖、果實等部位都會食用。分布於海拔1000~3000公尺山區森林，但以中海拔為主。大多數不會冬眠。晨昏時活動。");
        Species mam_4 = new Species(11, R.drawable.s11, "穿山甲", SpeciesType.MAMMALS,"中國鯪鯉",SpeciesNative.NATIVE,SpeciesCons.PROTECTED, "棲息於中低海拔森林，以海拔300~500公尺較常出現。善挖掘，夜行性，白天休憩於洞穴中，夜晚覓食。蟲食性，食物以白蟻、螞蟻為主，以長舌黏取吞食。因為獵捕及棲地破壞，目前已極為稀少，全台灣僅零星記錄。");

        Species rept_1 = new Species(12, R.drawable.s12, "綠蠵龜", SpeciesType.REPTILES,"海龜",SpeciesNative.NATIVE,SpeciesCons.PROTECTED, "棲息地以海洋為主。幼年期肉食性較明顯，長大後轉變為雜食性，為海龜中唯一攝食較多海藻的種類。分布於各大洋熱帶及亞熱帶。早期臺灣北部、東部、南部及離島都有上岸產卵的記錄，但近年極少於臺灣本島海岸產卵，僅澎湖、蘭嶼等離島仍有少數上岸產卵記錄。");
        Species rept_2 = new Species(13, R.drawable.s13, "雪山草蜥", SpeciesType.REPTILES,"蛇舅母",SpeciesNative.NATIVE,SpeciesCons.NORMAL, "棲息地以針葉林、草原、墾地為主。以昆蟲及其他小型無脊椎動物為食。尾細長，可用來平衡及纏繞攀附於植物上，因此較少有自割尾部的情形。侷限分布於中部偏北之雪山山脈山區，海拔約1800~3000公尺。");
        Species rept_3 = new Species(14, R.drawable.s14, "過山刀", SpeciesType.REPTILES,"烏梢蛇",SpeciesNative.NATIVE,SpeciesCons.NORMAL, "大型蛇類。棲息地以闊葉林、混生林、草原、墾地、溪流、湖沼為主。日行性，以魚、蛙、蜥蜴、蛇、鳥及鼠類為食。常見於全島海拔500公尺以下地區。");
        Species rept_4 = new Species(15, R.drawable.s15, "龜殼花", SpeciesType.REPTILES,"烙鐵頭",SpeciesNative.NATIVE,SpeciesCons.NORMAL, "常見的毒蛇種類。棲息地以闊葉林、混生林、草原、墾地、溪流、溝渠、建築物為主。毒性強，攻擊性亦強。夜行性，以蛙、蜥蜴、鳥、鼠類及蝙蝠為食，常因獵捕鼠類闖入民宅。分布於全島海拔1000公尺以下地區。");
        Species rept_5 = new Species(16, R.drawable.s16, "赤尾青竹絲", SpeciesType.REPTILES,"赤尾鮐",SpeciesNative.NATIVE,SpeciesCons.NORMAL, "常見的毒蛇種類。棲息於森林、開墾地、竹林附近，喜歡在池塘溝渠旁的樹上活動。胎生。毒性強，攻擊性強。夜行性，以坐等伏擊的方式獵捕經過的動物，以蛙、蜥蜴、鳥及小型哺乳類動物為食。於全島及蘭嶼海拔1500公尺以下地區皆有分布。");

        Species amphi_1 = new Species(17, R.drawable.s17, "黑眶蟾蜍", SpeciesType.AMPHIBIANS,"蛤蟆",SpeciesNative.NATIVE,SpeciesCons.NORMAL, "棲息於住家附近、農地、低海拔闊葉林、濕地。喜好公園水池、水田、水溝、池塘、森林底層潮濕處等環境。以靜水池、緩水域、靜水域為產卵場。卵成串念珠狀。蝌蚪主食藻類、落葉；成蛙主食為小型無脊椎動物。廣泛分布在海拔500公尺以下，綠島與蘭嶼也有分布。");
        Species amphi_2 = new Species(18, R.drawable.s18, "莫氏樹蛙", SpeciesType.AMPHIBIANS,"雨怪",SpeciesNative.NATIVE,SpeciesCons.NORMAL, "棲息於中低海拔闊葉林、次生林、混生林、農地。喜好山區的人工蓄水池、溝渠、森林底層潮濕處等水域環境。將卵泡產在植物體上、植物基部泥土、水池壁上。泡沫狀。蝌蚪主食為藻類、落葉；成蛙主食為小型無脊椎動物。廣泛分布在海拔2500公尺以下。");
        Species amphi_3 = new Species(19, R.drawable.s19, "台北樹蛙", SpeciesType.AMPHIBIANS,"無",SpeciesNative.NATIVE,SpeciesCons.PROTECTED, "棲息於低海拔闊葉林及森林邊緣、農地。偏好人工蓄水池、森林內潮濕處、淡水沼澤、竹林及灌木叢等環境。卵泡產在植物體上、植物基部泥土。泡沫狀。蝌蚪主食為藻類、落葉；成蛙主食為小型無脊椎動物。區域分布於臺北縣、苗栗縣、宜蘭縣及南投縣。");
        Species amphi_4 = new Species(20, R.drawable.s20, "虎皮蛙", SpeciesType.AMPHIBIANS,"田雞",SpeciesNative.NATIVE,SpeciesCons.NORMAL, "棲息於低海拔水田、草澤、旱田、水池與甘蔗田等環境。以靜水池表面為產卵場，卵單顆浮水面。蝌蚪主食為藻類、落葉；成蛙主食為小型無脊椎動物。過去廣泛分布在低海拔地區，現以東部與中南部較常見。");
        Species amphi_5 = new Species(21, R.drawable.s21, "台北赤蛙", SpeciesType.AMPHIBIANS,"無",SpeciesNative.NATIVE,SpeciesCons.PROTECTED, "棲息於山區的草澤或湖泊、濕地、茶園、水田。以靜水域為產卵場。卵成團狀聚集。蝌蚪主食為藻類、落葉；成蛙主食為小型無脊椎動物。侷限性分布在北部與南部的低海拔地區，數量已十分稀少。");
        Species amphi_6 = new Species(22, R.drawable.s22, "小雨蛙", SpeciesType.AMPHIBIANS,"飾紋姬蛙",SpeciesNative.NATIVE,SpeciesCons.NORMAL, "棲息於低海拔森林、闊葉林底層、草地、水田、水溝、山區的人工蓄水池與旱田。以靜水池表面為產卵場。卵塊外包膠膜，成片狀。蝌蚪採濾食，取食浮游生物；成蛙主食為螞蟻及白蟻。廣泛分布在低海拔地區，北部較常見。");
        Species amphi_7 = new Species(23, R.drawable.s23, "亞洲錦蛙", SpeciesType.AMPHIBIANS,"花狹口蛙",SpeciesNative.INVASIVE,SpeciesCons.NORMAL, "外來種，棲息於旱地、水溝、泥土中或樹洞內。繁殖力強，且因為皮膚會分泌毒性，少有天敵，未來恐對其它本地蛙類生存造成威脅。蝌蚪採濾食，取食浮游生物；成蛙主食為螞蟻及白蟻。目前分布於高屏、臺南，可能是隨貨櫃、原木擴散到臺灣的。");
        Species amphi_8 = new Species(24, R.drawable.s24, "台灣山椒魚", SpeciesType.AMPHIBIANS,"台灣小鯢",SpeciesNative.NATIVE,SpeciesCons.PROTECTED, "棲息於高海拔針葉林或闊葉林底層、溪流附近。產卵於沙地中。卵鞝包被卵。以小型無脊椎為食。侷限性分布在合歡山與能高山的中高海拔地區的原始森林中，數量十分稀少。");
        Species amphi_9 = new Species(25, R.drawable.s25, "南湖山椒魚", SpeciesType.AMPHIBIANS,"南湖小鯢",SpeciesNative.NATIVE,SpeciesCons.PROTECTED, "棲息於高海拔針葉林或闊葉林底層、溪流附近。多數習性不詳。以小型無脊椎為食。侷限性分布在中高海拔地區的原始森林中。");
        Species amphi_10 = new Species(26, R.drawable.s26, "觀霧山椒魚", SpeciesType.AMPHIBIANS,"觀霧小鯢",SpeciesNative.NATIVE,SpeciesCons.PROTECTED, "棲息於高海拔針葉林或闊葉林底層、溪流附近。卵為膠質卵囊一對。以小型無脊椎為食。侷限性分布在中高海拔地區的原始森林中。");

        // 將 Species 物件上傳到 Firebase
        //myRef.child(String.valueOf(bird_1.getSpeciesId())).setValue(bird_1);
        myRef.child(String.valueOf(bird_1.getSpeciesId())).setValue(bird_1);
        myRef.child(String.valueOf(bird_2.getSpeciesId())).setValue(bird_2);
        myRef.child(String.valueOf(bird_3.getSpeciesId())).setValue(bird_3);
        myRef.child(String.valueOf(bird_4.getSpeciesId())).setValue(bird_4);
        myRef.child(String.valueOf(bird_5.getSpeciesId())).setValue(bird_5);
        myRef.child(String.valueOf(bird_6.getSpeciesId())).setValue(bird_6);
        myRef.child(String.valueOf(bird_7.getSpeciesId())).setValue(bird_7);

        myRef.child(String.valueOf(mam_1.getSpeciesId())).setValue(mam_1);
        myRef.child(String.valueOf(mam_2.getSpeciesId())).setValue(mam_2);
        myRef.child(String.valueOf(mam_3.getSpeciesId())).setValue(mam_3);
        myRef.child(String.valueOf(mam_4.getSpeciesId())).setValue(mam_4);

        myRef.child(String.valueOf(rept_1.getSpeciesId())).setValue(rept_1);
        myRef.child(String.valueOf(rept_2.getSpeciesId())).setValue(rept_2);
        myRef.child(String.valueOf(rept_3.getSpeciesId())).setValue(rept_3);
        myRef.child(String.valueOf(rept_4.getSpeciesId())).setValue(rept_4);
        myRef.child(String.valueOf(rept_5.getSpeciesId())).setValue(rept_5);

        myRef.child(String.valueOf(amphi_1.getSpeciesId())).setValue(amphi_1);
        myRef.child(String.valueOf(amphi_2.getSpeciesId())).setValue(amphi_2);
        myRef.child(String.valueOf(amphi_3.getSpeciesId())).setValue(amphi_3);
        myRef.child(String.valueOf(amphi_4.getSpeciesId())).setValue(amphi_4);
        myRef.child(String.valueOf(amphi_5.getSpeciesId())).setValue(amphi_5);
        myRef.child(String.valueOf(amphi_6.getSpeciesId())).setValue(amphi_6);
        myRef.child(String.valueOf(amphi_7.getSpeciesId())).setValue(amphi_7);
        myRef.child(String.valueOf(amphi_8.getSpeciesId())).setValue(amphi_8);
        myRef.child(String.valueOf(amphi_9.getSpeciesId())).setValue(amphi_9);
        myRef.child(String.valueOf(amphi_10.getSpeciesId())).setValue(amphi_10);
    }

    private void loadSpeciesData() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("species");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Species> birds = new ArrayList<>();
                List<Species> mams = new ArrayList<>();
                List<Species> repts = new ArrayList<>();
                List<Species> amphis = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Species species = snapshot.getValue(Species.class);

                    if (species != null) {
                        switch (species.getSpeciesType()) {
                            case BIRDS:
                                birds.add(species);
                                break;
                            case MAMMALS:
                                mams.add(species);
                                break;
                            case REPTILES:
                                repts.add(species);
                                break;
                            case AMPHIBIANS:
                                amphis.add(species);
                                break;
                        }
                    }
                }

                // 設置Adapter並更新UI
                IllustratedBookCardAdapter birdAdapter = new IllustratedBookCardAdapter(getContext(), birds);
                gvBirds.setAdapter(birdAdapter);

                IllustratedBookCardAdapter mamAdapter = new IllustratedBookCardAdapter(getContext(), mams);
                gvMams.setAdapter(mamAdapter);

                IllustratedBookCardAdapter reptAdapter = new IllustratedBookCardAdapter(getContext(), repts);
                gvRepts.setAdapter(reptAdapter);

                IllustratedBookCardAdapter amphiAdapter = new IllustratedBookCardAdapter(getContext(), amphis);
                gvAmphis.setAdapter(amphiAdapter);

                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        calculateAndSetGVsHeight(gvBirds, birdAdapter, 3, 60);
                        calculateAndSetGVsHeight(gvMams, mamAdapter, 3, 60);
                        calculateAndSetGVsHeight(gvRepts, reptAdapter, 3, 60);
                        calculateAndSetGVsHeight(gvAmphis, amphiAdapter, 3, 60);

                        progressBar.setVisibility(View.GONE); // 計算完高度後隱藏進度條
                        contentScrollView.setVisibility(View.VISIBLE);
                    }
                }, 500); // 延遲500毫秒
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // 處理錯誤
                progressBar.setVisibility(View.GONE);
                contentScrollView.setVisibility(View.VISIBLE);
            }
        });
    }

    public void calculateAndSetGVsHeight(GridView gridView, ListAdapter adapter, int itemsPerRow, int extraHeight) {
        // 如果adapter不為空，且有item
        if (adapter != null && adapter.getCount() > 0) {
            int totalHeight = 0;

            // 計算有幾行(最少一行)
            int rowCount = (adapter.getCount() + itemsPerRow - 1) / itemsPerRow;

            // 計算總高度(item高度*行數)
            for (int i = 0; i < rowCount; i++) {
                View listItem = adapter.getView(i, null, gridView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }
            totalHeight += rowCount * extraHeight; // 增加額外高度

            // 設置Gridview高度
            ViewGroup.LayoutParams params = gridView.getLayoutParams();
            params.height = totalHeight;
            gridView.setLayoutParams(params);
            gridView.requestLayout(); // 重新布局
        }
    }

    // 設置showFavorite的值
    public void setShowFavorite(boolean showFavorite) {
        IllustratedBookCardAdapter.showFavorite = showFavorite;
    }

    @Override
    public void onPause() {
        super.onPause();
        // 當user離開頁面時，將showFavorite設為false
        IllustratedBookCardAdapter.showFavorite = false;
    }
}