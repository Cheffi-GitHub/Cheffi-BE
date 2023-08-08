package com.cheffi.common.config.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
public class MapperConfig extends WebMvcConfigurationSupport {


	private final ObjectMapper objectMapper;

	public MapperConfig(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper();
	}

	public ObjectMapper objectMapper() {
		return new ObjectMapper()
			.registerModule(new SimpleModule())
			.registerModule(new JavaTimeModule())
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@Override
	protected void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new FoodDtoConverter(objectMapper));
	}

}
