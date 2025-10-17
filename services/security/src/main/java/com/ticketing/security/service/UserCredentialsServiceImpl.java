package com.ticketing.security.service;

import com.ticketing.security.entity.UserCredentials;
import com.ticketing.security.mapper.UserCredentialsMapper;
import com.ticketing.security.repository.UserCredentialsRepository;
import com.ticketing.security.request.UserCredentialsRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCredentialsServiceImpl implements UserCredentialsService {

    private final UserCredentialsMapper userCredentialsMapper;
    private final UserCredentialsRepository userCredentialsRepository;
    private final PasswordEncoder passwordEncoder;
    public UserCredentialsServiceImpl(UserCredentialsMapper userCredentialsMapper,
                                      UserCredentialsRepository userCredentialsRepository,
                                      PasswordEncoder passwordEncoder) {
        this.userCredentialsMapper = userCredentialsMapper;
        this.userCredentialsRepository = userCredentialsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserCredentials createUser(UserCredentialsRequest userCredentialsRequest) {
        UserCredentials newUserCredentials = userCredentialsMapper.userCredentialsRequestToUserCredentials(userCredentialsRequest);
        newUserCredentials.setPassword(passwordEncoder.encode(newUserCredentials.getPassword()));

        return userCredentialsRepository.saveAndFlush(newUserCredentials);
    }
}
