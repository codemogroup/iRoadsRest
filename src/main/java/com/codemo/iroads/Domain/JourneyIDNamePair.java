package com.codemo.iroads.Domain;

public class JourneyIDNamePair {

    private String journeyID;
    private String journeyName;
    private String syncTime;

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

    public String getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(String syncTime) {
        this.syncTime = syncTime;
    }

    @Override
    public String toString() {
        return "JourneyIDNamePair{" +
                "journeyID='" + journeyID + '\'' +
                ", journeyName='" + journeyName + '\'' +
                ", syncTime='" + syncTime + '\'' +
                '}';
    }
}
