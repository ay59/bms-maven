package com.bms.show.util;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import java.time.LocalTime;

public class LocalTimeConverter implements DynamoDBTypeConverter<String, LocalTime> {

    @Override
    public String convert(LocalTime localTime) {
        return localTime.toString();
    }

    @Override
    public LocalTime unconvert(String stringValue) {
        return LocalTime.parse(stringValue);
    }
}
