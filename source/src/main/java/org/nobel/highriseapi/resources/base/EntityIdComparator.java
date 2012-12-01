package org.nobel.highriseapi.resources.base;

import java.util.Comparator;

import org.nobel.highriseapi.entities.base.Entity;

public class EntityIdComparator implements Comparator<Entity> {

	public int compare(Entity lhs, Entity rhs) {
		return rhs.getId().compareTo(lhs.getId());
	}

}
