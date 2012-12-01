package org.nobel.highriseapi.resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.nobel.highriseapi.entities.Note;
import org.nobel.highriseapi.entities.lists.NoteList;
import org.nobel.highriseapi.resources.base.EntityCacheProvider;
import org.nobel.highriseapi.resources.base.EntityCacheProvider.EntityCache;
import org.nobel.highriseapi.resources.base.EntityIdComparator;
import org.nobel.highriseapi.resources.base.EntityResource;
import org.nobel.highriseapi.resources.base.RestResourceConfig;

public class NoteResource extends EntityResource<Note> {

	public enum NoteKind {
		DEAL_NOTES("deals/{id}/notes.xml"), CASE_NOTES("kases/{id}/notes.xml");

		public String resourceUrl;

		NoteKind(String resourceUrl) {
			this.resourceUrl = resourceUrl;
		}
	}

	public NoteResource(String baseUrl, String token, EntityCacheProvider entityCacheProvider) {
		super(baseUrl, token, entityCacheProvider);
	}

	@Override
	protected RestResourceConfig getEntityConfig() {
		return createResourceConfig("notes/{id}.xml", Note.class);
	}

	@Override
	protected RestResourceConfig getEntityListConfig() {
		return createResourceConfig("notes.xml", NoteList.class);
	}

	@Override
	public List<Note> getAll() {
		return new ArrayList<Note>();
	}

	public Note createForEntity(NoteKind noteKind, int entityId, Note note) {

		String url = buildResourceUrl(getBaseUrl(), noteKind.resourceUrl);
		url = replaceVariablesInUrl(url, createIdVariableReplacement(entityId));

		return new RemoteEntityAccessorWithCacheSupport<Note>(url, getCache(noteKind.name())).createEntity(note);
	}

	public List<Note> getAll(NoteKind noteKind, int id) {

		Class<?> type = getEntityListConfig().type;
		String url = buildResourceUrl(getBaseUrl(), noteKind.resourceUrl);
		EntityCache cache = getCache(noteKind.name());
		url = replaceVariablesInUrl(url, createIdVariableReplacement(id));

		List<Note> noteList = new RemoteEntityAccessorWithCacheSupport<Note>(url, type, cache).getEntityList();
		Collections.sort(noteList, new EntityIdComparator());

		return noteList;
	}

}
