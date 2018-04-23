package com.codemo.iroads;

import com.codemo.iroads.Entity.DataItem;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by dushan on 4/23/18.
 */

@RestController
public class RequestController {

    @RequestMapping("/")
    public String root(){
        return "iRoads rest api";
    }

    @RequestMapping("/dataitem")
    public DataItem getObj(@RequestParam(value="lat", defaultValue="0") float lat,
                           @RequestParam(value = "lon", defaultValue = "0") float lon){
        return new DataItem(lat,lon);
    }
}
