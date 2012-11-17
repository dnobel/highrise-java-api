package org.nobel.highriseapi.entities;

import java.util.Date;

import org.nobel.highriseapi.entities.base.Entity;
import org.simpleframework.xml.Element;


public class Task extends Entity {

    @Element(required = false)
    private String body;

    @Element(required = false)
    private Date doneAt;

    @Element(required = false)
    private String frame;

    @Element(name = "subject-name", required = false)
    private String subjectName;

    public String getBody() {
        return body;
    }

    public String getFrame() {
        return frame;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public boolean isComplete() {
        return doneAt != null;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setFrame(String frame) {
        this.frame = frame;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

}
