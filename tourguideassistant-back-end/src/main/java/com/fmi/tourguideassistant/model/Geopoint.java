package com.fmi.tourguideassistant.model;

import org.springframework.stereotype.Component;

@Component
public class Geopoint {
    private double latitude;
    private double longitude;

    public Geopoint(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Geopoint() {}

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
