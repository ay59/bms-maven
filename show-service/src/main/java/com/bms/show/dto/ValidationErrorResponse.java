package com.bms.show.dto;

import java.util.List;

public class ValidationErrorResponse extends BaseErrorResponse {

	private List<String> details;

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

}
