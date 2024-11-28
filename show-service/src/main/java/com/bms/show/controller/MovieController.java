package com.bms.show.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bms.show.entity.Movie;
import com.bms.show.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

	@Autowired
	private MovieService movieService;

	/**
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> getAllMovies() {
		List<Movie> movies = movieService.getAllMovies();
		return ResponseEntity.ok().body(movies);
	}

	/**
	 * @param movie
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> saveMovie(@RequestBody Movie movie) {
		Movie mv = movieService.saveMovie(movie);
		return ResponseEntity.status(HttpStatus.CREATED).body(mv);
	}

	/**
	 * @param movieId
	 * @param movie
	 * @return
	 */
	@PutMapping("/{movieId}")
	public ResponseEntity<?> updateMovie(@PathVariable String movieId, @RequestBody Movie movie) {
		movieService.updateMovie(movieId, movie);
		return ResponseEntity.noContent().build();
	}

	/**
	 * @param movieId
	 * @return
	 */
	@DeleteMapping("/{movieId}")
	public ResponseEntity<?> deleteMovie(@PathVariable String movieId) {
		movieService.deleteMovie(movieId);
		return ResponseEntity.noContent().build();
	}

	/**
	 * @param movieId
	 * @return
	 */
	@GetMapping("/{movieId}")
	public ResponseEntity<?> getMovieById(@PathVariable String movieId) {
		Movie movie = movieService.getMovieById(movieId);
		return ResponseEntity.ok().body(movie);
	}

	/**
	 * @param movieIds
	 * @return
	 */
	@GetMapping("/by-ids")
	public ResponseEntity<?> getMoviesByIds(@RequestParam List<String> movieIds) {
		List<Movie> movies = movieService.findAllById(movieIds);
		return ResponseEntity.ok().body(movies);
	}

	/**
	 * @param minRating
	 * @return
	 */
	@GetMapping("/rating/{minRating}")
	public ResponseEntity<?> getMoviesAboveRating(@PathVariable double minRating) {
		List<Movie> movies = movieService.getMoviesAboveRating(minRating);
		return ResponseEntity.ok().body(movies);
	}

}
