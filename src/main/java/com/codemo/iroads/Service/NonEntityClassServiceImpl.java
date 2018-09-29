package com.codemo.iroads.Service;

import com.codemo.iroads.Dao.NonEntityClassDao;
import com.codemo.iroads.Domain.JourneyIDNamePair;
import com.codemo.iroads.Domain.Tag;
import com.couchbase.client.java.document.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
