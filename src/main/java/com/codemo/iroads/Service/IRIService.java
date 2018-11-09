package com.codemo.iroads.Service;

import com.codemo.iroads.Domain.IRISegmentParameter;

import java.util.List;

public interface IRIService {

    List<IRISegmentParameter> getAll();

    IRISegmentParameter getIRIEqBySegment(int segmentLength);
}
