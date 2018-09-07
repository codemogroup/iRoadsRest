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
    private String id;

    @Field
    private double acceX;

    @Field
    private double acceY;

    @Field
    private double acceZ;

    @Field
    private double acceX_raw;

    @Field
    private double acceY_raw;

    @Field
    private double acceZ_raw;

    @Field
    private double magnetX;

    @Field
    private double magnetY;

    @Field
    private double magnetZ;

    @Field
    private double gyroX;

    @Field
    private double gyroY;

    @Field
    private double gyroZ;


    @Field
    private double gpsSpeed;

    @Field
    private long count;

    @Field
    private String imei;

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

    @Field
    private String journeyID;

    @Field
    private String dataType;


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

    public String getJourneyID() {
        return journeyID;
    }

    public String getDataType() {
        return dataType;
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

    public void setJourneyID(String journeyID) {
        this.journeyID = journeyID;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public double getAcceX_raw() {
        return acceX_raw;
    }

    public void setAcceX_raw(double acceX_raw) {
        this.acceX_raw = acceX_raw;
    }

    public double getAcceY_raw() {
        return acceY_raw;
    }

    public void setAcceY_raw(double acceY_raw) {
        this.acceY_raw = acceY_raw;
    }

    public double getAcceZ_raw() {
        return acceZ_raw;
    }

    public void setAcceZ_raw(double acceZ_raw) {
        this.acceZ_raw = acceZ_raw;
    }

    public double getMagnetX() {
        return magnetX;
    }

    public void setMagnetX(double magnetX) {
        this.magnetX = magnetX;
    }

    public double getMagnetY() {
        return magnetY;
    }

    public void setMagnetY(double magnetY) {
        this.magnetY = magnetY;
    }

    public double getMagnetZ() {
        return magnetZ;
    }

    public void setMagnetZ(double magnetZ) {
        this.magnetZ = magnetZ;
    }

    public double getGyroX() {
        return gyroX;
    }

    public void setGyroX(double gyroX) {
        this.gyroX = gyroX;
    }

    public double getGyroY() {
        return gyroY;
    }

    public void setGyroY(double gyroY) {
        this.gyroY = gyroY;
    }

    public double getGyroZ() {
        return gyroZ;
    }

    public void setGyroZ(double gyroZ) {
        this.gyroZ = gyroZ;
    }

    public double getGpsSpeed() {
        return gpsSpeed;
    }

    public void setGpsSpeed(double gpsSpeed) {
        this.gpsSpeed = gpsSpeed;
    }
}

