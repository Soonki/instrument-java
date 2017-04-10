package com.demo;

import com.demo.serviceinterfaces.IEchoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class App {
    public static void main(String[] args) throws Exception {
        String me = "App.main()";
        System.out.println(me + " begin");
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        IEchoService echoService = ac.getBean(IEchoService.class);
        String result = echoService.echo("One");
        System.out.println(me + " end: " + result);
    }
}
