package com.demo;

import com.demo.service.EchoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class App {
    @Autowired EchoService echoService;

    public static void main(String[] args) {
        String me = "App.main()";
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
        App app = ac.getBean(App.class);
        System.out.println(me + " begin");
        String result = app.echoService.echo("One");
        System.out.println(me + " end: " + result);
    }
}
