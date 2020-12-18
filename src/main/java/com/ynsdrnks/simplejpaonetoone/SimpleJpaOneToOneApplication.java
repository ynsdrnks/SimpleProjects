package com.ynsdrnks.simplejpaonetoone;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ynsdrnks.simplejpaonetoone.converter.Converter;
import com.ynsdrnks.simplejpaonetoone.dto.CountryDto;
import com.ynsdrnks.simplejpaonetoone.entity.Country;
import com.ynsdrnks.simplejpaonetoone.service.impl.CountryServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@EnableJpaRepositories
@SpringBootApplication
public class SimpleJpaOneToOneApplication {

	public static void main(String[] args)
	{
				SpringApplication.run(SimpleJpaOneToOneApplication.class, args);
	}




	}


