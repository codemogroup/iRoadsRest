package com.codemo.iroads.Service;

import com.codemo.iroads.Dao.DataItemDao;
import com.codemo.iroads.Domain.DataItem;
import com.codemo.iroads.Util.Util;
import com.couchbase.client.java.document.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

/**
 * Created by dushan on 4/25/18.
 */

@Service
public class DataItemServiceImpl implements DataItemService {

    @Autowired
    DataItemDao dataItemDao;

    @Override
    public List<DataItem> getAll() {
        List<DataItem> dataItems= dataItemDao.getAll();
        return dataItems;
    }

    @Override
    public List<DataItem> getDataItemByJourneyID(String journeyID) {
        List<DataItem> dataItems= dataItemDao.getDataItemByJourneyID(journeyID);
        return dataItems;
    }

    @Override
    public String getGraphDataByJourneyID(String journeyID) {
        List<DataItem> dataItems= dataItemDao.getDataItemByJourneyID(journeyID);

        List<JsonObject> graphData= Util.convertDataItemToGraphAxes(dataItems);
        return graphData.toString();
    }


}
