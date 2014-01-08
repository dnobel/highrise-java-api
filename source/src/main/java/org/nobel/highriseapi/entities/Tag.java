package org.nobel.highriseapi.entities;

import org.nobel.highriseapi.entities.base.Entity;
import org.simpleframework.xml.Element;

public class Tag extends Entity {

    @Element
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
