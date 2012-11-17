package org.nobel.highriseapi.mapper;

import java.util.Date;

import org.simpleframework.xml.transform.Matcher;
import org.simpleframework.xml.transform.Transform;

public class HighriseTypeMatcher implements Matcher {

    @SuppressWarnings("rawtypes")
    public Transform match(Class type) throws Exception {
        if (type.equals(Date.class)) {
            return new DateTransform();
        }
        return null;
    }

}
