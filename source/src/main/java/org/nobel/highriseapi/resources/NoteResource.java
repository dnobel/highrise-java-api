package org.nobel.highriseapi.resources;

import org.nobel.highriseapi.entities.Person;
import org.nobel.highriseapi.entities.lists.PersonList;
import org.nobel.highriseapi.resources.base.EntityCacheProvider;
import org.nobel.highriseapi.resources.base.EntityResource;
import org.nobel.highriseapi.resources.base.RestResourceConfig;

public class NoteResource extends EntityResource<Person> {

    public NoteResource(String baseUrl, String token, EntityCacheProvider entityCacheProvider) {
        super(baseUrl, token, entityCacheProvider);
    }

    @Override
    protected RestResourceConfig getEntityConfig() {
        return createResourceConfig("notes/{id}.xml", Person.class);
    }

    @Override
    protected RestResourceConfig getEntityListConfig() {
        return createResourceConfig("notes.xml", PersonList.class);
    }

}
