package com.ticketing.inventory.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "total_capacity")
    private Long totalCapacity;
    @Column(name = "left_capacity")
    private Long leftCapacity;
    @Column(name = "ticket_price")
    private BigDecimal ticketPrice;

    @ManyToOne
    @JoinColumn(name = "venue_id")
    private Venue venue;
}