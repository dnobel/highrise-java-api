package org.nobel.highriseapi.entities.lists;

import org.nobel.highriseapi.entities.EntityList;
import org.nobel.highriseapi.entities.Party;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;
import java.util.List;

@Root(strict = false)
public class PartyList implements EntityList<Party> {

    @ElementList(inline = true, entry = "party", required = false)
    protected List<Party> entities = new ArrayList<Party>();

    public List<Party> getEntities() {
        return entities;
    }

}
