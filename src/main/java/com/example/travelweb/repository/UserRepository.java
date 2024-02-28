package com.example.travelweb.repository;

import com.example.travelweb.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    @Query(value = "select * from users where email=?1", nativeQuery = true)
    Optional<UserEntity> getUserByUsername(String email);
}

