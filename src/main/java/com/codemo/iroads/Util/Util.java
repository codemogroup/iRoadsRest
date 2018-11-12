package com.codemo.iroads.Util;

import com.codemo.iroads.Domain.DataItem;
import com.codemo.iroads.Domain.LatLon;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dushan on 5/4/18.
 */
public class Util {


    static Gson gson=new Gson();

    /**
     *
     * @param dataItems
     * @param splitBy splitting time by seconds
     * @return
     */
    public static List<JsonObject> convertDataItemToGraphAxes(List<DataItem> dataItems, int splitBy){

        long zerothTime = dataItems.get(0).getTime();

        List<JsonObject> graphDataParts=new ArrayList<>();

        List<List<DataItem>> splitParts = splitByTime(dataItems, zerothTime, splitBy);

        for(List<DataItem> part:splitParts) {

            List<JsonObject> graphDataValues = createGraphPart(part, zerothTime);

            List<JsonObject> gpsArray = createGpsArray(part);

            JsonObject graphPart = JsonObject.create();
            JsonArray firstValuesArray=(JsonArray)graphDataValues.get(0).get("values");
            graphPart.put("dataItemDensity", firstValuesArray.size());
            graphPart.put("gps",gpsArray);
            graphPart.put("values", graphDataValues);
            graphDataParts.add(graphPart);

        }

        return graphDataParts;
    }

    private static List<List<DataItem>> splitByTime(List<DataItem> dataItems, long zerothTime,int splitBy){

        List<List<DataItem>> list=new ArrayList<>();

        int partNum=-1;

        ArrayList<DataItem> part = null;

        for (DataItem di:dataItems){

            if (di.getTimeSegmentNum(zerothTime,splitBy)==partNum){
                if (part!=null){
                    part.add(di);
                }
            }
            else if (di.getTimeSegmentNum(zerothTime,splitBy)== (partNum+1)){
                partNum++;
                part = new ArrayList<>();
                list.add(part);

                part.add(di);

            }

        }
        return list;

    }


    private static List<JsonObject> createGraphPart(List<DataItem> dataItemsPart, long zerothTime){


        JsonObject accelerationXObject = JsonObject.create();
        accelerationXObject.put("key", "Acceleration X");
        JsonArray arrayX = JsonArray.create();
        accelerationXObject.put("values", arrayX);

        JsonObject accelerationYObject = JsonObject.create();
        accelerationYObject.put("key", "Acceleration Y");
        JsonArray arrayY = JsonArray.create();
        accelerationYObject.put("values", arrayY);

        JsonObject accelerationZObject = JsonObject.create();
        accelerationZObject.put("key", "Acceleration Z");
        JsonArray arrayZ = JsonArray.create();
        accelerationZObject.put("values", arrayZ);

        JsonObject accelerationXObject_raw = JsonObject.create();
        accelerationXObject_raw.put("key", "Acceleration X raw");
        JsonArray arrayX_raw = JsonArray.create();
        accelerationXObject_raw.put("values", arrayX_raw);

        JsonObject accelerationYObject_raw = JsonObject.create();
        accelerationYObject_raw.put("key", "Acceleration Y raw");
        JsonArray arrayY_raw = JsonArray.create();
        accelerationYObject_raw.put("values", arrayY_raw);

        JsonObject accelerationZObject_raw = JsonObject.create();
        accelerationZObject_raw.put("key", "Acceleration Z raw");
        JsonArray arrayZ_raw = JsonArray.create();
        accelerationZObject_raw.put("values", arrayZ_raw);

        JsonObject speedObj = JsonObject.create();
        speedObj.put("key", "GPS Speed");
        JsonArray array_speed = JsonArray.create();
        accelerationZObject_raw.put("values", array_speed);

        JsonObject magnetXobject = JsonObject.create();
        magnetXobject.put("key", "Magneto X");
        JsonArray array_magnetX = JsonArray.create();
        magnetXobject.put("values", array_magnetX);

        JsonObject magnetYobject = JsonObject.create();
        magnetYobject.put("key", "Magneto Y");
        JsonArray array_magnetY = JsonArray.create();
        magnetYobject.put("values", array_magnetY);

        JsonObject magnetZobject = JsonObject.create();
        magnetZobject.put("key", "Magneto Z");
        JsonArray array_magnetZ = JsonArray.create();
        magnetZobject.put("values", array_magnetZ);

        JsonObject gyroXobject = JsonObject.create();
        gyroXobject.put("key", "Gyro X");
        JsonArray array_gyroX = JsonArray.create();
        gyroXobject.put("values", array_gyroX);

        JsonObject gyroYobject = JsonObject.create();
        gyroYobject.put("key", "Gyro Y");
        JsonArray array_gyroY = JsonArray.create();
        gyroYobject.put("values", array_gyroY);

        JsonObject gyroZobject = JsonObject.create();
        gyroZobject.put("key", "Gyro Z");
        JsonArray array_gyroZ = JsonArray.create();
        gyroZobject.put("values", array_gyroZ);


        for (DataItem dataItem : dataItemsPart) {
            arrayX.add(JsonObject.create()
                    .put("x", dataItem.getTime() - zerothTime)
                    .put("y", dataItem.getAcceX())
            );

            arrayY.add(JsonObject.create()
                    .put("x", dataItem.getTime() - zerothTime)
                    .put("y", dataItem.getAcceY())
            );

            arrayZ.add(JsonObject.create()
                    .put("x", dataItem.getTime() - zerothTime)
                    .put("y", dataItem.getAcceZ())
            );

            arrayX_raw.add(JsonObject.create()
                    .put("x", dataItem.getTime() - zerothTime)
                    .put("y", dataItem.getAcceX_raw())
            );

            arrayY_raw.add(JsonObject.create()
                    .put("x", dataItem.getTime() - zerothTime)
                    .put("y", dataItem.getAcceY_raw())
            );


            arrayZ_raw.add(JsonObject.create()
                    .put("x", dataItem.getTime() - zerothTime)
                    .put("y", dataItem.getAcceZ_raw())
            );


        }


        List<JsonObject> graphData = new ArrayList<>();

        graphData.add(accelerationXObject);
        graphData.add(accelerationYObject);
        graphData.add(accelerationZObject);
        graphData.add(accelerationXObject_raw);
        graphData.add(accelerationYObject_raw);
        graphData.add(accelerationZObject_raw);

        return graphData;
    }

