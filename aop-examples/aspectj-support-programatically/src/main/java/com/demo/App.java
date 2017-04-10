package com.demo;

import com.demo.aop.NotSoUsefulUppercaseAspect;
import com.demo.service.EchoService;
import com.demo.serviceifx.IEchoService;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

public class App {
    public static void main(String[] args) throws Exception {
        String me = "App.main()";
        EchoService echoService = new EchoService();
        AspectJProxyFactory factory = new AspectJProxyFactory(echoService);
        factory.addAspect(NotSoUsefulUppercaseAspect.class);
        IEchoService proxy = factory.getProxy();
        String result = proxy.echo("One");
        System.out.println(me + " result: " + result);
    }
}
