package com.ticketing.inventory.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TicketsBookedRequest(
        @NotBlank
        @Min(1)
        Long eventId,
        @NotBlank
        @Min(1)
        Long ticketsBooked) {
}
