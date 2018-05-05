package com.codemo.iroads.Repository;

import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dushan on 5/4/18.
 */
public class AbstractN1qlRunner {

    @Autowired
    CouchbaseTemplate couchbaseTemplate;

    public List<JsonObject> getJsonArray(String queryStr){
        N1qlQuery query=N1qlQuery.simple(queryStr);
        List<N1qlQueryRow> n1qlQueryRows = couchbaseTemplate.queryN1QL(query).allRows();
        List<JsonObject> jsonObjects=new ArrayList<>();
        for (N1qlQueryRow row : n1qlQueryRows) {
            jsonObjects.add(row.value());
        }
        return jsonObjects;
    }
}
