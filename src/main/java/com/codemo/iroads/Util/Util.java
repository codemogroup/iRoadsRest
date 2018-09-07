package com.codemo.iroads.Util;

import com.codemo.iroads.Domain.DataItem;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;

import javax.swing.text.Style;
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
        speedObj.put("key","GPS Speed");
        JsonArray array_speed=JsonArray.create();
        accelerationZObject_raw.put("values",array_speed);

        JsonObject magnetXobject=new JsonObject.create();
        magnetXobject.put("key", "Magneto X")
        JsonArray array_magnetX=JsonArray.create();
        magnetXobject.put("values",array_magnetX);

        JsonObject magnetYobject=new JsonObject.create();
        magnetYobject.put("key", "Magneto Y")
        JsonArray array_magnetY=JsonArray.create();
        magnetYobject.put("values",array_magnetY);

        JsonObject magnetZobject=new JsonObject.create();
        magnetZobject.put("key", "Magneto Z")
        JsonArray array_magnetZ=JsonArray.create();
        magnetZobject.put("values",array_magnetZ);

        JsonObject gyroXobject=new JsonObject.create();
        gyroXobject.put("key", "Gyro X")
        JsonArray array_gyroX=JsonArray.create();
        gyroXobject.put("values",array_gyroX);

        JsonObject gyroYobject=new JsonObject.create();
        gyroYobject.put("key", "Gyro Y")
        JsonArray array_gyroY=JsonArray.create();
        gyroYobject.put("values",array_gyroY);

        JsonObject gyroZobject=new JsonObject.create();
        gyroZobject.put("key", "Gyro Z")
        JsonArray array_gyroZ=JsonArray.create();
        gyroZobject.put("values",array_gyroZ);




        for (DataItem dataItem:dataItems) {
            arrayX.add(JsonObject.create()
                    .put("x",dataItem.getTime()-zerothTime)
                    .put("y",dataItem.getAcceX())
            );

            arrayY.add(JsonObject.create()
                    .put("x",dataItem.getTime()-zerothTime)
                    .put("y",dataItem.getAcceY())
            );

            arrayZ.add(JsonObject.create()
                    .put("x",dataItem.getTime()-zerothTime)
                    .put("y",dataItem.getAcceZ())
            );

            arrayX_raw.add(JsonObject.create()
                            .put("x",dataItem.getTime()-zerothTime)
                            .put("y",dataItem.getAcceX_raw())
            );

            arrayY_raw.add(JsonObject.create()
                            .put("x",dataItem.getTime()-zerothTime)
                            .put("y",dataItem.getAcceY_raw())
            );


            arrayZ_raw.add(JsonObject.create()
                            .put("x",dataItem.getTime()-zerothTime)
                            .put("y",dataItem.getAcceZ_raw())
            );

            if (dataItem.getGpsSpeed()!=null) {
                array_speed.add(JsonObject.create()
                                 .put("x",dataItem.getTime()-zerothTime)
                                 .put("y",dataItem.getGpsSpeed())
                );
            }

            if (dataItem.getMagnetX()!=null){
                array_magnetX.add(JsonObject.create()
                        .put("x",dataItem.getTime()-zerothTime)
                        .put("y",dataItem.getMagnetX())
                );
            }

            if (dataItem.getMagnetY()!=null) {
                array_magnetY.add(JsonObject.create()
                        .put("x", dataItem.getTime() - zerothTime)
                        .put("y", dataItem.getMagnetY())
                );
            }

            if (dataItem.getMagnetZ()!=null) {
                array_magnetZ.add(JsonObject.create()
                        .put("x", dataItem.getTime() - zerothTime)
                        .put("y", dataItem.getMagnetZ())
                );
            }

            if (dataItem.getGyroX()!=null) {
                array_gyroX.add(JsonObject.create()
                        .put("x", dataItem.getTime() - zerothTime)
                        .put("y", dataItem.getGyroX())
                );
            }

            if (dataItem.getGyroY()!=null) {
                array_gyroY.add(JsonObject.create()
                        .put("x", dataItem.getTime() - zerothTime)
                        .put("y", dataItem.getGyroY())
                );
            }

            if (dataItem.getGyroZ()!=null) {
                array_gyroZ.add(JsonObject.create()
                        .put("x", dataItem.getTime() - zerothTime)
                        .put("y", dataItem.getGyroZ())
                );
            }
        }



        List<JsonObject> graphData=new ArrayList<>();

        graphData.add(accelerationXObject);
        graphData.add(accelerationYObject);
        graphData.add(accelerationZObject);
        graphData.add(accelerationXObject_raw);
        graphData.add(accelerationYObject_raw);
        graphData.add(accelerationZObject_raw);

        if (array_speed.size()>0) {
            graphData.add(speedObj);
        }

        if (array_magnetX.size()>0) {
            graphData.add(magnetXobject);
            graphData.add(magnetYobject);
            graphData.add(magnetZobject);
        }

        if (array_gyroX.size()>0) {
            graphData.add(gyroXobject);
            graphData.add(gyroYobject);
            graphData.add(gyroZobject);
        }


        return graphData;
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
                "padding: 20px"+
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
        JsonObject metghod5=JsonObject.create().put("function","get graph data according to journey ID").put("path","/getGraph?journeyID=requiredId");
        JsonObject metghod6=JsonObject.create().put("function","get gps path according to journey ID").put("path","/getLocationsByjourneyID?journeyID=requiredId");
        JsonObject metghod7=JsonObject.create().put("function","get all data").put("path","/getAll");
        JsonObject metghod8=JsonObject.create().put("function","get average filtered data").put("path","/getAverageByjourneyID?journeyID=requiredId");

        msg.add(metghod1);
        msg.add(metghod2);
        msg.add(metghod3);
        msg.add(metghod4);
        msg.add(metghod5);
        msg.add(metghod6);
        msg.add(metghod7);
        msg.add(metghod8);

        return msg;
    }
}
