package com.codemo.iroads.Domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
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

    private double maxIri;

    private double minIri;

    private double minIri_ml;

    private double maxIri_ml;

    private String journeID;

    private Date startTime;

    private Date endTime;

    private double timeSpent; //in seconds

    private int noOfSegments;

}
