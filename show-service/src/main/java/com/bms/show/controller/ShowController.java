package com.bms.show.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bms.show.entity.Movie;
import com.bms.show.entity.Show;
import com.bms.show.service.ShowService;

import java.util.List;

@RestController
@RequestMapping("/shows")
public class ShowController {

	@Autowired
	private ShowService showService;

	/**
	 * @return
	 */
	@GetMapping
	public ResponseEntity<?> getAllShows() {
		List<Show> shows = showService.getAllShows();
		return ResponseEntity.ok().body(shows);
	}

	/**
	 * @param show
	 * @return
	 */
	@PostMapping
	public ResponseEntity<?> saveShow(@RequestBody Show show) {
		Show shw = showService.saveShow(show);
		return ResponseEntity.status(HttpStatus.CREATED).body(shw);
	}

	/**
	 * @param showId
	 * @param show
	 * @return
	 */
	@PutMapping("/{showId}")
	public ResponseEntity<?> updateShow(@PathVariable String showId, @RequestBody Show show) {
		showService.updateShow(showId, show);
		return ResponseEntity.noContent().build();
	}

	/**
	 * @param showId
	 * @return
	 */
	@DeleteMapping("/{showId}")
	public ResponseEntity<?> deleteShow(@PathVariable String showId) {
		showService.deleteShow(showId);
		return ResponseEntity.noContent().build();
	}

	/**
	 * @param showId
	 * @return
	 */
	@GetMapping("/{showId}")
	public ResponseEntity<?> getShowById(@PathVariable String showId) {
		Show show = showService.getShowById(showId);
		return ResponseEntity.ok().body(show);
	}

	/**
	 * @param movieId
	 * @return
	 */
	@GetMapping("/movie/{movieId}")
	public ResponseEntity<?> findByMovieId(@PathVariable String movieId) {
		List<Show> shows = showService.getShowByMovieId(movieId);
		return ResponseEntity.ok().body(shows);
	}

	/**
	 * @param theaterId
	 * @return
	 */
	@GetMapping("/theater/{theaterId}")
	public ResponseEntity<?> findByTheaterId(@PathVariable String theaterId) {
		List<Show> shows = showService.findByTheaterId(theaterId);
		return ResponseEntity.ok().body(shows);
	}

	/**
	 * @param theaterId
	 * @return
	 */
	@GetMapping("/{theaterId}/movies")
	public ResponseEntity<?> getMoviesPlayedInTheater(@PathVariable String theaterId) {
		List<Movie> movies = showService.getMoviesPlayedInTheater(theaterId);
		return ResponseEntity.ok().body(movies);
	}

	/**
	 * @param movieId
	 * @param theaterId
	 * @return
	 */
	@GetMapping("/{theaterId}/movie/{movieId}/showtimes")
	public ResponseEntity<?> getShowTimesForMovieInTheater(@PathVariable String movieId,
			@PathVariable String theaterId) {
		List<Show> shows = showService.getShowTimesForMovieInTheater(movieId, theaterId);
		return ResponseEntity.ok().body(shows);
	}

//	@GetMapping("/{movieId}/theaters")
//	public ResponseEntity<?> getTheatersPlayingMovie(@PathVariable String movieId) {
//		List<Theater> theaters =  showService.getTheatersPlayingMovie(movieId);
//		BaseResponse baseResponse = new BaseResponse();
//		baseResponse.setStatusCode(1);
//		baseResponse.setResponse(theaters);
//		return new ResponseEntity<>(baseResponse, HttpStatus.CREATED);
//	}

}
