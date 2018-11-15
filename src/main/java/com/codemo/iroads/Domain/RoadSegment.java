package com.codemo.iroads.Domain;

import com.couchbase.client.java.document.json.JsonObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RoadSegment {

    double length;

    ArrayList<DataItem> dataItems=new ArrayList<>();

    public DataItem getStart() {
        return dataItems.get(0);
    }

    public DataItem getEnd() {
        return dataItems.get(dataItems.size()-1);
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public ArrayList<DataItem> getDataItems() {
        return dataItems;
    }

    public LatLon getStartCoordinate(){
        return new LatLon(getStart().getLat(),getStart().getLon());
    }

    public LatLon getEndCoordinate(){
        return new LatLon(getEnd().getLat(),getEnd().getLon());
    }

    public double getTimeSpent(){
        return (getEnd().getTime()-getStart().getTime())/1000.0;
    }

    public double getAverageSpeed(){
        if (getTimeSpent()!=0){
            return (getLength()/getTimeSpent())*3.6;// to get as kmh
        }else {
            return 0;
        }
    }

    public ArrayList<LatLon> getCoordinates(){
        ArrayList<LatLon> coordinates=new ArrayList<>();
        for (DataItem di:dataItems){
            LatLon latLon=new LatLon(di.getLat(),di.getLon());
            coordinates.add(latLon);
        }
        return coordinates;
    }

    public int getSegmentN(){
        return dataItems.size();
    }

    public double getTotalAbsoluteVerticalAcceleration(){
        double absoluteYTotal=0;
        for(DataItem di:dataItems){
            absoluteYTotal+=di.getVerticalAccelerationAbsolute();
        }
        return absoluteYTotal;
    }

    public double getAverageAbsoluteVerticalAcceleration(){
        if (dataItems.size()>0){
            return getTotalAbsoluteVerticalAcceleration()/getSegmentN();
        }else {
            return 0;
        }
    }

    public double getStandardDeviationSegmentMean(){
        double diffMeanSquare= 0;
        double segmentMean=this.getAverageAbsoluteVerticalAcceleration();
        for(DataItem di:dataItems){
            diffMeanSquare+= Math.pow(di.getVerticalAccelerationAbsolute()-segmentMean,2);
        }

        if (dataItems.size()>0){
            double sd=Math.sqrt(diffMeanSquare/getSegmentN());
            return sd;
        }else {
            return 0;
        }
    }

    public double getStandardDeviationVerticalFullMean(double fullJourneyMean){
        double diffMeanSquare= 0;
        for(DataItem di:dataItems){
            diffMeanSquare+= Math.pow(di.getVerticalAccelerationAbsolute()-fullJourneyMean,2);
        }

        if (dataItems.size()>0){
            double sd=Math.sqrt(diffMeanSquare/getSegmentN());
            return sd;
        }else {
            return 0;
        }

    }

    public double getAverageAccelerationRms(){
        double rmsTotal=0;
        for(DataItem di:dataItems){
            rmsTotal+=di.getRmsAcceleration();
        }
        if (dataItems.size()>0){
            return rmsTotal/getSegmentN();
        }else {
            return 0;
        }
    }

    public int getAboveThreshold(double threshold) {
        int aboveThreshold=0;

        for (DataItem di:dataItems){
            if (di.isAccelYAboveThreshold(threshold)){
                aboveThreshold++;
            }
        }

        return aboveThreshold;
    }

    public double getIRI(IRISegmentParameter iriEqBySegment ,int segmentLength) {

        int X = getAboveThreshold(iriEqBySegment.getThreshold());
        double m = iriEqBySegment.getM();
        double c = iriEqBySegment.getC();

        //  y=mx + c  (iri equation)
        double y = m*X + c ;


        return y;
    }

    public JsonObject getIRIMlPostObj(IRISegmentParameter iriEqBySegment, int segmentLength) {
        int calcSpikesY = getAboveThreshold(iriEqBySegment.getThreshold());
        JsonObject obj=JsonObject.create();
        obj.put("calcSpikesY",calcSpikesY);
        obj.put("gpsSpeed",getAverageSpeed());
        return obj;
    }
}
