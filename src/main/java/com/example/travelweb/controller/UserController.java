package com.example.travelweb.controller;

import com.example.travelweb.dto.ChangePasswordDto;
import com.example.travelweb.dto.UserDto;
import com.example.travelweb.entity.UserEntity;
import com.example.travelweb.service.AuthenticateService;
import com.example.travelweb.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticateService authenticateService; // Inject your AuthenticateService

    @GetMapping("/data")
    public  String getData(){
        return "data retrieved";
    }

    @PostMapping("/save")
    public String createData(@RequestBody UserDto userDto){
        userService.save(userDto);
        return "created data";
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public Optional<UserEntity> findById(@PathVariable("id") Integer id) {
        return userService.findById(id);
    }

    @GetMapping("/getAll")
    public List<UserEntity> getAllData() {
        return userService.getAll();
    }

    @GetMapping("/getById/{id}")
    public Optional<UserEntity> getById(@PathVariable("id") Integer id) {
        return userService.getById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public  void deleteById(@PathVariable("id") Integer id) {
        userService.deleteById(id);
    }


    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDto requestDTO) {
        try {
            authenticateService.changePassword(requestDTO.getEmail(), requestDTO.getOldPassword(), requestDTO.getNewPassword());
            return ResponseEntity.ok("Password changed successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to change password. " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @Valid @RequestBody UserDto userDto) {
        try {
            userService.updateUser(id, userDto);
            return ResponseEntity.ok("User updated successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to update user: " + e.getMessage());
        }
    }

}