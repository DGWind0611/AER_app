package com.fcu.android.animal_emergency_rescure;

public class Species {
    private final int speciesId;
    private final int speciesPicId;
    private final String speciesName;
    private final String speciesDescription;

    public Species(int speciesId, int speciesPicId, String speciesName, String speciesDescription) {
        this.speciesId = speciesId;
        this.speciesPicId = speciesPicId;
        this.speciesName = speciesName;
        this.speciesDescription = speciesDescription;
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

    public String getSpeciesDescription() { return speciesDescription; }
}
