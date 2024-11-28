package com.theatres.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.theatres.model.City;
import com.theatres.repository.CityRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("CityService")
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public City addCity(City city) {
        if (city.getCityId() == null || city.getCityId().isEmpty()) {
            city.setCityId(UUID.randomUUID().toString());
        }
        Optional<City> existingCity = cityRepository.findByCityName(city.getCityName());

        if (existingCity.isPresent()) {
            throw new RuntimeException("A city with the same name already exists: " + city.getCityName());
        } else {
            return cityRepository.save(city);
        }
    }

 

    @Override
    public List<City> getAllCities() {
        return (List<City>) cityRepository.findAll();
    }
    @Override
    public void removeCity(String cityId) throws RuntimeException {
        Optional<City> existingCity = cityRepository.findById(cityId);

        if (existingCity.isPresent()) {
            cityRepository.deleteById(cityId);
        } else {
            throw new RuntimeException("City not found with ID: " + cityId);
        }
    }

}
