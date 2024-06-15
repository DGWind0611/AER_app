package com.fcu.android.animal_emergency_rescure;

public class Shelter {
    public String name;
    public String url;
    public String phoneNumber;
    public Capacity capacity;
    public Location location;

    public String distance;

    public Shelter() {
    }

    public Shelter(String name, String url, String phoneNumber, Capacity capacity, Location location, String distance) {
        this.name = name;
        this.url = url;
        this.phoneNumber = phoneNumber;
        this.capacity = capacity;
        this.location = location;
        this.distance = distance;

    }

    public static class Capacity{
        public int current;
        public int max;

        public Capacity(Integer current, Integer max) {
            this.current = current;
            this.max = max;
        }

        public void capacity() {
        }
        public void capacity(int current, int max) {
            this.current = current;
            this.max = max;
        }

        public int getCurrent() {
            return current;
        }

        public int getMax() {
            return max;
        }
    }
    public static class Location {
        public double latitude;
        public double longitude;

        public Location() {
        }

        public Location(Double latitude, Double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }
    }
}
