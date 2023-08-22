package com.cheffi.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.cheffi.avatar.domain.Avatar;
import com.cheffi.common.service.SecurityContextService;
import com.cheffi.oauth.model.UserPrincipal;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @Order(1) 을 통해서 @Transactional 이후에 실행 되도록함
 */
@Slf4j
@Aspect
@Order(1)
@RequiredArgsConstructor
@Component
public class SessionUpdateAspect {

	private final SecurityContextService securityContextService;

	@Pointcut("@annotation(com.cheffi.common.aspect.annotation.UpdatePrincipal)")
	private void updatePrincipal(){}

	@Pointcut("execution(com.cheffi.avatar.domain.Avatar update*(..))")
	private void updateAvatar(){}

	@Around("updateAvatar() && updatePrincipal()")
	public Object updateAvatarWithToken(ProceedingJoinPoint joinPoint) throws Throwable {
		Avatar avatar = (Avatar) joinPoint.proceed();
		log.info("Avtar 업데이트 완료");
		UserPrincipal principal = securityContextService.getUserPrincipal();
		securityContextService.updatePrincipal(principal.update(avatar));
		log.info("Avtar 변경사항 Principal 적용 완료");
		return avatar;
	}


}
