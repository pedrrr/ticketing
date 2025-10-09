package com.ticketing.booking.service;

import com.ticketing.booking.entity.Customer;

import java.util.Optional;

public interface CustomerService {
    Optional<Customer> findById(Long id);
}
