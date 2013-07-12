package org.nobel.highriseapi.resources;

import org.nobel.highriseapi.HighriseClientConfig;
import org.nobel.highriseapi.entities.Recording;
import org.nobel.highriseapi.entities.lists.RecordingList;
import org.nobel.highriseapi.resources.base.EntityCacheProvider;
import org.nobel.highriseapi.resources.base.EntityResource;
import org.nobel.highriseapi.resources.base.RestResourceConfig;

public class RecordingsResource extends EntityResource<Recording> {

    public RecordingsResource(HighriseClientConfig clientConfig, EntityCacheProvider entityCacheProvider) {
        super(clientConfig, entityCacheProvider);
    }

    @Override
    protected RestResourceConfig getEntityConfig() {
        throw new IllegalStateException("'Get by id' is not supported for Recordings.");
    }

    @Override
    protected RestResourceConfig getEntityListConfig() {
        return createResourceConfig("recordings.xml", RecordingList.class);
    }

}
