package org.nobel.highriseapi.resources.base;

import org.nobel.highriseapi.entities.base.Entity;
import org.nobel.highriseapi.resources.base.EntityResource.UserCredentials;

public interface RemoteEntityManager {

    public Entity createEntity(String fullResourceUrl, UserCredentials userCredentials, Entity entity);

    public <E> E getEntity(String fullResourceUrl, UserCredentials userCredentials, Class<E> entityClass);

    public void updateEntity(String fullResourceUrl, UserCredentials userCredentials, Entity entity);

}