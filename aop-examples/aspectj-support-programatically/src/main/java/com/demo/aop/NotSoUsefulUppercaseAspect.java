package com.demo.aop;

import java.util.Arrays;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class NotSoUsefulUppercaseAspect {
    @Around("execution(* com.demo.serviceifx.IEchoService.echo(..))")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        String me = "NotSoUsefulUppercaseAspect.logAround()";
        System.out.println(me + " AOP before: " + Arrays.toString(pjp.getArgs()));
        Object result = pjp.proceed();
        result = ((String) result).toUpperCase();
        System.out.println(me + " AOP after: " + result);
        return result;
    }
}
