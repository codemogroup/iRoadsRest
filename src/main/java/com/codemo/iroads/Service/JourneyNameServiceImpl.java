package com.codemo.iroads.Service;

import com.codemo.iroads.Dao.JourneyNameDao;
import com.codemo.iroads.Dao.NonEntityClassDao;
import com.codemo.iroads.Domain.JourneyName;
import com.codemo.iroads.Domain.PositionAddress;
import com.couchbase.client.java.document.json.JsonObject;
import javafx.beans.binding.StringBinding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

@Service
public class JourneyNameServiceImpl implements JourneyNameService {

    @Autowired
    JourneyNameDao journeyNameDao;

    Logger logger = LoggerFactory.getLogger(JourneyNameServiceImpl.class);

    RestTemplate restTemplate = new RestTemplate();


    @Scheduled(cron = "0 0/10 * * * *")  //second, minute, hour, day of month, month, day(s) of week
    private void ScheduleTaskForRenamingLatestJourneys(){

        /*
         * job to run every 5 minuites
         */

        System.out.println("::Running scheduled job..");

        renameAllLatestJourneyNames();

    }


    @Override
    public List<JourneyName> getLatestJourneys(String latestJourneyName) {
        List<JourneyName> latestJourneys = journeyNameDao.getLatestJourneys(latestJourneyName);

        for (JourneyName jn:latestJourneys){
            System.out.println("jid="+jn.getJourneyName());
        }
        return latestJourneys;
    }

    @Override
    public List<Object> updateLatestJourneyName(String journeyID, String journeyName) {
        List<Object> objects = journeyNameDao.updateLatestJourneyName(journeyID, journeyName);
        return objects;
    }



    @Override
    public void renameAllLatestJourneyNames(){

        List<JourneyName> latestJourneys = getLatestJourneys("latest");

        if (latestJourneys.size()>0) {
            for (JourneyName journeyName : latestJourneys) {
                System.out.println("updating jid="+journeyName.getJourneyID());
                renameLatestJourney(journeyName.getJourneyID(), journeyName.getStartLat(), journeyName.getStartLon());
            }
        }else {
            logger.info(" ** "+"No latest journeys to update name");
        }
    }


    public void renameLatestJourney(String journeyID,String lat,String lon){

        StringBuilder sb = new StringBuilder();
        sb.append("https://nominatim.openstreetmap.org/reverse?")
                .append("format=").append("jsonv2").append("&")
                .append("lat=").append(lat).append("&")
                .append("lon=").append(lon);

        try{

            ResponseEntity<PositionAddress> exchange = restTemplate.exchange(sb.toString(),
                    HttpMethod.GET, getHttpEntity(), PositionAddress.class);

            String display_name = exchange.getBody().getDisplay_name();
            if (display_name!=null) {
                StringTokenizer st = new StringTokenizer(display_name, ",");

                String name = st.nextToken() + (st.hasMoreTokens() ? st.nextToken() : "");

                List<Object> objects = updateLatestJourneyName(journeyID, name);

                logger.info(" ** "+"updated name of " + lat + ", " + lon + " to " + name);
            }else {
                logger.info(" ** "+"fail updating name name of " + lat + ", " + lon + " to " + "display_name=null");
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }

    private HttpEntity getHttpEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko)" +
                        " Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        return entity;
    }


}
