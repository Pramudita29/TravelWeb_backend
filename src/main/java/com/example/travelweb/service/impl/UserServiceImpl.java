package com.example.travelweb.service.impl;

import com.example.travelweb.config.PasswordEncoderUtil;
import com.example.travelweb.dto.UserDto;
import com.example.travelweb.entity.UserEntity;
import com.example.travelweb.repository.UserRepository;
import com.example.travelweb.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String save(UserDto userDto) {

        UserEntity user = new UserEntity();

        if (userDto.getId() != null) {
            user = userRepository.findById((userDto.getId())).orElseThrow(() -> new NullPointerException("data not found"));
        }

        user.setEmail(userDto.getEmail());
        user.setFullName(userDto.getFullName());
        user.setUsername(userDto.getUsername());
        user.setPassword(PasswordEncoderUtil.getInstance().encode(userDto.getPassword()));
        user.setCpassword(PasswordEncoderUtil.getInstance().encode(userDto.getCpassword()));

        userRepository.save(user);


        return "created";
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<UserEntity> getById(Integer id) {
        return userRepository.findById(id);
    }
    @Override
    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<UserEntity> findById(Integer id) {
        return userRepository.findById(id);
    }


    @Override
    public void updateUser(Integer id, UserDto userDto) {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        // Update userEntity fields with userDto data
        userEntity.setFullName(userDto.getFullName());
        userEntity.setUsername(userDto.getUsername());
        userEntity.setEmail(userDto.getEmail());

        // Save the updated userEntity
        userRepository.save(userEntity);
    }
}


