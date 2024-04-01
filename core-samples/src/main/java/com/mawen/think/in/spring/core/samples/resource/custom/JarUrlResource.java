package com.mawen.think.in.spring.core.samples.resource.custom;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.jar.JarFile;

import org.springframework.core.io.UrlResource;
import org.springframework.util.ResourceUtils;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/4/1
 */
public class JarUrlResource extends UrlResource {

	public JarUrlResource(URL url) {
		super(url);
	}

	public JarUrlResource(String location) throws MalformedURLException {
		super(ResourceUtils.URL_PROTOCOL_JAR, location);
	}
}
