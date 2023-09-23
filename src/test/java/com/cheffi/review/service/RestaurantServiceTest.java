package com.cheffi.review.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;
import java.util.function.Function;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cheffi.common.config.exception.business.EntityNotFoundException;
import com.cheffi.review.domain.Restaurant;
import com.cheffi.review.domain.RestaurantData;
import com.cheffi.review.dto.RestaurantInfoDto;
import com.cheffi.review.repository.RestaurantDataRepository;
import com.cheffi.review.repository.RestaurantRepository;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {

	@Mock
	private RestaurantRepository restaurantRepository;
	@Mock
	private RestaurantDataRepository restaurantDataRepository;

	@InjectMocks
	private RestaurantService restaurantService;

	@Nested
	@DisplayName("searchRestaurantByName 메서드")
	class SearchRestaurantByName {

		private static final String KEYWORD_WITHOUT_SPACES = "중앙해장";
		private static final String KEYWORD_WITH_SPACES = " 중앙 해장    ";

		@Mock
		private Pageable pageable;
		@Mock
		private Page<Restaurant> resultWithNoSpaces;
		@Mock
		private Page<RestaurantInfoDto> dtoWithNoSpaces;

		@Test
		@DisplayName("공백이 있는 검색어가 주어져도 공백을 제거하고 쿼리한다.")
		void givenNameWithSpacesWhenSearchByNameThenReturnResultWithoutSpaces() {
			//given
			when(restaurantRepository.findByNameContaining(KEYWORD_WITHOUT_SPACES, pageable))
				.thenReturn(resultWithNoSpaces);

			when(resultWithNoSpaces.map(any(Function.class))).thenReturn(dtoWithNoSpaces);

			//when
			Page<RestaurantInfoDto> page = restaurantService.searchRestaurantByName(KEYWORD_WITH_SPACES, pageable);

			//then
			assertThat(page).isEqualTo(dtoWithNoSpaces);

		}

	}

	@Nested
	@DisplayName("getOrRegisterById 메서드")
	class GetOrRegisterById {
		@Mock
		private Restaurant registeredRestaurant;
		@Mock
		private Restaurant newRestaurant;
		@Mock
		private RestaurantData restaurantData;

		private static final Long REGISTERED_RESTAURANT_ID = 10L;
		private static final Long UNREGISTERED_RESTAURANT_ID = 300020L;
		private static final Long NOT_EXIST_ID = 54312220L;

		@Nested
		@DisplayName("registered flag 가 true면")
		class RegisteredRestaurant {
			@Test
			@DisplayName("RestaurantRepository 에 데이터가 존재할 때 Restaurant 엔티티를 반환한다.")
			void givenValidId() {
				//given
				when(restaurantRepository.findById(REGISTERED_RESTAURANT_ID))
					.thenReturn(Optional.of(registeredRestaurant));

				//when
				Restaurant result = restaurantService.getOrRegisterById(REGISTERED_RESTAURANT_ID, true);

				//then
				Assertions.assertThat(result).isEqualTo(registeredRestaurant);

			}

			@Test
			@DisplayName("RestaurantRepository 에 식당정보가 없다면 실패한다.")
			void givenInvalidId() {
				//given
				when(restaurantRepository.findById(NOT_EXIST_ID))
					.thenReturn(Optional.empty());

				//then
				assertThatExceptionOfType(EntityNotFoundException.class)
					.isThrownBy(
						//when
						() -> restaurantService.getOrRegisterById(NOT_EXIST_ID, true)
					);

			}

		}

		@Nested
		@DisplayName("registered flag 가 false면")
		class UnregisteredRestaurant {

			@Test
			@DisplayName("RestaurantDataRepository 에 데이터가 존재할 때 데이터를 식당 엔티티로 바꾼 뒤 save하고 Restaurant 엔티티를 반환한다.")
			void givenValidId() {
				//given
				when(restaurantDataRepository.findById(UNREGISTERED_RESTAURANT_ID))
					.thenReturn(Optional.of(restaurantData));
				when(restaurantData.toRestaurant()).thenReturn(newRestaurant);
				when(restaurantRepository.save(newRestaurant)).thenReturn(newRestaurant);

				//when
				Restaurant result = restaurantService.getOrRegisterById(UNREGISTERED_RESTAURANT_ID, false);

				//then
				Assertions.assertThat(result).isEqualTo(newRestaurant);

			}

			@Test
			@DisplayName("RestaurantDataRepository 에서 식당정보가 없다면 실패한다.")
			void givenInvalidID() {
				//given
				when(restaurantDataRepository.findById(NOT_EXIST_ID))
					.thenReturn(Optional.empty());

				//then
				assertThatExceptionOfType(EntityNotFoundException.class)
					.isThrownBy(
						//when
						() -> restaurantService.getOrRegisterById(NOT_EXIST_ID, false)
					);
			}
		}

	}

}
