package com.example.bms.bmsmaven.unittest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.bms.bmsmaven.pojo.Movie;
import com.example.bms.bmsmaven.service.MovieService;
import com.example.bms.bmsmaven.service.MovieServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class MovieServiceTest {
    private MovieService movieService;

    @BeforeEach
    public void setup() {

        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("City A", "Theater X", "Movie 1", "Actor A", 4.5, "10:00 AM"));
        movies.add(new Movie("City A", "Theater Y", "Movie 2", "Actor B", 3.8, "12:00 PM"));
        movies.add(new Movie("City B", "Theater Z", "Movie 3", "Actor C", 4.2, "02:00 PM"));
        movieService = new MovieServiceImpl(movies);
    }

    @Test
    public void testGetTheatersInCity() {
        List<String> theatersInCityA = movieService.getTheatersInCity("City A");
        Assertions.assertEquals(2, theatersInCityA.size());
        Assertions.assertTrue(theatersInCityA.contains("Theater X"));
        Assertions.assertTrue(theatersInCityA.contains("Theater Y"));

        List<String> theatersInCityB = movieService.getTheatersInCity("City B");
        Assertions.assertEquals(1, theatersInCityB.size());
        Assertions.assertTrue(theatersInCityB.contains("Theater Z"));

        List<String> theatersInCityC = movieService.getTheatersInCity("City C");
        Assertions.assertEquals(0, theatersInCityC.size());
    }

    @Test
    public void testGetTheatersPlayingMovie() {
        List<String> theatersPlayingMovie1 = movieService.getTheatersPlayingMovie("Movie 1");
        Assertions.assertEquals(1, theatersPlayingMovie1.size());
        Assertions.assertTrue(theatersPlayingMovie1.contains("Theater X"));

        List<String> theatersPlayingMovie2 = movieService.getTheatersPlayingMovie("Movie 2");
        Assertions.assertEquals(1, theatersPlayingMovie2.size());
        Assertions.assertTrue(theatersPlayingMovie2.contains("Theater Y"));

        List<String> theatersPlayingMovie3 = movieService.getTheatersPlayingMovie("Movie 3");
        Assertions.assertEquals(1, theatersPlayingMovie3.size());
        Assertions.assertTrue(theatersPlayingMovie3.contains("Theater Z"));

        List<String> theatersPlayingMovie4 = movieService.getTheatersPlayingMovie("Movie 4");
        Assertions.assertEquals(0, theatersPlayingMovie4.size());
    }

    @Test
    public void testGetMoviesPlayedInTheater() {
        List<String> moviesPlayedInX = movieService.getMoviesPlayedInTheater("Theater X");
        Assertions.assertEquals(1, moviesPlayedInX.size());
        Assertions.assertTrue(moviesPlayedInX.contains("Movie 1"));

        List<String> moviesPlayedInY = movieService.getMoviesPlayedInTheater("Theater Y");
        Assertions.assertEquals(1, moviesPlayedInY.size());
        Assertions.assertTrue(moviesPlayedInY.contains("Movie 2"));

        List<String> moviesPlayedInZ = movieService.getMoviesPlayedInTheater("Theater Z");
        Assertions.assertEquals(1, moviesPlayedInZ.size());
        Assertions.assertTrue(moviesPlayedInZ.contains("Movie 3"));

        List<String> moviesPlayedInA = movieService.getMoviesPlayedInTheater("Theater A");
        Assertions.assertEquals(0, moviesPlayedInA.size());
    }

    @Test
    public void testGetMoviesPlayedInCity() {
        List<String> moviesPlayedInCityA = movieService.getMoviesPlayedInCity("City A");
        Assertions.assertEquals(2, moviesPlayedInCityA.size());
        Assertions.assertTrue(moviesPlayedInCityA.contains("Movie 1"));
        Assertions.assertTrue(moviesPlayedInCityA.contains("Movie 2"));

        List<String> moviesPlayedInCityB = movieService.getMoviesPlayedInCity("City B");
        Assertions.assertEquals(1, moviesPlayedInCityB.size());
        Assertions.assertTrue(moviesPlayedInCityB.contains("Movie 3"));

        List<String> moviesPlayedInCityC = movieService.getMoviesPlayedInCity("City C");
        Assertions.assertEquals(0, moviesPlayedInCityC.size());
    }

    @Test
    public void testGetMoviesAboveRating() {
        List<Movie> moviesAboveRating4 = movieService.getMoviesAboveRating(4.0);
        Assertions.assertEquals(2, moviesAboveRating4.size());

        List<Movie> moviesAboveRating4_5 = movieService.getMoviesAboveRating(4.5);
        Assertions.assertEquals(1, moviesAboveRating4_5.size());
    }

    @Test
    public void testGetShowTimesForMovieAndTheater() {
        List<String> showTimes1 = movieService.getShowTimesForMovieAndTheater("Movie 1", "Theater X");
        Assertions.assertEquals(1, showTimes1.size());
        Assertions.assertTrue(showTimes1.contains("10:00 AM"));

        List<String> showTimes2 = movieService.getShowTimesForMovieAndTheater("Movie 2", "Theater Y");
        Assertions.assertEquals(1, showTimes2.size());
        Assertions.assertTrue(showTimes2.contains("12:00 PM"));

        List<String> showTimes3 = movieService.getShowTimesForMovieAndTheater("Movie 3", "Theater Z");
        Assertions.assertEquals(1, showTimes3.size());
        Assertions.assertTrue(showTimes3.contains("02:00 PM"));

        List<String> showTimes4 = movieService.getShowTimesForMovieAndTheater("Movie 4", "Theater A");
        Assertions.assertEquals(0, showTimes4.size());
    }
}
