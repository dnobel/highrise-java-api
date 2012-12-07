package org.nobel.highriseapi.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.nobel.highriseapi.HighriseClientConfig;
import org.nobel.highriseapi.entities.Note;
import org.nobel.highriseapi.entities.lists.NoteList;
import org.nobel.highriseapi.resources.base.EntityCacheProvider;
import org.nobel.highriseapi.resources.base.EntityCacheProvider.EntityCache;
import org.nobel.highriseapi.resources.base.EntityIdComparator;
import org.nobel.highriseapi.resources.base.EntityResource;
import org.nobel.highriseapi.resources.base.RestResourceConfig;

public class NoteResource extends EntityResource<Note> {

    public enum NoteKind {
        CASE_NOTES("kases/{id}/notes.xml"), DEAL_NOTES("deals/{id}/notes.xml");

        public String resourceUrl;

        NoteKind(String resourceUrl) {
            this.resourceUrl = resourceUrl;
        }
    }

    public NoteResource(HighriseClientConfig clientConfig, EntityCacheProvider entityCacheProvider) {
        super(clientConfig, entityCacheProvider);
    }

    public Note createForEntity(NoteKind noteKind, int entityId, Note note) {

        String url = buildResourceUrl(getBaseUrl(), noteKind.resourceUrl);
        url = replaceVariablesInUrl(url, createIdVariableReplacement(entityId));

        return new RemoteEntityAccessorWithCacheSupport<Note>(url, getCache(noteKind.name()), getClientConfig()
                .isUseCache()).createEntity(note);
    }

    @Override
    public List<Note> getAll() {
        return new ArrayList<Note>();
    }

    public List<Note> getAll(NoteKind noteKind, int id) {

        Class<?> type = getEntityListConfig().type;
        String url = buildResourceUrl(getBaseUrl(), noteKind.resourceUrl);
        EntityCache cache = getCache(noteKind.name());
        url = replaceVariablesInUrl(url, createIdVariableReplacement(id));

        List<Note> noteList = new RemoteEntityAccessorWithCacheSupport<Note>(url, type, cache, getClientConfig()
                .isUseCache()).getEntityList();
        Collections.sort(noteList, new EntityIdComparator());

        return noteList;
    }

    @Override
    protected RestResourceConfig getEntityConfig() {
        return createResourceConfig("notes/{id}.xml", Note.class);
    }

    @Override
    protected RestResourceConfig getEntityListConfig() {
        return createResourceConfig("notes.xml", NoteList.class);
    }

}
