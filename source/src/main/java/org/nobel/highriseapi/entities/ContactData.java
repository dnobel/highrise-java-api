package org.nobel.highriseapi.entities;

import java.io.Serializable;
import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(strict = false)
public class ContactData implements Serializable {

    private static final long serialVersionUID = -4821662158481786596L;

    @ElementList(name = "phone-numbers")
    List<PhoneNumber> phoneNumbers;

    @ElementList(name = "email-addresses")
    List<EMailAddress> eMailAddresses;

    public List<PhoneNumber> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(List<PhoneNumber> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public List<EMailAddress> getEMailAddresses() {
        return eMailAddresses;
    }

    public void setEMailAddresses(List<EMailAddress> eMailAddresses) {
        this.eMailAddresses = eMailAddresses;
    }

}
