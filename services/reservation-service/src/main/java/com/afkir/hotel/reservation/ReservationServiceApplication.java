package com.afkir.hotel.reservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.afkir.hotel")
@EntityScan("com.afkir.hotel")
@EnableJpaRepositories("com.afkir.hotel")
@EnableScheduling
public class ReservationServiceApplication {

    static void main(String[] args) {
        SpringApplication.run(ReservationServiceApplication.class, args);
    }

}
