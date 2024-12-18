package com.example.bookmyshow.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;
import org.springframework.data.annotation.Id;
@Data
@DynamoDBTable(tableName = "screenSeat")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScreenSeat {
    @DynamoDBHashKey(attributeName = "screenSeatId")
    @DynamoDBAutoGeneratedKey
    private String screenSeatId;
    @DynamoDBAttribute
    private String screenId;
    @DynamoDBAttribute
    private String seatNum;
    @DynamoDBAttribute
    private String seatType;

}
