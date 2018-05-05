package com.codemo.iroads.Service;

import com.codemo.iroads.Domain.DataItem;
import com.couchbase.client.java.document.json.JsonObject;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by dushan on 4/24/18.
 */

public interface DataItemService {

    List<DataItem> getAll();

    List<DataItem> getDataItemByJourneyID(String journeyID);

    String getGraphDataByJourneyID(String journeyID);
}
