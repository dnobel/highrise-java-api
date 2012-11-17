package org.nobel.highriseapi.entities;

import org.nobel.highriseapi.entities.base.Entity;
import org.simpleframework.xml.Element;


public class PhoneNumber extends Entity {

    @Element
    private String location;

    @Element
    private String number;

    public String getLocation() {
        return location;
    }

    public String getNumber() {
        return number;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setNumber(String number) {
        this.number = number;
    }

}
