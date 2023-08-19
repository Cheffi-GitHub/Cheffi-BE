package com.cheffi.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cheffi.user.constant.RoleType;
import com.cheffi.user.domain.Role;
import com.cheffi.user.repository.RoleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class RoleService {

	private final RoleRepository roleRepository;

	@Transactional
	public Role getUserRole() {
		return roleRepository.findByAuthority(RoleType.USER)
			.orElseGet(() ->
				roleRepository.save(new Role(RoleType.USER))
			);
	}

	@Transactional
	public Role getNoProfileRole() {
		return roleRepository.findByAuthority(RoleType.NO_PROFILE)
			.orElseGet(() ->
				roleRepository.save(new Role(RoleType.NO_PROFILE))
			);
	}

}
