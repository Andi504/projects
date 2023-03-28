package com.airline.flight.service.Iservice;

import com.airline.flight.dto.UserDto;
import com.airline.flight.entity.User;

public interface UserService {

    UserDto saveUser(UserDto user);

    User getUser(String username);

    void addRoleToUser(String username, String roleName);

    void deleteUser(Long id);

}
