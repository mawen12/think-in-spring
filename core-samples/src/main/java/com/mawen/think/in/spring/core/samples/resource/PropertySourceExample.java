package com.mawen.think.in.spring.core.samples.resource;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.jar.JarFile;

import com.mawen.think.in.spring.core.samples.resource.custom.JarUrlResource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.util.ResourceUtils;

/**
 * Load Property from classpath:application.properties
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/4/1
 */
@Configuration
public class PropertySourceExample {

	public static void main(String[] args) throws IOException {

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(PropertySourceExample.class);

		ctx.close();

//		loadFromClasspath();

//		loadFromJar(args[0]);
//		loadFromJar2(args[0]);
		loadFromJarByCustomImpl(args[0]);
	}

	public static void loadFromClasspath() throws IOException {
		ResourcePropertySource propertySource = new ResourcePropertySource("classpath:application.properties");
		Object hello = propertySource.getProperty("hello");
		System.out.println(hello);
	}

	public static void loadFromJar(String javaAgent) throws IOException {
		System.out.println("Load from : " + javaAgent);
		int equalsIndex = javaAgent.indexOf("=");
		if (equalsIndex == -1) {
			return;
		}

		String pathToUse = javaAgent.substring(equalsIndex + 1);
		System.out.println(pathToUse);

		UrlResource urlResource = new UrlResource(ResourceUtils.JAR_URL_PREFIX + ResourceUtils.FILE_URL_PREFIX + pathToUse + ResourceUtils.JAR_URL_SEPARATOR + "agent.properties");
		ResourcePropertySource propertySource = new ResourcePropertySource(urlResource);
		System.out.println(propertySource.getProperty("name"));
	}

	public static void loadFromJar2(String javaAgent) throws IOException {
		System.out.println("Load from : " + javaAgent);
		int equalsIndex = javaAgent.indexOf("=");
		if (equalsIndex == -1) {
			return;
		}

		String pathToUse = javaAgent.substring(equalsIndex + 1);
		System.out.println(pathToUse);

		UrlResource urlResource = new UrlResource(ResourceUtils.URL_PROTOCOL_JAR, ResourceUtils.FILE_URL_PREFIX + pathToUse + ResourceUtils.JAR_URL_SEPARATOR + "agent.properties");
		ResourcePropertySource propertySource = new ResourcePropertySource(urlResource);
		System.out.println(propertySource.getProperty("name"));
	}

	public static void loadFromJarByCustomImpl(String javaAgent) throws IOException {
		System.out.println("Load from : " + javaAgent);

		int equalsIndex = javaAgent.indexOf("=");
		if (equalsIndex == -1) {
			return;
		}

		String pathToUse = javaAgent.substring(equalsIndex + 1);
		System.out.println(pathToUse);

		UrlResource urlResource = new JarUrlResource(ResourceUtils.FILE_URL_PREFIX + pathToUse + ResourceUtils.JAR_URL_SEPARATOR + "agent.properties");
		PropertySource propertySource = new ResourcePropertySource(urlResource);
		System.out.println(propertySource.getProperty("name"));
	}
}
