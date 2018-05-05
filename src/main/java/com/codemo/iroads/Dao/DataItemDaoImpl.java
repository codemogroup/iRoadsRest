package com.codemo.iroads.Dao;

import com.codemo.iroads.Domain.DataItem;
import com.codemo.iroads.Repository.DataItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by dushan on 4/25/18.
 */

@Component
public class DataItemDaoImpl implements DataItemDao {

    @Autowired
    DataItemRepository dataItemRepository;

    @Override
    public List<DataItem> getAll() {
        return dataItemRepository.getAll();
    }

    @Override
    public List<DataItem> getDataItemByJourneyID(String journeyID) {
        List<DataItem> dataItemByJourneyID = dataItemRepository.getDataItemByJourneyID(journeyID);
        return dataItemByJourneyID;
    }

}