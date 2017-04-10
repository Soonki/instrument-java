package com.demo.service;

import com.demo.serviceifx.IEchoService;
import org.springframework.stereotype.Component;

@Component
public class EchoService implements IEchoService {
    @Override
    public String echo(String name) {
        String me = "EchoService.echo()";
        System.out.println(me + " name: " + name);
        return name;
    }
}
