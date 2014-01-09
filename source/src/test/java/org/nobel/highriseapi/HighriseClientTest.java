package org.nobel.highriseapi;

import org.junit.Before;
import org.junit.Test;
import org.nobel.highriseapi.entities.Party;
import org.nobel.highriseapi.entities.Person;
import org.nobel.highriseapi.entities.Tag;
import org.nobel.highriseapi.resources.PersonResource;
import org.nobel.highriseapi.resources.TagResource;
import org.nobel.highriseapi.resources.UserResource;

import java.util.List;

import static java.lang.System.getProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/*
 Define the following properties with -D

 baseUrl - point to your Highrise instance, with trailing "/"
 username - your Highrise account name
 password - your Highrise account password

 Remove @Ignore and provide any your Highrise instance specific data (such as names or email addresses)
 */
public class HighriseClientTest {
    private static final Integer TEST_PERSON_ID = 193370377;
    private static final String TEST_PERSON_EMAIL = "test.person@reaktor.fi";
    private static final String TEST_PERSON_TAG = "test-tag";
    private HighriseClient client;

    @Before
    public void create() throws Exception {
        client = HighriseClient.create(getProperty("baseUrl"));
        client.auth(getProperty("username"), getProperty("password"));
    }

    @Test
    public void getMeHasValidUsername() throws Exception {
        assertEquals(getProperty("username"), client.getResource(UserResource.class).getMe().getEmailAddress());
    }

    @Test
    public void getAllReturnsMultiplePersons() throws Exception {
        assertTrue(client.getResource(PersonResource.class).getAll().size() > 1);
    }

    @Test
    public void getWithIdReturnsTestPerson() throws Exception {
        assertEquals(TEST_PERSON_ID, client.getResource(PersonResource.class).get(TEST_PERSON_ID).getId());
    }

    @Test
    public void getWithIdReturnsTags() throws Exception {
        assertEquals(TEST_PERSON_TAG, client.getResource(PersonResource.class).get(TEST_PERSON_ID).getTags().get(0).getName());
    }

    @Test
    public void searchByEmailReturnsTestPerson() throws Exception {
        List<Person> people = client.getResource(PersonResource.class).searchByEmail(TEST_PERSON_EMAIL);
        assertEquals(1, people.size());
        assertEquals(TEST_PERSON_EMAIL, people.get(0).getContactData().getEMailAddresses().get(0).getAddress());
        assertEquals(TEST_PERSON_ID, people.get(0).getId());
    }

    @Test
    public void searchByNameReturnsTestPerson() throws Exception {
        List<Person> people = client.getResource(PersonResource.class).searchByName("Test Person");
        assertEquals(1, people.size());
        assertEquals(TEST_PERSON_EMAIL, people.get(0).getContactData().getEMailAddresses().get(0).getAddress());
        assertEquals(TEST_PERSON_ID, people.get(0).getId());
    }

    @Test
    public void searchByTagReturnsTestPerson() throws Exception {
        Tag testTag = client.getResource(TagResource.class).getTagByName(TEST_PERSON_TAG);
        List<Party> parties = client.getResource(TagResource.class).getPartyByTag(testTag);
        assertEquals(1, parties.size());
        assertEquals(TEST_PERSON_ID, parties.get(0).getId());
    }

    @Test
    public void searchByNonExistingEmailReturnsEmptyList() throws Exception {
        List<Person> people = client.getResource(PersonResource.class).searchByEmail("email@addre.ss");
        assertEquals(0, people.size());
    }

    @Test
    public void getTagsReturnsMultipleEntities() throws Exception {
        assertTrue(client.getResource(TagResource.class).getAll().size() > 0);
    }
}
