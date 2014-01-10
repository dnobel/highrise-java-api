package org.nobel.highriseapi.entities;

import org.nobel.highriseapi.entities.base.Entity;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import java.io.Serializable;

@Root(name = "name")
public class CreateTag implements Serializable {

    @Text
    private String name;

    public CreateTag(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
