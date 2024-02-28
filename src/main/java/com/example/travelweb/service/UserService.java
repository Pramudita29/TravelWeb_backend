package com.example.travelweb.service;

import com.example.travelweb.dto.UserDto;
import com.example.travelweb.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserService {


    String save(UserDto userDto);

    List<UserEntity> getAll();

    Optional<UserEntity> getById(Integer id);

    void deleteById(Integer id);

    Optional<UserEntity> findById(Integer id);

    void updateUser(Integer id, UserDto userDto);

}
