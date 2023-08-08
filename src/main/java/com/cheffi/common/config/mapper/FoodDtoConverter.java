package com.cheffi.common.config.mapper;

import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.cheffi.review.dto.FoodDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

@Component
public class FoodDtoConverter implements Converter<String, List<FoodDto>> {

	private ObjectMapper objectMapper;

	public FoodDtoConverter(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@SneakyThrows
	@Override
	public List<FoodDto> convert(String source) {
		return objectMapper.readValue(source, new TypeReference<>() {});
	}
}
