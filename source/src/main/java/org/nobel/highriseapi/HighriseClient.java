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

    private final HighriseClientConfig clientConfig;

    private final EntityCacheProvider entityCacheProvider;

    private HighriseClient(String baseUrl, String token) {
        this.clientConfig = new HighriseClientConfig(baseUrl, token, false);
        this.entityCacheProvider = new EntityCacheProvider();
    }

    public String auth(String user, String password) throws InvalidUserCredentialsException {
        String token = getResource(UserResource.class).getMe(user, password).getToken();
        this.clientConfig.setToken(token);
        return token;
    }

    public User getLoggedInUser() {
        return getResource(UserResource.class).getMe();
    }

    public <T extends EntityResource<?>> T getResource(Class<T> resourceClass) {
        return EntityResourceFactory.getInstance().createEntityResource(clientConfig, entityCacheProvider,
                resourceClass);
    }

    public String getToken() {
        return clientConfig.getToken();
    }

    public void setUseCache(boolean useCache) {
        this.clientConfig.setUseCache(useCache);
    }

}
