package PETVET.bg.petvet.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "owners")
public class OwnerEntity extends BaseEntity{
    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false,unique = true)
    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    private AddressEntity address;

    @Column(nullable = false,unique = true)
    private String phoneNumber;

    @OneToMany(mappedBy = "owner")
    private List<AnimalEntity> pets;

    public OwnerEntity() {}

    public String getFirstName() {
        return firstName;
    }

    public OwnerEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public OwnerEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public OwnerEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public OwnerEntity setAddress(AddressEntity address) {
        this.address = address;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public OwnerEntity setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public List<AnimalEntity> getPets() {
        return pets;
    }

    public OwnerEntity setPets(List<AnimalEntity> pets) {
        this.pets = pets;
        return this;
    }
}
