package com.example.bookmyshow.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "showSeat")
public class ShowSeat {
    @DynamoDBHashKey(attributeName = "seatId")
    @DynamoDBAutoGeneratedKey
    private String seatId;
    @DynamoDBAttribute
    private String showSeatId;

    @DynamoDBAttribute
    private String screenSeatId;

    @DynamoDBAttribute
    private String status;
    @DynamoDBAttribute
    private int price;
    @DynamoDBAttribute
    private String showId;

}
