package com.codemo.iroads.Service;

import com.codemo.iroads.Dao.NonEntityClassDao;
import com.codemo.iroads.Domain.*;
import com.couchbase.client.java.document.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dushan on 5/4/18.
 */
@Service
public class NonEntityClassServiceImpl implements NonEntityClassService {

    @Autowired
    NonEntityClassDao nonEntityClassDao;

    @Override
    public String getJourneyIds() {
        List<JsonObject> journeyIDs = nonEntityClassDao.getJourneyIDs();
        return journeyIDs.toString();
    }

    @Override
    public  List<JourneyIDNamePair> getJourneyNameObjects() {
        List<JourneyIDNamePair> journeyNameObjects = nonEntityClassDao.getJourneyNameObjects();
        return journeyNameObjects;
    }

    @Override
    public String getLocationsByjourneyID(String journeyID) {
        List<JsonObject> locationObjects = nonEntityClassDao.getLocationsByjourneyID(journeyID);
        return locationObjects.toString();
    }

    @Override
    public List<Tag> getTagsByJourneyID(String journeyID) {
        List<Tag> tagsByJourneyID = nonEntityClassDao.getTagsByJourneyID(journeyID);
        return tagsByJourneyID;
    }

    @Override
    public List<LatLonTag> getAllTaggs() {
        List<LatLonTag> allTags = nonEntityClassDao.getAllTags();
        return allTags;
    }

    @Override
    public List<JourneyIDNamePair> getTaggedJourneyIDs() {
        List<JourneyIDNamePair> taggedIds = nonEntityClassDao.getTaggedJourneyIDs();
        return taggedIds;
    }

    @Override
    public Summary getSummary() {
        int journeyCount =nonEntityClassDao.getJourneyCount();
        int taggedJourneyCount =nonEntityClassDao.getTaggedJourneyCount();

        Summary summary = new Summary();
        summary.setJourneyCount(journeyCount);
        summary.setTaggedJourneyCount(taggedJourneyCount);
        return summary;
    }

    @Override
    public List<NameID> getAllTaggedJourneys() {
        List<NameID> allTaggedJourneys = nonEntityClassDao.getAllTaggedJourneys();
        return allTaggedJourneys;
    }

    @Override
    public List<TagsWithName> getTagsWithNames() {

        List<NameID> allTaggedJourneys = getAllTaggedJourneys();

        List<TagsWithName> tagsWithNames=new ArrayList<>();

        for (NameID ni:allTaggedJourneys){

            List<TagPoint> tagsPointsByJourneyID = nonEntityClassDao.getTagsPointsByJourneyID(ni.getJourneyID());
            TagsWithName tagsWithName = new TagsWithName(ni.getJourneyName(), ni.getJourneyID(), tagsPointsByJourneyID);
            tagsWithNames.add(tagsWithName);

        }

        //get manual tags
        List<TagsWithName> manualTags = nonEntityClassDao.getManualTags();
        tagsWithNames.addAll(manualTags);

        return tagsWithNames;
    }

    @Override
    public List<ColorRange> getAllColorRanges() {
        List<ColorRange> allColorRanges = nonEntityClassDao.getAllColorRanges();
        return allColorRanges;
    }
}
