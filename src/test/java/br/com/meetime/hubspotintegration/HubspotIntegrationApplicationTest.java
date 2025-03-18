package br.com.meetime.hubspotintegration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class HubspotIntegrationApplicationTest {

    @Test
    void applicationContextLoads() {
        HubspotIntegrationApplication.main(new String[] {});
        assertTrue(true);
    }
}