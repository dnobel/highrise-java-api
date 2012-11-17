package org.nobel.highriseapi.resources.base;

import java.lang.reflect.Constructor;

public class EntityResourceFactory {

    private static EntityResourceFactory instance;

    public static EntityResourceFactory getInstance() {
        if (instance == null) {
            instance = new EntityResourceFactory();
        }
        return instance;
    }

    protected EntityResourceFactory() {

    }

    public <T extends EntityResource<?>> T createEntityResource(String baseUrl, String token,
            EntityCacheProvider entityCacheProvider, Class<T> entityResourceClass) {

        try {
            Constructor<T> constructor = entityResourceClass.getConstructor(String.class, String.class,
                    EntityCacheProvider.class);
            return constructor.newInstance(baseUrl, token, entityCacheProvider);
        }
        catch (Exception exception) {
            return null;
        }
    }

}
