package com.codemo.iroads.Service;

import com.codemo.iroads.Domain.*;

import java.util.List;

/**
 * Created by dushan on 5/4/18.
 */
public interface NonEntityClassService {
    String getJourneyIds();

    List<JourneyIDNamePair> getJourneyNameObjects();

    String getLocationsByjourneyID(String journeyID);

    List<Tag> getTagsByJourneyID(String journeyID);

    List<LatLonTag> getAllTaggs();

    List<JourneyIDNamePair> getTaggedJourneyIDs();

    Summary getSummary();

    List<NameID> getAllTaggedJourneys();

    List<TagsWithName> getTagsWithNames();

    List<ColorRange> getAllColorRanges();

}
