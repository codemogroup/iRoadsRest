package com.codemo.iroads.Rest;

import com.codemo.iroads.Domain.DataItem;
import com.codemo.iroads.Domain.JourneyIDNamePair;
import com.codemo.iroads.Domain.Tag;
import com.codemo.iroads.Service.DataItemService;
import com.codemo.iroads.Service.NonEntityClassService;
import com.codemo.iroads.Util.Util;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @RequestMapping("/getJourneyNames")
    public List<JourneyIDNamePair> getJourneyNames(){
        return nonEntityClassService.getJourneyNameObjects();
    }

    @RequestMapping("/getByjourneyID")
    public List<DataItem> getDataItemByJourneyID(@RequestParam("journeyID") String journeyID){
        return dataItemService.getDataItemByJourneyID(journeyID);
    }

    @RequestMapping("/getLocationsByjourneyID")
    public String getLocationsByjourneyID(@RequestParam("journeyID") String journeyID){
        return nonEntityClassService.getLocationsByjourneyID(journeyID);
    }

    @RequestMapping("/getAverageByjourneyID")
    public List<DataItem> getDataAverageByJourneyID(@RequestParam("journeyID") String journeyID){
        return dataItemService.getAverageFilterdData(journeyID);
    }

    @RequestMapping(value="/getCsvByjourneyID" ,produces = "text/csv")
    public void getCsvDataItemByJourneyID(@RequestParam("journeyID") String journeyID, HttpServletResponse response) throws CsvRequiredFieldEmptyException, IOException, CsvDataTypeMismatchException {
        dataItemService.getCsvDataItemByJourneyID(journeyID,response);
    }

    @RequestMapping("/getGraph")
    public String getGraphData(@RequestParam("journeyID") String journeyID){
        return dataItemService.getGraphDataByJourneyID(journeyID);
    }

    @RequestMapping("/getTags")
    public List<Tag> getTags(@RequestParam("journeyID") String journeyID){
        return nonEntityClassService.getTagsByJourneyID(journeyID);
    }

    @RequestMapping("/getAll")
    public List<DataItem> getAll(){
        return dataItemService.getAll();
    }

}
