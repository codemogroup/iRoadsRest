package com.codemo.iroads.Domain;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;

/**
 * Created by dushan on 4/23/18.
 */

@Document
public class DataItem {

    private static final double GRAVITY = 9.8;

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
    private String magnetX;

    @Field
    private String magnetY;

    @Field
    private String magnetZ;

    @Field
    private String gyroX;

    @Field
    private String gyroY;

    @Field
    private String gyroZ;


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

    public String getMagnetX() {
        return magnetX;
    }

    public void setMagnetX(String magnetX) {
        this.magnetX = magnetX;
    }

    public String getMagnetY() {
        return magnetY;
    }

    public void setMagnetY(String magnetY) {
        this.magnetY = magnetY;
    }

    public String getMagnetZ() {
        return magnetZ;
    }

    public void setMagnetZ(String magnetZ) {
        this.magnetZ = magnetZ;
    }

    public String getGyroX() {
        return gyroX;
    }

    public void setGyroX(String gyroX) {
        this.gyroX = gyroX;
    }

    public String getGyroY() {
        return gyroY;
    }

    public void setGyroY(String gyroY) {
        this.gyroY = gyroY;
    }

    public String getGyroZ() {
        return gyroZ;
    }

    public void setGyroZ(String gyroZ) {
        this.gyroZ = gyroZ;
    }

    public double getGpsSpeed() {
        return gpsSpeed;
    }

    public void setGpsSpeed(double gpsSpeed) {
        this.gpsSpeed = gpsSpeed;
    }

    /**
     * absolute value of vertical reoriented acceleration without gravity
     *
     * @return
     *
     */
    public double getVerticalAccelerationAbsolute(){
        double value= Math.abs(acceY-GRAVITY);
        return value;
    }

    /**
     * rms value of all 3 reoriented accelerations
     *
     * @return
     */
    public double getRmsAcceleration(){
        double value= Math.sqrt(Math.pow(acceX,2)+Math.pow(acceY-GRAVITY,2)+Math.pow(acceZ,2));
        return value;
    }

    /**
     * threshold based identification
     *
     * @param threshold
     * @return
     */
    public boolean isAccelYAboveThreshold(double threshold){
        return acceY-threshold>0;
    }

}

