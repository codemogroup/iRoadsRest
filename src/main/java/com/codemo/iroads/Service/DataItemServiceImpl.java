package com.codemo.iroads.Service;

import com.codemo.iroads.Algorithms.SignalProcessor;
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

    @Override
    public List<DataItem> getAverageFilterdData(String journeyID) {
        List<DataItem> dataSet = dataItemDao.getDataItemByJourneyID(journeyID);
        List<DataItem> averageDataSet = null;
        SignalProcessor xProcessor = new SignalProcessor();
        SignalProcessor yProcessor = new SignalProcessor();
        SignalProcessor zProcessor = new SignalProcessor();
        for (int i = 0; i < dataSet.size(); i++){
            DataItem row = dataSet.get(i);
            DataItem averageRow = new DataItem();
            averageRow.setAcceX((float) xProcessor.averageFilter(row.getAcceX()));
            averageRow.setAcceY((float) yProcessor.averageFilter(row.getAcceY()));
            averageRow.setAcceZ((float) zProcessor.averageFilter(row.getAcceZ()));
            averageRow.setTime(row.getTime());
            averageRow.setCount(row.getCount());
            averageRow.setDataType(row.getDataType());
            averageRow.setId(row.getId());
            averageRow.setImei(row.getImei());
            averageRow.setJourneyID(row.getJourneyID());
            averageRow.setLat((float) row.getLat());
            averageRow.setLon((float) row.getLon());
            averageRow.setObdRpm((float) row.getObdRpm());
            averageRow.setObdSpeed((float) row.getObdSpeed());
            averageDataSet.add(averageRow);
        }

        return averageDataSet;
    }




}
