package com.demo.aop;

import com.demo.service.UpperCaseBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class NotSoUsefulUppercaseAspect {
    @Autowired
    UpperCaseBean upperCaseBean;

    @Around("execution(* com.demo.service.EchoService.echo(..))")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        String me = "NotSoUsefulUppercaseAspect.aroundAdvice()";
        System.out.println(me + " begin: " + Arrays.toString(pjp.getArgs()));
        Object result = pjp.proceed();
        result = upperCaseBean.toUpperCase(((String) result));
        System.out.println(me + " end: " + result);
        return result;
    }
}
