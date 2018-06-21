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

//         JsonObject accelerationXObject=JsonObject.create();
//         accelerationXObject.put("key","Acceleration X");
//         JsonArray arrayX=JsonArray.create();
//         accelerationXObject.put("values",arrayX);

//         JsonObject accelerationYObject=JsonObject.create();
//         accelerationYObject.put("key","Acceleration Y");
//         JsonArray arrayY=JsonArray.create();
//         accelerationYObject.put("values",arrayY);

//         JsonObject accelerationZObject=JsonObject.create();
//         accelerationZObject.put("key","Acceleration Z");
//         JsonArray arrayZ=JsonArray.create();
//         accelerationZObject.put("values",arrayZ);

        JsonObject accelerationXObject_raw=JsonObject.create();
        accelerationXObject_raw.put("key","Acceleration X raw");
        JsonArray arrayX_raw=JsonArray.create();
        accelerationXObject_raw.put("values",arrayX_raw);

        JsonObject accelerationYObject_raw=JsonObject.create();
        accelerationYObject_raw.put("key","Acceleration Y raw");
        JsonArray arrayY_raw=JsonArray.create();
        accelerationYObject_raw.put("values",arrayY_raw);

        JsonObject accelerationZObject_raw=JsonObject.create();
        accelerationZObject_raw.put("key","Acceleration Z raw");
        JsonArray arrayZ_raw=JsonArray.create();
        accelerationZObject_raw.put("values",arrayZ_raw);

        JsonObject speedObj=JsonObject.create();
        speedObj.put("key","Speed");
        JsonArray speed=JsonArray.create();
        accelerationZObject_raw.put("values",speed);


        List<JsonObject> graphData=new ArrayList<>();
//         graphData.add(accelerationXObject);
//         graphData.add(accelerationYObject);
//         graphData.add(accelerationZObject);
        graphData.add(accelerationXObject_raw);
        graphData.add(accelerationYObject_raw);
        graphData.add(accelerationZObject_raw);
        graphData.add(speedObj);

        for (DataItem dataItem:dataItems) {
//             arrayX.add(JsonObject.create()
//                     .put("x",dataItem.getTime()-zerothTime)
//                     .put("y",dataItem.getAcceX())
// //                    .put("time",dataItem.getTime())
// //                    .put("gps",JsonObject.create()
// //                            .put("lat",dataItem.getLat()).put("lon",dataItem.getLon()))
//             );

//             arrayY.add(JsonObject.create()
//                     .put("x",dataItem.getTime()-zerothTime)
//                     .put("y",dataItem.getAcceY())
// //                    .put("time",dataItem.getTime())
// //                    .put("gps",JsonObject.create()
// //                            .put("lat",dataItem.getLat()).put("lon",dataItem.getLon()))
//             );

//             arrayZ.add(JsonObject.create()
//                     .put("x",dataItem.getTime()-zerothTime)
//                     .put("y",dataItem.getAcceZ())
// //                    .put("time",dataItem.getTime())
// //                    .put("gps",JsonObject.create()
// //                            .put("lat",dataItem.getLat()).put("lon",dataItem.getLon()))
//             );

            arrayX_raw.add(JsonObject.create()
                            .put("x",dataItem.getTime()-zerothTime)
                            .put("y",dataItem.getAcceX_raw())
//                    .put("time",dataItem.getTime())
//                    .put("gps",JsonObject.create()
//                            .put("lat",dataItem.getLat()).put("lon",dataItem.getLon()))
            );

            arrayY_raw.add(JsonObject.create()
                            .put("x",dataItem.getTime()-zerothTime)
                            .put("y",dataItem.getAcceY_raw())
//                    .put("time",dataItem.getTime())
//                    .put("gps",JsonObject.create()
//                            .put("lat",dataItem.getLat()).put("lon",dataItem.getLon()))
            );


            arrayZ_raw.add(JsonObject.create()
                            .put("x",dataItem.getTime()-zerothTime)
                            .put("y",dataItem.getAcceZ_raw())
//                    .put("time",dataItem.getTime())
//                    .put("gps",JsonObject.create()
//                            .put("lat",dataItem.getLat()).put("lon",dataItem.getLon()))
            );


            speed.add(JsonObject.create()
                            .put("x",dataItem.getTime()-zerothTime)
                            .put("y",dataItem.getGpsSpeed())
//                    .put("time",dataItem.getTime())
//                    .put("gps",JsonObject.create()
//                            .put("lat",dataItem.getLat()).put("lon",dataItem.getLon()))
            );
        }

        return graphData;
    }

    public static String welcome(){
        String welcomeHtml="<html><body>";
        List<JsonObject> welcomeJson = getWelcomeJson();
        for (JsonObject obj:welcomeJson) {
            welcomeHtml+="<h2>"+obj.get("function").toString()+"</h2>";
            welcomeHtml+="<p>"+obj.get("path").toString()+"</p>";
        }
        return welcomeHtml+"</body></html>";
    }

    public static  List<JsonObject> getWelcomeJson(){

        List<JsonObject> msg=new ArrayList<>();

        JsonObject metghod1=JsonObject.create().put("function","get journey IDs").put("path","/getJourneyIDs");
        JsonObject metghod2=JsonObject.create().put("function","get journey Names with ID").put("path","/getJourneyNames");
        JsonObject metghod3=JsonObject.create().put("function","get data according to journey ID").put("path","/getByjourneyID?journeyID=requiredId");
        JsonObject metghod4=JsonObject.create().put("function","get data according to journey ID as a Csv").put("path","/getCsvByjourneyID?journeyID=requiredId");
        JsonObject metghod5=JsonObject.create().put("function","get graph data according to journey ID").put("path","/getGraph?journeyID=requiredId");
        JsonObject metghod6=JsonObject.create().put("function","get all data").put("path","/getAll");

        msg.add(metghod1);
        msg.add(metghod2);
        msg.add(metghod3);
        msg.add(metghod4);
        msg.add(metghod5);
        msg.add(metghod6);

        return msg;
    }
}
