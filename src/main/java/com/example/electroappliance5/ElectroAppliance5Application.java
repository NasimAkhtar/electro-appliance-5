package com.example.electroappliance5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableScheduling
public class ElectroAppliance5Application {

	public static void main(String[] args) {
		SpringApplication.run(ElectroAppliance5Application.class, args);
	}

	@Autowired
	private KafkaTemplate kafkaTemplate;

	private static final String TOPIC = "heartbeat_topic";
	private static final String APPLIANCE_ID = "YS2R4X20005387949";


	@Scheduled(fixedRate = 1, timeUnit = TimeUnit.MINUTES)
	private void sentHeartbeat() {
		kafkaTemplate.send(TOPIC, APPLIANCE_ID+","+ LocalDateTime.now().toString());
	}

}
