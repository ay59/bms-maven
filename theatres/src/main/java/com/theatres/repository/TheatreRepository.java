package com.theatres.repository;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.theatres.model.Theatre;
@EnableScan
@Repository
public interface TheatreRepository extends CrudRepository<Theatre, String>{

	

}


