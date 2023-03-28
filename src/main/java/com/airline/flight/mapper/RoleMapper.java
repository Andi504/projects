package com.airline.flight.mapper;

import com.airline.flight.dto.RoleDto;
import com.airline.flight.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class RoleMapper implements AbstractMapper<RoleDto, Role> {
}
