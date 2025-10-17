package com.ticketing.security.service;

import com.ticketing.security.entity.UserCredentials;
import com.ticketing.security.request.UserCredentialsRequest;

public interface UserCredentialsService {
    public UserCredentials createUser(UserCredentialsRequest userCredentialsRequest);
}
