package PETVET.bg.petvet.model.view;

import javax.validation.constraints.NotNull;

public class AdminEditUserView {

    @NotNull
    private Long id;

    private String email;

    private String firstName;

    private String lastName;

    private String imageUrl;

    public AdminEditUserView() {
    }

    public Long getId() {
        return id;
    }

    public AdminEditUserView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AdminEditUserView setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public AdminEditUserView setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public AdminEditUserView setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public AdminEditUserView setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}
