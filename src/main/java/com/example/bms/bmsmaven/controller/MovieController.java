package com.example.bms.bmsmaven.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bms.bmsmaven.pojo.Movie;
import com.example.bms.bmsmaven.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
	
	@Autowired
    private MovieService movieService;

	/*
	 * public MovieController(MovieService movieService) { this.movieService =
	 * movieService; }
	 */

    @GetMapping("/theaters/{city}")
    public ResponseEntity<List<String>> getTheatersInCity(@PathVariable String city) {
        List<String> theaters = movieService.getTheatersInCity(city);
        return ResponseEntity.ok(theaters);
    }

    @GetMapping("/theaters/movie/{movie}")
    public ResponseEntity<List<String>> getTheatersPlayingMovie(@PathVariable String movie) {
        List<String> theaters = movieService.getTheatersPlayingMovie(movie);
        return ResponseEntity.ok(theaters);
    }

    @GetMapping("/movies/{theater}")
    public ResponseEntity<List<String>> getMoviesPlayedInTheater(@PathVariable String theater) {
        List<String> movies = movieService.getMoviesPlayedInTheater(theater);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/movies/city/{city}")
    public ResponseEntity<List<String>> getMoviesPlayedInCity(@PathVariable String city) {
        List<String> movies = movieService.getMoviesPlayedInCity(city);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/movies/rating/{rating}")
    public ResponseEntity<List<Movie>> getMoviesAboveRating(@PathVariable double rating) {
        List<Movie> movies = movieService.getMoviesAboveRating(rating);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/showtimes/{movie}/{theater}")
    public ResponseEntity<List<String>> getShowTimesForMovieAndTheater(
            @PathVariable String movie,
            @PathVariable String theater) {
        List<String> showTimes = movieService.getShowTimesForMovieAndTheater(movie, theater);
        return ResponseEntity.ok(showTimes);
    }
}
