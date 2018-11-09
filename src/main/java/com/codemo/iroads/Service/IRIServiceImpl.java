package com.codemo.iroads.Service;

import com.codemo.iroads.Dao.IRIDao;
import com.codemo.iroads.Domain.IRISegmentParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IRIServiceImpl implements IRIService {

    @Qualifier("IRIDaoImpl")
    @Autowired
    IRIDao iriDao;

    @Override
    public List<IRISegmentParameter> getAll() {
        List<IRISegmentParameter> all = iriDao.getAll();
        return all;
    }

    @Override
    public IRISegmentParameter getIRIEqBySegment(int segmentLength) {
        IRISegmentParameter iriEqBySegment = iriDao.getIRIEqBySegment(segmentLength);
        return iriEqBySegment;
    }
}
