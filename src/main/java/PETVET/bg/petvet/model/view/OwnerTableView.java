package PETVET.bg.petvet.model.view;

import javax.persistence.Column;

public class OwnerTableView {

    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    public OwnerTableView() {
    }

    public Long getId() {
        return id;
    }

    public OwnerTableView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public OwnerTableView setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public OwnerTableView setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public OwnerTableView setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public OwnerTableView setEmail(String email) {
        this.email = email;
        return this;
    }
}
