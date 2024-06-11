package com.fcu.android.animal_emergency_rescure;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FavoriteManager {

    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    public FavoriteManager() {
        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("users");
    }

    public void addFavorite(String speciesId) {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String email = currentUser.getEmail();
            if (email != null) {
                databaseReference.child(encodeEmail(email)).child("favorites").child(speciesId).setValue(true);
            }
        }
    }

    public void removeFavorite(String speciesId) {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            String email = currentUser.getEmail();
            if (email != null) {
                databaseReference.child(encodeEmail(email)).child("favorites").child(speciesId).removeValue();
            }
        }
    }

    private String encodeEmail(String email) {
        return email.replace(".", ",");
    }
}
