package PETVET.bg.petvet.model.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class SearchOwnerDTO {

    private String phoneNumber;

    private String email;

    public SearchOwnerDTO() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public SearchOwnerDTO setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public SearchOwnerDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public boolean isEmpty() {
        return (phoneNumber == null || phoneNumber.isEmpty()) && (email == null || email.isEmpty());
    }
}
