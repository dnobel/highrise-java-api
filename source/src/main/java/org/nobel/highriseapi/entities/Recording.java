package org.nobel.highriseapi.entities;

import java.util.Date;

import org.nobel.highriseapi.entities.base.Entity;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Transient;

public class Recording extends Entity {

    public enum SubjectType {
        Deal,
        Kase,
    }

    @Transient
    private User author;

    @Element(name = "author-id", required = false)
    private int authorId;

    @Element
    private String body;

    @Element(name = "created-at", required = false)
    private Date createdAt;

    @Element(name = "subject-id")
    private int subjectId;

    @Element(name = "subject-name")
    private String subjectName;

    @Element(name = "subject-type")
    private SubjectType subjectType;

    @Element(name = "updated-at", required = false)
    private Date updatedAt;

    public User getAuthor() {
        return author;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public String getBody() {
        return body;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public int getSubjectId() {
        return subjectId;
    }


    public String getSubjectName() {
        return subjectName;
    }

    public SubjectType getSubjectType() {
        return subjectType;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
