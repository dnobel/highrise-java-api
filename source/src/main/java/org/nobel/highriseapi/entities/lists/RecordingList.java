package org.nobel.highriseapi.entities.lists;

import java.util.ArrayList;
import java.util.List;

import org.nobel.highriseapi.entities.EntityList;
import org.nobel.highriseapi.entities.Recording;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class RecordingList implements EntityList<Recording> {

    @ElementList(inline = true, entry = "recording")
    protected List<Recording> entities = new ArrayList<Recording>();

    public List<Recording> getEntities() {
        return entities;
    }
}
