package com.codemo.iroads.Service;

import com.codemo.iroads.Algorithms.SignalProcessor;
import com.codemo.iroads.Dao.DataItemDao;
import com.codemo.iroads.Domain.*;
import com.codemo.iroads.Util.CSV_Writer;
import com.codemo.iroads.Util.Util;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.google.gson.Gson;
import org.apache.http.impl.client.CloseableHttpClient;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;


import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 * Created by dushan on 4/25/18.
 */

@Service
public class DataItemServiceImpl implements DataItemService {

    @Autowired
    DataItemDao dataItemDao;

    @Autowired
    IRIService iriService;

    @Autowired
    Gson gson;

    @Override
    public List<DataItem> getAll() {
        List<DataItem> dataItems= dataItemDao.getAll();
        return dataItems;
    }

    @Override
    public List<DataItem> getDataItemByJourneyID(String journeyID) {
        List<DataItem> dataItems= dataItemDao.getDataItemByJourneyID(journeyID);
        return dataItems;
    }

    @Override
    public String getAccelerationGraphDataByJourneyID(String journeyID, int splitBy) {
        List<DataItem> dataItems= dataItemDao.getDataItemByJourneyID(journeyID);

        List<JsonObject> graphData= Util.convertDataItemToGraphAxes(dataItems,splitBy);
        return graphData.toString();
    }

    @Override
    public String getGyroGraphDataByJourneyID(String journeyID, int splitBy) {
        List<DataItem> dataItems= dataItemDao.getDataItemByJourneyID(journeyID);

        List<List<JsonObject>> graphData= Util.convertDataItemToGraphAxesGyro(dataItems,splitBy);
        return graphData.toString();
    }

    @Override
    public void getCsvDataItemByJourneyID(String journeyID,HttpServletResponse response) throws IOException {
        List<DataItem> dataItems= dataItemDao.getDataItemByJourneyID(journeyID);
        String fileName=DateTime.now()+"_"+journeyID+".csv";
        response.setContentType ("application/csv");
        response.setHeader ("Content-Disposition",
                "attachment; filename="+fileName);
        CSV_Writer.writeToCsv(response.getWriter(),dataItems);
    }

    @Override
    public List<DataItem> getAverageFilterdData(String journeyID) {
        List<DataItem> dataSet = dataItemDao.getDataItemByJourneyID(journeyID);
        List<DataItem> averageDataSet = null;
        SignalProcessor xProcessor = new SignalProcessor();
        SignalProcessor yProcessor = new SignalProcessor();
        SignalProcessor zProcessor = new SignalProcessor();
        for (int i = 0; i < dataSet.size(); i++){
            DataItem row = dataSet.get(i);
            DataItem averageRow = new DataItem();
            averageRow.setAcceX((float) xProcessor.averageFilter(row.getAcceX()));
            averageRow.setAcceY((float) yProcessor.averageFilter(row.getAcceY()));
            averageRow.setAcceZ((float) zProcessor.averageFilter(row.getAcceZ()));
            averageRow.setTime(row.getTime());
            averageRow.setCount(row.getCount());
            averageRow.setDataType(row.getDataType());
            averageRow.setId(row.getId());
            averageRow.setImei(row.getImei());
            averageRow.setJourneyID(row.getJourneyID());
            averageRow.setLat((float) row.getLat());
            averageRow.setLon((float) row.getLon());
            averageRow.setObdRpm((float) row.getObdRpm());
            averageRow.setObdSpeed((float) row.getObdSpeed());
            averageDataSet.add(averageRow);
        }

        return averageDataSet;
    }

