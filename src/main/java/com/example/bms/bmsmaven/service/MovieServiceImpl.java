package com.example.bms.bmsmaven.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.bms.bmsmaven.pojo.Movie;

@Service
public class MovieServiceImpl implements MovieService {
    private List<Movie> movies;

    public MovieServiceImpl(List<Movie> movies) {
        this.movies = movies;
    }
    
    @Value("${movie.data.file}")
    private String movieDataFile;

    public MovieServiceImpl() {
        movies = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        try (BufferedReader br = new BufferedReader(new FileReader(movieDataFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String cityName = data[0].trim();
                String theaterName = data[1].trim();
                String movieName = data[2].trim();
                String actors = data[3].trim();
                double movieRating = Double.parseDouble(data[4].trim());
                String showTime = data[5].trim();

                Movie movie = new Movie(cityName, theaterName, movieName, actors, movieRating, showTime);
                movies.add(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getTheatersInCity(String city) {
        return movies.stream()
                .filter(movie -> movie.getCityName().equals(city))
                .map(Movie::getTheaterName)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> getTheatersPlayingMovie(String movie) {
        return movies.stream()
                .filter(m -> m.getMovieName().equals(movie))
                .map(Movie::getTheaterName)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> getMoviesPlayedInTheater(String theater) {
        return movies.stream()
                .filter(movie -> movie.getTheaterName().equals(theater))
                .map(Movie::getMovieName)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<String> getMoviesPlayedInCity(String city) {
        return movies.stream()
                .filter(movie -> movie.getCityName().equals(city))
                .map(Movie::getMovieName)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Movie> getMoviesAboveRating(double rating) {
        return movies.stream()
                .filter(movie -> movie.getMovieRating() >= rating)
                .collect(Collectors.toList());
    }

    public List<String> getShowTimesForMovieAndTheater(String movie, String theater) {
        return movies.stream()
                .filter(m -> m.getMovieName().equals(movie) && m.getTheaterName().equals(theater))
                .map(Movie::getShowTime)
                .collect(Collectors.toList());
    }
}
