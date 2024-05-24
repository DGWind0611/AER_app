package com.fcu.android.animal_emergency_rescure;

public class Cash {
    private int cashRecruit;
    private String url;

    public Cash(int cashRecruit, String url) {
        this.cashRecruit = cashRecruit;
        this.url = url;
    }

    public int getCashRecruit() {
        return cashRecruit;
    }

    public String getUrl() {
        return url;
    }
}
