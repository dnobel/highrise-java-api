package org.nobel.highriseapi.mapper;

import org.nobel.highriseapi.entities.Comment;
import org.nobel.highriseapi.entities.EMail;
import org.nobel.highriseapi.entities.Note;
import org.nobel.highriseapi.entities.Recording;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

public class RecordingConverter implements Converter<Recording> {

    private final Serializer serializer;

    public RecordingConverter(Serializer serializer) {
        this.serializer = serializer;
    }

    public Recording read(InputNode node) throws Exception {
        InputNode attribute = node.getAttribute("type");

        if (attribute == null) {
            return null;
        }

        String type = attribute.getValue();

        if (type.equals("Note")) {
            return serializer.read(Note.class, node);
        }
        if (type.equals("Comment")) {
            return serializer.read(Comment.class, node);
        }
        if (type.equals("Email")) {
            return serializer.read(EMail.class, node);
        }
        return null;
    }

    public void write(OutputNode arg0, Recording arg1) throws Exception {
        // serialization not yet needed
    }

}
