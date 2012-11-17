package org.nobel.highriseapi;

import org.nobel.highriseapi.entities.User;
import org.nobel.highriseapi.resources.UserResource;
import org.nobel.highriseapi.resources.base.EntityCacheProvider;
import org.nobel.highriseapi.resources.base.EntityResource;
import org.nobel.highriseapi.resources.base.EntityResourceFactory;

public class HighriseClient {

    public static HighriseClient create(String baseUrl) {
        return new HighriseClient(baseUrl, null);
    }

    public static HighriseClient create(String baseUrl, String token) {
        return new HighriseClient(baseUrl, token);
    }

    private final String baseUrl;

    private final EntityCacheProvider entityCacheProvider;

    private String token;

    private HighriseClient(String baseUrl, String token) {
        this.baseUrl = baseUrl;
        this.token = token;
        this.entityCacheProvider = new EntityCacheProvider();
    }

    public String auth(String user, String password) throws InvalidUserCredentialsException {
        this.token = getResource(UserResource.class).getMe(user, password).getToken();
        return this.token;
    }

    public User getLoggedInUser() {
        return getResource(UserResource.class).getMe();
    }

    public <T extends EntityResource<?>> T getResource(Class<T> resourceClass) {
        return EntityResourceFactory.getInstance().createEntityResource(baseUrl, token, entityCacheProvider,
                resourceClass);
    }

    public String getToken() {
        return token;
    }

}
