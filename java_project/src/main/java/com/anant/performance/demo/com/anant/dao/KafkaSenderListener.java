package com.anant.performance.demo.com.anant.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class KafkaSenderListener {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	
	public void send(String topic, String payload) {
		System.out.println("Thread 2-->"+ Thread.currentThread().getId());
		System.out.println("Thread 2-->"+ Thread.currentThread().getId());
		System.out.println("Thread 2-->"+ Thread.currentThread().getId());
		System.out.println("Thread 2-->"+ Thread.currentThread().getId());
		System.out.println("Thread 2-->"+ Thread.currentThread().getId());
		System.out.println("Thread 2-->"+ Thread.currentThread().getId());
		System.out.println("Thread 2-->"+ Thread.currentThread().getId());
		System.out.println("Thread 2-->"+ Thread.currentThread().getId());
		kafkaTemplate.send(topic, payload);
	
		
		
	}
	
	
	@KafkaListener(topics = "test2")
	public void listen(String message) {
	    System.out.println("Received Messasge in group helloworld: " + message);
	}
}
