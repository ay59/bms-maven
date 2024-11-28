package com.bms.show.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.bms.show.repository")
public class DynamoDBConfig {

	@Bean
	public AmazonDynamoDB amazonDynamoDB(AWSCredentials AWSCredentials,
			@Value("${aws.dynamoDBUrl}") String dynamoDBURl) {

		AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(dynamoDBURl, "us-east-1"))
				.withCredentials(new AWSStaticCredentialsProvider(AWSCredentials));
		AmazonDynamoDB build = builder.build();
		return build;
	}

	@Bean
	public AWSCredentials awsCredentials(@Value("${aws.accessKey}") String accesskey,
			@Value("${aws.secretKey}") String secretkey) {
		return new BasicAWSCredentials(accesskey, secretkey);
	}
}
