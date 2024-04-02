package com.mawen.think.in.spring.core.samples.env.custom;

import java.io.IOException;

import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.ResourcePropertySource;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/4/2
 */
public class CustomEnvironment extends StandardEnvironment {

	public static final String AGENT_RESOURCE_NAME = "custom";
	public static final String AGENT_PROPERTY_SOURCE_NAME = "custom.properties";

	@Override
	protected void customizePropertySources(MutablePropertySources propertySources) {
		// read property from AGENT_PROPERTY_SOURCE_NAME
		propertySources.addLast(getResourcePropertySource());

		// set system properties and system environment in StandardEnvironment
		super.customizePropertySources(propertySources);
	}

	public ResourcePropertySource getResourcePropertySource() {
		try {
			return new ResourcePropertySource(AGENT_RESOURCE_NAME, new ClassPathResource(AGENT_PROPERTY_SOURCE_NAME));
		}
		catch (IOException ex) {
			throw new RuntimeException("Cannot load resource from [classpath:" + AGENT_PROPERTY_SOURCE_NAME + "]", ex);
		}
	}
}
