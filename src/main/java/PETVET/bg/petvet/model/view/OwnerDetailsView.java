package PETVET.bg.petvet.model.view;

import PETVET.bg.petvet.model.entity.AddressEntity;
import PETVET.bg.petvet.model.entity.AnimalEntity;

import java.util.List;

public class OwnerDetailsView {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private AddressEntity address;

    private String phoneNumber;

    private List<AnimalEntity> pets;

    public OwnerDetailsView() {
    }

    public Long getId() {
        return id;
    }

    public OwnerDetailsView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public OwnerDetailsView setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public OwnerDetailsView setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public OwnerDetailsView setEmail(String email) {
        this.email = email;
        return this;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public OwnerDetailsView setAddress(AddressEntity address) {
        this.address = address;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public OwnerDetailsView setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public List<AnimalEntity> getPets() {
        return pets;
    }

    public OwnerDetailsView setPets(List<AnimalEntity> pets) {
        this.pets = pets;
        return this;
    }
}
