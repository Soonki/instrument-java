package com.demo.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class NotSoUsefulUppercaseMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation mi) throws Throwable {
        Object result = mi.proceed();
        String me = "NotSoUsefulUppercaseMethodInterceptor.invoke()";
        System.out.println(me + " AOP before: " + result);
        result = ((String) result).toUpperCase();
        System.out.println(me + " AOP after: " + result);
        return result;
    }
}
