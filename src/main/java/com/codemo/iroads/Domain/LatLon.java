package com.codemo.iroads.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class LatLon {
    double lat;
    double lon;

    public LatLon(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }
}
