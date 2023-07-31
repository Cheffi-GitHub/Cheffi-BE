package com.cheffi.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheffi.user.domain.Role;
import com.cheffi.user.domain.User;
import com.cheffi.user.dto.UserInfoDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

	public UserInfoDto getUserInfo() {
		return UserInfoDto.of(
			User.builder()
				.email("Mock@mock.com")
				.expired(false)
				.locked(false)
				.withdrawn(false)
				.name("안유진")
				.provider("KAKAO")
				.activated(true)
				.build(),
			List.of(new Role("USER"))
		);
	}
}
