package com.codemo.iroads.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Tag {

    private String journeyID;

    private String lat;

    private String lon;

    private String tagType;

    private String time;

    @Override
    public String toString() {
        return "Tag{" +
                "journeyID='" + journeyID + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", tagType='" + tagType + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
