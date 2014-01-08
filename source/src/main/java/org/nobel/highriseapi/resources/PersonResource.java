package org.nobel.highriseapi.resources;

import org.nobel.highriseapi.HighriseClientConfig;
import org.nobel.highriseapi.entities.Person;
import org.nobel.highriseapi.entities.lists.PersonList;
import org.nobel.highriseapi.resources.base.EntityCacheProvider;
import org.nobel.highriseapi.resources.base.EntityResource;
import org.nobel.highriseapi.resources.base.RestResourceConfig;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.StringUtils.collectionToDelimitedString;

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

    @SuppressWarnings("unchecked")
    public List<Person> searchByEmail(final String address) {
        Map<String, String> criteria = new HashMap<String, String>() {{
            put("email", address);
        }};
        return search(criteria);
    }

    @SuppressWarnings("unchecked")
    public List<Person> search(Map<String, String> criteria) {
        String url = withCriteria(buildResourceUrl(getBaseUrl(), "people/search.xml"), criteria);
        return createRemoteEntityAccesor(PersonList.class, url).getEntityList();
    }

    protected String withCriteria(String url, Map<String, String> parameters) {
        List<String> criteria = new ArrayList<String>();
        for (String parameter : parameters.keySet()) {
            criteria.add("criteria[" + parameter + "]=" + parameters.get(parameter));
        }
        return url + "?" + collectionToDelimitedString(criteria, "&");
    }
}
