package com.fcu.android.animal_emergency_rescure;

public class Informations {
    private final int infoCardId;
    private final String infoCardTitle;
    private final String infoCardLink;

    public Informations(int infoCardId, String infoCardTitle, String infoCardLink) {
        this.infoCardId = infoCardId;
        this.infoCardTitle = infoCardTitle;
        this.infoCardLink = infoCardLink;
    }

    public int getInfoCardId() {
        return infoCardId;
    }
    public String getInfoCardTitle() {
        return infoCardTitle;
    }
    public String getInfoCardLink() {
        return infoCardLink;
    }
}
