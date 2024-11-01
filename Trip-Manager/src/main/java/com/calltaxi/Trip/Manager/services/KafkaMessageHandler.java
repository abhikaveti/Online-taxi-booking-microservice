package com.calltaxi.Trip.Manager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.calltaxi.Trip.Manager.DTO.Ride;

@Service
public class KafkaMessageHandler {

	@Autowired
	private KafkaTemplate<String, Ride> kafkaTemplate;
	
	@Value("${kafka.topic.name}")
	private String topicName;
	
	
	public void sendMessage(String action, Ride data) {
		kafkaTemplate.send(topicName, action, data);
	}
}
