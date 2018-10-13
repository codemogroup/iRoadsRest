package com.codemo.iroads.Domain;

import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Prediction {

    @Id
    String id;

    @Field
    private String predictionGroup;

    @Field
    private String subName;

    @Field
    private List<PredictionPoint> data;

    @Field
    private String dataType="prediction";


    public Prediction(String predictionGroup, String subName, List<PredictionPoint> data) {
        this.id=predictionGroup+subName+System.currentTimeMillis();
        this.predictionGroup = predictionGroup;
        this.subName = subName;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPredictionGroup() {
        return predictionGroup;
    }

    public void setPredictionGroup(String predictionGroup) {
        this.predictionGroup = predictionGroup;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public List<PredictionPoint> getData() {
        return data;
    }

    public void setData(List<PredictionPoint> data) {
        this.data = data;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
}
