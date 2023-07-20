package com.cheffi.common.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
	@Bean
	public OpenAPI springOpenAPI() {
		return new OpenAPI()
			.info(
				new Info().title("Cheffi API 문서")
					.description("API 스펙에 대해서 설명해주는 문서입니다.")
					.version("0.0.1"))
			.externalDocs(new ExternalDocumentation()
				.description("외부")
				.url("URL"))
			.components(component());
	}

	private static Components component() {
		return new Components().addSecuritySchemes("session-token",
			new SecurityScheme()
				.type(SecurityScheme.Type.APIKEY)
				.in(SecurityScheme.In.HEADER)
				.name("Authorization"));
	}
}
