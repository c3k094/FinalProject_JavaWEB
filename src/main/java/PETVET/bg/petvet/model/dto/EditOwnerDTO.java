package PETVET.bg.petvet.model.dto;

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
    private String addressCountry;
    @NotEmpty
    private String addressCity;
    @NotEmpty
    private String addressStreet;
    private String addressPostcode;

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

    public String getAddressCountry() {
        return addressCountry;
    }

    public EditOwnerDTO setAddressCountry(String addressCountry) {
        this.addressCountry = addressCountry;
        return this;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public EditOwnerDTO setAddressCity(String addressCity) {
        this.addressCity = addressCity;
        return this;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public EditOwnerDTO setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
        return this;
    }

    public String getAddressPostcode() {
        return addressPostcode;
    }

    public EditOwnerDTO setAddressPostcode(String addressPostcode) {
        this.addressPostcode = addressPostcode;
        return this;
    }
}
