package com.codemo.iroads.Dao;

import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dushan on 5/4/18.
 */
@Repository
public interface NonEntityClassDao {

    List<JsonObject> getJourneyIDs();

    List<JsonObject> getJourneyNameObjects();
}
