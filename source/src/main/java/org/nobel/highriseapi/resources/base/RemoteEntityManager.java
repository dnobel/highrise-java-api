package org.nobel.highriseapi.resources.base;

import org.nobel.highriseapi.entities.base.Entity;
import org.nobel.highriseapi.resources.base.EntityResource.UserCredentials;
import org.springframework.util.MultiValueMap;

public interface RemoteEntityManager {

    public Entity createEntity(String fullResourceUrl, UserCredentials userCredentials, Entity entity);

    public Entity createEntity(String fullResourceUrl, UserCredentials userCredentials, Object entity, Class responseEntityClass);

    public <E> E getEntity(String fullResourceUrl, UserCredentials userCredentials, Class<E> entityClass);

    public void updateEntity(String fullResourceUrl, UserCredentials userCredentials, Entity entity);

    public <E> E createEntityFromMultipartFormData(String fullResourceUrl, UserCredentials userCredentials, Class<E> entityClass, MultiValueMap<String, Object> parts);

    public void destroyEntity(String fullResourceUrl, UserCredentials userCredentials);
}