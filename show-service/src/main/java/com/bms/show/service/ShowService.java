package com.bms.show.service;

import java.util.List;

import com.bms.show.entity.Movie;
import com.bms.show.entity.Show;

public interface ShowService {

	public List<Show> getAllShows();

	public Show saveShow(Show show);

	public void updateShow(String showId, Show show);

	public void deleteShow(String showId);

	public Show getShowById(String showId);

	public List<Show> getShowByMovieId(String movieId);

	public List<Show> findByTheaterId(String theaterId);

	public List<Movie> getMoviesPlayedInTheater(String theaterId);

	public List<Show> getShowTimesForMovieInTheater(String movieId, String theaterId);

}
