package com.demo.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.util.Arrays;

public class LoggerInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        System.out.println("AOP interceptor before: " + Arrays.toString(mi.getArguments()));
        Object result = mi.proceed();
        result = ((String) result).toUpperCase();
        System.out.println("AOP interceptor: converting the result into uppercase: " + result);
        return result;
    }
}
