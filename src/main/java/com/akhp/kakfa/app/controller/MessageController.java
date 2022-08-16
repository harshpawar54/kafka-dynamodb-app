package com.akhp.kakfa.app.controller;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.akhp.kakfa.app.record.MessageRequest;


@RestController

public class MessageController {

	private KafkaTemplate<String, String> kafkaTemplate;
	
	public MessageController(KafkaTemplate<String, String> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}

	@PostMapping(value = "api/v1/messages")
	public void publish(@RequestBody MessageRequest messageRequest) {
		kafkaTemplate.send("akhpTopic", messageRequest.message());
	}
	
	@PostMapping(value = "api/v1/user")
	public void addUser(@RequestBody MessageRequest messageRequest) {
		kafkaTemplate.send("userAddTopic", messageRequest.message());
	}
}
