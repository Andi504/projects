package com.airline.flight.repository;

import com.airline.flight.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);

    @Modifying
    @Query(value = "UPDATE USER SET is_DISABLE = TRUE WHERE uid = :id",nativeQuery = true)
    void deleteUser(Long id);
}
