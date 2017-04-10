package com.demo;

import com.demo.service.EchoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class AppTest {
    @Autowired
    EchoService echoService;

    @Test
    public void testEchoService() {
        String me = "AppTest.testEchoService()";
        System.out.println(me + " begin");
        String result = echoService.echo("Two");
        System.out.println(me + " end: " + result);
        assertEquals("TWO", result);
    }
}
