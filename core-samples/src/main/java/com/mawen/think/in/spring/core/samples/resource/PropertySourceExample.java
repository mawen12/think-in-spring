package com.mawen.think.in.spring.core.samples.resource;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import com.mawen.think.in.spring.core.samples.resource.custom.JarUrlResource;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.SimpleCommandLinePropertySource;
import org.springframework.core.env.SystemEnvironmentPropertySource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.util.ResourceUtils;

/**
 * Load Property from classpath:application.properties
 *
 * <p>
 *     <ul>
 *         <li>classpath</li>
 *         <li>jar</li>
 *         <li>commandLine</li>
 *         <li>system properties</li>
 *         <li>system env</li>
 *     </ul>
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
		loadFromCommandLine(args);
		loadFromSystemProperties();
		loadFromSystemEnv();
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

	// --path=/opt/agent/agent-dept.jar
	// extract /opt/agent/agent-dept.jar!/agent.properties
	public static void loadFromJarByCustomImpl(String javaAgent) throws IOException {
		System.out.println("Load from : " + javaAgent);

		int equalsIndex = javaAgent.indexOf("=");
		if (equalsIndex == -1) {
			return;
		}

		String pathToUse = javaAgent.substring(equalsIndex + 1);
		System.out.println(pathToUse);

		UrlResource urlResource = new JarUrlResource(ResourceUtils.FILE_URL_PREFIX + pathToUse + ResourceUtils.JAR_URL_SEPARATOR + "agent.properties");
		PropertiesPropertySource propertySource = new ResourcePropertySource(urlResource);
		System.out.println(propertySource.getProperty("name"));
	}

	// --k1=v1 --k2=v2
	public static void loadFromCommandLine(String[] args) {
		System.out.println("Load from CommandLine");

		SimpleCommandLinePropertySource propertySource = new SimpleCommandLinePropertySource(args);
		System.out.println(propertySource.getProperty("k1"));
		System.out.println(propertySource.getProperty("k2"));
	}

	public static void loadFromSystemProperties() {
		System.out.println("Load from System Properties");

		SystemEnvironmentPropertySource propertySource = new SystemEnvironmentPropertySource("systemProps", (Map)System.getProperties());
		System.out.println(propertySource.getProperty("file.encoding"));
		System.out.println(propertySource.getProperty("java.version"));
	}

	public static void loadFromSystemEnv() {
		System.out.println("Load from System Environment");

		SystemEnvironmentPropertySource propertySource = new SystemEnvironmentPropertySource("systemEnv", (Map) System.getenv());
		System.out.println(propertySource.getProperty("JAVA_HOME"));
		System.out.println(propertySource.getProperty("java.home")); // SystemEnvironmentPropertySource can handle java.home to JAVA_HOME
		System.out.println(propertySource.getProperty("MAVEN_HOME"));
	}
}
