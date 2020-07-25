package com.in28minutes.jpa.hibernate.demo.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    protected void log(Object values) {
	StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();

//		System.out.println("################### " + stackTraceElements[2].getClassName());
//		System.out.println("################### " + stackTraceElements[2].getMethodName());

	logger.info("{}: {}", stackTraceElements[2].getClassName() + "." + stackTraceElements[2].getMethodName(),
		values);
    }
}