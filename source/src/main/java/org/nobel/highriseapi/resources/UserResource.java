package org.nobel.highriseapi.resources;

import org.nobel.highriseapi.HighriseClientConfig;
import org.nobel.highriseapi.entities.User;
import org.nobel.highriseapi.entities.lists.UserList;
import org.nobel.highriseapi.resources.base.EntityCacheProvider;
import org.nobel.highriseapi.resources.base.EntityResource;
import org.nobel.highriseapi.resources.base.RestResourceConfig;
import org.nobel.highriseapi.resources.base.SpringRestTemplateRemoteEntityManager;

public class UserResource extends EntityResource<User> {

    public UserResource(HighriseClientConfig clientConfig, EntityCacheProvider entityCacheProvider) {
        super(clientConfig, entityCacheProvider);
    }

    public User getMe() {
        return getMe(createTokenBasedUserCredentials());
    }

    public User getMe(String user, String password) {
        return getMe(new UserCredentials(user, password));
    }

    public User getMe(UserCredentials userCredentials) {
        return (User) SpringRestTemplateRemoteEntityManager.getInstance().getEntity(
                buildResourceUrl(getBaseUrl(), "me.xml"), userCredentials, getEntityConfig().type);
    }

    @Override
    protected RestResourceConfig getEntityConfig() {
        return createResourceConfig("users/{id}.xml", User.class);
    }

    @Override
    protected RestResourceConfig getEntityListConfig() {
        return createResourceConfig("users.xml", UserList.class);
    }

}
