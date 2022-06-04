package PETVET.bg.petvet.model.entity;

import PETVET.bg.petvet.model.entity.enums.Address;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "owners")
public class Owner extends BaseEntity{
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false,unique = true)
    private String email;

    @OneToOne(optional = false)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(nullable = false,unique = true)
    private int phoneNumber;

    @OneToMany(mappedBy = "owner")
    private List<Animal> pets;

    public Owner() {}

    public String getFirstName() {
        return firstName;
    }

    public Owner setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Owner setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Owner setEmail(String email) {
        this.email = email;
        return this;
    }

    public Address getAddress() {
        return address;
    }

    public Owner setAddress(Address address) {
        this.address = address;
        return this;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public Owner setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public List<Animal> getPets() {
        return pets;
    }

    public Owner setPets(List<Animal> pets) {
        this.pets = pets;
        return this;
    }
}
