package org.nobel.highriseapi.resources;

import org.nobel.highriseapi.entities.Case;
import org.nobel.highriseapi.entities.lists.CaseList;
import org.nobel.highriseapi.resources.base.EntityCacheProvider;
import org.nobel.highriseapi.resources.base.EntityResource;
import org.nobel.highriseapi.resources.base.RestResourceConfig;

public class CaseResource extends EntityResource<Case> {

	public CaseResource(String baseUrl, String token, EntityCacheProvider entityCacheProvider) {
		super(baseUrl, token, entityCacheProvider);
	}

	@Override
	protected RestResourceConfig getEntityConfig() {
		return createResourceConfig("kases/{id}.xml", Case.class);
	}

	@Override
	protected RestResourceConfig getEntityListConfig() {
		return createResourceConfig("kases.xml", CaseList.class);
	}

}
