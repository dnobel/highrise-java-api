package org.nobel.highriseapi.resources.base;

import java.lang.reflect.Constructor;

import org.nobel.highriseapi.HighriseClientConfig;

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

    public <T extends EntityResource<?>> T createEntityResource(HighriseClientConfig clientConfig,
            EntityCacheProvider entityCacheProvider, Class<T> entityResourceClass) {

        try {
            Constructor<T> constructor = entityResourceClass.getConstructor(HighriseClientConfig.class,
                    EntityCacheProvider.class);
            return constructor.newInstance(clientConfig, entityCacheProvider);
        }
        catch (Exception exception) {
            return null;
        }
    }

}
