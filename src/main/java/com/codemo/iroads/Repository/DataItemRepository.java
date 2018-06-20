package com.codemo.iroads.Repository;

import com.codemo.iroads.Domain.DataItem;
import org.springframework.data.couchbase.core.query.*;
import org.springframework.data.couchbase.repository.CouchbaseRepository;


import java.util.List;
import java.util.Optional;

/**
 * Created by dushan on 4/23/18.
 */
@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "iroads")
public interface DataItemRepository extends CouchbaseRepository<DataItem, String> {

    Optional<DataItem> findById(String id);

    @Query("#{#n1ql.selectEntity} where dataType ='data_item'")
    List<DataItem> getAll();

    @Query("#{#n1ql.selectEntity} where dataType ='data_item' and journeyID= $1 order by time")
    List<DataItem> getDataItemByJourneyID(String journeyID);

}
