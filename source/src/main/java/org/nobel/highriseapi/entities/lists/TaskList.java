package org.nobel.highriseapi.entities.lists;

import java.util.ArrayList;
import java.util.List;

import org.nobel.highriseapi.entities.EntityList;
import org.nobel.highriseapi.entities.Task;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


@Root(strict = false)
public class TaskList implements EntityList<Task> {

    @ElementList(inline = true, entry = "task", required = false)
    protected List<Task> tasks = new ArrayList<Task>();

    public List<Task> getEntities() {
        return tasks;
    }

}
