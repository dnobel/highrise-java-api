package org.nobel.highriseapi.mapper;

import org.nobel.highriseapi.entities.Company;
import org.nobel.highriseapi.entities.Party;
import org.nobel.highriseapi.entities.Person;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;


public class PartyConverter implements Converter<Party> {

    private final Serializer serializer;

    public PartyConverter(Serializer serializer) {
        this.serializer = serializer;
    }

    public Party read(InputNode node) throws Exception {

        Person contact = serializer.read(Person.class, node);

        if (contact.getType().equals("Company")) {
            Company company = new Company();
            company.setName(contact.getName());
            company.setAvatarUrl(contact.getAvatarUrl());
            company.setBackground(contact.getBackground());
            company.setContactData(contact.getContactData());
            company.setType(contact.getType());
            return company;
        }
        else {
            return contact;
        }

    }

    public void write(OutputNode node, Party party) throws Exception {
        // serialization not yet needed
    }

}
