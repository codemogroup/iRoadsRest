package com.codemo.iroads.Dao;

import com.codemo.iroads.Repository.NonEntityClassRepository;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dushan on 5/4/18.
 */
@Component
public class NonENtityClassDaoImpl implements NonEntityClassDao {

    @Autowired
    NonEntityClassRepository nonEntityClassRepository;

    @Override
    public List<JsonObject> getJourneyIDs() {
        return nonEntityClassRepository.getJourneyIds();
    }
}
