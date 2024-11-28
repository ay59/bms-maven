package com.bms.show.repository;

import java.util.List;
import java.util.Optional;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bms.show.entity.Movie;
import com.bms.show.util.Genre;

@EnableScan
@Repository
public interface MovieRepository extends CrudRepository<Movie, String> {

	Optional<Movie> findByMovieId(String movieId);

	List<Movie> findByRatingGreaterThan(double minRating);

	Movie findByMovieNameAndReleaseYear(String movieName, int releaseYear);

	List<Movie> findByMovieName(String name);

	List<Movie> findByReleaseYear(int releaseYear);

	List<Movie> findByActorsContains(String actor);

	List<Movie> findByGenreContains(Genre genre);

}
