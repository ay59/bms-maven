package com.theatres.repository;



import java.util.List;
import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import com.theatres.model.Screen;
@EnableScan
@Repository
public interface ScreenRepository extends CrudRepository<Screen, String> {

//	Iterable<Screen> findAllById(List<String> screenIds);


}

