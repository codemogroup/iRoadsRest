package com.codemo.iroads.Util;

import com.codemo.iroads.Domain.DataItem;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dushan on 5/4/18.
 */
public class Util {

    public static List<JsonObject> convertDataItemToGraphAxes(List<DataItem> dataItems){

        long zerothTime=dataItems.get(0).getTime();

        JsonObject accelerationXObject=JsonObject.create();
        accelerationXObject.put("key","Acceleration X");
        JsonArray arrayX=JsonArray.create();
        accelerationXObject.put("values",arrayX);

        JsonObject accelerationYObject=JsonObject.create();
        accelerationYObject.put("key","Acceleration Y");
        JsonArray arrayY=JsonArray.create();
        accelerationYObject.put("values",arrayY);

        JsonObject accelerationZObject=JsonObject.create();
        accelerationZObject.put("key","Acceleration Z");
        JsonArray arrayZ=JsonArray.create();
        accelerationZObject.put("values",arrayZ);

        List<JsonObject> graphData=new ArrayList<>();
        graphData.add(accelerationXObject);
        graphData.add(accelerationYObject);
        graphData.add(accelerationZObject);

        for (DataItem dataItem:dataItems) {
            arrayX.add(JsonObject.create()
                    .put("x",dataItem.getTime()-zerothTime)
                    .put("y",dataItem.getAcceX())
//                    .put("time",dataItem.getTime())
//                    .put("gps",JsonObject.create()
//                            .put("lat",dataItem.getLat()).put("lon",dataItem.getLon()))
            );

            arrayY.add(JsonObject.create()
                    .put("x",dataItem.getTime()-zerothTime)
                    .put("y",dataItem.getAcceY())
//                    .put("time",dataItem.getTime())
//                    .put("gps",JsonObject.create()
//                            .put("lat",dataItem.getLat()).put("lon",dataItem.getLon()))
            );

            arrayZ.add(JsonObject.create()
                    .put("x",dataItem.getTime()-zerothTime)
                    .put("y",dataItem.getAcceZ())
//                    .put("time",dataItem.getTime())
//                    .put("gps",JsonObject.create()
//                            .put("lat",dataItem.getLat()).put("lon",dataItem.getLon()))
            );
        }

        return graphData;
    }

    public static String welcome(){

        List<JsonObject> msg=new ArrayList<>();

        JsonObject metghod1=JsonObject.create().put("function","get journey IDs").put("path","/getJourneyIDs");
        JsonObject metghod2=JsonObject.create().put("function","get data according to journey ID").put("path","/getByjourneyID?journeyID=requiredId");
        JsonObject metghod3=JsonObject.create().put("function","get graph data according to journey ID").put("path","/getGraph?journeyID=requiredId");
        JsonObject metghod4=JsonObject.create().put("function","get all data").put("path","/getAll");

        msg.add(metghod1);
        msg.add(metghod2);
        msg.add(metghod3);
        msg.add(metghod4);

        return msg.toString();
    }
}
