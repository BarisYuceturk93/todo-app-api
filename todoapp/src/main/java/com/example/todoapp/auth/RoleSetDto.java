package com.example.todoapp.auth;

import java.util.Set;

public class RoleSetDto {

    private String username;

    private String roles;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "RoleSetDto{" +
                "username='" + username + '\'' +
                ", roles=" + roles +
                '}';
    }
}
