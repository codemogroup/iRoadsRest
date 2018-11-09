package com.codemo.iroads.Repository;

import com.codemo.iroads.Domain.DataItem;
import com.codemo.iroads.Domain.IRISegmentParameter;
import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.couchbase.repository.CouchbaseRepository;

import java.util.List;

@N1qlPrimaryIndexed
@ViewIndexed(designDoc = "iroads")
public interface IRIDataRepository extends CouchbaseRepository<IRISegmentParameter, String> {

    @Query("#{#n1ql.selectEntity} where dataType = 'iri_equation' ")
    List<IRISegmentParameter> getAllSegmentParameters();

    @Query("#{#n1ql.selectEntity} where dataType = 'iri_equation' and segmentLength= $1 ")
    IRISegmentParameter getIRIEqBySegment(int segmentLength);
}
