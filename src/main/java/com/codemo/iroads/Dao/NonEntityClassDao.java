package com.codemo.iroads.Dao;

import com.codemo.iroads.Domain.JourneyIDNamePair;
import com.codemo.iroads.Domain.LatLonTag;
import com.codemo.iroads.Domain.Tag;
import com.couchbase.client.java.document.json.JsonObject;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dushan on 5/4/18.
 */
@Repository
public interface NonEntityClassDao {

    List<JsonObject> getJourneyIDs();

    List<JourneyIDNamePair> getJourneyNameObjects();

    List<JsonObject> getLocationsByjourneyID(String journeyID);

    List<Tag> getTagsByJourneyID(String journeyID);

    List<LatLonTag> getAllTags();

    List<JourneyIDNamePair> getTaggedJourneyIDs();

    int getJourneyCount();

    int getTaggedJourneyCount();


}
