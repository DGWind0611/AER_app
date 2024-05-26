package com.fcu.android.animal_emergency_rescure;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;

import java.util.List;

public class MemberRecruitAdapter extends BaseAdapter {

    private Context context;
    private List<Member> lsMembers;

    public MemberRecruitAdapter(Context context, List<Member> lsMembers) {
        this.context = context;
        this.lsMembers = lsMembers;
    }

    @Override
    public int getCount() {
        return lsMembers.size();
    }

    @Override
    public Object getItem(int i) {
        return lsMembers.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.member_recruit_card, viewGroup, false);
        }

        ImageView imMemberRecruit = view.findViewById(R.id.iv_member_recruit);
        Button btnJoin = view.findViewById(R.id.btn_join);

        Member member = lsMembers.get(i);
        imMemberRecruit.setImageResource(member.getMemberRecruit());

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(member.getUrl()));
                context.startActivity(intent);
            }
        };
        btnJoin.setOnClickListener(listener);

        return view;
    }
}
