package PETVET.bg.petvet.model.view;

import PETVET.bg.petvet.model.entity.UserRoleEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserAdminView {

    private Long id;
    private String firstName;
    private String lastName;
    private String imageUrl;
    private String email;
    private boolean isActive;
    private boolean isLocked;
    private LocalDateTime lastLoginDate;
    private List<UserRoleEntity> userRoles;

    public UserAdminView() {
    }

    public Long getId() {
        return id;
    }

    public UserAdminView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserAdminView setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserAdminView setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public UserAdminView setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserAdminView setEmail(String email) {
        this.email = email;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public UserAdminView setActive(boolean active) {
        isActive = active;
        return this;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public UserAdminView setLocked(boolean locked) {
        isLocked = locked;
        return this;
    }

    public LocalDateTime getLastLoginDate() {
        return lastLoginDate;
    }

    public UserAdminView setLastLoginDate(LocalDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
        return this;
    }

    public List<UserRoleEntity> getUserRoles() {
        return userRoles;
    }

    public UserAdminView setUserRoles(List<UserRoleEntity> userRoles) {
        this.userRoles = userRoles;
        return this;
    }
}
