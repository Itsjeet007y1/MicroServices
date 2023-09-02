package com.example.demo.config.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;

@Service
public class JsonKafkaProducer {

	private static final Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);
	
	private KafkaTemplate<String, User> kafkTemplate;

	public JsonKafkaProducer(KafkaTemplate<String, User> kafkTemplate) {
		this.kafkTemplate = kafkTemplate;
	}
	
	public void sendJsonMessage(User data) {
		
		LOGGER.info(String.format("Message sent: %s", data));
		
		Message<User> message = MessageBuilder
								.withPayload(data)
								.setHeader(KafkaHeaders.TOPIC, "apacheKafka_json")
								.build();
		kafkTemplate.send(message);		
	}
}
