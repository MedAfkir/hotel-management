package com.afkir.hotel.housekeeping;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(properties = {
        "eureka.client.enabled=false",
        "spring.cloud.config.enabled=false",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@Import(TestcontainersConfiguration.class)
class HousekeepingServiceApplicationTests {

    @Test
    void contextLoads() {
    }
}
