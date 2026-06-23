package com.afkir.hotel.hotel.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    private UUID id;

    private String name;

    private String address;

    private String city;

    protected Hotel() {
    }

    public Hotel(String name, String address, String city) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.address = address;
        this.city = city;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }
}
