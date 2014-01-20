package org.nobel.highriseapi.entities;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "person", strict = false)
public class Person extends Party {

    private static final long serialVersionUID = 4420548359319237886L;

    @Element(name = "first-name", required = false)
    private String firstName;

    @Element(name = "last-name", required = false)
    private String lastName;

    @Element(required = false)
    private String title;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

}
