package org.nobel.highriseapi.entities;

import org.nobel.highriseapi.entities.base.EntityWithName;
import org.simpleframework.xml.Element;


public class User extends EntityWithName {

    @Element(required = false)
    private String token;

    @Element(name = "email-address")
    private String emailAddress;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
