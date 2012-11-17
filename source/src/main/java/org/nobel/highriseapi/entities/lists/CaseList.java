package org.nobel.highriseapi.entities.lists;

import java.util.ArrayList;
import java.util.List;

import org.nobel.highriseapi.entities.Case;
import org.nobel.highriseapi.entities.EntityList;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


@Root(strict = false)
public class CaseList implements EntityList<Case> {

    @ElementList(inline = true, entry = "kase", required = false)
    protected List<Case> cases = new ArrayList<Case>();

    public List<Case> getEntities() {
        return cases;
    }

}
