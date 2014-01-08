package org.nobel.highriseapi.entities.lists;

import java.util.ArrayList;
import java.util.List;

import org.nobel.highriseapi.entities.EntityList;
import org.nobel.highriseapi.entities.Person;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


@Root(strict = false)
public class PersonList implements EntityList<Person> {

    @ElementList(inline = true, entry = "person", required = false)
    protected List<Person> entities = new ArrayList<Person>();

    public List<Person> getEntities() {
        return entities;
    }

}
