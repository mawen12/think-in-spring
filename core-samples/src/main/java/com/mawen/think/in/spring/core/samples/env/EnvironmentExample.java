package com.mawen.think.in.spring.core.samples.env;

import com.mawen.think.in.spring.core.samples.env.custom.CustomEnvironment;

import org.springframework.core.env.StandardEnvironment;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/4/2
 */
public class EnvironmentExample {

	public static void main(String[] args) {
		createStandardEnvironment();
		createCustomEnvironment();
	}

	public static void createStandardEnvironment() {
		StandardEnvironment environment = new StandardEnvironment();

		System.out.println(environment.getProperty("java.home"));
		System.out.println(environment.getProperty("java.version"));
	}

	public static void createCustomEnvironment() {
		CustomEnvironment environment = new CustomEnvironment();

		System.out.println(environment.getProperty("java.home"));
		// src/main/resources/custom.properties override System Properties
		System.out.println(environment.getProperty("java.version"));
	}
}
