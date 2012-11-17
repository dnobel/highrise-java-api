package org.nobel.highriseapi.entities.base;

import java.io.Serializable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false)
public abstract class Entity implements Serializable {

    private static final long serialVersionUID = -130863263861782922L;

    @Element(required = false)
    private Integer id;

    public Integer getId() {
        return id;
    }
}
