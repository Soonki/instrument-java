package com.demo.service;

import org.springframework.stereotype.Component;

@Component
public class UpperCaseBean {
    public String toUpperCase(String name) {
        String me = "UppercaseBean.toUpperCase()";
        System.out.println(me + " begin: " + name);
        String result = name.toUpperCase();
        System.out.println(me + " end: " + result);
        return result;
    }
}
