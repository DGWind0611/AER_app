package com.fcu.android.animal_emergency_rescure;

public class Member {
    private int memberRecruit;
    private String url;

    public Member(int memberRecruit, String url) {
        this.memberRecruit = memberRecruit;
        this.url = url;
    }

    public int getMemberRecruit() {
        return memberRecruit;
    }

    public String getUrl() {
        return url;
    }
}
