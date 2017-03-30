package com.demo.service;

import org.springframework.stereotype.Component;

@Component
public class EchoService {

	public void addCustomer(){
		System.out.println("addCustomer() is running ");
	}
	
	public String addCustomerReturnValue(){
		System.out.println("addCustomerReturnValue() is running ");
		return "abc";
	}
	
	public void addCustomerThrowException() throws Exception {
		System.out.println("addCustomerThrowException() is running ");
		throw new Exception("Generic Error");
	}
	
	public String echo(String name){
		System.out.println("echo() is running, args : " + name);
        	return name;
	}
}