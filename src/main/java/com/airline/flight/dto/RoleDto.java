package com.airline.flight.dto;

import javax.validation.constraints.NotNull;

public class RoleDto {

    @NotNull(message = "Name should not be null")
    private String name;

    public RoleDto(String name) {
        this.name = name;
    }

    public RoleDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RoleDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
