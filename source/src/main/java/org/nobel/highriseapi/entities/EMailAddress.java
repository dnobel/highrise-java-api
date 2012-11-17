package org.nobel.highriseapi.entities;

import org.nobel.highriseapi.entities.base.Entity;
import org.simpleframework.xml.Element;


public class EMailAddress extends Entity {

    private static final long serialVersionUID = 463970472423612624L;

    @Element
    private String address;

    @Element
    private String location;

    public String getAddress() {
        return address;
    }

    public String getLocation() {
        return location;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
