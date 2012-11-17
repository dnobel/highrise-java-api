package org.nobel.highriseapi;

import org.junit.Assert;
import org.junit.Test;
import org.nobel.highriseapi.resources.PersonResource;
import org.nobel.highriseapi.resources.UserResource;

public class HighriseClientTest {

    HighriseClient highriseClient;

    @Test
    public void getMe() throws Exception {
        highriseClient = HighriseClient.create("https://supshowcase.highrisehq.com/");
        Assert.assertNotNull(highriseClient.auth("highrise_test@itemis.de", "1tem1s"));
        Assert.assertNotNull(highriseClient.getResource(UserResource.class).getMe());
        Assert.assertNotNull(highriseClient.getResource(PersonResource.class).getAll());
        Assert.assertNotNull(highriseClient.getResource(PersonResource.class).get(120593863));
        // highriseClient.getResource(PersonResource.class).get(111);
    }
}
