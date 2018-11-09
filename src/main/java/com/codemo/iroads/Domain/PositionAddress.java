package com.codemo.iroads.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter
public class PositionAddress {

    private String name;
    private String addresstype;
    private String display_name;

    @Override
    public String toString() {
        return "PositionAddress{" +
                "name='" + name + '\'' +
                ", addresstype='" + addresstype + '\'' +
                ", display_name='" + display_name + '\'' +
                '}';
    }
}
