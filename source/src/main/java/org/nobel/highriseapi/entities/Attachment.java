package org.nobel.highriseapi.entities;

import org.nobel.highriseapi.entities.base.EntityWithName;
import org.simpleframework.xml.Element;

public class Attachment extends EntityWithName {

    @Element(required = true)
    private String url;

    @Element(required = true)
    private Integer size;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
