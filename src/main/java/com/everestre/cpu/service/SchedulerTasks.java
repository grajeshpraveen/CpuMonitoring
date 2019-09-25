package com.everestre.cpu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SchedulerTasks { 
	@Autowired
	RemoteConnection remoteConnection;
	
	@Scheduled(fixedDelay = 20000)
	public void cronJobSch() { 
		
		remoteConnection.remoteHostUsage();

	}

	
}
