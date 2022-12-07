package com.upgrad.mba.services;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SampleTest {

    @Autowired
    private CityService cityService;

    @Test
    public void testCityServiceNotNull() {
        Assertions.assertNotNull(cityService);
    }
}
