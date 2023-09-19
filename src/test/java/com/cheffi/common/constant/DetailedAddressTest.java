package com.cheffi.common.constant;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DetailedAddressTest {

	private static final String PROVINCE = "서울시";
	private static final String CITY = "마포구";
	private static final String DETAIL = "숭문길 24";
	private static final String FULL_ADDRESS = PROVINCE + " " + CITY + " " + DETAIL;

	@Test
	@DisplayName("DetailedAddress 객체로 getFull 메서드를 호출하면 주소를 하나로 합쳐 String 타입으로 반환한다.")
	void givenDetailedAddressWhenGetFullThenReturnFullStringOfAddress() {
		//when
		DetailedAddress address = DetailedAddress.of(PROVINCE, CITY, DETAIL);

		//then
		assertThat(address.getFull()).isEqualTo(FULL_ADDRESS);
	}

}
