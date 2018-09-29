package com.codemo.iroads.Dao;

import com.codemo.iroads.Domain.JourneyName;
import com.codemo.iroads.Repository.JourneyNameRepository;
import com.codemo.iroads.Repository.NonEntityClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JourneyNameDaoImpl implements JourneyNameDao {

    @Autowired
    JourneyNameRepository journeyNameRepository;

    @Autowired
    NonEntityClassRepository nonEntityClassRepository;



    @Override
    public List<JourneyName> getLatestJourneys(String latestJourneyName) {
        List<JourneyName> latestJourneys=journeyNameRepository.getLatestJourneys(latestJourneyName);
        return latestJourneys;
    }

    @Override
    public List<Object>  updateLatestJourneyName(String journeyID, String journeyName) {
        List<Object> objects = journeyNameRepository.updateLatestJourneyName(journeyID, journeyName);
        return objects;
    }
}
