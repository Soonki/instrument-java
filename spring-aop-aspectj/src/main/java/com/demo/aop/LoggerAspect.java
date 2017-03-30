package com.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class LoggerAspect {
    @Around("execution(* com.demo.service.EchoService.echo(..))")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("AOP interceptor: " + Arrays.toString(pjp.getArgs()));
        Object result = pjp.proceed();
        result = ((String) result).toUpperCase();
        System.out.println("AOP interceptor: converting the result into uppercase: " + result);
        return result;
    }
}
