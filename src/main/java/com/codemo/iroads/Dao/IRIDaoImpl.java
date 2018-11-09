package com.codemo.iroads.Dao;

import com.codemo.iroads.Domain.IRISegmentParameter;
import com.codemo.iroads.Domain.Prediction;
import com.codemo.iroads.Repository.IRIDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IRIDaoImpl implements IRIDao {

    @Autowired
    IRIDataRepository iriDataRepository;

    @Override
    public List<IRISegmentParameter> getAll() {
        List<IRISegmentParameter> allSegmentParameters = iriDataRepository.getAllSegmentParameters();
        return allSegmentParameters;
    }

    @Override
    public IRISegmentParameter getIRIEqBySegment(int segmentLength) {
        IRISegmentParameter iriEqBySegment = iriDataRepository.getIRIEqBySegment(segmentLength);
        return iriEqBySegment;
    }
}