    @Override
    public SegmentInfoWrapper getJourneySegments(String journeyID, double lat, double lon, double threshold,int segmentLength) {
        List<DataItem> dataItems = dataItemDao.getDataItemByJourneyID(journeyID);
//        List<DataItem> dataItems = dataItemDao.getDummy();

        double totalVerticalAbsoluteAcceletaion=0;

        DataItem selected=dataItems.get(0);
        double selecteddiff=getDifference(selected,lat,lon);
        int selectedIndex=0;

        for (int i=0;i<dataItems.size();i++){
            double thisDiff=getDifference(dataItems.get(i),lat,lon);
            if (thisDiff<selecteddiff){
                selected=dataItems.get(i);
                selecteddiff=thisDiff;
                selectedIndex=i;
            }

            totalVerticalAbsoluteAcceletaion+=dataItems.get(i).getVerticalAccelerationAbsolute();
        }

        double fullJourneyMeanAccelerationY=totalVerticalAbsoluteAcceletaion/dataItems.size();


        List<RoadSegment> segments = createSegments(dataItems, selectedIndex,segmentLength);
        List<SegmentInfo> segmentInfoList = finalizeSegments(segments,fullJourneyMeanAccelerationY,threshold,segmentLength);

        SegmentInfoWrapper segmentInfoWrapper = new SegmentInfoWrapper();
        segmentInfoWrapper.setSegmentInfoList(segmentInfoList);


        double maxAvgSpeed=segmentInfoList.get(0).getAvgSpeed();
        double minAvgSpeed=segmentInfoList.get(0).getAvgSpeed();

        double minAvgAccelY=segmentInfoList.get(0).getAvgAccelY();
        double maxAvgAccelY=segmentInfoList.get(0).getAvgAccelY();

        double minStandardDeviationFullMeanAccelY=segmentInfoList.get(0).getStandardDeviationFullMeanAccelY();
        double maxStandardDeviationFullMeanAccelY=segmentInfoList.get(0).getStandardDeviationFullMeanAccelY();

        double minStandardDeviationSegmentMeanAccelY=segmentInfoList.get(0).getStandardDeviationSegmentMeanAccelY();
        double maxStandardDeviationSegmentMeanAccelY=segmentInfoList.get(0).getStandardDeviationSegmentMeanAccelY();

        double maxAvgRmsAccel=segmentInfoList.get(0).getAvgRmsAccel();
        double minAvgRmsAccel=segmentInfoList.get(0).getAvgRmsAccel();

        double maxAboveThresholdPerMeter=segmentInfoList.get(0).getAboveThresholdPerMeter();
        double minAboveThresholdPerMeter=segmentInfoList.get(0).getAboveThresholdPerMeter();

        double maxIri=segmentInfoList.get(0).getIri();
        double minIri=segmentInfoList.get(0).getIri();

        double maxIri_ml=segmentInfoList.get(0).getIri_ml();
        double minIri_ml=segmentInfoList.get(0).getIri_ml();

        for (SegmentInfo segmentInfo:segmentInfoList){
            //avg speed
            if (maxAvgSpeed<segmentInfo.getAvgSpeed()){
                maxAvgSpeed=segmentInfo.getAvgSpeed();
            }
            if (minAvgSpeed>segmentInfo.getAvgSpeed()){
                minAvgSpeed=segmentInfo.getAvgSpeed();
            }

            //sd avg accel Y
            if (maxAvgAccelY<segmentInfo.getAvgAccelY()){
                maxAvgAccelY=segmentInfo.getAvgAccelY();
            }
            if (minAvgAccelY>segmentInfo.getAvgAccelY()){
                minAvgAccelY=segmentInfo.getAvgAccelY();
            }

            //sd full mean
            if (maxStandardDeviationFullMeanAccelY<segmentInfo.getStandardDeviationFullMeanAccelY()){
                maxStandardDeviationFullMeanAccelY=segmentInfo.getStandardDeviationFullMeanAccelY();
            }
            if (minStandardDeviationFullMeanAccelY>segmentInfo.getStandardDeviationFullMeanAccelY()){
                minStandardDeviationFullMeanAccelY=segmentInfo.getStandardDeviationFullMeanAccelY();
            }

            //sd segment mean
            if (maxStandardDeviationSegmentMeanAccelY<segmentInfo.getStandardDeviationSegmentMeanAccelY()){
                maxStandardDeviationSegmentMeanAccelY=segmentInfo.getStandardDeviationSegmentMeanAccelY();
            }
            if (minStandardDeviationFullMeanAccelY>segmentInfo.getStandardDeviationSegmentMeanAccelY()){
                minStandardDeviationFullMeanAccelY=segmentInfo.getStandardDeviationSegmentMeanAccelY();
            }

            //rms accel
            if (maxAvgRmsAccel<segmentInfo.getAvgRmsAccel()){
                maxAvgRmsAccel=segmentInfo.getAvgRmsAccel();
            }
            if (minAvgRmsAccel>segmentInfo.getAvgRmsAccel()){
                minAvgRmsAccel=segmentInfo.getAvgRmsAccel();
            }

            //above threshold
            if (maxAboveThresholdPerMeter<segmentInfo.getAboveThresholdPerMeter()){
                maxAboveThresholdPerMeter=segmentInfo.getAboveThresholdPerMeter();
            }
            if (minAboveThresholdPerMeter>segmentInfo.getAboveThresholdPerMeter()){
                minAboveThresholdPerMeter=segmentInfo.getAboveThresholdPerMeter();
            }

            //iri
            if (maxIri<segmentInfo.getIri()){
                maxIri=segmentInfo.getIri();
            }
            if (minIri>segmentInfo.getIri()){
                minIri=segmentInfo.getIri();
            }

            //iri
            if (maxIri<segmentInfo.getIri()){
                maxIri=segmentInfo.getIri();
            }
            if (minIri>segmentInfo.getIri()){
                minIri=segmentInfo.getIri();
            }

            //iri_ml
            if (maxIri_ml<segmentInfo.getIri_ml()){
                maxIri_ml=segmentInfo.getIri_ml();
            }
            if (minIri_ml>segmentInfo.getIri_ml()){
                minIri_ml=segmentInfo.getIri_ml();
            }
        }

        segmentInfoWrapper.setMinAvgSpeed(minAvgSpeed);
        segmentInfoWrapper.setMaxAvgSpeed(maxAvgSpeed);

        segmentInfoWrapper.setMinAvgAccelY(minAvgAccelY);
        segmentInfoWrapper.setMaxAvgAccelY(maxAvgAccelY);

        segmentInfoWrapper.setMinStandardDeviationFullMeanAccelY(minStandardDeviationFullMeanAccelY);
        segmentInfoWrapper.setMaxStandardDeviationFullMeanAccelY(maxStandardDeviationFullMeanAccelY);

        segmentInfoWrapper.setMinStandardDeviationSegmentMeanAccelY(minStandardDeviationSegmentMeanAccelY);
        segmentInfoWrapper.setMaxStandardDeviationSegmentMeanAccelY(maxStandardDeviationSegmentMeanAccelY);

        segmentInfoWrapper.setMinAvgRmsAccel(minAvgRmsAccel);
        segmentInfoWrapper.setMaxAvgRmsAccel(maxAvgRmsAccel);

        segmentInfoWrapper.setMinAboveThresholdPerMeter(minAboveThresholdPerMeter);
        segmentInfoWrapper.setMaxAboveThresholdPerMeter(maxAboveThresholdPerMeter);

        segmentInfoWrapper.setMinIri(minIri);
        segmentInfoWrapper.setMaxIri(maxIri);

        segmentInfoWrapper.setMinIri_ml(minIri_ml);
        segmentInfoWrapper.setMaxIri_ml(maxIri_ml);

        segmentInfoWrapper.setJourneID(journeyID);

        long journeyStartTimeMS = segments.get(0).getStart().getTime();
        long journeyEndTimeMS = segments.get(segments.size()-1).getEnd().getTime();

        segmentInfoWrapper.setStartTime(new Date(journeyStartTimeMS));

        segmentInfoWrapper.setEndTime(new Date(journeyEndTimeMS));

        segmentInfoWrapper.setTimeSpent((journeyEndTimeMS-journeyStartTimeMS)/1000.0); //as seconds

        segmentInfoWrapper.setNoOfSegments(segments.size());

        return segmentInfoWrapper;

    }

