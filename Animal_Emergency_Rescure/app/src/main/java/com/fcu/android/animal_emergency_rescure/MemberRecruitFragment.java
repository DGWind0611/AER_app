package com.fcu.android.animal_emergency_rescure;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class MemberRecruitFragment extends Fragment {

    private GridView gvMember;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_member_recruit, container, false);

        gvMember = view.findViewById(R.id.gv_member);

        // 建立志工資料
        Member member1 = new Member(R.drawable.member01, "https://web.hotac.org.tw/local.php?id=1440");
        Member member2 = new Member(R.drawable.member02, "https://web.hotac.org.tw/act_join.php?id=252");
        Member member3 = new Member(R.drawable.member03, "https://www.flomisup.com/blank-9/Cleanbeachsighup");
        Member member4 = new Member(R.drawable.member04, "https://www.igiving.org.tw/npo/volunteer_ct?id=40");
        // 加入志工資料
        List<Member> member = new ArrayList<>();
        member.add(member1);
        member.add(member2);
        member.add(member3);
        member.add(member4);
        // 建立適配器
        MemberRecruitAdapter memberRecruitAdapter = new MemberRecruitAdapter(getContext(), member);
        gvMember.setAdapter(memberRecruitAdapter);

        return view;
    }
}