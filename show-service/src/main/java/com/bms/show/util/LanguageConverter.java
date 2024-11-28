package com.bms.show.util;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

public class LanguageConverter implements DynamoDBTypeConverter<String, Language> {

	@Override
	public String convert(Language language) {
		return language.toString();
	}

	@Override
	public Language unconvert(String stringValue) {
		try {
			return Language.valueOf(stringValue);
		} catch (IllegalArgumentException e) {
			return Language.DEFAULT;
		}
	}
}
