package com.codemo.iroads.Service;

import com.codemo.iroads.Dao.NonEntityClassDao;
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
    public String getJourneyNameObjects() {
        List<JsonObject> journeyNameObjects = nonEntityClassDao.getJourneyNameObjects();
        return journeyNameObjects.toString();
    }
}
