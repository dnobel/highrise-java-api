package org.nobel.highriseapi.entities.lists;

import java.util.List;

import org.nobel.highriseapi.entities.EntityList;
import org.nobel.highriseapi.entities.User;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


@Root(strict = false)
public class UserList implements EntityList<User> {

    @ElementList(inline = true, entry = "user")
    private List<User> entities;

    public List<User> getEntities() {
        return entities;
    }

}
