package com.fmi.tourguideassistant.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Route {
    private ArrayList<Landmark> landmarks;

    public ArrayList<Landmark> getLandmarks() {
        return landmarks;
    }

    public void setLandmarks(ArrayList<Landmark> landmarks) {
        this.landmarks = landmarks;
    }

    public void addLandmark(Landmark landmark){
        this.landmarks.add(landmark);
    }

    public void removeLandmark(Landmark landmark){
        this.landmarks.remove(landmark);
    }

    public Route(ArrayList<Landmark> landmarks) {
        this.landmarks = landmarks;
    }
}