    private List<SegmentInfo> finalizeSegments(List<RoadSegment> segments,double fullJourneyMeanAccelerationY,double threshold,int segmentLength){

        IRISegmentParameter iriEqBySegment = iriService.getIRIEqBySegment(segmentLength);

        List<Double> mlIri=null;
        try {
            mlIri = getMlIri(segments, iriEqBySegment, segmentLength);
        }catch (Exception e){
            e.printStackTrace();
        }

        int index=0;
        List<SegmentInfo> segmentInfoList=new ArrayList<>();
        for(RoadSegment segment:segments){
            SegmentInfo segmentInfo=new SegmentInfo();

            //start coordinate
            segmentInfo.setStartCoordinate(segment.getStartCoordinate());

            //end coordinate
            segmentInfo.setEndCoordinate(segment.getEndCoordinate());

            //latlon array
            segmentInfo.setCoordinates(segment.getCoordinates());

            //time spent in segment in s
            segmentInfo.setTimeSpent(segment.getTimeSpent());

            //set segment length
            segmentInfo.setLength(segment.getLength());

            //set avg speed
            segmentInfo.setAvgSpeed(segment.getAverageSpeed());

            // set avgY
            segmentInfo.setAvgAccelY(segment.getAverageAbsoluteVerticalAcceleration());

            //set no of points above threshold
            segmentInfo.setAboveThreshold(segment.getAboveThreshold(threshold));

            // set sd Y fullMean
            segmentInfo.setStandardDeviationFullMeanAccelY(segment.getStandardDeviationVerticalFullMean(fullJourneyMeanAccelerationY));

            // set sd Y segment mean
            segmentInfo.setStandardDeviationSegmentMeanAccelY(segment.getStandardDeviationSegmentMean());

            // set rms accec
            segmentInfo.setAvgRmsAccel(segment.getAverageAccelerationRms());

            // set iri
            segmentInfo.setIri(segment.getIRI(iriEqBySegment, segmentLength));

            // set iri_ml
            if (mlIri!=null) {
                segmentInfo.setIri_ml(mlIri.get(index++));
            }

            ////adding to list
            segmentInfoList.add(segmentInfo);

        }
        return segmentInfoList;

    }

