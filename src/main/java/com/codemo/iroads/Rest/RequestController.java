package com.codemo.iroads.Rest;

import com.codemo.iroads.Domain.DataItem;
import com.codemo.iroads.Service.DataItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by dushan on 4/23/18.
 */

@RestController
public class RequestController {

    @Autowired
    DataItemService  dataItemService;

    @RequestMapping("/")
    public String root(){
        return "iRoads rest api";
    }

    @RequestMapping("/getall")
    public List<DataItem> getAll(){
        return dataItemService.getAll();
    }


}
