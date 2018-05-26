package com.codemo.iroads.Service;

import com.codemo.iroads.Dao.DataItemDao;
import com.codemo.iroads.Domain.DataItem;
import com.codemo.iroads.Util.CSV_Writer;
import com.codemo.iroads.Util.Util;
import com.couchbase.client.java.document.json.JsonObject;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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

    @Override
    public void getCsvDataItemByJourneyID(String journeyID,HttpServletResponse response) throws IOException {
        List<DataItem> dataItems= dataItemDao.getDataItemByJourneyID(journeyID);
        String fileName=DateTime.now()+"_"+journeyID+".csv";
        response.setContentType ("application/csv");
        response.setHeader ("Content-Disposition",
                "attachment; filename="+fileName);
        CSV_Writer.writeToCsv(response.getWriter(),dataItems);
    }


}
