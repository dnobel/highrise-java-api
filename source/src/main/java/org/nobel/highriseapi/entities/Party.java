package org.nobel.highriseapi.entities;

import org.nobel.highriseapi.entities.base.EntityImage;
import org.nobel.highriseapi.entities.base.EntityWithAvatarUrl;
import org.nobel.highriseapi.entities.base.EntityWithImage;
import org.nobel.highriseapi.entities.base.EntityWithName;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Transient;


public class Party extends EntityWithName implements EntityWithAvatarUrl, EntityWithImage {

    @Element(name = "company-name", required = false)
    protected String companyName;

    @Transient
    protected transient EntityImage image;

    @Element(name = "avatar-url", required = false)
    private String avatarUrl;

    @Element(required = false)
    private String background;

    @Element(name = "contact-data")
    private ContactData contactData;

    @Element(name = "type", required = false)
    private String type;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getBackground() {
        return background;
    }

    public ContactData getContactData() {
        return contactData;
    }

    public EntityImage getImage() {
        return image;
    }

    public String getType() {
        return type;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public void setContactData(ContactData contactData) {
        this.contactData = contactData;
    }

    public void setImage(EntityImage image) {
        this.image = image;
    }

    public void setType(String type) {
        this.type = type;
    }
}
