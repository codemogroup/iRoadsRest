package com.codemo.iroads.Rest;

import com.codemo.iroads.Domain.DataItem;
import com.codemo.iroads.Service.DataItemService;
import com.codemo.iroads.Service.NonEntityClassService;
import com.codemo.iroads.Util.Util;
import com.couchbase.client.java.document.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by dushan on 4/23/18.
 */

@RestController
public class RequestController {

    @Autowired
    DataItemService  dataItemService;

    @Autowired
    NonEntityClassService nonEntityClassService;


    @RequestMapping("/")
    public String root(){
        return Util.welcome();
    }

    @RequestMapping("/getJourneyIDs")
    public String getJourneyIDs(){
        return nonEntityClassService.getJourneyIds();
    }

    @RequestMapping("/getByjourneyID")
    public List<DataItem> getDataItemByJourneyID(@RequestParam("journeyID") String journeyId){
        return dataItemService.getDataItemByJourneyID(journeyId);
    }

    @RequestMapping("/getGraph")
    public String getGraphData(@RequestParam("journeyID") String journeyID){
        return dataItemService.getGraphDataByJourneyID(journeyID);
    }

    @RequestMapping("/getAll")
    public List<DataItem> getAll(){
        return dataItemService.getAll();
    }

}
