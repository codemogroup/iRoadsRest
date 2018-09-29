package com.codemo.iroads.Service;

import com.codemo.iroads.Domain.JourneyIDNamePair;
import com.codemo.iroads.Domain.Tag;
import com.couchbase.client.java.document.json.JsonObject;

import java.util.List;

/**
 * Created by dushan on 5/4/18.
 */
public interface NonEntityClassService {
    String getJourneyIds();

    List<JourneyIDNamePair> getJourneyNameObjects();

    String getLocationsByjourneyID(String journeyID);

    List<Tag> getTagsByJourneyID(String journeyID);
}
