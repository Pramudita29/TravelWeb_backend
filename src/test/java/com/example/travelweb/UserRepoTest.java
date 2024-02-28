package com.example.travelweb;

import com.example.travelweb.entity.UserEntity;
import com.example.travelweb.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class UserRepoTest {

    @Autowired
    private UserRepository userRepository;

    private UserEntity createUserEntity() {
        UserEntity user = UserEntity.builder()
                .fullName("Test User")
                .username("testuser")
                .email("testuser@example.com")
                .password("encryptedPassword") // Assuming password is already encrypted
                .cpassword("encryptedPassword")
                .build();
        return userRepository.save(user);
    }

    @Test
    public void saveAndRetrieveUser() {
        UserEntity savedUser = createUserEntity();
        Optional<UserEntity> retrievedUser = userRepository.findById(savedUser.getId());
        Assertions.assertThat(retrievedUser).isPresent();
        Assertions.assertThat(retrievedUser.get().getEmail()).isEqualTo(savedUser.getEmail());
    }

    @Test
    public void getUserByUsername() {
        UserEntity savedUser = createUserEntity();
        Optional<UserEntity> retrievedUser = userRepository.getUserByUsername(savedUser.getEmail());
        Assertions.assertThat(retrievedUser).isPresent();
        Assertions.assertThat(retrievedUser.get().getEmail()).isEqualTo(savedUser.getEmail());
    }

    @Test
    public void deleteUserById() {
        UserEntity savedUser = createUserEntity();
        userRepository.deleteById(savedUser.getId());
        Optional<UserEntity> deletedUser = userRepository.findById(savedUser.getId());
        Assertions.assertThat(deletedUser).isEmpty();
    }

    @Test
    public void updateUserInformation() {
        UserEntity user = createUserEntity();
        String updatedFullName = "Updated Test User";
        user.setFullName(updatedFullName);
        userRepository.save(user);

        Optional<UserEntity> updatedUser = userRepository.findById(user.getId());
        Assertions.assertThat(updatedUser).isPresent();
        Assertions.assertThat(updatedUser.get().getFullName()).isEqualTo(updatedFullName);
    }
}
