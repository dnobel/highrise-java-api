package org.nobel.highriseapi.entities.base;

import org.simpleframework.xml.Element;

public abstract class EntityWithName extends Entity {

    private static final long serialVersionUID = -2606364303635661790L;

    @Element(required = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
