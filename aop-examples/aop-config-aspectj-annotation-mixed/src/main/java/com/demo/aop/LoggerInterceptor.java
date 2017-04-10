package com.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class LoggerInterceptor {
        public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
                String me = "LoggerInterceptor.aroundAdvice()";
                System.out.println(me + " AOP interceptor begin: " + Arrays.toString(pjp.getArgs()));
                Object result = pjp.proceed();
                result = ((String) result).toUpperCase();
                System.out.println(me + " AOP interceptor end: " + result);
                return result;
        }
}
