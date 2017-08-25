package com.xdzl.cloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@EnableZuulServer
@SpringBootApplication
public class ZuulServer {
	private static final Logger logger = LoggerFactory.getLogger(ZuulServer.class);
	private static ConfigurableApplicationContext ctx;

	public static void main(String[] args) throws Exception {
		ctx = SpringApplication.run(ZuulServer.class, args);

		logger.info("spring.profiles.active:");
		for (String str : ctx.getEnvironment().getActiveProfiles()) {
			logger.info(str);
		}
		logger.info("Boot Server started.");

	}

	public void destroy() throws Exception {
		if (null != ctx && ctx.isRunning()) {
			ctx.close();
		}
	}

	@Bean
	public MultipartConfigElement multipartConfigElement(@Value("${http.upload.maxFileSize}") String maxFileSize,
														 @Value("${http.upload.maxRequestSize}") String maxRequestSize) {
		MultipartConfigFactory factory = new MultipartConfigFactory();
		factory.setMaxFileSize(maxFileSize);
		factory.setMaxRequestSize(maxRequestSize);
		return factory.createMultipartConfig();
	}
}
