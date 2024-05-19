package com.fcu.android.animal_emergency_rescure;

public class Species {
    private int speciesPicId;
    private String speciesName;

    public Species(int speciesPicId, String speciesName) {
        this.speciesPicId = speciesPicId;
        this.speciesName = speciesName;
    }

    public int getSpeciesPicId() {
        return speciesPicId;
    }

    public String getSpeciesName() {
        return speciesName;
    }
}
