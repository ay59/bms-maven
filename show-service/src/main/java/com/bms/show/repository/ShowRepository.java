package com.bms.show.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bms.show.entity.Show;

@EnableScan
@Repository
public interface ShowRepository extends CrudRepository<Show, String> {

	List<Show> findByMovieId(String movieId);

	List<Show> findByTheaterId(String theaterId);

	List<Show> findByMovieIdAndTheaterId(String movieId, String theaterId);

	boolean existsByScreenIdAndTheaterIdAndShowDateAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(int screenId,
			String theaterId, LocalDate showDate, LocalTime endTime, LocalTime startTime);

	boolean existsByScreenIdAndTheaterId(int screenId, String theaterId);
}
