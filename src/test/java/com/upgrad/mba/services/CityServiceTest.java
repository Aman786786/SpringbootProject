package com.upgrad.mba.services;

import com.upgrad.mba.dao.CityDao;
import com.upgrad.mba.entities.City;
import com.upgrad.mba.exceptions.CityDetailsNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class CityServiceTest {

    @Mock
    private CityDao cityDao;

    @InjectMocks
    private CityServiceImpl cityService;

    @BeforeEach
    public void setupMockito() {
        Mockito.when(cityDao.save(new City("save test"))).thenReturn(new City(1, "save test"));

        Mockito.when(cityDao.findById(1)).thenReturn(Optional.of(new City(1, "save test")));
        Mockito.when(cityDao.save(new City(1, "update test"))).thenReturn(new City(1, "update test"));

        Mockito.when(cityDao.save(new City("get test"))).thenReturn(new City(1, "get test"));
        Mockito.when(cityDao.findByCityName("get test")).thenReturn(new City(1, "get test"));
    }

    @Test
    public void testAcceptCityDetails() {

        City city = new City();
        city.setCityName("save test");
        City savedCity = cityService.acceptCityDetails(city);
        Assertions.assertNotNull(savedCity);
        Assertions.assertTrue(savedCity.getCityId() != 0);
        Assertions.assertEquals("save test", savedCity.getCityName());
    }

    @Test
    public void testUpdateCityDetails() throws CityDetailsNotFoundException {
        City city = new City();
        city.setCityName("save test");
        City savedCity = cityService.acceptCityDetails(city);

        City updateCity = new City();
        updateCity.setCityName("update test");
        City updatedCity = cityService.updateCityDetails(savedCity.getCityId(), updateCity);

        Assertions.assertNotNull(updatedCity);
        Assertions.assertTrue(updatedCity.getCityId() != 0);
        Assertions.assertEquals("update test", updatedCity.getCityName());
        Assertions.assertEquals(updatedCity.getCityId(), savedCity.getCityId());
        Assertions.assertNotEquals(updatedCity.getCityName(), savedCity.getCityName());
    }

    @Test
    public void testGetCityDetailsByCityName() throws CityDetailsNotFoundException {
        City city = new City();
        city.setCityName("get test");
        cityService.acceptCityDetails(city);

        City savedCity = cityService.getCityDetailsByCityName("get test");
        Assertions.assertNotNull(savedCity);
        Assertions.assertTrue(savedCity.getCityId() != 0);
        Assertions.assertEquals("get test", savedCity.getCityName());
    }
}
