package com.codemo.iroads.Domain;


import com.couchbase.client.java.repository.annotation.Field;

import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
public class JourneyName {

    @Id
    private String id;

    @Field
    private String journeyID;

    @Field
    private String journeyName;

    @Field
    private String startLat;

    @Field
    private String startLon;

    @Field
    private String dataType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getJourneyID() {
        return journeyID;
    }

    public void setJourneyID(String journeyID) {
        this.journeyID = journeyID;
    }

    public String getJourneyName() {
        return journeyName;
    }

    public void setJourneyName(String journeyName) {
        this.journeyName = journeyName;
    }

    public String getStartLat() {
        return startLat;
    }

    public void setStartLat(String startLat) {
        this.startLat = startLat;
    }

    public String getStartLon() {
        return startLon;
    }

    public void setStartLon(String startLon) {
        this.startLon = startLon;
    }

    @Override
    public String toString() {
        return "JourneyName{" +
                "journeyID='" + journeyID + '\'' +
                ", journeyName='" + journeyName + '\'' +
                ", startLat='" + startLat + '\'' +
                ", startLon='" + startLon + '\'' +
                ", dataType='" + dataType + '\'' +
                '}';
    }
}
