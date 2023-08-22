package com.cheffi.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
// @Component
public class TimeTraceAspect {

	@Around("execution(* com.cheffi.common.service.FileUploadService.uploadImageToS3(..))")
	public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		log.info("[{}] start", joinPoint.getSignature());

		Object result = joinPoint.proceed();

		stopWatch.stop();
		log.info("[{}] end, it took {}", joinPoint.getSignature(), stopWatch.getTotalTimeMillis());
		return result;
	}
}
