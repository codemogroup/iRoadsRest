package com.codemo.iroads.Dao;

import com.codemo.iroads.Domain.JourneyName;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JourneyNameDao {

    List<JourneyName> getLatestJourneys(String latestJourneyName);

    List<Object> updateLatestJourneyName(String journeyID,String journeyName);

}
