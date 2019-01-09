package com.springboot.Schedular;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobSchedular {

	@Scheduled(cron = "1 * * * * *")
	public void reportSchedular() {
		System.out.println("every minute Cronjob executed");
	}
}
