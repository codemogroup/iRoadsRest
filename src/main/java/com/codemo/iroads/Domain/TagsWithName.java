package com.codemo.iroads.Domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @AllArgsConstructor
public class TagsWithName {

    private String journeyName;
    private String journeyID;
    private List<TagPoint> tags;


}