    private List<Double> getMlIri(List<RoadSegment> segments,IRISegmentParameter iriSegmentParameter,int segmentLength) {

        JsonArray array=JsonArray.create();
        for(RoadSegment segment:segments){
            JsonObject iriMlPostObj = segment.getIRIMlPostObj(iriSegmentParameter, segmentLength);
            array.add(iriMlPostObj);
        }

        JsonObject obj=JsonObject.create();
        obj.put("array",array);
        obj.put("segment",Integer.toString(segmentLength));

        HttpResponse dataFromPost = getDataFromPost(obj);


        try {
            InputStream content = dataFromPost.getEntity().getContent();

            BufferedReader r = new BufferedReader(new InputStreamReader(content));
            ArrayList<String> resultLines = new ArrayList<String>();
            String line;
            while ((line=r.readLine()) != null) {
                resultLines.add(line);
            }

            ArrayList<Double> resultList = convertLineToArray(resultLines.get(0));
            return resultList;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    private HttpResponse getDataFromPost(JsonObject obj){
        String       postUrl       = "http://iroads.projects.mrt.ac.lk:8085/getIri";// put in your url
        Gson         gson          = new Gson();
        CloseableHttpClient httpClient    = HttpClientBuilder.create().build();
        HttpPost     post          = new HttpPost(postUrl);
        StringEntity postingString = null;//gson.tojson() converts your pojo to json
        try {
            postingString = new StringEntity(obj.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        try {
            HttpResponse  response = httpClient.execute(post);
            return response;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Double> convertLineToArray(String stringArray){
        //remove brackets
        String withoutBrackets=stringArray.substring(1, stringArray.length() - 1);
        String[] split = withoutBrackets.split(",");

        ArrayList<Double> converted=new ArrayList<>();

        for(String s:split){
            converted.add(Double.parseDouble(s));
        }
        return converted;
    }


    private double getDifference(DataItem di,double lat, double lon){
        double diff=Math.abs(di.getLat()-lat)+Math.abs(di.getLon()-lon);
        return diff;
    }

    /**
     * Haversine formula
     *
     * @param item1
     * @param item2
     * @return in meters
     */
    private double getDistance(DataItem item1, DataItem item2){

        double lat1 = item1.getLat();
        double lon1 = item1.getLon();
        double lat2 = item2.getLat();
        double lon2 = item2.getLon();

        double R = 6371; // Radius of the earth in km
        double dLat = deg2rad(lat2-lat1);  // deg2rad below
        double dLon = deg2rad(lon2-lon1);
        double a =
                Math.sin(dLat/2) * Math.sin(dLat/2) +
                        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                                Math.sin(dLon/2) * Math.sin(dLon/2)
                ;
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c; // Distance in km
        double dm = d*1000; // Distance in m
        return dm;
    }

    private double deg2rad(double deg) {
        return deg * (Math.PI/180);
    }

    private final static int pointGap=1;

    private List<RoadSegment> createSegments(List<DataItem> dataItems,int fromPointIndex,int segmentLength){

        //0 to fromPointIndex-pointGap
        List<RoadSegment> segmentsBackward = createSegmentsBackward(dataItems, fromPointIndex, pointGap, segmentLength);

        //fromPointIndex to end
        List<RoadSegment> segmentsForward = createSegmentsForward(dataItems, fromPointIndex, pointGap, segmentLength);

        //0 to end
        List<RoadSegment> allSegments = new ArrayList<RoadSegment>(segmentsBackward);
        allSegments.addAll(segmentsForward);

        return allSegments;
    }

    private  List<RoadSegment> createSegmentsForward(List<DataItem> dataItems,int fromPointIndex,int pointGap,int segmentLength){
        //forward pass
        List<RoadSegment> roadSegments=new ArrayList<>();

        RoadSegment currentSegment=new RoadSegment(); //first segment

        roadSegments.add(currentSegment);

        DataItem lastItem=dataItems.get(fromPointIndex); //first item (relative to fromPointIndex)
        currentSegment.getDataItems().add(lastItem); //adding starting point to first segment

        double currentDistance=0;
        for (int i=fromPointIndex+pointGap;i<dataItems.size();i=i+pointGap){
            DataItem thisItem = dataItems.get(i);



            currentDistance+=getDistance(lastItem,thisItem);

            lastItem=thisItem;

            if(currentDistance<=segmentLength){ //inside a segment
                currentSegment.getDataItems().add(thisItem);

                //setting length for current segment
                currentSegment.setLength(currentDistance);

            }else { //new segment start

                //creating new segment
                currentSegment=new RoadSegment();
                roadSegments.add(currentSegment);
                //adding first item for new segment
                currentSegment.getDataItems().add(thisItem);
                currentDistance=0;

            }

        }

        return roadSegments;
    }

    private List<RoadSegment> createSegmentsBackward(List<DataItem> dataItems, int fromPointIndex, int pointGap, int segmentLength) {
        //backward pass
        List<RoadSegment> roadSegments = new ArrayList<>();
        if (fromPointIndex>1) {

            RoadSegment currentSegment = new RoadSegment(); //first segment

            roadSegments.add(currentSegment);

            DataItem lastItem = dataItems.get(fromPointIndex); //end item won't be added to this (as this is the first item in forward pass)

            double currentDistance=0;
            for (int i = fromPointIndex - 1; i >= 0; i = i - pointGap) {
                DataItem thisItem = dataItems.get(i);

                //setting length for currunt segment
                currentSegment.setLength(currentDistance);

                currentDistance+=getDistance(lastItem,thisItem);
                lastItem=thisItem;

                if(currentDistance<=segmentLength){ //inside a segment
                    currentSegment.getDataItems().add(thisItem);

                    //setting length for current segment
                    currentSegment.setLength(currentDistance);

                    // final segment (this segment has already added to list from below else{})
                    if (i==0){
                        //reversing final segment
                        Collections.reverse(currentSegment.getDataItems());
                    }

                }else { //new segment start

                    //reversing previous segment dataitem list because here it went from middle to start
                    Collections.reverse(currentSegment.getDataItems());

                    //creating new segment
                    currentSegment=new RoadSegment();
                    roadSegments.add(currentSegment);
                    //adding first item for new segment
                    currentSegment.getDataItems().add(thisItem);
                    currentDistance=0;
                }
            }

            //reversing roadsegments list because here it went from middle to start
            Collections.reverse(roadSegments);

        }
        return roadSegments;
    }
}
