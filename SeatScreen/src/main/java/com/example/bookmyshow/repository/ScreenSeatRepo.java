package com.example.bookmyshow.repository;

import com.example.bookmyshow.models.ScreenSeat;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@EnableScan
@Repository
public interface ScreenSeatRepo extends PagingAndSortingRepository<ScreenSeat, String> {

}
