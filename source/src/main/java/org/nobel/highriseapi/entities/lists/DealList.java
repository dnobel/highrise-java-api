package org.nobel.highriseapi.entities.lists;

import java.util.ArrayList;
import java.util.List;

import org.nobel.highriseapi.entities.Deal;
import org.nobel.highriseapi.entities.EntityList;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


@Root(strict = false)
public class DealList implements EntityList<Deal> {

    @ElementList(inline = true, entry = "deal", required = false)
    protected List<Deal> deals = new ArrayList<Deal>();

    public List<Deal> getEntities() {
        return deals;
    }

}
