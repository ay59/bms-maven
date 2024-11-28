package com.bms.show.util;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import java.util.List;
import java.util.stream.Collectors;

public class GenreListConverter implements DynamoDBTypeConverter<List<String>, List<Genre>> {

	@Override
	public List<String> convert(List<Genre> genres) {
		return genres.stream().map(Genre::toString).collect(Collectors.toList());
	}

	@Override
	public List<Genre> unconvert(List<String> stringValues) {
		return stringValues.stream().map(Genre::valueOf).collect(Collectors.toList());
	}
}
