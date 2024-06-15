package com.fcu.android.animal_emergency_rescure;

//public class Species {
//    private final int speciesId;
//    private final int speciesPicId;
//    private final String speciesName;
//    private final SpeciesType speciesType;
//    private final String speciesDescription;
//
//    public Species(int speciesId, int speciesPicId, String speciesName, SpeciesType speciesType, String speciesDescription) {
//        this.speciesId = speciesId;
//        this.speciesPicId = speciesPicId;
//        this.speciesName = speciesName;
//        this.speciesType = speciesType;
//        this.speciesDescription = speciesDescription;
//    }
//
//    public int getSpeciesId() {
//        return speciesId;
//    }
//
//    public int getSpeciesPicId() {
//        return speciesPicId;
//    }
//
//    public String getSpeciesName() {
//        return speciesName;
//    }
//
//    public SpeciesType getSpeciesType() { return speciesType; }
//
//    public String getSpeciesDescription() { return speciesDescription; }
//}
public class Species {
    private int speciesId;
    private int speciesPicId;
    private String speciesName;
    private String speciesDescription;
    private SpeciesType speciesType;

    // 空的構造函數是必需的
    public Species() {}

    public Species(int speciesId, int speciesPicId, String speciesName, SpeciesType speciesType, String speciesDescription) {
        this.speciesId = speciesId;
        this.speciesPicId = speciesPicId;
        this.speciesName = speciesName;
        this.speciesType = speciesType;
        this.speciesDescription = speciesDescription;
    }

    // Getters and Setters
    public int getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(int speciesId) {
        this.speciesId = speciesId;
    }

    public int getSpeciesPicId() {
        return speciesPicId;
    }

    public void setSpeciesPicId(int speciesPicId) {
        this.speciesPicId = speciesPicId;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public void setSpeciesName(String speciesName) {
        this.speciesName = speciesName;
    }

    public SpeciesType getSpeciesType() {
        return speciesType;
    }

    public void setSpeciesType(SpeciesType speciesType) {
        this.speciesType = speciesType;
    }

    public String getSpeciesDescription() {
        return speciesDescription;
    }

    public void setSpeciesDescription(String speciesDescription) {
        this.speciesDescription = speciesDescription;
    }
}

enum SpeciesType {
    BIRDS,
    MAMMALS,
    REPTILES,
    AMPHIBIANS
}
