package com.fmi.tourguideassistant.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Landmark {
    private String id;

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getLandmarkCategoryId() {
        return landmarkCategoryId;
    }

    public void setLandmarkCategoryId(String landmarkCategoryId) {
        this.landmarkCategoryId = landmarkCategoryId;
    }

    public String getRegionId() {
        return regionId;
    }

    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    public Geopoint getLocation() {
        return location;
    }

    public void setLocation(Geopoint location) {
        this.location = location;
    }

    private String name;
    private String imagePath;
    private String description;
    private String typeId;
    private String landmarkCategoryId;
    private String regionId;
    private Geopoint location;


    public Landmark(String id, String name, String imagePath, String description, String typeId, String landmarkCategoryId, String regionId, Geopoint location) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.description = description;
        this.typeId = typeId;
        this.landmarkCategoryId = landmarkCategoryId;
        this.regionId = regionId;
        this.location = location;
    }

    public Landmark() {}
}
