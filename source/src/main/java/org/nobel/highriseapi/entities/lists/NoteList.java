package org.nobel.highriseapi.entities.lists;

import java.util.ArrayList;
import java.util.List;

import org.nobel.highriseapi.entities.EntityList;
import org.nobel.highriseapi.entities.Note;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;


@Root(strict = false)
public class NoteList implements EntityList<Note> {

    @ElementList(inline = true, entry = "note", required = false)
    protected List<Note> notes = new ArrayList<Note>();

    public List<Note> getEntities() {
        return notes;
    }

}
