package com.cheffi.user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheffi.common.code.ErrorCode;
import com.cheffi.common.config.exception.business.BusinessException;
import com.cheffi.common.config.exception.business.EntityNotFoundException;
import com.cheffi.user.constant.UserType;
import com.cheffi.user.domain.Role;
import com.cheffi.user.domain.User;
import com.cheffi.user.dto.UserCreateRequest;
import com.cheffi.user.dto.UserInfoDto;
import com.cheffi.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

	private final UserRepository userRepository;

	public UserInfoDto getUserInfo() {
		return UserInfoDto.of(
			User.builder()
				.email("Mock@mock.com")
				.expired(false)
				.locked(false)
				.withdrawn(false)
				.name("안유진")
				.userType(UserType.KAKAO)
				.activated(true)
				.build(),
			List.of(new Role("USER"))
		);
	}

	public User signUp(UserCreateRequest request) {
		if (request.userType().equals(UserType.LOCAL))
			throw new BusinessException(ErrorCode.EMAIL_LOGIN_NOT_SUPPORTED);
		return userRepository.save(User.createUser(request));
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email)
			.orElseThrow(() -> new EntityNotFoundException(ErrorCode.USER_NOT_EXISTS));
	}
}
