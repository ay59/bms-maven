package com.bms.show.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bms.show.entity.Movie;
import com.bms.show.entity.Show;
import com.bms.show.util.AppConstants;
import com.bms.show.exception.DuplicateShowException;
import com.bms.show.exception.NoShowsFoundException;
import com.bms.show.exception.OperationFailedException;
import com.bms.show.exception.ShowNotFoundException;
import com.bms.show.repository.ShowRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowServiceImpl implements ShowService {

	@Autowired
	private ShowRepository showRepository;

//	@Autowired
//	private FeignTheaterService theaterService;

	@Autowired
	private MovieService movieService;

	/**
	 * @param show
	 * @return
	 */
	public Show saveShow(Show show) {
		try {
			boolean isDuplicate = isScreenOccupiedDuringTimeSlot(show);

			if (isDuplicate) {
				throw new DuplicateShowException(AppConstants.DUPLICATE_SHOW);
			} else {
				Show savedShow = showRepository.save(show);
				return savedShow;
			}
		} catch (DuplicateShowException dse) {
			dse.printStackTrace();
			throw dse;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new OperationFailedException(AppConstants.OPERATION_FAILED_SHOW_CREATE);
		}
	}

	/**
	 * @param showId
	 * @param updatedShow
	 */
	public void updateShow(String showId, Show updatedShow) {
		try {
			Show existingShow = getShowById(showId);

			existingShow.setStartTime(updatedShow.getStartTime());
			existingShow.setEndTime(updatedShow.getEndTime());
			existingShow.setShowDate(updatedShow.getShowDate());
			existingShow.setMovieId(updatedShow.getMovieId());
			existingShow.setScreenId(updatedShow.getScreenId());
			existingShow.setTheaterId(updatedShow.getTheaterId());
			existingShow.setLanguage(updatedShow.getLanguage());

			showRepository.save(existingShow);
		} catch (ShowNotFoundException snf) {
			snf.printStackTrace();
			throw snf;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new OperationFailedException(AppConstants.OPERATION_FAILED_SHOW_UPDATE + showId);
		}
	}

	/**
	 * @param showId
	 */
	public void deleteShow(String showId) {
		try {
			Show show = getShowById(showId);
			showRepository.delete(show);
		} catch (ShowNotFoundException snf) {
			snf.printStackTrace();
			throw snf;
		} catch (Exception e) {
			throw new OperationFailedException(AppConstants.OPERATION_FAILED_SHOW_DELETE + showId);
		}
	}

	/**
	 * @return
	 */
	public List<Show> getAllShows() {
		try {
			List<Show> shows = (List<Show>) showRepository.findAll();
			if (shows.isEmpty()) {
				throw new NoShowsFoundException(AppConstants.NO_SHOW_RECORD);
			}
			return shows;
		} catch (NoShowsFoundException nsf) {
			nsf.printStackTrace();
			throw nsf;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new OperationFailedException(AppConstants.OPERATION_FAILED_CONST);
		}

	}

	/**
	 * @param showId
	 * @return
	 */
	public Show getShowById(String showId) {
		try {
			Show show = showRepository.findById(showId).orElse(null);
			if (show == null) {
				throw new ShowNotFoundException(AppConstants.SHOW_NOT_FOUND_ID + showId);
			}
			return show;
		} catch (ShowNotFoundException snf) {
			snf.printStackTrace();
			throw snf;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new OperationFailedException(AppConstants.OPERATION_FAILED_CONST);
		}
	}

	/**
	 * @param movieId
	 * @return
	 */
	public List<Show> getShowByMovieId(String movieId) {
		try {
			List<Show> shows = showRepository.findByMovieId(movieId);
			if (shows.isEmpty()) {
				throw new ShowNotFoundException(AppConstants.SHOW_NOT_FOUND_MOVIE + movieId);
			}
			return shows;
		} catch (ShowNotFoundException snf) {
			snf.printStackTrace();
			throw snf;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new OperationFailedException(AppConstants.OPERATION_FAILED_CONST);
		}
	}

	/**
	 * @param theaterId
	 * @return
	 */
	public List<Show> findByTheaterId(String theaterId) {
		try {
			List<Show> shows = showRepository.findByTheaterId(theaterId);
			if (shows.isEmpty()) {
				throw new ShowNotFoundException(AppConstants.SHOW_NOT_FOUND_THEATER + theaterId);
			}
			return shows;
		} catch (ShowNotFoundException snf) {
			snf.printStackTrace();
			throw snf;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new OperationFailedException(AppConstants.OPERATION_FAILED_CONST);
		}
	}

	/**
	 * @param movieId
	 * @param theaterId
	 * @return
	 */
	public List<Show> getShowTimesForMovieInTheater(String movieId, String theaterId) {
		try {
			List<Show> shows = showRepository.findByMovieIdAndTheaterId(movieId, theaterId);
			if (shows.isEmpty()) {
				throw new ShowNotFoundException(AppConstants.NO_SHOW_RECORD);
			}
			return shows;
		} catch (ShowNotFoundException snf) {
			snf.printStackTrace();
			throw snf;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new OperationFailedException(AppConstants.OPERATION_FAILED_CONST);
		}
	}

	/**
	 * @param movieId
	 * @return
	 */
//	public List<Theater> getTheatersPlayingMovie(String movieId) {
//		List<Show> shows = getShowByMovieId(movieId);
//		List<String> theaterIds = shows.stream().map(Show::getTheaterId).distinct().collect(Collectors.toList());
//		return theaterService.getTheatersByIds(theaterIds);
//	}

	/**
	 * @param theaterId
	 * @return
	 */
	public List<Movie> getMoviesPlayedInTheater(String theaterId) {
		try {
			List<Show> shows = showRepository.findByTheaterId(theaterId);
			if (shows.isEmpty()) {
				throw new ShowNotFoundException(AppConstants.SHOW_NOT_FOUND_THEATER + theaterId);
			}
			List<String> movieIds = shows.stream().map(Show::getMovieId).distinct().collect(Collectors.toList());
			List<Movie> movie = movieService.findAllById(movieIds);
			return movie;
		} catch (ShowNotFoundException snf) {
			snf.printStackTrace();
			throw snf;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new OperationFailedException(AppConstants.OPERATION_FAILED_CONST);
		}
	}

	public boolean isScreenOccupiedDuringTimeSlot(Show show) {
		return showRepository
				.existsByScreenIdAndTheaterIdAndShowDateAndStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
						show.getScreenId(), show.getTheaterId(), show.getShowDate(), show.getEndTime(),
						show.getStartTime());
//		return showRepository.existsByScreenIdAndTheaterId(show.getScreenId(), show.getTheaterId());
	}

}
