package PETVET.bg.petvet.model.dto;

import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AddOwnerDTO {

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
    private String postCode;

    public AddOwnerDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public AddOwnerDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AddOwnerDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AddOwnerDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public AddOwnerDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public AddOwnerDTO setCountry(String country) {
        this.country = country;
        return this;
    }

    public String getCity() {
        return city;
    }

    public AddOwnerDTO setCity(String city) {
        this.city = city;
        return this;
    }

    public String getStreet() {
        return street;
    }

    public AddOwnerDTO setStreet(String street) {
        this.street = street;
        return this;
    }

    public String getPostCode() {
        return postCode;
    }

    public AddOwnerDTO setPostCode(String postCode) {
        this.postCode = postCode;
        return this;
    }
}
