package com.wackywozniaks.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WWController {
	
	protected ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");

}
