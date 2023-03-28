package com.airline.flight.mapper;

import com.airline.flight.dto.UserDto;
import com.airline.flight.entity.User;
import org.mapstruct.Mapper;


@Mapper (componentModel = "spring")
public abstract class UserMapper implements AbstractMapper<UserDto, User> {


}
