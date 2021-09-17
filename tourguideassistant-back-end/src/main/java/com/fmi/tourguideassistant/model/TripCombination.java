package com.fmi.tourguideassistant.model;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class TripCombination {
    private ArrayList<Route> routes;

    public TripCombination(ArrayList<Route> routes) {
        this.routes = routes;
    }

    public ArrayList<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(ArrayList<Route> routes) {
        this.routes = routes;
    }

    public void addRoute(Route route){
        this.routes.add(route);
    }

    public void removeRoute(Route route){
        this.routes.remove(route);
    }
}
