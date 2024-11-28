package com.theatres;


import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

@Configuration(enforceUniqueMethods=false)
@EnableTransactionManagement
@EnableDynamoDBRepositories(basePackages = "com.theatres.repository")
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@PropertySource("classpath:application.properties")
public class DynamoDbConfiguration {

    @Autowired
    private AWSCredentials awsCredentials;

    @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard().build();
    }

    @Bean
    public AmazonDynamoDB amazonDynamoDB(AWSCredentials awsCredentials, @Value("${aws.dynamoDBUrl}") String dynamoDBURL) {
        AmazonDynamoDBClientBuilder builder = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(dynamoDBURL, "us-east-1")) 
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials));
        AmazonDynamoDB client = builder.build();
        return client;
    }

    @Bean
    public AWSCredentials awsCredentials(@Value("${aws.accesskey}") String accesskey,
                                          @Value("${aws.secretkey}") String secretkey) {
        return new BasicAWSCredentials(accesskey, secretkey);
    }
}

