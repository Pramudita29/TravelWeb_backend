package com.example.travelweb.service;

import com.example.travelweb.dto.AuthenticateRequest;
import com.example.travelweb.dto.AuthenticateResponse;

public interface AuthenticateService {

    AuthenticateResponse authenticate(AuthenticateRequest authenticateRequest);

    void changePassword(String email, String oldPassword, String newPassword);


}
