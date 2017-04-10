package com.demo.service;

import org.springframework.stereotype.Component;

@Component
public class EchoService {
    public String echo(String name) {
        String me = "EchoService.echo()";
        System.out.println(me + " name: " + name);
        return name;
    }
}
