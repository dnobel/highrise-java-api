package org.nobel.highriseapi.entities;

import org.simpleframework.xml.Element;

public class Comment extends Recording {


    @Element(name = "parent-id", required = false)
    private int parentId;

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }



}
