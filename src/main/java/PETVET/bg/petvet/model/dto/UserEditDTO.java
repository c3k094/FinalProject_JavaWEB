package PETVET.bg.petvet.model.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserEditDTO {
    private String email;

    @NotEmpty
    @Size(min = 2, max = 20)
    private String firstName;

    @NotEmpty
    @Size(min = 2, max = 20)
    private String lastName;

    private String imageUrl;

    public UserEditDTO() {
    }

    public String getEmail() {
        return email;
    }

    public UserEditDTO setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEditDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEditDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UserEditDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
