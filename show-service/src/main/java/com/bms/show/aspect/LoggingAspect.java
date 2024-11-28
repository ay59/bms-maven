package com.bms.show.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
@Component
public class LoggingAspect {

	Logger log = LoggerFactory.getLogger(LoggingAspect.class);

	@Pointcut(value = "execution(* com.bms..*.*(..))")
	public void myPointcut() {

	}

	@Before("myPointcut()")
	public void logBefore(JoinPoint joinPoint) {
		System.out.println("Logging before method execution: " + joinPoint.getSignature().toShortString());
	}

	@After("myPointcut()")
	public void logAfter(JoinPoint joinPoint) {
		System.out.println("Logging after method execution: " + joinPoint.getSignature().toShortString());
	}
}
