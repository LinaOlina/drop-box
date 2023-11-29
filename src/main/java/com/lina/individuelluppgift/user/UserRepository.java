package com.lina.individuelluppgift.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {



    @Query(value = "SELECT CASE WHEN COUNT(*) > 0 THEN true else false END FROM users u " +
            "WHERE u.username = :username AND u.password = :password", nativeQuery = true)
    boolean isValidUser(String username, String password);

    User findByUsername(String username);

}
