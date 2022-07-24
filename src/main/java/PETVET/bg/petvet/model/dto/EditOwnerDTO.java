package PETVET.bg.petvet.model.dto;

import PETVET.bg.petvet.model.entity.AddressEntity;

import javax.validation.constraints.*;

public class EditOwnerDTO {
    @NotNull
    @Positive
    private Long id;
    @NotEmpty
    @Size(min = 2, max = 20)
    private String firstName;
    @NotEmpty
    @Size(min = 2, max = 20)
    private String lastName;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Pattern(regexp="(^$|[0-9]{10})")
    @Size(min = 10, max = 10)
    private String phoneNumber;
    @NotEmpty
    private String country;
    @NotEmpty
    private String city;
    @NotEmpty
    private String street;
    private String postcode;

    public EditOwnerDTO() {
    }

    public Long getId() {
        return id;
    }

    public EditOwnerDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public EditOwnerDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public EditOwnerDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public EditOwnerDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public EditOwnerDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public EditOwnerDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public EditOwnerDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public EditOwnerDTO setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getPostcode() {
        return postcode;
    }

    public EditOwnerDTO setPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }
}
