package com.fcu.android.animal_emergency_rescure;

public class Agency {
    public String name;
    public String url;
    public String phoneNumber;
    public Location location;

    public static class Location {
        public double latitude;
        public double longitude;

        public Location(Double latitude, Double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public Location() {
        }
    }

    public Agency() {
    }

    public Agency(String name, String url, String phoneNumber, Location location) {
        this.name = name;
        this.url = url;
        this.phoneNumber = phoneNumber;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Location getLocation() {
        return location;
    }

}
