package org.nobel.highriseapi.resources;

import org.nobel.highriseapi.HighriseClientConfig;
import org.nobel.highriseapi.entities.Deal;
import org.nobel.highriseapi.entities.lists.DealList;
import org.nobel.highriseapi.resources.base.EntityCacheProvider;
import org.nobel.highriseapi.resources.base.EntityResource;
import org.nobel.highriseapi.resources.base.RestResourceConfig;

public class DealResource extends EntityResource<Deal> {

    public DealResource(HighriseClientConfig clientConfig, EntityCacheProvider entityCacheProvider) {
        super(clientConfig, entityCacheProvider);
    }

    @Override
    protected RestResourceConfig getEntityConfig() {
        return createResourceConfig("deals/{id}.xml", Deal.class);
    }

    @Override
    protected RestResourceConfig getEntityListConfig() {
        return createResourceConfig("deals.xml", DealList.class);
    }

}
