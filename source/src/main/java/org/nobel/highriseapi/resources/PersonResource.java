package org.nobel.highriseapi.resources;

import org.nobel.highriseapi.HighriseClientConfig;
import org.nobel.highriseapi.entities.Person;
import org.nobel.highriseapi.entities.lists.PersonList;
import org.nobel.highriseapi.resources.base.EntityCacheProvider;
import org.nobel.highriseapi.resources.base.EntityResource;
import org.nobel.highriseapi.resources.base.RestResourceConfig;

public class PersonResource extends EntityResource<Person> {

    public PersonResource(HighriseClientConfig clientConfig, EntityCacheProvider entityCacheProvider) {
        super(clientConfig, entityCacheProvider);
    }

    @Override
    protected RestResourceConfig getEntityConfig() {
        return createResourceConfig("people/{id}.xml", Person.class);
    }

    @Override
    protected RestResourceConfig getEntityListConfig() {
        return createResourceConfig("people.xml", PersonList.class);
    }

}
