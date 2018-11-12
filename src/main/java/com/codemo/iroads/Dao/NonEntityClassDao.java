package com.codemo.iroads.Dao;

import com.codemo.iroads.Domain.*;
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

    List<NameID> getAllTaggedJourneys();

    List<TagPoint> getTagsPointsByJourneyID(String journeyID);

    List<ColorRange> getAllColorRanges();

    List<TagsWithName> getManualTags();
}
