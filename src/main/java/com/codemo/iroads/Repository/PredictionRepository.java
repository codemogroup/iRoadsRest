package com.codemo.iroads.Repository;

import com.codemo.iroads.Domain.DataItem;
import com.codemo.iroads.Domain.Prediction;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

import java.util.List;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "iroads")
public interface PredictionRepository extends CouchbaseRepository<Prediction, String> {

    @Query("#{#n1ql.selectEntity} where dataType = 'prediction' and  predictionGroup = $1")
    List<Prediction> getPredictionsByGroupId(String groupId);

    @Query("#{#n1ql.selectEntity} where dataType = 'prediction' ")
    List<Prediction> getAllPredictions();

}
