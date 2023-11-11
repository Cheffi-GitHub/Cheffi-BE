package com.cheffi.common.config.data;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "env")
public class RegionProperties {

	private List<Region> regions;

	@Getter
	@Setter
	public static class Region {
		private String province;
		private List<String> cities;
	}
}
