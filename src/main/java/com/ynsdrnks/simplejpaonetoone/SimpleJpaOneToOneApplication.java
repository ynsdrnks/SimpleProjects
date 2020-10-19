package com.ynsdrnks.simplejpaonetoone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class SimpleJpaOneToOneApplication {

	public static void main(String[] args)
	{
				SpringApplication.run(SimpleJpaOneToOneApplication.class, args);
	}

}
