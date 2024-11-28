package com.bms.show.util;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import java.util.List;
import java.util.stream.Collectors;

public class LanguageListConverter implements DynamoDBTypeConverter<List<String>, List<Language>> {

	@Override
	public List<String> convert(List<Language> languages) {
		return languages.stream().map(Language::toString).collect(Collectors.toList());
	}

	@Override
	public List<Language> unconvert(List<String> stringValues) {
		return stringValues.stream().map(Language::valueOf).collect(Collectors.toList());
	}
}
