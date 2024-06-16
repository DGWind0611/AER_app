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
    private SpeciesType speciesType;
    private String speciesCommonName;
    private SpeciesNative speciesNative;
    private SpeciesCons speciesConservation;
    private String speciesDescription;


    // 空的構造函數是必需的
    public Species() {}

    public Species(int speciesId, int speciesPicId, String speciesName, SpeciesType speciesType,String speciesCommonName,SpeciesNative speciesNative ,SpeciesCons speciesConservation ,String speciesDescription) {
        this.speciesId = speciesId;
        this.speciesPicId = speciesPicId;
        this.speciesName = speciesName;
        this.speciesType = speciesType;
        this.speciesCommonName = speciesCommonName;
        this.speciesNative = speciesNative;
        this.speciesConservation = speciesConservation;
        this.speciesDescription = speciesDescription;
    }

    // Getters and Setters

    public int getSpeciesId() {
        return speciesId;
    }

    public int getSpeciesPicId() {
        return speciesPicId;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public SpeciesType getSpeciesType() {
        return speciesType;
    }

    public String getSpeciesCommonName() {
        return speciesCommonName;
    }

    public SpeciesNative getSpeciesNative() {
        return speciesNative;
    }

    public SpeciesCons getSpeciesConservation() {
        return speciesConservation;
    }

    public String getSpeciesDescription() {
        return speciesDescription;
    }
}
enum SpeciesType {
    BIRDS,
    MAMMALS,
    REPTILES,
    AMPHIBIANS
}

enum SpeciesNative {
    NATIVE,
    NONNATIVE,
    INVASIVE,
    UNKNOWN;
    public String getNativeString() {
        switch (this) {
            case NATIVE:
                return "原生物種";
            case NONNATIVE:
                return "非原生物種";
            case INVASIVE:
                return "外來侵入種";
            default:
                return "";
        }
    }
}

enum SpeciesCons {
    NORMAL,
    PROTECTED,
    EXTINCT;
    public String getConsString() {
        switch (this) {
            case NORMAL:
                return "一般類";
            case PROTECTED:
                return "保育類";
            case EXTINCT:
                return "已滅絕";
            default:
                return "";
        }
    }
}