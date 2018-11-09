package com.codemo.iroads.Domain;

import com.couchbase.client.java.repository.annotation.Field;
import com.couchbase.client.java.repository.annotation.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.couchbase.core.mapping.Document;

@Document
@Getter
@Setter
public class IRISegmentParameter {

    @Id
    private String id;

    @Field
    private double c;

    @Field
    private double m;

    @Field
    private double threshold;

    @Field
    private int segmentLength;

    @Field
    private String dataType;


}
