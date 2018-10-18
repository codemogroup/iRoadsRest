package com.codemo.iroads.Domain;

import java.util.Date;
import java.util.List;

public class SegmentInfoWrapper {

    private List<SegmentInfo> segmentInfoList;

    private double minAvgSpeed;

    private double maxAvgSpeed;

    private double minAvgAccelY;

    private double maxAvgAccelY;

    private double minStandardDeviationFullMeanAccelY;

    private double maxStandardDeviationFullMeanAccelY;

    private double minStandardDeviationSegmentMeanAccelY;

    private double maxStandardDeviationSegmentMeanAccelY;

    private double minAvgRmsAccel;

    private double maxAvgRmsAccel;

    private double maxAboveThresholdPerMeter;

    private double minAboveThresholdPerMeter;

    private String journeID;

    private Date startTime;

    private Date endTime;

    private double timeSpent; //in seconds

    private int noOfSegments;

    public List<SegmentInfo> getSegmentInfoList() {
        return segmentInfoList;
    }

    public void setSegmentInfoList(List<SegmentInfo> segmentInfoList) {
        this.segmentInfoList = segmentInfoList;
    }

    public double getMinAvgSpeed() {
        return minAvgSpeed;
    }

    public void setMinAvgSpeed(double minAvgSpeed) {
        this.minAvgSpeed = minAvgSpeed;
    }

    public double getMaxAvgSpeed() {
        return maxAvgSpeed;
    }

    public void setMaxAvgSpeed(double maxAvgSpeed) {
        this.maxAvgSpeed = maxAvgSpeed;
    }

    public String getJourneID() {
        return journeID;
    }

    public void setJourneID(String journeID) {
        this.journeID = journeID;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(double timeSpent) {
        this.timeSpent = timeSpent;
    }

    public int getNoOfSegments() {
        return noOfSegments;
    }

    public void setNoOfSegments(int noOfSegments) {
        this.noOfSegments = noOfSegments;
    }

    public double getMinAvgAccelY() {
        return minAvgAccelY;
    }

    public void setMinAvgAccelY(double minAvgAccelY) {
        this.minAvgAccelY = minAvgAccelY;
    }

    public double getMaxAvgAccelY() {
        return maxAvgAccelY;
    }

    public void setMaxAvgAccelY(double maxAvgAccelY) {
        this.maxAvgAccelY = maxAvgAccelY;
    }

    public double getMinStandardDeviationFullMeanAccelY() {
        return minStandardDeviationFullMeanAccelY;
    }

    public void setMinStandardDeviationFullMeanAccelY(double minStandardDeviationFullMeanAccelY) {
        this.minStandardDeviationFullMeanAccelY = minStandardDeviationFullMeanAccelY;
    }

    public double getMaxStandardDeviationFullMeanAccelY() {
        return maxStandardDeviationFullMeanAccelY;
    }

    public void setMaxStandardDeviationFullMeanAccelY(double maxStandardDeviationFullMeanAccelY) {
        this.maxStandardDeviationFullMeanAccelY = maxStandardDeviationFullMeanAccelY;
    }

    public double getMinStandardDeviationSegmentMeanAccelY() {
        return minStandardDeviationSegmentMeanAccelY;
    }

    public void setMinStandardDeviationSegmentMeanAccelY(double minStandardDeviationSegmentMeanAccelY) {
        this.minStandardDeviationSegmentMeanAccelY = minStandardDeviationSegmentMeanAccelY;
    }

    public double getMaxStandardDeviationSegmentMeanAccelY() {
        return maxStandardDeviationSegmentMeanAccelY;
    }

    public void setMaxStandardDeviationSegmentMeanAccelY(double maxStandardDeviationSegmentMeanAccelY) {
        this.maxStandardDeviationSegmentMeanAccelY = maxStandardDeviationSegmentMeanAccelY;
    }

    public double getMinAvgRmsAccel() {
        return minAvgRmsAccel;
    }

    public void setMinAvgRmsAccel(double minAvgRmsAccel) {
        this.minAvgRmsAccel = minAvgRmsAccel;
    }

    public double getMaxAvgRmsAccel() {
        return maxAvgRmsAccel;
    }

    public void setMaxAvgRmsAccel(double maxAvgRmsAccel) {
        this.maxAvgRmsAccel = maxAvgRmsAccel;
    }

    public double getMaxAboveThresholdPerMeter() {
        return maxAboveThresholdPerMeter;
    }

    public void setMaxAboveThresholdPerMeter(double maxAboveThresholdPerMeter) {
        this.maxAboveThresholdPerMeter = maxAboveThresholdPerMeter;
    }

    public double getMinAboveThresholdPerMeter() {
        return minAboveThresholdPerMeter;
    }

    public void setMinAboveThresholdPerMeter(double minAboveThresholdPerMeter) {
        this.minAboveThresholdPerMeter = minAboveThresholdPerMeter;
    }
}
