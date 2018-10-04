package com.codemo.iroads.Dao;

import com.codemo.iroads.Domain.JourneyIDNamePair;
import com.codemo.iroads.Domain.Tag;
import com.codemo.iroads.Repository.NonEntityClassRepository;
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

    @Override
    public List<JourneyIDNamePair> getJourneyNameObjects() {
        return nonEntityClassRepository.getJourneyNameObjects();
    }

    @Override
    public List<JsonObject> getLocationsByjourneyID(String journeyID) {
        return nonEntityClassRepository.getLocationsByjourneyID(journeyID);
    }

    @Override
    public List<Tag> getTagsByJourneyID(String journeyID) {
        return nonEntityClassRepository.getTagsByJourneyID(journeyID);
    }

    @Override
    public List<JourneyIDNamePair> getTaggedJourneyIDs() {
        return nonEntityClassRepository.getTaggedJourneyIDs();
    }

    @Override
    public int getJourneyCount() {
        return nonEntityClassRepository.getJourneyCount();
    }

    @Override
    public int getTaggedJourneyCount() {
        return nonEntityClassRepository.getTaggedJourneyCount();
    }

}
