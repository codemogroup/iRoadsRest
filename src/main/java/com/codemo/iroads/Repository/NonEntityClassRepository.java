package com.codemo.iroads.Repository;

import com.codemo.iroads.Domain.*;
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


    public List<JourneyIDNamePair> getJourneyNameObjects(){
        String query="select distinct journeyID,journeyName,DATE_FORMAT_STR(_sync.time_saved, '1111-11-11  00:00:00') as syncTime from iroads where dataType='trip_names' order by _sync.time_saved desc";
        return getEntityArray(query,JourneyIDNamePair.class);
    }


    public List<JsonObject> getLocationsByjourneyID(String journeyID){
        String query="select lat,lon from iroads where dataType='data_item' and journeyID='"+journeyID+"' order by time";
        return getJsonArray(query);
    }


    public List<JourneyName> getLatestJourneys(String latestJourneyName){
        String query="select journeyID,dataType,journeyName,startLat,startLon from iroads where dataType ='trip_names' and journeyName='"+latestJourneyName+"'";
        return getEntityArray(query,JourneyName.class);
    }
    public List<Tag> getTagsByJourneyID(String journeyID){
        String query="select journeyID,lat,lon,tagType,time from iroads where dataType='tag' and journeyID='"+journeyID +"'";
        return getEntityArray(query,Tag.class);
    }

    public List<TagPoint> getTagsPointsByJourneyID(String journeyID){
        String query="select lat,lon,tagType from iroads where dataType='tag' and journeyID='"+journeyID +"'";
        return getEntityArray(query,TagPoint.class);
    }

    public List<LatLonTag> getAllTags() {
        String query="select lat,lon,tagType from iroads where dataType='tag'";
        return getEntityArray(query, LatLonTag.class);
    }

    public List<JourneyIDNamePair> getTaggedJourneyIDs() {
        String query="select distinct journeyID from iroads where dataType='tag'";
        return getEntityArray(query,JourneyIDNamePair.class);
    }

    public int getJourneyCount() {
        String query = "select count(distinct journeyID) as count from iroads where dataType='trip_names'";
        return execQuery(query).getInt("count");
    }

    public int getTaggedJourneyCount() {
        String query="select count(distinct journeyID) as count from iroads where dataType='tag'";
        return execQuery(query).getInt("count");
    }

    public List<PredictionGroupWrapper> getPredictionGroups(){
        String query="select distinct predictionGroup from iroads where dataType='prediction'";
        return getEntityArray(query, PredictionGroupWrapper.class);
    }

    public List<NameID> getAllTaggedJourneys(){
        String query="select journeyName,journeyID from iroads i where i.dataType='trip_names' and i.journeyID in (select distinct raw journeyID from iroads where dataType='tag');";
        return getEntityArray(query,NameID.class);
    }

    public List<ColorRange> getAllColorRanges(){
        String query="select dataType,rangeFor,ranges from iroads where dataType='color_range';";
        return getEntityArray(query,ColorRange.class);
    }

    public List<TagsWithName> getManualTags() {
        String query="select journeyID,journeyName,tags from iroads where dataType='manual_tag';";
        return getEntityArray(query,TagsWithName.class);
    }
}
