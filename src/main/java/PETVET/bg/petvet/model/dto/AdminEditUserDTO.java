package PETVET.bg.petvet.model.dto;

import PETVET.bg.petvet.model.entity.enums.UserRoleEnum;

import java.util.List;

public class AdminEditUserDTO {

    private boolean isActive;

    private boolean isLocked;

    private List<UserRoleEnum> userRoles;

    public AdminEditUserDTO() {
    }

    public boolean isActive() {
        return isActive;
    }

    public AdminEditUserDTO setIsActive(boolean active) {
        isActive = active;
        return this;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public AdminEditUserDTO setIsLocked(boolean locked) {
        isLocked = locked;
        return this;
    }

    public List<UserRoleEnum> getUserRoles() {
        return userRoles;
    }

    public AdminEditUserDTO setUserRoles(List<UserRoleEnum> userRoles) {
        this.userRoles = userRoles;
        return this;
    }
}
