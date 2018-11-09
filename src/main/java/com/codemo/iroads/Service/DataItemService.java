package com.codemo.iroads.Service;

import com.codemo.iroads.Domain.DataItem;
import com.codemo.iroads.Domain.SegmentInfoWrapper;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by dushan on 4/24/18.
 */

public interface DataItemService {

    List<DataItem> getAll();

    List<DataItem> getDataItemByJourneyID(String journeyID);

    String getAccelerationGraphDataByJourneyID(String journeyID, int splitBy);

    String getGyroGraphDataByJourneyID(String journeyID, int splitBy);

    void getCsvDataItemByJourneyID(String journeyID,HttpServletResponse response) throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException;

    List<DataItem> getAverageFilterdData(String journeyID);

    SegmentInfoWrapper getJourneySegments(String journeyID, double lat, double lon, double threshold,int segmentLength);
}
