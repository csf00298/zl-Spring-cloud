package com.xdzl.cloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.ConfigurableApplicationContext;

@EnableEurekaServer
@SpringBootApplication
public class EurekaServer {

	private static final Logger logger = LoggerFactory.getLogger(EurekaServer.class);
	private static ConfigurableApplicationContext ctx;

	public static void main(String[] args) throws Exception {
		ctx = SpringApplication.run(EurekaServer.class, args);

		logger.info("spring.profiles.active:");
		for (String str : ctx.getEnvironment().getActiveProfiles()) {
			logger.info(str);
		}
		logger.info("Boot Server started.");

	}
}
