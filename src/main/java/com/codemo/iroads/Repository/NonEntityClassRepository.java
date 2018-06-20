package com.codemo.iroads.Repository;

import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dushan on 5/4/18.
 */
@Repository
public class NonEntityClassRepository extends AbstractN1qlRunner{

    public List<JsonObject> getJourneyIds(){

        String query="select i.journeyID from iroads i where journeyID is not missing and dataType='data_item' group by i.journeyID";
        return getJsonArray(query);
    }

    public List<JsonObject> getJourneyNameObjects(){

        String query="select journeyID,journeyName from iroads where dataType='trip_names' order by journeyName";
        return getJsonArray(query);
    }



}
