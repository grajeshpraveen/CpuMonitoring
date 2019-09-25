package com.everestre.cpu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CpuMonitorStarter extends SpringBootServletInitializer //implements CommandLineRunner
{
	private static final Logger logger = LoggerFactory.getLogger(CpuMonitorStarter.class);

	
	public static void main(String[] args) {
		logger.info("Application Started..."); 
		SpringApplication.run(CpuMonitorStarter.class, args);
	}

}