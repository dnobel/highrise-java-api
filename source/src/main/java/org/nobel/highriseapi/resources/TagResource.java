package org.nobel.highriseapi.resources;

import org.nobel.highriseapi.HighriseClientConfig;
import org.nobel.highriseapi.entities.CreateTag;
import org.nobel.highriseapi.entities.Party;
import org.nobel.highriseapi.entities.Person;
import org.nobel.highriseapi.entities.Recording;
import org.nobel.highriseapi.entities.Tag;
import org.nobel.highriseapi.entities.lists.PartyList;
import org.nobel.highriseapi.entities.lists.TagList;
import org.nobel.highriseapi.resources.base.EntityCacheProvider;
import org.nobel.highriseapi.resources.base.EntityResource;
import org.nobel.highriseapi.resources.base.RestResourceConfig;

import java.util.List;
import java.util.Map;

import static org.nobel.highriseapi.entities.Recording.SubjectType.Party;

public class TagResource extends EntityResource<Tag> {
    public TagResource(HighriseClientConfig clientConfig, EntityCacheProvider entityCacheProvider) {
        super(clientConfig, entityCacheProvider);
    }

    @Override
    protected RestResourceConfig getEntityConfig() {
        throw new IllegalStateException("'Get by id' is not supported for Tags.");
    }

    @Override
    protected RestResourceConfig getEntityListConfig() {
        return createResourceConfig("tags.xml", TagList.class);
    }

    public Tag getTagByName(String name) {
        List<Tag> tags = getAll();
        for (Tag tag : tags) {
            if (tag.getName().equals(name)) {
                return tag;
            }
        }
        throw new RuntimeException("Tag '" + name + "' not found");
    }

    @SuppressWarnings("unchecked")
    public List<Party> getPartyByTag(Tag tag) {
        String url = buildResourceUrl(getBaseUrl(), "tags/" + tag.getId() + ".xml");
        return createRemoteEntityAccesor(PartyList.class, url).getEntityList();
    }

    @Override
    public Tag create(Tag tag) {
        throw new UnsupportedOperationException("Not supported for tags, use create(tag,person)");
    }

    public Tag create(CreateTag tag, Person person) {
        String url = buildResourceUrl(getBaseUrl(), "people/" + person.getId() + "/tags.xml");
        return (Tag)createRemoteEntityAccesor(Tag.class, url).createEntity(tag);
    }
}
