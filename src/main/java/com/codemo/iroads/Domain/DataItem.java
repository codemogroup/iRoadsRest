package com.codemo.iroads.Domain;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

import javax.validation.constraints.NotNull;

/**
 * Created by dushan on 4/23/18.
 */

@Document
public class DataItem {

    @Id
    @NotNull
    private String id;

    @Field
    private double acceX;

    @Field
    private double acceY;

    @Field
    private double acceZ;

    @Field
    private long count;

    @Field
    private String imei;

    @Field
    private String iemi;

    @Field
    private double lat;

    @Field
    private double lon;

    @Field
    private double obdRpm;

    @Field
    private double obdSpeed;

    @Field
    private long time;


    public String getId() {
        return id;
    }

    public double getAcceX() {
        return acceX;
    }

    public double getAcceY() {
        return acceY;
    }

    public double getAcceZ() {
        return acceZ;
    }

    public long getCount() {
        return count;
    }

    public String getImei() {
        return imei;
    }

    public String getIemi() {
        return iemi;
    }

    public double getLat() {
        return lat;
    }

    public double getLon() {
        return lon;
    }

    public double getObdRpm() {
        return obdRpm;
    }

    public double getObdSpeed() {
        return obdSpeed;
    }

    public long getTime() {
        return time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setAcceX(float acceX) {
        this.acceX = acceX;
    }

    public void setAcceY(float acceY) {
        this.acceY = acceY;
    }

    public void setAcceZ(float acceZ) {
        this.acceZ = acceZ;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public void setIemi(String iemi) {
        this.iemi = iemi;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public void setObdRpm(float obdRpm) {
        this.obdRpm = obdRpm;
    }

    public void setObdSpeed(float obdSpeed) {
        this.obdSpeed = obdSpeed;
    }

    public void setTime(long time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "DataItem{" +
                "id='" + id + '\'' +
                ", acceX=" + acceX +
                ", acceY=" + acceY +
                ", acceZ=" + acceZ +
                ", count=" + count +
                ", imei='" + imei + '\'' +
                ", lat=" + lat +
                ", lon=" + lon +
                ", obdRpm=" + obdRpm +
                ", obdSpeed=" + obdSpeed +
                ", time=" + time +
                '}';
    }


}

