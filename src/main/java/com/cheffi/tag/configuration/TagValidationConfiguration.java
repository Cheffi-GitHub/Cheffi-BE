package com.cheffi.tag.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cheffi.tag.service.validation.SimpleValidationPolicy;
import com.cheffi.tag.service.validation.TagValidationPolicy;

@Configuration
public class TagValidationConfiguration {

	@Bean
	static TagValidationPolicy tagValidationPolicy() {
		return new SimpleValidationPolicy();
	}

}
