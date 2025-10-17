package com.ticketing.security.mapper;

import com.ticketing.security.entity.UserCredentials;
import com.ticketing.security.request.UserCredentialsRequest;
import com.ticketing.security.response.UserCredentialsResponse;
import com.ticketing.security.type.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface UserCredentialsMapper {
    public UserCredentialsResponse userCredentialsToUserCredentialsResponse(UserCredentials userCredentials);
    @Mapping(target = "role", source = "roleValue", qualifiedByName = "roleValueToRole")
    public UserCredentials userCredentialsRequestToUserCredentials(UserCredentialsRequest userCredentialsRequest);
    @Named("roleValueToRole")
    public static Role roleValueToRole(int roleValue) {
        return Role.values()[roleValue];
    }
}
