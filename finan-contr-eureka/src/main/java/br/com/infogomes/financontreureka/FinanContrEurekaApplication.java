package br.com.infogomes.financontreureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class FinanContrEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinanContrEurekaApplication.class, args);
	}

}
