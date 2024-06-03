package com.fcu.android.animal_emergency_rescure;

public class Species {
    private final int speciesId;
    private final int speciesPicId;
    private final String speciesName;

    public Species(int speciesId, int speciesPicId, String speciesName) {
        this.speciesId = speciesId;
        this.speciesPicId = speciesPicId;
        this.speciesName = speciesName;
    }

    public int getSpeciesId() {
        return speciesId;
    }

    public int getSpeciesPicId() {
        return speciesPicId;
    }

    public String getSpeciesName() {
        return speciesName;
    }
}
