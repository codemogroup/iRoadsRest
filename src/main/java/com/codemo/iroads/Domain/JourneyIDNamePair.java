package com.codemo.iroads.Domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JourneyIDNamePair {

    private String journeyID;
    private String journeyName;
    private String syncTime;

    @Override
    public String toString() {
        return "JourneyIDNamePair{" +
                "journeyID='" + journeyID + '\'' +
                ", journeyName='" + journeyName + '\'' +
                ", syncTime='" + syncTime + '\'' +
                '}';
    }
}
