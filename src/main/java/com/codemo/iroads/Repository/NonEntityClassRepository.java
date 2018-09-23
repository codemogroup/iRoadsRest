package com.codemo.iroads.Repository;

import com.couchbase.client.java.document.json.JsonObject;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dushan on 5/4/18.
 */
@Repository
public class NonEntityClassRepository extends AbstractN1qlRunner{

    public List<JsonObject> getJourneyIds(){

        String query="select i.journeyID from iroads i where journeyID is not missing and dataType='data_item' group by i.journeyID";
        return getJsonArray(query);
    }

    public List<JsonObject> getJourneyNameObjects(){

        String query="select distinct journeyID,journeyName,DATE_FORMAT_STR(_sync.time_saved, '1111-11-11  00:00:00') as syncTime from iroads where dataType='trip_names' order by _sync.time_saved desc";
        return getJsonArray(query);
    }


    public List<JsonObject> getLocationsByjourneyID(String journeyID){

        String query="select lat,lon from iroads where dataType='data_item' and journeyID='"+journeyID+"' order by time";
        return getJsonArray(query);
    }



}
