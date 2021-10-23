package com.vsoft.shopping.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
	@Pointcut("@annotation(Logging)")
	public void executeLogger() {}
	
	@Before("executeLogger()")
	public void executeBeforeLogger(JoinPoint jp) {
		Object[] args = jp.getArgs();
		for (Object object : args) {
			System.out.println(object);
		}
		System.out.println("I'm the before logger...");
	}
	
	@AfterReturning(value = "executeLogger()",returning = "retVal")
	public void executeAfterLogger(JoinPoint jp, Object retVal) {
		System.out.println("I'm the after logger...");
		System.out.println("My return value is.." +  retVal);
	}
	
	@Around("executeLogger()")
	public Object executeAroundLogger(ProceedingJoinPoint jp) throws Throwable {
		System.out.println("I'm the before-around logger...");
		Object retVal = jp.proceed(jp.getArgs());
		System.out.println("I'm the after-around logger...");
		System.out.println(retVal);
		return retVal ;
	}
	
	
}
