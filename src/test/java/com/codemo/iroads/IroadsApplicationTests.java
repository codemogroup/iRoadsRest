package com.codemo.iroads;

import com.codemo.iroads.Domain.JourneyName;
import com.codemo.iroads.Repository.NonEntityClassRepository;
import com.codemo.iroads.Service.JourneyNameService;
import com.codemo.iroads.Service.JourneyNameServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IroadsApplicationTests {

	@Autowired
	JourneyNameService journeyNameService;

	@Autowired
	NonEntityClassRepository nonEntityClassRepository;


	@Test
	public void contextLoads() {
	}


	@Test
	public void latestJournes(){
		List<JourneyName> latest = journeyNameService.getLatestJourneys("latest");
		System.out.println(latest);
	}

	@Test
	public void updateLatestJourneys(){
	    journeyNameService.renameAllLatestJourneyNames();
    }

    @Test
    public void entityTypeObj(){
        List<JourneyName> latest = nonEntityClassRepository.getLatestJourneys("latest");
        System.out.println(latest);
    }

}
