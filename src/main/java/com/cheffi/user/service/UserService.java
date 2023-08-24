package com.cheffi.user.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheffi.avatar.domain.Avatar;
import com.cheffi.avatar.repository.AvatarRepository;
import com.cheffi.common.aspect.annotation.UpdatePrincipal;
import com.cheffi.common.code.ErrorCode;
import com.cheffi.common.config.exception.business.BusinessException;
import com.cheffi.user.constant.UserType;
import com.cheffi.user.domain.Role;
import com.cheffi.user.domain.User;
import com.cheffi.user.domain.UserRole;
import com.cheffi.user.dto.UserCreateRequest;
import com.cheffi.user.dto.adapter.UserInfo;
import com.cheffi.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class UserService {

	private final UserRepository userRepository;
	private final AvatarRepository avatarRepository;

	@UpdatePrincipal
	public UserInfo getUserInfo(Long userId) {
		User user = getByIdWithRoles(userId);
		List<Role> roles = user.getUserRoles().stream().map(UserRole::getRole).toList();
		return UserInfo.of(user, roles);
	}

	public User getByIdWithRoles(Long userId) {
		return userRepository.findByIdWithRoles(userId)
			.orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_EXISTS));
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
