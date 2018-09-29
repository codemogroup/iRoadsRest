package com.codemo.iroads.Repository;

import com.codemo.iroads.Domain.JourneyName;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

import java.util.List;
import java.util.Optional;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "iroads")
public interface JourneyNameRepository extends CouchbaseRepository<JourneyName, String> {

    Optional<JourneyName> findById(String id);

    @Query("#{#n1ql.selectEntity} where dataType ='trip_names' and journeyName= $1")
    List<JourneyName> getLatestJourneys(String latestJourneyName);


    @Query("update iroads set journeyName=$2 where dataType='trip_names' and journeyID=$1")
    List<Object> updateLatestJourneyName(String journeyID,String journeyName);

}