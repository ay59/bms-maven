package com.theatres.repository;

import java.util.List;
import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.theatres.model.City;
import com.theatres.model.Screen;
import com.theatres.model.Theatre;
@EnableScan
@Repository
public interface CityRepository extends CrudRepository<City, String> {

	
   Optional<City> findByCityName(String cityName);


}