    private static List<JsonObject> createGpsArray(List<DataItem> dataItemsPart){
        List<JsonObject> gpsList=new ArrayList<>();
        for (DataItem di:dataItemsPart){

            JsonObject gpsPoint = JsonObject.create();
            gpsPoint.put("lat", di.getLat());
            gpsPoint.put("lon", di.getLon());

            gpsList.add(gpsPoint);
        }
        return gpsList;
    }

    public static List<List<JsonObject>> convertDataItemToGraphAxesGyro(List<DataItem> dataItems, int splitBy){


        long zerothTime = dataItems.get(0).getTime();


        List<List<JsonObject>> graphDataParts=new ArrayList<>();

        int partLength=dataItems.size()/splitBy;

        int reminder=dataItems.size()%splitBy;

        if (reminder>0){
            partLength++;
        }

        for(int i = 0; i < partLength ; i++) {

            int start=i*splitBy;
            int end=i*splitBy+splitBy;

            List<DataItem> dataItemsPart;

            if(i==partLength-1) {
                dataItemsPart = new ArrayList<>(dataItems.subList(start, dataItems.size()-1));
            }else {
                dataItemsPart = new ArrayList<>(dataItems.subList(start, end));
            }



            JsonObject gyroXObject = JsonObject.create();
            gyroXObject.put("key", "Gyro X");
            JsonArray arrayX = JsonArray.create();
            gyroXObject.put("values", arrayX);

            JsonObject gyroYObject = JsonObject.create();
            gyroYObject.put("key", "Gyro Y");
            JsonArray arrayY = JsonArray.create();
            gyroYObject.put("values", arrayY);

            JsonObject gyroZObject = JsonObject.create();
            gyroZObject.put("key", "Acceleration Z");
            JsonArray arrayZ = JsonArray.create();
            gyroZObject.put("values", arrayZ);



            for (DataItem dataItem : dataItemsPart) {
                arrayX.add(JsonObject.create()
                        .put("x", dataItem.getTime() - zerothTime)
                        .put("y", dataItem.getAcceX())
                );

                arrayY.add(JsonObject.create()
                        .put("x", dataItem.getTime() - zerothTime)
                        .put("y", dataItem.getAcceY())
                );

                arrayZ.add(JsonObject.create()
                        .put("x", dataItem.getTime() - zerothTime)
                        .put("y", dataItem.getAcceZ())
                );

            }


            List<JsonObject> graphData = new ArrayList<>();

            graphData.add(gyroXObject);
            graphData.add(gyroYObject);
            graphData.add(gyroZObject);


            graphDataParts.add(graphData);
        }

        return graphDataParts;
    }




