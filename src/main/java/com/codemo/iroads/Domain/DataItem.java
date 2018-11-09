package com.codemo.iroads.Domain;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.couchbase.core.mapping.Document;

/**
 * Created by dushan on 4/23/18.
 */

@Document
@Getter @Setter
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
        return getVerticalAccelerationAbsolute()-threshold>0;
    }

    public int getTimeSegmentNum(long zerothTime,int splitBy){
        double timeToPoint=(this.time-zerothTime)/1000.0;
        int num=0;

        if (timeToPoint==0){
            return 0;
        }

        if(timeToPoint%splitBy==0){
            num= (int) (timeToPoint/splitBy);
            num--;
        }
        else {
            num = (int) (timeToPoint / splitBy);
        }
        return num;
    }

}

