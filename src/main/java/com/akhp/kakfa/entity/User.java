package com.akhp.kakfa.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "user")
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2918153236056045161L;
	
	@DynamoDBHashKey(attributeName="id")
	@DynamoDBAutoGeneratedKey 
	@Id
	private String id;
	@DynamoDBAttribute
	private String name;
	@DynamoDBAttribute
	private String email;
//	@DynamoDBAttribute
//	private Address address;
}
