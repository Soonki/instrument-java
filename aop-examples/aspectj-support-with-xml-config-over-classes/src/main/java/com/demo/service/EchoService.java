package com.demo.service;

import org.springframework.stereotype.Component;

@Component
public class EchoService {
    public String echo(String name) {
        System.out.println("echo() is running, args : " + name);
        return name;
    }
}
