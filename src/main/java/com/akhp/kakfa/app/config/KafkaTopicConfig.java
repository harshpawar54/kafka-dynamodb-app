package com.akhp.kakfa.app.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
	
	@Bean
	public NewTopic akhpTopic() {
		return TopicBuilder.name("akhpTopic").build();
	}
	
	@Bean
	public NewTopic addUserTopic() {
		return TopicBuilder.name("userAddTopic").build();
	}
}
