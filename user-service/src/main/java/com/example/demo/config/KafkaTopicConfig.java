package com.example.demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {
	
	@Bean
	public NewTopic apacheKafkaTopic() {
		return TopicBuilder.name("apacheKafka")
				.build();
	}
	
	@Bean
	public NewTopic apacheKafkaTopicJson() {
		return TopicBuilder.name("apacheKafka_json")
				.build();
	}
}
