package com.afkir.hotel.guest.domain.model;

import java.util.UUID;

import jakarta.persistence.*;

import lombok.Builder;
import lombok.Getter;

@Entity
@Table(name = "guest")
@Getter
@Builder
public class Guest {

    @Id
    private UUID id;

    private String firstName;

    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    private LoyaltyTier loyaltyTier;

    protected Guest() {
    }

    public Guest(String firstName, String lastName, String email, String phone) {
        this.id = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.loyaltyTier = LoyaltyTier.STANDARD;
    }

    public void promoteTo(LoyaltyTier tier) {
        this.loyaltyTier = tier;
    }

}
