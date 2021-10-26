package com.rosenhristov.bank;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
public class BankApplication {

	private static ApplicationContext applicationContext;

	@Autowired
	public BankApplication(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
		displayBeans();
	}

	private static void displayBeans() {
		log.info("::: PRINTING ALL APPLICATION BEANS ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
		String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
		Arrays.stream(beanDefinitionNames)
			  .forEach(bean -> log.info("Bean: {}", bean));
		log.info("::: TOTALLY {} BEANS INITIALIZED ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::", beanDefinitionNames.length);
	}
}
