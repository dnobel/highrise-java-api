package org.nobel.highriseapi.entities;

import java.util.Date;

import org.simpleframework.xml.Element;

public class EMail extends Recording {

    @Element
    private String title;

    @Element(name = "updated-at", required = false)
    private Date updatedAt;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
