package com.theatres.services;
import com.theatres.model.City;
import com.theatres.model.Theatre;

import com.theatres.model.City;
import com.theatres.model.Theatre;

import java.util.List;
import org.springframework.stereotype.Service;
@Service
public interface CityService {
    City addCity(City city);

    List<City> getAllCities();

    void removeCity(String cityId);
}
