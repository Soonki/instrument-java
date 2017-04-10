package com.demo.service;

import com.demo.serviceinterfaces.IEchoService;
import org.springframework.stereotype.Component;

@Component
public class EchoService implements IEchoService {
    @Override
    public String echo(String name) {
        System.out.println("echo() is running, args : " + name);
        return name;
    }
}
