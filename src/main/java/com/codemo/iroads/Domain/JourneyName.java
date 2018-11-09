package com.codemo.iroads.Domain;


import com.couchbase.client.java.repository.annotation.Field;

import com.couchbase.client.java.repository.annotation.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
@Getter @Setter
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
