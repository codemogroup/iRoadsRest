package com.codemo.iroads.Service;

import com.codemo.iroads.Domain.JourneyName;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;

import java.util.List;

public interface JourneyNameService {

    List<JourneyName> getLatestJourneys(String latestJourneyName);

    List<Object> updateLatestJourneyName(String journeyID, String journeyName);

    void renameAllLatestJourneyNames();
}
