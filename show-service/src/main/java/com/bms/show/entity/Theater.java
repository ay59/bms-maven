package com.bms.show.entity;

public class Theater {

	private String theaterId;
	private String name;
	private String city;

	/**
	 * 
	 */
	public Theater() {
		super();
	}

	/**
	 * @param theaterId
	 * @param name
	 * @param city
	 */
	public Theater(String theaterId, String name, String city) {
		super();
		this.theaterId = theaterId;
		this.name = name;
		this.city = city;
	}

	/**
	 * @return the theaterId
	 */

	public String getTheaterId() {
		return theaterId;
	}

	/**
	 * @param theaterId the theaterId to set
	 */
	public void setTheaterId(String theaterId) {
		this.theaterId = theaterId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

}
