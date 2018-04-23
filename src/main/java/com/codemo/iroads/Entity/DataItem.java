package com.codemo.iroads.Entity;

/**
 * Created by dushan on 4/23/18.
 */
public class DataItem {
    /**
     * "acceX": "0.019153614",
     "acceY": "0.2777274",
     "acceZ": "9.787497",
     "count": 419,
     "iemi": "359988060800748",
     "lat": "6.8",
     "lon": "79.901",
     "obdRpm": "0.0",
     "obdSpeed": "0.0",
     "time": 1524486375745
     */

    private float acceX;
    private float acceY;
    private float acceZ;
    private long count;
    private String imei;
    private float lat;
    private float lon;
    private float obdRpm;
    private float obdSpeed;
    private long time;

    public DataItem(float lat, float lon) {

        this.lat = lat;
        this.lon = lon;

    }
    public DataItem(float acceX, float acceY, float acceZ, long count, String imei, float lat, float lon, float obdRpm, float obdSpeed, long time) {
        this.acceX = acceX;
        this.acceY = acceY;
        this.acceZ = acceZ;
        this.count = count;
        this.imei = imei;
        this.lat = lat;
        this.lon = lon;
        this.obdRpm = obdRpm;
        this.obdSpeed = obdSpeed;
        this.time = time;
    }

    public float getAcceX() {
        return acceX;
    }

    public float getAcceY() {
        return acceY;
    }

    public float getAcceZ() {
        return acceZ;
    }

    public long getCount() {
        return count;
    }

    public String getImei() {
        return imei;
    }

    public float getLat() {
        return lat;
    }

    public float getLon() {
        return lon;
    }

    public float getObdRpm() {
        return obdRpm;
    }

    public float getObdSpeed() {
        return obdSpeed;
    }

    public long getTime() {
        return time;
    }
}
