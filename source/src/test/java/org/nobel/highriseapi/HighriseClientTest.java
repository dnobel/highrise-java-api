package org.nobel.highriseapi;

import com.google.common.collect.ImmutableList;
import org.apache.http.annotation.Immutable;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.nobel.highriseapi.entities.ContactData;
import org.nobel.highriseapi.entities.CreateTag;
import org.nobel.highriseapi.entities.EMailAddress;
import org.nobel.highriseapi.entities.Note;
import org.nobel.highriseapi.entities.Party;
import org.nobel.highriseapi.entities.Person;
import org.nobel.highriseapi.entities.Tag;
import org.nobel.highriseapi.entities.UploadAttachment;
import org.nobel.highriseapi.entities.base.Entity;
import org.nobel.highriseapi.resources.NoteResource;
import org.nobel.highriseapi.resources.PersonResource;
import org.nobel.highriseapi.resources.TagResource;
import org.nobel.highriseapi.resources.UserResource;

import java.util.List;

import static java.lang.System.getProperty;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.nobel.highriseapi.resources.NoteResource.NoteKind.PERSON_NOTES;

/*
 Define the following properties with -D

 baseUrl - point to your Highrise instance, with trailing "/"
 username - your Highrise account name
 password - your Highrise account password
 */
public class HighriseClientTest {
    private static final String TAG = "test-tag";
    private static final String EMAIL = "test.person@inter.net";
    private static final String FIRSTNAME = "HighriseClient";
    private static final String LASTNAME = "Test";
    private static final String NAME = FIRSTNAME + " " + LASTNAME;
    private static HighriseClient client;
    private static Person person;
    private static Tag tag;

    @BeforeClass
    public static void create() throws Exception {
        createAuthenticatedClient();
        createTestPerson();
    }

    @AfterClass
    public static void destroy() {
        destroyTestPerson();
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
        Person created = client.getResource(PersonResource.class).get(person.getId());
        assertEquals(person.getId(), created.getId());
        assertEquals(TAG, created.getTags().get(0).getName());
    }

    @Test
    public void searchByEmailReturnsTestPerson() throws Exception {
        List<Person> people = client.getResource(PersonResource.class).searchByEmail(EMAIL);
        assertReturnsTestPerson(people);
    }

    @Test
    public void searchByNameReturnsTestPerson() throws Exception {
        List<Person> people = client.getResource(PersonResource.class).searchByName(NAME);
        assertReturnsTestPerson(people);
    }

    @Test
    public void searchByTagReturnsTestPerson() throws Exception {
        Tag testTag = client.getResource(TagResource.class).getTagByName(TAG);
        List<Party> parties = client.getResource(TagResource.class).getPartyByTag(testTag);
        assertReturnsTestPerson(parties);
    }

    @Test
    public void searchByNonExistingEmailReturnsEmptyList() throws Exception {
        List<Person> people = client.getResource(PersonResource.class).searchByEmail("invalid-email-address");
        assertEquals(0, people.size());
    }

    @Test
    public void getTagsReturnsMultipleEntities() throws Exception {
        assertTrue(client.getResource(TagResource.class).getAll().size() > 0);
    }

    @Test
    public void addNoteWithHtmlForTestPerson() throws Exception {
        Note note = new Note();
        note.setBody("<html><body><b>Test comment</b><br/>filed with Java Technology</body></html>");
        assertNotNull(client.getResource(NoteResource.class).createForEntity(PERSON_NOTES, person.getId(), note));
    }

    @Test
    public void addNoteWithAttachmentForTestPerson() throws Exception {
        Note note = new Note();
        note.setBody("Note");
        note.setUploadAttachments(ImmutableList.of(new UploadAttachment(filePath("/kitten.jpg"))));
        assertNotNull(client.getResource(NoteResource.class).createForEntity(PERSON_NOTES, person.getId(), note));
    }

    private String filePath(String resourceName) {
        return getClass().getResource(resourceName).toExternalForm().replace("file:", "");
    }

    private void assertReturnsTestPerson(List<? extends Entity> people) {
        assertEquals(1, people.size());
        assertEquals(person.getId(), people.get(0).getId());
    }

    private static void createAuthenticatedClient() throws InvalidUserCredentialsException {
        client = HighriseClient.create(getProperty("baseUrl"));
        client.auth(getProperty("username"), getProperty("password"));
    }

    private static void createTestPerson() {
        Person newPerson = new Person();
        newPerson.setFirstName(FIRSTNAME);
        newPerson.setLastName(LASTNAME);
        ContactData contact = new ContactData();
        contact.setEMailAddresses(ImmutableList.of(new EMailAddress(EMAIL, "Work")));
        newPerson.setContactData(contact);
        person = client.getResource(PersonResource.class).create(newPerson);
        tag = client.getResource(TagResource.class).create(new CreateTag(TAG), person);
    }

    public static void destroyTestPerson() {
        if (client != null && person != null) {
            client.getResource(PersonResource.class).destroy(person.getId());
        }
    }
}
