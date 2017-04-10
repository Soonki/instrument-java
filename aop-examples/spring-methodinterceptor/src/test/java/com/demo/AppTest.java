package com.demo;

import com.demo.service.EchoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext-test.xml"})
public class AppTest {

    @Resource(name = "echoServiceProxy")
    private EchoService echoService;

    @Test
    public void testEchoService() {
        String result = echoService.echo("Two");
        System.out.println(result);
        assertEquals("TWO", result);
    }
}
