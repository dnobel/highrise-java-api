package org.nobel.highriseapi.entities;

import java.util.List;

import org.nobel.highriseapi.entities.base.EntityWithName;
import org.simpleframework.xml.ElementList;


public class Case extends EntityWithName {

    @ElementList(required = false)
    List<Party> parties;

    public List<Party> getParties() {
        return parties;
    }

    public void setParties(List<Party> parties) {
        this.parties = parties;
    }

}
