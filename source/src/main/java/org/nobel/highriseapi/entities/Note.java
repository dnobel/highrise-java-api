package org.nobel.highriseapi.entities;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Transient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

@Root(name = "note", strict = false)
public class Note extends Recording {
    @ElementList(name = "attachments", required = false)
    private List<Attachment> attachments;

    @Transient
    private List<UploadAttachment> uploadAttachments;

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setUploadAttachments(List<UploadAttachment> uploadAttachments) {
        this.uploadAttachments = uploadAttachments;
    }

    public boolean hasUploadAttachments() {
        return (this.uploadAttachments != null) && (!this.uploadAttachments.isEmpty());
    }

    public MultiValueMap<String, Object> toMultiValueMap() {
        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<String, Object>();
        parts.add("note[body]", getBody());
        for (UploadAttachment upload : uploadAttachments) {
            parts.add("note[files][]", upload.getResource());
        }
        return parts;
    }
}