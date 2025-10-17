package com.ticketing.security.controller;

import com.ticketing.security.entity.UserCredentials;
import com.ticketing.security.mapper.UserCredentialsMapper;
import com.ticketing.security.request.UserCredentialsRequest;
import com.ticketing.security.response.UserCredentialsResponse;
import com.ticketing.security.service.UserCredentialsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserCredentialsMapper userCredentialsMapper;
    private final UserCredentialsService userCredentialsService;
    public AuthController(UserCredentialsMapper userCredentialsMapper,
                          UserCredentialsService userCredentialsService) {
        this.userCredentialsMapper = userCredentialsMapper;
        this.userCredentialsService = userCredentialsService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserCredentialsResponse> signup(@RequestBody @Valid
                                                          UserCredentialsRequest userCredentialsRequest) {
        return ResponseEntity.ok(userCredentialsMapper
                .userCredentialsToUserCredentialsResponse(userCredentialsService
                        .createUser(userCredentialsRequest)));
    }
}
