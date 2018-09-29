package com.codemo.iroads.Domain;

public class Tag {

    private String journeyID;

    private String lat;

    private String lon;

    private String tagType;

    private String time;

    public String getJourneyID() {
        return journeyID;
    }

    public void setJourneyID(String journeyID) {
        this.journeyID = journeyID;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

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
