package com.bms.show.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.show.entity.Movie;
import com.bms.show.exception.DuplicateMovieException;
import com.bms.show.exception.MovieNotFoundException;
import com.bms.show.exception.OperationFailedException;
import com.bms.show.repository.MovieRepository;
import com.bms.show.util.AppConstants;
import com.bms.show.util.Genre;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieRepository movieRepository;

	/**
	 * @param movie
	 * @return
	 */
	public Movie saveMovie(Movie movie) {
		try {
			Movie existingMovie = movieRepository.findByMovieNameAndReleaseYear(movie.getMovieName(),
					movie.getReleaseYear());

			if (existingMovie != null) {
				throw new DuplicateMovieException(AppConstants.DUPLICATE_MOVIE);
			} else {
				return movieRepository.save(movie);
			}
		} catch (DuplicateMovieException dm) {
			dm.printStackTrace();
			throw dm;
		} catch (Exception e) {
			throw new OperationFailedException(AppConstants.OPERATION_FAILED_MOVIE_CREATE);
		}
	}

	/**
	 * @param movieId
	 * @param updatedMovie
	 */
	public void updateMovie(String movieId, Movie updatedMovie) {
		try {
			Movie duplicateMovie = movieRepository.findByMovieNameAndReleaseYear(updatedMovie.getMovieName(),
					updatedMovie.getReleaseYear());

			if (duplicateMovie != null) {
				throw new DuplicateMovieException(AppConstants.DUPLICATE_MOVIE);
			} else {
				Movie existingMovie = getMovieById(movieId);
				existingMovie.setMovieName(updatedMovie.getMovieName());
				existingMovie.setRating(updatedMovie.getRating());
				existingMovie.setLanguages(updatedMovie.getLanguages());
				existingMovie.setGenre(updatedMovie.getGenre());
				existingMovie.setActors(updatedMovie.getActors());
				existingMovie.setReleaseYear(updatedMovie.getReleaseYear());

				movieRepository.save(existingMovie);
			}

		} catch (DuplicateMovieException dm) {
			dm.printStackTrace();
			throw dm;
		} catch (MovieNotFoundException mnf) {
			mnf.printStackTrace();
			throw mnf;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OperationFailedException(AppConstants.OPERATION_FAILED_MOVIE_UPDATE + movieId);
		}
	}

	/**
	 * @param movieId
	 */
	public void deleteMovie(String movieId) {
		try {
			Movie movie = getMovieById(movieId);
			movieRepository.delete(movie);
		} catch (MovieNotFoundException mnf) {
			mnf.printStackTrace();
			throw mnf;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OperationFailedException(AppConstants.OPERATION_FAILED_MOVIE_DELETE + movieId);
		}
	}

	/**
	 * @return
	 */
	public List<Movie> getAllMovies() {
		try {
			List<Movie> movies = (List<Movie>) movieRepository.findAll();
			if (movies.isEmpty()) {
				throw new MovieNotFoundException(AppConstants.NO_MOVIES_RECORD);
			}
			return movies;
		} catch (MovieNotFoundException mnf) {
			mnf.printStackTrace();
			throw mnf;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OperationFailedException(AppConstants.OPERATION_FAILED_CONST);
		}
	}

	/**
	 * @param movieId
	 * @return
	 */
	public Movie getMovieById(String movieId) {
		try {
			Movie movie = movieRepository.findById(movieId).orElse(null);
			if (movie == null) {
				throw new MovieNotFoundException(AppConstants.MOVIE_NOT_FOUND_ID + movieId);
			}
			return movie;
		} catch (MovieNotFoundException mnf) {
			mnf.printStackTrace();
			throw mnf;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OperationFailedException(AppConstants.OPERATION_FAILED_CONST);
		}
	}

	/**
	 * @param movieIds
	 * @return
	 */
	public List<Movie> findAllById(List<String> movieIds) {
		try {
			List<Movie> movies = (List<Movie>) movieRepository.findAllById(movieIds);
			if (movies.isEmpty()) {
				throw new MovieNotFoundException(AppConstants.MOVIE_NOT_FOUND_IDS);
			}
			return movies;
		} catch (MovieNotFoundException mnf) {
			mnf.printStackTrace();
			throw mnf;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OperationFailedException(AppConstants.OPERATION_FAILED_CONST);
		}
	}

	/**
	 * @param minRating
	 * @return
	 */
	public List<Movie> getMoviesAboveRating(double minRating) {
		try {
			List<Movie> movies = movieRepository.findByRatingGreaterThan(minRating);
			if (movies.isEmpty()) {
				throw new MovieNotFoundException(AppConstants.MOVIE_NOT_FOUND_RATING + minRating);
			}
			return movies;
		} catch (MovieNotFoundException mnf) {
			mnf.printStackTrace();
			throw mnf;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OperationFailedException(AppConstants.OPERATION_FAILED_CONST);
		}
	}

	/**
	 * @param name
	 * @return
	 */
	public List<Movie> searchMoviesByName(String name) {
		try {
			List<Movie> movies = movieRepository.findByMovieName(name);
			if (movies.isEmpty()) {
				throw new MovieNotFoundException(AppConstants.MOVIE_NOT_FOUND_NAME + name);
			}
			return movies;
		} catch (MovieNotFoundException mnf) {
			mnf.printStackTrace();
			throw mnf;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OperationFailedException(AppConstants.OPERATION_FAILED_CONST);
		}

	}

	/**
	 * @param releaseYear
	 * @return
	 */
	public List<Movie> searchMoviesByReleaseYear(int releaseYear) {
		try {
			List<Movie> movies = movieRepository.findByReleaseYear(releaseYear);
			if (movies.isEmpty()) {
				throw new MovieNotFoundException(AppConstants.MOVIE_NOT_FOUND_YEAR + releaseYear);
			}
			return movies;
		} catch (MovieNotFoundException mnf) {
			mnf.printStackTrace();
			throw mnf;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OperationFailedException(AppConstants.OPERATION_FAILED_CONST);
		}

	}

	/**
	 * @param genre
	 * @return
	 */
	public List<Movie> searchMoviesByGenre(Genre genre) {
		try {
			List<Movie> movies = movieRepository.findByGenreContains(genre);
			if (movies.isEmpty()) {
				throw new MovieNotFoundException(AppConstants.MOVIE_NOT_FOUND_GENRE + genre);
			}
			return movies;
		} catch (MovieNotFoundException mnf) {
			mnf.printStackTrace();
			throw mnf;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OperationFailedException(AppConstants.OPERATION_FAILED_CONST);
		}
	}

	/**
	 * @param actor
	 * @return
	 */
	public List<Movie> searchMoviesByActor(String actor) {
		try {
			List<Movie> movies = movieRepository.findByActorsContains(actor);
			if (movies.isEmpty()) {
				throw new MovieNotFoundException(AppConstants.MOVIE_NOT_FOUND_ACTOR + actor);
			}
			return movies;
		} catch (MovieNotFoundException mnf) {
			mnf.printStackTrace();
			throw mnf;
		} catch (Exception e) {
			e.printStackTrace();
			throw new OperationFailedException(AppConstants.OPERATION_FAILED_CONST);
		}
	}

}
