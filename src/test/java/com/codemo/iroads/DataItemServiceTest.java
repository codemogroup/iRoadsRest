package com.codemo.iroads;

import com.codemo.iroads.Service.DataItemService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataItemServiceTest {


    @Autowired
    DataItemService dataItemService;


    @Test
    public void getJourneySegmentsTest(){
//        lat=6.839059600893724&lon=79.97806549072267
        dataItemService.getJourneySegments("3599880608007481537609916152",6.839059600893724,79.97806549072267, 1.0,100);
    }
}
