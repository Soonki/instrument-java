package com.demo;

import com.demo.service.EchoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class App {
    public static void main(String[] args) {
        String me = "App.main()";
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        EchoService echoService = (EchoService) ac.getBean("echoServiceProxy");
        String result = echoService.echo("One");
        System.out.println(me + " result: " + result);
    }
}
