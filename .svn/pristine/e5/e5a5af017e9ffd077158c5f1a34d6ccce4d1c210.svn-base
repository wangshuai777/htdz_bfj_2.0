package com.ehangtian;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class HtdzEurekaApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(HtdzEurekaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HtdzEurekaApplication.class, args);
	}

	public void run(String... strings) throws Exception {
		logger.info("HtdzEureka服务启动完成!");
	}
}
