package com.demo;

import static org.junit.Assert.assertEquals;

import com.demo.service.EchoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class AppTest {

    @Autowired EchoService echoService;

    @Test
    public void testEchoService() {
        String me = "AppTest.testEchoService()";
        System.out.println(me + " call");
        String result = echoService.echo("Two");
        System.out.println(me + " done: " + result);
        assertEquals("TWO", result);
    }
}
