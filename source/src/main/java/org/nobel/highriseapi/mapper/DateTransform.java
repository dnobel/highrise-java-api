package org.nobel.highriseapi.mapper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.simpleframework.xml.transform.Transform;

public class DateTransform implements Transform<Date> {

    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");

    public Date read(String value) throws Exception {
        return formatter.parse(value);
    }

    public String write(Date date) throws Exception {
        return formatter.format(date);
    }

}
