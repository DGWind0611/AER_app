package com.fcu.android.animal_emergency_rescure;

public class Animal {
    private String scientificName;
    private String status;
    private String endemic;
    private String conservation;
    private String commonName1;
    private String commonName2;
    private String commonName3;
    private int imageResId;

    public Animal() {
    }

    public Animal(String scientificName, String status, String endemic, String conservation, String commonName1, String commonName2, String commonName3, int imageResId) {
        this.scientificName = scientificName;
        this.status = status;
        this.endemic = endemic;
        this.conservation = conservation;
        this.commonName1 = commonName1;
        this.commonName2 = commonName2;
        this.commonName3 = commonName3;
        this.imageResId = imageResId;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEndemic() {
        return endemic;
    }

    public void setEndemic(String endemic) {
        this.endemic = endemic;
    }

    public String getConservation() {
        return conservation;
    }

    public void setConservation(String conservation) {
        this.conservation = conservation;
    }

    public String getCommonName1() {
        return commonName1;
    }

    public void setCommonName1(String commonName1) {
        this.commonName1 = commonName1;
    }

    public String getCommonName2() {
        return commonName2;
    }

    public void setCommonName2(String commonName2) {
        this.commonName2 = commonName2;
    }

    public String getCommonName3() {
        return commonName3;
    }

    public void setCommonName3(String commonName3) {
        this.commonName3 = commonName3;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }
}
