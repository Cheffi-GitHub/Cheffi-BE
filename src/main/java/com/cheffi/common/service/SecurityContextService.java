package com.cheffi.common.service;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;

import com.cheffi.oauth.model.AuthenticationToken;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class SecurityContextService {

	private final SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();

	private final SecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();

	public void saveToSecurityContext(HttpServletRequest request, HttpServletResponse response,
		AuthenticationToken authenticationToken) {
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authenticationToken);
		securityContextHolderStrategy.setContext(context);
		securityContextRepository.saveContext(context, request, response);
	}
}