    public static String welcome(){


        String centerCss="style=\""+
                "margin: auto;\n" +
                "    width: 50%;\n" +
                "    padding: 10px;" +
                "\"";

        String cellTitleCss="style=\""+
                "padding: 20px"+
                "\"";
        String cellCss="style=\""+
                "padding: 10px"+
                "\"";

        String welcomeHtml="<html><body><div "+centerCss+"><table border=\"2\">";
        List<JsonObject> welcomeJson = getWelcomeJson();

        welcomeHtml+="<tr>\n" +
                "    <th>Function</th>\n" +
                "    <th>Path</th>\n" +
                "  </tr>";

        for (JsonObject obj:welcomeJson) {
            welcomeHtml+="<tr>";
                welcomeHtml+="<td "+cellTitleCss+"><b>"+obj.get("function").toString()+"</b></td>";
                welcomeHtml+="<td "+cellCss+">"+obj.get("path").toString()+"</td>";
            welcomeHtml+="</tr>";
        }
        return welcomeHtml+"</table></div></body></html>";
    }

    public static  List<JsonObject> getWelcomeJson(){

        List<JsonObject> msg=new ArrayList<>();

        JsonObject metghod1=JsonObject.create().put("function","get journey IDs").put("path","/getJourneyIDs");
        JsonObject metghod2=JsonObject.create().put("function","get journey Names with ID").put("path","/getJourneyNames");
        JsonObject metghod3=JsonObject.create().put("function","get data according to journey ID").put("path","/getByjourneyID?journeyID=requiredId");
        JsonObject metghod4=JsonObject.create().put("function","get data according to journey ID as a Csv").put("path","/getCsvByjourneyID?journeyID=requiredId");
        JsonObject metghod5=JsonObject.create().put("function","get acceleration graph data according to journey ID").put("path","/getGraph?journeyID=requiredId&splitBy=splitingValue");
        JsonObject metghod6=JsonObject.create().put("function","get gyro graph data according to journey ID").put("path","/getGraphGyro?journeyID=requiredId&splitBy=splitingValueOfSeconds");
        JsonObject metghod7=JsonObject.create().put("function","get gps path according to journey ID").put("path","/getLocationsByjourneyID?journeyID=requiredId");
        JsonObject metghod8=JsonObject.create().put("function","get tagged ids").put("path","/getTaggedIds");
        JsonObject metghod9=JsonObject.create().put("function","get tagged names").put("path","/getTaggedNames");
        JsonObject metghod10=JsonObject.create().put("function","get tagged Bumps and Potholes").put("path","/getTags?journeyID=requiredId");
        JsonObject metghod11=JsonObject.create().put("function","get all tagged Bumps and Potholes").put("path","/getAllTags");

        JsonObject metghod12=JsonObject.create().put("function","get average filtered data").put("path","/getAverageByjourneyID?journeyID=requiredId");
        JsonObject metghod13=JsonObject.create().put("function","get summary on database").put("path","/getSummary");
        JsonObject metghod14=JsonObject.create().put("function","get journey as segmented information").put("path","/getJourneySegments?journeyID=requiredId&lat=latitude&lon=longitude");
        JsonObject metghod15=JsonObject.create().put("function","get predicted data group names").put("path","/getPredictionGroups");
        JsonObject metghod16=JsonObject.create().put("function","get predicted data by group name").put("path","/getPredictionsByGroup?groupID=groupId");
        JsonObject metghod17=JsonObject.create().put("function","get all predicted data").put("path","/getAllPredictions");
        JsonObject metghod18=JsonObject.create().put("function","get all IRI equation parameters").put("path","/getAllIriEq");
        JsonObject metghod19=JsonObject.create().put("function","get IRI equation parameters by segment lenght").put("path","/getIriEqBySegment?segmentLength=length");
        JsonObject metghod20=JsonObject.create().put("function","get color ranges for visualization").put("path","/getColorRanges");

        //        JsonObject metghod11=JsonObject.create().put("function","get all data").put("path","/getAll");

        msg.add(metghod1);
        msg.add(metghod2);
        msg.add(metghod3);
        msg.add(metghod4);
        msg.add(metghod5);
        msg.add(metghod6);
        msg.add(metghod7);
        msg.add(metghod8);
        msg.add(metghod9);
        msg.add(metghod10);
        msg.add(metghod11);
        msg.add(metghod12);
        msg.add(metghod13);
        msg.add(metghod14);
        msg.add(metghod15);
        msg.add(metghod16);
        msg.add(metghod17);
        msg.add(metghod18);
        msg.add(metghod19);
        msg.add(metghod20);

        return msg;
    }
}
