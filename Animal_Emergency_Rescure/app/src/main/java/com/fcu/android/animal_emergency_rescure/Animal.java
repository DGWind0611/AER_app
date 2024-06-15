package com.fcu.android.animal_emergency_rescure;

public class Animal {
    private String scientificName;
    private String engScientificName;
    private String status;
    private String endemic;
    private String conservation;
    private String commonName1;
    private String commonName2;
    private String commonName3;
    private int imageResId;

    public Animal() {
    }

    public Animal(String scientificName, String engScientificName, String status, String endemic, String conservation, String commonName1, String commonName2, String commonName3, int imageResId) {
        this.scientificName = scientificName;
        this.engScientificName = engScientificName;
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

    public String getEngScientificName() { return engScientificName; }

    public String getStatus() {
        return status;
    }

    public String getEndemic() {
        return endemic;
    }

    public String getConservation() {
        return conservation;
    }

    public String getCommonName1() {
        return commonName1;
    }

    public String getCommonName2() {
        return commonName2;
    }

    public String getCommonName3() {
        return commonName3;
    }

    public int getImageResId() {
        return imageResId;
    }
}
