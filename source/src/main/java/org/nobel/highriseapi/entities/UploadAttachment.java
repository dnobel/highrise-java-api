package org.nobel.highriseapi.entities;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class UploadAttachment {
    private String filename;

    public UploadAttachment(String filename) {
        this.filename = filename;
    }

    public Resource getResource() {
        return new FileSystemResource(filename);
    }
}
