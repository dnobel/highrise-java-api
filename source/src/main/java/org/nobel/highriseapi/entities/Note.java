package org.nobel.highriseapi.entities;

import java.util.Date;

import org.nobel.highriseapi.entities.base.Entity;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Transient;


@Root(name = "note", strict = false)
public class Note extends Entity {

    private static final long serialVersionUID = 8283721356355584590L;

    @Transient
    private User author;

    @Element(name = "author-id", required = false)
    private Integer authorId;

    @Element
    private String body;

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

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
