package com.afkir.hotel.guest.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "guest")
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

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LoyaltyTier getLoyaltyTier() {
        return loyaltyTier;
    }
}
