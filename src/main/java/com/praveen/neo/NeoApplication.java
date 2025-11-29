package com.praveen.neo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;

@SpringBootApplication
public class NeoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeoApplication.class, args);
	}

}
