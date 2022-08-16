package com.akhp.kakfa.app.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.akhp.kakfa.repository.UserRepository;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;

@Configuration
@PropertySource(value = { "classpath:application.properties" })
@EnableDynamoDBRepositories(mappingContextRef = "dynamoDBMappingContext", basePackageClasses = UserRepository.class)
public class DynamoDBConfig {
	
	@Value("${amazon.aws.dynamo.endpoint}")
	private String dynamoEndpoint = "http://localhost:8000";

	@Value("${amazon.aws.region}")
	private String awsRegion = "ap-south-1";

	@Value("${amazon.aws.accesskey}")
	private String amazonAWSAccessKey;

	@Value("${amazon.aws.secretkey}")
	private String amazonAWSSecretKey;

	public AWSCredentialsProvider amazonAWSCredentialsProvider() {
		return new AWSStaticCredentialsProvider(amazonAWSCredentials());
	}

	@Bean
	public AWSCredentials amazonAWSCredentials() {
		return new BasicAWSCredentials(amazonAWSAccessKey, amazonAWSSecretKey);
	}

	@Bean
	public DynamoDBMapperConfig dynamoDBMapperConfig() {
		return DynamoDBMapperConfig.DEFAULT;
	}

	@Bean
	public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB, DynamoDBMapperConfig config) {
		return new DynamoDBMapper(amazonDynamoDB, config);
	}

	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		return AmazonDynamoDBClientBuilder.standard()
				.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(dynamoEndpoint, awsRegion))
				.withCredentials(amazonAWSCredentialsProvider())
				.build();
	}
}

//public class DynamoDBConfig {
//
////	@Bean
////	public DynamoDBMapper mapper() {
////		return new DynamoDBMapper(amazonDynamoDBConfig());
////	}
////
////	private AmazonDynamoDB amazonDynamoDBConfig() {
////		return AmazonDynamoDBClientBuilder.standard().withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "ap-south-1"))
////				.withCredentials(awsCredentialProvider())
////				.build();
////	}
//	
//	private AWSCredentialsProvider awsCredentialProvider() {
//		return new AWSStaticCredentialsProvider(new BasicAWSCredentials("dummy-key", "dummy-secret"));
//	}
//
//	@Bean
//    public AmazonDynamoDB amazonDynamoDB() {
//        AWSCredentialsProvider credentials = 
//        		awsCredentialProvider();
//        AmazonDynamoDB amazonDynamoDB 
//          = AmazonDynamoDBClientBuilder
//               .standard()
//               .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration("http://localhost:8000", "ap-south-1"))
//               .withCredentials(credentials)
//               .build();
//        
//        return amazonDynamoDB;
//    }
//}
