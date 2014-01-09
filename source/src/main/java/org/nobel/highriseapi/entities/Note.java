package org.nobel.highriseapi.entities;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "note", strict = false)
public class Note extends Recording {
    @ElementList(name = "attachments", required = false)
    private List<Attachment> attachments;
}
