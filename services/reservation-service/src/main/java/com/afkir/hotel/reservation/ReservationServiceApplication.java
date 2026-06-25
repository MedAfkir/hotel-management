package com.afkir.hotel.reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ReservationServiceApplication {

    static void main(String[] args) {
        SpringApplication.run(ReservationServiceApplication.class, args);
    }

}
