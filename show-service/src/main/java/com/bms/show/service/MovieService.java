package com.bms.show.service;

import java.util.List;

import com.bms.show.entity.Movie;

public interface MovieService {

	public List<Movie> getAllMovies();

	public Movie saveMovie(Movie movie);

	public void updateMovie(String movieId, Movie movie);

	public void deleteMovie(String movieId);

	public Movie getMovieById(String movieId);

	public List<Movie> findAllById(List<String> movieIds);

	public List<Movie> getMoviesAboveRating(double minRating);

}
