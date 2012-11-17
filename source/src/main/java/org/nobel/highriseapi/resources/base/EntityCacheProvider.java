package org.nobel.highriseapi.resources.base;

import java.util.HashMap;
import java.util.Map;

import org.nobel.highriseapi.entities.base.Entity;


public class EntityCacheProvider {

    public class EntityCache extends HashMap<Integer, Entity> {

    }

    private final Map<String, EntityCache> caches = new HashMap<String, EntityCache>();

    public EntityCache getCache(String cacheName) {
        if (!caches.containsKey(cacheName)) {
            caches.put(cacheName, new EntityCache());
        }
        return caches.get(cacheName);
    }

}
