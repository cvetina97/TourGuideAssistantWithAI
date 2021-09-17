package com.fmi.tourguideassistant.model;

import org.springframework.stereotype.Component;

@Component
public class Type {
    private String id;
    private String name;
    private String landmarkCategoryId;

    public String getLandmarkCategoryId() {
        return landmarkCategoryId;
    }

    public void setLandmarkCategoryId(String landmarkCategoryId) {
        this.landmarkCategoryId = landmarkCategoryId;
    }

    public Type(String id, String name, String landmarkCategoryId) {
        this.id = id;
        this.name = name;
        this.landmarkCategoryId = landmarkCategoryId;
    }

    public Type() {};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
