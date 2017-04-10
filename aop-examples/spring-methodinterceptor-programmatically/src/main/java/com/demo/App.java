package com.demo;

import com.demo.aop.NotSoUsefulUppercaseMethodInterceptor;
import com.demo.service.EchoService;
import com.demo.serviceinterfaces.IEchoService;
import org.springframework.aop.framework.ProxyFactory;

public class App {
    public static void main(String[] args) throws Exception {
        String me = "App.main()";
        ProxyFactory factory = new ProxyFactory(new EchoService());
        factory.addInterface(IEchoService.class);
        factory.addAdvice(new NotSoUsefulUppercaseMethodInterceptor());
        IEchoService echoService = (IEchoService) factory.getProxy();
        String result = echoService.echo("One");
        System.out.println(me + " result: " + result);
    }
}
