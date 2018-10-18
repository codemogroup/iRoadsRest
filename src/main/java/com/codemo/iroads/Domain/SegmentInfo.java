package com.codemo.iroads.Domain;

import java.util.List;

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

    public List<LatLon> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<LatLon> coordinates) {
        this.coordinates = coordinates;
    }

    public LatLon getStartCoordinate() {
        return startCoordinate;
    }

    public void setStartCoordinate(LatLon startCoordinate) {
        this.startCoordinate = startCoordinate;
    }

    public LatLon getEndCoordinate() {
        return endCoordinate;
    }

    public void setEndCoordinate(LatLon endCoordinate) {
        this.endCoordinate = endCoordinate;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(double avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public double getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(double timeSpent) {
        this.timeSpent = timeSpent;
    }

    public double getAvgAccelY() {
        return avgAccelY;
    }

    public void setAvgAccelY(double avgAccelY) {
        this.avgAccelY = avgAccelY;
    }

    public double getStandardDeviationFullMeanAccelY() {
        return standardDeviationFullMeanAccelY;
    }

    public void setStandardDeviationFullMeanAccelY(double standaardDeviationFullMeanAccelY) {
        this.standardDeviationFullMeanAccelY = standaardDeviationFullMeanAccelY;
    }

    public double getStandardDeviationSegmentMeanAccelY() {
        return standardDeviationSegmentMeanAccelY;
    }

    public void setStandardDeviationSegmentMeanAccelY(double standaardDeviationSegmentMeanAccelY) {
        this.standardDeviationSegmentMeanAccelY = standaardDeviationSegmentMeanAccelY;
    }

    public double getAvgRmsAccel() {
        return avgRmsAccel;
    }

    public void setAvgRmsAccel(double avgRmsAccel) {
        this.avgRmsAccel = avgRmsAccel;
    }


    public double getAboveThreshold() {
        return aboveThreshold;
    }

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
