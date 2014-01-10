package org.nobel.highriseapi.resources.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.nobel.highriseapi.HighriseClientConfig;
import org.nobel.highriseapi.entities.EntityList;
import org.nobel.highriseapi.entities.base.Entity;
import org.nobel.highriseapi.resources.base.EntityCacheProvider.EntityCache;
import org.springframework.util.MultiValueMap;

public abstract class EntityResource<T extends Entity> {

    public class RemoteEntityAccessorWithCacheSupport<V extends Entity> extends EntityAccessorWithCacheSupport<V> {

        private Class<?> type;
        private final String url;

        public RemoteEntityAccessorWithCacheSupport(String url, Class<?> type, Map<Integer, Entity> cache,
                boolean useCache) {
            super(cache, useCache);
            this.url = url;
            this.type = type;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected V delegateCreateEntity(V entity) {
            return (V) getRemoteEntityManager().createEntity(url, createTokenBasedUserCredentials(), entity);
        }

        @SuppressWarnings("unchecked")
        @Override
        protected EntityList<V> delegateGetAllEntities() {
            return ((EntityList<V>) getRemoteEntityManager().getEntity(url, createTokenBasedUserCredentials(), type));
        }

        @SuppressWarnings("unchecked")
        @Override
        protected V delegateGetEntity(int id) {
            return (V) getRemoteEntity();
        }

        @SuppressWarnings("unchecked")
        @Override
        protected V delegateCreateEntityFromMultipartFormData(MultiValueMap<String, Object> parts) {
            return (V) getRemoteEntityManager().createEntityFromMultipartFormData(url, createTokenBasedUserCredentials(), type, parts);
        }

        @Override
        protected void delegateDestroyEntity(int id) {
            getRemoteEntityManager().destroyEntity(url, createTokenBasedUserCredentials());
        }

        private Object getRemoteEntity() {
            return getRemoteEntityManager().getEntity(url, createTokenBasedUserCredentials(), type);
        }
    }

    public static class UserCredentials {

        public String password;
        public String user;

        public UserCredentials(String user, String password) {
            this.user = user;
            this.password = password;
        }

    }

    static class ResourceConfig {
        public Class<?> entityClass;
        public String path;
    }

    private final HighriseClientConfig clientConfig;

    private final EntityCacheProvider entityCacheProvider;

    public EntityResource(HighriseClientConfig clientConfig, EntityCacheProvider entityCacheProvider) {
        this.clientConfig = clientConfig;
        this.entityCacheProvider = entityCacheProvider;
    }

    public void clear() {
        getDefaultResourceCache().clear();
    }

    @SuppressWarnings("unchecked")
    public T get(int id) {

        String path = getEntityConfig().path;
        Class<?> type = getEntityConfig().type;

        Map<String, String> variables = createIdVariableReplacement(id);
        String url = replaceVariablesInUrl(buildResourceUrl(getBaseUrl(), path), variables);

        return (T) createRemoteEntityAccesor(type, url).getEntity(id);
    }

    @SuppressWarnings("unchecked")
    public List<T> getAll() {

        String path = getEntityListConfig().path;
        Class<?> type = getEntityListConfig().type;

        String url = buildResourceUrl(getBaseUrl(), path);

        return createRemoteEntityAccesor(type, url).getEntityList();
    }

    public void destroy(int id) {
        String path = getEntityConfig().path;
        Map<String, String> variables = createIdVariableReplacement(id);
        String url = replaceVariablesInUrl(buildResourceUrl(getBaseUrl(), path), variables);
        createRemoteEntityAccesor(getEntityConfig().type, url).destroyEntity(id);
    }

    protected String buildResourceUrl(String baseUrl, String resourceUrl) {
        return baseUrl + resourceUrl;
    }

    protected Map<String, String> createIdVariableReplacement(int i) {
        Map<String, String> variables = new HashMap<String, String>();
        variables.put("id", String.valueOf(i));
        return variables;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected RemoteEntityAccessorWithCacheSupport createRemoteEntityAccesor(Class<?> type, String url) {
        return new RemoteEntityAccessorWithCacheSupport(url, type, getDefaultResourceCache(), clientConfig.isUseCache());
    }

    protected RestResourceConfig createResourceConfig(String path, Class<?> type) {
        RestResourceConfig resourceConfig = new RestResourceConfig();
        resourceConfig.path = path;
        resourceConfig.type = type;
        return resourceConfig;
    }

    protected UserCredentials createTokenBasedUserCredentials() {
        return new UserCredentials(clientConfig.getToken(), "X");
    }

    protected String getBaseUrl() {
        return clientConfig.getBaseUrl();
    }

    protected EntityCache getCache(String cacheName) {
        return this.entityCacheProvider.getCache(this.getClass().getName() + cacheName);
    };

    protected HighriseClientConfig getClientConfig() {
        return clientConfig;
    }

    protected Map<Integer, Entity> getDefaultResourceCache() {
        return this.entityCacheProvider.getCache(this.getClass().getName());
    }

    protected abstract RestResourceConfig getEntityConfig();

    protected abstract RestResourceConfig getEntityListConfig();

    protected String getResourcePath() {
        return "";
    };

    protected String replaceVariablesInUrl(String url, Map<String, String> variables) {
        for (String variable : variables.keySet()) {
            url = url.replace("{" + variable + "}", variables.get(variable));
        }
        return url;
    }

    private RemoteEntityManager getRemoteEntityManager() {
        return SpringRestTemplateRemoteEntityManager.getInstance();
    }

}
