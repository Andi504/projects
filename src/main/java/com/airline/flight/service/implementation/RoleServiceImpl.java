package com.airline.flight.service.implementation;

import com.airline.flight.dto.RoleDto;
import com.airline.flight.entity.Role;
import com.airline.flight.exeptions.ExistException;
import com.airline.flight.mapper.RoleMapper;
import com.airline.flight.repository.RoleRepository;
import com.airline.flight.service.Iservice.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final RoleMapper roleMapper;
    private final RoleRepository roleRepo;

    public RoleServiceImpl(RoleMapper roleMapper, RoleRepository roleRepo) {
        this.roleMapper = roleMapper;
        this.roleRepo = roleRepo;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public RoleDto saveRole(RoleDto roleDto) {
        Optional<Role> roleByName = roleRepo.findByName(roleDto.getName());
        if (roleByName.isPresent()){
            throw new ExistException("Role is present in the db");
        }
        Role role = roleMapper.toEntity(roleDto);
        LOGGER.debug("Saving Role to database {} ", roleDto.getName());
        return roleMapper.toDTO(roleRepo.saveAndFlush(role));
    }
}
