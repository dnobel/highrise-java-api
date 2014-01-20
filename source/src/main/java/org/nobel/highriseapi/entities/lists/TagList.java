package org.nobel.highriseapi.entities.lists;

import org.nobel.highriseapi.entities.EntityList;
import org.nobel.highriseapi.entities.Tag;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(strict = false)
public class TagList implements EntityList<Tag> {

    @ElementList(inline = true, entry = "tag", required = false)
    protected List<Tag> tags = new ArrayList<Tag>();

    public List<Tag> getEntities() {
        return tags;
    }

}
