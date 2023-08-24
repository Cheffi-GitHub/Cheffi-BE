package com.cheffi.user.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheffi.avatar.domain.Avatar;
import com.cheffi.avatar.repository.AvatarRepository;
import com.cheffi.common.code.ErrorCode;
import com.cheffi.common.config.exception.business.BusinessException;
import com.cheffi.user.constant.RoleType;
import com.cheffi.user.constant.UserType;
import com.cheffi.user.domain.Role;
import com.cheffi.user.domain.User;
import com.cheffi.user.dto.UserCreateRequest;
import com.cheffi.user.dto.UserInfoDto;
import com.cheffi.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

	private final UserRepository userRepository;
	private final AvatarRepository avatarRepository;

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
			List.of(new Role(RoleType.USER))
		);
	}

	@Transactional
	public User signUp(UserCreateRequest request) {
		if (request.userType().equals(UserType.LOCAL))
			throw new BusinessException(ErrorCode.EMAIL_LOGIN_NOT_SUPPORTED);
		User user = userRepository.save(User.createUser(request));
		avatarRepository.save(new Avatar(getRandomString() + "쉐피", user));
		return user;
	}

	private String getRandomString() {
		return RandomStringUtils.randomNumeric(6);
	}

	public Optional<User> getByEmailWithRoles(String email) {
		return userRepository.findByEmailWithRoles(email);
	}

}
