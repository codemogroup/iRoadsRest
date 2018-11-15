package com.codemo.iroads.Domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class SegmentInfo {

    private List<LatLon> coordinates;

    private LatLon startCoordinate;

    private LatLon endCoordinate;

    private double length;

    private double avgSpeed;

    private double timeSpent; //in seconds

    private double avgAccelY;

    private int aboveThreshold;

    private double aboveThresholdPerMeter;

    private double standardDeviationFullMeanAccelY;

    private double standardDeviationSegmentMeanAccelY;

    private double avgRmsAccel;

    private double iri;

    private double iri_ml;

    public void setAboveThreshold(int aboveThreshold) {
        this.aboveThreshold = aboveThreshold;
        if (length!=0){
            this.aboveThresholdPerMeter=aboveThreshold/length;
        }
    }

    public double getAboveThresholdPerMeter() {
        return aboveThresholdPerMeter;
    }

}
