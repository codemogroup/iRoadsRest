package com.codemo.iroads.Domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PositionAddress {

    private String name;
    private String addresstype;
    private String display_name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddresstype() {
        return addresstype;
    }

    public void setAddresstype(String addresstype) {
        this.addresstype = addresstype;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    @Override
    public String toString() {
        return "PositionAddress{" +
                "name='" + name + '\'' +
                ", addresstype='" + addresstype + '\'' +
                ", display_name='" + display_name + '\'' +
                '}';
    }
}
