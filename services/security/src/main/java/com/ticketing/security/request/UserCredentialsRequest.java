package com.ticketing.security.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

public record UserCredentialsRequest(
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String password,
        @Min(0) @Max(2)
        int roleValue) {
}
