package com.demo;

import com.demo.aop.NotSoUsefulUppercaseMethodInterceptor;
import com.demo.service.EchoService;
import com.demo.serviceinterfaces.IEchoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class AppTest {

    @Test
    public void testEchoService() {
        String me = "AppTest.testEchoService()";
        ProxyFactory factory = new ProxyFactory(new EchoService());
        factory.addInterface(IEchoService.class);
        factory.addAdvice(new NotSoUsefulUppercaseMethodInterceptor());
        IEchoService echoService = (IEchoService) factory.getProxy();
        String result = echoService.echo("Two");
        System.out.println(me + " result: " + result);
    }
}
