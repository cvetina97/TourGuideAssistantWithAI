package com.fmi.tourguideassistant.model;

import org.springframework.stereotype.Component;

@Component
public class Region {
    private String id;
    private String name;

    public Region(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Region() {}

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
