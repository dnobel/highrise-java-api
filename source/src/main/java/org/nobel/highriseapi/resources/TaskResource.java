package org.nobel.highriseapi.resources;

import java.util.List;

import org.nobel.highriseapi.entities.Task;
import org.nobel.highriseapi.resources.base.EntityCacheProvider;
import org.nobel.highriseapi.resources.base.EntityCacheProvider.EntityCache;
import org.nobel.highriseapi.resources.base.EntityResource;
import org.nobel.highriseapi.resources.base.RestResourceConfig;

public class TaskResource extends EntityResource<Task> {

	public enum TaskList {
		COMPLETED(1, "tasks/completed.xml"), UPCOMING(0, "tasks/upcoming.xml");
		public static TaskList getById(int id) {
			for (TaskList taskList : TaskList.values()) {
				if (taskList.id == id) {
					return taskList;
				}
			}
			return null;
		}

		public int id;

		public String resourceUrl;

		TaskList(int id, String resourceUrl) {
			this.id = id;
			this.resourceUrl = resourceUrl;
		}
	}

	@Override
	public void clear() {
		super.clear();
		getCache(TaskList.COMPLETED.name()).clear();
	}

	public TaskResource(String baseUrl, String token, EntityCacheProvider entityCacheProvider) {
		super(baseUrl, token, entityCacheProvider);
	}

	public List<Task> getCompleted() {

		Class<?> type = getEntityListConfig().type;
		String url = buildResourceUrl(getBaseUrl(), TaskList.COMPLETED.resourceUrl);
		EntityCache cache = getCache(TaskList.COMPLETED.name());

		return new RemoteEntityAccessorWithCacheSupport<Task>(url, type, cache).getEntityList();
	}

	public List<Task> getUpcoming() {
		return getAll();
	}

	@Override
	protected RestResourceConfig getEntityConfig() {
		return createResourceConfig("tasks/{id}.xml", Task.class);
	}

	@Override
	protected RestResourceConfig getEntityListConfig() {
		return createResourceConfig(TaskList.UPCOMING.resourceUrl, org.nobel.highriseapi.entities.lists.TaskList.class);
	}

}
