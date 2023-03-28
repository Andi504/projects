package com.airline.flight.service.implementation;

import com.airline.flight.dto.UserDto;
import com.airline.flight.entity.Role;
import com.airline.flight.entity.User;
import com.airline.flight.exeptions.ResourceNotFoundException;
import com.airline.flight.mapper.UserMapper;
import com.airline.flight.repository.RoleRepository;
import com.airline.flight.repository.UserRepository;
import com.airline.flight.service.Iservice.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepo;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepo, PasswordEncoder passwordEncoder, RoleRepository roleRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (userRepo.findByUsername(username).isEmpty()){
            LOGGER.debug("username exist {}", username);
            throw new ResourceNotFoundException("User does not exist");
        }else {

            Optional<User> optionalUser = userRepo.findByUsername(username);
            if (optionalUser.isEmpty() || optionalUser.get().getDisable().equals(Boolean.TRUE)) {
                LOGGER.debug("User is not available: {}", username);
                throw new UsernameNotFoundException("User not found in the database");
            } else {
                LOGGER.debug("User found in the database: {}", username);
            }
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            List<Role> roles = optionalUser.get().getRoles();
            roles.forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
                LOGGER.debug("User role is : {}", optionalUser.get().getRoles());
            });
            return new org.springframework.security.core.userdetails.User(optionalUser.get().getUsername(), optionalUser.get().getPassword(), authorities);

        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserDto saveUser(UserDto user) {
        List<Role> roles = new ArrayList<>();
        Optional<Role> role = roleRepo.findByName("ROLE_USER");
        if (role.isPresent() && userRepo.findByUsername(user.getUsername()).isEmpty()){
            roles.add(role.get());
            User user1 = userMapper.toEntity(user);
            user1.setDisable(Boolean.FALSE);
            LOGGER.debug("Saving new User to database {}", user.getUsername());
            user1.setPassword(passwordEncoder.encode(user.getPassword()));
            user1.setRoles(roles);
            User userToBePersisted = userRepo.saveAndFlush(user1);
            return userMapper.toDTO(userToBePersisted);
        }else {
            LOGGER.debug("User can't be persisted {}", user);
            throw new ResourceNotFoundException("Cant persist the user entity");
        }
    }

    @Override
    public User getUser(String username) {
        Optional<User> optionalUser = userRepo.findByUsername(username);
        if (optionalUser.isEmpty()){
            LOGGER.debug("User with username {} does not exist ", username);
            throw new ResourceNotFoundException("Not found");
        }else {
            return optionalUser.get();

        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addRoleToUser(String username, String roleName) {
        if (userRepo.findByUsername(username).isEmpty() && roleRepo.findByName(roleName).isEmpty()){
            return;
        }
        User user = userRepo.findByUsername(username).get();
        Role role = roleRepo.findByName(roleName).get();
        LOGGER.debug("Adding role {} to user {}", role.getName(), user.getUsername());
        user.getRoles().add(role);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUser(Long id) {
        LOGGER.debug("Updating user availability to false with id : {}", id);
        userRepo.deleteUser(id);
    }

}
