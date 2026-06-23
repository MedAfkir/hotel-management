package com.afkir.hotel.notification;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(properties = {
        "eureka.client.enabled=false",
        "spring.cloud.config.enabled=false",
        "spring.jpa.hibernate.ddl-auto=create-drop"
})
@Import(TestcontainersConfiguration.class)
class NotificationServiceApplicationTests {

    @Test
    void contextLoads() {
    }
}
