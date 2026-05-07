package com.efootball.tournament.repository;

import com.efootball.tournament.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    //getting admin info
    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByAdminID(String adminID);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    //Optional<User> findByUsername(String username);
    Optional<User> findByAdminID(String adminID);
    //boolean existsByUsername(String username);
    //boolean existsByEmail(String email);
    //boolean existsByAdminID(String adminID);
}
