package com.cheffi.common.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ImagePathStrategyTest {

	private final ImagePathStrategy imagePathStrategy = new ImagePathStrategy();

	@Test
	@DisplayName("getFileName 메서드는 공백이 없는 문자열을 반환한다.")
	void getFileName() {
		String original = imagePathStrategy.getFileName();
		System.out.println(original);

		String noBlank = original.replace(" ", "");

		assertThat(noBlank).hasSize(original.length());
	}
}
