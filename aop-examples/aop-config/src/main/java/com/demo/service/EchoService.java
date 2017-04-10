package com.demo.service;

import org.springframework.stereotype.Component;

@Component
public class EchoService {
    public String echo(String name) {
        return name;
    }
}
