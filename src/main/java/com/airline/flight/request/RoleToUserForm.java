package com.airline.flight.request;

import javax.validation.constraints.NotNull;

public class RoleToUserForm {


    @NotNull(message = "Username should not be null")
    private String username;
    @NotNull(message = "Role should not be null")
    private String roleName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "RoleToUserForm{" +
                "username='" + username + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
