package com.example.bms.bmsmaven.pojo;

public class Movie {

	private String cityName;
    private String theaterName;
    private String movieName;
    private String actors;
    private double movieRating;
    private String showTime;
    
	/**
	 * @param cityName
	 * @param theaterName
	 * @param movieName
	 * @param actors
	 * @param movieRating
	 * @param showTime
	 */
	public Movie(String cityName, String theaterName, String movieName, String actors, double movieRating,
			String showTime) {
		super();
		this.cityName = cityName;
		this.theaterName = theaterName;
		this.movieName = movieName;
		this.actors = actors;
		this.movieRating = movieRating;
		this.showTime = showTime;
	}

	/**
	 * @return the cityName
	 */
	public String getCityName() {
		return cityName;
	}

	/**
	 * @param cityName the cityName to set
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	/**
	 * @return the theaterName
	 */
	public String getTheaterName() {
		return theaterName;
	}

	/**
	 * @param theaterName the theaterName to set
	 */
	public void setTheaterName(String theaterName) {
		this.theaterName = theaterName;
	}

	/**
	 * @return the movieName
	 */
	public String getMovieName() {
		return movieName;
	}

	/**
	 * @param movieName the movieName to set
	 */
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	/**
	 * @return the actors
	 */
	public String getActors() {
		return actors;
	}

	/**
	 * @param actors the actors to set
	 */
	public void setActors(String actors) {
		this.actors = actors;
	}

	/**
	 * @return the movieRating
	 */
	public double getMovieRating() {
		return movieRating;
	}

	/**
	 * @param movieRating the movieRating to set
	 */
	public void setMovieRating(double movieRating) {
		this.movieRating = movieRating;
	}

	/**
	 * @return the showTime
	 */
	public String getShowTime() {
		return showTime;
	}

	/**
	 * @param showTime the showTime to set
	 */
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	
	
    
    
}
