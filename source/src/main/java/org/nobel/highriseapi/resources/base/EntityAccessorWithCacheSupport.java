package org.nobel.highriseapi.resources.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.nobel.highriseapi.entities.EntityList;
import org.nobel.highriseapi.entities.base.Entity;

public abstract class EntityAccessorWithCacheSupport<T extends Entity> {

	Map<Integer, Entity> cache;

	public EntityAccessorWithCacheSupport(Map<Integer, Entity> cache) {
		this.cache = cache;
	}

	@SuppressWarnings("unchecked")
	public T getEntity(int id) {

		if (cache.containsKey(id)) {
			return (T) cache.get(id);
		} else {
			T entity = delegateGetEntity(id);
			cache.put(id, entity);
			return entity;
		}

	}

	public T createEntity(T entity) {
		T createdEntity = delegateCreateEntity(entity);
		cache.put(createdEntity.getId(), createdEntity);
		return entity;
	}

	protected abstract T delegateCreateEntity(T entity);

	@SuppressWarnings("unchecked")
	public List<T> getEntityList() {
		if (!cache.isEmpty()) {
			return new ArrayList<T>((Collection<? extends T>) cache.values());
		} else {
			EntityList<T> entityList = delegateGetAllEntities();
			for (T entity : entityList.getEntities()) {
				cache.put(((Entity) entity).getId(), entity);
			}
			return entityList.getEntities();
		}
	}

	protected abstract EntityList<T> delegateGetAllEntities();

	protected abstract T delegateGetEntity(int id);
}
