package com.nitesh.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {
	
	//creating custom pointcut expression for reuse
	@Pointcut("execution(* com.nitesh.aopdemo.dao.*.*(..))")
	private void forDAOPackage() {}
	
	@Before("forDAOPackage()")//point-cut expression
	public void beforeAddAccountAdvice() {
		System.out.println("\n====>>>> Executing @Before advice on addAccount()");
	}
}
