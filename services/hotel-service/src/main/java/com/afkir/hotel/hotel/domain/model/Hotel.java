package com.afkir.hotel.hotel.domain.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "hotel")
@Getter
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

}
