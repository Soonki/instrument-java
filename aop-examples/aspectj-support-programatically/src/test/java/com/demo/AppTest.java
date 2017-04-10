package com.demo;

import com.demo.aop.NotSoUsefulUppercaseAspect;
import com.demo.service.EchoService;
import com.demo.serviceifx.IEchoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class AppTest {

    @Test
    public void testEchoService() {
        String me = "AppTest.testEchoService()";
        EchoService echoService = new EchoService();
        AspectJProxyFactory factory = new AspectJProxyFactory(echoService);
        factory.addAspect(NotSoUsefulUppercaseAspect.class);
        IEchoService proxy = factory.getProxy();
        String result = proxy.echo("Two");
        System.out.println(me + " result: " + result);
    }
}
