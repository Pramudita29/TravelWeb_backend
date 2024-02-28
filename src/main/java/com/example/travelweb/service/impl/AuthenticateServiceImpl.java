package com.example.travelweb.service.impl;


import com.example.travelweb.config.PasswordEncoderUtil;
import com.example.travelweb.dto.AuthenticateRequest;
import com.example.travelweb.dto.AuthenticateResponse;
import com.example.travelweb.entity.UserEntity;
import com.example.travelweb.repository.UserRepository;
import com.example.travelweb.security.JwtService;
import com.example.travelweb.service.AuthenticateService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticateServiceImpl implements AuthenticateService {

    private final UserRepository userRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public AuthenticateResponse authenticate(AuthenticateRequest authenticateRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticateRequest.getEmail(), authenticateRequest.getPassword()
                )
        );

        UserEntity user = userRepo.getUserByUsername(authenticateRequest.getEmail())
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
        UserDetails userDetails = user;
        String jwtToken = jwtService.generateToken(userDetails);


        return AuthenticateResponse.builder().token(jwtToken).userId(user.getId()).role(user.getId()==1?"admin":"").build();
    }



    @Override
    public void changePassword(String email, String oldPassword, String newPassword) {
        Optional<UserEntity> optionalUser = userRepo.getUserByUsername(email);
        optionalUser.ifPresent(user -> {
            // Validate old password
            if (PasswordEncoderUtil.getInstance().matches(oldPassword, user.getPassword())) {
                // Update password if old password is valid
                user.setPassword(PasswordEncoderUtil.getInstance().encode(newPassword));
                userRepo.save(user);
            } else {
                throw new RuntimeException("Invalid old password.");
            }
        });
    }
}