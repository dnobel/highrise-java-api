package org.nobel.highriseapi.entities;

import java.util.List;

import org.nobel.highriseapi.entities.base.Entity;


public interface EntityList<T extends Entity> {
    public List<T> getEntities();
}
