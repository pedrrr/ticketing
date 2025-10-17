package com.ticketing.security.response;

public record UserCredentialsResponse(Long id,
                                      String name,
                                      String email) {
}
