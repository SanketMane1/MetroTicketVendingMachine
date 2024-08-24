package com.metro.model;

import java.util.List;

public class Route {
    private String routeName;
    private List<MetroStation> stations;

    public Route(String routeName, List<MetroStation> stations) {
        this.routeName = routeName;
        this.stations = stations;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public List<MetroStation> getStations() {
        return stations;
    }

    public void setStations(List<MetroStation> stations) {
        this.stations = stations;
    }

}
