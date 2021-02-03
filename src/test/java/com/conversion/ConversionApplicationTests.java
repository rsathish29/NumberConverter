package com.conversion;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {"HOST_IP=localhost"})
class ConversionApplicationTests {

    @Test
    void contextLoads() {
    }

}
