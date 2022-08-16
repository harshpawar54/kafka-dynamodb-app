package com.akhp.kakfa.app;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import com.akhp.kakfa.entity.User;
import com.akhp.kakfa.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaListener {

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private UserRepository userRepository;
	
	
	@org.springframework.kafka.annotation.KafkaListener(
			topics = "akhpTopic",
			groupId = "groupId")
	public void listener(String data) {
		log.info("Listener Received: {}", data);
	}
	
	@org.springframework.kafka.annotation.KafkaListener(
			topics = "userAddTopic",
			groupId = "groupId")
	public void listenerUserAdd(String data) throws JsonMappingException, JsonProcessingException {
		log.info("Listener Received user To add : {}", data);
		
		User user = objectMapper.readValue(data, User.class);
//		List<User> userOptional = userRepository.findByEmail(user.getEmail());
//		if(!userOptional.isEmpty()) {
			User save = userRepository.save(user);
			log.info("Added new user with id: {}",save.getId());
//		}
	}
}
