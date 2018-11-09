package com.codemo.iroads.Dao;

import com.codemo.iroads.Domain.IRISegmentParameter;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRIDao {

    List<IRISegmentParameter> getAll();

    IRISegmentParameter getIRIEqBySegment(int segmentLength);
}
