package com.example.bms.bmsmaven.service;

import java.util.List;

import com.example.bms.bmsmaven.pojo.Movie;

public interface MovieService {
	
	public List<String> getTheatersInCity(String city);
	public List<String> getTheatersPlayingMovie(String movie);
	public List<String> getMoviesPlayedInTheater(String theater);
	public List<String> getMoviesPlayedInCity(String city);
	public List<Movie> getMoviesAboveRating(double rating);
	public List<String> getShowTimesForMovieAndTheater(String movie, String theater);

}
