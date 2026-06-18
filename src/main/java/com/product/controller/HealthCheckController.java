package com.product.controller;

import java.time.LocalDateTime;
import java.util.Hashtable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/products")
public class HealthCheckController {

	@Value("${spring.application.name}")
	private String appName;

	@GetMapping(value = "/health")
	public ResponseEntity<Map<String, String>> healthCheck() {
		log.info("Health Check Request for: " + appName);
		Map<String, String> healthCheckInfo = new Hashtable<>();
		healthCheckInfo.put("Status", "UP");
		healthCheckInfo.put("Name", appName.toUpperCase());
		healthCheckInfo.put("Timestamp", LocalDateTime.now().toString());
		return new ResponseEntity<>(healthCheckInfo, HttpStatus.OK);
	}
}
