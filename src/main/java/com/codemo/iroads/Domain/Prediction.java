package com.codemo.iroads.Domain;

import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import lombok.*;
import org.springframework.data.couchbase.core.mapping.Document;

import java.util.List;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
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

}
